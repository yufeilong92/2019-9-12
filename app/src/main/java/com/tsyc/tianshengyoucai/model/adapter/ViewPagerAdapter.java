package com.tsyc.tianshengyoucai.model.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;
import com.tsyc.tianshengyoucai.ui.base.BaseFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/29
 * File description： viewPager adapter
 */
public class ViewPagerAdapter  extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private FragmentManager fm;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;
    }

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fm = fm;
        this.fragments = fragments;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

/*    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void setFragments(ArrayList<BaseFragment> fragments) {
        if (this.fragments != null) {
            FragmentTransaction ft = fm.beginTransaction();
            for (Fragment f : this.fragments) {
                ft.remove(f);
            }
            ft.commit();
            ft = null;
            fm.executePendingTransactions();
        }
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        Object obj = super.instantiateItem(container, position);
        return obj;
    }*/
}
