package com.tsyc.tianshengyoucai.adapter.bank;

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
import com.tsyc.tianshengyoucai.vo.PayTypeVo;
import com.tsyc.tianshengyoucai.vo.SelectVo;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/19 16:51
 * @Purpose :支付方式适配器
 */
public class PayTypeAdapter extends RecyclerView.Adapter<PayTypeAdapter.PayTypeHolder> {
    private Context mContext;
    private List<PayTypeVo.ResultBean> mData;
    private final LayoutInflater mInflater;
    private List<SelectVo> mSelevtVo;

    public PayTypeAdapter(Context mContext, List<PayTypeVo.ResultBean> mData, List<SelectVo> list) {
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
        void itemClick(int position, PayTypeVo.ResultBean vo, SelectVo selectVo);

    }

    public void refresh(List<SelectVo> mSelevtVo) {
        this.mSelevtVo = mSelevtVo;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public PayTypeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_pay_type, null);
        return new PayTypeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PayTypeHolder holder, int position) {
        PayTypeVo.ResultBean vo = mData.get(position);
        SelectVo selectVo = mSelevtVo.get(position);
        GlideUtil.getSingleton().loadQuadRangleImager(mContext, holder.mIvPayHear, vo.getLogo());
        holder.mIvPayType.setImageResource(selectVo.isSelect() ? R.mipmap.jft_but_sexselection : R.mipmap.jft_but_unselectedgender);
        holder.mTvPayTypeName.setText(vo.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.itemClick(position, vo, selectVo);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class PayTypeHolder extends RecyclerView.ViewHolder {
        public ImageView mIvPayHear;
        public TextView mTvPayTypeName;
        public ImageView mIvPayType;

        public PayTypeHolder(View itemView) {
            super(itemView);
            this.mIvPayHear = (ImageView) itemView.findViewById(R.id.iv_pay_hear);
            this.mTvPayTypeName = (TextView) itemView.findViewById(R.id.tv_pay_type_name);
            this.mIvPayType = (ImageView) itemView.findViewById(R.id.iv_pay_type);
        }
    }


}
