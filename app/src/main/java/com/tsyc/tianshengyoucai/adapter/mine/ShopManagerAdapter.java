package com.tsyc.tianshengyoucai.adapter.mine;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.SMGoodsListBean;
import com.tsyc.tianshengyoucai.utils.ImageLoader;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/21 11:56
 * @Purpose :
 */
public class ShopManagerAdapter extends RecyclerView.Adapter<ShopManagerAdapter.ShopManagViewHodler> {
    private Context mContext;
    private List<SMGoodsListBean.ResultBean> mData;
    private final LayoutInflater mInflater;
    private boolean isCanDown = true;

    public ShopManagerAdapter(Context mContext, List<SMGoodsListBean.ResultBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        /**
         * @param position
         * @param vo
         * @param i        1编辑 2 下架 ，3 删除
         */
        void itemClick(int position, SMGoodsListBean.ResultBean vo, int i);

    }

    @NonNull
    @Override
    public ShopManagViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_shop_manage_item, null);
        return new ShopManagViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopManagViewHodler holder, int position) {
        SMGoodsListBean.ResultBean vo = mData.get(position);
        String goodsImage = vo.getGoods_image();
        String goods_name = vo.getGoods_name();
        float goods_price = vo.getGoods_price();
        int goods_state = vo.getGoods_state();// 1正常 0 下架
        int stock = vo.getStock(); // 库存

        ImageLoader.loadCornersImage(mContext, goodsImage, holder.mIvShopImage, 5);
        holder.mTvShopName.setText(goods_name);
        holder.mTvShopCount.setText(String.valueOf("库存" + stock + "件"));
        holder.mTvShopPrice.setText(String.valueOf(goods_price));
        if (goods_state == 1) {
            holder.mTvUp.setText("下架");
            holder.mCtlDelete.setClickable(false);
            holder.mTvDelete.setTextColor(mContext.getResources().getColor(R.color.color_7B8391));
        } else if (goods_state == 0) {
            holder.mTvUp.setText("上架");
        }
        holder.mCtlUp.setClickable(isCanDown);
        if (!isCanDown) {
            holder.mTvUp.setTextColor(mContext.getResources().getColor(R.color.color_7B8391));
        }
        holder.mCtlEdit.setOnClickListener(new View.OnClickListener() {//编辑
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.itemClick(position, vo, 1);
                }
            }
        });
        holder.mCtlDelete.setOnClickListener(new View.OnClickListener() {//删除
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.itemClick(position, vo, 3);
                }
            }
        });
        holder.mCtlUp.setOnClickListener(new View.OnClickListener() {//上架
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.itemClick(position, vo, 2);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ShopManagViewHodler extends RecyclerView.ViewHolder {
        public ImageView mIvShopImage;
        public TextView mTvShopName;
        public TextView mTvShopCount;
        public TextView mTvShopPrice;
        public ConstraintLayout mCtlEdit;
        public TextView mTvUp;
        public ConstraintLayout mCtlUp;
        public TextView mTvDelete;
        public ConstraintLayout mCtlDelete;

        public ShopManagViewHodler(View itemView) {
            super(itemView);
            this.mIvShopImage = (ImageView) itemView.findViewById(R.id.iv_shop_image);
            this.mTvShopName = (TextView) itemView.findViewById(R.id.tv_shop_name);
            this.mTvShopCount = (TextView) itemView.findViewById(R.id.tv_shop_count);
            this.mTvShopPrice = (TextView) itemView.findViewById(R.id.tv_shop_price);
            this.mCtlEdit = (ConstraintLayout) itemView.findViewById(R.id.ctl_edit);
            this.mTvUp = (TextView) itemView.findViewById(R.id.tv_up);
            this.mCtlUp = (ConstraintLayout) itemView.findViewById(R.id.ctl_up);
            this.mTvDelete = (TextView) itemView.findViewById(R.id.tv_delete);
            this.mCtlDelete = (ConstraintLayout) itemView.findViewById(R.id.ctl_delete);
        }
    }


}
