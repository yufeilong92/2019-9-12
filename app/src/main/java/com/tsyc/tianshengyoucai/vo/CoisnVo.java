package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/19 19:04
 * @Purpose :
 */
public class CoisnVo extends NormalBean {

    /**
     * result : {"current_page":1,"data":[{"date":"2019-05-28 10:50:05","money":"+12.00","note":"支付宝充值","time":1565609422,"type":2}],"last_page":1,"per_page":15,"total":1}
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
         * data : [{"date":"2019-05-28 10:50:05","money":"+12.00","note":"支付宝充值","time":1565609422,"type":2}]
         * last_page : 1
         * per_page : 15
         * total : 1
         */

        private int current_page;
        private int last_page;
        private int per_page;
        private int total;
        private List<CoisnItemVo> data;

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

        public List<CoisnItemVo> getData() {
            return data;
        }

        public void setData(List<CoisnItemVo> data) {
            this.data = data;
        }

    }
}
