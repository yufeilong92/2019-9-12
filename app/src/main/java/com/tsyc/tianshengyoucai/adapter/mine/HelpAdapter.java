package com.tsyc.tianshengyoucai.adapter.mine;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.vo.HelpVo;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/16 11:14
 * @Purpose :帮助列表
 */
public class HelpAdapter extends RecyclerView.Adapter<HelpAdapter.HelpViewHolder> {
    private Context mContext;
    private List<HelpVo.ResultBean.ListBean.DataBean> mData;
    private final LayoutInflater mInflater;

    public HelpAdapter(Context mContext, List<HelpVo.ResultBean.ListBean.DataBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(int position, HelpVo.ResultBean.ListBean.DataBean vo);

    }

    @NonNull
    @Override
    public HelpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_help, null);
        return new HelpViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HelpViewHolder holder, int position) {
        HelpVo.ResultBean.ListBean.DataBean vo = mData.get(position);
        holder.mTvHelpContent.setText(vo.getHelp_title());
        holder.mTvHelpContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.itemClick(position,vo);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class HelpViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvHelpContent;

        public HelpViewHolder(View itemView) {
            super(itemView);
            this.mTvHelpContent = (TextView) itemView.findViewById(R.id.tv_help_content);
        }
    }


}
