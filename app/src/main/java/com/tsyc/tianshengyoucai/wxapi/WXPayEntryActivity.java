package com.tsyc.tianshengyoucai.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.listener.event.UnifiedNotifyEvent;
import com.tsyc.tianshengyoucai.utils.PayUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;

/**
 * 创 建 者：van
 * 创建日期：2019/02/20.
 * 描    述： 微信支付回调
 * 修改描述：
 * 修改日期：
 */

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.pay_result);

        api = WXAPIFactory.createWXAPI(this, Constant.WX_APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {

        String transaction = resp.transaction;

        XLog.e("支付成功 :"+PayUtils.getTag());
        XLog.e("微信支付 " + resp.errCode + " ////  " + transaction);
        switch (resp.errCode) {
            case 0:
                XToast.normal("支付成功");
                //回调通知
                switch (PayUtils.getTag()) {
                    case "online": // 线上订单
                        EventBus.getDefault().post(new UnifiedNotifyEvent(Constant.WX_PAY_SUCCESS_EVENT));
                        EventBus.getDefault().post(new UnifiedNotifyEvent(Constant.WX_PAY_SUCCESS_EVENT_ONLINE));
                        break;
                    case "underline": //线下订单
                        EventBus.getDefault().post(new UnifiedNotifyEvent(Constant.WX_PAY_SUCCESS_EVENT));
                        EventBus.getDefault().post(new UnifiedNotifyEvent(Constant.WX_PAY_SUCCESS_EVENT_ONLINE));
                        break;
                    case "release_red_bag": //发布红包
                        EventBus.getDefault().post(new UnifiedNotifyEvent(Constant.WX_PAY_SUCCESS_EVENT));
                        break;
                    case "order_manage": //订单管理
                        EventBus.getDefault().post(new UnifiedNotifyEvent(Constant.WX_PAY_SUCCESS_EVENT));
                        break;
                    case "partner": //订单管理
                        EventBus.getDefault().post(new UnifiedNotifyEvent(Constant.WX_PAY_SUCCESS_EVENT));
                        break;
                    case "recharge": //余额充值
                        EventBus.getDefault().post(new UnifiedNotifyEvent(Constant.WX_PAY_SUCCESS_EVENT));
                        break;
                        case "scan_order": //扫码付款
                        EventBus.getDefault().post(new UnifiedNotifyEvent(Constant.WX_PAY_SUCCESS_EVENT));
                        break;
                }
                break;
            case -2:
                XToast.normal("支付取消");
                break;
            default:
                break;
        }
        WXPayEntryActivity.this.finish();
    }
}