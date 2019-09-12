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
import com.tsyc.tianshengyoucai.model.bean.HomeListBean;
import com.tsyc.tianshengyoucai.model.bean.ProductListBean;
import com.tsyc.tianshengyoucai.utils.ImageLoader;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/22
 * File description： 商家详情 商品适配器
 */
public class ProductListAdapter extends BaseQuickAdapter<ProductListBean.ResultBean, BaseViewHolder> {

    public ProductListAdapter(@Nullable List<ProductListBean.ResultBean> data) {
        super(R.layout.layout_product_list, data);
    }

    public ProductListAdapter() {
        super(R.layout.layout_product_list);
    }

    @Override
    protected void convert(BaseViewHolder holder, ProductListBean.ResultBean goodsBean) {


        ImageView mIvGoodsImg = holder.getView(R.id.iv_goods_img);
        TextView mIvGoodsName = holder.getView(R.id.tv_goods_name);
        TextView mTvMonthCount = holder.getView(R.id.tv_month_count);
        TextView mTvPrice = holder.getView(R.id.tv_price);
        TextView mTvShareMoney = holder.getView(R.id.tv_share_money);
        LinearLayout mLLCharge = holder.getView(R.id.ll_charge); // 佣金条目
        TextView mTvShareBack = holder.getView(R.id.tv_share_back); // 分享返现
        TextView mTvTextBack = holder.getView(R.id.tv_text_back); // 分享返现
        TextView mTvComText = holder.getView(R.id.tv_commission_text); // 分享返现


        String goodsImage = goodsBean.getGoods_image();
        String goodsName = goodsBean.getGoods_name();
        String goodsPrice = goodsBean.getGoods_price();
        int goodsSaleNum = goodsBean.getGoods_salenum();
        mTvMonthCount.setText("月销  " + goodsSaleNum);
        String commissionMoney = goodsBean.getCommission();
        String commissionText = goodsBean.getCommission_text();
        String commissionType = goodsBean.getCommission_type();
        String shareText = goodsBean.getShare_text();

        ImageLoader.loadCornersImage(mContext, goodsImage, mIvGoodsImg, 20);
        mIvGoodsName.setText(goodsName);
        mTvPrice.setText("￥" + goodsPrice);

        if (!TextUtils.isEmpty(shareText)) {
            mTvShareBack.setVisibility(View.VISIBLE);
            mTvShareBack.setText(shareText);
        }else {
            mTvShareBack.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(commissionMoney)) {
            mLLCharge.setVisibility(View.VISIBLE);
            mTvShareMoney.setText(commissionMoney);
            mTvComText.setText(commissionText);
            mTvTextBack.setText(commissionType);
        } else {
            mLLCharge.setVisibility(View.GONE);
        }


    }
}
