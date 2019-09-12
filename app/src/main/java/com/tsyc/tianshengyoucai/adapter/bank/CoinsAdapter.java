package com.tsyc.tianshengyoucai.adapter.bank;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.vo.CoisnItemVo;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/19 18:45
 * @Purpose :金币适配器
 */
public class CoinsAdapter extends RecyclerView.Adapter<CoinsAdapter.CoinViewHodle> {
    private Context mContext;
    private List<CoisnItemVo> mData;
    private final LayoutInflater mInflater;

    public CoinsAdapter(Context mContext, List<CoisnItemVo> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(int position, CoisnItemVo vo);

    }

    @NonNull
    @Override
    public CoinViewHodle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_cois_layout, null);
        return new CoinViewHodle(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoinViewHodle holder, int position) {
        CoisnItemVo vo = mData.get(position);
        holder.mTvCoisnTitle.setText(vo.getNote());
        holder.mTvCoisnTime.setText(vo.getDate());
        if (StringUtil.isEmpty(vo.getMoney())) {
            holder.mTvCoisnValues.setText(vo.getMoney());
        } else {
            double aDouble = Double.parseDouble(vo.getMoney());
            holder.mTvCoisnValues.setTextColor(mContext.getResources().getColor(
                    aDouble <= 0 ? R.color.color_444A53 : R.color.tab_color
            ));
            holder.mTvCoisnValues.setText(vo.getMoney());
        }
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

    public class CoinViewHodle extends RecyclerView.ViewHolder {
        public TextView mTvCoisnTitle;
        public TextView mTvCoisnTime;
        public TextView mTvCoisnValues;

        public CoinViewHodle(View itemView) {
            super(itemView);
            this.mTvCoisnTitle = (TextView) itemView.findViewById(R.id.tv_coisn_title);
            this.mTvCoisnTime = (TextView) itemView.findViewById(R.id.tv_coisn_time);
            this.mTvCoisnValues = (TextView) itemView.findViewById(R.id.tv_coisn_values);
        }
    }

}
