package com.tsyc.tianshengyoucai.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.demo
 * @Description: todo
 * @author: L-BackPacker
 * @date: 2019/4/1 0:02
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
public class SaveHistoryInfom {
    // 用户名key
    public final static String KEY_NAME = "user";
    private static SaveHistoryInfom sharedUserUtils;
    private final SharedPreferences msp;
    private List<String> s_User =null;

    @SuppressLint("WrongConstant")
    public SaveHistoryInfom(Context context) {
        msp = context.getSharedPreferences("data",
                Context.MODE_PRIVATE | Context.MODE_APPEND);
    }

    public static synchronized void initSharedPreference(Context context) {
        if (sharedUserUtils == null) {
            sharedUserUtils = new SaveHistoryInfom(context);
        }
    }
    public static synchronized SaveHistoryInfom getInstance() {
        return sharedUserUtils;
    }
    public SharedPreferences getSharedPref()
    {
        return msp;
    }


    public  synchronized void putHistoryInfom(List<String> vo){
        SharedPreferences.Editor editor = msp.edit();
        String str=null;
        try {
            str = SerializableUtil.list2String(vo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.putString(KEY_NAME,str);
        editor.commit();
        s_User = vo;
    }
    public synchronized List<String> getHistoryInfom(){
        if (s_User == null)
        {
            s_User = new ArrayList<>();
            //获取序列化的数据
            String str = msp.getString(SaveHistoryInfom.KEY_NAME, "");
            try {
                Object obj = SerializableUtil.string2List(str);
                if(obj != null){
                    s_User = (List<String>) obj;
                }
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return s_User;
    }
    public  synchronized void delectUserInfom(){
        SharedPreferences.Editor editor = msp.edit();
        editor.putString(KEY_NAME,"");
        editor.commit();
        s_User = null;
    }
}
