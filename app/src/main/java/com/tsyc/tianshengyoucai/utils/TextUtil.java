package com.tsyc.tianshengyoucai.utils;

import android.view.View;
import android.widget.TextView;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/8 14:19
 * @Purpose :工具
 */
public class TextUtil {
    public static void bindTextViewWHine(TextView tv, String com) {
        if (StringUtil.isEmpty(com)) {
            tv.setVisibility(View.GONE);
            return;
        }
        tv.setVisibility(View.VISIBLE);
        tv.setText(com);
    }

    public static void bindTextView(TextView tv, String com) {
        if (StringUtil.isEmpty(com)) {
            tv.setText("");
            return;
        }
        tv.setText(com);
    }
}
