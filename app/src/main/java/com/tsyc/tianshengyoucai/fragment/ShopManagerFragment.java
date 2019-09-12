package com.tsyc.tianshengyoucai.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.mine.ShopManagerAdapter;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.manager.DataMangerVo;
import com.tsyc.tianshengyoucai.model.bean.NormalResultBean;
import com.tsyc.tianshengyoucai.model.bean.SMGoodsListBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.ReleaseShopActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.ReleaseShopNewActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseFragment;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/21 12:28:
 * @Purpose :商品管理
 */

public class ShopManagerFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private RecyclerView mGmRlvContent;
    private ImageView mIvEmpty;
    private SmartRefreshLayout mGmSmrlayoyut;
    private ArrayList mArray;
    private ShopManagerAdapter mShopManageAdapter;
    private int mPager = 0;

    public static ShopManagerFragment newInstance(String param1, String param2) {
        ShopManagerFragment fragment = new ShopManagerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_shop_manager, container, false);
//        initView(view);
//        return view;
//    }
//

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_shop_manager;
    }

    @Override
    protected void loadData() {
        clearData();
        initEvent();
        initAdapter();
        initRefresh();
        mGmSmrlayoyut.autoRefresh();

    }

    private void initEvent() {


    }

    private void initRefresh() {
        mGmSmrlayoyut.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadNew(1, mParam2);
            }
        });
        mGmSmrlayoyut.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore(mPager, mParam2);
            }
        });


    }

    private void loadNew(int page, String itemPosition) {
        Map<String, String> params = new HashMap<>();
        params.put("status", String.valueOf(itemPosition)); //1 可领取 2 已领取
        params.put("page", String.valueOf(page));

        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.goodsList, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("商品管理 " + response.body());
                SMGoodsListBean goodsListBean = FastJsonUtil.fromJson(response.body(), SMGoodsListBean.class);
                mGmSmrlayoyut.finishRefresh();
                if (goodsListBean.getCode().equals("100")) {
                    mGmRlvContent.setVisibility(View.GONE);
                    mIvEmpty.setVisibility(View.VISIBLE);
                    T.showToast(mContext, goodsListBean.getMessage());
                    return;
                }

                List<SMGoodsListBean.ResultBean> dataList = goodsListBean.getResult();
                if (null == dataList || dataList.size() == 0) {
                    mGmSmrlayoyut.setEnableLoadMore(false);
                    mGmRlvContent.setVisibility(View.GONE);
                    mIvEmpty.setVisibility(View.VISIBLE);
                    mShopManageAdapter.notifyDataSetChanged();
                    return;
                }
                mIvEmpty.setVisibility(View.GONE);
                mGmRlvContent.setVisibility(View.VISIBLE);
                if (mArray.size() % DataMangerVo.PAGERE_NUMBER == 0) {
                    mGmSmrlayoyut.setEnableLoadMore(true);
                } else {
                    mGmSmrlayoyut.setEnableLoadMore(false);
                }
                mPager=1;
                clearData();
                addData(dataList);
                mShopManageAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(Response<String> response) {
                mStateView.showRetry();
                mGmSmrlayoyut.finishLoadMore();
            }
        });
    }

    private void loadMore(int page, String itemPosition) {
        Map<String, String> params = new HashMap<>();
        params.put("status", String.valueOf(itemPosition)); //1 可领取 2 已领取
        params.put("page", String.valueOf(page));
        XLog.e("请求数据  " + itemPosition);

        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.goodsList, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("商品管理 " + response.body());
                SMGoodsListBean goodsListBean = FastJsonUtil.fromJson(response.body(), SMGoodsListBean.class);
                mGmSmrlayoyut.finishLoadMore();
                if (!goodsListBean.getCode().equals(Constant.REQUEST_SUCCESS) || null == goodsListBean.getResult()) {
                    T.showToast(mContext, goodsListBean.getMessage());
                    return;
                }
                List<SMGoodsListBean.ResultBean> dataList = goodsListBean.getResult();
                if (null == dataList || dataList.size() == 0) {
                    mGmSmrlayoyut.setEnableLoadMore(false);
                    mShopManageAdapter.notifyDataSetChanged();
                    return;
                }
                if (mArray.size() % DataMangerVo.PAGERE_NUMBER == 0) {
                    mGmSmrlayoyut.setEnableLoadMore(true);
                    ++mPager;
                } else {
                    mGmSmrlayoyut.setEnableLoadMore(false);
                }
                addData(dataList);
                mShopManageAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(Response<String> response) {
                mGmSmrlayoyut.finishLoadMore();
            }
        });
    }

    private void initAdapter() {
        mShopManageAdapter = new ShopManagerAdapter(mContext, mArray);
        RecyclerUtil.setGridManage(mActivity, mGmRlvContent, mShopManageAdapter);
        mShopManageAdapter.setListener(new ShopManagerAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, SMGoodsListBean.ResultBean vo, int i) {
                int goods_id = vo.getGoods_id();
                int goods_state = vo.getGoods_state();
                switch (i) {
                    case 1:
                        Bundle bundle = new Bundle();
                        bundle.putString("goods_id", String.valueOf(vo.getGoods_id()));
                        openActivity(ReleaseShopNewActivity.class, bundle);
                        break;
                    case 2:
                        if (vo.getGoods_state() == 1) {
                            downUpShop(goods_id, 2, position);
                        } else {
                            downUpShop(goods_id, 1, position);
                        }
                        break;
                    case 3:
                        deleteShop(goods_id, position);
                        break;
                    default:
                        break;
                }

            }
        });
    }

    @Override
    public void initView(View view) {
        mGmRlvContent = (RecyclerView) view.findViewById(R.id.gm_rlv_content);
        mIvEmpty = (ImageView) view.findViewById(R.id.iv_empty);
        mGmSmrlayoyut = (SmartRefreshLayout) view.findViewById(R.id.gm_smrlayoyut);
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
                mShopManageAdapter.notifyDataSetChanged();
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
                mShopManageAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(Response<String> response) {
                XLog.e("上下架商品" + response.getException().getMessage());

                XToast.normal("请求失败，请稍候再试");
                dismiss();
            }
        });
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
