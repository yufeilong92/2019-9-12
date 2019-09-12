package com.tsyc.tianshengyoucai.model.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.listener.ItemClickListener;
import com.tsyc.tianshengyoucai.utils.ImageLoader;
import com.tsyc.tianshengyoucai.utils.ImgUtils;
import com.youth.xframe.utils.XDensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/22
 * File description：
 */
public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder> {

    private Context mContext;

    private Integer[] categoryIcons = null;
    private String[] categoryNames = null;
    private ItemClickListener itemClickListener;

    public HomeCategoryAdapter(Integer[] categoryIcons, String[] categoryNames) {
        this.categoryIcons = categoryIcons;
        this.categoryNames = categoryNames;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_home_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ImgUtils.setImage(mContext, categoryIcons[position], holder.mIvIcon);
        holder.mTvName.setText(categoryNames[position]);
        if (categoryNames[0].equals("发布商品")) {
            ViewGroup.LayoutParams layoutParams = holder.mIvIcon.getLayoutParams();
            layoutParams.height = XDensityUtils.dp2px(30);
            layoutParams.width = XDensityUtils.dp2px(30);
        }

        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null)
                itemClickListener.onItemClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return categoryIcons.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTvName;
        private final ImageView mIvIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tv_name);
            mIvIcon = itemView.findViewById(R.id.iv_icon);
        }
    }
}
