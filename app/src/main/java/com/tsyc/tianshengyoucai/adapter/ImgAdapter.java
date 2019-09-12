package com.tsyc.tianshengyoucai.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.utils.GlideUtil;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/12 20:12
 * @Purpose : 评价中图片适配器
 */
public class ImgAdapter extends RecyclerView.Adapter<ImgAdapter.ImageViewHolde> {
    private Context mContext;
    private List<String> mData;
    private final LayoutInflater mInflater;

    public ImgAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(int position, String s);

    }

    @NonNull
    @Override
    public ImageViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_img, null);
        return new ImageViewHolde(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolde holder, int position) {
        String s = mData.get(position);
        GlideUtil.getSingleton().loadQuadRangleImager(mContext, holder.mIvEvlauteImg, s);

        holder.mIvEvlauteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) ;
                {
                    listener.itemClick(position, s);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ImageViewHolde extends RecyclerView.ViewHolder {
        public ImageView mIvEvlauteImg;

        public ImageViewHolde(View itemView) {
            super(itemView);
            this.mIvEvlauteImg = (ImageView) itemView.findViewById(R.id.iv_evlaute_img);


        }
    }

}
