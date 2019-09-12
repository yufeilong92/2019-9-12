package com.tsyc.tianshengyoucai.view.pop;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;

import com.tsyc.tianshengyoucai.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * author：van
 * CreateTime：2019/8/6
 * File description：口令 弹窗
 */
public class TaoInvitePop extends BasePopupWindow {

    public TaoInvitePop(Context context) {
        super(context);
        setPopupGravity(Gravity.CENTER);
        setAllowDismissWhenTouchOutside(false);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.tao_invite_pop);
    }

    @Override
    protected Animation onCreateShowAnimation() {
        return getTranslateVerticalAnimation(1f, 0, 300);
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        return getTranslateVerticalAnimation(0, 1f, 300);
    }
}
