package com.tsyc.tianshengyoucai.ui.activity.mine.shop;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.listener.event.NormalEvent;
import com.tsyc.tianshengyoucai.listener.event.UnifiedNotifyEvent;
import com.tsyc.tianshengyoucai.model.bean.MainCategoryBean;
import com.tsyc.tianshengyoucai.model.bean.NormalResultBean;
import com.tsyc.tianshengyoucai.model.bean.ShopApplyBean;
import com.tsyc.tianshengyoucai.model.bean.UploadImageBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.activity.mine.BecomeShopActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.ImageLoader;
import com.tsyc.tianshengyoucai.utils.ImgUtils;
import com.tsyc.tianshengyoucai.utils.matisse_picker.GifSizeFilter;
import com.tsyc.tianshengyoucai.utils.matisse_picker.Glide4Engine;
import com.tsyc.tianshengyoucai.view.StarBar;
import com.tsyc.tianshengyoucai.view.pop.ShopSetPhonePop;
import com.tsyc.tianshengyoucai.view.pop.ShopSetPop;
import com.tsyc.tianshengyoucai.view.pop.ShopSetWTPop;
import com.youth.xframe.utils.XRegexUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
import razerdp.basepopup.BasePopupWindow;

/**
 * author：van
 * CreateTime：2019/7/31
 * File description： 店铺设置
 */
@RuntimePermissions
public class ShopSetActivity extends BaseActivity {

    private static final int REQUEST_SHOP_DATA = 101;
    private static final int REQUEST_CODE_CHOOSE = 102;
    @BindView(R.id.iv_shop_image)
    ImageView ivShopImage;
    @BindView(R.id.tv_set_name)
    TextView tvSetName;
    @BindView(R.id.tv_set_desc)
    TextView tvSetDesc;
    @BindView(R.id.tv_set_main)
    TextView tvSetMain;
    @BindView(R.id.tv_set_address)
    TextView tvSetAddress;
    @BindView(R.id.tv_set_detail_address)
    TextView tvSetDetailAddress;
    @BindView(R.id.tv_set_phone)
    TextView tvSetPhone;
    @BindView(R.id.sb_shop_level)
    StarBar sbShopLevel;
    @BindView(R.id.tv_set_power)
    TextView tvSetPower;
    @BindView(R.id.tv_set_work)
    TextView tvSetWork;
    @BindView(R.id.cb_set_sync)
    CheckBox cbSetSync;

