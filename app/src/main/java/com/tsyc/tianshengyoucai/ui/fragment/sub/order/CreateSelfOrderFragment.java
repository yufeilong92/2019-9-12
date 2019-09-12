package com.tsyc.tianshengyoucai.ui.fragment.sub.order;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.amap.api.services.core.LatLonPoint;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.Eventbuss.ConponsEvent;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.model.bean.CreateOrderSuccessBean;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.model.bean.SelfOrderBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.activity.login.SingleWebActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.OrderPayActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseFragment;
import com.tsyc.tianshengyoucai.utils.Arith;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.ImageLoader;
import com.tsyc.tianshengyoucai.utils.NumberUtils;
import com.tsyc.tianshengyoucai.utils.Util;
import com.tsyc.tianshengyoucai.view.pop.SelfOrderPhonePop;
import com.tsyc.tianshengyoucai.vo.CouponListBean;
import com.tsyc.tianshengyoucai.vo.RedpacketVo;
import com.youth.xframe.utils.XDateUtils;
import com.youth.xframe.utils.XRegexUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author：van
 * CreateTime：2019/8/10
 * File description： 到店自取
 */
public class CreateSelfOrderFragment extends BaseFragment implements LocationSource, AMapLocationListener {


    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.aMapView)
    MapView aMapView;
    @BindView(R.id.tv_self_time)
    TextView tvSelfTime;
    @BindView(R.id.tv_level_phone)
    TextView tvLevelPhone;
    @BindView(R.id.cb_agreement)
    CheckBox cbAgreement;
    @BindView(R.id.tv_self_agreement)
    TextView tvSelfAgreement;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.iv_order_img)
    ImageView ivOrderImg;
    @BindView(R.id.rl_img)
    RelativeLayout rlImg;
    @BindView(R.id.tv_order_name)
    TextView tvOrderName;
    @BindView(R.id.tv_order_desc)
    TextView tvOrderDesc;
    @BindView(R.id.tv_order_price)
    TextView tvOrderPrice;
    @BindView(R.id.tv_order_count)
    TextView tvOrderCount;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_buy_number)
    TextView tvBuyNumber;
    @BindView(R.id.et_leave_msg)
    EditText etLeaveMsg;
    @BindView(R.id.tv_use_gold)
    TextView tvUseGold;
    @BindView(R.id.tv_choose_coupon)
    TextView tvChooseCoupon;
    @BindView(R.id.tv_shop_money)
    TextView tvShopMoney;
    @BindView(R.id.tv_post_money)
    TextView tvPostMoney;
    @BindView(R.id.tv_pay_money)
    TextView tvPayMoney;
    @BindView(R.id.tv_real_pay_count)
    TextView tvRealPayCount;
    @BindView(R.id.ctl_coupon)
    ConstraintLayout ctlcoupon;
    @BindView(R.id.cb_use_coupon)
    CheckBox cbUsecoupon;
    @BindView(R.id.tv_sub_money_title)
    TextView tvSubmoneyTitle;
    @BindView(R.id.tv_sub_money)
    TextView tvSubMoney;

    @BindView(R.id.ctl_coupon_selfs)
    ConstraintLayout ctlCouponSelfs;

    @BindView(R.id.tv_self_value)
    TextView tvSelfValue;

    @BindView(R.id.view_line_one)
    View viewlineone;
    @BindView(R.id.view_line_two)
    View viewlineTwo;
    @BindView(R.id.et_invite)
    TextView et_invite;


    private boolean isLocationSuccess = true;
    private AMap aMap;
    private final int STROKE_COLOR = Color.argb(180, 3, 145, 255);
    private final int FILL_COLOR = Color.argb(10, 0, 0, 180);
    private OnLocationChangedListener mListener;
    private AMapLocationClient mLocationClient;
    private int goodsCount;
    private double price;
    private String dateStr;
    private String timeStr;
    private String goodsId;
    private String goodsNum;
    private double payAmount;
    private SelfOrderBean mOrderInfoBean;
    /**
     * 红包请求码
     */
    private int REQUEST_CODE = 1001;
    private RedpacketVo mRedvo;
    private double mTotal;
    private double mValues;

    private double mStartValue;
    private List<CouponListBean> mCoupon_list;
    private CouponListBean mConponData;

    public static CreateSelfOrderFragment getInstance() {
        return new CreateSelfOrderFragment();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().removeAllStickyEvents();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_self_order;
    }

    @Override
    protected void loadData() {
        Bundle arguments = getArguments();
        if (null != arguments) {
            goodsId = arguments.getString("goods_id");
            goodsNum = arguments.getString("goods_num");
            String taoInviteCode = arguments.getString("taoInviteCode");
            if (null != taoInviteCode && !TextUtils.isEmpty(taoInviteCode)) {
                et_invite.setText(taoInviteCode);
            }
            requestGoodsData(goodsId, goodsNum);
        }

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        aMapView.onCreate(savedInstanceState);// 此方法必须重写
        initMap();
    }

    /**
     * 用户是否选用红包
     */
    private int SelectRedType;

    @SuppressLint("SetTextI18n")
    @OnClick({R.id.tv_dialog_subtract, R.id.tv_dialog_add, R.id.btn_commit, R.id.ctl_coupon_selfs, R.id.ctl_coupon, R.id.tv_self_time, R.id.tv_level_phone, R.id.tv_self_agreement})
    public void onViewClicked(View view) {
        String buyNum = tvBuyNumber.getText().toString();
        Bundle bundle = new Bundle();
        switch (view.getId()) {

            case R.id.tv_dialog_subtract:
                if (goodsCount > Integer.valueOf(goodsNum)) {
                    goodsCount--;

                    tvBuyNumber.setText(String.valueOf(goodsCount));
                    if (cbUsecoupon.isChecked()) {
                        mValues = goodsCount * price;
                        mStartValue = Arith.sub(mValues, Double.valueOf(mRedvo.getVoucher_price()));
                        tvRealPayCount.setText("￥" + String.valueOf(mStartValue));
                        tvPayMoney.setText("￥" + mStartValue);
                    } else {
                        mValues = goodsCount * price;
                        mStartValue = mValues;
                        tvRealPayCount.setText("￥" + NumberUtils.formatNum(String.valueOf(mValues)));
                        tvPayMoney.setText("￥" + NumberUtils.formatNum(String.valueOf(mValues)));
                    }
                    //优惠卷
                    if (mConponData != null) {
                        mStartValue = mStartValue - mConponData.getVoucher_price();
                        tvRealPayCount.setText("￥" + NumberUtils.formatNum(String.valueOf(mStartValue)));
                        tvPayMoney.setText("￥" + NumberUtils.formatNum(String.valueOf(mStartValue)));
                    }

                    tvShopMoney.setText("￥" + NumberUtils.formatNum(String.valueOf(goodsCount * price)));

                }
                break;
            case R.id.tv_dialog_add:
                goodsCount++;
                tvBuyNumber.setText(String.valueOf(goodsCount));
                if (cbUsecoupon.isChecked()) {
                    mValues = goodsCount * price;
                    mStartValue = Arith.sub(mValues, Double.valueOf(mRedvo.getVoucher_price()));
                    tvRealPayCount.setText("￥" + String.valueOf(mStartValue));
                    tvPayMoney.setText("￥" + String.valueOf(mStartValue));
                } else {
                    mValues = goodsCount * price;
                    mStartValue = mValues;
                    tvRealPayCount.setText("￥" + NumberUtils.formatNum(String.valueOf(mValues)));
                    tvPayMoney.setText("￥" + NumberUtils.formatNum(String.valueOf(mValues)));
                }
                //优惠卷
                if (mConponData != null) {
                    mStartValue = mStartValue - mConponData.getVoucher_price();
                    tvRealPayCount.setText("￥" + NumberUtils.formatNum(String.valueOf(mStartValue)));
                    tvPayMoney.setText("￥" + NumberUtils.formatNum(String.valueOf(mStartValue)));
                }
                tvShopMoney.setText("￥" + NumberUtils.formatNum(String.valueOf(goodsCount * price)));

                break;

            case R.id.tv_self_agreement:

                bundle.putString("type", "self");
                openActivity(SingleWebActivity.class, bundle);
                break;
            case R.id.btn_commit:
                if (Util.handleOnDoubleClick()) {
                    return;
                }
                commitOrder();
                break;

            case R.id.tv_self_time: //自提时间
                timePicker();
                break;
            case R.id.ctl_coupon_selfs: //优惠卷
                StringBuilder sb = new StringBuilder();
                if (mCoupon_list != null && !mCoupon_list.isEmpty())
                    mResultTo.startCouponList(getActivity(), mCoupon_list, "2");
                break;

            case R.id.tv_level_phone: //预留手机号
                levealPhonePop();
                break;
            case R.id.ctl_coupon: //用户使用
                if (cbUsecoupon.isChecked()) {
                    tvSubmoneyTitle.setVisibility(View.GONE);
                    tvSubMoney.setVisibility(View.GONE);
                    cbUsecoupon.setChecked(false);
                    if (mValues == 0) {
                        mStartValue = mTotal;
                        tvPayMoney.setText("￥" + Arith.keepTwoDecimalPlaces(mTotal));
                        tvRealPayCount.setText("￥" + Arith.keepTwoDecimalPlaces(mTotal));
                    } else {
                        tvPayMoney.setText("￥" + Arith.keepTwoDecimalPlaces(mValues));
                        tvRealPayCount.setText("￥" + Arith.keepTwoDecimalPlaces(mValues));
                    }
                } else {
                    tvSubmoneyTitle.setVisibility(View.VISIBLE);
                    tvSubMoney.setVisibility(View.VISIBLE);
                    cbUsecoupon.setChecked(true);
                    if (mValues == 0) {
                        double i = mTotal - mRedvo.getVoucher_price() < 0 ? 0.01 : mTotal - mRedvo.getVoucher_price();
                        mStartValue = i;
                        tvPayMoney.setText("￥" + Arith.keepTwoDecimalPlaces(i));
                        tvRealPayCount.setText("￥" + Arith.keepTwoDecimalPlaces(i));
                    } else {
                        mStartValue = mValues - mRedvo.getVoucher_price() < 0 ? 0.01 : mTotal - mRedvo.getVoucher_price();
                        tvPayMoney.setText("￥" + Arith.keepTwoDecimalPlaces(mStartValue));
                        tvRealPayCount.setText("￥" + Arith.keepTwoDecimalPlaces(mStartValue));
                    }
//                    double sub = Arith.sub(mValues, Double.valueOf(mRedvo.getVoucher_price()));
//                    String s = Arith.keepTwoDecimalPlaces(sub);
//                    tvPayMoney.setText("￥" + s);
//                    tvRealPayCount.setText("￥" + s);
                }
                break;
        }
    }

    private void commitOrder() {

        String leaveMsg = etLeaveMsg.getText().toString().trim();
        if (TextUtils.isEmpty(dateStr) || TextUtils.isEmpty(timeStr)) {
            XToast.normal("请选择自提时间");
            return;
        }
        String phone = tvLevelPhone.getText().toString().trim();

        if (TextUtils.isEmpty(phone)) {
            XToast.normal("请填写预留手机号");
            return;
        }
        if (!cbAgreement.isChecked()) {
            XToast.normal("请先同意到店自提协议");
            return;
        }
        String redId = "0";
        if (mRedvo != null) {
            if (cbUsecoupon.isChecked()) {
                redId = String.valueOf(mRedvo.getId());
            }
        }
        String inviteCode = et_invite.getText().toString().trim();
        Map<String, String> params = new HashMap<>();
        params.put("goods_id", goodsId);
        params.put("type", "2"); // 1.线上 2. 到店自提
        params.put("goods_number", String.valueOf(goodsNum));
        params.put("shop_note", leaveMsg);
        params.put("redpacket_id", redId);
        params.put("coupon_id", mConponData == null ? "" : String.valueOf(mConponData.getId()));
        params.put("address_id", "");
        params.put("ziti_time", timeStr);
        params.put("ziti_phone", phone);
        params.put("ziti_day", dateStr);
        params.put("share_code", inviteCode);
        loading(true);
        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.submit_order, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("到点支付 订单 " + response.body());
                dismiss();

//                CreateOrderSuccessBean successBean = FastJsonUtil.fromJson(response.body(), CreateOrderSuccessBean.class);
                NormalBean successBeans = GsonUtils.getGson(response.body().trim(), NormalBean.class);
                if (null == successBeans) {
                    XToast.normal("订单创建失败");
                    return;
                }
                if (!successBeans.getCode().equals(Constant.REQUEST_SUCCESS)) {
                    XToast.normal(successBeans.getMessage());
                    return;

                }
                CreateOrderSuccessBean successBean = GsonUtils.getGson(response.body().trim(), CreateOrderSuccessBean.class);
                String order_sn = successBean.getResult().getOrder_sn();
                Bundle bundle = new Bundle();
                int buyCount = Integer.parseInt(tvBuyNumber.getText().toString());
                String order_price = NumberUtils.formatNum(String.valueOf(buyCount * price));
                String price = String.valueOf(mStartValue < 0 ? 0.01 : mStartValue);
                XLog.e("金额   " + price);
                bundle.putString("order_sn", order_sn);
                bundle.putString("order_price", price.startsWith("￥") ? price.replace("￥", "") : price);
                bundle.putString("order_type", "underline");
                openActivity(OrderPayActivity.class, bundle);

            }

            @Override
            public void failed(Response<String> response) {
                dismiss();
              //  XLog.e("订单 " + response.getException().getMessage());
            }
        });
    }

    //选择时间
    private void timePicker() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2011, 1, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2069, 11, 28);
        TimePickerView pvTime = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                String dateAllStr = XDateUtils.date2String(date);
                dateStr = XDateUtils.date2Date(date);
                timeStr = XDateUtils.date2Time(date);

                XLog.e("" + dateAllStr + " = " + dateStr + " = " + timeStr);
                tvSelfTime.setText(dateAllStr);
            }
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setType(new boolean[]{true, true, true, true, true, false})
                .build();
        pvTime.show();
    }

    //预留手机号
    private void levealPhonePop() {
        SelfOrderPhonePop phonePop = new SelfOrderPhonePop(mContext);
        phonePop.showPopupWindow();

        EditText mEtContent = phonePop.findViewById(R.id.et_content);
        TextView mTvCancel = phonePop.findViewById(R.id.tv_cancel);
        TextView mTvConfig = phonePop.findViewById(R.id.tv_config);
        mTvCancel.setOnClickListener(v -> phonePop.dismiss());
        mTvConfig.setOnClickListener(v -> {
            String phone = mEtContent.getText().toString().trim();
            if (!XRegexUtils.checkMobile(phone)) {
                XToast.normal("请输入正确的手机号码");
                return;
            }
            tvLevelPhone.setText(phone);
            phonePop.dismiss();
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void refrehsData(ConponsEvent conponsEvent) {
        mConponData = conponsEvent.getBean();
        XLog.e("refrehsData" + mConponData.getId());
        tvSelfValue.setText(mConponData.getVoucher_price() + "");
        if (mRedvo != null && cbUsecoupon.isChecked()) {//有红包
            tvSubMoney.setText("￥" + (mRedvo.getVoucher_price() + mConponData.getVoucher_price()));
            if (mValues == 0) {
                double value = mTotal - mConponData.getVoucher_price() - mRedvo.getVoucher_price();
                if (value <= 0) {
                    value = 0.01;
                }
                this.mStartValue = value;
                tvPayMoney.setText("￥" + Arith.keepTwoDecimalPlaces(value));
                tvRealPayCount.setText("￥" + Arith.keepTwoDecimalPlaces(value));
            } else {
                double value = mValues - mConponData.getVoucher_price() - mRedvo.getVoucher_price();
                if (value <= 0) {
                    value = 0.01;
                }
                this.mStartValue = value;
                tvPayMoney.setText("￥" + Arith.keepTwoDecimalPlaces(value));
                tvRealPayCount.setText("￥" + Arith.keepTwoDecimalPlaces(value));
            }
        } else {//没红包
            tvSubMoney.setText("￥" + mConponData.getVoucher_price());
            if (mValues == 0) {
                double value = mTotal - mConponData.getVoucher_price();
                if (value <= 0) {
                    value = 0.01;
                }
                this.mStartValue = value;
                tvPayMoney.setText("￥" + Arith.keepTwoDecimalPlaces(value));
                tvRealPayCount.setText("￥" + Arith.keepTwoDecimalPlaces(value));

            } else {
                double value = mValues - mConponData.getVoucher_price();
                if (value <= 0) {
                    value = 0.01;
                }
                this.mStartValue = value;
                tvPayMoney.setText("￥" + Arith.keepTwoDecimalPlaces(value));
                tvRealPayCount.setText("￥" + Arith.keepTwoDecimalPlaces(value));

            }
        }
    }

    //订单信息
    private void requestGoodsData(String goodsId, String goodsNum) {

        loading(true);
        XLog.e("_______________" + goodsId);
        Map<String, String> params = new HashMap<>();
        params.put("goods_id", goodsId);
        params.put("type", "2");
        params.put("goods_num", goodsNum);
        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.goods_buy, params, new BaseRequestUtils.getRequestCallBack() {
            @SuppressLint("SetTextI18n")
            @Override
            public void success(Response<String> response) {
                XLog.e("线下订单信息" + response.body());
                dismiss();

//                SelfOrderBean orderInfoBean = FastJsonUtil.fromJson(response.body(), SelfOrderBean.class);
                NormalBean infom = GsonUtils.getGson(response.body().trim(), NormalBean.class);
                if (null == infom) {
                    XToast.normal("订单信息获取失败");
                    return;
                }
                if (!infom.getCode().equals(Constant.REQUEST_SUCCESS)) {
                    XToast.normal(infom.getMessage());
                    return;
                }
                mOrderInfoBean = GsonUtils.getGson(response.body().trim(), SelfOrderBean.class);
                SelfOrderBean.ResultBean resultBean = mOrderInfoBean.getResult();
                SelfOrderBean.ResultBean.StoreInfoBean store_info = resultBean.getStore_info();
                mCoupon_list = resultBean.getCoupon_list();

                SelfOrderBean.ResultBean.GoodsInfoBean goods_info = resultBean.getGoods_info();
                payAmount = resultBean.getPayamount();
                mTotal = resultBean.getGoods_info().getGoods_price() * Double.parseDouble(goodsNum);

                if (goods_info != null) {
                    String goodsImage = goods_info.getGoods_image();
                    String name = goods_info.getGoods_name();
                    goodsCount = goods_info.getGoods_num();
                    String spec = goods_info.getSpec();
                    price = goods_info.getGoods_price();

                    tvOrderName.setText(name);
                    tvOrderDesc.setText(spec);
                    tvOrderPrice.setText("￥" + price);
                    tvRealPayCount.setText("￥" + NumberUtils.formatNum(String.valueOf(price)));

                    tvBuyNumber.setText(String.valueOf(goodsCount));
                    ImageLoader.loadCenterCrop(mContext, goodsImage, ivOrderImg, 15);
                    tvShopMoney.setText("￥" + NumberUtils.formatNum(String.valueOf(price * Double.parseDouble(goodsNum))));
                }

                tvPayMoney.setText("￥" + NumberUtils.formatNum(String.valueOf(mTotal)));
                tvRealPayCount.setText("￥" + NumberUtils.formatNum(String.valueOf(payAmount)));
                if (mCoupon_list != null) {

                }
                mRedvo = resultBean.getRedpacket();
                mStartValue = mTotal;
                if (mRedvo != null) {
//                    ctlcoupon.setVisibility(View.VISIBLE);
                    showRed(true);
                    tvChooseCoupon.setText("￥" + mRedvo.getVoucher_price());
                    tvSubMoney.setText("￥" + mRedvo.getVoucher_price());
                } else {
                    showRed(false);
//                    ctlcoupon.setVisibility(View.GONE);
                }

                if (mCoupon_list == null || mCoupon_list.isEmpty()) {
                    showConpon(false);
//                    ctlCouponSelfs.setVisibility(View.GONE);
                } else {
                    showConpon(true);
//                    ctlCouponSelfs.setVisibility(View.VISIBLE);
                }


                if (store_info != null) {
                    setStoreInfo(store_info);
                }

            }

            @Override
            public void failed(Response<String> response) {
              //  XLog.e("订单信息" + response.getException().getMessage());

                XToast.normal(getString(R.string.service_error));
                dismiss();
            }
        });
    }

    private void showConpon(boolean show) {
        viewlineTwo.setVisibility(show ? View.VISIBLE : View.GONE);
        ctlCouponSelfs.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void showRed(boolean show) {
        viewlineone.setVisibility(show ? View.VISIBLE : View.GONE);
        ctlcoupon.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void setStoreInfo(SelfOrderBean.ResultBean.StoreInfoBean store_info) {

        String address = store_info.getAddress();
        String store_name = store_info.getStore_name();
        tvName.setText(address);
        if (!TextUtils.isEmpty(store_info.getLat()) && !TextUtils.isEmpty(store_info.getLng())) {

            double lat = Double.parseDouble(store_info.getLat());
            double lng = Double.parseDouble(store_info.getLng());
            if (!TextUtils.isEmpty(store_info.getLat()) && !TextUtils.isEmpty(store_info.getLng())) {
                CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(
                        new CameraPosition(new LatLng(lat, lng), 13, 0, 0));
                aMap.moveCamera(mCameraUpdate);

                LatLng latLng = new LatLng(lat, lng);
             /*   MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(store_name);
                markerOptions.visible(true);
                final Marker marker = aMap.addMarker(markerOptions);
                marker.showInfoWindow();*/
                MarkerOptions markerOptions = new MarkerOptions().icon(BitmapDescriptorFactory
                        .fromView(getBitmapView(mContext, store_name)));
                markerOptions.position(latLng);
                markerOptions.visible(true);
                final Marker marker = aMap.addMarker(markerOptions);
                marker.showInfoWindow();
            }
        }
    }


    public static View getBitmapView(Context context, String store_name) {
        LayoutInflater factory = LayoutInflater.from(context);
        View view = factory.inflate(R.layout.item_marker_name, null);
        TextView tv_title = view.findViewById(R.id.tv_name);
        tv_title.setText(store_name);
        return view;
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
                double latitude = aMapLocation.getLatitude();
                double longitude = aMapLocation.getLongitude();
                LatLng mLatLng = new LatLng(latitude, longitude);
                LatLonPoint myPoint = new LatLonPoint(latitude, longitude);

                String street = aMapLocation.getStreet();
                String description = aMapLocation.getDescription();
                String district = aMapLocation.getDistrict();
                String locationDetail = aMapLocation.getLocationDetail();
                String currLocation = district + street + description;
                if (isLocationSuccess) {
                    //setMarket(latitude, longitude);
                    String city = aMapLocation.getCity();
                    String address = aMapLocation.getAddress();

                    if (!TextUtils.isEmpty(city)) {
                        if (city.substring(city.length() - 1).equals("市")) {
                            String finalCity = city.substring(0, city.length() - 1);
                        }
                    }
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
    public void onDestroyView() {
        super.onDestroyView();
        aMapView.onDestroy();
    }

}
