package com.tsyc.tianshengyoucai.adapter.mine;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.utils.TextUtil;
import com.tsyc.tianshengyoucai.vo.TeamVo;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/13 15:07
 * @Purpose :我的团队
 */
public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {

    private Context mContext;
    private List<TeamVo.ResultBean.ParentListBean> mData;
    private final LayoutInflater mInflater;


    public TeamAdapter(Context mContext, List<TeamVo.ResultBean.ParentListBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick();

    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_team, null);
        return new TeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {
        TeamVo.ResultBean.ParentListBean vo = mData.get(position);
        TextUtil.bindTextView(holder.mTvName,vo.getMember_name());
        TextUtil.bindTextView(holder.mTvItemTeamPhone,vo.getMember_mobile());
        TextUtil.bindTextView(holder.mTvItemTeamTime,vo.getMember_add_time());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class TeamViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvName;
        public TextView mTvItemTeamPhone;
        public TextView mTvItemTeamTime;
        public TextView mTvItemTeamValues;
        public TeamViewHolder(View itemView) {
            super(itemView);
            this.mTvName = (TextView) itemView.findViewById(R.id.tv_name);
            this.mTvItemTeamPhone = (TextView) itemView.findViewById(R.id.tv_item_team_phone);
            this.mTvItemTeamTime = (TextView) itemView.findViewById(R.id.tv_item_team_time);
            this.mTvItemTeamValues = (TextView) itemView.findViewById(R.id.tv_item_team_values);
        }
    }


}
