package com.tsyc.tianshengyoucai.adapter.msg;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.utils.ImageLoader;
import com.tsyc.tianshengyoucai.utils.ImgUtils;
import com.tsyc.tianshengyoucai.vo.HomeDataBeanVo;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/12 11:57
 * @Purpose :消息适配器
 */
public class HomeMsgAdapter extends RecyclerView.Adapter<HomeMsgAdapter.MsgViewHodle> {

    private Context mContext;
    private List<HomeDataBeanVo> mData;
    private final LayoutInflater mInflater;
    private boolean mType;

    public HomeMsgAdapter(Context mContext, List<HomeDataBeanVo> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public interface OnItemClickListener {
        void itemClick(int position, HomeDataBeanVo vo);

        void itemDeleteClick(int postion, HomeDataBeanVo vo);
    }

    public void showDelete(boolean type) {
        this.mType = type;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MsgViewHodle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_home_msg, null);
        return new MsgViewHodle(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MsgViewHodle holder, int position) {
        HomeDataBeanVo vo = mData.get(position);
        holder.mTvItemHomeMsgTitle.setText(vo.getTitle());
        holder.mTvItemHomeMsgContent.setText(vo.getContent());
        holder.mTvItemHomeMsgTime.setText(vo.getCreate_time());
        holder.mTvItemHomeDelete.setVisibility(mType ? View.VISIBLE : View.GONE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.itemClick(position, vo);
                }
            }
        });
        holder.mTvItemHomeDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.itemDeleteClick(position, vo);
                }
            }
        });
        ImageLoader.setImageCircle(mContext, R.mipmap.logo, holder.mIvLogo);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MsgViewHodle extends RecyclerView.ViewHolder {
        public TextView mTvItemHomeMsgTitle;
        public TextView mTvItemHomeMsgContent;
        public TextView mTvItemHomeMsgApp;
        public TextView mTvItemHomeMsgTime;
        public TextView mTvItemHomeDelete;
        public ImageView mIvLogo;

        public MsgViewHodle(View itemView) {
            super(itemView);
            this.mTvItemHomeMsgTitle = (TextView) itemView.findViewById(R.id.tv_item_home_msg_title);
            this.mTvItemHomeMsgContent = (TextView) itemView.findViewById(R.id.tv_item_home_msg_content);
            this.mTvItemHomeMsgApp = (TextView) itemView.findViewById(R.id.tv_item_home_msg_app);
            this.mTvItemHomeMsgTime = (TextView) itemView.findViewById(R.id.tv_item_home_msg_time);
            this.mTvItemHomeDelete = (TextView) itemView.findViewById(R.id.tv_item_home_delete);
            this.mIvLogo = itemView.findViewById(R.id.iv_icon);

        }
    }


}
