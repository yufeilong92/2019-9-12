package com.tsyc.tianshengyoucai.adapter.recruit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.vo.BossJobManagerVo;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/4 10:57
 * @Purpose :
 */
public class BossJobManageAdapter extends RecyclerView.Adapter<BossJobManageAdapter.BossJobHolder> {

    private Context mContext;
    private List<BossJobManagerVo.ResultBean.DataBean> mData;
    private final LayoutInflater mInflater;

    public BossJobManageAdapter(Context mContext, List<BossJobManagerVo.ResultBean.DataBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(BossJobManagerVo.ResultBean.DataBean vo);

    }

    @NonNull
    @Override
    public BossJobHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_boss_job_manager, null);
        return new BossJobHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BossJobHolder holder, int position) {
        BossJobManagerVo.ResultBean.DataBean vo = mData.get(position);
        holder.mTvItemBossJobTitle.setText(vo.getPosition_name());
        holder.mTvItemBossJobMoney.setText(vo.getSalary().getDesc());
        StringBuffer buffer = new StringBuffer();
        if (vo.getWork_experience() != null)
            buffer.append(vo.getWork_experience().getDesc());
        if (vo.getEducation() != null)
            buffer.append(vo.getEducation().getDesc());
        if (vo.getJob_type() != null)
            buffer.append(vo.getJob_type().getDesc());
        holder.mTvItemBossJobTag.setText(buffer.toString());
        holder.mTvItemBossJobTime.setText(vo.getUpdate_time());
        holder.itemView.setOnClickListener(v -> {
            if (listener!=null){
                listener.itemClick(vo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class BossJobHolder extends RecyclerView.ViewHolder {
        public TextView mTvItemBossJobTitle;
        public TextView mTvItemBossJobMoney;
        public TextView mTvItemBossJobTag;
        public TextView mTvItemBossJobTime;

        public BossJobHolder(View itemView) {
            super(itemView);
            this.mTvItemBossJobTitle = (TextView) itemView.findViewById(R.id.tv_item_boss_job_title);
            this.mTvItemBossJobMoney = (TextView) itemView.findViewById(R.id.tv_item_boss_job_money);
            this.mTvItemBossJobTag = (TextView) itemView.findViewById(R.id.tv_item_boss_job_tag);
            this.mTvItemBossJobTime = (TextView) itemView.findViewById(R.id.tv_item_boss_job_time);

        }
    }


}
