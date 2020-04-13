package com.chocozhao.chocobilibili.mvp.ui.activity

import android.content.Intent
import android.os.Bundle

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.chocozhao.chocobilibili.di.component.DaggerCheckKotlinComponent
import com.chocozhao.chocobilibili.di.module.CheckKotlinModule
import com.chocozhao.chocobilibili.mvp.contract.CheckKotlinContract
import com.chocozhao.chocobilibili.mvp.presenter.CheckKotlinPresenter

import com.chocozhao.chocobilibili.R
import com.chocozhao.chocobilibili.mvp.model.entity.GetBannerData
import com.jess.arms.utils.LogUtils
import kotlinx.android.synthetic.main.activity_check_kotlin.*


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
/**
 * 如果没presenter
 * 你可以这样写
 *
 * @ActivityScope(請注意命名空間) class NullObjectPresenterByActivity
 * @Inject constructor() : IPresenter {
 * override fun onStart() {
 * }
 *
 * override fun onDestroy() {
 * }
 * }
 */
class CheckKotlinActivity : BaseActivity<CheckKotlinPresenter>(), CheckKotlinContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerCheckKotlinComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .checkKotlinModule(CheckKotlinModule(this))
                .build()
                .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_check_kotlin //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
//        mPresenter!!.requestBannerData()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }

    override fun launchActivity(intent: Intent) {
        ArmsUtils.startActivity(intent)
    }

    override fun killMyself() {
        finish()
    }

    override fun getBanner(getList : List<GetBannerData>) {
        LogUtils.debugInfo("getBanner:"+getList.get(0).title)
        text.text = getList.get(0).desc

    }
}
