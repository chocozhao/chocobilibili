package com.chocozhao.chocobilibili.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.chocozhao.chocobilibili.di.module.PartitionModule;
import com.chocozhao.chocobilibili.mvp.contract.PartitionContract;

import com.jess.arms.di.scope.FragmentScope;

import com.chocozhao.chocobilibili.mvp.ui.fragment.PartitionFragment;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 10/16/2019 09:59
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
@Component(modules = PartitionModule.class, dependencies = AppComponent.class)
public interface PartitionComponent {
    void inject(PartitionFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        PartitionComponent.Builder view(PartitionContract.View view);

        PartitionComponent.Builder appComponent(AppComponent appComponent);

        PartitionComponent build();
    }
}