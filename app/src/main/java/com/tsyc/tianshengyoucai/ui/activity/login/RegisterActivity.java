package com.tsyc.tianshengyoucai.ui.activity.login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Config;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.model.bean.SendSmsBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.youth.xframe.utils.XRegexUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * author：cxd
 * CreateTime：2019/7/11
 * File description：注册
 */
public class RegisterActivity extends BaseActivity {


    private static final int CAPTCHA_START = 109;
    @BindView(R.id.et_Account)
    EditText etAccount;
    @BindView(R.id.iv_Clear_Phone)
    ImageView ivClearPhone;

    @BindView(R.id.et_VerificationCode)
    EditText etVerificationCode;

    @BindView(R.id.tv_Send_Code)
    TextView tvSendCode;

    @BindView(R.id.iv_Password2)
    ImageView ivPassword2;
    @BindView(R.id.et_Password2)
    EditText etPassword2;

    @BindView(R.id.iv_Invitation_Code)
    ImageView ivInvitationCode;
    @BindView(R.id.et_Invitation_Code)
    EditText etInvitationCode;

    @BindView(R.id.cb_Select_Agreement)
    CheckBox cbSelectAgreement;
    @BindView(R.id.cb_woman)
    CheckBox cbWoman;
    @BindView(R.id.cb_man)
    CheckBox cbMan;

    @BindView(R.id.tv_Read_Agreement)
    TextView tvReadAgreement;

    @BindView(R.id.bt_Register)
    Button btRegister;
    private int timeCount = Config.COUNT_DOWN;
    private String sexStr = "男";
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
        return R.layout.activity_register;
    }

    @Override
    public void initData() {
        cbWoman.setOnCheckedChangeListener((buttonView, isChecked) -> {
            cbMan.setChecked(false);
            if (isChecked) {
                cbWoman.setChecked(true);
                sexStr = "女";
            }
        });

        cbMan.setOnCheckedChangeListener((buttonView, isChecked) -> {
            cbWoman.setChecked(false);
            if (isChecked) {
                cbMan.setChecked(true);
                sexStr = "男";
            }
        });


        ivClearPhone.setOnClickListener(this);
        tvSendCode.setOnClickListener(this);
        btRegister.setOnClickListener(this);
        tvReadAgreement.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_Clear_Phone: // 清空输入内容
                etAccount.setText("");
                break;

            case R.id.tv_Send_Code: // 发送验证码
                String phoneNum = etAccount.getText().toString().trim();
                if (!XRegexUtils.checkMobile(phoneNum)) {
                    XToast.normal(getString(R.string.phone_num_wrong));
                    return;
                }
                sendSms(phoneNum);
                break;

            case R.id.tv_Read_Agreement: // 用户协议
                Bundle bundle = new Bundle();
                bundle.putString("type", "register");
                openActivity(SingleWebActivity.class, bundle);
                break;

            case R.id.bt_Register: // 去注册
                String phoneAccount = etAccount.getText().toString().trim();
                String inputPwd = etPassword2.getText().toString().trim();
                String verifyCode = etVerificationCode.getText().toString().trim();
                String inviteCode = etInvitationCode.getText().toString().trim();

                if (!XRegexUtils.checkMobile(phoneAccount)) {
                    XToast.normal(getString(R.string.phone_num_wrong));
                    return;
                }
                if (TextUtils.isEmpty(verifyCode)) {
                    XToast.normal(getString(R.string.verify_code_empty));
                    return;
                }
                if (TextUtils.isEmpty(inputPwd)) {
                    XToast.normal(getString(R.string.pwd_empty));
                    return;
                }

//                if (TextUtils.isEmpty(inviteCode)) {
//                    XToast.normal(getString(R.string.invite_empty));
//                    return;
//                }

                if (!cbSelectAgreement.isChecked()) {
                    XToast.normal(getString(R.string.agreement_first));
                    return;
                }
                if (!cbMan.isChecked() && !cbWoman.isChecked()) {
                    XToast.normal("请选择性别");
                    return;
                }

                toRegister(phoneAccount, inputPwd, verifyCode, inviteCode);
                break;

            default:
                break;
        }
    }

    private void sendSms(String phoneNum) {
        showProgress();
        Map<String, String> params = new HashMap<>();
        params.put("type", Constant.SMS_REGISTER);
        params.put("phone", phoneNum);
        BaseRequestUtils.postRequest(this, UrlManager.send_captcha, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                dismissProgress();
                SendSmsBean smsBean = FastJsonUtil.fromJson(response.body(), SendSmsBean.class);
                if (!smsBean.getCode().equals(Constant.REQUEST_SUCCESS)) {
                    XToast.normal(smsBean.getMessage());
                    return;
                }
                T.showToast(mContext, "发送成功");
                //
                tvSendCode.setClickable(false);
                tvSendCode.setText(timeCount + "s后重新获取");
                mHandler.sendEmptyMessageDelayed(CAPTCHA_START, 1000);

            }

            @Override
            public void failed(Response<String> response) {
                dismissProgress();
                XToast.normal("发送失败");
            }
        });
    }

    //注册
    private void toRegister(String phoneAccount, String inputPwd, String verifyCode, String inviteCode) {
        Map<String, String> params = new HashMap<>();
        params.put("phone", phoneAccount);
        params.put("captcha", verifyCode);
        params.put("password", inputPwd);
        params.put("client", "android");
        params.put("invite", inviteCode);
        params.put("sex", sexStr);

        BaseRequestUtils.postRequest(this, UrlManager.register, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("注册" + response.body());
                SendSmsBean smsBean = FastJsonUtil.fromJson(response.body(), SendSmsBean.class);
                if (smsBean == null) {
                    XToast.normal("注册失败，请稍后再试");
                    return;
                }
                if (smsBean.getCode().equals(Constant.REQUEST_SUCCESS)) {
                    XToast.normal("注册成功");
                    finish();
                } else {
                    XToast.normal(String.valueOf(smsBean.getMessage()));
                }
            }

            @Override
            public void failed(Response<String> response) {

                XLog.e("注册失败");
                XToast.normal("注册失败");
            }
        });

    }
}
