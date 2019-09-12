package com.tsyc.tianshengyoucai.ui.fragment.sub;

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
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.CancelCouponActivity;
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
public class CouponListFragment extends BaseFragment {


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
    private  String itemPosition;
    private List<RelCouponBean.ResultBean> dataList;
    private List<RelCouponBean.ResultBean> dataAllList = new ArrayList<>();
    private CouponListAdapter couponListAdapter;
    private View footer;

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

        XLog.e("ssss " + itemPosition);
        Message message = new Message();
        message.what = FIRST_REQUEST_DATA;
        message.obj = itemPosition;
        mHandler.sendMessage(message);
    }


    @Override
    public void initData() {

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);
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
        mMultipleStatusView.setOnRetryClickListener(v -> loadData());
        footer = LayoutInflater.from(mContext).inflate(R.layout.layout_coupon_footer, (ViewGroup) recyclerView.getParent(), false);

        footer.setOnClickListener(v -> {
            openActivity(CancelCouponActivity.class);
        });

        couponListAdapter = new CouponListAdapter();
        recyclerView.setAdapter(couponListAdapter);
    }

    private void requestListData(boolean isLoadMore, int page, String itemPosition) {

        Map<String, String> params = new HashMap<>();
        params.put("type", String.valueOf(itemPosition)); //1 可领取 2 已领取
        params.put("page", String.valueOf(page));

        XLog.e("请求数据  " + itemPosition);

        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.couponList, params, new BaseRequestUtils.getRequestCallBack() {
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
//            couponListAdapter = new CouponListAdapter(dataAllList);
//            recyclerView.setAdapter(couponListAdapter);
            couponListAdapter.setNewData(dataAllList);
//            couponListAdapter = new CouponListAdapter();
            if (itemPosition != null && itemPosition.equals("1")) {
                couponListAdapter.setFooterView(footer);
            }

            couponListAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                if (view.getId() == R.id.iv_right) {
                    cancelCoupon(position);
                }
            });

            couponListAdapter.setOnItemClickListener((adapter, view, position) -> {

                int couponId = dataAllList.get(position).getVoucher_id();
                Bundle bundle = new Bundle();
                bundle.putString("id", String.valueOf(couponId));
                bundle.putString("type", "red");
                openActivity(ReceiveDetailActivity.class, bundle);
            });

        } else {
            couponListAdapter.addData(dataAllList);

            couponListAdapter.notifyDataSetChanged();
        }

    }

    private void refreshListData(int page, String itemPosition) {
        Map<String, String> params = new HashMap<>();
        params.put("type", String.valueOf(itemPosition));
        params.put("page", String.valueOf(page));
        XLog.e("刷新数据  " + itemPosition);
        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.couponList, params, new BaseRequestUtils.getRequestCallBack() {
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

    //取消
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
                couponListAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(Response<String> response) {
                XToast.normal("取消失败");
                Throwable exception = response.getException();
                exception.printStackTrace();

                XLog.e("取消优惠券 " + exception.getMessage().toString());
            }
        });
    }
}
