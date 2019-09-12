package com.tsyc.tianshengyoucai.ui.activity.login;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.pixelcan.inkpageindicator.InkPageIndicator;
import com.tsyc.tianshengyoucai.MainActivity;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * 创 建 者：van
 * 创建日期：2019/6/21.
 * 描    述： 引导页
 * 修改描述：
 * 修改日期：
 */
public class GuideActivity extends BaseActivity {

   private int[] imgTRes = new int[]{R.mipmap.logo, R.mipmap.logo, R.mipmap.logo};


    private List<View> mTViewList = new ArrayList<>();
    private ViewPager vpTop;
    private Button mBtnStart;

    private InkPageIndicator mIndicator;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_guide;
    }

    @Override
    public void initView() {
        getSwipeBackLayout().setEnableGesture(false);
        vpTop = (ViewPager) findViewById(R.id.vp_top);
        mBtnStart = (Button) findViewById(R.id.btn_start);
        mIndicator = (InkPageIndicator) findViewById(R.id.indicator);
        mBtnStart.setOnClickListener(v -> {
            openActivity(LoginActivity.class);
            finish();
        });

    }

    @Override
    public void initData() {

        for (int imgTRe : imgTRes) {
            View inflate = getLayoutInflater().inflate(R.layout.guide_item, null);
            ImageView ivGuide = inflate.findViewById(R.id.iv_guide);
            ivGuide.setBackgroundResource(imgTRe);
            mTViewList.add(inflate);
        }

        vpTop.setAdapter(new MyPagerAdapter());

        vpTop.setOnPageChangeListener(new PagePositionLister());
        mIndicator.setViewPager(vpTop);
    }

    /**
     * viewPager适配器
     */
    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mTViewList == null ? 0 : mTViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mTViewList.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mTViewList.get(position));
        }

    }
        private class PagePositionLister implements ViewPager.OnPageChangeListener {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //如果滑动到最后一张,显示按钮
                if (position == mTViewList.size() - 1) {
                    mBtnStart.setVisibility(View.VISIBLE);
                } else {
                    mBtnStart.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        }

}
