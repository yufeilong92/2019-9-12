package com.tsyc.tianshengyoucai.adapter.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.view.FlowLayout;
import com.tsyc.tianshengyoucai.vo.GoodSpecification;
import com.tsyc.tianshengyoucai.vo.SelectVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/7 12:22
 * @Purpose :商品规格选择
 */
public class GoodSelectAdapter extends RecyclerView.Adapter<GoodSelectAdapter.ViewHolde> {
    private Context mContext;
    private List<GoodSpecification> mData;
    private final LayoutInflater mInflater;

    public GoodSelectAdapter(Context mContext, List<GoodSpecification> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void selectItemClick(GoodSpecification vo,boolean isSelect);

    }

    @NonNull
    @Override
    public ViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_dialog_select, null);
        ViewHolde holde = new ViewHolde(view);
        return holde;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolde holder, int position) {
        GoodSpecification vo = mData.get(position);
        holder.mTvDialogStyle.setText(vo.getName());
        List<String> data = vo.getData();
        bindViewData(holder, data, "", vo);
    }

    private void bindViewData(ViewHolde holder, List<String> data, String select, GoodSpecification vo) {
        holder.mFlyStyleContent.removeAllViews();
        List<SelectVo> selectVos = selectClear(data, select);
        for (int i = 0; i < selectVos.size(); i++) {
            SelectVo s = selectVos.get(i);
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_item_size, null);
            CheckBox cb = inflate.findViewById(R.id.cb_selct_color);
            cb.setText(s.getName());
            cb.setChecked(s.isSelect());
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        if (listener != null) {
                            String cm = cb.getText().toString();
                            GoodSpecification mVo=vo;
                            mVo.setSelectType(cm);
                            listener.selectItemClick(mVo,isChecked);
                        }
                        bindViewData(holder, data, s.getName(), vo);
                    } else {
                        if (listener != null) {
                            GoodSpecification mVo=vo;
                            mVo.setSelectType("");
                            listener.selectItemClick(mVo,isChecked);
                        }
                        bindViewData(holder, data, "", vo);
                    }
                }
            });
            holder.mFlyStyleContent.addView(inflate);
        }
    }

    private List<SelectVo> selectClear(List<String> data, String select) {
        List<SelectVo> list = new ArrayList<>();
        if (StringUtil.isEmpty(select)) {
            for (int i = 0; i < data.size(); i++) {
                String s = data.get(i);
                SelectVo selectVo = new SelectVo();
                selectVo.setName(s);
                selectVo.setSelect(false);
                list.add(selectVo);
            }
        } else {
            for (int i = 0; i < data.size(); i++) {
                String s = data.get(i);
                SelectVo selectVo = new SelectVo();
                selectVo.setName(s);
                selectVo.setSelect(s.equals(select));
                list.add(selectVo);
            }
        }
        return list;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolde extends RecyclerView.ViewHolder {
        public TextView mTvDialogStyle;
        public FlowLayout mFlyStyleContent;

        public ViewHolde(View itemView) {
            super(itemView);
            this.mTvDialogStyle = (TextView) itemView.findViewById(R.id.tv_dialog_style);
            this.mFlyStyleContent = (FlowLayout) itemView.findViewById(R.id.fly_style_content);
        }
    }


}
