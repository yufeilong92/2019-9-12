package com.tsyc.tianshengyoucai.model.bean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/19
 * File description： 余额佣金提现bean
 */
public class UserBalanceRecordBean {

    /**
     * code : 200
     * message :
     * result : [{"pdc_account_type":2,"pdc_account_type_name":"支付宝","pdc_add_time":"2019-08-19 12:51:53","pdc_amount":"100.00","pdc_payment_state":0,"pdc_sn":530619534313357000,"pdc_status_text":"待审核"},{"pdc_account_type":2,"pdc_account_type_name":"支付宝","pdc_add_time":"2019-08-19 12:54:24","pdc_amount":"100.00","pdc_payment_state":0,"pdc_sn":940619534464524000,"pdc_status_text":"待审核"},{"pdc_account_type":2,"pdc_account_type_name":"支付宝","pdc_add_time":"2019-08-19 13:53:12","pdc_amount":"100.00","pdc_payment_state":0,"pdc_sn":440619537992325000,"pdc_status_text":"待审核"},{"pdc_account_type":2,"pdc_account_type_name":"支付宝","pdc_add_time":"2019-08-19 13:53:16","pdc_amount":"100.00","pdc_payment_state":0,"pdc_sn":630619537996217000,"pdc_status_text":"待审核"},{"pdc_account_type":2,"pdc_account_type_name":"支付宝","pdc_add_time":"2019-08-19 14:00:57","pdc_amount":"100.00","pdc_payment_state":0,"pdc_sn":670619538457721000,"pdc_status_text":"待审核"},{"pdc_account_type":2,"pdc_account_type_name":"支付宝","pdc_add_time":"2019-08-19 14:02:20","pdc_amount":"100.00","pdc_payment_state":0,"pdc_sn":510619538540481000,"pdc_status_text":"待审核"},{"pdc_account_type":2,"pdc_account_type_name":"支付宝","pdc_add_time":"2019-08-19 14:18:15","pdc_amount":"100.00","pdc_payment_state":0,"pdc_sn":600619539495153000,"pdc_status_text":"待审核"},{"pdc_account_type":2,"pdc_account_type_name":"支付宝","pdc_add_time":"2019-08-19 15:35:23","pdc_amount":"100.00","pdc_payment_state":0,"pdc_sn":620619544123542000,"pdc_status_text":"待审核"},{"pdc_account_type":2,"pdc_account_type_name":"支付宝","pdc_add_time":"2019-08-19 16:12:36","pdc_amount":"100.00","pdc_payment_state":0,"pdc_sn":630619546356521000,"pdc_status_text":"待审核"},{"pdc_account_type":2,"pdc_account_type_name":"支付宝","pdc_add_time":"2019-08-19 16:26:28","pdc_amount":"100.00","pdc_payment_state":0,"pdc_sn":460619547188429000,"pdc_status_text":"待审核"}]
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
         * pdc_account_type : 2
         * pdc_account_type_name : 支付宝
         * pdc_add_time : 2019-08-19 12:51:53
         * pdc_amount : 100.00
         * pdc_payment_state : 0
         * pdc_sn : 530619534313357000
         * pdc_status_text : 待审核
         */

        private int pdc_account_type;
        private String pdc_account_type_name;
        private String pdc_add_time;
        private String pdc_amount;
        private int pdc_payment_state;
        private long pdc_sn;
        private String pdc_status_text;

        public int getPdc_account_type() {
            return pdc_account_type;
        }

        public void setPdc_account_type(int pdc_account_type) {
            this.pdc_account_type = pdc_account_type;
        }

        public String getPdc_account_type_name() {
            return pdc_account_type_name;
        }

        public void setPdc_account_type_name(String pdc_account_type_name) {
            this.pdc_account_type_name = pdc_account_type_name;
        }

        public String getPdc_add_time() {
            return pdc_add_time;
        }

        public void setPdc_add_time(String pdc_add_time) {
            this.pdc_add_time = pdc_add_time;
        }

        public String getPdc_amount() {
            return pdc_amount;
        }

        public void setPdc_amount(String pdc_amount) {
            this.pdc_amount = pdc_amount;
        }

        public int getPdc_payment_state() {
            return pdc_payment_state;
        }

        public void setPdc_payment_state(int pdc_payment_state) {
            this.pdc_payment_state = pdc_payment_state;
        }

        public long getPdc_sn() {
            return pdc_sn;
        }

        public void setPdc_sn(long pdc_sn) {
            this.pdc_sn = pdc_sn;
        }

        public String getPdc_status_text() {
            return pdc_status_text;
        }

        public void setPdc_status_text(String pdc_status_text) {
            this.pdc_status_text = pdc_status_text;
        }
    }
}
