package com.tsyc.tianshengyoucai.ui.recruit.boss.sign;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.requet.RequestBossHttp;
import com.tsyc.tianshengyoucai.requet.RequestJobHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.SelectKeyUtil;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.GmSelectBean;
import com.tsyc.tianshengyoucai.vo.JobSelectItemVo;
import com.tsyc.tianshengyoucai.vo.SelectKeyVo;
import com.tsyc.tianshengyoucai.vo.TradeSelectIndox;

import java.util.ArrayList;
import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/26
 * File description： 创建新公司
 */
public class NewCompanyActivity extends Base2Activity implements View.OnClickListener {


    private SelectKeyVo mVo;
    /**
     * 规模
     */
    private GmSelectBean mSelectBean;

    /**
     * 行业
     *
     * @return
     */
    private static final int TRADECODE = 1020;

    /**
     * 用户选择行业
     */
    public static final String SELECT_TRADE = "trade";

    /**
     * 营业执照回调
     */
    public static final String UPDATAIMAGETAG = "updatatag";
    private List<TradeSelectIndox> mSelectTradeLists;
    private TextView mTvChooseRole;
    private TextView mTvName;
    private EditText mEtCompanyName;
    private ConstraintLayout mCtlName;
    private TextView mTvGenderText;
    private EditText mTvShoreName;
    private ConstraintLayout mCtlCompany;
    private TextView mTvPosition;
    private ConstraintLayout mCtlPosition;
    private TextView mTvCount;
    private ConstraintLayout mCtlMode;
    private TextView mTvTradingCertificate;
    private TextView mTvTradingCertificateStatus;
    private ConstraintLayout mCtlUpImage;
    private TextView mTvNext;
    /**
     * 营业执照
     */
    private static final int TRADE_REQUESTCODE = 1011;
    /**
     * 营业执照地址
     */
    private String mTradePath;

