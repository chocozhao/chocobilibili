package com.chocozhao.chocobilibili.mvp.model

import android.app.Application
import com.chocozhao.chocobilibili.mvp.contract.CheckKotlinContract
import com.chocozhao.chocobilibili.mvp.model.api.service.UserService
import com.chocozhao.chocobilibili.mvp.model.entity.BaseResponse
import com.chocozhao.chocobilibili.mvp.model.entity.GetBannerData
import com.google.gson.Gson
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
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
class CheckKotlinModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), CheckKotlinContract.Model {
    @Inject
    lateinit var mGson: Gson;

    @Inject
    lateinit var mApplication: Application;

    override fun getBanner() : Observable<BaseResponse<List<GetBannerData>>> {
        return mRepositoryManager
                .obtainRetrofitService(UserService::class.java)
                .banner

    }
    override fun onDestroy() {
        super.onDestroy();
    }
}
