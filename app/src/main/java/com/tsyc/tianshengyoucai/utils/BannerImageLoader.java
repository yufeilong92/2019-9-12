package com.tsyc.tianshengyoucai.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.loader.ImageLoader;

/**
 * author：van
 * CreateTime：2019/7/19
 * File description： 轮播图 loader
 */
public class BannerImageLoader extends ImageLoader {

    //圆角
    private int corners = 0;

    public BannerImageLoader(int corners) {
        this.corners = corners;
    }

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //设置图片圆角角度
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//        RoundedCorners roundedCorners = new RoundedCorners(corners);
        RoundedCorners roundedCorners = new RoundedCorners(corners);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
//        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300, 300);

        Glide.with(context).load(path).apply(options).into(imageView);
    }

}
