package com.tsyc.tianshengyoucai.ui.activity.mine.shop;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.listener.event.UnifiedNotifyEvent;
import com.tsyc.tianshengyoucai.model.adapter.ShowImageAdapter;
import com.tsyc.tianshengyoucai.model.adapter.ShowSpecAdapter;
import com.tsyc.tianshengyoucai.model.bean.EditShopBean;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.model.bean.ShopCategoryBean;
import com.tsyc.tianshengyoucai.model.bean.ShopSpecBean;
import com.tsyc.tianshengyoucai.model.bean.UploadImageBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.ActivityUtil;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.FileUtil;
import com.tsyc.tianshengyoucai.utils.LunBanUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.utils.Util;
import com.tsyc.tianshengyoucai.utils.matisse_picker.Glide4Engine;
import com.tsyc.tianshengyoucai.view.SelectCamerAlerdialog;
import com.tsyc.tianshengyoucai.view.SimpleDividerItemDecortion;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import org.greenrobot.eventbus.EventBus;

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
 * CreateTime：2019/7/30
 * File description： 发布商品
 */
@RuntimePermissions
public class ReleaseShopActivity extends BaseActivity {

    private static final int REQUEST_CODE_CHOOSE = 1002;
    protected static final int INTENT_REQUEST_CODE = 1003;

    @BindView(R.id.et_shop_name)
    EditText etShopName;
    @BindView(R.id.et_shop_price)
    EditText etShopPrice;
    @BindView(R.id.et_shop_money)
    EditText etShopMoney;
    @BindView(R.id.et_inner_money)
    EditText etInnerMoney;
    @BindView(R.id.rv_up_image)
    RecyclerView rvUpImage;
    @BindView(R.id.tv_shop_spec_)
    TextView tvShopSpec;
    @BindView(R.id.tv_up_image) // 上传图片
            TextView tvUpImage;
    @BindView(R.id.tv_update_spec)
    TextView tvUpdateSpec;
    @BindView(R.id.tv_shop_size)
    TextView tvShopSize;
    @BindView(R.id.rv_shop_spec)
    RecyclerView rvShopSpec;
    @BindView(R.id.ctl_spec)
    ConstraintLayout ctlSpecs;
    @BindView(R.id.tv_shop_count)
    TextView tvShopCount;

    @BindView(R.id.et_shop_count)
    EditText etShopCount;
    @BindView(R.id.et_shop_desc)
    EditText etShopDesc;
    @BindView(R.id.cb_open)
    CheckBox cbOpen;
    @BindView(R.id.ctl_take)
    ConstraintLayout ctlTake;
    @BindView(R.id.ctl_category)
    ConstraintLayout ctlCategory;
    @BindView(R.id.tv_shop_cate)
    TextView tvShopCate;
    @BindView(R.id.iv_detail_img)
    ImageView ivDetailImg;
    @BindView(R.id.tv_detail_image)
    TextView tvDetailImage;
    @BindView(R.id.rv_detail_image)
    RecyclerView rvDetailImage;

