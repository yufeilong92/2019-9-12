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
import com.tsyc.tianshengyoucai.utils.ImageLoader;
import com.tsyc.tianshengyoucai.view.StarBar;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/26
 * File description：评论管理 适配器
 */
public class EvaluateManageAdapter extends BaseQuickAdapter<EvaluateManageBean.ResultBean, BaseViewHolder> {

    public EvaluateManageAdapter(@Nullable List<EvaluateManageBean.ResultBean> data) {
        super(R.layout.layout_evaluate_manage_item, data);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(int adapterPosition, List<String> eval_image);

    }

    public EvaluateManageAdapter() {
        super(R.layout.layout_evaluate_manage_item);
    }

    @Override
    protected void convert(BaseViewHolder holder, EvaluateManageBean.ResultBean resultBean) {

        //评论
        ImageView iv_image = holder.getView(R.id.iv_image);
        TextView tv_name = holder.getView(R.id.tv_name);
        StarBar starBar = holder.getView(R.id.sb_starBar);
        TextView tv_evaluate_spec = holder.getView(R.id.tv_evaluate_spec);
        TextView tv_evaluate_content = holder.getView(R.id.tv_evaluate_content);
        RecyclerView rv_evaluate_img = holder.getView(R.id.rv_evaluate_img);

        //商品
        ImageView iv_shop_image = holder.getView(R.id.iv_shop_image);
        TextView tv_shop_name = holder.getView(R.id.tv_shop_name);
        TextView tv_shop_spec = holder.getView(R.id.tv_shop_spec);
        TextView tv_shop_price = holder.getView(R.id.tv_shop_price);
        //回复
        TextView tv_evaluate_reply = holder.getView(R.id.tv_evaluate_reply); // 回复按钮
        ConstraintLayout ctl_shop_reply = holder.getView(R.id.ctl_shop_reply); // 回复内容
        TextView tv_shop_reply = holder.getView(R.id.tv_shop_reply); // 回复内容


        String user_logo = resultBean.getUser_logo();
        String user_name = resultBean.getUser_name();
        String description = resultBean.getDescription();
        String eval_content = resultBean.getGeval_content();
        String star = resultBean.getStar();

        ImageLoader.loadCornersImage(mContext, user_logo, iv_image, 5);
        tv_name.setText(user_name);
        tv_evaluate_spec.setText(description);
        tv_evaluate_content.setText(eval_content);
        starBar.setStarCount(Integer.valueOf(star));
        starBar.setStarMark(Integer.valueOf(star));

        List<String> eval_image = resultBean.getGeval_image();
        if (null != eval_image && eval_image.size() > 0) {

            rv_evaluate_img.setLayoutManager(new GridLayoutManager(mContext, 3));
            rv_evaluate_img.setHasFixedSize(true);
            EvaluateImageAdapter adapter = new EvaluateImageAdapter(eval_image);
            rv_evaluate_img.setAdapter(adapter);
            adapter.setListener(new EvaluateImageAdapter.OnItemClickListener() {
                @Override
                public void itemClick(int adapterPosition, String item) {
                   if (listener!=null){
                       listener.itemClick(adapterPosition,eval_image);
                   }
                }
            });
        } else {
            rv_evaluate_img.setVisibility(View.GONE);
        }

        EvaluateManageBean.ResultBean.GoodsInfoBean goods_info = resultBean.getGoods_info();
        String goodsImage = goods_info.getGoods_image();
        String goods_name = goods_info.getGoods_name();
        String goods_price = goods_info.getGoods_price();
        String goods_description = goods_info.getGoods_description();
        ImageLoader.loadCornersImage(mContext, goodsImage, iv_shop_image, 5);

        tv_shop_name.setText(goods_name);
        tv_shop_price.setText("￥" + String.valueOf(goods_price == null ? 0 : goods_price));
        tv_shop_spec.setText(goods_description);


        String reply = resultBean.getReply();
        if (!TextUtils.isEmpty(reply)) {
            tv_evaluate_reply.setVisibility(View.GONE);
            ctl_shop_reply.setVisibility(View.VISIBLE);
            tv_shop_reply.setText(reply);
        } else { // 显示回复
            ctl_shop_reply.setVisibility(View.GONE);
            tv_evaluate_reply.setVisibility(View.VISIBLE);
        }
        holder.addOnClickListener(R.id.tv_evaluate_reply);


    }

}
