package com.tsyc.tianshengyoucai.ui.activity.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.RelativeLayout;

import com.classic.common.MultipleStatusView;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.home.HomSearchAdapter;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.SearchGoodVo;
import com.youth.xframe.widget.XToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * author：van
 * CreateTime：2019/9/10
 * File description：畅销品
 */
public class SaleGoodActivity extends BaseActivity {

    private int page = 1;
    private boolean isLoadMore = false;
    private HomSearchAdapter mAdapter;
    private SmartRefreshLayout refreshLayout;
    private MultipleStatusView mMultipleStatusView;
    private RecyclerView recyclerView;
    private List<SearchGoodVo.ResultBean.DataBean> dataAllList = new ArrayList<>();

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_sale_good;
    }

    @Override
    public void initView() {
        RelativeLayout mRlBack = (RelativeLayout) findViewById(R.id.rl_back);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mMultipleStatusView = (MultipleStatusView) findViewById(R.id.multiple_status_view);
        mRlBack.setOnClickListener(this);
        mTvTitle.setText("畅销品");

//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        initAdapter();
        refreshLayout.setOnLoadMoreListener(refreshLayout12 -> {
            page++;
            requestList(page, true);
        });

        refreshLayout.setOnRefreshListener(refreshLayout1 -> {
            page =1;
            refreshDataList(page);
        });

        refreshLayout.autoRefresh();
        mMultipleStatusView.setOnRetryClickListener(v -> refreshLayout.autoRefresh());
    }

    @Override
    public void initData() {
        requestList(page, isLoadMore);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;

            default:
                break;
        }
    }

    //请求数据
    private void requestList(int page, boolean isLoadMore) {
        HashMap<String, String> map = new HashMap<>();
        map.put("keyword", "");
        map.put("page", String.valueOf(page));
        map.put("sort_type", "sales");
        map.put("sort_price", "0");
        map.put("category_id", "");
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.getUrl(mContext, R.string.http_home_seach),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {
                        refreshLayout.finishLoadMore();
                        NormalBean vos = GsonUtils.getGson(response.body(), NormalBean.class);
                        if (vos.getCode().equals("100")) {
                            T.showToast(mContext, vos.getMessage());
                            mMultipleStatusView.showError();
                            return;
                        }
                        SearchGoodVo vo = GsonUtils.getGson(response.body(), SearchGoodVo.class);
                        if (vo == null) {
                            XToast.normal(getString(R.string.service_error));
                            mMultipleStatusView.showError();
                            return;
                        }
                        if (vo.getResult().getData() == null) {
                            XToast.normal(vo.getMessage());
                            mMultipleStatusView.showError();
                            return;
                        }

                        if (isLoadMore) {
                            List<SearchGoodVo.ResultBean.DataBean> dataList = vo.getResult().getData();
                            if (dataList.isEmpty()) {
                                refreshLayout.setNoMoreData(true);
                                refreshLayout.autoLoadMoreAnimationOnly();
                            } else {
                                dataAllList.addAll(dataList);
                                mAdapter.notifyDataSetChanged();
                            }

                        } else {
                            List<SearchGoodVo.ResultBean.DataBean> dataList = vo.getResult().getData();
                            if (dataList.isEmpty()) {
                                mMultipleStatusView.showEmpty();
                                return;
                            }
                            mMultipleStatusView.showContent();
                            dataAllList.clear();
                            dataAllList.addAll(dataList);
                            mAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void failed(Response<String> response) {
                        XToast.normal(getString(R.string.service_error));
                        refreshLayout.finishLoadMore();
                    }
                });
    }

    //刷新列表
    private void refreshDataList(int page) {
        HashMap<String, String> map = new HashMap<>();
        map.put("keyword", "");
        map.put("page", String.valueOf(page));
        map.put("sort_type", "sales");
        map.put("sort_price", "0");
        map.put("category_id", "");
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.getUrl(mContext, R.string.http_home_seach),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {
                        refreshLayout.finishRefresh();
                        NormalBean vos = GsonUtils.getGson(response.body(), NormalBean.class);
                        if (vos.getCode().equals("100")) {
                            T.showToast(mContext, vos.getMessage());
                            mMultipleStatusView.showError();
                            return;
                        }
                        SearchGoodVo vo = GsonUtils.getGson(response.body(), SearchGoodVo.class);
                        if (vo == null) {
                            XToast.normal(getString(R.string.service_error));
                            mMultipleStatusView.showError();
                            return;
                        }
                        if (vo.getResult().getData() == null) {
                            XToast.normal(vo.getMessage());
                            mMultipleStatusView.showError();
                            return;
                        }

                        List<SearchGoodVo.ResultBean.DataBean> dataList = vo.getResult().getData();
                        if (dataList.isEmpty()) {
                            mMultipleStatusView.showEmpty();
                            return;
                        }
                        mMultipleStatusView.showContent();
                        dataAllList.clear();
                        dataAllList.addAll(dataList);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void failed(Response<String> response) {
                        XToast.normal(getString(R.string.service_error));
                        refreshLayout.finishRefresh();
                    }
                });
    }


    private void initAdapter() {
        mAdapter = new HomSearchAdapter(mContext, dataAllList);
        RecyclerUtil.setGridManage(mContext, recyclerView, 2, mAdapter);
        mAdapter.setListener((position, bean) -> mResultTo.startGoodDetail(mContext, bean));

    }

}
