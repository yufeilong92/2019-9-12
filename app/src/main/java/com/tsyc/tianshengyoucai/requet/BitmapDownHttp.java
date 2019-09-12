package com.tsyc.tianshengyoucai.requet;

import android.content.Context;
import android.graphics.Bitmap;

import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.requet.view.BitmapCallBackView;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/12 10:16
 * @Purpose :图片下载
 */
public class BitmapDownHttp extends BaseRequestUtils {


    private volatile static BitmapDownHttp singleton;
    private static Context mContext;

    private BitmapDownHttp(Context mContext) {
        this.mContext = mContext;
    }

    public static BitmapDownHttp getSingleton(Context mContext) {
        if (singleton == null) {
            synchronized (BitmapDownHttp.class) {
                if (singleton == null) {
                    singleton = new BitmapDownHttp(mContext);
                }
            }
        }
        return singleton;
    }



    public interface BitmapInfaceCallBack {
        public void onSuccess(Bitmap bitmap);

        public void onError(String msg);
    }

    /**
     * 请求图标
     * @param path
     * @param callBackView
     */
    public void requestBitmap( String path, final BitmapInfaceCallBack callBackView) {
        requstBitmap( mContext,path, new BitmapCallBackView() {
            @Override
            public void onSuccess(Bitmap bitmap) {
                callBackView.onSuccess(bitmap);
            }

            @Override
            public void onError(String msg) {
                callBackView.onError(msg);
            }
        });
    }
}
