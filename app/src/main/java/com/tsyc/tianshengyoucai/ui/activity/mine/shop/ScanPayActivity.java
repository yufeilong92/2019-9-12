package com.tsyc.tianshengyoucai.ui.activity.mine.shop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzj.pass.dialog.PayPassDialog;
import com.lzj.pass.dialog.PayPassView;
import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.AppContext;
import com.tsyc.tianshengyoucai.Eventbuss.MineInfomEvents;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.listener.MyTextWatcherListener;
import com.tsyc.tianshengyoucai.listener.event.UnifiedNotifyEvent;
import com.tsyc.tianshengyoucai.model.adapter.PayTypeAdapter;
import com.tsyc.tianshengyoucai.model.bean.NormalResultBean;
import com.tsyc.tianshengyoucai.model.bean.PayTypeBean;
import com.tsyc.tianshengyoucai.model.bean.ScanCashierInfoBean;
import com.tsyc.tianshengyoucai.model.bean.ScanJsonBean;
import com.tsyc.tianshengyoucai.model.bean.WeChatPayBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.activity.mine.SetPayPwdActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.bank.MyBankListActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.money.PayActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.ImageLoader;
import com.tsyc.tianshengyoucai.utils.PayUtils;
import com.tsyc.tianshengyoucai.utils.Util;
import com.tsyc.tianshengyoucai.view.SimpleDividerItemDecortion;
import com.uuzuche.lib_zxing.decoding.Intents;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author：van
 * CreateTime：2019/7/31
 * File description： 扫码付款 界面
 */
public class ScanPayActivity extends BaseActivity {

    private static final int REQUEST_PAY_TYPE = 1020;
    private static final int REQUEST_CONFIG_CASH = 1021;

    @BindView(R.id.iv_shop_image)
    ImageView iv_shop_image;

    @BindView(R.id.tv_shop_name)
    TextView tv_shop_name;
    @BindView(R.id.tv_shop_take)
    TextView tv_shop_take;


    @BindView(R.id.tv_profit)
    TextView tvProfit;
    @BindView(R.id.tv_bank_info)
    TextView tv_bank_info;
    @BindView(R.id.et_profit)
    EditText etProfit;
    @BindView(R.id.rv_pay_type)
    RecyclerView rvPayType;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case REQUEST_CONFIG_CASH:
                    break;

