package com.tsyc.tianshengyoucai.adapter.bank;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.listener.ItemClickListener;
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.tsyc.tianshengyoucai.vo.MyBankListVo;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/8 15:52
 * @Purpose :银行卡列表
 */
public class MyBankListAdapter extends RecyclerView.Adapter<MyBankListAdapter.MyBankViewHolder> {
    private Context mContext;
    private List<MyBankListVo.ResultBean> mData;
    private final LayoutInflater mInflater;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void deleteCard(MyBankListVo.ResultBean vo);

    }

    public MyBankListAdapter(Context mContext, List<MyBankListVo.ResultBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    public void refresh(List<MyBankListVo.ResultBean> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyBankViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_my_bank_list, null);
        return new MyBankViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyBankViewHolder holder, int position) {
        MyBankListVo.ResultBean vo = mData.get(position);
        GlideUtil singleton = GlideUtil.getSingleton();
        singleton .loadQuadRangleImager(mContext, holder.mTvItemBg, vo.getBank_bg());
        singleton.loadQuadRangleImager(mContext, holder.mIvItemMybank, vo.getBank_logo());
        holder.mTvItemBankName.setText(vo.getBank_name());
        holder.mTvItemBankNumber.setText(vo.getBank_number());
        holder.mTvItemBankType.setText(vo.getBank_sn());

        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(position);
            }
        });
        holder.mTvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.deleteCard(vo);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class MyBankViewHolder extends RecyclerView.ViewHolder {
        public ImageView mTvItemBg;
        public ImageView mIvItemMybank;
        public TextView mTvItemBankName;
        public TextView mTvItemBankType;
        public TextView mTvItemBankNumber;
        public TextView mTvDelete;

        public MyBankViewHolder(View itemView) {
            super(itemView);
            this.mTvItemBg = (ImageView) itemView.findViewById(R.id.tv_item_bg);
            this.mIvItemMybank = (ImageView) itemView.findViewById(R.id.iv_item_mybank);
            this.mTvItemBankName = (TextView) itemView.findViewById(R.id.tv_item_bank_name);
            this.mTvItemBankType = (TextView) itemView.findViewById(R.id.tv_item_bank_type);
            this.mTvItemBankNumber = (TextView) itemView.findViewById(R.id.tv_item_bank_number);
            this.mTvDelete = (TextView) itemView.findViewById(R.id.tv_my_bank_delete);
        }
    }


}
