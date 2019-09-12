package com.tsyc.tianshengyoucai.adapter.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.vo.MyMoneyVo;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/7 18:11
 * @Purpose :提现记录
 */
public class WithdrawAdapter extends RecyclerView.Adapter<WithdrawAdapter.ViewHolde> {
    private Context mContext;
    private List<MyMoneyVo.ResultBean.ListBean> mData;
    private LayoutInflater mInflater;

    public WithdrawAdapter(Context mContext, List<MyMoneyVo.ResultBean.ListBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_withdraw, null);
        return new ViewHolde(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolde holder, int position) {
        MyMoneyVo.ResultBean.ListBean vo = mData.get(position);
        holder.mTvMoneyName.setText(vo.getNote());
        holder.mTvMoneyTime.setText(vo.getTime());
        holder.mTvMoneyValue.setText(vo.getMoney() + "元");
        double v = Double.parseDouble(vo.getMoney());
        boolean b = v <= 0;
        holder.mTvMoneyValue.setTextColor(mContext.getResources().getColor(
                b ? R.color.color_444A53 : R.color.tab_color));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolde extends RecyclerView.ViewHolder {
        public TextView mTvMoneyName;
        public TextView mTvMoneyTime;
        public TextView mTvMoneyValue;

        public ViewHolde(View itemView) {
            super(itemView);
            this.mTvMoneyName = (TextView) itemView.findViewById(R.id.tv_money_name);
            this.mTvMoneyTime = (TextView) itemView.findViewById(R.id.tv_money_time);
            this.mTvMoneyValue = (TextView) itemView.findViewById(R.id.tv_money_value);
        }
    }


}
