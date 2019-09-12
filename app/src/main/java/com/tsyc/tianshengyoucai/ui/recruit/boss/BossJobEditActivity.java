package com.tsyc.tianshengyoucai.ui.recruit.boss;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.Eventbuss.JobRefreshEvent;
import com.tsyc.tianshengyoucai.Eventbuss.RefreshHomeEvent;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.requet.RequestBossHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.DialogUtils;
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.ShareUtil;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.view.FlowLayout;
import com.tsyc.tianshengyoucai.vo.BossJobDeatailVo;
import com.tsyc.tianshengyoucai.vo.GmSelectBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/9/5 10:35:
 * @Purpose :职位编辑
 */
public class BossJobEditActivity extends Base2Activity implements View.OnClickListener {

    private ImageView mGmIvRight;
    private TextView mTvBossJobEditGonumber;
    private TextView mTvBossJobEditLooknumber;
    private TextView mTvBossJobEditTitle;
    private TextView mTvBossJobEditWorkTag;
    private TextView mTvBossJobEditStatus;
    private TextView mTvBossJobEditTag;
    private TextView mTvBossJobEditMoney;
    private TextView mTvBossJobEditTime;
    private TextView mTvBossJobEditCity;
    private TextView mTvBossJobEditAddress;
    private FlowLayout mFlBossJobType;
    private TextView mTvBossJobEditDescribe;
    private ImageView mIvBossJobCompanyLogo;
    private TextView mTvBossJobEditCompanyName;
    private TextView mTvBossJobEditCompanyTag;
    private Button mBtnBossJobEditBtn;
    private Button mBtnBossJobEditEdit;

