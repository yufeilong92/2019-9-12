package com.tsyc.tianshengyoucai.ui.activity.home;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.listener.event.UnifiedNotifyEvent;
import com.tsyc.tianshengyoucai.model.adapter.ViewPagerAdapter;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.PayResultActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseFragment;
import com.tsyc.tianshengyoucai.ui.fragment.sub.ShopManageFragment;
import com.tsyc.tianshengyoucai.ui.fragment.sub.order.CreateOnlineOrderFragment;
import com.tsyc.tianshengyoucai.ui.fragment.sub.order.CreateSelfOrderFragment;
import com.tsyc.tianshengyoucai.utils.ScreenUtils;
import com.tsyc.tianshengyoucai.view.indicator.CommonPagerIndicator;
import com.tsyc.tianshengyoucai.view.indicator.MClipIndicatorTitleView;
import com.youth.xframe.utils.log.XLog;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author：van
 * CreateTime：2019/8/10
 * File description：创建订单
 */
public class CreateOrderActivity extends BaseActivity {


    @BindView(R.id.indicator)
    MagicIndicator indicator;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    //    private String[] titles = {"线上物流"};
    private String[] titles = {"线上物流", "到店自取"};
    private List<Fragment> fragments = new ArrayList<>();
    private String goodsId;
    private String goodsSpec;
    private String goodsNum;
    private String taoInviteCode;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_release_list;
    }

    @Override
    public void initView() {
        mTvTitle.setText("提交订单");
        registerEventBus(this);

    }

    @Override
    public void initData() {
        initIndicator();
        fragments.clear();

        Bundle extra = getIntent().getBundleExtra(Constant.bundleExtra);
        if (null != extra) {
            goodsId = extra.getString("goods_id");
            goodsSpec = extra.getString("goods_spec");
            goodsNum = extra.getString("goods_num");
            taoInviteCode = extra.getString("taoInviteCode");
        }
        CreateOnlineOrderFragment onlineOrderFragment = CreateOnlineOrderFragment.getInstance();
        CreateSelfOrderFragment selfOrderFragment = CreateSelfOrderFragment.getInstance();
        Bundle bundle = new Bundle();
        bundle.putString("goods_id", goodsId);
        bundle.putString("goods_spec", goodsSpec);
        bundle.putString("goods_num", goodsNum);
        bundle.putString("taoInviteCode", taoInviteCode);
        onlineOrderFragment.setArguments(bundle);
        selfOrderFragment.setArguments(bundle);

        fragments.add(onlineOrderFragment);
        fragments.add(selfOrderFragment);

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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void paySuccessEvent(UnifiedNotifyEvent events) {

        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterEventBus(this);
    }
}
