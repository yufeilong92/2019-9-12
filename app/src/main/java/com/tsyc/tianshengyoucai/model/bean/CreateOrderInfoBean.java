package com.tsyc.tianshengyoucai.model.bean;

import com.tsyc.tianshengyoucai.vo.CouponListBean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/10
 * File description： 创建订单信息 bean
 */
public class CreateOrderInfoBean extends NormalBean{

    /**
     * code : 200
     * message :
     * result : {"address_info":[{"address":"河南省 郑州市 中原区美林河畔","address_id":54,"mob_phone":"18317837561","true_name":"于飞龙"}],"coupon_list":[{"id":22,"voucher_end_date":"2019-08-20 00:00","voucher_limit":"120.00","voucher_price":3},{"id":23,"voucher_end_date":"2019-08-25 00:00","voucher_limit":"130.00","voucher_price":4},{"id":24,"voucher_end_date":"2019-08-25 00:00","voucher_limit":"130.00","voucher_price":4},{"id":25,"voucher_end_date":"2019-08-25 00:00","voucher_limit":"130.00","voucher_price":4},{"id":26,"voucher_end_date":"2019-08-25 00:00","voucher_limit":"130.00","voucher_price":4}],"goods_info":{"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/8_2019072214103218585.jpg","goods_name":"小红书 颜色 红色","goods_num":1,"goods_price":200,"spec":"红色"},"payamount":200,"postage":0,"redpacket":{"id":36,"voucher_end_date":"2019-08-18 14:31","voucher_price":10},"total":200}
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
         * address_info : [{"address":"河南省 郑州市 中原区美林河畔","address_id":54,"mob_phone":"18317837561","true_name":"于飞龙"}]
         * coupon_list : [{"id":22,"voucher_end_date":"2019-08-20 00:00","voucher_limit":"120.00","voucher_price":3},{"id":23,"voucher_end_date":"2019-08-25 00:00","voucher_limit":"130.00","voucher_price":4},{"id":24,"voucher_end_date":"2019-08-25 00:00","voucher_limit":"130.00","voucher_price":4},{"id":25,"voucher_end_date":"2019-08-25 00:00","voucher_limit":"130.00","voucher_price":4},{"id":26,"voucher_end_date":"2019-08-25 00:00","voucher_limit":"130.00","voucher_price":4}]
         * goods_info : {"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/8_2019072214103218585.jpg","goods_name":"小红书 颜色 红色","goods_num":1,"goods_price":200,"spec":"红色"}
         * payamount : 200
         * postage : 0
         * redpacket : {"id":36,"voucher_end_date":"2019-08-18 14:31","voucher_price":10}
         * total : 200
         */

        private GoodsInfoBean goods_info;
        private double payamount;
        private double postage;
        private RedpacketBean redpacket;
        private double total;
        private List<AddressInfoBean> address_info;
        private List<CouponListBean> coupon_list;

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

        public double getPostage() {
            return postage;
        }

        public void setPostage(double postage) {
            this.postage = postage;
        }

        public RedpacketBean getRedpacket() {
            return redpacket;
        }

        public void setRedpacket(RedpacketBean redpacket) {
            this.redpacket = redpacket;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
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
             * goods_image : http://tsyc.jiefutong.net/uploads/home/membertag/201907/8_2019072214103218585.jpg
             * goods_name : 小红书 颜色 红色
             * goods_num : 1
             * goods_price : 200
             * spec : 红色
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

        public static class RedpacketBean {
            /**
             * id : 36
             * voucher_end_date : 2019-08-18 14:31
             * voucher_price : 10
             */

            private int id;
            private String voucher_end_date;
            private double voucher_price;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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
        }

        public static class AddressInfoBean {
            /**
             * address : 河南省 郑州市 中原区美林河畔
             * address_id : 54
             * mob_phone : 18317837561
             * true_name : 于飞龙
             */

            private String address;
            private int address_id;
            private String mob_phone;
            private String true_name;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getAddress_id() {
                return address_id;
            }

            public void setAddress_id(int address_id) {
                this.address_id = address_id;
            }

            public String getMob_phone() {
                return mob_phone;
            }

            public void setMob_phone(String mob_phone) {
                this.mob_phone = mob_phone;
            }

            public String getTrue_name() {
                return true_name;
            }

            public void setTrue_name(String true_name) {
                this.true_name = true_name;
            }
        }

    }
}
