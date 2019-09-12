package com.tsyc.tianshengyoucai.ui.recruit;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.Eventbuss.RcRefresh;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.recruit.RcEduAdapter;
import com.tsyc.tianshengyoucai.adapter.recruit.RcWorkHistoryAdapter;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.requet.RequestJobHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.ui.recruit.infom.EducationInfomActivity;
import com.tsyc.tianshengyoucai.ui.recruit.infom.WorkInfomActivity;
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.GmSelectBean;
import com.tsyc.tianshengyoucai.vo.ResumeVo;
import com.tsyc.tianshengyoucai.vo.SelectKeyVo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.picker.SinglePicker;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/27 14:31:
 * @Purpose :简历预览界面
 */
public class ResumeActivity extends Base2Activity implements View.OnClickListener {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private TextView mGmTvRightTitle;
    private ImageView mIvRcHear;
    private TextView mTvRcName;
    private TextView mTvRcSex;
    private TextView mTvRcWork;
    private TextView mTvRcTime;
    private TextView mTvRcAge;
    private TextView mTvRcEdu;
    private TextView mTvRcAddress;
    private TextView mTvRcPhone;
    private TextView mTvRcEmali;
    private TextView mTvRcWorkStatus;
    private LinearLayout mLlRcAddUnder;
    private RecyclerView mRlvRcWorkUndergo;
    private LinearLayout mLlRcUndergo;
    private LinearLayout mLlRcEduAdd;
    private RecyclerView mRlvRcWorkEdu;
    private LinearLayout mLlRcEdu;
    private TextView mTvRcMineEvaluate;
    private LinearLayout mLlRcEvaluate;
    private RequestJobHttp mHttpRequest;
    private SelectKeyVo mSelectVo;
    private GmSelectBean mSelectWorkStatus;
    private TextView mTvRcWorkPurpose;
    private TextView mTvRcWorkContentPurpose;
    private ArrayList mWOrkLists;
    private ArrayList mEduLists;
    private RcWorkHistoryAdapter mWorkadapter;
    private RcEduAdapter mRcEduAdapter;
    private LinearLayout mLlRcInfom;
    private LinearLayout mLlRcAsk;
    private ResumeVo.ResultBean mDataResult;
    private TextView mTvRcAddWork;
    private TextView mTvRcAddEdu;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_resume);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_resume;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        initView();
        clearWorkData();
        clearEduData();
        initEvent();
        initSelectRequest();
        initEduAdapter();
        initWorkAdapter();
        initRequestUserInfom();
    }

    private void initEduAdapter() {
        mRcEduAdapter = new RcEduAdapter(mContext, mEduLists);
        RecyclerUtil.setGridManage(mContext, mRlvRcWorkEdu, mRcEduAdapter);
        mRcEduAdapter.setListener(new RcEduAdapter.OnItemClickListener() {
            @Override
            public void itemClick(ResumeVo.EducationsBean bean) {
                mResultTo.startEducation(mContext, bean);
            }
        });
    }

    private void initWorkAdapter() {
        mWorkadapter = new RcWorkHistoryAdapter(mContext, mWOrkLists);
        RecyclerUtil.setGridManage(mContext, mRlvRcWorkUndergo, mWorkadapter);
        mWorkadapter.setListener(new RcWorkHistoryAdapter.OnItemClickListener() {
            @Override
            public void itemClick(ResumeVo.WorksBean vo) {
                mResultTo.startWork(mContext, vo);
            }
        });
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
        GlideUtil.getSingleton().loadQuadRangleImager(mContext, mIvRcHear, result.getAvatar());
        mTvRcName.setText(result.getUsername());
        mTvRcSex.setText(result.getSex().getDesc());
        mTvRcWork.setText(result.getExpected_position().getDesc());
        mTvRcTime.setText("工作" + result.getExperience() + "年");
        mTvRcPhone.setText(result.getMobile());
        mTvRcEmali.setText(result.getEmail());
        mTvRcWorkStatus.setText(result.getCurrent_status().getDesc());
        mTvRcAge.setText(result.getAge());
        mTvRcEdu.setText(result.getHighest_education().getDesc());
        mTvRcAddress.setText(result.getWork_city().getDesc());
        mTvRcWorkPurpose.setText(result.getExpected_position().getDesc() + " " + result.getExpected_salary().getDesc());
        mTvRcMineEvaluate.setText(result.getSelf_assessment());
        mTvRcWorkContentPurpose.setText(result.getWork_city().getDesc() + result.getExpected_industry().getDesc());

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
        mGmTvRightTitle.setText("预览");
        mHttpRequest = RequestJobHttp.getSingleton(this);
        mGmTvRightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResultTo.startLookResume(mContext);
            }
        });
    }

    private void initSelectRequest() {
        mHttpRequest.requestJobsCommonKeys(new RequestResultCallback() {


            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                mSelectVo = GsonUtils.getGson(success, SelectKeyVo.class);
            }

            @Override
            public void error(String error) {
                onError();
            }
        });
    }


    private void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mGmTvRightTitle = (TextView) findViewById(R.id.gm_tv_right_title);
        mIvRcHear = (ImageView) findViewById(R.id.iv_rc_hear);
        mTvRcName = (TextView) findViewById(R.id.tv_rc_name);
        mTvRcSex = (TextView) findViewById(R.id.tv_rc_sex);
        mTvRcWork = (TextView) findViewById(R.id.tv_rc_work);
        mTvRcTime = (TextView) findViewById(R.id.tv_rc_time);
        mTvRcAge = (TextView) findViewById(R.id.tv_rc_age);
        mTvRcEdu = (TextView) findViewById(R.id.tv_rc_edu);
        mTvRcAddress = (TextView) findViewById(R.id.tv_rc_address);
        mTvRcPhone = (TextView) findViewById(R.id.tv_rc_phone);
        mTvRcEmali = (TextView) findViewById(R.id.tv_rc_emali);
        mTvRcWorkStatus = (TextView) findViewById(R.id.tv_rc_work_status);
        mLlRcAddUnder = (LinearLayout) findViewById(R.id.ll_rc_add_under);
        mRlvRcWorkUndergo = (RecyclerView) findViewById(R.id.rlv_rc_work_undergo);
        mLlRcUndergo = (LinearLayout) findViewById(R.id.ll_rc_undergo);
        mLlRcEduAdd = (LinearLayout) findViewById(R.id.ll_rc_edu_add);
        mRlvRcWorkEdu = (RecyclerView) findViewById(R.id.rlv_rc_work_edu);
        mLlRcEdu = (LinearLayout) findViewById(R.id.ll_rc_edu);
        mTvRcMineEvaluate = (TextView) findViewById(R.id.tv_rc_mine_evaluate);
        mLlRcEvaluate = (LinearLayout) findViewById(R.id.ll_rc_evaluate);
        mLlRcEvaluate.setOnClickListener(this);
        mTvRcWorkStatus.setOnClickListener(this);
        mTvRcWorkPurpose = (TextView) findViewById(R.id.tv_rc_work_purpose);
        mTvRcWorkPurpose.setOnClickListener(this);
        mTvRcWorkContentPurpose = (TextView) findViewById(R.id.tv_rc_work_content_purpose);
        mTvRcWorkContentPurpose.setOnClickListener(this);
        mLlRcInfom = (LinearLayout) findViewById(R.id.ll_rc_infom);
        mLlRcInfom.setOnClickListener(this);
        mLlRcAsk = (LinearLayout) findViewById(R.id.ll_rc_ask);
        mLlRcAsk.setOnClickListener(this);
        mTvRcAddWork = (TextView) findViewById(R.id.tv_rc_add_work);
        mTvRcAddWork.setOnClickListener(this);
        mTvRcAddEdu = (TextView) findViewById(R.id.tv_rc_add_edu);
        mTvRcAddEdu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_rc_work_status://求职状态
                if (mSelectVo == null) {
                    initSelectRequest();
                    return;
                }
                List<GmSelectBean> current_status = mSelectVo.getResult().getCurrent_status();

                showSelect(1, current_status);
                break;
            case R.id.ll_rc_infom://个人信息
                mResultTo.startJobGo(mContext, mDataResult);
                break;
            case R.id.ll_rc_ask://求职意向

                mResultTo.startJobPurpost(mContext, mDataResult);
                break;
            case R.id.ll_rc_evaluate://评价
                mResultTo.startRcEvalue(mContext, mDataResult.getSelf_assessment());
                break;
            case R.id.tv_rc_add_edu://添加教育
                mResultTo.startEducation(mContext, EducationInfomActivity.ADDTYPES);
                break;
            case R.id.tv_rc_add_work://添加工作经历
                mResultTo.startWork(mContext, WorkInfomActivity.ADDTYPES);
                break;
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void refreshData(RcRefresh refresh) {
        initRequestUserInfom();

    }

    /**
     * @param index 1 求职状态
     */
    private void showSelect(int index, List<GmSelectBean> lists) {
        List<String> items = new ArrayList<>();
        for (int k = 0; k < lists.size(); k++) {
            items.add(lists.get(k).getDesc());
        }

        SinglePicker<String> picker = new SinglePicker<String>(this, items);
        picker.setCanLoop(false);//不禁用循环
        picker.setTopBackgroundColor(0xFFEEEEEE);
        picker.setTopHeight(50);
        picker.setTopLineColor(0xFF33B5E5);
        picker.setTopLineHeight(1);
        picker.setTitleText("请选择");
        picker.setTitleTextColor(0xFF999999);
        picker.setTitleTextSize(12);
        picker.setCancelTextColor(0xFF33B5E5);
        picker.setCancelTextSize(13);
        picker.setSubmitTextColor(0xFF33B5E5);
        picker.setSubmitTextSize(13);
        picker.setSelectedTextColor(0xFF33B5E5);
        picker.setUnSelectedTextColor(0xFF999999);
        picker.setWheelModeEnable(false);
        LineConfig config = new LineConfig();
        config.setColor(Color.TRANSPARENT);//线颜色
        config.setAlpha(120);//线透明度
//        config.setRatio(1);//线比率
        picker.setLineConfig(config);
        picker.setItemWidth(200);
        picker.setBackgroundColor(0xFFE1E1E1);
        //picker.setSelectedItem(isChinese ? "处女座" : "Virgo");
        picker.setSelectedIndex(7);
        picker.setOnItemPickListener(new OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int i, String com) {
                switch (index) {
                    case 1:
                        GmSelectBean mSelectWorkStatus = lists.get(i);
                        mTvRcWorkStatus.setText(com);
                        submitEditStatus(mSelectWorkStatus.getValue());
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                }


            }
        });
        picker.show();

    }

    private void submitEditStatus(int value) {
        showProgress();
        RequestJobHttp.getSingleton(this).subimtEditCurrentStatus(String.valueOf(value), new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                T.showToast(mContext, "保存成功");

            }

            @Override
            public void error(String error) {
                onError();
            }
        });
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
}
