package com.tsyc.tianshengyoucai.ui.activity.mine.shop;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.listener.event.NormalEvent;
import com.tsyc.tianshengyoucai.listener.event.UnifiedNotifyEvent;
import com.tsyc.tianshengyoucai.manager.SelectCityManager;
import com.tsyc.tianshengyoucai.model.bean.NormalResultBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：van
 * CreateTime：2019/8/13
 * File description：设置店铺地址
 */
public class SetShopAddressActivity extends BaseActivity implements AMapLocationListener {

    @BindView(R.id.tv_address_name)
    TextView tvAddressName;
    @BindView(R.id.ctl_address)
    ConstraintLayout ctlAddress;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.btn_commit)
    Button btnCommit;

    @BindView(R.id.line1)
    View line1;


    private SelectCityManager selectCityManager;
    private int provinceOpt;
    private int cityOpt;
    private int areaOpt;
    private String pName;
    private String cName;
    private String aName;

    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption = null;
    private boolean isLocationSuccess = true;
    private double latitude = 0.0;
    private double longitude = 0.0;

    @Override
    protected int provideContentViewId() {
        return R.layout.shop_set_pop;
    }

    @Override
    public void initView() {
        initLocation();
        line1.setVisibility(View.GONE);
        updateAddress();

        etContent.setHint("请输入详细地址");
    }


    //修改地址
    private void updateAddress() {
        mTvTitle.setText("设置详细地址");
        ctlAddress.setVisibility(View.VISIBLE);
        ctlAddress.setOnClickListener(v -> {
            selectAddress(tvAddressName);
        });
        findViewById(R.id.rl_back).setOnClickListener(v -> finish());

        btnCommit.setOnClickListener(v -> {
            updateStoreInfo();
        });

    }

    private void updateStoreInfo() {
        String addressName = tvAddressName.getText().toString();

        if (TextUtils.isEmpty(addressName)) {
            XToast.normal("请选择所在区域");
            return;
        }

        String content = etContent.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            XToast.normal("请输入详细地址");
            return;
        }
        loading(false);
        String detail = addressName + content;
        Map<String, String> params = new HashMap<>();

        params.put("province_name", pName);
        params.put("city_name", cName);
        params.put("area_name", aName);
        params.put("store_address", content);
        params.put("lng", String.valueOf(longitude));
        params.put("lat", String.valueOf(latitude));

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

                EventBus.getDefault().post(new NormalEvent(detail, Constant.UPDATE_ADDRESS));
                finish();
            }

            @Override
            public void failed(Response<String> response) {
                dismiss();

            }
        });
    }


    //选择地址
    private void selectAddress(TextView tvAddressName) {
        if (selectCityManager == null) {
            selectCityManager = SelectCityManager.getInstance(mContext, true);
            selectCityManager.setOnSelectOptionListener((options1, provinceName, options2, cityName, options3, areaName) -> {
                provinceOpt = options1;
                cityOpt = options2;
                areaOpt = options3;
                pName = provinceName;
                cName = cityName;
                aName = areaName;
                XLog.e("" + provinceOpt + " - " + cityOpt + " - " + areaOpt);
                tvAddressName.setText(String.valueOf(provinceName + " " + cityName + " " + areaName));
            });
        }

        selectCityManager.showDialog(provinceOpt, cityOpt, areaOpt);
    }

    private void initLocation() {
        mlocationClient = new AMapLocationClient(this);
        mLocationOption = new AMapLocationClientOption();
        mlocationClient.setLocationListener(this);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setInterval(2000);
        mlocationClient.setLocationOption(mLocationOption);
        mlocationClient.startLocation();

    }


    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                if (isLocationSuccess) {
                    //获取纬度
                    latitude = amapLocation.getLatitude();
                    //获取经度
                    longitude = amapLocation.getLongitude();
                    amapLocation.getAccuracy();//获取精度信息
                    isLocationSuccess = false;

                    XLog.e(""+latitude+" =   "+longitude);
                }
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                XLog.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }

}
