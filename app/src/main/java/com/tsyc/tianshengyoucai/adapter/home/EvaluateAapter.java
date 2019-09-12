package com.tsyc.tianshengyoucai.adapter.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.vo.EvaluateVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/12 19:37
 * @Purpose :评价适配器
 */
public class EvaluateAapter extends RecyclerView.Adapter<EvaluateAapter.EvaluateViewHodler> {

    private Context mContext;
    private List<EvaluateVo.ResultBean.ListBean.DataBean> mData;
    private final LayoutInflater mInflater;

    public EvaluateAapter(Context mContext, List<EvaluateVo.ResultBean.ListBean.DataBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(int position, EvaluateVo.ResultBean.ListBean.DataBean vo);

        void imgClick(int postion, List<String> list);
    }

    @NonNull
    @Override
    public EvaluateViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_evaluate, null);
        return new EvaluateViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EvaluateViewHodler holder, int position) {
        EvaluateVo.ResultBean.ListBean.DataBean vo = mData.get(position);
        GlideUtil.getSingleton().LoadImager(mContext, holder.mIvEvalutePicture, vo.getMember_avatar());
        holder.mTvEvaluteName.setText(vo.getMember_name());
        List<ImageView> imgs = new ArrayList<>();
        imgs.add(holder.mIvItemEvaluteStarOne);
        imgs.add(holder.mIvItemEvaluteStarTwo);
        imgs.add(holder.mIvItemEvaluteStarThree);
        imgs.add(holder.mIvItemEvaluteStarFour);
        imgs.add(holder.mIvItemEvaluteStarFive);
        showStart(imgs, vo.getGeval_scores());

        holder.mTvEvaluateContent.setText(vo.getGeval_content());
        StringBuffer buffer = new StringBuffer();
        buffer.append(vo.getDesc());
        holder.mTvEvaluateSize.setText(buffer.toString());
        List<String> geval_image = vo.getGeval_image();
        if (geval_image == null || geval_image.isEmpty()) {
            holder.mRlvEvaluateImg.setVisibility(View.GONE);
        } else {
            holder.mRlvEvaluateImg.setVisibility(View.VISIBLE);
            initImagerAdapter(geval_image, holder.mRlvEvaluateImg);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.itemClick(position, vo);
                }
            }
        });
    }

    private void initImagerAdapter(List<String> geval_image, RecyclerView mRlvEvaluateImg) {
        RecyclerUtil.setGridManage(mContext, mRlvEvaluateImg,3);
        EvaluateImgAdapter adapter = new EvaluateImgAdapter(mContext, geval_image);
        mRlvEvaluateImg.setAdapter(adapter);
        adapter.setListener(new EvaluateImgAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, String s) {
                if (listener != null) {
                   listener.imgClick(position,geval_image);
                }
            }
        });

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

    public class EvaluateViewHodler extends RecyclerView.ViewHolder {
        public ImageView mIvEvalutePicture;
        public TextView mTvEvaluteName;
        public ImageView mIvItemEvaluteStarOne;
        public ImageView mIvItemEvaluteStarTwo;
        public ImageView mIvItemEvaluteStarThree;
        public ImageView mIvItemEvaluteStarFour;
        public ImageView mIvItemEvaluteStarFive;
        public TextView mTvEvaluateSize;
        public TextView mTvEvaluateContent;
        public RecyclerView mRlvEvaluateImg;

        public EvaluateViewHodler(View itemView) {
            super(itemView);
            this.mIvEvalutePicture = (ImageView) itemView.findViewById(R.id.iv_evalute_picture);
            this.mTvEvaluteName = (TextView) itemView.findViewById(R.id.tv_evalute_name);
            this.mIvItemEvaluteStarOne = (ImageView) itemView.findViewById(R.id.iv_item_evalute_star_one);
            this.mIvItemEvaluteStarTwo = (ImageView) itemView.findViewById(R.id.iv_item_evalute_star_two);
            this.mIvItemEvaluteStarThree = (ImageView) itemView.findViewById(R.id.iv_item_evalute_star_three);
            this.mIvItemEvaluteStarFour = (ImageView) itemView.findViewById(R.id.iv_item_evalute_star_four);
            this.mIvItemEvaluteStarFive = (ImageView) itemView.findViewById(R.id.iv_item_evalute_star_five);
            this.mTvEvaluateSize = (TextView) itemView.findViewById(R.id.tv_evaluate_size);
            this.mTvEvaluateContent = (TextView) itemView.findViewById(R.id.tv_evaluate_content);
            this.mRlvEvaluateImg = (RecyclerView) itemView.findViewById(R.id.rlv_evaluate_img);

        }
    }


}
