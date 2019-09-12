package com.tsyc.tianshengyoucai.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/28 10:38
 * @Purpose :选择卡
 */
public class SelectKeyDialog extends AlertDialog {
    private TextView mTvSelectCanner;
    private TextView mTvSelectSure;
    private RecyclerView mRlvSelectContent;

    protected SelectKeyDialog(@NonNull Context context) {
        super(context);
    }

    protected SelectKeyDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected SelectKeyDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void initData(Context context) {
        getWindow().setWindowAnimations(R.style.popup_animation);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSizeMode();
        setContentView(R.layout.item_select_key);
        mTvSelectCanner.findViewById(R.id.tv_select_canner);
        mTvSelectSure.findViewById(R.id.tv_select_sure);
        mRlvSelectContent.findViewById(R.id.rlv_select_content);
    }

    private void setSizeMode() {
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }
}
