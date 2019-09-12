package com.tsyc.tianshengyoucai.model.adapter;

import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.OrderManageBean;
import com.tsyc.tianshengyoucai.model.bean.UnderlineOrderBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.OrderManageActivity;
import com.tsyc.tianshengyoucai.utils.ImageLoader;
import com.youth.xframe.utils.log.XLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author：van
 * CreateTime：2019/7/26
 * File description：订单管理 adapter
 */
public class OrderManageAdapter extends BaseQuickAdapter<OrderManageBean.ResultBean.DataBean, BaseViewHolder> {

    public OrderManageAdapter(@Nullable List<OrderManageBean.ResultBean.DataBean> data) {
        super(R.layout.layout_order_manage_item, data);
    }
    public OrderManageAdapter() {
        super(R.layout.layout_order_manage_item);
    }

    @Override
    protected void convert(BaseViewHolder holder, OrderManageBean.ResultBean.DataBean dataBean) {

        TextView mTvTitle = holder.getView(R.id.tv_title);
        TextView mTvOrderStatus = holder.getView(R.id.tv_order_status);
        TextView tv_order_shop_price = holder.getView(R.id.tv_order_shop_price);
        TextView mTvTotalCount = holder.getView(R.id.tv_total_count);
        ImageView mIvOrderImg = holder.getView(R.id.iv_order_img);
        TextView mTvOrderName = holder.getView(R.id.tv_order_name);
        TextView mTvOrderDesc = holder.getView(R.id.tv_order_desc);
        TextView mTvOrderCount = holder.getView(R.id.tv_order_count);
        TextView mTvOrderPrice = holder.getView(R.id.tv_order_price);
        TextView mTvOrderGrayStatus = holder.getView(R.id.tv_order_gray_status);
        TextView mTvOrderRedStatus = holder.getView(R.id.tv_order_red_status);
        ConstraintLayout mCtlBottom = holder.getView(R.id.ctl_bottom);

        //0 全部 1待发货 2已发货 3退款/售后
        String store_name = dataBean.getStore_name();

        List<OrderManageBean.ResultBean.DataBean.OrdergoodsBean> orderGoods = dataBean.getOrdergoods();

        OrderManageBean.ResultBean.DataBean.OrdergoodsBean goodsBean = orderGoods.get(0);
        String goodsImage = goodsBean.getGoods_image();
        String goods_name = goodsBean.getGoods_name();
        int goods_num = goodsBean.getGoods_num();
        String goods_price = goodsBean.getGoods_price();
        String goods_guige = goodsBean.getGoods_guige();
        int goods_id = goodsBean.getGoods_id();
        String order_sn = dataBean.getOrder_sn();

        int order_state = dataBean.getOrder_state();

        mTvTitle.setText(store_name);
        mTvOrderName.setText(goods_name);
        mTvOrderPrice.setText("￥ "+goods_price);
        tv_order_shop_price.setText("￥ "+goods_price);

        mTvOrderCount.setText(String.valueOf("订单金额   " + goods_num));
        mTvTotalCount.setText(String.valueOf("共" + goods_num + "件商品"));
        mTvOrderDesc.setText(goods_guige);
        ImageLoader.loadCornersImage(mContext, goodsImage, mIvOrderImg, 5);

        holder.addOnClickListener(R.id.tv_order_gray_status, R.id.tv_order_red_status);
        int order_type = dataBean.getOrder_type(); // 1 线上  2. 线下
        //0 全部 1待发货 2已发货 3退款/售后
        mTvOrderRedStatus.setVisibility(View.VISIBLE);
        mTvOrderGrayStatus.setVisibility(View.VISIBLE);
        mCtlBottom.setVisibility(View.VISIBLE);

        XLog.e("order_type   "+order_type);
        if (order_type == 1) {
            lineOrder(order_state, mTvOrderStatus, mTvOrderRedStatus, mCtlBottom, mTvOrderGrayStatus);
        } else {
            underlineOrder(order_state, mTvOrderStatus, mTvOrderRedStatus, mCtlBottom, mTvOrderGrayStatus);
        }
    }

