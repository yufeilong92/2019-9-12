package com.tsyc.tianshengyoucai.model.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.DetailEvaluateBean;
import com.tsyc.tianshengyoucai.model.bean.EvaluateManageBean;
import com.tsyc.tianshengyoucai.ui.ImagerActivity;
import com.tsyc.tianshengyoucai.utils.ImageLoader;
import com.tsyc.tianshengyoucai.view.StarBar;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static com.tsyc.tianshengyoucai.manager.StartFragmentManger.CNT_PARAMETE_TITLE;

/**
 * author：van
 * CreateTime：2019/7/26
 * File description：商家详情评论 适配器
 */
public class ShopEvaluateAdapter extends BaseQuickAdapter<DetailEvaluateBean.ResultBean.ListBean, BaseViewHolder> {

    public ShopEvaluateAdapter(@Nullable List<DetailEvaluateBean.ResultBean.ListBean> data) {
        super(R.layout.layout_evaluate_manage_item, data);
    }

    public ShopEvaluateAdapter() {
        super(R.layout.layout_evaluate_manage_item);
    }

    @Override
    protected void convert(BaseViewHolder holder, DetailEvaluateBean.ResultBean.ListBean listBean) {

        //评论
        ImageView iv_image = holder.getView(R.id.iv_image);
        TextView tv_name = holder.getView(R.id.tv_name);
        StarBar starBar = holder.getView(R.id.sb_starBar);
        TextView tv_evaluate_spec = holder.getView(R.id.tv_evaluate_spec);
        TextView tv_evaluate_content = holder.getView(R.id.tv_evaluate_content);
        RecyclerView rv_evaluate_img = holder.getView(R.id.rv_evaluate_img);
        rv_evaluate_img.setLayoutManager(new GridLayoutManager(mContext, 3));
        rv_evaluate_img.setHasFixedSize(true);

        int geval_addtime = listBean.getGeval_addtime();
        String geval_content = listBean.getGeval_content();
        String geval_frommembername = listBean.getGeval_frommembername();
        List<String> geval_image = listBean.getGeval_image();
        String geval_remark = listBean.getGeval_remark();
        int geval_scores = listBean.getGeval_scores();
        String member_avatar = listBean.getMember_avatar();

        ImageLoader.loadCenterCrop(mContext, member_avatar, iv_image, 0);

        tv_evaluate_content.setText(geval_content);
        starBar.setStarMark(geval_scores);
        tv_name.setText(geval_frommembername);
        //tv_evaluate_spec.setText(geval_addtime);

        if (geval_image != null && geval_image.size() > 0) {
            EvaluateImageAdapter showImageAdapter = new EvaluateImageAdapter();
            rv_evaluate_img.setAdapter(showImageAdapter);
            showImageAdapter.setNewData(geval_image);
            showImageAdapter.setListener(new EvaluateImageAdapter.OnItemClickListener() {
                @Override
                public void itemClick(int adapterPosition, String item) {
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList(ImagerActivity.listType, (ArrayList<String>)geval_image);
                    bundle.putInt(ImagerActivity.postion, holder.getLayoutPosition());
                    Intent intentB = new Intent();
                    intentB.setClass(mContext, ImagerActivity.class);
                    intentB.putExtras(bundle);
                    intentB.putExtra(CNT_PARAMETE_TITLE, "图片查看");
                     mContext.startActivity(intentB);
                    ((FragmentActivity)mContext) .overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                }
            });
        }
    }

}
