package com.tsyc.tianshengyoucai.model.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.BackMoneyBean;
import com.tsyc.tianshengyoucai.utils.ImageLoader;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/8
 * File description： 退款是否收到货
 */
public class BackMoneyRecShopAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public BackMoneyRecShopAdapter(@Nullable List<String> data) {
        super(R.layout.layout_back_money_item, data);
    }
    @Override
    protected void convert(BaseViewHolder holder, String dataBean) {

        TextView tv_name = holder.getView(R.id.tv_name);
        tv_name.setText(dataBean);
    }
}
