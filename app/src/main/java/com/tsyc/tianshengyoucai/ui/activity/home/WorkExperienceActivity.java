package com.tsyc.tianshengyoucai.ui.activity.home;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：van
 * CreateTime：2019/7/29
 * File description：第三步  工作实习经历
 */
public class WorkExperienceActivity extends BaseActivity {

    @BindView(R.id.tv_choose_role)
    TextView tvChooseRole;
    @BindView(R.id.et_company)
    EditText etCompany;
    @BindView(R.id.tv_work_type)
    TextView tvWorkType;
    @BindView(R.id.et_work_content)
    EditText etWorkContent;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_work_experience;
    }

    @OnClick({R.id.ctl_work_time, R.id.ctl_work_type, R.id.ctl_work_content, R.id.tv_next})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ctl_work_time:
                break;
            case R.id.ctl_work_type:
                break;
            case R.id.ctl_work_content:
                break;
            case R.id.tv_next:
                openActivity(JobIntentActivity.class);
                break;
        }
    }
}
