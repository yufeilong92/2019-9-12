package com.tsyc.tianshengyoucai.ui.activity.mine;

import android.os.Bundle;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.SystemUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：van
 * CreateTime：2019/7/27
 * File description： 关于我们
 */
public class AppAboutActivity extends BaseActivity {
    @BindView(R.id.tv_about_version)
    TextView tvAboutVersion;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_app_about;
    }

    @Override
    public void initData() {
        mTvTitle.setText(getString(R.string.text_about));
        tvAboutVersion.setText("版本号：v"+SystemUtil.getVersion(mContext));
    }

}
