package com.tsyc.tianshengyoucai.adapter.recruit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.vo.JobSelectIndox;
import com.tsyc.tianshengyoucai.vo.JobSelectItemVo;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/20 11:37
 * @Purpose :职位列表
 */
public class JobListAdapter extends RecyclerView.Adapter<JobListAdapter.JobViewHolder> {
    private Context mContext;
    private List<JobSelectItemVo> mData;
    private final LayoutInflater mInflater;
    private int mType;
    private JobSelectIndox mJobSelectl;

    public JobListAdapter(Context mContext, List<JobSelectItemVo> mData, int i) {
        this.mContext = mContext;
        this.mData = mData;
        this.mType = i;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(int position, JobSelectItemVo vo);

    }

    public void refreshSelectVo(JobSelectIndox indox) {
        this.mJobSelectl = indox;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_job_list, null);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        JobSelectItemVo vo = mData.get(position);
        switch (mType) {
            case 1:
                holder.mIvItemJobGo.setVisibility(View.VISIBLE);
                if (mJobSelectl != null && mJobSelectl.getSelectVo() != null) {

                    holder.mTvItemJobListContent.setTextColor(mContext.getResources().getColor(
                            mJobSelectl.getTypeone() == vo.getId() ?
                                    R.color.tab_color : R.color.color_444A53));
                }
                break;
            case 2:
                if (vo.getSumItems() == null || vo.getSumItems().isEmpty()) {
                    holder.mIvItemJobGo.setVisibility(View.GONE);
                } else
                    holder.mIvItemJobGo.setVisibility(View.VISIBLE);
                holder.mTvItemJobListContent.setGravity(Gravity.CENTER);
                if (mJobSelectl != null && mJobSelectl.getSelectVo() != null) {
                    holder.mTvItemJobListContent.setTextColor(mContext.getResources().getColor(
                            mJobSelectl.getTypetwo() == vo.getId() ?
                                    R.color.tab_color : R.color.color_444A53));
                }
                break;
            case 3:
                holder.mTvItemJobListContent.setGravity(Gravity.CENTER);
                holder.mIvItemJobGo.setVisibility(View.GONE);
                if (mJobSelectl != null && mJobSelectl.getSelectVo() != null) {
                    holder.mTvItemJobListContent.setTextColor(mContext.getResources().getColor(
                            mJobSelectl.getTypethree() == vo.getId() ?
                                    R.color.tab_color : R.color.color_444A53));
                }
                break;
        }

        holder.mTvItemJobListContent.setText(vo.getName());
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

    public class JobViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvItemJobListContent;
        public ImageView mIvItemJobGo;


        public JobViewHolder(View itemView) {
            super(itemView);
            this.mTvItemJobListContent = (TextView) itemView.findViewById(R.id.tv_item_job_list_content);
            this.mIvItemJobGo = (ImageView) itemView.findViewById(R.id.iv_item_job_go);

        }
    }


}
