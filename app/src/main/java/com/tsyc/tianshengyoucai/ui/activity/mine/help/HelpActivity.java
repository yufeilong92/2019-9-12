package com.tsyc.tianshengyoucai.ui.activity.mine.help;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.mine.HelpAdapter;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.requet.RequestSettingHttp;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.HelpVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/16 11:05:
 * @Purpose :帮助中心
 */
public class HelpActivity extends BaseActivity {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private RecyclerView mGmRlvContent;
    private SmartRefreshLayout mGmSmrlayoyut;
    private ImageView mIvHelpImg;
    private ArrayList mArray;
    private boolean isRefresh;
    private int mPage;
    private HelpAdapter mAdapter;
    private RequestSettingHttp mHttpRequest;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_help);
//        initView();
//    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_help;
    }

    @Override
    public void initData() {
        initView();
        clearData();
        initEvent();
        initAdapter();
        initRefresh();
        mGmSmrlayoyut.autoRefresh();
    }

    private void initEvent() {
        mHttpRequest = RequestSettingHttp.getSingleton(this);

    }

    private void initAdapter() {
        mAdapter = new HelpAdapter(mContext, mArray);
        RecyclerUtil.setGridManage(mContext, mGmRlvContent, mAdapter);
        mAdapter.setListener(new HelpAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, HelpVo.ResultBean.ListBean.DataBean vo) {
               mResultTo.startHelpDetail(mContext,vo);

            }
        });

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

    private void loadNew() {
        if (isRefresh) {
            return;
        }
        isRefresh = true;
        mHttpRequest.requestHelpList("1", new RequestResultCallback() {
            @Override
            public void success(String success) {
                isRefresh = false;
                mGmSmrlayoyut.finishRefresh();
                NormalBean vos = GsonUtils.getGson(success, NormalBean.class);
                if (vos.getCode().equals("100")) {
                    T.showToast(mContext, vos.getMessage());
                    return;
                }
                HelpVo vo = GsonUtils.getGson(success, HelpVo.class);
                HelpVo.ResultBean bean = vo.getResult();
                bindViewData(bean.getAdv());
                HelpVo.ResultBean.ListBean data = bean.getList();
                List<HelpVo.ResultBean.ListBean.DataBean> list = data.getData();
                clearData();
                addData(list);
                if (list == null || list.isEmpty()) {
                    mGmSmrlayoyut.setEnableLoadMore(false);
                    mAdapter.notifyDataSetChanged();
                    return;
                }
                if (mArray.size() <= data.getTotal()) {
                    mGmSmrlayoyut.setEnableLoadMore(false);
                    mAdapter.notifyDataSetChanged();
                    return;
                }
                mPage = data.getCurrent_page() + 1;
                mGmSmrlayoyut.setEnableLoadMore(true);
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void error(String error) {
                isRefresh = false;
                mGmSmrlayoyut.finishRefresh();
                toastErrorNet();
            }
        });

    }

    private void bindViewData(String adv) {
        GlideUtil.getSingleton().LoadImager(mContext, mIvHelpImg, adv);
    }

    private void loadMore() {
        if (isRefresh) {
            return;
        }
        isRefresh = true;
        mHttpRequest.requestHelpList(String.valueOf(mPage), new RequestResultCallback() {
            @Override
            public void success(String success) {
                isRefresh = false;
                mGmSmrlayoyut.finishLoadMore();

                NormalBean vos = GsonUtils.getGson(success, NormalBean.class);
                if (vos.getCode().equals("100")) {
                    T.showToast(mContext, vos.getMessage());
                    return;
                }
                HelpVo vo = GsonUtils.getGson(success, HelpVo.class);

                HelpVo.ResultBean bean = vo.getResult();
                bindViewData(bean.getAdv());
                HelpVo.ResultBean.ListBean data = bean.getList();
                List<HelpVo.ResultBean.ListBean.DataBean> list = data.getData();
                addData(list);
                if (list == null || list.isEmpty()) {
                    mGmSmrlayoyut.setEnableLoadMore(false);
                    mAdapter.notifyDataSetChanged();
                    return;
                }
                if (mArray.size() <= data.getTotal()) {
                    mGmSmrlayoyut.setEnableLoadMore(false);
                    mAdapter.notifyDataSetChanged();
                    return;
                }
                mPage = data.getCurrent_page() + 1;
                mGmSmrlayoyut.setEnableLoadMore(true);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void error(String error) {
                isRefresh = false;
                mGmSmrlayoyut.finishLoadMore();
                toastErrorNet();
            }
        });
    }

    public void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mGmRlvContent = (RecyclerView) findViewById(R.id.gm_rlv_content);
        mGmSmrlayoyut = (SmartRefreshLayout) findViewById(R.id.gm_smrlayoyut);
        mIvHelpImg = (ImageView) findViewById(R.id.iv_help_img);
        mIvHelpImg.setOnClickListener(this);
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
