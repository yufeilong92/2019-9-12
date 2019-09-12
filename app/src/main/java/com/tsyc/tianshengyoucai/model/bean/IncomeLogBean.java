package com.tsyc.tianshengyoucai.model.bean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/31
 * File description：
 */
public class IncomeLogBean {

    /**
     * code : 200
     * result : {"total":18,"per_page":15,"current_page":1,"last_page":2,"data":[{"order_sn":"SP19081812281035456","order_amount":"40.00","finnshed_time":"2019-08-18 14:02:58","remark":"SP19081812281035456(商城订单收入)"},{"order_sn":"SP19081719344848531","order_amount":"0.01","finnshed_time":"2019-08-18 11:23:28","remark":"SP19081719344848531(商城订单收入)"},{"order_sn":"SP19081722055259331","order_amount":"20.00","finnshed_time":"2019-08-18 10:15:08","remark":"SP19081722055259331(商城订单收入)"},{"order_sn":"SP19081719342881522","order_amount":"0.01","finnshed_time":"2019-08-18 10:06:55","remark":"SP19081719342881522(商城订单收入)"},{"order_sn":"SP19081515062573779","order_amount":"0.01","finnshed_time":"2019-08-17 19:23:39","remark":"SP19081515062573779(商城订单收入)"},{"order_sn":"SP19081515160519460","order_amount":"0.01","finnshed_time":"2019-08-17 19:23:37","remark":"SP19081515160519460(商城订单收入)"},{"order_sn":"SP19081515182630971","order_amount":"0.01","finnshed_time":"2019-08-17 19:23:36","remark":"SP19081515182630971(商城订单收入)"},{"order_sn":"SP19081515313044875","order_amount":"0.01","finnshed_time":"2019-08-17 19:23:33","remark":"SP19081515313044875(商城订单收入)"},{"order_sn":"SP19081515322880858","order_amount":"0.01","finnshed_time":"2019-08-17 19:23:27","remark":"SP19081515322880858(商城订单收入)"},{"order_sn":"SP19081515334113649","order_amount":"0.01","finnshed_time":"2019-08-17 18:44:16","remark":"SP19081515334113649(商城订单收入)"},{"order_sn":"SP19081316354732106","order_amount":"0.04","finnshed_time":"2019-08-17 18:35:29","remark":"SP19081316354732106(商城订单收入)"},{"order_sn":"SP19081410381159720","order_amount":"0.01","finnshed_time":"2019-08-15 11:25:59","remark":"SP19081410381159720(商城订单收入)"},{"order_sn":"SP19081511202479421","order_amount":"0.01","finnshed_time":"2019-08-15 11:21:23","remark":"SP19081511202479421(商城订单收入)"},{"order_sn":"SP19081400520960427","order_amount":"0.01","finnshed_time":"2019-08-14 10:05:21","remark":"SP19081400520960427(商城订单收入)"},{"order_sn":"SP19081318590129231","order_amount":"0.02","finnshed_time":"2019-08-13 20:27:45","remark":"SP19081318590129231(商城订单收入)"}]}
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
         * total : 18
         * per_page : 15
         * current_page : 1
         * last_page : 2
         * data : [{"order_sn":"SP19081812281035456","order_amount":"40.00","finnshed_time":"2019-08-18 14:02:58","remark":"SP19081812281035456(商城订单收入)"},{"order_sn":"SP19081719344848531","order_amount":"0.01","finnshed_time":"2019-08-18 11:23:28","remark":"SP19081719344848531(商城订单收入)"},{"order_sn":"SP19081722055259331","order_amount":"20.00","finnshed_time":"2019-08-18 10:15:08","remark":"SP19081722055259331(商城订单收入)"},{"order_sn":"SP19081719342881522","order_amount":"0.01","finnshed_time":"2019-08-18 10:06:55","remark":"SP19081719342881522(商城订单收入)"},{"order_sn":"SP19081515062573779","order_amount":"0.01","finnshed_time":"2019-08-17 19:23:39","remark":"SP19081515062573779(商城订单收入)"},{"order_sn":"SP19081515160519460","order_amount":"0.01","finnshed_time":"2019-08-17 19:23:37","remark":"SP19081515160519460(商城订单收入)"},{"order_sn":"SP19081515182630971","order_amount":"0.01","finnshed_time":"2019-08-17 19:23:36","remark":"SP19081515182630971(商城订单收入)"},{"order_sn":"SP19081515313044875","order_amount":"0.01","finnshed_time":"2019-08-17 19:23:33","remark":"SP19081515313044875(商城订单收入)"},{"order_sn":"SP19081515322880858","order_amount":"0.01","finnshed_time":"2019-08-17 19:23:27","remark":"SP19081515322880858(商城订单收入)"},{"order_sn":"SP19081515334113649","order_amount":"0.01","finnshed_time":"2019-08-17 18:44:16","remark":"SP19081515334113649(商城订单收入)"},{"order_sn":"SP19081316354732106","order_amount":"0.04","finnshed_time":"2019-08-17 18:35:29","remark":"SP19081316354732106(商城订单收入)"},{"order_sn":"SP19081410381159720","order_amount":"0.01","finnshed_time":"2019-08-15 11:25:59","remark":"SP19081410381159720(商城订单收入)"},{"order_sn":"SP19081511202479421","order_amount":"0.01","finnshed_time":"2019-08-15 11:21:23","remark":"SP19081511202479421(商城订单收入)"},{"order_sn":"SP19081400520960427","order_amount":"0.01","finnshed_time":"2019-08-14 10:05:21","remark":"SP19081400520960427(商城订单收入)"},{"order_sn":"SP19081318590129231","order_amount":"0.02","finnshed_time":"2019-08-13 20:27:45","remark":"SP19081318590129231(商城订单收入)"}]
         */

        private int total;
        private int per_page;
        private int current_page;
        private int last_page;
        private List<DataBean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * order_sn : SP19081812281035456
             * order_amount : 40.00
             * finnshed_time : 2019-08-18 14:02:58
             * remark : SP19081812281035456(商城订单收入)
             */

            private String order_sn;
            private String order_amount;
            private String finnshed_time;
            private String remark;

            public String getOrder_sn() {
                return order_sn;
            }

            public void setOrder_sn(String order_sn) {
                this.order_sn = order_sn;
            }

            public String getOrder_amount() {
                return order_amount;
            }

            public void setOrder_amount(String order_amount) {
                this.order_amount = order_amount;
            }

            public String getFinnshed_time() {
                return finnshed_time;
            }

            public void setFinnshed_time(String finnshed_time) {
                this.finnshed_time = finnshed_time;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }
        }
    }
}
