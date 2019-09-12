package com.tsyc.tianshengyoucai.model.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.ApplyCashBean;
import com.tsyc.tianshengyoucai.model.bean.CashRecordBean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/8
 * File description： 收银台 --- 提现 adapter
 */
public class ApplyCashAdapter extends BaseQuickAdapter<ApplyCashBean.ResultBean.DataBean, BaseViewHolder> {

    public ApplyCashAdapter(@Nullable List<ApplyCashBean.ResultBean.DataBean> data) {
        super(R.layout.layout_record_item, data);
    }
    public ApplyCashAdapter() {
        super(R.layout.layout_record_item);
    }

    @Override
    protected void convert(BaseViewHolder holder, ApplyCashBean.ResultBean.DataBean dataBean) {

        TextView tvCashMoney = holder.getView(R.id.tv_cash_money);
        TextView tvCashStatus = holder.getView(R.id.tv_cash_status);
        TextView tvOrderSn = holder.getView(R.id.tv_phone);
        TextView tvCashTime = holder.getView(R.id.tv_register_time);
        TextView tvCashType = holder.getView(R.id.tv_level);

        String buyer_name = dataBean.getBuyer_name();
        int order_state = dataBean.getOrder_state();

        String order_sn = dataBean.getOrder_sn();




    /*    String settle_sn = dataBean.getSettle_sn();
        String create_time = dataBean.getCreate_time();
        String money = dataBean.getMoney();
        int status = dataBean.getStatus();
        String pay_type_text = dataBean.getPay_type_text();

        tvCashMoney.setText(money);
        tvCashMoney.setText(money);
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
        }*/
    }
}