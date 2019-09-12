package com.tsyc.tianshengyoucai.ui.recruit.boss;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.requet.RequestBossHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.DialogUtils;
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.SystemUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.BossInterViewVo;
import com.tsyc.tianshengyoucai.vo.ChatIdVo;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/9/5 16:03:
 * @Purpose :面试界面
 */
public class BossInterviewActivity extends Base2Activity implements View.OnClickListener {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private TextView mTvInterviewName;
    private TextView mTvInterviewStatus;
    private ImageView mIvInterviewHear;
    private TextView mTvInterviewJianli;
    private TextView mTvInterviewChat;
    private TextView mTvInterviewPhone;
    private TextView mTvInterviewTime;
    private TextView mTvInterviewAddress;
    private TextView mTvInterviewJob;
    private Button mBtnBossInterviewNo;
    private Button mBtnBossInterviewOver;
    private LinearLayout mLlButtom;
    /**
     * 简历id
     */
    public static final String JIANLI_ID = "jianliid";
    public static final String TIME = "time";
    /**
     * 类型
     */
    public static final String TYPE = "type";
    /**
     * 显示按钮
     */
    public static final String SHOWBTN_TYPE = "show_type";
    /**
     * 不显示按钮
     */
    public static final String NO_SHOWBTN_TYPE = "no_show_type";

    private String mJianLiId;
    private String mTime;
    private BossInterViewVo.ResultBean mResult;
    private RelativeLayout mRlBossInterview;
    private String mType;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_boss_interview);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_boss_interview;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        if (getIntent() != null) {
            mJianLiId = getIntent().getStringExtra(JIANLI_ID);
            mTime = getIntent().getStringExtra(TIME);
            mType = getIntent().getStringExtra(TYPE);
        }
        initView();
        initEvent();
        initRequest();
    }

    private void initEvent() {
        mRlBossInterview.setVisibility(mType.equals(SHOWBTN_TYPE) ? View.VISIBLE : View.GONE);
        mTvInterviewChat.setOnClickListener(v -> {
            getRecivrid();
        });
    }

    /**
     * 获取对话id
     */
    private void getRecivrid() {
        showProgress();
        RequestBossHttp.getSingleton(this).submitBossStartTalk(
                String.valueOf(mResult.getBoss_position_id()), String.valueOf(mResult.getUser_cv_id()), new RequestResultCallback() {
                    @Override
                    public void success(String success) {
                        if (onSuccess(success)) return;
                        ChatIdVo vo = GsonUtils.getGson(success, ChatIdVo.class);
                        BossInterViewVo.ResultBean.CvBean cv = mResult.getCv();
                        mResultTo.starBossChat(mContext, vo.getResult().getRecord_id(),cv.getUsername());
                    }

                    @Override
                    public void error(String error) {
                        onError();
                    }
                });

    }

    private void initRequest() {
        showProgress();
        RequestBossHttp.getSingleton(this).requestBossInterviewDetail(mJianLiId, mTime, new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                BossInterViewVo mVo = GsonUtils.getGson(success, BossInterViewVo.class);
                mResult = mVo.getResult();
                bindViewData(mResult);
            }

            @Override
            public void error(String error) {
                onError();
            }
        });
    }

    private void bindViewData(BossInterViewVo.ResultBean result) {

        BossInterViewVo.ResultBean.CvBean cv = result.getCv();
        mTvInterviewName.setText("与 " + cv.getUsername() + " 的面试");
        GlideUtil.getSingleton().loadQuadRangleImager(mContext, mIvInterviewHear, cv.getAvatar());
        mTvInterviewTime.setText("面试时间：" + result.getInterview_time());

        BossInterViewVo.ResultBean.AddressInfoBean info = result.getAddress_info();
        mTvInterviewAddress.setText("面试地点：" + info.getProvince() + info.getCity() + info.getArea() + info.getAddress());

        mTvInterviewJob.setText("面试职位：" + result.getPosition_name() + " " + result.getSalary().getDesc());
        mTvInterviewPhone.setOnClickListener(v -> {
            SystemUtil.playPhone(mContext, cv.getMobile());
        });

    }

    private void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mTvInterviewName = (TextView) findViewById(R.id.tv_interview_name);
        mTvInterviewStatus = (TextView) findViewById(R.id.tv_interview_status);
        mIvInterviewHear = (ImageView) findViewById(R.id.iv_interview_hear);
        mTvInterviewJianli = (TextView) findViewById(R.id.tv_interview_jianli);
        mTvInterviewChat = (TextView) findViewById(R.id.tv_interview_chat);
        mTvInterviewPhone = (TextView) findViewById(R.id.tv_interview_phone);
        mTvInterviewTime = (TextView) findViewById(R.id.tv_interview_time);
        mTvInterviewAddress = (TextView) findViewById(R.id.tv_interview_address);
        mTvInterviewJob = (TextView) findViewById(R.id.tv_interview_job);
        mBtnBossInterviewNo = (Button) findViewById(R.id.btn_boss_interview_no);
        mBtnBossInterviewNo.setOnClickListener(this);
        mBtnBossInterviewOver = (Button) findViewById(R.id.btn_boss_interview_over);
        mBtnBossInterviewOver.setOnClickListener(this);
        mLlButtom = (LinearLayout) findViewById(R.id.ll_buttom);
        mLlButtom.setOnClickListener(this);
        mRlBossInterview = (RelativeLayout) findViewById(R.id.rl_boss_interview);
        mRlBossInterview.setOnClickListener(this);
        mTvInterviewJianli.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_boss_interview_no:
                showAlertDialog(2);
                break;
            case R.id.btn_boss_interview_over:
                showAlertDialog(3);
                break;
            case R.id.tv_interview_jianli://在线简历
                mResultTo.startBossLookJianli(mContext, mJianLiId, BossLookResumeActivity.NO_BTN);
                break;
        }
    }

    /**
     * @param type 2 人没到，3 结束
     */
    private void showAlertDialog(int type) {
        DialogUtils.getSingleton().showSureAlerDialog(mContext, "是否确认", "",
                "确认", "取消", new DialogUtils.OnDialogSuceClickListener() {
                    @Override
                    public void sureClick() {
                        submitResult(type);
                    }

                    @Override
                    public void cannerClick() {

                    }
                });

    }

    /**
     * @param type 2 人没到，3 结束
     */
    private void submitResult(int type) {
        showProgress();
        RequestBossHttp.getSingleton(this).submitBossInterviewResult(type, mJianLiId, new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) {
                    return;
                }
                T.showToast(mContext, "提交成功");
                mResultTo.finishBase(mContext);
            }

            @Override
            public void error(String error) {
                onError();
            }
        });

    }
}
