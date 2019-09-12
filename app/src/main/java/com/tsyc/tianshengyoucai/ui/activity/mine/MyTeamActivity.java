package com.tsyc.tianshengyoucai.ui.activity.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.mine.TeamAdapter;
import com.tsyc.tianshengyoucai.listener.event.NormalEvent;
import com.tsyc.tianshengyoucai.manager.DataMangerVo;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.requet.RequestSettingHttp;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.TeamVo;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/27
 * File description：我的团队
 */
public class MyTeamActivity extends BaseActivity implements View.OnClickListener {


    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private TextView mTvMyTeamValues;
    private RecyclerView mGmRlvContent;
    private ImageView mIvEmpty;
    private SmartRefreshLayout mGmSmrlayoyut;
    private ScrollView mRootView;
    private ArrayList mArray;
    private TeamAdapter mAdapter;
    private boolean isRefresh;
    private Button mBtnMyteamSubmit;

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_team);
//        initView();
//    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_my_team;
    }


    @Override
    public void initData() {
        registerEventBus(this);
        mGmTvTitle.setText(getString(R.string.text_my_team));
        initView();
        initEvent();
        clearData();
        initAapter();
        initRefresh();
        initRequest();

    }


    private void initEvent() {
        mBtnMyteamSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String values = StringUtil.getObjectToStr(mTvMyTeamValues);
                double aDouble = Double.parseDouble(values);
//                if (aDouble == 0) {
//                    T.showToast(mContext, "提现金额不能为零");
//                    return;
//                }

                Bundle bundle = new Bundle();
                bundle.putString("type", "1");
                openActivity(MineCashActivity.class, bundle);

            }
        });

    }

    private void initRequest() {
        showProgress();
        RequestSettingHttp.getSingleton(this).requestMyTeam("1", new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                NormalBean vos = GsonUtils.getGson(success, NormalBean.class);
                if (vos.getCode().equals("100")) {
                    T.showToast(mContext, vos.getMessage());
                    return;
                }
                TeamVo vo = GsonUtils.getGson(success, TeamVo.class);

                mRootView.setVisibility(View.VISIBLE);
                bindView(vo.getResult());
                clearData();
                List<TeamVo.ResultBean.ParentListBean> list = vo.getResult().getParentList();
                if (list == null || list.isEmpty()) {
                    mGmRlvContent.setVisibility(View.GONE);
                    mIvEmpty.setVisibility(View.VISIBLE);
                    mGmSmrlayoyut.setEnableLoadMore(false);
                    return;
                }
                mGmRlvContent.setVisibility(View.VISIBLE);
                mIvEmpty.setVisibility(View.GONE);
                addData(list);
                if (mArray.size() % DataMangerVo.PAGERE_NUMBER == 0) {
                    mGmSmrlayoyut.setEnableLoadMore(true);
                } else {
                    mGmSmrlayoyut.setEnableLoadMore(false);

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

    private void bindView(TeamVo.ResultBean result) {
        mTvMyTeamValues.setText(result.getCommission());
    }

    private void initRefresh() {
        mGmSmrlayoyut.setEnableRefresh(false);
        mGmSmrlayoyut.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore();

            }
        });
    }

    private void loadMore() {
        if (isRefresh) {
            return;
        }
        isRefresh = true;
        RequestSettingHttp.getSingleton(this).requestMyTeam(String.valueOf(getNowPage() + 1), new RequestResultCallback() {
            @Override
            public void success(String success) {
                isRefresh = false;
                mGmSmrlayoyut.finishLoadMore();
                NormalBean vos = GsonUtils.getGson(success, NormalBean.class);
                if (vos.getCode().equals("100")) {
                    T.showToast(mContext, vos.getMessage());
                    return;
                }
                TeamVo vo = GsonUtils.getGson(success, TeamVo.class);

                mRootView.setVisibility(View.VISIBLE);
                bindView(vo.getResult());
                clearData();
                List<TeamVo.ResultBean.ParentListBean> list = vo.getResult().getParentList();
                if (list == null || list.isEmpty()) {
                    mGmRlvContent.setVisibility(View.GONE);
                    mIvEmpty.setVisibility(View.VISIBLE);
                    return;
                }
                mGmRlvContent.setVisibility(View.VISIBLE);
                mIvEmpty.setVisibility(View.GONE);
                addData(list);
                if (mArray.size() % DataMangerVo.PAGERE_NUMBER == 0) {
                    mGmSmrlayoyut.setEnableLoadMore(true);
                } else {
                    mGmSmrlayoyut.setEnableLoadMore(false);

                }
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void error(String error) {
                mGmSmrlayoyut.finishLoadMore();
                toastErrorNet();

            }
        });
    }


    private void initAapter() {
        mAdapter = new TeamAdapter(mContext, mArray);
        RecyclerUtil.setGridManage(mContext, mGmRlvContent, mAdapter);

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
        mTvMyTeamValues = (TextView) findViewById(R.id.tv_my_team_values);
        mGmRlvContent = (RecyclerView) findViewById(R.id.gm_rlv_content);
        mIvEmpty = (ImageView) findViewById(R.id.iv_empty);
        mGmSmrlayoyut = (SmartRefreshLayout) findViewById(R.id.gm_smrlayoyut);
        mRootView = (ScrollView) findViewById(R.id.root_view);
        mRootView.setOnClickListener(this);
        mBtnMyteamSubmit = (Button) findViewById(R.id.btn_myteam_submit);
        mBtnMyteamSubmit.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_myteam_submit:

                break;
        }
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
