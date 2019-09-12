package com.tsyc.tianshengyoucai.adapter.shop;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/10 15:21
 * @Purpose :
 */
public class ShopSortAdapter extends RecyclerView.Adapter<ShopSortAdapter.ShopTypeHolder> {

    private Context mContext;
    private List<String> mData;
    private final LayoutInflater mInflater;

    public ShopSortAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(String vo);

    }


    @NonNull
    @Override
    public ShopTypeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_shop_sort, null);
        return new ShopTypeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopTypeHolder holder, int position) {
        String s = mData.get(position);
        holder.mTvItemShopType.setText(s);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.itemClick(s);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ShopTypeHolder extends RecyclerView.ViewHolder {
        public TextView mTvItemShopType;

        public ShopTypeHolder(View itemView) {
            super(itemView);
            this.mTvItemShopType = (TextView) itemView.findViewById(R.id.tv_item_shop_sort);
        }
    }


}
