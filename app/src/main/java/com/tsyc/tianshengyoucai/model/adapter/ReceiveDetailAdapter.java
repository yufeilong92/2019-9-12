package com.tsyc.tianshengyoucai.model.adapter;

import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.EvaluateManageBean;
import com.tsyc.tianshengyoucai.model.bean.ReceiveBagCouponBean;
import com.tsyc.tianshengyoucai.utils.ImageLoader;
import com.tsyc.tianshengyoucai.utils.ImgUtils;
import com.tsyc.tianshengyoucai.view.StarBar;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/26
 * File description：评论管理 适配器
 */
public class ReceiveDetailAdapter extends BaseQuickAdapter<ReceiveBagCouponBean.ResultBean, BaseViewHolder> {

    public ReceiveDetailAdapter(@Nullable List<ReceiveBagCouponBean.ResultBean> data) {
        super(R.layout.recevie_detail_item, data);
    }

    public ReceiveDetailAdapter() {
        super(R.layout.recevie_detail_item);
    }

    @Override
    protected void convert(BaseViewHolder holder, ReceiveBagCouponBean.ResultBean resultBean) {

        //评论
        ImageView iv_image = holder.getView(R.id.iv_image);
        TextView tv_name = holder.getView(R.id.tv_name);
        TextView tv_date = holder.getView(R.id.tv_date);


        String avatar = resultBean.getAvatar();
        String date = resultBean.getDate();
        String name = resultBean.getName();

        ImgUtils.setImageCircle(mContext,avatar,iv_image);
        tv_date.setText("领取时间："+date);
        tv_name.setText(""+name);

    }

}
