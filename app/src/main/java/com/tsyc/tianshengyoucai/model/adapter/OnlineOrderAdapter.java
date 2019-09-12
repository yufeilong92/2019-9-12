package com.tsyc.tianshengyoucai.model.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.UnderlineOrderBean;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.SendOrderActivity;
import com.tsyc.tianshengyoucai.utils.ImageLoader;
import com.youth.xframe.widget.XToast;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/26
 * File description：线shang订单 adapter
 */
public class OnlineOrderAdapter extends BaseQuickAdapter<UnderlineOrderBean.ResultBean.DataBean, BaseViewHolder> {

    public OnlineOrderAdapter(@Nullable List<UnderlineOrderBean.ResultBean.DataBean> data) {
        super(R.layout.layout_order_item, data);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void convert(BaseViewHolder holder, UnderlineOrderBean.ResultBean.DataBean dataBean) {

        TextView mTvTitle = holder.getView(R.id.tv_title);
        TextView tv_order_shop_price = holder.getView(R.id.tv_order_shop_price);
        ConstraintLayout ctlBottom = holder.getView(R.id.ctl_bottom);
        TextView mTvOrderStatus = holder.getView(R.id.tv_order_status);
        ImageView mIvOrderImg = holder.getView(R.id.iv_order_img);
        TextView mTvOrderName = holder.getView(R.id.tv_order_name);
        TextView mTvOrderDesc = holder.getView(R.id.tv_order_desc);
        TextView mTvOrderCount = holder.getView(R.id.tv_order_count);
        TextView mTvOrderPrice = holder.getView(R.id.tv_order_price);
        TextView tvTotalCount = holder.getView(R.id.tv_total_count);
        TextView mTvOrderGrayStatus = holder.getView(R.id.tv_order_gray_status);
        TextView mTvOrderRedStatus = holder.getView(R.id.tv_order_red_status);
        TextView mTvOrderGrayLogistic = holder.getView(R.id.tv_order_gray_logistic); // 查看物流

        // 0 全部 1待付款 2待使用 3待评价 4 退款 售后
        List<UnderlineOrderBean.ResultBean.DataBean.OrdergoodsBean> orderGoods = dataBean.getOrdergoods();
        String store_name = dataBean.getStore_name();
        String order_sn = dataBean.getOrder_sn();
        int order_id = dataBean.getOrder_id();
        int status = dataBean.getOrder_state();
        mTvTitle.setText(store_name);

        if (orderGoods != null && orderGoods.size() != 0) {
            String goodsImage = orderGoods.get(0).getGoods_image();
            String goods_price = orderGoods.get(0).getGoods_price(); // 13.00yuan
            String goods_name = orderGoods.get(0).getGoods_name();
            int goods_num = orderGoods.get(0).getGoods_num();
            String description = orderGoods.get(0).getGoods_guige();
            mTvOrderName.setText(goods_name);
            tv_order_shop_price.setText("￥" + goods_price);
            mTvOrderDesc.setText(description);
            ImageLoader.loadCornersImage(mContext, goodsImage, mIvOrderImg, 5);
        }
        String order_amount = dataBean.getOrder_amount();
        int total_goods_num = dataBean.getTotal_goods_num();
        mTvOrderCount.setText("x " + total_goods_num);
        tvTotalCount.setText("共" + total_goods_num + "件商品");
        mTvOrderPrice.setText("订单金额   " + "￥" + order_amount + " 元");
        mTvOrderRedStatus.setVisibility(View.VISIBLE);
        mTvOrderGrayStatus.setVisibility(View.VISIBLE);
        ctlBottom.setVisibility(View.VISIBLE);
        holder.addOnClickListener(R.id.tv_order_gray_status, R.id.tv_order_red_status, R.id.tv_order_gray_logistic);

        String orderType = "";
        //订单状态：0，-1(已取消)10(默认):未付款;20:已付款;30:已发货;40:已收货,待评价;50已评价 100退款 110退款完成
        switch (status) {
            case -1:
            case 0:
                orderType = "已取消";
                mTvOrderGrayStatus.setText("删除订单");
                mTvOrderRedStatus.setVisibility(View.GONE);
             //   holder.addOnClickListener(R.id.tv_order_gray_status);
                break;

            case 10:
                orderType = "待付款";
                mTvOrderGrayStatus.setText("取消订单");
                mTvOrderRedStatus.setText("去付款");
             //   holder.addOnClickListener(R.id.tv_order_gray_status, R.id.tv_order_red_status);
                break;

            case 20:
                orderType = "待发货";
                mTvOrderGrayStatus.setText("申请退货");
                mTvOrderRedStatus.setVisibility(View.GONE);
               // holder.addOnClickListener(R.id.tv_order_gray_status, R.id.tv_order_red_status);
                break;

            case 30:
                orderType = "待收货";
                mTvOrderGrayLogistic.setVisibility(View.VISIBLE);
                mTvOrderGrayStatus.setText("申请退货");
                mTvOrderGrayLogistic.setText("查看物流");
                mTvOrderRedStatus.setText("确认收货");
             //   holder.addOnClickListener(R.id.tv_order_gray_status, R.id.tv_order_red_status, R.id.tv_order_gray_logistic);
                break;
            case 40:
                orderType = "待评价";
                mTvOrderGrayStatus.setText("删除订单");
                mTvOrderRedStatus.setText("去评价");
            //    holder.addOnClickListener(R.id.tv_order_red_status);
                break;
            case 50:
                orderType = "已完成";
                mTvOrderRedStatus.setVisibility(View.GONE);
                mTvOrderGrayStatus.setText("删除订单");
                mTvOrderGrayLogistic.setVisibility(View.GONE);
                break;

            case 100:
                orderType = "已完成";
                mTvOrderGrayStatus.setText("删除订单");
                mTvOrderGrayLogistic.setVisibility(View.GONE);
                mTvOrderRedStatus.setVisibility(View.GONE);
                break;
            case 101:
                orderType = "等待商家审核";
                mTvOrderRedStatus.setVisibility(View.GONE);
                mTvOrderGrayStatus.setText("退款详情");
           //     holder.addOnClickListener(R.id.tv_order_gray_status);
                break;

            case 102:
                orderType = "等待用户发货";
                mTvOrderGrayStatus.setVisibility(View.GONE);
                mTvOrderRedStatus.setText("去发货");
               // holder.addOnClickListener(R.id.tv_order_red_status);
                mTvOrderRedStatus.setOnClickListener(v -> {
                    Bundle bundle = new Bundle();
                    bundle.putString("goods_id", String.valueOf(order_id));
                    Intent intent = new Intent(mContext, SendOrderActivity.class);
                    intent.putExtra("extra", bundle);
                    mContext.startActivity(intent);
                });
                break;
            case 103:
                orderType = "等待商家收货";
                mTvOrderRedStatus.setVisibility(View.GONE);
                mTvOrderGrayStatus.setText("等待商家收货");
          //      holder.addOnClickListener(R.id.tv_order_gray_status);
                break;
            case 104:
                orderType = "等待平台打款";
                ctlBottom.setVisibility(View.GONE);
                break;

        }
        mTvOrderStatus.setText(orderType);

    }

}
