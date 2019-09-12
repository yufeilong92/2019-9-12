package com.tsyc.tianshengyoucai.adapter.bank;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.vo.BankListVo;
import com.tsyc.tianshengyoucai.vo.SelectVo;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/8 16:04
 * @Purpose :银行列表适配器
 */
public class BankListAdapter extends RecyclerView.Adapter<BankListAdapter.ViewHolder> {
    private Context mContext;
    private List<BankListVo.ResultBean> mData;
    private final LayoutInflater mInflater;
    private List<SelectVo> mSelectVo;

    public BankListAdapter(Context mContext, List<BankListVo.ResultBean> mData, List<SelectVo> vo) {
        this.mContext = mContext;
        this.mData = mData;
        this.mSelectVo = vo;
        mInflater = LayoutInflater.from(mContext);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_dialog_bank, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BankListVo.ResultBean resultBean = mData.get(position);
        SelectVo vo = mSelectVo.get(position);
        holder.mTvItemDialogContent.setText(resultBean.getBank_name());
        holder.mTvItemDialogContent.setTextColor(mContext.getResources().getColor(vo.isSelect() ? R.color.tab_color : R.color.color_444A53));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(position, resultBean);
                }
            }
        });
    }
    public void setRefresh(List<SelectVo> vo){
        this.mSelectVo=vo;
        notifyDataSetChanged();

    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int postion, BankListVo.ResultBean vo);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvItemDialogContent;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mTvItemDialogContent = (TextView) itemView.findViewById(R.id.tv_item_dialog_content);

        }
    }


}
