package com.tsyc.tianshengyoucai.model.bean;

import com.tsyc.tianshengyoucai.vo.CouponListBean;
import com.tsyc.tianshengyoucai.vo.RedpacketVo;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/12
 * File description：到店自取bean
 */
public class SelfOrderBean {

    /**
     * code : 200
     * message :
     * result : {"coupon_list":[{"voucher_end_date":"2019-06-09 00:00:00","voucher_id":1,"voucher_limit":100,"voucher_price":10,"voucher_start_date":"2019-06-04 00:00:00","voucher_type":1}],"goods_info":{"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_num":1,"goods_price":148,"spec":""},"payamount":148,"store_info":{"address":"","lat":"34.758262","lng":"113.608148","store_name":"测试店铺"},"total":148}
     */

    private String code;
    private String message;
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * coupon_list : [{"voucher_end_date":"2019-06-09 00:00:00","voucher_id":1,"voucher_limit":100,"voucher_price":10,"voucher_start_date":"2019-06-04 00:00:00","voucher_type":1}]
         * goods_info : {"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_num":1,"goods_price":148,"spec":""}
         * payamount : 148
         * store_info : {"address":"","lat":"34.758262","lng":"113.608148","store_name":"测试店铺"}
         * total : 148
         */

        private GoodsInfoBean goods_info;
        private double payamount;
        private StoreInfoBean store_info;
        private double total;
        private List<CouponListBean> coupon_list;
        private  RedpacketVo redpacket;

        public RedpacketVo getRedpacket() {
            return redpacket;
        }

        public void setRedpacket(RedpacketVo redpacket) {
            this.redpacket = redpacket;
        }

        public GoodsInfoBean getGoods_info() {
            return goods_info;
        }

        public void setGoods_info(GoodsInfoBean goods_info) {
            this.goods_info = goods_info;
        }

        public double getPayamount() {
            return payamount;
        }

        public void setPayamount(double payamount) {
            this.payamount = payamount;
        }

        public StoreInfoBean getStore_info() {
            return store_info;
        }

        public void setStore_info(StoreInfoBean store_info) {
            this.store_info = store_info;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public List<CouponListBean> getCoupon_list() {
            return coupon_list;
        }

        public void setCoupon_list(List<CouponListBean> coupon_list) {
            this.coupon_list = coupon_list;
        }

        public static class GoodsInfoBean {
            /**
             * goods_image : http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg
             * goods_name : LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套
             * goods_num : 1
             * goods_price : 148
             * spec :
             */

            private String goods_image;
            private String goods_name;
            private int goods_num;
            private double goods_price;
            private String spec;

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

            public int getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(int goods_num) {
                this.goods_num = goods_num;
            }

            public double getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(double goods_price) {
                this.goods_price = goods_price;
            }

            public String getSpec() {
                return spec;
            }

            public void setSpec(String spec) {
                this.spec = spec;
            }
        }

        public static class StoreInfoBean {
            /**
             * address :
             * lat : 34.758262
             * lng : 113.608148
             * store_name : 测试店铺
             */

            private String address;
            private String lat;
            private String lng;
            private String store_name;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }
        }


    }
}
