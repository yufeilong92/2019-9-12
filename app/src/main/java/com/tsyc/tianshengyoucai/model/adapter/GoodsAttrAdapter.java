package com.tsyc.tianshengyoucai.model.adapter;

import android.support.annotation.Nullable;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.listener.MyTextWatcherListener;
import com.tsyc.tianshengyoucai.model.bean.GoodsTemplateBean;
import com.tsyc.tianshengyoucai.model.bean.ShopAttrBean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/30
 * File description： 商品属性 adapter
 */
public class GoodsAttrAdapter extends BaseQuickAdapter<ShopAttrBean, BaseViewHolder> {

    public GoodsAttrAdapter(@Nullable List<ShopAttrBean> data) {
        super(R.layout.layout_goods_attr_item, data);
    }

    public GoodsAttrAdapter() {
        super(R.layout.layout_goods_attr_item);
    }

    @Override
    protected void convert(BaseViewHolder holder, ShopAttrBean item) {
        EditText mEtKey = holder.getView(R.id.et_key);
        EditText mEtValue = holder.getView(R.id.et_value);

        mEtKey.addTextChangedListener(new MyTextWatcherListener(){
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                super.onTextChanged(s, start, before, count);
                String trim = s.toString().trim();
                if (adapterInterface != null){
                    adapterInterface.onTextChanged(holder.getLayoutPosition(),1,trim);
                }
            }
        });

        mEtValue.addTextChangedListener(new MyTextWatcherListener(){
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String trim = s.toString().trim();
                if (adapterInterface != null){
                    adapterInterface.onTextChanged(holder.getLayoutPosition(),2,trim);
                }
            }
        });

        holder.addOnClickListener(R.id.rl_delete);
        mEtKey.setText(item.getName());
        mEtValue.setText(item.getValue());

    }

    private ShopSpecAdapterInterface adapterInterface;

    public void setAdapterInterface(ShopSpecAdapterInterface adapterInterface) {
        this.adapterInterface = adapterInterface;
    }

    public interface  ShopSpecAdapterInterface{
        /**
         *
         * @param position
         * @param type 1 sp  2 prc 3 cnt 4 inner prc
         * @param s
         */
        void onTextChanged(int position, int type, String s);
    }



}
