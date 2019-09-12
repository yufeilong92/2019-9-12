package com.tsyc.tianshengyoucai.model.bean;

/**
 * author：van
 * CreateTime：2019/7/27
 * File description：
 */
public class AgreementBean {


    /**
     * code : 200
     * result : {"agree":"<p style=\"text-align: center;\"><strong>用户注册协议<\/strong><\/p><p><span style=\"font-size: 14px;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议<\/span><strong><br/><\/strong><\/p><p><span style=\"font-size: 14px;\"><span style=\"font-size: 14px;\"><span style=\"font-size: 14px;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<\/span><\/span><\/span>用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议<span style=\"font-size: 14px;\"><span style=\"font-size: 14px;\"><span style=\"font-size: 14px;\"><span style=\"font-size: 14px;\"><\/span><\/span><\/span><\/span><\/p><p>用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议用户注册协议<\/p><p><span style=\"font-size: 14px;\"><span style=\"font-size: 14px;\"><span style=\"font-size: 14px;\"><span style=\"font-size: 14px;\"><br/><\/span><\/span><\/span><\/span><\/p>"}
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
         * agreement :
         */

        private String agree;
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getAgree() {
            return agree;
        }

        public void setAgree(String agree) {
            this.agree = agree;
        }
    }
}
