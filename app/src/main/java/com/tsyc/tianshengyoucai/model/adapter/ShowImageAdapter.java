package com.tsyc.tianshengyoucai.model.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.utils.ImageLoader;
import com.youth.xframe.utils.log.XLog;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/30
 * File description：
 */

public class ShowImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ShowImageAdapter(@Nullable List<String> data) {
        super(R.layout.layout_image_item, data);
    }

    public ShowImageAdapter() {
        super(R.layout.layout_image_item);

    }

    @Override
    protected void convert(BaseViewHolder holder, String item) {

        ImageView image = holder.getView(R.id.iv_image);
        ImageView ivDelete = holder.getView(R.id.iv_delete);

        ImageLoader.loadNormal(mContext, item, image);
        holder.addOnClickListener(R.id.iv_delete);

    }


   /* @Override
    public int getItemCount() {
        if (data != null && data.size() > 3) {
            return 3;
        }
        return data == null ? 0 : data.size();
    }*/
}
