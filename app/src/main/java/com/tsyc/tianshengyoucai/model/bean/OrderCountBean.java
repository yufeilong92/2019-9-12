package com.tsyc.tianshengyoucai.model.bean;

/**
 * 创 建 者：van
 * 创建日期：2019/8/15.
 * 描    述：订单数量
 * 修改描述：
 * 修改日期：
 */
public class OrderCountBean {


    /**
     * code : 200
     * result : {"unpay":4,"send":12,"receive":4,"refund":1}
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
         * unpay : 4
         * send : 12
         * receive : 4
         * refund : 1
         */

        private int unpay;
        private int send;
        private int receive;
        private int refund;

        public int getUnpay() {
            return unpay;
        }

        public void setUnpay(int unpay) {
            this.unpay = unpay;
        }

        public int getSend() {
            return send;
        }

        public void setSend(int send) {
            this.send = send;
        }

        public int getReceive() {
            return receive;
        }

        public void setReceive(int receive) {
            this.receive = receive;
        }

        public int getRefund() {
            return refund;
        }

        public void setRefund(int refund) {
            this.refund = refund;
        }
    }
}
