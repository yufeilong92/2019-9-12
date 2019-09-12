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
import com.tsyc.tianshengyoucai.vo.BossJobContentVo;
import com.tsyc.tianshengyoucai.vo.GmSelectBean;
import com.tsyc.tianshengyoucai.vo.GmSelectBeanStr;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/4 14:47
 * @Purpose :boss 职位适配器
 */
public class BossJobAdapter extends RecyclerView.Adapter<BossJobAdapter.JobHolder> {
    private Context mContext;
    private List<BossJobContentVo.ResultBean.DataBean> mData;
    private final LayoutInflater mInflater;

    public BossJobAdapter(Context mContext, List<BossJobContentVo.ResultBean.DataBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(BossJobContentVo.ResultBean.DataBean vo);

    }

    @NonNull
    @Override
    public JobHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_boss_job, null);
        return new JobHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobHolder holder, int position) {
        BossJobContentVo.ResultBean.DataBean vo = mData.get(position);
        GlideUtil.getSingleton().loadQuadRangleImager(mContext, holder.mIvItemBossJobHear,
                vo.getAvatar());
        holder.mTvItemBossJobName.setText(vo.getUsername());
        holder.mTvItemBossJobSex.setText("/" + vo.getSex().getDesc());

        StringBuffer buffer = new StringBuffer();
        GmSelectBeanStr industry = vo.getExpected_industry();
        GmSelectBean salary = vo.getExpected_salary();
        if (industry != null) {
            buffer.append(industry.getDesc());
            buffer.append(" | ");
        }
        if (salary != null) {
            buffer.append("工作" + vo.getExperience() + "年");
            buffer.append(" | ");
            buffer.append(salary.getDesc());
        }
        holder.mTvItemBossJobContentOne.setText(buffer.toString());

        GmSelectBean education = vo.getHighest_education();
        StringBuffer bufferTwo = new StringBuffer();
        bufferTwo.append(vo.getAge());
        bufferTwo.append(" | ");
        if (education != null) {
            bufferTwo.append(education.getDesc());
            bufferTwo.append(" | ");
        }
        GmSelectBean city = vo.getWork_city();
        if (city != null)
            bufferTwo.append(city.getDesc());
        holder.mTvItemBossJobContentTwo.setText(bufferTwo.toString());
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

    public class JobHolder extends RecyclerView.ViewHolder {
        public ImageView mIvItemBossJobHear;
        public TextView mTvItemBossJobName;
        public TextView mTvItemBossJobSex;
        public TextView mTvItemBossJobContentOne;
        public TextView mTvItemBossJobContentTwo;

        public JobHolder(View itemView) {
            super(itemView);
            this.mIvItemBossJobHear = (ImageView) itemView.findViewById(R.id.iv_item_boss_job_hear);
            this.mTvItemBossJobName = (TextView) itemView.findViewById(R.id.tv_item_boss_job_name);
            this.mTvItemBossJobSex = (TextView) itemView.findViewById(R.id.tv_item_boss_job_sex);
            this.mTvItemBossJobContentOne = (TextView) itemView.findViewById(R.id.tv_item_boss_job_content_one);
            this.mTvItemBossJobContentTwo = (TextView) itemView.findViewById(R.id.tv_item_boss_job_content_two);

        }
    }


}
