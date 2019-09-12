package com.tsyc.tianshengyoucai;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.fragment.HomeTypeFragment;
import com.tsyc.tianshengyoucai.listener.event.UnifiedNotifyEvent;
import com.tsyc.tianshengyoucai.manager.DataMangerVo;
import com.tsyc.tianshengyoucai.manager.SPManager;
import com.tsyc.tianshengyoucai.model.adapter.MainTabAdapter;
import com.tsyc.tianshengyoucai.model.bean.GoodsDetailBean;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.model.bean.ScanJsonBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.requet.OkGoUpdateHttpUtil;
import com.tsyc.tianshengyoucai.requet.RequestInfomHttp;
import com.tsyc.tianshengyoucai.ui.activity.home.GoodsDetailActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.ScanOrderActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.ScanPayActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseFragment;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.ui.fragment.HomeFragment;
import com.tsyc.tianshengyoucai.ui.fragment.HomeNewFragment;
import com.tsyc.tianshengyoucai.ui.fragment.MineFragment;
import com.tsyc.tianshengyoucai.ui.fragment.MsgFragment;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.ImageLoader;
import com.tsyc.tianshengyoucai.utils.SaveUserInfomUtilJave;
import com.tsyc.tianshengyoucai.utils.SystemUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.utils.TextUtil;
import com.tsyc.tianshengyoucai.view.NoScrollViewPager;
import com.tsyc.tianshengyoucai.view.bottombarlayout.BottomBarLayout;
import com.tsyc.tianshengyoucai.view.pop.TaoInvitePop;
import com.tsyc.tianshengyoucai.vo.UpdataVo;
import com.tsyc.tianshengyoucai.vo.UserInfomVo;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.UpdateCallback;
import com.vector.update_app.listener.ExceptionHandler;
import com.youth.xframe.utils.XPreferencesUtils;
import com.youth.xframe.utils.XRegexUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;


