package com.tsyc.tianshengyoucai.model.bean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/5
 * File description：商家详情 商品适配器
 */
public class ProductListBean {


    /**
     * code : 200
     * result : [{"goods_id":288,"store_id":18,"goods_name":"明年","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019081100471466900.png","goods_salenum":0,"goods_price":"66.00","inner_price":"10.00","commission":"￥1","commission_type":"返","commission_text":"佣金","share_text":"分享消费返"},{"goods_id":289,"store_id":18,"goods_name":"djjdjdj","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019081101164816391.png","goods_salenum":0,"goods_price":"4545.00","inner_price":"4664.00","commission":"￥45,545","commission_type":"返","commission_text":"佣金","share_text":"分享消费返"},{"goods_id":297,"store_id":18,"goods_name":"鞋子？？","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019081311280394152.png","goods_salenum":0,"goods_price":"666.00","inner_price":"666.00","commission":"￥666","commission_type":"返","commission_text":"佣金","share_text":"分享消费返"},{"goods_id":301,"store_id":18,"goods_name":"真香手机","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019081315044219009.png","goods_salenum":0,"goods_price":"6800.00","inner_price":"5400.00","commission":"￥601","commission_type":"返","commission_text":"佣金","share_text":"分享消费返"},{"goods_id":302,"store_id":18,"goods_name":"测试测试商品","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019081316242915803.png","goods_salenum":0,"goods_price":"0.01","inner_price":"0.01","commission":"￥0","commission_type":"返","commission_text":"佣金","share_text":"分享消费返"}]
     * message :
     */

    private String code;
    private String message;
    private List<ResultBean> result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * goods_id : 288
         * store_id : 18
         * goods_name : 明年
         * goods_image : http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019081100471466900.png
         * goods_salenum : 0
         * goods_price : 66.00
         * inner_price : 10.00
         * commission : ￥1
         * commission_type : 返
         * commission_text : 佣金
         * share_text : 分享消费返
         */

        private int goods_id;
        private int store_id;
        private String goods_name;
        private String goods_image;
        private int goods_salenum;
        private String goods_price;
        private String inner_price;
        private String commission;
        private String commission_type;
        private String commission_text;
        private String share_text;

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_image() {
            return goods_image;
        }

        public void setGoods_image(String goods_image) {
            this.goods_image = goods_image;
        }

        public int getGoods_salenum() {
            return goods_salenum;
        }

        public void setGoods_salenum(int goods_salenum) {
            this.goods_salenum = goods_salenum;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public String getInner_price() {
            return inner_price;
        }

        public void setInner_price(String inner_price) {
            this.inner_price = inner_price;
        }

        public String getCommission() {
            return commission;
        }

        public void setCommission(String commission) {
            this.commission = commission;
        }

        public String getCommission_type() {
            return commission_type;
        }

        public void setCommission_type(String commission_type) {
            this.commission_type = commission_type;
        }

        public String getCommission_text() {
            return commission_text;
        }

        public void setCommission_text(String commission_text) {
            this.commission_text = commission_text;
        }

        public String getShare_text() {
            return share_text;
        }

        public void setShare_text(String share_text) {
            this.share_text = share_text;
        }
    }
}
