package com.tsyc.tianshengyoucai.ui.activity.mine;

import android.content.SharedPreferences;
import android.graphics.Paint;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.AppContext;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.listener.PayBorderListener;
import com.tsyc.tianshengyoucai.listener.event.UnifiedNotifyEvent;
import com.tsyc.tianshengyoucai.model.bean.MyBalanceBean;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.model.bean.NormalResultBean;
import com.tsyc.tianshengyoucai.model.bean.WeChatPayBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.requet.RequestSettingHttp;
import com.tsyc.tianshengyoucai.ui.activity.login.ForGetPwdActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.OrderPayActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.PayResultActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.AliPayKeyBorderUtils;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.PayUtils;
import com.tsyc.tianshengyoucai.utils.SaveUserInfomUtilJave;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.utils.Util;
import com.tsyc.tianshengyoucai.vo.PrartnerVo;
import com.tsyc.tianshengyoucai.vo.UserInfomVo;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/9 16:31:
 * @Purpose :成为合伙人
 */
public class PartnerActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private TextView mTvPartenrTitle;
    private TextView mTvPartenrValue;
    private TextView mTvPartnerPresented;
    private TextView mTvPartnerOldvalue;
    private ImageView mIvPayWeixin;
    private LinearLayout mLlPartnerWeixin;
    private ImageView mIvPayZhifu;
    private LinearLayout mLlPartnerZhifu;
    private TextView mTvPartnerValues;
    private ImageView mIvPayYe;
    private LinearLayout mLlPartnerYe;
    private Button mBtnSubmitPay;
    private TextView mTvPartenrName;
    private PrartnerVo mVo;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_partner);
//        initView();
//    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_partner;
    }

    @Override
    public void initData() {
        registerEventBus(this);
        initView();
        initEvent();
        initRequest();
        requestMyBalance();
    }

    private void initRequest() {
        showProgress();
        RequestSettingHttp.getSingleton(this).requestPartner(new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                mVo = GsonUtils.getGson(success, PrartnerVo.class);
                if (mVo.getCode().equals("100")) {
                    T.showToast(mContext, mVo.getMessage());
                    return;
                }
                bindViewData(mVo);
            }

            @Override
            public void error(String error) {
                dismissProgress();
                toastErrorNet();
            }
        });

    }

    private void bindViewData(PrartnerVo vo) {
        PrartnerVo.ResultBean data = vo.getResult();
        mTvPartenrValue.setText(data.getPrice() + "");
        mTvPartnerOldvalue.setText(data.getOld_price() + "");
        mTvPartenrName.setText(data.getName());
        mTvPartnerPresented.setText(data.getDescription());
        mTvPartnerOldvalue.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    //默认微信
    private int mSelectPay = 1;

    private void initEvent() {
        mLlPartnerWeixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectPay = 1;
                setSelectBG(true, mIvPayWeixin);
                setSelectBG(false, mIvPayZhifu);
                setSelectBG(false, mIvPayYe);

            }
        });
        mLlPartnerYe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectPay = 3;
                setSelectBG(false, mIvPayWeixin);
                setSelectBG(true, mIvPayYe);
                setSelectBG(false, mIvPayZhifu);
            }
        });
        mLlPartnerZhifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectPay = 2;
                setSelectBG(false, mIvPayWeixin);
                setSelectBG(false, mIvPayYe);
                setSelectBG(true, mIvPayZhifu);
            }
        });
    }


    private void setSelectBG(boolean show, ImageView imageView) {
        imageView.setImageResource(show ? R.mipmap.jft_btn_duipay_selected : R.mipmap.jft_but_paymentnotselected);
    }

    public void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        setFinish(mContext, mGmIvBack);
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mTvPartenrTitle = (TextView) findViewById(R.id.tv_partenr_title);
        mTvPartenrValue = (TextView) findViewById(R.id.tv_partenr_value);
        mTvPartnerPresented = (TextView) findViewById(R.id.tv_partner_presented);
        mTvPartnerOldvalue = (TextView) findViewById(R.id.tv_partner_oldvalue);
        mIvPayWeixin = (ImageView) findViewById(R.id.iv_pay_weixin);
        mLlPartnerWeixin = (LinearLayout) findViewById(R.id.ll_partner_weixin);
        mIvPayZhifu = (ImageView) findViewById(R.id.iv_pay_zhifu);
        mLlPartnerZhifu = (LinearLayout) findViewById(R.id.ll_partner_zhifu);
        mTvPartnerValues = (TextView) findViewById(R.id.tv_partner_values);
        mIvPayYe = (ImageView) findViewById(R.id.iv_pay_ye);
        mLlPartnerYe = (LinearLayout) findViewById(R.id.ll_partner_ye);
        mBtnSubmitPay = (Button) findViewById(R.id.btn_submit_pay);

        mBtnSubmitPay.setOnClickListener(this);
        mTvPartenrName = (TextView) findViewById(R.id.tv_partenr_name);
        mTvPartenrName.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit_pay:
