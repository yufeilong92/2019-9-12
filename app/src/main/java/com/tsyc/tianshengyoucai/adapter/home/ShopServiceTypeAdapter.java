package com.tsyc.tianshengyoucai.adapter.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.tsyc.tianshengyoucai.vo.ShopServiceVo;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/15 09:29
 * @Purpose :
 */
public class ShopServiceTypeAdapter extends RecyclerView.Adapter<ShopServiceTypeAdapter.TypeViewHolde> {

    private Context mContext;
    private List<ShopServiceVo.ResultBean.TypeListBean> mData;
    private final LayoutInflater mInflater;

    public ShopServiceTypeAdapter(Context mContext, List<ShopServiceVo.ResultBean.TypeListBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(int position, ShopServiceVo.ResultBean.TypeListBean vo);

    }

    @NonNull
    @Override
    public TypeViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_type_shopservice, null);
        return new TypeViewHolde(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeViewHolde holder, int position) {
        ShopServiceVo.ResultBean.TypeListBean vo = mData.get(position);
        holder.mTvShopServiceName.setText(vo.getSc_name());
        switch (vo.getSc_sort()) {
            case -100:
            case -101:
            case -102:
                holder.mIvShopServiceType.setImageResource(vo.getLocal());
                break;
            default:
                GlideUtil.getSingleton().loadQuadRangleImager(mContext, holder.mIvShopServiceType, vo.getSc_icon());
                break;
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.itemClick(position, vo);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class TypeViewHolde extends RecyclerView.ViewHolder {
        public ImageView mIvShopServiceType;
        public TextView mTvShopServiceName;

        public TypeViewHolde(View itemView) {
            super(itemView);
            this.mIvShopServiceType = (ImageView) itemView.findViewById(R.id.iv_shop_service_type);
            this.mTvShopServiceName = (TextView) itemView.findViewById(R.id.tv_shop_service_name);
        }
    }


}
