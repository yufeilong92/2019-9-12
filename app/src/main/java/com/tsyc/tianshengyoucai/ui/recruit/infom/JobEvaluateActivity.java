package com.tsyc.tianshengyoucai.ui.recruit.infom;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.tsyc.tianshengyoucai.utils.StringUtil;

import org.greenrobot.eventbus.EventBus;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/20 10:55:
 * @Purpose :自我评价
 */
public class JobEvaluateActivity extends Base2Activity implements View.OnClickListener {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private EditText mEtJobevaluateInput;
    private TextView mTvJobevaluateNumber;
    private Button mBtnJobevaluateOver;
    public static final String TYPE = "TYPE";
    private String mContent;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_job_evaluate);
//    initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_job_evaluate;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        if (getIntent() != null) {
            mContent = getIntent().getStringExtra(TYPE);
        }
        initView();
        initEvent();
        bindViewData();
    }

    private void bindViewData() {
        if (StringUtil.isEmpty(mContent)) return;
        mEtJobevaluateInput.setText(mContent);
        mBtnJobevaluateOver.setText("提交");

    }

    private void initEvent() {
        mEtJobevaluateInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String com = StringUtil.getObjectToStr(mEtJobevaluateInput);
                if (StringUtil.isEmpty(com)) {
                    mTvJobevaluateNumber.setText(String.valueOf(0));
                } else {
                    mTvJobevaluateNumber.setText(String.valueOf(com.length()));
                }
            }
        });

    }

    private void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mEtJobevaluateInput = (EditText) findViewById(R.id.et_jobevaluate_input);
        mTvJobevaluateNumber = (TextView) findViewById(R.id.tv_jobevaluate_number);
        mBtnJobevaluateOver = (Button) findViewById(R.id.btn_jobevaluate_over);

        mBtnJobevaluateOver.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_jobevaluate_over:
                submit();
                break;
        }
    }

    private void submit() {
        String input = mEtJobevaluateInput.getText().toString().trim();
        if (TextUtils.isEmpty(input)) {
            Toast.makeText(this, "自我评价不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        showProgress();
        RequestJobHttp.getSingleton(this).submitaddSelfAssessment(input, new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                if (StringUtil.isEmpty(mContent))
                    mResultTo.startRecruitHome(mContext);
                else {
                    EventBus.getDefault().postSticky(new RcRefresh(""));
                    mResultTo.finishBase(mContext);
                }
            }

            @Override
            public void error(String error) {
                onError();
            }
        });
    }
}
