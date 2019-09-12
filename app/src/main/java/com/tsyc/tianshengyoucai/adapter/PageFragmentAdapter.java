package com.tsyc.tianshengyoucai.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/8 20:09
 * @Purpose :
 */
public class PageFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private List<String> mTitles;
    private Context mContext;

    public PageFragmentAdapter(Context mContext, FragmentManager fm, List<Fragment> mfrigments, List<String> mtitls) {
        super(fm);
        this.mFragments = mfrigments;
        this.mTitles = mtitls;
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitles == null || mTitles.isEmpty())
            return super.getPageTitle(position);
        else
            return mTitles.get(position);
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_header, null);
        TextView textView = view.findViewById(R.id.tv_content_titel);
        textView.setText(mTitles.get(position));
        return view;
    }
}
