package com.chocozhao.chocobilibili.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.chocozhao.chocobilibili.R;
import com.chocozhao.chocobilibili.di.component.DaggerMainComponent;
import com.chocozhao.chocobilibili.mvp.contract.MainContract;
import com.chocozhao.chocobilibili.mvp.presenter.MainPresenter;
import com.chocozhao.chocobilibili.mvp.ui.adapter.SectionsPagerAdapter;
import com.chocozhao.chocobilibili.mvp.ui.fragment.HomeFragment;
import com.chocozhao.chocobilibili.mvp.ui.fragment.MessageFragment;
import com.chocozhao.chocobilibili.mvp.ui.fragment.MoveFragment;
import com.chocozhao.chocobilibili.mvp.ui.fragment.PartitionFragment;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;


    private List<Fragment> fragmentList = new ArrayList<>();
    private int curFragment = -1;
    private String[] mTitles;

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
//        initFragment();
//      初始化底部导航栏
        initBottomNavigationBar();
        initViewPager();

    }

    private void initFragment() {
        fragmentList.add(new HomeFragment());
        fragmentList.add(new PartitionFragment());
        fragmentList.add(new MoveFragment());
        fragmentList.add(new MessageFragment());
    }

    private void initViewPager() {
        fragmentList.clear();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new PartitionFragment());
        fragmentList.add(new MoveFragment());
        fragmentList.add(new MessageFragment());
        mViewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager(), fragmentList));
//        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(mTitles.length);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomNavigationBar.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    /*初始化底部导航栏*/
    private void initBottomNavigationBar() {
        mTitles = new String[]{getString(R.string.title_home), getString(R.string.title_partition),
                getString(R.string.title_move), getString(R.string.title_message)};
        mBottomNavigationBar
                .setActiveColor(R.color.black)
                .setInActiveColor(R.color.gray)
                .setBarBackgroundColor(R.color.white);

        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);

        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.tabs_icon_home_press, mTitles[0])
                        .setInactiveIcon(ContextCompat.getDrawable(this, R.drawable.tabs_icon_home_normal)))
                .addItem(new BottomNavigationItem(R.drawable.tabs_icon_partition_press, mTitles[1])
                        .setInactiveIcon(ContextCompat.getDrawable(this, R.drawable.tabs_icon_partition_normal)))
                .addItem(new BottomNavigationItem(R.drawable.tabs_icon_move_press, mTitles[2])
                        .setInactiveIcon(ContextCompat.getDrawable(this, R.drawable.tabs_icon_move_normal)))
                .addItem(new BottomNavigationItem(R.drawable.tabs_icon_person_press, mTitles[3])
                        .setInactiveIcon(ContextCompat.getDrawable(this, R.drawable.tabs_icon_person_normal)))
                .setFirstSelectedPosition(0)
                .initialise();//所有的设置需在调用该方法前完成```

//这里也可以使用SimpleOnTabSelectedListener
        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {//未选中 -> 选中
//                switchTab(position);
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(int position) {//选中 -> 未选中


            }

            @Override
            public void onTabReselected(int position) {//选中 -> 选中

            }
        });

    }

    private void switchTab(int position) {
       /* FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentByTag("" + position);
        FragmentTransaction beginTransaction = manager.beginTransaction();
        if (fragment == null) {
            if (manager.findFragmentByTag("" + curFragment) != null) {
                beginTransaction.hide(fragmentList.get(curFragment));
            }
            beginTransaction.add(R.id.fram_layout, fragmentList.get(position), "" + position)
                    .show(fragmentList.get(position))
                    .commit();


        } else if (curFragment != position) {
            beginTransaction.hide(fragmentList.get(curFragment))
                    .show(fragmentList.get(position))
                    .commit();
        }
        curFragment = position;*/
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
