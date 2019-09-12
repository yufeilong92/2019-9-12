package com.tsyc.tianshengyoucai.adapter.recruit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.vo.DeliveryListVo;
import com.tsyc.tianshengyoucai.vo.GmSelectBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/28 15:35
 * @Purpose : 我的投递
 */
public class MyDeliverAdapter extends RecyclerView.Adapter<MyDeliverAdapter.DeliveHolder> {
    private Context mContext;
    private List<DeliveryListVo.ResultBean.DataBean> mData;
    private final LayoutInflater mInflater;

    public MyDeliverAdapter(Context mContext, List<DeliveryListVo.ResultBean.DataBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(DeliveryListVo.ResultBean.DataBean bean);

    }

    @NonNull
    @Override
    public DeliveHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_my_delivery, null);
        return new DeliveHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveHolder holder, int position) {
        DeliveryListVo.ResultBean.DataBean bean = mData.get(position);
        holder.mTvMyDeliveTitle.setText(bean.getPosition_name());
        holder.mTvItemMyDeliveTime.setText(bean.getSend_time());
        GmSelectBean salary = bean.getSalary();
        holder.mTvItemMyDeliveMoney.setText(salary.getDesc() + "/月");
        holder.mTvItemMyDeliveCompany.setText(bean.getCompany_name());
        GmSelectBean status = bean.getStatus();
        holder.mTvItemMyDeliveStatus.setText(status.getDesc());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.itemClick(bean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class DeliveHolder extends RecyclerView.ViewHolder {
        public TextView mTvMyDeliveTitle;
        public TextView mTvItemMyDeliveTime;
        public TextView mTvItemMyDeliveMoney;
        public TextView mTvItemMyDeliveCompany;
        public TextView mTvItemMyDeliveStatus;

        public DeliveHolder(View itemView) {
            super(itemView);
            this.mTvMyDeliveTitle = (TextView) itemView.findViewById(R.id.tv_my_delive_title);
            this.mTvItemMyDeliveTime = (TextView) itemView.findViewById(R.id.tv_item_my_delive_time);
            this.mTvItemMyDeliveMoney = (TextView) itemView.findViewById(R.id.tv_item_my_delive_money);
            this.mTvItemMyDeliveCompany = (TextView) itemView.findViewById(R.id.tv_item_my_delive_company);
            this.mTvItemMyDeliveStatus = (TextView) itemView.findViewById(R.id.tv_item_my_delive_status);
        }
    }


}
