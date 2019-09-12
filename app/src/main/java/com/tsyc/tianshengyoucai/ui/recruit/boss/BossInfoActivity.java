package com.tsyc.tianshengyoucai.ui.recruit.boss;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tsyc.tianshengyoucai.Eventbuss.RefreshBossMeEvent;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.requet.RequestBossHttp;
import com.tsyc.tianshengyoucai.requet.UpdataImagHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.ActivityUtil;
import com.tsyc.tianshengyoucai.utils.FileUtil;
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.LunBanUtil;
import com.tsyc.tianshengyoucai.utils.SelectKeyUtil;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.utils.Util;
import com.tsyc.tianshengyoucai.view.SelectCamerAlerdialog;
import com.tsyc.tianshengyoucai.vo.BossInfomVo;
import com.tsyc.tianshengyoucai.vo.GmImagerVo;
import com.tsyc.tianshengyoucai.vo.JobSelectIndox;
import com.youth.xframe.widget.XToast;
import com.zhihu.matisse.Matisse;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

/**
 * author：van
 * CreateTime：2019/8/27
 * File description： 编辑boss 信息
 */
@RuntimePermissions
public class BossInfoActivity extends Base2Activity implements View.OnClickListener {
    private ImageView mIvBossInfomHeader;
    private ConstraintLayout mCtlBossInfomTop;
    private TextView mTvBossInfomNameTip;
    private EditText mEtBossInfomName;
    private TextView mTvBossInfomGenderTip;
    private TextView mTvBossInfomGender;
    private TextView mTvBossInfomPositionTip;
    private TextView mEtBossInfomPosition;
    private TextView mTvBossInfomPhoneTip;
    private EditText mEtBossInfomPhone;
    private TextView mTvBossInfomEmailTip;
    private EditText mEtBossInfomEmail;
    private Button mBtnBossInfomCommit;
    private NestedScrollView mNsvBossInfomRoot;
    private BossInfomVo mVo;
    /**
     * 职务
     */
    private static final int JOB_REQUESTCODE = 1011;
    /**
     * 用户选择职业  //暂不用
     */
    public static final String SELECT_JOBVO = "job";
    //暂不用
    private JobSelectIndox mSelectJobVo;
    private SelectCamerAlerdialog mCamerAlerdialog;
    /**
     * 相机
     */
    private static final int CANMER_REQUESTCODE = 1012;
    /**
     * 相册
     */
    private static final int XIANGCE_REQUESTCODE = 1013;
    /**
     * 修改手机号码
     */
    private static final int VERIFYPHONECODE = 1014;
    /**
     * 回调手机号
     */
    public static final String PHONE = "phone";

    private String mPath;

    private boolean isSelect;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_boss_info);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_boss_info;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        initView();
        initEvent();
        initRequest();

    }

    private void initEvent() {

    }

    private void initRequest() {
        showProgress();
        RequestBossHttp.getSingleton(this).requestBossInfom(new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                mVo = GsonUtils.getGson(success, BossInfomVo.class);
                bindViewData();
            }

            @Override
            public void error(String error) {
                onError();
            }
        });


    }

    private void bindViewData() {
        if (mVo == null) {
            return;
        }
        mNsvBossInfomRoot.setVisibility(View.VISIBLE);
        BossInfomVo.ResultBean result = mVo.getResult();
        GlideUtil.getSingleton().loadQuadRangleImager(mContext, mIvBossInfomHeader, result.getAvatar());
        mEtBossInfomName.setText(result.getReal_name());
        mTvBossInfomGender.setText(result.getSex().getDesc());
        mEtBossInfomPosition.setText(result.getDuties());
        mEtBossInfomPhone.setText(result.getMobile());
        mEtBossInfomEmail.setText(result.getEmail());
        mPath = result.getAvatar();
    }

    private void initView() {
        mIvBossInfomHeader = (ImageView) findViewById(R.id.iv_boss_infom_header);
        mCtlBossInfomTop = (ConstraintLayout) findViewById(R.id.ctl_boss_infom_top);
        mTvBossInfomNameTip = (TextView) findViewById(R.id.tv_boss_infom_name_tip);
        mEtBossInfomName = (EditText) findViewById(R.id.et_boss_infom_name);
        mTvBossInfomGenderTip = (TextView) findViewById(R.id.tv_boss_infom_gender_tip);
        mTvBossInfomGender = (TextView) findViewById(R.id.tv_boss_infom_gender);
        mTvBossInfomPositionTip = (TextView) findViewById(R.id.tv_boss_infom_position_tip);
        mEtBossInfomPosition = (EditText) findViewById(R.id.et_boss_infom_position);
        mTvBossInfomPhoneTip = (TextView) findViewById(R.id.tv_boss_infom_phone_tip);
        mEtBossInfomPhone = (EditText) findViewById(R.id.et_boss_infom_phone);
        mTvBossInfomEmailTip = (TextView) findViewById(R.id.tv_boss_infom_email_tip);
        mEtBossInfomEmail = (EditText) findViewById(R.id.et_boss_infom_email);
        mBtnBossInfomCommit = (Button) findViewById(R.id.btn_boss_infom_commit);

        mTvBossInfomGender.setOnClickListener(this);
        mBtnBossInfomCommit.setOnClickListener(this);
        mNsvBossInfomRoot = (NestedScrollView) findViewById(R.id.nsv_boss_infom_root);
        mNsvBossInfomRoot.setOnClickListener(this);
        mIvBossInfomHeader.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_boss_infom_commit:
                submit();
                break;
            case R.id.tv_boss_infom_gender://性别
                showSex(mTvBossInfomGender);
                break;
//            case R.id.tv_boss_infom_position://职务
//                mResultTo.startJobsSelect(mContext, JOB_REQUESTCODE);
//                break;
            case R.id.iv_boss_infom_header://头像
                BossInfoActivityPermissionsDispatcher.showCameraWithPermissionCheck(this);
                break;
//            case R.id.tv_boss_infom_phone://修改手机
////                mResultTo.startVerifyPhone(mContext, VERIFYPHONECODE);
//                break;
        }
    }

    private void showSex(TextView tv) {
        List<String> items = new ArrayList<>();
        items.add("男");
        items.add("女");
        SelectKeyUtil.showSelect(BossInfoActivity.this, items, new SelectKeyUtil.SelectClick() {
            @Override
            public void selectItem(int postion, String com) {
                tv.setText(com);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        if (resultCode == RESULT_OK && requestCode == JOB_REQUESTCODE) {
            mSelectJobVo = (JobSelectIndox) data.getSerializableExtra(SELECT_JOBVO);
            mEtBossInfomPosition.setText(mSelectJobVo.getSelectVo().getName());
            return;
        }
        if (requestCode == CANMER_REQUESTCODE && resultCode == RESULT_OK) {
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
                    showImag(mIvBossInfomHeader, s);
                    isSelect = true;
                }

                @Override
                public void imgError() {
                    dismissProgress();
                    isSelect = false;
                    T.showToast(mContext, "图片压缩失败,请重新选择");
                }
            });
            return;
        }

        if (requestCode == XIANGCE_REQUESTCODE && resultCode == RESULT_OK) {
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
                    showImag(mIvBossInfomHeader, path);
                    isSelect = true;
                }

                @Override
                public void imgError() {
                    dismissProgress();
                    isSelect = false;
                    T.showToast(mContext, "图片压缩失败,请重新选择");
                }
            });
            return;
        }
