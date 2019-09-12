package com.tsyc.tianshengyoucai.model.adapter;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.HomeListBean;
import com.tsyc.tianshengyoucai.utils.ImageLoader;
import com.youth.xframe.utils.log.XLog;

import java.util.List;

/**
 * author：cxd
 * CreateTime：2019/7/19
 * File description： 首页 adapter
 */
public class HomeListAdapter extends BaseQuickAdapter<HomeListBean.ResultBean.GoodsBean, BaseViewHolder> {

    public HomeListAdapter() {
        super(R.layout.layout_home_goods_item);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void convert(BaseViewHolder holder, HomeListBean.ResultBean.GoodsBean goodsBean) {

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
        String commissionMoney = goodsBean.getCommission_money();
        String commissionText = goodsBean.getCommission_text();
        String commissionType = goodsBean.getCommission_type();
        String saleNumText = goodsBean.getSalenum_text();
        String shareText = goodsBean.getShare_text();

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
