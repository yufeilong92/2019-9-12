package com.tsyc.tianshengyoucai.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 创 建 者：van
 * 创建日期：2018/11/6.
 * 描    述： 不能左右滑动的ViewPager
 * 修改描述：
 * 修改日期：
 */
public class NoScrollViewPager extends ViewPager {
    public NoScrollViewPager(@NonNull Context context) {
        super(context);
    }

    public NoScrollViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return false;
    }

    /**
     * 不拦截事件
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return false;
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        return super.dispatchTouchEvent(ev);
//    }
}
