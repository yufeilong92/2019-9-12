package com.tsyc.tianshengyoucai.manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.tsyc.tianshengyoucai.AppContext;
import com.tsyc.tianshengyoucai.model.bean.LoginSuccessBean;

/**
 * author：YL
 * CreateTime：2019/6/15
 * File description：
 */
public class SPManager {
    private static SharedPreferences mPublicSP;
    private static SharedPreferences mSaveTimeSP;
    public static final String PROVINCE_CITY = "province_city";

    public static final String KEY = "key";
    public static final String USER_ID = "userid";
    public static final String USER_NAME = "username";
    public static final String USER_AVATAR = "avatar";
    public static final String USER_MEMBER_POINTS = "member_points";
    public static final String USER_INVITE_CODE = "invite_code";
    public static final String USER_MY_BALANCE = "my_balance";
    public static final String USER_PHONE = "phone";
    public static final String USER_GRADE_STATUS = "grade_status";
    public static final String USER_MYVOUCHER = "myvoucher";
    public static final String USER_BIND_ALIPAY_STATUS = "bindAliPayStatus";
    public static final String USER_BIND_WECHAT_STATUS = "bindWeChatStatus";
    public static final String USER_BIND_BANK_STATUS = "bindBankStatus";
    public static final String USER_IS_STORE = "is_store";
    public static final String USER_SHARE_LINK = "share_link";
    public static final String USER_SHARE_QRCODE = "share_qrcode";
    public static final String TAO_CODE_TIME = "tao_code_time";
    public static final String TAO_CODE = "tao_code";
    public static final String TAO_GOODS_ID = "tao_goods_id";
    public static final String IS_OPEN = "is_open";


    @SuppressLint("WrongConstant")
    public static SharedPreferences getPublicSP() {
        if (mPublicSP == null) {
            mPublicSP = AppContext.getInstance().getSharedPreferences("tsyc", Context.MODE_PRIVATE | Context.MODE_APPEND);
        }
        return mPublicSP;
    }

    //保存用户信息
    public static void saveUserInfo(LoginSuccessBean dataBean) {

        getPublicSP();
        SharedPreferences.Editor editor = mPublicSP.edit();
        editor.putInt(USER_ID, dataBean.getResult().getUserid());
        editor.putString(USER_NAME, dataBean.getResult().getUsername());
        editor.putString(KEY, dataBean.getResult().getKey());
        editor.apply();
    }

    public static void clearUserInfo() {
        getPublicSP();
        SharedPreferences.Editor editor = mPublicSP.edit();
        editor.putString(KEY, "");
        editor.putInt(USER_ID, 0);
        editor.putString(USER_NAME, "");
        editor.apply();
    }
}
