package com.tsyc.tianshengyoucai.model.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.ApplyCashBean;
import com.tsyc.tianshengyoucai.model.bean.BillDetailBean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/8
 * File description： 收银台 --- 提现 adapter
 */
public class BillDetailAdapter extends BaseQuickAdapter<BillDetailBean.ResultBean.ListBean.DataBean, BaseViewHolder> {

    public BillDetailAdapter(@Nullable List<BillDetailBean.ResultBean.ListBean.DataBean> data) {
        super(R.layout.layout_record_item, data);
    }

    public BillDetailAdapter() {
        super(R.layout.layout_record_item);
    }

    @Override
    protected void convert(BaseViewHolder holder, BillDetailBean.ResultBean.ListBean.DataBean dataBean) {

        TextView tvCashMoney = holder.getView(R.id.tv_cash_money);
        TextView tv_nicker = holder.getView(R.id.tv_nicker);
        TextView tvCashStatus = holder.getView(R.id.tv_cash_status);
        TextView tvOrderSn = holder.getView(R.id.tv_phone);
        TextView tvCashTime = holder.getView(R.id.tv_register_time);
        TextView tvCashType = holder.getView(R.id.tv_level);
        TextView tvPayMoney = holder.getView(R.id.tv_pay_money);
        tvPayMoney.setVisibility(View.VISIBLE);
        String applied_text = dataBean.getApplied_text();
        String buyer_name = dataBean.getBuyer_name();
        String ordersn = dataBean.getOrdersn();
        String apply_time = dataBean.getApply_time();
        String pay_type_text = dataBean.getPay_type_text();
        String money = dataBean.getMoney();

        tv_nicker.setText("付款人: ");
        tvCashMoney.setText(buyer_name);
        tvCashStatus.setText(applied_text);
        tvOrderSn.setText("订单编号: " + ordersn);
        tvCashTime.setText("订单时间: " + apply_time);
        tvCashType.setText("支付方式: " + pay_type_text);
        tvPayMoney.setText("付款金额: " + money+" 元");
        int pay_type = dataBean.getPay_type();
        if (pay_type ==1){
            tvCashStatus.setTextColor(mContext.getResources().getColor(R.color.color_63D376));
        }else {
            tvCashStatus.setTextColor(mContext.getResources().getColor(R.color.tab_color));
        }

    }
}
