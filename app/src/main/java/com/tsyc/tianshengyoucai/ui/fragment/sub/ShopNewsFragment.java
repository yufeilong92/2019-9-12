package com.tsyc.tianshengyoucai.ui.fragment.sub;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.model.adapter.ShopNewsAdapter;
import com.tsyc.tianshengyoucai.model.bean.ShopNewsListBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.activity.home.ShopNewsDetailActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseFragment;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.view.SimpleDividerItemDecortion;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author：van
 * CreateTime：2019/8/20
 * File description：商业咨询
 */
public class ShopNewsFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private SmartRefreshLayout refreshLayout;

    private List<ShopNewsListBean.ResultBean> dataAllList = new ArrayList<>();
    private ShopNewsAdapter shopNewsAdapter;
    private String position;
    private int page = 1;

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_shop_news;
    }

    @Override
    protected void loadData() {

        //requestNewsList(position, 1, false);

    }

    @Override
    public void initView(View rootView) {

        Bundle arguments = getArguments();
        if (arguments == null) return;
        // mStateView.showLoading();
        position = arguments.getString("position");

        recyclerView = rootView.findViewById(R.id.recyclerView);
        refreshLayout = rootView.findViewById(R.id.refreshLayout);
        recyclerView.addItemDecoration(new SimpleDividerItemDecortion(mContext));
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);

        refreshLayout.setOnRefreshListener(refreshLayout -> refreshNewsList(position));
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            page++;
            requestNewsList(position, page, true);
        });
        refreshLayout.autoRefresh();
    }

    @Override
    public void initData() {
        shopNewsAdapter = new ShopNewsAdapter(dataAllList);
        recyclerView.setAdapter(shopNewsAdapter);
        shopNewsAdapter.setOnItemClickListener((adapter, view, position) -> {
            int article_id = dataAllList.get(position).getArticle_id();
            Bundle bundle = new Bundle();
            bundle.putString("id", String.valueOf(article_id));
            openActivity(ShopNewsDetailActivity.class, bundle);
        });

    }

    private void requestNewsList(String position, int page, boolean isLoadMore) {
        Map<String, String> params = new HashMap<>();
        params.put("id", position);
        params.put("page", String.valueOf(page));

        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.getNewsList, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e(response.body());
                refreshLayout.finishLoadMore();
                ShopNewsListBean shopNewsListBean = FastJsonUtil.fromJson(response.body(), ShopNewsListBean.class);
                if (!shopNewsListBean.getCode().equals(Constant.REQUEST_SUCCESS) || shopNewsListBean.getResult() == null) {
                    XToast.normal(shopNewsListBean.getMessage());
                    mStateView.showEmpty();
                    return;
                }
                if (isLoadMore) {
                    List<ShopNewsListBean.ResultBean> result = shopNewsListBean.getResult();
                    if (null != result && result.size() > 0) {
                        dataAllList.addAll(addType(result));
                        if (result.size() < 10) {
                            refreshLayout.setEnableLoadMore(false);
                        } else {
                            refreshLayout.setEnableLoadMore(true);
                        }
                    } else {
                        refreshLayout.setEnableLoadMore(false);
                        refreshLayout.setNoMoreData(true);
                        refreshLayout.autoLoadMoreAnimationOnly();
                    }
                    refreshAdapter(false);
                } else {
                    List<ShopNewsListBean.ResultBean> result = shopNewsListBean.getResult();
                    if (result.size() == 0) {
                        mStateView.showEmpty().setOnClickListener(v -> loadData());
                        return;
                    }
                    if (result.size() < 10)
                        refreshLayout.setEnableLoadMore(false);
                    mStateView.showContent();
                    dataAllList.clear();
                    dataAllList.addAll(addType(result));
                    refreshAdapter(true);
                }

            }

            @Override
            public void failed(Response<String> response) {
                mStateView.showRetry();
            }
        });
    }

    private void refreshAdapter(boolean isReset) {
        if (isReset) {
            shopNewsAdapter.setSpanSizeLookup((gridLayoutManager, position) -> {
                int itemViewType = shopNewsAdapter.getItemViewType(position);
                int itemType = dataAllList.get(position).getItemType();
                if (itemType == 1) {
                    return ShopNewsAdapter.TYPE_1;
                } else if (itemType == 2) {
                    return ShopNewsAdapter.TYPE_2;
                } else {
                    return ShopNewsAdapter.TYPE_3;
                }
            });
            shopNewsAdapter.addData(dataAllList);
        } else {
            shopNewsAdapter.setNewData(dataAllList);
        }
    }

    private void refreshNewsList(String position) {
        Map<String, String> params = new HashMap<>();
        params.put("id", position);
        params.put("page", "1");

        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.getNewsList, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                refreshLayout.finishRefresh();
                ShopNewsListBean shopNewsListBean = FastJsonUtil.fromJson(response.body(), ShopNewsListBean.class);
                if (!shopNewsListBean.getCode().equals(Constant.REQUEST_SUCCESS) || shopNewsListBean.getResult() == null) {
                    XToast.normal(shopNewsListBean.getMessage());
                    mStateView.showRetry();
                    return;
                }

                List<ShopNewsListBean.ResultBean> result = shopNewsListBean.getResult();
                if (result.size() == 0) {
                    mStateView.showEmpty().setOnClickListener(v -> loadData());
                    return;
                }
                if (result.size() < 10)
                    refreshLayout.setEnableLoadMore(false);
                mStateView.showContent();
                dataAllList.clear();
                dataAllList.addAll(addType(result));
                refreshAdapter(true);
            }

            @Override
            public void failed(Response<String> response) {
                mStateView.showRetry();
                refreshLayout.finishRefresh();
            }
        });
    }

    private List<ShopNewsListBean.ResultBean> addType(List<ShopNewsListBean.ResultBean> dataList) {
        for (int i = 0; i < dataList.size(); i++) {
            try {
                List<String> article_thumb = dataList.get(i).getArticle_thumb();
                if (article_thumb.size() == 1 || article_thumb.size() == 2) {
                    dataList.get(i).setItemType(1);
                } else if (article_thumb.size() == 3) {
                    dataList.get(i).setItemType(3);
                } else {
                    dataList.get(i).setItemType(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
                dataList.get(i).setItemType(0);
            }
        }
        return dataList;
    }

}
