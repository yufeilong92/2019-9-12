package com.tsyc.tianshengyoucai.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.classic.common.MultipleStatusView;
import com.github.nukc.stateview.StateView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.tsyc.tianshengyoucai.AppContext;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.listener.PermissionListener;
import com.tsyc.tianshengyoucai.manager.ResultActivityTo;
import com.tsyc.tianshengyoucai.manager.SPManager;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.requet.RequestInfomHttp;
import com.tsyc.tianshengyoucai.utils.DialogUtils;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.SaveUserInfomUtilJave;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.utils.swipeback.app.SwipeBackActivity;
import com.tsyc.tianshengyoucai.vo.UserInfomVo;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XLoadingDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import butterknife.ButterKnife;

/**
 * @author ChayChan
 * @description: activity的基类
 * @date 2017/6/10  16:42
 */

public abstract class BaseActivity extends SwipeBackActivity implements View.OnClickListener {

    private Activity mCurrentActivity;// 对所有activity进行管理
    protected static List<Activity> mActivities = new LinkedList<Activity>();
    protected Bundle savedInstanceState;
    protected PermissionListener mPermissionListener;
    protected Gson gson = new Gson();

    protected MultipleStatusView mMultipleStatusView;

    protected Handler mHandler;

    protected TextView mTvTitle;
    protected Context mContext;
    protected HttpHeaders baseHeaders = new HttpHeaders();
    protected Bundle bundleExtra;
    protected StateView mStateView;
    protected ResultActivityTo mResultTo;
    private AlertDialog dialog;
    protected Toolbar mToolBar;
    protected UserInfomVo mUserInfo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppContext.addActivity(this);
        mResultTo = new ResultActivityTo(this);
        this.savedInstanceState = savedInstanceState;
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }

        // 侧滑关闭
        //initSlidAble();
        //初始化的时候将其添加到集合中
        synchronized (mActivities) {
            mActivities.add(this);
        }
        //子类不再需要设置布局ID，也不再需要使用ButterKnife.bind()
        setContentView(provideContentViewId());
        requestPermission();
        mContext = this;
        ButterKnife.bind(this);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        // 是否留actionBar
        mStateView = StateView.inject(this, false);

        if (mToolBar != null) {
            setSupportActionBar(mToolBar);
            ActionBar bar = getSupportActionBar();
            bar.setDisplayHomeAsUpEnabled(true);
        }

        initView();
        initData();
        initData(savedInstanceState);
        initListener();

        mMultipleStatusView = (MultipleStatusView) findViewById(R.id.multiple_status_view);

        //侧滑关闭 开关
        //getSwipeBackLayout().setEnableGesture(false);

        baseHeaders.put("Content-Type", "application/x-www-form-urlencoded");
        baseHeaders.put("loginkey", SPManager.getPublicSP().getString(SPManager.KEY, ""));
//        XLog.e("token  " + SPManager.getPublicSP().getString(SPManager.KEY, ""));
        XLog.e("token  " + SPManager.getPublicSP().getString(SPManager.KEY, ""));
        bundleExtra = getIntent().getBundleExtra("extra");
        initTitle();
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
                toastErrorNet();
                if (anInterface != null) {
                    anInterface.infomError();
                }
            }
        });
    }

    protected void initData(Bundle savedInstanceState) {
    }

    public void requestPermission() {

    }

    //得到当前界面的布局文件id(由子类实现)
    protected abstract int provideContentViewId();

    public void initView() {
    }

    public void initData() {
    }

    public void initListener() {
    }

    public void setFinish(Context mContext, ImageView iv) {
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResultTo.finishBase(mContext);
            }
        });
    }

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

    @Override
    protected void onResume() {
        super.onResume();
        mCurrentActivity = this;
        //统计
        //MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCurrentActivity = null;
        //统计
        //MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁的时候从集合中移除
        synchronized (mActivities) {
            mActivities.remove(this);
        }
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
        OkGo.getInstance().cancelTag(this);

    }

    /**
     * 退出应用的方法
     */
    public static void exitApp() {

        ListIterator<Activity> iterator = mActivities.listIterator();

        while (iterator.hasNext()) {
            Activity next = iterator.next();
            next.finish();
        }
    }

    protected Activity getCurrentActivity() {
        return mCurrentActivity;
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

    /**
     * 申请运行时权限
     */
//    public void requestRuntimePermission(String[] permissions,
//                                         PermissionListener permissionListener) {
//        mPermissionListener = permissionListener;
//        List<String> permissionList = new ArrayList<>();
//        for (String permission : permissions) {
//            if (ContextCompat.checkSelfPermission(this, permission) !=
//                    PackageManager.PERMISSION_GRANTED) {
//                permissionList.add(permission);
//            }
//        }
//
//        if (!permissionList.isEmpty()) {
//            ActivityCompat.requestPermissions(this, permissionList.toArray(new
//                    String[permissionList.size()]), 1);
//        } else {
//            permissionListener.onGranted();
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case 1:
//                if (grantResults.length > 0) {
//                    List<String> deniedPermissions = new ArrayList<>();
//                    for (int i = 0; i < grantResults.length; i++) {
//                        int grantResult = grantResults[i];
//                        String permission = permissions[i];
//                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
//                            deniedPermissions.add(permission);
//                        }
//                    }
//                    if (deniedPermissions.isEmpty()) {
//                        mPermissionListener.onGranted();
//                    } else {
//                        mPermissionListener.onDenied(deniedPermissions);
//                    }
//                }
//                break;
//        }
//    }

    // 打开界面不带动画
    public void openNoAnimActivity(Class<?> activity) {
        Intent intent = new Intent(getApplicationContext(), activity);
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    public void openActivity(Class<?> activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    public void openActivity(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(Constant.bundleExtra, bundle);
        startActivity(intent);
    }

    public void openActivity(Class<?> clazz, Bundle bundle, int code) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(Constant.bundleExtra, bundle);
        startActivityForResult(intent, code);
    }

    public void openActivity(Class<?> clazz, boolean isClear) {
        Intent intent = new Intent(this, clazz);
        //清栈
        if (isClear)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    public void openActivity(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }


    @Override
    public void onClick(View view) {

    }


    public void loading(boolean isCanceled) {
        XLoadingDialog.with(mContext)
                .setCanceled(isCanceled)
                .setOrientation(XLoadingDialog.VERTICAL)
                .setBackgroundColor(Color.parseColor("#aa000000"))
                .setMessageColor(Color.WHITE)
                .setMessage("加载中...")
                .show();
    }

    public void loading(boolean isCanceled, String content) {
        XLoadingDialog.with(mContext)
                .setCanceled(isCanceled)
                .setOrientation(XLoadingDialog.VERTICAL)
                .setBackgroundColor(Color.parseColor("#aa000000"))
                .setMessageColor(Color.WHITE)
                .setMessage(content)
                .show();
    }

    public void dismiss() {
        XLoadingDialog.with(mContext).dismiss();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

}
