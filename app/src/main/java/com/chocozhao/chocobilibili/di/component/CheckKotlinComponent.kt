package com.chocozhao.chocobilibili.di.component

import com.chocozhao.chocobilibili.di.module.CheckKotlinModule
import com.chocozhao.chocobilibili.mvp.ui.activity.CheckKotlinActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import dagger.Component


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
@Component(modules = arrayOf(CheckKotlinModule::class), dependencies = arrayOf(AppComponent::class))
interface CheckKotlinComponent {
    fun inject(activity: CheckKotlinActivity)
}
