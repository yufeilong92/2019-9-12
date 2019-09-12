package com.tsyc.tianshengyoucai.ui.recruit.jobmine;

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
import com.tsyc.tianshengyoucai.adapter.recruit.JobCollectAdapter;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.requet.RequestJobHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.DialogUtils;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.JobCollectListVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/30 10:49:
 * @Purpose :职位收藏列表
 */

public class CollectJobActivity extends Base2Activity {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private RecyclerView mGmRlvContent;
    private ImageView mIvEmpty;
    private SmartRefreshLayout mGmSmrlayoyut;
    private boolean isRefresh;
    private ArrayList mArray;
    private int mPage;
    private JobCollectAdapter mAdapter;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_collect_job);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_collect_job;
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

    private void initEvent() {

    }

    private void initAdapter() {
        mAdapter = new JobCollectAdapter(mContext, mArray);
        RecyclerUtil.setGridManage(mContext, mGmRlvContent, mAdapter);
        mAdapter.setListener(new JobCollectAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, JobCollectListVo.ResultBean.DataBean vo) {
               mResultTo.startJobDetail(mContext,vo.getId());
            }

            @Override
            public void itemDelete(int postion, int id) {
                showDeleteAlertDialog(postion, id);
            }
        });
    }

    private void showDeleteAlertDialog(int postion, int id) {
        DialogUtils.getSingleton().showSureAlerDialog(mContext, "是否确认删除",
                "", "确认", "取消", new DialogUtils.OnDialogSuceClickListener() {
                    @Override
                    public void sureClick() {
                        submitDelete(postion, id);
                    }

                    @Override
                    public void cannerClick() {

                    }
                });
    }

    private void submitDelete(int postion, int id) {
        showProgress();
        RequestJobHttp.getSingleton(this).subimtCannerCollectJob(String.valueOf(id), new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                T.showToast(mContext, "删除成功");
                mArray.remove(postion);
                if (mArray == null || mArray.isEmpty()) {
                    mIvEmpty.setVisibility(View.VISIBLE);
                    mGmRlvContent.setVisibility(View.GONE);
                }
                if (mAdapter != null) {
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void error(String error) {
                onError();
            }
        });
    }

    private void initRefresh() {
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
        RequestJobHttp.getSingleton(this).requestJobMyLickList("1", new RequestResultCallback() {
            @Override
            public void success(String success) {
                mGmSmrlayoyut.finishRefresh();
                isRefresh = false;
                if (onSuccess(success)) {
                    mIvEmpty.setVisibility(View.VISIBLE);
                    mGmRlvContent.setVisibility(View.GONE);
                    return;
                }
                showContent(success, true);
            }

            @Override
            public void error(String error) {
                isRefresh = false;
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
        RequestJobHttp.getSingleton(this).requestJobMyLickList(String.valueOf(mPage), new RequestResultCallback() {
            @Override
            public void success(String success) {
                mGmSmrlayoyut.finishLoadMore();
                isRefresh = false;
                if (onSuccess(success)) {
                    return;
                }
                showContent(success, false);
            }

            @Override
            public void error(String error) {
                isRefresh = false;
                mGmSmrlayoyut.finishLoadMore();
                onError();
            }
        });
    }

    private void showContent(String success, boolean isRefresh) {
        JobCollectListVo vo = GsonUtils.getGson(success, JobCollectListVo.class);
        JobCollectListVo.ResultBean result = vo.getResult();
        List<JobCollectListVo.ResultBean.DataBean> lists = result.getData();
        if (isRefresh) {
            clearData();
        }
        if (lists == null || lists.isEmpty()) {
            if (isRefresh) {
                mIvEmpty.setVisibility(View.VISIBLE);
                mGmRlvContent.setVisibility(View.GONE);
            }
            mGmSmrlayoyut.setEnableLoadMore(false);
            mAdapter.notifyDataSetChanged();
            return;
        }
        if (isRefresh) {
            mIvEmpty.setVisibility(View.GONE);
            mGmRlvContent.setVisibility(View.VISIBLE);
        }
        addData(lists);
        if (mArray.size() >= result.getTotal()) {
            mGmSmrlayoyut.setEnableLoadMore(false);
        } else {
            mPage = result.getCurrent_page() + 1;
            mGmSmrlayoyut.setEnableLoadMore(true);
        }
        mAdapter.notifyDataSetChanged();

    }


    private void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mGmRlvContent = (RecyclerView) findViewById(R.id.gm_rlv_content);
        mIvEmpty = (ImageView) findViewById(R.id.iv_empty);
        mGmSmrlayoyut = (SmartRefreshLayout) findViewById(R.id.gm_smrlayoyut);
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
