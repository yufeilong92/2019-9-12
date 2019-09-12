package com.tsyc.tianshengyoucai.ui.activity.mine.bank;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/15 14:13:
 * @Purpose :解绑支付宝
 */
public class UnBindAlipayActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private TextView mTvUnbindPhone;
    private EditText mEtUnbindCode;
    private Button mBtnSendCode;
    private Button mBtnUnbind;
    private CountdownUtil mCountdownUtil;
    public static final String MPHONE = "Phone";
    private String mPhone;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_un_bind_alipay);
//        initView();
//    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_un_bind_alipay;
    }

    @Override
    public void initData() {
        if (getIntent() != null) {
            mPhone = getIntent().getStringExtra(MPHONE);
        }
        initView();
        initEvent();
    }

    private void initEvent() {
        mCountdownUtil = CountdownUtil.getInstance();
        mTvUnbindPhone.setText(mPhone);
    }

    public void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResultTo.finishBase(mContext);
            }
        });
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mTvUnbindPhone = (TextView) findViewById(R.id.tv_unbind_phone);
        mEtUnbindCode = (EditText) findViewById(R.id.et_unbind_code);
        mBtnSendCode = (Button) findViewById(R.id.btn_send_code);
        mBtnUnbind = (Button) findViewById(R.id.btn_unbind);

        mBtnSendCode.setOnClickListener(this);
        mBtnUnbind.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_code:
                sendCode();
                break;
            case R.id.btn_unbind:
                submit();
                break;
        }
    }

    private void sendCode() {
        String phone = StringUtil.getObjectToStr(mTvUnbindPhone);
        if (StringUtil.isEmpty(phone)) {
            T.showToast(mContext, StringUtil.getStringWid(mContext, R.string.login_phone_hint));
            return;
        }
        if (!Util.isPhoneNum(phone)) {
            T.showToast(mContext, "请输入正确的手机号");
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
                    mCountdownUtil.startTime(mContext, R.color.normal_text_color, mBtnSendCode);
                }

            }

            @Override
            public void error(String error) {
                dismissProgress();
                toastErrorNet();
            }
        });
    }

    private void submit() {
        String phone = mTvUnbindPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Util.isPhoneNum(phone)) {
            T.showToast(mContext, "请输入正确的手机号");
            return;
        }
        String code = mEtUnbindCode.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }
        showProgress();
        RequestSettingHttp.getSingleton(this).submitUnBindAlipay(code, new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                NormalBean vo = GsonUtils.getGson(success, NormalBean.class);
                if (vo.getCode().equals("100")) {
                    T.showToast(mContext, vo.getMessage());
                    return;
                }
                T.showToast(mContext, "解绑成功");
                SaveUserInfomUtilJave infomUtilJave = SaveUserInfomUtilJave.getInstance();
                UserInfomVo userInfom = infomUtilJave.getUserInfom();
                UserInfomVo.ResultBean result = userInfom.getResult();
                result.setBindAliPayStatus(0);
                infomUtilJave.delectUserInfom();
                userInfom.setResult(result);
                infomUtilJave.putUserInfom(userInfom);
                mResultTo.startInfom(mContext);

            }

            @Override
            public void error(String error) {
                dismissProgress();
                toastErrorNet();
            }
        });
    }
}
