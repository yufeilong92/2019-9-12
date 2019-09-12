package com.tsyc.tianshengyoucai.ui.activity.login;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.manager.SPManager;
import com.tsyc.tianshengyoucai.model.bean.LoginSuccessBean;
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
import com.tsyc.tianshengyoucai.vo.BindVo;
import com.tsyc.tianshengyoucai.vo.UserInfomVo;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/13 19:00:
 * @Purpose :绑定手机号
 */
public class BindPhoneActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private EditText mEtBindPhonePhone;
    private EditText mEtBindPhoneCode;
    private Button mBtnBindPhoneSend;
    private EditText mEtBindPhonePsw;
    private Button mBtnBindPhoneSubmit;
    private CountdownUtil mInstance;
    public static final String KEY = "key";
    public static final String TYPE = "key";
    private String mKey;
    private String mType;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bind_phone);
//        initView();
//    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_bind_phone;
    }

    @Override
    public void initData() {
        if (getIntent() != null) {
            mKey = getIntent().getStringExtra(KEY);
            mType = getIntent().getStringExtra(TYPE);
        }
        initView();
        initEvent();
    }

    private void initEvent() {
        mInstance = CountdownUtil.getInstance();

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
        mEtBindPhonePhone = (EditText) findViewById(R.id.et_bind_phone_phone);
        mEtBindPhoneCode = (EditText) findViewById(R.id.et_bind_phone_code);
        mBtnBindPhoneSend = (Button) findViewById(R.id.btn_bind_phone_send);
        mEtBindPhonePsw = (EditText) findViewById(R.id.et_bind_phone_psw);
        mBtnBindPhoneSubmit = (Button) findViewById(R.id.btn_bind_phone_submit);

        mBtnBindPhoneSend.setOnClickListener(this);
        mBtnBindPhoneSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bind_phone_send:
                String phone = StringUtil.getObjectToStr(mEtBindPhonePhone);
                if (StringUtil.isEmpty(phone)) {
                    T.showToast(mContext, "请输入您的手机号");
                    return;
                }
                if (!Util.isPhoneNum(phone)) {
                    T.showToast(mContext, "请输正确手机号");
                    return;
                }
                requestCode(phone);
                break;
            case R.id.btn_bind_phone_submit:
                submit();
                break;
        }
    }

    private void requestCode(String phone) {
        showProgress();
        RequestCodeHttp.getSingleton(this).reqeustCode("7", phone, new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                NormalBean vo = GsonUtils.getGson(success, NormalBean.class);
                if (vo.getCode().equals("100")) {
                    T.showToast(mContext, vo.getMessage());
                    return;
                }
                T.showToast(mContext, "发送成功");
                mInstance.startTime(mContext, R.color.normal_text_color, mBtnBindPhoneSend);
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
        mInstance.stop();
    }

    private void submit() {
        String phone = mEtBindPhonePhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入您的手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Util.isPhoneNum(phone)) {
            T.showToast(mContext, "请输入正确的手机号");
            return;
        }
        String code = mEtBindPhoneCode.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            Toast.makeText(this, "请输入手机短信验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        String psw = mEtBindPhonePsw.getText().toString().trim();
        if (TextUtils.isEmpty(psw)) {
            Toast.makeText(this, "请设置您的密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (psw.length() < 6) {
            T.showToast(mContext, "密码最少输入六位");
            return;
        }
        if (StringUtil.isEmpty(mKey)) {
            T.showToast(mContext, "获取信息失败，请重新授权登录");
            return;
        }
        showProgress();
        RequestSettingHttp.getSingleton(this).submitBindWeiXinLogin(phone, psw, mKey, code, new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                NormalBean vos = GsonUtils.getGson(success, NormalBean.class);
                if (vos.getCode().equals("100")) {
                    T.showToast(mContext, vos.getMessage());
                    return;
                }
                BindVo vo = GsonUtils.getGson(success, BindVo.class);
                BindVo.ResultBean bean = vo.getResult();
                T.showToast(mContext, "绑定成功");
                if (!StringUtil.isEmpty(mType)) {
                    SaveUserInfomUtilJave instance = SaveUserInfomUtilJave.getInstance();
                    UserInfomVo userInfom = instance.getUserInfom();
                    if (userInfom == null) {
                        userInfom = new UserInfomVo();
                    }
                    UserInfomVo.ResultBean result;
                    if (userInfom.getResult() == null) {
                        result = new UserInfomVo.ResultBean();
                    } else {
                        result = userInfom.getResult();
                    }
                    result.setBindWeChatStatus(1);
                    userInfom.setResult(result);
                    instance.delectUserInfom();
                    instance.putUserInfom(userInfom);
                    mResultTo.finishBase(mContext);
                    return;
                }
                SaveUserInfomUtilJave instance = SaveUserInfomUtilJave.getInstance();
                UserInfomVo userInfom = new UserInfomVo();
                UserInfomVo.ResultBean resultBean1 = new UserInfomVo.ResultBean();
                resultBean1.setKey(bean.getKey());
                resultBean1.setUser_name(bean.getUsername());
                resultBean1.setMember_id(bean.getUserid());
                userInfom.setResult(resultBean1);
                instance.putUserInfom(userInfom);
                LoginSuccessBean loginSuccessBean = new LoginSuccessBean();
                LoginSuccessBean.ResultBean resultBean = new LoginSuccessBean.ResultBean();
                resultBean.setKey(bean.getKey());
                loginSuccessBean.setResult(resultBean);
                SPManager.saveUserInfo(loginSuccessBean);
                mResultTo.startMain(mContext, bean.getKey());
            }

            @Override
            public void error(String error) {
                dismissProgress();
                toastErrorNet();
            }
        });


    }
}
