package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/10 10:51
 * @Purpose :
 */
public class GoodCollectVo extends NormalBean {

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * fav_id : 250
         * fav_type : goods
         * goods_image : http://tsyc.jiefutong.net/uploads/home/common/default_goods_image.gif
         * goods_name : 夏季新款女装真丝欧根纱圆领植绒印花无袖假两件宽松套头连衣长裙 样式1 红色 L
         * log_msg : 2198.00
         * log_price : 2198.00
         * member_id : 25
         * member_name : phone_18317837561
         * sc_id : 0
         * store_id : 1
         * store_name : 测试店铺
         */

        private int fav_id;
        private String fav_type;
        private String goods_image;
        private String goods_name;
        private String log_msg;
        private String log_price;
        private int member_id;
        private String member_name;
        private int sc_id;
        private int store_id;
        private String store_name;

        public int getFav_id() {
            return fav_id;
        }

        public void setFav_id(int fav_id) {
            this.fav_id = fav_id;
        }

        public String getFav_type() {
            return fav_type;
        }

        public void setFav_type(String fav_type) {
            this.fav_type = fav_type;
        }

        public String getGoods_image() {
            return goods_image;
        }

        public void setGoods_image(String goods_image) {
            this.goods_image = goods_image;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getLog_msg() {
            return log_msg;
        }

        public void setLog_msg(String log_msg) {
            this.log_msg = log_msg;
        }

        public String getLog_price() {
            return log_price;
        }

        public void setLog_price(String log_price) {
            this.log_price = log_price;
        }

        public int getMember_id() {
            return member_id;
        }

        public void setMember_id(int member_id) {
            this.member_id = member_id;
        }

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public int getSc_id() {
            return sc_id;
        }

        public void setSc_id(int sc_id) {
            this.sc_id = sc_id;
        }

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }
    }
}
