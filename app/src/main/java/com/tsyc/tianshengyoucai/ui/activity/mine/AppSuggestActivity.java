package com.tsyc.tianshengyoucai.ui.activity.mine;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Config;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.listener.MyTextWatcherListener;
import com.tsyc.tianshengyoucai.model.adapter.ShowImageAdapter;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.model.bean.NormalResultBean;
import com.tsyc.tianshengyoucai.model.bean.UploadImageBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.requet.RequestJobHttp;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.ActivityUtil;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.FileUtil;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.LunBanUtil;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.utils.Util;
import com.tsyc.tianshengyoucai.utils.matisse_picker.Glide4Engine;
import com.tsyc.tianshengyoucai.view.SelectCamerAlerdialog;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * author：van
 * CreateTime：2019/8/6
 * File description：举报商家
 */
@RuntimePermissions
public class AppSuggestActivity extends BaseActivity {


    private static final int REQUEST_CODE_CHOOSE = 1001;

    @BindView(R.id.et_input_suggest)
    EditText etInputSuggest;
    @BindView(R.id.tv_text_lenght)
    TextView tvTextLenght;
    @BindView(R.id.rv_img)
    RecyclerView rvImg;
    @BindView(R.id.tv_report_tip)
    TextView tvReportTip;
    private List<String> uploadImage = new ArrayList<>();// 上传的图片结合


