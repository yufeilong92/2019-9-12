package com.tsyc.tianshengyoucai.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;


public class T {
    private static Toast toast = null;
    private static Handler mHandler = new Handler();

    private static Runnable r = new Runnable() {
        @Override
        public void run() {
            if (toast != null)
                toast.cancel();
        }
    };

    public static void showToast(Context mContext, String com) {
        mHandler.removeCallbacks(r);
        if (toast != null) {
            toast.setText(com);
        } else {
            toast = Toast.makeText(mContext, com, Toast.LENGTH_SHORT);

        }
        mHandler.postDelayed(r, 2000);
        toast.show();
    }

    public static void showToast(Context mContext, Integer id) {
        showToast(mContext, mContext.getString(id));
    }

}
