package com.tsyc.tianshengyoucai.utils;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.util.List;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/19 14:27
 * @Purpose :
 */
public class LunBanUtil {

    private volatile static LunBanUtil singleton;
    private static Context mContext;

    private LunBanUtil(Context mContext) {
        this.mContext = mContext;
    }

    public static LunBanUtil getSingleton(Context mContext) {
        if (singleton == null) {
            synchronized (LunBanUtil.class) {
                if (singleton == null) {
                    singleton = new LunBanUtil(mContext);
                }
            }
        }
        return singleton;
    }

    public interface lunBanInterface {
        void imgStart();
        void imgSuccess(String path);

        void imgError();
    }

    public void lunBanImager(String path, lunBanInterface anInterface) {
        Luban.with(mContext)
                .load(path)
                .ignoreBy(100)
                .setTargetDir(FileUtil.getFilePath(mContext))
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        Log.i("压缩==", "开始");
                        if (anInterface != null) {
                            anInterface.imgStart();
                        }
                    }

                    @Override
                    public void onSuccess(File file) {
                        if (anInterface != null) {
                            anInterface.imgSuccess(file.getPath().toString());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("压缩==", "失败");
                        if (anInterface != null) {
                            anInterface.imgError();
                        }
                    }
                }).launch();


    }
    public void lunBanImagerS(List<String> path, lunBanInterface anInterface) {
        Luban.with(mContext)
                .load(path)
                .ignoreBy(100)
                .setTargetDir(FileUtil.getFilePath(mContext))
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        Log.i("压缩==", "开始");
                        if (anInterface != null) {
                            anInterface.imgStart();
                        }
                    }

                    @Override
                    public void onSuccess(File file) {
                        if (anInterface != null) {
                            anInterface.imgSuccess(file.getPath().toString());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("压缩==", "失败");
                        if (anInterface != null) {
                            anInterface.imgError();
                        }
                    }
                }).launch();


    }
}
