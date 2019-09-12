package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/9 17:20
 * @Purpose : 合伙人
 */
public class PrartnerVo  extends NormalBean {

    /**
     * result : {"description":"赠送200金币","name":"合伙人","old_price":29800,"partner_id":1,"price":2000}
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
         * description : 赠送200金币
         * name : 合伙人
         * old_price : 29800
         * partner_id : 1
         * price : 2000
         */

        private String description;
        private String name;
        private int old_price;
        private int partner_id;
        private double price;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOld_price() {
            return old_price;
        }

        public void setOld_price(int old_price) {
            this.old_price = old_price;
        }

        public int getPartner_id() {
            return partner_id;
        }

        public void setPartner_id(int partner_id) {
            this.partner_id = partner_id;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}
