package com.tsyc.tianshengyoucai.model.adapter;

import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.SMGoodsListBean;
import com.tsyc.tianshengyoucai.utils.ImageLoader;
import com.tsyc.tianshengyoucai.utils.NumberUtils;
import com.youth.xframe.utils.log.XLog;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/26
 * File description：商品管理 适配器
 */
public class ShopManageAdapter extends BaseQuickAdapter<SMGoodsListBean.ResultBean, BaseViewHolder> {

    public ShopManageAdapter(@Nullable List<SMGoodsListBean.ResultBean> data) {
        super(R.layout.layout_shop_manage_item, data);
    }

    private boolean isCanDown = true;

    public void isEnableDown(boolean isCanDown) {
        this.isCanDown = isCanDown;
    }

    @Override
    protected void convert(BaseViewHolder holder, SMGoodsListBean.ResultBean resultBean) {

        ImageView iv_shop_image = holder.getView(R.id.iv_shop_image);
        TextView tv_shop_name = holder.getView(R.id.tv_shop_name);
        TextView tv_shop_count = holder.getView(R.id.tv_shop_count);
        TextView tv_shop_price = holder.getView(R.id.tv_shop_price);
        ConstraintLayout ctl_edit = holder.getView(R.id.ctl_edit);
        ConstraintLayout ctl_up = holder.getView(R.id.ctl_up);
        ConstraintLayout ctl_delete = holder.getView(R.id.ctl_delete);
        TextView tvUp = holder.getView(R.id.tv_up);
        TextView tv_delete = holder.getView(R.id.tv_delete);

        String goodsImage = resultBean.getGoods_image();
        String goods_name = resultBean.getGoods_name();
        float goods_price = resultBean.getGoods_price();
        int goods_state = resultBean.getGoods_state();// 1正常 0 下架
        int stock = resultBean.getStock(); // 库存

        ImageLoader.loadCornersImage(mContext, goodsImage, iv_shop_image, 5);
        tv_shop_name.setText(goods_name);
        tv_shop_count.setText(String.valueOf("库存" + stock + "件"));
        tv_shop_price.setText("￥"+NumberUtils.formatNum(goods_price+"")+"元");
        if (goods_state == 1) {
            tvUp.setText("下架");
            ctl_delete.setClickable(false);
            tv_delete.setTextColor(mContext.getResources().getColor(R.color.color_7B8391));
            holder.addOnClickListener(R.id.ctl_edit);
        } else if (goods_state == 0) {
            tvUp.setText("上架");
            holder.addOnClickListener(R.id.ctl_edit, R.id.ctl_delete);
        } else {
            holder.addOnClickListener(R.id.ctl_edit, R.id.ctl_delete);
        }
        ctl_up.setClickable(isCanDown);
        if (!isCanDown) {
            tvUp.setTextColor(mContext.getResources().getColor(R.color.color_7B8391));
        } else {
            holder.addOnClickListener(R.id.ctl_up);
        }


    }

}
