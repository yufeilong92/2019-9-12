package com.tsyc.tianshengyoucai.model.bean;

import java.io.Serializable;

/**
 * author：van
 * CreateTime：2019/8/5
 * File description： 店铺申请 bean
 */
public class ShopApplyBean implements Serializable {

    /**
     * code : 200
     * result : {"name":"测试","store_id":19,"idcard":"421111198708065852","store_name":"我的钓鱼","area_info":"北京市 北京市 东城区","lng":"","lat":"","store_zy":"美妆","store_description":"介绍？不存在的","door_photo":"http://tsyc.jiefutong.net/uploads/home/membertag/201908/31_2019080511414283361.png","business_license":"http://tsyc.jiefutong.net/uploads/home/membertag/201908/31_2019080511414258887.jpg","store_address":"共鸣你","store_state":2,"business_hours":"","mobile":"15639736373","is_recruit":1}
     * message :
     */

    private String code;
    private ResultBean result;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class ResultBean implements Serializable{
        /**
         * name : 测试
         * store_id : 19
         * idcard : 421111198708065852
         * store_name : 我的钓鱼
         * area_info : 北京市 北京市 东城区
         * lng :
         * lat :
         * store_zy : 美妆
         * store_description : 介绍？不存在的
         * door_photo : http://tsyc.jiefutong.net/uploads/home/membertag/201908/31_2019080511414283361.png
         * business_license : http://tsyc.jiefutong.net/uploads/home/membertag/201908/31_2019080511414258887.jpg
         * store_address : 共鸣你
         * store_state : 2
         * business_hours :
         * mobile : 15639736373
         * is_recruit : 1
         * store_credit : 1
         */

        private String name;
        private int store_id;
        private String idcard;
        private String store_name;
        private String area_info;
        private String lng;
        private String lat;
        private String store_zy;
        private String store_description;
        private String door_photo;
        private String business_license;
        private String store_address;
        private int store_state;
        private String business_hours;
        private String mobile;
        private int is_recruit;
        private int store_credit;

        public int getStore_credit() {
            return store_credit;
        }

        public void setStore_credit(int store_credit) {
            this.store_credit = store_credit;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getArea_info() {
            return area_info;
        }

        public void setArea_info(String area_info) {
            this.area_info = area_info;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getStore_zy() {
            return store_zy;
        }

        public void setStore_zy(String store_zy) {
            this.store_zy = store_zy;
        }

        public String getStore_description() {
            return store_description;
        }

        public void setStore_description(String store_description) {
            this.store_description = store_description;
        }

        public String getDoor_photo() {
            return door_photo;
        }

        public void setDoor_photo(String door_photo) {
            this.door_photo = door_photo;
        }

        public String getBusiness_license() {
            return business_license;
        }

        public void setBusiness_license(String business_license) {
            this.business_license = business_license;
        }

        public String getStore_address() {
            return store_address;
        }

        public void setStore_address(String store_address) {
            this.store_address = store_address;
        }

        public int getStore_state() {
            return store_state;
        }

        public void setStore_state(int store_state) {
            this.store_state = store_state;
        }

        public String getBusiness_hours() {
            return business_hours;
        }

        public void setBusiness_hours(String business_hours) {
            this.business_hours = business_hours;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getIs_recruit() {
            return is_recruit;
        }

        public void setIs_recruit(int is_recruit) {
            this.is_recruit = is_recruit;
        }
    }
}
