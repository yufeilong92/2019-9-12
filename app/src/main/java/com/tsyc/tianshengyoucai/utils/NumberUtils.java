package com.tsyc.tianshengyoucai.utils;

import android.text.TextUtils;

import java.text.DecimalFormat;

/**
 * author：van
 * CreateTime：2019/7/31
 * File description： 数字相关 utils
 */
public class NumberUtils {


    /**
     * 格式化 小数点数据
     *
     * @param numStr     数字字符串
     * @param formatType 格式类型
     * @return 格式化后的数据
     */
    public static String formatNum(String numStr, String formatType) {
        if (TextUtils.isEmpty(numStr) || TextUtils.isEmpty(formatType))
            return "";
        DecimalFormat decimalFormat = new DecimalFormat(formatType); //"#0.00"

        return decimalFormat.format(Float.valueOf(numStr));
    }   /**
     * 格式化 小数点数据
     *
     * @param numStr     数字字符串
     * @return 格式化后的数据
     */
    public static String formatNum(String numStr) {
        if (TextUtils.isEmpty(numStr) || TextUtils.isEmpty("#0.00"))
            return "";
        DecimalFormat decimalFormat = new DecimalFormat("#0.00"); //"#0.00"

        return decimalFormat.format(Float.valueOf(numStr));
    }
}
