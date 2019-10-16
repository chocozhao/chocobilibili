package com.chocozhao.chocobilibili.di.module;

import dagger.Binds;
import dagger.Module;

import com.chocozhao.chocobilibili.mvp.contract.MessageContract;
import com.chocozhao.chocobilibili.mvp.model.MessageModel;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 10/16/2019 10:02
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
public abstract class MessageModule {

    @Binds
    abstract MessageContract.Model bindMessageModel(MessageModel model);
}