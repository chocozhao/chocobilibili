package com.chocozhao.chocobilibili.mvp.ui.widget.ViewPager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * ClaseName：CustomViewPager
 * Description：自定义ViewPager
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/5/12 14:48
 * Modified By：
 * Fixtime：2017/5/12 14:48
 * FixDescription：
 */

public class CustomViewPager extends ViewPager {
    private boolean enabled = false;
    private ViewPageHelper helper;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        helper = new ViewPageHelper(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 触摸事件不触发
        if (this.enabled) {
            return super.onTouchEvent(event);
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        // 不处理触摸拦截事件
        return this.enabled && super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        // 分发事件，这个是必须要的，如果把这个方法覆盖了，那么ViewPager的子View就接收不到事件了
        if (this.enabled) {
            return super.dispatchTouchEvent(event);
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public void setCurrentItem(int item) {
        setCurrentItem(item, true);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        MScroller scroller = helper.getScroller();
        if (Math.abs(getCurrentItem() - item) > 1) {
            scroller.setNoDuration(true);
            super.setCurrentItem(item, smoothScroll);
            scroller.setNoDuration(false);
        } else {
            scroller.setNoDuration(false);
            super.setCurrentItem(item, smoothScroll);
        }
    }

    public void setPagingEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
