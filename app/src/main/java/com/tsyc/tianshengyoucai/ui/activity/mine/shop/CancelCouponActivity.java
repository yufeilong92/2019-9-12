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
import com.tsyc.tianshengyoucai.model.adapter.CouponListAdapter;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.model.bean.RelCouponBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.fragment.sub.CouponListFragment;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
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
 * CreateTime：2019/7/30
 * File description： 已取消的优惠券
 */
public class CancelCouponActivity extends BaseActivity {

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
    private List<RelCouponBean.ResultBean> dataList;
    private List<RelCouponBean.ResultBean> dataAllList = new ArrayList<>();
    private CouponListAdapter couponListAdapter;

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
        return R.layout.activity_cancel_coupon;
    }

    @Override
    public void initView() {
        mTvTitle.setText(getString(R.string.text_coupon_cancel));

    }

    @Override
    public void initData() {

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);
        couponListAdapter = new CouponListAdapter();
        recyclerView.setAdapter(couponListAdapter);

        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            isLoadMore = false;
            mHandler.sendEmptyMessage(REFRESH_LIST_DATA);
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            page++;
            isLoadMore = true;
            mHandler.sendEmptyMessage(FIRST_REQUEST_DATA);
        });
        mHandler.sendEmptyMessage(FIRST_REQUEST_DATA);
        mMultipleStatusView.setOnRetryClickListener(v -> mHandler.sendEmptyMessage(FIRST_REQUEST_DATA));

        couponListAdapter = new CouponListAdapter();
        recyclerView.setAdapter(couponListAdapter);
    }

    private void requestListData(boolean isLoadMore, int page) {

        Map<String, String> params = new HashMap<>();
        params.put("type", "3"); //1 可领取 2 已领取
       // params.put("page", String.valueOf(page));


        BaseRequestUtils.postRequestWithHeader(this, UrlManager.couponList, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                refreshLayout.finishLoadMore();
                RelCouponBean couponBean = FastJsonUtil.fromJson(response.body(), RelCouponBean.class);
                if (null == couponBean) {
                    mMultipleStatusView.showError();
                    return;
                }
                if (!couponBean.getCode().equals(Constant.REQUEST_SUCCESS) || null == couponBean.getResult()) {
                    mMultipleStatusView.showError();
                    return;
                }

                if (isLoadMore) {
                dataList = couponBean.getResult();
                    if (null != dataList && dataList.size() > 0) {
                        dataAllList.addAll(dataList);
                    } else {
                        refreshLayout.setNoMoreData(true);
                        refreshLayout.autoLoadMoreAnimationOnly();
                    }
                    refreshAdapter(false);
                } else {
                dataList = couponBean.getResult();
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

            couponListAdapter.addData(dataAllList);
        } else {
            couponListAdapter.setNewData(dataAllList);
        }

    }

    private void refreshListData(int page) {
        Map<String, String> params = new HashMap<>();
        params.put("type", "3");
        //params.put("page", String.valueOf(page));
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.couponList, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                refreshLayout.finishRefresh();
                RelCouponBean couponBean = FastJsonUtil.fromJson(response.body(), RelCouponBean.class);
                if (null == couponBean) {
                    return;
                }
                if (!couponBean.getCode().equals(Constant.REQUEST_SUCCESS) || null == couponBean.getResult()) {
                    return;
                }
                dataList = couponBean.getResult();
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
