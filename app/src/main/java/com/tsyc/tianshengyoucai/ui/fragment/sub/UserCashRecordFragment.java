package com.tsyc.tianshengyoucai.ui.fragment.sub;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.model.adapter.CashRecordAdapter;
import com.tsyc.tianshengyoucai.model.adapter.UserCashRecordAdapter;
import com.tsyc.tianshengyoucai.model.bean.UserBalanceRecordBean;
import com.tsyc.tianshengyoucai.model.bean.CashRecordBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.base.BaseFragment;
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
 * CreateTime：2019/7/30
 * File description： 用户 余额 佣金  提现记录 Fragment
 */
public class UserCashRecordFragment extends BaseFragment {


    private static final int FIRST_REQUEST_DATA = 1001;
    private static final int REFRESH_LIST_DATA = 1003;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;


    private int page = 1;
    private boolean isLoadMore = false;
    private List<UserBalanceRecordBean.ResultBean> dataList;
    private List<UserBalanceRecordBean.ResultBean> dataAllList = new ArrayList<>();
    private  String itemPosition;
    private UserCashRecordAdapter shopManageAdapter;
    private String url;
    private  String requestType;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case FIRST_REQUEST_DATA:
                    requestListData(isLoadMore, page, itemPosition);
                    break;

                case REFRESH_LIST_DATA:
                    page = 1;
                    refreshListData(page, itemPosition);
                    break;

                default:
                    break;
            }
        }
    };


    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_manage;
    }

    @Override
    protected void loadData() {

        mStateView.showLoading();
        Bundle arguments = getArguments();
        if (arguments == null) {
            mStateView.showRetry();
            return;
        }
        String title = arguments.getString("title");
        itemPosition = arguments.getString("position");
        requestType = arguments.getString("type");
        if (requestType != null) {
            if (requestType.equals("shop")) {
                url = UrlManager.cash_list;
            } else if (requestType.equals("casher")) {
                url = UrlManager.cashApplyList;
            } else if (requestType.equals("yue")) {
                url = UrlManager.withdrawLog;
            } else if (requestType.equals("yongjin")) {
                url = UrlManager.withdrawLog;
            }
        }

        XLog.e("提现记录 " + itemPosition + " - " + url);
        Message message = new Message();
        message.what = FIRST_REQUEST_DATA;
        message.obj = itemPosition;
        mHandler.sendMessage(message);
    }


    @Override
    public void initData() {

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);

        refreshLayout.setOnRefreshListener(refreshLayout -> {
            isLoadMore = false;
            mHandler.sendEmptyMessage(REFRESH_LIST_DATA);
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            page++;
            isLoadMore = true;
            mHandler.sendEmptyMessage(FIRST_REQUEST_DATA);
        });

        mStateView.setOnRetryClickListener(this::loadData);

    }

    private void requestListData(boolean isLoadMore, int page, String itemPosition) {

        Map<String, String> params = new HashMap<>();
        params.put("status", String.valueOf(itemPosition));
        params.put("page", String.valueOf(page));
        if (requestType.equals("yue")) {
            params.put("type", "0");
            params.put("pageNum", "10");
        } else {
            params.put("type", "1");
            params.put("pageNum", "10");
        }
        XLog.e("请求数据  " + itemPosition+"_____"+page+"____"+requestType+"");

        BaseRequestUtils.postRequestWithHeader(mActivity, url, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("提现记录: "+response.body());
                UserBalanceRecordBean balanceRecordBean = FastJsonUtil.fromJson(response.body(), UserBalanceRecordBean.class);
                if (null == balanceRecordBean) {
                    mStateView.showRetry();
                    refreshLayout.finishLoadMore();
                    return;
                }
                if (!balanceRecordBean.getCode().equals(Constant.REQUEST_SUCCESS) || null == balanceRecordBean.getResult()) {
                    mStateView.showRetry();
                    refreshLayout.finishLoadMore();
                    XToast.normal(balanceRecordBean.getMessage());
                    return;
                }

                dataList = balanceRecordBean.getResult();
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
                        mStateView.showEmpty().setOnClickListener(v -> loadData());
                        return;
                    }
                    mStateView.showContent();
                    dataAllList.clear();
                    dataAllList.addAll(dataList);

                    refreshAdapter(true);
                }
            }

            @Override
            public void failed(Response<String> response) {
                mStateView.showRetry();
                refreshLayout.finishLoadMore();
            }
        });
    }

    private void refreshAdapter(boolean isReset) {
        if (dataAllList == null) {
            return;
        }
        if (isReset) {
            shopManageAdapter = new UserCashRecordAdapter(dataAllList);
            recyclerView.setAdapter(shopManageAdapter);
        } else {
            shopManageAdapter.notifyDataSetChanged();
        }
    }

    private void refreshListData(int page, String itemPosition) {
        Map<String, String> params = new HashMap<>();
        params.put("status", String.valueOf(itemPosition));
        params.put("page", String.valueOf(page));
        if (requestType.equals("yue")) {
            params.put("type", "0");
            params.put("pageNum", "10");
        } else {
            params.put("type", "1");
            params.put("pageNum", "10");
        }
        XLog.e("刷新数据  " + itemPosition);
        BaseRequestUtils.postRequestWithHeader(mActivity, url, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                    refreshLayout.finishRefresh();
                UserBalanceRecordBean balanceRecordBean = FastJsonUtil.fromJson(response.body(), UserBalanceRecordBean.class);
                if (null == balanceRecordBean) {
                    return;
                }
                if (!balanceRecordBean.getCode().equals(Constant.REQUEST_SUCCESS) || null == balanceRecordBean.getResult()) {
                    XToast.normal(balanceRecordBean.getMessage());
                    return;
                }

                dataList = balanceRecordBean.getResult();
                if (null == dataList || dataList.size() == 0) {
                    return;
                }
                dataAllList.clear();
                dataAllList.addAll(dataList);
                refreshAdapter(true);

            }

            @Override
            public void failed(Response<String> response) {
                XToast.normal("刷新失败");
                refreshLayout.finishRefresh();

            }
        });
    }
}
