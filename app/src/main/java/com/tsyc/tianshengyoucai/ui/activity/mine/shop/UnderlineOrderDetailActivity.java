package com.tsyc.tianshengyoucai.ui.activity.mine.shop;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.flyn.Eyes;
import com.tsyc.tianshengyoucai.listener.event.OrderDetailInEvent;
import com.tsyc.tianshengyoucai.listener.event.UnderDetailInEvent;
import com.tsyc.tianshengyoucai.listener.event.UnifiedNotifyEvent;
import com.tsyc.tianshengyoucai.model.adapter.EvaluateImageAdapter;
import com.tsyc.tianshengyoucai.model.bean.NormalResultBean;
import com.tsyc.tianshengyoucai.model.bean.UnderlineOrderDetailBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.activity.home.GoodsDetailActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.ImageLoader;
import com.tsyc.tianshengyoucai.utils.ImgUtils;
import com.tsyc.tianshengyoucai.utils.PayUtils;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.youth.xframe.utils.XDensityUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：van
 * CreateTime：2019/8/12
 * File description：线下订单 详情
 */
public class UnderlineOrderDetailActivity extends BaseActivity implements LocationSource, AMapLocationListener {


    @BindView(R.id.tv_order_status)
    TextView tvOrderStatus;
    @BindView(R.id.tv_auto_close)
    TextView tvAutoClose;
    @BindView(R.id.iv_qrcode)
    ImageView ivQrcode;
    @BindView(R.id.iv_qrcode_top)
    ImageView iv_qrcode_top;
    @BindView(R.id.tv_use_status)
    TextView tvUseStatus;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.aMapView)
    MapView aMapView;
    @BindView(R.id.tv_self_time)
    TextView tvSelfTime;
    @BindView(R.id.tv_level_phone)
    TextView tvLevelPhone;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.iv_order_img)
    ImageView ivOrderImg;
    @BindView(R.id.tv_order_name)
    TextView tvOrderName;
    @BindView(R.id.tv_order_desc)
    TextView tvOrderDesc;
    @BindView(R.id.tv_order_price)
    TextView tvOrderPrice;
    @BindView(R.id.tv_order_count)
    TextView tvOrderCount;
    @BindView(R.id.tv_pay_type)
    TextView tvPayType;
    @BindView(R.id.tv_post_money)
    TextView tvPostMoney;
    @BindView(R.id.tv_pay_money)
    TextView tvPayMoney;
    @BindView(R.id.tv_order_sn)
    TextView tvOrderSn;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_pay_time)
    TextView tvPayTime;
    @BindView(R.id.tv_copy_sn)
    TextView tvCopySn;
    @BindView(R.id.tv_user_say)
    TextView tvUserSay;
    @BindView(R.id.tv_order_gray_status)
    TextView mTvOrderGrayStatus;
    @BindView(R.id.tv_order_red_status)
    TextView mTvOrderRedStatus;
    @BindView(R.id.ctl_btm)
    ConstraintLayout ctlBtm;
    @BindView(R.id.ctl_qrcode)
    ConstraintLayout ctlQrcode;

    @BindView(R.id.ctl_back)
    ConstraintLayout ctlBack;

    @BindView(R.id.ctl_map)
    ConstraintLayout ctlMap;
    @BindView(R.id.ctl_top)
    ConstraintLayout ctlTop;

    @BindView(R.id.ctl_detail)
    ConstraintLayout ctlDetail;

    @BindView(R.id.tv_back_type)
    TextView tvBackType;
    @BindView(R.id.tv_back_time)
    TextView tvBackTime;
    @BindView(R.id.tv_back_money)
    TextView tvBackMoney;
    @BindView(R.id.tv_back_reason)
    TextView tvBackReason;
    @BindView(R.id.rv_reason)
    RecyclerView rvReason;
    @BindView(R.id.tv_bag_tip)
    TextView tvBagTip;
    @BindView(R.id.ctl_bag)
    ConstraintLayout ctlBag;
    @BindView(R.id.tv_coupon)
    TextView tvCoupon;
    @BindView(R.id.tv_coupon_tip)
    TextView tvCouponTip;
    @BindView(R.id.ctl_detail_histroy)
    ConstraintLayout ctl_detail_histroy; // 退款记录
    @BindView(R.id.ctl_status)
    ConstraintLayout ctl_status; // 退款 状态
    @BindView(R.id.root_title)
    View root_title; // 退款 状态 title

    @BindView(R.id.tv_curr_status)
    TextView tvCurrStatus;
    @BindView(R.id.tv_curr_status_detail)
    TextView tvCurrStatusDetail;

    @BindView(R.id.tv_buyer_apply_time)
    TextView tvBuyerApplyTime;
    @BindView(R.id.tv_shoper_agree_time)
    TextView tvShoperAgreeTime;
    @BindView(R.id.tv_shoper_agree_time_tip)
    TextView tvShoperAgreeTimeTip;

    @BindView(R.id.ctl_goods_info)
    ConstraintLayout ctl_goods_info;

    private String orderId;
    private String order_sn;
    private int orderStatus;

    private boolean isLocationSuccess = true;
    private AMap aMap;
    private final int STROKE_COLOR = Color.argb(180, 3, 145, 255);
    private final int FILL_COLOR = Color.argb(10, 0, 0, 180);
    private OnLocationChangedListener mListener;
    private AMapLocationClient mLocationClient;
    private String order_amount;
    private String goodsImage;
    private String goodsName;
    private EvaluateImageAdapter imageAdapter;
    private String order_sn1;
    private String verify_code_img;
    private int goods_id = -1;


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_underline_order_detail;
    }

    @Override
    public void initView() {
        registerEventBus(this);
        mTvTitle.setText(getString(R.string.text_my_order));
        Eyes.translucentStatusBar(this, true);
        Bundle extra = getIntent().getBundleExtra(Constant.bundleExtra);
        if (null != extra) {
            orderId = extra.getString("goods_id");
            order_sn = extra.getString("order_sn");

            requestGoodsInfo(orderId);
        }

        rvReason.setLayoutManager(new GridLayoutManager(mContext, 3));
        rvReason.setHasFixedSize(true);

        imageAdapter = new EvaluateImageAdapter();
        rvReason.setAdapter(imageAdapter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        aMapView.onCreate(savedInstanceState);// 此方法必须重写
        initMap();
    }

    //请求信息
    private void requestGoodsInfo(String goodsId) {
        loading(false);
        Map<String, String> params = new HashMap<>();
        params.put("order_id", goodsId);
        params.put("type", "1");
        params.put("goods_num", "1");

        BaseRequestUtils.postRequestWithHeader(this, UrlManager.line_underline_order, params, new BaseRequestUtils.getRequestCallBack() {

            @Override
            public void success(Response<String> response) {
                dismiss();
                XLog.e("线下订单商品详情信息" + response.body());
                UnderlineOrderDetailBean orderPayBean = FastJsonUtil.fromJson(response.body(), UnderlineOrderDetailBean.class);
                if (null == orderPayBean) {
                    return;
                }
                if (!orderPayBean.getCode().equals(Constant.REQUEST_SUCCESS) || orderPayBean.getResult() == null) {
                    XToast.normal(orderPayBean.getMessage());
                    return;
                }
                UnderlineOrderDetailBean.ResultBean result = orderPayBean.getResult();

                int order_id = result.getOrder_id();
                String liuyan = result.getLiuyan();
                tvUserSay.setText("留言备注：" + liuyan);

                //订单状态
                UnderlineOrderDetailBean.ResultBean.StatusInfoBean status_info = result.getStatus_info();
                if (status_info != null) {
                    String status_text = status_info.getStatus_text(); //状态
                    String status_desc = status_info.getStatus_desc();
                    orderStatus = status_info.getOrder_state();
                    tvOrderStatus.setText(status_text);
                    tvAutoClose.setText(status_desc);
                    tvCurrStatus.setText(status_text);
                }

                //商店信息
                UnderlineOrderDetailBean.ResultBean.StoreInfoBean store_info = result.getStore_info();
                if (store_info != null) {
                    String address = store_info.getAddress();
                    String lat = store_info.getLat();
                    String lng = store_info.getLng();
                    int store_id = store_info.getStore_id();
                    String store_name = store_info.getStore_name();
                    //tvName.setText(address);
                    setStoreInfo(store_info);
                }

                //留言信息
                UnderlineOrderDetailBean.ResultBean.TakingInfoBean taking_info = result.getTaking_info();
                if (taking_info != null) {
                    String ziti_phone = taking_info.getZiti_phone();
                    String ziti_time = taking_info.getZiti_time();
                    String zitiVerifyCode = taking_info.getZiti_verify_code();
                    verify_code_img = taking_info.getVerify_code_img();
                    tvSelfTime.setText(ziti_time);
                    tvLevelPhone.setText(ziti_phone);

                    ImageLoader.loadNormal(mContext, verify_code_img, ivQrcode);
                    //genQrCode(zitiVerifyCode);
                }

                //订单信息
                List<UnderlineOrderDetailBean.ResultBean.OrdergoodsBean> order_goods = result.getOrdergoods();
                if (order_goods != null && order_goods.size() > 0) {
                    goods_id = order_goods.get(0).getGoods_id();
                    goodsImage = order_goods.get(0).getGoods_image();
                    goodsName = order_goods.get(0).getGoods_name();
                    int num = order_goods.get(0).getGoods_num();
                    String price = order_goods.get(0).getGoods_price();
                    String guide = order_goods.get(0).getGoods_guige();
                    ImgUtils.setImage(mContext, goodsImage, ivOrderImg);
                    tvOrderName.setText(goodsName);
                    tvOrderCount.setText(String.valueOf(num));
                    tvOrderPrice.setText("￥" + price);
                    tvOrderDesc.setText("规格："+guide);
                }

                //退款状态
                UnderlineOrderDetailBean.ResultBean.RefundBean refund = result.getRefund();
                if (refund != null) {
                    String add_time = refund.getAdd_time();
                    String reason_info = refund.getReason_info();
                    String refund_amount = refund.getRefund_amount();
                    String refund_type = refund.getRefund_type();
                    List<String> pic_info = refund.getPic_info();

                    tvBackTime.setText(add_time);
                    tvBackMoney.setText("￥" + refund_amount);
                    tvBackType.setText(refund_type);
                    tvBackReason.setText(reason_info);

                    if (pic_info.size() > 0) {
                        imageAdapter.setNewData(pic_info);
                        imageAdapter.setListener((adapterPosition, item) -> mResultTo.startEvaluateImager(mContext,adapterPosition,pic_info));

                    }
                }

                //支付信息
                UnderlineOrderDetailBean.ResultBean.PayInfoBean pay_info = result.getPay_info();
                if (pay_info != null) {
                    String goods_amount = pay_info.getGoods_amount();
                    order_amount = pay_info.getOrder_amount();
                    String payment_name = pay_info.getPayment_name();

                    tvPayType.setText(payment_name);
                    tvPostMoney.setText("￥" + goods_amount);
                    tvPayMoney.setText("￥" + order_amount);
                    tvBagTip.setText("￥" + pay_info.getRedpacket_discount()+"元");
                    tvCouponTip.setText("￥" + pay_info.getCoupon_discount()+"元");
                }

                //退款 订单等  一些时间
                UnderlineOrderDetailBean.ResultBean.LineInfoBean line_info = result.getLine_info();
                if (line_info != null) {
                    String add_time = line_info.getAdd_time();
                    String payment_time = line_info.getPayment_time();
                    order_sn1 = result.getOrder_sn();
                    tvOrderSn.setText("订单编号：" + order_sn1);
                    tvStartTime.setText("创建时间：" + add_time);
                    tvPayTime.setText("付款时间：" + payment_time);

                    tvStartTime.setText("创建时间：" + add_time);
                    String refund_time = line_info.getRefund_time();
                    String seller_time = line_info.getAdmin_time();
                    tvBuyerApplyTime.setText(refund_time);
                    if (TextUtils.isEmpty(seller_time)) {
                        tvShoperAgreeTimeTip.setVisibility(View.GONE);
                        tvShoperAgreeTime.setVisibility(View.GONE);
                    } else {
                        tvShoperAgreeTime.setText(seller_time);
                    }

                }
                if (orderStatus == 20) {
                    tvUseStatus.setText("待使用");
                } else {
                    tvUseStatus.setText("已使用");
                }
                mTvOrderGrayStatus.setVisibility(View.VISIBLE);
                mTvOrderRedStatus.setVisibility(View.VISIBLE);

                switch (orderStatus) {
                    case -1:
                    case 0:
                        ctlQrcode.setVisibility(View.GONE);
                        mTvOrderGrayStatus.setText("删除订单");
                        mTvOrderRedStatus.setVisibility(View.GONE);
                        break;

                    case 10:
                        ctlQrcode.setVisibility(View.GONE); // 核销码隐藏
                        mTvOrderGrayStatus.setText("取消订单");
                        mTvOrderRedStatus.setText("去付款");
                        break;

                    case 20:
                        mTvOrderGrayStatus.setText("申请退款");
                        mTvOrderRedStatus.setText("待使用");

                        break;
                    case 40: //已使用
                        iv_qrcode_top.setVisibility(View.VISIBLE);
                        mTvOrderGrayStatus.setText("删除订单");
                        mTvOrderRedStatus.setText("去评价");
                        break;

                    case 50:
                        mTvOrderRedStatus.setVisibility(View.GONE);
                        mTvOrderGrayStatus.setText("删除订单");
                        break;

                    case 100: //退款完成
                        ctlQrcode.setVisibility(View.GONE);
                        mTvOrderGrayStatus.setText("删除订单");
                        mTvOrderRedStatus.setVisibility(View.GONE);statusOfBackMoney();
                        tvCurrStatusDetail.setText("退款已完成");
                        break;

                    case 101:
                        tvCurrStatusDetail.setText("您申请了维权，等待商家审核");
                        mTvOrderRedStatus.setVisibility(View.GONE);
                        mTvOrderGrayStatus.setText("待商家审核");
                        ctlBack.setVisibility(View.VISIBLE);
                        ctlMap.setVisibility(View.GONE);
                        ctlQrcode.setVisibility(View.GONE);statusOfBackMoney();
                        break;

                    case 104:
                        tvCurrStatusDetail.setText("商家已同意，等待退款");
                        mTvOrderRedStatus.setVisibility(View.GONE);
                        mTvOrderGrayStatus.setText("待平台打款");
                        ctlBack.setVisibility(View.VISIBLE);
                        ctlMap.setVisibility(View.GONE);
                        ctlQrcode.setVisibility(View.GONE);
                        statusOfBackMoney();
                        break;

                }
            }

            @Override
            public void failed(Response<String> response) {
                dismiss();

            }
        });
    }

    //申请退款后的状态
    private void statusOfBackMoney() {
        ctlBack.setVisibility(View.VISIBLE);
        root_title.setVisibility(View.VISIBLE);
        ctlDetail.setVisibility(View.GONE);
        ctlTop.setVisibility(View.GONE);
        ctl_status.setVisibility(View.VISIBLE);
        ctl_detail_histroy.setVisibility(View.VISIBLE);
        ctlMap.setVisibility(View.GONE);

    }

    @OnClick({R.id.rl_back, R.id.tv_order_gray_status, R.id.tv_copy_sn,R.id.ctl_goods_info, R.id.tv_order_red_status})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;

            case R.id.tv_copy_sn:
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData mClipData = ClipData.newPlainText("Label", order_sn1);
                cm.setPrimaryClip(mClipData);
                XToast.normal("复制成功");
                break;
            case R.id.tv_order_gray_status:
                grayBtnOrderStatus();
                break;

            case R.id.tv_order_red_status:
                redBtnOrderStatus();
                break;

            case R.id.ctl_goods_info:
                if (goods_id ==-1){
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("goods_id",String.valueOf(goods_id));
                openActivity(GoodsDetailActivity.class,bundle);
                break;

        }

    }

    // 灰按钮点击事件
    private void grayBtnOrderStatus() {
        Bundle bundle = new Bundle();
        switch (orderStatus) {
            case -1:
            case 0:
            case 40:
            case 50:
                dealOrder("delete");
                break;
            case 10:// 待付款 取消订单
                dealOrder("quxiao_order");
                break;
            case 20: // 待发货 申请退货
                bundle.putString("order_id", orderId);
                bundle.putString("order_in", "underline_order");
                openActivity(ApplyBackMoneyActivity.class, bundle);
                break;
            case 30:  // 待收货 申请退货
                bundle.putString("order_id", orderId);
                openActivity(ApplyBackMoneyActivity.class, bundle);
                break;
            case 100:
                dealOrder("delete");
                break;
            case 101:
                // orderType = "等待商家审核";
                break;
            case 104:
                // orderType = "等待平台打款";
                break;

        }
    }

    // 红按钮点击事件
    private void redBtnOrderStatus() {
        Bundle bundle = new Bundle();
        switch (orderStatus) {
            case 10: //去付款
                if (TextUtils.isEmpty(order_sn)) {
                    XToast.normal("请支付有效订单");
                    return;
                }
                bundle.putString("order_sn", order_sn);
                bundle.putString("order_type", "underline");
                bundle.putString("order_price", String.valueOf(order_amount));
                bundle.putString("order_in", "underline_order");
                openActivity(OrderPayActivity.class, bundle);
                break;
            case 20:
                XToast.normal("请商家扫码二维码");
                break;
            case 40: // 去评价
                bundle.putString("goods_image", goodsImage);
                bundle.putString("goods_name", goodsName);
                bundle.putString("order_id", orderId);
                bundle.putString("order_in", "underline_order");
                openActivity(EvaluateActivity.class, bundle);
                break;
        }
    }

    private void dealOrder(String type) {

        String url = "";
        if (type.equals("confirm_receive")) {
            url = UrlManager.confirm_receive;
        } else if (type.equals("quxiao_order")) {
            url = UrlManager.cancel_order;
        }else if (type.equals("delete")){
            url = UrlManager.del_order;
        }

        Map<String, String> params = new HashMap<>();
        params.put("order_id", orderId);
        loading(true);
        BaseRequestUtils.postRequestWithHeader(this, url, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                dismiss();

                NormalResultBean resultBean = FastJsonUtil.fromJson(response.body(), NormalResultBean.class);
                if (null == resultBean) {
                    XToast.normal("操作失败，请稍候再试");
                    return;
                }
                if (!resultBean.getCode().equals(Constant.REQUEST_SUCCESS)) {
                    XToast.normal(resultBean.getMessage());
                    return;
                }
                XToast.normal(resultBean.getMessage());

                EventBus.getDefault().post(new UnifiedNotifyEvent(Constant.UNDERLINE_APPLY_BACK_MONEY));
                finish();
            }

            @Override
            public void failed(Response<String> response) {
                dismiss();
                XToast.normal(getString(R.string.service_error));

            }
        });
    }


    //设置地图
    private void setStoreInfo(UnderlineOrderDetailBean.ResultBean.StoreInfoBean store_info) {
        double lat = Double.parseDouble(TextUtils.isEmpty(store_info.getLat()) ? "0.0" : store_info.getLat());
        double lng = Double.parseDouble(TextUtils.isEmpty(store_info.getLng()) ? "0.0" : store_info.getLng());
        String address = store_info.getAddress();
        String store_name = store_info.getStore_name();
        tvName.setText(address);

        CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(
                new CameraPosition(new LatLng(lat, lng), 13, 0, 0));
        aMap.moveCamera(mCameraUpdate);
        LatLng latLng = new LatLng(lat, lng);

        MarkerOptions markerOptions = new MarkerOptions().icon(BitmapDescriptorFactory
                .fromView(getBitmapView(mContext, store_name)));
        markerOptions.position(latLng);
        markerOptions.visible(true);
        final Marker marker = aMap.addMarker(markerOptions);
        marker.showInfoWindow();

    }

    public static View getBitmapView(Context context, String store_name) {
        LayoutInflater factory = LayoutInflater.from(context);
        View view = factory.inflate(R.layout.item_marker_name, null);
        TextView tv_title = view.findViewById(R.id.tv_name);
        tv_title.setText(store_name);
        return view;
    }


    //生成二维码
    private void genQrCode(String verifyCode) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.logo);

