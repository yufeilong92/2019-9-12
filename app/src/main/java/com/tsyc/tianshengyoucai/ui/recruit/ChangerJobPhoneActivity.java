package com.tsyc.tianshengyoucai.ui.recruit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.Eventbuss.ChangerEvent;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.requet.RequestCodeHttp;
import com.tsyc.tianshengyoucai.requet.RequestJobHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.CountdownUtil;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;

import org.greenrobot.eventbus.EventBus;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/29 14:06:
 * @Purpose :修改手机号吗
 */
public class ChangerJobPhoneActivity extends Base2Activity implements View.OnClickListener {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private EditText mEtChangerPhone;
    private EditText mEtChangerCode;
    private Button mBtnSendChangerCode;
    private Button mBtnChangerPhone;
    private CountdownUtil mInstance;

    public static final String TYPE = "type";
    /**
     * 用户信息
     */
    public static final String INFOMTYPE = "infomtype";
    /**
     * 隐私
     */
    public static final String DEILIVETYPE = "delivetype";

    /**
     * 只做校验
     */
    public static final String VERIFYTYPE = "verifytype";
    private String mType;
    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_changer_job_phone);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_changer_job_phone;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        if (getIntent() != null) {
            mType = getIntent().getStringExtra(TYPE);
        }
        initView();
        initEvent();

    }

    private void initEvent() {
        mInstance = CountdownUtil.getInstance();
    }

    private void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mEtChangerPhone = (EditText) findViewById(R.id.et_changer_phone);
        mEtChangerCode = (EditText) findViewById(R.id.et_changer_code);
        mBtnSendChangerCode = (Button) findViewById(R.id.btn_send_changer_code);
        mBtnChangerPhone = (Button) findViewById(R.id.btn_changer_phone);

        mBtnSendChangerCode.setOnClickListener(this);
        mBtnChangerPhone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_changer_code:
                String phone = StringUtil.getObjectToStr(mEtChangerPhone);
                if (StringUtil.isEmpty(phone)) {
                    T.showToast(mContext, "请输入手机号码");
                    return;
                }
                sentCode(phone);
                break;
            case R.id.btn_changer_phone:

                                submit();
                break;
        }
    }

    private void sentCode(String phone) {
        String type = "1";
        if (mType.equals(INFOMTYPE)) {
            type = "6";
        } else if (mType.equals(DEILIVETYPE)) {
            type = "5";
        }else if (mType.equals(VERIFYTYPE)){

        }
        showProgress();
        RequestCodeHttp.getSingleton(this).reqeustCode(type, phone, new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) {
                    return;
                }
                T.showToast(mContext, "发送成功");
                mInstance.startTime(mContext, R.color.color_orange, mBtnSendChangerCode);
            }

            @Override
            public void error(String error) {
                onError();
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mInstance.stop();
    }

    private void submit() {
        String phone = StringUtil.getObjectToStr(mEtChangerPhone);
        if (StringUtil.isEmpty(phone)) {
            T.showToast(mContext, "请输入手机号码");
            return;
        }
        String code = StringUtil.getObjectToStr(mEtChangerCode);
        if (StringUtil.isEmpty(code)) {
            T.showToast(mContext, "请输入验证码");
            return;
        }
        /**
         * 修改绑定手机号码
         */
        int id = R.string.http_jobs_changeMobile;
        if (mType.equals(INFOMTYPE)) {
            //验证绑定手机号
            id = R.string.http_jobs_changeinfomMobile;
        } else if (mType.equals(DEILIVETYPE)) {
            //个人隐私修改手机号码
            id = R.string.http_jobs_changeMobile;
        }

        showProgress();
        RequestJobHttp.getSingleton(this).submitChangeMobile(phone, code, id, new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) {
                    return;
                }
                T.showToast(mContext, "提交成功");
                if (mType.equals(DEILIVETYPE))
                    EventBus.getDefault().postSticky(new ChangerEvent(""));
                else if (mType.equals(INFOMTYPE)) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString(JobInfomActivity.PHONE, phone);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                }
                mResultTo.finishBase(mContext);

            }

            @Override
            public void error(String error) {
                onError();
            }
        });
    }

    private void setResults(String phone) {

    }
}
