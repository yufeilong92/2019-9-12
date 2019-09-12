package com.tsyc.tianshengyoucai.adapter.recruit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.tsyc.tianshengyoucai.vo.ChatListVo;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/7 15:43
 * @Purpose :
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    private Context mContext;
    private List<ChatListVo> mData;
    private final LayoutInflater mInflater;

    public ChatAdapter(Context mContext, List<ChatListVo> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(String content);

    }

    private String mTop = "下拉获取历史记录";

    public void changerText(String com) {
        mTop = com;
        notifyItemChanged(0);
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_chat, null);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        ChatListVo vo = mData.get(position);
//        if (position == 0) {
//            holder.mTvChatService.setVisibility(View.VISIBLE);
//            holder.mTvChatService.setText(mTop);
//        }
//        holder.mTvChatService.setVisibility(View.GONE);
        if (vo == null) return;
        if (vo.isUserInfom()) {
            holder.mLlRightChat.setVisibility(View.VISIBLE);
            holder.mLlLiftChat.setVisibility(View.GONE);
            GlideUtil.getSingleton().loadQuadRangleImager(mContext, holder.mIvChatRightLogo, vo.getLogo());
            if (vo.getType() == 1) {
                holder.mTvChatRight.setText(vo.getContent());
                holder.mIvChatRightImg.setVisibility(View.GONE);
                holder.mTvChatRight.setVisibility(View.VISIBLE);
                holder.mIvChatRight.setVisibility(View.VISIBLE);
            } else {
                holder.mIvChatRight.setVisibility(View.INVISIBLE);
                holder.mIvChatRightImg.setVisibility(View.VISIBLE);
                holder.mTvChatRight.setVisibility(View.GONE);
                GlideUtil.getSingleton().loadQuadRangleImager(mContext, holder.mIvChatRightImg, vo.getContent());
            }
        } else {
            holder.mLlLiftChat.setVisibility(View.VISIBLE);
            holder.mLlRightChat.setVisibility(View.GONE);
            GlideUtil.getSingleton().loadQuadRangleImager(mContext, holder.mIvChatLiftLogo, vo.getLogo());
            holder.mTvChatLiftName.setText(vo.getName());
            holder.mTvChatLift.setText(vo.getContent());
            if (vo.getType() == 1) {
                holder.mTvChatLift.setText(vo.getContent());
                holder.mIvChatLiftImg.setVisibility(View.GONE);
                holder.mIvChatLift.setVisibility(View.VISIBLE);
            } else {
                holder.mIvChatLift.setVisibility(View.INVISIBLE);
                holder.mTvChatLift.setVisibility(View.GONE);
                holder.mIvChatLiftImg.setVisibility(View.VISIBLE);
                GlideUtil.getSingleton().loadQuadRangleImager(mContext, holder.mIvChatLiftImg, vo.getContent());
            }
        }

        holder.mIvChatLiftImg.setOnClickListener(v -> {
            if (listener != null) {
                listener.itemClick(vo.getContent());
            }
        });
        holder.mIvChatRightImg.setOnClickListener(v -> {
            if (listener != null) {
                listener.itemClick(vo.getContent());
            }
        });
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {
        public ImageView mIvChatLiftLogo;
        public TextView mTvChatLiftName;
        public ImageView mIvChatLift;
        public ImageView mIvChatLiftImg;
        public TextView mTvChatLift;
        public LinearLayout mLlLiftChat;
        public ImageView mIvChatRightImg;
        public TextView mTvChatRight;
        public TextView mTvChatService;
        public ImageView mIvChatRight;
        public ImageView mIvChatRightLogo;
        public LinearLayout mLlRightChat;

        public ChatViewHolder(View itemView) {
            super(itemView);
            this.mIvChatLiftLogo = (ImageView) itemView.findViewById(R.id.iv_chat_lift_logo);
            this.mTvChatLiftName = (TextView) itemView.findViewById(R.id.tv_chat_lift_name);
            this.mIvChatLift = (ImageView) itemView.findViewById(R.id.iv_chat_lift);
            this.mIvChatLiftImg = (ImageView) itemView.findViewById(R.id.iv_chat_lift_img);
            this.mTvChatLift = (TextView) itemView.findViewById(R.id.tv_chat_lift);
            this.mLlLiftChat = (LinearLayout) itemView.findViewById(R.id.ll_lift_chat);
            this.mIvChatRightImg = (ImageView) itemView.findViewById(R.id.iv_chat_right_img);
            this.mTvChatRight = (TextView) itemView.findViewById(R.id.tv_chat_right);
            this.mTvChatService = (TextView) itemView.findViewById(R.id.tv_chat_servie);
            this.mIvChatRight = (ImageView) itemView.findViewById(R.id.iv_chat_right);
            this.mIvChatRightLogo = (ImageView) itemView.findViewById(R.id.iv_chat_right_logo);
            this.mLlRightChat = (LinearLayout) itemView.findViewById(R.id.ll_right_chat);
        }
    }


}
