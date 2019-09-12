package com.tsyc.tianshengyoucai.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.tsyc.tianshengyoucai.R;

import java.io.File;

public class GlideUtil {

    private volatile static GlideUtil singleton;

    private GlideUtil() {
    }

    public static GlideUtil getSingleton() {
        if (singleton == null) {
            synchronized (GlideUtil.class) {
                if (singleton == null) {
                    singleton = new GlideUtil();
                }
            }
        }
        return singleton;
    }

    /**
     * 加载图片
     */
    public void LoadImager(Context context, ImageView img, String path) {
//        img.scaleType=ImageView.ScaleType.FIT_XY
        if (StringUtil.isEmpty(path)) {
            img.setImageResource(R.mipmap.ic_default_picture);
            return;
        }
        Glide.with(context)
                .load(path)
                .into(img);
    }
    /**
     * 加载图片
     */
    public void LoadBImager(Context context, ImageView img, String path) {
//        img.scaleType=ImageView.ScaleType.FIT_XY
        if (StringUtil.isEmpty(path)) {
            img.setImageResource(R.mipmap.ic_default_picture);
            return;
        }
        Glide.with(context)
                .load(new File(path))
                .into(img);
    }

    /**
     * 加载图片
     */
    public void LoadImagerWithOutHttp(Context context, ImageView img, String path) {
//        img.scaleType=ImageView.ScaleType.FIT_XY
        if (StringUtil.isEmpty(path)) {
            img.setImageResource(R.mipmap.ic_default_picture);
            return;
        }
        Glide.with(context)
                .load(path)
                .into(img);
    }
    /**
     * 加载图片
     */
    public void LoadBImagerWithOutHttp(Context context, ImageView img, String path) {
//        img.scaleType=ImageView.ScaleType.FIT_XY
        if (StringUtil.isEmpty(path)) {
            img.setImageResource(R.mipmap.ic_default_picture);
            return;
        }
        Glide.with(context)
                .load(new File(path))
                .into(img);
    }

    /**
     * 加载四个圆角
     */
    public void loadQuadRangleImager(Context context, ImageView img, String path) {
//        img.scaleType=ImageView.ScaleType.FIT_XY
        if (StringUtil.isEmpty(path)) {
            img.setImageResource(R.mipmap.ic_default_picture);
            return;
        }
        RoundedCorners roundedCorners = new RoundedCorners(10);
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
//         RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
//        val override = RequestOptions.bitmapTransform(roundedCorners).override(300, 300)
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
//        val float=8.0f
//        val options = RequestOptions.bitmapTransform(
//            RoundCorner(
//                context,
//                leftTop = float,
//                rightBottom = float,
//                rightTop = float,
//                leftBottom = float
//            )
//        )
        Glide.with(context)
                .load(path)
                .apply(options)
                .into(img);
    }

    /**
     * 加载四个圆角
     */
    public void loadBQuadRangleImager(Context context, ImageView img, String path) {
//        img.scaleType=ImageView.ScaleType.FIT_XY
        if (StringUtil.isEmpty(path)) {
            img.setImageResource(R.mipmap.ic_default_picture);
            return;
        }
        RoundedCorners roundedCorners = new RoundedCorners(10);
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
//         RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
//        val override = RequestOptions.bitmapTransform(roundedCorners).override(300, 300)
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
//        val float=8.0f
//        val options = RequestOptions.bitmapTransform(
//            RoundCorner(
//                context,
//                leftTop = float,
//                rightBottom = float,
//                rightTop = float,
//                leftBottom = float
//            )
//        )
        Glide.with(context)
                .load(new File(path))
                .apply(options)
                .into(img);
    }

    /**
     * 加载圆角
     */
    public void loadCilcleImager(Context context, ImageView img, String path) {
//        img.scaleType=ImageView.ScaleType.FIT_XY
        if (StringUtil.isEmpty(path)) {
            img.setImageResource(R.mipmap.ic_default_picture);
            return;
        }
        RequestOptions mRequestOptions = RequestOptions.circleCropTransform()
                .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
                .skipMemoryCache(true);
        Glide.with(context)
                .load(path)
                .apply(mRequestOptions)
                .into(img);
    }

    /**
     * 加载圆角
     */
    public void loadBCilcleImager(Context context, ImageView img, String path) {
//        img.scaleType=ImageView.ScaleType.FIT_XY
        if (StringUtil.isEmpty(path)) {
            img.setImageResource(R.mipmap.ic_default_picture);
            return;
        }
        RequestOptions mRequestOptions = RequestOptions.circleCropTransform()
                .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
                .skipMemoryCache(true);
        Glide.with(context)
                .load(new File(path))
                .apply(mRequestOptions)
                .into(img);
    }
}
