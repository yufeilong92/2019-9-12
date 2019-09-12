package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/7 17:40
 * @Purpose :我余额
 */
public class MyMoneyVo extends NormalBean {

    /**
     * result : {"list":[{"money":"-599.00","note":"商品购买","time":"2019-08-10 09:07:00"},{"money":"-699.00","note":"商品购买","time":"2019-08-10 09:05:56"},{"money":"+300.00","note":"会员充值","time":"2019-08-09 15:24:29"},{"money":"-1.00","note":"发布红包","time":"2019-08-09 11:58:31"},{"money":"-2000.00","note":"升级合伙人","time":"2019-08-09 11:55:05"},{"money":"-2000.00","note":"升级合伙人","time":"2019-08-09 11:42:01"},{"money":"-2000.00","note":"升级合伙人","time":"2019-08-09 11:41:00"},{"money":"-2000.00","note":"升级合伙人","time":"2019-08-09 11:40:58"},{"money":"-15.80","note":"商品购买","time":"2019-08-01 11:20:44"},{"money":"-15.80","note":"商品购买","time":"2019-08-01 11:20:37"}],"my_balance":"7000.99"}
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * list : [{"money":"-599.00","note":"商品购买","time":"2019-08-10 09:07:00"},{"money":"-699.00","note":"商品购买","time":"2019-08-10 09:05:56"},{"money":"+300.00","note":"会员充值","time":"2019-08-09 15:24:29"},{"money":"-1.00","note":"发布红包","time":"2019-08-09 11:58:31"},{"money":"-2000.00","note":"升级合伙人","time":"2019-08-09 11:55:05"},{"money":"-2000.00","note":"升级合伙人","time":"2019-08-09 11:42:01"},{"money":"-2000.00","note":"升级合伙人","time":"2019-08-09 11:41:00"},{"money":"-2000.00","note":"升级合伙人","time":"2019-08-09 11:40:58"},{"money":"-15.80","note":"商品购买","time":"2019-08-01 11:20:44"},{"money":"-15.80","note":"商品购买","time":"2019-08-01 11:20:37"}]
         * my_balance : 7000.99
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
             * money : -599.00
             * note : 商品购买
             * time : 2019-08-10 09:07:00
             */

            private String money;
            private String note;
            private String time;

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getNote() {
                return note;
            }

            public void setNote(String note) {
                this.note = note;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }
    }
}
