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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.listener.MyTextWatcherListener;
import com.tsyc.tianshengyoucai.listener.event.OrderDetailInEvent;
import com.tsyc.tianshengyoucai.listener.event.UnderDetailInEvent;
import com.tsyc.tianshengyoucai.listener.event.UnifiedNotifyEvent;
import com.tsyc.tianshengyoucai.model.adapter.BackMoneyRecAdapter;
import com.tsyc.tianshengyoucai.model.adapter.BackMoneyRecShopAdapter;
import com.tsyc.tianshengyoucai.model.adapter.ShowImageAdapter;
import com.tsyc.tianshengyoucai.model.bean.BackMoneyBean;
import com.tsyc.tianshengyoucai.model.bean.NormalResultBean;
import com.tsyc.tianshengyoucai.model.bean.UploadImageBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.ActivityUtil;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.FileUtil;
import com.tsyc.tianshengyoucai.utils.LunBanUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.utils.matisse_picker.GifSizeFilter;
import com.tsyc.tianshengyoucai.utils.matisse_picker.Glide4Engine;
import com.tsyc.tianshengyoucai.view.SelectCamerAlerdialog;
import com.tsyc.tianshengyoucai.view.SimpleDividerItemDecortion;
import com.tsyc.tianshengyoucai.view.pop.OrderBackMoneyPop;
import com.tsyc.tianshengyoucai.view.pop.OrderBackMoneyPop2;
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
import java.util.PriorityQueue;

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
 * File description：线上线下订单申请退款
 */
@RuntimePermissions
public class ApplyBackMoneyActivity extends BaseActivity {

