package com.tsyc.tianshengyoucai.adapter.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.HomeListBean;
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.view.FlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/8 11:04
 * @Purpose :工作
 */
public class JobAdapter extends RecyclerView.Adapter<JobAdapter.ViewHolder> {
    private Context mContext;
    private List<HomeListBean.ResultBean.PositionBean> mData;
    private final LayoutInflater mInflater;

    public JobAdapter(Context mContext, List<HomeListBean.ResultBean.PositionBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(int postion, HomeListBean.ResultBean.PositionBean vo);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.item_home_job_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeListBean.ResultBean.PositionBean bean = mData.get(position);
        holder.mTvItemHomeJobTitle.setText(bean.getName());
        holder.mTvItemHomeJobCompany.setText(bean.getDetail());
        holder.mTvItemHomeJobMoney.setText(bean.getSalary());
        holder.mTvItemHomeJobName.setText(bean.getContact());
        GlideUtil.getSingleton().LoadImager(mContext, holder.mIvItemHomeJobHear, bean.getLogo());
        List<String> data = new ArrayList<>();
        if (!StringUtil.isEmpty(bean.getAddress())) {
            data.add(bean.getAddress());
        }
        if (!StringUtil.isEmpty(bean.getExperience())) {
            data.add(bean.getExperience());
        }
        if (!StringUtil.isEmpty(bean.getEducational())) {
            data.add(bean.getEducational());
        }
        setFlayout(data, holder.mFlItemHomeJobTag);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.itemClick(position, bean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvItemHomeJobTitle;
        public TextView mTvItemHomeJobMoney;
        public TextView mTvItemHomeJobCompany;
        public FlowLayout mFlItemHomeJobTag;
        public ImageView mIvItemHomeJobHear;
        public TextView mTvItemHomeJobName;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mTvItemHomeJobTitle = (TextView) itemView.findViewById(R.id.tv_item_home_job_title);
            this.mTvItemHomeJobMoney = (TextView) itemView.findViewById(R.id.tv_item_home_job_money);
            this.mTvItemHomeJobCompany = (TextView) itemView.findViewById(R.id.tv_item_home_job_company);
            this.mFlItemHomeJobTag = (FlowLayout) itemView.findViewById(R.id.fl_item_home_job_tag);
            this.mIvItemHomeJobHear = (ImageView) itemView.findViewById(R.id.iv_item_home_job_hear);
            this.mTvItemHomeJobName = (TextView) itemView.findViewById(R.id.tv_item_home_job_name);

        }
    }

    private void setFlayout(List<String> data, FlowLayout fl) {
        if (data == null || data.size() == 0) return;
        fl.removeAllViews();
        for (int i = 0; i < data.size(); i++) {
            String s = data.get(i);
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_home_postion_flaout, null);
            TextView tvContent = view.findViewById(R.id.tv_item_flayout_content);
            tvContent.setText(s);
            fl.addView(view);
        }
    }

}
