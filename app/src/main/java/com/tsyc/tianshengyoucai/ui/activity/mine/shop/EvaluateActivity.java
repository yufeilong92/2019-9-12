package com.tsyc.tianshengyoucai.ui.activity.mine.shop;

import android.Manifest;
import android.annotation.SuppressLint;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.listener.MyTextWatcherListener;
import com.tsyc.tianshengyoucai.listener.event.OrderDetailInEvent;
import com.tsyc.tianshengyoucai.listener.event.UnderDetailInEvent;
import com.tsyc.tianshengyoucai.listener.event.UnifiedNotifyEvent;
import com.tsyc.tianshengyoucai.model.adapter.ShowImageAdapter;
import com.tsyc.tianshengyoucai.model.bean.NormalResultBean;
import com.tsyc.tianshengyoucai.model.bean.UploadImageBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.activity.mine.BecomeShopActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.ActivityUtil;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.FileUtil;
import com.tsyc.tianshengyoucai.utils.ImageLoader;
import com.tsyc.tianshengyoucai.utils.LunBanUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.utils.matisse_picker.GifSizeFilter;
import com.tsyc.tianshengyoucai.utils.matisse_picker.Glide4Engine;
import com.tsyc.tianshengyoucai.view.SelectCamerAlerdialog;
import com.tsyc.tianshengyoucai.view.StarBar;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

/**
 * author：van
 * CreateTime：2019/8/10
 * File description： 评价
 */
@RuntimePermissions
public class EvaluateActivity extends BaseActivity {

