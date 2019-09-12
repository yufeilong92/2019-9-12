package com.tsyc.tianshengyoucai.model.bean;

/**
 * author：van
 * CreateTime：2019/8/6
 * File description：
 */
public class DetailStoreBean {

    /**
     * code : 200
     * result : {"store_name":"V MALL旗舰","store_working_time":"9：00 至 20：00","sc_name":"服务类","store_description":"介绍一下店铺啊","door_photo":"http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019081321445273424.png","is_recruit":0}
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

    public static class ResultBean {
        /**
         * store_name : V MALL旗舰
         * store_working_time : 9：00 至 20：00
         * sc_name : 服务类
         * store_description : 介绍一下店铺啊
         * door_photo : http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019081321445273424.png
         * is_recruit : 0
         * store_phone : 0
         */

        private String store_name;
        private String store_working_time;
        private String sc_name;
        private String store_description;
        private String door_photo;
        private String store_phone;
        private int is_recruit;

        public String getStore_phone() {
            return store_phone;
        }

        public void setStore_phone(String store_phone) {
            this.store_phone = store_phone;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getStore_working_time() {
            return store_working_time;
        }

        public void setStore_working_time(String store_working_time) {
            this.store_working_time = store_working_time;
        }

        public String getSc_name() {
            return sc_name;
        }

        public void setSc_name(String sc_name) {
            this.sc_name = sc_name;
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

        public int getIs_recruit() {
            return is_recruit;
        }

        public void setIs_recruit(int is_recruit) {
            this.is_recruit = is_recruit;
        }
    }
}
