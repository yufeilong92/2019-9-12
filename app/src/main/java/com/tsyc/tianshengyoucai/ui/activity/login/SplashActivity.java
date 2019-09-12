package com.tsyc.tianshengyoucai.ui.activity.login;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.ImageView;

import com.tsyc.tianshengyoucai.MainActivity;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.manager.SPManager;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.SaveUserInfomUtilJave;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.vo.UserInfomVo;
import com.youth.xframe.widget.XToast;

/**
 * author：cxd
 * CreateTime：2019/7/11
 * File description：
 */
public class SplashActivity extends BaseActivity {

    private ImageView iv_image;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        iv_image = (ImageView) findViewById(R.id.iv_image);

        String token = SPManager.getPublicSP().getString(SPManager.KEY, "");
        UserInfomVo userInfom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        if (!TextUtils.isEmpty(token) &&userInfom!=null&&userInfom.getResult()!=null&& !StringUtil.isEmpty(userInfom.getResult().getKey())) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            intent.putExtra(MainActivity.token, token);
            startActivity(intent);
            SplashActivity.this.finish();
        } else {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            SplashActivity.this.finish();
        }

    }

    protected long clickTime;

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - clickTime) >= 2000) {
            clickTime = System.currentTimeMillis();
            XToast.normal("再按一次退出程序");
        } else {
            System.exit(0);
        }
    }
}
