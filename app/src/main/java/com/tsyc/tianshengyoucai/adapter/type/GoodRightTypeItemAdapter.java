package com.tsyc.tianshengyoucai.adapter.type;

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
import com.tsyc.tianshengyoucai.vo.GoodTypeVo;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/12 17:25
 * @Purpose : 分类右侧item 下面适配器
 */
public class GoodRightTypeItemAdapter extends RecyclerView.Adapter<GoodRightTypeItemAdapter.GoodTypeItemViewHolde> {
    private Context mContext;
    private List<GoodTypeVo.ResultBean> mData;
    private final LayoutInflater mInflater;

    public GoodRightTypeItemAdapter(Context mContext, List<GoodTypeVo.ResultBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(int position, GoodTypeVo.ResultBean bean);

    }

    @NonNull
    @Override
    public GoodTypeItemViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.item_right_content, null);
        return new GoodTypeItemViewHolde(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodTypeItemViewHolde holder, int position) {
        GoodTypeVo.ResultBean bean = mData.get(position);
        GlideUtil.getSingleton().loadCilcleImager(mContext,holder.mIvItemGoodItemPicture,bean.getPic());
        holder.mTvItemGoodItemName.setText(bean.getGc_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.itemClick(position,bean);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class GoodTypeItemViewHolde extends RecyclerView.ViewHolder {
        public ImageView mIvItemGoodItemPicture;
        public TextView mTvItemGoodItemName;
        public GoodTypeItemViewHolde(View itemView) {
            super(itemView);
            this.mIvItemGoodItemPicture = (ImageView) itemView.findViewById(R.id.iv_item_good_item_picture);
            this.mTvItemGoodItemName = (TextView) itemView.findViewById(R.id.tv_item_good_item_name);

        }
    }


}
