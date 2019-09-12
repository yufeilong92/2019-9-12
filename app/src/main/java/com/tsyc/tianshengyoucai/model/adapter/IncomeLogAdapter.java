package com.tsyc.tianshengyoucai.model.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.IncomeLogBean;
import com.tsyc.tianshengyoucai.model.bean.ShopSpecBean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/30
 * File description：收入明细
 */
public class IncomeLogAdapter extends BaseQuickAdapter<IncomeLogBean.ResultBean.DataBean, BaseViewHolder> {

    public IncomeLogAdapter(@Nullable List<IncomeLogBean.ResultBean.DataBean> data) {
        super(R.layout.layout_income_item, data);
    }

    public IncomeLogAdapter() {
        super(R.layout.layout_income_item);
    }

    @Override
    protected void convert(BaseViewHolder holder, IncomeLogBean.ResultBean.DataBean dataBean) {

//        TextView tvDate = holder.getView(R.id.tv_date);
//        TextView tvStatus = holder.getView(R.id.tv_status);
//        TextView tvDesc = holder.getView(R.id.tv_desc);

        holder.setText(R.id.tv_date, String.valueOf(dataBean.getFinnshed_time()));
        holder.setText(R.id.tv_status,String.valueOf( dataBean.getOrder_amount()));
        holder.setText(R.id.tv_desc, dataBean.getRemark());
    }
}
