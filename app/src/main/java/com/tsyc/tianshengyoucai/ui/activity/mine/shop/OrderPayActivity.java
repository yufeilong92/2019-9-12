package com.tsyc.tianshengyoucai.ui.activity.mine.shop;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.lzj.pass.dialog.PayPassDialog;
import com.lzj.pass.dialog.PayPassView;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tsyc.tianshengyoucai.AppContext;
import com.tsyc.tianshengyoucai.Eventbuss.MineInfomEvents;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.listener.event.OrderDetailInEvent;
import com.tsyc.tianshengyoucai.listener.event.UnderDetailInEvent;
import com.tsyc.tianshengyoucai.listener.event.UnifiedNotifyEvent;
import com.tsyc.tianshengyoucai.model.bean.WeChatPayBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.activity.mine.SetPayPwdActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.AliPayKeyBorderUtils;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.PayUtils;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.utils.Util;
import com.tsyc.tianshengyoucai.vo.UserInfomVo;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author：van
 * CreateTime：2019/8/10
 * File description：订单支付界面
 */
public class OrderPayActivity extends BaseActivity {

    @BindView(R.id.tv_total_pay)
    TextView tvTotalPay;
    @BindView(R.id.cb_wx_pay)
    CheckBox cbWxPay;
    @BindView(R.id.cb_ali_pay)
    CheckBox cbAliPay;
    @BindView(R.id.tv_account_money)
    TextView tvAccountMoney;
    @BindView(R.id.cb_account_pay)
    CheckBox cbAccountPay;
    @BindView(R.id.ctl_account_pay)
    ConstraintLayout ctlAccountPay;

