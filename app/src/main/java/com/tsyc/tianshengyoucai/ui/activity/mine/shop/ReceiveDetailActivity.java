package com.tsyc.tianshengyoucai.ui.activity.mine.shop;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.classic.common.MultipleStatusView;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.model.adapter.ReceiveDetailAdapter;
import com.tsyc.tianshengyoucai.model.bean.ReceiveBagCouponBean;
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

/**
 * author：van
 * CreateTime：2019/8/20
 * File description：领取详情
 */
public class ReceiveDetailActivity extends BaseActivity {

    private static final int FIRST_REQUEST_DATA = 1001;
    private static final int REFRESH_LIST_DATA = 1003;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private int page = 1;
    private boolean isLoadMore = false;
    private List<ReceiveBagCouponBean.ResultBean> dataList;
    private List<ReceiveBagCouponBean.ResultBean> dataAllList = new ArrayList<>();
    private ReceiveDetailAdapter receiveDetailAdapter;

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
    private String id;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_receive_detail;
    }

    @Override
    public void initView() {

        mTvTitle.setText("领取详情");

        Bundle extra = getIntent().getBundleExtra(Constant.bundleExtra);
        if (extra != null) {
            id = extra.getString("id");
            String type = extra.getString("type");
            mHandler.sendEmptyMessage(REFRESH_LIST_DATA);
        }

    }

    @Override
    public void initData() {

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new SimpleDividerItemDecortion(mContext));
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

    }

    private void requestListData(boolean isLoadMore, int page) {

        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("page", String.valueOf(page));

        BaseRequestUtils.postRequestWithHeader(this, UrlManager.receiveDetail, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("评价数据  " + response.body());
                ReceiveBagCouponBean receiveBagCouponBean = FastJsonUtil.fromJson(response.body(), ReceiveBagCouponBean.class);
                if (null == receiveBagCouponBean) {
                    multipleStatusView.showError();
                    refreshLayout.finishLoadMore();
                    return;
                }

                if (!receiveBagCouponBean.getCode().equals(Constant.REQUEST_SUCCESS) || null == receiveBagCouponBean.getResult()) {
                    multipleStatusView.showError();
                    refreshLayout.finishLoadMore();
                    return;
                }

                dataList = receiveBagCouponBean.getResult();
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
                        multipleStatusView.showEmpty();
                        return;
                    }
                    multipleStatusView.showContent();
                    dataAllList.clear();
                    dataAllList.addAll(dataList);

                    refreshAdapter(true);
                }
            }

            @Override
            public void failed(Response<String> response) {
                multipleStatusView.showError();
                refreshLayout.finishLoadMore();
            }
        });
    }

    private void refreshAdapter(boolean isReset) {
        if (dataAllList == null) {
            return;
        }
        if (isReset) {
            receiveDetailAdapter = new ReceiveDetailAdapter(dataAllList);
            recyclerView.setAdapter(receiveDetailAdapter);
        } else {
            receiveDetailAdapter.notifyDataSetChanged();
        }
    }

    private void refreshListData(int page) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("page", String.valueOf(page));

        BaseRequestUtils.postRequestWithHeader(this, UrlManager.receiveDetail, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                refreshLayout.finishRefresh();
                ReceiveBagCouponBean evaluateManageBean = FastJsonUtil.fromJson(response.body(), ReceiveBagCouponBean.class);
                if (null == evaluateManageBean) {
                    return;
                }
                if (!evaluateManageBean.getCode().equals(Constant.REQUEST_SUCCESS) || null == evaluateManageBean.getResult()) {
                    return;
                }

                dataList = evaluateManageBean.getResult();
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
                XLog.e("" + response.getException().getMessage());
            }
        });
    }
}
