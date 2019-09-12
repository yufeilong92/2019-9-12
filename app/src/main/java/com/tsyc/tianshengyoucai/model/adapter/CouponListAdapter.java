package com.tsyc.tianshengyoucai.model.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.RelCouponBean;
import com.tsyc.tianshengyoucai.model.bean.RelRedBagBean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/26
 * File description：优惠券 列表 适配器
 */
public class CouponListAdapter extends BaseQuickAdapter<RelCouponBean.ResultBean, BaseViewHolder> {

    public CouponListAdapter(@Nullable List<RelCouponBean.ResultBean> data) {
        super(R.layout.layout_coupon_bag_item, data);
    }

    public CouponListAdapter() {
        super(R.layout.layout_coupon_bag_item);
    }

    @Override
    protected void convert(BaseViewHolder holder, RelCouponBean.ResultBean resultBean) {

        TextView tvBagMoney = holder.getView(R.id.tv_bag_money);
        TextView tvUse = holder.getView(R.id.tv_use);
        TextView tvMoney = holder.getView(R.id.tv_money);
        TextView tvLastCount = holder.getView(R.id.tv_last_count);
        TextView tvLastDate = holder.getView(R.id.tv_last_date);
        ImageView ivRight = holder.getView(R.id.iv_right);
        tvBagMoney.setVisibility(View.GONE);

        String endDate = resultBean.getEnd_date();
        String startDate = resultBean.getStart_date();

        int remainder = resultBean.getRemainder(); // 还剩多少
        int voucher_id = resultBean.getVoucher_id(); // 红包id
        int voucher_price = resultBean.getVoucher_limit(); // 优惠券最低使用金额
        float voucher_money = resultBean.getVoucher_money(); // 优惠券金额

        tvMoney.setText(String.valueOf(voucher_money+"元"));
        tvLastCount.setText(String.valueOf("剩余" + remainder + "张"));
        tvLastDate.setText(String.valueOf(startDate + "-" + endDate));

        tvUse.setText(String.valueOf("满"+voucher_price+"元可用"));

        holder.addOnClickListener(R.id.iv_right);
    }

}
