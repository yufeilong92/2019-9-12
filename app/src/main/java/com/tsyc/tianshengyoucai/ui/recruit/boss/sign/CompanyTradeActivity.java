package com.tsyc.tianshengyoucai.ui.recruit.boss.sign;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.requet.UpdataImagHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.ActivityUtil;
import com.tsyc.tianshengyoucai.utils.FileUtil;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.LunBanUtil;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.view.SelectCamerAlerdialog;
import com.tsyc.tianshengyoucai.vo.GmImagerVo;
import com.youth.xframe.widget.XToast;
import com.zhihu.matisse.Matisse;

import java.util.List;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/9/3 10:57:
 * @Purpose :上传营业执照
 */
@RuntimePermissions
public class CompanyTradeActivity extends Base2Activity implements View.OnClickListener {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private TextView mTvNameTip;
    private TextView mTvCompanyName;
    private TextView mTvContentLength;
    private ConstraintLayout mCtlCompanyName;
    private ImageView mIvImage;
    private ConstraintLayout mCtlImage;
    private TextView mTvRule;
    private TextView mTvNext;
    /**
     * 公司名称
     */
    public static final String COMPANYNAME = "companyname";
    private String mCompanyName;
    private SelectCamerAlerdialog mSelectAlerdialog;
    /**
     * 相机
     */
    private static final int CANNER_REQUESTCODE = 1021;
    /**
     * 相册
     */
    private static final int XIANGCE_REQUESTCODE = 1022;
    /**
     * 图片地址
     */
    private String mPath;


//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_company_trade);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_company_trade;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        if (getIntent() != null) {
            mCompanyName = getIntent().getStringExtra(COMPANYNAME);
        }
        initView();
        initEvent();
        bindView();
    }

    private void bindView() {

        mTvCompanyName.setText(mCompanyName);
    }

    private void initEvent() {
        String ruleContent = mTvRule.getText().toString();
        SpannableString spannableString = new SpannableString(ruleContent);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#5584F6"));
        spannableString.setSpan(colorSpan, 5, 7, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(colorSpan, 18, 20, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(colorSpan, 36, ruleContent.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mTvRule.setText(spannableString);
    }

    private void initView() {

        mTvNameTip = (TextView) findViewById(R.id.tv_name_tip);
        mTvCompanyName = (TextView) findViewById(R.id.tv_company_name);
        mCtlCompanyName = (ConstraintLayout) findViewById(R.id.ctl_company_name);
        mIvImage = (ImageView) findViewById(R.id.iv_image);
        mCtlImage = (ConstraintLayout) findViewById(R.id.ctl_image);
        mCtlImage.setOnClickListener(this);
        mTvRule = (TextView) findViewById(R.id.tv_rule);
        mTvNext = (TextView) findViewById(R.id.tv_next);
        mTvNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ctl_image:
                CompanyTradeActivityPermissionsDispatcher.showCameraWithPermissionCheck(CompanyTradeActivity.this);
                break;
            case R.id.tv_next:
                submit();
                break;
            default:
                break;
        }

    }

    private void submit() {

        if (StringUtil.isEmpty(mPath)) {
            T.showToast(mContext, "请选择营业执照");
            return;
        }
        showProgress();
        UpdataImagHttp.getSingleton(this).updataImage("", mPath, new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                T.showToast(mContext, "提交成功");
                GmImagerVo vo = GsonUtils.getGson(success, GmImagerVo.class);
                GmImagerVo.ResultBean result = vo.getResult();
                Intent intent = new Intent();
                intent.putExtra(NewCompanyActivity.UPDATAIMAGETAG, result.getImg_url());
                setResult(RESULT_OK, intent);
                mResultTo.finishBase(mContext);
            }

            @Override
            public void error(String error) {
                onError();
            }
        });

    }

    private void showAlertDialog() {
        mSelectAlerdialog = new SelectCamerAlerdialog(mContext, R.style.mydialog);
        mSelectAlerdialog.initData(mContext);
        mSelectAlerdialog.setListener(new SelectCamerAlerdialog.OnItemClickListener() {
            @Override
            public void openCamer() {
                ActivityUtil.openCammer(CompanyTradeActivity.this, CANNER_REQUESTCODE);
            }

            @Override
            public void openXinCe() {
                ActivityUtil.openXiangCes(CompanyTradeActivity.this, XIANGCE_REQUESTCODE);
            }
        });
        mSelectAlerdialog.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        if (requestCode == CANNER_REQUESTCODE && resultCode == RESULT_OK) {
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
                    showImag(mIvImage, s);
                }

                @Override
                public void imgError() {
                    dismissProgress();
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
                    showImag(mIvImage, path);
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

    private void showImag(ImageView mIvImage, String path) {
        mIvImage.setImageBitmap(BitmapFactory.decodeFile(path));
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


}
