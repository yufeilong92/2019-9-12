package com.tsyc.tianshengyoucai.ui.activity.mine;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.requet.RequestCodeHttp;
import com.tsyc.tianshengyoucai.requet.RequestSettingHttp;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.CountdownUtil;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.SaveUserInfomUtilJave;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.utils.Util;
import com.tsyc.tianshengyoucai.vo.UserInfomVo;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author：van
 * CreateTime：2019/7/27
 * File description： 设置支付密码
 */
public class SetPayPwdActivity extends BaseActivity {
    public static final String MAKE = "make";
    public static final String PAY_TURE = "TURE";
    public static final String PAY_FALSE = "FALSE";


    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.et_version_code)
    EditText etVersionCode;
    @BindView(R.id.et_new_pwd)
    EditText etNewPwd;
    @BindView(R.id.et_new_pwd_)
    EditText etNewPwd_;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    @BindView(R.id.btn_send_code)
    Button btnSendCode;
    private CountdownUtil mCountdownUtil;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_set_pay_pwd;
    }

    @Override
    public void initData() {
        mTvTitle.setText(getString(R.string.text_update_pay_pwd));
        mCountdownUtil = CountdownUtil.getInstance();

        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        tvPhone.setText(infom.getResult().getPhone());
    }

    @OnClick({R.id.btn_commit, R.id.btn_send_code})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_commit://提交
                submit();

                break;
            case R.id.btn_send_code: //发送验证码
                sendCode();
                break;
        }

    }

    private void submit() {
        String phone = StringUtil.getObjectToStr(tvPhone);
        if (StringUtil.isEmpty(phone)) {
            T.showToast(mContext, "用户信息获取失败");
            return;
        }

        String code = StringUtil.getObjectToStr(etVersionCode);
        if (StringUtil.isEmpty(code)) {
            T.showToast(mContext, StringUtil.getStringWid(mContext, R.string.input_verification_code_hint));
            return;
        }

        String psw0 = StringUtil.getObjectToStr(etNewPwd);
        if (StringUtil.isEmpty(psw0)) {
            T.showToast(mContext, StringUtil.getStringWid(mContext, R.string.hint_pay_pwd));
            return;
        }
        String psw1 = StringUtil.getObjectToStr(etNewPwd_);

        if (StringUtil.isEmpty(psw1)) {
            T.showToast(mContext, StringUtil.getStringWid(mContext, R.string.hint_pay_pwd_));
            return;
        }

        if (!psw0.equals(psw1)) {
            T.showToast(mContext, "两次输入的支付密码不一致，请重新输入");
            return;
        }

        showProgress();
        RequestSettingHttp.getSingleton(this).submitPayPsw(psw0, code, new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                NormalBean bean = GsonUtils.getGson(success, NormalBean.class);
                if (bean.getCode().equals("100")) {
                    T.showToast(mContext, bean.getMessage());
                    return;
                }
                UserInfomVo vo = SaveUserInfomUtilJave.getInstance().getUserInfom();
                vo.getResult().setIs_paypwd(1);
                SaveUserInfomUtilJave.getInstance().delectUserInfom();
                SaveUserInfomUtilJave.getInstance().putUserInfom(vo);
                finish();
            }

            @Override
            public void error(String error) {
                dismissProgress();
                toastErrorNet();

            }
        });

    }


    private void sendCode() {
        String phone = StringUtil.getObjectToStr(tvPhone);
        if (StringUtil.isEmpty(phone)) {
            T.showToast(mContext, StringUtil.getStringWid(mContext, R.string.login_phone_hint));
            return;
        }
        if (!Util.isPhoneNum(phone)){
            T.showToast(mContext,"请输入正确的手机号");
            return;
        }
        showProgress();
        RequestCodeHttp.getSingleton(this).reqeustCode("4", phone, new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                NormalBean data = GsonUtils.getGson(success, NormalBean.class);
                if (data.getCode().equals("100")) {
                    T.showToast(mContext, data.getMessage());
                } else {
                    T.showToast(mContext, "发送成功");
                    mCountdownUtil.startTime(mContext,R.color.normal_text_color ,btnSendCode);
                }

            }

            @Override
            public void error(String error) {
                dismissProgress();
                toastErrorNet();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCountdownUtil.stop();
    }
}
