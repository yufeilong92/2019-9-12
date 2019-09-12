package com.tsyc.tianshengyoucai.ui.recruit.boss;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.requet.RequestBossHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.utils.Util;

import java.util.Calendar;

import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.picker.DateTimePicker;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/9/5 17:53:
 * @Purpose :发起邀请界面
 */
public class BossSendInterViewActivity extends Base2Activity implements View.OnClickListener {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private ImageView mIvSendInterviewHear;
    private TextView mTvSendInterviewName;
    private TextView mTvSendInterviewJob;
    private TextView mTvSendInterviewTime;
    private Button mBtnSendInterviewSubmit;

    public static final String ID = "id";
    public static final String HEAR = "hear";
    public static final String NAME = "name";
    public static final String JOB = "job";
    private String mID;
    private String mHear;
    private String mName;
    private String mJob;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_boss_send_inter_view);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_boss_send_inter_view;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        if (getIntent() != null) {
            mID = getIntent().getStringExtra(ID);
            mHear = getIntent().getStringExtra(HEAR);
            mName = getIntent().getStringExtra(NAME);
            mJob = getIntent().getStringExtra(JOB);
        }
        initView();
        initEvent();
        bindViewData();

    }

    private void initEvent() {
        mTvSendInterviewTime.setOnClickListener(v -> {
            if (Util.handleOnDoubleClick()) {
                return;
            }
            showTime();
        });
    }

    private void showTime() {
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int mWorkYearParam = c.get(Calendar.YEAR);
        int mWorkMonthParam = c.get(Calendar.MONTH);
        int mWorkDayParam = c.get(Calendar.DATE);
        int mWorkDaymINUT = c.get(Calendar.HOUR_OF_DAY);
        int mWorkDayseond = c.get(Calendar.MINUTE);
        DateTimePicker picker = new DateTimePicker(this, DateTimePicker.HOUR_24);
        picker.setActionButtonTop(true);
        picker.setDateRangeStart(1960, 1, 1);
        picker.setDateRangeEnd(3000, 11, 11);
        picker.setSelectedItem(mWorkYearParam, mWorkMonthParam, mWorkDayParam, mWorkDaymINUT, mWorkDayseond);
        picker.setTimeRangeStart(0, 0);
        picker.setTimeRangeEnd(23, 0);
        picker.setCanLinkage(false);
        picker.setTitleText("请选择");
//        picker.setStepMinute(5);
        picker.setWeightEnable(true);
        picker.setWheelModeEnable(true);
        LineConfig config = new LineConfig();
        config.setColor(Color.BLUE);//线颜色
        config.setAlpha(120);//线透明度
        config.setVisible(true);//线不显示 默认显示
        picker.setLineConfig(config);
        picker.setLabel("年", "月", "日", "时", "分");
        picker.setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
            @Override
            public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
//                T.showToast(mContext,year + "-" + month + "-" + day + " " + hour + ":" + minute);
                mTvSendInterviewTime.setText(year + "-" + month + "-" + day + " " + hour + ":" + minute);
            }
        });
        picker.show();
    }

    private void bindViewData() {
        GlideUtil.getSingleton().loadQuadRangleImager(mContext, mIvSendInterviewHear, mHear);
        mTvSendInterviewName.setText(mName);
        mTvSendInterviewJob.setText(mJob);

    }

    private void submit() {
        String time = StringUtil.getObjectToStr(mTvSendInterviewTime);
        if (StringUtil.isEmpty(time)) {
            T.showToast(mContext, "请选择发起邀请时间");
            return;
        }

        showProgress();
        RequestBossHttp.getSingleton(this).submitSendBossInterview(mID, time, new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                T.showToast(mContext, "发起邀请成功");
                mResultTo.finishBase(mContext);
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
        mIvSendInterviewHear = (ImageView) findViewById(R.id.iv_send_interview_hear);
        mTvSendInterviewName = (TextView) findViewById(R.id.tv_send_interview_name);
        mTvSendInterviewJob = (TextView) findViewById(R.id.tv_send_interview_job);
        mTvSendInterviewTime = (TextView) findViewById(R.id.tv_send_interview_time);
        mBtnSendInterviewSubmit = (Button) findViewById(R.id.btn_send_interview_submit);

        mBtnSendInterviewSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_interview_submit:
                submit();
                break;
        }
    }
}
