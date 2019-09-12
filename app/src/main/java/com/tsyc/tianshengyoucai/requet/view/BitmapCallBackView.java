package com.tsyc.tianshengyoucai.requet.view;

import android.graphics.Bitmap;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/12 10:18
 * @Purpose :
 */
public interface BitmapCallBackView {
    public void onSuccess(Bitmap bitmap);

    public void onError(String msg);
}
