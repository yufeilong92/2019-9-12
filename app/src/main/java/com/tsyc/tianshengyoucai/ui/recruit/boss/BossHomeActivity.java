package com.tsyc.tianshengyoucai.ui.recruit.boss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.recruit.RcViewPagerAdapter;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.flyn.Eyes;
import com.tsyc.tianshengyoucai.fragment.recruite.boss.BossJobFragment;
import com.tsyc.tianshengyoucai.fragment.recruite.boss.BossMineFragment;
import com.tsyc.tianshengyoucai.fragment.recruite.boss.BossMsgFragment;
import com.tsyc.tianshengyoucai.view.NoScrollViewPager;
import com.tsyc.tianshengyoucai.vo.SelectVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/9/3 16:00:
 * @Purpose :boss求职页面
 */
public class BossHomeActivity extends Base2Activity {

    private NoScrollViewPager mViewpageBossContent;
    private RadioButton mRbBossRcJobs;
    private RadioButton mRbBossRcMsg;
    private RadioButton mRbBossRcMine;
    private RadioGroup mRgBossGroup;
    private BossJobFragment jobFragment;
    public static final String EDU = "edu";
    public static final String EXP = "exp";
    public static final String MONEY = "money";
    public static final String STATUS = "status";

    public static final int SELECT_REQUESTCODE = 1000;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_boss_home);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_boss_home;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        Eyes.translucentStatusBar(this, false);
        initView();
        initEvent();
    }

    private void initView() {
        mViewpageBossContent = (NoScrollViewPager) findViewById(R.id.viewpage_boss_content);
        mRbBossRcJobs = (RadioButton) findViewById(R.id.rb_boss_rc_jobs);
        mRbBossRcMsg = (RadioButton) findViewById(R.id.rb_boss_rc_msg);
        mRbBossRcMine = (RadioButton) findViewById(R.id.rb_boss_rc_mine);
        mRgBossGroup = (RadioGroup) findViewById(R.id.rg_boss_group);
    }
    private void initEvent() {
        List<Fragment> list = new ArrayList<>();
        jobFragment = BossJobFragment.newInstance("", "");
        list.add(jobFragment);
        list.add(BossMsgFragment.newInstance("", ""));
        list.add(BossMineFragment.newInstance("", ""));
        RcViewPagerAdapter adapter = new RcViewPagerAdapter(getSupportFragmentManager(), list);
        mViewpageBossContent.setAdapter(adapter);
        mViewpageBossContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mRbBossRcJobs.setChecked(true);
                        break;
                    case 1:
                        mRbBossRcMsg.setChecked(true);
                        break;
                    case 2:
                        mRbBossRcMine.setChecked(true);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mRgBossGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_boss_rc_jobs:
                        mViewpageBossContent.setCurrentItem(0);
                        break;
                    case R.id.rb_boss_rc_msg:
                        mViewpageBossContent.setCurrentItem(1);
                        break;
                    case R.id.rb_boss_rc_mine:
                        mViewpageBossContent.setCurrentItem(2);
                        break;
                }

            }
        });
        mRbBossRcJobs.setChecked(true);
        mViewpageBossContent.setOffscreenPageLimit(3);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mResultTo.finishBase(this);
        mResultTo.startMain(mContext);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        if (resultCode == RESULT_OK && requestCode == SELECT_REQUESTCODE) {
            List<SelectVo> mEduList = (List<SelectVo>) data.getSerializableExtra(EDU);
            List<SelectVo> mExpList = (List<SelectVo>) data.getSerializableExtra(EXP);
            List<SelectVo> mMoneyList = (List<SelectVo>) data.getSerializableExtra(MONEY);
            List<SelectVo> mStatusList = (List<SelectVo>) data.getSerializableExtra(STATUS);
            jobFragment.notifyType(mEduList,mExpList,mMoneyList,mStatusList);
        }
    }
}
