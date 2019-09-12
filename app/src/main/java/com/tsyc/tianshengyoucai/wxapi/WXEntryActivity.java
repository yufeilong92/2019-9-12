package com.tsyc.tianshengyoucai.wxapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.manager.DataMangerVo;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

/**
 * 创 建 者：van
 * 创建日期：2019/02/20.
 * 描    述： 微信分享回调
 * 修改描述：
 * 修改日期：
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    /**
     * 微信登录相关
     */
    private IWXAPI api;
    private Context mContext;
    private Gson gson;

    // 微信提现授权 state
    private final String authorWX = "bind_wx_sdk_frog_top";

    // 文章详情 微信分享
    private final String shareArticleWX = "article_detail_share";
    private final String shareInviteWX = "invite_share";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        super.onCreate(savedInstanceState);

        gson = new Gson();
        api = WXAPIFactory.createWXAPI(this, Constant.WX_APP_ID, true);
        api.registerApp(Constant.WX_APP_ID);
        mContext = this;
        try {
            boolean result = api.handleIntent(getIntent(), this);
            if (!result) {
                Log.e("注册 ", "参数不合法，未被SDK处理，退出");
                XToast.normal("微信登录失败");
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        api.handleIntent(data, this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
    }

    @Override
    public void onResp(BaseResp baseResp) {
       /*
           //调起小程序
        if (baseResp.getType() == ConstantsAPI.COMMAND_LAUNCH_WX_MINIPROGRAM) {
            WXLaunchMiniProgram.Resp l = (WXLaunchMiniProgram.Resp) baseResp;
            int errCode = l.errCode;
            int type = l.getType();
            XLog.e("xiaochengxu  "+errCode+"---"+type);
            // 对应小程序组件 <button open-type="launchApp"> 中的 app-parameter 属性
            //String extraData =launchMiniProResp.extMsg;
        } */
        String s = gson.toJson(baseResp);
        XLog.e("BaseResp " + s);

        if (baseResp.getType() == 1) { // 微信登陆
            SendAuth.Resp sendResp = (SendAuth.Resp) baseResp;
            sendBroadcastData(sendResp);

        } else if (baseResp.getType() == 2) { // 微信分享
            ShareWXEntity shareWXEntity = gson.fromJson(s, ShareWXEntity.class);
            share(baseResp, shareWXEntity);
        }
    }

    //分享
    private void share(BaseResp baseResp, ShareWXEntity shareWXEntity) {
        String result = "";
        if (shareWXEntity == null) {
            result = "分享失败";
            XToast.normal(result);
            return;
        }
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                String transaction = shareWXEntity.getTransaction();
                //分享成功 回调

                if (transaction.equals(shareArticleWX)) {
                    //文章详情分享
                    //EventBus.getDefault().post(new NormalNotifyEvent(Constant.SHARE_ARTICLE_WX));
                    XToast.normal("分享成功");
                } else if (transaction.equals(shareInviteWX)){
                    //邀请好友分享
                    //EventBus.getDefault().post(new NormalNotifyEvent(Constant.SHARE_INVITE_WX));
                    XToast.normal("分享成功");
                }

                result = "分享成功";
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = "分享取消";
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = "分享被拒绝";
                break;
            case BaseResp.ErrCode.ERR_BAN:
                result = "分享错误";
                break;
            default:
                result = "分享返回";
                break;
        }

        XToast.normal(result);
        finish();
    }

    private void login(BaseResp baseResp, WXBaseRespEntity entity) {

        String result = "";
        if (entity == null) {
            result = "微信拉起失败";
            XToast.normal(result);
            return;
        }
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                if (entity.getState().equals(authorWX))
                    result = "授权取消";
                else
                    result = "登录取消";
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = "登录被拒绝";
                break;
            case BaseResp.ErrCode.ERR_BAN:
                result = "签名错误";
                break;
            default:
                result = "登录返回";
                break;
        }
        if (!TextUtils.isEmpty(result)) {
            XToast.normal(result);
        }
        finish();
    }
    private void sendBroadcastData(SendAuth.Resp sendResp) {
        Intent intent = new Intent();
        intent.setAction(DataMangerVo.WEI_LOGIN_ACTION);
        intent.putExtra(DataMangerVo.WEICODE,sendResp.code);
        intent.putExtra(DataMangerVo.WEISTATE,sendResp.state);
        this.sendBroadcast(intent);
        finish();
    }
}
