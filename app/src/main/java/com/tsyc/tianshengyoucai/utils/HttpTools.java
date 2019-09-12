package com.tsyc.tianshengyoucai.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/12 10:21
 * @Purpose :
 */
public class HttpTools {
    /**
     * 判断当前网络是否连接可用
     * @param context 上下文
     * @return boolean
     */
    public static boolean isNetworkAble(Context context){
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info != null && info.isAvailable()) {
                return true;
            }
        }
        return false;
    }
}
