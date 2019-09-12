package com.tsyc.tianshengyoucai.model.bean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/10
 * File description： 线上线下 订单详情bean
 */
public class OrderDetailBean {


    /**
     * code : 200
     * result : {"order_id":161,"order_type":1,"order_sn":"SP19080922110271707","buy_name":"SSS","status_info":{"order_state":"101","status_text":"维权中-待商家审核","status_desc":"您申请了维权，等待商家审核"},"ordergoods":[{"goods_id":253,"goods_guige":"样式1 绿色 M","goods_name":"夏季新款女装真丝欧根纱圆领植绒印花无袖假两件宽松套头连衣长裙 样式1 绿色 M","goods_price":"0.01","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019080112002796773.jpg"}],"pay_info":{"goods_amount":"0.01","order_amount":"0.01","payment_name":"支付宝"},"receive_info":{"true_name":"CC","mob_phone":"13203831658","area_info":"北京市 北京市 东城区","address":"ABC"},"express_info":null,"store_info":null,"refund":{"refund_type":"退货退款","add_time":"2019-08-10 19:15:01","refund_amount":"0.01","reason_info":"材质与商品描述不符合,","pic_info":[],"phone":"1837834561"},"taking_info":null,"line_info":{"add_time":"2019-08-09 22:11:02","payment_time":"2019-08-10 14:34:48","shipping_time":"","finnshed_time":"","evaluation_time":"","refund_sn":"860101190810191501","refund_time":"2019-08-10 19:15:01","seller_time":"","admin_time":""}}
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
         * order_id : 161
         * order_type : 1
         * order_sn : SP19080922110271707
         * buy_name : SSS
         * status_info : {"order_state":"101","status_text":"维权中-待商家审核","status_desc":"您申请了维权，等待商家审核"}
         * ordergoods : [{"goods_id":253,"goods_guige":"样式1 绿色 M","goods_name":"夏季新款女装真丝欧根纱圆领植绒印花无袖假两件宽松套头连衣长裙 样式1 绿色 M","goods_price":"0.01","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019080112002796773.jpg"}]
         * pay_info : {"goods_amount":"0.01","order_amount":"0.01","payment_name":"支付宝"}
         * receive_info : {"true_name":"CC","mob_phone":"13203831658","area_info":"北京市 北京市 东城区","address":"ABC"}
         * express_info : null
         * store_info : null
         * refund : {"refund_type":"退货退款","add_time":"2019-08-10 19:15:01","refund_amount":"0.01","reason_info":"材质与商品描述不符合,","pic_info":[],"phone":"1837834561"}
         * taking_info : null
         * line_info : {"add_time":"2019-08-09 22:11:02","payment_time":"2019-08-10 14:34:48","shipping_time":"","finnshed_time":"","evaluation_time":"","refund_sn":"860101190810191501","refund_time":"2019-08-10 19:15:01","seller_time":"","admin_time":""}
         */

        private int order_id;
        private int order_type;
        private String order_sn;
        private String buy_name;
        private String liuyan;
        private StatusInfoBean status_info;
        private PayInfoBean pay_info;
        private ReceiveInfoBean receive_info;
        private Object express_info;
        private Object store_info;
        private RefundBean refund;
        private Object taking_info;
        private LineInfoBean line_info;
        private List<OrdergoodsBean> ordergoods;

        public String getLiuyan() {
            return liuyan;
        }

