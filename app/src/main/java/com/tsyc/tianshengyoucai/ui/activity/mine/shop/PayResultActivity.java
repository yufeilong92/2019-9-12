package com.tsyc.tianshengyoucai.ui.activity.mine.shop;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.youth.xframe.utils.log.XLog;

/**
 * author：van
 * CreateTime：2019/8/13
 * File description：支付成功界面
 */
public class PayResultActivity extends BaseActivity {

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_pay_result;
    }

    @Override
    public void initView() {

        mTvTitle.setText("支付成功");

    }


}
