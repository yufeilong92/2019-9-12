package com.tsyc.tianshengyoucai.wxapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tsyc.tianshengyoucai.constant.Constant;

public class AppRegister extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		final IWXAPI msgApi = WXAPIFactory.createWXAPI(context, Constant.WX_APP_ID);

		// 将该app注册到微信
		msgApi.registerApp(Constant.WX_APP_ID);
	}
}
