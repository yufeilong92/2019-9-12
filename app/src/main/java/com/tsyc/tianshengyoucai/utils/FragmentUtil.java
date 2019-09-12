package com.tsyc.tianshengyoucai.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/11 16:06
 * @Purpose :
 */
public class FragmentUtil {
    /**
     * @param sfm    fragment管理器
     * @param list   fragment集合
     * @param layout 显示fragment 布局
     * @param id     要显示的集合的fragment 的几个
     * @return
     */
    public static FragmentTransaction showSelectFragment(FragmentManager sfm, List<Fragment> list, int layout, int id) {
        if ((id+1) > list.size()) {
            throw new IndexOutOfBoundsException("超出集合长度");
        }
        FragmentTransaction transaction = sfm.beginTransaction();
        Fragment fragment = list.get(id);
        if (!fragment.isVisible()) {
            if (!fragment.isAdded()) {
                transaction.add(layout, fragment, fragment.getClass().getName());
            } else {
                for (int i = 0; i < list.size(); i++) {
                    sfm.beginTransaction().hide(list.get(i)).commit();
                }
                transaction.show(fragment);
            }
        }
        return transaction;
    }
}
