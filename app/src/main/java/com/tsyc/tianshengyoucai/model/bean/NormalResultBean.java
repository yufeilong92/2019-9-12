package com.tsyc.tianshengyoucai.model.bean;

/**
 * author：van
 * CreateTime：2019/7/30
 * File description： 发布红包
 */
public class NormalResultBean {

    /**
     * code : 200
     * result : {"order_sn":"VO19073014160318739","status":0}
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
         * order_sn : VO19073014160318739
         * status : 0
         */

        private String order_sn;
        private int status;

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
