package com.tsyc.tianshengyoucai.utils;

import android.app.Activity;
import android.graphics.Color;

import java.util.List;

import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.picker.SinglePicker;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/3 10:06
 * @Purpose :
 */
public class SelectKeyUtil {
    public interface SelectClick {
        void selectItem(int postion, String com);
    }

    public static void showSelect(Activity activity, List<String> items, SelectClick selectClick) {
        SinglePicker<String> picker = new SinglePicker<String>(activity, items);
        picker.setCanLoop(false);//不禁用循环
        picker.setTopBackgroundColor(0xFFEEEEEE);
        picker.setTopHeight(50);
        picker.setTopLineColor(0xFF33B5E5);
        picker.setTopLineHeight(1);
        picker.setTitleText("请选择");
        picker.setTitleTextColor(0xFF999999);
        picker.setTitleTextSize(12);
        picker.setCancelTextColor(0xFF33B5E5);
        picker.setCancelTextSize(13);
        picker.setSubmitTextColor(0xFF33B5E5);
        picker.setSubmitTextSize(13);
        picker.setSelectedTextColor(0xFF33B5E5);
        picker.setUnSelectedTextColor(0xFF999999);
        picker.setWheelModeEnable(false);
        LineConfig config = new LineConfig();
        config.setColor(Color.TRANSPARENT);//线颜色
        config.setAlpha(120);//线透明度
//        config.setRatio(1);//线比率
        picker.setLineConfig(config);
        picker.setItemWidth(200);
        picker.setBackgroundColor(0xFFE1E1E1);
        //picker.setSelectedItem(isChinese ? "处女座" : "Virgo");
//        picker.setSelectedIndex(7);
        picker.setOnItemPickListener(new OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int i, String com) {
                if (selectClick != null) {
                    selectClick.selectItem(i, com);
                }
            }
        });
        picker.show();
    }
}
