package com.chocozhao.chocobilibili.mvp.presenter;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.v7.widget.RecyclerView;

import com.chocozhao.chocobilibili.mvp.contract.HomeContract;
import com.chocozhao.chocobilibili.mvp.model.entity.BaseResponse;
import com.chocozhao.chocobilibili.mvp.model.entity.GetArticleData;
import com.chocozhao.chocobilibili.mvp.model.entity.GetBannerData;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import timber.log.Timber;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 10/16/2019 09:48
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class HomePresenter extends BasePresenter<HomeContract.Model, HomeContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;
    @Inject
    List<GetBannerData> mBannerData;
    @Inject
    List<GetArticleData> mArticleData;
    @Inject
    RecyclerView.Adapter mAdapter;
    private int lastUserId = 1;
    private boolean isFirst = true;
    private int preEndIndex;
    @Inject
    public HomePresenter(HomeContract.Model model, HomeContract.View rootView) {
        super(model, rootView);
    }

    //Fragment使用ON_START   Avtivity使用ON_CREATE
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onCreate() {
        requestBannerData();//打开 App 时自动加载列表
        requestArticle(true);
    }

    public void requestBannerData() {
        mModel.getBanner()
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse<List<GetBannerData>>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseResponse<List<GetBannerData>> listBaseResponse) {
                        if (listBaseResponse.isSuccess()) {
//                        mRootView.setBanner(listBaseResponse.getData());
                        Timber.d("P:mDataList=" + listBaseResponse);
                        mBannerData.addAll(listBaseResponse.getData());
                        } else {
                            mRootView.showMessage(listBaseResponse.getMsg());
                        }
                    }
                });
    }


    public void requestArticle(final boolean pullToRefresh) {
        requestFromModel(pullToRefresh);
        //请求外部存储权限用于适配android6.0的权限管理机制
        /*PermissionUtil.externalStorage(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {
                //request permission success, do something.
                requestFromModel(pullToRefresh);
            }

            @Override
            public void onRequestPermissionFailure(List<String> permissions) {
                mRootView.showMessage("Request permissions failure");
                mRootView.hideLoading();//隐藏下拉刷新的进度条
            }

            @Override
            public void onRequestPermissionFailureWithAskNeverAgain(List<String> permissions) {
                mRootView.showMessage("Need to go to the settings");
                mRootView.hideLoading();//隐藏下拉刷新的进度条
            }
        }, mRootView.getRxPermissions(), mErrorHandler);*/
    }

    private void requestFromModel(boolean pullToRefresh) {
        if (pullToRefresh) {
            lastUserId = 1;//下拉刷新默认只请求第一页
        }

        //关于RxCache缓存库的使用请参考 http://www.jianshu.com/p/b58ef6b0624b

        //是否驱逐缓存,为ture即不使用缓存,每次下拉刷新即需要最新数据,则不使用缓存
        boolean isEvictCache = pullToRefresh;

        //默认在第一次下拉刷新时使用缓存
        if (pullToRefresh && isFirst) {
            isFirst = false;
            isEvictCache = false;
        }

        mModel.getArticle(lastUserId, isEvictCache)
                .subscribeOn(Schedulers.io())
                //遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .retryWhen(new RetryWithDelay(3, 2))
                .doOnSubscribe(disposable -> {
                    if (pullToRefresh) {
                        mRootView.showLoading();//显示下拉刷新的进度条
                    } else {
                        mRootView.startLoadMore();//显示上拉加载更多的进度条
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    if (pullToRefresh) {
                        mRootView.hideLoading();//隐藏下拉刷新的进度条
                    } else {
                        mRootView.endLoadMore();//隐藏上拉加载更多的进度条
                    }
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .subscribe(new ErrorHandleSubscriber<BaseResponse<List<GetArticleData>>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseResponse<List<GetArticleData>> articleData) {
                        if (articleData.isSuccess()) {
                            lastUserId = articleData.getData().get(articleData.getData().size() - 1).getData().getCurPage();//记录最后一个id,用于下一次请求
                            if (pullToRefresh) {
                                mArticleData.clear();//如果是下拉刷新则清空列表
                            }
                            preEndIndex = mArticleData.size();//更新之前列表总长度,用于确定加载更多的起始位置
                            mArticleData.addAll(articleData.getData());
                            if (pullToRefresh) {
                                mAdapter.notifyDataSetChanged();
                            } else {
                                mAdapter.notifyItemRangeInserted(preEndIndex, articleData.getData().size());
                            }
                        } else {
                            articleData.getMsg();
                        }

                    }
                });
    }


    @Override
    public void onDestroy() {

        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
}
