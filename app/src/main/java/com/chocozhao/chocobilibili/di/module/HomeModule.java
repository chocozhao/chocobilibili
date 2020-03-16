package com.chocozhao.chocobilibili.di.module;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chocozhao.chocobilibili.mvp.contract.HomeContract;
import com.chocozhao.chocobilibili.mvp.model.HomeModel;
import com.chocozhao.chocobilibili.mvp.model.entity.GetArticleData;
import com.chocozhao.chocobilibili.mvp.model.entity.GetBannerData;
import com.chocozhao.chocobilibili.mvp.ui.adapter.ArticleAdapter;
import com.chocozhao.chocobilibili.mvp.ui.adapter.BannerHolderView.BannerNetWorkImageHolderView;
import com.jess.arms.di.scope.ActivityScope;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;


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
@Module
public abstract class HomeModule {

    @Binds
    abstract HomeContract.Model bindHomeModel(HomeModel model);


    @ActivityScope
    @Provides
    static List<GetBannerData> provideBanner() {
        return new ArrayList<>();
    }

    @ActivityScope
    @Provides
    static RxPermissions provideRxPermissions(HomeContract.View view) {
//        return new RxPermissions((FragmentActivity) view.getActivity());
        return new RxPermissions(view.getFragment());
    }

    @ActivityScope
    @Provides
    static RecyclerView.LayoutManager provideLayoutManager(HomeContract.View view) {
        return new LinearLayoutManager(view.getActivity());
    }

    @ActivityScope
    @Provides
    static List<GetArticleData.DatasBean> provideArticleList() {
        return new ArrayList<>();
    }

    @ActivityScope
    @Provides
    static ArticleAdapter provideArticleAdapter(List<GetArticleData.DatasBean> list) {
        return new ArticleAdapter(list);
    }

    @ActivityScope
    @Provides
    static View provideView(HomeContract.View view) {
        return new View(view.getActivity());
    }
    @ActivityScope
    @Provides
    static BannerNetWorkImageHolderView provideBannerNetWorkImageHolderView(View view) {
        return new BannerNetWorkImageHolderView(view);
    }
}