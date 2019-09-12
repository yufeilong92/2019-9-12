package com.tsyc.tianshengyoucai.ui.activity.mine;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.listener.event.NormalEvent;
import com.tsyc.tianshengyoucai.manager.DataMangerVo;
import com.tsyc.tianshengyoucai.model.adapter.PayTypeAdapter;
import com.tsyc.tianshengyoucai.model.bean.CashTipBean;
import com.tsyc.tianshengyoucai.model.bean.MineWithdrawBean;
import com.tsyc.tianshengyoucai.model.bean.MyBalanceBean;
import com.tsyc.tianshengyoucai.model.bean.PayTypeBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.activity.mine.bank.MyBankListActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.CashRecordActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.UserCashRecordActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.view.SimpleDividerItemDecortion;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author：van
 * CreateTime：2019/7/31
 * File description： 余额 佣金 提现
 */
public class MineCashActivity extends BaseActivity {

    private static final int REQUEST_PAY_TYPE = 1020;
    private static final int REQUEST_CONFIG_CASH = 1021;

    @BindView(R.id.tv_profit_tip)
    TextView tvProfitTip;
    @BindView(R.id.tv_profit)
    TextView tvProfit;
    @BindView(R.id.tv_rule)
    TextView tvRule;
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
                    requestPayType();
                    requestCashRule(type);//佣金
                    break;
            }
        }

    };

    private PayTypeAdapter payTypeAdapter;
    private List<PayTypeBean.ResultBean> result;
    private int payType = -1; // 1 微信  2 支付宝 3 银联
    private String type; // 0 余额  1 佣金提现
    private String bank_id;


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_mine_cash;
    }

    @Override
    public void initView() {

        Bundle bundle = getIntent().getBundleExtra(Constant.bundleExtra);
        if (null != bundle) {
            type = bundle.getString("type");
        }

        mHandler.sendEmptyMessage(REQUEST_PAY_TYPE);
        mTvTitle.setText(getString(R.string.text_cash));

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

            for (PayTypeBean.ResultBean bean : result) {
                bean.setCheck(false);
            }
            result.get(position).setCheck(true);
            payTypeAdapter.notifyDataSetChanged();
            if (result.get(position).getName().equals("银行卡")) {
                Bundle bankBundle = new Bundle();
                bankBundle.putString("type", "get_bank");
                bankBundle.putString(DataMangerVo.TITLEPARAM, "银行卡");
                openActivity(MyBankListActivity.class, bankBundle, 200);
            }
            tv_bank_info.setVisibility(View.GONE);
            tv_bank_info.setText("");
        });

    }

    @OnClick(R.id.btn_commit)
    public void onViewClick() {
        String profit = etProfit.getText().toString().trim();
        if (payType == -1) {
            XToast.normal("支付方式获取失败,暂不能支付");
            return;
        }
        if (TextUtils.isEmpty(profit)) {
            XToast.normal("请输入提现金额");
            return;
        }

        cashProfit(profit);

    }

    //申请提现
    private void cashProfit(String profit) {

        Map<String, String> params = new HashMap<>();
        params.put("type", type); // 0 余额提现   1 佣金提现
        params.put("pay_type", String.valueOf(payType));
        params.put("money", profit);
        params.put("account_id", bank_id);
        loading(true);
        XLog.e("" + type + " --- " + payType + "  --- " + profit + " --- " + UrlManager.mine_withdraw);
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.mine_withdraw, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("申请提现" + response.body());
                dismiss();
                MineWithdrawBean resultBean = FastJsonUtil.fromJson(response.body(), MineWithdrawBean.class);
                if (null == resultBean) {
                    XToast.normal("申请提现，请求失败");
                    return;
                }
                if (!resultBean.getCode().equals(Constant.REQUEST_SUCCESS)) {
                    XToast.normal(resultBean.getMessage());
                    return;
                }
                XToast.normal(resultBean.getResult());
                finish();

                EventBus.getDefault().post(new NormalEvent("my_team_activity",124));
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
        if (type.equals("0")) {
            params.put("type", "3"); //  1 充值 2 支付 3余额提现 4佣金提现 5店铺提现 6收银台提现
        } else if (type.equals("1")) {
            params.put("type", "4");
        }
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

    //我的余额
    private void requestMyBalance() {
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.my_balance, null, new BaseRequestUtils.getRequestCallBack() {

            @Override
            public void success(Response<String> response) {
                XLog.e("my_balance " + response.body());
                MyBalanceBean cashTipBean = FastJsonUtil.fromJson(response.body(), MyBalanceBean.class);

                if (null == cashTipBean) {
                    return;
                }
                if (!cashTipBean.getCode().equals("200") || cashTipBean.getResult() == null) {
                    return;
                }

                MyBalanceBean.ResultBean result = cashTipBean.getResult();
                String service_fee = result.getMy_balance();
                tvProfit.setText(String.valueOf(service_fee + "元"));
            }

            @Override
            public void failed(Response<String> response) {

            }

        });
    }

    private void requestCashRule(String type) {

        Map<String, String> params = new HashMap<>();
        params.put("type", type);
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.withdrawDate, params, new BaseRequestUtils.getRequestCallBack() {

            @Override
            public void success(Response<String> response) {
                XLog.e("withdrawDate " + response.body());
                CashTipBean cashTipBean = FastJsonUtil.fromJson(response.body(), CashTipBean.class);

                if (null == cashTipBean) {
                    return;
                }
                if (!cashTipBean.getCode().equals("200") || cashTipBean.getResult() == null) {
                    return;
                }

                CashTipBean.ResultBean result = cashTipBean.getResult();
                List<String> rule = result.getRule();
                float unsettled = result.getMoney();
                String service_fee = result.getService_fee();

                tvProfit.setText(String.valueOf(unsettled + "元"));
                StringBuilder sb = new StringBuilder();
                sb.append("提现规则：\n\n");
                for (int i = 0; i < rule.size(); i++) {
                    sb.append(rule.get(i) + "\n\n");
                }
                tvRule.setText(sb.toString());
            }

            @Override
            public void failed(Response<String> response) {

            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == 201) {
            String bank_number = data.getStringExtra("bank_number");
            String bank_name = data.getStringExtra("bank_name");
            bank_id = data.getStringExtra("bank_id");
            XLog.e("" + bank_name + "   " + bank_number);
            tv_bank_info.setVisibility(View.VISIBLE);
            tv_bank_info.setText(bank_name + " \n " + bank_number);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cash_record, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Bundle bundle = new Bundle();
            if (type.equals("0")) {
                bundle.putString("type", "yue");
            } else {
                bundle.putString("type", "yongjin");
            }
            openActivity(UserCashRecordActivity.class, bundle);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
