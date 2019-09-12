package com.tsyc.tianshengyoucai.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.vo.AddressListBeanVo;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/9 10:38
 * @Purpose :地址管理
 */
public class AddressAapter extends RecyclerView.Adapter<AddressAapter.AddressHolder> {


    private Context mContext;
    private List<AddressListBeanVo> mData;
    private final LayoutInflater mInflater;


    public AddressAapter(Context mContext, List<AddressListBeanVo> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void addressItemClick(int position, AddressListBeanVo vo);
        void deleteItemClick(int position, AddressListBeanVo vo);

    }

    @NonNull
    @Override
    public AddressHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_address_layout, null);
        return new AddressHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressHolder holder, int position) {
        AddressListBeanVo vo = mData.get(position);
        holder.mTvAdressName.setText(vo.getTrue_name());
        holder.mTvAddressPhone.setText(vo.getMob_phone());
        holder.mTvAddressAddress.setText(vo.getArea_info() + vo.getAddress());
        holder.mTvAddressDefault.setVisibility(vo.getIs_default().equals("1") ? View.VISIBLE : View.GONE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.addressItemClick(position, vo);
                }
            }
        });
        holder.mTvAddressDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.deleteItemClick(position,vo);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class AddressHolder extends RecyclerView.ViewHolder {
        public TextView mTvAdressName;
        public TextView mTvAddressPhone;
        public TextView mTvAddressDefault;
        public TextView mTvAddressAddress;
        public ImageView mIvAddressMake;
        public TextView mTvAddressDelete;

        public AddressHolder(View itemView) {
            super(itemView);
            this.mTvAdressName = (TextView) itemView.findViewById(R.id.tv_adress_name);
            this.mTvAddressPhone = (TextView) itemView.findViewById(R.id.tv_address_phone);
            this.mTvAddressDefault = (TextView) itemView.findViewById(R.id.tv_address_default);
            this.mTvAddressAddress = (TextView) itemView.findViewById(R.id.tv_address_address);
            this.mIvAddressMake = (ImageView) itemView.findViewById(R.id.iv_address_make);
            this.mTvAddressDelete = (TextView) itemView.findViewById(R.id.tv_address_delete);
        }
    }

}
