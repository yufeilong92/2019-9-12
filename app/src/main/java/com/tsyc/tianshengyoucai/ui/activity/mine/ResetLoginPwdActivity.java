package com.tsyc.tianshengyoucai.ui.activity.mine;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.requet.RequestSettingHttp;
import com.tsyc.tianshengyoucai.ui.activity.login.ForGetPwdActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author：van
 * CreateTime：2019/7/27
 * File description： 修改登陆密码
 */
public class ResetLoginPwdActivity extends BaseActivity {
    @BindView(R.id.et_old_pwd)
    EditText etOldPwd;
    @BindView(R.id.et_new_pwd)
    EditText etNewPwd;
    @BindView(R.id.et_new_pwd_)
    EditText etNewPwd_;
    @BindView(R.id.tv_forget_pwd)
    TextView tvForgetPwd;
    @BindView(R.id.btn_commit)
    Button btnCommit;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_reset_login_pwd;
    }

    @Override
    public void initData() {
        mTvTitle.setText(getString(R.string.text_update_login_pwd));
    }


    @OnClick({R.id.tv_forget_pwd, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_forget_pwd:
                openActivity(ForGetPwdActivity.class);
                break;
            case R.id.btn_commit:
                submit();
                break;
        }
    }

    private void submit() {
        String oldpsw = StringUtil.getObjectToStr(etOldPwd);
        if (StringUtil.isEmpty(oldpsw)) {
            T.showToast(mContext, StringUtil.getStringWid(mContext, R.string.hint_old_pwd));
            return;
        }
        String newPsw0 = StringUtil.getObjectToStr(etNewPwd);
        if (StringUtil.isEmpty(newPsw0)) {
            T.showToast(mContext, StringUtil.getStringWid(mContext, R.string.hint_new_pwd));
            return;
        }
        String newPsw1 = StringUtil.getObjectToStr(etNewPwd_);
        if (StringUtil.isEmpty(newPsw1)) {
            T.showToast(mContext, StringUtil.getStringWid(mContext, R.string.hint_new_pwd_));
            return;
        }
        if (!newPsw0.equals(newPsw1)) {
            T.showToast(mContext, "两次输入密码不一致，请重新输入");
            return;
        }
        showProgress();
        RequestSettingHttp.getSingleton(this).submit(oldpsw, newPsw1, new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                NormalBean gson = GsonUtils.getGson(success, NormalBean.class);
                if (gson.getCode().equals("100")) {
                    T.showToast(mContext, gson.getMessage());
                } else {
                    finish();
                    mResultTo.startLogin(ResetLoginPwdActivity.this);
                }
            }

            @Override
            public void error(String error) {
                dismissProgress();
                toastErrorNet();
            }
        });
    }
}

