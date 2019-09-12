package com.tsyc.tianshengyoucai.ui.activity.mine.shop;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.youth.xframe.utils.XDateUtils;
import com.youth.xframe.utils.XKeyboardUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：van
 * CreateTime：2019/7/30
 * File description： 发布优惠券的 发布界面
 */
public class ReleaseCoupon_Activity extends BaseActivity {

    @BindView(R.id.et_coupon_money)
    EditText etCouponMoney;
    @BindView(R.id.et_coupon_size)
    EditText etCouponSize;
    @BindView(R.id.et_coupon_lib)
    EditText etCouponLib;
    @BindView(R.id.et_coupon_limit)
    EditText etCouponLimit;
    @BindView(R.id.et_start_time)
    TextView etStartTime;
    @BindView(R.id.et_end_time)
    TextView etEndTime;
    private String couponId;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_release_coupon_;
    }

    @Override
    public void initView() {
        mTvTitle.setText(getString(R.string.text_release_coupon_));

        Bundle extra = getIntent().getBundleExtra("extra");
        if (null != extra)
            couponId = extra.getString("coupon_id");
        etStartTime.setOnClickListener(this);
        etEndTime.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_start_time:
                XKeyboardUtils.closeKeyboard(this);
                showStartPicker(1);
                break;
            case R.id.et_end_time:
                XKeyboardUtils.closeKeyboard(this);
                showStartPicker(2);
                break;
        }
    }

    private void showStartPicker(int type) {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2011, 1, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2069, 11, 28);
        TimePickerView pvTime = new TimePickerBuilder(mContext, (date, v) -> {
            String dateAllStr = XDateUtils.date2String(date);
            if (type == 1)
                etStartTime.setText(dateAllStr);
            else
                etEndTime.setText(dateAllStr);
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setType(new boolean[]{true, true, true, true, true, false})
                .build();
        pvTime.show();
    }

    //relCoupon
    @OnClick(R.id.btn_commit)
    public void onViewClick() {

        String couponMoney = etCouponMoney.getText().toString().trim();
        String couponSize = etCouponSize.getText().toString().trim();
        String couponLib = etCouponLib.getText().toString().trim();
        String couponLimit = etCouponLimit.getText().toString().trim();
        String startTime = etStartTime.getText().toString().trim();
        String endTime = etEndTime.getText().toString().trim();


        if (TextUtils.isEmpty(couponMoney)) {
            XToast.normal("请输入优惠券金额");
            return;
        }
        if (TextUtils.isEmpty(couponSize)) {
            XToast.normal("请输入用券最低订单金额");
            return;
        }

        if (Double.valueOf(couponMoney)>Double.valueOf(couponSize)){
            XToast.normal("优惠券金额不能大于实付金额");
            return;
        }

        if (TextUtils.isEmpty(couponLib)) {
            XToast.normal("请输入优惠券总数");
            return;
        }
        if (TextUtils.isEmpty(couponLimit)) {
            XToast.normal("请输入每人限领数量");
            return;
        }
        if (TextUtils.isEmpty(startTime)) {
            XToast.normal("请输入优惠券时间用时间");
            return;
        }
        if (TextUtils.isEmpty(endTime)) {
            XToast.normal("请输入优惠券时间用时间");
            return;
        }

        loading(true);
        Map<String, String> params = new HashMap<>();

        params.put("voucher_limit", couponSize); // 最低使用金额
        params.put("voucher_price", couponMoney); // 优惠券金额
        params.put("voucher_total", couponLib);//可被领的总数
        params.put("place_limit", couponLimit);// 每人可领取的优惠券张数
        params.put("start_date", startTime);
        params.put("end_date", endTime);
        if (!TextUtils.isEmpty(couponId))
            params.put("voucher_id", couponId); // 修改 传入

        BaseRequestUtils.postRequestWithHeader(this, UrlManager.relCoupon, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("添加优惠券成功" + response.body());
                dismiss();
                NormalBean normalBean = FastJsonUtil.fromJson(response.body(), NormalBean.class);
                if (null == normalBean) {
                    XToast.normal("发布失败");
                    return;
                }
                if (!normalBean.getCode().equals("200")) {
                    XToast.normal("发布失败");
                    return;
                }

                if (TextUtils.isEmpty(couponId)) {
                    XToast.normal("发布成功");
                } else {
                    XToast.normal("修改成功");
                }
                finish();
            }

            @Override
            public void failed(Response<String> response) {
                XLog.e("添加优惠券失败" + "");
                dismiss();
                XToast.normal("发布失败");
            }
        });
    }
}
