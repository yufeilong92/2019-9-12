package com.tsyc.tianshengyoucai.ui.activity.type;

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
import com.tsyc.tianshengyoucai.adapter.home.HomSearchAdapter;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.requet.RequestSettingHttp;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.SearchGoodVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/15 11:30:
 * @Purpose :商品分类列表
 */
public class TypeListActivity extends BaseActivity {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private RecyclerView mGmRlvContent;
    private ImageView mIvEmpty;
    private SmartRefreshLayout mGmSmrlayoyut;
    private ArrayList mArray;
    private boolean mRefrehs;
    private int mPage;
    private HomSearchAdapter mAdapter;
    private RequestSettingHttp mHttpReqeust;
    public static final String MCATEGORYIDPARAM="mCategoryIdParam";
    public static final String TITLE="title";
    private String mCateoryId;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_type_list);
//        initView();
//    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_type_list;
    }

    @Override
    public void initData() {
          if (getIntent()!=null){
              mCateoryId = getIntent().getStringExtra(MCATEGORYIDPARAM);
          }
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
    public void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResultTo.finishBase(mContext);
            }
        });
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mGmRlvContent = (RecyclerView) findViewById(R.id.gm_rlv_content);
        mIvEmpty = (ImageView) findViewById(R.id.iv_empty);
        mGmSmrlayoyut = (SmartRefreshLayout) findViewById(R.id.gm_smrlayoyut);
    }

    private void loadNew() {
        if (mRefrehs){
            return;
        }
        mRefrehs=true;
        mHttpReqeust.requestHomeSearch("1", "", "", "",
                mCateoryId, new RequestResultCallback() {
                    @Override
                    public void success(String success) {
                        mRefrehs=false;
                        mGmSmrlayoyut.finishRefresh();
                        NormalBean vos = GsonUtils.getGson(success, NormalBean.class);
                        if (vos.getCode().equals("100")) {
                            mIvEmpty.setVisibility(View.VISIBLE);
                            mGmRlvContent.setVisibility(View.GONE);
                            T.showToast(mContext, vos.getMessage());
                            return;
                        }
                        SearchGoodVo vo = GsonUtils.getGson(success, SearchGoodVo.class);
                        mIvEmpty.setVisibility(View.GONE);
                        mGmRlvContent.setVisibility(View.VISIBLE);
                        clearData();
                        addData(vo.getResult().getData());
                        if (mArray == null || mArray.isEmpty()) {
                            mIvEmpty.setVisibility(View.VISIBLE);
                            mGmSmrlayoyut.setEnableLoadMore(false);
                            mAdapter.notifyDataSetChanged();
                            return;
                        }
                        mIvEmpty.setVisibility(View.GONE);
                        if (mArray.size() <= vo.getResult().getTotal()) {
                            mGmSmrlayoyut.setEnableLoadMore(false);
                            mAdapter.notifyDataSetChanged();
                            return;
                        }
                        mPage = vo.getResult().getCurrent_page() + 1;
                        mAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void error(String error) {
                        mRefrehs=false;
                        mGmSmrlayoyut.finishRefresh();
                        toastErrorNet();
                    }
                });
    }

    private void loadMore() {
        if (mRefrehs){
            return;
        }
        mRefrehs=true;
        mHttpReqeust.requestHomeSearch(String.valueOf(mPage), "", "", "",
                mCateoryId, new RequestResultCallback() {
                    @Override
                    public void success(String success) {
                        mRefrehs=false;
                        mGmSmrlayoyut.finishLoadMore();
                        NormalBean vos = GsonUtils.getGson(success, NormalBean.class);
                        if (vos.getCode().equals("100")) {
                            T.showToast(mContext, vos.getMessage());
                            return;
                        }
                        SearchGoodVo vo = GsonUtils.getGson(success, SearchGoodVo.class);
                        List<SearchGoodVo.ResultBean.DataBean> data = vo.getResult().getData();
                        if (data == null || data.isEmpty()) {
                            mGmSmrlayoyut.setEnableLoadMore(false);
                            mAdapter.notifyDataSetChanged();
                            return;
                        }
                        addData(data);
                        if (mArray.size() <= vo.getResult().getTotal()) {
                            mGmSmrlayoyut.setEnableLoadMore(false);
                            mAdapter.notifyDataSetChanged();
                            return;
                        }
                        mPage = vo.getResult().getCurrent_page() + 1;
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void error(String error) {
                        mRefrehs=false;
                        mGmSmrlayoyut.finishLoadMore();
                        toastErrorNet();
                    }
                });

    }

    private void initAdapter() {
        mAdapter = new HomSearchAdapter(mContext, mArray);
        RecyclerUtil.setGridManage(mContext, mGmRlvContent, 2, mAdapter);
        mAdapter.setListener(new HomSearchAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, SearchGoodVo.ResultBean.DataBean bean) {
                mResultTo.startGoodDetail(mContext, bean);
            }
        });

    }

    private void initEvent() {
        mHttpReqeust = RequestSettingHttp.getSingleton(this);

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
