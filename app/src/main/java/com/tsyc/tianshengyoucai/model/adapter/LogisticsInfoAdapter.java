package com.tsyc.tianshengyoucai.model.adapter;

import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.LogisticsListBean;
import com.youth.xframe.utils.XDensityUtils;
import com.youth.xframe.utils.XStringUtils;
import com.youth.xframe.utils.log.XLog;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/31
 * File description：查看物流适配器
 */
public class LogisticsInfoAdapter extends BaseQuickAdapter<LogisticsListBean.ResultBean.DeliverInfoBean, BaseViewHolder> {

    public LogisticsInfoAdapter(@Nullable List<LogisticsListBean.ResultBean.DeliverInfoBean> data) {
        super(R.layout.layout_logistics_item, data);
    }

    public LogisticsInfoAdapter() {
        super(R.layout.layout_logistics_item);
    }

    @Override
    protected void convert(BaseViewHolder holder, LogisticsListBean.ResultBean.DeliverInfoBean deliverInfoBean) {

        TextView tvDate = holder.getView(R.id.tv_date);
        TextView tvShopAddress = holder.getView(R.id.tv_shop_address);
        ImageView ivIcon = holder.getView(R.id.iv_icon);

        int position = holder.getLayoutPosition();

        String context = deliverInfoBean.getContext();
        String time = deliverInfoBean.getTime();
        XLog.e("" + context + "_____" + time);
        tvDate.setText(time);
        tvShopAddress.setText(context);
        ivIcon.setLayoutParams(new ConstraintLayout.LayoutParams(XDensityUtils.dp2px(12), XDensityUtils.dp2px(12)));
        if (position == 0) {
            tvDate.setTextColor(mContext.getResources().getColor(R.color.tab_color));
            tvShopAddress.setTextColor(mContext.getResources().getColor(R.color.tab_color));
            ivIcon.setBackground(mContext.getResources().getDrawable(R.mipmap.jft_img_selectedlogistics));
            ivIcon.setLayoutParams(new ConstraintLayout.LayoutParams(XDensityUtils.dp2px(20), XDensityUtils.dp2px(20)));
        }

    }
}
