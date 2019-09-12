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
import com.tsyc.tianshengyoucai.view.FlowLayout;
import com.tsyc.tianshengyoucai.vo.BossJobItemVo;
import com.tsyc.tianshengyoucai.vo.GmSelectBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/30 10:54
 * @Purpose :职位列表
 */
public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {
    private Context mContext;
    private List<BossJobItemVo> mData;
    private final LayoutInflater mInflater;
    private int mType = 0;

    public JobAdapter(Context mContext, List<BossJobItemVo> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(int position, BossJobItemVo vo);

        void itemDelete(int postion, int id);
    }

    public void hineDelete(int type) {
        this.mType = type;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_rc_job, null);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        BossJobItemVo vo = mData.get(position);

        BossJobItemVo.CompanyBean company = vo.getCompany();

        BossJobItemVo.BossBean boss = vo.getBoss();

        BossJobItemVo.AddressInfoBean addressInfo = vo.getAddress_info();

        GmSelectBean jobType = vo.getJob_type();

        GmSelectBean work_experience = vo.getWork_experience();

        GmSelectBean education = vo.getEducation();


        holder.mIvInviteTitle.setText(vo.getPosition_name());
        holder.mIvInviteTag.setText(jobType.getDesc());
        holder.mIvInviteValues.setText(vo.getSalary().getDesc());
        if (company != null)
            holder.mIvInviteCompany.setText(company.getFull_name());
        if (boss != null) {
            holder.mTvInviteUser.setText(boss.getUsername() + "·" + boss.getDuties());
            GlideUtil.getSingleton().loadQuadRangleImager(mContext, holder.mIvInviteLogo,
                    boss.getAvatar());
        }

        List<String> list = new ArrayList<>();
        if (addressInfo != null)
            list.add(addressInfo.getProvince() + addressInfo.getCity() + addressInfo.getArea() +
                    addressInfo.getAddress());
        if (work_experience != null) {
            list.add(work_experience.getDesc());
            list.add(work_experience.getDesc());
        }
        if (education != null)
            list.add(education.getDesc());
        bindViewData(holder, list);
//        holder.mTvJobCollectDelete.setVisibility(mType==1?View.GONE:View.VISIBLE);
        holder.mTvJobCollectDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.itemDelete(position, vo.getId());
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.itemClick(position, vo);
                }
            }
        });

    }

    private void bindViewData(JobViewHolder holder, List<String> list) {
        if (list == null || list.isEmpty()) return;
        holder.mFlInviteType.removeAllViews();

        for (int i = 0; i < list.size(); i++) {
            String comtent = list.get(i);
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_job_type, null);
            TextView tv = inflate.findViewById(R.id.tv_job_type_content);
            tv.setText(comtent);
            holder.mFlInviteType.addView(inflate);
        }


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class JobViewHolder extends RecyclerView.ViewHolder {
        public TextView mIvInviteTitle;
        public TextView mIvInviteTag;
        public TextView mIvInviteValues;
        public TextView mIvInviteCompany;
        public FlowLayout mFlInviteType;
        public ImageView mIvInviteLogo;
        public TextView mTvInviteUser;
        public TextView mTvJobCollectDelete;

        public JobViewHolder(View itemView) {
            super(itemView);
            this.mIvInviteTitle = (TextView) itemView.findViewById(R.id.iv_invite_title);
            this.mIvInviteTag = (TextView) itemView.findViewById(R.id.iv_invite_tag);
            this.mIvInviteValues = (TextView) itemView.findViewById(R.id.iv_invite_values);
            this.mIvInviteCompany = (TextView) itemView.findViewById(R.id.iv_invite_company);
            this.mFlInviteType = (FlowLayout) itemView.findViewById(R.id.fl_invite_type);
            this.mIvInviteLogo = (ImageView) itemView.findViewById(R.id.iv_invite_logo);
            this.mTvInviteUser = (TextView) itemView.findViewById(R.id.tv_invite_user);
            this.mTvJobCollectDelete = (TextView) itemView.findViewById(R.id.tv_job_collect_delete);
        }
    }


}
