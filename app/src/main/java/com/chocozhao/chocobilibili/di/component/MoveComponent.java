package com.chocozhao.chocobilibili.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.chocozhao.chocobilibili.di.module.MoveModule;
import com.chocozhao.chocobilibili.mvp.contract.MoveContract;

import com.jess.arms.di.scope.FragmentScope;

import com.chocozhao.chocobilibili.mvp.ui.fragment.MoveFragment;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 10/16/2019 10:01
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
@Component(modules = MoveModule.class, dependencies = AppComponent.class)
public interface MoveComponent {
    void inject(MoveFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        MoveComponent.Builder view(MoveContract.View view);

        MoveComponent.Builder appComponent(AppComponent appComponent);

        MoveComponent build();
    }
}