    //线下订单处理
    private void underlineOrder(int order_state, TextView mTvOrderStatus, TextView mTvOrderRedStatus,
                                ConstraintLayout mCtlBottom, TextView mTvOrderGrayStatus) {
        switch (order_state) {
            case 20: // 扫描核销
                mTvOrderStatus.setText("未核销");
                mTvOrderGrayStatus.setVisibility(View.GONE);
                mTvOrderRedStatus.setText("扫码核销");
                break;

            case 40: // 评价
            case 50: // 已完成
                mTvOrderStatus.setText("已核销");
                mTvOrderRedStatus.setVisibility(View.GONE);
                mTvOrderGrayStatus.setText("删除订单");
                mCtlBottom.setVisibility(View.GONE);
                break;

            case 100:
                mTvOrderStatus.setText("已退款");
                mCtlBottom.setVisibility(View.GONE);
                mTvOrderGrayStatus.setVisibility(View.VISIBLE);
                mTvOrderGrayStatus.setText("删除订单");
                break;
            case 101: // 同意 拒绝
                mTvOrderStatus.setText("维权中-待商家审核");
                mTvOrderGrayStatus.setText("不同意退款");
                mTvOrderRedStatus.setText("同意退款");
                break;

            case 102:
                mTvOrderStatus.setText("维权中-待用户发货");
                mCtlBottom.setVisibility(View.GONE);
                mTvOrderRedStatus.setVisibility(View.GONE);
                mTvOrderGrayStatus.setVisibility(View.GONE);
                break;
            case 103: // 退款完成
                mTvOrderStatus.setText("维权中-待商家收货");
                mTvOrderGrayStatus.setText("确认收货");
                mTvOrderRedStatus.setVisibility(View.GONE);
                break;

            case 104: // 退款完成
                mTvOrderStatus.setText("维权中-待平台打款");
                mTvOrderGrayStatus.setText("确认收货");
                mCtlBottom.setVisibility(View.GONE);
                break;
        }

    }

    //线上订单处理
    private void lineOrder(int order_state, TextView mTvOrderStatus, TextView mTvOrderRedStatus,
                           ConstraintLayout mCtlBottom, TextView mTvOrderGrayStatus) {

        switch (order_state) {
            case 0:
            case -1: // 未付款
                mTvOrderStatus.setText("已取消");
                mTvOrderRedStatus.setVisibility(View.GONE);
                mTvOrderGrayStatus.setText("删除订单");
                mCtlBottom.setVisibility(View.GONE);
                break;
            case 10: // 未付款
                mTvOrderStatus.setText("未付款");
                mCtlBottom.setVisibility(View.GONE);
                break;

            case 20://待发货
                mTvOrderStatus.setText("待发货");
                mTvOrderGrayStatus.setVisibility(View.GONE);
                mTvOrderRedStatus.setText("去发货");
                break;

            case 30://已发货
                mTvOrderGrayStatus.setVisibility(View.GONE);
                mTvOrderRedStatus.setText("查看物流");
                mTvOrderStatus.setText("已发货");
                break;

            case 40: // 已完成
                mTvOrderStatus.setText("已完成");
                mTvOrderRedStatus.setVisibility(View.GONE);
                mTvOrderGrayStatus.setVisibility(View.GONE);
                mCtlBottom.setVisibility(View.GONE);
                break;

            case 50: // 已完成
                mTvOrderStatus.setText("已完成");
                mTvOrderRedStatus.setVisibility(View.GONE);
                mTvOrderGrayStatus.setVisibility(View.GONE);
                mCtlBottom.setVisibility(View.GONE);
                break;

            case 100://申请退款
                mTvOrderStatus.setText("退款完成");
                mTvOrderGrayStatus.setVisibility(View.GONE);
                mTvOrderRedStatus.setVisibility(View.GONE);
                break;

            case 101://申请退款
                mTvOrderStatus.setText("维权中-待商家审核");
                mTvOrderGrayStatus.setText("不同意退款");
                mTvOrderRedStatus.setText("同意退款");
                break;

            case 102:
                mTvOrderStatus.setText("维权中-待用户发货");
                mCtlBottom.setVisibility(View.GONE);
                break;
            case 103: // 退款完成
                mTvOrderStatus.setText("维权中-待商家收货");
                mTvOrderGrayStatus.setText("确认收货");
                mTvOrderRedStatus.setVisibility(View.GONE);
                break;

            case 104: // 退款完成
                mTvOrderStatus.setText("维权中-待平台打款");
                mTvOrderGrayStatus.setText("确认收货");
                mCtlBottom.setVisibility(View.GONE);
                break;
        }
    }
}
