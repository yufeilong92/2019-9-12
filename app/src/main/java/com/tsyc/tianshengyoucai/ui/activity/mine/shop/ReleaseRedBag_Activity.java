package com.tsyc.tianshengyoucai.ui.activity.mine.shop;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.lzj.pass.dialog.PayPassDialog;
import com.lzj.pass.dialog.PayPassView;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tsyc.tianshengyoucai.AppContext;
import com.tsyc.tianshengyoucai.Eventbuss.MineInfomEvents;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.listener.MyTextWatcherListener;
import com.tsyc.tianshengyoucai.listener.event.UnifiedNotifyEvent;
import com.tsyc.tianshengyoucai.model.bean.ReleaseBagBean;
import com.tsyc.tianshengyoucai.model.bean.WeChatPayBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.activity.mine.SetPayPwdActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.Arith;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.PayUtils;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.view.MoneyTextWatcher;
import com.tsyc.tianshengyoucai.vo.UserInfomVo;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author：van
 * CreateTime：2019/7/30
 * File description： 发布红包
 */
public class ReleaseRedBag_Activity extends BaseActivity {

    @BindView(R.id.et_total_count)
    EditText etTotalCount;
    @BindView(R.id.et_single_count)
    EditText etSingleCount;
    @BindView(R.id.cb_open)
    CheckBox cbOpen;
    @BindView(R.id.cb_man)
    CheckBox cbMan;
    @BindView(R.id.cb_woman)
    CheckBox cbWoman;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_account_money)
    TextView tvAccountMoney;

    private int payType = 1;
    private CheckBox[] checkBoxes = new CheckBox[3];
    private IWXAPI wxapi;
    private String orderSn;
    private double mPayValue;
    private UserInfomVo mUserInfom;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_release_bag;
    }

    @Override
    public void initView() {
        wxapi = WXAPIFactory.createWXAPI(mContext, Constant.WX_APP_ID, false);


    }

    @Override
    public void initData() {
        etSingleCount.addTextChangedListener(new MoneyTextWatcher(etSingleCount));
        initEvent();
        initRequest();
        initView();

    }

    private void initEvent() {
        checkBoxes[0] = (CheckBox) findViewById(R.id.cb_wx_pay);
        checkBoxes[1] = (CheckBox) findViewById(R.id.cb_ali_pay);
        checkBoxes[2] = (CheckBox) findViewById(R.id.cb_account_pay);
    }

    private void initRequest() {
        requestInfom(new InfomInterface() {
            @Override
            public void infomSuccess(UserInfomVo vo) {
                bindViewData(vo);
                mUserInfom = vo;
            }

            @Override
            public void infomError() {

            }
        });
    }

    private void bindViewData(UserInfomVo vo) {
        double my_balance = vo.getResult().getMy_balance();
        tvAccountMoney.setText("当前余额: ￥" + my_balance);
        mTvTitle.setText(getString(R.string.text_release_bag));
    }

    @Override
    public void initListener() {

        etTotalCount.addTextChangedListener(new MyTextWatcherListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString())) {
                    String number = StringUtil.getObjectToStr(etSingleCount);
                    String counts = StringUtil.getObjectToStr(etTotalCount);
                    if (StringUtil.isEmpty(number)) return;
                    if (StringUtil.isEmpty(counts)) return;
                    double values = Double.parseDouble(number);
                    double numbers = Double.parseDouble(counts);
                    mPayValue = Arith.mul(values, numbers);
                    String s1 = Arith.keepTwoDecimalPlaces(mPayValue);
                    tvMoney.setText("￥" + s1);
                }
            }
        });
        etSingleCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString())) {
                    String number = StringUtil.getObjectToStr(etSingleCount);
                    String counts = StringUtil.getObjectToStr(etTotalCount);
                    if (StringUtil.isEmpty(number)) return;
                    if (StringUtil.isEmpty(counts)) return;
                    double values = Double.parseDouble(number);
                    double numbers = Double.parseDouble(counts);
                    mPayValue = Arith.mul(values, numbers);
                    String s1 = Arith.keepTwoDecimalPlaces(mPayValue);
                    tvMoney.setText("￥" + s1);
                }
            }
        });


        cbMan.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                cbWoman.setChecked(false);
            }else {
                cbMan.setChecked(false);
            }
        });

        cbWoman.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                cbMan.setChecked(false);
            }else {
                cbWoman.setChecked(false);
            }
        });

    }

    private void payType(boolean isChecked, int checkPos) {
        if (isChecked) {
            for (int i = 0; i < checkBoxes.length; i++) {
                if (i == checkPos) {
                    checkBoxes[i].setChecked(true);
                } else {
                    checkBoxes[i].setChecked(false);
                }
            }
        }
    }

    @OnClick({R.id.ctl_wx_pay, R.id.ctl_ali_pay, R.id.ctl_account_pay, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ctl_wx_pay:
                payType(true, 0);
                payType = 1;
                break;
            case R.id.ctl_ali_pay:
                payType(true, 1);
                payType = 2;
                break;
            case R.id.ctl_account_pay:


                int is_paypwd = mUserInfom.getResult().getIs_paypwd();
                if (is_paypwd == 0) {
                    XToast.normal("请先设置支付密码");
                    openActivity(SetPayPwdActivity.class);
                    return;
                }
                payType = 3;
                payType(true, 2);
                break;
            case R.id.btn_commit:

                releaseRedBag();
                break;
        }
    }


    private void doPay(String pass) {

        Map<String, String> params = new HashMap<>();
        params.put("order_sn", orderSn);
        params.put("pay_type", String.valueOf(payType));
        if (payType == 3) {
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
                if (payType == 1) {
                    PayUtils.weChatPay(AppContext.getInstance(), content, "release_red_bag");
                } else if (payType == 2) {
                    PayUtils.aliPay(ReleaseRedBag_Activity.this, content);
                } else {
                    XToast.normal("支付成功");
                    openActivity(PayResultActivity.class);
                    EventBus.getDefault().postSticky(new MineInfomEvents(""));
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

    private void accountMoneyPay() {
        final PayPassDialog dialog = new PayPassDialog(this);
        dialog.getPayViewPass()
                .setPayClickListener(new PayPassView.OnPayClickListener() {
                    @Override
                    public void onPassFinish(String passContent) {
                        //6位输入完成回调
                        doPay(passContent);
                        dialog.dismiss();
                    }

                    @Override
                    public void onPayClose() {
                        dialog.dismiss();
                        //关闭回调
                    }

                    @Override
                    public void onPayForget() {
                        dialog.dismiss();
                        openActivity(SetPayPwdActivity.class);
                    }
                });
    }


    private void releaseRedBag() {
        if (payType == 3) {
            if (mUserInfom == null) {
                T.showToast(mContext, "获取用户信息失败");
                return;
            }
            double my_balance = mUserInfom.getResult().getMy_balance();
            if (my_balance == 0) {
                T.showToast(mContext, "余额为0,无法支付");
                return;
            }
            if (my_balance < mPayValue) {
                T.showToast(mContext, "余额不足,无法支付");
                return;
            }
        }

        String totalCount = etTotalCount.getText().toString().trim();
        String singleCount = etSingleCount.getText().toString().trim();
        if (TextUtils.isEmpty(totalCount)) {
            XToast.normal(getString(R.string.hint_bag_total_count));
            return;
        }
        if (TextUtils.isEmpty(singleCount)) {
            XToast.normal(getString(R.string.hint_single_bag_count));
            return;
        }
        Map<String, String> params = new HashMap<>();

        params.put("number", totalCount);
        params.put("money", singleCount);

        if (cbMan.isChecked()) {
            params.put("sex", "男");
        } else if (cbWoman.isChecked()){
            params.put("sex", "女");
        }else {
            params.put("sex", "");
        }

        if (cbOpen.isChecked()) {
            params.put("is_push", "1");
        } else {
            params.put("is_push", "0");
        }

        params.put("pay_type", String.valueOf(payType));
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.relRedBag, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("发布红包：" + response.body());

                ReleaseBagBean releaseBagBean = FastJsonUtil.fromJson(response.body(), ReleaseBagBean.class);
                if (null == releaseBagBean) {
                    XToast.normal("发布失败");
                    return;
                }

                if (!releaseBagBean.getCode().equals("200") || releaseBagBean.getResult() == null) {
                    XToast.normal("发布失败");
                    return;
                }
                orderSn = releaseBagBean.getResult().getOrder_sn();
                if (payType == 1) {
                    if (!wxapi.isWXAppInstalled()) {
                        XToast.normal("请先安装微信");
                        return;
                    }
                }
                if (payType == 3) {

                    accountMoneyPay();
                } else {
                    doPay("");
                }
                // XToast.normal("发布成功");

            }

            @Override
            public void failed(Response<String> response) {
                XToast.normal("发布失败");
                XLog.e("红包发布失败： " + "");
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void paySuccessEvent(UnifiedNotifyEvent events) {
        int eventCode = events.getEventCode();
        XLog.e("微信支付回调   " + eventCode);
        if (eventCode == Constant.ALI_PAY_SUCCESS_EVENT) {
            openActivity(PayResultActivity.class);
        } else if (eventCode == Constant.WX_PAY_SUCCESS_EVENT) {
            openActivity(PayResultActivity.class);
        }
        finish();
    }

}
