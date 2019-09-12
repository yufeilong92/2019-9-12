package com.tsyc.tianshengyoucai.ui.recruit.boss;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.fragment.recruite.boss.BossHomeJobFragment;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/9/6 15:41:
 * @Purpose :职位搜素界面
 */
public class BossOtherJobResultActivity extends Base2Activity {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private FrameLayout mFramelayoutContent;
    public static final String JOB_ID = "jobid";
    private String mJobid;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_boss_other_job_result);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_boss_other_job_result;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        if (getIntent() != null) {
            mJobid = getIntent().getStringExtra(JOB_ID);
        }
        initView();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        BossHomeJobFragment fragment = BossHomeJobFragment.newInstance("", mJobid);
        transaction.add(R.id.framelayout_content, fragment);
        transaction.commit();
    }

    private void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mFramelayoutContent = (FrameLayout) findViewById(R.id.framelayout_content);
    }
}
