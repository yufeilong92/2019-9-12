package com.tsyc.tianshengyoucai.adapter.type;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.vo.GoodTypeVo;
import com.tsyc.tianshengyoucai.vo.SelectVo;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/12 16:21
 * @Purpose :
 */
public class GoodLiftTypeAdapter extends RecyclerView.Adapter<GoodLiftTypeAdapter.TypeViewHolder> {
    private Context mContext;
    private List<GoodTypeVo.ResultBean> mData;
    private final LayoutInflater mInflater;
    private List<SelectVo> mSelectDatas;

    public GoodLiftTypeAdapter(Context mContext, List<GoodTypeVo.ResultBean> mData, List<SelectVo> mSelect) {
        this.mContext = mContext;
        this.mSelectDatas = mSelect;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(int position, GoodTypeVo.ResultBean vo);

    }

    public void dataRefresh(List<SelectVo> mSelect) {
        this.mSelectDatas = mSelect;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.item_good_type, null);
        return new TypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeViewHolder holder, int position) {
        GoodTypeVo.ResultBean vo = mData.get(position);
        if (mSelectDatas != null && !mSelectDatas.isEmpty()) {
            SelectVo selectVo = mSelectDatas.get(position);
            holder.mViewTag.setVisibility(selectVo.isSelect() ? View.VISIBLE : View.INVISIBLE);
            holder.mTvHomeTypeTitle.setTextColor(mContext.getResources().getColor(
                    selectVo.isSelect() ? R.color.tab_color : R.color.color_444A53
            ));
            holder.mLlTypeBg.setBackgroundColor(mContext.getResources().getColor(
                    selectVo.isSelect() ? R.color.white : R.color.main_color
            ));
        }
        if (!StringUtil.isEmpty(vo.getGc_name())){
            holder.mTvHomeTypeTitle.setText(vo.getGc_name().trim());
        }else {
            holder.mTvHomeTypeTitle.setText("");
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

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class TypeViewHolder extends RecyclerView.ViewHolder {
        public View mViewTag;
        public TextView mTvHomeTypeTitle;
        public LinearLayout mLlTypeBg;

        public TypeViewHolder(View itemView) {
            super(itemView);
            this.mViewTag = (View) itemView.findViewById(R.id.view_tag);
            this.mTvHomeTypeTitle = (TextView) itemView.findViewById(R.id.tv_home_type_title);
            this.mLlTypeBg = (LinearLayout) itemView.findViewById(R.id.ll_type_bg);

        }
    }


}
