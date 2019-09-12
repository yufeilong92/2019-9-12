package com.tsyc.tianshengyoucai.model.bean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/14
 * File description： 收银台账单明细
 */
public class BillDetailBean {

    /**
     * code : 200
     * result : {"start_time":"2019-06-01","end_time":"2019-06-31","list":{"total":1,"per_page":15,"current_page":1,"last_page":1,"data":[{"id":28,"buyer_id":8,"buyer_name":"phone_15037142280","store_id":9,"cashier_id":4,"ordersn":"CN201907031512452352","money":"10.00","status":1,"pay_type":1,"pay_time":"2019-06-18 15:11:58","is_applied":1,"apply_time":"2019-07-03 20:30:57","create_time":"2019-06-18 15:11:58","transaction_id":"12312312","update_time":"2019-06-18 15:11:58","applied_text":"已结算","pay_type_text":"微信"}]}}
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
         * start_time : 2019-06-01
         * end_time : 2019-06-31
         * list : {"total":1,"per_page":15,"current_page":1,"last_page":1,"data":[{"id":28,"buyer_id":8,"buyer_name":"phone_15037142280","store_id":9,"cashier_id":4,"ordersn":"CN201907031512452352","money":"10.00","status":1,"pay_type":1,"pay_time":"2019-06-18 15:11:58","is_applied":1,"apply_time":"2019-07-03 20:30:57","create_time":"2019-06-18 15:11:58","transaction_id":"12312312","update_time":"2019-06-18 15:11:58","applied_text":"已结算","pay_type_text":"微信"}]}
         */

        private String start_time;
        private String end_time;
        private ListBean list;

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * total : 1
             * per_page : 15
             * current_page : 1
             * last_page : 1
             * data : [{"id":28,"buyer_id":8,"buyer_name":"phone_15037142280","store_id":9,"cashier_id":4,"ordersn":"CN201907031512452352","money":"10.00","status":1,"pay_type":1,"pay_time":"2019-06-18 15:11:58","is_applied":1,"apply_time":"2019-07-03 20:30:57","create_time":"2019-06-18 15:11:58","transaction_id":"12312312","update_time":"2019-06-18 15:11:58","applied_text":"已结算","pay_type_text":"微信"}]
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
                 * id : 28
                 * buyer_id : 8
                 * buyer_name : phone_15037142280
                 * store_id : 9
                 * cashier_id : 4
                 * ordersn : CN201907031512452352
                 * money : 10.00
                 * status : 1
                 * pay_type : 1
                 * pay_time : 2019-06-18 15:11:58
                 * is_applied : 1
                 * apply_time : 2019-07-03 20:30:57
                 * create_time : 2019-06-18 15:11:58
                 * transaction_id : 12312312
                 * update_time : 2019-06-18 15:11:58
                 * applied_text : 已结算
                 * pay_type_text : 微信
                 */

                private int id;
                private int buyer_id;
                private String buyer_name;
                private int store_id;
                private int cashier_id;
                private String ordersn;
                private String money;
                private int status;
                private int pay_type;
                private String pay_time;
                private int is_applied;
                private String apply_time;
                private String create_time;
                private String transaction_id;
                private String update_time;
                private String applied_text;
                private String pay_type_text;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getBuyer_id() {
                    return buyer_id;
                }

                public void setBuyer_id(int buyer_id) {
                    this.buyer_id = buyer_id;
                }

                public String getBuyer_name() {
                    return buyer_name;
                }

                public void setBuyer_name(String buyer_name) {
                    this.buyer_name = buyer_name;
                }

                public int getStore_id() {
                    return store_id;
                }

                public void setStore_id(int store_id) {
                    this.store_id = store_id;
                }

                public int getCashier_id() {
                    return cashier_id;
                }

                public void setCashier_id(int cashier_id) {
                    this.cashier_id = cashier_id;
                }

                public String getOrdersn() {
                    return ordersn;
                }

                public void setOrdersn(String ordersn) {
                    this.ordersn = ordersn;
                }

                public String getMoney() {
                    return money;
                }

                public void setMoney(String money) {
                    this.money = money;
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

                public String getPay_time() {
                    return pay_time;
                }

                public void setPay_time(String pay_time) {
                    this.pay_time = pay_time;
                }

                public int getIs_applied() {
                    return is_applied;
                }

                public void setIs_applied(int is_applied) {
                    this.is_applied = is_applied;
                }

                public String getApply_time() {
                    return apply_time;
                }

                public void setApply_time(String apply_time) {
                    this.apply_time = apply_time;
                }

                public String getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(String create_time) {
                    this.create_time = create_time;
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

                public String getApplied_text() {
                    return applied_text;
                }

                public void setApplied_text(String applied_text) {
                    this.applied_text = applied_text;
                }

                public String getPay_type_text() {
                    return pay_type_text;
                }

                public void setPay_type_text(String pay_type_text) {
                    this.pay_type_text = pay_type_text;
                }
            }
        }
    }
}
