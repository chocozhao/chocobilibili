package com.chocozhao.chocobilibili.di.module;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chocozhao.chocobilibili.mvp.contract.HomeContract;
import com.chocozhao.chocobilibili.mvp.model.HomeModel;
import com.chocozhao.chocobilibili.mvp.model.entity.GetArticleData;
import com.chocozhao.chocobilibili.mvp.model.entity.GetBannerData;
import com.chocozhao.chocobilibili.mvp.ui.adapter.ArticleAdapter;
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
        return new RxPermissions((FragmentActivity) view.getActivity());
    }

    @ActivityScope
    @Provides
    static RecyclerView.LayoutManager provideLayoutManager(HomeContract.View view) {
        return new GridLayoutManager(view.getActivity(), 2);
    }

    @ActivityScope
    @Provides
    static List<GetArticleData> provideArticleList() {
        return new ArrayList<>();
    }

    @ActivityScope
    @Provides
    static RecyclerView.Adapter provideArticleAdapter(List<GetArticleData> list) {
        return new ArticleAdapter(list);
    }

}