package com.tsyc.tianshengyoucai.ui.activity.mine.money;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.PageFragmentAdapter;
import com.tsyc.tianshengyoucai.fragment.CouponFragment;
import com.tsyc.tianshengyoucai.fragment.PackerFragment;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.Util;

import java.util.ArrayList;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/8 20:40:
 * @Purpose :我的红包
 */

public class MyRedPacketActivity extends BaseActivity {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private TabLayout mTabPacketTitle;
    private ViewPager mViewpageContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_red_packet);
//        initView();
    }

    @Override
    public void initData() {
        initView();
        initEvent();
    }

    private void initEvent() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(PackerFragment.newInstance("", ""));
        fragments.add(CouponFragment.newInstance("", "2"));
        ArrayList<String> titel = new ArrayList<>();
        titel.add("店铺红包");
        titel.add("优惠卷");
        PageFragmentAdapter adapter = new PageFragmentAdapter(mContext,getSupportFragmentManager(), fragments, titel);
        mViewpageContent.setAdapter(adapter);
        mTabPacketTitle.setupWithViewPager(mViewpageContent);
        Util.setTablayoutCouncis(mContext,adapter,mTabPacketTitle);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_my_red_packet;
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
        mTabPacketTitle = (TabLayout) findViewById(R.id.tab_packet_title);
        mViewpageContent = (ViewPager) findViewById(R.id.viewpage_content);
    }
}
