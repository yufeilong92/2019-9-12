package com.tsyc.tianshengyoucai.model.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.ToolsBean;
import com.tsyc.tianshengyoucai.utils.ImageLoader;
import com.tsyc.tianshengyoucai.utils.ImgUtils;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/27
 * File description：
 */
public class ToolsAdapter extends BaseQuickAdapter<ToolsBean, BaseViewHolder> {

    public ToolsAdapter(@Nullable List<ToolsBean> data) {
        super(R.layout.layout_tools_item, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, ToolsBean toolsBean) {
        ImageView view = holder.getView(R.id.iv_image);
        holder.setText(R.id.tv_name, toolsBean.getToolName());

        ImgUtils.setImage(mContext,toolsBean.getToolImg(), view);
    }
}
