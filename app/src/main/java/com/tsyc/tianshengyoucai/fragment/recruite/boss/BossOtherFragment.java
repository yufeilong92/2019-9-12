package com.tsyc.tianshengyoucai.fragment.recruite.boss;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.recruit.BossDeliverAdapter;
import com.tsyc.tianshengyoucai.base.Base2Fragment;
import com.tsyc.tianshengyoucai.requet.RequestBossHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.ui.recruit.boss.BossInterviewActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.BossLookResumeActivity;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.vo.DeliverBossVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/9/4 18:18:
 * @Purpose :已处理(简历，已处理)
 */
public class BossOtherFragment extends Base2Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private RecyclerView mGmRlvContent;
    private ImageView mIvEmpty;
    private SmartRefreshLayout mGmSmrlayoyut;

    private ArrayList mArray;
    private boolean isRefresh;
    private int mPage;
    private BossDeliverAdapter mAdapter;
    private boolean mAsk;

    public static BossOtherFragment newInstance(String param1, String param2) {
        BossOtherFragment fragment = new BossOtherFragment();
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
//        View view = inflater.inflate(R.layout.fragment_boss_other, container, false);
//        initView(view);
//        return view;
//    }

    @Override
    protected int getFramgentViewId() {
        return R.layout.fragment_boss_other;
    }

    @Override
    protected void initContentView(View view, Bundle savedInstanceState) {
        initView(view);
        clearData();
        initEvent();
        initAdapter();
        initRefhresh();
        mGmSmrlayoyut.autoRefresh();
    }

    private void initEvent() {
        mAsk = mParam2.equals("1");
    }

    private void initAdapter() {
        mAdapter = new BossDeliverAdapter(mContext, mArray);
        RecyclerUtil.setGridManage(mContext, mGmRlvContent, mAdapter);
        mAdapter.setListener(vo -> {
            if (mAsk) {//简历
               mResultTo.startBossLookJianli(getActivity(), BossLookResumeActivity.OTHER_TYPE,vo.getId());
                return;
            }//面试
            mResultTo.startInterView(mContext, vo.getId(), vo.getInterview_time(),BossInterviewActivity.NO_SHOWBTN_TYPE);
        });

    }

    private void initRefhresh() {
        mGmSmrlayoyut.setEnableLoadMore(false);
        mGmSmrlayoyut.setOnRefreshListener(refreshLayout -> {
            loadContent(true);
        });
        mGmSmrlayoyut.setOnLoadMoreListener(refreshLayout -> {
            loadContent(false);
        });
    }

    private void loadContent(boolean refresh) {
        if (isRefresh) {
            return;
        }
        isRefresh = true;
        RequestBossHttp.getSingleton(getActivity()).requestBossOtherList(mParam2.equals("1"), String.valueOf(mPage),
                new RequestResultCallback() {
                    @Override
                    public void success(String success) {
                        isRefresh = false;
                        if (refresh)
                            mGmSmrlayoyut.finishRefresh();
                        else
                            mGmSmrlayoyut.finishLoadMore();
                        if (onSuccess(success)) return;
                        DeliverBossVo vo = GsonUtils.getGson(success, DeliverBossVo.class);

                        DeliverBossVo.ResultBean result = vo.getResult();
                        List<DeliverBossVo.ResultBean.DataBean> data = result.getData();

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
