package com.tsyc.tianshengyoucai.adapter.bank;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.vo.SelectVo;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/19 16:51
 * @Purpose :支付方式适配器
 */
public class PayValuesAdapter extends RecyclerView.Adapter<PayValuesAdapter.PayTypeHolder> {
    private Context mContext;
    private List<String> mData;
    private final LayoutInflater mInflater;
    private List<SelectVo> mSelevtVo;

    public PayValuesAdapter(Context mContext, List<String> mData, List<SelectVo> list) {
        this.mContext = mContext;
        this.mData = mData;
        this.mSelevtVo = list;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(int position, String com, SelectVo selectVo);

    }

    public void refresh(List<SelectVo> mSelevtVo) {
        this.mSelevtVo = mSelevtVo;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public PayTypeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_pay_values, null);
        return new PayTypeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PayTypeHolder holder, int position) {
        String com = mData.get(position);
        holder.mTvPayValues.setText(com);
        SelectVo selectVo = mSelevtVo.get(position);
        holder.mTvPayValues.setTextColor(mContext.getResources().getColor(
                selectVo.isSelect() ? R.color.white : R.color.color_444A53
        ));
        LinearLayout.LayoutParams params =
                (LinearLayout.LayoutParams) holder.mRlTypeVaules.getLayoutParams();
        if (position % 2 == 0) {
            params.setMargins(40, 16, 16, 0);
        } else {
            params.setMargins(16, 16, 40, 0);
        }
        holder.mRlTypeVaules.setLayoutParams(params);
        holder.mTvPayValues.setBackgroundResource(selectVo.isSelect() ? R.drawable.gm_4_red_bg_circle :
                R.drawable.gm_4_gray_bg_circle);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.itemClick(position, com, selectVo);
                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class PayTypeHolder extends RecyclerView.ViewHolder {
        public TextView mTvPayValues;
        public RelativeLayout mRlTypeVaules;

        public PayTypeHolder(View itemView) {
            super(itemView);
            this.mTvPayValues = (TextView) itemView.findViewById(R.id.tv_pay_values);
            this.mRlTypeVaules = (RelativeLayout) itemView.findViewById(R.id.rl_type_vlaues);

        }
    }

}
