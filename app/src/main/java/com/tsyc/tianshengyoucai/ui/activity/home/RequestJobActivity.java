package com.tsyc.tianshengyoucai.ui.activity.home;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：van
 * CreateTime：2019/7/29
 * File description：我要求职 第一步
 */
public class RequestJobActivity extends BaseActivity {

    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.et_real_name)
    EditText etRealName;
    @BindView(R.id.tv_gender)
    TextView tvGender;
    @BindView(R.id.tv_bron)
    TextView tvBron;
    @BindView(R.id.tv_work)
    TextView tvWork;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_email)
    TextView tvEmail;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_request_job;
    }


    @Override
    public void initView() {

        mTvTitle.setText(getString(R.string.text_req_job));
    }


    @OnClick({R.id.ctl_gender, R.id.ctl_bron, R.id.ctl_work, R.id.ctl_city, R.id.ctl_phone, R.id.ctl_email, R.id.tv_next})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ctl_gender:
                break;
            case R.id.ctl_bron:
                break;
            case R.id.ctl_work:
                break;
            case R.id.ctl_city:
                break;
            case R.id.ctl_phone:
                break;
            case R.id.ctl_email:
                break;
            case R.id.tv_next: // 下一步
                openActivity(EduExperienceActivity.class);
                break;
        }
    }
}
