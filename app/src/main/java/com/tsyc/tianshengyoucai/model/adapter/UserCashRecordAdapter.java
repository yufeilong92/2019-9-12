package com.tsyc.tianshengyoucai.model.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.CashRecordBean;
import com.tsyc.tianshengyoucai.model.bean.UserBalanceRecordBean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/8
 * File description： 提现记录 bean
 */
public class UserCashRecordAdapter extends BaseQuickAdapter<UserBalanceRecordBean.ResultBean, BaseViewHolder> {

    public UserCashRecordAdapter(@Nullable List<UserBalanceRecordBean.ResultBean> data) {
        super(R.layout.layout_record_item, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, UserBalanceRecordBean.ResultBean dataBean) {

        TextView tvCashMoney = holder.getView(R.id.tv_cash_money);
        TextView tvCashStatus = holder.getView(R.id.tv_cash_status);
        TextView tvOrderSn = holder.getView(R.id.tv_phone);
        TextView tvCashTime = holder.getView(R.id.tv_register_time);
        TextView tvCashType = holder.getView(R.id.tv_level);


        long settle_sn = dataBean.getPdc_sn();
        String create_time = dataBean.getPdc_add_time();
        String money = dataBean.getPdc_amount();
        int status = dataBean.getPdc_payment_state();
        String pay_type_text = dataBean.getPdc_account_type_name();

        tvCashMoney.setText(money+"元");
        tvOrderSn.setText("订单编号：" + settle_sn);
        tvCashTime.setText("提现时间：" + create_time);
        tvCashType.setText("到账账户：" + pay_type_text);

        //0 待审核 1 待打款 2 已完成 3 拒绝
        if (status == 0) {
            tvCashStatus.setText("待审核");
            tvCashStatus.setTextColor(mContext.getResources().getColor(R.color.color_5769E7));
        } else if (status == 1) {
            tvCashStatus.setText("待打款");
            tvCashStatus.setTextColor(mContext.getResources().getColor(R.color.color_E83C4C));
        } else if (status == 2) {
            tvCashStatus.setText("已完成");
            tvCashStatus.setTextColor(mContext.getResources().getColor(R.color.color_63D376));
        } else {
            tvCashStatus.setText("已拒绝");
            tvCashStatus.setTextColor(mContext.getResources().getColor(R.color.color_E83C4C));
        }
    }
}
