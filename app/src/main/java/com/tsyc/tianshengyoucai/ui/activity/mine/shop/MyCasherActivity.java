package com.tsyc.tianshengyoucai.ui.activity.mine.shop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.flyn.Eyes;
import com.tsyc.tianshengyoucai.model.bean.CashHomeInfoBean;
import com.tsyc.tianshengyoucai.model.bean.ScanJsonBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.SecondActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.UnderLineOrderActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author：van
 * CreateTime：2019/8/8
 * File description： 我的收银台
 */
public class MyCasherActivity extends BaseActivity {

    private final int REQUEST_INFO = 200;
    @BindView(R.id.tv_wait_money)
    TextView tvWaitMoney;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_month)
    TextView tvMonth;
    @BindView(R.id.tv_day)
    TextView tvDay;
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.tv_casher)
    TextView tvCasher;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == REQUEST_INFO) {
                int type = (int) msg.obj;
                requestInfo(type);
            }
        }
    };
    private int cashierId;
    private String storeName;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_my_casher;
    }

    @Override
    public void initView() {
        Eyes.translucentStatusBar(this, true);
        Message obtain = Message.obtain();
        obtain.what = REQUEST_INFO;
        obtain.obj = 1;
        mHandler.sendMessage(obtain);
    }

    @OnClick({R.id.rl_back, R.id.ctl_detail, R.id.ctl_receive, R.id.ctl_cash, R.id.ctl_scan, R.id.ctl_under_order})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.ctl_detail:
                //账单明细
                openActivity(BillDetailActivity.class);
                break;
            case R.id.ctl_receive: // 点击付款
                bundle.putString("casher_id", String.valueOf(cashierId));
                bundle.putString("casher_name", storeName);
                openActivity(MyReceiveCashActivity.class, bundle);
                break;
            case R.id.ctl_cash: // 提现界面
                openActivity(MyApplyCashActivity.class);
                break;
            case R.id.ctl_scan: // 扫码核销
                openActivity(SecondActivity.class, Constant.CASHIER_SCAN);
                break;
            case R.id.ctl_under_order: // 先下订单
                bundle.putString("type", "2");
                openActivity(OrderManageActivity.class, bundle);
                break;
        }
    }

    private void requestInfo(int type) {
        if (type == 1)
            loading(true);
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.cashInfo, null, new BaseRequestUtils.getRequestCallBack() {

            @Override
            public void success(Response<String> response) {
                CashHomeInfoBean infoBean = FastJsonUtil.fromJson(response.body(), CashHomeInfoBean.class);
                if (type == 1)
                    dismiss();
                if (null == infoBean) {
                    return;
                }

                if (!infoBean.getCode().equals(Constant.REQUEST_SUCCESS) || infoBean.getResult() == null) {
                    XToast.normal(infoBean.getMessage());
                    return;
                }

                CashHomeInfoBean.ResultBean result = infoBean.getResult();
                CashHomeInfoBean.ResultBean.CashierBean cashier = result.getCashier();
                cashierId = cashier.getId();
                storeName = cashier.getStore_name();
                float all_turnover = result.getAll_turnover();
                float month_turnover = result.getMonth_turnover();
                float today_turnover = result.getToday_turnover();
                float unsettled = result.getUnsettled();

                tvWaitMoney.setText(String.valueOf(unsettled));
                tvTotal.setText(String.valueOf(all_turnover));
                tvMonth.setText(String.valueOf(month_turnover));
                tvDay.setText(String.valueOf(today_turnover));

            }

            @Override
            public void failed(Response<String> response) {
                XToast.normal(getString(R.string.service_error));
                if (type == 1)
                    dismiss();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null && resultCode == RESULT_OK) {

            Bundle extra = data.getExtras();
            if (null != extra) {
                String code = extra.getString(CodeUtils.RESULT_STRING);
                Bundle bundle = new Bundle();
                XLog.e("------------" + code);

                String finalCode = StringUtil.checkCode(code);
                if (TextUtils.isEmpty(finalCode)) {
                    XToast.normal(getString(R.string.plz_show_code));
                    return;
                }
                bundle.putString("code", finalCode);
                bundle.putString("type", "order_cashier");
                openActivity(ScanOrderActivity.class, bundle);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        Message obtain = Message.obtain();
        obtain.what = REQUEST_INFO;
        obtain.obj = 2;
        mHandler.sendMessage(obtain);
    }
}
