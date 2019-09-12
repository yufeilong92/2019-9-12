package com.tsyc.tianshengyoucai.ui.recruit.infom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.Eventbuss.RcRefresh;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.requet.RequestJobHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.DialogUtils;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.utils.Util;
import com.tsyc.tianshengyoucai.vo.GmSelectBean;
import com.tsyc.tianshengyoucai.vo.ResumeVo;
import com.tsyc.tianshengyoucai.vo.SelectKeyVo;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.picker.DatePicker;
import cn.addapp.pickers.picker.SinglePicker;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/20 9:52:
 * @Purpose :教育经历
 */
public class EducationInfomActivity extends Base2Activity implements View.OnClickListener {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private EditText mEtEducationSchool;
    private TextView mTvEducationEducation;
    private EditText mEtEducationSpecialty;
    private Button mBtnEducationGo;

    private int mEduStartYearParam;
    private int mEduStartMonthParam;
    private int mEduStartDayParam;
    private int mEduEndYearParam;
    private int mEduEndMonthParam;
    private int mEduEndDayParam;
    private DialogUtils mDialogUtils;
    private AlertDialog mBirthdayAlertDialog;
    private SelectKeyVo mVo;
    private TextView mTvEducationStarttime;
    private TextView mTvEducationEndtime;
    private GmSelectBean mSelectEdu;
    public static final String TYPE = "TYPE";
    public static final String ADDTYPE = "ADD";
    public static final String ADDTYPES = "ADDTYPEs";
    private ResumeVo.EducationsBean mData;
    private String mADD;
    private TextView mGmTvRightTitle;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_education_infom);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_education_infom;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        if (getIntent() != null) {
            mData = (ResumeVo.EducationsBean) getIntent().getSerializableExtra(TYPE);
            mADD = getIntent().getStringExtra(ADDTYPE);
        }

        initView();
        initEvent();
        initReqeust();
        bindView();


    }

    private void bindView() {
        if (mData != null) {
            mEtEducationSchool.setText(mData.getSchool());
            mSelectEdu = mData.getEducation();
            mTvEducationEducation.setText(mData.getEducation().getDesc());
            mEtEducationSpecialty.setText(mData.getSpeciality());
            mTvEducationStarttime.setText(mData.getStart_time());
            mTvEducationEndtime.setText(mData.getEnd_time());
            String startTime = mData.getStart_time();
            if (!StringUtil.isEmpty(startTime)) {
                String[] split = startTime.split("-");
                mEduStartYearParam = Integer.parseInt(split[0]);
                mEduStartMonthParam = Integer.parseInt(split[1]);
                mEduStartDayParam = Integer.parseInt(split[2]);
            }
            String endTime = mData.getEnd_time();
            if (!StringUtil.isEmpty(endTime)) {
                String[] split = endTime.split("-");
                mEduEndYearParam = Integer.parseInt(split[0]);
                mEduEndMonthParam = Integer.parseInt(split[1]);
                mEduEndDayParam = Integer.parseInt(split[2]);
            }
            mBtnEducationGo.setText("提交");
            mGmTvRightTitle.setText("删除");
            mGmTvRightTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAlertDelete(mData.getId());
                }
            });
            return;
        }
        if (!StringUtil.isEmpty(mADD)) {
            mBtnEducationGo.setText("提交");
            return;
        }
    }

    private void showAlertDelete(int id) {
        DialogUtils.getSingleton().showSureAlerDialog(mContext, "是否确认删除教育经历",
                "", "确认", "取消", new DialogUtils.OnDialogSuceClickListener() {
                    @Override
                    public void sureClick() {
                        submitDelete(id);
                    }

                    @Override
                    public void cannerClick() {

                    }
                });

    }

    private void submitDelete(int id) {
        showProgress();
        RequestJobHttp.getSingleton(this).submitDeleteEdu(String.valueOf(id), new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                T.showToast(mContext, "删除成功");
                EventBus.getDefault().postSticky(new RcRefresh(""));
                mResultTo.finishBase(mContext);
            }

            @Override
            public void error(String error) {
                onError();
            }
        });

    }

    private void initReqeust() {
        showProgress();
        RequestJobHttp.getSingleton(this).requestJobsCommonKeys(new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) {
                    return;
                }
                mVo = GsonUtils.getGson(success, SelectKeyVo.class);
            }

            @Override
            public void error(String error) {
                onError();
            }
        });

    }


    private void initEvent() {
        mDialogUtils = DialogUtils.getSingleton();
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        mEduStartYearParam = c.get(Calendar.YEAR);
        mEduStartMonthParam = c.get(Calendar.MONTH);
        mEduStartDayParam = c.get(Calendar.DATE);
        mEduEndYearParam = c.get(Calendar.YEAR);
        mEduEndMonthParam = c.get(Calendar.MONTH);
        mEduEndDayParam = c.get(Calendar.DATE);
    }

    private void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mEtEducationSchool = (EditText) findViewById(R.id.et_education_school);
        mTvEducationEducation = (TextView) findViewById(R.id.tv_education_education);
        mEtEducationSpecialty = (EditText) findViewById(R.id.et_education_specialty);
        mBtnEducationGo = (Button) findViewById(R.id.btn_education_go);
        mTvEducationEducation.setOnClickListener(this);
        mBtnEducationGo.setOnClickListener(this);
        mTvEducationStarttime = (TextView) findViewById(R.id.tv_education_starttime);
        mTvEducationStarttime.setOnClickListener(this);
        mTvEducationEndtime = (TextView) findViewById(R.id.tv_education_endtime);
        mTvEducationEndtime.setOnClickListener(this);
        mGmTvRightTitle = (TextView) findViewById(R.id.gm_tv_right_title);
        mGmTvRightTitle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_education_go:
                submit();
                break;
            case R.id.tv_education_education://选择学历
                if (mVo == null) {
                    initReqeust();
                    return;
                }
                showSelectDialog();
                break;
            case R.id.tv_education_starttime:
                if (Util.handleOnDoubleClick()) return;
                selectTime(1, mEduStartYearParam, mEduStartMonthParam, mEduStartDayParam);
                break;
            case R.id.tv_education_endtime:
                if (Util.handleOnDoubleClick()) return;
                selectTime(2, mEduEndYearParam, mEduEndMonthParam, mEduEndDayParam);
                break;
        }
    }

    private void showSelectDialog() {
        List<GmSelectBean> education = mVo.getResult().getEducation();

        if (education == null || education.isEmpty()) return;
        ArrayList<String> items = new ArrayList<>();
        for (int i = 0; i < education.size(); i++) {
            GmSelectBean bean = education.get(i);
            items.add(bean.getDesc());
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
                mSelectEdu = education.get(i);
                mTvEducationEducation.setText(com);
            }
        });
        picker.show();

    }

    private void selectTime(int i, int year, int month, int day) {
        DatePicker picker = new DatePicker(this);
        picker.setCanLoop(false);
        picker.setWheelModeEnable(true);
        picker.setTopPadding(15);
        picker.setRangeStart(1911, 1, 1);
        picker.setRangeEnd(3000, 1, 1);
        picker.setSelectedItem(year, month + 1, day);
        picker.setWeightEnable(true);
        picker.setLineColor(Color.WHITE);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                if (i == 1) {
                    mEduStartYearParam = Integer.parseInt(year);
                    mEduStartMonthParam = Integer.parseInt(month) - 1;
                    mEduStartDayParam = Integer.parseInt(day);
                    mTvEducationStarttime.setText(year + "-" + month + "-" + day);
                    return;
                }
                mEduEndYearParam = Integer.parseInt(year);
                mEduEndMonthParam = Integer.parseInt(month) - 1;
                mEduEndDayParam = Integer.parseInt(day);
                mTvEducationEndtime.setText(year + "-" + month + "-" + day);
            }
        });
        picker.show();
    }

    public void submit() {
        String school = StringUtil.getObjectToStr(mEtEducationSchool);
        if (StringUtil.isEmpty(school)) {
            T.showToast(mContext, "请选择学校名称");
            return;
        }
        if (mSelectEdu == null) {
            T.showToast(mContext, "请选择学历");
            return;
        }
        String education = StringUtil.getObjectToStr(mTvEducationEducation);
        if (StringUtil.isEmpty(education)) {
            T.showToast(mContext, "请选择学历");
            return;
        }
        String specialty = StringUtil.getObjectToStr(mEtEducationSpecialty);
        if (StringUtil.isEmpty(specialty)) {
            T.showToast(mContext, "选择填写专业名称");
            return;
        }
        String starttime = StringUtil.getObjectToStr(mTvEducationStarttime);
        if (StringUtil.isEmpty(starttime)) {
            T.showToast(mContext, "请选择开始时间段");
            return;
        }

        String endtime = StringUtil.getObjectToStr(mTvEducationEndtime);
        if (StringUtil.isEmpty(endtime)) {
            T.showToast(mContext, "请选择结束时间段");
            return;
        }
        showProgress();
        RequestJobHttp.getSingleton(this).submitAddEdu(school, String.valueOf(mSelectEdu.getValue()),
                specialty, starttime, endtime, "",mData==null?"":String.valueOf(mData.getId()), new RequestResultCallback() {
                    @Override
                    public void success(String success) {
                        if (onSuccess(success)) return;
                        T.showToast(mContext, "提交成功");
                        if (mData != null) {
                            mResultTo.finishBase(mContext);
                            EventBus.getDefault().postSticky(new RcRefresh(""));
                            return;
                        }
                        if (!StringUtil.isEmpty(mADD)) {
                            mResultTo.finishBase(mContext);
                            EventBus.getDefault().postSticky(new RcRefresh(""));
                            return;
                        }
                        mResultTo.startWork(mContext);
                    }

                    @Override
                    public void error(String error) {
                        onError();
                    }
                });

    }
}
