package com.tsyc.tianshengyoucai.net;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.manager.SPManager;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.requet.view.BitmapCallBackView;
import com.tsyc.tianshengyoucai.ui.activity.login.LoginActivity;
import com.tsyc.tianshengyoucai.utils.HttpTools;
import com.tsyc.tianshengyoucai.utils.SaveUserInfomUtilJave;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.ParamGET;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * 创 建 者：van
 * 创建日期：2017/11/24.
 * 描    述： 请求简易封装
 * 修改描述：
 * 修改日期：
 */
public class BaseRequestUtils {

    public static void postRequest(Activity activity, String url, Map<String, String> params, final getRequestCallBack callBack) {

        if (!HttpTools.isNetworkAble(activity)) {
            T.showToast(activity, "网络异常，请检查网络");
            Response response = new Response();
            callBack.failed(response);
            return;
        }
        HttpHeaders baseHeaders = new HttpHeaders();
        baseHeaders.put("Content-Type", "application/x-www-form-urlencoded");

        OkGo.<String>post(url)
                .tag(activity)
                .headers(baseHeaders)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        NormalBean bean = new Gson().fromJson(response.body(), NormalBean.class);
                        if (bean.getCode().equals("300")) {
                            if (callBack != null) {
                                callBack.failed(response);
                            }
                            SPManager.clearUserInfo();
                            SaveUserInfomUtilJave.getInstance().delectUserInfom();
                            activity.startActivity(new Intent(activity, LoginActivity.class));

                            XToast.error(activity.getString(R.string.no_login));
                            return;
                        }
                       /* if (bean.getCode().equals("100")) {
                            if (callBack != null) {
                                callBack.failed(response);
                            }
                            return;
                        }*/
                        if (callBack != null) {
                            callBack.success(response);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        if (callBack != null) {
                            callBack.failed(response);
                        }
                    }
                });

    }

    public static void postRequest(Activity activity, String url, HttpHeaders headers,
                                   Map<String, String> params, final getRequestCallBack callBack) {
        if (!HttpTools.isNetworkAble(activity)) {
            T.showToast(activity, "网络异常，请检查网络");
            Response response = new Response();
            callBack.failed(response);
            return;
        }
        OkGo.<String>post(url)
                .tag(activity)
                .headers(headers)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        XLog.e("postRequest \n" + response.body());
                        NormalBean bean = new Gson().fromJson(response.body(), NormalBean.class);
                        if (bean.getCode().equals("300")) {
                            if (callBack != null) {
                                callBack.failed(response);
                            }
                            SPManager.clearUserInfo();
                            SaveUserInfomUtilJave.getInstance().delectUserInfom();
                            activity.startActivity(new Intent(activity, LoginActivity.class));
                            XToast.error(activity.getString(R.string.no_login));

                            return;
                        }
//                        if (bean.getCode().equals("100")) {
//                            if (callBack != null) {
//                                callBack.failed(response);
//                            }
//                            return;
//                        }
                        if (callBack != null) {
                            callBack.success(response);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        if (callBack != null) {
                            callBack.failed(response);
                        }
                    }
                });

    }

    //post
    public static void postRequestWithHeader(Activity activity, String url, Map<String, String> params,
                                             final getRequestCallBack callBack) {

        if (!HttpTools.isNetworkAble(activity)) {
            T.showToast(activity, "网络异常，请检查网络");
            Response response = new Response();
            callBack.failed(response);
            return;
        }

        OkGo.<String>post(url)
                .tag(activity)
                .headers("Content-Type", "application/x-www-form-urlencoded")
                .headers("loginkey", SPManager.getPublicSP().getString(SPManager.KEY, ""))
//                .headers("loginkey", "423b1d8398e381fd95fd4c5272d7b229")//测试key
//                .headers("loginkey", "00844a62fd742e7e554bab5d85945177")//测试key
//                .headers("loginkey", "cf29bf5103f1ec759f0531ca8e6b1f88")//测试key 订单管理
                .params(params)

                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
//                        XLog.e("postRequestWithHeader \n" + response.body());
                        NormalBean bean = new Gson().fromJson(response.body(), NormalBean.class);

                        if (bean.getCode().equals("300")) {
                            if (callBack != null) {
                                callBack.failed(response);
                            }
                            SPManager.clearUserInfo();
                            SaveUserInfomUtilJave.getInstance().delectUserInfom();
                            activity.startActivity(new Intent(activity, LoginActivity.class));
                            XToast.error(activity.getString(R.string.no_login));
                            return;
                        }
                  /*      if (bean.getCode().equals("100")) {
                            if (callBack != null) {
                                callBack.failed(response);
                            }
                            return;
                        }
                    */
                        if (callBack != null) {
                            callBack.success(response);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        if (callBack != null) {
                            callBack.failed(response);
                        }
                    }
                });

    }
    //post
    public static void postRequestWithHeader(Activity activity, String url, Map<String, String> params, RequestBody body,
                                             final getRequestCallBack callBack) {

        if (!HttpTools.isNetworkAble(activity)) {
            T.showToast(activity, "网络异常，请检查网络");
            Response response = new Response();
            callBack.failed(response);
            return;
        }

        OkGo.<String>post(url)
                .tag(activity)
                .headers("Content-Type", "application/x-www-form-urlencoded")
                .headers("loginkey", SPManager.getPublicSP().getString(SPManager.KEY, ""))
//                .headers("loginkey", "423b1d8398e381fd95fd4c5272d7b229")//测试key
//                .headers("loginkey", "00844a62fd742e7e554bab5d85945177")//测试key
//                .headers("loginkey", "cf29bf5103f1ec759f0531ca8e6b1f88")//测试key 订单管理
                .params(params)
                .upRequestBody(body)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
//                        XLog.e("postRequestWithHeader \n" + response.body());
                        NormalBean bean = new Gson().fromJson(response.body(), NormalBean.class);

                        if (bean.getCode().equals("300")) {
                            if (callBack != null) {
                                callBack.failed(response);
                            }
                            SPManager.clearUserInfo();
                            SaveUserInfomUtilJave.getInstance().delectUserInfom();
                            activity.startActivity(new Intent(activity, LoginActivity.class));
                            XToast.error(activity.getString(R.string.no_login));

                            return;
                        }
                  /*      if (bean.getCode().equals("100")) {
                            if (callBack != null) {
                                callBack.failed(response);
                            }
                            return;
                        }
                    */
                        if (callBack != null) {
                            callBack.success(response);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        if (callBack != null) {
                            callBack.failed(response);
                        }
                    }
                });

    }

    //get
    public static void getRequest(Activity activity, String url, HttpHeaders headers,
                                  List<ParamGET> params, final getRequestCallBack callBack) {
        if (!HttpTools.isNetworkAble(activity)) {
            T.showToast(activity, "网络异常，请检查网络");
            Response response = new Response();
            callBack.failed(response);
            return;
        }

        StringBuffer buffer = new StringBuffer();
        buffer.append(url);
        buffer.append("?");
        int length = params.size();
        for (int i = 0; i < length; i++) {
            ParamGET param = params.get(i);
            buffer.append(param.getNama());
            buffer.append("=");
            buffer.append(param.getValues());
            if (i != length - 1) {
                buffer.append("&");
            }
        }
        OkGo.<String>post(buffer.toString())
                .tag(activity)
                .headers(headers)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        XLog.e("postRequest \n" + response.body());
                        NormalBean bean = new Gson().fromJson(response.body(), NormalBean.class);
                        if (bean.getCode().equals("300")) {
                            if (callBack != null) {
                                callBack.failed(response);
                            }
                            SPManager.clearUserInfo();
                            SaveUserInfomUtilJave.getInstance().delectUserInfom();
                            activity.startActivity(new Intent(activity, LoginActivity.class));
                            XToast.error(activity.getString(R.string.no_login));
                            return;
                        }
//                        if (bean.getCode().equals("100")) {
//                            if (callBack != null) {
//                                callBack.failed(response);
//                            }
//                            return;
//                        }
                        if (callBack != null) {
                            callBack.success(response);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        if (callBack != null) {
                            callBack.failed(response);
                        }
                    }
                });

    }

    //get
    public static void getRequestWithHeader(Activity activity, String url, List<ParamGET> params,
                                            final getRequestCallBack callBack) {

        if (!HttpTools.isNetworkAble(activity)) {
            T.showToast(activity, "网络异常，请检查网络");
            Response response = new Response();
            callBack.failed(response);
            return;
        }

        StringBuffer buffer = new StringBuffer();
        buffer.append(url);
        buffer.append("?");
        int length = params.size();
        for (int i = 0; i < length; i++) {
            ParamGET param = params.get(i);
            buffer.append(param.getNama());
            buffer.append("=");
            buffer.append(param.getValues());
            if (i != length - 1) {
                buffer.append("&");
            }
        }


        OkGo.<String>post(buffer.toString())
                .tag(activity)
                .headers("Content-Type", "application/x-www-form-urlencoded")
                .headers("loginkey", SPManager.getPublicSP().getString(SPManager.KEY, ""))
//                .headers("loginkey", "423b1d8398e381fd95fd4c5272d7b229")//测试key
//                .headers("loginkey", "00844a62fd742e7e554bab5d85945177")//测试key
//                .headers("loginkey", "cf29bf5103f1ec759f0531ca8e6b1f88")//测试key 订单管理
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
//                        XLog.e("postRequestWithHeader \n" + response.body());
                        NormalBean bean = new Gson().fromJson(response.body(), NormalBean.class);

                        if (bean.getCode().equals("300")) {
                            if (callBack != null) {
                                callBack.failed(response);
                            }
                            SPManager.clearUserInfo();
                            SaveUserInfomUtilJave.getInstance().delectUserInfom();
                            activity.startActivity(new Intent(activity, LoginActivity.class));
                            XToast.error(activity.getString(R.string.no_login));

                            return;
                        }
                  /*      if (bean.getCode().equals("100")) {
                            if (callBack != null) {
                                callBack.failed(response);
                            }
                            return;
                        }
                    */
                        if (callBack != null) {
                            callBack.success(response);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        if (callBack != null) {
                            callBack.failed(response);
                        }
                    }
                });

    }

    /**
     * 申请店铺  图片
     */
    public static void postRequestMultipleData(final Activity mActivity, String url, List<String> doorList, List<String> qualifyList,
                                               final Map<String, String> params, final getRequestCallBack callBack) {
        if (!HttpTools.isNetworkAble(mActivity)) {
            T.showToast(mActivity, "网络异常，请检查网络");
            Response response = new Response();
            callBack.failed(response);
            return;
        }
        OkGo.<String>post(url).tag(mActivity)
                .isMultipart(true)
                .headers("Content-Type", "application/x-www-form-urlencoded")
                .headers("loginkey", SPManager.getPublicSP().getString(SPManager.KEY, ""))
//                .headers("loginkey", "423b1d8398e381fd95fd4c5272d7b229")//测试key
//                .headers("loginkey", "cf29bf5103f1ec759f0531ca8e6b1f88")//测试key 订单管理
                .addUrlParams("door_photo", doorList)
                .addUrlParams("business_license", qualifyList)
                .params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                XLog.e("" + response.body());
                NormalBean bean = new Gson().fromJson(response.body(), NormalBean.class);

                if (bean.getCode().equals("300")) {
                    if (callBack != null) {
                        callBack.failed(response);
                    }
                    SPManager.clearUserInfo();
                    SaveUserInfomUtilJave.getInstance().delectUserInfom();
                    mActivity.startActivity(new Intent(mActivity, LoginActivity.class));
                    XToast.error(mActivity.getString(R.string.no_login));

                    return;
                }
//                if (bean.getCode().equals("100")) {
//                    if (callBack != null) {
//                        callBack.failed(response);
//                    }
//                    return;
//                }

                if (callBack != null) {
                    callBack.success(response);
                }
            }

            @Override
            public void onError(Response<String> response) {
                if (callBack != null) {
                    callBack.failed(response);
                }
            }
        });
    }

    /**
     * 上传 图片
     *
     * @param mActivity 当前activity
     * @param url       接口
     * @param files     特定的 图片集合
     * @param callBack  s List<File> files,
     */
    public static void postRequestWithPhoto(final Activity mActivity, String url, File files,
                                            final Map<String, String> params, final getRequestCallBack callBack) {
        if (!HttpTools.isNetworkAble(mActivity)) {
            T.showToast(mActivity, "网络异常，请检查网络");
            Response response = new Response();
            callBack.failed(response);
            return;
        }
        OkGo.<String>post(url).tag(mActivity)
                .isMultipart(true)
                .headers("Content-Type", "application/x-www-form-urlencoded")
                .headers("loginkey", SPManager.getPublicSP().getString(SPManager.KEY, ""))
//                .headers("loginkey", "423b1d8398e381fd95fd4c5272d7b229")//测试key
//                .headers("loginkey", "cf29bf5103f1ec759f0531ca8e6b1f88")//测试key 订单管理
//                .addFileParams("image", files)
                .params("image", files)
                .params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                NormalBean bean = new Gson().fromJson(response.body(), NormalBean.class);

                if (bean.getCode().equals("300")) {
                    if (callBack != null) {
                        callBack.failed(response);
                    }
                    SPManager.clearUserInfo();
                    SaveUserInfomUtilJave.getInstance().delectUserInfom();
                    mActivity.startActivity(new Intent(mActivity, LoginActivity.class));
                    XToast.error(mActivity.getString(R.string.no_login));

                    return;
                }
//                if (bean.getCode().equals("100")) {
//                    if (callBack != null) {
//                        callBack.failed(response);
//                    }
//                    return;
//                }

                if (callBack != null) {
                    callBack.success(response);
                }
            }

            @Override
            public void onError(Response<String> response) {
                if (callBack != null) {
                    callBack.failed(response);
                }
            }
        });
    }


    public interface getRequestCallBack {
        void success(Response<String> response);

        void failed(Response<String> response);
    }

    protected void requstBitmap(final Context context, String path, final BitmapCallBackView backView) {
        if (!HttpTools.isNetworkAble(context)) {
            backView.onError("");
            return;
        }
        OkGo.<Bitmap>get(path)
                .tag(context)
                .execute(new BitmapCallback() {
                    @Override
                    public void onSuccess(Response<Bitmap> response) {
                        Bitmap body = response.body();
                        backView.onSuccess(body);
                    }

                    @Override
                    public void onError(Response<Bitmap> response) {
                        backView.onError(response.message());

                    }
                });
    }


}
