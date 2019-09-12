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
import com.tsyc.tianshengyoucai.vo.RedVo;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/8 20:54
 * @Purpose : 优惠卷
 */
public class MyCopuonAdapter extends RecyclerView.Adapter<MyCopuonAdapter.PackViewholer> {

    private Context mContext;
    private List<RedVo.ResultBean.DataBean> mData;
    private final LayoutInflater mInflater;

    public MyCopuonAdapter(Context mContext, List<RedVo.ResultBean.DataBean> mData ) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(int position, RedVo.ResultBean.DataBean vo);
    }

    @NonNull
    @Override
    public PackViewholer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_packer_layout, null);
        return new PackViewholer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PackViewholer holder, int position) {
        RedVo.ResultBean.DataBean vo = mData.get(position);
        holder.mTvRedPackeName.setText(vo.getStore_name());
        StringBuilder builder = new StringBuilder();
        builder.append(vo.getVoucher_start_date());
        builder.append("-");
        builder.append(vo.getVoucher_end_date());
        holder.mTvRedPackeTime.setText(builder.toString());
        holder.mTvRedPackeContent.setText(vo.getVoucher_limit());
        holder.mTvRedPackeValues.setText(vo.getVoucher_price() + "元");
        holder.mIvRedPackeGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vo.getVoucher_state() == 1) {
                    if (listener != null) {
                        listener.itemClick(position,vo);
                    }
                }
            }
        });
        holder.mTvRedPackeTime.setTextColor(getTvGoStatus(vo.getVoucher_state()));
        holder.mTvRedPackeName.setTextColor(getTvGoStatus(vo.getVoucher_state()));
        holder.mLlRedPackBg.setBackground(mContext.getResources().getDrawable(getBGGoStatus(vo.getVoucher_state())));
        holder.mIvRedPackeGo.setImageResource(getGoStatus(vo.getVoucher_state()));
    }

    private int getGoStatus(int voucher_state) {
        int mId = R.mipmap.jft_img_theredenvelopehasexpired;
        switch (voucher_state) {
            case 1://待使用
                mId = R.mipmap.jft_but_useredenvelopes;
                break;
            case 2://已使用
                mId = R.mipmap.jft_img_redenvelopeinuse;
                break;
            case 3://已失效
                mId = R.mipmap.jft_img_theredenvelopehasexpired;
                break;
            case 4://已回收
                mId = R.mipmap.jft_img_theredenvelopehasexpired;
                break;
            default:
                break;
        }
        return mId;

    }
    private int getBGGoStatus(int voucher_state) {
        int mId = R.mipmap.jft_img_redenvelopehead;
        switch (voucher_state) {
            case 1://待使用
                mId = R.mipmap.jft_img_redenvelopehead;
                break;
            case 2://已使用
                mId = R.mipmap.jft_img_redenvelopeheadused;
                break;
            case 3://已失效
                mId = R.mipmap.jft_img_redenvelopeheadused;
                break;
            case 4://已回收
                mId = R.mipmap.jft_img_redenvelopeheadused;
                break;
            default:
                break;
        }
        return mId;

    }
    private int getTvGoStatus(int voucher_state) {
        int mId = R.color.color_444A53;
        switch (voucher_state) {
            case 1://待使用
                mId = R.color.color_444A53;
                break;
            case 2://已使用
                mId = R.color.lost_gray;
                break;
            case 3://已失效
                mId = R.color.lost_gray;
                break;
            case 4://已回收
                mId = R.color.lost_gray;
                break;
            default:
                break;
        }
        return mId;

    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class PackViewholer extends RecyclerView.ViewHolder {
        public TextView mTvRedPackeValues;
        public TextView mTvRedPackeContent;
        public LinearLayout mLlRedPackBg;
        public TextView mTvRedPackeName;
        public TextView mTvRedPackeTime;
        public ImageView mIvRedPackeGo;

        public PackViewholer(View itemView) {
            super(itemView);
            this.mTvRedPackeValues = (TextView) itemView.findViewById(R.id.tv_red_packe_values);
            this.mTvRedPackeContent = (TextView) itemView.findViewById(R.id.tv_red_packe_content);
            this.mLlRedPackBg = (LinearLayout) itemView.findViewById(R.id.ll_red_pack_bg);
            this.mTvRedPackeName = (TextView) itemView.findViewById(R.id.tv_red_packe_name);
            this.mTvRedPackeTime = (TextView) itemView.findViewById(R.id.tv_red_packe_time);
            this.mIvRedPackeGo = (ImageView) itemView.findViewById(R.id.iv_red_packe_go);

        }
    }


}
