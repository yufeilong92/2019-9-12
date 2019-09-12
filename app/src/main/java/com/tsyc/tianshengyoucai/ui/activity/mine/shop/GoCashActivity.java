package com.tsyc.tianshengyoucai.ui.activity.mine.shop;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.listener.MyTextWatcherListener;
import com.tsyc.tianshengyoucai.manager.DataMangerVo;
import com.tsyc.tianshengyoucai.model.adapter.PayTypeAdapter;
import com.tsyc.tianshengyoucai.model.bean.CashTipBean;
import com.tsyc.tianshengyoucai.model.bean.NormalResultBean;
import com.tsyc.tianshengyoucai.model.bean.PayTypeBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.activity.mine.UserInfoActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.bank.MyBankListActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.DialogUtils;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.Util;
import com.tsyc.tianshengyoucai.view.SimpleDividerItemDecortion;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author：van
 * CreateTime：2019/7/31
 * File description： 去提现
 */
public class GoCashActivity extends BaseActivity {

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
                    requestCashRule();
                    break;
            }
        }

    };
    private PayTypeAdapter payTypeAdapter;
    private List<PayTypeBean.ResultBean> result;
    private int payType = -1; // 1 微信  2 支付宝 3 银联
    private String url;
    private String type;
    private String ruleUrl;
    private String bank_id;


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_cash;
    }

    @Override
    public void initView() {
        // UserInfomVo userInfo = SaveUserInfomUtilJave.getInstance().getUserInfom();
//        double my_balance = userInfo.getResult().getMy_balance();
//        tvProfit.setText(String.valueOf(my_balance));

        Bundle bundle = getIntent().getBundleExtra(Constant.bundleExtra);
        if (null != bundle) {
            type = bundle.getString("type");
            if (type == null)
                return;

            switch (type) {
                case "shop"://店铺提现
                    url = UrlManager.cashApply;
                    ruleUrl = UrlManager.cashRule;
                    break;
                case "casher"://收银台提现
                    url = UrlManager.casherApply;
                    ruleUrl = UrlManager.unsettled;
                    break;
            }
        }

        //设置hint 字体大小
        String hintStr = "请输入提现金额";
        SpannableString ss = new SpannableString(hintStr);
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(20, true);
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_POINT_MARK_MASK);
        etProfit.setHint(new SpannedString(ss));

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


        etProfit.addTextChangedListener(new MyTextWatcherListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }
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
        if (Util.handleOnDoubleClick()) {
            return;
        }
        cashProfit(profit);

    }

    //申请提现
    private void cashProfit(String profit) {

        Map<String, String> params = new HashMap<>();
        params.put("type", String.valueOf(payType));
        params.put("money", profit);
        params.put("account_id", bank_id);
        loading(true);
        BaseRequestUtils.postRequestWithHeader(this, url, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("申请提现" + response.body());
                dismiss();
                NormalResultBean resultBean = FastJsonUtil.fromJson(response.body(), NormalResultBean.class);
                if (null == resultBean) {
                    return;
                }
                if (!resultBean.getCode().equals(Constant.REQUEST_SUCCESS)) {
                    if (resultBean.getMessage().contains("您没有授权微信账号")) {
                        DialogUtils.getSingleton().showBindBank(mContext, 1, type -> openActivity(UserInfoActivity.class));
                        return;
                    } else if (resultBean.getMessage().contains("您还没有绑定支付宝账户")) {
                        DialogUtils.getSingleton().showBindBank(mContext, 0, type -> openActivity(UserInfoActivity.class));
                        return;
                    }
                    XToast.normal(resultBean.getMessage());
                    return;
                }
                XToast.normal(resultBean.getMessage());
                finish();
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
        if (type.equals("shop")) {
            params.put("type", "5"); // 1 充值 2 支付 3余额提现 4佣金提现 5店铺提现 6收银台提现
        } else if (type.equals("casher")) {
            params.put("type", "6"); // 1 充值 2 支付 3余额提现 4佣金提现 5店铺提现 6收银台提现
        }

        BaseRequestUtils.postRequestWithHeader(this, UrlManager.payType, params, new BaseRequestUtils.getRequestCallBack() {

            @Override
            public void success(Response<String> response) {
                XLog.e(type + "_____________" + response.body());
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

    private void requestCashRule() {

        BaseRequestUtils.postRequestWithHeader(this, ruleUrl, null, new BaseRequestUtils.getRequestCallBack() {

            @Override
            public void success(Response<String> response) {
                CashTipBean cashTipBean = FastJsonUtil.fromJson(response.body(), CashTipBean.class);

                if (null == cashTipBean) {
                    return;
                }
                if (!cashTipBean.getCode().equals("200") || cashTipBean.getResult() == null) {
                    return;
                }

                CashTipBean.ResultBean result = cashTipBean.getResult();
                List<String> rule = result.getRule();
                float unsettled = result.getUnsettled();
                String service_fee = result.getService_fee();

                tvProfit.setText(String.valueOf(unsettled + "元"));
                etProfit.setText("" + unsettled);
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
            tv_bank_info.setText(bank_name + " - " + bank_number);
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
            bundle.putString("type", type);
            openActivity(CashRecordActivity.class, bundle);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
