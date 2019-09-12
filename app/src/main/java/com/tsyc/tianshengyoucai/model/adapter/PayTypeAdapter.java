package com.tsyc.tianshengyoucai.model.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.FindBean;
import com.tsyc.tianshengyoucai.model.bean.PayTypeBean;
import com.tsyc.tianshengyoucai.utils.ImageLoader;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/26
 * File description：支付类型
 */
public class PayTypeAdapter extends BaseQuickAdapter<PayTypeBean.ResultBean, BaseViewHolder> {

    public PayTypeAdapter(@Nullable List<PayTypeBean.ResultBean> data) {
        super(R.layout.layout_pay_item, data);
    }

    public PayTypeAdapter() {
        super(R.layout.layout_pay_item);
    }

    @Override
    protected void convert(BaseViewHolder holder, PayTypeBean.ResultBean resultBean) {
        ImageView ivImage = holder.getView(R.id.iv_image);
        TextView tvPayName = holder.getView(R.id.tv_pay_name);
        CheckBox cbSelect = holder.getView(R.id.cb_select);
        String logo = resultBean.getLogo();
        String name = resultBean.getName();
        int pay_type = resultBean.getPay_type();

        ImageLoader.loadNormal(mContext, logo, ivImage);
        tvPayName.setText(name);
        boolean check = resultBean.isCheck();
        cbSelect.setChecked(check);

    }
}
