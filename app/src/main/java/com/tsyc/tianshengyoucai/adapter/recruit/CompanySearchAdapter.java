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
import com.tsyc.tianshengyoucai.vo.CompanySearchVo;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/3 14:03
 * @Purpose :公司适配器
 */
public class CompanySearchAdapter extends RecyclerView.Adapter<CompanySearchAdapter.CompanySearchHolder> {

    private Context mContext;
    private List<CompanySearchVo.ResultBean> mData;
    private final LayoutInflater mInflater;

    public CompanySearchAdapter(Context mContext, List<CompanySearchVo.ResultBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void itemClick(CompanySearchVo.ResultBean vo);

    }

    @NonNull
    @Override
    public CompanySearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_company_search, null);
        return new CompanySearchHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanySearchHolder holder, int position) {
        CompanySearchVo.ResultBean vo = mData.get(position);
        GlideUtil.getSingleton().loadQuadRangleImager(mContext, holder.mIvCompanysearchLogo, vo.getLogo());
        holder.mTvCompanysearchContent.setText(vo.getFull_name());
        holder.mTvCompanyStatus.setText(vo.getStatus_text());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.itemClick(vo);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class CompanySearchHolder extends RecyclerView.ViewHolder {
        public ImageView mIvCompanysearchLogo;
        public TextView mTvCompanysearchContent;
        public TextView mTvCompanyStatus;

        public CompanySearchHolder(View itemView) {
            super(itemView);
            this.mIvCompanysearchLogo = (ImageView) itemView.findViewById(R.id.iv_companysearch_logo);
            this.mTvCompanysearchContent = (TextView) itemView.findViewById(R.id.tv_companysearch_content);
            this.mTvCompanyStatus = (TextView) itemView.findViewById(R.id.tv_company_status);
        }
    }


}
