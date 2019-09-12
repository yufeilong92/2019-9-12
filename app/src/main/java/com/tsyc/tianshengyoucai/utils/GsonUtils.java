package com.tsyc.tianshengyoucai.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class GsonUtils {

    public static <T> List<T> getGsons(String json, Class<T> t) {
        Gson gson1 = new Gson();
        Type type = new TypeToken<List<T>>() {
        }.getType();
        List<T> list = gson1.fromJson(json, type);
        return list;
    }

    public static <T> T getGson(String com, Class<T> t) {
        Gson gson = new Gson();
        return gson.fromJson(com, t);
    }

    public static String toJson(Object t) {
        Gson gson = new Gson();
        return gson.toJson(t);
    }

}
