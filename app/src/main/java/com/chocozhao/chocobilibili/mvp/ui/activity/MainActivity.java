package com.chocozhao.chocobilibili.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import com.chocozhao.chocobilibili.R;
import com.chocozhao.chocobilibili.di.component.DaggerMainComponent;
import com.chocozhao.chocobilibili.mvp.contract.MainContract;
import com.chocozhao.chocobilibili.mvp.presenter.MainPresenter;
import com.chocozhao.chocobilibili.mvp.ui.fragment.HomeFragment;
import com.chocozhao.chocobilibili.mvp.ui.fragment.MessageFragment;
import com.chocozhao.chocobilibili.mvp.ui.fragment.MoveFragment;
import com.chocozhao.chocobilibili.mvp.ui.fragment.PartitionFragment;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 10/15/2019 17:26
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        fragmentList.add(new HomeFragment());
        fragmentList.add(new PartitionFragment());
        fragmentList.add(new MoveFragment());
        fragmentList.add(new MessageFragment());
//      初始化底部导航栏
        initBottomNavigationBar();
//      初始化ViewPager
        initViewPager();



    }

    /*初始化底部导航栏*/
    private void initBottomNavigationBar() {
        String[] mTitles = new String[]{getString(R.string.title_home), getString(R.string.title_partition),
                getString(R.string.title_move), getString(R.string.title_message)};
        bottomNavigationBar
                .setActiveColor(R.color.black)
                .setInActiveColor(R.color.gray)
                .setBarBackgroundColor(R.color.white);

        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, mTitles[0])
                        .setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.ic_launcher)))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, mTitles[1])
                        .setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.ic_launcher)))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, mTitles[2])
                        .setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.ic_launcher)))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, mTitles[3])
                        .setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.ic_launcher)))
                .setFirstSelectedPosition(0)
                .initialise();//所有的设置需在调用该方法前完成```

        //这里也可以使用SimpleOnTabSelectedListener
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {//未选中 -> 选中
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(int position) {//选中 -> 未选中


            }

            @Override
            public void onTabReselected(int position) {//选中 -> 选中

            }
        });
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        viewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager(), fragmentList));
        viewPager.setCurrentItem(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationBar.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> fragmentList;

        public SectionsPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragmentList = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }
}
