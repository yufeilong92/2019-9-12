package com.youth.xframe.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import com.youth.xframe.common.XActivityStack;
import com.youth.xframe.utils.permission.XPermission;
import com.youth.xframe.widget.XLoadingDialog;



public abstract class XActivity extends AppCompatActivity implements ICallback {


    public Context mContext;
    public Activity mAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XActivityStack.getInstance().addActivity(this);
        if (getLayoutId()>0) {
            setContentView(getLayoutId());
        }
        mContext = this;
        mAct = this;
        initData(savedInstanceState);
        initView();
    }

    /**
     * Android M 全局权限申请回调
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        XPermission.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        XActivityStack.getInstance().finishActivity();
    }
}
