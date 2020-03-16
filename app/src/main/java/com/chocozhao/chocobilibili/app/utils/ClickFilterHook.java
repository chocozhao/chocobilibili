package com.chocozhao.chocobilibili.app.utils;

import android.view.View;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import timber.log.Timber;


/**
 * ClaseName：ClickFilterHook
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/4/11 9:29
 * Modified By：
 * Fixtime：2019/4/11 9:29
 * FixDescription：
 */
@Aspect
public class ClickFilterHook {
    private static Long sLastclick = 0L;
    private static final Long FILTER_TIMEM = 1000L;
    private View mLastView;

    @Around("execution(* android.view.View.OnClickListener.onClick(..))")
    public void clickFilterHook(ProceedingJoinPoint joinPoint) {
        Object[] objects = joinPoint.getArgs();
        View view = objects.length == 0 ? null : (View) objects[0];
        if (view != mLastView || System.currentTimeMillis() - sLastclick >= FILTER_TIMEM) {
            sLastclick = System.currentTimeMillis();
            mLastView = view;
            try {
                joinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        } else {
            Timber.d("重复点击,已过滤");
        }
    }
}
