package com.tsyc.tianshengyoucai.adapter.shop;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/10 19:11
 * @Purpose : 评价
 */
public class EvaluteAdapter extends RecyclerView.Adapter<EvaluteAdapter.EvalueHolder> {

    private Context mContext;
    private List<String> mData;
    private final LayoutInflater mInflater;

    public EvaluteAdapter(Context mContext, List<String> mData) {
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
    public EvalueHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_evalue, null);
        return new EvalueHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EvalueHolder holder, int position) {

        List<ImageView> imgs = new ArrayList<>();
        imgs.add(holder.mIvItemEvaluteStarOne);
        imgs.add(holder.mIvItemEvaluteStarTwo);
        imgs.add(holder.mIvItemEvaluteStarThree);
        imgs.add(holder.mIvItemEvaluteStarFour);
        imgs.add(holder.mIvItemEvaluteStarFive);
    }

    private void showStart(List<ImageView> list, int postion) {
        for (int i = 0; i < list.size(); i++) {
            if (i <= postion - 1) {
                showOrHine(list.get(i), true);
            } else {
                showOrHine(list.get(i), false);
            }
        }
    }

    private void showOrHine(ImageView imageView, boolean show) {
        imageView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class EvalueHolder extends RecyclerView.ViewHolder {
        public ImageView mIvEvaluteHear;
        public TextView mTvEvaluteName;
        public ImageView mIvItemEvaluteStarOne;
        public ImageView mIvItemEvaluteStarTwo;
        public ImageView mIvItemEvaluteStarThree;
        public ImageView mIvItemEvaluteStarFour;
        public ImageView mIvItemEvaluteStarFive;
        public TextView mTvEvaluateTime;
        public TextView mTvEvaluateSize;
        public TextView mTvEvaluateContent;
        public RecyclerView mRlvEvaluateImg;
        public ImageView mIvEvaluteGoodImg;
        public TextView mTvEvaluateGoodTitle;
        public TextView mTvEvaluateGoodSize;
        public TextView mTvEvaluateGoodValues;

        public EvalueHolder(View itemView) {
            super(itemView);
            this.mIvEvaluteHear = (ImageView) itemView.findViewById(R.id.iv_evalute_hear);
            this.mTvEvaluteName = (TextView) itemView.findViewById(R.id.tv_evalute_name);
            this.mIvItemEvaluteStarOne = (ImageView) itemView.findViewById(R.id.iv_item_evalute_star_one);
            this.mIvItemEvaluteStarTwo = (ImageView) itemView.findViewById(R.id.iv_item_evalute_star_two);
            this.mIvItemEvaluteStarThree = (ImageView) itemView.findViewById(R.id.iv_item_evalute_star_three);
            this.mIvItemEvaluteStarFour = (ImageView) itemView.findViewById(R.id.iv_item_evalute_star_four);
            this.mIvItemEvaluteStarFive = (ImageView) itemView.findViewById(R.id.iv_item_evalute_star_five);
            this.mTvEvaluateTime = (TextView) itemView.findViewById(R.id.tv_evaluate_time);
            this.mTvEvaluateSize = (TextView) itemView.findViewById(R.id.tv_evaluate_size);
            this.mTvEvaluateContent = (TextView) itemView.findViewById(R.id.tv_evaluate_content);
            this.mRlvEvaluateImg = (RecyclerView) itemView.findViewById(R.id.rlv_evaluate_img);
            this.mIvEvaluteGoodImg = (ImageView) itemView.findViewById(R.id.iv_evalute_good_img);
            this.mTvEvaluateGoodTitle = (TextView) itemView.findViewById(R.id.tv_evaluate_good_title);
            this.mTvEvaluateGoodSize = (TextView) itemView.findViewById(R.id.tv_evaluate_good_size);
            this.mTvEvaluateGoodValues = (TextView) itemView.findViewById(R.id.tv_evaluate_good_values);

        }
    }

}
