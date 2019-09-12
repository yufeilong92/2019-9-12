package com.tsyc.tianshengyoucai.model.bean;

/**
 * author：van
 * CreateTime：2019/8/16
 * File description： 商家资质
 */
public class StoreCerticationBean {

    /**
     * code : 200
     * result : {"business_license":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019070311101842327.jpg"}
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
         * business_license : http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019070311101842327.jpg
         */

        private String business_license;

        public String getBusiness_license() {
            return business_license;
        }

        public void setBusiness_license(String business_license) {
            this.business_license = business_license;
        }
    }
}
