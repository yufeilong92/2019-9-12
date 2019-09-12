package com.tsyc.tianshengyoucai.model.bean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/5
 * File description：
 */
public class PayTypeBean {


    /**
     * code : 200
     * result : [{"name":"微信支付","logo":"http://tsyc.jiefutong.net//static/home/images/jft_icon_wechatpayment.png","pay_type":1},{"name":"支付宝支付","logo":"http://tsyc.jiefutong.net//static/home/images/jft_icon_alipaypayment.png","pay_type":2}]
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
         * name : 微信支付
         * logo : http://tsyc.jiefutong.net//static/home/images/jft_icon_wechatpayment.png
         * pay_type : 1
         */

        private String name;
        private String logo;
        private int pay_type;
        private boolean isCheck;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public int getPay_type() {
            return pay_type;
        }

        public void setPay_type(int pay_type) {
            this.pay_type = pay_type;
        }
    }
}
