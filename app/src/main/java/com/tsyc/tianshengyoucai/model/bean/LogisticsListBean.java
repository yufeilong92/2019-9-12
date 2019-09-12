package com.tsyc.tianshengyoucai.model.bean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/31
 * File description： 物流信息bean
 */
public class LogisticsListBean {

    /**
     * code : 200
     * result : {"express_name":"圆通快递","shipping_code":"804353639937873846","deliver_info":[{"time":"2019-02-16 13:37:03","context":"客户 签收人 :邮件收发章 已签收 感谢使用圆通速递，期待再次为您服务"},{"time":"2019-02-16 08:44:13","context":"【浙江省杭州市余杭区临平荷花塘公司】 派件人 :耿太强 派件中 派件员电话18521105564"},{"time":"2019-02-16 06:44:41","context":"【浙江省杭州市余杭区临平】 已发出 下一站 【浙江省杭州市余杭区临平荷花塘公司】"},{"time":"2019-02-16 06:40:47","context":"【浙江省杭州市余杭区临平公司】 已收入"},{"time":"2019-02-16 02:11:35","context":"【杭州转运中心】 已发出 下一站 【浙江省杭州市余杭区临平荷花塘公司】"},{"time":"2019-02-16 02:10:56","context":"【杭州转运中心】 已收入"},{"time":"2019-02-16 02:00:20","context":"【杭州转运中心】 已收入"},{"time":"2019-02-15 21:02:19","context":"【上海转运中心】 已发出 下一站 【杭州转运中心】"},{"time":"2019-02-15 19:26:32","context":"【上海转运中心】 已打包"},{"time":"2019-02-15 19:22:55","context":"【上海转运中心】 已收入"},{"time":"2019-02-15 19:07:35","context":"【上海市直营市场部公司】 已收件"}]}
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
         * express_name : 圆通快递
         * shipping_code : 804353639937873846
         * deliver_info : [{"time":"2019-02-16 13:37:03","context":"客户 签收人 :邮件收发章 已签收 感谢使用圆通速递，期待再次为您服务"},{"time":"2019-02-16 08:44:13","context":"【浙江省杭州市余杭区临平荷花塘公司】 派件人 :耿太强 派件中 派件员电话18521105564"},{"time":"2019-02-16 06:44:41","context":"【浙江省杭州市余杭区临平】 已发出 下一站 【浙江省杭州市余杭区临平荷花塘公司】"},{"time":"2019-02-16 06:40:47","context":"【浙江省杭州市余杭区临平公司】 已收入"},{"time":"2019-02-16 02:11:35","context":"【杭州转运中心】 已发出 下一站 【浙江省杭州市余杭区临平荷花塘公司】"},{"time":"2019-02-16 02:10:56","context":"【杭州转运中心】 已收入"},{"time":"2019-02-16 02:00:20","context":"【杭州转运中心】 已收入"},{"time":"2019-02-15 21:02:19","context":"【上海转运中心】 已发出 下一站 【杭州转运中心】"},{"time":"2019-02-15 19:26:32","context":"【上海转运中心】 已打包"},{"time":"2019-02-15 19:22:55","context":"【上海转运中心】 已收入"},{"time":"2019-02-15 19:07:35","context":"【上海市直营市场部公司】 已收件"}]
         */

        private String express_name;
        private String shipping_code;
        private List<DeliverInfoBean> deliver_info;

        public String getExpress_name() {
            return express_name;
        }

        public void setExpress_name(String express_name) {
            this.express_name = express_name;
        }

        public String getShipping_code() {
            return shipping_code;
        }

        public void setShipping_code(String shipping_code) {
            this.shipping_code = shipping_code;
        }

        public List<DeliverInfoBean> getDeliver_info() {
            return deliver_info;
        }

        public void setDeliver_info(List<DeliverInfoBean> deliver_info) {
            this.deliver_info = deliver_info;
        }

        public static class DeliverInfoBean {
            /**
             * time : 2019-02-16 13:37:03
             * context : 客户 签收人 :邮件收发章 已签收 感谢使用圆通速递，期待再次为您服务
             */

            private String time;
            private String context;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getContext() {
                return context;
            }

            public void setContext(String context) {
                this.context = context;
            }
        }
    }
}
