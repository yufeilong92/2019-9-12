package com.tsyc.tianshengyoucai.model.bean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/30
 * File description： 发布的红包bean
 */
public class RelRedBagBean {

    /**
     * code : 200
     * result : [{"voucher_id":3,"voucher_price":10,"description":"新人进店有礼专享红包","remainder":1001},{"voucher_id":5,"voucher_price":10,"description":"新人进店有礼专享红包","remainder":1001},{"voucher_id":6,"voucher_price":10,"description":"新人进店有礼专享红包","remainder":1001},{"voucher_id":7,"voucher_price":10,"description":"新人进店有礼专享红包","remainder":1001},{"voucher_id":8,"voucher_price":10,"description":"新人进店有礼专享红包","remainder":1001},{"voucher_id":9,"voucher_price":10,"description":"新人进店有礼专享红包","remainder":1001},{"voucher_id":10,"voucher_price":10,"description":"新人进店有礼专享红包","remainder":1001},{"voucher_id":11,"voucher_price":10,"description":"新人进店有礼专享红包","remainder":1001},{"voucher_id":12,"voucher_price":10,"description":"新人进店有礼专享红包","remainder":1001},{"voucher_id":47,"voucher_price":10,"description":"新人进店有礼专享红包","remainder":1001},{"voucher_id":48,"voucher_price":10,"description":"新人进店有礼专享红包","remainder":1001},{"voucher_id":49,"voucher_price":50,"description":"新人进店有礼专享红包","remainder":1000},{"voucher_id":50,"voucher_price":50,"description":"新人进店有礼专享红包","remainder":1000},{"voucher_id":51,"voucher_price":50,"description":"新人进店有礼专享红包","remainder":1000},{"voucher_id":52,"voucher_price":50,"description":"新人进店有礼专享红包","remainder":1000},{"voucher_id":53,"voucher_price":50,"description":"新人进店有礼专享红包","remainder":1000},{"voucher_id":54,"voucher_price":50,"description":"新人进店有礼专享红包","remainder":1000},{"voucher_id":55,"voucher_price":50,"description":"新人进店有礼专享红包","remainder":1000},{"voucher_id":56,"voucher_price":50,"description":"新人进店有礼专享红包","remainder":1000},{"voucher_id":57,"voucher_price":50,"description":"新人进店有礼专享红包","remainder":1000},{"voucher_id":58,"voucher_price":50,"description":"新人进店有礼专享红包","remainder":1000}]
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
         * voucher_id : 3
         * voucher_price : 10
         * description : 新人进店有礼专享红包
         * remainder : 1001
         */

        private int voucher_id;
        private float voucher_price;
        private String description;
        private int remainder;

        public int getVoucher_id() {
            return voucher_id;
        }

        public void setVoucher_id(int voucher_id) {
            this.voucher_id = voucher_id;
        }

        public float getVoucher_price() {
            return voucher_price;
        }

        public void setVoucher_price(float voucher_price) {
            this.voucher_price = voucher_price;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getRemainder() {
            return remainder;
        }

        public void setRemainder(int remainder) {
            this.remainder = remainder;
        }
    }
}
