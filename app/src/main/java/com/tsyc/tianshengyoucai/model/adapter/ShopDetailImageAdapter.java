package com.tsyc.tianshengyoucai.model.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.utils.ImageLoader;
import com.youth.xframe.utils.log.XLog;
import com.zhihu.matisse.internal.entity.Item;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/30
 * File description：
 */

public class ShopDetailImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ShopDetailImageAdapter(@Nullable List<String> data) {
        super(R.layout.shop_detail_image_item, data);
    }

    public ShopDetailImageAdapter() {
        super(R.layout.shop_detail_image_item);

    }

    @Override
    protected void convert(BaseViewHolder holder, String item) {

        ImageView image = holder.getView(R.id.iv_image);

        ImageLoader.loadNormal(mContext, item, image);

    }

}
