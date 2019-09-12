package com.tsyc.tianshengyoucai.ui.activity.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.model.bean.MapBagBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.NumberUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * author：van
 * CreateTime：2019/8/12
 * File description： 地图红包
 */
public class MapRedBagActivity extends BaseActivity implements LocationSource, AMapLocationListener {

    private final int REQUEST_BAG_DATA = 2000;
    @BindView(R.id.aMapView)
    MapView aMapView;
    private boolean isLocationSuccess = true;
    private AMap aMap;
    private final int STROKE_COLOR = Color.argb(180, 3, 145, 255);
    private final int FILL_COLOR = Color.argb(10, 0, 0, 180);
    private LocationSource.OnLocationChangedListener mListener;
    private AMapLocationClient mLocationClient;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case REQUEST_BAG_DATA:
                    requestBagData();
                    break;
            }
        }
    };
    private double latitude = 0.00;
    private double longitude = 0.00;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_map_red_bag;
    }

    @Override
    public void initView() {
        mTvTitle.setText("地图红包");

        initMap();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        aMapView.onCreate(savedInstanceState);

    }

    // 请求红包数据
    private void requestBagData() {
        String lng = String.valueOf(longitude);
        String lat = String.valueOf(latitude);
        if (lng.equals("0.00") || lat.equals("0.00")) {
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("lng", lng);
        params.put("lat", lat);
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.red_envelope, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("地图红包" + response.body());
                MapBagBean mapBagBean = FastJsonUtil.fromJson(response.body(), MapBagBean.class);
                if (null == mapBagBean) {
                    XToast.normal("红包数据获取失败");
                    return;
                }

                if (!mapBagBean.getCode().equals(Constant.REQUEST_SUCCESS) || mapBagBean.getResult() == null) {
                    XToast.normal(mapBagBean.getMessage());
                    return;
                }
                List<MapBagBean.ResultBean> bagBeanResult = mapBagBean.getResult();

                int size = bagBeanResult.size();
                for (int i = 0; i < size; i++) {
                    MapBagBean.ResultBean resultBean = bagBeanResult.get(i);
                    double bagLat = resultBean.getLat();
                    double bagLng = resultBean.getLng();
                    int storeId = resultBean.getStore_id();
                    LatLng latLng = new LatLng(bagLat, bagLng);
                    MarkerOptions markerOptions = new MarkerOptions().icon(BitmapDescriptorFactory
                            .fromView(getBitmapView(mContext, resultBean)));
                    markerOptions.position(latLng);
                    markerOptions.visible(true);
                    final Marker marker = aMap.addMarker(markerOptions);
                    marker.setObject(storeId); // 传入数据
                    marker.showInfoWindow();
                }

                aMap.setOnMarkerClickListener(marker1 -> {
                    int storeId = (int) marker1.getObject();
                    Bundle bundle = new Bundle();
                    bundle.putString("store_id", String.valueOf(storeId));
                    openActivity(ShopDetailActivity.class, bundle);

                    return true;
                });
            }

            @Override
            public void failed(Response<String> response) {
                //XLog.e("地图红包" + response.getException().getMessage());

            }
        });
    }

    public static View getBitmapView(Context context, MapBagBean.ResultBean resultBean) {
        int store_id = resultBean.getStore_id();
        LayoutInflater factory = LayoutInflater.from(context);
        View view = factory.inflate(R.layout.item_marker_title, null);
        TextView tv_title = view.findViewById(R.id.tv_name);
        TextView tv_snippet = view.findViewById(R.id.tv_snippet);
        tv_title.setText(resultBean.getStore_name());
//        XLog.e("snippet set " + store_id);
        tv_snippet.setText(String.valueOf(store_id));
        return view;
    }

    //初始化地图
    private void initMap() {
        if (aMap == null) {
            aMap = aMapView.getMap();
        }
        aMap.setLocationSource(this);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        //设置true 表示显示定位层并出发 定位，false 表示
        aMap.setMyLocationEnabled(true);
        aMap.setOnMapTouchListener(motionEvent -> {

        });
        aMap.moveCamera(CameraUpdateFactory.zoomTo(10f));// 设置地图的缩放等级
        MyLocationStyle myLocationStyle = new MyLocationStyle();

        //仅仅是展示地图
        aMap.setMyLocationStyle(myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW));
        setupLocationStyle();
    }

    /**
     * 设置自定义当前位置蓝点
     */
    private void setupLocationStyle() {
        // 自定义系统定位蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        // 自定义定位蓝点图标
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.
                fromResource(R.mipmap.jft_icon_companyaddress));
        // 自定义精度范围的圆形边框颜色
        myLocationStyle.strokeColor(STROKE_COLOR);
        //自定义精度范围的圆形边框宽度
        myLocationStyle.strokeWidth(5);
        // 设置圆形的填充颜色
        myLocationStyle.radiusFillColor(FILL_COLOR);
        // 将自定义的 myLocationStyle 对象添加到地图上
        aMap.setMyLocationStyle(myLocationStyle);
    }

    @Override
    public void activate(LocationSource.OnLocationChangedListener listener) {
        mListener = listener;
        if (mLocationClient == null) {
            mLocationClient = new AMapLocationClient(mContext);
        }
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        //设置定位监听   onLocationChanged
        mLocationClient.setLocationListener(this);

        //设置为高精度定位模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setNeedAddress(true); // 返回位置
        mLocationOption.setLocationCacheEnable(false);//关闭缓存
        mLocationOption.setMockEnable(true); //是否允许模拟位置
        //设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation(); // 开启定位
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
        mLocationClient = null;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                latitude = aMapLocation.getLatitude();
                longitude = aMapLocation.getLongitude();
                LatLng mLatLng = new LatLng(latitude, longitude);
                LatLonPoint myPoint = new LatLonPoint(latitude, longitude);

                String street = aMapLocation.getStreet();
                String description = aMapLocation.getDescription();
                String district = aMapLocation.getDistrict();
                //XLog.e("" + latitude + " =  " + longitude + " = " + street);
                if (isLocationSuccess) {
                    //setMarket(latitude, longitude);
                    String city = aMapLocation.getCity();
                    String address = aMapLocation.getAddress();
                    if (!TextUtils.isEmpty(city)) {
                        if (city.substring(city.length() - 1).equals("市")) {
                            String finalCity = city.substring(0, city.length() - 1);
                        }
                    }
                    mListener.onLocationChanged(aMapLocation); //显示小蓝点
                    mHandler.sendEmptyMessage(REQUEST_BAG_DATA);
                    isLocationSuccess = false;
                }
            } else {
                String errorInfo = aMapLocation.getErrorInfo();
                int errorCode = aMapLocation.getErrorCode();
                XLog.e("定位失败 " + errorInfo + "\n" + errorCode);
                if (isTip) {
                    if (errorCode == 12) {
                        openGPSSettings();
                        isTip = false;
                    }

                }
            }
        } else {
            XToast.normal("定位失败");
        }
    }

    private boolean isTip = true;

    private void openGPSSettings() {
        LocationManager alm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!alm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
            XToast.normal("请开启GPS！");
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent, 0); // 此为设置完成后返回到获取界面
            initMap();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        aMapView.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        aMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();
        aMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        aMapView.onDestroy();
    }


/*    @Override
    public boolean onMarkerClick(Marker marker) {
//        if (aMap != null) {
//            jumpPoint(marker);
//        }

        String id = marker.getId();

        XLog.e(""+id+" --- "+marker.getTitle());
        XToast.normal("点击了Marker");
        return true;
    }*/
}
