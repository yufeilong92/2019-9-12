package com.tsyc.tianshengyoucai.model.bean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/30
 * File description： 发布的优惠券bean
 */
public class RelCouponBean {

    /**
     * code : 200
     * result : [{"voucher_id":4,"voucher_money":10,"voucher_limit":100,"start_date":"2019-06-04","end_date":"2019-06-09","remainder":10},{"voucher_id":13,"voucher_money":10,"voucher_limit":100,"start_date":"2019-06-04","end_date":"2019-06-09","remainder":10},{"voucher_id":14,"voucher_money":10,"voucher_limit":100,"start_date":"2019-06-04","end_date":"2019-06-09","remainder":10},{"voucher_id":15,"voucher_money":10,"voucher_limit":100,"start_date":"2019-06-04","end_date":"2019-06-09","remainder":10},{"voucher_id":16,"voucher_money":10,"voucher_limit":100,"start_date":"2019-06-04","end_date":"2019-06-09","remainder":10},{"voucher_id":17,"voucher_money":10,"voucher_limit":100,"start_date":"2019-06-04","end_date":"2019-11-09","remainder":8},{"voucher_id":29,"voucher_money":10,"voucher_limit":100,"start_date":"2019-06-04","end_date":"2019-06-09","remainder":10},{"voucher_id":46,"voucher_money":10,"voucher_limit":100,"start_date":"2019-06-04","end_date":"2019-06-09","remainder":10},{"voucher_id":59,"voucher_money":10,"voucher_limit":100,"start_date":"2019-06-04","end_date":"2019-06-09","remainder":10},{"voucher_id":60,"voucher_money":100,"voucher_limit":50,"start_date":"2016-06-08","end_date":"2019-06-08","remainder":10000},{"voucher_id":61,"voucher_money":100,"voucher_limit":50,"start_date":"2016-06-08","end_date":"2019-06-08","remainder":10000}]
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
         * voucher_id : 4
         * voucher_money : 10
         * voucher_limit : 100
         * start_date : 2019-06-04
         * end_date : 2019-06-09
         * remainder : 10
         */

        private int voucher_id;
        private float voucher_money;
        private int voucher_limit;
        private String start_date;
        private String end_date;
        private int remainder;

        public int getVoucher_id() {
            return voucher_id;
        }

        public void setVoucher_id(int voucher_id) {
            this.voucher_id = voucher_id;
        }

        public float getVoucher_money() {
            return voucher_money;
        }

        public void setVoucher_money(float voucher_money) {
            this.voucher_money = voucher_money;
        }

        public int getVoucher_limit() {
            return voucher_limit;
        }

        public void setVoucher_limit(int voucher_limit) {
            this.voucher_limit = voucher_limit;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

        public int getRemainder() {
            return remainder;
        }

        public void setRemainder(int remainder) {
            this.remainder = remainder;
        }
    }
}
