package com.tsyc.tianshengyoucai.fragment.recruite.boss;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.recruit.BossJobAdapter;
import com.tsyc.tianshengyoucai.base.Base2Fragment;
import com.tsyc.tianshengyoucai.requet.RequestBossHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.ui.fragment.selectobserver.ChangerObserver;
import com.tsyc.tianshengyoucai.ui.recruit.boss.BossLookResumeActivity;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.vo.BossJobContentVo;
import com.tsyc.tianshengyoucai.vo.SelectVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/9/3 16:20:
 * @Purpose :职位列表
 */
public class BossHomeJobFragment extends Base2Fragment implements ChangerObserver {
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
    /**
     * (选填）学历筛选
     */
    private String mEducationParam;
    /**
     * (选填）工作经验年限筛选
     */
    private String mWorkExperienceParam;
    /**
     * (选填）期望薪资筛选
     */
    private String mSalaryParam;
    /**
     * (选填）当前状态筛选
     */
    private String mCurrentStatusParam;
    private BossJobAdapter mAdapter;


    public static BossHomeJobFragment newInstance(String param1, String param2) {
        BossHomeJobFragment fragment = new BossHomeJobFragment();
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
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_boss_home_job, container, false);
//        initView(view);
//        return view;
//    }

    @Override
    protected int getFramgentViewId() {
        return R.layout.fragment_boss_home_job;
    }

    @Override
    protected void initContentView(View view, Bundle savedInstanceState) {
        initView(view);
        clearData();
        initEvent();
        initAdapter();
        initRefresh();
        mGmSmrlayoyut.autoRefresh();
    }

    private void initEvent() {


    }

    public void refreshData() {
        if (mGmSmrlayoyut != null) {
            mGmSmrlayoyut.autoRefresh();
        }
    }

    private void initAdapter() {
        mAdapter = new BossJobAdapter(mContext, mArray);
        RecyclerUtil.setGridManage(mContext, mGmRlvContent, mAdapter);
        mAdapter.setListener(new BossJobAdapter.OnItemClickListener() {
            @Override
            public void itemClick(BossJobContentVo.ResultBean.DataBean vo) {
                mResultTo.startBossLookJianli(getActivity(), BossLookResumeActivity.OTHER_TYPE,
                        vo.getId());
            }
        });
    }

    private void initRefresh() {
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
        if (refresh)
            mPage = 1;
        RequestBossHttp.getSingleton(getActivity()).requestBossCvRecommend(mParam2, mEducationParam, mWorkExperienceParam, mSalaryParam, mCurrentStatusParam, String.valueOf(mPage), new RequestResultCallback() {
            @Override
            public void success(String success) {
                isRefresh = false;
                if (refresh)
                    mGmSmrlayoyut.finishRefresh();
                else
                    mGmSmrlayoyut.finishLoadMore();
                if (onSuccess(success)) return;
                BossJobContentVo vo = GsonUtils.getGson(success, BossJobContentVo.class);

                BossJobContentVo.ResultBean result = vo.getResult();
                List<BossJobContentVo.ResultBean.DataBean> data = result.getData();

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


    @Override
    public void updateData(List<SelectVo> mEduList, List<SelectVo> mExpList, List<SelectVo> mMoneyList,
                           List<SelectVo> mStatusList) {
        if (mEduList != null && !mEduList.isEmpty()) {
            StringBuffer edu = new StringBuffer();
            for (int i = 0; i < mEduList.size(); i++) {
                SelectVo vo = mEduList.get(i);
                edu.append(vo.getId());
                if (i != mEduList.size() - 1) {
                    edu.append(",");
                }
            }
            mEducationParam = edu.toString();
            edu = null;
        }
        if (mExpList != null && !mExpList.isEmpty()) {
            StringBuffer edu = new StringBuffer();
            for (int i = 0; i < mExpList.size(); i++) {
                SelectVo vo = mExpList.get(i);
                edu.append(vo.getId());
                if (i != mExpList.size() - 1) {
                    edu.append(",");
                }
            }
            mWorkExperienceParam = edu.toString();
            edu = null;
        }
        if (mMoneyList != null && !mMoneyList.isEmpty()) {
            StringBuffer edu = new StringBuffer();
            for (int i = 0; i < mMoneyList.size(); i++) {
                SelectVo vo = mMoneyList.get(i);
                edu.append(vo.getId());
                if (i != mEduList.size() - 1) {
                    edu.append(",");
                }
            }
            mSalaryParam = edu.toString();
            edu = null;
        }
        if (mStatusList != null && !mStatusList.isEmpty()) {
            StringBuffer edu = new StringBuffer();
            for (int i = 0; i < mStatusList.size(); i++) {
                SelectVo vo = mStatusList.get(i);
                edu.append(vo.getId());
                if (i != mStatusList.size() - 1) {
                    edu.append(",");
                }
            }
            mCurrentStatusParam = edu.toString();
            edu = null;
        }
        if (mGmSmrlayoyut != null) {
            mGmSmrlayoyut.autoRefresh();
        }
    }
}
