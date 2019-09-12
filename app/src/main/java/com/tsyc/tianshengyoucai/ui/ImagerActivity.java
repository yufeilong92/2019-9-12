package com.tsyc.tianshengyoucai.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.fragment.ImagerFragment;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/12 20:20:
 * @Purpose :图片查看
 */

public class ImagerActivity extends BaseActivity {
    public static final String listType = "listType";
    public static final String postion = "postion";
    private List<String> mListDatas;
    private int mPostion = 0;
    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private ViewPager mViewpager;
    private TextView mTvNumber;
    private TextView mTvCount;


//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_imager);
//        initView();
//    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_imager;
    }

    @Override
    public void initData() {
        if (getIntent() != null) {
            mListDatas = (List<String>) getIntent().getSerializableExtra(listType);
            mPostion = getIntent().getIntExtra(postion, 0);
        }
        initEvent();
        bindView();


    }

    private void bindView() {
        mTvNumber.setText(String.valueOf(mPostion+1));
        mTvCount.setText(mListDatas.size()+"");
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTvNumber.setText(String.valueOf(position+1));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initEvent() {
        if (mListDatas == null || mListDatas.isEmpty()) return;
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < mListDatas.size(); i++) {
            String s = mListDatas.get(i);
            ImagerFragment imagerFragment = ImagerFragment.newInstance("", s);
            fragments.add(imagerFragment);
        }

        viewpager viewpager = new viewpager(getSupportFragmentManager(), fragments);
        mViewpager.setAdapter(viewpager);
        mViewpager.setCurrentItem(mPostion);

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
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        mTvNumber = (TextView) findViewById(R.id.tv_number);
        mTvCount = (TextView) findViewById(R.id.tv_count);
    }

    private class viewpager extends FragmentPagerAdapter {
        private List<Fragment> data;

        public viewpager(FragmentManager fm, List<Fragment> data) {
            super(fm);
            this.data = data;
        }

        @Override
        public Fragment getItem(int position) {
            return data.get(position);
        }

        @Override
        public int getCount() {
            return data.size();
        }
    }
}
