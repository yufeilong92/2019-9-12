package com.tsyc.tianshengyoucai.model.bean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/13
 * File description： 店铺订单bean
 */
public class StoreOrderDetailBean {

    /**
     * code : 200
     * message :
     * result : {"buy_name":"phone_15639736373","line_info":{"add_time":"2019-08-12 15:23:02","evaluation_time":"","finnshed_time":"","payment_time":"","shipping_time":""},"order_id":216,"order_sn":"SP19081215230283468","order_type":2,"ordergoods":[{"goods_guige":"样式1 红色 L","goods_id":250,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019080112002796773.jpg","goods_name":"夏季新款女装真丝欧根纱圆领植绒印花无袖假两件宽松套头连衣长裙 样式1 红色 L","goods_num":1,"goods_price":"0.01"}],"pay_info":{"goods_amount":"0.01","order_amount":"0.01","payment_name":"微信"},"status_info":{"order_state":10,"status_desc":"请尽快付款，逾期将自动取消订单","status_text":"待付款"},"store_info":{"address":"","lat":"34.758262","lng":"113.608148","store_id":1,"store_name":"测试店铺"},"taking_info":{"verify_code_img":"","ziti_phone":"15639736373","ziti_time":"2019-08-12 15:22:00","ziti_verify_code":"69e832ec77c9584e"}}
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
         * buy_name : phone_15639736373
         * line_info : {"add_time":"2019-08-12 15:23:02","evaluation_time":"","finnshed_time":"","payment_time":"","shipping_time":""}
         * order_id : 216
         * order_sn : SP19081215230283468
         * order_type : 2
         * ordergoods : [{"goods_guige":"样式1 红色 L","goods_id":250,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019080112002796773.jpg","goods_name":"夏季新款女装真丝欧根纱圆领植绒印花无袖假两件宽松套头连衣长裙 样式1 红色 L","goods_num":1,"goods_price":"0.01"}]
         * pay_info : {"goods_amount":"0.01","order_amount":"0.01","payment_name":"微信"}
         * status_info : {"order_state":10,"status_desc":"请尽快付款，逾期将自动取消订单","status_text":"待付款"}
         * store_info : {"address":"","lat":"34.758262","lng":"113.608148","store_id":1,"store_name":"测试店铺"}
         * taking_info : {"verify_code_img":"","ziti_phone":"15639736373","ziti_time":"2019-08-12 15:22:00","ziti_verify_code":"69e832ec77c9584e"}
         */

        private String buy_name;
        private LineInfoBean line_info;
        private int order_id;
        private String order_sn;
        private String liuyan;
        private int order_type;
        private PayInfoBean pay_info;
        private StatusInfoBean status_info;
        private StoreInfoBean store_info;
        private TakingInfoBean taking_info;
        private RefundBean refund;
        private ReceiveInfoBean receive_info;

        public ReceiveInfoBean getReceive_info() {
            return receive_info;
        }

        public void setReceive_info(ReceiveInfoBean receive_info) {
            this.receive_info = receive_info;
        }

        public String getLiuyan() {
            return liuyan;
        }

        public void setLiuyan(String liuyan) {
            this.liuyan = liuyan;
        }

        public RefundBean getRefund() {
            return refund;
        }

        public void setRefund(RefundBean refund) {
            this.refund = refund;
        }

        private List<OrdergoodsBean> ordergoods;

        public String getBuy_name() {
            return buy_name;
        }

        public void setBuy_name(String buy_name) {
            this.buy_name = buy_name;
        }

        public LineInfoBean getLine_info() {
            return line_info;
        }

