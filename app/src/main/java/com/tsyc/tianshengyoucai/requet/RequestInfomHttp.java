package com.tsyc.tianshengyoucai.requet;

import android.app.Activity;

import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;

import java.util.HashMap;

public class RequestInfomHttp {
    private volatile static RequestInfomHttp singleton;
    private Activity mContext;

    private RequestInfomHttp(Activity mContext) {
        this.mContext = mContext;
    }

    public static RequestInfomHttp getSingleton(Activity mContext) {
        if (singleton == null) {
            synchronized (RequestInfomHttp.class) {
                if (singleton == null) {
                    singleton = new RequestInfomHttp(mContext);
                }
            }
        }
        return singleton;
    }

    /**
     * 请求用户信息
     *
     * @param callback
     */
    public void requestUserInfom(String key, RequestResultCallback callback) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("loginkey", key);
        BaseRequestUtils.postRequestWithHeader(mContext, UrlManager.getUrl(mContext, R.string.http_userinfom), hashMap, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {
                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error(response.message());
                    }
                }
        );
    }

}
