package com.chocozhao.chocobilibili.mvp.model;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import com.chocozhao.chocobilibili.mvp.contract.HomeContract;
import com.chocozhao.chocobilibili.mvp.model.api.cache.CommonCache;
import com.chocozhao.chocobilibili.mvp.model.api.service.UserService;
import com.chocozhao.chocobilibili.mvp.model.entity.BaseResponse;
import com.chocozhao.chocobilibili.mvp.model.entity.GetArticleData;
import com.chocozhao.chocobilibili.mvp.model.entity.GetBannerData;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.rx_cache2.DynamicKey;
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
public class HomeModel extends BaseModel implements HomeContract.Model {

    public static final int USERS_PER_PAGE = 10;
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public HomeModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    //获取轮播图数据
    @Override
    public Observable<BaseResponse<List<GetBannerData>>> getBanner() {
        return mRepositoryManager
                .obtainRetrofitService(UserService.class)
                .getBanner();
    }

    //获取文章列表
    @Override
    public Observable<BaseResponse<List<GetArticleData>>> getArticle(int lastIdQueried, boolean update) {
        return mRepositoryManager
                .obtainCacheService(UserService.class)
                .getArticle(lastIdQueried);

    }


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause() {
        Timber.d("Release Resource");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}