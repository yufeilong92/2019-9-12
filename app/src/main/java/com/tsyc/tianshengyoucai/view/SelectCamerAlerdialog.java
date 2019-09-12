package com.tsyc.tianshengyoucai.view;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.MainActivity;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.ui.activity.home.ReportStoreActivity;
import com.tsyc.tianshengyoucai.utils.matisse_picker.GifSizeFilter;
import com.tsyc.tianshengyoucai.utils.matisse_picker.Glide4Engine;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import static com.youth.xframe.XFrame.getResources;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/8 16:20
 * @Purpose :选择照相机
 */
public class SelectCamerAlerdialog extends AlertDialog {
    private DisplayMetrics metrics;
    private TextView mTvDialogCamner;
    private TextView mTvDialogXiangce;
    private TextView mTvDialogCanner;

    protected SelectCamerAlerdialog(@NonNull Context context) {
        super(context);
        initData(context);
    }

    public SelectCamerAlerdialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        initData(context);
    }

    public void initData(Context context) {
        metrics = context.getResources().getDisplayMetrics();
        getWindow().setWindowAnimations(R.style.popup_animation);
    }

    private void setSizeMode() {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = metrics.widthPixels;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
        getWindow().setGravity(Gravity.BOTTOM);
//        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSizeMode();
        setContentView(R.layout.dialog_camer);
        initView();
        initEvent();

    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void openCamer();

        void openXinCe();
    }

    private void initEvent() {
        mTvDialogCanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mTvDialogCamner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    dismiss();
                    listener.openCamer();
                }
            }
        });
        mTvDialogXiangce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    dismiss();
                    listener.openXinCe();
                }
            }
        });
    }

    private void initView() {
        mTvDialogCamner = findViewById(R.id.tv_dialog_camner);
        mTvDialogCanner = findViewById(R.id.tv_dialog_canner);
        mTvDialogXiangce = findViewById(R.id.tv_dialog_xiangce);
    }
}
