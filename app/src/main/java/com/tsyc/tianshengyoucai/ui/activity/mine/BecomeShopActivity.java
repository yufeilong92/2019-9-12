package com.tsyc.tianshengyoucai.ui.activity.mine;

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
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.manager.SelectCityManager;
import com.tsyc.tianshengyoucai.model.adapter.ShowImageAdapter;
import com.tsyc.tianshengyoucai.model.bean.MainCategoryBean;
import com.tsyc.tianshengyoucai.model.bean.NormalResultBean;
import com.tsyc.tianshengyoucai.model.bean.UploadImageBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.activity.login.SingleWebActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.ReleaseShopActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.ActivityUtil;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.FileUtil;
import com.tsyc.tianshengyoucai.utils.LunBanUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.utils.Util;
import com.tsyc.tianshengyoucai.utils.matisse_picker.Glide4Engine;
import com.tsyc.tianshengyoucai.view.SelectCamerAlerdialog;
import com.youth.xframe.utils.XKeyboardUtils;
import com.youth.xframe.utils.XRegexUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import butterknife.BindView;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

/**
 * author：van
 * CreateTime：2019/8/1
 * File description：申请成为商家
 */
@RuntimePermissions
public class BecomeShopActivity extends BaseActivity implements AMapLocationListener {

    private static final int REQUEST_CODE_CHOOSE = 102;
    private static final int REQUEST_MAIN_CATEGORY = 103;
    @BindView(R.id.et_your_name)
    EditText etYourName;
    @BindView(R.id.et_your_phone)
    EditText etYourPhone;
    @BindView(R.id.et_your_id)
    EditText etYourId;
    @BindView(R.id.et_shop_name)
    EditText etShopName;
    @BindView(R.id.et_shop_address)
    EditText etShopAddress;
    @BindView(R.id.tv_main_category)
    TextView tvMainCategory;
    @BindView(R.id.tv_select_area)
    TextView tvSelectArea;
    @BindView(R.id.rv_shop_image)
    RecyclerView rvShopImage;
    @BindView(R.id.rv_shop_qualify)
    RecyclerView rvShopQualify;
    @BindView(R.id.et_your_desc)
    EditText etYourDesc;
    @BindView(R.id.cb_agreement)
    CheckBox cbAgreement;
    @BindView(R.id.btn_commit)
    Button btnCommit;

    private int selectType = -1;
    private List<String> selectShopImage = new ArrayList<>(); // 门头照
    private List<String> selectQualifyImage = new ArrayList<>(); // 执照

    private List<String> uploadShopImage = new ArrayList<>(); // 上传成功后的集合
    private List<String> uploadQualifyImage = new ArrayList<>(); // 执照
    private List<String> mainCategory = new ArrayList<>(); // 主营类目一级
    private List<List<String>> subMainCategory = new ArrayList<>(); // 主营类目二级

    /**
     * 打开相机回调
     */
    private static final int OPENCAMMER = 1055;
    private static final int FIVE_NMUBER = 5;
    private static final int NINE_NMUBER = 9;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case REQUEST_CODE_CHOOSE:
                    break;
                case REQUEST_MAIN_CATEGORY:
                    requestMainCategory();
                    break;

