package com.tsyc.tianshengyoucai.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tsyc.tianshengyoucai.R;

public class StartActivityManger {

    private static Activity mContext;
    private static final String CNT_PARAMETE_TITLE = "param_title";

    public StartActivityManger(Activity m) {
        mContext = m;
    }


    public void jumpTo(Context mContext, Class<?> clazz) {
        Intent intentB = new Intent();
        intentB.setClass(mContext, clazz);
        mContext.startActivity(intentB);
        ((Activity) mContext).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public void jumpTo(Context mContext, Class<?> clazz, String title) {
        Intent intentB = new Intent();
        intentB.setClass(mContext, clazz);
        intentB.putExtra(CNT_PARAMETE_TITLE, title);
        mContext.startActivity(intentB);
        ((Activity) mContext).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public void jumpTo(Context mContext, Class<?> clazz, Bundle bundle) {
        Intent intentB = new Intent();
        intentB.setClass(mContext, clazz);
        intentB.putExtras(bundle);
        mContext.startActivity(intentB);
        ((Activity) mContext).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public void jumpTo(Context mContext, Class<?> clazz, Bundle bundle, String title) {
        Intent intentB = new Intent();
        intentB.setClass(mContext, clazz);
        intentB.putExtras(bundle);
        intentB.putExtra(CNT_PARAMETE_TITLE, title);
        mContext.startActivity(intentB);
        ((Activity) mContext).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }


    public void jumpToFoResult(Context mContext, Class<?> clazz, int resultCode) {
        Intent intentB = new Intent();
        intentB.setClass(mContext, clazz);
        ((Activity) mContext).startActivityForResult(intentB, resultCode);
        ((Activity) mContext).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public void jumpToFoResult(Context mContext, Class<?> clazz, Bundle bundle, int resultCode) {
        Intent intentB = new Intent();
        intentB.setClass(mContext, clazz);
        intentB.putExtras(bundle);
        ((Activity) mContext).startActivityForResult(intentB, resultCode);
        ((Activity) mContext).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public void jumpToBU(Context mContext, Class<?> clazz) {
        Intent intentB = new Intent();
        intentB.setClass(mContext, clazz);
        mContext.startActivity(intentB);
        ((Activity) mContext).overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
    }

    public void jumpToBU(Context mContext, Class<?> clazz, Bundle bundle) {
        Intent intentB = new Intent();
        intentB.setClass(mContext, clazz);
        intentB.putExtras(bundle);
        mContext.startActivity(intentB);
        ((Activity) mContext).overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
    }

    public void jumpToFoResulBU(Context mContext, Class<?> clazz, int resultCode) {
        Intent intentB = new Intent();
        intentB.setClass(mContext, clazz);
        ((Activity) mContext).startActivityForResult(intentB, resultCode);
        ((Activity) mContext).overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
    }


    public void jumpToFoResulBU(Context mContext, Class<?> clazz, Bundle bundle, int resultCode) {
        Intent intentB = new Intent();
        intentB.setClass(mContext, clazz);
        intentB.putExtras(bundle);
        ((Activity) mContext).startActivityForResult(intentB, resultCode);
        ((Activity) mContext).overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
    }

    public void jumpToFoResulBU(Context mContext, Class<?> clazz, Bundle bundle, String title, int resultCode) {
        Intent intentB = new Intent();
        intentB.setClass(mContext, clazz);
        intentB.putExtras(bundle);
        intentB.putExtra(CNT_PARAMETE_TITLE, title);
        ((Activity) mContext).startActivityForResult(intentB, resultCode);
        ((Activity) mContext).overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out);
    }

    public void finishBase(Context mContext) {
        ((Activity) mContext).finish();
        ((Activity) mContext).overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }


    public void finishBaseBU(Context mContext) {
        ((Activity) mContext).finish();
        ((Activity) mContext).overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
    }

}
