package com.tsyc.tianshengyoucai.ui.recruit.jobmine;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.PageBuleFragmentAdapter;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.fragment.mine.DeliveryFragment;
import com.tsyc.tianshengyoucai.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/27 19:37:
 * @Purpose :我的投递
 */
public class MyDeliveryActivity extends Base2Activity {

    private TabLayout mTablayoutMyDelivery;
    private ViewPager mViewpageMyDeliverycontent;
    private PageBuleFragmentAdapter mAdapter;
    public static final String TYPE = "type";
    public static final String ALLTYPE = "alltype";
    public static final String GOTYPE = "GOtype";
    public static final String LOOKTYPE = "LOOKtype";
    public static final String ASKTYPE = "ASKtype";
    public static final String NOTYPE = "NOtype";
    private String mType;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_delivery);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_my_delivery;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        if (getIntent() != null) {
            mType = getIntent().getStringExtra(TYPE);
        }
        initView();
        initEvent();
        initSelect();


    }

    private void initSelect() {
        if (mType.equals(ALLTYPE))//全部
        {
            mViewpageMyDeliverycontent.setCurrentItem(0);
            return;
        }
        if (mType.equals(GOTYPE)) {//已投递
            mViewpageMyDeliverycontent.setCurrentItem(1);
            return;
        }
        if (mType.equals(LOOKTYPE)) {//被查看
            mViewpageMyDeliverycontent.setCurrentItem(2);
            return;
        }
        if (mType.equals(ASKTYPE)) {//邀请面试
            mViewpageMyDeliverycontent.setCurrentItem(3);
            return;
        }
        if (mType.equals(NOTYPE)) {//不合适
            mViewpageMyDeliverycontent.setCurrentItem(4);
            return;
        }
    }

    private void initEvent() {
        List<String> titles = Util.getArrayWithId(mContext, R.array.my_delivery);
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            fragments.add(DeliveryFragment.newInstance("", String.valueOf(i)));
        }
        mAdapter = new PageBuleFragmentAdapter(mContext, getSupportFragmentManager(), fragments, titles);
        mViewpageMyDeliverycontent.setAdapter(mAdapter);
        mTablayoutMyDelivery.setupWithViewPager(mViewpageMyDeliverycontent);
        Util.setBuleTablayoutCouncis(mContext, mAdapter, mTablayoutMyDelivery);
    }

    private void initView() {
        mTablayoutMyDelivery = (TabLayout) findViewById(R.id.tablayout_my_delivery);
        mViewpageMyDeliverycontent = (ViewPager) findViewById(R.id.viewpage_my_deliverycontent);
    }
}
