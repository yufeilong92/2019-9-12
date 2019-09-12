package com.tsyc.tianshengyoucai.wxapi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXFileObject;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.utils.SimpleUtils;
import com.tsyc.tianshengyoucai.utils.StringUtil;

/**
 * 创 建 者：van
 * 创建日期：2019/2/27.
 * 描    述： 微信分享简单封装
 * 修改描述：
 * 修改日期：
 */
public class WxShareUtils {

    private static final int THUMB_SIZE = 150;

    private static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    /**
     * 分享文本
     *
     * @param api        IWXAPI
     * @param text       description
     * @param mediaTag   类型
     * @param shareTag   分享标识
     * @param whichShare 分享到朋友圈（SendMessageToWX.Req.WXSceneTimeline）
     *                   还是好友（SendMessageToWX.Req.WXSceneSession）
     *                   分享的类型   网页（webpage）  图片（img）  文本（text）音乐（music）
     */
    public static void shareTextToWx(IWXAPI api, String text, String mediaTag, String shareTag, int whichShare) {
        WXTextObject textObj = new WXTextObject();
        textObj.text = text;

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;
        msg.description = text;
        msg.mediaTagName = mediaTag;

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        //req.transaction = buildTransaction("text");
        req.transaction = shareTag;
        req.message = msg;
        req.scene = whichShare;

        api.sendReq(req);
    }

    /**
     * 分享网页
     *
     * @param mContext   context
     * @param api        api
     * @param url        url
     * @param title      title
     * @param desc       描 述
     * @param resId      图片id
     * @param whichShare 分享到好友 0  还是朋友圈 1
     * @param shareType  用于区分是个分享的  分享的类型   网页（webpage）  图片（img）  文本（text）音乐（music）
     */
    public static void shareUrlToFriends(Context mContext, IWXAPI api, String url, String title, String desc,
                                         int resId, int whichShare, String shareType) {
        WXWebpageObject webPage = new WXWebpageObject();
        webPage.webpageUrl = url;
        WXMediaMessage msg = new WXMediaMessage(webPage);

        msg.title = title;
        msg.description = desc;
        Bitmap bmp = BitmapFactory.decodeResource(mContext.getResources(), resId);
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
        bmp.recycle();
        msg.thumbData = StringUtil.bmpToByteArray(thumbBmp, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction(shareType);
        req.message = msg;
        req.scene = whichShare;
        api.sendReq(req);
    }

    /**
     * @param mContext   context
     * @param api        api
     * @param url        share url
     * @param shareTitle share title
     * @param shareDesc  share desc
     * @param shareTag   transaction
     * @param shareType  app or com
     */
    public static void shareWebToWx(Context mContext, IWXAPI api, String url, String shareTitle,
                                    String shareDesc, String shareTag, int shareType) {

        WXWebpageObject webPage = new WXWebpageObject();

        webPage.webpageUrl = url;

        WXMediaMessage msg = new WXMediaMessage(webPage);

        msg.title = shareTitle;
        msg.description = shareDesc;
        Bitmap bmp = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.logo);
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
        bmp.recycle();
        msg.thumbData = StringUtil.bmpToByteArray(thumbBmp, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        //req.transaction = buildTransaction("webpage");
        req.transaction = shareTag;
        req.message = msg;
        req.scene = shareType;
        api.sendReq(req);
    }

    /**
     * 微信分享  图片
     *
     * @param bmp      图片
     * @param shareTag 唯一标识，分享回调
     * @param api      api
     */
    public static void shareImgToWx(Bitmap bmp, String shareTag, IWXAPI api, int shareType) {
        WXImageObject imgObj = new WXImageObject(bmp);

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;

        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
        bmp.recycle();
        msg.thumbData = StringUtil.bmpToByteArray(thumbBmp, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = shareTag;
        req.message = msg;
        req.scene = shareType;
        api.sendReq(req);
    }


    /**
     * 分分享文件
     *
     * @param mContext   context
     * @param api        api
     * @param filePath   filePath
     * @param shareTitle share title
     * @param shareDesc  share desc
     * @param shareType  app or com
     * @param shareTag   transaction
     */
    public static void shareFileToWx(Context mContext, IWXAPI api, String filePath, String shareTitle,
                                      String shareDesc,int shareType,String shareTag) {

        WXFileObject fileObj = new WXFileObject(filePath);

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = fileObj;
        msg.title = shareTitle;
        msg.description = shareDesc;

        Bitmap bmp = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.logo);
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
        bmp.recycle();
        msg.thumbData = StringUtil.bmpToByteArray(thumbBmp, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = shareTag;
        req.message = msg;
        req.scene = shareType;
        api.sendReq(req);
    }

}
