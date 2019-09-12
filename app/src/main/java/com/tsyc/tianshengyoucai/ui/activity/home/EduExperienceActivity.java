package com.tsyc.tianshengyoucai.ui.activity.home;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
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
 * File description：第二步  教育经历
 */
public class EduExperienceActivity extends BaseActivity {

    @BindView(R.id.et_school)
    EditText etSchool;
    @BindView(R.id.tv_experience)
    TextView tvExperience;
    @BindView(R.id.et_special)
    EditText etSpecial;
    @BindView(R.id.ctl_special)
    ConstraintLayout ctlSpecial;
    @BindView(R.id.tv_times)
    TextView tvTimes;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_edu_experience;
    }


    @OnClick({R.id.ctl_experience, R.id.ctl_special, R.id.ctl_times, R.id.tv_next})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ctl_experience:
                break;
            case R.id.ctl_special:
                break;
            case R.id.ctl_times:
                break;
            case R.id.tv_next:
                openActivity(WorkExperienceActivity.class);
                break;
        }
    }
}
