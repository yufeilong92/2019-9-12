package com.tsyc.tianshengyoucai.ui.activity.mine.collect;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.PageFragmentAdapter;
import com.tsyc.tianshengyoucai.fragment.collect.GoodCollectFragment;
import com.tsyc.tianshengyoucai.fragment.collect.ShopCollectFragment;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/10 9:32:
 * @Purpose :我的收藏
 */
public class MyCollectActivity extends BaseActivity {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private TabLayout mTabCollectLayout;
    private ViewPager mViewpagerCollectContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_collect);
//        initView();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_my_collect;
    }

    @Override
    public void initData() {
        initView();
        initEvent();
        bindViewData();
    }

    private void bindViewData() {


    }

    private void initEvent() {
        List<Fragment> list = new ArrayList<>();
        list.add(GoodCollectFragment.newInstance("", "1"));
        list.add(ShopCollectFragment.newInstance("", "2"));
        List<String> titls = new ArrayList<>();
        titls.add("收藏的商品");
        titls.add("收藏的店铺");
        PageFragmentAdapter adapter = new PageFragmentAdapter(mContext, getSupportFragmentManager(), list, titls);
        mViewpagerCollectContent.setAdapter(adapter);
        mTabCollectLayout.setupWithViewPager(mViewpagerCollectContent);
        Util.setTablayoutCouncis(mContext, adapter, mTabCollectLayout);
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
        mTabCollectLayout = (TabLayout) findViewById(R.id.tab_collect_layout);
        mViewpagerCollectContent = (ViewPager) findViewById(R.id.viewpager_collect_content);
    }
}
