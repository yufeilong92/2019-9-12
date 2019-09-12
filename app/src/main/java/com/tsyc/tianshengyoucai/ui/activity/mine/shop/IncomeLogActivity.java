package com.tsyc.tianshengyoucai.ui.activity.mine.shop;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.classic.common.MultipleStatusView;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.model.adapter.IncomeLogAdapter;
import com.tsyc.tianshengyoucai.model.adapter.ShopManageAdapter;
import com.tsyc.tianshengyoucai.model.bean.IncomeLogBean;
import com.tsyc.tianshengyoucai.model.bean.SMGoodsListBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.view.SimpleDividerItemDecortion;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：van
 * CreateTime：2019/7/31
 * File description： 收入明细
 */
public class IncomeLogActivity extends BaseActivity {

    private static final int FIRST_REQUEST_DATA = 1001;
    private static final int REFRESH_LIST_DATA = 1003;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.multiple_status_view)
    MultipleStatusView mMultipleStatusView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private int page = 1;
    private boolean isLoadMore = false;
    private List<IncomeLogBean.ResultBean.DataBean> dataList;
    private List<IncomeLogBean.ResultBean.DataBean> dataAllList = new ArrayList<>();
    private IncomeLogAdapter incomeLogAdapter;


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case FIRST_REQUEST_DATA:
                    if (page == 1)
                        mMultipleStatusView.showLoading();
                    requestListData(isLoadMore, page);
                    break;

                case REFRESH_LIST_DATA:
                    refreshListData();
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_income_log;
    }

    @Override
    public void initView() {
        mTvTitle.setText(getString(R.string.text_money_detail));

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SimpleDividerItemDecortion(mContext));

    }

    @Override
    public void initData() {

        mHandler.sendEmptyMessage(FIRST_REQUEST_DATA);

        refreshLayout.setOnRefreshListener(refreshLayout -> {
            page = 1;
            mHandler.sendEmptyMessage(REFRESH_LIST_DATA);
        });

        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            page++;
            isLoadMore = true;
            mHandler.sendEmptyMessage(FIRST_REQUEST_DATA);
        });

        mMultipleStatusView.setOnRetryClickListener(v -> {
            mHandler.sendEmptyMessage(FIRST_REQUEST_DATA);
        });

    }


    private void requestListData(boolean isLoadMore, int page) {

        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));

        BaseRequestUtils.postRequestWithHeader(this, UrlManager.incomeLog, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("收入明细" + response.body());
                IncomeLogBean incomeLogBean = FastJsonUtil.fromJson(response.body(), IncomeLogBean.class);
                if (null == incomeLogBean) {
                    mMultipleStatusView.showError();
                    refreshLayout.finishLoadMore();
                    return;
                }
                if (!incomeLogBean.getCode().equals(Constant.REQUEST_SUCCESS) || null == incomeLogBean.getResult()) {
                    mMultipleStatusView.showError();
                    refreshLayout.finishLoadMore();
                    return;
                }

                if (isLoadMore) {
                    dataList = incomeLogBean.getResult().getData();
                    if (null != dataList && dataList.size() > 0) {
                        dataAllList.addAll(dataList);
                        refreshLayout.finishLoadMore();
                    } else {
                        refreshLayout.setNoMoreData(true);
                        refreshLayout.autoLoadMoreAnimationOnly();
                    }
                    refreshAdapter(false);
                } else {
                    dataList = incomeLogBean.getResult().getData();
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
            incomeLogAdapter = new IncomeLogAdapter(dataAllList);
            recyclerView.setAdapter(incomeLogAdapter);
        } else {
            incomeLogAdapter.notifyDataSetChanged();
        }
    }

    private void refreshListData() {
        Map<String, String> params = new HashMap<>();
        params.put("page", "1");
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.incomeLog, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                IncomeLogBean incomeLogBean = FastJsonUtil.fromJson(response.body(), IncomeLogBean.class);
                if (null == incomeLogBean) {
                    refreshLayout.finishRefresh();
                    return;
                }
                if (!incomeLogBean.getCode().equals(Constant.REQUEST_SUCCESS) || null == incomeLogBean.getResult()) {
                    refreshLayout.finishRefresh();
                    return;
                }
                dataList = incomeLogBean.getResult().getData();

                if (null == dataList || dataList.size() == 0) {
                    refreshLayout.finishRefresh();
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

}