    public static final String JOBID = "jobid";
    private String mJobId;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_boss_job_edit);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_boss_job_edit;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        if (getIntent() != null) {
            mJobId = getIntent().getStringExtra(JOBID);
        }
        initView();
        initEvent();
        initRequest();

    }

    private void initEvent() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.jft_but_jobsharing);
        mGmIvRight.setImageBitmap(bitmap);
        mGmIvRight.setOnClickListener(v -> {
            ShareUtil.getSingleton().shareText(mContext, "等待界面");
        });
    }

    private void initRequest() {
        showProgress();
        RequestBossHttp.getSingleton(this).requestBossJobDetail(mJobId, new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                BossJobDeatailVo mVo = GsonUtils.getGson(success, BossJobDeatailVo.class);
                BossJobDeatailVo.ResultBean result = mVo.getResult();
                bindViewData(result);

            }

            @Override
            public void error(String error) {
                onError();
            }
        });

    }

    private void bindViewData(BossJobDeatailVo.ResultBean result) {
        mTvBossJobEditTitle.setText(result.getPosition_name());
        mTvBossJobEditLooknumber.setText(String.valueOf(result.getLook_num()));
        mTvBossJobEditGonumber.setText(String.valueOf(result.getTalk_num()));

        GmSelectBean jobType = result.getJob_type();
        mTvBossJobEditWorkTag.setText(jobType.getDesc());

        GmSelectBean status = result.getStatus();
        mTvBossJobEditStatus.setText(status.getDesc());
        int value = status.getValue();
        if (value == 1) {
            mBtnBossJobEditBtn.setText("关闭职位");
        } else {
            mBtnBossJobEditBtn.setText("开启职位");
        }
        StringBuffer buffer = new StringBuffer();
        GmSelectBean experience = result.getWork_experience();
        buffer.append(experience.getDesc());
        buffer.append(" | ");

        GmSelectBean education = result.getEducation();
        buffer.append(education.getDesc());
        buffer.append(" | ");

        GmSelectBean salary = result.getSalary();
        buffer.append(salary.getDesc());
        mTvBossJobEditTag.setText(buffer.toString());

        mTvBossJobEditMoney.setText(salary.getDesc());

        mTvBossJobEditTime.setText(result.getUpdate_time());

        BossJobDeatailVo.ResultBean.AddressInfoBean info = result.getAddress_info();

        StringBuffer address = new StringBuffer();
        address.append(info.getProvince());
        address.append(info.getCity());
        address.append(info.getArea());
        address.append(info.getAddress());
        mTvBossJobEditAddress.setText(address.toString());
        mTvBossJobEditCity.setText(info.getCity());
        addJobTag(result.getHighlights());
        mTvBossJobEditDescribe.setText(result.getDesc());

        BossJobDeatailVo.ResultBean.CompanyBean company = result.getCompany();

        mTvBossJobEditCompanyName.setText(company.getFull_name());

        StringBuffer companyTag = new StringBuffer();
        GmSelectBean companySize = company.getCompany_size();
        companyTag.append(companySize.getDesc());

        BossJobDeatailVo.ResultBean.CompanyBean.IndustriesBean bean = company.getIndustries();
        companyTag.append(" | ");
        companyTag.append(bean.getDesc());
        mTvBossJobEditCompanyTag.setText(companyTag.toString());
        GlideUtil.getSingleton().loadQuadRangleImager(mContext, mIvBossJobCompanyLogo,
                company.getLogo());


    }

    private void addJobTag(BossJobDeatailVo.ResultBean.HighlightsBean highlights) {
        mFlBossJobType.removeAllViews();
        List<String> desc = highlights.getDesc();
        if (desc == null || desc.isEmpty()) return;
        for (int i = 0; i < desc.size(); i++) {
            String com = desc.get(i);
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_boss_edit_tag, null);
            TextView tv = view.findViewById(R.id.tv_boss_edit_tag);
            tv.setText(com);
            mFlBossJobType.addView(view);
        }
    }

    private void initView() {
        mGmIvRight = (ImageView) findViewById(R.id.gm_iv_right);
        mTvBossJobEditGonumber = (TextView) findViewById(R.id.tv_boss_job_edit_gonumber);
        mTvBossJobEditLooknumber = (TextView) findViewById(R.id.tv_boss_job_edit_looknumber);
        mTvBossJobEditTitle = (TextView) findViewById(R.id.tv_boss_job_edit_title);
        mTvBossJobEditWorkTag = (TextView) findViewById(R.id.tv_boss_job_edit_work_tag);
        mTvBossJobEditStatus = (TextView) findViewById(R.id.tv_boss_job_edit_status);
        mTvBossJobEditTag = (TextView) findViewById(R.id.tv_boss_job_edit_tag);
        mTvBossJobEditMoney = (TextView) findViewById(R.id.tv_boss_job_edit_money);
        mTvBossJobEditTime = (TextView) findViewById(R.id.tv_boss_job_edit_time);
        mTvBossJobEditCity = (TextView) findViewById(R.id.tv_boss_job_edit_city);
        mTvBossJobEditAddress = (TextView) findViewById(R.id.tv_boss_job_edit_address);
        mFlBossJobType = (FlowLayout) findViewById(R.id.fl_boss_job_type);
        mTvBossJobEditDescribe = (TextView) findViewById(R.id.tv_boss_job_edit_describe);
        mIvBossJobCompanyLogo = (ImageView) findViewById(R.id.tv_boss_job_company_logo);
        mTvBossJobEditCompanyName = (TextView) findViewById(R.id.tv_boss_job_edit_company_name);
        mTvBossJobEditCompanyTag = (TextView) findViewById(R.id.tv_boss_job_edit_company_tag);
        mBtnBossJobEditBtn = (Button) findViewById(R.id.btn_boss_job_edit_btn);
        mBtnBossJobEditEdit = (Button) findViewById(R.id.btn_boss_job_edit_edit);

        mBtnBossJobEditBtn.setOnClickListener(this);
        mBtnBossJobEditEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_boss_job_edit_btn:
                String btn = StringUtil.getObjectToStr(mBtnBossJobEditBtn);
                boolean b = btn.equals("开启职位");
                DialogUtils.getSingleton().showSureAlerDialog(mContext,
                        b ? "是否确认开启职位" : "是否确认关闭职位", "",
                        "确认", "取消", new DialogUtils.OnDialogSuceClickListener() {
                            @Override
                            public void sureClick() {
                                setOnClick(b);
                            }

                            @Override
                            public void cannerClick() {

                            }
                        });

                break;
            case R.id.btn_boss_job_edit_edit:
                mResultTo.startSendJobFinish(mContext,mJobId);
                break;
        }
    }

    private void setOnClick(boolean b) {
        showProgress();
        RequestBossHttp.getSingleton(this).submitBossJobOpenOrStop(b, String.valueOf(mJobId), new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                T.showToast(mContext, "提交成功");
                EventBus.getDefault().postSticky(new JobRefreshEvent(""));
                mResultTo.finishBase(mContext);
                EventBus.getDefault().postSticky(new RefreshHomeEvent(""));
            }

            @Override
            public void error(String error) {
                onError();
            }
        });

    }
}
