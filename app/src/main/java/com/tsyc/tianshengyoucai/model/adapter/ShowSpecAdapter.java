package com.tsyc.tianshengyoucai.model.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.ShopSpecBean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/30
 * File description：
 */
public class ShowSpecAdapter extends BaseQuickAdapter<ShopSpecBean.ShopSpecItemBean, BaseViewHolder> {

    public ShowSpecAdapter(@Nullable List<ShopSpecBean.ShopSpecItemBean> data) {
        super(R.layout.layout_show_spec_item, data);
    }

    public ShowSpecAdapter() {
        super(R.layout.layout_show_spec_item);
    }

    private String isInner;

    public void setIsInner(String isInner) {
        this.isInner = isInner;
    }

    @Override
    protected void convert(BaseViewHolder holder, ShopSpecBean.ShopSpecItemBean item) {
        TextView mTvShopSpec = holder.getView(R.id.tv_shop_spec);
        TextView mTvShopCount = holder.getView(R.id.tv_shop_count);

        String specCount = item.getStock_num();
        String name = item.getSpec_value();
        String price = item.getPrice();
        String inner_price = item.getInner_price();

        if (isInner != null && isInner.equals("1")) {
            mTvShopSpec.setText(String.valueOf(name + "   " + price + " 元 , 内部价 " + inner_price+"元"));
        } else {
            mTvShopSpec.setText(String.valueOf(name + "   " + price + " 元"));
        }
        mTvShopCount.setText(String.valueOf("库存量 " + specCount));
    }
}
