package com.tsyc.tianshengyoucai.ui.activity.mine.shop;

import android.os.Bundle;
import android.view.View;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：van
 * CreateTime：2019/7/30
 * File description：选择发布哪种优惠券
 */
public class SelectReleaseCouponActivity extends BaseActivity {

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_select_release_coupon;
    }

    @Override
    public void initView() {
        mTvTitle.setText(getString(R.string.text_release_coupon));
    }


    @OnClick({R.id.ctl_top, R.id.ctl_btm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ctl_top:
                openActivity(ReleaseRedBag_Activity.class);
                break;
            case R.id.ctl_btm:
                openActivity(ReleaseCoupon_Activity.class);
                break;
        }
    }
}
