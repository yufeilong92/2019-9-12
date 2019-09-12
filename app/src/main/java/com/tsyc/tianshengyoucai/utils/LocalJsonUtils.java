package com.tsyc.tianshengyoucai.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 读取本地json工具
 */

public class LocalJsonUtils {
    private static final String fileName = "province_city.json";

    /**
     * 读取json文件
     *
     * @param context
     * @return string
     */
    public static String getJsonToString(Context context) {
        StringBuilder builder = new StringBuilder();
        AssetManager assetManager = context.getAssets();
        //使用io读取文件
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }


}
