package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/14 11:26
 * @Purpose :红包vo
 */
public class RedVo extends NormalBean {

    /**
     * result : {"current_page":1,"data":[{"id":12,"state_name":"去使用","store_name":"贝佳尼","voucher_end_date":"2019.08.16","voucher_price":1,"voucher_start_date":"2019.08.13","voucher_state":1,"voucher_store_id":10}],"last_page":1,"per_page":15,"total":1}
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
         * current_page : 1
         * data : [{"id":12,"state_name":"去使用","store_name":"贝佳尼","voucher_end_date":"2019.08.16","voucher_price":1,"voucher_start_date":"2019.08.13","voucher_state":1,"voucher_store_id":10}]
         * last_page : 1
         * per_page : 15
         * total : 1
         */

        private int current_page;
        private int last_page;
        private int per_page;
        private int total;
        private List<DataBean> data;

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

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
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
             * state_name : 去使用
             * store_name : 贝佳尼
             * voucher_end_date : 2019.08.16
             * voucher_price : 1
             * voucher_start_date : 2019.08.13
             * voucher_state : 1
             * voucher_store_id : 10
             */

            private int id;
            private String state_name;
            private String store_name;
            private String voucher_end_date;
            private double voucher_price;
            private String voucher_start_date;
            private int voucher_state;
            private int voucher_store_id;
            private String voucher_limit;

            public String getVoucher_limit() {
                return voucher_limit;
            }

            public void setVoucher_limit(String voucher_limit) {
                this.voucher_limit = voucher_limit;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getState_name() {
                return state_name;
            }

            public void setState_name(String state_name) {
                this.state_name = state_name;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getVoucher_end_date() {
                return voucher_end_date;
            }

            public void setVoucher_end_date(String voucher_end_date) {
                this.voucher_end_date = voucher_end_date;
            }

            public double getVoucher_price() {
                return voucher_price;
            }

            public void setVoucher_price(double voucher_price) {
                this.voucher_price = voucher_price;
            }

            public String getVoucher_start_date() {
                return voucher_start_date;
            }

            public void setVoucher_start_date(String voucher_start_date) {
                this.voucher_start_date = voucher_start_date;
            }

            public int getVoucher_state() {
                return voucher_state;
            }

            public void setVoucher_state(int voucher_state) {
                this.voucher_state = voucher_state;
            }

            public int getVoucher_store_id() {
                return voucher_store_id;
            }

            public void setVoucher_store_id(int voucher_store_id) {
                this.voucher_store_id = voucher_store_id;
            }
        }
    }
}
