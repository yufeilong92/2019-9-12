package com.tsyc.tianshengyoucai.ui.activity.mine.money;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.view.MoneyTextWatcher;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/7 20:17:
 * @Purpose :提现数据
 */
public class WithdrawActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private TextView mGmTvRightTitle;
    private EditText mEtWithdraw;
    private TextView mTvMyMoneyLeft;
    private TextView mTvMyMoneyValue;
    private ImageView mIvPayWeixin;
    private LinearLayout mLlPayWeixin;
    private ImageView mIvPayZhifu;
    private LinearLayout mLlPayZhifu;
    private ImageView mIvPayBank;
    private LinearLayout mLlPayBank;
    private Button mBtnPaySure;
    //0 微信 1 支付吧 2 银行
    private int mType=0;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_withdraw;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_withdraw);
        initView();
        mGmTvRightTitle.setText("提现记录");
        initEvent();

    }

    private void initEvent() {
        mEtWithdraw.addTextChangedListener(new MoneyTextWatcher(mEtWithdraw));
    }
    private void setSelectBG(boolean show, ImageView imageView) {
        imageView.setImageResource(show ? R.mipmap.jft_btn_duipay_selected : R.mipmap.jft_but_paymentnotselected);
    }


    public void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmIvBack.setOnClickListener(this);
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mGmTvRightTitle = (TextView) findViewById(R.id.gm_tv_right_title);
        mEtWithdraw = (EditText) findViewById(R.id.et_withdraw);
        mEtWithdraw.setOnClickListener(this);
        mTvMyMoneyLeft = (TextView) findViewById(R.id.tv_my_money_left);
        mTvMyMoneyLeft.setOnClickListener(this);
        mTvMyMoneyValue = (TextView) findViewById(R.id.tv_my_money_value);
        mTvMyMoneyValue.setOnClickListener(this);
        mIvPayWeixin = (ImageView) findViewById(R.id.iv_pay_weixin);
        mIvPayWeixin.setOnClickListener(this);
        mLlPayWeixin = (LinearLayout) findViewById(R.id.ll_pay_weixin);
        mLlPayWeixin.setOnClickListener(this);
        mIvPayZhifu = (ImageView) findViewById(R.id.iv_pay_zhifu);
        mIvPayZhifu.setOnClickListener(this);
        mLlPayZhifu = (LinearLayout) findViewById(R.id.ll_pay_zhifu);
        mLlPayZhifu.setOnClickListener(this);
        mIvPayBank = (ImageView) findViewById(R.id.iv_pay_bank);
        mIvPayBank.setOnClickListener(this);
        mLlPayBank = (LinearLayout) findViewById(R.id.ll_pay_bank);
        mLlPayBank.setOnClickListener(this);
        mBtnPaySure = (Button) findViewById(R.id.btn_pay_sure);
        mBtnPaySure.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gm_iv_back:
                mResultTo.finishBase(this);
                break;
            case R.id.btn_pay_sure:
                break;
            case R.id.ll_pay_weixin:
                mType=0;
                setSelectBG(true,mIvPayWeixin);
                setSelectBG(false,mIvPayZhifu);
                setSelectBG(false,mIvPayBank);
                break;
            case R.id.ll_pay_zhifu:
                mType=1;
                setSelectBG(false,mIvPayWeixin);
                setSelectBG(true,mIvPayZhifu);
                setSelectBG(false,mIvPayBank);
                break;
            case R.id.ll_pay_bank:
                mType=2;
                setSelectBG(false,mIvPayWeixin);
                setSelectBG(false,mIvPayZhifu);
                setSelectBG(true,mIvPayBank);
                break;

            default:
                break;
        }

    }

}