                case REQUEST_PAY_TYPE:
                    userScanPay();
                    requestPayType();
                    break;
            }
        }

    };
    private PayTypeAdapter payTypeAdapter;
    private List<PayTypeBean.ResultBean> result;
    private int payType = -1; // 1 微信  2 支付宝 3 银联
    private String code;
    private String money;
    private int cashier_id;


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_scan_pay;
    }

    @Override
    public void initView() {
        registerEventBus(this);
        Bundle bundle = getIntent().getBundleExtra(Constant.bundleExtra);
        if (null != bundle) {
            code = bundle.getString("code");
            XLog.e("" + code);
            if (code != null) {
                ScanJsonBean jsonBean = FastJsonUtil.fromJson(code, ScanJsonBean.class);
                if (jsonBean == null) {
                    XToast.normal(getString(R.string.plz_show_code));
                    finish();
                    return;
                }

                if (jsonBean.getType() == null || jsonBean.getCashier_id() == 0) {
                    XToast.normal("付款已失效，请刷新二维码");
                    finish();
                    return;
                }

                if (!jsonBean.getType().equals(Constant.SCAN_PAY)) {
                    XToast.normal(getString(R.string.plz_show_code));
                    finish();
                    return;
                }
                money = jsonBean.getMoney();
                cashier_id = jsonBean.getCashier_id();
            }
        }

        mHandler.sendEmptyMessage(REQUEST_PAY_TYPE);
        mTvTitle.setText("扫码付款");

        rvPayType.setLayoutManager(new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvPayType.addItemDecoration(new SimpleDividerItemDecortion(mContext));
        rvPayType.setHasFixedSize(true);

        payTypeAdapter = new PayTypeAdapter();
        rvPayType.setAdapter(payTypeAdapter);

        payTypeAdapter.setOnItemClickListener((adapter, view, position) -> {

            payType = position + 1;
            int pay_type = result.get(position).getPay_type();

            XLog.e(pay_type + "pauType   " + payType);
            for (PayTypeBean.ResultBean bean : result) {
                bean.setCheck(false);
            }
            result.get(position).setCheck(true);
            payTypeAdapter.notifyDataSetChanged();
            tv_bank_info.setVisibility(View.GONE);
            tv_bank_info.setText("");
        });

    }

    @OnClick(R.id.btn_commit)
    public void onViewClick() {
        money = etProfit.getText().toString().trim();
        if (payType == -1) {
            XToast.normal("支付方式获取失败,暂不能支付");
            return;
        }
        if (TextUtils.isEmpty(money)) {
            XToast.normal("请输入提现金额");
            return;
        }
        if (Util.handleOnDoubleClick()) {
            return;
        }
        if (payType == 3) {
            accountMoneyPay();
        } else {
            createPayOrder("");
        }
    }

    private void createPayOrder(String pass) {
        Map<String, String> params = new HashMap<>();
        params.put("cashier_id", String.valueOf(cashier_id));
        params.put("money", money);
        params.put("type", String.valueOf(payType));
        params.put("member_paypwd", pass);
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.userScanPay, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {

                XLog.e("扫码订单下单  " + response.body());

                WeChatPayBean weChatPayBean = FastJsonUtil.fromJson(response.body(), WeChatPayBean.class);
                if (null == weChatPayBean) {
                    XToast.normal("获取支付信息失败，请稍候再试");
                    return;
                }
                if (!weChatPayBean.getCode().equals(Constant.REQUEST_SUCCESS) || weChatPayBean.getResult() == null) {
                    XToast.normal(weChatPayBean.getMessage());
                    return;
                }
                WeChatPayBean.ResultBean data = weChatPayBean.getResult();
                String content = data.getContent();
                if (payType == 1) {
                    PayUtils.weChatPay(AppContext.getInstance(), content, "scan_order");

                } else if (payType == 2) {
                    PayUtils.aliPay(ScanPayActivity.this, content);
                } else if (payType == 3) {
                    XToast.normal("支付成功");
                    openActivity(PayResultActivity.class);
                    finish();
                }
            }

            @Override
            public void failed(Response<String> response) {

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
                        createPayOrder(passContent);
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

    // 扫码详情
    private void userScanPay() {

        Map<String, String> params = new HashMap<>();
        params.put("cashier_id", String.valueOf(cashier_id));
        params.put("money", money);
        loading(true);
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.userScan, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("用户扫描付款码" + response.body());
                dismiss();
                ScanCashierInfoBean cashierInfoBean = FastJsonUtil.fromJson(response.body(), ScanCashierInfoBean.class);
                if (null == cashierInfoBean) {
                    return;
                }
                if (!cashierInfoBean.getCode().equals(Constant.REQUEST_SUCCESS) || cashierInfoBean.getResult() == null) {
                    XToast.normal(cashierInfoBean.getMessage());
                    return;
                }
                ScanCashierInfoBean.ResultBean result = cashierInfoBean.getResult();
                String member_credit = result.getMember_credit();
                String money = result.getMoney();

                tvProfit.setText("￥ " + member_credit);
                if (money.equals("-1")) {
                    etProfit.setClickable(true);
                    etProfit.setFocusable(true);
                } else {
                    etProfit.setText(money);
                    etProfit.setSelection(money.length() - 1);
                    etProfit.setClickable(false);
                    etProfit.setFocusable(false);
                }

                ScanCashierInfoBean.ResultBean.CashierBean cashier = result.getCashier();

                if (cashier != null) {
                    String storeName = cashier.getStore_name();
                    String door_photo = cashier.getDoor_photo();

                    ImageLoader.loadCenterCrop(mContext, door_photo, iv_shop_image, 15);
                    tv_shop_name.setText(storeName);
                }
            }

            @Override
            public void failed(Response<String> response) {
                XToast.normal(getString(R.string.service_error));
                dismiss();
            }
        });

    }

    private void requestPayType() {
        Map<String, String> params = new HashMap<>();
        params.put("type", "2"); // 1 充值 2 支付 3余额提现 4佣金提现 5店铺提现 6收银台提现

        BaseRequestUtils.postRequestWithHeader(this, UrlManager.payType, params, new BaseRequestUtils.getRequestCallBack() {

            @Override
            public void success(Response<String> response) {
                PayTypeBean cashBean = FastJsonUtil.fromJson(response.body(), PayTypeBean.class);

                if (null == cashBean) {
                    return;
                }

                if (!cashBean.getCode().equals(Constant.REQUEST_SUCCESS) || cashBean.getResult() == null) {
                    XToast.normal(String.valueOf(cashBean.getMessage()));
                    return;
                }

                payType = 1; // 默认是1
                result = cashBean.getResult();
                if (result.size() > 0) {
                    result.get(0).setCheck(true);
                }
                payTypeAdapter.setNewData(result);
            }

            @Override
            public void failed(Response<String> response) {
                XToast.normal("支付方式请求失败，请稍候再试");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == 201) {
            String bank_number = data.getStringExtra("bank_number");
            String bank_name = data.getStringExtra("bank_name");
            XLog.e("" + bank_name + "   " + bank_number);
            tv_bank_info.setVisibility(View.VISIBLE);
            tv_bank_info.setText(bank_name + " - " + bank_number);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void paySuccessEvent(UnifiedNotifyEvent events) {
        int eventCode = events.getEventCode();
        XLog.e("微信支付回调   " + eventCode);
        if (eventCode == Constant.ALI_PAY_SUCCESS_EVENT) {
            openActivity(PayResultActivity.class);
            EventBus.getDefault().post(new UnifiedNotifyEvent(Constant.REFRESH_ORDER_LIST));
        } else if (eventCode == Constant.WX_PAY_SUCCESS_EVENT) {
            openActivity(PayResultActivity.class);
            EventBus.getDefault().post(new UnifiedNotifyEvent(Constant.REFRESH_ORDER_LIST));
        }
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterEventBus(this);
    }

}