    private int payType = 1;
    private CheckBox[] checkBoxes = new CheckBox[3];
    private String orderSn;
    private String orderPrice;
    private IWXAPI wxapi;
    private double member_points;
    private String orderType;
    private UserInfomVo mUserInfom;
    private String orderIn;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_order_pay;
    }

    @Override
    public void initView() {
        registerEventBus(this);
        checkBoxes[0] = (CheckBox) findViewById(R.id.cb_wx_pay);
        checkBoxes[1] = (CheckBox) findViewById(R.id.cb_ali_pay);
        checkBoxes[2] = (CheckBox) findViewById(R.id.cb_account_pay);
        checkBoxes[0].setChecked(true);
    }

    @Override
    public void initData() {
        initEvent();
        initRequest();


    }

    private void bindViewData(UserInfomVo vo) {
        member_points = vo.getResult().getMy_balance();
        tvAccountMoney.setText("当前余额: ￥" + member_points);

        Bundle extra = getIntent().getBundleExtra(Constant.bundleExtra);
        if (extra != null) {
            orderSn = extra.getString("order_sn");
            orderType = extra.getString("order_type");
            orderPrice = extra.getString("order_price");
            orderIn = extra.getString("order_in");
            XLog.e("orderType = " + orderType);
        }
        tvTotalPay.setText("￥ " + orderPrice);
    }

    private void initRequest() {
        requestInfom(new InfomInterface() {
            @Override
            public void infomSuccess(UserInfomVo vo) {
                bindViewData(vo);
                mUserInfom = vo;
            }

            @Override
            public void infomError() {

            }
        });
    }

    private void initEvent() {

        wxapi = WXAPIFactory.createWXAPI(mContext, Constant.WX_APP_ID, false);

    }

    @OnClick({R.id.ctl_wx_pay, R.id.ctl_ali_pay, R.id.ctl_account_pay, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ctl_wx_pay:
                payType(true, 0);
                payType = 1;
                break;
            case R.id.ctl_ali_pay:
                payType(true, 1);
                payType = 2;
                break;
            case R.id.ctl_account_pay:
                int is_paypwd = mUserInfom.getResult().getIs_paypwd();
                if (is_paypwd == 0) {
                    XToast.normal("请先设置支付密码");
                    openActivity(SetPayPwdActivity.class);
                    return;
                }
                payType = 3;
                payType(true, 2);
                break;
            case R.id.btn_commit:

                if (payType == 1) {
                    if (!wxapi.isWXAppInstalled()) {
                        XToast.normal("请先安装微信");
                        return;
                    }
                }
                if (payType == 3) {
                    if (mUserInfom == null) {
                        T.showToast(mContext, "获取用户信息失败,请重新进入");
                        return;
                    }
                    double my_balance = mUserInfom.getResult().getMy_balance();
                    if (my_balance == 0) {
                        T.showToast(mContext, "当前余额为0,请充值");
                        return;
                    }
                    if (StringUtil.isEmpty(orderPrice)) {
                        T.showToast(mContext, "获取支付金额失败,请重新进入");
                        return;
                    }
                    double aDouble = Double.parseDouble(orderPrice);

                    if (my_balance < aDouble) {
                        T.showToast(mContext, "当前余额不足,请充值");
                        return;
                    }
                    if (Util.handleOnDoubleClick()) {
                        return;
                    }
                    accountMoneyPay();
                } else {
                    if (Util.handleOnDoubleClick()) {
                        return;
                    }
                    doPay("");
                }
                break;
        }
    }

    private void doPay(String pass) {

        Map<String, String> params = new HashMap<>();
        params.put("order_sn", orderSn);
        params.put("pay_type", String.valueOf(payType));
        if (payType == 3) {
            params.put("member_paypwd", pass);
        }

        loading(true);
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.order_pay, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {

                dismiss();
                XLog.e("支付 " + response.body());
                WeChatPayBean weChatPayBean = FastJsonUtil.fromJson(response.body(), WeChatPayBean.class);
                if (null == weChatPayBean) {
                    XToast.normal("支付失败");
                    return;
                }
                if (!weChatPayBean.getCode().equals(Constant.REQUEST_SUCCESS) || weChatPayBean.getResult() == null) {
                    XToast.normal(weChatPayBean.getMessage());
                    return;
                }
                WeChatPayBean.ResultBean data = weChatPayBean.getResult();
                String content = data.getContent();
                if (payType == 1) {
                    PayUtils.weChatPay(AppContext.getInstance(), content, orderType);
                } else if (payType == 2) {
                    PayUtils.aliPay(OrderPayActivity.this, content);
                } else {
                    XToast.normal("支付成功");
                    openActivity(PayResultActivity.class);
                    notifyList();
                    EventBus.getDefault().post(new UnifiedNotifyEvent(Constant.REFRESH_ORDER_LIST));
                    EventBus.getDefault().postSticky(new MineInfomEvents(""));
                    finish();
                }

            }

            @Override
            public void failed(Response<String> response) {
                XLog.e("支付 " + response.getException().getMessage());
                XToast.normal(getString(R.string.service_error));
                dismiss();
            }
        });

    }

    private void accountMoneyPay() {
        final PayPassDialog dialog = new PayPassDialog(this);
        dialog.getPayViewPass()
                .setPayClickListener(new PayPassView.OnPayClickListener() {
                    @Override
                    public void onPassFinish(String passContent) {
                        //6位输入完成回调
                        doPay(passContent);
                        dialog.dismiss();
                    }

                    @Override
                    public void onPayClose() {
                        dialog.dismiss();
                        //关闭回调
                    }

                    @Override
                    public void onPayForget() {
                        dialog.dismiss();
                        openActivity(SetPayPwdActivity.class);
                    }
                });
    }


    private void payType(boolean isChecked, int checkPos) {
        if (isChecked) {
            for (int i = 0; i < checkBoxes.length; i++) {
                if (i == checkPos) {
                    checkBoxes[i].setChecked(true);
                } else {
                    checkBoxes[i].setChecked(false);
                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void paySuccessEvent(UnifiedNotifyEvent events) {
        int eventCode = events.getEventCode();
        XLog.e("微信支付回调   " + eventCode);
      notifyList();

        if (eventCode == Constant.ALI_PAY_SUCCESS_EVENT) {
            openActivity(PayResultActivity.class);
            EventBus.getDefault().post(new UnifiedNotifyEvent(Constant.REFRESH_ORDER_LIST));
        } else if (eventCode == Constant.WX_PAY_SUCCESS_EVENT) {
            openActivity(PayResultActivity.class);
            EventBus.getDefault().post(new UnifiedNotifyEvent(Constant.REFRESH_ORDER_LIST));
        }

        finish();
    }


    private void  notifyList(){
        if (orderIn!=null&&orderIn.equals("online_order")){
            EventBus.getDefault().post(new OrderDetailInEvent("ok"));
        }
        if (orderIn!=null && orderIn.equals("underline_order")){
            EventBus.getDefault().post(new UnderDetailInEvent("ok"));
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterEventBus(this);
    }
}
