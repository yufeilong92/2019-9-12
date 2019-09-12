package com.tsyc.tianshengyoucai.ui.recruit.boss.sign;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.requet.RequestBossHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.T;
import com.youth.xframe.utils.XRegexUtils;
import com.youth.xframe.widget.XToast;

/**
 * author：van
 * CreateTime：2019/8/26
 * File description：实名认证
 */
public class BossNameActivity extends Base2Activity {


    private EditText mEtBossOneRealName;
    private EditText mEtBossOneCardId;
    private TextView mTvBossOneNext;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_boos_name);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_boos_name;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        initView();
        initEvent();


    }

    private void initEvent() {
        mTvBossOneNext.setOnClickListener(v -> {
            submit();
        });
    }



    private void initView() {

        mEtBossOneRealName = (EditText) findViewById(R.id.et_boss_one_real_name);
        mEtBossOneCardId = (EditText) findViewById(R.id.et_boss_one_card_id);
        mTvBossOneNext = (TextView) findViewById(R.id.tv_boss_one_next);
    }

    private void submit() {
        String realName = mEtBossOneRealName.getText().toString().trim();
        String idCard = mEtBossOneCardId.getText().toString().trim();

        if (TextUtils.isEmpty(realName)) {
            XToast.normal("请输入姓名");
            return;
        }
        if (!XRegexUtils.checkIdCard(idCard)) {
            XToast.normal("请输入正确的身份证号");
            return;
        }
        showProgress();
        RequestBossHttp.getSingleton(this).submitCheckIDinfo(realName, idCard, new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                T.showToast(mContext,"提交成功");
                mResultTo.startSendJob(mContext);
            }

            @Override
            public void error(String error) {
                onError();
            }
        });

    }

}
