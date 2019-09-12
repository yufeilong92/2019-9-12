package com.tsyc.tianshengyoucai.ui.activity.mine.shop;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.classic.common.MultipleStatusView;
import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.adapter.LogisticsInfoAdapter;
import com.tsyc.tianshengyoucai.model.bean.LogisticsListBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：van
 * CreateTime：2019/7/31
 * File description：查看物流
 */
public class LookLogisticsActivity extends BaseActivity {

    private static final int REQUEST_DATA = 1001;
    @BindView(R.id.tv_logist_company)
    TextView tvLogisticsCompany;
    @BindView(R.id.tv_order_sn)
    TextView tvOrderSn;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.multiple_status_view)
    MultipleStatusView mMultipleStatusView;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case REQUEST_DATA:
                    loadLogistics();
                    break;

                default:
                    break;
            }
        }
    };
    private String goodsId;
    private LogisticsInfoAdapter infoAdapter;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_look_logistics;
    }

    @Override
    public void initView() {
        mTvTitle.setText(getString(R.string.text_look_logistics));
        mMultipleStatusView.showLoading();
        Bundle extra = getIntent().getBundleExtra("extra");
        if (null == extra) {
            mMultipleStatusView.showError();
            return;
        }
        goodsId = extra.getString("goods_id");
        mHandler.sendEmptyMessage(REQUEST_DATA);

    }

    @Override
    public void initData() {


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mMultipleStatusView.setOnRetryClickListener(v -> initView());

    }

    List<LogisticsListBean.ResultBean.DeliverInfoBean> list = new ArrayList<>();
    //物流信息
    private void loadLogistics() {


        Map<String, String> params = new HashMap<>();
        params.put("order_id", goodsId);
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.lookLogistics, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {

                XLog.e("查看物流 " + response.body());
                LogisticsListBean logisticsListBean = FastJsonUtil.fromJson(response.body(), LogisticsListBean.class);
                if (null == logisticsListBean) {
                    mMultipleStatusView.showError();
                    return;
                }

                if (!logisticsListBean.getCode().equals("200")) {
                    mMultipleStatusView.showError();
                    return;
                }

                LogisticsListBean.ResultBean result = logisticsListBean.getResult();

                String shipping_code = result.getShipping_code();
                String express_name = result.getExpress_name();
                tvLogisticsCompany.setText(express_name);
                tvOrderSn.setText(shipping_code);
                List<LogisticsListBean.ResultBean.DeliverInfoBean> deliver_info = result.getDeliver_info();
                if (null == deliver_info || deliver_info.size() == 0) {
                    mMultipleStatusView.showEmpty();
                    return;
                }
                mMultipleStatusView.showContent();
                list.clear();
                list.addAll(deliver_info);

                LogisticsInfoAdapter infoAdapter = new LogisticsInfoAdapter(list);
                recyclerView.setAdapter(infoAdapter);
//                infoAdapter.setNewData(list);
            }

            @Override
            public void failed(Response<String> response) {
                mMultipleStatusView.showError();
            }
        });
    }


}
