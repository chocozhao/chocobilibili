package com.chocozhao.chocobilibili.mvp.presenter

import android.app.Application
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.chocozhao.chocobilibili.mvp.contract.CheckKotlinContract
import com.chocozhao.chocobilibili.mvp.model.entity.BaseResponse
import com.chocozhao.chocobilibili.mvp.model.entity.GetBannerData
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.utils.LogUtils
import com.jess.arms.utils.RxLifecycleUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import javax.inject.Inject


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 03/26/2020 09:49
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class CheckKotlinPresenter
@Inject
constructor(model: CheckKotlinContract.Model, rootView: CheckKotlinContract.View) :
        BasePresenter<CheckKotlinContract.Model, CheckKotlinContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler

    @Inject
    lateinit var mApplication: Application

    @Inject
    lateinit var mImageLoader: ImageLoader

    @Inject
    lateinit var mAppManager: AppManager

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        //打开 App 时自动加载列表
        requestBannerData()
    }

    fun requestBannerData() {
        mModel.getBanner()
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
//                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .subscribe(object : ErrorHandleSubscriber<BaseResponse<List<GetBannerData>>>(mErrorHandler) {
                    override fun onNext(t: BaseResponse<List<GetBannerData>>) {
                        TODO("Not yet implemented")
//                        if (t.isSuccess) {
                            LogUtils.debugInfo("isSuccess"+t.data.get(0).desc)
                            mRootView.getBanner(t.data)
//                        } else {
//                            mRootView.showMessage(t.errorMsg)
//                        }
                    }

                    override fun onError(t: Throwable) {
                        super.onError(t)

                    }
                })
    }

    override fun onDestroy() {
        super.onDestroy();
    }
}
