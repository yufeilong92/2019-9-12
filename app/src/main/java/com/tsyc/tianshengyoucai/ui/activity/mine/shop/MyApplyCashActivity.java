package com.tsyc.tianshengyoucai.ui.activity.mine.shop;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.classic.common.MultipleStatusView;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.model.adapter.ApplyCashAdapter;
import com.tsyc.tianshengyoucai.model.bean.ApplyCashBean;
import com.tsyc.tianshengyoucai.model.bean.CashRecordBean;
import com.tsyc.tianshengyoucai.model.bean.OrderManageBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.fragment.sub.OrderManageFragment;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * author：van
 * CreateTime：2019/8/8
 * File description：我的收银台中的 申请提现
 */
public class MyApplyCashActivity extends BaseActivity {

    private static final int FIRST_REQUEST_DATA = 1001;
    private static final int REFRESH_LIST_DATA = 1003;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.multiple_status_view)
    MultipleStatusView mMultipleStatusView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_cash)
    TextView tvCash;

    private int page = 1;
    private boolean isLoadMore = false;
    private List<ApplyCashBean.ResultBean.DataBean> dataList;
    private List<ApplyCashBean.ResultBean.DataBean> dataAllList = new ArrayList<>();
    private ApplyCashAdapter orderManageAdapter;

    public static OrderManageFragment getInstance() {
        return new OrderManageFragment();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case FIRST_REQUEST_DATA:
                    requestListData(isLoadMore, page);
                    break;

                case REFRESH_LIST_DATA:
                    page = 1;
                    refreshListData(page);
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_casher_cash;
    }

    @Override
    public void initView() {
        mTvTitle.setText(getString(R.string.text_cash));
        tvCash.setOnClickListener(this);

    }

    @Override
    public void initData() {

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);

        orderManageAdapter = new ApplyCashAdapter();
        recyclerView.setAdapter(orderManageAdapter);

        View headerView = LayoutInflater.from(mContext).inflate(R.layout.layout_apply_cash_header,
                (ViewGroup) recyclerView.getParent(), false);
        orderManageAdapter.addHeaderView(headerView);


        mHandler.sendEmptyMessage(FIRST_REQUEST_DATA);
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            isLoadMore = false;
            mHandler.sendEmptyMessage(REFRESH_LIST_DATA);
        });

        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            page++;
            isLoadMore = true;
            mHandler.sendEmptyMessage(FIRST_REQUEST_DATA);
        });
        mMultipleStatusView.setOnRetryClickListener(v -> mHandler.sendEmptyMessage(REFRESH_LIST_DATA));

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cash: //去体现
                Bundle bundle = new Bundle();
                bundle.putString("type", "casher");
                openActivity(GoCashActivity.class, bundle);
                break;
        }
    }

    private void requestListData(boolean isLoadMore, int page) {
        Map<String, String> params = new HashMap<>();
        params.put("order_type", "2");
        params.put("status", "");
        params.put("page", String.valueOf(page));

        BaseRequestUtils.postRequestWithHeader(this, UrlManager.cashApplyList, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {

                ApplyCashBean orderManageBean = FastJsonUtil.fromJson(response.body(), ApplyCashBean.class);
                if (null == orderManageBean) {
                    mMultipleStatusView.showError();
                    refreshLayout.finishLoadMore();
                    return;
                }
                if (!orderManageBean.getCode().equals(Constant.REQUEST_SUCCESS)) {
                    refreshLayout.finishLoadMore();
                    mMultipleStatusView.showError();
                    if (orderManageBean.getMessage().toString().equals("暂未开通收银台")) {
                        mMultipleStatusView.showEmpty();
                    }
                    XToast.normal(orderManageBean.getMessage());
                    return;
                }
                if (null == orderManageBean.getResult()) {
                    mMultipleStatusView.showEmpty();
                    return;
                }
                dataList = orderManageBean.getResult().getData();
                if (isLoadMore) {
                    if (null != dataList && dataList.size() > 0) {
                        dataAllList.addAll(dataList);
                        refreshLayout.finishLoadMore();
                    } else {
                        refreshLayout.setNoMoreData(true);
                        refreshLayout.autoLoadMoreAnimationOnly();
                    }
                    refreshAdapter(false);
                } else {
                    if (null == dataList || dataList.size() == 0) {
                        refreshLayout.setEnableLoadMore(false);
                        mMultipleStatusView.showEmpty();
                        return;
                    }
                    refreshLayout.setEnableLoadMore(true);
                    mMultipleStatusView.showContent();
                    dataAllList.clear();
                    dataAllList.addAll(dataList);
                    refreshAdapter(true);
                }
            }

            @Override
            public void failed(Response<String> response) {
               // XLog.e("" + response.getException().getMessage());
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
            //orderManageAdapter = new ApplyCashAdapter(dataAllList);
            //recyclerView.setAdapter(orderManageAdapter);
            orderManageAdapter.setNewData(dataAllList);
        } else {
            orderManageAdapter.addData(dataAllList);
            //orderManageAdapter.notifyDataSetChanged();
        }

    }


    private void refreshListData(int page) {
        Map<String, String> params = new HashMap<>();
        params.put("order_type", "2");
        params.put("status", "");
        params.put("page", String.valueOf(page));

        BaseRequestUtils.postRequestWithHeader(this, UrlManager.cashApplyList, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("提现 " + response.body());
                ApplyCashBean orderManageBean = FastJsonUtil.fromJson(response.body(), ApplyCashBean.class);
                if (null == orderManageBean) {
                    refreshLayout.finishRefresh();
                    return;
                }

                if (!orderManageBean.getCode().equals(Constant.REQUEST_SUCCESS) || null == orderManageBean.getResult()) {
                    refreshLayout.finishRefresh();
                    return;
                }

                dataList = orderManageBean.getResult().getData();
                if (null == dataList || dataList.size() == 0) {
                    return;
                }
                dataAllList.clear();
                dataAllList.addAll(dataList);
                refreshAdapter(true);
                refreshLayout.finishRefresh();

            }

            @Override
            public void failed(Response<String> response) {
                XToast.normal("刷新失败");
                refreshLayout.finishRefresh();

            }
        });
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
            bundle.putString("type", "casher");
            openActivity(CashRecordActivity.class, bundle);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
