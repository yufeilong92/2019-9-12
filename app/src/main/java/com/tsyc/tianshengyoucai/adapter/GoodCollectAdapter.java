package com.tsyc.tianshengyoucai.adapter;

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
import com.tsyc.tianshengyoucai.vo.GoodCollectVo;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/10 10:01
 * @Purpose :货物收藏
 */
public class GoodCollectAdapter extends RecyclerView.Adapter<GoodCollectAdapter.GoodCollectHolder> {

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(int postion,GoodCollectVo.ResultBean vo);

        void cannerClick(int postion,GoodCollectVo.ResultBean vo);
    }


    private Context mContext;
    private List<GoodCollectVo.ResultBean> mData;
    private final LayoutInflater mInflater;

    public GoodCollectAdapter(Context mContext, List<GoodCollectVo.ResultBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public GoodCollectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_good_collect, null);
        return new GoodCollectHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodCollectHolder holder, int position) {
        GoodCollectVo.ResultBean vo = mData.get(position);
        GlideUtil.getSingleton().loadQuadRangleImager(mContext, holder.mIvItemGoodCollect, vo.getGoods_image());
        holder.mTvItemGoodcollectTitle.setText(vo.getGoods_name());
        holder.mTvItemGoodcollectValue.setText("￥" + vo.getLog_price());
        holder.mTvItemGoodcollectCanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.cannerClick(position,vo);
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.itemClick(position,vo);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class GoodCollectHolder extends RecyclerView.ViewHolder {
        public ImageView mIvItemGoodCollect;
        public TextView mTvItemGoodcollectTitle;
        public TextView mTvItemGoodcollectValue;
        public TextView mTvItemGoodcollectCanner;

        public GoodCollectHolder(View itemView) {
            super(itemView);
            this.mIvItemGoodCollect = (ImageView) itemView.findViewById(R.id.iv_item_good_collect);
            this.mTvItemGoodcollectTitle = (TextView) itemView.findViewById(R.id.tv_item_goodcollect_title);
            this.mTvItemGoodcollectValue = (TextView) itemView.findViewById(R.id.tv_item_goodcollect_value);
            this.mTvItemGoodcollectCanner = (TextView) itemView.findViewById(R.id.tv_item_goodcollect_canner);

        }
    }


}
