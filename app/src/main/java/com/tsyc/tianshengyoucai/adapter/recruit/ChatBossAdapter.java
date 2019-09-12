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
import com.tsyc.tianshengyoucai.view.FlowLayout;
import com.tsyc.tianshengyoucai.vo.BossChatVo;
import com.tsyc.tianshengyoucai.vo.ChatBossListData;
import com.tsyc.tianshengyoucai.vo.ChatListVo;
import com.tsyc.tianshengyoucai.vo.GmSelectBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/10 10:07
 * @Purpose :
 */
public class ChatBossAdapter extends RecyclerView.Adapter<ChatBossAdapter.ChatJobHodler> {
    private Context mContext;
    private List<ChatBossListData> mData;
    private final LayoutInflater mInflater;

    public ChatBossAdapter(Context mContext, List<ChatBossListData> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void refresh(ArrayList mArray) {
        this.mData = mArray;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void itemClick(String comtent);

    }

    public void add(ChatBossListData text, int position) {
        mData.add(position, text);
        notifyItemInserted(position);
    }


    private String mTop = "下拉获取历史记录";

    public void changerText(String com) {
        mTop = com;
        notifyItemChanged(0);
    }

    @NonNull
    @Override
    public ChatJobHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_chat_boss, null);
        return new ChatJobHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatJobHodler holder, int position) {
        ChatBossListData data = mData.get(position);
        if (position == 0) {
            holder.mTvChatBoss.setText(mTop);
            holder.mTvChatBoss.setVisibility(View.VISIBLE);
            holder.mLlChatJob.setVisibility(View.VISIBLE);
            holder.mLlChatChat.setVisibility(View.GONE);
            BossChatVo.ResultBean.HeadCardBean vo = data.getHear();
            if (vo == null) return;
            GmSelectBean expected_position = vo.getExpected_position();
            if (expected_position != null)
                holder.mTvBossTitle.setText(expected_position.getDesc());
            GmSelectBean salary = vo.getExpected_salary();
            if (salary != null)
                holder.mTvBossValues.setText(salary.getDesc());
            List<String> items = new ArrayList<>();
            GmSelectBean city = vo.getWork_city();
            if (city != null)
                items.add(city.getDesc());
            GmSelectBean education = vo.getHighest_education();
            if (education != null)
                items.add(education.getDesc());
            bindViewData(holder.mFlBossType, items);

            GlideUtil.getSingleton().loadQuadRangleImager(mContext, holder.mIvBossLogo, vo.getAvatar());
            holder.mTvBossUser.setText(vo.getUsername() + "·" + vo.getSex().getDesc());
            holder.mTvBossTime.setText(data.getTime());
            return;
        }
        holder.mTvChatBoss.setVisibility(View.GONE);
        holder.mLlChatJob.setVisibility(View.GONE);
        holder.mLlChatChat.setVisibility(View.VISIBLE);
        ChatListVo vo = data.getmData();
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

    private void bindViewData(FlowLayout flowLayout, List<String> list) {
        if (list == null || list.isEmpty()) return;
        flowLayout.removeAllViews();

        for (int i = 0; i < list.size(); i++) {
            String comtent = list.get(i);
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_job_type, null);
            TextView tv = inflate.findViewById(R.id.tv_job_type_content);
            tv.setText(comtent);
            flowLayout.addView(inflate);
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ChatJobHodler extends RecyclerView.ViewHolder {
        public TextView mTvBossTitle;
        public TextView mTvBossValues;
        public FlowLayout mFlBossType;
        public ImageView mIvBossLogo;
        public TextView mTvBossUser;
        public TextView mTvJobBossDelete;
        public TextView mTvBossTime;
        public LinearLayout mLlChatJob;
        public ImageView mIvChatLiftLogo;
        public TextView mTvChatLiftName;
        public ImageView mIvChatLift;
        public ImageView mIvChatLiftImg;
        public TextView mTvChatLift;
        public LinearLayout mLlLiftChat;
        public ImageView mIvChatRightImg;
        public TextView mTvChatRight;
        public TextView mTvChatBoss;
        public ImageView mIvChatRight;
        public ImageView mIvChatRightLogo;
        public LinearLayout mLlRightChat;
        public LinearLayout mLlChatChat;


        public ChatJobHodler(View itemView) {
            super(itemView);
            this.mTvBossTitle = (TextView) itemView.findViewById(R.id.tv_boss_title);
            this.mTvBossValues = (TextView) itemView.findViewById(R.id.tv_boss_values);
            this.mFlBossType = (FlowLayout) itemView.findViewById(R.id.fl_boss_type);
            this.mIvBossLogo = (ImageView) itemView.findViewById(R.id.iv_boss_logo);
            this.mTvBossUser = (TextView) itemView.findViewById(R.id.tv_boss_user);
            this.mTvJobBossDelete = (TextView) itemView.findViewById(R.id.tv_job_boss_delete);
            this.mTvBossTime = (TextView) itemView.findViewById(R.id.tv_boss_time);
            this.mLlChatJob = (LinearLayout) itemView.findViewById(R.id.ll_chat_job);
            this.mIvChatLiftLogo = (ImageView) itemView.findViewById(R.id.iv_chat_lift_logo);
            this.mTvChatLiftName = (TextView) itemView.findViewById(R.id.tv_chat_lift_name);
            this.mIvChatLift = (ImageView) itemView.findViewById(R.id.iv_chat_lift);
            this.mIvChatLiftImg = (ImageView) itemView.findViewById(R.id.iv_chat_lift_img);
            this.mTvChatLift = (TextView) itemView.findViewById(R.id.tv_chat_lift);
            this.mLlLiftChat = (LinearLayout) itemView.findViewById(R.id.ll_lift_chat);
            this.mIvChatRightImg = (ImageView) itemView.findViewById(R.id.iv_chat_right_img);
            this.mTvChatRight = (TextView) itemView.findViewById(R.id.tv_chat_right);
            this.mTvChatBoss = (TextView) itemView.findViewById(R.id.tv_chat_boss);
            this.mIvChatRight = (ImageView) itemView.findViewById(R.id.iv_chat_right);
            this.mIvChatRightLogo = (ImageView) itemView.findViewById(R.id.iv_chat_right_logo);
            this.mLlRightChat = (LinearLayout) itemView.findViewById(R.id.ll_right_chat);
            this.mLlChatChat = (LinearLayout) itemView.findViewById(R.id.ll_chat_chat);
        }
    }


}
