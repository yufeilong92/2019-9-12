package com.tsyc.tianshengyoucai.ui.recruit;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.recruit.RcEduAdapter;
import com.tsyc.tianshengyoucai.adapter.recruit.RcWorkHistoryAdapter;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.requet.RequestJobHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.vo.ResumeVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/30 14:24:
 * @Purpose :简历预览
 */
public class LookResumeActivity extends Base2Activity {


    private RcEduAdapter mRcEduAdapter;
    private RcWorkHistoryAdapter mWorkadapter;
    private ArrayList mWOrkLists;
    private ArrayList mEduLists;
    private ImageView mIvLookRcHear;
    private TextView mTvLookRcName;
    private TextView mTvLookRcSex;
    private TextView mTvLookRcWork;
    private TextView mTvLookRcTime;
    private TextView mTvLookRcAge;
    private TextView mTvLookRcEdu;
    private TextView mTvLookRcAddress;
    private LinearLayout mLlLookRcInfom;
    private TextView mTvLookRcPhone;
    private TextView mTvLookRcEmali;
    private TextView mTvLookRcWorkStatus;
    private TextView mTvLookRcWorkPurpose;
    private TextView mTvLookRcWorkContentPurpose;
    private RecyclerView mRlvLookRcWorkUndergo;
    private LinearLayout mLlLookRcUndergo;
    private RecyclerView mRlvLookRcWorkEdu;
    private LinearLayout mLlLookRcEdu;
    private TextView mTvLookRcMineEvaluate;
    private LinearLayout mLlLookRcEvaluate;
    private RequestJobHttp mHttpRequest;
    private ResumeVo.ResultBean mDataResult;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_look_resume);