    private ShowImageAdapter imageAdapter;
    private String specName;
    private int releaseType = 1; // 1 发布 2 编辑
    private int imageType = -1; //上传图片类型  1 商品图片 2. 商品描述
    private List<String> selectAllImage = new ArrayList<>();
    private List<String> selectDetailImage = new ArrayList<>();
    private List<String> uploadDetailImage = new ArrayList<>();
    private List<String> uploadImage = new ArrayList<>();
    private String specJson;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };
    private StringBuilder sbEnd;
    private ShowImageAdapter detailAdapter;
    private String goodsId;
    private OptionsPickerView pvOptions;
    private String isInner; // 1 内部人  2 不是内部人
    private List<ShopCategoryBean.ResultBean> result;
    private int gc_id;
    private ArrayList<ShopSpecBean.ShopSpecItemBean> list = new ArrayList<>(); // 编辑规格
    private SelectCamerAlerdialog mCamerAlerdialog;
    /**
     * 打开相机回调
     */
    private static final int OPENCAMMER = 1055;
    private static final int FIVE_NMUBER = 5;
    private static final int NINE_NMUBER = 9;


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_release_shop;
    }

    @Override
    public void initView() {
//        ctlCategory.setVisibility(View.GONE);
        Bundle extra = getIntent().getBundleExtra(Constant.bundleExtra);
        if (extra != null) {
            isInner = extra.getString("is_inner");
            goodsId = extra.getString("goods_id");
            XLog.e("内部人 " + isInner);
            if (goodsId != null) {
                new Handler().postDelayed(() -> editShopDetail(goodsId), 0);
            }
        }

        mTvTitle.setText(getString(R.string.text_release_shop));
    }

    @Override
    public void initData() {
        rvShopSpec.setHasFixedSize(true);
        rvShopSpec.addItemDecoration(new SimpleDividerItemDecortion(mContext));
        rvShopSpec.setLayoutManager(new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });


        rvDetailImage.setLayoutManager(new GridLayoutManager(mContext, 2));
        rvDetailImage.setHasFixedSize(true);

        detailAdapter = new ShowImageAdapter();
        rvDetailImage.setAdapter(detailAdapter);
        detailAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            selectDetailImage.remove(position);
            detailAdapter.notifyDataSetChanged();
        });

        rvUpImage.setLayoutManager(new GridLayoutManager(mContext, 2));
        rvUpImage.setHasFixedSize(true);

        imageAdapter = new ShowImageAdapter();
        rvUpImage.setAdapter(imageAdapter);
        imageAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            selectAllImage.remove(position);
            imageAdapter.notifyDataSetChanged();
        });
        tvUpdateSpec.setOnClickListener(this);

    }

    //编辑商品详细
    private void editShopDetail(String goodsId) {
        loading(true);
        Map<String, String> params = new HashMap<>();
        params.put("goods_id", goodsId);
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.goodsDetailEdit, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                dismiss();
                EditShopBean editShopBean = FastJsonUtil.fromJson(response.body(), EditShopBean.class);
                XLog.e("编辑资料  " + response.body());
                if (editShopBean == null) {
                    XToast.normal("商品信息获取失败，无法编辑");
                    return;
                }
                if (!editShopBean.getCode().equals(Constant.REQUEST_SUCCESS) || editShopBean.getResult() == null) {
                    XToast.normal(editShopBean.getMessage());
                    return;
                }
                releaseType = 2;
                EditShopBean.ResultBean result = editShopBean.getResult();
                selectAllImage = result.getGoods_images();
                selectDetailImage = result.getDetail_images();
                String name = result.getGoods_name();
                String price = result.getGoods_price();
                String inner_price = result.getInner_price();
                String commission = result.getCommission();
                int editStock = result.getGoods_stock();
                //设置图片

                imageAdapter.setNewData(selectAllImage);
                detailAdapter.setNewData(selectDetailImage);

                String detail = result.getDetail();

                int is_yourself = result.getIs_yourself();

                EditShopBean.ResultBean.SpecListBean spec_list = result.getSpec_list();
                if (spec_list != null) {
                    String spec_name = spec_list.getSpec_name();
                    List<EditShopBean.ResultBean.SpecListBean.SpecValueBean> spec_value = spec_list.getSpec_value();
                    int goods_stock = result.getGoods_stock(); // 商品库存
                    tvShopSize.setText(spec_name);

                    ctlSpecs.setVisibility(View.VISIBLE);
                    tvUpdateSpec.setVisibility(View.VISIBLE);
                    tvShopSpec.setVisibility(View.GONE);
                    if (spec_value != null) {

                        for (int i = 0; i < spec_value.size(); i++) {
                            EditShopBean.ResultBean.SpecListBean.SpecValueBean specValueBean = spec_value.get(i);
                            ShopSpecBean.ShopSpecItemBean shopSpecItemBean = new ShopSpecBean.ShopSpecItemBean();
                            shopSpecItemBean.setPrice(specValueBean.getPrice());
                            shopSpecItemBean.setSpec_value(specValueBean.getSpec_value());
                            shopSpecItemBean.setInner_price(specValueBean.getInner_price());
                            shopSpecItemBean.setStock_num(specValueBean.getStock_num() + "");
                            list.add(shopSpecItemBean);
                        }
                        ShowSpecAdapter showSpecAdapter = new ShowSpecAdapter();
                        showSpecAdapter.setNewData(list);
                        showSpecAdapter.setIsInner(isInner);
                        rvShopSpec.setVisibility(View.VISIBLE);
                        rvShopSpec.setAdapter(showSpecAdapter);
                    }
                    //  etShopCount.setText(String.valueOf(goods_stock));
                }

                tvShopCate.setHint("已选择，请改正");
                etShopName.setText(name);
                etShopPrice.setText(price);
                etInnerMoney.setText(inner_price);
                etShopMoney.setText(commission);
                etShopDesc.setText(detail);
                etShopCount.setText(String.valueOf(editStock));
                if (is_yourself == 1) {
                    cbOpen.setChecked(true);
                } else {
                    cbOpen.setChecked(false);
                }

            }

            @Override
            public void failed(Response<String> response) {
                dismiss();
                XToast.normal(getString(R.string.service_error));
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_update_spec) {
            Bundle bundle = new Bundle();
            bundle.putString("is_inner", isInner);
            bundle.putString("spec_name", tvShopSize.getText().toString());
            bundle.putParcelableArrayList("spec_list", list);
            openActivity(ShopSpecActivity.class, bundle, INTENT_REQUEST_CODE);
        }
    }

    @OnClick({R.id.btn_commit, R.id.tv_shop_spec_, R.id.ctl_category, R.id.tv_update_spec, R.id.iv_up_img, R.id.iv_detail_img})
    public void onViewClick(View view) {

        switch (view.getId()) {
            case R.id.btn_commit:
                String shopName = etShopName.getText().toString().trim();
                String shopPrice = etShopPrice.getText().toString().trim();
                String innerPrice = etInnerMoney.getText().toString().trim();
                String shopMoney = etShopMoney.getText().toString().trim();
                String shopCount = etShopCount.getText().toString().trim();
                String shopDesc = etShopDesc.getText().toString().trim();

                if (TextUtils.isEmpty(shopName) || TextUtils.isEmpty(shopPrice) || TextUtils.isEmpty(innerPrice) ||
                        TextUtils.isEmpty(shopCount) || TextUtils.isEmpty(shopDesc)) {

                    XToast.normal("请填写完整商品信息");
                    return;
                }
                loading(false);
                if (Util.handleOnDoubleClick()) {
                    return;
                }
                if (selectAllImage == null || selectAllImage.size() == 0) {
                    XToast.normal("请上传商品图片");
                    dismiss();
                    return;
                }

                if (selectDetailImage == null || selectDetailImage.size() == 0) {
                    XToast.normal("请上传详情图片");
                    dismiss();
                    return;
                }


                if (releaseType == 1) {
                    uploadImage.clear();
                    for (int i = 0; i < selectAllImage.size(); i++) {
                        File file = new File(selectAllImage.get(i));
                        mHandler.postDelayed(() -> uploadImage(file, 1), 300);
                    }
                } else {
                    releaseGoods();
                }

                break;

            case R.id.tv_shop_spec_:
                Bundle bundle = new Bundle();
                bundle.putString("is_inner", isInner);
                openActivity(ShopSpecActivity.class, bundle, INTENT_REQUEST_CODE);
                break;

            case R.id.iv_detail_img:
                if (selectDetailImage != null) {
                    if (selectDetailImage.size() >= 9) {
                        T.showToast(mContext, "最多选择9张图片");
                        return;
                    }
                }
                ReleaseShopActivityPermissionsDispatcher.showCameraWithPermissionCheck(ReleaseShopActivity.this);
                imageType = 2;
                break;
            case R.id.iv_up_img:
                imageType = 1;
                if (selectAllImage != null) {
                    if (selectAllImage.size() >= 5) {
                        T.showToast(mContext, "最多选择5张照片");
                        return;
                    }
                }
                ReleaseShopActivityPermissionsDispatcher.showCameraWithPermissionCheck(ReleaseShopActivity.this);
                break;
            case R.id.ctl_category:
                requestShopCategory();
                break;
        }
    }

    private List<String> gcList = new ArrayList<>();

    private List<List<String>> gcChildList = new ArrayList<>();

    //选择商品分类
    private void requestShopCategory() {
        loading(true);
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.shopCategory, null, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                dismiss();
                ShopCategoryBean shopCategoryBean = FastJsonUtil.fromJson(response.body(), ShopCategoryBean.class);
                if (null == shopCategoryBean) {
                    XToast.normal("商品分类获取失败");
                    return;
                }
                if (!shopCategoryBean.getCode().equals(Constant.REQUEST_SUCCESS) || shopCategoryBean.getResult() == null) {
                    XToast.normal(shopCategoryBean.getMessage());
                    return;
                }

                result = shopCategoryBean.getResult();
                if (result.size() == 0) {
                    XToast.normal("暂无商品分类数据");
                    return;
                }
                gcChildList.clear();
                gcList.clear();

                for (int i = 0; i < result.size(); i++) {
                    String gc_name = result.get(i).getGc_name();
                    gcList.add(gc_name);
                    List<String> gcChild = new ArrayList<>();
                    if (result.get(i).getChild() != null && result.get(i).getChild().size() > 0) {
                        for (int j = 0; j < result.get(i).getChild().size(); j++) {
                            String gc_child_name = result.get(i).getChild().get(j).getGc_name();
                            gcChild.add(gc_child_name);
                        }
                        gcChildList.add(gcChild);
                    }
                }

                initCompanyPicker(gcList, gcChildList);
            }

            @Override
            public void failed(Response<String> response) {
                dismiss();
                XToast.normal(getString(R.string.service_error));
            }
        });
    }

    private void initCompanyPicker(final List<String> listData, List<List<String>> childList) {
        pvOptions = new OptionsPickerBuilder(this, (options1, options2, options3, v) -> {
            String pStr = listData.get(options1);
            String cStr = childList.get(options1).get(options2);
            if (result != null && result.get(options1) != null && result.get(options1).getChild().get(options2) != null) {

                gc_id = result.get(options1).getChild().get(options2).getGc_id();
                XLog.e(gc_id + "---------------");
            }

            XLog.e("商品分类  " + pStr + " - " + cStr);
            tvShopCate.setText(pStr + " " + cStr);
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

        pvOptions.setPicker(listData, childList, null);
        pvOptions.show();
    }

    //上传 图片
    private void uploadImage(File file, int imageType) {

        Map<String, String> params = new HashMap<>();
        params.put("type", "goods_issue");
        BaseRequestUtils.postRequestWithPhoto(this, UrlManager.uploadImage, file, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("上传图片" + response.body());
                UploadImageBean uploadImageBean = FastJsonUtil.fromJson(response.body(), UploadImageBean.class);
                if (null == uploadImageBean) {
                    XToast.normal("图片上传失败");
                    dismiss();
                    return;
                }
                if (!uploadImageBean.getCode().equals("200") || uploadImageBean.getResult() == null) {
                    XToast.normal(uploadImageBean.getMessage());
                    dismiss();
                    return;
                }
                String imgUrl = uploadImageBean.getResult().getImg_url();
                if (imageType == 1) {
                    uploadImage.add(imgUrl);
                    if (selectAllImage.size() == uploadImage.size() || selectAllImage.size() < uploadImage.size()) {
                        for (int i = 0; i < selectDetailImage.size(); i++) {
                            File file = new File(selectDetailImage.get(i));
                            mHandler.postDelayed(() -> uploadImage(file, 2), 300);
                        }
                    }
                } else {
                    uploadDetailImage.add(imgUrl);
                    if (selectDetailImage.size() == uploadDetailImage.size()) {
                        releaseGoods();
                    }
                }
            }

            @Override
            public void failed(Response<String> response) {
                dismiss();
                XToast.normal(getString(R.string.service_error));
            }
        });

    }

    //发布商品
    private void releaseGoods() {
        // loading(true);

        String shopName = etShopName.getText().toString().trim();
        String shopPrice = etShopPrice.getText().toString().trim();
        String innerPrice = etInnerMoney.getText().toString().trim();
        String shopMoney = etShopMoney.getText().toString().trim();
        String shopCount = etShopCount.getText().toString().trim();
        String shopDesc = etShopDesc.getText().toString().trim();
        boolean checked = cbOpen.isChecked();

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (releaseType == 1) {
            for (int i = 0; i < uploadImage.size(); i++) {
                sb.append("\"" + uploadImage.get(i) + "\"" + ",");
            }
        } else {
            for (int i = 0; i < selectAllImage.size(); i++) {
                sb.append("\"" + selectAllImage.get(i) + "\"" + ",");
            }
        }
        if (sb.toString().endsWith(",")) {
            int i = sb.toString().lastIndexOf(",");
            sb.replace(i, i + 1, "");
        }
        sb.append("]");

        StringBuilder sbDetailImage = new StringBuilder();
        sbDetailImage.append("[");

        if (releaseType == 1) {
            for (int i = 0; i < uploadDetailImage.size(); i++) {
                sbDetailImage.append("\"" + uploadDetailImage.get(i) + "\"" + ",");
            }
        } else {
            if (selectDetailImage != null && selectDetailImage.size() > 0) {
                for (int i = 0; i < selectDetailImage.size(); i++) {
                    sbDetailImage.append("\"" + selectDetailImage.get(i) + "\"" + ",");
                }
            }
        }
        if (sbDetailImage.toString().endsWith(",")) {
            int i = sbDetailImage.toString().lastIndexOf(",");
            sbDetailImage.replace(i, i + 1, "");
        }
        sbDetailImage.append("]");

        Map<String, String> params = new HashMap<>();
        params.put("images", sb.toString());
        params.put("detail_images", sbDetailImage.toString()); // 商品详情图片
        params.put("goods_name", shopName);
        params.put("goods_price", shopPrice);
        params.put("commission", shopMoney);
        params.put("detail", shopDesc);
        params.put("type", String.valueOf(releaseType)); // 是否是编辑 1 新增 2 编辑
        params.put("gc_id", String.valueOf(gc_id)); //商品分类id
        params.put("goods_id", goodsId); // 商品id（修改时填写）
        params.put("goods_stock", shopCount);
        params.put("goods_storage", shopCount);
        params.put("inner_price", innerPrice);
        params.put("spec_list", specJson);
        if (checked) {
            params.put("is_yourself", "1");
        } else {
            params.put("is_yourself", "0");
        }
        //showProgress();
        XLog.e("上船书局" + shopName + " == " + sbDetailImage.toString() + " =========" + shopPrice + " ==== " + shopMoney + "=====" + shopDesc + "======" + releaseType
                + " ====== " + shopCount + "====" + innerPrice + "\n " + sb.toString() + " ====== " + specJson);

        BaseRequestUtils.postRequestWithHeader(this, UrlManager.addGoods, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("发布商品 " + response.body());
                NormalBean normalBean = FastJsonUtil.fromJson(response.body(), NormalBean.class);
                dismiss();
//                dismissProgress();
                if (null == normalBean) {
                    XToast.normal("发布失败");
                    return;
                }
                if (!normalBean.getCode().equals("200")) {
                    XToast.normal(normalBean.getMessage());
                    return;
                }

                if (releaseType == 1) {
                    XToast.normal("发布成功");

                } else {
                    XToast.normal("编辑成功");
                    EventBus.getDefault().post(new UnifiedNotifyEvent(Constant.EDIT_SHOP_RELEASE));
                }
                selectAllImage.clear();
                selectDetailImage.clear();
                finish();

            }

            @Override
            public void failed(Response<String> response) {
                dismiss();
                Throwable exception = response.getException();
                exception.printStackTrace();
                String message = exception.getMessage();
                XLog.e("" + message);
//                dismissProgress();
                XToast.normal(getString(R.string.service_error));
            }
        });
    }

    //开启相册选择图片
    private void selectPhoto(int imageCount) {
        Matisse.from(ReleaseShopActivity.this)
                .choose(MimeType.ofImage(), true)
                .showSingleMediaType(true)
                .countable(false)
                .capture(false)
//                .captureStrategy(
//                        new  (true,
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

    private void selectPhotoListener(List<String> pathList) {

        if (imageType == 1) {
            selectAllImage.addAll(pathList);
            //上传头像
            if (selectAllImage != null && !selectAllImage.isEmpty()) {
                imageAdapter.setNewData(selectAllImage);
            }
        } else {
            selectDetailImage.addAll(pathList);
            //上传头像
            if (selectDetailImage != null && !selectDetailImage.isEmpty()) {
                detailAdapter.setNewData(selectDetailImage);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<String> strings = Matisse.obtainPathResult(data);
            selectPhotoListener(strings);
            return;
        }

        if (resultCode == INTENT_REQUEST_CODE) {
            specJson = data.getStringExtra("specJson");
            specName = data.getStringExtra("specName");
            ArrayList<ShopSpecBean.ShopSpecItemBean> gsons = data.getParcelableArrayListExtra("spec_value");
            ShowSpecAdapter showSpecAdapter = new ShowSpecAdapter();
            showSpecAdapter.setNewData(gsons);
            showSpecAdapter.setIsInner(isInner);
            ctlSpecs.setVisibility(View.VISIBLE);
            rvShopSpec.setVisibility(View.VISIBLE);
            rvShopSpec.setAdapter(showSpecAdapter);
            String value = "";
            int stockNum = 0;
            for (int i = 0; i < gsons.size(); i++) {
                ShopSpecBean.ShopSpecItemBean item = gsons.get(i);
                if (item == null) {
                    continue;
                }
                String price = item.getPrice();

                value += (price + " 元 ");
                stockNum += Integer.valueOf(item.getStock_num());
            }

            XLog.e("库存 " + stockNum);
            tvShopSize.setText(specName);
            if (stockNum == 0) {
                etShopCount.setClickable(true);
                etShopCount.setFocusable(true);
            } else {
                etShopCount.setText(String.valueOf(stockNum));
                etShopCount.setClickable(false);
                etShopCount.setFocusable(false);
            }
            ctlSpecs.setVisibility(View.VISIBLE);
            tvShopSpec.setVisibility(View.GONE);
            tvUpdateSpec.setVisibility(View.VISIBLE);
            return;
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

    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ReleaseShopActivityPermissionsDispatcher.showCameraWithPermissionCheck(ReleaseShopActivity.this);
    }


    @NeedsPermission(Manifest.permission.CAMERA)
    void showCamera() {
        mCamerAlerdialog = new SelectCamerAlerdialog(mContext, R.style.mydialog);
        mCamerAlerdialog.setListener(new SelectCamerAlerdialog.OnItemClickListener() {
            @Override
            public void openCamer() {
                ActivityUtil.openCammer(ReleaseShopActivity.this, OPENCAMMER);
            }

            @Override
            public void openXinCe() {
                if (imageType == 1) {
                    selectPhoto(FIVE_NMUBER - selectAllImage.size());
                } else {
                    selectPhoto(NINE_NMUBER - selectDetailImage.size());
                }
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
