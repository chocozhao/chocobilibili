package com.chocozhao.chocobilibili.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.chocozhao.chocobilibili.mvp.contract.CheckKotlinContract
import com.chocozhao.chocobilibili.mvp.model.CheckKotlinModel


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
@Module
//构建CheckKotlinModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class CheckKotlinModule(private val view: CheckKotlinContract.View) {
    @ActivityScope
    @Provides
    fun provideCheckKotlinView(): CheckKotlinContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideCheckKotlinModel(model: CheckKotlinModel): CheckKotlinContract.Model {
        return model
    }
}
