package com.tsyc.tianshengyoucai.manager;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.ProvinceCityAreaBean;
import com.tsyc.tianshengyoucai.utils.LocalJsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择城市
 * Created by admin on 2018/6/7.
 */

public class SelectCityManager {
    private static Context mContext;
    private static boolean isShowArea;
    private static SelectCityManager mInstance;
    private static List<ProvinceCityAreaBean.ProvinceBean> provinceList;

    private static List<String> provinceNameList;
    private static List<List<String>> cityNameList;
    private static List<List<List<String>>> areaNameList;

    private OnSelectOptionListener onSelectOptionListener;

    private SelectCityManager() {
        initData();
    }

    public static SelectCityManager getInstance(Context context, boolean isShowAreas) {
        mContext = context;
        isShowArea = isShowAreas;
        if (mInstance == null) {
            mInstance = new SelectCityManager();
        }
        return mInstance;
    }

    public void setOnSelectOptionListener(OnSelectOptionListener onSelectOptionListener) {
        this.onSelectOptionListener = onSelectOptionListener;
    }

    /**
     * 初始化省市区数据
     */
    private void initData() {
        String strProvinceCity = SPManager.getPublicSP().getString(SPManager.PROVINCE_CITY, "");
        if (TextUtils.isEmpty(strProvinceCity)) {
            //本地未存储数据，使用项目中的json文件
            strProvinceCity = LocalJsonUtils.getJsonToString(mContext);
        }
        if (!TextUtils.isEmpty(strProvinceCity)) {
            ProvinceCityAreaBean provinceCityAreaBean = new Gson().fromJson(strProvinceCity, ProvinceCityAreaBean.class);
            provinceList = provinceCityAreaBean.getProvince();
        }
        provinceNameList = new ArrayList<>();
        cityNameList = new ArrayList<>();
        areaNameList = new ArrayList<>();
        for (int i = 0; i < provinceList.size(); i++) {
            String provinceName = provinceList.get(i).getName();
            provinceNameList.add(provinceName);
            //市
            List<String> cityList = new ArrayList<>();
            List<List<String>> area1List = new ArrayList<>();
            for (ProvinceCityAreaBean.ProvinceBean.CityBean cityBean : provinceList.get(i).getCity()) {
                cityList.add(cityBean.getName());
                //区
                area1List.add(cityBean.getArea());
            }
            cityNameList.add(cityList);
            areaNameList.add(area1List);
        }
    }



    public void showDialog(int selectProvinceIndex, int selectCityIndex, int selectAreaIndex) {
        OptionsPickerView optionsPickerView = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String province = provinceNameList.get(options1);
                String city = cityNameList.get(options1).get(options2);
                String area = areaNameList.get(options1).get(options2).get(options3);
                if (isShowArea) {
                    area = areaNameList.get(options1).get(options2).get(options3);
                }
                if (onSelectOptionListener != null) {
                    onSelectOptionListener.OnOptionSelect(options1, province, options2, city, options3, area);
                }
            }
        })
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setCancelColor(R.color.color_7B8391)
                .setTitleText("")//标题
                .setOutSideCancelable(false)
                .build();
        if (isShowArea) {
            optionsPickerView.setPicker(provinceNameList, cityNameList, areaNameList);
        } else {
            optionsPickerView.setPicker(provinceNameList, cityNameList);
        }
        optionsPickerView.setSelectOptions(selectProvinceIndex, selectCityIndex, selectAreaIndex);
        optionsPickerView.show();
    }

    /**
     * @param province 省份
     * @param city     城市
     * @param area     地区
     */
    public void showDialog(String province, String city, String area) {
        OptionsPickerView optionsPickerView = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String province = provinceNameList.get(options1);
                String city = cityNameList.get(options1).get(options2);
                String area = areaNameList.get(options1).get(options2).get(options3);
                if (isShowArea) {
                    area = areaNameList.get(options1).get(options2).get(options3);
                }
                if (onSelectOptionListener != null) {
                    onSelectOptionListener.OnOptionSelect(options1, province, options2, city, options3, area);
                }
            }
        })
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setCancelColor(R.color.color_7B8391)
                .setTitleText("")//标题
                .setOutSideCancelable(false)
                .build();

        int provincePostion = 0;
        int cityPostion = 0;
        int areaPostion = 0;
        if (isShowArea) {
            provincePostion = getProvincePostion(provinceNameList, province);
            cityPostion = getCityPostion(cityNameList, city);
            areaPostion = getAreaPostion(areaNameList, area);
            optionsPickerView.setPicker(provinceNameList, cityNameList, areaNameList);
        } else {
            provincePostion = getProvincePostion(provinceNameList, province);
            cityPostion = getCityPostion(cityNameList, city);
            optionsPickerView.setPicker(provinceNameList, cityNameList);
        }
        optionsPickerView.setSelectOptions(provincePostion, cityPostion, areaPostion);
        optionsPickerView.show();
    }

    private int getProvincePostion(List<String> lsit, String name) {
        for (int i = 0; i < provinceNameList.size(); i++) {
            String s = provinceNameList.get(i);
            if (s.equals(name)) {
                return i;
            }

        }
        return 0;
    }

    private int getCityPostion(List<List<String>> cityNameList, String name) {
        for (int i = 0; i < cityNameList.size(); i++) {
            List<String> lists = cityNameList.get(i);
            for (int l = 0; l < lists.size(); l++) {
                String s = lists.get(l);
                if (s.equals(name)) {
                    return l;
                }
            }

        }
        return 0;
    }

    private int getAreaPostion(List<List<List<String>>> areaNameList, String name) {
        for (int i = 0; i < areaNameList.size(); i++) {
            List<List<String>> lists = areaNameList.get(i);
            for (int l = 0; l < lists.size(); l++) {
                List<String> data = lists.get(l);
                for (int h = 0; h < data.size(); h++) {
                    String s = data.get(h);
                    if (s.equals(name)) {
                        return h;
                    }
                }
            }
        }
        return 0;
    }

    public interface OnSelectOptionListener {
        void OnOptionSelect(int options1, String provinceName, int options2, String cityName, int options3, String areaName);
    }
}
