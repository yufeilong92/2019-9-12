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
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.vo.SearchGoodVo;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/8 11:15
 * @Purpose :精品
 */
public class HomSearchAdapter extends RecyclerView.Adapter<HomSearchAdapter.VIewHolder> {
    private Context mContext;
    private List<SearchGoodVo.ResultBean.DataBean> mData;
    private final LayoutInflater mInflater;

    public HomSearchAdapter(Context mContext, List<SearchGoodVo.ResultBean.DataBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(int position, SearchGoodVo.ResultBean.DataBean bean);

    }

    @NonNull
    @Override
    public VIewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_home_search_layout, null);
        return new VIewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VIewHolder holder, int position) {
        SearchGoodVo.ResultBean.DataBean bean = mData.get(position);
        GlideUtil.getSingleton().LoadImager(mContext, holder.mIvItemHomesearchImg, bean.getGoods_image());
        holder.mTvItemHomesearchTitle.setText(bean.getGoods_name());
        holder.mTvItemHomesearchMoney.setText("￥" + bean.getGoods_price());

        if (StringUtil.isEmpty(bean.getCommission_money())) {
            holder.mTvItemHomesearchMoneyMoney.setText("0");
        } else {

            holder.mTvItemHomesearchMoneyMoney.setText(bean.getCommission_money());
        }
        holder.mTvItemHomesearchMoneyTag.setText(bean.getCommission_type());
        holder.mTvItemHomesearchMoneyType.setText(bean.getCommission_text());
        holder.mTvItemHomesearchNumber.setText(bean.getGoods_salenum() + "");
//        if (StringUtil.isEmpty(bean.getShare_text())) {
//            holder.mTvItemHomesearchTag.setVisibility(View.GONE);
//        } else {
//            holder.mTvItemHomesearchTag.setVisibility(View.VISIBLE);
//            holder.mTvItemHomesearchTag.setText(bean.getShare_text());
//        }
        holder.mTvItemHomesearchType.setText(bean.getSalenum_text());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.itemClick(position, bean);
                }
            }
        });
        holder.mLlHomeSearchMoney.setVisibility(StringUtil.isEmpty(bean.getCommission_money()) || bean.getCommission_money().equals("0")
                ? View.GONE : View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class VIewHolder extends RecyclerView.ViewHolder {
        public ImageView mIvItemHomesearchImg;
        public TextView mTvItemHomesearchTitle;
        public TextView mTvItemHomesearchType;
        public TextView mTvItemHomesearchNumber;
        public TextView mTvItemHomesearchTag;
        public TextView mTvItemHomesearchMoney;
        public TextView mTvItemHomesearchMoneyTag;
        public TextView mTvItemHomesearchMoneyMoney;
        public TextView mTvItemHomesearchMoneyType;
        public LinearLayout mLlHomeSearchMoney;

        public VIewHolder(View itemView) {
            super(itemView);
            this.mIvItemHomesearchImg = (ImageView) itemView.findViewById(R.id.iv_item_home_search_img);
            this.mTvItemHomesearchTitle = (TextView) itemView.findViewById(R.id.tv_item_home_search_title);
            this.mTvItemHomesearchType = (TextView) itemView.findViewById(R.id.tv_item_home_search_type);
            this.mTvItemHomesearchNumber = (TextView) itemView.findViewById(R.id.tv_item_home_search_number);
            this.mTvItemHomesearchTag = (TextView) itemView.findViewById(R.id.tv_item_home_search_tag);
            this.mTvItemHomesearchMoney = (TextView) itemView.findViewById(R.id.tv_item_home_search_money);
            this.mTvItemHomesearchMoneyTag = (TextView) itemView.findViewById(R.id.tv_item_home_search_money_tag);
            this.mTvItemHomesearchMoneyMoney = (TextView) itemView.findViewById(R.id.tv_item_home_search_money_money);
            this.mTvItemHomesearchMoneyType = (TextView) itemView.findViewById(R.id.tv_item_home_search_money_type);
            this.mLlHomeSearchMoney = (LinearLayout) itemView.findViewById(R.id.ll_home_search_money);

        }
    }


}
