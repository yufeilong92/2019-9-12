package com.tsyc.tianshengyoucai.model.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.ApplyCashBean;
import com.tsyc.tianshengyoucai.model.bean.GoodsDetailBean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/8
 * File description： 商品详情 属性
 */
public class ShowShopSpecAdapter extends BaseQuickAdapter<GoodsDetailBean.ResultBean.GoodsAttrBean, BaseViewHolder> {

    public ShowShopSpecAdapter(@Nullable List<GoodsDetailBean.ResultBean.GoodsAttrBean> data) {
        super(R.layout.goods_spec_item, data);
    }
    public ShowShopSpecAdapter() {
        super(R.layout.goods_spec_item);
    }

    @Override
    protected void convert(BaseViewHolder holder, GoodsDetailBean.ResultBean.GoodsAttrBean dataBean) {

        TextView specName = holder.getView(R.id.tv_spec_name);
        TextView specValue = holder.getView(R.id.tv_spec_value);

        String name = dataBean.getName();
        String value = dataBean.getValue();
        specName.setText(name);
        specValue.setText(value);
    }
}
