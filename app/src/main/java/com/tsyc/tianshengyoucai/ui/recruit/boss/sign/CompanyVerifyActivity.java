package com.tsyc.tianshengyoucai.ui.recruit.boss.sign;

import android.os.Bundle;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.base.Base2Activity;

/**
 * author：van
 * CreateTime：2019/8/27
 * File description：公司审核中
 */
public class CompanyVerifyActivity extends Base2Activity {

    private TextView mTvUpdateInfo;
    private TextView mTvUpdateNext;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_company_verify);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_company_verify;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        initView();
        initEvent();
    }

    private void initEvent() {
        mTvUpdateInfo.setOnClickListener(v->{
            mResultTo.startJobGo(mContext);
        });
        mTvUpdateNext.setOnClickListener(v->{

        });
    }

    private void initView() {
        mTvUpdateInfo = (TextView) findViewById(R.id.tv_update_info);
        mTvUpdateNext = (TextView) findViewById(R.id.tv_update_next);
    }
}
