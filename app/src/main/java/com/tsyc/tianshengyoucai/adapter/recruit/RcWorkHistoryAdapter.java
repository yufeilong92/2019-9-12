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
 * @Time :2019/8/29 17:28
 * @Purpose : 工作经历
 */
public class RcWorkHistoryAdapter extends RecyclerView.Adapter<RcWorkHistoryAdapter.RcWorkHistoryHodle> {

    private Context mContext;
    private List<ResumeVo.WorksBean> mData;
    private final LayoutInflater mInflater;

    public RcWorkHistoryAdapter(Context mContext, List<ResumeVo.WorksBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(ResumeVo.WorksBean vo);

    }

    private String mType;

    public void refreshType(String type) {
        this.mType = type;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RcWorkHistoryHodle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_work_histhory, null);
        return new RcWorkHistoryHodle(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RcWorkHistoryHodle holder, int position) {
        ResumeVo.WorksBean vo = mData.get(position);
        holder.mTvWorkHisthoryCompany.setText(vo.getCompany());
        StringBuffer buffer = new StringBuffer();
        buffer.append(vo.getStart_time());
        buffer.append("--");
        if (StringUtil.isEmpty(vo.getEnd_time())) {
            buffer.append("至今");
        } else {
            buffer.append(vo.getEnd_time());
        }
        buffer.append("/");
        buffer.append(vo.getPosition());
        holder.mTvWorkHisthoryContenttime.setText(buffer.toString());
        holder.mTvWorkHisthoryContent.setText(vo.getDesc());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.itemClick(vo);
                }
            }
        });
        if (!StringUtil.isEmpty(mType)) {
            holder.mIvWorkGo.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class RcWorkHistoryHodle extends RecyclerView.ViewHolder {
        public TextView mTvWorkHisthoryCompany;
        public TextView mTvWorkHisthoryContenttime;
        public TextView mTvWorkHisthoryContent;
        public ImageView mIvWorkGo;

        public RcWorkHistoryHodle(View itemView) {
            super(itemView);
            this.mTvWorkHisthoryCompany = (TextView) itemView.findViewById(R.id.tv_work_histhory_company);
            this.mTvWorkHisthoryContenttime = (TextView) itemView.findViewById(R.id.tv_work_histhory_contenttime);
            this.mTvWorkHisthoryContent = (TextView) itemView.findViewById(R.id.tv_work_histhory_content);
            this.mIvWorkGo = (ImageView) itemView.findViewById(R.id.iv_work_go);

        }
    }

}
