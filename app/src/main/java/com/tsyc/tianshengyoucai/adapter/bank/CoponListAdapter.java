package com.tsyc.tianshengyoucai.adapter.bank;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.utils.TextUtil;
import com.tsyc.tianshengyoucai.vo.CouponListBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/17 17:45
 * @Purpose :优惠卷
 */
public class CoponListAdapter extends RecyclerView.Adapter<CoponListAdapter.ConponViewHolde> {

    private Context mContext;
    private List<CouponListBean> mData;
    private final LayoutInflater mInflater;

    public CoponListAdapter(Context mContext, List<CouponListBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(int position, CouponListBean vo);

    }

    @NonNull
    @Override
    public ConponViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_coponlist, null);
        return new ConponViewHolde(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConponViewHolde holder, int position) {
        CouponListBean vo = mData.get(position);
        TextUtil.bindTextViewWHine(holder.mTvCoponName, "");
        holder.mTvCoponTime.setText(vo.getVoucher_end_date());
        holder.mTvCoponValues.setText(vo.getVoucher_price() + "");
        holder.mTvCoponContent.setText("满" + vo.getVoucher_limit() + "元使用");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
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

    public class ConponViewHolde extends RecyclerView.ViewHolder {
        public TextView mTvCoponValues;
        public TextView mTvCoponContent;
        public LinearLayout mLlCoponBg;
        public TextView mTvCoponName;
        public TextView mTvCoponTime;
        public ImageView mIvCoponGo;

        public ConponViewHolde(View itemView) {
            super(itemView);
            this.mTvCoponValues = (TextView) itemView.findViewById(R.id.tv_copon_values);
            this.mTvCoponContent = (TextView) itemView.findViewById(R.id.tv_copon_content);
            this.mLlCoponBg = (LinearLayout) itemView.findViewById(R.id.ll_copon_bg);
            this.mTvCoponName = (TextView) itemView.findViewById(R.id.tv_copon_name);
            this.mTvCoponTime = (TextView) itemView.findViewById(R.id.tv_copon_time);
            this.mIvCoponGo = (ImageView) itemView.findViewById(R.id.iv_copon_go);
        }
    }


}