    private ShopSetPop shopSetPop;
    private OptionsPickerView pvOptions;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case REQUEST_SHOP_DATA:
                    requestShopData();
                    break;
            }
        }
    };
    private TextView popTitle;
    private Button popCommit;
    private EditText popContent;
    private ShopSetPhonePop phonePop;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_shop_set;
    }

    @Override
    public void initView() {
        registerEventBus(this);
        mHandler.sendEmptyMessage(REQUEST_SHOP_DATA);
        mTvTitle.setText(getString(R.string.text_shop_info));
        shopSetPop = new ShopSetPop(this);
        shopSetPop.findViewById(R.id.rl_back).setOnClickListener(v -> shopSetPop.dismiss());
        popTitle = shopSetPop.findViewById(R.id.tv_title);
        popCommit = shopSetPop.findViewById(R.id.btn_commit);

    }


    @OnClick({R.id.iv_shop_image, R.id.tv_set_name, R.id.tv_set_desc, R.id.tv_set_main,
            R.id.tv_set_detail_address, R.id.tv_set_phone, R.id.tv_set_power, R.id.tv_set_work})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_shop_image:
                ShopSetActivityPermissionsDispatcher.showCameraWithPermissionCheck(this);
                break;
            case R.id.tv_set_name:
                shopSetPop.showPopupWindow();
                popTitle.setText("设置店铺名称");
                popContent = shopSetPop.findViewById(R.id.et_content);
                popContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
                popContent.setText("");
                shopSetPop.findViewById(R.id.rl_back).setOnClickListener(v -> shopSetPop.dismiss());
                popCommit.setOnClickListener(v -> {
                    String store_name = popContent.getText().toString().trim();
                    if (TextUtils.isEmpty(store_name)) {
                        XToast.normal("请输入要修改的内容");
                        return;
                    }
                    updateStoreInfo("store_name", store_name);
                });
                break;
            case R.id.tv_set_desc:// 简介
                shopSetPop.showPopupWindow();
                popContent = shopSetPop.findViewById(R.id.et_content);
                popContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(40)});
                popContent.setText("");
                shopSetPop.findViewById(R.id.rl_back).setOnClickListener(v -> shopSetPop.dismiss());
                popTitle.setText("设置店铺介绍");
                popCommit.setOnClickListener(v -> {
                    String desc = popContent.getText().toString().trim();
                    if (TextUtils.isEmpty(desc)) {
                        XToast.normal("请输入要修改的内容");
                        return;
                    }
                    updateStoreInfo("store_description", desc);
                });
                break;
            case R.id.tv_set_main:
                XToast.normal("主营类目不可修改");
                break;
            case R.id.tv_set_detail_address: // 设置地址
                openActivity(SetShopAddressActivity.class);
                break;
            case R.id.tv_set_phone:
                setPhonePop();
                break;
            case R.id.tv_set_power: //资质

                break;
            case R.id.tv_set_work: // 工作时间
                openActivity(ShopSetWTActivity.class);
                break;
        }
    }


    private void setPhonePop() {
        phonePop = new ShopSetPhonePop(mContext);
        phonePop.showPopupWindow();

        EditText phoneText = phonePop.findViewById(R.id.et_content);
        phoneText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});

        phoneText.setInputType(InputType.TYPE_CLASS_NUMBER);
        TextView tvTitle = phonePop.findViewById(R.id.tv_title);
        Button btnCommit = phonePop.findViewById(R.id.btn_commit);
        tvTitle.setText("设置手机号");
        phonePop.findViewById(R.id.rl_back).setOnClickListener(v -> phonePop.dismiss());

        btnCommit.setOnClickListener(v -> {
            String phoneContent = phoneText.getText().toString().trim();
            if (!XRegexUtils.checkMobile(phoneContent)) {
                XToast.normal("请输入正确的手机号");
                return;
            }
            updateStoreInfo("mobile", phoneContent);
        });
    }

    /**
     * 修改商铺信息
     *
     * @param type    修改的类型
     * @param content 内容
     */
    private void updateStoreInfo(String type, String content) {
        loading(true);
        Map<String, String> params = new HashMap<>();
        params.put(type, content);
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.storeEdit, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("修改店铺信息" + response.body());
                dismiss();
                NormalResultBean resultBean = FastJsonUtil.fromJson(response.body(), NormalResultBean.class);

                if (null == resultBean) {
                    XToast.normal("信息修改失败");
                    return;
                }
                if (!resultBean.getCode().equals(Constant.REQUEST_SUCCESS)) {
                    XToast.normal(String.valueOf(resultBean.getMessage()));
                    return;
                }
                XToast.normal(String.valueOf(resultBean.getMessage()));

                if (type.equals("store_description")) {
                    tvSetDesc.setText(content);
                    if (shopSetPop != null)
                        shopSetPop.dismiss();
                } else if (type.equals("store_name")) {
                    tvSetName.setText(content);
                    if (shopSetPop != null)
                        shopSetPop.dismiss();
                } else if (type.equals("mobile")) {
                    tvSetPhone.setText(content);
                    if (phonePop != null)
                        phonePop.dismiss();
                }
            }

            @Override
            public void failed(Response<String> response) {
                dismiss();
                XLog.e("修改店铺信息" + response.getException().getMessage());
                if (shopSetPop != null)
                    shopSetPop.dismiss();
            }
        });
    }


    private void requestShopData() {

        BaseRequestUtils.postRequestWithHeader(this, UrlManager.shopInfo, null, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                ShopApplyBean shopApplyBean = FastJsonUtil.fromJson(response.body(), ShopApplyBean.class);
                if (null == shopApplyBean) {
                    XToast.normal("店铺状态查询失败，请稍候再试");
                    return;
                }

                if (shopApplyBean.getCode().equals("100")) {
                    if (shopApplyBean.getMessage().contains("您还没有申请店铺")) {
                        openActivity(BecomeShopActivity.class);
                    } else {
                        XToast.normal(String.valueOf(shopApplyBean.getMessage()));
                        return;
                    }
                }
                if (shopApplyBean.getResult() == null) {
                    return;
                }

                ShopApplyBean.ResultBean result = shopApplyBean.getResult();
                String name = result.getName();
                String store_name = result.getStore_name();
                String idCard = result.getIdcard();
                String area_info = result.getArea_info();
                String store_zy = result.getStore_zy();
                String store_description = result.getStore_description();
                String door_photo = result.getDoor_photo();
                String business_license = result.getBusiness_license();
                String store_address = result.getStore_address();
                int store_state = result.getStore_state(); //:0关闭，1开启，2审核中
                String business_hours = result.getBusiness_hours();
                String mobile = result.getMobile();
                int is_recruit = result.getIs_recruit(); //是否同步招聘 1 是  2 不是
                int store_credit = result.getStore_credit();

                sbShopLevel.setStarMark(store_credit);
                ImageLoader.setImageCircle(mContext, door_photo, ivShopImage);
                tvSetName.setText(store_name);
                tvSetDesc.setText(store_description);
                tvSetMain.setText(store_zy);
                tvSetAddress.setText(area_info);
                tvSetDetailAddress.setText(area_info + store_address);
                // sbShopLevel.setStarCount();
                tvSetPhone.setText(mobile);
                tvSetWork.setText(business_hours);

                if (is_recruit == 1) {
                    cbSetSync.setChecked(true);
                } else {
                    cbSetSync.setChecked(false);
                }

                if (!TextUtils.isEmpty(business_license)) {
                    tvSetPower.setText("已上传");
                    tvSetPower.setClickable(false);
                }
            }

            @Override
            public void failed(Response<String> response) {

            }
        });
    }

    private void initCompanyPicker(final List<String> listData, List<
            MainCategoryBean.ResultBean> result) {
        pvOptions = new OptionsPickerBuilder(this, (options1, options2, options3, v) -> {
            tvSetMain.setText(listData.get(options1));
            //mainCateID = result.get(options1).getGc_id();

        }).setLayoutRes(R.layout.layout_company_picker, v -> {
            final TextView tvSubmit = v.findViewById(R.id.tv_finish);
            TextView tvCancel = v.findViewById(R.id.tv_Cancel);

            tvSubmit.setOnClickListener(v1 -> {
                pvOptions.returnData();
                pvOptions.dismiss();
                updateStoreInfo("store_zy", tvSetMain.getText().toString());
            });

            tvCancel.setOnClickListener(v12 -> pvOptions.dismiss());
        }).setContentTextSize(18)//滚轮文字大小
                .setLineSpacingMultiplier((float) 2.5) //设置item的高度
                .isDialog(false)
                .setOutSideCancelable(false)
                .build();

        pvOptions.setPicker(listData);
        pvOptions.show();
    }

    private void selectPhotoListener(List<String> pathList) {

        if (pathList != null && pathList.size() > 0) {
            File file = new File(pathList.get(0));
            loading(false);
            Map<String, String> params = new HashMap<>();
            params.put("type", "foor");

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
                    ImageLoader.setImageCircle(mContext, imgUrl, ivShopImage);
                    if (!TextUtils.isEmpty(imgUrl)) {
                        updateStoreInfo("door_photo", imgUrl);
                    }

                    dismiss();
                }

                @Override
                public void failed(Response<String> response) {
                    dismiss();
                    XToast.normal(getString(R.string.service_error));
                }
            });
        }
    }


    private void selectPhoto() {
        Matisse.from(this)
                .choose(MimeType.ofImage(), false)
                .countable(true)
                .capture(true)
                .captureStrategy(
                        new CaptureStrategy(true,
                                "com.tsyc.tianshengyoucai.fileprovider",
                                "tsyc"))
                .maxSelectable(1)
                .addFilter(new GifSizeFilter(320, 320,
                        5 * Filter.K * Filter.K))
                .gridExpectedSize(
                        getResources().getDimensionPixelSize(R.dimen.dp_120))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(new Glide4Engine())    // for glide-V4
                .setOnSelectedListener((uriList, pathList) -> {

                })
                .originalEnable(true)
                .maxOriginalSize(5)
                .autoHideToolbarOnSingleTap(true)
                /*.setOnCheckedListener(isChecked -> {
                   //是否勾选原图

                  })*/
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
    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //ShopDetailActivityPermissionsDispatcher.onSkipIntentWithPermissionCheck(ShopDetailActivity.this);
        ShopSetActivityPermissionsDispatcher.showCameraWithPermissionCheck(ShopSetActivity.this);
    }


    @NeedsPermission(Manifest.permission.CAMERA)
    void showCamera() {
        selectPhoto();
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateAddress(NormalEvent event) {

        int code = event.getCode();
        String msg = event.getMsg();
        if (code == Constant.UPDATE_ADDRESS) {
            tvSetDetailAddress.setText(msg);
        } else if (code == Constant.UPDATE_TIME) {
            tvSetWork.setText(msg);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterEventBus(this);
    }
}
