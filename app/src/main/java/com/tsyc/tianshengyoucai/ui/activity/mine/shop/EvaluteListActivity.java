package com.tsyc.tianshengyoucai.ui.activity.mine.shop;

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
import com.tsyc.tianshengyoucai.adapter.home.EvaluateAapter;
import com.tsyc.tianshengyoucai.manager.DataMangerVo;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.requet.RequestSettingHttp;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.EvaluateVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/10 18:59:
 * @Purpose :评价列表
 */
public class EvaluteListActivity extends BaseActivity {

    public static final String GOODID = "goodid";
    private String mGoodId;
    private RecyclerView mGmRlvContent;
    private ImageView mIvEmpty;
    private SmartRefreshLayout mGmSmrlayoyut;

    private ArrayList mArray;
    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private EvaluateAapter mAdapter;
    private int mPager;
     private boolean isRefresh;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_evalute_list);
//        initView();
//    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_evalute_list;
    }

    @Override
    public void initData() {
        if (getIntent() != null) {
            mGoodId = getIntent().getStringExtra(GOODID);
        }
        initView();
        clearData();
        initEvent();
        initAdapter();
        initRefresh();
        mGmSmrlayoyut.autoRefresh();
    }

    private void initRefresh() {
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

    private void loadMore() {
        if (isRefresh){
            return;
        }
        isRefresh=true;
        RequestSettingHttp.getSingleton(this).requestEvaluateList(mGoodId, "", "1", new RequestResultCallback() {
            @Override
            public void success(String success) {
                isRefresh=false;
                mGmSmrlayoyut.finishLoadMore();
                NormalBean vos = GsonUtils.getGson(success, NormalBean.class);
                if (vos.getCode().equals("100")) {
                    T.showToast(mContext, vos.getMessage());
                    return;
                }
                EvaluateVo vo = GsonUtils.getGson(success, EvaluateVo.class);

                List<EvaluateVo.ResultBean.ListBean.DataBean> data = vo.getResult().getList().getData();
                addData(data);
//                if (mArray == null || mArray.isEmpty()) {
//                    mIvEmpty.setVisibility(View.VISIBLE);
//                    mGmSmrlayoyut.setEnableLoadMore(false);
//                    mAdapter.notifyDataSetChanged();
//                    return;
//                }
//                mIvEmpty.setVisibility(View.GONE);
//                if (mArray.size() <= DataMangerVo.PAGERE_NUMBER) {
//                    mGmSmrlayoyut.setEnableLoadMore(false);
//                    mAdapter.notifyDataSetChanged();
//                    return;
//                }

                EvaluateVo.ResultBean.ListBean bean = vo.getResult().getList();
                if (mArray.size() <= bean.getTotal()) {
                    mGmSmrlayoyut.setEnableLoadMore(false);
                    mAdapter.notifyDataSetChanged();
                    return;
                }
                mPager = vo.getResult().getList().getCurrent_page() + 1;
//                if (mArray.size() % DataMangerVo.PAGERE_NUMBER == 0) {
//                    mGmSmrlayoyut.setEnableLoadMore(true);
//                } else {
//                    mGmSmrlayoyut.setEnableLoadMore(false);
//
//                }
                mAdapter.notifyDataSetChanged();


            }

            @Override
            public void error(String error) {
                isRefresh=false;
                mGmSmrlayoyut.finishLoadMore();
                toastErrorNet();

            }
        });
    }


    private void loadNew() {
        if (isRefresh){
            return;
        }
        isRefresh=true;
        RequestSettingHttp.getSingleton(this).requestEvaluateList(mGoodId, "", "1", new RequestResultCallback() {
            @Override
            public void success(String success) {
                isRefresh=false;
                mGmSmrlayoyut.finishRefresh();
                NormalBean vos = GsonUtils.getGson(success, NormalBean.class);
                if (vos.getCode().equals("100")) {
                    T.showToast(mContext, vos.getMessage());
                    return;
                }
                EvaluateVo vo = GsonUtils.getGson(success, EvaluateVo.class);
                clearData();
                List<EvaluateVo.ResultBean.ListBean.DataBean> data = vo.getResult().getList().getData();
                addData(data);
                if (mArray == null || mArray.isEmpty()) {
                    mIvEmpty.setVisibility(View.VISIBLE);
                    mGmSmrlayoyut.setEnableLoadMore(false);
                    mAdapter.notifyDataSetChanged();
                    return;
                }
                mIvEmpty.setVisibility(View.GONE);
                EvaluateVo.ResultBean.ListBean bean = vo.getResult().getList();
                if (mArray.size() <= bean.getTotal()) {
                    mGmSmrlayoyut.setEnableLoadMore(false);
                    mAdapter.notifyDataSetChanged();
                    return;
                }
                mPager = vo.getResult().getList().getCurrent_page() + 1;
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void error(String error) {
                isRefresh=false;
                mGmSmrlayoyut.finishRefresh();
                toastErrorNet();

            }
        });

    }


    private void initAdapter() {
        RecyclerUtil.setGridManage(mContext, mGmRlvContent);
        mAdapter = new EvaluateAapter(mContext, mArray);
        mGmRlvContent.setAdapter(mAdapter);
        mAdapter.setListener(new EvaluateAapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, EvaluateVo.ResultBean.ListBean.DataBean vo) {

            }

            @Override
            public void imgClick(int postion, List<String> list) {
                mResultTo.startEvaluateImager(mContext, postion, (ArrayList<String>) list);
            }
        });
    }

    private void initEvent() {


    }


    public void initView() {
        mGmRlvContent = (RecyclerView) findViewById(R.id.gm_rlv_content);
        mGmRlvContent.setOnClickListener(this);
        mIvEmpty = (ImageView) findViewById(R.id.iv_empty);
        mIvEmpty.setOnClickListener(this);
        mGmSmrlayoyut = (SmartRefreshLayout) findViewById(R.id.gm_smrlayoyut);
        mGmSmrlayoyut.setOnClickListener(this);
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResultTo.finishBase(mContext);
            }
        });
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
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

    /**
     * 当前数据有几页
     *
     * @return
     */
    private int getNowPage() {
        if (mArray == null || mArray.isEmpty())
            return 0;
        if (mArray.size() % DataMangerVo.PAGERE_NUMBER == 0)
            return mArray.size() / DataMangerVo.PAGERE_NUMBER;
        else
            return mArray.size() / DataMangerVo.PAGERE_NUMBER + 1;
    }
}
