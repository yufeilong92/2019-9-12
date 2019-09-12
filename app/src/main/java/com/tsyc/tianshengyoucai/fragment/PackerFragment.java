package com.tsyc.tianshengyoucai.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.bank.MyPackerAdapter;
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
 * @Time :2019/8/14 12:24:
 * @Purpose :我的红包
 */
public class PackerFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private RecyclerView mRlvPackerContent;
    private SmartRefreshLayout mSmrlayout;
    private ArrayList mArray;
    private MyPackerAdapter mAdapter;
    private boolean mRefresh = false;
    private ImageView mIvEmpty;

    public static PackerFragment newInstance(String param1, String param2) {
        PackerFragment fragment = new PackerFragment();
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
//        View view = inflater.inflate(R.layout.fragment_packer, container, false);
//        initView(view);
//        return view;
//    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_packer;
    }

    @Override
    protected void loadData() {
        initEvent();
        clearData();
        initAdapter();
        initRefresh();
        mSmrlayout.autoRefresh();


    }

    private void initRefresh() {
        mSmrlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initRequest();
            }
        });
        mSmrlayout.setOnLoadMoreListener(new OnLoadMoreListener() {
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
        RecyclerUtil.setGridManage(mActivity, mRlvPackerContent);
        mAdapter = new MyPackerAdapter(mContext, mArray);
        mRlvPackerContent.setAdapter(mAdapter);
        mAdapter.setListener(new MyPackerAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, RedVo.ResultBean.DataBean vo) {
                mResultTo.startShopDetail(getActivity(), String.valueOf(vo.getVoucher_store_id()));
            }
        });

    }

    private int mPage;

    private void initRequest() {
        if (mRefresh) {
            return;
        }
        mRefresh = true;
        RequestSettingHttp.getSingleton(getActivity()).requestMyRedPacker("1", mParam2, new RequestResultCallback() {
            @Override
            public void success(String success) {
                mRefresh = false;
                mSmrlayout.finishRefresh();
                NormalBean beans = GsonUtils.getGson(success, NormalBean.class);
                if (beans == null || beans.getCode().equals("100")) {
                    mIvEmpty.setVisibility(View.VISIBLE);
                    mRlvPackerContent.setVisibility(View.GONE);
                    T.showToast(mContext, beans.getMessage());
                    return;
                }
                RedVo bean = GsonUtils.getGson(success, RedVo.class);

                clearData();
                List<RedVo.ResultBean.DataBean> data = bean.getResult().getData();
                if (data == null || data.isEmpty()) {
                    mRlvPackerContent.setVisibility(View.GONE);
                    mIvEmpty.setVisibility(View.VISIBLE);
                    mSmrlayout.setEnableLoadMore(false);
                    mAdapter.notifyDataSetChanged();
                    return;
                }
                mRlvPackerContent.setVisibility(View.VISIBLE);
                mIvEmpty.setVisibility(View.GONE);
                RedVo.ResultBean result = bean.getResult();
                addData(data);
                if (mArray.size() >= result.getTotal()) {
                    mSmrlayout.setEnableLoadMore(false);
                    mAdapter.notifyDataSetChanged();
                    return;
                } else {
                    mPage = result.getCurrent_page() + 1;
                    mSmrlayout.setEnableLoadMore(true);
                }
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void error(String error) {
                mRefresh = false;
                mSmrlayout.finishRefresh();
                toastErrorNet();
            }
        });

    }

    private void loadMore() {
        if (mRefresh) {
            return;
        }
        mRefresh = true;
        RequestSettingHttp.getSingleton(getActivity()).requestMyRedPacker(String.valueOf(mPage), mParam2, new RequestResultCallback() {
            @Override
            public void success(String success) {
                mRefresh = false;
                mSmrlayout.finishLoadMore();
                NormalBean beans = GsonUtils.getGson(success, NormalBean.class);
                if (beans == null || beans.getCode().equals("100")) {
                    T.showToast(mContext, beans.getMessage());
                    return;
                }
                RedVo bean = GsonUtils.getGson(success, RedVo.class);

                List<RedVo.ResultBean.DataBean> data = bean.getResult().getData();
                if (data==null||data.isEmpty()){
                    mSmrlayout.setEnableLoadMore(false);
                    mAdapter.notifyDataSetChanged();
                    return;
                }

                RedVo.ResultBean result = bean.getResult();
                addData(data);
                if (mArray.size() >= result.getTotal()) {
                    mSmrlayout.setEnableLoadMore(false);
                } else {
                    mPage = result.getCurrent_page() + 1;
                    mSmrlayout.setEnableLoadMore(true);
                }
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void error(String error) {
                mRefresh = false;
                mSmrlayout.finishLoadMore();
                toastErrorNet();
            }
        });

    }

    private void initEvent() {


    }

    @Override
    public void initView(View view) {
        mRlvPackerContent = (RecyclerView) view.findViewById(R.id.rlv_packer_content);
        mSmrlayout = (SmartRefreshLayout) view.findViewById(R.id.smrlayout);
        mIvEmpty = (ImageView) view.findViewById(R.id.iv_empty);
        mIvEmpty.setOnClickListener(this);
    }
}
