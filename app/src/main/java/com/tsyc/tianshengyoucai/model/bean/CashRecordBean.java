package com.tsyc.tianshengyoucai.model.bean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/8
 * File description：
 */
public class CashRecordBean {


    /**
     * code : 200
     * result : {"list":{"total":3,"per_page":15,"current_page":1,"last_page":1,"data":[{"id":12,"store_id":4,"settle_sn":"SP201907190931536211","money":"13.00","real_money":"12.74","service_fee":"0.26","orders":"54","pay_type":2,"pay_account":{"alipay_realname":"张三","alipay_account":"516954023@qq.com"},"create_time":"2019-07-19 09:31:53","status":2,"audit_time":"2019-07-19 09:32:07","pay_time":"2019-07-19 09:32:52","transaction_id":"手动打款","update_time":"2019-07-19 09:32:52","status_text":"已完成","pay_type_text":"支付宝"},{"id":11,"store_id":4,"settle_sn":"SP201907190930538298","money":"13.00","real_money":"12.74","service_fee":"0.26","orders":"54","pay_type":3,"pay_account":{"union_realname":"123456","union_account":"123456","union_bank":"123456"},"create_time":"2019-07-19 09:30:53","status":3,"audit_time":"2019-07-19 09:31:21","pay_time":"","transaction_id":null,"update_time":"2019-07-19 09:31:21","status_text":"已拒绝","pay_type_text":"银联"},{"id":10,"store_id":4,"settle_sn":"SP201907181543163483","money":"13.00","real_money":"12.74","service_fee":"0.26","orders":"54","pay_type":3,"pay_account":{"union_realname":"123456","union_account":"123456","union_bank":"123456"},"create_time":"2019-07-18 15:43:16","status":3,"audit_time":"2019-07-19 09:29:30","pay_time":"","transaction_id":null,"update_time":"2019-07-19 09:29:30","status_text":"已拒绝","pay_type_text":"银联"}]}}
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
         * list : {"total":3,"per_page":15,"current_page":1,"last_page":1,"data":[{"id":12,"store_id":4,"settle_sn":"SP201907190931536211","money":"13.00","real_money":"12.74","service_fee":"0.26","orders":"54","pay_type":2,"pay_account":{"alipay_realname":"张三","alipay_account":"516954023@qq.com"},"create_time":"2019-07-19 09:31:53","status":2,"audit_time":"2019-07-19 09:32:07","pay_time":"2019-07-19 09:32:52","transaction_id":"手动打款","update_time":"2019-07-19 09:32:52","status_text":"已完成","pay_type_text":"支付宝"},{"id":11,"store_id":4,"settle_sn":"SP201907190930538298","money":"13.00","real_money":"12.74","service_fee":"0.26","orders":"54","pay_type":3,"pay_account":{"union_realname":"123456","union_account":"123456","union_bank":"123456"},"create_time":"2019-07-19 09:30:53","status":3,"audit_time":"2019-07-19 09:31:21","pay_time":"","transaction_id":null,"update_time":"2019-07-19 09:31:21","status_text":"已拒绝","pay_type_text":"银联"},{"id":10,"store_id":4,"settle_sn":"SP201907181543163483","money":"13.00","real_money":"12.74","service_fee":"0.26","orders":"54","pay_type":3,"pay_account":{"union_realname":"123456","union_account":"123456","union_bank":"123456"},"create_time":"2019-07-18 15:43:16","status":3,"audit_time":"2019-07-19 09:29:30","pay_time":"","transaction_id":null,"update_time":"2019-07-19 09:29:30","status_text":"已拒绝","pay_type_text":"银联"}]}
         */

