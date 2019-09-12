package com.tsyc.tianshengyoucai.model.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.model.bean.NormalResultBean;
import com.tsyc.tianshengyoucai.model.bean.RelCouponBean;
import com.tsyc.tianshengyoucai.model.bean.ShopDetailHeaderBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.activity.home.ShopDetailActivity;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.NumberUtils;
import com.youth.xframe.widget.XToast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author：van
 * CreateTime：2019/7/26
 * File description：商家首页领取优惠券
 */
public class StoreCouponAdapter extends BaseQuickAdapter<ShopDetailHeaderBean.ResultBean.CouponListBean, BaseViewHolder> {

    public StoreCouponAdapter(@Nullable List<ShopDetailHeaderBean.ResultBean.CouponListBean> data) {
        super(R.layout.layout_store_coupon_item, data);
    }

    public StoreCouponAdapter() {
        super(R.layout.layout_store_coupon_item);
    }

    private int pos = -1;

    public void notifyItem(int position) {
        this.pos = position;
    }

    @Override
    protected void convert(BaseViewHolder holder, ShopDetailHeaderBean.ResultBean.CouponListBean resultBean) {

        TextView tvMoney = holder.getView(R.id.tv_money);
        TextView tvCouponTip = holder.getView(R.id.tv_coupon_tip);
        TextView tvCoupon = holder.getView(R.id.tv_coupon);

        String voucher_limit = resultBean.getVoucher_limit();
        String voucher_price = resultBean.getVoucher_price();
        int voucher_id = resultBean.getVoucher_id();
        String formatNum = NumberUtils.formatNum(voucher_limit, "0");
        tvMoney.setText("￥" + voucher_price);
        tvCouponTip.setText("满" + formatNum + "元可用");
        if (holder.getLayoutPosition() == pos)
            resultBean.setIs_get(1);

        int is_get = resultBean.getIs_get();
        if (is_get == 0) {
            tvCoupon.setText("领取");
        } else {
            tvCoupon.setText("已领取");
        }

        holder.addOnClickListener(R.id.tv_coupon);

    }

}