//        if (requestCode == VERIFYPHONECODE && resultCode == RESULT_OK) {
//            String phone = data.getStringExtra(PHONE);
//            mTvBossInfomPhone.setText(phone);
//            return;
//        }

    }

    private void showImag(ImageView mIvBossInfomHeader, String path) {
        GlideUtil.getSingleton().loadBQuadRangleImager(mContext, mIvBossInfomHeader, path);
    }

    private void submit() {
        if (StringUtil.isEmpty(mPath)) {
            T.showToast(mContext, "请选择头像");
            return;
        }
        String name = mEtBossInfomName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
            return;
        }

        String gender = mTvBossInfomGender.getText().toString().trim();
        if (TextUtils.isEmpty(gender)) {
            Toast.makeText(this, "请选择性别", Toast.LENGTH_SHORT).show();
            return;
        }

        String position = mEtBossInfomPosition.getText().toString().trim();
        if (TextUtils.isEmpty(position)) {
            Toast.makeText(this, "请选择职务", Toast.LENGTH_SHORT).show();
            return;
        }

        String phone = mEtBossInfomPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Util.isPhoneNum(phone)) {
            T.showToast(mContext, "请输入正确手机号码");
            return;
        }

        String email = mEtBossInfomEmail.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "请输入邮箱", Toast.LENGTH_SHORT).show();
            return;
        }

        String sex = "";
        if (gender.equals("男")) {
            sex = "2";
        }else if (gender.equals("女")){
            sex = "1";
        }

        showProgress();
        if (mVo != null && !isSelect) {
            submitData(mPath, name,position , email, sex,
                    phone);
            return;
        }
        String finalSex = sex;
        UpdataImagHttp.getSingleton(this).updataImage("", mPath, new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                GmImagerVo imagerVo = GsonUtils.getGson(success, GmImagerVo.class);
                GmImagerVo.ResultBean bean = imagerVo.getResult();
                submitData(bean.getImg_url(), name, position,
                        email, finalSex, phone);
            }

            @Override
            public void error(String error) {
                onError();
            }
        });

    }

    private void submitData(String path, String username, String duties, String email, String sex,
                            String mobile) {
        RequestBossHttp.getSingleton(this).submitEditBossInfom(path, username, duties, email, sex, mobile, new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                T.showToast(mContext, "保存成功");
                EventBus.getDefault().postSticky(new RefreshBossMeEvent(""));
                mResultTo.finishBase(mContext);
            }

            @Override
            public void error(String error) {
                onError();
            }
        });
    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    //拒绝
    @OnPermissionDenied({Manifest.permission.CAMERA})
    void showDeniedForCamera() {
        XToast.normal("权限拒绝");
    }

    // 不再询问
    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void showNeverAskForCamera() {
        XToast.normal("权限拒绝，不再询问");
    }


    @NeedsPermission(Manifest.permission.CAMERA)
    void showCamera() {
        showAlertDialog();
    }

    private void showAlertDialog() {
        if (mCamerAlerdialog != null) {
            if (mCamerAlerdialog.isShowing()) {
                return;
            }
            mCamerAlerdialog.show();
            return;
        }
        mCamerAlerdialog = new SelectCamerAlerdialog(mContext, R.style.mydialog);
        mCamerAlerdialog.initData(mContext);
        mCamerAlerdialog.setListener(new SelectCamerAlerdialog.OnItemClickListener() {
            @Override
            public void openCamer() {
                ActivityUtil.openCammer(BossInfoActivity.this, CANMER_REQUESTCODE);
            }

            @Override
            public void openXinCe() {
                ActivityUtil.openXiangCes(BossInfoActivity.this, XIANGCE_REQUESTCODE);
            }
        });
        mCamerAlerdialog.show();
    }
}
