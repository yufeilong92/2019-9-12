package com.tsyc.tianshengyoucai.ui.recruit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.recruit.JobAdapter;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.requet.RequestJobHttp;
import com.tsyc.tianshengyoucai.ui.activity.mine.AppSuggestActivity;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.DialogUtils;
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.view.FlowLayout;
import com.tsyc.tianshengyoucai.vo.BossJobItemVo;
import com.tsyc.tianshengyoucai.vo.ChatIdVo;
import com.tsyc.tianshengyoucai.vo.GmSelectBean;
import com.tsyc.tianshengyoucai.vo.JobDetailVo;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/9/6 16:43:
 * @Purpose :职位详情
 */
public class JobDetailActivity extends Base2Activity implements View.OnClickListener {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private ImageView mGmIvRightCollect;
    private ImageView mGmIvRightShare;
    private TextView mTvJobDetailWorkTitle;
    private TextView mTvJobDetailWorkTag;
    private TextView mTvJobDetailStatus;
    private TextView mTvJobDetailTag;
    private TextView mTvJobDetailMoney;
    private TextView mTvJobDetailTime;
    private TextView mTvJobDetailCity;
    private TextView mTvJobDetailAddress;
    private FlowLayout mFlJobDetailCompanyTag;
    private TextView mTvJobDetailCompanyDesc;
    private ImageView mIvJobDetailCompanyLogo;
    private TextView mTvJobDetailCompanyName;
    private TextView mTvJobDetailCompanyTag;
    private TextView mTvJobDetailInform;
    private RecyclerView mRlvJobDetailContent;
    private Button mBtnJobDetailGo;
    private Button mBtnJobDetailSend;

