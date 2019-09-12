package com.tsyc.tianshengyoucai.ui.activity.mine.shop;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.flyn.Eyes;
import com.tsyc.tianshengyoucai.model.adapter.EvaluateImageAdapter;
import com.tsyc.tianshengyoucai.model.bean.StoreOrderDetailBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.activity.home.GoodsDetailActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.ImageLoader;
import com.youth.xframe.utils.XRegexUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * author：van
 * CreateTime：2019/8/12
 * File description：收银台 订单 详情  线下
 */
@RuntimePermissions
public class StoreCashierOrderDetailActivity extends BaseActivity {

    @BindView(R.id.tv_order_status)
    TextView tvOrderStatus;
    @BindView(R.id.tv_auto_close)
    TextView tvAutoClose;
    @BindView(R.id.ctl_top)
    ConstraintLayout ctlTop;
    @BindView(R.id.tv_address_address)
    TextView tvAddressAddress;
    @BindView(R.id.tv_address_name)
    TextView tvAddressName;
    @BindView(R.id.tv_address_phone)
    TextView tvAddressPhone;
    @BindView(R.id.tv_address_default)
    TextView tvAddressDefault;
    @BindView(R.id.ctl_has_address)
    ConstraintLayout ctlHasAddress;
    @BindView(R.id.ctl_address)
    ConstraintLayout ctlAddress;
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
    @BindView(R.id.tv_apply_name)
    TextView tvApplyName;
    @BindView(R.id.tv_pay_type)
    TextView tvPayType;
    @BindView(R.id.tv_apply_time)
    TextView tvApplyTime;
    @BindView(R.id.tv_post_money)
    TextView tvPostMoney;
    @BindView(R.id.tv_apply_money)
    TextView tvApplyMoney;
    @BindView(R.id.tv_pay_money)
    TextView tvPayMoney;
    @BindView(R.id.ctl_order)
    ConstraintLayout ctlOrder;
    @BindView(R.id.tv_apply_name_ip)
    TextView tvApplyNameIp;

    @BindView(R.id.tv_back_type)
    TextView tvBackType;
    @BindView(R.id.tv_apply_time_tip)
    TextView tvApplyTimeTip;
    @BindView(R.id.tv_back_time)
    TextView tvBackTime;
    @BindView(R.id.tv_apply_money_tip)
    TextView tvApplyMoneyTip;
    @BindView(R.id.tv_back_money)
    TextView tvBackMoney;
    @BindView(R.id.tv_apply_reason)
    TextView tvApplyReason;
    @BindView(R.id.tv_back_reason)
    TextView tvBackReason;
    @BindView(R.id.rv_reason)
    RecyclerView rvReason;
    @BindView(R.id.ctl_back)
    ConstraintLayout ctlBack;
    @BindView(R.id.tv_buyer_name)
    TextView tv_buyer_name;
    @BindView(R.id.tv_contact)
    TextView tv_contact;
    @BindView(R.id.iv_call)
    ImageView iv_call;

