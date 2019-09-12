package com.tsyc.tianshengyoucai.ui.fragment.sub;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.classic.common.MultipleStatusView;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.model.adapter.RedBagListAdapter;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.model.bean.RelRedBagBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.ReceiveDetailActivity;
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
 * File description： 优惠券 红包 列表
 */
public class RedBagListFragment extends BaseFragment {


    private static final int FIRST_REQUEST_DATA = 1001;
    private static final int REFRESH_LIST_DATA = 1003;

    @BindView(R.id.tv_look_bag)
    TextView tv_look_bag;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.multiple_status_view)
    MultipleStatusView mMultipleStatusView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;


    private int page = 1;
    private boolean isLoadMore = false;
    private  String itemPosition;
    private List<RelRedBagBean.ResultBean> dataAllList = new ArrayList<>();
    private RedBagListAdapter orderAdapter;

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
        return R.layout.fragment_order;
    }

    @Override
    protected void loadData() {

        mMultipleStatusView.showLoading();
        Bundle arguments = getArguments();
        if (arguments == null) {
            mMultipleStatusView.showEmpty();
            return;
        }
        String title = arguments.getString("title");
        itemPosition = arguments.getString("position");

        Message message = new Message();
        message.what = FIRST_REQUEST_DATA;
        message.obj = itemPosition;
        mHandler.sendMessage(message);
    }


    @Override
    public void initData() {
        tv_look_bag.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            isLoadMore = false;
            mHandler.sendEmptyMessage(REFRESH_LIST_DATA);
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            page++;
            isLoadMore = true;
            mHandler.sendEmptyMessage(FIRST_REQUEST_DATA);
        });
        mMultipleStatusView.setOnRetryClickListener(v -> loadData());

    }

    private void requestListData(boolean isLoadMore, int page, String itemPosition) {
        Map<String, String> params = new HashMap<>();
        params.put("type", String.valueOf(itemPosition)); //1 可领取 2 已领取
        params.put("page", String.valueOf(page));

        XLog.e("请求数据  " + itemPosition);

        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.redBagList, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {

                RelRedBagBean redBagBean = FastJsonUtil.fromJson(response.body(), RelRedBagBean.class);
                if (null == redBagBean) {
                    mMultipleStatusView.showError();
                    refreshLayout.finishLoadMore();
                    return;
                }
                if (!redBagBean.getCode().equals(Constant.REQUEST_SUCCESS) || null == redBagBean.getResult()) {
                    mMultipleStatusView.showError();
                    refreshLayout.finishLoadMore();
                    return;
                }

                if (isLoadMore) {
                List<RelRedBagBean.ResultBean> dataList = redBagBean.getResult();
                    if (null != dataList && dataList.size() > 0) {
                        dataAllList.addAll(dataList);
                        refreshLayout.finishLoadMore();
                    } else {
                        refreshLayout.setNoMoreData(true);
                        refreshLayout.autoLoadMoreAnimationOnly();
                    }
                    refreshAdapter(false);
                } else {
                List<RelRedBagBean.ResultBean> dataList = redBagBean.getResult();
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
            orderAdapter = new RedBagListAdapter(dataAllList);
            recyclerView.setAdapter(orderAdapter);
            orderAdapter.setOnItemChildClickListener((adapter, view, position) -> cancelCoupon(position));
            orderAdapter.setOnItemClickListener((adapter, view, position) -> {
                int couponId = dataAllList.get(position).getVoucher_id();
                Bundle bundle = new Bundle();
                bundle.putString("id", String.valueOf(couponId));
                bundle.putString("type", "red");
                openActivity(ReceiveDetailActivity.class, bundle);
//                receiveDetail(position)
            });
        } else {
            orderAdapter.notifyDataSetChanged();
        }

    }


    private void refreshListData(int page, String itemPosition) {
        Map<String, String> params = new HashMap<>();
        params.put("type", String.valueOf(itemPosition));
        params.put("page", String.valueOf(page));
        XLog.e("刷新数据  " + itemPosition);
        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.redBagList, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                refreshLayout.finishRefresh();
                RelRedBagBean redBagBean = FastJsonUtil.fromJson(response.body(), RelRedBagBean.class);
                if (null == redBagBean) {
                    return;
                }
                if (!redBagBean.getCode().equals(Constant.REQUEST_SUCCESS) || null == redBagBean.getResult()) {
                    return;
                }

                List<RelRedBagBean.ResultBean> dataList = redBagBean.getResult();
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

    //取消红包
    private void cancelCoupon(int position) {
        int couponId = dataAllList.get(position).getVoucher_id();
        Map<String, String> params = new HashMap<>();
        params.put("voucher_id", String.valueOf(couponId));
        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.cancelCouponBag, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("取消优惠券  " + response.body());
                NormalBean normalBean = FastJsonUtil.fromJson(response.body(), NormalBean.class);
                if (null == normalBean) {
                    XToast.normal("取消失败");
                    return;
                }
                if (!normalBean.getCode().equals("200")) {
                    XToast.normal("取消失败");
                    return;
                }
                XToast.normal("取消成功");
                dataAllList.remove(position);
                orderAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(Response<String> response) {
                XToast.normal("取消失败");

            }
        });
    }

    //领取详情
    private void receiveDetail(int position) {

        int voucher_id = dataAllList.get(position).getVoucher_id();
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(voucher_id));

        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.receiveDetail, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("领取详情" + response.body());
            }

            @Override
            public void failed(Response<String> response) {
              //  XLog.e("领取失败" + response.getException().toString());

            }
        });


    }

}
