package com.tsyc.tianshengyoucai.model.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.utils.ImageLoader;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/9/7
 * File description：
 */
public class RelShowDragImageAdapter extends BaseItemDraggableAdapter<String, BaseViewHolder> {


    public RelShowDragImageAdapter(@Nullable List<String> data) {
        super(R.layout.layout_image_item, data);
    }


    @Override
    protected void convert(BaseViewHolder holder, String item) {

        ImageView image = holder.getView(R.id.iv_image);
        ImageView ivDelete = holder.getView(R.id.iv_delete);

        ImageLoader.loadNormal(mContext, item, image);
        holder.addOnClickListener(R.id.iv_delete);

    }
}


