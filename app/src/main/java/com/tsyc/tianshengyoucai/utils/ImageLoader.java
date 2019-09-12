package com.tsyc.tianshengyoucai.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.tsyc.tianshengyoucai.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by Meiji on 2017/5/31.
 */
@GlideModule
public class ImageLoader extends AppGlideModule {

    public static void loadCenterCrop(Context context, String url, ImageView view, int defaultResId) {
        if (SettingUtil.getInstance().getIsNoPhotoMode() && NetWorkUtils.isMobileConnected(context)) {
            view.setImageResource(defaultResId);
        } else {
//            GlideApp.with(context)
            Glide.with(context)
                    .load(url)
                    .transition(withCrossFade())
                    .apply(new RequestOptions().centerCrop())
                    .apply(new RequestOptions().placeholder(R.mipmap.jft_img_occupancymap))
                    .apply(new RequestOptions().error(R.mipmap.jft_img_occupancymap))
                    .into(view);
        }
    }

    /**
     * 加载圆角图片
     *
     * @param context c
     * @param url     u
     * @param view    v
     * @param corner  圆角
     */
    public static void loadCornersImage(Context context, String url, ImageView view, int corner) {
        //设置图片圆角角度
        RoundedCorners roundedCorners = new RoundedCorners(corner);
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300, 300);

