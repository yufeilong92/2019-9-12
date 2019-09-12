package com.tsyc.tianshengyoucai.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.classic.common.MultipleStatusView;
import com.github.nukc.stateview.StateView;
import com.google.gson.Gson;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.listener.PermissionListener;
import com.tsyc.tianshengyoucai.manager.ResultFragmentTo;
import com.tsyc.tianshengyoucai.utils.DialogUtils;
import com.tsyc.tianshengyoucai.utils.T;
import com.youth.xframe.widget.XLoadingDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @ author ChayChan
 * @ desc: Fragment的基类
 * @ date 2017/6/10  17:09
 */

public abstract class BaseFragment extends LazyLoadFragment implements View.OnClickListener {

    private View rootView;
    protected Activity mActivity;
    //Multiple
    protected MultipleStatusView mMultipleStatusView;
    //state
    protected StateView mStateView;

    private Unbinder bind;
    protected TextView mTvTitle;
    protected Context mContext;
    protected boolean isHasActionBar = false;
    protected Handler mHandler = new Handler();
    protected Gson gson = new Gson();
    protected ResultFragmentTo mResultTo;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        mContext = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(provideContentViewId(), container, false);
        }
        mResultTo = new ResultFragmentTo(getActivity());
        bind = ButterKnife.bind(this, rootView);
        //重新加载   // false 是否留actionBar 位置
        Toolbar mToolBar = rootView.findViewById(R.id.toolbar);
        mTvTitle = rootView.findViewById(R.id.tv_title);
        if (mToolBar != null) {
            ((BaseActivity) getActivity()).setSupportActionBar(mToolBar);
            ActionBar bar = ((BaseActivity) getActivity()).getSupportActionBar();
            bar.setDisplayHomeAsUpEnabled(false); // 不显示返回键
        }
        mStateView = StateView.inject(rootView, isHasActionBar);

        initView(rootView);
        initData();
        initListener();
        initData(savedInstanceState);
        return rootView;
    }

    //初始化控件
    protected <T extends View> T findView(int id) {
        if (rootView == null) {
            return null;
        }
        return (T) rootView.findViewById(id);
    }

    /**
     * StateView的根布局，默认是整个界面，如果需要变换可以重写此方法
     */
    public View getStateViewRoot() {
        return rootView;
    }


    /**
     * 初始化一些view
     *
     * @param rootView
     */
    public void initView(View rootView) {
    }

    /**
     * 初始化数据
     */
    public void initData() {

    }
 /**
     * 初始化数据
     */
    public void initData(Bundle savedInstanceState) {

    }

    /**
     * 设置listener的操作
     */
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onFragmentFirstVisible() {
        //当第一次可见的时候，加载数据
        loadData();
    }

    //得到当前界面的布局文件id(由子类实现)
    protected abstract int provideContentViewId();

    //只在当界面第一次可见时，加载数据,只加载一次
    protected abstract void loadData();

    /**
     * 跳转
     *
     * @param clazz c
     */
    public void openActivity(Class<?> clazz) {
        Intent intent = new Intent(mActivity, clazz);
        startActivity(intent);
    }

    public void openActivity(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(mActivity, clazz);
        intent.putExtra("extra", bundle);
        startActivity(intent);
    }

    public void openActivity(Class<?> clazz, Bundle bundle,int code) {
        Intent intent = new Intent(mActivity, clazz);
        intent.putExtra("extra", bundle);
        startActivityForResult(intent,code);
    }

    public void openActivity(Class<?> clazz, boolean isClear) {
        Intent intent = new Intent(mActivity, clazz);
        //清栈
        if (isClear)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        rootView = null;
        if (bind != null)
            bind.unbind();
    }

    public boolean isEventBusRegisted(Object subscribe) {
        return EventBus.getDefault().isRegistered(subscribe);
    }

    public void registerEventBus(Object subscribe) {
        if (!isEventBusRegisted(subscribe)) {
            EventBus.getDefault().register(subscribe);
        }
    }

    public void unregisterEventBus(Object subscribe) {
        if (isEventBusRegisted(subscribe)) {
            EventBus.getDefault().unregister(subscribe);
        }
    }
    protected PermissionListener mPermissionListener;
    /**
     * 申请运行时权限
     */
    public void requestRuntimePermission(String[] permissions,
                                         PermissionListener permissionListener) {
        mPermissionListener = permissionListener;
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(getActivity(), permission) !=
                    PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }

        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(getActivity(), permissionList.toArray(new
                    String[permissionList.size()]), 1);
        } else {
            permissionListener.onGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    List<String> deniedPermissions = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            deniedPermissions.add(permission);
                        }
                    }
                    if (deniedPermissions.isEmpty()) {
                        mPermissionListener.onGranted();
                    } else {
                        mPermissionListener.onDenied(deniedPermissions);
                    }
                }
                break;
        }
    }


    public void loading(boolean isCanceled) {
        XLoadingDialog.with(mActivity)
                .setCanceled(isCanceled)
                .setOrientation(XLoadingDialog.VERTICAL)
                .setBackgroundColor(Color.parseColor("#aa000000"))
                .setMessageColor(Color.WHITE)
                .setMessage("加载中...")
                .show();
    }

    public void loading(boolean isCanceled, String message) {
        XLoadingDialog.with(mActivity)
                .setCanceled(isCanceled)
                .setOrientation(XLoadingDialog.VERTICAL)
                .setBackgroundColor(Color.parseColor("#aa000000"))
                .setMessageColor(Color.WHITE)
                .setMessage(message)
                .show();
    }

    public void dismiss() {
        XLoadingDialog.with(mActivity).dismiss();
    }

    public boolean isHasActionBar() {
        return isHasActionBar;
    }

    public void setHasActionBar(boolean hasActionBar) {
        isHasActionBar = hasActionBar;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mHandler != null)
            mHandler.removeCallbacksAndMessages(null);
    }

    private AlertDialog dialog;

    public void showProgress() {
        if (dialog == null) {
            dialog = DialogUtils.getSingleton().showProgress(mContext);
        } else {
            dialog.show();
        }
    }

    public void dismissProgress() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
    public void toastErrorNet() {
        T.showToast(mContext, "网络异常，请稍后重试");
    }
}
