package com.tsyc.tianshengyoucai.ui.activity.mine.shop;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.Eventbuss.OrderRefreshEvent;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.listener.event.OrderDetailInEvent;
import com.tsyc.tianshengyoucai.model.bean.LogisticsCompanyBean;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author：van
 * CreateTime：2019/7/31
 * File description： 发货
 */
public class SendOrderActivity extends BaseActivity {


    private static final int REQUEST_COMPANY_DATA = 100;
    @BindView(R.id.tv_choose_company)
    TextView tvChooseCompany;
    @BindView(R.id.et_order_sn)
    EditText etOrderSn;

    private List<String> companyList = new ArrayList<>();

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case REQUEST_COMPANY_DATA:
                    requestCompanyData();
                    break;
            }

        }
    };
    private OptionsPickerView pvOptions;
    private int companyId;
    private String goodsId;
    private String sendType;
    private String url;
    private String orderIn;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_send_order;
    }

    @Override
    public void initView() {

        mTvTitle.setText(getString(R.string.text_send_info));

        Bundle extra = getIntent().getBundleExtra(Constant.bundleExtra);
        if (null != extra) {
            goodsId = extra.getString("goods_id");
            sendType = extra.getString("send_type");
            orderIn = extra.getString("order_in");
            XLog.e("__发货  " + goodsId);
        }
    }


    @OnClick({R.id.tv_choose_company, R.id.btn_commit})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_choose_company:
                mHandler.sendEmptyMessage(REQUEST_COMPANY_DATA);
                break;
            case R.id.btn_commit:
                sendOrder();
                break;
        }
    }

    //发货
    private void sendOrder() {

        String orderSn = etOrderSn.getText().toString().trim();
        String companyName = tvChooseCompany.getText().toString().trim();
        if (TextUtils.isEmpty(companyName)) {
            XToast.normal("请选择物流公司");
            return;
        }
        if (TextUtils.isEmpty(orderSn)) {
            XToast.normal(getString(R.string.hint_input_order_sn));
            return;
        }
        loading(true);
        Map<String, String> params = new HashMap<>();
        if (sendType != null && sendType.equals("shop_order")) {
            url = UrlManager.orderSend;
            params.put("order_id", goodsId);
            params.put("shipping_code", orderSn);
            params.put("shipping_express_id", String.valueOf(companyId));
        } else {
            url = UrlManager.ship_post;
            params.put("return_id", goodsId);
            params.put("invoice_no", orderSn);
            params.put("express_id", String.valueOf(companyId));
        }

        XLog.e("" + companyId + " - - " + goodsId + " - " + orderSn + " = " + url);
        BaseRequestUtils.postRequestWithHeader(this, url, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("发货 " + response.body());
                NormalBean normalBean = FastJsonUtil.fromJson(response.body(), NormalBean.class);
                EventBus.getDefault().postSticky(new OrderRefreshEvent(""));
                if (null == normalBean) {
                    XToast.normal("发货成功");
                    dismiss();
                    return;
                }
                if (!normalBean.getCode().equals("200")) {
                    XToast.normal(String.valueOf(normalBean.getMessage()));
                    dismiss();
                    return;
                }
                dismiss();
                XToast.normal("发货成功");
                if (orderIn!=null&&orderIn.equals("online_order")){
                    EventBus.getDefault().post(new OrderDetailInEvent("ok"));
                }
                finish();

            }

            @Override
            public void failed(Response<String> response) {
                XToast.normal("发货失败");
                dismiss();
            }
        });

    }

    //选择快递公司
    private void requestCompanyData() {
        loading(true);
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.logisticsCompanyList, null, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("物流业  " + response.body());
                LogisticsCompanyBean companyBean = FastJsonUtil.fromJson(response.body(), LogisticsCompanyBean.class);
                if (null == companyBean) {
                    dismiss();
                    XToast.normal("获取失败");
                    return;
                }

                if (!companyBean.getCode().equals("200")) {
                    dismiss();
                    XToast.normal("物流公司获取失败");
                    return;
                }

                List<LogisticsCompanyBean.ResultBean> result = companyBean.getResult();
                for (int i = 0; i < result.size(); i++) {
                    companyList.add(result.get(i).getE_name());
                }
                dismiss();

                initCompanyPicker(companyList, result);
            }

            @Override
            public void failed(Response<String> response) {
                XToast.normal("物流公司查询失败");
                dismiss();
            }
        });
    }

    private void initCompanyPicker(final List<String> listData, List<LogisticsCompanyBean.ResultBean> result) {
        pvOptions = new OptionsPickerBuilder(this, (options1, options2, options3, v) -> {
            tvChooseCompany.setText(listData.get(options1));
            companyId = result.get(options1).getId();


        }).setLayoutRes(R.layout.layout_company_picker, v -> {
            final TextView tvSubmit = v.findViewById(R.id.tv_finish);
            TextView tvCancel = v.findViewById(R.id.tv_Cancel);


            tvSubmit.setOnClickListener(v1 -> {
                pvOptions.returnData();
                pvOptions.dismiss();
            });

            tvCancel.setOnClickListener(v12 -> pvOptions.dismiss());
        }).setContentTextSize(18)//滚轮文字大小
                .setLineSpacingMultiplier((float) 2.5) //设置item的高度
                .isDialog(false)
                .setOutSideCancelable(false)
                .build();

        pvOptions.setPicker(listData);
        pvOptions.show();
    }

}
