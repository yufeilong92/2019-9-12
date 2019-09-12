package com.tsyc.tianshengyoucai.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.provider.MediaStore;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.utils.matisse_picker.Glide4Engine;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/9 15:51
 * @Purpose :
 */
public class ActivityUtil {
    /***
     * 打开相册
     * @param mContext
     * @param REQUESTCODECE
     */
    public static void openXiangCe(Activity mContext, int REQUESTCODECE) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        mContext.startActivityForResult(intent, REQUESTCODECE);
    }

    public static void openCammer(Activity mContext, int REQUESTCODE) {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mContext.startActivityForResult(camera, REQUESTCODE);
    }


    public static void openXiangCes(Activity mContext,int REQUESTCODE){

        Matisse.from(mContext)
                .choose(MimeType.ofImage(), true)
                .showSingleMediaType(true)
                .countable(false)
                .capture(false)
//                .captureStrategy(
//                        new  (true,
//                                "com.tsyc.tianshengyoucai.fileprovider",
//                                "tsyc"))  // 设置正确，才能调起相机
                .maxSelectable(1)
//                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(mContext.getResources().getDimensionPixelSize(R.dimen.dp_120))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(new Glide4Engine())    // for glide-V4
//                .setOnSelectedListener((uriList, pathList) -> {
//                    selectPhotoListener(pathList);
//                })
                .originalEnable(true)
                .maxOriginalSize(10)
                .autoHideToolbarOnSingleTap(true)
                .forResult(REQUESTCODE);
    }
}
