package com.tsyc.tianshengyoucai.ui.activity.mine.shop;

import android.view.View;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;

import butterknife.OnClick;

/**
 * author：van
 * CreateTime：2019/7/30
 * File description： 发布优惠
 */
public class ReleaseCouponActivity extends BaseActivity {
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_release_coupon;
    }

    @Override
    public void initView() {
        mTvTitle.setText(getString(R.string.text_release_coupon));
    }

    @OnClick({R.id.ctl_top, R.id.ctl_youhui, R.id.ctl_release_coupon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ctl_top: //  发布的红包
                openActivity(RelRedBagListActivity.class);
                break;

            case R.id.ctl_youhui:  // 发布的红包
                openActivity(RelCouponListActivity.class);
                break;

            case R.id.ctl_release_coupon:
                openActivity(SelectReleaseCouponActivity.class);
                break;
        }
    }
}
