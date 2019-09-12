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
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.view.FlowLayout;
import com.tsyc.tianshengyoucai.vo.BossChatList;
import com.tsyc.tianshengyoucai.vo.BossChatVo;
import com.tsyc.tianshengyoucai.vo.ChatListVo;
import com.tsyc.tianshengyoucai.vo.GmSelectBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/9 17:56
 * @Purpose :
 */
public class BossChatAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private BossChatList mData;
    private final LayoutInflater mInflater;
    private final int MAIN_BANNER = 1001;//
    private final int MAIN_WARE = 1002;//
    private int MAIN_COMMON = MAIN_BANNER;

    public BossChatAdapter(Context mContext, BossChatList mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case MAIN_BANNER:
                return new ChatJobHolder(mInflater.inflate(R.layout.item_chat_top, null));
            case MAIN_WARE:
                return new ChatViewHolder(mInflater.inflate(R.layout.item_chat_view, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ChatJobHolder) {
            ChatJobHolder job = (ChatJobHolder) holder;
            initJob(job);
        } else if (holder instanceof ChatViewHolder) {
            ChatViewHolder view = (ChatViewHolder) holder;
            initView(view);
        }
    }


    private void initJob(ChatJobHolder job) {
        BossChatVo.ResultBean.HeadCardBean vo = mData.getHear();
        if (vo == null) return;
        GmSelectBean position = vo.getExpected_position();
        if (position != null)
            job.mTvBossTitle.setText(position.getDesc());
        GmSelectBean salary = vo.getExpected_salary();
        if (salary != null)
            job.mTvBossValues.setText(salary.getDesc());
        List<String> items = new ArrayList<>();
        GmSelectBean city = vo.getWork_city();
        if (city != null)
            items.add(city.getDesc());
        GmSelectBean education = vo.getHighest_education();
        if (education != null)
            items.add(education.getDesc());
        bindViewData(job.mFlBossType, items);
        GlideUtil.getSingleton().loadQuadRangleImager(mContext, job.mIvBossLogo, vo.getAvatar());
        job.mTvBossUser.setText(vo.getUsername() + "·" + vo.getSex().getDesc());
        job.mTvBossTime.setText(mData.getTime());

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

    private void initView(ChatViewHolder view) {
        List<ChatListVo> chatListVos = mData.getmData();
        if (chatListVos == null || chatListVos.isEmpty()) return;
        ChatAdapter mChatAdapter = new ChatAdapter(mContext, chatListVos);
        RecyclerUtil.setGridManage(mContext, view.mGmRlvContent, mChatAdapter);
        mChatAdapter.setListener(new ChatAdapter.OnItemClickListener() {
            @Override
            public void itemClick(String content) {

            }
        });


    }


    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                MAIN_COMMON = MAIN_BANNER;
                break;
            case 1:
                MAIN_COMMON = MAIN_WARE;
                break;
        }
        return MAIN_COMMON;

    }

    public void refreshData(BossChatList mChatList) {
        this.mData = mChatList;
        notifyDataSetChanged();

    }

    public interface OnItemClickListener {
        void itemClick();

    }

    public class ChatJobHolder extends RecyclerView.ViewHolder {
        public TextView mTvBossTitle;
        public TextView mTvBossValues;
        public FlowLayout mFlBossType;
        public ImageView mIvBossLogo;
        public TextView mTvBossUser;
        public TextView mTvJobBossDelete;
        public TextView mTvBossTime;
        public LinearLayout mLlChatJob;

        public ChatJobHolder(View itemView) {
            super(itemView);
            this.mTvBossTitle = (TextView) itemView.findViewById(R.id.tv_boss_title);
            this.mTvBossValues = (TextView) itemView.findViewById(R.id.tv_boss_values);
            this.mFlBossType = (FlowLayout) itemView.findViewById(R.id.fl_boss_type);
            this.mIvBossLogo = (ImageView) itemView.findViewById(R.id.iv_boss_logo);
            this.mTvBossUser = (TextView) itemView.findViewById(R.id.tv_boss_user);
            this.mTvJobBossDelete = (TextView) itemView.findViewById(R.id.tv_job_boss_delete);
            this.mTvBossTime = (TextView) itemView.findViewById(R.id.tv_boss_time);
            this.mLlChatJob = (LinearLayout) itemView.findViewById(R.id.ll_chat_job);
        }
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {
        public RecyclerView mGmRlvContent;

        public ChatViewHolder(View itemView) {
            super(itemView);
            this.mGmRlvContent = (RecyclerView) itemView.findViewById(R.id.rlv_chat_item_content);

        }
    }

}
