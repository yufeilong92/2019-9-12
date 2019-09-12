package com.tsyc.tianshengyoucai.model.adapter;

import android.support.annotation.Nullable;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.listener.MyTextWatcherListener;
import com.tsyc.tianshengyoucai.model.bean.GoodsTemplateBean;
import com.tsyc.tianshengyoucai.model.bean.ShopSpecBean;
import com.youth.xframe.utils.log.XLog;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/30
 * File description： 商品 模板
 */
public class GoodsTemplateAdapter extends BaseQuickAdapter<GoodsTemplateBean.ResultBean.TempleteBean, BaseViewHolder> {

    public GoodsTemplateAdapter(@Nullable List<GoodsTemplateBean.ResultBean.TempleteBean> data) {
        super(R.layout.layout_goods_template_item, data);
    }

    public GoodsTemplateAdapter() {
        super(R.layout.layout_goods_template_item);
    }

    @Override
    protected void convert(BaseViewHolder holder, GoodsTemplateBean.ResultBean.TempleteBean item) {
        EditText mEtTemplate = holder.getView(R.id.et_template);
        TextView mTvName = holder.getView(R.id.tv_name);
//        mTvName.addTextChangedListener(new MyTextWatcherListener(){
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                String trim = s.toString().trim();
//                if (adapterInterface != null){
//                    adapterInterface.onTextChanged(holder.getLayoutPosition(),2,trim);
//                }
//            }
//        });

        String type = item.getType_desc();
        mEtTemplate.setHint("请输入 \""+type+"\" 类型的值");
        mEtTemplate.setText(item.getValue());
        mTvName.setText(item.getName());

        if (!TextUtils.isEmpty(item.getValue())){
            if (adapterInterface != null) {
                adapterInterface.onTextChanged(holder.getLayoutPosition(), 1,item.getName(), item.getValue());
            }
        }
        mEtTemplate.addTextChangedListener(new MyTextWatcherListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                super.onTextChanged(s, start, before, count);

                    String trim = s.toString().trim();
                if (TextUtils.isEmpty(trim)){
                    if (!TextUtils.isEmpty(item.getValue())){
                        trim = item.getValue();
                    }
                }
                if (adapterInterface != null) {
                    adapterInterface.onTextChanged(holder.getLayoutPosition(), 1,item.getName(), trim);
                }
            }
        });
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
        void onTextChanged(int position, int type,String name, String s);
    }


}
