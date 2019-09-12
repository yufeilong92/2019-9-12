package com.tsyc.tianshengyoucai.model.bean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/20
 * File description：
 */
public class ShopNewsBean {

    /**
     * code : 200
     * result : [{"ac_id":9,"ac_name":"新闻"},{"ac_id":10,"ac_name":"正能量"},{"ac_id":11,"ac_name":"每日问候"},{"ac_id":12,"ac_name":"投资理财"},{"ac_id":13,"ac_name":"更多分类"}]
     * message :
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
         * ac_id : 9
         * ac_name : 新闻
         */

        private int ac_id;
        private String ac_name;

        public int getAc_id() {
            return ac_id;
        }

        public void setAc_id(int ac_id) {
            this.ac_id = ac_id;
        }

        public String getAc_name() {
            return ac_name;
        }

        public void setAc_name(String ac_name) {
            this.ac_name = ac_name;
        }
    }
}
