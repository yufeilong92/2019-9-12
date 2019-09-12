package com.tsyc.tianshengyoucai.ui.recruit.boss.sign;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.view.SelectCamerAlerdialog;
import com.tsyc.tianshengyoucai.vo.CompanySearchVo;
import com.tsyc.tianshengyoucai.vo.GmImagerVo;
import com.youth.xframe.widget.XToast;
import com.zhihu.matisse.Matisse;

import java.util.List;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

/**
 * author：van
 * CreateTime：2019/8/26
 * File description： boos 创建名片
 */
@RuntimePermissions
public class CreateBossCardActivity extends Base2Activity implements View.OnClickListener {


    private int REQUESTCODE = 200;

    private TextView mTvTitle;
    private Toolbar mToolbar;
    private TextView mTvBossOneChooseRole;
    private ImageView mIvBossOneHeader;
    private TextView mTvBossOneName;
    private TextView mEtBossOneRealName;
    private ConstraintLayout mCtlBossOneName;
    private TextView mTvBossOneGenderText;
    private TextView mTvBossOneCompany;
    private ConstraintLayout mCtlBossOneCompany;
    private TextView mTvBossOneBronText;
    private EditText mEtBossOnePosition;
    private ConstraintLayout mCtlBossOnePosition;
    private TextView mTvBossOneWorkText;
    private EditText mEtBossOneEmail;
    private ConstraintLayout mCtlBossOneEmail;
    private Button mBtnBossOneNext;
    private SelectCamerAlerdialog mSelectAlerdialog;
    //头像路径
    private String mPath;
    /**
     * 相机请求码
     */
    private static final int CANMERCODE = 1021;
    /**
     * 相册请求码
     */
    private static final int XIANGCECODE = 1022;
    /**
     * 公司
     */
    public static final String SELECTCOMPANY = "company";
    /**
     * 公司请求码
     */
    private static final int REQUESTCOMPANYCODE = 201;
    private CompanySearchVo.ResultBean mSelectCompany;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_create_boos_card);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_create_boos_card;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        initView();

    }


    private void showImag(ImageView mIvBossOneHeader, String path) {
        GlideUtil.getSingleton().loadBCilcleImager(mContext, mIvBossOneHeader, path);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ctl_boss_one_company:
                mResultTo.startCompany(mContext, REQUESTCOMPANYCODE);
                break;
            case R.id.btn_boss_one_next:
                submit();
                break;
            case R.id.iv_boss_one_header://头像
                CreateBossCardActivityPermissionsDispatcher.showCameraWithPermissionCheck(CreateBossCardActivity.this);
                break;
        }

    }

    private void showAlertDialog() {
        mSelectAlerdialog = new SelectCamerAlerdialog(mContext, R.style.mydialog);
        mSelectAlerdialog.initData(mContext);
        mSelectAlerdialog.setListener(new SelectCamerAlerdialog.OnItemClickListener() {
            @Override
            public void openCamer() {
                ActivityUtil.openCammer(CreateBossCardActivity.this, CANMERCODE);
            }

            @Override
            public void openXinCe() {
                ActivityUtil.openXiangCes(CreateBossCardActivity.this, XIANGCECODE);
            }
        });
        mSelectAlerdialog.show();
    }


    private void initView() {
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTvBossOneChooseRole = (TextView) findViewById(R.id.tv_boss_one_choose_role);
        mIvBossOneHeader = (ImageView) findViewById(R.id.iv_boss_one_header);
        mTvBossOneName = (TextView) findViewById(R.id.tv_boss_one_name);
        mEtBossOneRealName = (EditText) findViewById(R.id.et_boss_one_real_name);
        mCtlBossOneName = (ConstraintLayout) findViewById(R.id.ctl_boss_one_name);
        mTvBossOneGenderText = (TextView) findViewById(R.id.tv_boss_one_gender_text);
        mTvBossOneCompany = (TextView) findViewById(R.id.tv_boss_one_company);
        mCtlBossOneCompany = (ConstraintLayout) findViewById(R.id.ctl_boss_one_company);
        mTvBossOneBronText = (TextView) findViewById(R.id.tv_boss_one_bron_text);
        mEtBossOnePosition = (EditText) findViewById(R.id.et_boss_one_position);
        mCtlBossOnePosition = (ConstraintLayout) findViewById(R.id.ctl_boss_one_position);
        mTvBossOneWorkText = (TextView) findViewById(R.id.tv_boss_one_work_text);
        mEtBossOneEmail = (EditText) findViewById(R.id.et_boss_one_email);
        mCtlBossOneEmail = (ConstraintLayout) findViewById(R.id.ctl_boss_one_email);
        mBtnBossOneNext = (Button) findViewById(R.id.btn_boss_one_next);
        mCtlBossOneCompany.setOnClickListener(this);
        mBtnBossOneNext.setOnClickListener(this);
        mCtlBossOneName.setOnClickListener(this);
        mIvBossOneHeader.setOnClickListener(this);
    }

    private void submit() {
        if (StringUtil.isEmpty(mPath)) {
            T.showToast(mContext, "请选择头像");
            return;
        }
        String name = StringUtil.getObjectToStr(mEtBossOneRealName);
        if (StringUtil.isEmpty(name)) {
            T.showToast(mContext, getResources().getString(R.string.hint_req_job_name));
            return;
        }

        if (mSelectCompany == null) {
            T.showToast(mContext, "请选择公司全称");
            return;
        }

        String position = mEtBossOnePosition.getText().toString().trim();
        if (TextUtils.isEmpty(position)) {
            Toast.makeText(this, "请填写您的职务", Toast.LENGTH_SHORT).show();
            return;
        }

        String email = mEtBossOneEmail.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "请填写邮箱", Toast.LENGTH_SHORT).show();
            return;
        }

        showProgress();
        UpdataImagHttp.getSingleton(this).updataImage("", mPath, new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                GmImagerVo mVo = GsonUtils.getGson(success, GmImagerVo.class);
                GmImagerVo.ResultBean bean = mVo.getResult();
                submitInfom(bean.getImg_url(), name, mSelectCompany.getId(), position,
                        email);

            }

            @Override
            public void error(String error) {
                onError();
            }
        });


    }

    private void submitInfom(String img_url, String name, int id, String position, String email) {
        RequestBossHttp.getSingleton(this).submitAddBaseinfo(img_url, name, String.valueOf(id),
                position, email, new RequestResultCallback() {
                    @Override
                    public void success(String success) {
                        if (onSuccess(success)) return;
                        T.showToast(mContext, "提交成功");
                        mResultTo.startBossName(mContext);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        if (requestCode == CANMERCODE && resultCode == RESULT_OK) {
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
                    showImag(mIvBossOneHeader, s);
                }

                @Override
                public void imgError() {
                    dismissProgress();
                    T.showToast(mContext, "图片压缩失败,请重新选择");
                }
            });
            return;
        }

        if (requestCode == XIANGCECODE && resultCode == RESULT_OK) {
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
                    showImag(mIvBossOneHeader, path);
                }

                @Override
                public void imgError() {
                    dismissProgress();
                    T.showToast(mContext, "图片压缩失败,请重新选择");
                }
            });
            return;
        }
        //公司名称
        if (resultCode == RESULT_OK && requestCode == REQUESTCOMPANYCODE) {
            mSelectCompany = (CompanySearchVo.ResultBean) data.getSerializableExtra(SELECTCOMPANY);
            mTvBossOneCompany.setText(mSelectCompany.getFull_name());
        }
    }
}
