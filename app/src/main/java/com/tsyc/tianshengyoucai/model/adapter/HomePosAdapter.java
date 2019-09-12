package com.tsyc.tianshengyoucai.model.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.HomeListBean;
import com.tsyc.tianshengyoucai.utils.ImageLoader;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/22
 * File description： 首页 职位推荐
 */
public class HomePosAdapter extends BaseQuickAdapter<HomeListBean.ResultBean.PositionBean, BaseViewHolder> {

    public HomePosAdapter(@Nullable List<HomeListBean.ResultBean.PositionBean> data) {
        super(R.layout.layout_home_position, data);
    }

    public HomePosAdapter() {
        super(R.layout.layout_home_position);
    }

    @Override
    protected void convert(BaseViewHolder holder, HomeListBean.ResultBean.PositionBean positionBean) {

        TextView mTvPosName = holder.getView(R.id.tv_pos_name);
        TextView mTvPosPrice = holder.getView(R.id.tv_pos_price);
        TextView mTvCompanyName = holder.getView(R.id.tv_company_name);
        TextView mTvWorkAddress = holder.getView(R.id.tv_work_address);
        TextView mTvWorkAge = holder.getView(R.id.tv_work_age);
        TextView mTvWorkEdu = holder.getView(R.id.tv_work_edu);
        ImageView mTvPosImg = holder.getView(R.id.iv_pos_img);
        TextView mTvPosLeader = holder.getView(R.id.tv_pos_leader);

        String address = positionBean.getAddress();
        String detail = positionBean.getDetail();
        String name = positionBean.getName();
        String salary = positionBean.getSalary();
        String educational = positionBean.getEducational();
        String experience = positionBean.getExperience();
        String logo = positionBean.getLogo();
        String contact = positionBean.getContact();
        mTvPosName.setText(name);
        mTvPosPrice.setText(salary);
        mTvCompanyName.setText(detail);
        mTvWorkAddress.setText(address);
        mTvWorkAge.setText(experience);
        mTvWorkEdu.setText(educational);
        mTvPosLeader.setText(contact);

        ImageLoader.loadNormal(mContext,logo,mTvPosImg);


    }
}
