package com.tsyc.tianshengyoucai.ui.fragment.sub;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tsyc.tianshengyoucai.Eventbuss.OrderRefreshEvent;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.model.adapter.OrderManageAdapter;
import com.tsyc.tianshengyoucai.model.bean.NormalResultBean;
import com.tsyc.tianshengyoucai.model.bean.OrderManageBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.SecondActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.LookLogisticsActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.ScanOrderActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.SendOrderActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.StoreCashierOrderDetailActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.StoreOrderDetailActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseFragment;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.uuzuche.lib_zxing.activity.CodeUtils;
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

import static android.app.Activity.RESULT_OK;

/**
 * author：van
 * CreateTime：2019/7/29
 * File description：店铺 订单管理
 */
public class OrderManageFragment extends BaseFragment {


    private static final int FIRST_REQUEST_DATA = 1001;
    private static final int REFRESH_LIST_DATA = 1003;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;


    private int mPage = 1;
    private boolean isLoadMore = false;
    private List<OrderManageBean.ResultBean.DataBean> dataAllList = new ArrayList<>();
    private OrderManageAdapter orderManageAdapter;

    private String itemPosition;
    private String title;
    private String type; // 1 线上  2 线下

//    @SuppressLint("HandlerLeak")
//    private Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//
//            switch (msg.what) {
//                case FIRST_REQUEST_DATA:
//                    requestListData(isLoadMore, page, itemPosition);
//                    break;
//
//                case REFRESH_LIST_DATA:
//                    page = 1;
//                    refreshListData(page, itemPosition);
//                    break;
//
//                default:
//                    break;
//            }
//        }
//    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            title = arguments.getString("title");
            itemPosition = arguments.getString("position");
            type = arguments.getString("type");
        }

    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_manage;
    }

    @Override
    protected void loadData() {
        mStateView.showLoading();
        orderManageAdapter = new OrderManageAdapter();
        recyclerView.setAdapter(orderManageAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);

        refreshLayout.setOnRefreshListener(refreshLayout -> {
            isLoadMore = false;
            refreshListData(1, itemPosition);
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            isLoadMore = true;
            requestListData(isLoadMore, mPage, itemPosition);
        });
        mStateView.setOnRetryClickListener(this::loadData);
        refreshListData(1, itemPosition);
    }


    private void requestListData(boolean isLoadMore, int page, String itemPosition) {
        Map<String, String> params = new HashMap<>();
        params.put("order_type", type);
        params.put("page", String.valueOf(page));
        if (itemPosition.equals("3"))
            params.put("status", "4");
        else
            params.put("status", itemPosition);

        if (type != null) {
            if (type.equals("2")) {
                if (title.equals("待核销")) {
                    params.put("status", "1");
                } else if (title.equals("已核销")) {
                    params.put("status", "3");
                } else { // 退款
                    params.put("status", "4");
                }
            }
        }
        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.shopManage, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e(itemPosition + "-" + type + " - 订单管理  " + response.body());
                refreshLayout.finishLoadMore();
                OrderManageBean orderManageBean = FastJsonUtil.fromJson(response.body(), OrderManageBean.class);
                if (!orderManageBean.getCode().equals(Constant.REQUEST_SUCCESS) || null == orderManageBean.getResult()) {
                    XToast.normal(orderManageBean.getMessage());
                    return;
                }
                List<OrderManageBean.ResultBean.DataBean> dataList = orderManageBean.getResult().getData();
                mStateView.showContent();
                if (null != dataList && dataList.size() > 0) {
                    ++mPage;
                    dataAllList.addAll(dataList);
                } else {
                    refreshLayout.setEnableLoadMore(false);
                }
                refreshAdapter(false);

            }

            @Override
            public void failed(Response<String> response) {
                refreshLayout.finishLoadMore();
                toastErrorNet();
            }
        });
    }

    private void refreshAdapter(boolean isReset) {

        if (dataAllList == null) {
            return;
        }
        if (isReset) {
            //  orderManageAdapter = new OrderManageAdapter(dataAllList);
            //  recyclerView.setAdapter(orderManageAdapter);
            orderManageAdapter.setNewData(dataAllList);
            orderManageAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                if (type.equals("1")) {
                    onlineOrderStatusClick(view, position);
                } else {
                    underlineOrderStatusClick(view, position);
                }
            });
            orderManageAdapter.setOnItemClickListener((adapter, view, position) -> {
                int order_id = dataAllList.get(position).getOrder_id();
                int order_state = dataAllList.get(position).getOrder_state();
                String order_sn = dataAllList.get(position).getOrder_sn();

                XLog.e("订单id " + order_id + "__" + order_state + "__" + order_sn);
                Bundle bundle = new Bundle();
                bundle.putString("order_id", String.valueOf(order_id));
                if (type.equals("1")) {
                    bundle.putString("order_type", "1");
                    openActivity(StoreOrderDetailActivity.class, bundle);
                } else {
                    bundle.putString("order_type", "2");
                    openActivity(StoreCashierOrderDetailActivity.class, bundle);
                }

            });
        } else {
            orderManageAdapter.addData(dataAllList);
            //orderManageAdapter.notifyDataSetChanged();
        }

    }

    //刷新数据
    private void refreshListData(int page, String itemPosition) {
        Map<String, String> params = new HashMap<>();
        params.put("order_type", type);

        if (itemPosition.equals("3"))
            params.put("status", "4");
        else
            params.put("status", itemPosition);

        params.put("page", String.valueOf(page));
        if (type != null) {
            if (type.equals("2")) {
                if (title.equals("待核销")) {
                    params.put("status", "1");
                } else if (title.equals("已核销")) {
                    params.put("status", "3");
                } else { // 退款
                    params.put("status", "4");
                }
            }
        }

        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.shopManage, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                refreshLayout.finishRefresh();
                OrderManageBean orderManageBean = FastJsonUtil.fromJson(response.body(), OrderManageBean.class);
                if (!orderManageBean.getCode().equals(Constant.REQUEST_SUCCESS) || null == orderManageBean.getResult()) {
                    XToast.normal(orderManageBean.getMessage());
                    return;
                }
                List<OrderManageBean.ResultBean.DataBean> dataList = orderManageBean.getResult().getData();
                mStateView.showContent();
                if (null == dataList || dataList.size() == 0) {
                    mStateView.showEmpty().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loadData();
                        }
                    });
                    return;
                }
                OrderManageBean.ResultBean result = orderManageBean.getResult();
                dataAllList.clear();
                dataAllList.addAll(dataList);
                if (dataAllList.size() >= result.getTotal()) {
                    refreshLayout.setEnableLoadMore(false);
                } else {
                    mPage = orderManageBean.getResult().getCurrent_page() + 1;
                    refreshLayout.setEnableLoadMore(true);
                }
                refreshAdapter(true);
            }

            @Override
            public void failed(Response<String> response) {
                XToast.normal("刷新失败");
                refreshLayout.finishRefresh();
            }
        });
    }

    //线下订单处理
    private void underlineOrderStatusClick(View view, int position) {
        OrderManageBean.ResultBean.DataBean dataBean = dataAllList.get(position);
        int order_state = dataBean.getOrder_state();
        String orderSn = dataBean.getOrder_sn();
        int order_id = dataBean.getOrder_id();


        switch (view.getId()) {
            case R.id.tv_order_gray_status:
                XLog.e("tv_order_gray_status 订单状态 :" + order_state);
                switch (order_state) {
                    case 40:
                    case 50:
                    case 100:
                        deleteOrder(order_id,2,position);
                        break;
                    case 101: // 不同意退款
                        confirm_refund(order_id, 2, position);
                        break;

                    case 102: // 确认收货
                        confirm_refund(order_id, 2, position);
                        break;
                }
                break;

            case R.id.tv_order_red_status:
                Bundle bundle = new Bundle();
                XLog.e("tv_order_red_status 订单状态 :" + order_state);
                switch (order_state) {
                    case 20://核销

                        openActivity(SecondActivity.class, null, Constant.CASHIER_SCAN);
                        break;

                    case 101: // 同意退款
                        confirm_refund(order_id, 1, position);
                        break;
                }
                break;

            default:
                break;
        }
    }

    // 线上订单处理
    private void onlineOrderStatusClick(View view, int position) {
        OrderManageBean.ResultBean.DataBean dataBean = dataAllList.get(position);
        int order_state = dataBean.getOrder_state();
        String orderSn = dataBean.getOrder_sn();
        int order_id = dataBean.getOrder_id();

        switch (view.getId()) {
            case R.id.tv_order_gray_status:
                XLog.e("订单状态 :" + order_state + "  " + orderSn + " -");

                switch (order_state) {
                    case 20://去发货
                        XToast.normal("去发货1");
                        break;

                    case 30://查看物流
                        XToast.normal("查看物流1");
                        break;
                    case 0:
                    case -1:
                    case 40:
                    case 50:
                        deleteOrder(order_id,2,position);
                        break;
                    case 100: // 退款完成
                        deleteOrder(order_id, 2, position);
                        break;

                    case 101: // 不同意退款
                        confirm_refund(order_id, 2, position);
                        break;

                    case 103: // 确认收货
                        confirm_receive(order_id, 2, position);
                        break;
                }
                break;

            case R.id.tv_order_red_status:
                XLog.e("订单状态 :" + order_state + "  " + orderSn + " - ");
                Bundle bundle = new Bundle();
                switch (order_state) {
                    case 20://去发货
                        bundle.putString("goods_id", String.valueOf(order_id));
                        bundle.putString("send_type", "shop_order");
                        openActivity(SendOrderActivity.class, bundle);
                        break;

                    case 30://查看物流
                        XToast.normal("查看物流");
                        bundle.putString("goods_id", String.valueOf(order_id));
                        openActivity(LookLogisticsActivity.class, bundle);
                        break;
                    case 101: // 同意退款
                        confirm_refund(order_id, 1, position);
                        break;
                }

                break;

            default:
                break;
        }
    }

    //删除线上订单
    private void deleteOrder(int order_id, int i, int position) {

        loading(true);
        Map<String, String> params = new HashMap<>();
        params.put("order_id", String.valueOf(order_id));
        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.del_order, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                dismiss();
                XLog.e("店铺删除完成订单" + response.body());
                NormalResultBean resultBean = FastJsonUtil.fromJson(response.body(), NormalResultBean.class);
                dismiss();
                if (null == resultBean) {
                    XToast.normal(getString(R.string.service_error));
                    return;
                }
                if (!resultBean.getCode().equals(Constant.REQUEST_SUCCESS)) {
                    XToast.normal(resultBean.getMessage());
                    return;
                }

                XToast.normal("删除成功");
                dataAllList.remove(position);
                refreshLayout.autoRefresh();

            }

            @Override
            public void failed(Response<String> response) {

                XToast.normal(getString(R.string.service_error));
                dismiss();
            }
        });

    }

    /**
     * 退货申请
     *
     * @param orderId id
     * @param type    1 同意 2 拒绝
     * @param index   position
     */
    private void confirm_refund(int orderId, int type, int index) {

        Map<String, String> params = new HashMap<>();
        params.put("order_id", String.valueOf(orderId));
        params.put("type", String.valueOf(type));
        loading(true);
        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.confirm_refund, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("---------- " + response.body());
                NormalResultBean resultBean = FastJsonUtil.fromJson(response.body(), NormalResultBean.class);
                dismiss();
                if (null == resultBean) {
                    return;
                }
                if (!resultBean.getCode().equals(Constant.REQUEST_SUCCESS)) {
                    XToast.normal(resultBean.getMessage());
                    return;
                }
                XToast.normal(resultBean.getMessage());
                dataAllList.remove(index);
                orderManageAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(Response<String> response) {
                XToast.normal("网络请求失败，请稍候再试");
                dismiss();
            }
        });
    }

    /**
     * 退货 商家收货
     *
     * @param orderId id
     * @param type    1 同意 2 拒绝
     * @param index   position
     */
    private void confirm_receive(int orderId, int type, int index) {

        Map<String, String> params = new HashMap<>();
        params.put("order_id", String.valueOf(orderId));
        loading(true);
        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.store_confirm_receive, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("确认收货  " + response.body());
                NormalResultBean resultBean = FastJsonUtil.fromJson(response.body(), NormalResultBean.class);
                dismiss();
                if (null == resultBean) {
                    XToast.normal("确认收货失败,请稍候再试");
                    return;
                }
                if (!resultBean.getCode().equals(Constant.REQUEST_SUCCESS)) {
                    XToast.normal(resultBean.getMessage());
                    return;
                }
                XToast.normal(resultBean.getMessage());
                dataAllList.remove(index);
                orderManageAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(Response<String> response) {
                XToast.normal("网络请求失败，请稍候再试");
                dismiss();
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null && resultCode == RESULT_OK) {

            Bundle extra = data.getExtras();
            if (null != extra) {
                String code = extra.getString(CodeUtils.RESULT_STRING);
                XLog.e("扫描结果 ： " + code);

                String finalCode = StringUtil.checkCode(code);
                if (TextUtils.isEmpty(finalCode)) {
                    XToast.normal(getString(R.string.plz_show_code));
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("code", finalCode);
                bundle.putString("type", "order_fragment");
                openActivity(ScanOrderActivity.class, bundle);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void refreshOrderStatus(OrderRefreshEvent event) {
        if (refreshLayout != null) {
            refreshLayout.autoRefresh();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}
