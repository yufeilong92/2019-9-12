package com.tsyc.tianshengyoucai.model.bean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/10
 * File description： 订单支付bean
 */
public class OrderPayBean {

    /**
     * code : 200
     * result : {"goods_info":{"goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":148,"spec":"","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg"},"address_info":[{"true_name":"丽丽","mob_phone":"15713861029","address_id":19,"address":"北京 北京市 朝阳区地址详情"}],"total":148,"postage":10,"payamount":158,"coupon_list":[{"voucher_id":1,"voucher_start_date":"2019-06-04 00:00:00","voucher_end_date":"2019-06-09 00:00:00","voucher_price":10,"voucher_limit":100,"voucher_type":1}]}
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
         * goods_info : {"goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":148,"spec":"","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg"}
         * address_info : [{"true_name":"丽丽","mob_phone":"15713861029","address_id":19,"address":"北京 北京市 朝阳区地址详情"}]
         * total : 148
         * postage : 10
         * payamount : 158
         * coupon_list : [{"voucher_id":1,"voucher_start_date":"2019-06-04 00:00:00","voucher_end_date":"2019-06-09 00:00:00","voucher_price":10,"voucher_limit":100,"voucher_type":1}]
         */

        private GoodsInfoBean goods_info;
        private int total;
        private int postage;
        private int payamount;
        private List<AddressInfoBean> address_info;
        private List<CouponListBean> coupon_list;

        public GoodsInfoBean getGoods_info() {
            return goods_info;
        }

        public void setGoods_info(GoodsInfoBean goods_info) {
            this.goods_info = goods_info;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPostage() {
            return postage;
        }

        public void setPostage(int postage) {
            this.postage = postage;
        }

        public int getPayamount() {
            return payamount;
        }

        public void setPayamount(int payamount) {
            this.payamount = payamount;
        }

        public List<AddressInfoBean> getAddress_info() {
            return address_info;
        }

        public void setAddress_info(List<AddressInfoBean> address_info) {
            this.address_info = address_info;
        }

        public List<CouponListBean> getCoupon_list() {
            return coupon_list;
        }

        public void setCoupon_list(List<CouponListBean> coupon_list) {
            this.coupon_list = coupon_list;
        }

        public static class GoodsInfoBean {
            /**
             * goods_name : LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套
             * goods_price : 148
             * spec :
             * goods_num : 1
             * goods_image : http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg
             */

            private String goods_name;
            private float goods_price;
            private String spec;
            private int goods_num;
            private String goods_image;

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public float getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(float goods_price) {
                this.goods_price = goods_price;
            }

            public String getSpec() {
                return spec;
            }

            public void setSpec(String spec) {
                this.spec = spec;
            }

            public int getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(int goods_num) {
                this.goods_num = goods_num;
            }

            public String getGoods_image() {
                return goods_image;
            }

            public void setGoods_image(String goods_image) {
                this.goods_image = goods_image;
            }
        }

        public static class AddressInfoBean {
            /**
             * true_name : 丽丽
             * mob_phone : 15713861029
             * address_id : 19
             * address : 北京 北京市 朝阳区地址详情
             */

            private String true_name;
            private String mob_phone;
            private int address_id;
            private String address;

            public String getTrue_name() {
                return true_name;
            }

            public void setTrue_name(String true_name) {
                this.true_name = true_name;
            }

            public String getMob_phone() {
                return mob_phone;
            }

            public void setMob_phone(String mob_phone) {
                this.mob_phone = mob_phone;
            }

            public int getAddress_id() {
                return address_id;
            }

            public void setAddress_id(int address_id) {
                this.address_id = address_id;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }

        public static class CouponListBean {
            /**
             * voucher_id : 1
             * voucher_start_date : 2019-06-04 00:00:00
             * voucher_end_date : 2019-06-09 00:00:00
             * voucher_price : 10
             * voucher_limit : 100
             * voucher_type : 1
             */

            private int voucher_id;
            private String voucher_start_date;
            private String voucher_end_date;
            private int voucher_price;
            private int voucher_limit;
            private int voucher_type;

            public int getVoucher_id() {
                return voucher_id;
            }

            public void setVoucher_id(int voucher_id) {
                this.voucher_id = voucher_id;
            }

            public String getVoucher_start_date() {
                return voucher_start_date;
            }

            public void setVoucher_start_date(String voucher_start_date) {
                this.voucher_start_date = voucher_start_date;
            }

            public String getVoucher_end_date() {
                return voucher_end_date;
            }

            public void setVoucher_end_date(String voucher_end_date) {
                this.voucher_end_date = voucher_end_date;
            }

            public int getVoucher_price() {
                return voucher_price;
            }

            public void setVoucher_price(int voucher_price) {
                this.voucher_price = voucher_price;
            }

            public int getVoucher_limit() {
                return voucher_limit;
            }

            public void setVoucher_limit(int voucher_limit) {
                this.voucher_limit = voucher_limit;
            }

            public int getVoucher_type() {
                return voucher_type;
            }

            public void setVoucher_type(int voucher_type) {
                this.voucher_type = voucher_type;
            }
        }
    }
}
