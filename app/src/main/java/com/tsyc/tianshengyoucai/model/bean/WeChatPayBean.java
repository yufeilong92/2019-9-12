package com.tsyc.tianshengyoucai.model.bean;

/**
 * author：van
 * CreateTime：2019/8/10
 * File description： 微信支付
 */
public class WeChatPayBean {


    /**
     * code : 200
     * result : {"content":"{\"appid\":\"wx67b8de1c1850510a\",\"partnerid\":\"1529185071\",\"prepayid\":\"wx10154918673997c9e5207c9d1470592600\",\"timestamp\":\"1565423358\",\"noncestr\":\"dHOWQ4rLp7VnfyiM\",\"package\":\"Sign=WXPay\",\"sign\":\"30F2E3595330A627B3C178955027DB31\"}","status":0,"pay_type":1}
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
         * content : {"appid":"wx67b8de1c1850510a","partnerid":"1529185071","prepayid":"wx10154918673997c9e5207c9d1470592600","timestamp":"1565423358","noncestr":"dHOWQ4rLp7VnfyiM","package":"Sign=WXPay","sign":"30F2E3595330A627B3C178955027DB31"}
         * status : 0
         * pay_type : 1
         */

        private String content;
        private int status;
        private int pay_type;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getPay_type() {
            return pay_type;
        }

        public void setPay_type(int pay_type) {
            this.pay_type = pay_type;
        }
    }
}
