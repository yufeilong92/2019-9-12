package com.tsyc.tianshengyoucai.ui.activity.mine.shop;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.model.adapter.ViewPagerAdapter;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseFragment;
import com.tsyc.tianshengyoucai.ui.fragment.sub.CashRecordFragment;
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
 * CreateTime：2019/8/8
 * File description：提现记录
 */
public class CashRecordActivity extends BaseActivity {


    @BindView(R.id.indicator)
    MagicIndicator indicator;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    private static String[] titles;
    private List<Fragment> fragments = new ArrayList<>();
    private static String type;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_release_list;
    }

    @Override
    public void initView() {
        mTvTitle.setText(getString(R.string.text_cash_record));

        Bundle extra = getIntent().getBundleExtra(Constant.bundleExtra);
        if (null != extra) {
            type = extra.getString("type");
            if (type.equals("yue") || type.equals("yongjin")) {
                titles = new String[]{"审核中", "已到帐", "审核失败"};
            } else {
                titles = new String[]{"待审核", "待打款", "已完成"};
            }
        }
    }

    @Override
    public void initData() {


        initIndicator();
        fragments.clear();
        for (int i = 0; i < titles.length; i++) {
            CashRecordFragment orderFragment = new CashRecordFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title", titles[i]);
            bundle.putString("position", String.valueOf(i + 1));
            bundle.putString("type", type);
            orderFragment.setArguments(bundle);
            fragments.add(orderFragment);
        }

        ViewPagerAdapter mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(pageListener);
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
        mCommonNavigator.setAdjustMode(true); // 平分
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
