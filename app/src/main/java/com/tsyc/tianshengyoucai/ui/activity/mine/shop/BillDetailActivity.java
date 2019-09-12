package com.tsyc.tianshengyoucai.ui.activity.mine.shop;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.classic.common.MultipleStatusView;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.model.adapter.BillDetailAdapter;
import com.tsyc.tianshengyoucai.model.bean.BillDetailBean;
import com.tsyc.tianshengyoucai.model.bean.ProductListBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.youth.xframe.utils.XDateUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：van
 * CreateTime：2019/8/9
 * File description：账单明细
 */
public class BillDetailActivity extends BaseActivity {

    private static final int FIRST_REQUEST_DATA = 1002;
    private static final int REFRESH_REQUEST_DATA = 1003;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.multiple_status_view)
    MultipleStatusView mMultipleStatusView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private BillDetailAdapter billDetailAdapter;

    private int page = 1;
    private boolean isLoadMore = false;
    private String startTime;
    private String endTime;
    private List<BillDetailBean.ResultBean.ListBean.DataBean> dataList;
    private List<BillDetailBean.ResultBean.ListBean.DataBean> dataAllList = new ArrayList<>();


    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case FIRST_REQUEST_DATA:
                    requestDetailData(isLoadMore, page, startTime, endTime);
                    break;

                case REFRESH_REQUEST_DATA:
                    refreshDetailData(page, startTime, endTime);
                    break;

                default:
                    break;
            }
        }
    };


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_bill_detail;
    }

    @Override
    public void initView() {
        mTvTitle.setText(getString(R.string.text_bill_detail));

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        billDetailAdapter = new BillDetailAdapter();
        recyclerView.setAdapter(billDetailAdapter);

        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            page++;
            isLoadMore = true;
            mHandler.sendEmptyMessage(FIRST_REQUEST_DATA);
        });

        refreshLayout.setOnRefreshListener(refreshLayout -> {
            page = 1;
            mHandler.sendEmptyMessage(REFRESH_REQUEST_DATA);
        });

        mMultipleStatusView.setOnRetryClickListener(v -> {
            mHandler.sendEmptyMessage(FIRST_REQUEST_DATA);
        });

    }

    @OnClick({R.id.ll_start_time, R.id.ll_end_time, R.id.btn_commit})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_start_time:
                showStartPicker(1);
                break;
            case R.id.ll_end_time:
                showStartPicker(2);
                break;
            case R.id.btn_commit:
                startTime = tvStartTime.getText().toString().trim();
                endTime = tvEndTime.getText().toString().trim();
                if (TextUtils.isEmpty(endTime) || TextUtils.isEmpty(startTime)) {
                    XToast.normal("请把日期选择完整");
                    return;
                }
                mMultipleStatusView.showLoading();
                mHandler.sendEmptyMessage(FIRST_REQUEST_DATA);
                break;
        }
    }

    private void requestDetailData(boolean isLoadMore, int page, String startTime, String endTime) {
        Map<String, String> params = new HashMap<>();
        params.put("start_time", startTime);
        params.put("end_time", endTime);
        params.put("page", String.valueOf(page));

        BaseRequestUtils.postRequestWithHeader(this, UrlManager.cashierList, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("订单明细" + response.body());

                BillDetailBean billDetailBean = FastJsonUtil.fromJson(response.body(), BillDetailBean.class);
                if (null == billDetailBean) {
                    mMultipleStatusView.showError();
                    refreshLayout.finishLoadMore();
                    return;
                }
                if (!billDetailBean.getCode().equals(Constant.REQUEST_SUCCESS) || null == billDetailBean.getResult()) {
                    mMultipleStatusView.showEmpty();
                    refreshLayout.finishLoadMore();
                    return;
                }


                if (isLoadMore) {
                    dataList = billDetailBean.getResult().getList().getData();
                    if (null != dataList && dataList.size() > 0) {
                        dataAllList.addAll(dataList);
                        refreshLayout.finishLoadMore();
                    } else {
                        refreshLayout.setNoMoreData(true);
                        refreshLayout.autoLoadMoreAnimationOnly();
                    }
                    refreshAdapter(false);
                } else {
                    dataList = billDetailBean.getResult().getList().getData();
                    if (null == dataList || dataList.size() == 0) {
                        mMultipleStatusView.showEmpty();
                        return;
                    }

                    mMultipleStatusView.showContent();
                    dataAllList.clear();
                    dataAllList.addAll(dataList);

                    refreshAdapter(true);
                }
            }

            @Override
            public void failed(Response<String> response) {
                mMultipleStatusView.showError();
                refreshLayout.finishLoadMore();
            }
        });
    }

    private void refreshAdapter(boolean isReset) {
        if (dataAllList == null) {
            return;
        }
        if (isReset) {
            billDetailAdapter.addData(dataAllList);
        } else {
            billDetailAdapter.setNewData(dataAllList);
        }
    }

    //刷新
    private void refreshDetailData(int page, String startTime, String endTime) {
        Map<String, String> params = new HashMap<>();
        params.put("start_time", startTime);
        params.put("end_time", endTime);
        params.put("page", String.valueOf(page));
        if (TextUtils.isEmpty(startTime)||TextUtils.isEmpty(endTime)){
            XToast.normal("请选择开始或结束时间");
            refreshLayout.finishRefresh();
            return;
        }
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.cashierList, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("订单明细" + response.body());
                refreshLayout.finishRefresh();

                BillDetailBean billDetailBean = FastJsonUtil.fromJson(response.body(), BillDetailBean.class);
                if (null == billDetailBean) {
                    return;
                }
                if (!billDetailBean.getCode().equals(Constant.REQUEST_SUCCESS) || null == billDetailBean.getResult()) {
                    return;
                }

                dataList = billDetailBean.getResult().getList().getData();
                if (null == dataList || dataList.size() == 0) {
                    XToast.normal("暂无数据");
                    return;
                }

                dataAllList.clear();
                dataAllList.addAll(dataList);

                refreshAdapter(false);
            }

            @Override
            public void failed(Response<String> response) {
                refreshLayout.finishRefresh();
                XToast.normal(getString(R.string.service_error));
            }
        });
    }


    private void showStartPicker(int type) {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2011, 1, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2069, 11, 28);
        TimePickerView pvTime = new TimePickerBuilder(mContext, (date, v) -> {
            String dateAllStr = XDateUtils.date2Date(date);
            if (type == 1)
                tvStartTime.setText(dateAllStr);
            else
                tvEndTime.setText(dateAllStr);
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setType(new boolean[]{true, true, true, false, false, false})
                .build();
        pvTime.show();
    }

}
