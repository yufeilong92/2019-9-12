package com.tsyc.tianshengyoucai.ui.recruit.jobmine;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.recruit.JobAdapter;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.requet.RequestJobHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.vo.BossJobItemVo;
import com.tsyc.tianshengyoucai.vo.JobHomeListsVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/9/7 10:54:
 * @Purpose :全职、兼职、全部职位
 */
public class AllPostionActivity extends Base2Activity {

    private RecyclerView mGmRlvContent;
    private ImageView mIvEmpty;
    private SmartRefreshLayout mGmSmrlayoyut;

    private ArrayList mArray;
    private boolean isRefresh;
    private int mPage;
    private JobAdapter mAdapter;
    public static final String TYPE="TYPE";
    public static final String ALL_TYPE="all_TYPE";
    public static final String JIAN_TYPE="jian_TYPE";
    public static final String QUAN_TYPE="qaun_TYPE";
    private  String mType;
    private String mParams;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_all_postion);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_all_postion;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        if (getIntent()!=null) {
            mType = getIntent().getStringExtra(TYPE);
        }
        initView();
        clearData();
        initEvent();
        initAdapter();
        initRefresh();
        mGmSmrlayoyut.autoRefresh();
    }

    private void initView() {
        mGmRlvContent = (RecyclerView) findViewById(R.id.gm_rlv_content);
        mIvEmpty = (ImageView) findViewById(R.id.iv_empty);
        mGmSmrlayoyut = (SmartRefreshLayout) findViewById(R.id.gm_smrlayoyut);
    }
    private void initEvent() {
         if (mType.equals(ALL_TYPE)){
             mParams="";
         }else if (mType.equals(QUAN_TYPE)){
             mParams="1";
         }else if (mType.equals(JIAN_TYPE)){
             mParams="2";
         }


    }

    private void initAdapter() {
        mAdapter = new JobAdapter(mContext, mArray);
        RecyclerUtil.setGridManage(mContext, mGmRlvContent, mAdapter);
        mAdapter.setListener(new JobAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, BossJobItemVo vo) {
                mResultTo.startJobDetail(mContext, vo.getId());
            }

            @Override
            public void itemDelete(int postion, int id) {

            }
        });
    }

    private void initRefresh() {
        mGmSmrlayoyut.setEnableLoadMore(false);
        mGmSmrlayoyut.setOnRefreshListener(refreshLayout -> {
            loadData(true);
        });
        mGmSmrlayoyut.setOnLoadMoreListener(refreshLayout -> {
            loadData(false);
        });

    }

    private void loadData(boolean refresh) {
        if (isRefresh) {
            return;
        }
        isRefresh = true;
        if (refresh) {
            mPage = 1;
        }

        RequestJobHttp.getSingleton(this).requestPositionlist(mParams,String.valueOf(mPage), new RequestResultCallback() {
            @Override
            public void success(String success) {
                isRefresh=false;
                if (refresh)
                    mGmSmrlayoyut.finishRefresh();
                else
                    mGmSmrlayoyut.finishLoadMore();
                if (onSuccess(success)) return;

                JobHomeListsVo mVo = GsonUtils.getGson(success, JobHomeListsVo.class);
                JobHomeListsVo.ResultBean result = mVo.getResult();
                List<BossJobItemVo> data = result.getData();
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
                isRefresh=false;
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
