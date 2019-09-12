package com.tsyc.tianshengyoucai.model.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Config;
import com.tsyc.tianshengyoucai.utils.ImageLoader;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/23
 * File description：
 */
public class NewsImageAdapter  extends BaseQuickAdapter<String, BaseViewHolder> {

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(int adapterPosition, String item);

    }

    public NewsImageAdapter(@Nullable List<String> data) {
        super(R.layout.news_image_item, data);
    }

    public NewsImageAdapter() {
        super(R.layout.news_image_item);
    }

    @Override
    protected void convert(BaseViewHolder holder, String item) {
        int adapterPosition = holder.getLayoutPosition();
        ImageView image = holder.getView(R.id.iv_image);

        String imageUrl = item.contains(Config.BASE_URL)?item:Config.BASE_URL+item;
        ImageLoader.loadCornersImage(mContext, imageUrl, image, 5);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.itemClick(adapterPosition,item);
                }
            }
        });
    }
}
