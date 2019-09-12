package com.tsyc.tianshengyoucai.ui.activity.login;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Config;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.model.bean.SendSmsBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.utils.Util;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author：cxd
 * CreateTime：2019/7/11
 * File description： 找回密码
 */
public class ForGetPwdActivity extends BaseActivity {

    private static final int CAPTCHA_START = 109;
    @BindView(R.id.et_Account)
    EditText etAccount;

    @BindView(R.id.et_VerificationCode)
    EditText etVerificationCode;
    @BindView(R.id.tv_Send_Code)
    TextView tvSendCode;
    @BindView(R.id.et_Password)
    EditText etPassword;
    @BindView(R.id.et_Password2)
    EditText etPassword2;
    @BindView(R.id.bt_Enter)
    Button btEnter;

    private int timeCount = Config.COUNT_DOWN;
    @SuppressLint("HandlerLeak")
    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == CAPTCHA_START) {
                timeCount--;
                if (timeCount <= 0) {
                    timeCount = Config.COUNT_DOWN;
                    tvSendCode.setClickable(true);
                    tvSendCode.setText("重新获取");
                } else {
                    tvSendCode.setText(timeCount + "s后重新获取");
                    mHandler.sendEmptyMessageDelayed(CAPTCHA_START, 1000);
                }
            }
        }
    };

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    public void initData() {
        mTvTitle.setText(getString(R.string.forget_password2));

    }

    @OnClick({R.id.tv_Send_Code, R.id.bt_Enter})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_Send_Code:
                String phone = etAccount.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    XToast.normal("手机号不能为空");
                    return;
                }
                if (!Util.isPhoneNum(phone)){
                    T.showToast(mContext,"请输入正确的手机号");
                    return;
                }
                sendSms(phone);
                break;
            case R.id.bt_Enter:
                changePassword();
                break;
        }
    }

    private void changePassword() {
        String phone = etAccount.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            XToast.normal("手机号不能为空");
            return;
        }
        String code = etVerificationCode.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            XToast.normal("验证码不能为空");
            return;
        }
        String password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            XToast.normal("密码不能为空");
            return;
        }
        String password2 = etPassword2.getText().toString().trim();
        if (TextUtils.isEmpty(password2)) {
            XToast.normal("密码不能为空");
            return;
        }
        if (!password.equals(password2)) {
            XToast.normal("两次输入密码不一致，请重新输入");
            return;
        }

        loading(true);
        Map<String, String> params = new HashMap<>();
        params.put("phone", phone);
        params.put("captcha", code);
        params.put("password", password);
        params.put("client", Constant.CLIENT);
        BaseRequestUtils.postRequest(this, UrlManager.find_password, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                NormalBean smsBean = FastJsonUtil.fromJson(response.body(), NormalBean.class);
                if (smsBean == null) {
                    XToast.normal("修改失败");
                    dismiss();
                    return;
                }
                if (!smsBean.getCode().equals(Constant.REQUEST_SUCCESS)) {
                    XToast.normal(smsBean.getMessage());
                    dismiss();
                    return;
                }
                XToast.normal("修改成功");
                finish();
                dismiss();
            }

            @Override
            public void failed(Response<String> response) {
                XToast.normal(getString(R.string.service_error));
                dismiss();
            }
        });

    }


    //发送验证码
    private void sendSms(String phoneNum) {
loading(true);
        Map<String, String> params = new HashMap<>();
        params.put("type", Constant.SMS_FIND_PWD);
        params.put("phone", phoneNum);
        BaseRequestUtils.postRequest(this, UrlManager.send_captcha, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                // {"code":"200","result":{"sms_time":10},"message":"发送成功！"}
                SendSmsBean smsBean = FastJsonUtil.fromJson(response.body(), SendSmsBean.class);
                if (smsBean==null){
                    XToast.normal("发送失败");
                    return;
                }
                if (!smsBean.getCode().equals(Constant.REQUEST_SUCCESS)) {
                    XToast.normal(smsBean.getMessage());
                    dismiss();
                    return;
                }
                XToast.normal("发送成功");
                tvSendCode.setClickable(false);
                tvSendCode.setText(timeCount + "s后重新获取");
                mHandler.sendEmptyMessageDelayed(CAPTCHA_START, 1000);
                dismiss();
            }

            @Override
            public void failed(Response<String> response) {
                XToast.normal("验证码发送失败，请稍候再试");
                dismiss();
            }
        });
    }
}
