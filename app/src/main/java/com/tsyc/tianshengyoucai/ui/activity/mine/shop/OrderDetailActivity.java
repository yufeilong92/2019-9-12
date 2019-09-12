package com.tsyc.tianshengyoucai.ui.activity.mine.shop;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
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
import com.tsyc.tianshengyoucai.listener.event.OrderDetailInEvent;
import com.tsyc.tianshengyoucai.listener.event.UnifiedNotifyEvent;
import com.tsyc.tianshengyoucai.model.adapter.EvaluateImageAdapter;
import com.tsyc.tianshengyoucai.model.bean.NormalResultBean;
import com.tsyc.tianshengyoucai.model.bean.OrderDetailBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.activity.home.GoodsDetailActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.address.AddressActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.ImageLoader;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author：van
 * CreateTime：2019/8/10
 * File description： 线上 订单 详情
 */
public class OrderDetailActivity extends BaseActivity {

    @BindView(R.id.tv_order_status)
    TextView tvOrderStatus;
    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.tv_auto_close)
    TextView tvAutoClose;
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
    @BindView(R.id.ctl_btm)
    ConstraintLayout ctlBtm;
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
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    @BindView(R.id.tv_total_pay_money)
    TextView tvTotalPayMoney;
    @BindView(R.id.ctl_center)
    ConstraintLayout ctlCenter;
    @BindView(R.id.tv_order_sn)
    TextView tvOrderSn;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.ctl_detail)
    ConstraintLayout ctlDetail;
    @BindView(R.id.tv_order_gray_status)
    TextView mTvOrderGrayStatus;
    @BindView(R.id.tv_order_red_status)
    TextView mTvOrderRedStatus;
    @BindView(R.id.tv_order_gray_logistic)
    TextView mTvOrderGrayLogistic;
    @BindView(R.id.tv_address_address)
    TextView tv_address_address;

    @BindView(R.id.tv_back_type)
    TextView tvBackType;
    @BindView(R.id.tv_back_time)
    TextView tvBackTime;
    @BindView(R.id.tv_back_money)
    TextView tvBackMoney;
    @BindView(R.id.tv_back_reason)
    TextView tvBackReason;
    @BindView(R.id.tv_user_say)
    TextView tvUserSay;
    @BindView(R.id.ctl_back)
    ConstraintLayout ctlBack;
    @BindView(R.id.ctl_top)
    ConstraintLayout ctlTop;
    @BindView(R.id.ctl_status)
    ConstraintLayout ctlStatus;

    @BindView(R.id.ctl_detail_histroy)
    ConstraintLayout ctlDetailHistory;
    @BindView(R.id.tv_buyer_apply_time)
    TextView tvBuyerApplyTime;
    @BindView(R.id.tv_shoper_agree_time)
    TextView tvShoperAgreeTime;
    @BindView(R.id.tv_shoper_agree_time_tip)
    TextView tvShoperAgreeTimeTip;

    @BindView(R.id.root_title)
    View root_title;
    @BindView(R.id.tv_curr_status)
    TextView tvCurrStatus;
    @BindView(R.id.tv_curr_status_detail)
    TextView tvCurrStatusDetail;
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

    @BindView(R.id.tv_post)
    TextView tv_post;
    @BindView(R.id.tv_pay_type)
    TextView tvPayType;
    @BindView(R.id.ctl_goods_info)
    ConstraintLayout ctl_goods_info;

    private String order_sn;
    private String order_amount;
    private String order_sn1;
    private int orderStatus;
    private String orderId;
    private String url;
    private String goodsImage;
    private String goods_name;
    private EvaluateImageAdapter imageAdapter;
    private String mType;
    private int goods_id = -1;


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void initView() {
        registerEventBus(this);
        Eyes.translucentStatusBar(this, true);
        Bundle extra = getIntent().getBundleExtra(Constant.bundleExtra);
        if (null != extra) {
            orderId = extra.getString("goods_id");
            order_sn = extra.getString("order_sn");
            mType = extra.getString("type");
            requestGoodsInfo(orderId);
        }
        mTvTitle.setText("退款详情");

        rvReason.setLayoutManager(new GridLayoutManager(mContext, 3));
        rvReason.setHasFixedSize(true);

        imageAdapter = new EvaluateImageAdapter();
        rvReason.setAdapter(imageAdapter);

        if (orderStatus == 10) {
            ctlAddress.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putString("type", "type");
                openActivity(AddressActivity.class, bundle, 200);
            });
        }
        if (!StringUtil.isEmpty(mType) && mType.equals("3")) {
            ctlAddress.setVisibility(View.GONE);
        }


    }

    @OnClick({R.id.rl_back, R.id.tv_copy_sn, R.id.tv_order_gray_status, R.id.ctl_goods_info,
            R.id.tv_order_red_status, R.id.tv_order_gray_logistic})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {

            case R.id.rl_back:
                finish();
                break;

            case R.id.ctl_goods_info:
                if (goods_id == -1)
                    return;

                bundle.putString("goods_id", String.valueOf(goods_id));
                openActivity(GoodsDetailActivity.class, bundle);
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

            case R.id.tv_order_gray_logistic:
                bundle.putString("goods_id", orderId);
                openActivity(LookLogisticsActivity.class, bundle);
                break;
        }
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
                XLog.e("购买界面信息" + response.body());
                OrderDetailBean orderPayBean = FastJsonUtil.fromJson(response.body(), OrderDetailBean.class);
                if (null == orderPayBean) {
                    return;
                }
                if (!orderPayBean.getCode().equals(Constant.REQUEST_SUCCESS) || orderPayBean.getResult() == null) {
                    XToast.normal(orderPayBean.getMessage());
                    return;
                }

                OrderDetailBean.ResultBean result = orderPayBean.getResult();

                OrderDetailBean.ResultBean.StatusInfoBean status_info = result.getStatus_info();
                String status_text = status_info.getStatus_text();
                String status_desc = status_info.getStatus_desc();

                orderStatus = status_info.getOrder_state();
                tvOrderStatus.setText(status_text);
                tvAutoClose.setText(status_desc);
                tvCurrStatus.setText(status_text);

                //


                //退款
                OrderDetailBean.ResultBean.LineInfoBean line_info = result.getLine_info();
                if (line_info != null) {
                    String add_time = line_info.getAdd_time();

                    String refund_sn = line_info.getRefund_sn();
                    String refund_time = line_info.getRefund_time();
                    String seller_time = line_info.getSeller_time(); // 审核时间

                    tvStartTime.setText("创建时间：" + add_time);
                    tvBuyerApplyTime.setText(refund_time);
                    if (TextUtils.isEmpty(seller_time)) {
                        tvShoperAgreeTimeTip.setVisibility(View.GONE);
                        tvShoperAgreeTime.setVisibility(View.GONE);
                    } else {
                        tvShoperAgreeTime.setText(seller_time);
                    }
                }

                OrderDetailBean.ResultBean.PayInfoBean pay_info = result.getPay_info();
                if (pay_info != null) {
                    String goods_amount = pay_info.getGoods_amount();
                    order_amount = pay_info.getOrder_amount();
                    String payment_name = pay_info.getPayment_name(); // 微信 支付宝
                    String postage = pay_info.getPostage();
                    tv_post.setText("￥"+postage);
                    tvPayType.setText(payment_name);
                    tvTotalMoney.setText("￥" + goods_amount);
                    tvTotalPayMoney.setText("￥" + order_amount);
                    tvBagTip.setText("￥" + pay_info.getRedpacket_discount() + "元");
                    tvCouponTip.setText("￥" + pay_info.getCoupon_discount() + "元");
                }

                String liuyan = result.getLiuyan();
                tvUserSay.setText("留言备注：" + liuyan);

                OrderDetailBean.ResultBean.ReceiveInfoBean receive_info = result.getReceive_info();
                if (orderStatus == 10) {
                    if (receive_info == null) {
                        ctlHasAddress.setVisibility(View.GONE);
                        tv_address_address.setVisibility(View.VISIBLE);
                        ctlAddress.setOnClickListener(v -> {
                            Bundle bundle = new Bundle();
                            bundle.putString("type", "type");
                            openActivity(AddressActivity.class, bundle, 200);
                        });
                    } else {
                        ctlHasAddress.setVisibility(View.VISIBLE);
                        String address = receive_info.getArea_info() + receive_info.getAddress();
                        String mob_phone = receive_info.getMob_phone();
                        String true_name = receive_info.getTrue_name();
                        tvAddressName.setText(true_name);
                        tvAddressPhone.setText(mob_phone);
                        tvAddressDefault.setText(address);
                    }
                } else {
                    if (receive_info == null) {
                        ctlHasAddress.setVisibility(View.GONE);
                        ctlAddress.setVisibility(View.GONE);
                    } else {
                        ctlHasAddress.setVisibility(View.VISIBLE);
                        String address = receive_info.getArea_info() + receive_info.getAddress();
                        String mob_phone = receive_info.getMob_phone();
                        String true_name = receive_info.getTrue_name();
                        tvAddressName.setText(true_name);
                        tvAddressPhone.setText(mob_phone);
                        tvAddressDefault.setText(address);
                    }

                }
                List<OrderDetailBean.ResultBean.OrdergoodsBean> ordergoods = result.getOrdergoods();
                if (ordergoods != null && ordergoods.size() > 0) {
                    goodsImage = ordergoods.get(0).getGoods_image();
                    goods_id = ordergoods.get(0).getGoods_id();
                    goods_name = ordergoods.get(0).getGoods_name();
                    int goods_num = ordergoods.get(0).getGoods_num();
                    String goods_price = ordergoods.get(0).getGoods_price();
                    String spec = ordergoods.get(0).getGoods_guige();

                    tvOrderName.setText(goods_name);
                    tvOrderCount.setText(String.valueOf("x" + goods_num));
                    tvOrderDesc.setText("规格：" + spec);
                    tvOrderPrice.setText(String.valueOf("￥" + goods_price));

                    ImageLoader.loadCenterCrop(mContext, goodsImage, ivOrderImg, 20);
                    order_sn1 = result.getOrder_sn();
                    tvOrderSn.setText("订单编号：" + order_sn1);

                }

                OrderDetailBean.ResultBean.RefundBean refund = result.getRefund(); // 退款
                if (null != refund) {
                    String add_time1 = refund.getAdd_time();
                    String refund_type = refund.getRefund_type();
                    String reason_info = refund.getReason_info();
                    String refund_amount = refund.getRefund_amount();
                    tvBackMoney.setText("￥ " + refund_amount);
                    tvBackTime.setText(add_time1);
                    tvBackReason.setText(reason_info);
                    tvBackType.setText(refund_type);
                    List<String> pic_info = refund.getPic_info();
                    if (null != pic_info && pic_info.size() > 0) {
                        imageAdapter.setNewData(pic_info);
                        imageAdapter.setListener((adapterPosition, item) -> mResultTo.startEvaluateImager(mContext, adapterPosition, pic_info));
                    }
                }

                mTvOrderGrayStatus.setVisibility(View.VISIBLE);
                mTvOrderRedStatus.setVisibility(View.VISIBLE);
                switch (orderStatus) {
                    case -1:
                    case 0:
                        ctlBtm.setVisibility(View.GONE);
                        break;

                    case 10:
                        mTvOrderGrayStatus.setText("取消订单");
                        mTvOrderRedStatus.setText("去付款");
                        break;

                    case 20:
                        mTvOrderGrayStatus.setText("申请退货");
                        mTvOrderRedStatus.setVisibility(View.GONE);
                        break;

                    case 30:
                        mTvOrderGrayLogistic.setVisibility(View.VISIBLE);
                        mTvOrderGrayStatus.setText("申请退货");
                        mTvOrderGrayLogistic.setText("查看物流");
                        mTvOrderRedStatus.setText("确认收货");
                        break;
                    case 40:
                        mTvOrderGrayStatus.setText("删除订单");
                        mTvOrderRedStatus.setText("去评价");
                        break;
                    case 50:
                        mTvOrderRedStatus.setVisibility(View.GONE);
                        mTvOrderGrayStatus.setText("已完成");
                        break;

                    case 100:
                        mTvOrderGrayStatus.setText("已完成");
                        mTvOrderRedStatus.setVisibility(View.GONE);
                        break;

                    case 101:
                        ctlBtm.setVisibility(View.GONE);
                        ctlBack.setVisibility(View.VISIBLE);
                        statusOfBackMoney();
                        tvCurrStatusDetail.setText("您申请了维权，等待商家审核");
                        break;

                    case 102:
                        mTvOrderGrayStatus.setVisibility(View.GONE);
                        mTvOrderRedStatus.setText("去发货");
                        statusOfBackMoney();
                        tvCurrStatusDetail.setText("商家已同意，去发货");
                        break;

                    case 103:
                        mTvOrderRedStatus.setVisibility(View.GONE);
                        mTvOrderGrayStatus.setText("等待商家收货");
                        statusOfBackMoney();
                        tvCurrStatusDetail.setText("货已发出，等待商家收货");
                        break;

                    case 104:
                        mTvOrderRedStatus.setVisibility(View.GONE);
                        mTvOrderGrayStatus.setText("等待打款");
                        statusOfBackMoney();
                        tvCurrStatusDetail.setText("商家已收货，等待退款");
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
        ctlTop.setVisibility(View.GONE);
        ctlDetail.setVisibility(View.GONE);
        ctlStatus.setVisibility(View.VISIBLE);
        ctlDetailHistory.setVisibility(View.VISIBLE);

    }

    // 灰按钮点击事件
    private void grayBtnOrderStatus() {
        Bundle bundle = new Bundle();
        switch (orderStatus) {
            case 10:// 待付款 取消订单

                dealOrder("quxiao_order");
                break;
            case 20: // 待发货 申请退货
                bundle.putString("order_id", orderId);
                bundle.putString("order_in", "online_order");
                openActivity(ApplyBackMoneyActivity.class, bundle);
                break;
            case 30:  // 待收货 申请退货
                bundle.putString("order_id", orderId);
                bundle.putString("order_in", "online_order");
                openActivity(ApplyBackMoneyActivity.class, bundle);
                break;

            case 40:  // 删除订单
                dealOrder("delete");
                break;


            case 101:
                // orderType = "等待商家审核";

                break;
            case 103:
                // orderType = "等待商家收货";

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
                bundle.putString("order_type", "online");
                bundle.putString("order_price", String.valueOf(order_amount));
                bundle.putString("order_in", "online_order");
                openActivity(OrderPayActivity.class, bundle);

                break;

            case 30: // 确认收货
                dealOrder("confirm_receive");
                break;
            case 40: // 去评价
                bundle.putString("goods_image", goodsImage);
                bundle.putString("goods_name", goods_name);
                bundle.putString("order_id", orderId);
                bundle.putString("order_in", "online_order");
                openActivity(EvaluateActivity.class, bundle);
                break;
            case 102: // 待退货
                bundle.putString("goods_id", orderId);
                bundle.putString("order_in", "online_order");
                openActivity(SendOrderActivity.class, bundle);
                break;

        }
    }

    private void dealOrder(String type) {

        if (type.equals("confirm_receive")) {
            url = UrlManager.confirm_receive;
        } else if (type.equals("quxiao_order")) {
            url = UrlManager.cancel_order;
        } else if (type.equals("delete")) {
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

                EventBus.getDefault().post(new UnifiedNotifyEvent(Constant.APPLY_BACK_MONEY));
                finish();
            }

            @Override
            public void failed(Response<String> response) {
                dismiss();
                XToast.normal(getString(R.string.service_error));

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            String mobile = data.getStringExtra("mobile");
            String true_name = data.getStringExtra("true_name");
            String address = data.getStringExtra("address");
            tv_address_address.setVisibility(View.GONE);
            ctlAddress.setVisibility(View.VISIBLE);
            ctlHasAddress.setVisibility(View.VISIBLE);
            tvAddressName.setText(true_name);
            tvAddressPhone.setText(mobile);
            tvAddressDefault.setText(address);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void evaluateSuccess(OrderDetailInEvent event) {
        String msg = event.getMsg();
        if (msg.equals("ok")) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterEventBus(this);
    }
}
