package com.tsyc.tianshengyoucai.adapter.type;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.vo.GoodTypeVo;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/12 17:18
 * @Purpose :
 */
public class GoodRightAdapter extends RecyclerView.Adapter<GoodRightAdapter.RightViewHolde> {

    private Context mContext;
    private List<GoodTypeVo.ResultBean> mData;
    private final LayoutInflater mInflater;
    private String mTitle;

    public GoodRightAdapter(Context mContext, List<GoodTypeVo.ResultBean> mData, String title) {
        this.mContext = mContext;
        this.mData = mData;
        this.mTitle = title;

        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(int position, GoodTypeVo.ResultBean bean);

    }

    public void dataRefresh(String title) {
        this.mTitle = title;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RightViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_type_right, null);
        return new RightViewHolde(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RightViewHolde holder, int position) {
        GoodTypeVo.ResultBean resultBean = mData.get(position);
        String gc_name = resultBean.getGc_name();
        holder.mTvTypeRightTitle.setText(gc_name);
        List<GoodTypeVo.ResultBean> child = resultBean.getChild();
        RecyclerUtil.setGridManage(mContext, holder.mRlvTypeRightContent, 3);
        GoodRightTypeItemAdapter adapter = new GoodRightTypeItemAdapter(mContext, child);
        holder.mRlvTypeRightContent.setAdapter(adapter);
        adapter.setListener(new GoodRightTypeItemAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, GoodTypeVo.ResultBean bean) {
                if (listener != null) {
                    listener.itemClick(position, bean);
                }
            }
        });
     //   initData(holder, mData);


    }

    private void initData(RightViewHolde holder, List<GoodTypeVo.ResultBean> child) {
        RecyclerUtil.setGridManage(mContext, holder.mRlvTypeRightContent, 3);
        GoodRightTypeItemAdapter adapter = new GoodRightTypeItemAdapter(mContext, child);
        holder.mRlvTypeRightContent.setAdapter(adapter);
        adapter.setListener(new GoodRightTypeItemAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, GoodTypeVo.ResultBean bean) {
                if (listener != null) {
                    listener.itemClick(position, bean);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class RightViewHolde extends RecyclerView.ViewHolder {
        public TextView mTvTypeRightTitle;
        public RecyclerView mRlvTypeRightContent;

        public RightViewHolde(View itemView) {
            super(itemView);
            this.mTvTypeRightTitle = (TextView) itemView.findViewById(R.id.tv_type_right_title);
            this.mRlvTypeRightContent = (RecyclerView) itemView.findViewById(R.id.rlv_type_right_content);

        }
    }

}
