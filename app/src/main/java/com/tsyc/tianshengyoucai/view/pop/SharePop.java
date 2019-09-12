package com.tsyc.tianshengyoucai.view.pop;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import com.tsyc.tianshengyoucai.R;
import razerdp.basepopup.BasePopupWindow;

/**
 * 创 建 者：van
 * 创建日期：2019/5/11.
 * 描    述： 分享 pop
 * 修改描述：
 * 修改日期：
 */
public class SharePop extends BasePopupWindow {


    public SharePop(Context context) {
        super(context);
        setPopupGravity(Gravity.BOTTOM);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.share_pop);
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