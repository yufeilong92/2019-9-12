package com.tsyc.tianshengyoucai.ui.recruit.jobmine;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.Eventbuss.ChangerEvent;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.requet.RequestJobHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.ui.recruit.RecruitInActivity;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.DeliveryVo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/28 17:13:
 * @Purpose :隐私设置
 */
public class JobPrivacyActivity extends Base2Activity implements View.OnClickListener {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private TextView mTvPrivacyPhone;
    private EditText mEtPrivacyEmali;
    private TextView mTvPrivacyStatus;
    private CheckBox mCbPrivacySelect;
    private TextView mGmTvRightTitle;
    private Button mBtnChangerSubmit;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_job_privacy);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_job_privacy;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        initView();
        initEvent();
        initRequest();
    }

    private void initRequest() {
        showProgress();
        RequestJobHttp.getSingleton(this).requestPrivaySetting(new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                DeliveryVo vo = GsonUtils.getGson(success, DeliveryVo.class);
                bindViewData(vo.getResult());

            }

            @Override
            public void error(String error) {
                onError();
            }
        });

    }

    private void bindViewData(DeliveryVo.ResultBean vo) {
        mTvPrivacyPhone.setText(vo.getMobile());
        mEtPrivacyEmali.setText(vo.getEmail());
        mCbPrivacySelect.setChecked(vo.getIs_show() == 0);
        mBtnChangerSubmit.setVisibility(View.GONE);
    }

    private void initEvent() {
        mTvPrivacyPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResultTo.startChanger(mContext);
            }
        });

        mEtPrivacyEmali.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (StringUtil.isEmpty(mEtPrivacyEmali.getText().toString())) {
                    mBtnChangerSubmit.setVisibility(View.GONE);
                } else {
                    mBtnChangerSubmit.setVisibility(View.VISIBLE);
                }
            }
        });
        mCbPrivacySelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!mCbPrivacySelect.isPressed()) return;
                submitJobJianli(isChecked);
            }
        });

    }

    private void submitJobJianli(boolean is) {
        showProgress();
        RequestJobHttp.getSingleton(this).submitchangeState(is ? "0" : "1", new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) {
                    return;
                }
                T.showToast(mContext, "保存成功");
            }

            @Override
            public void error(String error) {
                onError();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().removeAllStickyEvents();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void refrshData(ChangerEvent event) {
        initRequest();
    }

    private void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mTvPrivacyPhone = (TextView) findViewById(R.id.tv_privacy_phone);
        mEtPrivacyEmali = (EditText) findViewById(R.id.et_privacy_emali);
        mTvPrivacyStatus = (TextView) findViewById(R.id.tv_privacy_status);
        mCbPrivacySelect = (CheckBox) findViewById(R.id.cb_privacy_select);
        mGmTvRightTitle = (TextView) findViewById(R.id.gm_tv_right_title);
        mGmTvRightTitle.setOnClickListener(this);
        mBtnChangerSubmit = (Button) findViewById(R.id.btn_changer_submit);
        mBtnChangerSubmit.setOnClickListener(this);
        mTvPrivacyStatus.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_changer_submit:
                String emali = StringUtil.getObjectToStr(mEtPrivacyEmali);
                if (StringUtil.isEmpty(emali)) {
                    T.showToast(mContext, "请输入邮箱");
                    return;
                }
                submit(emali);
                break;
            case R.id.tv_privacy_status://切换身份
                  mResultTo.startRecruitInHome(mContext, RecruitInActivity.CHANGERTYPE,RecruitInActivity.JOB_ID);
                break;
        }
    }

    private void submit(String emali) {
        showProgress();
        RequestJobHttp.getSingleton(this).submitchangeEmail(emali, new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                T.showToast(mContext, "修改成功");
                mBtnChangerSubmit.setVisibility(View.GONE);
            }

            @Override
            public void error(String error) {
                onError();
            }
        });
    }
}