//        initView();
//
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_look_resume;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        initView();
        clearWorkData();
        clearEduData();
        initEvent();
        initEduAdapter();
        initWorkAdapter();
        initRequestUserInfom();
    }

    private void initEduAdapter() {
        mRcEduAdapter = new RcEduAdapter(mContext, mEduLists);
        RecyclerUtil.setGridManage(mContext, mRlvLookRcWorkEdu, mRcEduAdapter);
        mRcEduAdapter.setListener(new RcEduAdapter.OnItemClickListener() {
            @Override
            public void itemClick(ResumeVo.EducationsBean bean) {
                mResultTo.startEducation(mContext, bean);
            }
        });
        mRcEduAdapter.refrehsLook("2");
    }

    private void initWorkAdapter() {
        mWorkadapter = new RcWorkHistoryAdapter(mContext, mWOrkLists);
        RecyclerUtil.setGridManage(mContext, mRlvLookRcWorkUndergo, mWorkadapter);
        mWorkadapter.setListener(new RcWorkHistoryAdapter.OnItemClickListener() {
            @Override
            public void itemClick(ResumeVo.WorksBean vo) {
                mResultTo.startWork(mContext, vo);
            }
        });
        mWorkadapter.refreshType("2");
    }

    private void initRequestUserInfom() {
        showProgress();
        RequestJobHttp.getSingleton(this).requestMyVc(new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                ResumeVo mVcInfom = GsonUtils.getGson(success, ResumeVo.class);
                mDataResult = mVcInfom.getResult();
                bindViewData(mDataResult);
            }

            @Override
            public void error(String error) {
                onError();
            }
        });


    }

    private void bindViewData(ResumeVo.ResultBean result) {
        GlideUtil.getSingleton().loadQuadRangleImager(mContext, mIvLookRcHear, result.getAvatar());
        mTvLookRcName.setText(result.getUsername());
        mTvLookRcSex.setText(result.getSex().getDesc());
        mTvLookRcWork.setText(result.getExpected_position().getDesc());
        mTvLookRcTime.setText("工作" + result.getExperience() + "年");
        mTvLookRcPhone.setText(result.getMobile());
        mTvLookRcEmali.setText(result.getEmail());
        mTvLookRcWorkStatus.setText(result.getCurrent_status().getDesc());
        mTvLookRcAge.setText(result.getAge());
        mTvLookRcEdu.setText(result.getHighest_education().getDesc());
        mTvLookRcAddress.setText(result.getWork_city().getDesc());
        mTvLookRcWorkPurpose.setText(result.getExpected_position().getDesc() + " " + result.getExpected_salary().getDesc());
        mTvLookRcMineEvaluate.setText(result.getSelf_assessment());
        mTvLookRcWorkContentPurpose.setText(result.getWork_city().getDesc() + result.getExpected_industry().getDesc());

        addWorkList(result.getWorks());
        addEduList(result.getEducations());


    }

    private void addWorkList(List<ResumeVo.WorksBean> works) {
        clearWorkData();
        addWorkData(works);
        mWorkadapter.notifyDataSetChanged();

    }

    private void addEduList(List<ResumeVo.EducationsBean> educations) {
        clearEduData();
        addEduData(educations);
        mRcEduAdapter.notifyDataSetChanged();

    }

    private void initEvent() {
        mHttpRequest = RequestJobHttp.getSingleton(this);

    }



    private void clearWorkData() {
        if (mWOrkLists == null) {
            mWOrkLists = new ArrayList();
        } else {
            mWOrkLists.clear();
        }
    }

    private void addWorkData(List<?> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        if (mWOrkLists == null) {
            clearWorkData();
        }
        mWOrkLists.addAll(list);
    }

    private void clearEduData() {
        if (mEduLists == null) {
            mEduLists = new ArrayList();
        } else {
            mEduLists.clear();
        }
    }

    private void addEduData(List<?> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        if (mEduLists == null) {
            clearEduData();
        }
        mEduLists.addAll(list);
    }

    private void initView() {
        mIvLookRcHear = (ImageView) findViewById(R.id.iv_look_rc_hear);
        mTvLookRcName = (TextView) findViewById(R.id.tv_look_rc_name);
        mTvLookRcSex = (TextView) findViewById(R.id.tv_look_rc_sex);
        mTvLookRcWork = (TextView) findViewById(R.id.tv_look_rc_work);
        mTvLookRcTime = (TextView) findViewById(R.id.tv_look_rc_time);
        mTvLookRcAge = (TextView) findViewById(R.id.tv_look_rc_age);
        mTvLookRcEdu = (TextView) findViewById(R.id.tv_look_rc_edu);
        mTvLookRcAddress = (TextView) findViewById(R.id.tv_look_rc_address);
        mLlLookRcInfom = (LinearLayout) findViewById(R.id.ll_look_rc_infom);
        mTvLookRcPhone = (TextView) findViewById(R.id.tv_look_rc_phone);
        mTvLookRcEmali = (TextView) findViewById(R.id.tv_look_rc_emali);
        mTvLookRcWorkStatus = (TextView) findViewById(R.id.tv_look_rc_work_status);
        mTvLookRcWorkPurpose = (TextView) findViewById(R.id.tv_look_rc_work_purpose);
        mTvLookRcWorkContentPurpose = (TextView) findViewById(R.id.tv_look_rc_work_content_purpose);
        mRlvLookRcWorkUndergo = (RecyclerView) findViewById(R.id.rlv_look_rc_work_undergo);
        mLlLookRcUndergo = (LinearLayout) findViewById(R.id.ll_look_rc_undergo);
        mRlvLookRcWorkEdu = (RecyclerView) findViewById(R.id.rlv_look_rc_work_edu);
        mLlLookRcEdu = (LinearLayout) findViewById(R.id.ll_look_rc_edu);
        mTvLookRcMineEvaluate = (TextView) findViewById(R.id.tv_look_rc_mine_evaluate);
        mLlLookRcEvaluate = (LinearLayout) findViewById(R.id.ll_look_rc_evaluate);
    }
}