/**
 * 主界面
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.vp_content)
    NoScrollViewPager vpContent;
    @BindView(R.id.bottom_bar)
    BottomBarLayout bottomBar;
    private List<BaseFragment> mFragments;
    public static final String token = "token";
    private String mToken;
    private String replaceStart;
    private TaoInvitePop invitePop;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        registerEventBus(this);
        if (getIntent() != null) {
            mToken = getIntent().getStringExtra(MainActivity.token);
        }
        requestUserInfom();
        initRequestUpdata();
        mFragments = new ArrayList<>();
        mFragments.add(HomeNewFragment.newInstance("", ""));
        mFragments.add(HomeTypeFragment.newInstance("", ""));
        mFragments.add(new MsgFragment());
        mFragments.add(new MineFragment());

        //当前界面不可侧滑关闭
        getSwipeBackLayout().setEnableGesture(false);
        savekey();

    }

    private void savekey() {
        UserInfomVo vo = new UserInfomVo();
        UserInfomVo.ResultBean resultBean = new UserInfomVo.ResultBean();
        resultBean.setKey(mToken);
        vo.setResult(resultBean);
        SaveUserInfomUtilJave.getInstance().putUserInfom(vo);
    }

    private void initRequestUpdata() {
        String path = mContext.getExternalCacheDir().getAbsolutePath() + File.separator + "/";
        String url = UrlManager.getUrl(mContext, R.string.http_update);
        new UpdateAppManager.Builder()
                .setActivity(this)
                .setHttpManager(new OkGoUpdateHttpUtil())
                .setUpdateUrl(url)
                .setPost(false)
                .setTargetPath(path)
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        e.printStackTrace();
                    }
                })
                .build()
                .checkNewApp(new UpdateCallback() {
                    @Override
                    protected UpdateAppBean parseJson(String json) {
                        UpdataVo data = GsonUtils.getGson(json, UpdataVo.class);
                        UpdataVo.ResultBean vo = data.getResult();
                        UpdateAppBean updateAppBean = new UpdateAppBean();
                        String updata = "Yes";
                        boolean isConstraint = false;
                        int code = SystemUtil.getVersionCode(mContext);
                        if (code == vo.getAndroid_version_code()) {
                            updata = "No";
                        }
                        if (vo.getForce_update() == 1) {
                            isConstraint = true;
                        }
                        updateAppBean.setApkFileUrl(vo.getApp_url())
                                //（必须）是否更新Yes,No
                                .setUpdate(updata)
                                .setNewVersion(vo.getAndroid_version())
                                //（必须）下载地址
                                .setApkFileUrl(vo.getDownload_url())
                                //（必须）更新内容
                                .setUpdateLog(vo.getApp_desc())
                                //大小，不设置不显示大小，可以不设置
//                                .setTargetSize("")
                                //是否强制更新，可以不设置
                                .setConstraint(isConstraint);

                        return updateAppBean;
                    }

                    @Override
                    protected void hasNewApp(UpdateAppBean updateApp, UpdateAppManager updateAppManager) {
                        super.hasNewApp(updateApp, updateAppManager);
                    }

                    @Override
                    protected void onAfter() {
                        super.onAfter();
                    }

                    @Override
                    protected void noNewApp(String error) {
                        super.noNewApp(error);
                    }

                    @Override
                    protected void onBefore() {
                        super.onBefore();
                    }
                });

    }

    private void requestUserInfom() {
        DataMangerVo.key = mToken;
        RequestInfomHttp.getSingleton(this).requestUserInfom(mToken, new RequestResultCallback() {
            @Override
            public void success(String success) {
                NormalBean datas = GsonUtils.getGson(success, NormalBean.class);
                if (datas.getCode().equals("100")) {
                    T.showToast(mContext, datas.getMessage());
                    return;
                }
                UserInfomVo data = GsonUtils.getGson(success, UserInfomVo.class);
                UserInfomVo.ResultBean result = data.getResult();
                result.setKey(mToken);
                data.setResult(result);
                SaveUserInfomUtilJave.getInstance().delectUserInfom();
                SaveUserInfomUtilJave.getInstance().putUserInfom(data);
            }

            @Override
            public void error(String error) {

            }
        });

    }

    @Override
    public void initListener() {
        MainTabAdapter mTabAdapter = new MainTabAdapter(mFragments, getSupportFragmentManager());
        vpContent.setAdapter(mTabAdapter);
        vpContent.setOffscreenPageLimit(mFragments.size());
        bottomBar.setViewPager(vpContent);
        //设置条目点击的监听
        bottomBar.setOnItemSelectedListener((bottomBarItem, previousPosition, position) -> {

            //如果点击了其他条目
            // BottomBarItem bottomItem = bottomBar.getBottomItem(0);
            // bottomItem.setIconSelectedResourceId(R.mipmap.tab_home_selected);//更换为原来的图标
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChangeFragmentListener(UnifiedNotifyEvent event) {
        int code = event.getEventCode();
        switch (code) {
            default:
                break;
        }
    }

    // 首页扫码
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                String jsonData = extras.getString(CodeUtils.RESULT_STRING);

                if (jsonData == null) {
                    XToast.normal(getString(R.string.plz_show_code));
                    return;
                }

//                if (jsonData.startsWith("\"")) {
//                    jsonData = jsonData.replace("\"", "");
//                }
//                if (jsonData.endsWith("\"")) {
//                    jsonData = jsonData.replace("", "\"");
//                }

                XLog.e("首页JSONDATA " + jsonData);
                Bundle bundle = new Bundle();
                ScanJsonBean jsonBean = FastJsonUtil.fromJson(jsonData, ScanJsonBean.class);
                if (jsonBean == null) {
                    XToast.normal(getString(R.string.plz_show_code));
                    return;
                }
                if (jsonBean.getType().equals(Constant.SCAN_UNDERLINE_ORDER)) {
                    bundle.putString("code", jsonBean.getMoney());
                    bundle.putString("type", "");
                    openActivity(ScanOrderActivity.class, bundle);
                } else if (jsonBean.getType().equals(Constant.SCAN_PAY)) {
                    bundle.putString("code", jsonData);
                    openActivity(ScanPayActivity.class, bundle);
                } else {
                    XToast.normal(getString(R.string.plz_show_code));
                }
            }
        }
    }

    private long mPreTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mPreTime) > 2000) {
                XToast.normal("再按一次退出程序");
                mPreTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void getGoodsDetail(String goodsId, String taoInviteCode) {
        Map<String, String> params = new HashMap<>();
        params.put("goods_id", goodsId);

        BaseRequestUtils.postRequestWithHeader(this, UrlManager.goodsDetail,
                params, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {
                        XLog.e("商品详情" + response.body());
                        GoodsDetailBean goodsDetailBean = FastJsonUtil.fromJson(response.body(), GoodsDetailBean.class);

                        if (goodsDetailBean == null || !goodsDetailBean.getCode().equals(Constant.REQUEST_SUCCESS) || goodsDetailBean.getResult() == null) {
                            return;
                        }

                        GoodsDetailBean.ResultBean.GoodsInfoBean goods_info = goodsDetailBean.getResult().getGoods_info();

                        List<String> goods_images = goods_info.getGoods_images();
                        String name = goods_info.getGoods_name();

                        if (invitePop == null)
                            invitePop = new TaoInvitePop(mContext);
                        invitePop.showPopupWindow();

                        ImageView ivImage = invitePop.findViewById(R.id.iv_image);
                        TextView tvName = invitePop.findViewById(R.id.tv_name);
                        ImageView ivDis = invitePop.findViewById(R.id.iv_delete);
                        TextView tvGo = invitePop.findViewById(R.id.btn_commit);

                        tvName.setText(name);
                        ImageLoader.loadCenterCrop(mContext, goods_images.get(0), ivImage, 5);

                        ivDis.setOnClickListener(v -> {
                            invitePop.dismiss();
                            clearClipboard();
                        });
                        tvGo.setOnClickListener(v -> {
                            Bundle bundle = new Bundle();
                            bundle.putString("goods_id", goodsId);
                            bundle.putString("taoInviteCode", taoInviteCode);
                            openActivity(GoodsDetailActivity.class, bundle);
                            clearClipboard();
                            invitePop.dismiss();

                        });
                    }

                    @Override
                    public void failed(Response<String> response) {

                    }
                });
    }

    private void initTaoCode() {
        try {
            runOnUiThread(() -> {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                if (clipboard == null) return;

                ClipData clipData = clipboard.getPrimaryClip();
                if (clipData != null && clipData.getItemCount() > 0) {
                    CharSequence text = clipData.getItemAt(0).getText();
                    String pasteString = text.toString();
                    XLog.e("粘贴板 getFromClipboard text" + pasteString);
                    if (!pasteString.startsWith("【"+getString(R.string.app_name))/*||!pasteString.endsWith("进入APP商城")*/ && !pasteString.contains("@")) {
                        XLog.e("没有找到@，口令不合法");
                        return;
                    }
                    int endOaIndex = pasteString.lastIndexOf("@");
                    String firstOaStr = pasteString.substring(0, endOaIndex);

                    if (!firstOaStr.contains("@")) {
                        XLog.e("没有找到开始的@");
                        return;
                    }
                    int firstOaIndex = firstOaStr.lastIndexOf("@");

                    String taoCode = pasteString.substring(firstOaIndex + 1, endOaIndex);
                    if (!taoCode.startsWith("Xjie") && !taoCode.contains("_")) {
                        XLog.e("taoCode不合法");
                        return;
                    }

                    int end_index = taoCode.lastIndexOf("_");
                    String first_str = taoCode.substring(0, end_index);
                    if (!first_str.contains("_")) {
                        XLog.e("没有找到_,taoCode不合法");
                        return;
                    }
                    String goodsId = taoCode.substring(end_index + 1, taoCode.length());
                    int first_index = first_str.lastIndexOf("_");
                    String taoInviteCode = taoCode.substring(first_index + 1, end_index);
                    XLog.e("淘口令 邀请码  " + taoInviteCode + "  ==  " + goodsId);
                    getGoodsDetail(goodsId, taoInviteCode);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //清空粘贴板
    private void clearClipboard() {
        ClipboardManager manager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        if (manager != null) {
            try {
                manager.setPrimaryClip(manager.getPrimaryClip());
                manager.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        new Handler().postDelayed(this::initTaoCode, 1111);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterEventBus(this);
    }

}
