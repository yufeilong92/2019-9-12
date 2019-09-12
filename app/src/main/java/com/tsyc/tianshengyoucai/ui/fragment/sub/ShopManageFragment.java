package com.tsyc.tianshengyoucai.ui.fragment.sub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.listener.event.NormalEvent;
import com.tsyc.tianshengyoucai.listener.event.UnifiedNotifyEvent;
import com.tsyc.tianshengyoucai.manager.DataMangerVo;
import com.tsyc.tianshengyoucai.model.adapter.ShopManageAdapter;
import com.tsyc.tianshengyoucai.model.bean.NormalResultBean;
import com.tsyc.tianshengyoucai.model.bean.SMGoodsListBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.ReleaseShopNewActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseFragment;
import com.tsyc.tianshengyoucai.utils.DialogUtils;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
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
 * CreateTime：2019/7/30
 * File description： 商品管理Fragment
 */
public class ShopManageFragment extends BaseFragment {

    private static final int FIRST_REQUEST_DATA = 1001;
    private static final int REFRESH_LIST_DATA = 1003;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private int page = 1;
    private boolean isLoadMore = false;
    private boolean isLoadSuccess = true;

    private ArrayList mArray;
    private String itemPosition = "1";
    private ShopManageAdapter shopManageAdapter;
    private String title;


//    @SuppressLint("HandlerLeak")
//    private Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//
//            switch (msg.what) {
//                case FIRST_REQUEST_DATA:
//                    requestListData(page, itemPosition);
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
            itemPosition = arguments.getString("position");
            title = arguments.getString("title");
        }
        XLog.e("onCreate");
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_manage;
    }

    @Override
    protected void loadData() {
        mStateView.showLoading();
        clearData();
        initEvent();
        initAdapter();
        refreshLayout.autoRefresh();
    }

    private void initEvent() {

    }

    private void initAdapter() {
        shopManageAdapter = new ShopManageAdapter(mArray);
        RecyclerUtil.setGridManage(mActivity, recyclerView, shopManageAdapter);
        shopManageAdapter.setOnItemChildClickListener((adapter, view, position) -> childItemClickListener(adapter, view, position));
        if (itemPosition != null && itemPosition.equals("3")) {
            shopManageAdapter.isEnableDown(false);
        }
    }

    @Override
    public void initData() {
        registerEventBus(ShopManageFragment.this);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);

        refreshLayout.setOnRefreshListener(refreshLayout -> {
            refreshListData(1, itemPosition);
        });

        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            page++;
            isLoadMore = true;
            requestListData(page, itemPosition);
        });

    }

    private void requestListData(int page, String itemPosition) {
        Map<String, String> params = new HashMap<>();
        params.put("status", String.valueOf(itemPosition)); //1 可领取 2 已领取
        params.put("page", String.valueOf(page));
        //  XLog.e("请求数据  " + itemPosition);

        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.goodsList, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("商品管理 " + response.body());
                SMGoodsListBean goodsListBean = FastJsonUtil.fromJson(response.body(), SMGoodsListBean.class);
                refreshLayout.finishLoadMore();
                if (!goodsListBean.getCode().equals(Constant.REQUEST_SUCCESS) || null == goodsListBean.getResult()) {
                    T.showToast(mContext, goodsListBean.getMessage());
                    return;
                }

                List<SMGoodsListBean.ResultBean> dataList = goodsListBean.getResult();
                if (!isLoadMore) {
                    mStateView.showEmpty().setOnClickListener(v -> loadData());
                }

                if (null == dataList || dataList.size() == 0) {
                    refreshLayout.setEnableLoadMore(false);
                    shopManageAdapter.notifyDataSetChanged();
                    return;
                }
                mStateView.showContent();
                if (mArray.size() % DataMangerVo.PAGERE_NUMBER == 0) {
                    refreshLayout.setEnableLoadMore(true);
                } else {
                    refreshLayout.setEnableLoadMore(false);
                }
                addData(dataList);
                shopManageAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(Response<String> response) {
                mStateView.showRetry();
                refreshLayout.finishLoadMore();
            }
        });
    }

    private void refreshAdapter(boolean isReset) {
        if (isReset) {
            shopManageAdapter = new ShopManageAdapter(mArray);
            recyclerView.setAdapter(shopManageAdapter);

            shopManageAdapter.setOnItemChildClickListener((adapter, view, position) -> childItemClickListener(adapter, view, position));
        } else {
            shopManageAdapter.notifyDataSetChanged();
        }

        if (itemPosition != null && itemPosition.equals("3")) {
            shopManageAdapter.isEnableDown(false);
        }
    }

    private void refreshListData(int page, String itemPosition) {
        Map<String, String> params = new HashMap<>();
        params.put("status", String.valueOf(itemPosition));
        params.put("page", String.valueOf(page));
//        XLog.e("刷新数据  " + itemPosition);
        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.goodsList, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                refreshLayout.finishRefresh();
                SMGoodsListBean goodsListBean = FastJsonUtil.fromJson(response.body(), SMGoodsListBean.class);
                if (!goodsListBean.getCode().equals(Constant.REQUEST_SUCCESS) || null == goodsListBean.getResult()) {
                    T.showToast(mContext, goodsListBean.getMessage());
                    return;
                }
                List<SMGoodsListBean.ResultBean> dataList = goodsListBean.getResult();
                if (null == dataList || dataList.size() == 0) {
                    refreshLayout.setEnableLoadMore(false);
                    if (shopManageAdapter != null)
                        shopManageAdapter.notifyDataSetChanged();
                    mStateView.showEmpty().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loadData();
                        }
                    });
                    return;
                }
                mStateView.showContent();
                if (mArray.size() % DataMangerVo.PAGERE_NUMBER == 0) {
                    refreshLayout.setEnableLoadMore(true);
                } else {
                    refreshLayout.setEnableLoadMore(false);
                }
                clearData();
                addData(dataList);
                shopManageAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(Response<String> response) {
                refreshLayout.finishRefresh();
                toastErrorNet();
            }
        });
    }


    private void childItemClickListener(BaseQuickAdapter adapter, View view, int position) {
        SMGoodsListBean.ResultBean vo = (SMGoodsListBean.ResultBean) mArray.get(position);
        int goods_state = vo.getGoods_state();
        int goods_id = vo.getGoods_id();

        XLog.e("商品管理 " + goods_id + "_" + goods_state);
        switch (view.getId()) {
            case R.id.ctl_edit:
                Bundle bundle = new Bundle();
                bundle.putString("goods_id", String.valueOf(goods_id));
//                openActivity(ReleaseShopActivity.class, bundle);
                openActivity(ReleaseShopNewActivity.class, bundle);
                break;
            case R.id.ctl_up:
                String com;
                if (goods_state == 1) {
                    com = "是否确认下架该商品";
                } else {
                    com = "是否确认上架该商品";
                }
                DialogUtils.getSingleton().showSureAlerDialog(mContext, com, "",
                        "确认", "取消", new DialogUtils.OnDialogSuceClickListener() {
                            @Override
                            public void sureClick() {
                                if (goods_state == 1) {
                                    downUpShop(goods_id, 2, position);
                                } else {
                                    downUpShop(goods_id, 1, position);
                                }
                            }

                            @Override
                            public void cannerClick() {

                            }
                        });


                break;
            case R.id.ctl_delete:
                DialogUtils.getSingleton().showSureAlerDialog(mContext, "是否确认删除该商品", "",
                        "确认", "取消", new DialogUtils.OnDialogSuceClickListener() {
                            @Override
                            public void sureClick() {
                                deleteShop(goods_id, position);
                            }

                            @Override
                            public void cannerClick() {

                            }
                        });

                break;
        }

    }

    //删除商品
    private void deleteShop(int goods_id, int position) {

        loading(true);

        Map<String, String> params = new HashMap<>();

        params.put("goods_id", String.valueOf(goods_id));
        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.goods_del, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                dismiss();

                NormalResultBean resultBean = FastJsonUtil.fromJson(response.body(), NormalResultBean.class);
                if (null == resultBean) {
                    XToast.normal("操作失败");
                    return;
                }
                if (!resultBean.getCode().equals(Constant.REQUEST_SUCCESS)) {
                    XToast.normal(resultBean.getMessage());
                    return;
                }
                XToast.normal(resultBean.getMessage());
                mArray.remove(position);
                shopManageAdapter.notifyDataSetChanged();
                if (mArray.size() == 0) {
                    mStateView.showEmpty().setOnClickListener(v -> loadData());
                }
                EventBus.getDefault().post(new UnifiedNotifyEvent(Constant.EDIT_SHOP_RELEASE));
            }

            @Override
            public void failed(Response<String> response) {
                XToast.normal("请求失败，请稍候再试");
                dismiss();
            }
        });

    }

    /**
     * 上、下架商品
     *
     * @param id   商品id
     * @param type 1 上架  2 下架
     */
    private void downUpShop(int id, int type, int position) {
        loading(true);
        Map<String, String> params = new HashMap<>();
        params.put("goods_id", String.valueOf(id));
        params.put("type", String.valueOf(type));
        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.goods_lockup, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("上下架商品" + response.body());
                dismiss();
                NormalResultBean resultBean = FastJsonUtil.fromJson(response.body(), NormalResultBean.class);
                if (null == resultBean) {
                    XToast.normal("操作失败");
                    return;
                }
                if (!resultBean.getCode().equals(Constant.REQUEST_SUCCESS)) {
                    XToast.normal(resultBean.getMessage());
                    return;
                }
                XToast.normal(resultBean.getMessage());
                mArray.remove(position);
                shopManageAdapter.notifyDataSetChanged();
                if (mArray.size() == 0) {
                    mStateView.showEmpty().setOnClickListener(v -> loadData());
                }
                EventBus.getDefault().post(new UnifiedNotifyEvent(Constant.EDIT_SHOP_RELEASE));
            }

            @Override
            public void failed(Response<String> response) {
                XLog.e("上下架商品" + response.getException().getMessage());

                XToast.normal("请求失败，请稍候再试");
                dismiss();
            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void paySuccessEvent(UnifiedNotifyEvent events) {
        int eventCode = events.getEventCode();
        if (eventCode == Constant.EDIT_SHOP_RELEASE) {
            loadData();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void pageChangedEvent(NormalEvent events) {
        if (events.getMsg().equals("shop_manage_fragment")) {
            mArray.clear();
            itemPosition = String.valueOf(events.getCode());
            XLog.e("changed  " + itemPosition);
            mHandler.sendEmptyMessage(FIRST_REQUEST_DATA);
            isLoadSuccess = false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unregisterEventBus(ShopManageFragment.this);
    }


    private void clearData() {
        if (mArray == null) {
            mArray = new ArrayList();
        } else {
            mArray.clear();
        }
    }

    private void addData(List<?> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        if (mArray == null) {
            clearData();
        }
        mArray.addAll(list);
    }
}