    @BindView(R.id.tv_order_buyer)
    TextView tvOrderBuyer;
    @BindView(R.id.tv_order_sn)
    TextView tvOrderSn;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_pay_time)
    TextView tvPayTime;
    @BindView(R.id.tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.tv_rec_time)
    TextView tvRecTime;
    @BindView(R.id.tv_user_say)
    TextView tvUserSay;

    @BindView(R.id.ctl_detail)
    ConstraintLayout ctlDetail;
    @BindView(R.id.tv_order_gray_status)
    TextView mTvOrderGrayStatus;
    @BindView(R.id.tv_order_red_status)
    TextView mTvOrderRedStatus;

    @BindView(R.id.tv_bag_tip)
    TextView tvBagTip;
    @BindView(R.id.tv_coupon_tip)
    TextView tvCouponTip;

    @BindView(R.id.ctl_goods_info)
    ConstraintLayout ctl_goods_info;

    private String orderId;
    private String order_sn;
    private EvaluateImageAdapter imageAdapter;
    private String phone;
    private int goods_id = -1;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_store_cashier_order_detail;
    }

    @Override
    public void initView() {

        Eyes.translucentStatusBar(this, false);

        Bundle extra = getIntent().getBundleExtra(Constant.bundleExtra);
        if (null != extra) {
            orderId = extra.getString("order_id");
            requestOrderDetail(orderId);
        }
        rvReason.setLayoutManager(new GridLayoutManager(mContext, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvReason.setHasFixedSize(true);

        imageAdapter = new EvaluateImageAdapter();
        rvReason.setAdapter(imageAdapter);

    }

    @OnClick({R.id.rl_back, R.id.tv_copy_sn, R.id.iv_call,R.id.ctl_goods_info, R.id.tv_order_gray_status, R.id.tv_order_red_status})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_copy_sn:
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData mClipData = ClipData.newPlainText("Label", order_sn);
                cm.setPrimaryClip(mClipData);
                XToast.normal("复制成功");

                break;
            case R.id.tv_order_gray_status:

                break;
            case R.id.tv_order_red_status:

                break;
            case R.id.ctl_goods_info:
                if (goods_id == -1) return;
                Bundle bundle = new Bundle();
                bundle.putString("goods_id",String.valueOf(goods_id));
                openActivity(GoodsDetailActivity.class,bundle);
                break;
            case R.id.iv_call: // 打电话
                StoreCashierOrderDetailActivityPermissionsDispatcher.onSkipIntentWithPermissionCheck(this);
                break;
        }
    }


    private void requestOrderDetail(String orderId) {
        Map<String, String> params = new HashMap<>();
        params.put("order_id", orderId);
        loading(false);
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.storeDetail, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("店铺订单详情 " + response.body());
                StoreOrderDetailBean orderDetailBean = FastJsonUtil.fromJson(response.body(), StoreOrderDetailBean.class);
                dismiss();
                if (orderDetailBean == null) {
                    return;
                }

                if (!orderDetailBean.getCode().equals(Constant.REQUEST_SUCCESS) || orderDetailBean.getResult() == null) {
                    XToast.normal(orderDetailBean.getMessage());
                    return;
                }

                StoreOrderDetailBean.ResultBean result = orderDetailBean.getResult();

                order_sn = result.getOrder_sn();
                int order_id = result.getOrder_id();
                int order_type = result.getOrder_type();
                String buy_name = result.getBuy_name();
                tv_buyer_name.setText(buy_name);
                String liuYan = result.getLiuyan();

                ctlAddress.setVisibility(View.GONE);
                tvOrderSn.setText("订单编号：" + order_sn);
                tvUserSay.setText("留言备注：" + liuYan);

                List<StoreOrderDetailBean.ResultBean.OrdergoodsBean> order_goods = result.getOrdergoods();//订单信息
                StoreOrderDetailBean.ResultBean.StatusInfoBean status_info = result.getStatus_info();//订单状态
                StoreOrderDetailBean.ResultBean.PayInfoBean pay_info = result.getPay_info(); //支付信息
                StoreOrderDetailBean.ResultBean.ReceiveInfoBean receive_info = result.getReceive_info(); // 收款人信息
                StoreOrderDetailBean.ResultBean.LineInfoBean line_info = result.getLine_info(); //时间

                StoreOrderDetailBean.ResultBean.RefundBean refund = result.getRefund();
                if (refund!=null){
                    String refund_type = refund.getRefund_type();
                    String add_time = refund.getAdd_time();
                     phone = refund.getPhone();
                    String refund_amount = refund.getRefund_amount();
                    String reason_info = refund.getReason_info();
                    List<String> pic_info = refund.getPic_info();
                    tvBackMoney.setText(refund_amount);
                    tvBackTime.setText(add_time);
                    tvBackType.setText(refund_type);
                    tv_contact.setText(phone);
                    tvBackReason.setText(reason_info);
                    if (null != pic_info && pic_info.size() > 0) {
                        imageAdapter.setNewData(pic_info);
                    }
                }

                if (order_goods != null && order_goods.size() > 0) {
                    String goods_guide = order_goods.get(0).getGoods_guige(); // desc
                    String goodsImage = order_goods.get(0).getGoods_image();
                    goods_id = order_goods.get(0).getGoods_id();
                    String name = order_goods.get(0).getGoods_name();
                    String price = order_goods.get(0).getGoods_price();
                    int goods_num = order_goods.get(0).getGoods_num();

                    tvOrderName.setText(name);
                    tvOrderDesc.setText("规格："+goods_guide);
                    tvOrderPrice.setText("￥" + price);
                    tvOrderCount.setText("x" + goods_num);
                    ImageLoader.loadCenterCrop(mContext, goodsImage, ivOrderImg, 20);
                }

                if (status_info != null) {
                    int order_state = status_info.getOrder_state();
                    String status_desc = status_info.getStatus_desc();
                    String status_text = status_info.getStatus_text();

                    tvOrderStatus.setText(status_text);
                    tvAutoClose.setText(status_desc);

                    orderStatus(order_state, result);
                }

                if (pay_info != null) {
                    String goods_amount = pay_info.getGoods_amount();
                    String order_amount = pay_info.getOrder_amount();
                    String payment_name = pay_info.getPayment_name();

                    tvPayType.setText(payment_name);
                    tvPostMoney.setText("￥" + goods_amount);
                    tvPayMoney.setText("￥" + order_amount);

                    tvBagTip.setText("￥" + pay_info.getRedpacket_discount()+"元");
                    tvCouponTip.setText("￥" + pay_info.getCoupon_discount()+"元");
                }

                if (receive_info != null) {
                    String address = receive_info.getAddress();
                    String area_info = receive_info.getArea_info();
                    String mob_phone = receive_info.getMob_phone();
                    String true_name = receive_info.getTrue_name();

                    tvAddressName.setText(true_name);
                    tvAddressPhone.setText(mob_phone);
                    tvAddressDefault.setText(area_info + "" + address);
                    tvOrderBuyer.setText("买家昵称：" + true_name);
                }

                if (line_info != null) {
                    String add_time = line_info.getAdd_time();
                    String payment_time = line_info.getPayment_time();

                    tvCreateTime.setText("创建时间：" + add_time);
                    tvPayTime.setText("支付时间：" + payment_time);
                }

            }

            @Override
            public void failed(Response<String> response) {
                dismiss();
            }
        });
    }

    private void orderStatus(int order_state, StoreOrderDetailBean.ResultBean result) {
        StoreOrderDetailBean.ResultBean.LineInfoBean line_info = result.getLine_info();
        StoreOrderDetailBean.ResultBean.RefundBean refund = result.getRefund();
        StoreOrderDetailBean.ResultBean.TakingInfoBean taking_info = result.getTaking_info();

        switch (order_state) {
            case 101:
            case 102:
            case 103:
            case 104:
                ctlBack.setVisibility(View.VISIBLE);
                if (line_info != null) {
                    tvStartTime.setVisibility(View.VISIBLE);
                    String refund_sn = line_info.getRefund_sn();// 退编号
                    String add_time = line_info.getAdd_time();
                    String refund_time = line_info.getRefund_time();
                    tvStartTime.setText("退款时间: " + add_time);
                    tvPayTime.setText("退款时间: " + refund_time);
                    tvCreateTime.setText("退款编号: " + refund_sn);
                    String seller_time = line_info.getSeller_time();
                    if (!TextUtils.isEmpty(seller_time)) {
                        tvRecTime.setText("退款时间: " + seller_time);
                    }
                }
                if (refund != null) {
                    phone = refund.getPhone();
                    List<String> pic_info = refund.getPic_info();
                    String reason_info = refund.getReason_info();
                    String refund_amount = refund.getRefund_amount();
                    String refund_type = refund.getRefund_type();
                    String add_time = refund.getAdd_time();

                    tv_contact.setText(phone);
                    tvBackTime.setText(add_time);
                    tvBackType.setText(refund_type);
                    tvBackMoney.setText("￥ " + refund_amount);
                    tvBackReason.setText(reason_info);
                    if (null != pic_info && pic_info.size() > 0) {
                        imageAdapter.setNewData(pic_info);
                    }
                }

                break;

            case 20:
            case 40:
            case 50:
                ctlBack.setVisibility(View.VISIBLE);

                tvApplyNameIp.setVisibility(View.GONE);
                tvBackType.setVisibility(View.GONE);
                tvApplyMoneyTip.setVisibility(View.GONE);
                tvBackMoney.setVisibility(View.GONE);
                tvApplyReason.setVisibility(View.GONE);
                tvBackReason.setVisibility(View.GONE);

                if (taking_info != null) {
                    String ziti_time = taking_info.getZiti_time();
                    phone = taking_info.getZiti_phone();
                    tvApplyTimeTip.setText("预约时间: ");
                    tvBackTime.setText(ziti_time);
                    tv_contact.setText(phone);
                }

                break;
        }
    }


    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        StoreCashierOrderDetailActivityPermissionsDispatcher.onSkipIntentWithPermissionCheck(this);
    }

    @NeedsPermission(Manifest.permission.CALL_PHONE)
    void onSkipIntent() {
        if (!XRegexUtils.checkMobile(phone)) {
            XToast.normal("店铺号码有误，拨号失败");
            return;
        }
        Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));//直接拨打电话

        if (ActivityCompat.checkSelfPermission(StoreCashierOrderDetailActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(StoreCashierOrderDetailActivity.this, new String[]{"Manifest.permission.CALL_PHONE"}, 0);
            return;
        }
        startActivity(dialIntent);
    }


    @OnShowRationale(Manifest.permission.CALL_PHONE)
    void onSkipIntentShow(final PermissionRequest request) {
        request.proceed();
    }

    @OnNeverAskAgain(Manifest.permission.CALL_PHONE)
    void onSkipIntentNever() {
        //Toast.makeText(getActivity(), "当前功能需要在设置-应用-千客家-权限中开启打电话权限。", Toast.LENGTH_LONG).show();
        XToast.normal("当前功能需要在设置-应用-" + getString(R.string.app_name) + "-权限中开启打电话权限。");
    }

}
