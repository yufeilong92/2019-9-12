package com.tsyc.tianshengyoucai.ui.recruit.boss;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.PageBuleFragmentAdapter;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.fragment.recruite.boss.BossOtherFragment;
import com.tsyc.tianshengyoucai.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/9/4 18:09:
 * @Purpose :已处理
 */
public class BossOtherActivity extends Base2Activity {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private TabLayout mTabBossOther;
    private ViewPager mViewpageBossContent;
    private PageBuleFragmentAdapter mAdapter;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_boss_other);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_boss_other;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        initView();
        List<String> titles = new ArrayList<>();
        titles.add("简历");
        titles.add("面试");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(BossOtherFragment.newInstance("", "1"));
        fragments.add(BossOtherFragment.newInstance("", "2"));
        mAdapter = new PageBuleFragmentAdapter(mContext, getSupportFragmentManager(), fragments, titles);
        mViewpageBossContent.setAdapter(mAdapter);
        mTabBossOther.setupWithViewPager(mViewpageBossContent);
        Util.setBuleTablayoutCouncis(mContext, mAdapter, mTabBossOther);
        mViewpageBossContent.setCurrentItem(0);

    }

    private void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mTabBossOther = (TabLayout) findViewById(R.id.tab_boss_other);
        mViewpageBossContent = (ViewPager) findViewById(R.id.viewpage_boss_content);
    }
}
