package com.tsyc.tianshengyoucai.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.tsyc.tianshengyoucai.vo.JobInfom;

import java.io.IOException;
import java.io.StreamCorruptedException;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.demo
 * @Description: 工作者信息
 * @author: L-BackPacker
 * @date: 2019/4/1 0:02
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
public class SaveJobUtil {
    // 用户名key
    public final static String KEY_NAME = "jobinfom";
    private static SaveJobUtil sharedUserUtils;
    private final SharedPreferences msp;
    private JobInfom s_User =null;

    @SuppressLint("WrongConstant")
    public SaveJobUtil(Context context) {
        msp = context.getSharedPreferences("data",
                Context.MODE_PRIVATE | Context.MODE_APPEND);
    }

    public static synchronized void initSharedPreference(Context context) {
        if (sharedUserUtils == null) {
            sharedUserUtils = new SaveJobUtil(context);
        }
    }
    public static synchronized SaveJobUtil getInstance() {
        return sharedUserUtils;
    }
    public SharedPreferences getSharedPref()
    {
        return msp;
    }


    public  synchronized void putJobInfom(JobInfom vo){
        SharedPreferences.Editor editor = msp.edit();
        String str=null;
        try {
            str = SerializableUtil.obj2Str(vo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.putString(KEY_NAME,str);
        editor.commit();
        s_User = vo;
    }
    public synchronized JobInfom getJobInfom(){
        if (s_User == null)
        {
            s_User = new JobInfom();
            //获取序列化的数据
            String str = msp.getString(SaveJobUtil.KEY_NAME, "");
            try {
                Object obj = SerializableUtil.str2Obj(str);
                if(obj != null){
                    s_User = (JobInfom) obj;
                }
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return s_User;
    }
    public  synchronized void delectJobInfom(){
        SharedPreferences.Editor editor = msp.edit();
        editor.putString(KEY_NAME,"");
        editor.commit();
        s_User = null;
    }
}
