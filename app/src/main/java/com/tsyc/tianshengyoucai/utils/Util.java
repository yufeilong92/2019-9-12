package com.tsyc.tianshengyoucai.utils;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.PageBuleFragmentAdapter;
import com.tsyc.tianshengyoucai.adapter.PageFragmentAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/7 16:21
 * @Purpose :
 */
public class Util {

    public static void dismissDialog(AlertDialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public static void setTablayoutCouncis(Context mContext, PageFragmentAdapter adapter, TabLayout mTabMycoinsTitle) {
        mTabMycoinsTitle.setSelectedTabIndicatorColor(mContext.getResources().getColor(R.color.transparent));
        for (int i = 0; i < mTabMycoinsTitle.getTabCount(); i++) {
            TabLayout.Tab tabAt = mTabMycoinsTitle.getTabAt(i);
            if (tabAt != null) {
                tabAt.setCustomView(adapter.getTabView(i));
            }

        }
        View view = mTabMycoinsTitle.getTabAt(0).getCustomView();
        TextView tv = view.findViewById(R.id.tv_content_titel);
        tv.setTextColor(mContext.getResources().getColor(R.color.tab_color));
        View view2 = view.findViewById(R.id.iv_tab_line);
        view2.setVisibility(View.VISIBLE);
        mTabMycoinsTitle.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view1 = tab.getCustomView();
                TextView tv = view1.findViewById(R.id.tv_content_titel);
                tv.setTextColor(mContext.getResources().getColor(R.color.tab_color));
                View view2 = view1.findViewById(R.id.iv_tab_line);
                view2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view1 = tab.getCustomView();
                TextView tv = view1.findViewById(R.id.tv_content_titel);
                tv.setTextColor(mContext.getResources().getColor(R.color.color_444A53));
                View view2 = view1.findViewById(R.id.iv_tab_line);
                view2.setVisibility(View.GONE);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
    }

    public static void setBuleTablayoutCouncis(Context mContext, PageBuleFragmentAdapter adapter, TabLayout mTabMycoinsTitle) {
        mTabMycoinsTitle.setSelectedTabIndicatorColor(mContext.getResources().getColor(R.color.transparent));
        for (int i = 0; i < mTabMycoinsTitle.getTabCount(); i++) {
            TabLayout.Tab tabAt = mTabMycoinsTitle.getTabAt(i);
            if (tabAt != null) {
                tabAt.setCustomView(adapter.getTabView(i));
            }

        }
        View view = mTabMycoinsTitle.getTabAt(0).getCustomView();
        TextView tv = view.findViewById(R.id.tv_content_titel);
        tv.setTextColor(mContext.getResources().getColor(R.color.color_5769E7));
        ImageView view2 = view.findViewById(R.id.iv_tab_line);
        view2.setVisibility(View.VISIBLE);
        mTabMycoinsTitle.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view1 = tab.getCustomView();
                if (view1 == null) return;
                TextView tv = view1.findViewById(R.id.tv_content_titel);
                tv.setTextColor(mContext.getResources().getColor(R.color.color_5769E7));
                View view2 = view1.findViewById(R.id.iv_tab_line);
                view2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view1 = tab.getCustomView();
                if (view1 == null) return;
                TextView tv = view1.findViewById(R.id.tv_content_titel);
                tv.setTextColor(mContext.getResources().getColor(R.color.color_444A53));
                View view2 = view1.findViewById(R.id.iv_tab_line);
                view2.setVisibility(View.GONE);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

    }

    /**
     * 隐藏键盘
     *
     * @param activity activity
     */
    public static void hideInputMethod(Activity activity) {
        hideInputMethod(activity, activity.getCurrentFocus());
    }

    /**
     * 隐藏键盘
     *
     * @param context context
     * @param view    The currently focused view
     */
    public static void hideInputMethod(Context context, View view) {
        if (context == null || view == null) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 判断是否为手机号码
     *
     * @return
     */
    public static boolean isPhoneNum(String phone) {
        Pattern pattern = Pattern.compile("1[0-9]{10}");
        Matcher matcher = pattern.matcher(phone);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 邮箱
     *
     * @return
     */
    public static boolean isEaml(String phone) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$");
        Matcher matcher = pattern.matcher(phone);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }


    private static final int MIN_CLICK_DELAY_TIME = 2000;
    private static long lastClickTime = 0;

    /***
     * 处理多次点击问题
     * @return
     */

    public static boolean handleOnDoubleClick() {
        long l = System.currentTimeMillis();
        if (l - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = l;
            return false;
        }
        return true;
    }


    public static int getRandom() {
        Random random = new Random();
        int i = random.nextInt(3);
        return i;

    }

    public static List<String> getArrayWithId(Context mContext, int id) {
        List<String> list = new ArrayList<>();
        String[] stringArray = mContext.getResources().getStringArray(id);
        for (int i = 0; i < stringArray.length; i++) {
            list.add(stringArray[i]);
        }
        return list;
    }

    public static int getScreenWidth(Context context) {

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(Context context) {

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }


    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


}
