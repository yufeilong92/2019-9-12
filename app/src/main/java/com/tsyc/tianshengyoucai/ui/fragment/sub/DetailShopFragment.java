package com.tsyc.tianshengyoucai.ui.fragment.sub;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.classic.common.MultipleStatusView;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.model.adapter.ProductListAdapter;
import com.tsyc.tianshengyoucai.model.bean.ProductListBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.activity.home.GoodsDetailActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseFragment;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * author：van
 * CreateTime：2019/7/30
 * File description： 商家详情 商品列表
 */
public class DetailShopFragment extends BaseFragment {


    private static final int FIRST_REQUEST_DATA = 1001;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.multiple_status_view)
    MultipleStatusView mMultipleStatusView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;


    private int page = 1;
    private boolean isLoadMore = false;
    private  String storeId;
    private List<ProductListBean.ResultBean> dataList;
    private List<ProductListBean.ResultBean> dataAllList = new ArrayList<>();
    private ProductListAdapter productListAdapter;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case FIRST_REQUEST_DATA:
                    requestListData(isLoadMore, page, storeId);
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_order;
    }

    @Override
    protected void loadData() {

        mStateView.showLoading();
    }

    @Override
    public void initData() {

        Bundle arguments = getArguments();
        if (arguments == null) {
            mStateView.showEmpty();
            return;
        }
        storeId = arguments.getString("store_id");
        XLog.e("store_id   " + storeId);
        mHandler.sendEmptyMessage(FIRST_REQUEST_DATA);

        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        recyclerView.setHasFixedSize(true);
        refreshLayout.setEnableRefresh(false);

        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            page++;
            isLoadMore = true;
            mHandler.sendEmptyMessage(FIRST_REQUEST_DATA);
        });

        mMultipleStatusView.setOnRetryClickListener(v -> {
            mHandler.sendEmptyMessage(FIRST_REQUEST_DATA);
        });

        productListAdapter = new ProductListAdapter();
        recyclerView.setAdapter(productListAdapter);
        productListAdapter.setOnItemClickListener((adapter, view, position) -> {
            int goods_id = dataAllList.get(position).getGoods_id();
            Bundle bundle = new Bundle();
            bundle.putString("goods_id", String.valueOf(goods_id));
            openActivity(GoodsDetailActivity.class, bundle);
        });
    }

    private void requestListData(boolean isLoadMore, int page, String itemPosition) {

        Map<String, String> params = new HashMap<>();
        params.put("store_id", String.valueOf(itemPosition));
        params.put("page", String.valueOf(page));
        params.put("pageNum", String.valueOf(10));
            XLog.e(""+storeId+"  -  "+page);
        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.productList, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("商品 " + response.body());
                ProductListBean productListBean = FastJsonUtil.fromJson(response.body(), ProductListBean.class);
                if (null == productListBean) {
                    mStateView.showRetry();
                    refreshLayout.finishLoadMore();
                    return;
                }
                if (!productListBean.getCode().equals(Constant.REQUEST_SUCCESS) || null == productListBean.getResult()) {
                    mStateView.showRetry();
                    refreshLayout.finishLoadMore();
                    return;
                }


                if (isLoadMore) {
                    dataList = productListBean.getResult();
                    if (null != dataList && dataList.size() > 0) {
                        dataAllList.addAll(dataList);
                        refreshLayout.finishLoadMore();
                    } else {
                        refreshLayout.setNoMoreData(true);
                        refreshLayout.autoLoadMoreAnimationOnly();
                    }
                    refreshAdapter(false);
                } else {
                    dataList = productListBean.getResult();
                    if (null == dataList || dataList.size() == 0) {
                        mStateView.showEmpty();
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
            productListAdapter.addData(dataAllList);
        } else {
            productListAdapter.setNewData(dataAllList);
        }

    }
}
