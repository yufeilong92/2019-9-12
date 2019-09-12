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
import com.tsyc.tianshengyoucai.vo.JobSearchCompanyVo;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/7 14:39
 * @Purpose :
 */
public class JobSearchCompanyAdapter extends RecyclerView.Adapter<JobSearchCompanyAdapter.JobSearchCompanyHolder> {
    private Context mContext;
    private List<JobSearchCompanyVo.ResultBean.DataBean> mData;
    private final LayoutInflater mInflater;

    public JobSearchCompanyAdapter(Context mContext, List<JobSearchCompanyVo.ResultBean.DataBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(JobSearchCompanyVo.ResultBean.DataBean bean);

    }

    @NonNull
    @Override
    public JobSearchCompanyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_job_search_company, null);
        return new JobSearchCompanyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobSearchCompanyHolder holder, int position) {
        JobSearchCompanyVo.ResultBean.DataBean bean = mData.get(position);
        GlideUtil.getSingleton().loadQuadRangleImager(mContext, holder.mIvItemSearchCompanyLogo, bean.getLogo());
        holder.mTvItemSearchCompanyName.setText(bean.getFull_name());

        GmSelectBean companySize = bean.getCompany_size();
        StringBuffer buffer = new StringBuffer();
        if (companySize != null) {
            buffer.append(companySize.getDesc());
            buffer.append(" | ");
        }
        JobSearchCompanyVo.ResultBean.DataBean.IndustriesBean industriesBean = bean.getIndustries();
        if (industriesBean != null)
            buffer.append(industriesBean.getDesc());
        holder.mTvItemSearchCompanyContent.setText(buffer.toString());
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

    public class JobSearchCompanyHolder extends RecyclerView.ViewHolder {
        public ImageView mIvItemSearchCompanyLogo;
        public TextView mTvItemSearchCompanyName;
        public TextView mTvItemSearchCompanyContent;

        public JobSearchCompanyHolder(View itemView) {
            super(itemView);
            this.mIvItemSearchCompanyLogo = (ImageView) itemView.findViewById(R.id.iv_item_search_company_logo);
            this.mTvItemSearchCompanyName = (TextView) itemView.findViewById(R.id.tv_item_search_company_name);
            this.mTvItemSearchCompanyContent = (TextView) itemView.findViewById(R.id.tv_item_search_company_content);

        }
    }


}
