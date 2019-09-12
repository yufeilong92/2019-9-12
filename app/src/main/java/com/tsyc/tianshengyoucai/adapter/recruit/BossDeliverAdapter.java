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
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.tsyc.tianshengyoucai.vo.DeliverBossVo;
import com.tsyc.tianshengyoucai.vo.GmSelectBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/4 17:32
 * @Purpose : 查看
 */
public class BossDeliverAdapter extends RecyclerView.Adapter<BossDeliverAdapter.BossDeliveHodler> {
    private Context mContext;
    private List<DeliverBossVo.ResultBean.DataBean> mData;
    private final LayoutInflater mInflater;

    public BossDeliverAdapter(Context mContext, List<DeliverBossVo.ResultBean.DataBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(DeliverBossVo.ResultBean.DataBean vo);

    }


    @NonNull
    @Override
    public BossDeliveHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_delivery_boss, null);
        return new BossDeliveHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BossDeliveHodler holder, int position) {
        DeliverBossVo.ResultBean.DataBean vo = mData.get(position);
        DeliverBossVo.ResultBean.DataBean.CvBean cv = vo.getCv();
        if (cv != null) {
            GlideUtil.getSingleton().loadQuadRangleImager(mContext, holder.mIvItemBossDeliverHear, cv.getAvatar());
            holder.mTvItemBossDeliverName.setText(cv.getUsername());
        }
        GmSelectBean sex = cv.getSex();
        if (sex != null)
            holder.mTvItemBossDeliverSex.setText("/" + sex.getDesc());
        GmSelectBean status = vo.getStatus();
        if (status != null)
            holder.mTvItemBossDeliverStatus.setText(status.getDesc());
        StringBuffer buffer = new StringBuffer();

        GmSelectBean expectedPosition = cv.getExpected_position();
        if (expectedPosition != null) {
            buffer.append(expectedPosition.getDesc());
            buffer.append(" | ");
            buffer.append("工作" + cv.getExperience() + "年");

        }
        GmSelectBean expected_salary = cv.getExpected_salary();
        if (expected_salary != null) {
            buffer.append(" | ");
            buffer.append(expected_salary.getDesc());
        }
        holder.mTvItemBossDeliverContentOne.setText(buffer.toString());

        StringBuffer bufferOne = new StringBuffer();
        bufferOne.append(cv.getAge());

        GmSelectBean education = cv.getHighest_education();
        if (education != null) {
            bufferOne.append(" | ");
            bufferOne.append(education.getDesc());
            bufferOne.append(" | ");

        }
        GmSelectBean city = cv.getWork_city();
        if (city != null)
            bufferOne.append(city.getDesc());
        holder.mTvItemBossDeliverContentTwo.setText(bufferOne.toString());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.itemClick(vo);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class BossDeliveHodler extends RecyclerView.ViewHolder {
        public ImageView mIvItemBossDeliverHear;
        public TextView mTvItemBossDeliverName;
        public TextView mTvItemBossDeliverSex;
        public TextView mTvItemBossDeliverStatus;
        public TextView mTvItemBossDeliverContentOne;
        public TextView mTvItemBossDeliverContentTwo;

        public BossDeliveHodler(View itemView) {
            super(itemView);
            this.mIvItemBossDeliverHear = (ImageView) itemView.findViewById(R.id.iv_item_boss_deliver_hear);
            this.mTvItemBossDeliverName = (TextView) itemView.findViewById(R.id.tv_item_boss_deliver_name);
            this.mTvItemBossDeliverSex = (TextView) itemView.findViewById(R.id.tv_item_boss_deliver_sex);
            this.mTvItemBossDeliverStatus = (TextView) itemView.findViewById(R.id.tv_item_boss_deliver_status);
            this.mTvItemBossDeliverContentOne = (TextView) itemView.findViewById(R.id.tv_item_boss_deliver_content_one);
            this.mTvItemBossDeliverContentTwo = (TextView) itemView.findViewById(R.id.tv_item_boss_deliver_content_two);

        }
    }


}
