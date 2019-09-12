package com.tsyc.tianshengyoucai.utils;

import android.content.Context;

import com.lzj.pass.dialog.PayPassDialog;
import com.lzj.pass.dialog.PayPassView;
import com.tsyc.tianshengyoucai.listener.PayBorderListener;
import com.tsyc.tianshengyoucai.ui.activity.mine.SetPayPwdActivity;

/**
 * author：van
 * CreateTime：2019/8/16
 * File description： 支付宝 键盘
 */
public class AliPayKeyBorderUtils {

    private PayBorderListener payListener;
    private static AliPayKeyBorderUtils borderUtils;

    public void setPayListener(PayBorderListener payListener) {
        this.payListener = payListener;
    }

    public static AliPayKeyBorderUtils getInstance() {
        if (borderUtils == null)
            borderUtils = new AliPayKeyBorderUtils();
        return borderUtils;
    }

    private PayPassDialog dialog;

    public void showPayDialog(Context context) {
        dialog = new PayPassDialog(context);
        dialog.getPayViewPass()
                .setPayClickListener(new PayPassView.OnPayClickListener() {
                    @Override
                    public void onPassFinish(String passContent) { //6位输入完成回调
                        dialog.dismiss();
                        if (payListener != null) {
                            payListener.onFinished(passContent);
                        }
                    }

                    @Override
                    public void onPayClose() {
                        dialog.dismiss();//关闭回调
                        if (payListener != null) {
                            payListener.onClosed();
                        }
                    }

                    @Override
                    public void onPayForget() {
                        dialog.dismiss();
                        if (payListener != null) {
                            payListener.onPwdForget();
                        }
                    }
                });
    }


}
