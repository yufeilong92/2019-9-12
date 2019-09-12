package com.tsyc.tianshengyoucai.model.bean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/20
 * File description： 红包优惠券领取bean
 */
public class ReceiveBagCouponBean {

    /**
     * code : 200
     * message :
     * result : [{"avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201908/28_2019080914240573551.jpg","date":"2019-08-19 16:34:10","name":"phone_17778137895"},{"avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019081913513482150.jpg","date":"2019-08-19 21:58:08","name":"p56397363"},{"avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201908/8_2019081317312342844.jpg","date":"2019-08-20 22:09:25","name":"phone_15037142280"}]
     */

    private String code;
    private String message;
    private List<ResultBean> result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * avatar : http://tsyc.jiefutong.net/uploads/home/membertag/201908/28_2019080914240573551.jpg
         * date : 2019-08-19 16:34:10
         * name : phone_17778137895
         */

        private String avatar;
        private String date;
        private String name;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
