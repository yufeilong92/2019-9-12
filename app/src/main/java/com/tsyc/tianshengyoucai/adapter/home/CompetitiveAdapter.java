package com.tsyc.tianshengyoucai.adapter.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.HomeListBean;
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.TextUtil;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/8 11:15
 * @Purpose :精品
 */
public class CompetitiveAdapter extends RecyclerView.Adapter<CompetitiveAdapter.VIewHolder> {
    private Context mContext;
    private List<HomeListBean.ResultBean.GoodsBean> mData;
    private final LayoutInflater mInflater;

    public CompetitiveAdapter(Context mContext, List<HomeListBean.ResultBean.GoodsBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(int position, HomeListBean.ResultBean.GoodsBean bean);

    }

    @NonNull
    @Override
    public VIewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_home_good_layout, null);
        return new VIewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VIewHolder holder, int position) {
        HomeListBean.ResultBean.GoodsBean bean = mData.get(position);
        GlideUtil.getSingleton().LoadImager(mContext, holder.mIvItemHomeGoodImg, bean.getGoods_image());
        holder.mTvItemHomeGoodTitle.setText(bean.getGoods_name());
        holder.mTvItemHomeGoodMoney.setText("￥" + bean.getGoods_promotion_price());
        holder.mTvItemHomeGoodNumber.setText(bean.getGoods_salenum() + "");
        holder.mLlHomeGoodMoney.setVisibility(StringUtil.isEmpty(bean.getCommission_money())||bean.getCommission_money().equals("0") ? View.GONE:View.VISIBLE);
        TextUtil.bindTextViewWHine(holder.mTvItemHomeGoodTag, bean.getShare_text());
        TextUtil.bindTextViewWHine(holder.mTvItemHomeGoodMoneyTag, bean.getCommission_type());
        TextUtil.bindTextViewWHine(holder.mTvItemHomeGoodMoneyMoney, bean.getCommission_money());
        TextUtil.bindTextViewWHine(holder.mTvItemHomeGoodMoneyType, bean.getCommission_text());
        holder.mTvItemHomeGoodType.setText(bean.getSalenum_text());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.itemClick(position, bean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class VIewHolder extends RecyclerView.ViewHolder {
        public ImageView mIvItemHomeGoodImg;
        public TextView mTvItemHomeGoodTitle;
        public TextView mTvItemHomeGoodType;
        public TextView mTvItemHomeGoodNumber;
        public TextView mTvItemHomeGoodTag;
        public TextView mTvItemHomeGoodMoney;
        public TextView mTvItemHomeGoodMoneyTag;
        public TextView mTvItemHomeGoodMoneyMoney;
        public TextView mTvItemHomeGoodMoneyType;
        public LinearLayout mLlHomeGoodMoney;

        public VIewHolder(View itemView) {
            super(itemView);
            this.mIvItemHomeGoodImg = (ImageView) itemView.findViewById(R.id.iv_item_home_good_img);
            this.mTvItemHomeGoodTitle = (TextView) itemView.findViewById(R.id.tv_item_home_good_title);
            this.mTvItemHomeGoodType = (TextView) itemView.findViewById(R.id.tv_item_home_good_type);
            this.mTvItemHomeGoodNumber = (TextView) itemView.findViewById(R.id.tv_item_home_good_number);
            this.mTvItemHomeGoodTag = (TextView) itemView.findViewById(R.id.tv_item_home_good_tag);
            this.mTvItemHomeGoodMoney = (TextView) itemView.findViewById(R.id.tv_item_home_good_money);
            this.mTvItemHomeGoodMoneyTag = (TextView) itemView.findViewById(R.id.tv_item_home_good_money_tag);
            this.mTvItemHomeGoodMoneyMoney = (TextView) itemView.findViewById(R.id.tv_item_home_good_money_money);
            this.mTvItemHomeGoodMoneyType = (TextView) itemView.findViewById(R.id.tv_item_home_good_money_type);
            this.mLlHomeGoodMoney = (LinearLayout) itemView.findViewById(R.id.ll_home_good_money_layout);

        }
    }


}
