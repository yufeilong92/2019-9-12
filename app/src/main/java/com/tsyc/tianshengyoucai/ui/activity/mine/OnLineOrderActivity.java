package com.tsyc.tianshengyoucai.ui.activity.mine;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.animation.AccelerateInterpolator;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.adapter.ViewPagerAdapter;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.fragment.sub.order.OnlineOrderFragment;
import com.tsyc.tianshengyoucai.utils.ScreenUtils;
import com.tsyc.tianshengyoucai.view.indicator.CommonPagerIndicator;
import com.tsyc.tianshengyoucai.view.indicator.MClipIndicatorTitleView;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author：van
 * CreateTime：2019/7/27
 * File description： 线上订单
 */
public class OnLineOrderActivity extends BaseActivity {

    @BindView(R.id.indicator)
    MagicIndicator indicator;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private String[] titles;


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_underline_order;
    }

    @Override
    public void initView() {
        mTvTitle.setText(getString(R.string.on_line_order));


    }

    @Override
    public void initData() {

        List<Fragment> fragments = new ArrayList<>();
        titles = new String[]{"全部", "待付款", "待发货", "待收货", "待评价", "退款"};
        fragments.clear();
//        fragments.add(new OnlineAllFragment());
//        fragments.add(new OnlineWpFragment());
//        fragments.add(new OnlineWSFragment());
//        fragments.add(new OnlineWrFragment());
//        fragments.add(new OnlineWeFragment());
//        fragments.add(new OnlineWbFragment());
        for (int i = 0; i < titles.length; i++) {
            OnlineOrderFragment orderFragment = new OnlineOrderFragment();

            Bundle bundle = new Bundle();
            bundle.putString("title", titles[i]);
            bundle.putString("position", String.valueOf(i));
            orderFragment.setArguments(bundle);
            fragments.add(orderFragment);
        }
        initIndicator();

        ViewPagerAdapter mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(pageListener);

        Bundle extra = getIntent().getBundleExtra("extra");
        if (null != extra) {
            String pageIndex = extra.getString("pageIndex");
            mViewPager.setCurrentItem(Integer.valueOf(pageIndex), true);
            indicator.onPageSelected(Integer.valueOf(pageIndex));
        }
    }

    private void initIndicator() {
        CommonNavigator mCommonNavigator = new CommonNavigator(this);
        mCommonNavigator.setSkimOver(true);
        mCommonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {

                //MIndicatorTitleView titleView = new MIndicatorTitleView(context);

                MClipIndicatorTitleView clipPagerTitleView = new MClipIndicatorTitleView(context);
                clipPagerTitleView.setText(titles[index]);
                clipPagerTitleView.setTextSize(ScreenUtils.sp2px(context, 16));
                // 设置字体选中和非选中的颜色
                clipPagerTitleView.setClipColor(getResources().getColor(R.color.tab_color));
                clipPagerTitleView.setTextColor(getResources().getColor(R.color.color_7B8391));
                clipPagerTitleView.setOnClickListener(v -> mViewPager.setCurrentItem(index));

                return clipPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                CommonPagerIndicator commonPagerIndicator = new CommonPagerIndicator(context);
                Drawable drawable = context.getResources().getDrawable(R.mipmap.jft_but_switch);
                commonPagerIndicator.setIndicatorDrawable(drawable);
                commonPagerIndicator.setDrawableHeight(UIUtil.dip2px(context, 4));
                commonPagerIndicator.setDrawableWidth(UIUtil.dip2px(context, 35));
                commonPagerIndicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                commonPagerIndicator.setStartInterpolator(new AccelerateInterpolator());

                return commonPagerIndicator;
            }
        });
        indicator.setNavigator(mCommonNavigator);

    }

    /**
     * ViewPager切换监听方法
     */
    public ViewPager.OnPageChangeListener pageListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrollStateChanged(int state) {
            indicator.onPageScrollStateChanged(state);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            indicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }

        @Override
        public void onPageSelected(int position) {
            mViewPager.setCurrentItem(position);
            indicator.onPageSelected(position);
        }
    };

}