        Glide.with(context)
                .load(url)
                .apply(options)
                .apply(new RequestOptions().placeholder(R.mipmap.jft_img_occupancymap))
                .apply(new RequestOptions().error(R.mipmap.jft_img_occupancymap))
                .into(view);
    }

    /**
     * 加载圆角图片
     *
     * @param context c
     * @param resId   u
     * @param view    v
     * @param corner  圆角
     */
    public static void loadCornersImage(Context context, int resId, ImageView view, int corner) {
        //设置图片圆角角度
        RoundedCorners roundedCorners = new RoundedCorners(corner);
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300, 300);

        Glide.with(context)
                .load(resId)
                .apply(options)
                .apply(new RequestOptions().placeholder(R.mipmap.jft_img_occupancymap))
                .apply(new RequestOptions().error(R.mipmap.jft_img_occupancymap))
                .into(view);
    }

    /**
     * 带加载异常图片
     */
    public static void loadCenterCrop(Context context, String url, ImageView view, int defaultResId, int errorResId) {
        if (SettingUtil.getInstance().getIsNoPhotoMode() && NetWorkUtils.isMobileConnected(context)) {
            view.setImageResource(defaultResId);
        } else {
            Glide.with(context)
                    .load(url)
                    .transition(withCrossFade())
                    .apply(new RequestOptions().centerCrop().error(errorResId))
                    .apply(new RequestOptions().placeholder(R.mipmap.jft_img_occupancymap))
                    .apply(new RequestOptions().error(R.mipmap.jft_img_occupancymap))
                    .into(view);
        }
    }

    /**
     * 带监听处理
     */
    public static void loadCenterCrop(Context context, String url, ImageView view, RequestListener listener) {
        Glide.with(context)
                .load(url)
                .transition(withCrossFade())
                .apply(new RequestOptions().centerCrop())
                .apply(new RequestOptions().placeholder(R.mipmap.jft_img_occupancymap))
                .apply(new RequestOptions().error(R.mipmap.jft_img_occupancymap))
                .listener(listener)
                .into(view);
    }

    /**
     * 加载普通图片
     *
     * @param context
     * @param url
     * @param view
     */
    public static void loadNormal(Context context, String url, ImageView view) {
        RoundedCorners roundedCorners = new RoundedCorners(10);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
        Glide.with(context)
                .load(url)
                .transition(new DrawableTransitionOptions().crossFade(200))
                .apply(options)
                .apply(new RequestOptions().placeholder(R.mipmap.jft_img_occupancymap))
                .apply(new RequestOptions().error(R.mipmap.jft_img_occupancymap))
                .into(view);
    }

    /**
     * 加载普通图片
     *
     * @param context
     * @param bmp
     * @param view
     */
    public static void loadNormal(Context context, Bitmap bmp, ImageView view) {
        Glide.with(context)
                .load(bmp)
                .transition(new DrawableTransitionOptions().crossFade(200))
//                .apply(new RequestOptions().placeholder(R.drawable.shape_img_loader))
                .apply(new RequestOptions().placeholder(R.mipmap.jft_img_occupancymap))
                .apply(new RequestOptions().error(R.mipmap.jft_img_occupancymap))
                .into(view);
    }

    /**
     * 加载普通图片
     *
     * @param context
     * @param resIds
     * @param view
     */
    public static void loadNormal(Context context, int resIds, ImageView view) {
        Glide.with(context)
                .load(resIds)
                .transition(new DrawableTransitionOptions().crossFade(200))
                .apply(new RequestOptions().placeholder(R.mipmap.jft_img_occupancymap))
                .apply(new RequestOptions().error(R.mipmap.jft_img_occupancymap))
                .into(view);
    }

    /**
     * 视频设置第一帧
     *
     * @param mContext
     * @param url
     * @param view
     */

    //    public static void setImage(Context mContext, String url, JzvdStd view) {
    //        Glide.with(mContext)
    //                .load(url)
    //                .into(view.thumbImageView);
    //    }

    /**
     * 设置圆形图片
     *
     * @param mContext v
     * @param url      v
     * @param view     v
     */
    public static void setImageCircle(Context mContext, String url, ImageView view) {
        Glide.with(mContext).load(url).apply(RequestOptions.
                bitmapTransform(new CircleCrop()))
                .apply(new RequestOptions().placeholder(R.mipmap.jft_img_occupancymap))
                .apply(new RequestOptions().error(R.mipmap.jft_img_occupancymap))
                .into(view);
    }

    /**
     * 设置圆形图片
     *
     * @param mContext v
     * @param url      v
     * @param view     v
     */
    public static void setImageCircle(Context mContext, int url, ImageView view) {
        Glide.with(mContext).load(url).apply(RequestOptions.
                bitmapTransform(new CircleCrop()))
                .apply(new RequestOptions().placeholder(R.mipmap.jft_img_occupancymap))
                .apply(new RequestOptions().error(R.mipmap.jft_img_occupancymap))
                .into(view);
    }

    /**
     * 把网络资源图片转化成bitmap
     *
     * @param url 网络资源图片
     * @return Bitmap
     */
    public static Bitmap GetLocalOrNetBitmap(String url) {
        Bitmap bitmap = null;
        InputStream in = null;
        BufferedOutputStream out = null;
        try {
            in = new BufferedInputStream(new URL(url).openStream(), 1024);
            final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
            out = new BufferedOutputStream(dataStream, 1024);
            copy(in, out);
            out.flush();
            byte[] data = dataStream.toByteArray();
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            data = null;
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void copy(InputStream in, OutputStream out)
            throws IOException {
        byte[] b = new byte[1024];
        int read;
        while ((read = in.read(b)) != -1) {
            out.write(b, 0, read);
        }
    }

    /**
     * 将图片url 转换为BitMap
     *
     * @param url url
     * @return bitmap
     */
    public static Bitmap getBitmap(String url) {
        Bitmap bm = null;
        try {
            URL iconUrl = new URL(url);
            URLConnection conn = iconUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;

            int length = http.getContentLength();

            conn.connect();
            // 获得图像的字符流
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, length);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();// 关闭流
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bm;
    }

    /**
     * Bitmap转换成byte[]并且进行压缩,压缩到不大于maxkb
     *
     * @param bitmap b
     * @param maxkb  m
     * @return b
     */
    public static byte[] bitmap2Bytes(Bitmap bitmap, int maxkb) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
        int options = 100;
        while (output.toByteArray().length > maxkb && options != 10) {
            output.reset(); //清空output
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, output);//这里压缩options%，把压缩后的数据存放到output中
            options -= 10;
        }
        return output.toByteArray();
    }
}
