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
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.vo.ResumeVo;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/29 17:47
 * @Purpose : 教育
 */
public class RcEduAdapter extends RecyclerView.Adapter<RcEduAdapter.RcEduViewHolder> {
    private Context mContext;
    private List<ResumeVo.EducationsBean> mData;
    private final LayoutInflater mInflater;
    private String mType;

    public RcEduAdapter(Context mContext, List<ResumeVo.EducationsBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(ResumeVo.EducationsBean bean);

    }

    public void refrehsLook(String type) {
        this.mType = type;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RcEduViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_edu, null);
        return new RcEduViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RcEduViewHolder holder, int position) {
        ResumeVo.EducationsBean bean = mData.get(position);
        StringBuffer buffer = new StringBuffer();
        buffer.append(bean.getSchool());
        buffer.append(" ");
        buffer.append(bean.getEducation().getDesc());
        holder.mTvEduTitle.setText(buffer.toString());
        StringBuffer com = new StringBuffer();
        com.append(bean.getStart_time());
        com.append("--");
        com.append(bean.getEnd_time());
        com.append("/");
        com.append(bean.getSpeciality());
        holder.mTvEduContent.setText(com.toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.itemClick(bean);
                }
            }
        });
        if (!StringUtil.isEmpty(mType)) {
            holder.mIvEduGo.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class RcEduViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvEduTitle;
        public TextView mTvEduContent;
        public ImageView mIvEduGo;

        public RcEduViewHolder(View itemView) {
            super(itemView);
            this.mTvEduTitle = (TextView) itemView.findViewById(R.id.tv_edu_title);
            this.mTvEduContent = (TextView) itemView.findViewById(R.id.tv_edu_content);
            this.mIvEduGo = (ImageView) itemView.findViewById(R.id.iv_edu_go);
        }
    }


}