    private static final int REQUEST_CODE_CHOOSE = 103;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_text_count)
    TextView tvTextCount;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.sb_desc)
    StarBar sbDesc;
    @BindView(R.id.tv_desc_score)
    TextView tvDescScore;
    @BindView(R.id.sb_logistic)
    StarBar sbLogistic;
    @BindView(R.id.tv_logistic_score)
    TextView tvLogisticScore;
    @BindView(R.id.sb_service)
    StarBar sbService;
    @BindView(R.id.tv_service_score)
    TextView tvServiceScore;
    @BindView(R.id.rv_img)
    RecyclerView rvImg;
    private List<String> selectShopImage = new ArrayList<>();
    private List<String> uploadShopImage = new ArrayList<>();

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);


        }
    };
    private String goodsId;
    /**
     * 打开相机回调
     */
    private static final int OPENCAMMER = 1055;
    private static final int FIVE_NMUBER = 5;
    private static final int NINE_NMUBER = 9;
    private ShowImageAdapter imageAdapter;
    private String orderIn;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_evaluate;
    }

    @Override
    public void initView() {
        mTvTitle.setText("发表评论");

        Bundle extra = getIntent().getBundleExtra(Constant.bundleExtra);
        if (extra != null) {
            goodsId = extra.getString("order_id");
            String goodsImage = extra.getString("goods_image");
            String goodsName = extra.getString("goods_name");
            orderIn = extra.getString("order_in");
            ImageLoader.loadCenterCrop(mContext, goodsImage, ivIcon, 10);
            tvName.setText(goodsName);
        }

        rvImg.setHasFixedSize(true);
        rvImg.setLayoutManager(new GridLayoutManager(mContext, 3));

        imageAdapter = new ShowImageAdapter();
        rvImg.setAdapter(imageAdapter);

        imageAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            selectShopImage.remove(position);
            imageAdapter.notifyDataSetChanged();
        });
        setStarBarStyle(sbDesc, tvDescScore);
        setStarBarStyle(sbLogistic, tvLogisticScore);
        setStarBarStyle(sbService, tvServiceScore);

        etContent.addTextChangedListener(new MyTextWatcherListener() {
            @Override
            public void afterTextChanged(Editable s) {
                tvTextCount.setText(s.length() + "/200");
            }
        });
    }

    private void setStarBarStyle(StarBar sb, TextView text) {
        sb.setCanTouch(true);
        sb.setIntegerMark(true);
        sb.setOnStarChangeListener(mark -> {
            int starMark = (int) sb.getStarMark();
            text.setText(String.valueOf(starMark));
            if (starMark == 1) {
                text.setText("差");
            } else if (starMark == 2) {
                text.setText("较差");
            } else if (starMark == 3) {
                text.setText("一般");
            } else if (starMark == 4) {
                text.setText("好");
            } else {
                text.setText("很好");
            }
        });
    }


    @OnClick({R.id.iv_image, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_image:
                if (selectShopImage != null && selectShopImage.size() >= 5) {
                    XToast.normal("图片最多只能上传5张");
                    return;
                }
                EvaluateActivityPermissionsDispatcher.showCameraWithPermissionCheck(EvaluateActivity.this);
                break;
            case R.id.btn_commit:
                commitPhoto();
                break;
        }
    }


    //上传数据
    private void commitPhoto() {

        if (selectShopImage.size() == 0) {
            XToast.normal("请上传图片凭证");
            return;
        }
        uploadShopImage.clear();
        for (int i = 0; i < selectShopImage.size(); i++) {
            File file = new File(selectShopImage.get(i));
            mHandler.postDelayed(() -> uploadImage(file, "image"), 300);
        }

    }

    //上传图片
    private void uploadImage(File file, String type) {

        loading(true);
        Map<String, String> params = new HashMap<>();
        params.put("type", "evaluate");

        BaseRequestUtils.postRequestWithPhoto(this, UrlManager.uploadImage, file, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("上传图片 " + response.body());
                dismiss();
                UploadImageBean uploadImageBean = FastJsonUtil.fromJson(response.body(), UploadImageBean.class);
                if (null == uploadImageBean) {
                    XToast.normal("图片上传失败");
                    return;
                }
                if (!uploadImageBean.getCode().equals("200") || uploadImageBean.getResult() == null) {
                    XToast.normal("图片上传失败");
                    return;
                }
                String imgUrl = uploadImageBean.getResult().getImg_url();

                uploadShopImage.add(imgUrl);

                if (uploadShopImage.size() == selectShopImage.size()) {
                    commitInfo();
                }

            }

            @Override
            public void failed(Response<String> response) {
                dismiss();
                XToast.normal(getString(R.string.service_error));
            }
        });
    }

    private void commitInfo() {

        String desc = tvDescScore.getText().toString();
        String logistic = tvLogisticScore.getText().toString();
        String service = tvServiceScore.getText().toString();

        String content = etContent.getText().toString().trim();

        if (TextUtils.isEmpty(content)) {
            XToast.normal("请输入评价内容");
            return;
        }
        if (TextUtils.isEmpty(desc) || TextUtils.isEmpty(logistic) || TextUtils.isEmpty(service)) {
            XToast.normal("请选择评分");
            return;
        }

        loading(true);
        Map<String, String> params = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < uploadShopImage.size(); i++) {
            sb.append("\"" + uploadShopImage.get(i) + "\"" + ",");
        }
        if (sb.toString().endsWith(",")) {
            int i = sb.toString().lastIndexOf(",");
            sb.replace(i, i + 1, "");
        }
        sb.append("]");
        params.put("order_id", goodsId);
        params.put("geval_express", String.valueOf(sbLogistic.getStarMark()));
        params.put("geval_description", String.valueOf(sbDesc.getStarMark()));
        params.put("geval_serive", String.valueOf(sbService.getStarMark()));
        params.put("geval_content", content);
        params.put("images", sb.toString());
        XLog.e("上传图片 sb.toString() " + sb.toString());
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.evaluation_add, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                dismiss();
                NormalResultBean resultBean = FastJsonUtil.fromJson(response.body(), NormalResultBean.class);
                if (null == resultBean) {
                    XToast.normal("评价失败");
                    return;
                }
                if (!resultBean.getCode().equals(Constant.REQUEST_SUCCESS)) {
                    XToast.normal(resultBean.getMessage());
                    return;
                }

                XToast.normal(resultBean.getMessage());
                EventBus.getDefault().post(new UnifiedNotifyEvent(Constant.APPLY_BACK_MONEY));
                notifyList();
                finish();

            }

            @Override
            public void failed(Response<String> response) {
                dismiss();
                XToast.normal(getString(R.string.service_error));
            }
        });

    }

    private void notifyList() {
        if (orderIn != null && orderIn.equals("online_order")) {
            EventBus.getDefault().post(new OrderDetailInEvent("ok"));
        }
        if (orderIn != null && orderIn.equals("underline_order")) {
            EventBus.getDefault().post(new UnderDetailInEvent("ok"));
        }
    }

    private void selectPhoto(int imageCount) {
        Matisse.from(EvaluateActivity.this)
                .choose(MimeType.ofImage(), true)
                .showSingleMediaType(true)
                .countable(false)
                .capture(false)
//                .captureStrategy(
//                        new CaptureStrategy(true,
//                                "com.tsyc.tianshengyoucai.fileprovider",
//                                "tsyc"))  // 设置正确，才能调起相机
                .maxSelectable(imageCount)
//                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.dp_120))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(new Glide4Engine())    // for glide-V4
//                .setOnSelectedListener((uriList, pathList) -> {
//                    selectPhotoListener(pathList);
//                })
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
        selectShopImage.addAll(pathList);

        imageAdapter.setNewData(selectShopImage);

    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //ShopDetailActivityPermissionsDispatcher.onSkipIntentWithPermissionCheck(ShopDetailActivity.this);
        EvaluateActivityPermissionsDispatcher.showCameraWithPermissionCheck(EvaluateActivity.this);
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    void showCamera() {
        SelectCamerAlerdialog mCamerAlerdialog = new SelectCamerAlerdialog(mContext, R.style.mydialog);
        mCamerAlerdialog.setListener(new SelectCamerAlerdialog.OnItemClickListener() {
            @Override
            public void openCamer() {
                ActivityUtil.openCammer(EvaluateActivity.this, OPENCAMMER);
            }

            @Override
            public void openXinCe() {

                selectPhoto(FIVE_NMUBER - selectShopImage.size());
            }
        });
        mCamerAlerdialog.show();
    }


 /*   @OnShowRationale(Manifest.permission.CAMERA)
    void showRationaleForCamera(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage(R.string.permission_camera_rationale)
                .setPositiveButton(R.string.button_allow, (dialog, button) -> request.proceed())
                .setNegativeButton(R.string.button_deny, (dialog, button) -> request.cancel())
                .show();
    }*/


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
