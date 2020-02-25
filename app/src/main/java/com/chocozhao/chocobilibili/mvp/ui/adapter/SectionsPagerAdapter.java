package com.chocozhao.chocobilibili.mvp.ui.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * .::::.
 * .::::::::.
 * :::::::::::
 * ..:::::::::::'
 * '::::::::::::'         What bad code is written to suck my vagina
 * .::::::::::          Fuck You,baby
 * '::::::::::::::..
 * ..::::::::::::.
 * ``::::::::::::::::
 * ::::``:::::::::'        .:::.
 * ::::'   ':::::'       .::::::::.
 * .::::'      ::::     .:::::::'::::.
 * .:::'       :::::  .:::::::::' ':::::.
 * .::'        :::::.:::::::::'      ':::::.
 * .::'         ::::::::::::::'         ``::::.
 * ...:::           ::::::::::::'              ``::.
 * ```` ':.          ':::::::::'                  ::::..
 * '.:::::'                    ':'````..
 * <p>
 * ClaseName：SectionsPagerAdapter
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2020/2/24 17:18
 * Modified By：
 * Fixtime：2020/2/24 17:18
 * FixDescription：
 **/

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments2;

    public SectionsPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments2 = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments2.get(position);
    }

    @Override
    public int getCount() {
        return fragments2.size();
    }

}
