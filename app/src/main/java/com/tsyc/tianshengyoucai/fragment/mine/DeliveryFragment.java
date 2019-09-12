package com.tsyc.tianshengyoucai.fragment.mine;

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
import com.tsyc.tianshengyoucai.adapter.recruit.MyDeliverAdapter;
import com.tsyc.tianshengyoucai.base.Base2Fragment;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.requet.RequestJobHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.DeliveryListVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/28 15:16:
 * @Purpose :我的投递
 */
public class DeliveryFragment extends Base2Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private RecyclerView mGmRlvContent;
    private ImageView mIvEmpty;
    private SmartRefreshLayout mGmSmrlayoyut;
    private ArrayList mArray;
    private MyDeliverAdapter mAdapter;
    private boolean isRefresh;
    private String mStatus;
    private int mPage;

    public static DeliveryFragment newInstance(String param1, String param2) {
        DeliveryFragment fragment = new DeliveryFragment();
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
//        View view = inflater.inflate(R.layout.fragment_delivery, container, false);
//        initView(view);
//        return view;
//    }

    @Override
    protected int getFramgentViewId() {
        return R.layout.fragment_delivery;
    }

    @Override
    protected void initContentView(View view, Bundle savedInstanceState) {
        initView(view);
        clearData();
        initEvent();
        initAdapter();
        iniRefresh();
        mGmSmrlayoyut.autoRefresh();
    }

    private void initEvent() {
        switch (Integer.parseInt(mParam2)) {
            case 0:
                mStatus = "all";
                break;
            case 1:
                mStatus = "send";
                break;
            case 2:
                mStatus = "check";
                break;
            case 3:
                mStatus = "interview";
                break;
            case 4:
                mStatus = "unmatch";
                break;
        }


    }

    private void iniRefresh() {
        mGmSmrlayoyut.setEnableLoadMore(false);
        mGmSmrlayoyut.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadNew();
            }
        });
        mGmSmrlayoyut.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore();
            }
        });
    }

    private void loadNew() {
        if (isRefresh) {
            return;
        }
        isRefresh = true;
        RequestJobHttp.getSingleton(getActivity()).requestSendList(mStatus,"1", new RequestResultCallback() {
            @Override
            public void success(String success) {
                loadContent(success, true);
            }

            @Override
            public void error(String error) {
                mGmSmrlayoyut.finishRefresh();
                onError();
            }
        });

    }

    private void loadMore() {
        if (isRefresh) {
            return;
        }
        isRefresh = true;
        RequestJobHttp.getSingleton(getActivity()).requestSendList(mStatus, String.valueOf(mPage),new RequestResultCallback() {
            @Override
            public void success(String success) {
                loadContent(success, false);
            }

            @Override
            public void error(String error) {
                mGmSmrlayoyut.finishLoadMore();
                onError();
            }
        });
    }

    /**
     * @param success
     * @param isLoadNew 是否刷新
     */
    private void loadContent(String success, boolean isLoadNew) {
        if (isLoadNew)
            mGmSmrlayoyut.finishRefresh();
        else
            mGmSmrlayoyut.finishLoadMore();
        NormalBean vo = GsonUtils.getGson(success, NormalBean.class);
        if (vo.getCode().equals("100")) {
            if (isLoadNew) {
                mIvEmpty.setVisibility(View.VISIBLE);
                mGmRlvContent.setVisibility(View.GONE);
            }
            T.showToast(mContext, vo.getMessage());
            return;
        }
        if (isLoadNew) {
            mIvEmpty.setVisibility(View.GONE);
            mGmRlvContent.setVisibility(View.VISIBLE);
        }
        DeliveryListVo listVo = GsonUtils.getGson(success, DeliveryListVo.class);
        DeliveryListVo.ResultBean result = listVo.getResult();

        if (isLoadNew) {
            clearData();
        }
        List<DeliveryListVo.ResultBean.DataBean> list = result.getData();
        if (list == null || list.isEmpty()) {
            if (isLoadNew) {
                mIvEmpty.setVisibility(View.VISIBLE);
                mGmRlvContent.setVisibility(View.GONE);
                mAdapter.notifyDataSetChanged();
                return;
            }
            mGmSmrlayoyut.setEnableLoadMore(false);
            mAdapter.notifyDataSetChanged();
            return;
        }
        addData(list);
        if (mArray.size() == result.getTotal()) {
            mGmSmrlayoyut.setEnableLoadMore(false);
        } else {
            if (isLoadNew) {
                mPage = result.getCurrent_page() + 1;
            } else {
                mPage = result.getCurrent_page() + 1;
            }
            mGmSmrlayoyut.setEnableLoadMore(true);
        }
        mAdapter.notifyDataSetChanged();


    }


    private void initAdapter() {
        mAdapter = new MyDeliverAdapter(mContext, mArray);
        RecyclerUtil.setGridManage(mContext, mGmRlvContent, mAdapter);
        mAdapter.setListener(new MyDeliverAdapter.OnItemClickListener() {
            @Override
            public void itemClick(DeliveryListVo.ResultBean.DataBean bean) {
               mResultTo.startDeliverDetail(getActivity(),bean.getId());
            }
        });
    }

    private void initView(View view) {
        mGmRlvContent = (RecyclerView) view.findViewById(R.id.gm_rlv_content);
        mIvEmpty = (ImageView) view.findViewById(R.id.iv_empty);
        mGmSmrlayoyut = (SmartRefreshLayout) view.findViewById(R.id.gm_smrlayoyut);
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
