package com.tsyc.tianshengyoucai.model.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.HomeListBean;
import com.tsyc.tianshengyoucai.utils.ImageLoader;
import com.tsyc.tianshengyoucai.view.StarBar;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/22
 * File description： 首页附近商家
 */
public class HomeShopAdapter extends BaseQuickAdapter<HomeListBean.ResultBean.StoreBean, BaseViewHolder> {

    public HomeShopAdapter(@Nullable List<HomeListBean.ResultBean.StoreBean> data) {
        super(R.layout.layout_home_shop, data);
    }

    public HomeShopAdapter() {
        super(R.layout.layout_home_shop);
    }

    @Override
    protected void convert(BaseViewHolder holder, HomeListBean.ResultBean.StoreBean storeBean) {
        ImageView mIvIcon = holder.getView(R.id.iv_icon);
        TextView mTvName = holder.getView(R.id.tv_name);
        StarBar mStarBar = holder.getView(R.id.sb_starBar);
        TextView mTvShareBack = holder.getView(R.id.tv_share_back);
        TextView mTvShopAddress = holder.getView(R.id.tv_shop_address);
        TextView mTvItemType1 = holder.getView(R.id.tv_item_type1);
        TextView mTvItemType2 = holder.getView(R.id.tv_item_type2);
        TextView mTvItemType3 = holder.getView(R.id.tv_item_type3);

        String store_name = storeBean.getStore_name();
        String image = storeBean.getImage();
        String address = storeBean.getAddress();
        String share_info = storeBean.getShare_info();
        String category_tab = storeBean.getCategory_tab();
        String online_tab = storeBean.getOnline_tab();
        String red_tab = storeBean.getRed_tab();
        int store_grade = storeBean.getStore_grade();


        ImageLoader.loadNormal(mContext,image,mIvIcon);
        mTvName.setText(store_name);
        mTvShopAddress.setText(address);
        mTvShareBack.setVisibility(TextUtils.isEmpty(share_info)? View.GONE:View.VISIBLE);
        mTvShareBack.setText(share_info);
        mStarBar.setStarMark(store_grade);
        mStarBar.setCanTouch(false);
        mStarBar.setIntegerMark(true);

        mTvItemType1.setText(category_tab);
        mTvItemType2.setText(online_tab);
        mTvItemType3.setText(red_tab);


    }
}
