package com.tsyc.tianshengyoucai.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;

public class ShareAlertdialog extends AlertDialog implements View.OnClickListener {

    private DisplayMetrics metrics;
    private TextView mTvShareWeixin;
    private TextView mTvShareWeixinCircle;

    public ShareAlertdialog(@NonNull Context context) {
        super(context);
        initData(context);

    }

    public ShareAlertdialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public void initData(Context context) {
        metrics = context.getResources().getDisplayMetrics();
        getWindow().setWindowAnimations(R.style.popup_animation);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSizeMode();
        setContentView(R.layout.dialog_share);
        mTvShareWeixin = findViewById(R.id.tv_share_weixin);
        mTvShareWeixinCircle = findViewById(R.id.tv_share_weixin_circle);
        mTvShareWeixin.setOnClickListener(this);
        mTvShareWeixinCircle.setOnClickListener(this);

    }


    private void setSizeMode() {
//        WindowManager.LayoutParams params = getWindow().getAttributes();
//        params.width=metrics.widthPixels;
//        params.height=WindowManager.LayoutParams.WRAP_CONTENT;
//        getWindow().setAttributes(params);
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_share_weixin:
                if (listener != null) {
                    dismiss();
                    listener.weixinClick();
                }
                break;
            case R.id.tv_share_weixin_circle:
                if (listener != null) {
                    dismiss();
                    listener.weixinCircleClick();
                }
                break;
            default:
                break;
        }

    }

    private OnItemClickListener listener;

    public void setItemListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void weixinClick();

        void weixinCircleClick();
    }
}
