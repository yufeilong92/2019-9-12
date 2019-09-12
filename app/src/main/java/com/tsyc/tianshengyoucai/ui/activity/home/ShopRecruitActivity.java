package com.tsyc.tianshengyoucai.ui.activity.home;

import android.view.View;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.sign.CreateBossCardActivity;

import butterknife.OnClick;

/**
 * author：van
 * CreateTime：2019/7/29
 * File description：商家招聘
 */
public class ShopRecruitActivity extends BaseActivity {

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_shop_recruit;
    }


    @OnClick({R.id.tv_req_job, R.id.tv_resp_job})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_req_job:
                openActivity(RequestJobActivity.class);
                break;
            case R.id.tv_resp_job:
                // todo  是否认证
                openActivity(CreateBossCardActivity.class);
                break;
        }
    }
}