//                T.showToast(mContext,"功能待完善");
                submit();
                break;
        }
    }

    //我的余额
    private void requestMyBalance() {
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.my_balance, null, new BaseRequestUtils.getRequestCallBack() {

            @Override
            public void success(Response<String> response) {
                XLog.e("my_balance " + response.body());
                MyBalanceBean cashTipBean = FastJsonUtil.fromJson(response.body(), MyBalanceBean.class);

                if (null == cashTipBean) {
                    return;
                }
                if (!cashTipBean.getCode().equals("200") || cashTipBean.getResult() == null) {
                    return;
                }

                MyBalanceBean.ResultBean result = cashTipBean.getResult();
                String service_fee = result.getMy_balance();
                mTvPartnerValues.setText(String.valueOf("￥ "+service_fee + " 元"));
            }

            @Override
            public void failed(Response<String> response) {

            }

        });
    }

    private void submit() {
        if (mVo == null || mVo.getResult() == null) {
            T.showToast(mContext, "信息丢失,请重新打开");
            return;
        }
        if (Util.handleOnDoubleClick()) {
            return;
        }

        PrartnerVo.ResultBean data = mVo.getResult();

        showProgress();
        RequestSettingHttp.getSingleton(this).submitPartnerOrder(String.valueOf(data.getPartner_id()),
                String.valueOf(mSelectPay), new RequestResultCallback() {

                    @Override
                    public void success(String success) {
                        XLog.e("" + success);
                        dismissProgress();
                        NormalResultBean vo = GsonUtils.getGson(success, NormalResultBean.class);
                        if (vo == null) {
                            T.showToast(mContext, vo.getMessage());
                            return;
                        }
                        if (!vo.getCode().equals(Constant.REQUEST_SUCCESS) || vo.getResult() == null) {
                            T.showToast(mContext, vo.getMessage());
                            return;
                        }
                        String order_sn = vo.getResult().getOrder_sn();

                        switch (mSelectPay) {
                            case 1://微信

                                doPay("", order_sn);
                                break;
                            case 2://支付宝
                                PayUtils.aliPay(PartnerActivity.this, order_sn);
                                doPay("", order_sn);
                                break;
                            case 3://金币
                                AliPayKeyBorderUtils instance = AliPayKeyBorderUtils.getInstance();
                                instance.showPayDialog(mContext);
                                instance.setPayListener(new PayBorderListener() {
                                    @Override
                                    public void onFinished(String pwdContent) {
                                        doPay(pwdContent, order_sn);
                                    }

                                    @Override
                                    public void onClosed() {

                                    }

                                    @Override
                                    public void onPwdForget() {
                                        openActivity(ForGetPwdActivity.class);
                                    }
                                });

                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    public void error(String error) {
                        dismissProgress();
                        toastErrorNet();
                    }
                });
    }

    private void doPay(String pass, String order_sn) {

        Map<String, String> params = new HashMap<>();
        params.put("order_sn", order_sn);
        params.put("pay_type", String.valueOf(mSelectPay));
        if (mSelectPay == 3) {
            params.put("member_paypwd", pass);
        }

        loading(true);
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.order_pay, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {

                dismiss();
                XLog.e("支付 " + response.body());
                WeChatPayBean weChatPayBean = FastJsonUtil.fromJson(response.body(), WeChatPayBean.class);
                if (null == weChatPayBean) {
                    XToast.normal("支付失败");
                    return;
                }
                if (!weChatPayBean.getCode().equals(Constant.REQUEST_SUCCESS) || weChatPayBean.getResult() == null) {
                    XToast.normal(weChatPayBean.getMessage());
                    return;
                }
                WeChatPayBean.ResultBean data = weChatPayBean.getResult();
                String content = data.getContent();
                if (mSelectPay == 1) {
                    PayUtils.weChatPay(AppContext.getInstance(), content, "partner");
                } else if (mSelectPay == 2) {
                    PayUtils.aliPay(PartnerActivity.this, content);
                } else {
                    UserInfomVo infoVo = SaveUserInfomUtilJave.getInstance().getUserInfom();
                    UserInfomVo.ResultBean result = infoVo.getResult();
                    result.setGrade_status(1);
                    infoVo.setResult(result);
                    SaveUserInfomUtilJave.getInstance().putUserInfom(infoVo);
                    openActivity(PayResultActivity.class);
                    finish();
                }

            }

            @Override
            public void failed(Response<String> response) {
                XLog.e("支付 " + response.getException().getMessage());
                XToast.normal(getString(R.string.service_error));
                dismiss();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void paySuccessEvent(UnifiedNotifyEvent events) {
        int eventCode = events.getEventCode();
        XLog.e("微信支付回调   " + eventCode);
        if (eventCode == Constant.ALI_PAY_SUCCESS_EVENT) {
            openActivity(PayResultActivity.class);
            EventBus.getDefault().post(new UnifiedNotifyEvent(Constant.REFRESH_ORDER_LIST));
        } else if (eventCode == Constant.WX_PAY_SUCCESS_EVENT) {
            openActivity(PayResultActivity.class);
            EventBus.getDefault().post(new UnifiedNotifyEvent(Constant.REFRESH_ORDER_LIST));
        }
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterEventBus(this);
    }
}
