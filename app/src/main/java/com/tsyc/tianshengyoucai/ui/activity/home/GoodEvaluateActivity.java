package com.tsyc.tianshengyoucai.ui.activity.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tsyc.tianshengyoucai.R;

public class GoodEvaluateActivity extends AppCompatActivity {

    private TextView mTvTitle;
    private RecyclerView mRlvEvaluateContent;
    private SmartRefreshLayout mSmrEvaluate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_evaluate);
        initView();
        initRefresh();
        initEvent();
    }

    private void initRefresh() {
        mSmrEvaluate.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {


            }
        });
        mSmrEvaluate.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {


            }
        });

    }


    private void initView() {
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mRlvEvaluateContent = (RecyclerView) findViewById(R.id.rlv_evaluate_content);
        mSmrEvaluate = (SmartRefreshLayout) findViewById(R.id.smr_evaluate);

    }

    private void initEvent() {


    }
}
