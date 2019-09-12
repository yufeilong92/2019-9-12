package com.tsyc.tianshengyoucai.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.bank.MyCopuonAdapter;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.requet.RequestSettingHttp;
import com.tsyc.tianshengyoucai.ui.base.BaseFragment;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.RedVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/14 12:23:
 * @Purpose :优惠卷
 */
public class CouponFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private RecyclerView mRlvCouponContent;
    private SmartRefreshLayout mSmrlayoutCoupon;
    private ArrayList mArray;
    private boolean mRefresh = false;
    private MyCopuonAdapter mAdapter;
    private ImageView mIvEmpty;
    private TextView mTvLoseRed;

    public static CouponFragment newInstance(String param1, String param2) {
        CouponFragment fragment = new CouponFragment();
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
//        View view = inflater.inflate(R.layout.fragment_coupon, container, false);
//        initView(view);
//        return view;
//    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_coupon;
    }

    @Override
    protected void loadData() {
        initEvent();
        clearData();
        initAdapter();
        initRefresh();
        mSmrlayoutCoupon.autoRefresh();


    }

    private void initRefresh() {
        mSmrlayoutCoupon.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initRequest();
            }
        });
        mSmrlayoutCoupon.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore();
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

    private void initAdapter() {
        RecyclerUtil.setGridManage(mActivity, mRlvCouponContent);
        mAdapter = new MyCopuonAdapter(mContext, mArray);
        mRlvCouponContent.setAdapter(mAdapter);
        mAdapter.setListener(new MyCopuonAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, RedVo.ResultBean.DataBean vo) {
                mResultTo.startShopDetail(getActivity(),String.valueOf(vo.getVoucher_store_id()));
            }
        });

    }

    private int mPage;

    private void initRequest() {
        if (mRefresh) {
            return;
        }
        mRefresh = true;
        RequestSettingHttp.getSingleton(getActivity()).requestMyCouponList("1", mParam2, new RequestResultCallback() {
            @Override
            public void success(String success) {
                mRefresh = false;
                mSmrlayoutCoupon.finishRefresh();
                NormalBean beans = GsonUtils.getGson(success, NormalBean.class);
                if (beans == null || beans.getCode().equals("100")) {
                    mIvEmpty.setVisibility(View.VISIBLE);
                    mRlvCouponContent.setVisibility(View.GONE);
                    T.showToast(mContext, beans.getMessage());
                    return;
                }
                RedVo bean = GsonUtils.getGson(success, RedVo.class);
                clearData();
                List<RedVo.ResultBean.DataBean> data = bean.getResult().getData();
                if (data==null||data.isEmpty()){
                    mIvEmpty.setVisibility(View.VISIBLE);
                    mRlvCouponContent.setVisibility(View.GONE);
                    mSmrlayoutCoupon.setEnableLoadMore(false);
                    mAdapter.notifyDataSetChanged();
                    return;
                }
                mIvEmpty.setVisibility(View.GONE);
                mRlvCouponContent.setVisibility(View.VISIBLE);
                RedVo.ResultBean result = bean.getResult();
                addData(data);
                if (mArray.size() >= result.getTotal()) {
                    mSmrlayoutCoupon.setEnableLoadMore(false);
                } else {
                    mPage = result.getCurrent_page() + 1;
                    mSmrlayoutCoupon.setEnableLoadMore(true);
                }
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void error(String error) {
                mRefresh = false;
                mSmrlayoutCoupon.finishRefresh();
                toastErrorNet();
            }
        });

    }

    private void loadMore() {
        if (mRefresh) {
            return;
        }
        mRefresh = true;
        RequestSettingHttp.getSingleton(getActivity()).requestMyCouponList(String.valueOf(mPage), mParam2, new RequestResultCallback() {
            @Override
            public void success(String success) {
                mRefresh = false;
                mSmrlayoutCoupon.finishLoadMore();
                NormalBean beans = GsonUtils.getGson(success, NormalBean.class);

                if (beans == null || beans.getCode().equals("100")) {
                    T.showToast(mContext, beans.getMessage());
                    return;
                }
                RedVo bean = GsonUtils.getGson(success, RedVo.class);

                List<RedVo.ResultBean.DataBean> data = bean.getResult().getData();
                if (data==null||data.isEmpty()){
                    mSmrlayoutCoupon.setEnableLoadMore(false);
                    mAdapter.notifyDataSetChanged();
                    return;
                }
                RedVo.ResultBean result = bean.getResult();
                addData(data);
                if (mArray.size() >= result.getTotal()) {
                    mSmrlayoutCoupon.setEnableLoadMore(false);
                } else {
                    mPage = result.getCurrent_page() + 1;
                    mSmrlayoutCoupon.setEnableLoadMore(true);
                }
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void error(String error) {
                mRefresh = false;
                mSmrlayoutCoupon.finishLoadMore();
                toastErrorNet();
            }
        });

    }

    private void initEvent() {


    }


    @Override
    public void initView(View view) {
        mRlvCouponContent = (RecyclerView) view.findViewById(R.id.rlv_coupon_content);
        mSmrlayoutCoupon = (SmartRefreshLayout) view.findViewById(R.id.smrlayout_coupon);
        mIvEmpty = (ImageView) view.findViewById(R.id.iv_empty);
        mIvEmpty.setOnClickListener(this);
        mTvLoseRed = (TextView) view.findViewById(R.id.tv_lose_red);
        mTvLoseRed.setOnClickListener(this);
    }
}
