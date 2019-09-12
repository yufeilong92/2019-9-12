package com.tsyc.tianshengyoucai.adapter.bank;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tsyc.tianshengyoucai.R;
import com.zhihu.matisse.internal.entity.Item;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/8 20:29
 * @Purpose :
 */
public class MyCoisnAdapter extends RecyclerView.Adapter<MyCoisnAdapter.ConisViewHolder> {
    private Context mContext;
    private List<String> mData;
    private final LayoutInflater mInflater;

    public MyCoisnAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick();
    }


    @NonNull

    @Override
    public MyCoisnAdapter.ConisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyCoisnAdapter.ConisViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ConisViewHolder extends RecyclerView.ViewHolder {
        public ConisViewHolder(View itemView) {
            super(itemView);
        }
    }
}
