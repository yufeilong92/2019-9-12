package com.tsyc.tianshengyoucai.adapter.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.HomeListBean;
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.tsyc.tianshengyoucai.utils.TextUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/8 10:45
 * @Purpose :商家入驻
 */
public class SellerListAdapter extends RecyclerView.Adapter<SellerListAdapter.ViewHolder> {
    private Context mContext;
    private List<HomeListBean.ResultBean.StoreBean> mData;
    private final LayoutInflater mInflater;
    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int postion, HomeListBean.ResultBean.StoreBean vo);

    }

    public SellerListAdapter(Context mContext, List<HomeListBean.ResultBean.StoreBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_home_seller_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeListBean.ResultBean.StoreBean bean = mData.get(position);
        List<ImageView> imgs = new ArrayList<>();
        imgs.add(holder.mIvItemHomeSellerStarOne);
        imgs.add(holder.mIvItemHomeSellerStarTwo);
        imgs.add(holder.mIvItemHomeSellerStarThree);
        imgs.add(holder.mIvItemHomeSellerStarFour);
        imgs.add(holder.mIvItemHomeSellerStarFive);
        showStart(imgs, bean.getStore_grade());
        GlideUtil.getSingleton().loadQuadRangleImager(mContext, holder.mIvItemHomeSellerImg, bean.getImage());
        holder.mTvItemHomeSellerName.setText(bean.getStore_name());
        holder.mTvItemHomeSellerTag.setText(bean.getShare_info());
        holder.mTvItemHomeSellerAddress.setText(bean.getAddress());

        TextUtil.bindTextViewWHine( holder.mTvItemType1,bean.getCategory_tab());
        TextUtil.bindTextViewWHine( holder.mTvItemType2,bean.getOnline_tab());
        TextUtil.bindTextViewWHine( holder.mTvItemType3,bean.getRed_tab());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(position, bean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private void showStart(List<ImageView> list, int postion) {
        for (int i = 0; i < list.size(); i++) {
            if (i <= postion - 1) {
                showOrHine(list.get(i), true);
            } else {
                showOrHine(list.get(i), false);
            }
        }
    }

    private void showOrHine(ImageView imageView, boolean show) {
        imageView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mIvItemHomeSellerImg;
        public TextView mTvItemHomeSellerName;
        public ImageView mIvItemHomeSellerStarOne;
        public ImageView mIvItemHomeSellerStarTwo;
        public ImageView mIvItemHomeSellerStarThree;
        public ImageView mIvItemHomeSellerStarFour;
        public ImageView mIvItemHomeSellerStarFive;
        public TextView mTvItemHomeSellerTag;
        public TextView mTvItemHomeSellerAddress;
        public TextView mTvItemType1;
        public TextView mTvItemType2;
        public TextView mTvItemType3;


        public ViewHolder(View itemView) {
            super(itemView);
            this.mIvItemHomeSellerImg = (ImageView) itemView.findViewById(R.id.iv_item_home_seller_img);
            this.mTvItemHomeSellerName = (TextView) itemView.findViewById(R.id.tv_item_home_seller_name);
            this.mIvItemHomeSellerStarOne = (ImageView) itemView.findViewById(R.id.iv_item_home_seller_star_one);
            this.mIvItemHomeSellerStarTwo = (ImageView) itemView.findViewById(R.id.iv_item_home_seller_star_two);
            this.mIvItemHomeSellerStarThree = (ImageView) itemView.findViewById(R.id.iv_item_home_seller_star_three);
            this.mIvItemHomeSellerStarFour = (ImageView) itemView.findViewById(R.id.iv_item_home_seller_star_four);
            this.mIvItemHomeSellerStarFive = (ImageView) itemView.findViewById(R.id.iv_item_home_seller_star_five);
            this.mTvItemHomeSellerTag = (TextView) itemView.findViewById(R.id.tv_item_home_seller_tag);
            this.mTvItemHomeSellerAddress = (TextView) itemView.findViewById(R.id.tv_item_home_seller_address);
            this.mTvItemType1 = (TextView) itemView.findViewById(R.id.tv_item_type1);
            this.mTvItemType2 = (TextView) itemView.findViewById(R.id.tv_item_type2);
            this.mTvItemType3 = (TextView) itemView.findViewById(R.id.tv_item_type3);
        }
    }


}
