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
import com.tsyc.tianshengyoucai.utils.ImageLoader;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/26
 * File description：发现页适配器
 */
public class FindAdapter extends BaseQuickAdapter<FindBean.ResultBean.DataBean, BaseViewHolder> {

    public FindAdapter(@Nullable List<FindBean.ResultBean.DataBean> data) {
        super(R.layout.layout_find_item,data);
    }

    @Override
    protected void convert(BaseViewHolder holder, FindBean.ResultBean.DataBean dataBean) {

        ImageView mIvGoodsImg = holder.getView(R.id.iv_goods_img);
        TextView mIvGoodsName = holder.getView(R.id.tv_goods_name);
        TextView mTvMonthCount = holder.getView(R.id.tv_month_count);
        TextView mTvPrice = holder.getView(R.id.tv_price);
        TextView mTvShareMoney = holder.getView(R.id.tv_share_money);
        LinearLayout mLLCharge = holder.getView(R.id.ll_charge); // 佣金条目
        TextView mTvShareBack = holder.getView(R.id.tv_share_back); // 分享返现
        TextView mTvTextBack = holder.getView(R.id.tv_text_back); // 分享返现
        TextView mTvComText = holder.getView(R.id.tv_commission_text); // 分享返现


        String goodsImage = dataBean.getGoods_image();
        String goodsName = dataBean.getGoods_name();
        String goodsPrice = dataBean.getGoods_price();
        int goodsSaleNum = dataBean.getGoods_salenum();
        String commissionMoney = dataBean.getCommission_money();
        String commissionText = dataBean.getCommission_text();
        String commissionType = dataBean.getCommission_type();
        String saleNumText = dataBean.getSalenum_text();
        String shareText = dataBean.getShare_text();

        ImageLoader.loadCornersImage(mContext, goodsImage, mIvGoodsImg,10);
        mIvGoodsName.setText(goodsName);
        mTvPrice.setText("￥" + goodsPrice);
        mTvMonthCount.setText(saleNumText + goodsSaleNum);


        if (!TextUtils.isEmpty(shareText)) {
            mTvShareBack.setVisibility(View.VISIBLE);
            mTvShareBack.setText(shareText);
        }
        if (!TextUtils.isEmpty(commissionMoney)) {
            mLLCharge.setVisibility(View.VISIBLE);
            mTvShareMoney.setText(commissionMoney);
            mTvComText.setText(commissionText);
            mTvTextBack.setText(commissionType);
        }

    }
}
