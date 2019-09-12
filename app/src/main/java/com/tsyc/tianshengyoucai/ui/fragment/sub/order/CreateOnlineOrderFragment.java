package com.tsyc.tianshengyoucai.ui.fragment.sub.order;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.Eventbuss.ConponEvent;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.model.bean.CreateOrderInfoBean;
import com.tsyc.tianshengyoucai.model.bean.CreateOrderSuccessBean;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.activity.mine.address.AddressActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.OrderPayActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseFragment;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.ImageLoader;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.utils.Util;
import com.tsyc.tianshengyoucai.vo.CouponListBean;
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
 * File description： 创建上线物流订单
 */
public class CreateOnlineOrderFragment extends BaseFragment {


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
    @BindView(R.id.tv_order_name)
    TextView tvOrderName;
    @BindView(R.id.tv_order_desc)
    TextView tvOrderDesc;
    @BindView(R.id.tv_order_price)
    TextView tvOrderPrice;
    @BindView(R.id.tv_order_count)
    TextView tvOrderCount;
    @BindView(R.id.et_leave_msg)
    EditText etLeaveMsg;
    @BindView(R.id.tv_back_type)
    TextView tvTotalMoney;
    @BindView(R.id.tv_back_time)
    TextView tvPostMoney;
    @BindView(R.id.tv_back_money)
    TextView tvPayAmount;
    @BindView(R.id.tv_real_money_count)
    TextView tvRealMoneyCount;
    @BindView(R.id.ctl_coupon)
    ConstraintLayout ctlCoupon;
    @BindView(R.id.tv_choose_onlinecoupon)
    TextView tvChooseOnlineCoupon;
    @BindView(R.id.cb_use_coupon)
    CheckBox cbUseCoupon;
    @BindView(R.id.iv_order_img)
    ImageView iv_order_img;
    @BindView(R.id.tv_sub_money_title)
    TextView tvSubMoneyTitle;
    @BindView(R.id.tv_sub_money)
    TextView tvSubMoney;

    @BindView(R.id.et_invite)
    TextView etInvite;

    @BindView(R.id.ctl_coupon_s)
    ConstraintLayout ctlCoupons;
    @BindView(R.id.tv_online_value)
    TextView tvOnlinevalue;
    @BindView(R.id.view_line_one)
    View viewLineOne;
    @BindView(R.id.view_line_two)
    View viewLineTwo;
    private String goodsId;
    private String goodsNum;
    private String addressId;
    private double payAmount;
    private double total;
    private CreateOrderInfoBean.ResultBean.RedpacketBean mRedPacket;
    private double mStartValue;
    private List<CouponListBean> mCoupon_list;
    private CouponListBean mCouponVo;

    public static CreateOnlineOrderFragment getInstance() {
        return new CreateOnlineOrderFragment();
    }

