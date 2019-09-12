package com.tsyc.tianshengyoucai.adapter.recruit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.utils.Util;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/27 11:28
 * @Purpose : 热词
 */
public class HostAdapter extends RecyclerView.Adapter<HostAdapter.HostViewHodler> {

    private Context mContext;
    private List<String> mData;
    private final LayoutInflater mInflater;

    public HostAdapter(Context mContext, List<String> mData) {
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
    public HostViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.host_layout, null);
        return new HostViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HostViewHodler holder, int position) {
        String com = mData.get(position);
        holder.mTvHostContent.setText(com);
        int random = Util.getRandom();
        int draw;
        switch (random) {
            case 0:
                draw = R.drawable.bg_qian_yellow;
                break;
            case 1:
                draw = R.drawable.bg_qian_fen;
                break;
            case 2:
                draw = R.drawable.bg_qian_bule;
                break;
            default:
                draw = R.drawable.bg_qian_yellow;
                break;
        }
        holder.mTvHostContent.setBackgroundResource(draw);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class HostViewHodler extends RecyclerView.ViewHolder {
        public TextView mTvHostContent;

        public HostViewHodler(View itemView) {
            super(itemView);
            this.mTvHostContent = (TextView) itemView.findViewById(R.id.tv_host_content);
        }
    }


}
