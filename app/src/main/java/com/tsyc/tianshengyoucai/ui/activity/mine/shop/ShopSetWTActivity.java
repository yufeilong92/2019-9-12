package com.tsyc.tianshengyoucai.ui.activity.mine.shop;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.listener.event.NormalEvent;
import com.tsyc.tianshengyoucai.model.bean.NormalResultBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.view.pop.ShopSetWTPop;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：van
 * CreateTime：2019/8/13
 * File description： 设置工作时间
 */
public class ShopSetWTActivity extends BaseActivity {
    @BindView(R.id.tv_top_time)
    TextView tvTopTime;
    @BindView(R.id.tv_btm_time)
    TextView tvBtmTime;
    @BindView(R.id.ctl_time)
    ConstraintLayout ctlTime;
    @BindView(R.id.cb_open)
    CheckBox cb_open;

    private OptionsPickerView pvOptions;

    private List<String> whichList = new ArrayList<>(); //上午下午
    private List<String> hourList = new ArrayList<>(); //小时
    private List<String> minList = new ArrayList<>(); //分钟
    private String time = "0:00 至 24:00";

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_set_work_time;
    }

    @Override
    public void initView() {
        mTvTitle.setText("设置工作时间");

        cb_open.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                tvBtmTime.setText("");
                tvTopTime.setText("");
            }
        });
    }

    @OnClick({R.id.btn_commit, R.id.tv_top_time, R.id.ctl_time, R.id.tv_btm_time})
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.btn_commit:

                String endTime = tvBtmTime.getText().toString();
                String starTime = tvTopTime.getText().toString();
                if (cb_open.isChecked()) {
                    updateStoreInfo(time);
                } else {
                    if (TextUtils.isEmpty(endTime) || TextUtils.isEmpty(starTime)) {
                        XToast.normal("请选择起始时间");
                        return;
                    }
                    time = starTime + " 至 " + endTime;
                    updateStoreInfo(time);
                }


                break;
            case R.id.tv_top_time:
                if (cb_open.isChecked()) {
                    XToast.normal("当前选择的是全天，不能选择时间");
                } else {
                    initTime(tvTopTime);
                }
                break;
            case R.id.tv_btm_time:
                if (cb_open.isChecked()) {
                    XToast.normal("当前选择的是全天，不能选择时间");
                } else {
                    initTime(tvBtmTime);
                }
                break;

            case R.id.ctl_time:
                if (cb_open.isChecked()) {
                    cb_open.setChecked(false);
                } else {
                    cb_open.setChecked(true);
                }
                break;
        }
    }

    /**
     * 修改商铺信息
     */
    private void updateStoreInfo(String content) {
        loading(true);
        Map<String, String> params = new HashMap<>();
        params.put("business_hours", content);
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.storeEdit, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("修改店铺信息" + response.body());
                dismiss();
                NormalResultBean resultBean = FastJsonUtil.fromJson(response.body(), NormalResultBean.class);
                if (null == resultBean) {
                    XToast.normal("信息修改失败");
                    return;
                }
                if (!resultBean.getCode().equals(Constant.REQUEST_SUCCESS)) {
                    XToast.normal(String.valueOf(resultBean.getMessage()));
                    return;
                }
                XToast.normal(String.valueOf(resultBean.getMessage()));
                EventBus.getDefault().post(new NormalEvent(time, Constant.UPDATE_TIME));
                finish();

            }

            @Override
            public void failed(Response<String> response) {
                dismiss();
                XLog.e("修改店铺信息" + response.getException().getMessage());

            }
        });
    }

    //工作时间
    private void initTime(TextView textView) {
        hourList.clear();
        minList.clear();

        for (int i = 0; i < 24; i++) {
            hourList.add(String.valueOf(i));
        }
        for (int i = 0; i < 60; i++) {
            minList.add(String.valueOf(i));
        }

        showWorkTime(hourList, whichList, minList, textView);
    }

    private void showWorkTime(List<String> listData, List<String> listData1, List<String> listData2, TextView textView) {
        pvOptions = new OptionsPickerBuilder(this, (options1, options2, options3, v) -> {
            if (listData2.get(options3).length() == 1)
                textView.setText(listData.get(options1) + "：0" + listData2.get(options3));
            else
                textView.setText(listData.get(options1) + "：" + listData2.get(options3));
        }).setLayoutRes(R.layout.layout_company_picker, v -> {
            final TextView tvSubmit = v.findViewById(R.id.tv_finish);
            TextView tvCancel = v.findViewById(R.id.tv_Cancel);


            tvSubmit.setOnClickListener(v1 -> {

                pvOptions.returnData();
                pvOptions.dismiss();
            });

            tvCancel.setOnClickListener(v12 -> pvOptions.dismiss());
        }).setContentTextSize(18)//滚轮文字大小
                .setLineSpacingMultiplier((float) 2.5) //设置item的高度
                .isDialog(false)
                .setOutSideCancelable(false)
                .build();

        pvOptions.setNPicker(listData, listData1, listData2);
        pvOptions.show();
    }

}
