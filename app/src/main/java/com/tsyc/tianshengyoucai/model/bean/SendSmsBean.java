package com.tsyc.tianshengyoucai.model.bean;

/**
 * author：van
 * CreateTime：2019/7/25
 * File description： 短息bean
 */
public class SendSmsBean {

    /**
     * code : 200
     * result : {"sms_time":10}
     * message : 发送成功！
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
         * sms_time : 10
         */

        private int sms_time;

        public int getSms_time() {
            return sms_time;
        }

        public void setSms_time(int sms_time) {
            this.sms_time = sms_time;
        }
    }
}
