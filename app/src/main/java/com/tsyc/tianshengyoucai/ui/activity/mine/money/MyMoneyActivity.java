package com.tsyc.tianshengyoucai.ui.activity.mine.money;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.home.WithdrawAdapter;
import com.tsyc.tianshengyoucai.flyn.Eyes;
import com.tsyc.tianshengyoucai.listener.event.NormalEvent;
import com.tsyc.tianshengyoucai.manager.DataMangerVo;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.requet.RequestSettingHttp;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.MyMoneyVo;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/7 16:32:
 * @Purpose :$我的余额
 */
public class MyMoneyActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTvTitleMoneyShow;
    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private RecyclerView mRlvMoneyContent;
    private LinearLayout mLlContent;
    private TextView mTvMymoneyNumber;
    private SmartRefreshLayout mSmarfrehsl;
    private ArrayList mArray;
    private WithdrawAdapter mAdapter;
    private RelativeLayout mScrollviewMoney;
    private ImageView mIvEmpty;
    private TextView mTvMoneyAdd;
    private TextView mTvMoneyTixian;
    private boolean isRefresh;


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_my_money;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_money);
        Eyes.translucentStatusBar(this, false);

    }

    @Override
    public void initData() {
        registerEventBus(this);
        initView();
        clearData();
        initRequest();
        initEvent();
        initAdapter();
    }

    private void initAdapter() {

        GridLayoutManager manager = new GridLayoutManager(mContext, 1);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        mAdapter = new WithdrawAdapter(mContext, mArray);
        mRlvMoneyContent.setLayoutManager(manager);
        mRlvMoneyContent.setAdapter(mAdapter);

    }

    private int page = 1;

    private void initEvent() {
        mSmarfrehsl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMoreData();

            }
        });

    }

    private void loadMoreData() {
        if (isRefresh) {
            return;
        }
        isRefresh = true;
        RequestSettingHttp.getSingleton(this).requestMyMoney(String.valueOf(page), new RequestResultCallback() {
            @Override
            public void success(String success) {
                isRefresh = false;
                page += 1;
                mSmarfrehsl.finishLoadMore();
                NormalBean datass = GsonUtils.getGson(success, NormalBean.class);
                if (datass.getCode().equals("100")) {
                    T.showToast(mContext, datass.getMessage());
                    return;
                }
                MyMoneyVo data = GsonUtils.getGson(success, MyMoneyVo.class);

                List<MyMoneyVo.ResultBean.ListBean> datas = data.getResult().getList();
                if (datas == null || datas.isEmpty()) {
                    mSmarfrehsl.setEnableLoadMore(false);
                    mAdapter.notifyDataSetChanged();
                    return;
                }
                addData(datas);
                bindViewData(data);
                if (mArray.size() % DataMangerVo.PAGERE_NUMBER == 0) {
                    mSmarfrehsl.setEnableLoadMore(true);
                } else {
                    mSmarfrehsl.setEnableLoadMore(false);
                }

                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void error(String error) {
                mSmarfrehsl.finishLoadMore();
                isRefresh = false;
                toastErrorNet();
            }
        });

    }

    private void initRequest() {
        showProgress();
        RequestSettingHttp.getSingleton(this).requestMyMoney("1", new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                MyMoneyVo data = GsonUtils.getGson(success, MyMoneyVo.class);
                if (data.getCode().equals("100")) {
                    T.showToast(mContext, data.getMessage());
                    return;
                }
                clearData();
                List<MyMoneyVo.ResultBean.ListBean> list = data.getResult().getList();
                if (list == null || list.size() == 0) {
                    mScrollviewMoney.setVisibility(View.GONE);
                    mIvEmpty.setVisibility(View.VISIBLE);
                    mSmarfrehsl.setEnableLoadMore(false);
                    mAdapter.notifyDataSetChanged();
                    return;
                }
                bindViewData(data);
                addData(list);
                mScrollviewMoney.setVisibility(View.VISIBLE);
                mIvEmpty.setVisibility(View.GONE);
                if (mArray.size() % DataMangerVo.PAGERE_NUMBER == 0) {
                    mSmarfrehsl.setEnableLoadMore(true);
                } else {
                    mSmarfrehsl.setEnableLoadMore(false);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void error(String error) {
                dismissProgress();
                toastErrorNet();
            }
        });

    }

    private void bindViewData(MyMoneyVo data) {
        mTvMymoneyNumber.setText(data.getResult().getMy_balance());


    }

    public void initView() {
        mTvTitleMoneyShow = (TextView) findViewById(R.id.tv_title_money_show);
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmIvBack.setOnClickListener(this);
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mRlvMoneyContent = (RecyclerView) findViewById(R.id.rlv_money_content);
        mLlContent = (LinearLayout) findViewById(R.id.ll_content);
        mTvMymoneyNumber = (TextView) findViewById(R.id.tv_mymoney_number);
        mTvMymoneyNumber.setOnClickListener(this);
        mSmarfrehsl = (SmartRefreshLayout) findViewById(R.id.smarfrehsl);
        mSmarfrehsl.setOnClickListener(this);
        mScrollviewMoney = (RelativeLayout) findViewById(R.id.scrollview_money);
        mIvEmpty = (ImageView) findViewById(R.id.iv_empty);
        mIvEmpty.setOnClickListener(this);
        mTvMoneyAdd = (TextView) findViewById(R.id.tv_money_add);
        mTvMoneyAdd.setOnClickListener(this);
        mTvMoneyTixian = (TextView) findViewById(R.id.tv_money_tixian);
        mTvMoneyTixian.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_money_add://充值
                mResultTo.startAddMoney(this);
                break;
            case R.id.tv_money_tixian:
                mResultTo.startTiXianMoney(this);
                break;
            case R.id.gm_iv_back:
                mResultTo.finishBase(this);
                break;
            default:
                break;
        }


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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void rechargeSuccess(NormalEvent event) {
        String msg = event.getMsg();
        int code = event.getCode();
        if (msg.equals("my_team_activity") && code == 124) {
            initRequest();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterEventBus(this);
    }
}
