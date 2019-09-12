package com.tsyc.tianshengyoucai.model.bean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/19
 * File description： 我的余额
 */
public class MyBalanceBean {

    /**
     * code : 200
     * result : {"my_balance":"9793.00","list":[{"money":"-10.00","time":"2019-08-10 18:53:13","note":"发布红包"},{"money":"-2000.00","time":"2019-08-09 11:53:12","note":"升级合伙人"},{"money":"+2.00","time":"2019-08-02 16:55:12","note":"订单退款"},{"money":"-1.00","time":"2019-07-24 17:52:36","note":"商品购买"},{"money":"-2.00","time":"2019-07-24 16:30:08","note":"商品购买"},{"money":"+4.00","time":"2019-07-24 14:09:46","note":"订单退款"},{"money":"-4.00","time":"2019-07-24 10:53:05","note":"商品购买"},{"money":"+1.00","time":"2019-07-24 10:27:09","note":"订单退款"},{"money":"-1.00","time":"2019-07-24 10:15:42","note":"商品购买"},{"money":"-1.00","time":"2019-07-22 17:14:55","note":"商品购买"}]}
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
         * my_balance : 9793.00
         * list : [{"money":"-10.00","time":"2019-08-10 18:53:13","note":"发布红包"},{"money":"-2000.00","time":"2019-08-09 11:53:12","note":"升级合伙人"},{"money":"+2.00","time":"2019-08-02 16:55:12","note":"订单退款"},{"money":"-1.00","time":"2019-07-24 17:52:36","note":"商品购买"},{"money":"-2.00","time":"2019-07-24 16:30:08","note":"商品购买"},{"money":"+4.00","time":"2019-07-24 14:09:46","note":"订单退款"},{"money":"-4.00","time":"2019-07-24 10:53:05","note":"商品购买"},{"money":"+1.00","time":"2019-07-24 10:27:09","note":"订单退款"},{"money":"-1.00","time":"2019-07-24 10:15:42","note":"商品购买"},{"money":"-1.00","time":"2019-07-22 17:14:55","note":"商品购买"}]
         */

        private String my_balance;
        private List<ListBean> list;

        public String getMy_balance() {
            return my_balance;
        }

        public void setMy_balance(String my_balance) {
            this.my_balance = my_balance;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * money : -10.00
             * time : 2019-08-10 18:53:13
             * note : 发布红包
             */

            private String money;
            private String time;
            private String note;

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getNote() {
                return note;
            }

            public void setNote(String note) {
                this.note = note;
            }
        }
    }
}
