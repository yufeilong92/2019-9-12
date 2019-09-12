package com.tsyc.tianshengyoucai.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tsyc.tianshengyoucai.manager.ResultFragmentTo;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.ui.base.LazyLoadFragment;
import com.tsyc.tianshengyoucai.utils.DialogUtils;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.T;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/23 11:21
 * @Purpose :
 */
public abstract class Base2Fragment extends LazyLoadFragment {
    protected Context mContext;
    protected ResultFragmentTo mResultTo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getFramgentViewId(), container, false);
        mContext = getActivity();
        mResultTo = new ResultFragmentTo(getActivity());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initContentView(view, savedInstanceState);

    }



    protected abstract int getFramgentViewId();

    protected abstract void initContentView(View view, Bundle savedInstanceState);

    public void onfinish(View view) {
        mResultTo.finishBase(mContext);
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
    protected void onError() {
        dismissProgress();
        toastErrorNet();
    }
    protected boolean onSuccess(String success) {
        dismissProgress();
        NormalBean vo = GsonUtils.getGson(success, NormalBean.class);
        if (vo.getCode().equals("100")) {
            T.showToast(mContext, vo.getMessage());
            return true;
        }
        return false;
    }
    public void toastErrorNet() {
        T.showToast(mContext, "网络异常，请稍后重试");
    }
}
