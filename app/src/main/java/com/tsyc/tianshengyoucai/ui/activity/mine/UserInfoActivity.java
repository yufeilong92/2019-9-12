package com.tsyc.tianshengyoucai.ui.activity.mine;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tsyc.tianshengyoucai.Eventbuss.MineInfomEvents;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.manager.DataMangerVo;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.requet.RequestSettingHttp;
import com.tsyc.tianshengyoucai.requet.UpdataImagHttp;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.ActivityUtil;
import com.tsyc.tianshengyoucai.utils.DialogUtils;
import com.tsyc.tianshengyoucai.utils.FileUtil;
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.LunBanUtil;
import com.tsyc.tianshengyoucai.utils.SaveUserInfomUtilJave;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.view.SelectCamerAlerdialog;
import com.tsyc.tianshengyoucai.vo.GmImagerVo;
import com.tsyc.tianshengyoucai.vo.UserInfomVo;
import com.youth.xframe.widget.XToast;
import com.zhihu.matisse.Matisse;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

/**
 * author：van
 * CreateTime：2019/7/27
 * File description：个人资料
 */
@RuntimePermissions
public class UserInfoActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTvTitle;
    private Toolbar mToolbar;
    private ImageView mIvHeader;
    private ConstraintLayout mCtlLoginPwd;
    private TextView mEtName;
    private ConstraintLayout mCtlPayPwd;
    private ConstraintLayout mCtlTop;
    private TextView mTvTipPhone;
    private TextView mTvMyBank;
    private TextView mTvVersionCode;
    private TextView mTvMyWx;
    private TextView mTvNew;
    private TextView mTvMyAli;
    private TextView mTvInvite;
    private TextView mTvInviteCode;
    private SelectCamerAlerdialog mAlerdialog;
    //相机
    private final int REQUESTCODE = 10014;
    //相册
    private final int REQUESTCODECE = 10013;
    private String mPath;
    private Button btn_userinfom_updata;
    private UserInfomVo mInfomVo;
    private TextView mTvGender;
    private BroadcastReceiver mReceiver;
    private IWXAPI mWxapi;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_user_info;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user_info);