    public static final String JOB_ID = "job_id";
    private String mJob_id;
    private boolean isCollect;
    private JobDetailVo.ResultBean.DetailBean.CompanyBean mCompany;
    private LinearLayout mLlCompanyInfom;
    private ScrollView mSrlRoot;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_job_detail);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_job_detail;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        if (getIntent() != null) {
            mJob_id = getIntent().getStringExtra(JOB_ID);
        }
        initView();
        initEvent();
        initRequest();
    }


    private void initEvent() {
        mGmIvRightCollect.setOnClickListener(v -> {
            DialogUtils.getSingleton().showSureAlerDialog(mContext,
                    isCollect ? "是否确认取消收藏" : "是否确认收藏", "",
                    "确认", "取消", new DialogUtils.OnDialogSuceClickListener() {
                        @Override
                        public void sureClick() {
                            subimtCollect();
                        }

                        @Override
                        public void cannerClick() {

                        }
                    });

        });
        mGmIvRightShare.setOnClickListener(v -> {
            T.showToast(mContext, "功能待开发");
            return;
//            ShareUtil.getSingleton().shareText(mContext, "等待连接");
        });

        mTvJobDetailInform.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, AppSuggestActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("store_id", String.valueOf(mCompany.getId()));
            bundle.putString(AppSuggestActivity.JOBTYPE, AppSuggestActivity.JOBTYPE);
            intent.putExtra(Constant.bundleExtra, bundle);
            startActivity(intent);
        });
        mLlCompanyInfom.setOnClickListener(v -> {
            mResultTo.startBossCompanyInfom(mContext, mCompany.getId());
        });
    }

    private void subimtCollect() {

        showProgress();
        RequestJobHttp.getSingleton(this).submitJobCollect(!isCollect, mJob_id, new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;

                T.showToast(mContext, isCollect ? "取消成功" : "收藏成功");
                if (isCollect) {
                    isCollect = false;
                } else
                    isCollect = true;
                mGmIvRightCollect.setImageResource(isCollect ? R.mipmap.icon_star_selected : R.mipmap.jft_icon_tracingstars);

            }

            @Override
            public void error(String error) {
                onError();
            }
        });
    }

    private void initRequest() {
        showProgress();
        RequestJobHttp.getSingleton(this).requestPositionDetail(mJob_id, new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                mSrlRoot.setVisibility(View.VISIBLE);
                JobDetailVo mVo = GsonUtils.getGson(success, JobDetailVo.class);
                bindViewData(mVo.getResult());
            }

            @Override
            public void error(String error) {
                onError();
            }
        });
    }

    private void bindViewData(JobDetailVo.ResultBean result) {
        JobDetailVo.ResultBean.DetailBean detail = result.getDetail();
        isCollect = detail.getIs_like() == 1;
        mGmIvRightCollect.setImageResource(isCollect ? R.mipmap.icon_star_selected : R.mipmap.jft_icon_tracingstars);
        mTvJobDetailWorkTitle.setText(detail.getPosition_name());
        GmSelectBean status = detail.getStatus();
        GmSelectBean job_type = detail.getJob_type();
        mTvJobDetailWorkTag.setText(job_type.getDesc());
        mTvJobDetailStatus.setText(status.getDesc());

        StringBuffer buffer = new StringBuffer();
        GmSelectBean experience = detail.getWork_experience();
        buffer.append(experience.getDesc());
        buffer.append(" | ");

        GmSelectBean education = detail.getEducation();
        buffer.append(education.getDesc());
        buffer.append(" | ");

        GmSelectBean salary = detail.getSalary();
        buffer.append(salary.getDesc());
        mTvJobDetailTag.setText(buffer.toString());


        mTvJobDetailMoney.setText(salary.getDesc());

        mTvJobDetailTime.setText(detail.getUpdate_time());

        JobDetailVo.ResultBean.DetailBean.AddressInfoBean info = detail.getAddress_info();

        StringBuffer address = new StringBuffer();
        address.append(info.getProvince());
        address.append(info.getCity());
        address.append(info.getArea());
        address.append(info.getAddress());
        mTvJobDetailAddress.setText(address.toString());
        mTvJobDetailCity.setText(info.getCity());

        addJobTag(detail.getHighlights());
        mTvJobDetailCompanyDesc.setText(detail.getDesc());

        mCompany = detail.getCompany();

        mTvJobDetailCompanyName.setText(mCompany.getFull_name());

        StringBuffer companyTag = new StringBuffer();
        GmSelectBean companySize = mCompany.getCompany_size();
        companyTag.append(companySize.getDesc());

        JobDetailVo.ResultBean.DetailBean.CompanyBean.IndustriesBean bean = mCompany.getIndustries();
        companyTag.append(" | ");
        companyTag.append(bean.getDesc());
        mTvJobDetailCompanyTag.setText(companyTag.toString());
        GlideUtil.getSingleton().loadQuadRangleImager(mContext, mIvJobDetailCompanyLogo,
                mCompany.getLogo());
        JobDetailVo.ResultBean.OtherRecommendBean recommend = result.getOther_recommend();
        List<BossJobItemVo> data = recommend.getData();
        bindViewJobList(data);

    }

    private void bindViewJobList(List<BossJobItemVo> data) {
        JobAdapter jobAdapter = new JobAdapter(mContext, data);
        RecyclerUtil.setGridManage(mContext, mRlvJobDetailContent, jobAdapter);
    }

    private void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mGmIvRightCollect = (ImageView) findViewById(R.id.gm_iv_right_collect);
        mGmIvRightShare = (ImageView) findViewById(R.id.gm_iv_right_share);
        mTvJobDetailWorkTitle = (TextView) findViewById(R.id.tv_job_detail_work_title);
        mTvJobDetailWorkTag = (TextView) findViewById(R.id.tv_job_detail_work_tag);
        mTvJobDetailStatus = (TextView) findViewById(R.id.tv_job_detail_status);
        mTvJobDetailTag = (TextView) findViewById(R.id.tv_job_detail_tag);
        mTvJobDetailMoney = (TextView) findViewById(R.id.tv_job_detail_money);
        mTvJobDetailTime = (TextView) findViewById(R.id.tv_job_detail_time);
        mTvJobDetailCity = (TextView) findViewById(R.id.tv_job_detail_city);
        mTvJobDetailAddress = (TextView) findViewById(R.id.tv_job_detail_address);
        mFlJobDetailCompanyTag = (FlowLayout) findViewById(R.id.fl_job_detail_company_tag);
        mTvJobDetailCompanyDesc = (TextView) findViewById(R.id.tv_job_detail_company_desc);
        mIvJobDetailCompanyLogo = (ImageView) findViewById(R.id.iv_job_detail_company_logo);
        mTvJobDetailCompanyName = (TextView) findViewById(R.id.tv_job_detail_company_name);
        mTvJobDetailCompanyTag = (TextView) findViewById(R.id.tv_job_detail_company_tag);
        mTvJobDetailInform = (TextView) findViewById(R.id.tv_job_detail_inform);
        mRlvJobDetailContent = (RecyclerView) findViewById(R.id.rlv_job_detail_content);
        mBtnJobDetailGo = (Button) findViewById(R.id.btn_job_detail_go);
        mBtnJobDetailSend = (Button) findViewById(R.id.btn_job_detail_send);

        mBtnJobDetailGo.setOnClickListener(this);
        mBtnJobDetailSend.setOnClickListener(this);
        mLlCompanyInfom = (LinearLayout) findViewById(R.id.ll_company_infom);
        mLlCompanyInfom.setOnClickListener(this);
        mSrlRoot = (ScrollView) findViewById(R.id.srl_root);
        mSrlRoot.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_job_detail_go:
                initChatId();

                break;
            case R.id.btn_job_detail_send:
                DialogUtils.getSingleton().showSureAlerDialog(mContext, "是否确认投递", "",
                        "确认", "取消", new DialogUtils.OnDialogSuceClickListener() {
                            @Override
                            public void sureClick() {
                                submitCv_Send();
                            }

                            @Override
                            public void cannerClick() {

                            }
                        });
                break;
        }
    }

    private void initChatId() {
        showProgress();
        RequestJobHttp.getSingleton(this).submitEmployeeStartTalk(mJob_id, new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                ChatIdVo vo = GsonUtils.getGson(success, ChatIdVo.class);
                ChatIdVo.ResultBean result = vo.getResult();
                String company = StringUtil.getObjectToStr(mTvJobDetailCompanyName);
                mResultTo.startChatId(mContext, result.getRecord_id(), company);
            }

            @Override
            public void error(String error) {
                onError();
            }
        });

    }

    private void submitCv_Send() {
        showProgress();
        RequestJobHttp.getSingleton(this).submitCv_Send(mJob_id, new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                T.showToast(mContext, "投递成功");
            }

            @Override
            public void error(String error) {
                onError();
            }
        });


    }

    private void addJobTag(JobDetailVo.ResultBean.DetailBean.HighlightsBean highlights) {
        mFlJobDetailCompanyTag.removeAllViews();
        List<String> desc = highlights.getDesc();
        if (desc == null || desc.isEmpty()) return;
        for (int i = 0; i < desc.size(); i++) {
            String com = desc.get(i);
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_boss_edit_tag, null);
            TextView tv = view.findViewById(R.id.tv_boss_edit_tag);
            tv.setText(com);
            mFlJobDetailCompanyTag.addView(view);
        }
    }
}
