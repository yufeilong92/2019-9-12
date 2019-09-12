package com.tsyc.tianshengyoucai.ui.activity.mine.shop;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.listener.event.UnifiedNotifyEvent;
import com.tsyc.tianshengyoucai.manager.SelectCityManager;
import com.tsyc.tianshengyoucai.model.adapter.GoodsAttrAdapter;
import com.tsyc.tianshengyoucai.model.adapter.GoodsTemplateAdapter;
import com.tsyc.tianshengyoucai.model.adapter.RelShowDragImageAdapter;
import com.tsyc.tianshengyoucai.model.adapter.ShopSpecAdapter;
import com.tsyc.tianshengyoucai.model.adapter.ShowImageAdapter;
import com.tsyc.tianshengyoucai.model.bean.AgreementBean;
import com.tsyc.tianshengyoucai.model.bean.EditShopBean;
import com.tsyc.tianshengyoucai.model.bean.GoodsTemplateBean;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.model.bean.ShopAttrBean;
import com.tsyc.tianshengyoucai.model.bean.ShopCategoryBean;
import com.tsyc.tianshengyoucai.model.bean.ShopSpecBean;
import com.tsyc.tianshengyoucai.model.bean.UploadImageBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.ActivityUtil;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.FileUtil;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.LunBanUtil;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.utils.TextUtil;
import com.tsyc.tianshengyoucai.utils.Util;
import com.tsyc.tianshengyoucai.utils.matisse_picker.Glide4Engine;
import com.tsyc.tianshengyoucai.view.SelectCamerAlerdialog;
import com.tsyc.tianshengyoucai.view.SimpleDividerItemDecortion;
import com.tsyc.tianshengyoucai.view.circleprogress.CircleTextProgressbar;
import com.tsyc.tianshengyoucai.view.circleprogress.CircleTextProgressbar.OnCountdownProgressListener;
import com.tsyc.tianshengyoucai.view.pop.ReleaseNoticePop;
import com.tsyc.tianshengyoucai.view.pop.SharePop;
import com.tsyc.tianshengyoucai.vo.UserInfomVo;
import com.tsyc.tianshengyoucai.wxapi.WxShareUtils;
import com.youth.xframe.utils.XKeyboardUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zzhoujay.richtext.RichText;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
public class ReleaseShopNewActivity extends BaseActivity {

    private static final int REQUEST_CODE_CHOOSE = 1002;
    protected static final int INTENT_REQUEST_CODE = 1003;

    @BindView(R.id.et_shop_name)
    EditText etShopName;
    @BindView(R.id.et_shop_price)
    TextView etShopArea;
    @BindView(R.id.et_shop_money)
    EditText etShopMoney;
    @BindView(R.id.rv_up_image)
    RecyclerView rvUpImage;
    @BindView(R.id.tv_up_image) // 上传图片
            TextView tvUpImage;
    @BindView(R.id.tv_update_spec)
    TextView tvUpdateSpec;
    @BindView(R.id.tv_shop_size)
    EditText tvShopSize;
    @BindView(R.id.et_post_money)
    EditText etPostMoney;
    @BindView(R.id.rv_shop_spec)
    RecyclerView rvShopSpec;
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
    @BindView(R.id.tv_add_spec)
    TextView tv_add_spec;
    @BindView(R.id.rv_detail_image)
    RecyclerView rvDetailImage;
    @BindView(R.id.rv_template)
    RecyclerView rvTemplate;
    @BindView(R.id.rv_attr)
    RecyclerView rvAttr;

    @BindView(R.id.tv_add_attr)
    TextView tvAddAttr;

    @BindView(R.id.tv_inner_money)
    TextView tvInnerMoneyText;

