package com.tsyc.tianshengyoucai.model.bean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/5
 * File description：申请提现 规则
 */
public class CashTipBean {


    /**
     * code : 200
     * result : {"unsettled":0,"service_fee":"2","rule":["1.提现收取2%手续费。","2.到账时间：工作日2小时内到账。"]}
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
         * unsettled : 0
         * service_fee : 2
         * rule : ["1.提现收取2%手续费。","2.到账时间：工作日2小时内到账。"]
         */

        private float unsettled;
        private float money;
        private String service_fee;
        private List<String> rule;

        public float getMoney() {
            return money;
        }

        public void setMoney(float money) {
            this.money = money;
        }

        public float getUnsettled() {
            return unsettled;
        }

        public void setUnsettled(float unsettled) {
            this.unsettled = unsettled;
        }

        public String getService_fee() {
            return service_fee;
        }

        public void setService_fee(String service_fee) {
            this.service_fee = service_fee;
        }

        public List<String> getRule() {
            return rule;
        }

        public void setRule(List<String> rule) {
            this.rule = rule;
        }
    }
}
