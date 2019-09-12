package com.tsyc.tianshengyoucai.ui.recruit.jobmine;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.requet.RequestJobHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.SystemUtil;
import com.tsyc.tianshengyoucai.vo.DeliveryDetailVo;
import com.tsyc.tianshengyoucai.vo.GmSelectBean;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/9/10 14:33:
 * @Purpose :投递详情
 */
public class DeliveryDetailsActivity extends Base2Activity {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private TextView mTvMyDeliveDetailTitle;
    private TextView mTvMyDeliveDetailTime;
    private TextView mTvMyDeliveDetailMoney;
    private TextView mTvMyDeliveDetailCompany;
    private TextView mTvMyDeliveDetailStatus;
    private ImageView mIvDeliveryDetailOne;
    private TextView mTvDeliveryDetailOne;
    private TextView mTvDeliveryDetailOneTime;
    private ImageView mIvDeliveryDetailTwo;
    private TextView mTvDeliveryDetailTwo;
    private TextView mTvDeliveryDetailTwoTime;
    private ImageView mIvDeliveryDetailThree;
    private TextView mTvDeliveryDetailThree;
    private TextView mTvDeliveryDetailThreeTime;
    private TextView mTvDelvieryDetailTime;
    private TextView mTvDelvieryDetailAddress;
    private TextView mTvDelvieryDetailPhone;
    private LinearLayout mLlDelvieryContent;
    private ScrollView mSrlRoot;
    public static final String POSTION_ID = "id";
    private String mPostionId;
    private DeliveryDetailVo.ResultBean mResult;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_delivery_details);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_delivery_details;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        if (getIntent() != null) {
            mPostionId = getIntent().getStringExtra(POSTION_ID);
        }
        initView();
        initEvent();
        initRequest();
    }

    private void initRequest() {
        showProgress();
        RequestJobHttp.getSingleton(this).requestSendDetail(mPostionId, new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                mSrlRoot.setVisibility(View.VISIBLE);
                DeliveryDetailVo mVo = GsonUtils.getGson(success, DeliveryDetailVo.class);
                mResult = mVo.getResult();
                bindView(mResult);

            }

            @Override
            public void error(String error) {
                onError();
            }
        });

    }

    private void bindView(DeliveryDetailVo.ResultBean result) {
        mTvMyDeliveDetailTitle.setText(result.getPosition_name());
        mTvMyDeliveDetailTime.setText(result.getCreate_time());
        mTvMyDeliveDetailMoney.setText(result.getSalary().getDesc());
        mTvMyDeliveDetailCompany.setText(result.getCompany_name());
        GmSelectBean status = result.getStatus();
//        0 已投递 1已查看 2邀请面试 3已结束 4面试未到 5不合适
        switch (status.getValue()) {
            case 0:
                setImageColor(mIvDeliveryDetailOne, true);
                mTvDeliveryDetailOne.setText("已投递");
                setTextColor(mTvDeliveryDetailOne, true);
                mTvDeliveryDetailOneTime.setText(result.getCreate_time());
                setTextColor(mTvDeliveryDetailOneTime, true);
                break;
            case 1:
                setImageColor(mIvDeliveryDetailOne, true);
                mTvDeliveryDetailOne.setText("已投递");
                setTextColor(mTvDeliveryDetailOne, true);
                mTvDeliveryDetailOneTime.setText(result.getCreate_time());
                setTextColor(mTvDeliveryDetailOneTime, true);


                setImageColor(mIvDeliveryDetailTwo,true);
                mTvDeliveryDetailTwo.setText("已查看");
                setTextColor(mTvDeliveryDetailTwo,true);
                mTvDeliveryDetailTwoTime.setText(result.getCheck_time());
                setTextColor(mTvDeliveryDetailTwoTime,true);
                break;
            case 2:

                setImageColor(mIvDeliveryDetailOne, true);
                mTvDeliveryDetailOne.setText("已投递");
                setTextColor(mTvDeliveryDetailOne, true);
                mTvDeliveryDetailOneTime.setText(result.getCreate_time());
                setTextColor(mTvDeliveryDetailOneTime, true);


                setImageColor(mIvDeliveryDetailTwo,true);
                mTvDeliveryDetailTwo.setText("已查看");
                setTextColor(mTvDeliveryDetailTwo,true);
                mTvDeliveryDetailTwoTime.setText(result.getCheck_time());
                setTextColor(mTvDeliveryDetailTwoTime,true);

                setImageColor(mIvDeliveryDetailThree,true);
                mTvDeliveryDetailThree.setText("邀面试");
                setTextColor(mTvDeliveryDetailThree,true);
                mTvDeliveryDetailThreeTime.setText(result.getSend_interview_time());
                setTextColor(mTvDeliveryDetailThreeTime,true);

                mLlDelvieryContent.setVisibility(View.VISIBLE);
                mTvDelvieryDetailTime.setText("面试时间："+result.getInterview_time());
                DeliveryDetailVo.ResultBean.AddressInfoBean info = result.getAddress_info();
                mTvDelvieryDetailAddress.setText("面试地点："+info.getProvince()+info.getCity()
                +info.getArea()+info.getAddress());
                mTvDelvieryDetailPhone.setText("联系人："+result.getBoss_name()+
                        " "+result.getBoss_mobile());
                break;
            case 3:
                setImageColor(mIvDeliveryDetailOne, true);
                mTvDeliveryDetailOne.setText("已投递");
                setTextColor(mTvDeliveryDetailOne, true);
                mTvDeliveryDetailOneTime.setText(result.getCreate_time());
                setTextColor(mTvDeliveryDetailOneTime, true);


                setImageColor(mIvDeliveryDetailTwo,true);
                mTvDeliveryDetailTwo.setText("已查看");
                setTextColor(mTvDeliveryDetailTwo,true);
                mTvDeliveryDetailTwoTime.setText(result.getCheck_time());
                setTextColor(mTvDeliveryDetailTwoTime,true);

                setImageColor(mIvDeliveryDetailThree,true);
                mTvDeliveryDetailThree.setText("已结束");
                setTextColor(mTvDeliveryDetailThree,true);
                mTvDeliveryDetailThreeTime.setText(result.getFinish_time());
                setTextColor(mTvDeliveryDetailThreeTime,true);
                break;
            case 4:
                setImageColor(mIvDeliveryDetailOne, true);
                mTvDeliveryDetailOne.setText("已投递");
                setTextColor(mTvDeliveryDetailOne, true);
                mTvDeliveryDetailOneTime.setText(result.getCreate_time());
                setTextColor(mTvDeliveryDetailOneTime, true);


                setImageColor(mIvDeliveryDetailTwo,true);
                mTvDeliveryDetailTwo.setText("已查看");
                setTextColor(mTvDeliveryDetailTwo,true);
                mTvDeliveryDetailTwoTime.setText(result.getCheck_time());
                setTextColor(mTvDeliveryDetailTwoTime,true);

                setImageColor(mIvDeliveryDetailThree,true);
                mTvDeliveryDetailThree.setText("面试未到");
                setTextColor(mTvDeliveryDetailThree,true);
                mTvDeliveryDetailThreeTime.setText(result.getFinish_time());
                setTextColor(mTvDeliveryDetailThreeTime,true);
                break;
            case 5:
                setImageColor(mIvDeliveryDetailOne, true);
                mTvDeliveryDetailOne.setText("已投递");
                setTextColor(mTvDeliveryDetailOne, true);
                mTvDeliveryDetailOneTime.setText(result.getCreate_time());
                setTextColor(mTvDeliveryDetailOneTime, true);


                setImageColor(mIvDeliveryDetailTwo,true);
                mTvDeliveryDetailTwo.setText("已查看");
                setTextColor(mTvDeliveryDetailTwo,true);
                mTvDeliveryDetailTwoTime.setText(result.getCheck_time());
                setTextColor(mTvDeliveryDetailTwoTime,true);

                setImageColor(mIvDeliveryDetailThree,true);
                mTvDeliveryDetailThree.setText("不合适");
                setTextColor(mTvDeliveryDetailThree,true);
                mTvDeliveryDetailThreeTime.setText(result.getFinish_time());
                setTextColor(mTvDeliveryDetailThreeTime,true);
                break;
        }

    }

    private void setTextColor(TextView tv, boolean showBULE) {
        tv.setTextColor(getResources().getColor(showBULE ? R.color.bule : R.color.color_7B8391));
    }

    private void setImageColor(ImageView iv, boolean showBULE) {
        iv.setImageResource(showBULE ? R.mipmap.jft_icon_successfuldelivery : R.mipmap.jft_icon_deliveryprogressnotchecked);
    }

    private void initEvent() {
        mTvDelvieryDetailPhone.setOnClickListener(v -> {
            SystemUtil.playPhone(mContext,mResult.getBoss_mobile());
        });
    }

    private void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mTvMyDeliveDetailTitle = (TextView) findViewById(R.id.tv_my_delive_detail_title);
        mTvMyDeliveDetailTime = (TextView) findViewById(R.id.tv_my_delive_detail_time);
        mTvMyDeliveDetailMoney = (TextView) findViewById(R.id.tv_my_delive_detail_money);
        mTvMyDeliveDetailCompany = (TextView) findViewById(R.id.tv_my_delive_detail_company);
        mTvMyDeliveDetailStatus = (TextView) findViewById(R.id.tv_my_delive_detail_status);
        mIvDeliveryDetailOne = (ImageView) findViewById(R.id.iv_delivery_detail_one);
        mTvDeliveryDetailOne = (TextView) findViewById(R.id.tv_delivery_detail_one);
        mTvDeliveryDetailOneTime = (TextView) findViewById(R.id.tv_delivery_detail_one_time);
        mIvDeliveryDetailTwo = (ImageView) findViewById(R.id.iv_delivery_detail_two);
        mTvDeliveryDetailTwo = (TextView) findViewById(R.id.tv_delivery_detail_two);
        mTvDeliveryDetailTwoTime = (TextView) findViewById(R.id.tv_delivery_detail_two_time);
        mIvDeliveryDetailThree = (ImageView) findViewById(R.id.iv_delivery_detail_three);
        mTvDeliveryDetailThree = (TextView) findViewById(R.id.tv_delivery_detail_three);
        mTvDeliveryDetailThreeTime = (TextView) findViewById(R.id.tv_delivery_detail_three_time);
        mTvDelvieryDetailTime = (TextView) findViewById(R.id.tv_delviery_detail_time);
        mTvDelvieryDetailAddress = (TextView) findViewById(R.id.tv_delviery_detail_address);
        mTvDelvieryDetailPhone = (TextView) findViewById(R.id.tv_delviery_detail_phone);
        mLlDelvieryContent = (LinearLayout) findViewById(R.id.ll_delviery_content);
        mSrlRoot = (ScrollView) findViewById(R.id.srl_root);
    }
}
