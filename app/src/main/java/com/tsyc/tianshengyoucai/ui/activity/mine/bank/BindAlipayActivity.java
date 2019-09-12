package com.tsyc.tianshengyoucai.ui.activity.mine.bank;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.requet.RequestSettingHttp;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.SaveUserInfomUtilJave;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.AlipayVo;
import com.tsyc.tianshengyoucai.vo.UserInfomVo;
import com.youth.xframe.utils.XRegexUtils;
import com.youth.xframe.utils.log.XLog;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/14 14:27:
 * @Purpose :绑定支付宝
 */

public class BindAlipayActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private TextView mGmTvRightTitle;
    private EditText mEtAlipayName;
    private EditText mEtAlipayId;
    private Button mBtnBindAlipay;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bind_alipay);
//        initView();
//    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_bind_alipay;
    }

    @Override
    public void initData() {
        initView();
        initEvent();
        initRequest();
    }

    private void initEvent() {


    }

    private void initRequest() {
        showProgress();
        RequestSettingHttp.getSingleton(this).requestMyAlipay(new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                XLog.e("" + success);
                NormalBean vos = GsonUtils.getGson(success, NormalBean.class);
                if (vos.getCode().equals("100")) {
                    T.showToast(mContext, vos.getMessage());
                    return;
                }
                AlipayVo vo = GsonUtils.getGson(success, AlipayVo.class);

                AlipayVo.ResultBean data = vo.getResult();
                if (data.getBindAliPayStatus() != 1) {
                    mBtnBindAlipay.setText("确认绑定");
                    return;
                }
                bindViewData(data);

            }

            @Override
            public void error(String error) {
                dismissProgress();
                toastErrorNet();
            }
        });
    }

    private void bindViewData(AlipayVo.ResultBean data) {
        AlipayVo.ResultBean.AlipayBean vo = data.getAlipay();
        mEtAlipayName.setText(vo.getReal_name());
        mEtAlipayId.setText(vo.getAccount());
        mBtnBindAlipay.setText("确认修改");
        mGmTvRightTitle.setText("解绑");
        mGmTvRightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResultTo.startUnBind(mContext, vo.getMember_mobile());
            }
        });
    }


    public void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResultTo.finishBase(mContext);
            }
        });
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mGmTvRightTitle = (TextView) findViewById(R.id.gm_tv_right_title);
        mEtAlipayName = (EditText) findViewById(R.id.et_alipay_name);
        mEtAlipayId = (EditText) findViewById(R.id.et_alipay_id);
        mBtnBindAlipay = (Button) findViewById(R.id.btn_bind_alipay);
        mBtnBindAlipay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bind_alipay:
                submit();
                break;
        }
    }

    private void submit() {
        String name = mEtAlipayName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请填写您的真实姓名", Toast.LENGTH_SHORT).show();
            return;
        }

        String id = mEtAlipayId.getText().toString().trim();
        if (TextUtils.isEmpty(id)) {
            Toast.makeText(this, "请填写支付宝账号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!XRegexUtils.checkMobile(id) && !XRegexUtils.checkEmail(id)) {
            Toast.makeText(this, "请填写正确的支付宝账号", Toast.LENGTH_SHORT).show();
            return;
        }
        showProgress();
        RequestSettingHttp.getSingleton(this).submitAlipayBind(name, id, new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                NormalBean vo = GsonUtils.getGson(success, NormalBean.class);
                if (vo.getCode().equals("100")) {
                    T.showToast(mContext, vo.getMessage());
                    return;
                }
                T.showToast(mContext, "绑定成功");

                SaveUserInfomUtilJave instance = SaveUserInfomUtilJave.getInstance();
                UserInfomVo infom = instance.getUserInfom();
                UserInfomVo.ResultBean result = infom.getResult();
                result.setBindAliPayStatus(1);
                infom.setResult(result);
                instance.delectUserInfom();
                instance.putUserInfom(infom);
                mResultTo.finishBase(mContext);
            }

            @Override
            public void error(String error) {
                dismissProgress();
                toastErrorNet();
            }
        });

    }
}
