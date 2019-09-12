package com.tsyc.tianshengyoucai.ui.activity.mine;

import android.support.v7.widget.Toolbar;
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
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.utils.Util;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author：van
 * CreateTime：2019/7/27
 * File description： 重置手机号
 */
public class ResetPhonePwdActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.view_bg)
    View viewBg;
    @BindView(R.id.tv_phone_title)
    TextView tvPhoneTitle;
    @BindView(R.id.et_change_phone)
    EditText etChangePhone;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.tv_code_title)
    TextView tvCodeTitle;
    @BindView(R.id.et_change_phone_code)
    EditText etChangePhoneCode;
    @BindView(R.id.view_line_height)
    View viewLineHeight;
    @BindView(R.id.btn_change_phone_code)
    Button btnChangePhoneCode;
    @BindView(R.id.btn_subimt_phone)
    Button btnSubimtPhone;
    private CountdownUtil mCountdownUtil;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_app_phone;
    }

    @Override
    public void initData() {
        mTvTitle.setText(getString(R.string.text_update_phone_pwd));

        initEvent();

    }

    private void initEvent() {

        mCountdownUtil = CountdownUtil.getInstance();
    }

    @OnClick({R.id.btn_change_phone_code, R.id.btn_subimt_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_change_phone_code://获取验证
                String phone = StringUtil.getObjectToStr(etChangePhone);
                if (StringUtil.isEmpty(phone)) {
                    T.showToast(mContext, StringUtil.getStringWid(mContext, R.string.please_new_phone));
                    return;
                }
                if (!Util.isPhoneNum(phone)) {
                    T.showToast(mContext, "请输入正确手机号码");
                    return;
                }
                getMsg(phone);

                break;
            case R.id.btn_subimt_phone://提交
                submit();
                break;
        }

    }
    private void getMsg(String phone){

        RequestCodeHttp.getSingleton(this).reqeustCode("5", phone, new RequestResultCallback() {
            @Override
            public void success(String success) {
                NormalBean gson = GsonUtils.getGson(success, NormalBean.class);
                if (gson.getCode().equals("100")) {
                    T.showToast(mContext, gson.getMessage());
                } else {
                    T.showToast(mContext, "验证码发送成功");
                    mCountdownUtil.startTime(mContext, R.color.color_ED772F, btnChangePhoneCode);
                }

            }

            @Override
            public void error(String error) {
                T.showToast(mContext, "验证码发送失败");
            }
        });
    }

    private void submit() {
        String phone = StringUtil.getObjectToStr(etChangePhone);
        if (StringUtil.isEmpty(phone)) {
            T.showToast(mContext, StringUtil.getStringWid(mContext, R.string.please_new_phone));
            return;
        }
        String code = StringUtil.getObjectToStr(etChangePhoneCode);
        if (StringUtil.isEmpty(code)) {
            T.showToast(mContext, StringUtil.getStringWid(mContext, R.string.please_new_code));
            return;
        }

        showProgress();
        RequestSettingHttp.getSingleton(this).submitPhone(phone, code, new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                NormalBean gson = GsonUtils.getGson(success, NormalBean.class);
                if (gson.getCode().equals("100")) {
                    T.showToast(mContext, gson.getMessage());
                } else {
                    T.showToast(mContext, StringUtil.getStringWid(mContext, R.string.success));
                    mResultTo.finishBase(ResetPhonePwdActivity.this);
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
