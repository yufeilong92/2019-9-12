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
import com.tsyc.tianshengyoucai.fragment.recruite.boss.BossJobManageFragment;
import com.tsyc.tianshengyoucai.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/9/4 10:49:
 * @Purpose :职位管理
 */
public class PostManageActivity extends Base2Activity {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private TabLayout mTabTitle;
    private ViewPager mViewpageContent;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_post_manage);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_post_manage;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        initView();
        initEvent();
    }

    private void initEvent() {
        List<Fragment> list = new ArrayList<>();
        List<String> titls = new ArrayList<>();
        list.add(BossJobManageFragment.newInstance("", "1"));
        list.add(BossJobManageFragment.newInstance("", "2"));
        titls.add("招聘中");
        titls.add("已关闭");
        PageBuleFragmentAdapter adapter = new PageBuleFragmentAdapter(mContext, getSupportFragmentManager(), list, titls);
        mViewpageContent.setAdapter(adapter);
        mTabTitle.setupWithViewPager(mViewpageContent);
        mViewpageContent.setOffscreenPageLimit(2);
        Util.setBuleTablayoutCouncis(mContext, adapter, mTabTitle);
    }

    private void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mTabTitle = (TabLayout) findViewById(R.id.tab_title);
        mViewpageContent = (ViewPager) findViewById(R.id.viewpage_content);
    }
}
