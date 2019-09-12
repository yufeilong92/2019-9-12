package com.tsyc.tianshengyoucai.model.adapter;

import android.support.annotation.Nullable;
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
public class BackMoneyRecAdapter extends BaseQuickAdapter<BackMoneyBean.ResultBean.RefundTypeBean, BaseViewHolder> {

    public BackMoneyRecAdapter(@Nullable List<BackMoneyBean.ResultBean.RefundTypeBean> data) {
        super(R.layout.layout_back_money_rec_item, data);
    }

    public BackMoneyRecAdapter() {
        super(R.layout.layout_back_money_rec_item);
    }

    @Override
    protected void convert(BaseViewHolder holder, BackMoneyBean.ResultBean.RefundTypeBean dataBean) {

        TextView tv_name = holder.getView(R.id.tv_bag_type);
        TextView tv_desc = holder.getView(R.id.tv_bag_detail);
        ImageView imageView = holder.getView(R.id.iv_bag);


        String description = dataBean.getDescription();
        String img = dataBean.getImg();
        String name = dataBean.getName();

        tv_name.setText(name);
        tv_desc.setText(description);

        ImageLoader.setImageCircle(mContext, img, imageView);


    }
}
