package com.tsyc.tianshengyoucai.ui.recruit.boss;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.recruit.InterestAdapter;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.requet.RequestBossHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.vo.InterestVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/9/9 15:42:
 * @Purpose :对我感兴趣
 */
public class BossInterestActivity extends Base2Activity {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private RecyclerView mGmRlvContent;
    private ImageView mIvEmpty;
    private SmartRefreshLayout mGmSmrlayoyut;
    private ArrayList mArray;
    private boolean isRefresh;
    private int mPage;
    private InterestAdapter mAdapter;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_boss_interest);
//
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_boss_interest;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        initView();
        clearData();
        initEvent();
        initAdapter();
        initRefresh();
        mGmSmrlayoyut.autoRefresh();
    }

    private void initAdapter() {
        mAdapter = new InterestAdapter(mContext, mArray);
        RecyclerUtil.setGridManage(mContext, mGmRlvContent, mAdapter);
        mAdapter.setListener(new InterestAdapter.OnItemClickListener() {
            @Override
            public void itemClick(InterestVo.ResultBean.DataBean bean) {
                mResultTo.startBossLookJianli(mContext,String.valueOf( bean.getId()), BossLookResumeActivity.HOME_TYPE);
            }
        });
    }

    private void initEvent() {

    }

    private void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mGmRlvContent = (RecyclerView) findViewById(R.id.gm_rlv_content);
        mIvEmpty = (ImageView) findViewById(R.id.iv_empty);
        mGmSmrlayoyut = (SmartRefreshLayout) findViewById(R.id.gm_smrlayoyut);
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
        RequestBossHttp.getSingleton(this).requestBossDeliverLists(3, String.valueOf(mPage), new RequestResultCallback() {
            @Override
            public void success(String success) {
                isRefresh = false;
                if (refresh)
                    mGmSmrlayoyut.finishRefresh();
                else
                    mGmSmrlayoyut.finishLoadMore();
                if (onSuccess(success)) return;
                InterestVo vo = GsonUtils.getGson(success, InterestVo.class);

                InterestVo.ResultBean result = vo.getResult();
                List<InterestVo.ResultBean.DataBean> data = result.getData();

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
