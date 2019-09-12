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
import com.tsyc.tianshengyoucai.vo.ChatJobListData;
import com.tsyc.tianshengyoucai.vo.ChatListVo;
import com.tsyc.tianshengyoucai.vo.GmSelectBean;
import com.tsyc.tianshengyoucai.vo.JobChatMsgVo;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/10 10:07
 * @Purpose :
 */
public class ChatJobAdapter extends RecyclerView.Adapter<ChatJobAdapter.ChatJobHodler> {
    private Context mContext;
    private List<ChatJobListData> mData;
    private final LayoutInflater mInflater;

    public ChatJobAdapter(Context mContext, List<ChatJobListData> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(String comtent);

    }

    public void add(ChatJobListData text, int position) {
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
        View view = mInflater.inflate(R.layout.item_chat_job, null);
        return new ChatJobHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatJobHodler holder, int position) {
        ChatJobListData data = mData.get(position);
        if (position == 0) {
            holder.mTvChatJob.setText(mTop);
            holder.mTvChatJob.setVisibility(View.VISIBLE);
            holder.mLLjobTop.setVisibility(View.VISIBLE);
            holder.mLlChatChat.setVisibility(View.GONE);
            JobChatMsgVo.ResultBean.HeadCardBean vo = data.getHear();
            if (vo == null) return;
            JobChatMsgVo.ResultBean.HeadCardBean.BossBean boss = vo.getBoss();
            if (boss != null)
                GlideUtil.getSingleton().loadQuadRangleImager(mContext, holder.mIvItemJobTopLogo, boss.getAvatar());
            holder.mTvItemJobTopTitle.setText(vo.getCompany_name());
            holder.mTvItemJobTopMoney.setText(vo.getSalary());
            StringBuffer buffer = new StringBuffer();
            GmSelectBean job_type = vo.getJob_type();
            if (job_type != null) {
                buffer.append(job_type.getDesc());
                buffer.append(" | ");
            }
            buffer.append(vo.getPosition_name());
            buffer.append(" | ");
            holder.mTvItemJobTopContent.setText(buffer.toString());
            JobChatMsgVo.ResultBean.HeadCardBean.AddressBean address = vo.getAddress();
            StringBuffer addres = new StringBuffer();
            if (address != null) {
                addres.append(address.getProvince());
                addres.append(address.getCity());
                addres.append(address.getArea());
            }
            holder.mTvItemJobTopCity.setText(addres.toString());
            return;
        }
        holder.mTvChatJob.setVisibility(View.GONE);
        holder.mLLjobTop.setVisibility(View.GONE);
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

//    private void bindViewData(FlowLayout flowLayout, List<String> list) {
//        if (list == null || list.isEmpty()) return;
//        flowLayout.removeAllViews();
//
//        for (int i = 0; i < list.size(); i++) {
//            String comtent = list.get(i);
//            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_job_type, null);
//            TextView tv = inflate.findViewById(R.id.tv_job_type_content);
//            tv.setText(comtent);
//            flowLayout.addView(inflate);
//        }
//
//    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ChatJobHodler extends RecyclerView.ViewHolder {

        public ImageView mIvItemJobTopLogo;
        public TextView mTvItemJobTopTitle;
        public TextView mTvItemJobTopMoney;
        public TextView mTvItemJobTopContent;
        public TextView mTvItemJobTopCity;
        public ImageView mIvChatLiftLogo;
        public TextView mTvChatLiftName;
        public ImageView mIvChatLift;
        public ImageView mIvChatLiftImg;
        public TextView mTvChatLift;
        public LinearLayout mLlLiftChat;
        public ImageView mIvChatRightImg;
        public TextView mTvChatRight;
        public TextView mTvChatJob;
        public ImageView mIvChatRight;
        public ImageView mIvChatRightLogo;
        public LinearLayout mLlRightChat;
        public LinearLayout mLlChatChat;
        public LinearLayout mLLjobTop;


        public ChatJobHodler(View itemView) {
            super(itemView);
            this.mIvItemJobTopLogo = (ImageView) itemView.findViewById(R.id.iv_item_job_top_logo);
            this.mTvItemJobTopTitle = (TextView) itemView.findViewById(R.id.tv_item_job_top_title);
            this.mTvItemJobTopCity = (TextView) itemView.findViewById(R.id.tv_item_job_top_city);
            this.mTvItemJobTopMoney = (TextView) itemView.findViewById(R.id.tv_item_job_top_money);
            this.mTvItemJobTopContent = (TextView) itemView.findViewById(R.id.tv_item_job_top_content);
            this.mIvChatLiftLogo = (ImageView) itemView.findViewById(R.id.iv_chat_lift_logo);
            this.mTvChatLiftName = (TextView) itemView.findViewById(R.id.tv_chat_lift_name);
            this.mIvChatLift = (ImageView) itemView.findViewById(R.id.iv_chat_lift);
            this.mIvChatLiftImg = (ImageView) itemView.findViewById(R.id.iv_chat_lift_img);
            this.mTvChatLift = (TextView) itemView.findViewById(R.id.tv_chat_lift);
            this.mLlLiftChat = (LinearLayout) itemView.findViewById(R.id.ll_lift_chat);
            this.mIvChatRightImg = (ImageView) itemView.findViewById(R.id.iv_chat_right_img);
            this.mTvChatRight = (TextView) itemView.findViewById(R.id.tv_chat_right);
            this.mTvChatJob = (TextView) itemView.findViewById(R.id.tv_chat_job);
            this.mIvChatRight = (ImageView) itemView.findViewById(R.id.iv_chat_right);
            this.mIvChatRightLogo = (ImageView) itemView.findViewById(R.id.iv_chat_right_logo);
            this.mLlRightChat = (LinearLayout) itemView.findViewById(R.id.ll_right_chat);
            this.mLlChatChat = (LinearLayout) itemView.findViewById(R.id.ll_chat_chat);
            this.mLLjobTop = (LinearLayout) itemView.findViewById(R.id.ll_job_top);
        }
    }


}