//      String qrCodeData = "{\"money\":" + (verifyCode.equals("") ? "-1" : verifyCode) + ",\"cashier_id\":" + verifyCode + ",\"type\":\"rec_pay\"}";
        XLog.e("二维码json" + verifyCode);
        Bitmap qrImage = CodeUtils.createImage(verifyCode, XDensityUtils.dp2px(179), XDensityUtils.dp2px(178), null);
        ivQrcode.setImageBitmap(qrImage);
    }


    //初始化地图
    private void initMap() {
        if (aMap == null) {
            aMap = aMapView.getMap();
        }
        aMap.setLocationSource(this);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        //设置true 表示显示定位层并出发 定位，false 表示
        aMap.setMyLocationEnabled(true);
        aMap.setOnMapTouchListener(motionEvent -> {

        });
        aMap.moveCamera(CameraUpdateFactory.zoomTo(14f));// 设置地图的缩放等级
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        //仅仅是展示地图
        aMap.setMyLocationStyle(myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW));
        setupLocationStyle();
    }

    /**
     * 设置自定义当前位置蓝点
     */
    private void setupLocationStyle() {
        // 自定义系统定位蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        // 自定义定位蓝点图标
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.
                fromResource(R.mipmap.jft_icon_companyaddress));
        // 自定义精度范围的圆形边框颜色
        myLocationStyle.strokeColor(STROKE_COLOR);
        //自定义精度范围的圆形边框宽度
        myLocationStyle.strokeWidth(5);
        // 设置圆形的填充颜色
        myLocationStyle.radiusFillColor(FILL_COLOR);
        // 将自定义的 myLocationStyle 对象添加到地图上
        aMap.setMyLocationStyle(myLocationStyle);
    }

    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mLocationClient == null) {
            mLocationClient = new AMapLocationClient(mContext);
            AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
            //设置定位监听   onLocationChanged
            mLocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            mLocationOption.setNeedAddress(true); // 返回位置
            mLocationOption.setLocationCacheEnable(false);//关闭缓存
            mLocationOption.setMockEnable(true); //是否允许模拟位置
            //设置定位参数
            mLocationClient.setLocationOption(mLocationOption);
            mLocationClient.startLocation(); // 开启定位
        }
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
        mLocationClient = null;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                if (isLocationSuccess) {
                    mListener.onLocationChanged(aMapLocation); //显示小蓝点
                    isLocationSuccess = false;
                }
            } else {
                String errorInfo = aMapLocation.getErrorInfo();
                int errorCode = aMapLocation.getErrorCode();
                XLog.e("定位失败 " + errorInfo + "\n" + errorCode);
            }
        } else {
            XToast.normal("定位失败");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        aMapView.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        aMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();
        aMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        aMapView.onDestroy();
        registerEventBus(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void evaluateSuccess(UnderDetailInEvent event){
        String msg = event.getMsg();
        if (msg.equals("ok")){
            finish();
        }
    }
}