    public static final String CompanyName="company_name";
    private String mCompanyName;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_create_new_company);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_create_new_company;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        if (getIntent()!=null) {
            mCompanyName = getIntent().getStringExtra(CompanyName);
        }
        initView();
        bindView();
    }

    private void bindView() {
        mEtCompanyName.setText(mCompanyName);
    }

    private void initView() {
        initRequestSelect();
        mTvChooseRole = (TextView) findViewById(R.id.tv_choose_role);
        mTvChooseRole.setOnClickListener(this);
        mTvName = (TextView) findViewById(R.id.tv_name);
        mTvName.setOnClickListener(this);
        mEtCompanyName = (EditText) findViewById(R.id.et_company_name);
        mEtCompanyName.setOnClickListener(this);
        mCtlName = (ConstraintLayout) findViewById(R.id.ctl_name);
        mCtlName.setOnClickListener(this);
        mTvGenderText = (TextView) findViewById(R.id.tv_gender_text);
        mTvGenderText.setOnClickListener(this);
        mTvShoreName = (EditText) findViewById(R.id.tv_shore_name);
        mTvShoreName.setOnClickListener(this);
        mCtlCompany = (ConstraintLayout) findViewById(R.id.ctl_company);
        mCtlCompany.setOnClickListener(this);
        mTvPosition = (TextView) findViewById(R.id.tv_position);
        mCtlPosition = (ConstraintLayout) findViewById(R.id.ctl_position);
        mCtlPosition.setOnClickListener(this);
        mTvCount = (TextView) findViewById(R.id.tv_count);
        mCtlMode = (ConstraintLayout) findViewById(R.id.ctl_mode);
        mCtlMode.setOnClickListener(this);
        mTvTradingCertificate = (TextView) findViewById(R.id.tv_trading_certificate);
        mTvTradingCertificateStatus = (TextView) findViewById(R.id.tv_trading_certificate_status);
        mCtlUpImage = (ConstraintLayout) findViewById(R.id.ctl_up_image);
        mCtlUpImage.setOnClickListener(this);
        mTvNext = (TextView) findViewById(R.id.tv_next);
        mTvNext.setOnClickListener(this);
    }

    private void initRequestSelect() {
        showProgress();
        RequestJobHttp.getSingleton(this).requestJobsCommonKeys(new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                mVo = GsonUtils.getGson(success, SelectKeyVo.class);
            }

            @Override
            public void error(String error) {
                onError();

            }
        });


    }


    private void initSelect() {
        SelectKeyVo.ResultBean result = mVo.getResult();
        List<GmSelectBean> companySize = result.getCompany_size();
        List<String> lists = new ArrayList<>();
        for (int i = 0; i < companySize.size(); i++) {
            GmSelectBean bean = companySize.get(i);
            lists.add(bean.getDesc());
        }

        SelectKeyUtil.showSelect(this, lists, new SelectKeyUtil.SelectClick() {
            @Override
            public void selectItem(int postion, String com) {
                mSelectBean = companySize.get(postion);
                mTvCount.setText(com);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == TRADECODE) {
            mSelectTradeLists = (List<TradeSelectIndox>) data.getSerializableExtra(SELECT_TRADE);
            bindViewData();
            return;
        }
        if (requestCode == TRADE_REQUESTCODE && resultCode == RESULT_OK) {
            mTradePath = data.getStringExtra(UPDATAIMAGETAG);
            mTvTradingCertificateStatus.setText("已上传");
            return;
        }


    }

    private void bindViewData() {
        TradeSelectIndox indox = mSelectTradeLists.get(0);
        mTvPosition.setText(indox.getSelectVo().getName());
    }

    private void submit() {
        String name = mEtCompanyName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入公司全称", Toast.LENGTH_SHORT).show();
            return;
        }

        String namejian = mTvShoreName.getText().toString().trim();
        if (TextUtils.isEmpty(namejian)) {
            Toast.makeText(this, "简称作为招聘时展示的公司名称", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mSelectTradeLists == null || mSelectTradeLists.isEmpty()) {
            T.showToast(mContext, "请选择公司行业");
            return;
        }

        TradeSelectIndox tradeSelectIndox = mSelectTradeLists.get(0);
        if (tradeSelectIndox == null) {
            T.showToast(mContext, "请选择公司行业");
            return;
        }
        JobSelectItemVo selectVo = tradeSelectIndox.getSelectVo();
        if (selectVo == null) {
            T.showToast(mContext, "请选择公司行业");
            return;

        }

        if (mSelectBean == null) {
            T.showToast(mContext, "请选择公司规模");
            return;
        }
        if (StringUtil.isEmpty(mTradePath)) {
            T.showToast(mContext, "请上传营业执照");
            return;
        }
        showProgress();
        RequestBossHttp.getSingleton(this).submitCompanyADD(name, namejian,String.valueOf( selectVo.getId()),
               String.valueOf(mSelectBean.getValue()) ,
                mTradePath, new RequestResultCallback() {
                    @Override
                    public void success(String success) {
                        if (onSuccess(success)) return;
                        T.showToast(mContext,"提交成功");
                        mResultTo.startCreatCompanyOver(mContext);
                    }

                    @Override
                    public void error(String error) {
                        onError();
                    }
                });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ctl_position://选择行业
                mResultTo.startTrade(mContext, TRADECODE, 1);
                break;
            case R.id.ctl_mode:// 规模
                if (mVo == null) {
                    initRequestSelect();
                    return;
                }
                initSelect();
                break;
            case R.id.tv_next: // 下一步
                submit();
                break;
            case R.id.ctl_up_image://营业执照
                String company = StringUtil.getObjectToStr(mEtCompanyName);
                if (StringUtil.isEmpty(company)) {
                    T.showToast(mContext, "请填写公司名称");
                    return;
                }
                mResultTo.startTradingCertificate(mContext, company, TRADE_REQUESTCODE);
                break;
        }

    }

}
