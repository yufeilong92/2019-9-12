package com.tsyc.tianshengyoucai.model.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.RelRedBagBean;
import com.tsyc.tianshengyoucai.model.bean.UnderlineOrderBean;
import com.tsyc.tianshengyoucai.utils.ImageLoader;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/26
 * File description：优惠券 红包 适配器
 */
public class RedBagListAdapter extends BaseQuickAdapter<RelRedBagBean.ResultBean, BaseViewHolder> {

    public RedBagListAdapter(@Nullable List<RelRedBagBean.ResultBean> data) {
        super(R.layout.layout_coupon_bag_item, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, RelRedBagBean.ResultBean resultBean) {

        TextView tvMoney = holder.getView(R.id.tv_bag_money);
        TextView tvLastCount = holder.getView(R.id.tv_last_count);
        TextView tvLastDate = holder.getView(R.id.tv_last_date);
        ImageView ivRight = holder.getView(R.id.iv_right);

        String description = resultBean.getDescription(); // 描述
        int remainder = resultBean.getRemainder(); // 还剩多少
        int voucher_id = resultBean.getVoucher_id(); // 红包id
        float voucher_price = resultBean.getVoucher_price(); // 红包金额

        tvMoney.setText(String.valueOf(voucher_price+"元"));
        tvLastCount.setText(String.valueOf("剩余"+remainder+"张"));
        tvLastDate.setText(description);
        holder.addOnClickListener(R.id.iv_right);

    }

}