    private static final int REQUEST_CODE_CHOOSE = 103;
    private final int BACK_DATA = 100;
    @BindView(R.id.tv_choose_reason)
    TextView tvChooseReason;
    @BindView(R.id.tv_choose_rec)
    TextView tvChooseRec;
    @BindView(R.id.tv_apply_reason)
    TextView tvApplyReason;
    @BindView(R.id.tv_should_money)
    TextView tvShouldMoney;
    @BindView(R.id.tv_money_tip)
    TextView tvMoneyTip;
    @BindView(R.id.et_apply_tip)
    EditText etApplyTip;
    @BindView(R.id.rv_img)
    RecyclerView rvImg;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_content_length)
    TextView tvContentLength;

    private List<String> selectShopImage = new ArrayList<>();
    private List<String> uploadShopImage = new ArrayList<>();

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == BACK_DATA) {
                requestBackMoneyData();
            }

        }
    };
    private String orderId;
    private OptionsPickerView pvOptions;
    private BackMoneyBean.ResultBean result;
    private String orderIn;

    /**
     * 打开相机回调
     */
    private static final int OPENCAMMER = 1055;
    private static final int FIVE_NMUBER = 5;
    private static final int NINE_NMUBER = 9;
    private ShowImageAdapter imageAdapter;
    private String chooseReason;
    private String chooseRec;
    private String applyReason;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_app_back_money;
    }

    @Override
    public void initView() {
        mTvTitle.setText("申请退款");
        Bundle extra = getIntent().getBundleExtra(Constant.bundleExtra);
        if (extra != null) {
            orderId = extra.getString("order_id");
            orderIn = extra.getString("order_in");

            mHandler.sendEmptyMessage(BACK_DATA);
            loading(true);
        } else {
            XToast.normal("无效订单");
        }

        rvImg.setHasFixedSize(true);
        rvImg.setLayoutManager(new GridLayoutManager(mContext, 3));

        etApplyTip.addTextChangedListener(new MyTextWatcherListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                tvContentLength.setText(s.length() + "/200");
            }
        });

         imageAdapter = new ShowImageAdapter();
        rvImg.setAdapter(imageAdapter);
    }

    @OnClick({R.id.iv_image, R.id.btn_commit, R.id.tv_choose_reason, R.id.tv_choose_rec, R.id.tv_apply_reason})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_image:
                if (selectShopImage!=null&&selectShopImage.size()>=5){
                    XToast.normal("图片最多只能上传5张");
                    return;
                }
                ApplyBackMoneyActivityPermissionsDispatcher.showCameraWithPermissionCheck(ApplyBackMoneyActivity.this);
                break;
            case R.id.btn_commit:

                commitPhoto();
                break;

            case R.id.tv_choose_reason: //退款

                chooseReason(result.getRefund_type());
                break;
            case R.id.tv_choose_rec: // 是否收到货
                chooseRec(result.getGoods_status());
                break;
            case R.id.tv_apply_reason: //原因
                chooseApplyReason(result.getGoods_reason());
                break;
        }
    }

    private void chooseApplyReason(List<String> goods_reason) {
        if (goods_reason == null)
            return;

        OrderBackMoneyPop2 pop = new OrderBackMoneyPop2(this);
        pop.showPopupWindow();

        RecyclerView rvReason = pop.findViewById(R.id.rv_reason);
        TextView tvPopTitle = pop.findViewById(R.id.tv_pop_title);
        tvPopTitle.setText("选择申请原因");
        rvReason.setHasFixedSize(true);
        rvReason.setLayoutManager(new LinearLayoutManager(mContext));
        rvReason.addItemDecoration(new SimpleDividerItemDecortion(mContext));
        BackMoneyRecShopAdapter adapter = new BackMoneyRecShopAdapter(goods_reason);
        rvReason.setAdapter(adapter);

        adapter.setOnItemClickListener((adapter1, view, position) -> {
            String name = goods_reason.get(position);
            tvApplyReason.setText(name);
            pop.dismiss();
        });

    }

    //是否收货
    private void chooseRec(List<String> goods_status) {
        if (goods_status == null)
            return;

        OrderBackMoneyPop2 pop = new OrderBackMoneyPop2(this);
        pop.showPopupWindow();

        RecyclerView rvReason = pop.findViewById(R.id.rv_reason);
        TextView tvPopTitle = pop.findViewById(R.id.tv_pop_title);
        tvPopTitle.setText("选择收货状态");
        rvReason.setHasFixedSize(true);
        rvReason.setLayoutManager(new LinearLayoutManager(mContext));
        rvReason.addItemDecoration(new SimpleDividerItemDecortion(mContext));
        BackMoneyRecShopAdapter adapter = new BackMoneyRecShopAdapter(goods_status);
        rvReason.setAdapter(adapter);

        adapter.setOnItemClickListener((adapter1, view, position) -> {
            String name = goods_status.get(position);
            tvChooseRec.setText(name);
            pop.dismiss();
        });
    }

    //申请类型
    private void chooseReason(List<BackMoneyBean.ResultBean.RefundTypeBean> refund_type) {
        if (refund_type == null)
            return;

        OrderBackMoneyPop pop = new OrderBackMoneyPop(this);
        pop.showPopupWindow();

        RecyclerView rvReason = pop.findViewById(R.id.rv_reason);
        RelativeLayout rl_back = pop.findViewById(R.id.rl_back);
        TextView tv_pop_title = pop.findViewById(R.id.tv_pop_title);
        tv_pop_title.setText("选择申请类型");
        rl_back.setOnClickListener(v -> pop.dismiss());

        rvReason.setHasFixedSize(true);
        rvReason.setLayoutManager(new LinearLayoutManager(mContext));
        rvReason.addItemDecoration(new SimpleDividerItemDecortion(mContext));
        BackMoneyRecAdapter adapter = new BackMoneyRecAdapter(refund_type);
        rvReason.setAdapter(adapter);

        adapter.setOnItemClickListener((adapter1, view, position) -> {
            BackMoneyBean.ResultBean.RefundTypeBean refundTypeBean = refund_type.get(position);
            String name = refundTypeBean.getName();
            tvChooseReason.setText(name);

            pop.dismiss();
        });
    }


    private void requestBackMoneyData() {

        Map<String, String> params = new HashMap<>();
        params.put("order_id", orderId);
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.refund_info, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                dismiss();
                XLog.e("退款  " + response.body());

                BackMoneyBean backMoneyBean = FastJsonUtil.fromJson(response.body(), BackMoneyBean.class);

                if (null == backMoneyBean) {
                    return;
                }

                if (!backMoneyBean.getCode().equals(Constant.REQUEST_SUCCESS) || backMoneyBean.getResult() == null) {
                    XToast.normal(backMoneyBean.getMessage());
                    return;
                }
                result = backMoneyBean.getResult();

                String mobile = result.getMobile();
                double money = result.getMoney();
                String money_info = result.getMoney_info();

                tvPhone.setText(mobile);
                tvMoneyTip.setText(money_info);
                tvShouldMoney.setText("￥" + money);

            }

            @Override
            public void failed(Response<String> response) {
                dismiss();

            }
        });
    }

    //上传数据
    private void commitPhoto() {

        chooseReason = tvChooseReason.getText().toString().trim();
        chooseRec = tvChooseRec.getText().toString().trim();
        applyReason = tvApplyReason.getText().toString().trim();

        if (TextUtils.isEmpty(chooseReason) || TextUtils.isEmpty(chooseRec) || TextUtils.isEmpty(applyReason)) {
            XToast.normal("请把信息选择完整");
            return;
        }


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

        loading(true, "发布中...");
        Map<String, String> params = new HashMap<>();
        params.put("type", "goods_return ");

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
                    return;
                }
                String imgUrl = uploadImageBean.getResult().getImg_url();

                uploadShopImage.add(imgUrl);

                if (uploadShopImage.size() >= selectShopImage.size()) {
                    commitInfo();
                }
                dismiss();
            }

            @Override
            public void failed(Response<String> response) {
                dismiss();

            }
        });
    }

    private void commitInfo() {

        String applyContent = etApplyTip.getText().toString().trim();
        String phone = tvPhone.getText().toString();
        if (TextUtils.isEmpty(chooseReason) || TextUtils.isEmpty(chooseRec) || TextUtils.isEmpty(applyReason)) {
            XToast.normal("请把信息选择完整");
            return;
        }

        loading(false);

        Map<String, String> params = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < uploadShopImage.size(); i++) {
            sb.append("\"" + uploadShopImage.get(i) + "\"" + ",");
            params.put("images[" + uploadShopImage.get(i) + "]", uploadShopImage.get(i));
        }
        if (sb.toString().endsWith(",")) {
            int i = sb.toString().lastIndexOf(",");
            sb.replace(i, i + 1, "");
        }

        sb.append("]");

        params.put("order_id", orderId);
        params.put("refund_type", chooseReason);
        params.put("goods_status", chooseRec);
        params.put("goods_reason", applyReason);
        params.put("refund_detail", applyContent);
        // params.put("images", sb.toString());
        params.put("mobile", phone);

        XLog.e("申请退货：" + sb.toString() + "--" + orderId + "--" + chooseReason + "--" + chooseRec + "--" + applyReason + " -- " + applyContent);

        BaseRequestUtils.postRequestWithHeader(this, UrlManager.refund_submit, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {

                dismiss();
                XLog.e(" 申请退货： " + response.body());

                NormalResultBean resultBean = FastJsonUtil.fromJson(response.body(), NormalResultBean.class);
                if (null == resultBean) {

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
                XLog.e(" 申请退货： " + response.getException().getMessage());
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
        Matisse.from(ApplyBackMoneyActivity.this)
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


              //  selectPhotoListener(list);

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
        imageAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            selectShopImage.remove(position);
            imageAdapter.notifyDataSetChanged();
        });

    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ApplyBackMoneyActivityPermissionsDispatcher.showCameraWithPermissionCheck(ApplyBackMoneyActivity.this);
    }


    @NeedsPermission(Manifest.permission.CAMERA)
    void showCamera() {
        SelectCamerAlerdialog mCamerAlerdialog = new SelectCamerAlerdialog(mContext, R.style.mydialog);
        mCamerAlerdialog.setListener(new SelectCamerAlerdialog.OnItemClickListener() {
            @Override
            public void openCamer() {
                ActivityUtil.openCammer(ApplyBackMoneyActivity.this, OPENCAMMER);
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
