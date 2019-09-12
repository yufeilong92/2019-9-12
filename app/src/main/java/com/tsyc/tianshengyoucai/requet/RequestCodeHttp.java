package com.tsyc.tianshengyoucai.requet;

import android.app.Activity;

import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;

import java.util.HashMap;
import java.util.Map;

public class RequestCodeHttp {
    private volatile static RequestCodeHttp singleton;
    private Activity mContext;

    private RequestCodeHttp(Activity mContext) {
        this.mContext = mContext;
    }

    public static RequestCodeHttp getSingleton(Activity mContext) {
        if (singleton == null) {
            synchronized (RequestSettingHttp.class) {
                if (singleton == null) {
                    singleton = new RequestCodeHttp(mContext);
                }
            }
        }
        return singleton;
    }

    public void reqeustCode(String type, String phone, RequestResultCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("type", type);
        map.put("phone", phone);
        BaseRequestUtils.postRequest(mContext, UrlManager.send_captcha, map, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                callback.success(response.body().toString());
            }

            @Override
            public void failed(Response<String> response) {
                callback.error(response.message().toString());

            }
        });
    }
}