                default:
                    break;
            }
        }
    };
    private OptionsPickerView pvOptions;
    private int mainCateID;
    private SelectCityManager selectCityManager;
    private int provinceOpt;
    private int cityOpt;
    private int areaOpt;
    private String pName;
    private String cName;
    private String aName;

    private boolean isLocationSuccess = true;
    private double latitude = 0.0;
    private double longitude = 0.0;
    private ShowImageAdapter qualifyAdapter;
    private ShowImageAdapter imageAdapter;
    private List<MainCategoryBean.ResultBean> categoryBean;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_becom_shop;
    }

    @Override
    public void initView() {
        initLocation();
        mTvTitle.setText(getString(R.string.text_my_shop));

        rvShopImage.setHasFixedSize(true);
        rvShopQualify.setHasFixedSize(true);
        rvShopImage.setLayoutManager(new GridLayoutManager(mContext, 3));
        rvShopQualify.setLayoutManager(new GridLayoutManager(mContext, 3));

        qualifyAdapter = new ShowImageAdapter();
        rvShopQualify.setAdapter(qualifyAdapter);

        imageAdapter = new ShowImageAdapter();
        rvShopImage.setAdapter(imageAdapter);

        imageAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            selectShopImage.remove(position);
            imageAdapter.notifyDataSetChanged();
        });
        qualifyAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            selectQualifyImage.remove(position);
            qualifyAdapter.notifyDataSetChanged();
        });

    }


    private void initLocation() {
        AMapLocationClient mlocationClient = new AMapLocationClient(this);
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        mlocationClient.setLocationListener(this);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setInterval(2000);
        mlocationClient.setLocationOption(mLocationOption);
        mlocationClient.startLocation();

    }


    @Override
    public void initData() {
//        Bundle extra = getIntent().getBundleExtra(Constant.bundleExtra);
//        if (null == extra) {
//            return;
//        }
//
//        ShopApplyBean shopApplyBean = (ShopApplyBean) extra.getSerializable("shop_apply");
//        if (null == shopApplyBean || null == shopApplyBean.getResult()) {
//            return;
//        }
//
//        btnCommit.setClickable(false);
//        ShopApplyBean.ResultBean applyResult = shopApplyBean.getResult();
//        String shopArea = applyResult.getArea_info();
//        String shopDetailArea = applyResult.getStore_address(); //
//
//        String shoperName = applyResult.getName();
//        String shoperIdCard = applyResult.getIdcard();
//        String shopName = applyResult.getStore_name();
//        String shopCate = applyResult.getStore_zy();
//        String shopDesc = applyResult.getStore_description();
//
//        String shopMobile = applyResult.getMobile();
//        int isRecruit = applyResult.getIs_recruit(); // 是否同步到招聘 1 是 2 否
//
//        String door_photo = applyResult.getDoor_photo();
//        String business_license = applyResult.getBusiness_license();
//
//        etYourName.setText(shoperName);
//        etYourId.setText(shoperIdCard);
//        etYourPhone.setText(shopMobile);
//        etShopName.setText(shopName);
//
//        tvSelectArea.setText(shopArea);
//        etShopAddress.setText(shopDetailArea);
//        etYourDesc.setText(shopDesc);
//        tvMainCategory.setText(shopCate);
//
//        selectShopImage.add(door_photo);
//        ShowImageAdapter imageAdapter = new ShowImageAdapter();
//        rvShopImage.setAdapter(imageAdapter);
//        imageAdapter.setNewData(selectShopImage);
//        selectQualifyImage.add(business_license);
//        ShowImageAdapter qualifyAdapter = new ShowImageAdapter();
//        rvShopQualify.setAdapter(qualifyAdapter);
//        qualifyAdapter.setNewData(selectQualifyImage);
    }

    @OnClick({R.id.iv_image, R.id.iv_qualify, R.id.tv_agreement, R.id.tv_main_category, R.id.tv_select_area, R.id.btn_commit})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_image: // 店铺图片选择图片
                chooseShopImage();
                break;
            case R.id.iv_qualify: //营业执照选择图片
                chooseQualifyImage();
                break;
            case R.id.tv_agreement: // 查看协议
                Bundle bundle = new Bundle();
                bundle.putString("type", "become_shop");
                openActivity(SingleWebActivity.class, bundle);
                break;

            case R.id.tv_main_category: // 主营类目
                mHandler.sendEmptyMessage(REQUEST_MAIN_CATEGORY);
                break;

            case R.id.tv_select_area: // 选择省市区
                XKeyboardUtils.closeKeyboard(this);
                selectAddress();

                break;
            case R.id.btn_commit: // 提交
                if (Util.handleOnDoubleClick()) {
                    return;
                }
                commitPhoto();
                break;
        }
    }

    //选择地址
    private void selectAddress() {
        if (selectCityManager == null) {
            selectCityManager = SelectCityManager.getInstance(mContext, true);
            selectCityManager.setOnSelectOptionListener((options1, provinceName, options2, cityName, options3, areaName) -> {
                provinceOpt = options1;
                cityOpt = options2;
                areaOpt = options3;
                pName = provinceName;
                cName = cityName;
                aName = areaName;
                XLog.e("" + provinceOpt + " - " + cityOpt + " - " + areaOpt);
                tvSelectArea.setText(String.valueOf(provinceName + " " + cityName + " " + areaName));
            });
        }

        selectCityManager.showDialog(provinceOpt, cityOpt, areaOpt);
    }

    //选择营业执照图片
    private void chooseQualifyImage() {
        if (selectQualifyImage != null && selectQualifyImage.size() >= 5) {
            XToast.normal("图片最多只能上传5张");
            return;
        }
        selectType = 1;
        BecomeShopActivityPermissionsDispatcher.showCameraWithPermissionCheck(BecomeShopActivity.this);

    }

    //选择店铺图片
    private void chooseShopImage() {
        if (selectShopImage != null && selectShopImage.size() >= 9) {
            XToast.normal("图片最多只能上传5张");
            return;
        }
        BecomeShopActivityPermissionsDispatcher.showCameraWithPermissionCheck(BecomeShopActivity.this);
        selectType = 2;
    }

    private void selectPhoto(int imageCount) {
        Matisse.from(BecomeShopActivity.this)
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
        if (data == null) return;

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
        if (selectType == 2) {
            selectShopImage.addAll(pathList);
            imageAdapter.setNewData(selectShopImage);

        } else if (selectType == 1) {
            selectQualifyImage.addAll(pathList);
            qualifyAdapter.setNewData(selectQualifyImage);
        }
    }

    //主营类目
    private void requestMainCategory() {

        loading(true);
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.mainCategory, null, new BaseRequestUtils.getRequestCallBack() {

            @Override
            public void success(Response<String> response) {
                XLog.e("主营类目 ： " + response.body());
                MainCategoryBean mainCategoryBean = FastJsonUtil.fromJson(response.body(), MainCategoryBean.class);
                if (null == mainCategoryBean) {

                    dismiss();
                    return;
                }

                if (!mainCategoryBean.getCode().equals("200") || mainCategoryBean.getResult() == null) {
                    dismiss();
                    return;
                }
                mainCategory.clear();

                categoryBean = mainCategoryBean.getResult();
                for (int i = 0; i < categoryBean.size(); i++) {
                    mainCategory.add(categoryBean.get(i).getGc_name());
                    List<String> sub = new ArrayList<>();
                    List<MainCategoryBean.ResultBean.ChildBean> child = categoryBean.get(i).getChild();
                    for (int i1 = 0; i1 < child.size(); i1++) {
                        sub.add(child.get(i1).getGc_name());
                    }
                    subMainCategory.add(sub);
                }

                initCompanyPicker(mainCategory, subMainCategory, categoryBean);
                dismiss();

            }

            @Override
            public void failed(Response<String> response) {
                XToast.normal("主营类目获取失败");
                dismiss();
            }
        });

    }

    //上传数据
    private void commitPhoto() {
        String yourName = etYourName.getText().toString().trim();
        String yourPhone = etYourPhone.getText().toString().trim();
        String yourId = etYourId.getText().toString().trim();
        String shopName = etShopName.getText().toString().trim();
        String shopAddress = etShopAddress.getText().toString().trim();
        String yourDesc = etYourDesc.getText().toString().trim();
        String yourCategory = tvMainCategory.getText().toString().trim();

        if (!XRegexUtils.checkMobile(yourPhone)) {
            XToast.normal("请输入正确的手机号");
            return;
        }
        if (!XRegexUtils.checkIdCard(yourId)) {
            XToast.normal("请输入正确的身份证号");
            return;
        }

        if (TextUtils.isEmpty(yourName) || TextUtils.isEmpty(yourPhone) || TextUtils.isEmpty(yourId) ||
                TextUtils.isEmpty(shopName) || TextUtils.isEmpty(shopAddress) || TextUtils.isEmpty(yourDesc)
                || TextUtils.isEmpty(yourCategory)) {
            XToast.normal("请检查信息是否填写完整");
            return;
        }

        if (selectShopImage.size() == 0) {
            XToast.normal("请上传店面照片");
            return;
        }
        if (selectQualifyImage.size() == 0) {
            XToast.normal("请上传营业执照");
            return;
        }

        uploadShopImage.clear();
        uploadQualifyImage.clear();
        loading(false);
        for (int i = 0; i < selectShopImage.size(); i++) {
            File file = new File(selectShopImage.get(i));
            mHandler.postDelayed(() -> uploadImage(file, "image"), 300);
        }

        for (int i = 0; i < selectQualifyImage.size(); i++) {
            File file = new File(selectQualifyImage.get(i));
            mHandler.postDelayed(() -> uploadImage(file, "qualify"), 300);
        }

    }

    //上传图片
    private void uploadImage(File file, String type) {
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
                if (type.equals("image")) {
                    uploadShopImage.add(imgUrl);
                    if (uploadShopImage.size() == selectShopImage.size()) {
                        XToast.normal("门店图片上传完成");
                    }
                } else {
                    uploadQualifyImage.add(imgUrl);
                    if (uploadQualifyImage.size() == selectQualifyImage.size()) {
                        XToast.normal("营业执照上传完成");
                    }
                }

                if (uploadQualifyImage.size() == selectQualifyImage.size() && uploadShopImage.size() == selectShopImage.size()) {
                    commitShopInfo();
                }
            }

            @Override
            public void failed(Response<String> response) {
                dismiss();

            }
        });
    }

    //提交数据
    private void commitShopInfo() {
        String yourName = etYourName.getText().toString().trim();
        String yourPhone = etYourPhone.getText().toString().trim();
        String yourId = etYourId.getText().toString().trim();
        String shopName = etShopName.getText().toString().trim();
        String shopAddress = etShopAddress.getText().toString().trim();
        String yourDesc = etYourDesc.getText().toString().trim();
        String yourCategory = tvMainCategory.getText().toString().trim();
        Map<String, String> params = new HashMap<>();
        params.put("name", yourName);
        params.put("mobile", yourPhone);
        params.put("idcard", yourId);
        params.put("store_name", shopName);
        params.put("store_address", shopAddress);
        params.put("store_zy", yourCategory);
        params.put("store_description", yourDesc);

        params.put("lng", String.valueOf(longitude));
        params.put("lat", String.valueOf(latitude));
        params.put("province_id", String.valueOf(provinceOpt));
        params.put("city_id", String.valueOf(cityOpt));
        params.put("area_id", String.valueOf(areaOpt));
        params.put("province_name", pName);
        params.put("city_name", cName);
        params.put("area_name", aName);

        BaseRequestUtils.postRequestMultipleData(this, UrlManager.becomeShop, uploadShopImage,
                uploadQualifyImage, params, new BaseRequestUtils.getRequestCallBack() {

                    @Override
                    public void success(Response<String> response) {
                        XLog.e("申请开店" + response.body());

                        NormalResultBean resultBean = FastJsonUtil.fromJson(response.body(), NormalResultBean.class);
                        dismiss();
                        if (null == resultBean) {
                            XToast.normal(Constant.OPERATION_FAIL_MESSAGE);
                            return;
                        }

                        if (!resultBean.getCode().equals("200")) {
                            XToast.normal(String.valueOf(resultBean.getMessage()));
                            return;
                        }

                        XToast.normal(String.valueOf(resultBean.getMessage()));
                        mResultTo.finishBase(mContext);

                    }

                    @Override
                    public void failed(Response<String> response) {
                        XLog.e("申请开店失败");
                        dismiss();
                        Throwable exception = response.getException();
                        if (null != exception) {
                            exception.printStackTrace();
                            XLog.e("申请开店失败  " + exception.getMessage());
                        }
                    }
                });
    }

    private void initCompanyPicker(final List<String> listData, List<List<String>> subList, List<MainCategoryBean.ResultBean> result) {
        pvOptions = new OptionsPickerBuilder(this, (options1, options2, options3, v) -> {
            MainCategoryBean.ResultBean resultBean = categoryBean.get(options1);
            String gc_name = resultBean.getGc_name();
            if (resultBean.getChild().size() > 0) {
                MainCategoryBean.ResultBean.ChildBean childBean = categoryBean.get(options1).getChild().get(options2);
                String child_name = childBean.getGc_name();
                tvMainCategory.setText(child_name);
                mainCateID = childBean.getGc_id();
            }else {
                tvMainCategory.setText(gc_name);
                mainCateID = resultBean.getGc_id();
            }


        }).setLayoutRes(R.layout.layout_company_picker, v -> {
            final TextView tvSubmit = v.findViewById(R.id.tv_finish);
            TextView tvCancel = v.findViewById(R.id.tv_Cancel);

            tvSubmit.setOnClickListener(v1 -> {
                pvOptions.returnData();
                pvOptions.dismiss();
            });

            tvCancel.setOnClickListener(v12 -> pvOptions.dismiss());
        }).setContentTextSize(18)//滚轮文字大小
                .setLineSpacingMultiplier((float) 2.5) //设置item的高度
                .isDialog(false)
                .setOutSideCancelable(false)
                .build();

        pvOptions.setPicker(listData, subList);
        pvOptions.show();
    }


    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                if (isLocationSuccess) {
                    //获取纬度
                    latitude = amapLocation.getLatitude();
                    //获取经度
                    longitude = amapLocation.getLongitude();
                    amapLocation.getAccuracy();//获取精度信息
                    isLocationSuccess = false;
                }
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                XLog.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }


    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        BecomeShopActivityPermissionsDispatcher.showCameraWithPermissionCheck(BecomeShopActivity.this);
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    void showCamera() {
        SelectCamerAlerdialog mCamerAlerdialog = new SelectCamerAlerdialog(mContext, R.style.mydialog);
        mCamerAlerdialog.setListener(new SelectCamerAlerdialog.OnItemClickListener() {
            @Override
            public void openCamer() {
                ActivityUtil.openCammer(BecomeShopActivity.this, OPENCAMMER);
            }

            @Override
            public void openXinCe() {
                if (selectType == 1) {
                    selectPhoto(FIVE_NMUBER - selectQualifyImage.size());
                } else {
                    selectPhoto(NINE_NMUBER - selectShopImage.size());
                }
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
