package com.tsyc.tianshengyoucai.ui.activity.home;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.animation.AccelerateInterpolator;

import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.flyn.Eyes;
import com.tsyc.tianshengyoucai.model.adapter.ViewPagerAdapter;
import com.tsyc.tianshengyoucai.model.bean.ShopNewsBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.fragment.sub.ShopNewsFragment;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.ScreenUtils;
import com.tsyc.tianshengyoucai.view.indicator.CommonPagerIndicator;
import com.tsyc.tianshengyoucai.view.indicator.MClipIndicatorTitleView;
import com.youth.xframe.widget.XToast;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author：van
 * CreateTime：2019/8/20
 * File description：店铺咨询
 */
public class ShopNewsActivity extends BaseActivity {

    @BindView(R.id.indicator)
    MagicIndicator indicator;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private List<String> tabList;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_release_list;
    }

    @Override
    public void initView() {
        mTvTitle.setText("商业资讯");
        Eyes.setStatusBarColor(this,R.color.bg_color);
        requestTabData();
    }

    //请求分类
    private void requestTabData() {
        loading(false);
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.getTypeList, null, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                dismiss();
                ShopNewsBean shopNewsBean = FastJsonUtil.fromJson(response.body(), ShopNewsBean.class);
                if (shopNewsBean == null) {
                    return;
                }

                if (!shopNewsBean.getCode().equals(Constant.REQUEST_SUCCESS)) {
                    XToast.normal(shopNewsBean.getMessage());
                    return;
                }

                if (shopNewsBean.getResult() == null) {
                    XToast.normal(getString(R.string.service_error));
                    return;
                }

                List<ShopNewsBean.ResultBean> result = shopNewsBean.getResult();
                tabList = new ArrayList<>();
                List<String> tabIdsList = new ArrayList<>();
                for (int i = 0; i < result.size(); i++) {
                    tabList.add(result.get(i).getAc_name());
                    tabIdsList.add(String.valueOf(result.get(i).getAc_id()));
                }

                initTabData(tabList, tabIdsList);

            }

            @Override
            public void failed(Response<String> response) {
                dismiss();
                XToast.normal(getString(R.string.service_error));
            }
        });
    }

    private void initTabData(List<String> tabList, List<String> tabIdsList) {

        List<Fragment> fragments = new ArrayList<>();
        fragments.clear();
        for (int i = 0; i < tabList.size(); i++) {
            ShopNewsFragment shopNewsFragment = new ShopNewsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title", tabList.get(i));
            bundle.putString("position", tabIdsList.get(i));
            shopNewsFragment.setArguments(bundle);
            fragments.add(shopNewsFragment);
        }

        initIndicator();

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

                return tabList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {

                MClipIndicatorTitleView clipPagerTitleView = new MClipIndicatorTitleView(context);
                clipPagerTitleView.setText(tabList.get(index));
                clipPagerTitleView.setTextSize(ScreenUtils.sp2px(context, 16));
                // 设置字体选中和非选中的颜色
                clipPagerTitleView.setClipColor(getResources().getColor(R.color.tab_color));
                clipPagerTitleView.setTextColor(getResources().getColor(R.color.color_7B8391));
                clipPagerTitleView.setOnClickListener(v -> mViewPager.setCurrentItem(index));

                return clipPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                CommonPagerIndicator indicator = new CommonPagerIndicator(context);
                Drawable drawable = context.getResources().getDrawable(R.mipmap.jft_but_switch);
                indicator.setIndicatorDrawable(drawable);
                indicator.setDrawableHeight(UIUtil.dip2px(context, 4));
                indicator.setDrawableWidth(UIUtil.dip2px(context, 35));
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setStartInterpolator(new AccelerateInterpolator());
                return indicator;
            }
        });
        mCommonNavigator.setAdjustMode(false); // 平分
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
