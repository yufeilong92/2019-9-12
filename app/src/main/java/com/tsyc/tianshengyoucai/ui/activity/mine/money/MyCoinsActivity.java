package com.tsyc.tianshengyoucai.ui.activity.mine.money;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.PageFragmentAdapter;
import com.tsyc.tianshengyoucai.flyn.Eyes;
import com.tsyc.tianshengyoucai.fragment.GmCoinsFragment;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/8 19:59:
 * @Purpose :我的金币
 */
public class MyCoinsActivity extends BaseActivity {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private TabLayout mTabMycoinsTitle;
    private ViewPager mViewpagerMyconis;
    public static final String VALUES = "values";
    private String mValues;
    private TextView mTvMyCoinsValues;
    private TextView mTvMyCoinsExchange;


//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_coins);
//        initView();
//    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_my_coins;

    }

    @Override
    public void initData() {
        Eyes.translucentStatusBar(this, false);
        if (getIntent() != null) {
            mValues = getIntent().getStringExtra(VALUES);
        }
        initView();
        initEvent();
        bindViewData(mValues);

    }

    private void bindViewData(String mValues) {
        mTvMyCoinsValues.setText(mValues);
    }

    private void initEvent() {
        List<Fragment> list = new ArrayList<>();
        list.add(GmCoinsFragment.newInstance("", ""));
        list.add(GmCoinsFragment.newInstance("", "1"));
        list.add(GmCoinsFragment.newInstance("", "2"));
        List<String> titls = new ArrayList<>();
        titls.add("全部");
        titls.add("收入");
        titls.add("支出");
        PageFragmentAdapter adapter = new PageFragmentAdapter(mContext, getSupportFragmentManager(), list, titls);
        mViewpagerMyconis.setAdapter(adapter);
        mTabMycoinsTitle.setupWithViewPager(mViewpagerMyconis);
        Util.setTablayoutCouncis(mContext, adapter, mTabMycoinsTitle);
    }


    public void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmIvBack.setOnClickListener(this);
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mTabMycoinsTitle = (TabLayout) findViewById(R.id.tab_mycoins_title);
        mViewpagerMyconis = (ViewPager) findViewById(R.id.viewpager_myconis);
        mTvMyCoinsValues = (TextView) findViewById(R.id.tv_my_coins_values);
        mTvMyCoinsValues.setOnClickListener(this);
        mTvMyCoinsExchange = (TextView) findViewById(R.id.tv_my_coins_exchange);
        mTvMyCoinsExchange.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gm_iv_back:
                mResultTo.finishBase(mContext);
                break;

            default:
                break;
        }


    }
}
