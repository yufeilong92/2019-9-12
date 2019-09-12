package com.tsyc.tianshengyoucai.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.classic.common.MultipleStatusView;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.model.adapter.FindAdapter;
import com.tsyc.tianshengyoucai.model.bean.FindBean;
import com.tsyc.tianshengyoucai.model.bean.GoodsDetailBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.activity.home.GoodsDetailActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseFragment;
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
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * author：cxd
 * CreateTime：2019/7/18
 * File description：发现
 */
public class FindFragment extends BaseFragment {

    private final int LOAD_FIND_DATA = 1001;
    private final int REFRESH_FIND_DATA = 1002;
    @BindView(R.id.ctl_search)
    LinearLayout ctlSearch;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.multiple_status_view)
    MultipleStatusView mMultipleStatusView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_sort_all)
    TextView tvSortAll;
    @BindView(R.id.iv_sort_all)
    ImageView ivSortAll;
    @BindView(R.id.tv_sort_sale)
    TextView tvSortSale;
    @BindView(R.id.iv_sort_sale)
    ImageView ivSortSale;
    @BindView(R.id.tv_sort_price)
    TextView tvSortPrice;
    @BindView(R.id.iv_sort_price)
    ImageView ivSortPrice;


    private int page = 1;
    //排序类型 all（综合排序）sales（销量排序）price（价格排序）
    //1（按价格降序排列） 0 （按价格升序排列 ）
    private String keyWord, sortType, sortPrice;
    private List<FindBean.ResultBean.DataBean> dataList;
    private List<FindBean.ResultBean.DataBean> dataAllList = new ArrayList<>();
    private FindAdapter findAdapter;
    private boolean isLoadMore = false;
    private View footerView;

    public static FindFragment getInstance() {
        return new FindFragment();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case LOAD_FIND_DATA:
                    loadFindData(isLoadMore, keyWord, sortType, sortPrice, page);
                    break;

                case REFRESH_FIND_DATA:
                    page = 1;
                    isLoadMore = false;
                    refreshFindData(keyWord, sortType, sortPrice, page);
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_find;
    }

    @Override
    protected void loadData() {

        mMultipleStatusView.showLoading();
        mHandler.sendEmptyMessage(LOAD_FIND_DATA);
    }

    @Override
    public void initData() {
        footerView = LayoutInflater.from(mContext).inflate(R.layout.layout_home_footer,
                (ViewGroup) recyclerView.getParent(), false);
        //recyclerView.addItemDecoration(new SimpleDividerItemDecortion(mContext));
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        recyclerView.setHasFixedSize(true);

        refreshLayout.setOnRefreshListener(refreshLayout -> {
            mHandler.sendEmptyMessage(REFRESH_FIND_DATA);
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            isLoadMore = true;
            page++;
            mHandler.sendEmptyMessage(LOAD_FIND_DATA);
        });
        mMultipleStatusView.setOnRetryClickListener(v -> loadData());

    }

    @OnClick({R.id.ctl_search, R.id.tv_sort_all, R.id.tv_sort_sale, R.id.tv_sort_price})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ctl_search:
                break;

            case R.id.tv_sort_all:
                selectView(0);
                XToast.normal("1");
                break;

            case R.id.tv_sort_sale:
                selectView(1);
                XToast.normal("2");
                break;

            case R.id.tv_sort_price:
                selectView(2);
                XToast.normal("3");
                break;

            default:
                break;
        }
    }

    private void selectView(int position) {
        tvSortAll.setTextColor(getResources().getColor(R.color.color_7B8391));
        ivSortAll.setVisibility(View.INVISIBLE);
        tvSortSale.setTextColor(getResources().getColor(R.color.color_7B8391));
        ivSortSale.setVisibility(View.INVISIBLE);
        tvSortPrice.setTextColor(getResources().getColor(R.color.color_7B8391));
        ivSortPrice.setVisibility(View.INVISIBLE);
        switch (position) {
            case 0:
                tvSortAll.setTextColor(getResources().getColor(R.color.tab_color));
                ivSortAll.setVisibility(View.VISIBLE);
                break;
            case 1:
                tvSortSale.setTextColor(getResources().getColor(R.color.tab_color));
                ivSortSale.setVisibility(View.VISIBLE);
                break;
            case 2:
                tvSortPrice.setTextColor(getResources().getColor(R.color.tab_color));
                ivSortPrice.setVisibility(View.VISIBLE);
                break;
        }
        //getListData(position);
    }

    private void loadFindData(boolean isLoadMore, String keyWord, String sortType, String sortPrice, int page) {

        Map<String, String> params = new HashMap<>();
        params.put("keyword", keyWord);
        params.put("sort_type", sortType);
        params.put("sort_price", sortPrice);
        params.put("page", String.valueOf(page));

        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.find, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                FindBean findBean = FastJsonUtil.fromJson(response.body(), FindBean.class);
                if (null == findBean) {
                    mMultipleStatusView.showError();
                    refreshLayout.finishLoadMore();
                    return;
                }
                if (!findBean.getCode().equals(Constant.REQUEST_SUCCESS) || null == findBean.getResult()) {
                    mMultipleStatusView.showError();
                    refreshLayout.finishLoadMore();
                    return;
                }

                dataList = findBean.getResult().getData();
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
                    // dataList = findBean.getResult().getData();
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
            findAdapter = new FindAdapter(dataAllList);
            recyclerView.setAdapter(findAdapter);
            findAdapter.setOnItemClickListener((adapter, view, position) -> {
                FindBean.ResultBean.DataBean dataBean = dataAllList.get(position);
                int goods_id = dataBean.getGoods_id();
                Bundle bundle = new Bundle();
                bundle.putString("goods_id",String.valueOf(goods_id));
                openActivity(GoodsDetailActivity.class,bundle);
            });
        } else {
            findAdapter.notifyDataSetChanged();
        }
//        findAdapter.removeFooterView(footerView);
//        findAdapter.addFooterView(footerView);

    }


    private void refreshFindData(String keyWord, String sortType, String sortPrice, int page) {
        Map<String, String> params = new HashMap<>();
        params.put("keyword", keyWord);
        params.put("sort_type", sortType);
        params.put("sort_price", sortPrice);
        params.put("page", String.valueOf(page));

        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.find, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                FindBean findBean = FastJsonUtil.fromJson(response.body(), FindBean.class);
                if (null == findBean) {
                    refreshLayout.finishRefresh();
                    return;
                }
                if (!findBean.getCode().equals(Constant.REQUEST_SUCCESS) || null == findBean.getResult()) {
                    refreshLayout.finishRefresh();
                    return;
                }

                dataList = findBean.getResult().getData();
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

}
