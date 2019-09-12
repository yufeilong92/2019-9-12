package com.tsyc.tianshengyoucai.adapter.recruit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/27 10:38
 * @Purpose : 历史
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {
    private Context mContext;
    private List<String> mData;
    private final LayoutInflater mInflater;

    public HistoryAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(String com);

    }

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = mInflater.inflate(R.layout.item_history, null);
        return new HistoryHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder holder, int position) {
        String com = mData.get(position);
        holder.mTvHistoryContent.setText(com);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.itemClick(com);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class HistoryHolder extends RecyclerView.ViewHolder {
        public TextView mTvHistoryContent;

        public HistoryHolder(View itemView) {
            super(itemView);
            this.mTvHistoryContent = (TextView) itemView.findViewById(R.id.tv_history_content);

        }
    }


}
