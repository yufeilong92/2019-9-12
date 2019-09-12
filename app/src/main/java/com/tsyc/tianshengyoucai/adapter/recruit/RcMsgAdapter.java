package com.tsyc.tianshengyoucai.adapter.recruit;

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
import com.tsyc.tianshengyoucai.vo.GmSelectBean;
import com.tsyc.tianshengyoucai.vo.JobChatListVo;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/23 15:14
 * @Purpose :招聘消息
 */
public class RcMsgAdapter extends RecyclerView.Adapter<RcMsgAdapter.RcMagViewHolder> {

    private Context mContext;
    private List<JobChatListVo.ResultBean.DataBean> mData;
    private final LayoutInflater mInflater;
    private boolean mIsBoss;

    public RcMsgAdapter(Context mContext, List<JobChatListVo.ResultBean.DataBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(JobChatListVo.ResultBean.DataBean vo);

    }

    public void isBoss(boolean isBoss) {
        this.mIsBoss = isBoss;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RcMagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_rc_msg, null);
        return new RcMagViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RcMagViewHolder holder, int position) {
        JobChatListVo.ResultBean.DataBean vo = mData.get(position);
        if (mIsBoss) {
            JobChatListVo.ResultBean.DataBean.CvBean cv = vo.getCv();
            if (cv != null){
                GlideUtil.getSingleton().loadQuadRangleImager(mContext, holder.mIvItemRcMsgHear,
                        cv.getAvatar());
                GmSelectBean bean = cv.getExpected_position();
                holder.mTvItemRcMsgTitle.setText(cv.getUsername() + "/" + bean.getDesc());
                holder.mTvItemRcMsgContent.setText(vo.getLast_record());
            }

        } else {
            JobChatListVo.ResultBean.DataBean.BossBean boss = vo.getBoss();
            if (boss != null)
                GlideUtil.getSingleton().loadQuadRangleImager(mContext, holder.mIvItemRcMsgHear,
                        boss.getAvatar());
            JobChatListVo.ResultBean.DataBean.CompanyBean company = vo.getCompany();
            holder.mTvItemRcMsgTitle.setText(company.getFull_name());
            holder.mTvItemRcMsgContent.setText(vo.getLast_record());
        }
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.itemClick(vo);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class RcMagViewHolder extends RecyclerView.ViewHolder {
        public ImageView mIvItemRcMsgHear;
        public TextView mTvItemRcMsgTitle;
        public TextView mTvItemRcMsgTime;
        public TextView mTvItemRcMsgContent;

        public RcMagViewHolder(View itemView) {
            super(itemView);
            this.mIvItemRcMsgHear = (ImageView) itemView.findViewById(R.id.iv_item_rc_msg_hear);
            this.mTvItemRcMsgTitle = (TextView) itemView.findViewById(R.id.tv_item_rc_msg_title);
            this.mTvItemRcMsgTime = (TextView) itemView.findViewById(R.id.tv_item_rc_msg_time);
            this.mTvItemRcMsgContent = (TextView) itemView.findViewById(R.id.tv_item_rc_msg_content);

        }
    }


}
