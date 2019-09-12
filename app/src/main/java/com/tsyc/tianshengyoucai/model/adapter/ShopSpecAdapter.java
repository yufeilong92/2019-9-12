package com.tsyc.tianshengyoucai.model.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.listener.MyTextWatcherListener;
import com.tsyc.tianshengyoucai.model.bean.ShopSpecBean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/30
 * File description：
 */
public class ShopSpecAdapter extends BaseQuickAdapter<ShopSpecBean.ShopSpecItemBean, BaseViewHolder> {

    public ShopSpecAdapter(@Nullable List<ShopSpecBean.ShopSpecItemBean> data) {
        super(R.layout.layout_shop_spec_item, data);
    }

    public String isInner;

    public String getIsInner() {
        return isInner;
    }

    public void setIsInner(String isInner) {
        this.isInner = isInner;
    }

    public ShopSpecAdapter() {
        super(R.layout.layout_shop_spec_item);
    }

    @Override
    protected void convert(BaseViewHolder holder, ShopSpecBean.ShopSpecItemBean item) {
        EditText mEtSpec = holder.getView(R.id.et_spec);
        EditText mEtPrice = holder.getView(R.id.et_price);
        EditText mEtCount = holder.getView(R.id.et_count);
        EditText mEtInner = holder.getView(R.id.et_inner_price);

        String isInner = getIsInner();
        if (isInner != null && isInner.equals("1")) {
            mEtInner.setVisibility(View.VISIBLE);
            mEtInner.addTextChangedListener(new MyTextWatcherListener() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    super.onTextChanged(s, start, before, count);
                    String trim = s.toString().trim();

                    if (adapterInterface != null) {
                        adapterInterface.onTextChanged(holder.getLayoutPosition(), 4, trim);
                    }
                }
            });
        }
        holder.addOnClickListener(R.id.rl_delete);

        mEtSpec.addTextChangedListener(new MyTextWatcherListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                super.onTextChanged(s, start, before, count);
                String trim = s.toString().trim();
                if (adapterInterface != null) {
                    adapterInterface.onTextChanged(holder.getLayoutPosition(), 1, trim);
                }
            }
        });

        mEtPrice.addTextChangedListener(new MyTextWatcherListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                super.onTextChanged(s, start, before, count);
                String trim = s.toString().trim();
                if (adapterInterface != null) {
                    adapterInterface.onTextChanged(holder.getLayoutPosition(), 2, trim);
                }
            }
        });
        mEtCount.addTextChangedListener(new MyTextWatcherListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                super.onTextChanged(s, start, before, count);
                String trim = s.toString().trim();
                if (adapterInterface != null) {
                    adapterInterface.onTextChanged(holder.getLayoutPosition(), 3, trim);
                }
            }
        });
        mEtSpec.setText(item.getSpec_value());
        mEtPrice.setText(item.getPrice());
        mEtCount.setText(item.getStock_num());
        mEtInner.setText(item.getInner_price());

    }

    private ShopSpecAdapterInterface adapterInterface;

    public void setAdapterInterface(ShopSpecAdapterInterface adapterInterface) {
        this.adapterInterface = adapterInterface;
    }

    public interface ShopSpecAdapterInterface {
        /**
         * @param position
         * @param type     1 sp  2 prc 3 cnt 4 inner prc
         * @param s
         */
        void onTextChanged(int position, int type, String s);
    }


}
