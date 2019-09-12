package com.tsyc.tianshengyoucai.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.media.ThumbnailUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.tsyc.tianshengyoucai.R;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 创 建 者：van
 * 创建日期：2018/11/23.
 * 描    述：
 * 修改描述：
 * 修改日期：
 */
public class ImgUtils {

    /**
     * 设置图片
     *
     * @param mContext
     * @param url
     * @param view
     */
    public static void setImage(Context mContext, String url, ImageView view) {
        Glide.with(mContext)
                .load(url)
                .transition(new DrawableTransitionOptions().crossFade(200))
                .apply(new RequestOptions().placeholder(R.drawable.shape_img_loader))
                .into(view);
    }

    /**
     * 设置图片
     *
     * @param mContext
     * @param ids
     * @param view
     */
    public static void setImage(Context mContext, int ids, ImageView view) {
        Glide.with(mContext)
                .load(ids)
                .transition(new DrawableTransitionOptions().crossFade(200))
                .apply(new RequestOptions().placeholder(R.drawable.shape_img_loader))
                .into(view);
    }

    /**
     * 设置圆形图片
     *
     * @param mContext
     * @param url
     * @param view
     */
    public static void setImageCircle(Context mContext, String url, ImageView view) {
        Glide.with(mContext).load(url).apply(RequestOptions.
                bitmapTransform(new CircleCrop())).into(view);
    }

    /**
     * 获取网页中所有的图片
     *
     * @param htmlText webView 内容
     * @return
     */
    public static ArrayList<String> getImagePath(String htmlText) {
        ArrayList<String> imagePath = new ArrayList<String>();
        Pattern p = Pattern.compile("<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.gif|\\.png|\\.jpe|\\.jpeg|\\.pic)\\b)[^>]*>", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(htmlText);
        String quote = null;
        String src = null;
        while (m.find()) {
            quote = m.group(1);
            src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("\\s+")[0] : m.group(2);
            imagePath.add(src);
        }
        return imagePath;
    }

    public static void setColorMatrix(Bitmap bitmap, ImageView imageView) {
        if (bitmap == null) return;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap faceIconGreyBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(faceIconGreyBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.1f);
        ColorMatrixColorFilter colorMatrixFilter = new ColorMatrixColorFilter(colorMatrix);
        paint.setColorFilter(colorMatrixFilter);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        imageView.setImageBitmap(faceIconGreyBitmap);
//        return faceIconGreyBitmap;
    }


    /**
     * 把一个View的对象转换成bitmap
     */
    public static Bitmap getViewBitmap(View v) {
        v.clearFocus();
        v.setPressed(false);

        //能画缓存就返回false
        boolean willNotCache = v.willNotCacheDrawing();
        v.setWillNotCacheDrawing(false);
        int color = v.getDrawingCacheBackgroundColor();
        v.setDrawingCacheBackgroundColor(0);
        if (color != 0) {
            v.destroyDrawingCache();
        }
        v.buildDrawingCache();
        Bitmap cacheBitmap = v.getDrawingCache();
        if (cacheBitmap == null) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);
        // Restore the view
        v.destroyDrawingCache();
        v.setWillNotCacheDrawing(willNotCache);
        v.setDrawingCacheBackgroundColor(color);
        return bitmap;
    }

    /**
     * 将彩色图转换为黑白图
     *
     * @param
     * @return 返回转换好的位图
     */
    public static Bitmap convertToBlackWhite(Bitmap bmp) {

        int width = bmp.getWidth(); // 获取位图的宽
        int height = bmp.getHeight(); // 获取位图的高
        int[] pixels = new int[width * height]; // 通过位图的大小创建像素点数组

        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        int alpha = 0xFF << 24;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int grey = pixels[width * i + j];

                int red = ((grey & 0x00FF0000) >> 16);
                int green = ((grey & 0x0000FF00) >> 8);
                int blue = (grey & 0x000000FF);

                grey = (int) (red * 0.3 + green * 0.59 + blue * 0.11);
                grey = alpha | (grey << 16) | (grey << 8) | grey;
                pixels[width * i + j] = grey;
            }
        }
        Bitmap newBmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

        newBmp.setPixels(pixels, 0, width, 0, 0, width, height);

        Bitmap resizeBmp = ThumbnailUtils.extractThumbnail(newBmp, 380, 460);
        return resizeBmp;
    }

}
