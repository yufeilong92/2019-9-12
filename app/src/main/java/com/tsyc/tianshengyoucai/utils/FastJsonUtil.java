package com.tsyc.tianshengyoucai.utils;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/5
 * File description： fastJson 简易封装
 */
public class FastJsonUtil {

    public static <T> T fromJson(String jsonString, Class<T> cls) {
        T t = null;
        try {
            t = JSON.parseObject(jsonString, cls);
        } catch (Exception e) {

        }
        return t;
    }

    public static<T> List<T> getArray(String jsonString, Class<T> cls) {
        List<T> list = new ArrayList<>();
        try {
            list = JSON.parseArray(jsonString, cls);
        } catch (Exception e) {
        }
        return list;
    }
}
