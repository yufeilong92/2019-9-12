package com.tsyc.tianshengyoucai.adapter.shop;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.tsyc.tianshengyoucai.utils.TextUtil;
import com.tsyc.tianshengyoucai.vo.ShopVo;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/10 10:58
 * @Purpose :
 */
public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ShopCollectViewHolder> {

    private Context mContext;
    private List<ShopVo> mData;
    private final LayoutInflater mInflater;

    public ShopListAdapter(Context mContext, List<ShopVo> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int postion, ShopVo vo);


    }

    @NonNull
    @Override
    public ShopCollectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_shop_list, null);
        return new ShopCollectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopCollectViewHolder holder, int position) {
        ShopVo bean = mData.get(position);
        List<ImageView> imgs = new ArrayList<>();
        imgs.add(holder.mIvItemShopcollectStarOne);
        imgs.add(holder.mIvItemShopcollectStarTwo);
        imgs.add(holder.mIvItemShopcollectStarThree);
        imgs.add(holder.mIvItemShopcollectStarFour);
        imgs.add(holder.mIvItemShopcollectStarFive);
        showStart(imgs, bean.getStore_credit());
        GlideUtil.getSingleton().loadQuadRangleImager(mContext, holder.mIvItemShopCollect, bean.getStore_avatar());
        holder.mTvItemShopcollectTitle.setText(bean.getStore_name());
        holder.mTvItemHomeSellerTag.setText(bean.getShare_commission());
        holder.mTvItemShopcollectAddress.setText(bean.getArea_info()+bean.getStore_address());
        ShopVo.OtherBean other = bean.getOther();
        TextUtil.bindTextViewWHine(holder.mTvItemShopcollectType1, other.getCategory_tab());
        TextUtil.bindTextViewWHine(holder.mTvItemShopcollectType2, other.getIs_voucher());
        TextUtil.bindTextViewWHine(holder.mTvItemShopcollectType3, other.getIs_online_offline());
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

    public class ShopCollectViewHolder extends RecyclerView.ViewHolder {
        public ImageView mIvItemShopCollect;
        public TextView mTvItemShopcollectTitle;
        public ImageView mIvItemShopcollectStarOne;
        public ImageView mIvItemShopcollectStarTwo;
        public ImageView mIvItemShopcollectStarThree;
        public ImageView mIvItemShopcollectStarFour;
        public ImageView mIvItemShopcollectStarFive;
        public TextView mTvItemHomeSellerTag;
        public TextView mTvItemShopcollectAddress;
        public TextView mTvItemShopcollectType1;
        public TextView mTvItemShopcollectType2;
        public TextView mTvItemShopcollectType3;


        public ShopCollectViewHolder(View itemView) {
            super(itemView);
            this.mIvItemShopCollect = (ImageView) itemView.findViewById(R.id.iv_item_shop_collect);
            this.mTvItemShopcollectTitle = (TextView) itemView.findViewById(R.id.tv_item_shopcollect_title);
            this.mIvItemShopcollectStarOne = (ImageView) itemView.findViewById(R.id.iv_item_shopcollect_star_one);
            this.mIvItemShopcollectStarTwo = (ImageView) itemView.findViewById(R.id.iv_item_shopcollect_star_two);
            this.mIvItemShopcollectStarThree = (ImageView) itemView.findViewById(R.id.iv_item_shopcollect_star_three);
            this.mIvItemShopcollectStarFour = (ImageView) itemView.findViewById(R.id.iv_item_shopcollect_star_four);
            this.mIvItemShopcollectStarFive = (ImageView) itemView.findViewById(R.id.iv_item_shopcollect_star_five);
            this.mTvItemHomeSellerTag = (TextView) itemView.findViewById(R.id.tv_item_shopcollect_tag);
            this.mTvItemShopcollectAddress = (TextView) itemView.findViewById(R.id.tv_item_shopcollect_address);
            this.mTvItemShopcollectType1 = (TextView) itemView.findViewById(R.id.tv_item_shopcollect_type1);
            this.mTvItemShopcollectType2 = (TextView) itemView.findViewById(R.id.tv_item_shopcollect_type2);
            this.mTvItemShopcollectType3 = (TextView) itemView.findViewById(R.id.tv_item_shopcollect_type3);

        }
    }


 
}
