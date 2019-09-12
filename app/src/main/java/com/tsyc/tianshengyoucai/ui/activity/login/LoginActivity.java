package com.tsyc.tianshengyoucai.ui.activity.login;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.manager.DataMangerVo;
import com.tsyc.tianshengyoucai.manager.SPManager;
import com.tsyc.tianshengyoucai.model.bean.LoginSuccessBean;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.requet.RequestSettingHttp;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.SaveUserInfomUtilJave;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.UserInfomVo;
import com.tsyc.tianshengyoucai.vo.WeiXinLoginVo;
import com.youth.xframe.utils.XRegexUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import java.util.HashMap;
import java.util.Map;

/**
 * author：van
 * CreateTime：2019/6/14
 * File description：
 */
public class LoginActivity extends BaseActivity {

    private EditText etAccount;
    private ImageView ivClearPhone;
    private EditText etPassword;
    private ImageView ivShowOrHiddenPassword;
    private TextView tvRegister;
    private TextView tvForgetPassword;
    private Button btLogin;
    private LinearLayout mLLWxLogin;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };
    private IWXAPI mWxapi;
    private BroadcastReceiver mReceiver;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        etAccount = (EditText) findViewById(R.id.et_Account);
        etPassword = (EditText) findViewById(R.id.et_Password);
        ivShowOrHiddenPassword = (ImageView) findViewById(R.id.iv_ShowOrHidden_Password);
        ivClearPhone = (ImageView) findViewById(R.id.iv_Clear_Phone);
        tvRegister = (TextView) findViewById(R.id.tv_register);
        tvForgetPassword = (TextView) findViewById(R.id.tv_Forget_Password);
        btLogin = (Button) findViewById(R.id.bt_Login);
        mLLWxLogin = (LinearLayout) findViewById(R.id.ll_wx_login);

    }

    @Override
    public void initData() {
        ivClearPhone.setOnClickListener(this);
        ivShowOrHiddenPassword.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvForgetPassword.setOnClickListener(this);
        btLogin.setOnClickListener(this);
        mLLWxLogin.setOnClickListener(this);
        initRegisterWeiXin();
        initWeiXinCall();
    }

    private void initWeiXinCall() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(DataMangerVo.WEI_LOGIN_ACTION);
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent != null) {
                    String extra = intent.getStringExtra(DataMangerVo.WEISTATE);
                    String code = intent.getStringExtra(DataMangerVo.WEICODE);
                    if (StringUtil.isEmpty(code)){
                        T.showToast(mContext,"用户取消");
                        return;
                    }
                    submitReuqest(code);
                }
            }
        };
        registerReceiver(mReceiver, filter);
    }


    private void submitReuqest(String code) {
        showProgress();
        RequestSettingHttp.getSingleton(this).submitWeiXinLogin(code, new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                NormalBean vos = GsonUtils.getGson(success, NormalBean.class);
                if (vos.getCode().equals("100")) {
                    T.showToast(mContext, vos.getMessage());
                    return;
                }
                WeiXinLoginVo vo = GsonUtils.getGson(success, WeiXinLoginVo.class);
                WeiXinLoginVo.ResultBean result = vo.getResult();
                if (result.getIs_bind_mobile()!=0) {
                    SaveUserInfomUtilJave instance = SaveUserInfomUtilJave.getInstance();
                    UserInfomVo infomVo = new UserInfomVo();
                    UserInfomVo.ResultBean bean = new UserInfomVo.ResultBean();
                    bean.setKey(result.getKey());
                    infomVo.setResult(bean);
                    instance.putUserInfom(infomVo);
                    LoginSuccessBean loginSuccessBean = new LoginSuccessBean();
                    LoginSuccessBean.ResultBean resultBean = new LoginSuccessBean.ResultBean();
                    resultBean.setKey(result.getKey());
                    loginSuccessBean.setResult(resultBean);
                    SPManager.saveUserInfo(loginSuccessBean);
                    mResultTo.finishBase(mContext);
                    mResultTo.startMain(mContext,result.getKey());
                }else {
                    LoginSuccessBean loginSuccessBean = new LoginSuccessBean();
                    LoginSuccessBean.ResultBean resultBean = new LoginSuccessBean.ResultBean();
                    resultBean.setKey(result.getKey());
                    loginSuccessBean.setResult(resultBean);
                    SPManager.saveUserInfo(loginSuccessBean);
                    mResultTo.finishBase(mContext);
                    mResultTo.startBindPhone(mContext,result.getKey());
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
        unregisterReceiver(mReceiver);
    }

    private void initRegisterWeiXin() {
        mWxapi = WXAPIFactory.createWXAPI(mContext, DataMangerVo.WeixinAPP_ID, true);
        mWxapi.registerApp(DataMangerVo.WeixinAPP_ID);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_Clear_Phone:// 清空输入手机号
                etAccount.setText("");
                break;

            case R.id.iv_ShowOrHidden_Password:// 显示隐藏密码
                if (etPassword.getInputType() == 129) {
                    etPassword.setInputType(145);
                    ivShowOrHiddenPassword.setImageResource(R.mipmap.jft_but_openone_seyes);
                } else {
                    etPassword.setInputType(129);
                    ivShowOrHiddenPassword.setImageResource(R.mipmap.jft_but_closeeyes);
                }
                etPassword.setSelection(etPassword.getText().length());
                break;

            case R.id.tv_register:// 注册
                openActivity(RegisterActivity.class);

                break;

            case R.id.tv_Forget_Password:// 忘记密码
                openActivity(ForGetPwdActivity.class);
                break;

            case R.id.bt_Login:// 登录按钮
                String phone = etAccount.getText().toString().trim();
                String pwd = etPassword.getText().toString().trim();

                if (!XRegexUtils.checkMobile(phone)) {
                    XToast.normal(getString(R.string.phone_num_wrong));
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    XToast.normal(getString(R.string.pwd_empty));
                    return;
                }
                toLogin(phone, pwd);
                break;
            case R.id.ll_wx_login://微信登录
                if (mWxapi.isWXAppInstalled()) {
                    WeiXinLogin();
                } else {
                    T.showToast(mContext, "请先安装微信");
                }

                break;

            default:
                break;
        }
    }

    private void WeiXinLogin() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        mWxapi.sendReq(req);
    }

    private void toLogin(String phone, String pwd) {
        loading(true, "正在登录...");
        Map<String, String> params = new HashMap<>();
        params.put("username", phone);
        params.put("password", pwd);
        params.put("client", Constant.CLIENT);
        BaseRequestUtils.postRequest(this, UrlManager.login, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {

                XLog.e(UrlManager.login+"登陆成功" + response.body());
                LoginSuccessBean loginSuccessBean = FastJsonUtil.fromJson(response.body(), LoginSuccessBean.class);
                //LoginSuccessBean loginSuccessBean = FastJsonUtil.fromJson(response.body(), LoginSuccessBean.class);
                dismiss();
                if (loginSuccessBean == null) {
                    XToast.normal("登陆失败");
                    return;
                }
                if (!loginSuccessBean.getCode().equals(Constant.REQUEST_SUCCESS)) {
                    XToast.normal(String.valueOf(loginSuccessBean.getMessage()));
                    return;
                }
                if (loginSuccessBean.getCode().equals(Constant.REQUEST_SUCCESS)) {
                    SPManager.saveUserInfo(loginSuccessBean);
                    XToast.normal("登陆成功");
//                    mHandler.postDelayed(() -> {
//                    Bundle bundle = new Bundle();
//                    bundle.putString(MainActivity.token, loginSuccessBean.getResult().getKey());
//                    openActivity(MainActivity.class, bundle);
                    mResultTo.startMain(mContext,loginSuccessBean.getResult().getKey());
                    finish();
//                    }, 500);

                } else {
                    XToast.normal("登陆失败");
                }
            }

            @Override
            public void failed(Response<String> response) {
              //  XLog.e("登陆" + response.getException().getMessage() == null ? "" : response.getException().getMessage());
                XToast.normal(getString(R.string.service_error));
                dismiss();
            }
        });

    }


}
