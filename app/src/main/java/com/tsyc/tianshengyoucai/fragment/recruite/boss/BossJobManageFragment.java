package com.tsyc.tianshengyoucai.fragment.recruite.boss;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tsyc.tianshengyoucai.Eventbuss.JobRefreshEvent;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.recruit.BossJobManageAdapter;
import com.tsyc.tianshengyoucai.base.Base2Fragment;
import com.tsyc.tianshengyoucai.requet.RequestBossHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.vo.BossJobManagerVo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/9/4 10:54:
 * @Purpose :职位管理
 */
public class BossJobManageFragment extends Base2Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private RecyclerView mGmRlvContent;
    private ImageView mIvEmpty;
    private SmartRefreshLayout mGmSmrlayoyut;

    private boolean isRefresh;
    private ArrayList mArray;
    private BossJobManageAdapter mAdapter;
    private int mPage;

    public static BossJobManageFragment newInstance(String param1, String param2) {
        BossJobManageFragment fragment = new BossJobManageFragment();
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
//        View view = inflater.inflate(R.layout.fragment_boss_job_manage, container, false);
//        initView(view);
//        return view;
//    }

    @Override
    protected int getFramgentViewId() {
        return R.layout.fragment_boss_job_manage;
    }

    @Override
    protected void initContentView(View view, Bundle savedInstanceState) {
        initView(view);
        clearData();
        initEvent();
        initAdapter();
        initRefrsh();
        mGmSmrlayoyut.autoRefresh();

    }

    private void initEvent() {

    }

    private void initAdapter() {
        mAdapter = new BossJobManageAdapter(mContext, mArray);
        RecyclerUtil.setGridManage(mContext, mGmRlvContent, mAdapter);
        mAdapter.setListener(vo -> {
            mResultTo.startBossJobDetail(getActivity(), vo.getId(), vo.getPosition_name());
        });
    }

    private void initRefrsh() {
        mGmSmrlayoyut.setEnableLoadMore(false);
        mGmSmrlayoyut.setOnRefreshListener(refreshLayout -> {
            loadData(true);

        });
        mGmSmrlayoyut.setOnLoadMoreListener(refreshLayout -> {
            loadData(false);
        });

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

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void refreshData(JobRefreshEvent event) {
        if (mGmSmrlayoyut != null) {
            mGmSmrlayoyut.autoRefresh();
        }

    }

    private void loadData(boolean refresh) {
        if (isRefresh) {
            return;
        }
        isRefresh = true;
        if (refresh)
            mPage = 1;
        RequestBossHttp.getSingleton(getActivity()).requestBossList(mParam2, String.valueOf(mPage), new RequestResultCallback() {
            @Override
            public void success(String success) {
                isRefresh = false;
                if (refresh)
                    mGmSmrlayoyut.finishRefresh();
                else
                    mGmSmrlayoyut.finishLoadMore();
                if (onSuccess(success)) return;
                BossJobManagerVo vo = GsonUtils.getGson(success, BossJobManagerVo.class);

                BossJobManagerVo.ResultBean result = vo.getResult();
                List<BossJobManagerVo.ResultBean.DataBean> data = result.getData();

                if (refresh)
                    clearData();
                if (data == null || data.isEmpty()) {
                    if (refresh) {
                        mIvEmpty.setVisibility(View.VISIBLE);
                        mGmRlvContent.setVisibility(View.GONE);
                    }
                    mGmSmrlayoyut.setEnableLoadMore(false);
                    mAdapter.notifyDataSetChanged();
                    return;
                }
                if (refresh) {
                    mIvEmpty.setVisibility(View.GONE);
                    mGmRlvContent.setVisibility(View.VISIBLE);
                }
                addData(data);
                if (mArray.size() >= result.getTotal()) {
                    mGmSmrlayoyut.setEnableLoadMore(false);
                } else {
                    mGmSmrlayoyut.setEnableLoadMore(true);
                    mPage = result.getCurrent_page() + 1;
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void error(String error) {
                isRefresh = false;
                if (refresh)
                    mGmSmrlayoyut.finishRefresh();
                else
                    mGmSmrlayoyut.finishLoadMore();
                onError();
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
