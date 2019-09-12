package com.tsyc.tianshengyoucai.ui.recruit.infom;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tsyc.tianshengyoucai.Eventbuss.RcRefresh;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.requet.RequestJobHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.DialogUtils;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.utils.Util;
import com.tsyc.tianshengyoucai.vo.JobSelectIndox;
import com.tsyc.tianshengyoucai.vo.ResumeVo;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;

import cn.addapp.pickers.picker.DatePicker;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/20 10:16:
 * @Purpose :工作经历
 */
public class WorkInfomActivity extends Base2Activity implements View.OnClickListener {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private EditText mEtWorkinfomCompanyname;
    private TextView mTvWorkinfomType;
    private EditText mEtWorkinfomInfom;
    private Button mBtnWorkinfomGo;
    /**
     * 工作经历职位
     */
    private static final int WORKINFOM_REQUESTCODE = 1004;
    /**
     * 工作职位
     */
    private static final String WORKINFOM_JOB = "job";
    private JobSelectIndox mSelectJobVo;
    private DialogUtils mDialogUtils;
    private AlertDialog mBirthdayAlertDialog;

    private int mStartYearParam;
    private int mStartMonthParam;
    private int mStartDayParam;
    private int mEndYearParam;
    private int mEndMonthParam;
    private int mEndDayParam;
    private TextView mTvWorkinfomStarttime;
    private TextView mTvWorkinfomEndtime;
    public static final String TYPE = "type";
    public static final String ADDTYPE = "add";
    public static final String ADDTYPES = "adds";
    private ResumeVo.WorksBean mData;
    private String mAdd;
    private TextView mGmTvRightTitle;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_work_infom);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_work_infom;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        if (getIntent() != null) {
            mData = (ResumeVo.WorksBean) getIntent().getSerializableExtra(TYPE);
            mAdd = getIntent().getStringExtra(ADDTYPE);
        }

        initView();
        initEvent();
        bindView();
    }

    private void bindView() {
        if (mData != null) {
            mEtWorkinfomCompanyname.setText(mData.getCompany());
            mTvWorkinfomStarttime.setText(mData.getStart_time());
            mTvWorkinfomEndtime.setText(mData.getEnd_time());
            mTvWorkinfomType.setText(mData.getPosition());
            mEtWorkinfomInfom.setText(mData.getDesc());
            String startTime = mData.getStart_time();
            if (!StringUtil.isEmpty(startTime)) {
                String[] split = startTime.split("-");
                mStartYearParam = Integer.parseInt(split[0]);
                mStartMonthParam = Integer.parseInt(split[1]);
                mStartDayParam = Integer.parseInt(split[2]);
            }
            String endTime = mData.getEnd_time();
            if (!StringUtil.isEmpty(endTime)) {
                String[] split = endTime.split("-");
                mEndYearParam = Integer.parseInt(split[0]);
                mEndMonthParam = Integer.parseInt(split[1]);
                mEndDayParam = Integer.parseInt(split[2]);
            }
            mBtnWorkinfomGo.setText("提交");
            mGmTvRightTitle.setText("删除");
            mGmTvRightTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteWork(mData.getId());
                }
            });
            return;
        }
        if (!StringUtil.isEmpty(mAdd)) {
            mBtnWorkinfomGo.setText("提交");
            return;
        }
    }

    private void deleteWork(int id) {
        DialogUtils.getSingleton().showSureAlerDialog(mContext, "是否确认删除工作经历",
                "", "确认", "取消", new DialogUtils.OnDialogSuceClickListener() {
                    @Override
                    public void sureClick() {
                        submitDeleteWork(id);

                    }

                    @Override
                    public void cannerClick() {

                    }
                });

    }

    private void submitDeleteWork(int id) {
        showProgress();
        RequestJobHttp.getSingleton(this).submitDeleteWork(String.valueOf(id), new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) {
                    return;
                }
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

    private void initEvent() {
        mDialogUtils = DialogUtils.getSingleton();
        mDialogUtils = DialogUtils.getSingleton();
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        mStartYearParam = c.get(Calendar.YEAR);
        mStartMonthParam = c.get(Calendar.MONTH);
        mStartDayParam = c.get(Calendar.DATE);
        mEndYearParam = c.get(Calendar.YEAR);
        mEndMonthParam = c.get(Calendar.MONTH);
        mEndDayParam = c.get(Calendar.DATE);
    }

    private void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mEtWorkinfomCompanyname = (EditText) findViewById(R.id.et_workinfom_companyname);
        mTvWorkinfomType = (TextView) findViewById(R.id.tv_workinfom_type);
        mTvWorkinfomType.setOnClickListener(this);
        mEtWorkinfomInfom = (EditText) findViewById(R.id.et_workinfom_infom);
        mBtnWorkinfomGo = (Button) findViewById(R.id.btn_workinfom_go);
        mBtnWorkinfomGo.setOnClickListener(this);
        mTvWorkinfomStarttime = (TextView) findViewById(R.id.tv_workinfom_starttime);
        mTvWorkinfomStarttime.setOnClickListener(this);
        mTvWorkinfomEndtime = (TextView) findViewById(R.id.tv_workinfom_endtime);
        mTvWorkinfomEndtime.setOnClickListener(this);
        mGmTvRightTitle = (TextView) findViewById(R.id.gm_tv_right_title);
        mGmTvRightTitle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_workinfom_go:
                submit();
                break;
            case R.id.tv_workinfom_type:
                mResultTo.startJobsSelect(mContext, WORKINFOM_REQUESTCODE);
                break;
            case R.id.tv_workinfom_starttime:
                if (Util.handleOnDoubleClick()) return;
                selectTime(1);
                break;
            case R.id.tv_workinfom_endtime:
                if (Util.handleOnDoubleClick()) return;
                selectTime(2);
                break;
        }
    }

    private void selectTime(int i) {
        DatePicker picker = new DatePicker(this);
        picker.setCanLoop(false);
        picker.setWheelModeEnable(true);
        picker.setTopPadding(15);
        picker.setRangeStart(1911, 1, 1);
        picker.setRangeEnd(3000, 1, 1);
        if (i == 1)
            picker.setSelectedItem(mStartYearParam, mStartMonthParam + 1, mStartDayParam);
        else
            picker.setSelectedItem(mEndYearParam, mEndMonthParam + 1, mEndDayParam);
        picker.setWeightEnable(true);
        picker.setLineColor(Color.WHITE);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                if (i == 1) {
                    mStartYearParam = Integer.parseInt(year);
                    mStartMonthParam = Integer.parseInt(month) - 1;
                    mStartDayParam = Integer.parseInt(day);
                    mTvWorkinfomStarttime.setText(year + "-" + month + "-" + day);
                    return;
                }
                mEndYearParam = Integer.parseInt(year);
                mEndMonthParam = Integer.parseInt(month) - 1;
                mEndDayParam = Integer.parseInt(day);
                mTvWorkinfomEndtime.setText(year + "-" + month + "-" + day);
            }
        });
        picker.show();
    }

    private void submit() {
        String companyname = mEtWorkinfomCompanyname.getText().toString().trim();
        if (TextUtils.isEmpty(companyname)) {
            Toast.makeText(this, "请填写公司名称", Toast.LENGTH_SHORT).show();
            return;
        }
        String startfom = StringUtil.getObjectToStr(mTvWorkinfomStarttime);
        if (StringUtil.isEmpty(startfom)) {
            T.showToast(mContext, "请选择在职时间");
            return;
        }
        String endInfom = StringUtil.getObjectToStr(mTvWorkinfomEndtime);
        if (StringUtil.isEmpty(endInfom)) {
            T.showToast(mContext, "请选择离职时间");
            return;
        }
        String type = StringUtil.getObjectToStr(mTvWorkinfomType);
        if (StringUtil.isEmpty(type)) {
            T.showToast(mContext, "请选择职位类型");
            return;
        }

        String infom = mEtWorkinfomInfom.getText().toString().trim();
        if (TextUtils.isEmpty(infom)) {
            Toast.makeText(this, "请填写您在公司的工作内容", Toast.LENGTH_SHORT).show();
            return;
        }
        showProgress();
        RequestJobHttp.getSingleton(this).submitWorkExperience(companyname, type, startfom, endInfom,
                String.valueOf(mSelectJobVo.getTypethree()),
                mData==null? "":String.valueOf(mData.getId()), new RequestResultCallback() {
                    @Override
                    public void success(String success) {
                        if (onSuccess(success)) return;
                        T.showToast(mContext, "提交成功");
                        if (mData != null) {
                            EventBus.getDefault().postSticky(new RcRefresh(""));
                            mResultTo.finishBase(mContext);
                            return;
                        }
                        if (!StringUtil.isEmpty(mAdd)) {
                            EventBus.getDefault().postSticky(new RcRefresh(""));
                            mResultTo.finishBase(mContext);
                            return;
                        }
                        mResultTo.startJobPurpost(mContext);

                    }

                    @Override
                    public void error(String error) {
                        onError();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        if (requestCode == WORKINFOM_REQUESTCODE && resultCode == RESULT_OK) {
            mSelectJobVo = (JobSelectIndox) data.getSerializableExtra(WORKINFOM_JOB);
            mTvWorkinfomType.setText(mSelectJobVo.getSelectVo().getName());
        }
    }
}
