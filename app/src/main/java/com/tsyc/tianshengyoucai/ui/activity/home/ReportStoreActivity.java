package com.tsyc.tianshengyoucai.ui.activity.home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
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
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.listener.MyTextWatcherListener;
import com.tsyc.tianshengyoucai.model.adapter.ShowImageAdapter;
import com.tsyc.tianshengyoucai.model.bean.NormalResultBean;
import com.tsyc.tianshengyoucai.model.bean.UploadImageBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.ApplyBackMoneyActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.ActivityUtil;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.FileUtil;
import com.tsyc.tianshengyoucai.utils.LunBanUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.utils.matisse_picker.GifSizeFilter;
import com.tsyc.tianshengyoucai.utils.matisse_picker.Glide4Engine;
import com.tsyc.tianshengyoucai.view.SelectCamerAlerdialog;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

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
public class ReportStoreActivity extends BaseActivity {

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
    private static final int NINE_NMUBER = 9;
    private ShowImageAdapter imageAdapter;

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private ShowImageAdapter qualifyAdapter;
    private String content;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_report_shop;
    }

    @Override
    public void initView() {
        mTvTitle.setText(getString(R.string.text_report_shop));

        rvImg.setHasFixedSize(true);
        rvImg.setLayoutManager(new GridLayoutManager(mContext, 3));

        Bundle extra = getIntent().getBundleExtra(Constant.bundleExtra);
        if (null != extra) {
            storeId = extra.getString("store_id");
        }
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
                if (selectImage != null) {
                    if (selectImage.size() >= 5) {
                        T.showToast(mContext, "最多选择5张照片");
                        return;
                    }
                }
                ReportStoreActivityPermissionsDispatcher.showCameraWithPermissionCheck(ReportStoreActivity.this);
                break;
            case R.id.btn_commit:
                commitPhoto();

                break;
        }
    }

    //上传数据
    private void commitPhoto() {

        content = etInputSuggest.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            XToast.normal("请输入内容");
            return;
        }
        if (selectImage.size() == 0) {
            XToast.normal("请上传凭证");
            return;
        }
        loading(false);
        uploadImage.clear();
        for (int i = 0; i < selectImage.size(); i++) {
            File file = new File(selectImage.get(i));
            mHandler.postDelayed(() -> uploadImage(file), 300);
        }
    }


    //上传图片
    private void uploadImage(File file) {

        // loading(true, "图片上传中...");
        Map<String, String> params = new HashMap<>();
        params.put("type", "store_apply");

        BaseRequestUtils.postRequestWithPhoto(this, UrlManager.uploadImage, file, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("上传图片 " + response.body());
                UploadImageBean uploadImageBean = FastJsonUtil.fromJson(response.body(), UploadImageBean.class);
                if (null == uploadImageBean) {
                    dismiss();
                    XToast.normal("图片上传失败");
                    return;
                }
                if (!uploadImageBean.getCode().equals("200") || uploadImageBean.getResult() == null) {
                    XToast.normal("图片上传失败");
                    dismiss();
                    return;
                }
                String imgUrl = uploadImageBean.getResult().getImg_url();
                uploadImage.add(imgUrl);
                if (uploadImage.size() == selectImage.size()) {
                    commitReport();
                }

            }

            @Override
            public void failed(Response<String> response) {
                dismiss();

            }
        });
    }

    private void commitReport() {

        Map<String, String> params = new HashMap<>();

        params.put("store_id", storeId);
        params.put("content", content);
        for (int i = 1; i < uploadImage.size(); i++) {
            if (i < 3)
                params.put("inform_pic" + i, uploadImage.get(i));
        }

        BaseRequestUtils.postRequestWithHeader(this, UrlManager.reportStore, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("举报" + response.body());
                dismiss();
                NormalResultBean resultBean = FastJsonUtil.fromJson(response.body(), NormalResultBean.class);
                if (null == resultBean) {
                    XToast.normal("举报失败");
                    return;
                }

                if (!resultBean.getCode().equals(Constant.REQUEST_SUCCESS) || resultBean.getResult() == null) {
                    XToast.normal(resultBean.getMessage());
                    return;
                }
                XToast.normal(resultBean.getMessage());
                ReportStoreActivity.this.finish();
            }

            @Override
            public void failed(Response<String> response) {
                XToast.normal("网络请求失败，请稍候再试");
                dismiss();
            }
        });
    }


    private void selectPhoto(int imageCount) {
        Matisse.from(ReportStoreActivity.this)
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
                selectPhotoListener(list);

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
        ReportStoreActivityPermissionsDispatcher.showCameraWithPermissionCheck(ReportStoreActivity.this);
    }


    @NeedsPermission(Manifest.permission.CAMERA)
    void showCamera() {
        SelectCamerAlerdialog mCamerAlerdialog = new SelectCamerAlerdialog(mContext, R.style.mydialog);
        mCamerAlerdialog.setListener(new SelectCamerAlerdialog.OnItemClickListener() {
            @Override
            public void openCamer() {
                ActivityUtil.openCammer(ReportStoreActivity.this, OPENCAMMER);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
