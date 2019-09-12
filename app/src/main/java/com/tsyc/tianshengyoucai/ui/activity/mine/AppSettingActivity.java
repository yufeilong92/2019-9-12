package com.tsyc.tianshengyoucai.ui.activity.mine;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.manager.SPManager;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.requet.OkGoUpdateHttpUtil;
import com.tsyc.tianshengyoucai.requet.RequestJobHttp;
import com.tsyc.tianshengyoucai.ui.activity.login.LoginActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.ui.recruit.RecruitInActivity;
import com.tsyc.tianshengyoucai.utils.DialogUtils;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.SaveUserInfomUtilJave;
import com.tsyc.tianshengyoucai.utils.SystemUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.RecruitInVo;
import com.tsyc.tianshengyoucai.vo.UpdataVo;
import com.tsyc.tianshengyoucai.vo.UserInfomVo;
import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.UpdateCallback;
import com.vector.update_app.listener.ExceptionHandler;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author：van
 * CreateTime：2019/7/27
 * File description： 设置
 */
public class AppSettingActivity extends BaseActivity {


    @BindView(R.id.ctl_login_pwd)
    ConstraintLayout ctlLoginPwd;
    @BindView(R.id.tv_pay_state)
    TextView tvPayState;
    @BindView(R.id.ctl_pay_pwd)
    ConstraintLayout ctlPayPwd;
    @BindView(R.id.tv_phone_state)
    TextView tvPhoneState;
    @BindView(R.id.ctl_phone_pwd)
    ConstraintLayout ctlPhonePwd;
    @BindView(R.id.ctl_advice)
    ConstraintLayout ctlAdvice;
    @BindView(R.id.ctl_about)
    ConstraintLayout ctlAbout;
    @BindView(R.id.tv_version_code)
    TextView tvVersionCode;
    @BindView(R.id.ctl_version_code)
    ConstraintLayout ctlVersionCode;
    @BindView(R.id.btn_exit)
    Button btnExit;


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_app_setting;
    }

    @Override
    public void initData() {
        mTvTitle.setText(getString(R.string.setting));

        UserInfomVo vo = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = vo.getResult();
        setTextView(result.getIs_paypwd() == 1, tvPayState);
        tvPhoneState.setText(result.getPhone());
        tvVersionCode.setText(SystemUtil.getVersion(mContext));
    }

    private void setTextView(boolean mIsSetPay, TextView tv) {
        if (mIsSetPay) {
            tv.setText("已设置");
        } else {
            tv.setText("未设置");
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initData();
    }

    @OnClick({R.id.ctl_login_pwd, R.id.ctl_pay_pwd, R.id.ctl_phone_pwd, R.id.ctl_advice, R.id.ctl_about, R.id.ctl_version_code, R.id.btn_exit})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ctl_login_pwd: // 修改登录密码
                openActivity(ResetLoginPwdActivity.class);
                break;
            case R.id.ctl_pay_pwd: // 设置支付密码
                Bundle bundle = new Bundle();
                bundle.putString(SetPayPwdActivity.MAKE, SetPayPwdActivity.PAY_TURE);
                openActivity(SetPayPwdActivity.class);
                break;
            case R.id.ctl_phone_pwd:// 修改手机号
                openActivity(ResetPhonePwdActivity.class);
                break;
            case R.id.ctl_advice: // 投诉建议
                openActivity(AppSuggestActivity.class);
                break;
            case R.id.ctl_about: // 关于我们
                openActivity(AppAboutActivity.class);
                break;
            case R.id.ctl_version_code:
                initRequestUpdata();
                break;
            case R.id.btn_exit: // 退出
                DialogUtils.getSingleton().showSureAlerDialog(mContext, "确认退出该账号", "", "确认", "取消",
                        new DialogUtils.OnDialogSuceClickListener() {
                            @Override
                            public void sureClick() {
                                SPManager.clearUserInfo();
                                SaveUserInfomUtilJave.getInstance().delectUserInfom();
                                openActivity(LoginActivity.class, true);
                                finish();
                            }

                            @Override
                            public void cannerClick() {

                            }
                        });
                break;

        }
    }

    private void initRequestUpdata() {
        String path = mContext.getExternalCacheDir().getAbsolutePath() + File.separator + "/";
        String url = UrlManager.getUrl(mContext, R.string.http_update);
        new UpdateAppManager.Builder()
                .setActivity(this)
                .setHttpManager(new OkGoUpdateHttpUtil())
                .setUpdateUrl(url)
                .setPost(false)
                .setTargetPath(path)
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        e.printStackTrace();
                    }
                })
                .build()
                .checkNewApp(new UpdateCallback() {
                    @Override
                    protected UpdateAppBean parseJson(String json) {
                        UpdataVo data = GsonUtils.getGson(json, UpdataVo.class);
                        UpdataVo.ResultBean vo = data.getResult();
                        UpdateAppBean updateAppBean = new UpdateAppBean();
                        String updata = "Yes";
                        boolean isConstraint = false;
                        int code = SystemUtil.getVersionCode(mContext);
                        if (code <= vo.getAndroid_version_code()) {
                            updata = "No";
                        }
                        if (vo.getForce_update() == 1) {
                            isConstraint = true;
                        }
                        updateAppBean.setApkFileUrl(vo.getApp_url())
                                //（必须）是否更新Yes,No
                                .setUpdate(updata)
                                .setNewVersion(vo.getAndroid_version())
                                //（必须）下载地址
                                .setApkFileUrl(vo.getDownload_url())
                                //（必须）更新内容
                                .setUpdateLog(vo.getApp_desc())
                                //大小，不设置不显示大小，可以不设置
//                                .setTargetSize("")
                                //是否强制更新，可以不设置
                                .setConstraint(isConstraint);

                        return updateAppBean;
                    }

                    @Override
                    protected void hasNewApp(UpdateAppBean updateApp, UpdateAppManager updateAppManager) {
                        super.hasNewApp(updateApp, updateAppManager);
                    }

                    @Override
                    protected void onAfter() {
                        super.onAfter();
                    }

                    @Override
                    protected void noNewApp(String error) {
                        super.noNewApp(error);
                        T.showToast(mContext, "已经是最新版本");
                    }

                    @Override
                    protected void onBefore() {
                        super.onBefore();
                    }
                });

    }



}
