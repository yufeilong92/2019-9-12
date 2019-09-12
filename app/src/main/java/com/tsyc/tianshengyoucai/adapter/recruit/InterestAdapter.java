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
import com.tsyc.tianshengyoucai.vo.InterestVo;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/9 15:47
 * @Purpose :
 */
public class InterestAdapter extends RecyclerView.Adapter<InterestAdapter.InterestHolder> {
    private Context mContext;
    private List<InterestVo.ResultBean.DataBean> mData;
    private final LayoutInflater mInflater;

    public InterestAdapter(Context mContext, List<InterestVo.ResultBean.DataBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(InterestVo.ResultBean.DataBean bean);

    }

    @NonNull
    @Override
    public InterestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_interst, null);
        return new InterestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InterestHolder holder, int position) {
        InterestVo.ResultBean.DataBean bean = mData.get(position);
        InterestVo.ResultBean.DataBean.CvinfoBean cvinfo = bean.getCvinfo();
        if (cvinfo == null) {
            return;
        }
        GlideUtil.getSingleton().loadQuadRangleImager(mContext, holder.mIvItemBossInterHear, cvinfo.getAvatar());
        holder.mTvItemBossInterName.setText(cvinfo.getUsername());
        GmSelectBean sex = cvinfo.getSex();
        holder.mTvItemBossInterSex.setText("/" + sex.getDesc());
        GmSelectBean status = cvinfo.getCurrent_status();
        holder.mTvItemBossInterStatus.setText(status.getDesc());
        StringBuffer buffer = new StringBuffer();

        GmSelectBean expectedPosition = cvinfo.getExpected_position();
        buffer.append(expectedPosition.getDesc());
        buffer.append(" | ");
        buffer.append("工作" + cvinfo.getExperience() + "年");
        buffer.append(" | ");

        GmSelectBean expected_salary = cvinfo.getExpected_salary();
        buffer.append(expected_salary.getDesc());
        holder.mTvItemBossInterContentOne.setText(buffer.toString());

        StringBuffer bufferOne = new StringBuffer();
        bufferOne.append(cvinfo.getAge());
        bufferOne.append(" | ");

        GmSelectBean education = cvinfo.getHighest_education();
        bufferOne.append(education.getDesc());
        bufferOne.append(" | ");

        GmSelectBean city = cvinfo.getWork_city();
        bufferOne.append(city.getDesc());
        holder.mTvItemBossInterContentTwo.setText(bufferOne.toString());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.itemClick(bean);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class InterestHolder extends RecyclerView.ViewHolder {
        public ImageView mIvItemBossInterHear;
        public TextView mTvItemBossInterName;
        public TextView mTvItemBossInterSex;
        public TextView mTvItemBossInterStatus;
        public TextView mTvItemBossInterContentOne;
        public TextView mTvItemBossInterContentTwo;

        public InterestHolder(View itemView) {
            super(itemView);
            this.mIvItemBossInterHear = (ImageView) itemView.findViewById(R.id.iv_item_boss_inter_hear);
            this.mTvItemBossInterName = (TextView) itemView.findViewById(R.id.tv_item_boss_inter_name);
            this.mTvItemBossInterSex = (TextView) itemView.findViewById(R.id.tv_item_boss_inter_sex);
            this.mTvItemBossInterStatus = (TextView) itemView.findViewById(R.id.tv_item_boss_inter_status);
            this.mTvItemBossInterContentOne = (TextView) itemView.findViewById(R.id.tv_item_boss_inter_content_one);
            this.mTvItemBossInterContentTwo = (TextView) itemView.findViewById(R.id.tv_item_boss_inter_content_two);
        }
    }


}
