package com.tsyc.tianshengyoucai.ui.activity.mine.money;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.AppContext;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.bank.PayTypeAdapter;
import com.tsyc.tianshengyoucai.adapter.bank.PayValuesAdapter;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.listener.event.UnifiedNotifyEvent;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.model.bean.NormalResultBean;
import com.tsyc.tianshengyoucai.model.bean.WeChatPayBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.requet.RequestSettingHttp;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.PayResultActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.PayUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.PayTypeVo;
import com.tsyc.tianshengyoucai.vo.PayValuesVo;
import com.tsyc.tianshengyoucai.vo.SelectVo;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/7 18:52:
 * @Purpose :充值界面
 */
public class PayActivity extends BaseActivity implements View.OnClickListener {
    private TextView mGmTvTitle;
    private Button mBtnPaySure;
    private TextView mTvPayAgreement;
    /**
     * 是否微信支付
     */
    private boolean mPay = true;
    private ImageView mGmIvBack;
    private double payCount = -1; // 充值金额
    private int pay_type = -1;  // 2 支付宝 1. 微信
    private RecyclerView mRlvTypeContent;
    private RecyclerView mRlvPayValues;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_pay;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_pay);
//        initView();
//
//    }

    @Override
    public void initData() {
        initView();
        initEvent();
        initRequestValues();
        registerEventBus(this);
    }

    private void initRequestValues() {
        showProgress();
        RequestSettingHttp.getSingleton(this).requestPayValues(new RequestResultCallback() {
            @Override
            public void success(String success) {
                NormalBean vos = GsonUtils.getGson(success, NormalBean.class);
                if (vos.getCode().equals("100")) {
                    T.showToast(mContext, vos.getMessage());
                    return;
                }
                PayValuesVo vo = GsonUtils.getGson(success, PayValuesVo.class);

                initValuesAdapter(vo.getResult());
                initRequest();
            }

            @Override
            public void error(String error) {
                dismissProgress();
                toastErrorNet();
            }
        });

    }

    private void initValuesAdapter(List<String> result) {
        List<SelectVo> selectVos = selectValuesList(result, null);
        PayValuesAdapter adapter = new PayValuesAdapter(mContext, result, selectVos);
        RecyclerUtil.setGridManage(mContext, mRlvPayValues, 2, adapter);
        adapter.setListener(new PayValuesAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, String com, SelectVo selectVo) {
                List<SelectVo> list1 = selectValuesList(result, selectVo);
                payCount = Double.parseDouble(com);
                adapter.refresh(list1);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initRequest() {
        RequestSettingHttp.getSingleton(this).requestPayType("1", new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                NormalBean vos = GsonUtils.getGson(success, NormalBean.class);
                if (vos.getCode().equals("100")) {
                    T.showToast(mContext, vos.getMessage());
                    return;
                }
                PayTypeVo vo = GsonUtils.getGson(success, PayTypeVo.class);
                initAdapter(vo.getResult());
            }

            @Override
            public void error(String error) {
                dismissProgress();
                toastErrorNet();
            }
        });


    }

    private void initAdapter(List<PayTypeVo.ResultBean> result) {
        List<SelectVo> list = selectList(result, null);
        PayTypeAdapter adapter = new PayTypeAdapter(mContext, result, list);
        RecyclerUtil.setGridManage(mContext, mRlvTypeContent, adapter);
        adapter.setListener(new PayTypeAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, PayTypeVo.ResultBean vo, SelectVo selectVo) {
                List<SelectVo> list1 = selectList(result, selectVo);
                pay_type = vo.getPay_type();
                adapter.refresh(list1);
                adapter.notifyDataSetChanged();
            }
        });

    }

    private List<SelectVo> selectValuesList(List<String> result, SelectVo mVo) {
        int size = result.size();
        List<SelectVo> list = new ArrayList<>();
        if (mVo == null)
            for (int i = 0; i < size; i++) {
                String bean = result.get(i);
                SelectVo vo = new SelectVo();
                if (i == 0) {
                    payCount=Double.parseDouble(bean);
                    vo.setSelect(true);
                } else {
                    vo.setSelect(false);
                }
                vo.setName(bean);
                list.add(vo);
            }
        else
            for (int i = 0; i < size; i++) {
                String bean = result.get(i);
                SelectVo vo = new SelectVo();
                vo.setSelect(bean.equals(mVo.getName()));
                vo.setName(bean);
                list.add(vo);
            }

        return list;

    }

    private List<SelectVo> selectList(List<PayTypeVo.ResultBean> result, SelectVo mVo) {
        int size = result.size();
        List<SelectVo> list = new ArrayList<>();
        if (mVo == null)
            for (int i = 0; i < size; i++) {
                PayTypeVo.ResultBean bean = result.get(i);
                SelectVo vo = new SelectVo();
                if (i == 0) {
                    pay_type=bean.getPay_type();
                    vo.setSelect(true);
                } else {
                    vo.setSelect(false);
                }
                vo.setName(bean.getName());
                vo.setId(bean.getPay_type());
                list.add(vo);
            }
        else
            for (int i = 0; i < size; i++) {
                PayTypeVo.ResultBean bean = result.get(i);
                SelectVo vo = new SelectVo();
                vo.setSelect(bean.getName().equals(mVo.getName()));
                vo.setName(bean.getName());
                vo.setId(bean.getPay_type());
                list.add(vo);
            }

        return list;

    }

    private void initEvent() {

    }

    public void initView() {
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mBtnPaySure = (Button) findViewById(R.id.btn_pay_sure);
        mTvPayAgreement = (TextView) findViewById(R.id.tv_pay_agreement);
        mBtnPaySure.setOnClickListener(this);

        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmIvBack.setOnClickListener(this);
        mRlvTypeContent = (RecyclerView) findViewById(R.id.rlv_type_content);
        mRlvTypeContent.setOnClickListener(this);
        mRlvPayValues = (RecyclerView) findViewById(R.id.rlv_pay_values);
        mRlvPayValues.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gm_iv_back:
                mResultTo.finishBase(this);
                break;
            case R.id.btn_pay_sure:
                submit();

                break;


        }
    }

    private void submit() {
        if (payCount == -1) {
            T.showToast(mContext, "请选择支付金额");
            return;
        }

        if (pay_type == -1) {
            T.showToast(mContext, "请选择支付方式");
            return;
        }


        Map<String, String> params = new HashMap<>();
//        params.put("price",String.valueOf(payCount));
        params.put("price", String.valueOf(payCount));
        params.put("pay_type", String.valueOf(pay_type));
        loading(true);
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.rechargeOrder, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                dismiss();
                XLog.e("rechargeOrder " + response.body());
                NormalResultBean resultBean = FastJsonUtil.fromJson(response.body(), NormalResultBean.class);
                if (null == resultBean) {
                    XToast.normal("充值失败");
                    return;
                }

                if (!resultBean.getCode().equals(Constant.REQUEST_SUCCESS) || resultBean.getResult() == null) {
                    XToast.normal(resultBean.getMessage());
                    return;
                }

                String order_sn = resultBean.getResult().getOrder_sn();
                doPay(order_sn);

            }

            @Override
            public void failed(Response<String> response) {
                dismiss();

            }
        });
    }

    private void doPay(String orderSn) {

        Map<String, String> params = new HashMap<>();
        params.put("order_sn", orderSn);
        params.put("pay_type", String.valueOf(pay_type));

        loading(true);
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.order_pay, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                dismiss();
                XLog.e("支付 " + response.body());
                WeChatPayBean weChatPayBean = FastJsonUtil.fromJson(response.body(), WeChatPayBean.class);
                if (null == weChatPayBean) {
                    XToast.normal("获取支付信息失败，请稍候再试");
                    return;
                }
                if (!weChatPayBean.getCode().equals(Constant.REQUEST_SUCCESS) || weChatPayBean.getResult() == null) {
                    XToast.normal(weChatPayBean.getMessage());
                    return;
                }
                WeChatPayBean.ResultBean data = weChatPayBean.getResult();
                String content = data.getContent();
                if (pay_type == 1) {
                    PayUtils.weChatPay(AppContext.getInstance(), content, "recharge");

                } else if (pay_type == 2) {
                    PayUtils.aliPay(PayActivity.this, content);
                }

            }

            @Override
            public void failed(Response<String> response) {
               // XLog.e("支付 " + response.getException().getMessage());
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
        } else if (eventCode == Constant.WX_PAY_SUCCESS_EVENT) {
            openActivity(PayResultActivity.class);
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterEventBus(this);
    }
}

