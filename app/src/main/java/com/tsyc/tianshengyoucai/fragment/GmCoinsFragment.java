package com.tsyc.tianshengyoucai.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.bank.CoinsAdapter;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.requet.RequestSettingHttp;
import com.tsyc.tianshengyoucai.ui.base.BaseFragment;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.CoisnItemVo;
import com.tsyc.tianshengyoucai.vo.CoisnVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/8 20:16:
 * @Purpose :全部金币消费记录
 */
public class GmCoinsFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private boolean mRefresh = false;
    private ArrayList mArray;
    private RecyclerView mGmRlvContent;
    private ImageView mIvEmpty;
    private SmartRefreshLayout mGmSmrlayoyut;
    private CoinsAdapter mAdapter;

    private int mPage;

    public GmCoinsFragment() {
    }

    public static GmCoinsFragment newInstance(String param1, String param2) {
        GmCoinsFragment fragment = new GmCoinsFragment();
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
//        View view = inflater.inflate(R.layout.fragment_all_coins, container, false);
//        initView(view);
//        return view;
//    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_all_coins;
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
        mGmSmrlayoyut.setEnableRefresh(false);
        loadNew();

    }

    private void initAdapter() {
        mAdapter = new CoinsAdapter(mContext, mArray);
        RecyclerUtil.setGridManage(mActivity, mGmRlvContent, mAdapter);
        mAdapter.setListener(new CoinsAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, CoisnItemVo vo) {

            }
        });

    }

    private void initRefresh() {
        mGmSmrlayoyut.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore();
            }
        });
    }


    private void loadNew() {
        showProgress();
        RequestSettingHttp.getSingleton(getActivity()).requestGoldCoin(mParam2, "1", new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                NormalBean vos = GsonUtils.getGson(success, NormalBean.class);
                if (vos.getCode().equals("100")) {
                    mIvEmpty.setVisibility(View.VISIBLE);
                    mGmRlvContent.setVisibility(View.GONE);
                    T.showToast(mContext, vos.getMessage());
                    return;
                }
                CoisnVo vo = GsonUtils.getGson(success, CoisnVo.class);
                clearData();
                CoisnVo.ResultBean result = vo.getResult();
                List<CoisnItemVo> data = result.getData();
                if (data == null || data.isEmpty()) {
                    mGmRlvContent.setVisibility(View.GONE);
                    mIvEmpty.setVisibility(View.VISIBLE);
                    mGmSmrlayoyut.setEnableLoadMore(false);
                    mAdapter.notifyDataSetChanged();
                    return;
                }
                mIvEmpty.setVisibility(View.GONE);
                mGmRlvContent.setVisibility(View.VISIBLE);
                addData(data);
                if (mArray.size() < result.getTotal()) {
                    mGmSmrlayoyut.setEnableLoadMore(true);
                    mPage = result.getCurrent_page() + 1;
                } else {
                    mGmSmrlayoyut.setEnableLoadMore(false);
                }
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void error(String error) {
                dismissProgress();
                toastErrorNet();
            }
        });
    }

    private void loadMore() {
        if (mRefresh) {
            return;
        }
        mRefresh = true;
        RequestSettingHttp.getSingleton(getActivity()).requestGoldCoin(mParam2, String.valueOf(mPage), new RequestResultCallback() {
            @Override
            public void success(String success) {
                mRefresh = false;
                mGmSmrlayoyut.finishLoadMore();
                NormalBean vos = GsonUtils.getGson(success, NormalBean.class);
                if (vos.getCode().equals("100")) {
                    T.showToast(mContext, vos.getMessage());
                    mAdapter.notifyDataSetChanged();
                    return;
                }
                CoisnVo vo = GsonUtils.getGson(success, CoisnVo.class);
                CoisnVo.ResultBean result = vo.getResult();
                List<CoisnItemVo> data = result.getData();
                if (data == null || data.isEmpty()) {
                    mGmSmrlayoyut.setEnableLoadMore(false);
                    mAdapter.notifyDataSetChanged();
                    return;
                }

                addData(data);
                if (mArray.size() >= result.getTotal()) {
                    mGmSmrlayoyut.setEnableLoadMore(false);
                } else {
                    mPage = result.getCurrent_page() + 1;
                    mGmSmrlayoyut.setEnableLoadMore(true);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void error(String error) {
                mRefresh = false;
                mGmSmrlayoyut.finishLoadMore();
                toastErrorNet();
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

    @Override
    public void initView(View view) {
        mGmRlvContent = (RecyclerView) view.findViewById(R.id.gm_rlv_content);
        mIvEmpty = (ImageView) view.findViewById(R.id.iv_empty);
        mGmSmrlayoyut = (SmartRefreshLayout) view.findViewById(R.id.gm_smrlayoyut);
    }
}
