package com.tsyc.tianshengyoucai.ui.recruit.infom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.recruit.JobListAdapter;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.requet.RequestJobHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.JobSelectIndox;
import com.tsyc.tianshengyoucai.vo.JobSelectItemVo;
import com.tsyc.tianshengyoucai.vo.JobSelectVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/20 11:32:
 * @Purpose :职位选择
 */
public class JobSelectActivity extends Base2Activity {

    private RecyclerView mRlvJobContent;
    private String mSelcetKey;
    private ArrayList mArray;
    private ArrayList mArrayLife;
    private ArrayList mArrayRight;
    private JobListAdapter mAdapter;
    private JobListAdapter mAdapterLife;
    private JobListAdapter mAdapterRight;
    private RecyclerView mRlvJobSelectLife;
    private RecyclerView mRlvJobSelectRight;
    private LinearLayout mLlJobSelectLayout;
    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private LinearLayout mLlJobSelectRight;
    private View mIvJobSelectLife;
    /**
     * 用回选中的数据
     */
    private JobSelectIndox mIndox;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_job_select);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_job_select;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        initView();
        initEvent();
        clearData();
        clearLifeData();
        clearRightData();
        initAdapterDown();
        initAdapterLife();
        initAdapterRight();
        initRequest();

    }


    private void initRequest() {
        showProgress();
        RequestJobHttp.getSingleton(this).requestPositions(mSelcetKey, new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                JobSelectVo vo = GsonUtils.getGson(success, JobSelectVo.class);
                if (vo.getCode().equals("100")) {
                    T.showToast(mContext, vo.getMessage());
                    return;
                }
                List<JobSelectItemVo> listItem = vo.getResult();
                clearData();
                addData(listItem);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void error(String error) {
                onError();
            }
        });


    }

    private void initAdapterDown() {
        mAdapter = new JobListAdapter(mContext, mArray, 1);
        RecyclerUtil.setGridManage(mContext, mRlvJobContent, mAdapter);
        mAdapter.setListener(new JobListAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, JobSelectItemVo vo) {
                List<JobSelectItemVo> list = vo.getSumItems();
                mIndox.setTypeone(vo.getId());
                mIndox.setSelectVo(vo);
                mAdapter.refreshSelectVo(mIndox);
                if (list == null || list.isEmpty()) {
                    mLlJobSelectLayout.setVisibility(View.GONE);
                    setResult();
                    return;
                }
                mLlJobSelectLayout.setVisibility(View.VISIBLE);
                clearLifeData();
                clearRightData();
                addLifeData(list);
                mAdapterLife.notifyDataSetChanged();
                mAdapterRight.notifyDataSetChanged();
            }
        });
    }

    private void initAdapterLife() {
        mAdapterLife = new JobListAdapter(mContext, mArrayLife, 2);
        RecyclerUtil.setGridManage(mContext, mRlvJobSelectLife, mAdapterLife);
        mAdapterLife.setListener(new JobListAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, JobSelectItemVo vo) {
                List<JobSelectItemVo> list = vo.getSumItems();
                mIndox.setTypetwo(vo.getId());
                mIndox.setSelectVo(vo);
                mAdapterLife.refreshSelectVo(mIndox);
                if (list == null || list.isEmpty()) {
                    mLlJobSelectLayout.setVisibility(View.GONE);
                    setResult();
                    return;
                }
                mRlvJobSelectRight.setVisibility(View.VISIBLE);
                clearRightData();
                addRightData(list);
                mAdapterRight.notifyDataSetChanged();

            }
        });

    }

    private void initAdapterRight() {
        mAdapterRight = new JobListAdapter(mContext, mArrayRight, 3);
        RecyclerUtil.setGridManage(mContext, mRlvJobSelectRight, mAdapterRight);
        mAdapterRight.setListener(new JobListAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, JobSelectItemVo vo) {
                mIndox.setTypethree(vo.getId());
                mIndox.setSelectVo(vo);
                mAdapterRight.refreshSelectVo(mIndox);
                mLlJobSelectLayout.setVisibility(View.GONE);
                setResult();
            }
        });
    }


    private void initEvent() {
        mLlJobSelectLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLlJobSelectLayout.setVisibility(View.GONE);
            }
        });

        mIndox =new JobSelectIndox();

    }

    private void initView() {
        mRlvJobContent = (RecyclerView) findViewById(R.id.rlv_job_content);
        mRlvJobSelectLife = (RecyclerView) findViewById(R.id.rlv_job_select_life);
        mRlvJobSelectRight = (RecyclerView) findViewById(R.id.rlv_job_select_right);
        mLlJobSelectLayout = (LinearLayout) findViewById(R.id.ll_job_select_layout);
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mLlJobSelectRight = (LinearLayout) findViewById(R.id.ll_job_select_right);
        mIvJobSelectLife = (View) findViewById(R.id.iv_job_select_life);
    }

    private void setResult() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable(JobPurposeActivity.SELECT_JOBVO, mIndox);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        mResultTo.finishBase(mContext);
    }

    private void clearData() {
        if (mArray == null) {
            mArray = new ArrayList();
        } else {
            mArray.clear();
        }
    }

    private void clearLifeData() {
        if (mArrayLife == null) {
            mArrayLife = new ArrayList();
        } else {
            mArrayLife.clear();
        }
    }

    private void clearRightData() {
        if (mArrayRight == null) {
            mArrayRight = new ArrayList();
        } else {
            mArrayRight.clear();
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

    private void addLifeData(List<?> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        if (mArrayLife == null) {
            clearData();
        }
        mArrayLife.addAll(list);
    }

    private void addRightData(List<?> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        if (mArrayRight == null) {
            clearData();
        }
        mArrayRight.addAll(list);
    }
}