    private RelShowDragImageAdapter imageAdapter;
    private int releaseType = 1; // 1 发布 2 编辑
    private int imageType = -1; //上传图片类型  1 商品图片 2. 商品描述
    private List<String> selectAllImage = new ArrayList<>();
    private List<String> selectDetailImage = new ArrayList<>();
    private List<String> uploadDetailImage = new ArrayList<>();
    private List<String> uploadImage = new ArrayList<>();
    private List<String> editAllImage = new ArrayList<>();
    private List<String> editDetailImage = new ArrayList<>();

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };
    private StringBuilder sbEnd;
    private RelShowDragImageAdapter detailAdapter;
    private String goodsId;
    private OptionsPickerView pvOptions;
    private String isInner = "2"; // 1 内部人  2 不是内部人
    private List<ShopCategoryBean.ResultBean> result;
    private int gc_id;
    private ArrayList<ShopSpecBean.ShopSpecItemBean> list = new ArrayList<>(); // 编辑规格
    private Map<String, String> temMap = new HashMap<>();
    private List<String> gcList = new ArrayList<>();
    private List<List<String>> gcChildList = new ArrayList<>();
    private List<List<List<String>>> gcChildList3 = new ArrayList<>();

    public ArrayList<ShopSpecBean.ShopSpecItemBean> shopSpecBeans = new ArrayList<>();
    public ArrayList<ShopAttrBean> shopAttrBeans = new ArrayList<>();
    private ShopSpecAdapter shopSpecAdapter;
    private String templateValue;
    private SelectCityManager mSelectCityManager;
    private int provinceOpt;
    private int cityOpt;
    private int areaOpt;

    /**
     * 打开相机回调
     */
    private static final int OPENCAMMER = 1055;
    private static final int FIVE_NMUBER = 5;
    private static final int NINE_NMUBER = 9;
    private List<GoodsTemplateBean.ResultBean.TempleteBean> template;
    private GoodsAttrAdapter attrAdapter;
    private String attrJson;
    private GoodsTemplateAdapter templateAdapter;
    private ArrayList<ShopSpecBean.ShopSpecItemBean> gsons;

    private int hasTemplate = 0; //是否有模板 0 没有 1 有
    private String postMoney;
    private ReleaseNoticePop noticePop;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_release_shop_new;
    }

    @Override
    public void initView() {

        Bundle extra = getIntent().getBundleExtra(Constant.bundleExtra);
        if (extra != null) {
            isInner = extra.getString("is_inner");
            goodsId = extra.getString("goods_id");
            XLog.e("内部人 " + isInner);
            if (goodsId != null) {
                new Handler().postDelayed(() -> editShopDetail(goodsId), 0);
            }
        }
        if (goodsId == null || TextUtils.isEmpty(goodsId)) {
            //添加 默认1 条属性
            for (int i = 0; i < 1; i++) {
                addSpec();
                addAttr();
            }
            mTvTitle.setText(getString(R.string.text_release_shop));
        } else {
            mTvTitle.setText("编辑商品");
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void initData() {
        showReleaseNotice(1);
        rvShopSpec.setHasFixedSize(true);
        rvShopSpec.setLayoutManager(new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

        initSelectDetailAdapter();

        initSelectAllAdapter();

        tvUpdateSpec.setOnClickListener(this);

        rvTemplate.setHasFixedSize(true);
        rvTemplate.addItemDecoration(new SimpleDividerItemDecortion(mContext));
        rvTemplate.setLayoutManager(new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        templateAdapter = new GoodsTemplateAdapter();
        rvTemplate.setAdapter(templateAdapter);

        rvAttr.setHasFixedSize(true);
        rvAttr.setLayoutManager(new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

        etShopDesc.setOnTouchListener((v, event) -> {
            if (v.getId() == R.id.et_shop_desc && StringUtil.canVerticalScroll(etShopDesc)) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    v.getParent().requestDisallowInterceptTouchEvent(false);
                }
            }
            return false;
        });
    }

    //选择商品详情图片
    private void initSelectDetailAdapter() {
        rvDetailImage.setLayoutManager(new GridLayoutManager(mContext, 3));
        rvDetailImage.setHasFixedSize(true);

        detailAdapter = new RelShowDragImageAdapter(selectDetailImage);

        ItemDragAndSwipeCallback mItemDragAndSwipeCallback = new ItemDragAndSwipeCallback(detailAdapter);
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(mItemDragAndSwipeCallback);
        mItemTouchHelper.attachToRecyclerView(rvDetailImage);
        mItemDragAndSwipeCallback.setSwipeMoveFlags(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        detailAdapter.enableDragItem(mItemTouchHelper);

        OnItemDragListener listener = new OnItemDragListener() {

            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {
                final int oldIndex = source.getAdapterPosition();
                final int newIndex = target.getAdapterPosition();
                try {
                    selectDetailImage.add(oldIndex, selectDetailImage.get(newIndex));
                    selectDetailImage.add(newIndex + 1, selectDetailImage.get(oldIndex + 1));

                    selectDetailImage.remove(oldIndex + 1);
                    selectDetailImage.remove(newIndex + 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {

            }
        };

        //detailAdapter.setOnItemDragListener(listener);

        rvDetailImage.setAdapter(detailAdapter);
        detailAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            selectDetailImage.remove(position);
            if (releaseType == 2 && !editDetailImage.isEmpty())
                editDetailImage.remove(position);
            detailAdapter.notifyDataSetChanged();
        });
    }

    //选择全部 图片
    private void initSelectAllAdapter() {
        rvUpImage.setLayoutManager(new GridLayoutManager(mContext, 3));
        rvUpImage.setHasFixedSize(true);
        imageAdapter = new RelShowDragImageAdapter(selectAllImage);

        ItemDragAndSwipeCallback mItemDragAndSwipeCallback = new ItemDragAndSwipeCallback(imageAdapter);
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(mItemDragAndSwipeCallback);
        mItemTouchHelper.attachToRecyclerView(rvUpImage);
        mItemDragAndSwipeCallback.setSwipeMoveFlags(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        imageAdapter.enableDragItem(mItemTouchHelper);

        OnItemDragListener listener = new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {

                final int oldIndex = source.getAdapterPosition();
                final int newIndex = target.getAdapterPosition();
                try {
                    selectAllImage.add(oldIndex, selectAllImage.get(newIndex));
                    selectAllImage.add(newIndex + 1, selectAllImage.get(oldIndex + 1));

                    selectAllImage.remove(oldIndex + 1);
                    selectAllImage.remove(newIndex + 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {

            }
        };

        //imageAdapter.setOnItemDragListener(listener);
        rvUpImage.setAdapter(imageAdapter);
        imageAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            selectAllImage.remove(position);
            if (releaseType == 2 && !editAllImage.isEmpty())
                editAllImage.remove(position);
            imageAdapter.notifyDataSetChanged();
        });
    }


    //编辑商品详细  todo

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
                postMoney = result.getGoods_freight();
                //设置图片

                imageAdapter.setNewData(selectAllImage);
                detailAdapter.setNewData(selectDetailImage);

                String detail = result.getDetail();
                int is_yourself = result.getIs_yourself();
                gc_id = result.getGc_id();

                EditShopBean.ResultBean.SpecListBean spec_list = result.getSpec_list();
                if (spec_list != null) {
                    String spec_name = spec_list.getSpec_name();
                    List<EditShopBean.ResultBean.SpecListBean.SpecValueBean> spec_value = spec_list.getSpec_value();
                    int goods_stock = result.getGoods_stock(); // 商品库存
                    tvShopSize.setText(spec_name);

                    if (spec_value != null) {
                        for (int i = 0; i < spec_value.size(); i++) {
                            EditShopBean.ResultBean.SpecListBean.SpecValueBean specValueBean = spec_value.get(i);
                            ShopSpecBean.ShopSpecItemBean shopSpecItemBean = new ShopSpecBean.ShopSpecItemBean();
                            shopSpecItemBean.setPrice(specValueBean.getPrice());
                            shopSpecItemBean.setSpec_value(specValueBean.getSpec_value());
                            shopSpecItemBean.setInner_price(specValueBean.getInner_price());
                            shopSpecItemBean.setStock_num(specValueBean.getStock_num() + "");
                            list.add(shopSpecItemBean);
                            shopSpecBeans.add(shopSpecItemBean);
                        }

                        refreshSpecAdapter(true);
                    }
                }

                List<EditShopBean.ResultBean.GoodsAttrBean> goods_attr = result.getGoods_attr();
                if (goods_attr != null && goods_attr.size() > 0) {
                    for (int i = 0; i < goods_attr.size(); i++) {
                        String gaName = goods_attr.get(i).getName();
                        String gaValue = goods_attr.get(i).getValue();
                        ShopAttrBean shopAttrBean = new ShopAttrBean();
                        shopAttrBean.setValue(gaValue);
                        shopAttrBean.setName(gaName);
                        shopAttrBeans.add(shopAttrBean);

                    }
                    refreshAttrAdapter(false);
                    attrAdapter.setNewData(shopAttrBeans);
                }

                List<EditShopBean.ResultBean.GoodsTempleteBean> goods_templete = result.getGoods_templete();
                if (goods_templete != null && goods_templete.size() > 0) {
                    template = new ArrayList<>();
                    for (int i = 0; i < goods_templete.size(); i++) {
                        String tempName = goods_templete.get(i).getName();
                        String tempValue = goods_templete.get(i).getValue();
                        XLog.e("" + tempName + " - " + tempValue);
                        temMap.put(tempName, tempValue);
                        hasTemplate = 1;
                        GoodsTemplateBean.ResultBean.TempleteBean templeteBean = new GoodsTemplateBean.ResultBean.TempleteBean();
                        templeteBean.setName(tempName);
                        templeteBean.setType("");
                        templeteBean.setRequire(0);
                        templeteBean.setValue(tempValue);
                        template.add(templeteBean);
                    }

                    templateAdapter = new GoodsTemplateAdapter();
                    templateAdapter.setNewData(template);
                    rvTemplate.setAdapter(templateAdapter);
                }

                String name = result.getGoods_name();
                String producer = result.getProducer();
                String inner_price = result.getInner_price();
                String commission = result.getCommission();
                int editStock = result.getGoods_stock();
                String gc_name = result.getGc_name();
                tvShopCate.setText(gc_name);
                etShopName.setText(name);
                etShopArea.setText(producer);
                etShopMoney.setText(commission);
                etShopDesc.setText(detail);
                etPostMoney.setText(postMoney);
                // etShopCount.setText(String.valueOf(editStock));
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

    @OnClick({R.id.btn_commit, R.id.ctl_category, R.id.et_shop_price, R.id.tv_update_spec,
            R.id.tv_add_spec, R.id.tv_add_attr, R.id.iv_up_img, R.id.iv_detail_img})
    public void onViewClick(View view) {

        switch (view.getId()) {
            case R.id.et_shop_price:
                XKeyboardUtils.closeKeyboard(this);
                showCitySelector();
                break;

            case R.id.btn_commit:
                releaseCheckData();
                break;

            case R.id.iv_detail_img:
                if (selectDetailImage != null) {
                    if (selectDetailImage.size() >= 9) {
                        T.showToast(mContext, "最多选择9张图片");
                        return;
                    }
                }
                ReleaseShopNewActivityPermissionsDispatcher.showCameraWithPermissionCheck(ReleaseShopNewActivity.this);
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
                ReleaseShopNewActivityPermissionsDispatcher.showCameraWithPermissionCheck(ReleaseShopNewActivity.this);
                break;

            case R.id.ctl_category:
                requestShopCategory();
                break;

            case R.id.tv_add_spec:
                addSpec();
                break;
            case R.id.tv_add_attr:
                addAttr();
                break;
        }
    }

    private void releaseCheckData() {
        String shopName = etShopName.getText().toString().trim();
        String shopArea = etShopArea.getText().toString().trim(); //产地

        String shopDesc = etShopDesc.getText().toString().trim();
        String shopComm = etShopMoney.getText().toString().trim();
        String shopSize = tvShopSize.getText().toString().trim();
        String shopCate = tvShopCate.getText().toString().trim();


        if (TextUtils.isEmpty(shopName)) {
            XToast.normal("请填写商品名称");
            return;
        }

        if (TextUtils.isEmpty(shopArea)) {
            XToast.normal("请选择商品产地");
            return;
        }
        if (!TextUtils.isEmpty(shopComm)) {
            if (Double.valueOf(shopComm) > 100) {
                XToast.normal("分享奖励比例必须小于100");
                return;
            }
        }

        if (TextUtils.isEmpty(shopDesc)) {
            XToast.normal("请填写商品描述信息");
            return;
        }

        if (TextUtils.isEmpty(shopCate)) {
            XToast.normal("请选择商品分类");
            return;
        }

        if (shopAttrBeans.size() == 0) {
            XToast.normal("请添加属性选项");
            return;
        }

        for (ShopAttrBean attrBean : shopAttrBeans) {
            String attrName = attrBean.getName();
            String attrValue = attrBean.getValue();
            if (TextUtils.isEmpty(attrName) || TextUtils.isEmpty(attrValue)) {
                XToast.normal("请把属性信息填写完整");
                return;
            }
        }
        // 属性 json
        attrJson = GsonUtils.toJson(shopAttrBeans);

//        if (template == null) {
//            XToast.normal("请选择商品分类");
//            return;
//        }


        if (Util.handleOnDoubleClick()) {
            return;
        }
        if (selectAllImage == null || selectAllImage.size() == 0) {
            XToast.normal("请上传商品图片");
            return;
        }

        if (selectDetailImage == null || selectDetailImage.size() == 0) {
            XToast.normal("请上传详情图片");
            return;
        }
        loading(false, "发布中...");

        uploadImage.clear();
        uploadDetailImage.clear();
        if (releaseType == 1) {
            for (int i = 0; i < selectAllImage.size(); i++) {
                File file = new File(selectAllImage.get(i));
                mHandler.postDelayed(() -> uploadImage(file, 1), 300);
            }
        } else {
            // TODO: 2019/9/7
            if (editAllImage.size() > 0) {
                for (int i = 0; i < editAllImage.size(); i++) {
                    File file = new File(editAllImage.get(i));
                    mHandler.postDelayed(() -> uploadImage(file, 1), 300);
                }
            } else {
                if (editDetailImage.size() > 0) {
                    for (int i = 0; i < editDetailImage.size(); i++) {
                        File file = new File(editDetailImage.get(i));
                        mHandler.postDelayed(() -> uploadImage(file, 2), 300);
                    }
                } else {
                    releaseGoods();
                }
            }
        }
    }

    //选择产地
    private void showCitySelector() {
        if (mSelectCityManager == null) {
            mSelectCityManager = SelectCityManager.getInstance(mContext, false);
            mSelectCityManager.setOnSelectOptionListener((options1, provinceName, options2, cityName, options3, areaName) -> {
                provinceOpt = options1;
                cityOpt = options2;
                areaOpt = options3;
                XLog.e("" + provinceOpt + " - " + cityOpt + " - " + areaOpt);
                //etShopArea.setText(String.valueOf(provinceName + " " + cityName));
                etShopArea.setText(cityName);
            });
        }
        mSelectCityManager.showDialog(provinceOpt, cityOpt, areaOpt);
    }

    //添加规格
    private void addSpec() {
        ShopSpecBean.ShopSpecItemBean shopSpecBean = new ShopSpecBean.ShopSpecItemBean();
        shopSpecBeans.add(shopSpecBean);
        refreshSpecAdapter(false);
    }

    //添加属性
    public void addAttr() {
        ShopAttrBean shopAttrBean = new ShopAttrBean();
        if (shopAttrBeans.size() == 0) {
            shopAttrBean.setName("");
            shopAttrBean.setValue("");
        }

        shopAttrBeans.add(shopAttrBean);
        refreshAttrAdapter(false);
    }

    public void refreshSpecAdapter(boolean isReset) {
        if (shopSpecAdapter == null || isReset) {
            shopSpecAdapter = new ShopSpecAdapter();
            shopSpecAdapter.setNewData(shopSpecBeans);
            shopSpecAdapter.setIsInner(isInner);

            shopSpecBeans = (ArrayList<ShopSpecBean.ShopSpecItemBean>) shopSpecAdapter.getData();
            shopSpecAdapter.setAdapterInterface(adapterInterface);
            rvShopSpec.setAdapter(shopSpecAdapter);
            shopSpecAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                if (position < 1) {
                    XToast.normal("商品第一条规格不可删除");
                    return;
                }
                shopSpecBeans.remove(position);
                shopSpecAdapter.notifyDataSetChanged();
            });
        } else {
            shopSpecAdapter.notifyDataSetChanged();
        }
    }

    private ShopSpecAdapter.ShopSpecAdapterInterface adapterInterface = new ShopSpecAdapter.ShopSpecAdapterInterface() {
        @Override
        public void onTextChanged(int position, int type, String s) {
            ShopSpecBean.ShopSpecItemBean bean = shopSpecBeans.get(position);

            switch (type) {
                case 1:
                    bean.setSpec_value(s);
                    break;
                case 2:
                    bean.setPrice(s);
                    break;
                case 3:
                    bean.setStock_num(s);
                    break;
                case 4:
                    bean.setInner_price(s);
                    break;
            }
        }
    };

    //属性 适配器
    public void refreshAttrAdapter(boolean isReset) {
        if (attrAdapter == null || isReset) {
            attrAdapter = new GoodsAttrAdapter();
            attrAdapter.setNewData(shopAttrBeans);

            shopAttrBeans = (ArrayList<ShopAttrBean>) attrAdapter.getData();
            attrAdapter.setAdapterInterface(adapterAttrInterface);
            rvAttr.setAdapter(attrAdapter);
            attrAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                if (position < 1) {
                    XToast.normal("商品第一条属性不可删除");
                    return;
                }
                shopAttrBeans.remove(position);
                attrAdapter.notifyDataSetChanged();
            });

        } else {
            attrAdapter.notifyDataSetChanged();
        }
    }

    private GoodsAttrAdapter.ShopSpecAdapterInterface adapterAttrInterface = new GoodsAttrAdapter.ShopSpecAdapterInterface() {
        @Override
        public void onTextChanged(int position, int type, String s) {
            ShopAttrBean bean = shopAttrBeans.get(position);
            switch (type) {
                case 1:
                    bean.setName(s);
                    break;
                case 2:
                    bean.setValue(s);
                    break;
            }
        }
    };

    //选择商品分类
    private void requestShopCategory() {
        loading(true);
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.shopCategory, null, new BaseRequestUtils.getRequestCallBack() {

            private List<List<String>> childList3;

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
                gcChildList3.clear();
                gcList.clear();

                for (int i = 0; i < result.size(); i++) {
                    String gc_name = result.get(i).getGc_name();
                    gcList.add(gc_name);
                    List<String> gcChild = new ArrayList<>();
                    childList3 = new ArrayList<>();
                    List<List<String>> gcChild3 = new ArrayList<>();

                    List<ShopCategoryBean.ResultBean.ChildBeanX> child2 = result.get(i).getChild();
                    if (child2 == null || child2.isEmpty()) {
                        gcChildList.add(gcChild);
                        gcChildList3.add(new ArrayList<List<String>>());
                        continue;
                    }

                    for (int j = 0; j < child2.size(); j++) {
                        List<String> gcChild3Child = new ArrayList<>();
                        ShopCategoryBean.ResultBean.ChildBeanX childBeanX = child2.get(j);
                        String gc_child_name = childBeanX.getGc_name();

                        gcChild.add(gc_child_name);
                        List<ShopCategoryBean.ResultBean.ChildBeanX.ChildBean> child3 = childBeanX.getChild();
                        if (child3 == null || child3.isEmpty()) {
                            gcChild3Child.add("其他的");
                            gcChild3.add(gcChild3Child);
                            continue;
                        }
                        for (ShopCategoryBean.ResultBean.ChildBeanX.ChildBean childBean : child3) {
                            String gc_name1 = childBean.getGc_name();
                            gcChild3Child.add(gc_name1);
                        }
                        gcChild3.add(gcChild3Child);
                    }
                    gcChildList3.add(gcChild3);
                    gcChildList.add(gcChild);
                }

                initCompanyPicker(gcList, gcChildList, gcChildList3);
            }

            @Override
            public void failed(Response<String> response) {
                dismiss();
                XToast.normal(getString(R.string.service_error));
            }
        });
    }

    private void initCompanyPicker(final List<String> listData, List<List<String>> childList, List<List<List<String>>> childList3) {
        pvOptions = new OptionsPickerBuilder(this, (options1, options2, options3, v) -> {
            String pStr = listData.get(options1);
            String cStr = "";
            String c3Str = "";
            String name = "";

            if (childList.size() > 0) {
                cStr = childList.get(options1).get(options2);
                c3Str = gcChildList3.get(options1).get(options2).get(options3);
                if ("其他的".equals(c3Str)) {
                    // 证明没数据
                    ShopCategoryBean.ResultBean.ChildBeanX childBeanX = result.get(options1).getChild().get(options2);
                    gc_id = childBeanX.getGc_id();
                } else {
                    ShopCategoryBean.ResultBean.ChildBeanX.ChildBean childBean = result.get(options1).getChild().get(options2).getChild().get(options3);
                    name = childBean.getGc_name();
                    gc_id = childBean.getGc_id();
                }
            } else {
                gc_id = result.get(options1).getGc_id();
            }
            showGoodsTemplate();

            XLog.e("商品分类  " + pStr + " - " + cStr);
            tvShopCate.setText(pStr + " " + cStr + " " + name);
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

        pvOptions.setPicker(listData, childList, childList3);
//        pvOptions.setPicker(listData, childList, null);
        pvOptions.show();
    }


    //商品模板
    private void showGoodsTemplate() {
        Map<String, String> params = new HashMap<>();
        params.put("gc_id", String.valueOf(gc_id));
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.get_goods_templete, params, new BaseRequestUtils.getRequestCallBack() {

            @Override
            public void success(Response<String> response) {
                XLog.e("模板 " + response.body());
                GoodsTemplateBean templateBean = FastJsonUtil.fromJson(response.body(), GoodsTemplateBean.class);
                if (!templateBean.getCode().equals(Constant.REQUEST_SUCCESS) || templateBean.getResult() == null) {
                    XToast.normal(templateBean.getMessage());
                    hasTemplate = 0;
                    return;
                }
                template = templateBean.getResult().getTemplete();
                if (template == null || template.size() == 0) {
                    hasTemplate = 0;
                    return;
                }
                hasTemplate = 1;

                templateAdapter.setNewData(template);
                templateAdapter.setAdapterInterface((position, type, name, s) -> {
                    temMap.put(name, s);
                    templateValue = s;
                    String json = GsonUtils.toJson(temMap);
                    XLog.e("temMap  " + json);
                });

            }

            @Override
            public void failed(Response<String> response) {
                hasTemplate = 0;
                XLog.e("模板  " + response.getException().getMessage());
            }
        });
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
                    if (releaseType == 1) {
                        if (selectAllImage.size() == uploadImage.size() || selectAllImage.size() < uploadImage.size()) {
                            for (int i = 0; i < selectDetailImage.size(); i++) {
                                File file = new File(selectDetailImage.get(i));
                                mHandler.postDelayed(() -> uploadImage(file, 2), 300);
                            }
                        }
                    } else {
                        if (editAllImage.size() == uploadImage.size() || editAllImage.size() < uploadImage.size()) {
                            if (editDetailImage.size() > 0) {
                                for (int i = 0; i < editDetailImage.size(); i++) {
                                    File fileE = new File(editDetailImage.get(i));
                                    mHandler.postDelayed(() -> uploadImage(fileE, 2), 300);
                                }
                            } else {
                                releaseGoods();
                            }
                        }
                    }
                } else {
                    uploadDetailImage.add(imgUrl);
                    if (releaseType == 1) {
                        if (selectDetailImage.size() == uploadDetailImage.size()) {
                            releaseGoods();
                        }
                    } else {
                        if (editDetailImage.size() == uploadDetailImage.size()) {
                            releaseGoods();
                        }
                    }
                }
            }

            @Override
            public void failed(Response<String> response) {
                dismiss();
                XLog.e("上传图片异常  " + response.getException().getMessage());
                XToast.normal(getString(R.string.service_error));
            }
        });

    }

    //发布商品 ss
    private void releaseGoods() {

        String shopName = etShopName.getText().toString().trim();
        String shopArea = etShopArea.getText().toString().trim();
        postMoney = etPostMoney.getText().toString().trim();
        String shopMoney = etShopMoney.getText().toString().trim();
        String shopDesc = etShopDesc.getText().toString().trim();
        String shopSpec = tvShopSize.getText().toString().trim();
        boolean checked = cbOpen.isChecked();

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (releaseType == 1) {
            for (int i = 0; i < uploadImage.size(); i++) {
                sb.append("\"").append(uploadImage.get(i)).append("\"").append(",");
            }
        } else {
            selectAllImage.addAll(uploadImage);
            for (int i = 0; i < selectAllImage.size(); i++) {
                if (selectAllImage.get(i).startsWith("http")) {
                    sb.append("\"").append(selectAllImage.get(i)).append("\"").append(",");
                }
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
                sbDetailImage.append("\"").append(uploadDetailImage.get(i)).append("\"").append(",");
            }
        } else {
            XLog.e("图片数量 " + selectDetailImage.size() + " ---- " + uploadDetailImage.size());
            if (!uploadDetailImage.isEmpty())
                selectDetailImage.addAll(uploadDetailImage);
            if (selectDetailImage != null && selectDetailImage.size() > 0) {
                for (int i = 0; i < selectDetailImage.size(); i++) {
                    if (selectDetailImage.get(i).startsWith("http")) {
                        sbDetailImage.append("\"").append(selectDetailImage.get(i)).append("\"").append(",");
                    }
                }
            }
        }
        if (sbDetailImage.toString().endsWith(",")) {
            int i = sbDetailImage.toString().lastIndexOf(",");
            sbDetailImage.replace(i, i + 1, "");
        }
        sbDetailImage.append("]");

        StringBuilder sbTemplate = new StringBuilder();

        if (hasTemplate == 1) {
            if (temMap.size() == 0) {
                XToast.normal("请填写完整分类信息");
                dismiss();
                return;
            }
            sbTemplate.append("[");

            for (Map.Entry<String, String> map : temMap.entrySet()) {
                String key = map.getKey();
                String value = map.getValue();
                if (TextUtils.isEmpty(value)) {
                    XToast.normal("请填写完整分类信息");
                    dismiss();
                    return;
                }
                sbTemplate.append("{\"name\":\"");
                sbTemplate.append(key).append("\",\"value\":\"").append(value).append("\"},");
            }
            if (sbTemplate.toString().endsWith(",")) {
                int i = sbTemplate.toString().lastIndexOf(",");
                sbTemplate.replace(i, i + 1, "");
            }
            sbTemplate.append("]");
        }
        XLog.e("模板  " + sbTemplate.toString());

        if (shopSpecBeans.size() == 0) {
            XToast.normal("请添加规格选项");
            dismiss();
            return;
        }
        for (ShopSpecBean.ShopSpecItemBean shopSpecBean : shopSpecBeans) {
            String price = shopSpecBean.getPrice();
            String spec = shopSpecBean.getSpec_value();
            String count = shopSpecBean.getStock_num();
            String innerMoney = shopSpecBean.getInner_price();
            if (TextUtils.isEmpty(price) || TextUtils.isEmpty(spec) || TextUtils.isEmpty(count)) {
                XToast.normal("请把规格信息填写完整");
                dismiss();
                return;
            }
            if (isInner != null && isInner.equals("1")) {
                tvInnerMoneyText.setVisibility(View.VISIBLE);
                if (TextUtils.isEmpty(innerMoney)) {
                    XToast.normal("请输入内部价");
                    dismiss();
                    return;
                }
            }
        }

        String toJson = GsonUtils.toJson(shopSpecBeans);
        String specJson = "{\"" + "规格" + "\":" + toJson + "}";
        XLog.e("specJson " + specJson);

        Map<String, String> params = new HashMap<>();

        params.put("goods_name", shopName); // 商品名称
        params.put("type", String.valueOf(releaseType)); // 是否是编辑 1 新增 2 编辑
        params.put("commission", shopMoney); // 分享奖励金
        params.put("images", sb.toString()); // 商品图
        params.put("detail", shopDesc); // 描述
        params.put("gc_id", String.valueOf(gc_id)); //商品分类id
        params.put("goods_id", goodsId); // 商品id（修改时填写）
        params.put("detail_images", sbDetailImage.toString()); // 商品详情图片
        params.put("spec_list", specJson); // 规格
        params.put("producer", shopArea); // 产地
        params.put("goods_templete", sbTemplate.toString()); // 模板
        params.put("goods_attr", attrJson); // 属性
        if (checked) {
            params.put("is_yourself", "1");
        } else {
            params.put("is_yourself", "0");
        }
        if (TextUtils.isEmpty(postMoney)) {
            params.put("goods_freight", "12");
        } else {
            params.put("goods_freight", postMoney);
        }
        //showProgress();
        XLog.e("上船书局" + shopName + " == " + sbDetailImage.toString() + " ====" + shopArea
                + " ==== " + shopMoney + "=====" + shopDesc + "======" + releaseType + sb.toString()
                + shopArea + " ---" + sbTemplate.toString() + " ---" + attrJson
                + " ====== " + specJson);

        BaseRequestUtils.postRequestWithHeader(this, UrlManager.addGoodsV2, params, new BaseRequestUtils.getRequestCallBack() {
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
                XLog.e("发布一场  " + message);
//                dismissProgress();
                XToast.normal(getString(R.string.service_error));
            }
        });
    }

    //开启相册选择图片
    private void selectPhoto(int imageCount) {
        Matisse.from(ReleaseShopNewActivity.this)
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

    private void selectPhotoListener(List<String> pathList) {

        if (imageType == 1) {
            if (releaseType == 2)
                editAllImage.addAll(pathList);

            selectAllImage.addAll(pathList);

            //上传头像
            if (selectAllImage != null && !selectAllImage.isEmpty()) {
                imageAdapter.setNewData(selectAllImage);
            }
        } else {
            if (releaseType == 2)
                editDetailImage.addAll(pathList);
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
            LunBanUtil.getSingleton(mContext).lunBanImagerS(strings, new LunBanUtil.lunBanInterface() {
                @Override
                public void imgStart() {
                    showProgress();
                    strings.clear();
                }

                @Override
                public void imgSuccess(String path) {
                    dismissProgress();
                    showSelectImag(path);
//                    strings.add(path);
//                    selectPhotoListener(strings);
                }

                @Override
                public void imgError() {
                    dismissProgress();
                    T.showToast(mContext, "图片压缩失败,请重新选择");
                }
            });

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
        ReleaseShopNewActivityPermissionsDispatcher.showCameraWithPermissionCheck(ReleaseShopNewActivity.this);
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    void showCamera() {
        SelectCamerAlerdialog mCamerAlerdialog = new SelectCamerAlerdialog(mContext, R.style.mydialog);
        mCamerAlerdialog.setListener(new SelectCamerAlerdialog.OnItemClickListener() {
            @Override
            public void openCamer() {
                ActivityUtil.openCammer(ReleaseShopNewActivity.this, OPENCAMMER);
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

    //发布须知协议
    private void showReleaseNotice(int type) {

        BaseRequestUtils.postRequest(this, UrlManager.goods_notice, null, new BaseRequestUtils.getRequestCallBack() {

            @Override
            public void success(Response<String> response) {
                AgreementBean agreementBean = FastJsonUtil.fromJson(response.body(), AgreementBean.class);
                if (agreementBean == null) return;
                if (!agreementBean.getCode().equals(Constant.REQUEST_SUCCESS) || agreementBean.getResult() == null) {
                    XToast.normal(getString(R.string.service_error));
                    return;
                }
                AgreementBean.ResultBean result = agreementBean.getResult();
                String agree = result.getAgree();
                noticePop = new ReleaseNoticePop(mContext);
                noticePop.showPopupWindow();
                TextView tv_webview = noticePop.findViewById(R.id.tv_webview);
                RichText.from(agree).into(tv_webview);

                CircleTextProgressbar cpb = noticePop.findViewById(R.id.cpb);
                cpb.setTimeMillis(5000);
                cpb.setOutLineColor(Color.parseColor("#cccccc"));
                cpb.setProgressColor(Color.parseColor("#3F51B5"));
                cpb.setCountdownProgressListener(5, new OnCountdownProgressListener() {
                    @Override
                    public void onProgress(int what, int progress) {
                        if (type == 1) {
                            if (progress == 80) {
                                cpb.setText("4");
                            } else if (progress == 60) {
                                cpb.setText("3");
                            } else if (progress == 40) {
                                cpb.setText("2");
                            } else if (progress == 20) {
                                cpb.setText("1");
                            } else if (progress == 0) {
                                //noticePop.dismiss();
                                cpb.setText("X");
                                cpb.setOnClickListener(v -> noticePop.dismiss());
                                noticePop.setBackPressEnable(true);
                                //noticePop.setAllowDismissWhenTouchOutside(true);
                            }
                        } else {
                            cpb.setText("X");
                            cpb.setOnClickListener(v -> noticePop.dismiss());
                            noticePop.setBackPressEnable(true);
                        }
                    }
                });

                cpb.start();
            }

            @Override
            public void failed(Response<String> response) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.release_top, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.iv_collect) {
            showReleaseNotice(2);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (noticePop != null && noticePop.isShowing())
            noticePop.dismiss();
    }
}