//        initView();
    }

    @Override
    public void initData() {
        mTvTitle.setText(getString(R.string.text_user_info));
        initView();
        initEvent();
        bindViewData();
        initRegisterWeiXin();
        initWeiXinCall();
    }

    private void initRegisterWeiXin() {
        mWxapi = WXAPIFactory.createWXAPI(mContext, DataMangerVo.WeixinAPP_ID, true);
        mWxapi.registerApp(DataMangerVo.WeixinAPP_ID);
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
                    if (StringUtil.isEmpty(code)) {
                        T.showToast(mContext, "用户取消");
                        return;
                    }
                    submitReuqest(code);
                }
            }
        };
        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    private void submitReuqest(String code) {
        showProgress();
        RequestSettingHttp.getSingleton(this).submitBindWeixinMine(code, new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                NormalBean vos = GsonUtils.getGson(success, NormalBean.class);
                if (vos.getCode().equals("100")) {
                    T.showToast(mContext, vos.getMessage());
                    return;
                }
                T.showToast(mContext, "绑定成功");
                SaveUserInfomUtilJave instance = SaveUserInfomUtilJave.getInstance();
                UserInfomVo userInfom = instance.getUserInfom();
                UserInfomVo.ResultBean bean = userInfom.getResult();
                bean.setBindWeChatStatus(1);
                userInfom.setResult(bean);
                instance.delectUserInfom();
                instance.putUserInfom(userInfom);
                bindViewData();
            }

            @Override
            public void error(String error) {
                dismissProgress();
                toastErrorNet();

            }
        });


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        requestInfom(new InfomInterface() {
            @Override
            public void infomSuccess(UserInfomVo vo) {
                SaveUserInfomUtilJave.getInstance().delectUserInfom();
                SaveUserInfomUtilJave.getInstance().putUserInfom(vo);
                bindViewData();
            }
            @Override
            public void infomError() {
            }
        });

    }

    private void bindViewData() {
        mInfomVo = SaveUserInfomUtilJave.getInstance().getUserInfom();
        mEtName.setText(mInfomVo.getResult().getUser_name());
        if (StringUtil.isEmpty(mPath)) {
            btn_userinfom_updata.setVisibility(View.GONE);
            GlideUtil.getSingleton().loadCilcleImager(mContext, mIvHeader, mInfomVo.getResult().getAvatar());
        }
        UserInfomVo.ResultBean voResult = mInfomVo.getResult();
        if (voResult.getBindBankStatus() == 1) {
            mTvMyBank.setText("已绑定");
        } else {
            mTvMyBank.setText("未绑定");
        }
        if (voResult.getBindAliPayStatus() == 1) {
            mTvMyAli.setText("已绑定");
        } else {
            mTvMyAli.setText("未绑定");
        }
        if (voResult.getBindWeChatStatus() == 1) {
            mTvMyWx.setText("已绑定");
        } else {
            mTvMyWx.setText("未绑定");
        }
        String invite_code = mInfomVo.getResult().getInvite_code();
        mTvInviteCode.setText(invite_code);
        if (voResult.getMember_sex().equals("未知") || StringUtil.isEmpty(voResult.getMember_sex())) {
            mTvGender.setText("未填写");
        } else {
            mTvGender.setText(voResult.getMember_sex());
        }
    }

    private void initEvent() {
        mEtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.e("==beforeTextChanged=", s + "beforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("==beforeTextChanged=", s + "onTextChanged");
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (StringUtil.isEmpty(mEtName.getText().toString())) {
                    if (StringUtil.isEmpty(mPath)) {
                        btn_userinfom_updata.setVisibility(View.GONE);
                    } else {
                        btn_userinfom_updata.setVisibility(View.VISIBLE);
                    }
                } else {
                    btn_userinfom_updata.setVisibility(View.VISIBLE);
                }
            }
        });
        btn_userinfom_updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_userinfom_updata.setVisibility(View.GONE);
                if (StringUtil.isEmpty(mPath)) {
                    String name = StringUtil.getObjectToStr(mEtName);
                    showProgress();
                    submitHttp(name, mPath);
                    return;
                }
                submit();
            }
        });

 /*       mTvVersionCode.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mInfomVo == null) return true;
                if (mInfomVo.getResult().getBindWeChatStatus() == 1)
                    showDeleteWeiXin();
                return false;
            }
        });*/

    }

    private void showDeleteWeiXin() {
        DialogUtils.getSingleton().showSureAlerDialog(mContext, "是否解绑微信", "", "解绑",
                "取消", new DialogUtils.OnDialogSuceClickListener() {
                    @Override
                    public void sureClick() {
                        submitDelete();
                    }

                    @Override
                    public void cannerClick() {
                    }
                });


    }

    private void submitDelete() {
        showProgress();
        RequestSettingHttp.getSingleton(this).submitDeleteWeiXin(new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                NormalBean vo = GsonUtils.getGson(success, NormalBean.class);
                if (vo.getCode().equals("100")) {
                    T.showToast(mContext, vo.getMessage());
                    return;
                }
                T.showToast(mContext, "解绑成功");
                SaveUserInfomUtilJave instance = SaveUserInfomUtilJave.getInstance();
                UserInfomVo infom = instance.getUserInfom();
                UserInfomVo.ResultBean result = infom.getResult();
                result.setBindWeChatStatus(0);
                infom.setResult(result);
                instance.delectUserInfom();
                instance.putUserInfom(infom);
                bindViewData();
            }

            @Override
            public void error(String error) {
                dismissProgress();
                toastErrorNet();

            }
        });

    }

    public void initView() {
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvGender = (TextView) findViewById(R.id.tv_gender);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mIvHeader = (ImageView) findViewById(R.id.iv_header);
        mCtlLoginPwd = (ConstraintLayout) findViewById(R.id.ctl_login_pwd);
        mCtlLoginPwd.setOnClickListener(this);
        mEtName = (EditText) findViewById(R.id.et_name);
        mCtlPayPwd = (ConstraintLayout) findViewById(R.id.ctl_pay_pwd);
        mCtlPayPwd.setOnClickListener(this);
        mCtlTop = (ConstraintLayout) findViewById(R.id.ctl_top);
        mTvTipPhone = (TextView) findViewById(R.id.tv_tip_phone);
        mTvTipPhone.setOnClickListener(this);
        mTvMyBank = (TextView) findViewById(R.id.tv_my_bank);
        mTvVersionCode = (TextView) findViewById(R.id.tv_version_code);
        mTvVersionCode.setOnClickListener(this);
        mTvMyWx = (TextView) findViewById(R.id.tv_my_wx);
        mTvNew = (TextView) findViewById(R.id.tv_new);
        mTvNew.setOnClickListener(this);
        mTvMyAli = (TextView) findViewById(R.id.tv_my_ali);
        mTvInvite = (TextView) findViewById(R.id.tv_invite);
        mTvInviteCode = (TextView) findViewById(R.id.tv_invite_code);
        btn_userinfom_updata = (Button) findViewById(R.id.btn_userinfom_updata);
        btn_userinfom_updata.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ctl_login_pwd://头像修改
                UserInfoActivityPermissionsDispatcher.showCameraWithPermissionCheck(this);
                break;
            case R.id.ctl_pay_pwd://密码修改
                break;
            case R.id.tv_tip_phone://银行卡
                mResultTo.startBankList(this);
                break;
            case R.id.tv_version_code://微信
                XToast.normal("暂未开放");
               /* String com = StringUtil.getObjectToStr(mTvMyWx);
                if (!com.equals("未绑定")) {
                    return;
                }

                if (mWxapi.isWXAppInstalled()) {
                    WeiXinLogin();
                } else {
                    T.showToast(mContext, "请先安装微信");
                }*/
                break;
            case R.id.tv_new://支付宝
                mResultTo.startBindAlipay(this);
                break;

            default:
                break;
            case R.id.btn_userinfom_updata:
                break;
        }

    }

    private void WeiXinLogin() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        mWxapi.sendReq(req);
    }

    private void selectOpen() {
        mAlerdialog = new SelectCamerAlerdialog(mContext, R.style.mydialog);
        mAlerdialog.setCanceledOnTouchOutside(true);
        mAlerdialog.show();
        mAlerdialog.setListener(new SelectCamerAlerdialog.OnItemClickListener() {
            @Override
            public void openCamer() {
                ActivityUtil.openCammer(UserInfoActivity.this, REQUESTCODE);
            }

            @Override
            public void openXinCe() {
                ActivityUtil.openXiangCes(UserInfoActivity.this, REQUESTCODECE);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        if (requestCode == REQUESTCODE && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            Bitmap bimap = (Bitmap) bundle.get("data");
            String s = FileUtil.saveImag(mContext, bimap);
            Log.e("===========", "onActivityResult: " + s);
            LunBanUtil.getSingleton(mContext).lunBanImager(s, new LunBanUtil.lunBanInterface() {
                @Override
                public void imgStart() {
                    showProgress();
                }

                @Override
                public void imgSuccess(String path) {
                    dismissProgress();
                    mPath = path;
                    showImag(mIvHeader, path);
                    btn_userinfom_updata.setVisibility(View.VISIBLE);
                }

                @Override
                public void imgError() {
                    dismissProgress();
                    T.showToast(mContext, "图片压缩失败,请重新选择");
                }
            });

            return;
        }
        if (requestCode == REQUESTCODECE && resultCode == RESULT_OK) {
            List<String> strings = Matisse.obtainPathResult(data);
            LunBanUtil.getSingleton(mContext).lunBanImagerS(strings, new LunBanUtil.lunBanInterface() {
                @Override
                public void imgStart() {
                    showProgress();
                }

                @Override
                public void imgSuccess(String path) {
                    dismissProgress();
                    mPath = path;
                    showImag(mIvHeader, path);
                    btn_userinfom_updata.setVisibility(View.VISIBLE);
                }

                @Override
                public void imgError() {
                    dismissProgress();
                    T.showToast(mContext, "图片压缩失败,请重新选择");
                }
            });
            return;
        }

    }

    /**
     * 提交图片
     */
    private void submit() {
        showProgress();
        String name = StringUtil.getObjectToStr(mEtName);
        UpdataImagHttp.getSingleton(this).updataImage("1", mPath, new RequestResultCallback() {
            @Override
            public void success(String success) {
                NormalBean vos = GsonUtils.getGson(success, NormalBean.class);
                if (vos.getCode().equals("100")) {
                    T.showToast(mContext, vos.getMessage());
                    return;
                }
                GmImagerVo vo = GsonUtils.getGson(success, GmImagerVo.class);

                mPath = "";
                submitHttp(name, vo.getResult().getImg_url());
            }

            @Override
            public void error(String error) {
                dismissProgress();
                btn_userinfom_updata.setVisibility(View.VISIBLE);
                toastErrorNet();
            }
        });


    }

    /**
     * 提交数据
     *
     * @param name
     * @param path
     */
    private void submitHttp(String name, String path) {
        RequestSettingHttp.getSingleton(this).submitHearAndNickName(name, path, new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                T.showToast(mContext, StringUtil.getStringWid(mContext, R.string.save_success));
                mResultTo.finishBase(mContext);
                if (StringUtil.isEmpty(path)) {
                    EventBus.getDefault().postSticky(new MineInfomEvents(""));
                } else {
                    EventBus.getDefault().postSticky(new MineInfomEvents("1"));
                }
            }

            @Override
            public void error(String error) {
                dismissProgress();
                btn_userinfom_updata.setVisibility(View.VISIBLE);
                toastErrorNet();
            }
        });
    }


    private void showImag(ImageView iv, String path) {
        GlideUtil.getSingleton().loadBCilcleImager(mContext, iv, path);
    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //ShopDetailActivityPermissionsDispatcher.onSkipIntentWithPermissionCheck(ShopDetailActivity.this);
        UserInfoActivityPermissionsDispatcher.showCameraWithPermissionCheck(UserInfoActivity.this);
    }


    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
    void showCamera() {
        selectOpen();
    }

    //拒绝
    @OnPermissionDenied({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
    void showDeniedForCamera() {
        XToast.normal("权限拒绝");
    }

    // 不再询问
    @OnNeverAskAgain({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
    void showNeverAskForCamera() {
        XToast.normal("权限拒绝，不再询问");
    }
}