    private List<String> selectImage = new ArrayList<>(); //选择图片集合
    private String storeId;
    /**
     * 打开相机回调
     */
    private static final int OPENCAMMER = 1055;
    private static final int FIVE_NMUBER = 5;
    private ShowImageAdapter imageAdapter;
    /**
     * 职位就举报
     */
    public static final String JOBTYPE = "TYPE";


    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private ShowImageAdapter qualifyAdapter;
    private String suggest;
    private String mJobType;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_report_shop;
    }

    @Override
    public void initView() {
        Bundle extra = getIntent().getBundleExtra(Constant.bundleExtra);
        if (null != extra) {
            storeId = extra.getString("store_id");
            mJobType = extra.getString(JOBTYPE);
        }
        if (StringUtil.isEmpty(mJobType)) {
            mTvTitle.setText(getString(R.string.text_advice));
        } else {
            mTvTitle.setText("举报商家");
        }
        rvImg.setHasFixedSize(true);
        rvImg.setLayoutManager(new GridLayoutManager(mContext, 3));

        qualifyAdapter = new ShowImageAdapter();
        rvImg.setAdapter(qualifyAdapter);

        etInputSuggest.addTextChangedListener(new MyTextWatcherListener() {
            @Override
            public void afterTextChanged(Editable s) {
                tvTextLenght.setText(s.length() + "/200");
            }
        });
    }

    @OnClick({R.id.iv_up_img, R.id.btn_commit})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_up_img:
                if (selectImage != null && selectImage.size() >= 5) {
                    XToast.normal("图片最多只能上传5张");
                    return;
                }
                AppSuggestActivityPermissionsDispatcher.showCameraWithPermissionCheck(AppSuggestActivity.this);
                break;
            case R.id.btn_commit:
                suggest = etInputSuggest.getText().toString().trim();
                if (TextUtils.isEmpty(suggest)) {
                    XToast.normal("请输入您的意见或建议");
                    return;
                }

                if (selectImage.size() == 0) {
                    XToast.normal("请上传店面照片");
                    return;
                }

                if (Util.handleOnDoubleClick()) {
                    return;
                }

                commitPhoto();
                break;
        }
    }

    //上传数据
    private void commitPhoto() {
        uploadImage.clear();
        for (int i = 0; i < selectImage.size(); i++) {
            File file = new File(selectImage.get(i));
            mHandler.postDelayed(() -> uploadImage(file), 300);
        }
    }


    //上传图片
    private void uploadImage(File file) {

        loading(true);
        Map<String, String> params = new HashMap<>();
        params.put("type", "store_apply");

        BaseRequestUtils.postRequestWithPhoto(this, UrlManager.uploadImage, file, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {

                XLog.e("上传图片 " + response.body());
                UploadImageBean uploadImageBean = FastJsonUtil.fromJson(response.body(), UploadImageBean.class);
                if (null == uploadImageBean) {
                    XToast.normal("图片上传失败");
                    dismiss();
                    return;
                }
                if (!uploadImageBean.getCode().equals("200") || uploadImageBean.getResult() == null) {
                    XToast.normal("图片上传失败");
                    dismiss();
                    return;
                }
                String imgUrl = uploadImageBean.getResult().getImg_url();
                uploadImage.add(imgUrl);
                if (uploadImage.size() >= selectImage.size()) {
                    commitReport();
                }

            }

            @Override
            public void failed(Response<String> response) {
                dismiss();
                XToast.normal("图片上传失败,请稍候再试");
            }
        });
    }

    private void commitReport() {
        if (StringUtil.isEmpty(mJobType)) {
            Map<String, String> params = new HashMap<>();
            params.put("feedback", suggest);
            for (int i = 1; i < uploadImage.size(); i++) {
                params.put("image", uploadImage.get(i));
            }
            BaseRequestUtils.postRequestWithHeader(this, Config.BASE_URL + getString(R.string.suggesst), params, new BaseRequestUtils.getRequestCallBack() {
                @Override
                public void success(Response<String> response) {

                    XLog.e("投诉建议 " + response.body());
                    dismiss();
                    NormalResultBean resultBean = FastJsonUtil.fromJson(response.body(), NormalResultBean.class);
                    if (null == resultBean) {
                        XToast.normal("发送失败,请稍后再试");
                        return;
                    }

                    if (!resultBean.getCode().equals(Constant.REQUEST_SUCCESS) || resultBean.getResult() == null) {
                        XToast.normal(resultBean.getMessage());
                        return;
                    }

                    XToast.normal(resultBean.getMessage());

                    AppSuggestActivity.this.finish();
                }

                @Override
                public void failed(Response<String> response) {
                    XToast.normal("网络请求失败，请稍候再试");
                    dismiss();
                }
            });
        } else {
            showProgress();
            RequestJobHttp.getSingleton(this).submitCompanyReport(storeId, suggest, uploadImage, new RequestResultCallback() {
                @Override
                public void success(String success) {
                    dismissProgress();
                    NormalBean vo = GsonUtils.getGson(success, NormalBean.class);
                    if (vo.getCode().equals("100")) {
                        T.showToast(mContext, vo.getMessage());
                        return;
                    }
                    T.showToast(mContext, "提交成功");
                    mResultTo.finishBase(mContext);
                }

                @Override
                public void error(String error) {
                    dismissProgress();
                    toastErrorNet();
                }
            });
        }

    }


    private void selectPhoto(int imageCount) {
        Matisse.from(AppSuggestActivity.this)
                .choose(MimeType.ofImage(), true)
                .showSingleMediaType(true)
                .countable(false)
                .capture(false)
                .maxSelectable(imageCount)
//                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.dp_120))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(new Glide4Engine())    // for glide-V4
                .originalEnable(true)
                .maxOriginalSize(10)
                .autoHideToolbarOnSingleTap(true)
                .forResult(REQUEST_CODE_CHOOSE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<String> list = Matisse.obtainPathResult(data);
            if (list.size() > 0) {
                LunBanUtil.getSingleton(mContext).lunBanImagerS(list, new LunBanUtil.lunBanInterface() {
                    @Override
                    public void imgStart() {
                        showProgress();
                    }

                    @Override
                    public void imgSuccess(String path) {
                        dismissProgress();
                        showSelectImag(path);
                    }

                    @Override
                    public void imgError() {
                        dismissProgress();
                        T.showToast(mContext, "图片压缩失败,请重新选择");
                    }
                });


            } else {
                XLog.e("没有选择图片 不上传");
            }
        }
        if (requestCode == OPENCAMMER && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            Bitmap bimap = (Bitmap) bundle.get("data");
            String s = FileUtil.saveImag(mContext, bimap);
            LunBanUtil.getSingleton(mContext).lunBanImager(s, new LunBanUtil.lunBanInterface() {
                @Override
                public void imgStart() {
                    showProgress();
                }

                @Override
                public void imgSuccess(String path) {
                    dismissProgress();
                    showSelectImag(path);
                }

                @Override
                public void imgError() {
                    dismissProgress();
                    T.showToast(mContext, "图片压缩失败,请重新选择");
                }
            });
        }
    }

    private void showSelectImag(String path) {
        ArrayList<String> pathList = new ArrayList<>();
        pathList.add(path);
        selectPhotoListener(pathList);
    }


    private void selectPhotoListener(List<String> pathList) {

        selectImage.addAll(pathList);

        qualifyAdapter.setNewData(selectImage);
        qualifyAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            selectImage.remove(position);
            qualifyAdapter.notifyDataSetChanged();
        });
    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //ShopDetailActivityPermissionsDispatcher.onSkipIntentWithPermissionCheck(ShopDetailActivity.this);
        AppSuggestActivityPermissionsDispatcher.showCameraWithPermissionCheck(AppSuggestActivity.this);
    }


    @NeedsPermission(Manifest.permission.CAMERA)
    void showCamera() {
        SelectCamerAlerdialog mCamerAlerdialog = new SelectCamerAlerdialog(mContext, R.style.mydialog);
        mCamerAlerdialog.setListener(new SelectCamerAlerdialog.OnItemClickListener() {
            @Override
            public void openCamer() {
                ActivityUtil.openCammer(AppSuggestActivity.this, OPENCAMMER);
            }

            @Override
            public void openXinCe() {

                selectPhoto(FIVE_NMUBER - selectImage.size());
            }
        });
        mCamerAlerdialog.show();
    }


    @OnShowRationale(Manifest.permission.CAMERA)
    void showRationaleForCamera(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage(R.string.permission_camera_rationale)
                .setPositiveButton(R.string.button_allow, (dialog, button) -> request.proceed())
                .setNegativeButton(R.string.button_deny, (dialog, button) -> request.cancel())
                .show();
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
}