    private int RREQUESTCODE = 1005;

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_create_online_order;
    }

    @Override
    protected void loadData() {


        Bundle arguments = getArguments();
        if (null != arguments) {
            goodsId = arguments.getString("goods_id");
            goodsNum = arguments.getString("goods_num");
            String goodsSpec = arguments.getString("goods_spec");
            String taoInviteCode = arguments.getString("taoInviteCode");
            if (null != taoInviteCode && !TextUtils.isEmpty(taoInviteCode)) {
                etInvite.setText(taoInviteCode);
            }
            requestGoodsData(goodsId, goodsNum);
        }

        ctlAddress.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("type", "type");
            openActivity(AddressActivity.class, bundle, 200);
        });
        initEvent();
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

    private void initEvent() {

        ctlCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbUseCoupon.isChecked()) {
                    cbUseCoupon.setChecked(false);
                    mStartValue = payAmount;
                    tvPayAmount.setText("￥ " + payAmount);
                    tvRealMoneyCount.setText("￥ " + payAmount);
                    tvSubMoneyTitle.setVisibility(View.GONE);
                    tvSubMoney.setVisibility(View.GONE);

                } else {
                    tvSubMoneyTitle.setVisibility(View.VISIBLE);
                    tvSubMoney.setVisibility(View.VISIBLE);
                    double com = payAmount - mRedPacket.getVoucher_price();
                    if (com <= 0) {
                        com = 0.01;
                    }
                    mStartValue = com;
                    tvPayAmount.setText("￥ " + String.valueOf(com));
                    tvRealMoneyCount.setText("￥ " + String.valueOf(com));
                    cbUseCoupon.setChecked(true);

                }

            }
        });
        ctlCoupons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCoupon_list != null && !mCoupon_list.isEmpty()) {
                    mResultTo.startCouponList(getActivity(), mCoupon_list, "1");
                }
            }
        });

    }

    private void requestGoodsData(String goodsId, String goodsNum) {

        loading(true);
        Map<String, String> params = new HashMap<>();
        params.put("goods_id", goodsId);
        params.put("type", "1");
        params.put("goods_num", goodsNum);
        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.goods_buy, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("订单信息" + response.body());
                dismiss();

                NormalBean orderInfoBeans = GsonUtils.getGson(response.body(), NormalBean.class);
                if (orderInfoBeans.getCode().equals("100")) {
                    T.showToast(mContext, orderInfoBeans.getMessage());
                    return;
                }
                CreateOrderInfoBean orderInfoBean = GsonUtils.getGson(response.body(), CreateOrderInfoBean.class);
                CreateOrderInfoBean.ResultBean resultBean = orderInfoBean.getResult();
                List<CreateOrderInfoBean.ResultBean.AddressInfoBean> address_info = resultBean.getAddress_info();
                mCoupon_list = resultBean.getCoupon_list();
                CreateOrderInfoBean.ResultBean.GoodsInfoBean goods_info = resultBean.getGoods_info();
                mRedPacket = resultBean.getRedpacket();
                payAmount = resultBean.getPayamount();
                total = resultBean.getTotal();
                double postage = resultBean.getPostage();

                tvTotalMoney.setText("￥ " + total);
                tvPostMoney.setText("￥ " + postage);
                tvPayAmount.setText("￥ " + payAmount);
                mStartValue = payAmount;
                tvRealMoneyCount.setText("￥ " + payAmount);
                if (address_info != null && address_info.size() != 0) {
                    String address = address_info.get(0).getAddress();
                    String mob_phone = address_info.get(0).getMob_phone();
                    String true_name = address_info.get(0).getTrue_name();
                    addressId = String.valueOf(address_info.get(0).getAddress_id());
                    tvAddressName.setText(true_name);
                    tvAddressPhone.setText(mob_phone);
                    tvAddressDefault.setText(address);

                } else {
                    ctlHasAddress.setVisibility(View.GONE);
                    tvAddressAddress.setVisibility(View.VISIBLE);
                    ctlAddress.setOnClickListener(v -> {
                        Bundle bundle = new Bundle();
                        bundle.putString("type", "type");
                        openActivity(AddressActivity.class, bundle, 200);
                    });

                }

                if (goods_info != null) {
                    String goodsImage = goods_info.getGoods_image();
                    String name = goods_info.getGoods_name();
                    int num = goods_info.getGoods_num();
                    String spec = goods_info.getSpec();
                    double price = goods_info.getGoods_price();

                    tvOrderName.setText(name);
                    tvOrderDesc.setText(spec);
                    tvOrderPrice.setText("￥" + price);
                    tvOrderCount.setText("x" + num);
                    ImageLoader.loadCenterCrop(mContext, goodsImage, iv_order_img, 15);
                }

                if (mCoupon_list != null) {
//                     viewLineTwo.setVisibility(View.VISIBLE);
//                    ctlCoupons.setVisibility(View.VISIBLE);
                    showConpon(false);
                } else {
//                    viewLineTwo.setVisibility(View.GONE);
//                    ctlCoupons.setVisibility(View.GONE);
                    showConpon(false);
                }

                if (mRedPacket != null) {
                    XLog.e("有红包   " + mRedPacket.getVoucher_price());
                    showRed(true);
//                    ctlCoupon.setVisibility(View.VISIBLE);
                    tvSubMoney.setText("￥" + mRedPacket.getVoucher_price());
                    tvChooseOnlineCoupon.setText("￥" + mRedPacket.getVoucher_price());
                } else {
                    showRed(false);
//                    ctlCoupon.setVisibility(View.GONE);

                }


            }

            @Override
            public void failed(Response<String> response) {
               // XLog.e("订单信息" + response.getException().getMessage());

                XToast.normal(getString(R.string.service_error));
                dismiss();
            }
        });

    }

    private void showConpon(boolean show) {
        viewLineTwo.setVisibility(show ? View.VISIBLE : View.GONE);
        ctlCoupons.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void showRed(boolean show) {
        viewLineOne.setVisibility(show ? View.VISIBLE : View.GONE);
        ctlCoupon.setVisibility(show ? View.VISIBLE : View.GONE);
    }


    @OnClick({R.id.tv_buy})
    public void onViewClicked() {
        if (Util.handleOnDoubleClick()) {
            return;
        }
        commitOrder();
    }

    //下单接口

    private void commitOrder() {
        String redId = "0";
        if (mRedPacket != null) {
            if (cbUseCoupon.isChecked()) {
                redId = String.valueOf(mRedPacket.getId());
            }
        }
        String inviteCode = etInvite.getText().toString().trim();
        String leaveMsg = etLeaveMsg.getText().toString().trim();
        Map<String, String> params = new HashMap<>();
        params.put("goods_id", goodsId);
        params.put("type", "1");
        params.put("goods_number", String.valueOf(goodsNum));
        params.put("shop_note", leaveMsg);
        params.put("redpacket_id", redId);
        params.put("coupon_id", mCouponVo == null ? "" : String.valueOf(mCouponVo.getId()));
        params.put("address_id", String.valueOf(addressId));
        params.put("share_code", inviteCode);
        loading(true);
        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.submit_order, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("订单 " + response.body());
                dismiss();

                CreateOrderSuccessBean successBean = FastJsonUtil.fromJson(response.body(), CreateOrderSuccessBean.class);

                if (null == successBean) {
                    XToast.normal("订单创建失败");
                    return;
                }

                if (!successBean.getCode().equals(Constant.REQUEST_SUCCESS) || successBean.getResult() == null) {
                    XToast.normal(successBean.getMessage());
                    return;

                }

                String order_sn = successBean.getResult().getOrder_sn();
                Bundle bundle = new Bundle();

                bundle.putString("order_sn", order_sn);
                bundle.putString("order_type", "online");
                bundle.putString("order_price", String.valueOf(mStartValue));

                openActivity(OrderPayActivity.class, bundle);

            }

            @Override
            public void failed(Response<String> response) {
                dismiss();
              //  XLog.e("订单 " + response.getException().getMessage());
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void conponData(ConponEvent event) {
        mCouponVo = event.getBean();
        tvOnlinevalue.setText(mCouponVo.getVoucher_price() + "");
        if (mRedPacket != null) {//有红包
            if (cbUseCoupon.isChecked()) {
                tvSubMoney.setText("￥" + (mRedPacket.getVoucher_price() + mCouponVo.getVoucher_price()));
                double v = payAmount - (mRedPacket.getVoucher_price() + mCouponVo.getVoucher_price());
                if (v <= 0) {
                    v = 0.01;
                }
                mStartValue = v;
                tvPayAmount.setText("￥ " + v);
                tvRealMoneyCount.setText("￥ " + v);
                tvSubMoneyTitle.setVisibility(View.GONE);
                tvSubMoney.setVisibility(View.GONE);
            }
        } else {//没红包
            tvSubMoney.setText("￥" + mCouponVo.getVoucher_price());
            tvSubMoneyTitle.setVisibility(View.VISIBLE);
            tvSubMoney.setVisibility(View.VISIBLE);
            double com = payAmount - mCouponVo.getVoucher_price();
            if (com <= 0) {
                com = 0.01;
            }
            mStartValue = com;
            tvPayAmount.setText("￥ " + String.valueOf(com));
            tvRealMoneyCount.setText("￥ " + String.valueOf(com));
            cbUseCoupon.setChecked(true);

        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null) return;


        String mobile = data.getStringExtra("mobile");
        String true_name = data.getStringExtra("true_name");
        String address = data.getStringExtra("address");
        addressId = data.getStringExtra("address_id");
        tvAddressAddress.setVisibility(View.GONE);
        ctlAddress.setVisibility(View.VISIBLE);
        ctlHasAddress.setVisibility(View.VISIBLE);
        tvAddressName.setText(true_name);
        tvAddressPhone.setText(mobile);
        tvAddressDefault.setText(address);
    }
}
