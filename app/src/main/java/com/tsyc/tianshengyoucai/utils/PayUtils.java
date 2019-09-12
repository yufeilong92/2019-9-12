package com.tsyc.tianshengyoucai.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tsyc.tianshengyoucai.AppContext;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.listener.event.UnifiedNotifyEvent;
import com.youth.xframe.utils.log.XLog;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * author：YL
 * CreateTime：2019/6/24
 * File description：
 * 支付的工具类
 */
public class PayUtils {
    private static Activity mActivity;

    public static String tag;

    public static String getTag() {
        return tag;
    }

    public static void setTag(String tag) {
        PayUtils.tag = tag;
    }

    /**
     * 调起微信支付
     */
    public static void weChatPay(AppContext myApplication, String content, String payTag) {
        try {
            setTag(payTag);
            JSONObject jsonObject = new JSONObject(content);
            String appId = jsonObject.getString("appid");
            String parenerId = jsonObject.getString("partnerid");
            String prepayId = jsonObject.getString("prepayid");
            String packageValue = jsonObject.getString("package");
            String nonceStr = jsonObject.getString("noncestr");
            String timeStamp = jsonObject.getString("timestamp");
            String sign = jsonObject.getString("sign");

            Log.e("info", "appId==" + appId + "\n" +
                    "parenerId==" + parenerId + "\n" +
                    "prepayId==" + prepayId + "\n" +
                    "packageValue==" + packageValue + "\n" +
                    "nonceStr==" + nonceStr + "\n" +
                    "timeStamp==" + timeStamp + "\n" +
                    "sign==" + sign
            );
            IWXAPI api = WXAPIFactory.createWXAPI(myApplication.getBaseContext(), appId, false);
            PayReq request = new PayReq();
            request.appId = appId;
            request.partnerId = parenerId;
            request.prepayId = prepayId;
            request.packageValue = packageValue;
            request.nonceStr = nonceStr;
            request.timeStamp = timeStamp;
            request.sign = sign;
            request.transaction = payTag;
            request.extData = payTag;

            XLog.e("PayTag = " + payTag);
            api.sendReq(request);
        } catch (Exception e) {
            e.printStackTrace();
            XLog.e("" + e.getMessage());
        }
    }

    /**
     * 支付宝支付
     */
    public static void aliPay(final Activity activity, final String orderInfo) {
        mActivity = activity;
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(activity);
                Map<String, String> result = alipay.payV2(orderInfo, true);

                Message msg = new Message();
                msg.what = 1;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @SuppressLint("HandlerLeak")
    private static Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            HashMap<String, String> obj = (HashMap<String, String>) msg.obj;
            String status = obj.get("resultStatus");
            String mssage = "";
            XLog.e("支付宝支付 ： " + status);
            switch (status) {
                case "9000"://	订单支付成功
                    mActivity.finish();
                    mssage = "支付成功";
                    EventBus.getDefault().post(new UnifiedNotifyEvent(Constant.ALI_PAY_SUCCESS_EVENT));
                    break;
                case "8000"://正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
                    mssage = "正在处理中";
                    break;
                case "4000"://	订单支付失败
                    mssage = "支付失败";
                    break;
                case "5000"://	重复请求
                    mssage = "重复请求";
                    break;
                case "6001"://	用户中途取消
                    mssage = "取消支付";
                    break;
                case "6002"://	网络连接出错
                    mssage = "网络连接出错";
                    break;
                case "6004"://	支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
                default:
                    mssage = "支付异常";
                    break;//其它	其它支付错误
            }
            Toast.makeText(mActivity, mssage, Toast.LENGTH_SHORT).show();
        }
    };
}
