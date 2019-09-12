package com.tsyc.tianshengyoucai.view.pop;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;

import com.tsyc.tianshengyoucai.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * author：van
 * CreateTime：2019/7/30
 * File description：商家优惠券列表  pop
 */
public class ShopCouponListPop extends BasePopupWindow {

    public ShopCouponListPop(Context context) {
        super(context);
        setPopupGravity(Gravity.BOTTOM);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.shop_coupon_list_pop);
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