        public void setLine_info(LineInfoBean line_info) {
            this.line_info = line_info;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public int getOrder_type() {
            return order_type;
        }

        public void setOrder_type(int order_type) {
            this.order_type = order_type;
        }

        public PayInfoBean getPay_info() {
            return pay_info;
        }

        public void setPay_info(PayInfoBean pay_info) {
            this.pay_info = pay_info;
        }

        public StatusInfoBean getStatus_info() {
            return status_info;
        }

        public void setStatus_info(StatusInfoBean status_info) {
            this.status_info = status_info;
        }

        public StoreInfoBean getStore_info() {
            return store_info;
        }

        public void setStore_info(StoreInfoBean store_info) {
            this.store_info = store_info;
        }

        public TakingInfoBean getTaking_info() {
            return taking_info;
        }

        public void setTaking_info(TakingInfoBean taking_info) {
            this.taking_info = taking_info;
        }

        public List<OrdergoodsBean> getOrdergoods() {
            return ordergoods;
        }

        public void setOrdergoods(List<OrdergoodsBean> ordergoods) {
            this.ordergoods = ordergoods;
        }

        public static class  RefundBean{

            /**
             * add_time : 2019-07-31 09:46:52
             * phone : 请输入手机号
             * pic_info : ["http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019073109463831471.jpg","http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019073109463881765.jpg","http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019073109463813620.jpg"]
             * reason_info : 未按约定时间发货,Buxiangyaole zadiba
             * refund_amount : 296.00
             * refund_type : 仅退款
             */

            private String add_time;
            private String phone;
            private String reason_info;
            private String refund_amount;
            private String refund_type;
            private List<String> pic_info;

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getReason_info() {
                return reason_info;
            }

            public void setReason_info(String reason_info) {
                this.reason_info = reason_info;
            }

            public String getRefund_amount() {
                return refund_amount;
            }

            public void setRefund_amount(String refund_amount) {
                this.refund_amount = refund_amount;
            }

            public String getRefund_type() {
                return refund_type;
            }

            public void setRefund_type(String refund_type) {
                this.refund_type = refund_type;
            }

            public List<String> getPic_info() {
                return pic_info;
            }

            public void setPic_info(List<String> pic_info) {
                this.pic_info = pic_info;
            }
        }
        public static class LineInfoBean {

            /**
             * add_time : 2019-07-27 16:03:18
             * admin_time :
             * evaluation_time :
             * finnshed_time :
             * payment_time :
             * refund_sn : 332101190731094652
             * refund_time : 2019-07-31 09:46:52
             * seller_time :
             * shipping_time :
             */

            private String add_time;
            private String admin_time;
            private String evaluation_time;
            private String finnshed_time;
            private String payment_time;
            private String refund_sn;
            private String refund_time;
            private String seller_time;
            private String shipping_time;

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getAdmin_time() {
                return admin_time;
            }

            public void setAdmin_time(String admin_time) {
                this.admin_time = admin_time;
            }

            public String getEvaluation_time() {
                return evaluation_time;
            }

            public void setEvaluation_time(String evaluation_time) {
                this.evaluation_time = evaluation_time;
            }

            public String getFinnshed_time() {
                return finnshed_time;
            }

            public void setFinnshed_time(String finnshed_time) {
                this.finnshed_time = finnshed_time;
            }

            public String getPayment_time() {
                return payment_time;
            }

            public void setPayment_time(String payment_time) {
                this.payment_time = payment_time;
            }

            public String getRefund_sn() {
                return refund_sn;
            }

            public void setRefund_sn(String refund_sn) {
                this.refund_sn = refund_sn;
            }

            public String getRefund_time() {
                return refund_time;
            }

            public void setRefund_time(String refund_time) {
                this.refund_time = refund_time;
            }

            public String getSeller_time() {
                return seller_time;
            }

            public void setSeller_time(String seller_time) {
                this.seller_time = seller_time;
            }

            public String getShipping_time() {
                return shipping_time;
            }

            public void setShipping_time(String shipping_time) {
                this.shipping_time = shipping_time;
            }
        }

        public static class PayInfoBean {
            /**
             * goods_amount : 0.01
             * order_amount : 0.01
             * payment_name : 微信
             * coupon_discount : 优惠券
             * redpacket_discount : 红包
             * postage : 邮费
             */

            private String goods_amount;
            private String order_amount;
            private String payment_name;
            private String redpacket_discount;
            private String coupon_discount;
            private String postage;

            public String getPostage() {
                return postage;
            }

            public void setPostage(String postage) {
                this.postage = postage;
            }

            public String getRedpacket_discount() {
                return redpacket_discount;
            }

            public void setRedpacket_discount(String redpacket_discount) {
                this.redpacket_discount = redpacket_discount;
            }

            public String getCoupon_discount() {
                return coupon_discount;
            }

            public void setCoupon_discount(String coupon_discount) {
                this.coupon_discount = coupon_discount;
            }

            public String getGoods_amount() {
                return goods_amount;
            }

            public void setGoods_amount(String goods_amount) {
                this.goods_amount = goods_amount;
            }

            public String getOrder_amount() {
                return order_amount;
            }

            public void setOrder_amount(String order_amount) {
                this.order_amount = order_amount;
            }

            public String getPayment_name() {
                return payment_name;
            }

            public void setPayment_name(String payment_name) {
                this.payment_name = payment_name;
            }
        }

        public static class StatusInfoBean {
            /**
             * order_state : 10
             * status_desc : 请尽快付款，逾期将自动取消订单
             * status_text : 待付款
             */

            private int order_state;
            private String status_desc;
            private String status_text;

            public int getOrder_state() {
                return order_state;
            }

            public void setOrder_state(int order_state) {
                this.order_state = order_state;
            }

            public String getStatus_desc() {
                return status_desc;
            }

            public void setStatus_desc(String status_desc) {
                this.status_desc = status_desc;
            }

            public String getStatus_text() {
                return status_text;
            }

            public void setStatus_text(String status_text) {
                this.status_text = status_text;
            }
        }

        public static class StoreInfoBean {
            /**
             * address :
             * lat : 34.758262
             * lng : 113.608148
             * store_id : 1
             * store_name : 测试店铺
             */

            private String address;
            private String lat;
            private String lng;
            private int store_id;
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

        public static class TakingInfoBean {
            /**
             * verify_code_img :
             * ziti_phone : 15639736373
             * ziti_time : 2019-08-12 15:22:00
             * ziti_verify_code : 69e832ec77c9584e
             */

            private String verify_code_img;
            private String ziti_phone;
            private String ziti_time;
            private String ziti_verify_code;

            public String getVerify_code_img() {
                return verify_code_img;
            }

            public void setVerify_code_img(String verify_code_img) {
                this.verify_code_img = verify_code_img;
            }

            public String getZiti_phone() {
                return ziti_phone;
            }

            public void setZiti_phone(String ziti_phone) {
                this.ziti_phone = ziti_phone;
            }

            public String getZiti_time() {
                return ziti_time;
            }

            public void setZiti_time(String ziti_time) {
                this.ziti_time = ziti_time;
            }

            public String getZiti_verify_code() {
                return ziti_verify_code;
            }

            public void setZiti_verify_code(String ziti_verify_code) {
                this.ziti_verify_code = ziti_verify_code;
            }
        }

        public static class ReceiveInfoBean {
            /**
             * true_name : CC
             * mob_phone : 13203831658
             * area_info : 北京市 北京市 东城区
             * address : ABC
             */

            private String true_name;
            private String mob_phone;
            private String area_info;
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

            public String getArea_info() {
                return area_info;
            }

            public void setArea_info(String area_info) {
                this.area_info = area_info;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }

        public static class OrdergoodsBean {
            /**
             * goods_guige : 样式1 红色 L
             * goods_id : 250
             * goods_image : http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019080112002796773.jpg
             * goods_name : 夏季新款女装真丝欧根纱圆领植绒印花无袖假两件宽松套头连衣长裙 样式1 红色 L
             * goods_num : 1
             * goods_price : 0.01
             */

            private String goods_guige;
            private int goods_id;
            private String goods_image;
            private String goods_name;
            private int goods_num;
            private String goods_price;

            public String getGoods_guige() {
                return goods_guige;
            }

            public void setGoods_guige(String goods_guige) {
                this.goods_guige = goods_guige;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
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

            public int getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(int goods_num) {
                this.goods_num = goods_num;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }
        }
    }
}
