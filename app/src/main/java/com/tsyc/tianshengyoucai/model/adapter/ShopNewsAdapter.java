package com.tsyc.tianshengyoucai.model.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Config;
import com.tsyc.tianshengyoucai.model.bean.ShopNewsListBean;
import com.tsyc.tianshengyoucai.utils.ImageLoader;
import com.youth.xframe.utils.log.XLog;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/23
 * File description：
 */
public class ShopNewsAdapter extends BaseMultiItemQuickAdapter<ShopNewsListBean.ResultBean, BaseViewHolder> {

    public static final int TYPE_1 = 0;
    public static final int TYPE_2 = 1;
    public static final int TYPE_3 = 3;

    public ShopNewsAdapter(List<ShopNewsListBean.ResultBean> data) {
        super(data);
        addItemType(TYPE_1, R.layout.shop_news_normal_item);
        addItemType(TYPE_2, R.layout.shop_news_right_item);
        addItemType(TYPE_3, R.layout.shop_news_normal_item);
    }

    @Override
    protected void convert(BaseViewHolder holder, ShopNewsListBean.ResultBean resultBean) {
       // int itemType = resultBean.getItemType();
        int itemType = holder.getItemViewType();
        switch (holder.getItemViewType()) {
            case TYPE_1:
                holderNoImage(holder, resultBean);
                break;

            case TYPE_2:
                holderSingleImage(holder, resultBean);
                break;

            case TYPE_3:
                holderThreeImage(holder, resultBean);
                break;
            default:
                break;

        }
    }

    private void holderThreeImage(BaseViewHolder holder, ShopNewsListBean.ResultBean resultBean) {
        TextView mTvNewsTitle = holder.getView(R.id.tv_news_title);
        RecyclerView recyclerView = holder.getView(R.id.recyclerView);
        TextView mTvCount = holder.getView(R.id.tv_count);
        List<String> article_thumb = resultBean.getArticle_thumb();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));

        int article_reading_vol = resultBean.getArticle_reading_vol();
        String article_title = resultBean.getArticle_title();
        mTvNewsTitle.setText(article_title);
        mTvCount.setText(article_reading_vol + " 阅读");

        if (article_thumb != null && article_thumb.size() != 0) {
            NewsImageAdapter imageAdapter = new NewsImageAdapter();
            imageAdapter.setNewData(article_thumb);
            recyclerView.setAdapter(imageAdapter);
        }
    }

    private void holderSingleImage(BaseViewHolder holder, ShopNewsListBean.ResultBean resultBean) {
        ImageView mIvLeft = holder.getView(R.id.iv_left_image);
        TextView mTvNewsTitle = holder.getView(R.id.tv_news_title);
        TextView mTvCount = holder.getView(R.id.tv_count);
        List<String> article_thumb = resultBean.getArticle_thumb();

        int article_reading_vol = resultBean.getArticle_reading_vol();
        String article_title = resultBean.getArticle_title();
        mTvNewsTitle.setText(article_title);
        mTvCount.setText(article_reading_vol + " 阅读");

        if (article_thumb != null && article_thumb.size() > 0) {
            String imageUrl = article_thumb.get(0).contains(Config.BASE_URL)?article_thumb.get(0):Config.BASE_URL+article_thumb.get(0);
            ImageLoader.loadNormal(mContext,imageUrl,mIvLeft);
        }
    }

    private void holderNoImage(BaseViewHolder holder, ShopNewsListBean.ResultBean resultBean) {
        ImageView mIvLeft = holder.getView(R.id.iv_left_image);
        TextView mTvNewsTitle = holder.getView(R.id.tv_news_title);
        TextView mTvCount = holder.getView(R.id.tv_count);
        RecyclerView recyclerView = holder.getView(R.id.recyclerView);
        recyclerView.setVisibility(View.GONE);

        int article_reading_vol = resultBean.getArticle_reading_vol();
        String article_title = resultBean.getArticle_title();
        mTvNewsTitle.setText(article_title);
        mTvCount.setText(article_reading_vol + " 阅读");

    }


}
