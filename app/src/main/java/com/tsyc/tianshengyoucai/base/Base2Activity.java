package com.tsyc.tianshengyoucai.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.manager.ActivityManager;
import com.tsyc.tianshengyoucai.manager.ResultActivityTo;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.requet.RequestInfomHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.DialogUtils;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.SaveUserInfomUtilJave;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.utils.swipeback.app.SwipeBackActivity;
import com.tsyc.tianshengyoucai.vo.UserInfomVo;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/19 10:14
 * @Purpose : 新建基类
 */
public abstract class Base2Activity extends SwipeBackActivity {
    protected ResultActivityTo mResultTo;
    private AlertDialog dialog;
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getComtentView());
        mResultTo = new ResultActivityTo(this);
        mContext = this;
        initTitle();
        initContent(savedInstanceState);


    }

    protected abstract int getComtentView();

    protected abstract void initContent(Bundle savedInstanceState);

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

    public void onfinish(View view) {
        mResultTo.finishBase(mContext);
    }

    protected void initTitle() {

        if (getIntent() != null) {
            String extra = getIntent().getStringExtra("param_title");
            if (StringUtil.isEmpty(extra)) {
                return;
            }
            TextView tvTitle = (TextView) findViewById(R.id.gm_tv_title);
            if (tvTitle == null) {
                return;
            }
            tvTitle.setText(extra);

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

    public interface InfomInterface {
        void infomSuccess(UserInfomVo vo);

        void infomError();
    }

    protected void requestInfom(InfomInterface anInterface) {
        SaveUserInfomUtilJave instance = SaveUserInfomUtilJave.getInstance();
        UserInfomVo userInfom = instance.getUserInfom();
        String key = userInfom.getResult().getKey();
        showProgress();
        RequestInfomHttp.getSingleton(this).requestUserInfom(key, new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                NormalBean vos = GsonUtils.getGson(success, NormalBean.class);
                if (vos.getCode().equals("100")) {
                    T.showToast(mContext, vos.getMessage());
                    return;
                }
                UserInfomVo vo = GsonUtils.getGson(success, UserInfomVo.class);
                UserInfomVo.ResultBean result = vo.getResult();
                result.setKey(key);
                instance.delectUserInfom();
                instance.putUserInfom(vo);
                if (anInterface != null) {
                    anInterface.infomSuccess(vo);
                }
            }

            @Override
            public void error(String error) {
                dismissProgress();
                if (anInterface != null) {
                    anInterface.infomError();
                }
            }
        });
    }


}
