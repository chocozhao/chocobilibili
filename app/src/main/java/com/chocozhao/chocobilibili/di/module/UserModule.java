/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.chocozhao.chocobilibili.di.module;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jess.arms.di.scope.ActivityScope;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import com.chocozhao.chocobilibili.mvp.contract.UserContract;
import com.chocozhao.chocobilibili.mvp.model.UserModel;
import com.chocozhao.chocobilibili.mvp.model.entity.User;
import com.chocozhao.chocobilibili.mvp.ui.adapter.UserAdapter;

/**
 * ================================================
 * 展示 Module 的用法
 *
 * @see <a href="https://github.com/JessYanCoding/MVPArms/wiki#2.4.5">Module wiki 官方文档</a>
 * Created by JessYan on 09/04/2016 11:10
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
@Module
public abstract class UserModule {

    @Binds
    abstract UserContract.Model bindUserModel(UserModel model);

    @ActivityScope
    @Provides
    static RxPermissions provideRxPermissions(UserContract.View view) {
        return new RxPermissions((FragmentActivity) view.getActivity());
    }

    @ActivityScope
    @Provides
    static RecyclerView.LayoutManager provideLayoutManager(UserContract.View view) {
        return new GridLayoutManager(view.getActivity(), 2);
    }

    @ActivityScope
    @Provides
    static List<User> provideUserList() {
        return new ArrayList<>();
    }

    @ActivityScope
    @Provides
    static RecyclerView.Adapter provideUserAdapter(List<User> list){
        return new UserAdapter(list);
    }
}
