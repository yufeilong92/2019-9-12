package com.tsyc.tianshengyoucai.requet;

import android.app.Activity;
import android.content.Intent;

import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.activity.login.LoginActivity;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.SaveUserInfomUtilJave;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.vo.UserInfomVo;

import java.io.File;
import java.util.HashMap;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/9 17:35
 * @Purpose :
 */
public class UpdataImagHttp {

    private volatile static UpdataImagHttp singleton;
    private static Activity mContext;

    private UpdataImagHttp(Activity mContext) {
        this.mContext = mContext;
    }

    public static UpdataImagHttp getSingleton(Activity mContext) {
        if (singleton == null) {
            synchronized (UpdataImagHttp.class) {
                if (singleton == null) {
                    singleton = new UpdataImagHttp(mContext);
                }
            }
        }
        return singleton;
    }

    /**
     * 请求我的收货地址
     *
     * @param callback
     */

    public void updataImage(String type, String url, RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }

        File file = new File(url);
        HashMap<String, String> map = new HashMap<>();
        map.put("type", type);
        BaseRequestUtils.postRequestWithPhoto(mContext, UrlManager.getUrl(mContext, R.string.http_upload_image),
                file, map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {
                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error(response.message());
                    }
                });
    }
}
