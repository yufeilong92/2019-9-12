package com.tsyc.tianshengyoucai.ui.fragment.sub.order;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.listener.event.OrderDetailInEvent;
import com.tsyc.tianshengyoucai.listener.event.UnifiedNotifyEvent;
import com.tsyc.tianshengyoucai.model.adapter.OnlineOrderAdapter;
import com.tsyc.tianshengyoucai.model.bean.NormalResultBean;
import com.tsyc.tianshengyoucai.model.bean.UnderlineOrderBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.ApplyBackMoneyActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.EvaluateActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.LookLogisticsActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.OrderDetailActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.OrderPayActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.SendOrderActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseFragment;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * author：van
 * CreateTime：2019/7/29
 * File description： 线上订单
 */
public class OnlineOrderFragment extends BaseFragment {

    private static final int FIRST_REQUEST_DATA = 1001;
    private static final int REFRESH_LIST_DATA = 1003;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private int page = 1;
    private boolean isLoadMore = false;
    private String itemPosition;
    private List<UnderlineOrderBean.ResultBean.DataBean> dataAllList = new ArrayList<>();
    private OnlineOrderAdapter onlineOrderAdapter;

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
        return R.layout.fragment_online_order;
    }

    @Override
    protected void loadData() {
        mStateView.showLoading();
        Bundle arguments = getArguments();
        if (arguments == null) {
            mStateView.showEmpty();
            return;
        }
        String title = arguments.getString("title");
        itemPosition = arguments.getString("position");
        mHandler.sendEmptyMessage(REFRESH_LIST_DATA);
    }

    @Override
    public void initView(View rootView) {

    }

    @Override
    public void initData() {

        registerEventBus(OnlineOrderFragment.this);
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


    private void refreshListData(int page, String itemPosition) {
        Map<String, String> params = new HashMap<>();
        params.put("status", String.valueOf(itemPosition));
        params.put("page", String.valueOf(page));
        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.online_list, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e(itemPosition + "刷新数据 " + response.body());
                refreshLayout.finishRefresh();
                UnderlineOrderBean orderBean = FastJsonUtil.fromJson(response.body(), UnderlineOrderBean.class);
                if (null == orderBean) {
                    return;
                }
                if (!orderBean.getCode().equals(Constant.REQUEST_SUCCESS) || null == orderBean.getResult()) {
                    T.showToast(mContext, orderBean.getMessage());
                    return;
                }
                List<UnderlineOrderBean.ResultBean.DataBean> dataList = orderBean.getResult().getData();
                if (null == dataList || dataList.size() == 0) {
                    mStateView.showEmpty().setOnClickListener(v -> loadData());
                    return;
                }
                mStateView.showContent();
                dataAllList.clear();
                dataAllList.addAll(dataList);
                refreshAdapter(true);
                refreshLayout.setEnableLoadMore(true);

            }

            @Override
            public void failed(Response<String> response) {
                XToast.normal(getString(R.string.service_error));
                refreshLayout.finishRefresh();
            }
        });
    }


    private void requestListData(boolean isLoadMore, int page, String itemPosition) {
        Map<String, String> params = new HashMap<>();
        params.put("status", String.valueOf(itemPosition));
        params.put("page", String.valueOf(page));

        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.online_list, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {

                XLog.e("请求数据 " + response.body());
                refreshLayout.finishLoadMore();
                UnderlineOrderBean orderBean = FastJsonUtil.fromJson(response.body(), UnderlineOrderBean.class);
                if (null == orderBean) {
                    return;
                }
                if (!orderBean.getCode().equals(Constant.REQUEST_SUCCESS) || null == orderBean.getResult()) {
                    T.showToast(mContext, orderBean.getMessage());
                    return;
                }

                if (isLoadMore) {
                    List<UnderlineOrderBean.ResultBean.DataBean> dataList = orderBean.getResult().getData();
                    if (null != dataList && dataList.size() > 0) {
                        dataAllList.addAll(dataList);
                    } else {
                        refreshLayout.setNoMoreData(true);
                        refreshLayout.setEnableLoadMore(false);
                        refreshLayout.autoLoadMoreAnimationOnly();
                    }
                    refreshAdapter(false);
                } else {
                    List<UnderlineOrderBean.ResultBean.DataBean> dataList = orderBean.getResult().getData();
                    if (null == dataList || dataList.size() == 0) {
                        mStateView.showEmpty().setOnClickListener(v -> loadData());
                        return;
                    }
                    mStateView.showContent();
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
        if (onlineOrderAdapter != null) {
            onlineOrderAdapter.notifyDataSetChanged();
            return;
        }
        if (isReset) {
            onlineOrderAdapter = new OnlineOrderAdapter(dataAllList);
            recyclerView.setAdapter(onlineOrderAdapter);
            onlineOrderAdapter.setOnItemClickListener((adapter, view, position) -> {
                UnderlineOrderBean.ResultBean.DataBean dataBean = dataAllList.get(position);
                String order_sn = dataBean.getOrder_sn();
                int order_id = dataBean.getOrder_id();
                Bundle bundle = new Bundle();
                bundle.putString("goods_id", String.valueOf(order_id));
                bundle.putString("order_sn", order_sn);
                openActivity(OrderDetailActivity.class, bundle);
            });
            onlineOrderAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                if (dataAllList.get(position) != null) {
                    adapterChildItemClickLister(view, position);
                } else {
                    XToast.normal("订单状态异常,请刷新重试");
                }
            });
        } else {
            onlineOrderAdapter.notifyDataSetChanged();
        }

    }

    private void adapterChildItemClickLister(View view, int position) {
        UnderlineOrderBean.ResultBean.DataBean dataBean = dataAllList.get(position);
        int status = dataBean.getOrder_state();
        int order_id = dataBean.getOrder_id();
        int total_goods_num = dataBean.getTotal_goods_num();
        String order_sn = dataBean.getOrder_sn();
        String order_amount = dataBean.getOrder_amount();
        String goodsImage = dataBean.getOrdergoods().get(0).getGoods_image();
        String goods_name = dataBean.getOrdergoods().get(0).getGoods_name();
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.tv_order_gray_status:
                XLog.e("订单状态  " + status);
                switch (status) {
                    case -1:
                    case 0:
                    case 40:
                    case 50:
                    case 100:
                        dealOrder("delete", order_id, position);
                        break;

                    case 10:
                        dealOrder("cancelOrder", order_id, position); // 待付款 取消订单
                        break;

                    case 20:
                    case 30:
                        bundle.putString("order_id", String.valueOf(order_id));
                        bundle.putString("order_in", "online_order");
                        openActivity(ApplyBackMoneyActivity.class, bundle); // 待发货 申请退货
                        break;

                    case 101:
                        // orderType = "等待商家审核";
                        bundle.putString("goods_id", String.valueOf(order_id));
                        bundle.putString("order_sn", order_sn);
                        bundle.putString("type", itemPosition);
                        openActivity(OrderDetailActivity.class, bundle);
                        break;
                    case 103:
                        // orderType = "等待商家收货";
                        bundle.putString("goods_id", String.valueOf(order_id));
                        bundle.putString("order_sn", order_sn);
                        bundle.putString("type", String.valueOf(itemPosition));
                        openActivity(OrderDetailActivity.class, bundle);
                        break;
                }
                break;

            case R.id.tv_order_red_status:
                switch (status) {
                    case 10:
                        bundle.putString("order_sn", order_sn);
                        bundle.putString("order_type", "online");
                        bundle.putString("order_price", order_amount);
                        bundle.putString("order_in", "online_order");
                        openActivity(OrderPayActivity.class, bundle);
                        break;

                    case 30:
                        dealOrder("config_rec", order_id, position);
                        break;

                    case 40: // 去评价
                        bundle.putString("goods_image", goodsImage);
                        bundle.putString("goods_name", goods_name);
                        bundle.putString("order_id", String.valueOf(order_id));
                        bundle.putString("order_in", "online_order");
                        openActivity(EvaluateActivity.class, bundle);
                        break;

                    case 102: // 带退货

                        bundle.putString("goods_id", String.valueOf(order_id));
                        bundle.putString("order_in", "online_order");
                        openActivity(SendOrderActivity.class, bundle);
                        break;
                }
                break;

            case R.id.tv_order_gray_logistic: // 查看物流
                if (status == 30) {
                    bundle.putString("goods_id", String.valueOf(order_id));
                    openActivity(LookLogisticsActivity.class, bundle);
                }
                break;

            default:
                break;
        }
    }

    /**
     * 处理订单 状态
     *
     * @param dealType delete 删除
     * @param order_id
     * @param position
     */
    private void dealOrder(String dealType, int order_id, int position) {

        String url = "";
        switch (dealType) {

            case "delete":
                url = UrlManager.del_order;
                break;

            case "cancelOrder":
                url = UrlManager.cancel_order;
                break;
            case "waitSendBack":
                url = UrlManager.refund_submit;
                break;

            case "waitRecBack":
                url = UrlManager.refund_submit;
                break;

            case "back_detail":
                url = UrlManager.refund_submit;
                break;

            case "config_rec":
                url = UrlManager.confirm_receive;
                break;
            default:
                break;
        }

        Map<String, String> params = new HashMap<>();

        loading(true);
        params.put("order_id", String.valueOf(order_id));
        BaseRequestUtils.postRequestWithHeader(mActivity, url, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("用户点击收货: " + response.body());
                NormalResultBean resultBean = FastJsonUtil.fromJson(response.body(), NormalResultBean.class);
                dismiss();
                if (null == resultBean) {
                    XToast.normal("操作失败，请稍候再试");
                    return;
                }
                if (!resultBean.getCode().equals(Constant.REQUEST_SUCCESS)) {
                    XToast.normal(resultBean.getMessage());
                    return;
                }

                XToast.normal(resultBean.getMessage());
                dataAllList.remove(position);
                mHandler.sendEmptyMessage(REFRESH_LIST_DATA);
            }

            @Override
            public void failed(Response<String> response) {
                dismiss();
                XToast.normal(getString(R.string.service_error));
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void applyBackMoneyRefresh(UnifiedNotifyEvent events) {
        int eventCode = events.getEventCode();
        if (eventCode == Constant.APPLY_BACK_MONEY) {
            mHandler.sendEmptyMessage(REFRESH_LIST_DATA);
        } /*else if (eventCode == Constant.REFRESH_ORDER_LIST) {
            mHandler.sendEmptyMessage(REFRESH_LIST_DATA);
        }*/
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void doInDetailSuccess(OrderDetailInEvent event){
        String msg = event.getMsg();
        if (msg.equals("ok")){
            mHandler.sendEmptyMessage(REFRESH_LIST_DATA);
        }
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        mHandler.sendEmptyMessage(REFRESH_LIST_DATA);
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unregisterEventBus(OnlineOrderFragment.this);
    }
}