        private ListBean list;

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * total : 3
             * per_page : 15
             * current_page : 1
             * last_page : 1
             * data : [{"id":12,"store_id":4,"settle_sn":"SP201907190931536211","money":"13.00","real_money":"12.74","service_fee":"0.26","orders":"54","pay_type":2,"pay_account":{"alipay_realname":"张三","alipay_account":"516954023@qq.com"},"create_time":"2019-07-19 09:31:53","status":2,"audit_time":"2019-07-19 09:32:07","pay_time":"2019-07-19 09:32:52","transaction_id":"手动打款","update_time":"2019-07-19 09:32:52","status_text":"已完成","pay_type_text":"支付宝"},{"id":11,"store_id":4,"settle_sn":"SP201907190930538298","money":"13.00","real_money":"12.74","service_fee":"0.26","orders":"54","pay_type":3,"pay_account":{"union_realname":"123456","union_account":"123456","union_bank":"123456"},"create_time":"2019-07-19 09:30:53","status":3,"audit_time":"2019-07-19 09:31:21","pay_time":"","transaction_id":null,"update_time":"2019-07-19 09:31:21","status_text":"已拒绝","pay_type_text":"银联"},{"id":10,"store_id":4,"settle_sn":"SP201907181543163483","money":"13.00","real_money":"12.74","service_fee":"0.26","orders":"54","pay_type":3,"pay_account":{"union_realname":"123456","union_account":"123456","union_bank":"123456"},"create_time":"2019-07-18 15:43:16","status":3,"audit_time":"2019-07-19 09:29:30","pay_time":"","transaction_id":null,"update_time":"2019-07-19 09:29:30","status_text":"已拒绝","pay_type_text":"银联"}]
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
                 * id : 12
                 * store_id : 4
                 * settle_sn : SP201907190931536211
                 * money : 13.00
                 * real_money : 12.74
                 * service_fee : 0.26
                 * orders : 54
                 * pay_type : 2
                 * pay_account : {"alipay_realname":"张三","alipay_account":"516954023@qq.com"}
                 * create_time : 2019-07-19 09:31:53
                 * status : 2
                 * audit_time : 2019-07-19 09:32:07
                 * pay_time : 2019-07-19 09:32:52
                 * transaction_id : 手动打款
                 * update_time : 2019-07-19 09:32:52
                 * status_text : 已完成
                 * pay_type_text : 支付宝
                 */

                private int id;
                private int store_id;
                private String settle_sn;
                private String money;
                private String real_money;
                private String service_fee;
                private String orders;
                private int pay_type;
                private PayAccountBean pay_account;
                private String create_time;
                private int status;
                private String audit_time;
                private String pay_time;
                private String transaction_id;
                private String update_time;
                private String status_text;
                private String pay_type_text;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getStore_id() {
                    return store_id;
                }

                public void setStore_id(int store_id) {
                    this.store_id = store_id;
                }

                public String getSettle_sn() {
                    return settle_sn;
                }

                public void setSettle_sn(String settle_sn) {
                    this.settle_sn = settle_sn;
                }

                public String getMoney() {
                    return money;
                }

                public void setMoney(String money) {
                    this.money = money;
                }

                public String getReal_money() {
                    return real_money;
                }

                public void setReal_money(String real_money) {
                    this.real_money = real_money;
                }

                public String getService_fee() {
                    return service_fee;
                }

                public void setService_fee(String service_fee) {
                    this.service_fee = service_fee;
                }

                public String getOrders() {
                    return orders;
                }

                public void setOrders(String orders) {
                    this.orders = orders;
                }

                public int getPay_type() {
                    return pay_type;
                }

                public void setPay_type(int pay_type) {
                    this.pay_type = pay_type;
                }

                public PayAccountBean getPay_account() {
                    return pay_account;
                }

                public void setPay_account(PayAccountBean pay_account) {
                    this.pay_account = pay_account;
                }

                public String getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(String create_time) {
                    this.create_time = create_time;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public String getAudit_time() {
                    return audit_time;
                }

                public void setAudit_time(String audit_time) {
                    this.audit_time = audit_time;
                }

                public String getPay_time() {
                    return pay_time;
                }

                public void setPay_time(String pay_time) {
                    this.pay_time = pay_time;
                }

                public String getTransaction_id() {
                    return transaction_id;
                }

                public void setTransaction_id(String transaction_id) {
                    this.transaction_id = transaction_id;
                }

                public String getUpdate_time() {
                    return update_time;
                }

                public void setUpdate_time(String update_time) {
                    this.update_time = update_time;
                }

                public String getStatus_text() {
                    return status_text;
                }

                public void setStatus_text(String status_text) {
                    this.status_text = status_text;
                }

                public String getPay_type_text() {
                    return pay_type_text;
                }

                public void setPay_type_text(String pay_type_text) {
                    this.pay_type_text = pay_type_text;
                }

                public static class PayAccountBean {
                    /**
                     * alipay_realname : 张三
                     * alipay_account : 516954023@qq.com
                     */

                    private String alipay_realname;
                    private String alipay_account;

                    public String getAlipay_realname() {
                        return alipay_realname;
                    }

                    public void setAlipay_realname(String alipay_realname) {
                        this.alipay_realname = alipay_realname;
                    }

                    public String getAlipay_account() {
                        return alipay_account;
                    }

                    public void setAlipay_account(String alipay_account) {
                        this.alipay_account = alipay_account;
                    }
                }
            }
        }
    }
}
