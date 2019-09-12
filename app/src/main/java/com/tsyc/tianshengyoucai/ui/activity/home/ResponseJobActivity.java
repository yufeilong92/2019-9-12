package com.tsyc.tianshengyoucai.ui.activity.home;

import android.view.View;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;

import butterknife.OnClick;

/**
 * author：van
 * CreateTime：2019/7/29
 * File description：我要招聘
 */
public class ResponseJobActivity extends BaseActivity {

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_shop_recruit;
    }

    @Override
    public void initView() {
        mTvTitle.setText(getString(R.string.text_resp_job));
    }
}
