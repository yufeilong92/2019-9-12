package com.tsyc.tianshengyoucai.adapter.recruit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.ui.recruit.boss.BossOtherJobActivity;
import com.tsyc.tianshengyoucai.ui.recruit.infom.TradeActivity;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.view.FlowLayout;
import com.tsyc.tianshengyoucai.vo.JobSelectItemVo;
import com.tsyc.tianshengyoucai.vo.SelectVo;
import com.tsyc.tianshengyoucai.vo.TradeSelectIndox;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/20 17:00
 * @Purpose : 行业
 */
public class TradeListAdapter extends RecyclerView.Adapter<TradeListAdapter.TradeHolder> {

    private Context mContext;
    private List<JobSelectItemVo> mData;
    private final LayoutInflater mInflater;
    private List<TradeSelectIndox> mSelectLists;
    private int mSelectNubmer;
    private List<SelectVo> mItemSelectLists = new ArrayList<>();

    public TradeListAdapter(Context mContext, List<JobSelectItemVo> mData, Integer mSelectNumber) {
        this.mContext = mContext;
        this.mData = mData;
        this.mSelectNubmer = mSelectNumber;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void refreshData(List<TradeSelectIndox> mSelectList) {
        this.mSelectLists = mSelectList;
        notifyDataSetChanged();

    }

    public interface OnItemClickListener {
        void itemClick(int position, JobSelectItemVo vo);

    }


    @NonNull
    @Override
    public TradeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_tarde_layout, null);
        return new TradeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TradeHolder holder, int position) {
        JobSelectItemVo vo = mData.get(position);
        holder.mTvItemTardTitle.setText(vo.getName());
        if (vo.isSelect()) {
            holder.mIvItemTardLogo.setImageResource(R.mipmap.jft_but_arrowup);
            holder.mFlItemTardContent.setVisibility(View.VISIBLE);
        } else {
            holder.mFlItemTardContent.setVisibility(View.GONE);
            holder.mIvItemTardLogo.setImageResource(R.mipmap.jft_but_arrowu_down);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<JobSelectItemVo> items = vo.getSumItems();
                if (items == null || items.isEmpty()) {
                    T.showToast(mContext, "没有下级");
                    holder.mFlItemTardContent.setVisibility(View.GONE);
                    if (listener != null) {
                        listener.itemClick(position, vo);
                    }
                    return;
                }
                if (holder.mFlItemTardContent.getVisibility() == View.VISIBLE) {
                    holder.mFlItemTardContent.setVisibility(View.GONE);
                    holder.mIvItemTardLogo.setImageResource(R.mipmap.jft_but_arrowu_down);
                } else {
                    holder.mIvItemTardLogo.setImageResource(R.mipmap.jft_but_arrowup);
                    holder.mFlItemTardContent.setVisibility(View.VISIBLE);
                }
            }
        });
        initFlayout(holder, vo, position, mItemSelectLists);

    }


    private void initFlayout(TradeHolder holder, JobSelectItemVo mVo, int position, List<SelectVo> mItemSelectLists) {
        List<JobSelectItemVo> lists = mVo.getSumItems();
        holder.mFlItemTardContent.removeAllViews();
        if (lists == null || lists.isEmpty()) {
            T.showToast(mContext, "没有下级");
            holder.mFlItemTardContent.setVisibility(View.GONE);
            return;
        }
        for (int i = 0; i < lists.size(); i++) {
            JobSelectItemVo vo = lists.get(i);
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_tarde_tag_layout, null);
            TextView tv = view.findViewById(R.id.tv_tarde_tag);
            int color1 = mContext.getResources().getColor(R.color.color_444A53);
            int color2 = mContext.getResources().getColor(R.color.color_5769E7);
            tv.setTextColor(color1);
            tv.setText(vo.getName());
            if (vo.isSelect()) {
                tv.setTextColor(mContext.getResources().getColor(R.color.color_5769E7));
                tv.setBackgroundResource(R.drawable.gm_circle_blue_bg);
            } else {
                tv.setTextColor(mContext.getResources().getColor(R.color.color_444A53));
                tv.setBackgroundResource(R.drawable.gm_circle_gray_bg);
            }
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mContext instanceof TradeActivity) {
                        TradeActivity activity = (TradeActivity) mContext;
                        int listSelectVo = activity.getListSelectVo();
                        int color = tv.getCurrentTextColor();
                        if (color == color2) {//选择
                            activity.addListSelectVo(vo, false, mVo.getId());
                            tv.setTextColor(mContext.getResources().getColor(R.color.color_444A53));
                            tv.setBackgroundResource(R.drawable.gm_circle_gray_bg);
                            return;
                        }
                        //没选中
                        if (listSelectVo == mSelectNubmer) {
                            T.showToast(mContext, "最多选择" + mSelectNubmer + "个");
                            return;
                        }
                        activity.addListSelectVo(vo, true, mVo.getId());
                        tv.setTextColor(mContext.getResources().getColor(R.color.color_5769E7));
                        tv.setBackgroundResource(R.drawable.gm_circle_blue_bg);
                        return;
                    }
                    if (mContext instanceof BossOtherJobActivity){
                        BossOtherJobActivity activity = (BossOtherJobActivity) mContext;
//                        int color = tv.getCurrentTextColor();
//                        if (color == color2) {//选择
//                            activity.addListSelectVo(vo, false, mVo.getId());
//                            tv.setTextColor(mContext.getResources().getColor(R.color.color_444A53));
//                            tv.setBackgroundResource(R.drawable.gm_circle_gray_bg);
//                            return;
//                        }
                        activity.addListSelectVo(vo, true, mVo.getId());
                        tv.setTextColor(mContext.getResources().getColor(R.color.color_5769E7));
                        tv.setBackgroundResource(R.drawable.gm_circle_blue_bg);
                        return;
                    }
                }
            });


            holder.mFlItemTardContent.addView(view);
        }


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class TradeHolder extends RecyclerView.ViewHolder {
        public TextView mTvItemTardTitle;
        public ImageView mIvItemTardLogo;
        public FlowLayout mFlItemTardContent;

        public TradeHolder(View itemView) {
            super(itemView);
            this.mTvItemTardTitle = (TextView) itemView.findViewById(R.id.tv_item_tard_title);
            this.mIvItemTardLogo = (ImageView) itemView.findViewById(R.id.iv_item_tard_logo);
            this.mFlItemTardContent = (FlowLayout) itemView.findViewById(R.id.fl_item_tard_content);

        }
    }

}
