package com.tsyc.tianshengyoucai;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.DBCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tsyc.tianshengyoucai.ui.recruit.chat.ForegroundCallbacks;
import com.tsyc.tianshengyoucai.ui.recruit.chat.WsManager;
import com.tsyc.tianshengyoucai.utils.SaveHistoryInfom;
import com.tsyc.tianshengyoucai.utils.SaveJobUtil;
import com.tsyc.tianshengyoucai.utils.SaveUserInfomUtilJave;
import com.youth.xframe.XFrame;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

/**
 * author：cxd
 * CreateTime：2019/7/18
 * File description：
 */
public class AppContext extends Application {

    private static List<Activity> activityList;

    private static AppContext mAppContext;
    private WsManager mWebStock;

    public static AppContext getInstance() {
        return mAppContext;
    }

    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        activityList = new ArrayList<>();

        mAppContext = this;

        XFrame.init(mAppContext);
        XFrame.initXLog();

        initOkGo();
        SaveUserInfomUtilJave.initSharedPreference(this);
        SaveJobUtil.initSharedPreference(this);
        SaveHistoryInfom.initSharedPreference(this);
        initRefresh();
        initAppStatusListener();
    }

    private void initAppStatusListener() {
        ForegroundCallbacks.init(this).addListener(new ForegroundCallbacks.Listener() {
            @Override
            public void onBecameForeground() {
                Log.d("===", "应用回到前台调用重连方法");
                WsManager.getInstance().reconnect();
            }

            @Override
            public void onBecameBackground() {

            }
        });
    }

    private void initRefresh() {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.white, android.R.color.darker_gray);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    private void initOkGo() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);

        //全局的读取超时时间 测试为10s  正式为30s
        builder.readTimeout(60000, TimeUnit.MILLISECONDS);
        //全局的写入超时时间
        builder.writeTimeout(60000, TimeUnit.MILLISECONDS);
        //全局的连接超时时间
        builder.connectTimeout(60000, TimeUnit.MILLISECONDS);

        //使用数据库保持cookie，如果cookie不过期，则一直有效
        builder.cookieJar(new CookieJarImpl(new DBCookieStore(this)));
        OkGo.getInstance().init(this)                       //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3);                           //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
    }


    public static void clearActivity() {
        for (Activity a :
                activityList) {
            a.finish();
        }
    }
}