        public void setLiuyan(String liuyan) {
            this.liuyan = liuyan;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public int getOrder_type() {
            return order_type;
        }

        public void setOrder_type(int order_type) {
            this.order_type = order_type;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getBuy_name() {
            return buy_name;
        }

        public void setBuy_name(String buy_name) {
            this.buy_name = buy_name;
        }

        public StatusInfoBean getStatus_info() {
            return status_info;
        }

        public void setStatus_info(StatusInfoBean status_info) {
            this.status_info = status_info;
        }

        public PayInfoBean getPay_info() {
            return pay_info;
        }

        public void setPay_info(PayInfoBean pay_info) {
            this.pay_info = pay_info;
        }

        public ReceiveInfoBean getReceive_info() {
            return receive_info;
        }

        public void setReceive_info(ReceiveInfoBean receive_info) {
            this.receive_info = receive_info;
        }

        public Object getExpress_info() {
            return express_info;
        }

        public void setExpress_info(Object express_info) {
            this.express_info = express_info;
        }

        public Object getStore_info() {
            return store_info;
        }

        public void setStore_info(Object store_info) {
            this.store_info = store_info;
        }

        public RefundBean getRefund() {
            return refund;
        }

        public void setRefund(RefundBean refund) {
            this.refund = refund;
        }

        public Object getTaking_info() {
            return taking_info;
        }

        public void setTaking_info(Object taking_info) {
            this.taking_info = taking_info;
        }

        public LineInfoBean getLine_info() {
            return line_info;
        }

        public void setLine_info(LineInfoBean line_info) {
            this.line_info = line_info;
        }

        public List<OrdergoodsBean> getOrdergoods() {
            return ordergoods;
        }

        public void setOrdergoods(List<OrdergoodsBean> ordergoods) {
            this.ordergoods = ordergoods;
        }

        public static class StatusInfoBean {
            /**
             * order_state : 101
             * status_text : 维权中-待商家审核
             * status_desc : 您申请了维权，等待商家审核
             */

            private int order_state;
            private String status_text;
            private String status_desc;

            public int getOrder_state() {
                return order_state;
            }

            public void setOrder_state(int order_state) {
                this.order_state = order_state;
            }

            public String getStatus_text() {
                return status_text;
            }

            public void setStatus_text(String status_text) {
                this.status_text = status_text;
            }

            public String getStatus_desc() {
                return status_desc;
            }

            public void setStatus_desc(String status_desc) {
                this.status_desc = status_desc;
            }
        }

        public static class PayInfoBean {
            /**
             * goods_amount : 0.01
             * order_amount : 0.01
             * payment_name : 支付宝
             * redpacket_discount : 红包
             * coupon_discount : 优惠券
             * postage： 运费
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

        public static class RefundBean {
            /**
             * refund_type : 退货退款
             * add_time : 2019-08-10 19:15:01
             * refund_amount : 0.01
             * reason_info : 材质与商品描述不符合,
             * pic_info : []
             * phone : 1837834561
             */

            private String refund_type;
            private String add_time;
            private String refund_amount;
            private String reason_info;
            private String phone;
            private List<String> pic_info;

            public String getRefund_type() {
                return refund_type;
            }

            public void setRefund_type(String refund_type) {
                this.refund_type = refund_type;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getRefund_amount() {
                return refund_amount;
            }

            public void setRefund_amount(String refund_amount) {
                this.refund_amount = refund_amount;
            }

            public String getReason_info() {
                return reason_info;
            }

            public void setReason_info(String reason_info) {
                this.reason_info = reason_info;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
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
             * add_time : 2019-08-09 22:11:02
             * payment_time : 2019-08-10 14:34:48
             * shipping_time :
             * finnshed_time :
             * evaluation_time :
             * refund_sn : 860101190810191501
             * refund_time : 2019-08-10 19:15:01
             * seller_time :
             * admin_time :
             */

            private String add_time;
            private String payment_time;
            private String shipping_time;
            private String finnshed_time;
            private String evaluation_time;
            private String refund_sn;
            private String refund_time;
            private String seller_time;
            private String admin_time;

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getPayment_time() {
                return payment_time;
            }

            public void setPayment_time(String payment_time) {
                this.payment_time = payment_time;
            }

            public String getShipping_time() {
                return shipping_time;
            }

            public void setShipping_time(String shipping_time) {
                this.shipping_time = shipping_time;
            }

            public String getFinnshed_time() {
                return finnshed_time;
            }

            public void setFinnshed_time(String finnshed_time) {
                this.finnshed_time = finnshed_time;
            }

            public String getEvaluation_time() {
                return evaluation_time;
            }

            public void setEvaluation_time(String evaluation_time) {
                this.evaluation_time = evaluation_time;
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

            public String getAdmin_time() {
                return admin_time;
            }

            public void setAdmin_time(String admin_time) {
                this.admin_time = admin_time;
            }
        }

        public static class OrdergoodsBean {
            /**
             * goods_id : 253
             * goods_guige : 样式1 绿色 M
             * goods_name : 夏季新款女装真丝欧根纱圆领植绒印花无袖假两件宽松套头连衣长裙 样式1 绿色 M
             * goods_price : 0.01
             * goods_num : 1
             * goods_image : http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019080112002796773.jpg
             */

            private int goods_id;
            private String goods_guige;
            private String goods_name;
            private String goods_price;
            private int goods_num;
            private String goods_image;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_guige() {
                return goods_guige;
            }

            public void setGoods_guige(String goods_guige) {
                this.goods_guige = goods_guige;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
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
    }
}
