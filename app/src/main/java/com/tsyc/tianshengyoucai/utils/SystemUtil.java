package com.tsyc.tianshengyoucai.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public class SystemUtil {
    /**
     * 获取系统版本号
     *
     * @param mContext
     * @return
     */
    public static String getVersion(Context mContext) {
        try {
            return mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";

    }

    /**
     * 获取版本�?
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.w("=", e);
        }
        return 0;
    }

    public static void playPhone(Context mContext, String phone) {
        if (StringUtil.isEmpty(phone)) {
            Toast.makeText(mContext, "电话号码为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+phone));
        mContext.startActivity(intent);
    }

}
