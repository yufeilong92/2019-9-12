package com.tsyc.tianshengyoucai.ui.recruit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.recruit.RcViewPagerAdapter;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.flyn.Eyes;
import com.tsyc.tianshengyoucai.fragment.recruite.job.RcHomeFragment;
import com.tsyc.tianshengyoucai.fragment.recruite.job.RcMineFragment;
import com.tsyc.tianshengyoucai.fragment.recruite.job.RcMsgFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/23 11:20:
 * @Purpose :招聘首页
 */
public class RecruitHomeActivity extends Base2Activity {


    private ViewPager mViewpagerContent;
    private RadioButton mRbRcJobs;
    private RadioButton mRbRcMsg;
    private RadioButton mRbRcMine;
    private RadioGroup mRgGroup;

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_recruit_home);
//        initView();
//    }


    @Override
    protected int getComtentView() {
        return R.layout.activity_recruit_home;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        Eyes.translucentStatusBar(this, false);
        initView();
        initEvent();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mResultTo.finishBase(this);
        mResultTo.startMain(mContext);
    }

    private void initEvent() {
        List<Fragment> list = new ArrayList<>();
        list.add(RcHomeFragment.newInstance("", ""));
        list.add(RcMsgFragment.newInstance("", ""));
        list.add(RcMineFragment.newInstance("", ""));
        RcViewPagerAdapter adapter = new RcViewPagerAdapter(getSupportFragmentManager(), list);
        mViewpagerContent.setAdapter(adapter);
        mViewpagerContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mRbRcJobs.setChecked(true);
                        break;
                    case 1:
                        mRbRcMsg.setChecked(true);
                        break;
                    case 2:
                        mRbRcMine.setChecked(true);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mRgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_rc_jobs:
                        mViewpagerContent.setCurrentItem(0);
                        break;
                    case R.id.rb_rc_msg:
                        mViewpagerContent.setCurrentItem(1);
                        break;
                    case R.id.rb_rc_mine:
                        mViewpagerContent.setCurrentItem(2);
                        break;
                }

            }
        });
        mRbRcJobs.setChecked(true);
        mViewpagerContent.setOffscreenPageLimit(3);
    }


    private void initView() {
        mViewpagerContent = (ViewPager) findViewById(R.id.viewpager_content);
        mRbRcJobs = (RadioButton) findViewById(R.id.rb_rc_jobs);
        mRbRcMsg = (RadioButton) findViewById(R.id.rb_rc_msg);
        mRbRcMine = (RadioButton) findViewById(R.id.rb_rc_mine);
        mRgGroup = (RadioGroup) findViewById(R.id.rg_group);
    }
}
