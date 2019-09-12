package com.tsyc.tianshengyoucai.model.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.FindBean;
import com.tsyc.tianshengyoucai.model.bean.UnderlineOrderBean;
import com.tsyc.tianshengyoucai.utils.ImageLoader;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/26
 * File description：线下订单 adapter
 */
public class UnderlineOrderAdapter extends BaseQuickAdapter<UnderlineOrderBean.ResultBean.DataBean, BaseViewHolder> {

    public UnderlineOrderAdapter(@Nullable List<UnderlineOrderBean.ResultBean.DataBean> data) {
        super(R.layout.layout_order_item, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, UnderlineOrderBean.ResultBean.DataBean dataBean) {

        TextView mTvTitle = holder.getView(R.id.tv_title);
        TextView tv_order_shop_price = holder.getView(R.id.tv_order_shop_price);
        TextView mTvOrderStatus = holder.getView(R.id.tv_order_status);
        ImageView mIvOrderImg = holder.getView(R.id.iv_order_img);
        TextView mTvOrderName = holder.getView(R.id.tv_order_name);
        TextView mTvOrderDesc = holder.getView(R.id.tv_order_desc);
        TextView mTvOrderCount = holder.getView(R.id.tv_order_count);
        TextView mTvTotalCount = holder.getView(R.id.tv_total_count);
        TextView mTvOrderPrice = holder.getView(R.id.tv_order_price);
        TextView mTvOrderGrayStatus = holder.getView(R.id.tv_order_gray_status);
        TextView mTvOrderRedStatus = holder.getView(R.id.tv_order_red_status);
        TextView mTvOrderGrayLogistic = holder.getView(R.id.tv_order_gray_logistic); // 查看物流

        // 0 全部 1待付款 2待使用 3待评价 4 退款 售后
        List<UnderlineOrderBean.ResultBean.DataBean.OrdergoodsBean> orderGoods = dataBean.getOrdergoods();

        if (orderGoods != null && orderGoods.size() > 0) {
            String goodsImage = orderGoods.get(0).getGoods_image();
            String goods_price = orderGoods.get(0).getGoods_price(); // 13.00yuan
            String goods_name = orderGoods.get(0).getGoods_name();
            int goods_num = orderGoods.get(0).getGoods_num();
            String description = orderGoods.get(0).getGoods_guige();
            mTvOrderName.setText(goods_name);
            mTvOrderPrice.setText("订单金额  ￥ "+goods_price);
            tv_order_shop_price.setText("￥ "+goods_price);
            mTvOrderCount.setText("x " + goods_num);
            mTvTotalCount.setText("共"+goods_num+"件商品");
            mTvOrderDesc.setText(description);
            ImageLoader.loadCornersImage(mContext, goodsImage, mIvOrderImg, 5);
        }
        String store_name = dataBean.getStore_name();
        String order_sn = dataBean.getOrder_sn();
        String ziti_verify_code = dataBean.getZiti_verify_code();
        mTvOrderRedStatus.setVisibility(View.VISIBLE);
        mTvOrderGrayStatus.setVisibility(View.VISIBLE);
        int status = dataBean.getOrder_state();
        mTvTitle.setText(store_name);
        holder.addOnClickListener(R.id.tv_order_gray_status, R.id.tv_order_red_status);
        String orderType = "";
        //订单状态：0，-1(已取消)10(默认):未付款;20:已付款;30:已发货;40:已收货,待评价;50已评价100退款 110退款完成
        switch (status) {
            case -1:
            case 0:
                orderType = "已取消";
                mTvOrderGrayStatus.setText("删除订单");
                mTvOrderRedStatus.setVisibility(View.GONE);
                break;

            case 10:
                orderType = "待付款";
                mTvOrderGrayStatus.setText("取消订单");
                mTvOrderRedStatus.setText("去付款");

                break;

            case 20: //待核销
                orderType = "待使用";
                mTvOrderGrayStatus.setText("申请退款");
                mTvOrderRedStatus.setText("去使用");
                break;

            case 40:
                orderType = "待评价";
                mTvOrderGrayStatus.setText("删除订单");
                mTvOrderRedStatus.setText("去评价");
                break;

            case 50:
                orderType = "已完成";
                mTvOrderRedStatus.setVisibility(View.GONE);
                mTvOrderGrayStatus.setText("删除订单");
                break;

            case 100:
                orderType = "退款完成";
                mTvOrderRedStatus.setVisibility(View.GONE);
                mTvOrderGrayStatus.setText("删除订单");
                break;

            case 101:
                orderType = "待商家审核";
                mTvOrderRedStatus.setVisibility(View.GONE);
                mTvOrderGrayStatus.setText("退款进度");
                break;

            case 102:
            case 103:
            case 104:
                orderType = "待平台打款";
                mTvOrderRedStatus.setVisibility(View.GONE);
                mTvOrderGrayStatus.setText("退款进度");
                break;
        }
        mTvOrderStatus.setText(orderType);

    }

}
