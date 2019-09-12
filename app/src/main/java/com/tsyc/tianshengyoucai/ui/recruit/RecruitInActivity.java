package com.tsyc.tianshengyoucai.ui.recruit;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.requet.RequestJobHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.DialogUtils;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.RecruitInVo;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/19 10:21:
 * @Purpose :招聘入口
 */
public class RecruitInActivity extends Base2Activity {

    private LinearLayout mLlJobGo;
    private LinearLayout mLlInviteGo;
    public static final String TYPE = "TYPE";
    /**
     * 登录入口
     */
    public static final String LOGINTYPE = "login";
    /**
     * 切换id
     */
    public static final String CHANGERTYPE = "changer";
    /**
     * 身份di
     */
    public static final String ID = "id";
    /**
     * boss
     */
    public static final String BOSS_ID = "boss_id";
    /**
     * job
     */
    public static final String JOB_ID = "job_id";
    private String mType;
    private String mID;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_recruit_in);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_recruit_in;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        if (getIntent() != null) {
            mType = getIntent().getStringExtra(TYPE);
            mID = getIntent().getStringExtra(ID);
        }
        initView();
        initEvent();


    }

    private void initEvent() {


        mLlJobGo.setOnClickListener(new View.OnClickListener() {//求职
            @Override
            public void onClick(View v) {
                if (mType.equals(CHANGERTYPE)) {
                    showAlertDialog(1);
                    return;
                }
                ReuqestId(1);
            }
        });
        mLlInviteGo.setOnClickListener(new View.OnClickListener() {//招聘
            @Override
            public void onClick(View v) {
                if (mType.equals(CHANGERTYPE)) {
                    showAlertDialog(2);
                    return;
                }
                ReuqestId(2);
            }
        });
        if (StringUtil.isEmpty(mID)) return;
        if (mID.equals(BOSS_ID)) {
            mLlInviteGo.setVisibility(View.GONE);
            mLlJobGo.setVisibility(View.VISIBLE);
        } else if (mID.equals(JOB_ID)) {
            mLlJobGo.setVisibility(View.GONE);
            mLlInviteGo.setVisibility(View.VISIBLE);
        }
    }

    private void showAlertDialog(int type) {
        DialogUtils.getSingleton().showSureAlerDialog(mContext, "是否确认切换身份", "",
                "确认", "取消", new DialogUtils.OnDialogSuceClickListener() {
                    @Override
                    public void sureClick() {
                        ReuqestId(type);
                    }

                    @Override
                    public void cannerClick() {

                    }
                });

    }

    private void ReuqestId(int type) {
        showProgress();
        RequestJobHttp.getSingleton(this).requestJobsChangeIdEntity(String.valueOf(type), new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                if (mType.equals(LOGINTYPE)) {//正常进入
                    if (type == 1) {
                        mResultTo.startInviteGo(mContext);
                    } else {
                        mResultTo.startJobGo(mContext);
                    }
                    mResultTo.finishBase(mContext);
                } else if (mType.equals(CHANGERTYPE)) {//切换身份
                    reqeustRecuit();
                }
            }

            @Override
            public void error(String error) {
                onError();
            }
        });
    }

    private void reqeustRecuit() {
        showProgress();
        RequestJobHttp.getSingleton(RecruitInActivity.this).requestTecruitIn(new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) {
                    return;
                }
                RecruitInVo vo = GsonUtils.getGson(success, RecruitInVo.class);
                RecruitInVo.ResultBean bean = vo.getResult();
                startJobActivity(bean);

            }

            @Override
            public void error(String error) {
                onError();
            }
        });

    }

    private void startJobActivity(RecruitInVo.ResultBean vo) {
        switch (vo.getStatus()) {
            case 0://请选择身份
                T.showToast(mContext, "身份异常,请联系客服");
                break;
            case 1:

                switch (vo.getStep()) {
                    case 1://完善求职信息-基础信息
                        mResultTo.startInviteGo(mContext);
                        break;
                    case 2://完善求职信息-教育经历
                        mResultTo.startEducation(mContext);
                        break;
                    case 3://完善求职信息-工作经历
                        mResultTo.startWork(mContext);
                        break;
                    case 4://完善求职信息-求职意向
                        mResultTo.startJobPurpost(mContext);
                        break;
                    case 5://完善求职信-个体评价
                        mResultTo.startEvaluest(mContext);
                        break;
                }
                mResultTo.finishBase(mContext);
                break;
            case 2://进入求职者中心
                mResultTo.startRecruitHome(mContext);
                mResultTo.finishBase(mContext);
                break;
            case 3:
                switch (vo.getStep()) {
                    case 1://完善招聘信息-基础信息
                        mResultTo.startJobGo(mContext);
                        break;
                    case 2://完善招聘信息-实名认证
                        mResultTo.startBossName(mContext);
                        break;
                    case 3://完善招聘信息-发布职位
                        mResultTo.startSendJob(mContext);
                        break;
                }
                mResultTo.finishBase(mContext);
                break;
            case 4://进入boss
                mResultTo.startBossHome(mContext);
                mResultTo.finishBase(mContext);
                break;

        }


    }


    private void initView() {
        mLlJobGo = (LinearLayout) findViewById(R.id.ll_job_go);
        mLlInviteGo = (LinearLayout) findViewById(R.id.ll_invite_go);
    }
}
