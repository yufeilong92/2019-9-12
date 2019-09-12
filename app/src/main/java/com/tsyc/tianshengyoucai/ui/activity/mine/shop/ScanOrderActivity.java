package com.tsyc.tianshengyoucai.ui.activity.mine.shop;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.listener.event.NormalEvent;
import com.tsyc.tianshengyoucai.listener.event.UnifiedNotifyEvent;
import com.tsyc.tianshengyoucai.model.bean.NormalResultBean;
import com.tsyc.tianshengyoucai.model.bean.ScanJsonBean;
import com.tsyc.tianshengyoucai.model.bean.ScanOrderBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.ImageLoader;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.youth.xframe.utils.XRegexUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * author：van
 * CreateTime：2019/8/13
 * File description：核销订单
 */
@RuntimePermissions
public class ScanOrderActivity extends BaseActivity {

    @BindView(R.id.iv_order_img)
    ImageView ivOrderImg;
    @BindView(R.id.tv_order_name)
    TextView tvOrderName;
    @BindView(R.id.tv_order_desc)
    TextView tvOrderDesc;
    @BindView(R.id.tv_order_price)
    TextView tvOrderPrice;
    @BindView(R.id.tv_order_count)
    TextView tvOrderCount;
    @BindView(R.id.tv_pay_type)
    TextView tvPayType;
    @BindView(R.id.tv_post_money)
    TextView tvPostMoney;
    @BindView(R.id.tv_pay_money)
    TextView tvPayMoney;
    @BindView(R.id.tv_buyer_name)
    TextView tvBuyerName;
    @BindView(R.id.tv_buyer_phone)
    TextView tvBuyerPhone;
    @BindView(R.id.tv_yy_time)
    TextView tvYyTime;
    @BindView(R.id.tv_back_type)
    TextView tvBackType;
    @BindView(R.id.tv_back_time)
    TextView tvBackTime;
    @BindView(R.id.tv_back_money)
    TextView tvBackMoney;
    @BindView(R.id.tv_order_sn)
    TextView tvOrderSn;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    private String order_sn;
    private int order_id;
    private String type;
    private String phone;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_scan_order;
    }

    @Override
    public void initView() {
        mTvTitle.setText("核销订单");
        Bundle extra = getIntent().getBundleExtra(Constant.bundleExtra);
        if (null != extra) {
            String code = extra.getString("code");
            type = extra.getString("type");
            XLog.e("核销订单 code "+code);

            requestOrderDetail(code);
        }
    }

    @OnClick({R.id.tv_copy_sn, R.id.btn_commit, R.id.iv_call})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_copy_sn:
                StringUtil.copyText(mContext, order_sn);
                break;

            case R.id.btn_commit:
                configOrder();
                break;
            case R.id.iv_call: // 打电话
                ScanOrderActivityPermissionsDispatcher.onSkipIntentWithPermissionCheck(this);
                break;
        }
    }

    //确认核销
    private void configOrder() {
        Map<String, String> params = new HashMap<>();
        params.put("order_id", String.valueOf(order_id));
        loading(true);
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.doVerify, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e( "扫码核销" + response.body());
                dismiss();
                NormalResultBean resultBean = FastJsonUtil.fromJson(response.body(), NormalResultBean.class);
                if (resultBean == null) {
                    XToast.normal("扫码核销失败");
                    return;
                }

                if (!resultBean.getCode().equals(Constant.REQUEST_SUCCESS)) {
                    XToast.normal(resultBean.getMessage());
                    return;
                }

                XToast.normal(resultBean.getMessage());
                if (type.equals("order_fragment")){
                    EventBus.getDefault().post(new UnifiedNotifyEvent(Constant.SCAN_ORDER));
                }
                finish();

            }

            @Override
            public void failed(Response<String> response) {
                XLog.e("扫码核销" + response.getException().getMessage());
                dismiss();
                XToast.normal(getString(R.string.service_error));

            }
        });
    }

    //请求核销代码
    private void requestOrderDetail(String code) {

        Map<String, String> params = new HashMap<>();
        params.put("verify_code", code);

        BaseRequestUtils.postRequestWithHeader(this, UrlManager.scan_order, params, new BaseRequestUtils.getRequestCallBack() {

            @SuppressLint("SetTextI18n")
            @Override
            public void success(Response<String> response) {
                XLog.e("核销  " + response.body());

                ScanOrderBean scanOrderBean = FastJsonUtil.fromJson(response.body(), ScanOrderBean.class);
                if (null == scanOrderBean) {
                    return;
                }
                if (!scanOrderBean.getCode().equals(Constant.REQUEST_SUCCESS) || scanOrderBean.getResult() == null) {
                    XToast.normal(scanOrderBean.getMessage());
                    return;
                }
                ScanOrderBean.ResultBean result = scanOrderBean.getResult();
                order_sn = result.getOrder_sn();
                order_id = result.getOrder_id();
                String add_time = result.getAdd_time();

                String order_amount = result.getOrder_amount();
                String goods_amount = result.getGoods_amount();
                String payment_name = result.getPayment_name();

                String buyer_name = result.getBuyer_name();
                phone = result.getZiti_phone();
                String ziti_time = result.getZiti_time();

                tvOrderSn.setText("订单编号：" + order_sn);
                tvStartTime.setText("下单时间：" + add_time);

                tvBuyerName.setText(buyer_name);
                tvBuyerPhone.setText(phone);
                tvYyTime.setText(ziti_time);

                tvPayType.setText(payment_name);
                tvPostMoney.setText("￥" + goods_amount);
                tvPayMoney.setText("￥" + order_amount);

                List<ScanOrderBean.ResultBean.OrdergoodsBean> order_goods = result.getOrdergoods();
                if (order_goods != null && order_goods.size() > 0) {
                    String goods_guide = order_goods.get(0).getGoods_guige();
                    String goods_image = order_goods.get(0).getGoods_image();
                    String goods_name = order_goods.get(0).getGoods_name();
                    String goods_price = order_goods.get(0).getGoods_price();
                    int goods_num = order_goods.get(0).getGoods_num();

                    tvOrderName.setText(goods_name);
                    tvOrderDesc.setText(goods_guide);
                    tvOrderPrice.setText("￥" + goods_price);
                    tvOrderCount.setText("x" + goods_num);
                    ImageLoader.loadCenterCrop(mContext, goods_image, ivOrderImg, 20);
                }
            }

            @Override
            public void failed(Response<String> response) {

            }
        });

    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ScanOrderActivityPermissionsDispatcher.onSkipIntentWithPermissionCheck(this);
    }

    @NeedsPermission(Manifest.permission.CALL_PHONE)
    void onSkipIntent() {
        if (!XRegexUtils.checkMobile(phone)) {
            XToast.normal("店铺号码有误，拨号失败");
            return;
        }
        Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));//直接拨打电话

        if (ActivityCompat.checkSelfPermission(ScanOrderActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ScanOrderActivity.this, new String[]{"Manifest.permission.CALL_PHONE"}, 0);
            return;
        }
        startActivity(dialIntent);
    }


    @OnShowRationale(Manifest.permission.CALL_PHONE)
    void onSkipIntentShow(final PermissionRequest request) {
        request.proceed();
    }

    @OnNeverAskAgain(Manifest.permission.CALL_PHONE)
    void onSkipIntentNever() {
        //Toast.makeText(getActivity(), "当前功能需要在设置-应用-千客家-权限中开启打电话权限。", Toast.LENGTH_LONG).show();
        XToast.normal("当前功能需要在设置-应用-" + getString(R.string.app_name) + "-权限中开启打电话权限。");
    }


}
