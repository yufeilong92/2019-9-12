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
import com.tsyc.tianshengyoucai.vo.AllTypeBeanVo;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/10 15:21
 * @Purpose :
 */
public class AllShopSubAdapter extends RecyclerView.Adapter<AllShopSubAdapter.ShopTypeHolder> {

    private Context mContext;
    private List<AllTypeBeanVo.ChildBean> mData;
    private final LayoutInflater mInflater;

    public AllShopSubAdapter(Context mContext, List<AllTypeBeanVo.ChildBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void allTypeClick(int position, AllTypeBeanVo.ChildBean vo);

    }


    @NonNull
    @Override
    public ShopTypeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_shop_type, null);

        return new ShopTypeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopTypeHolder holder, int position) {
        AllTypeBeanVo.ChildBean childBean = mData.get(position);
//        if (vo.getSc_id() == -1) {
//            holder.mIvShopType.setImageResource(R.mipmap.logo);
//        } else
//            GlideUtil.getSingleton().loadQuadRangleImager(mContext, holder.mIvShopType, vo.getSc_icon());
        holder.mTvItemShopType.setText(childBean.getSc_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.allTypeClick(position, childBean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ShopTypeHolder extends RecyclerView.ViewHolder {
        public ImageView mIvShopType;
        public TextView mTvItemShopType;

        public ShopTypeHolder(View itemView) {
            super(itemView);
            this.mIvShopType = (ImageView) itemView.findViewById(R.id.iv_shop_type);
            this.mTvItemShopType = (TextView) itemView.findViewById(R.id.tv_item_shop_type);
        }
    }


}
