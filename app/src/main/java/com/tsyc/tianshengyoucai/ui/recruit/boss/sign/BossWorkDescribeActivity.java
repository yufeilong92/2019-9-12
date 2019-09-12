package com.tsyc.tianshengyoucai.ui.recruit.boss.sign;

import android.content.Intent;
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

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.ReleaseJobActivity;
import com.tsyc.tianshengyoucai.utils.StringUtil;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/9/3 17:33:
 * @Purpose :职位描述
 */
public class BossWorkDescribeActivity extends Base2Activity implements View.OnClickListener {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private EditText mEtBossWorkInput;
    private TextView mTvBossWorkNumber;
    private Button mBtnBossWorkSubmit;

    public static final String INFOMWORK = "infom";
    public static final String TYPE = "type";
    private String mInFomWOrk;
    private String mType;
    private TextView mTvBossDescirberTitle;
    private TextView mTvBossDescirberWaraOne;
    private TextView mTvBossDescirberWaraTwo;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_boss_work_describe);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_boss_work_describe;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        if (getIntent() != null) {
            mInFomWOrk = getIntent().getStringExtra(INFOMWORK);
            mType = getIntent().getStringExtra(TYPE);
        }
        initView();
        initEvent();
        bindView();
    }

    private void bindView() {
        if (!StringUtil.isEmpty(mType)) {
            mTvBossDescirberTitle.setText("公司介绍");
            mTvBossDescirberWaraOne.setText("详细准确的公司描述能获得更多求职者关注");
            mTvBossDescirberWaraTwo.setVisibility(View.INVISIBLE);
        }

        mEtBossWorkInput.setText(mInFomWOrk);
        if (!StringUtil.isEmpty(mInFomWOrk))
            mEtBossWorkInput.setSelection(mInFomWOrk.length());
    }

    private void initEvent() {
        mEtBossWorkInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String com = StringUtil.getObjectToStr(mEtBossWorkInput);
                if (StringUtil.isEmpty(com)) {
                    mTvBossWorkNumber.setText(String.valueOf(0));
                } else {
                    mTvBossWorkNumber.setText(String.valueOf(com.length()));
                }
            }
        });

    }

    private void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mEtBossWorkInput = (EditText) findViewById(R.id.et_boss_work_input);
        mTvBossWorkNumber = (TextView) findViewById(R.id.tv_boss_work_number);
        mBtnBossWorkSubmit = (Button) findViewById(R.id.btn_boss_work_submit);

        mBtnBossWorkSubmit.setOnClickListener(this);
        mTvBossDescirberTitle = (TextView) findViewById(R.id.tv_boss_Descirber_title);
        mTvBossDescirberTitle.setOnClickListener(this);
        mTvBossDescirberWaraOne = (TextView) findViewById(R.id.tv_boss_Descirber_wara_one);
        mTvBossDescirberWaraOne.setOnClickListener(this);
        mTvBossDescirberWaraTwo = (TextView) findViewById(R.id.tv_boss_Descirber_wara_two);
        mTvBossDescirberWaraTwo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_boss_work_submit:
                submit();
                break;
        }
    }

    private void submit() {
        String input = mEtBossWorkInput.getText().toString().trim();
        if (TextUtils.isEmpty(input)) {
            Toast.makeText(this, "请填写职位描述", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString(ReleaseJobActivity.INPUTWORK, input);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        mResultTo.finishBase(mContext);
    }
}
