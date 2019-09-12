package com.tsyc.tianshengyoucai.model.bean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/14
 * File description： 扫码核销订单
 */
public class ScanOrderBean {

    /**
     * code : 200
     * result : {"order_id":254,"order_sn":"SP19081400520960427","pay_sn":"4200000376201908142582823166","store_id":18,"store_name":"V MALL旗舰","buyer_id":30,"buyer_name":"phone_15639736373","buyer_email":null,"add_time":"2019-08-14 00:52:09","payment_code":"","payment_time":"2019-08-14 00:52:29","finnshed_time":0,"goods_amount":"0.01","order_amount":"0.01","rcb_amount":"0.00","pd_amount":"0.00","shipping_fee":"0.00","evaluation_state":0,"order_state":20,"refund_state":0,"lock_state":0,"delete_state":0,"refund_amount":"0.00","delay_time":0,"order_from":1,"order_type":2,"address_id":0,"payment":1,"liuyan":"","is_ziti":1,"ziti_phone":"请输入手机号","ziti_time":"1970-01-01 08:00:00","ziti_verify_code":"b07058f4ab5e96b9","receive_info":[],"shipping_express_id":0,"shipping_code":"","shipping_time":0,"evaluation_time":0,"is_store_settled":0,"store_settled_time":null,"is_inner_order":0,"ordergoods":[{"goods_id":302,"goods_guige":"","goods_name":"测试测试商品","goods_price":"0.01","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019081316242915803.png"}],"status_text":"待使用","payment_name":"微信"}
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
         * order_id : 254
         * order_sn : SP19081400520960427
         * pay_sn : 4200000376201908142582823166
         * store_id : 18
         * store_name : V MALL旗舰
         * buyer_id : 30
         * buyer_name : phone_15639736373
         * buyer_email : null
         * add_time : 2019-08-14 00:52:09
         * payment_code :
         * payment_time : 2019-08-14 00:52:29
         * finnshed_time : 0
         * goods_amount : 0.01
         * order_amount : 0.01
         * rcb_amount : 0.00
         * pd_amount : 0.00
         * shipping_fee : 0.00
         * evaluation_state : 0
         * order_state : 20
         * refund_state : 0
         * lock_state : 0
         * delete_state : 0
         * refund_amount : 0.00
         * delay_time : 0
         * order_from : 1
         * order_type : 2
         * address_id : 0
         * payment : 1
         * liuyan :
         * is_ziti : 1
         * ziti_phone : 请输入手机号
         * ziti_time : 1970-01-01 08:00:00
         * ziti_verify_code : b07058f4ab5e96b9
         * receive_info : []
         * shipping_express_id : 0
         * shipping_code :
         * shipping_time : 0
         * evaluation_time : 0
         * is_store_settled : 0
         * store_settled_time : null
         * is_inner_order : 0
         * ordergoods : [{"goods_id":302,"goods_guige":"","goods_name":"测试测试商品","goods_price":"0.01","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019081316242915803.png"}]
         * status_text : 待使用
         * payment_name : 微信
         */

        private int order_id;
        private String order_sn;
        private String pay_sn;
        private int store_id;
        private String store_name;
        private int buyer_id;
        private String buyer_name;
        private Object buyer_email;
        private String add_time;
        private String payment_code;
        private String payment_time;
        private int finnshed_time;
        private String goods_amount;
        private String order_amount;
        private String rcb_amount;
        private String pd_amount;
        private String shipping_fee;
        private int evaluation_state;
        private int order_state;
        private int refund_state;
        private int lock_state;
        private int delete_state;
        private String refund_amount;
        private int delay_time;
        private int order_from;
        private int order_type;
        private int address_id;
        private int payment;
        private String liuyan;
        private int is_ziti;
        private String ziti_phone;
        private String ziti_time;
        private String ziti_verify_code;
        private int shipping_express_id;
        private String shipping_code;
        private int shipping_time;
        private int evaluation_time;
        private int is_store_settled;
        private Object store_settled_time;
        private int is_inner_order;
        private String status_text;
        private String payment_name;
        private List<?> receive_info;
        private List<OrdergoodsBean> ordergoods;

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

        public String getPay_sn() {
            return pay_sn;
        }

        public void setPay_sn(String pay_sn) {
            this.pay_sn = pay_sn;
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

        public int getBuyer_id() {
            return buyer_id;
        }

        public void setBuyer_id(int buyer_id) {
            this.buyer_id = buyer_id;
        }

        public String getBuyer_name() {
            return buyer_name;
        }

        public void setBuyer_name(String buyer_name) {
            this.buyer_name = buyer_name;
        }

        public Object getBuyer_email() {
            return buyer_email;
        }

        public void setBuyer_email(Object buyer_email) {
            this.buyer_email = buyer_email;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getPayment_code() {
            return payment_code;
        }

        public void setPayment_code(String payment_code) {
            this.payment_code = payment_code;
        }

        public String getPayment_time() {
            return payment_time;
        }

        public void setPayment_time(String payment_time) {
            this.payment_time = payment_time;
        }

        public int getFinnshed_time() {
            return finnshed_time;
        }

        public void setFinnshed_time(int finnshed_time) {
            this.finnshed_time = finnshed_time;
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

        public String getRcb_amount() {
            return rcb_amount;
        }

        public void setRcb_amount(String rcb_amount) {
            this.rcb_amount = rcb_amount;
        }

        public String getPd_amount() {
            return pd_amount;
        }

        public void setPd_amount(String pd_amount) {
            this.pd_amount = pd_amount;
        }

        public String getShipping_fee() {
            return shipping_fee;
        }

        public void setShipping_fee(String shipping_fee) {
            this.shipping_fee = shipping_fee;
        }

        public int getEvaluation_state() {
            return evaluation_state;
        }

        public void setEvaluation_state(int evaluation_state) {
            this.evaluation_state = evaluation_state;
        }

        public int getOrder_state() {
            return order_state;
        }

        public void setOrder_state(int order_state) {
            this.order_state = order_state;
        }

        public int getRefund_state() {
            return refund_state;
        }

        public void setRefund_state(int refund_state) {
            this.refund_state = refund_state;
        }

        public int getLock_state() {
            return lock_state;
        }

        public void setLock_state(int lock_state) {
            this.lock_state = lock_state;
        }

        public int getDelete_state() {
            return delete_state;
        }

        public void setDelete_state(int delete_state) {
            this.delete_state = delete_state;
        }

        public String getRefund_amount() {
            return refund_amount;
        }

        public void setRefund_amount(String refund_amount) {
            this.refund_amount = refund_amount;
        }

        public int getDelay_time() {
            return delay_time;
        }

        public void setDelay_time(int delay_time) {
            this.delay_time = delay_time;
        }

        public int getOrder_from() {
            return order_from;
        }

        public void setOrder_from(int order_from) {
            this.order_from = order_from;
        }

        public int getOrder_type() {
            return order_type;
        }

        public void setOrder_type(int order_type) {
            this.order_type = order_type;
        }

        public int getAddress_id() {
            return address_id;
        }

        public void setAddress_id(int address_id) {
            this.address_id = address_id;
        }

        public int getPayment() {
            return payment;
        }

        public void setPayment(int payment) {
            this.payment = payment;
        }

        public String getLiuyan() {
            return liuyan;
        }

        public void setLiuyan(String liuyan) {
            this.liuyan = liuyan;
        }

        public int getIs_ziti() {
            return is_ziti;
        }

        public void setIs_ziti(int is_ziti) {
            this.is_ziti = is_ziti;
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

        public int getShipping_express_id() {
            return shipping_express_id;
        }

        public void setShipping_express_id(int shipping_express_id) {
            this.shipping_express_id = shipping_express_id;
        }

        public String getShipping_code() {
            return shipping_code;
        }

        public void setShipping_code(String shipping_code) {
            this.shipping_code = shipping_code;
        }

        public int getShipping_time() {
            return shipping_time;
        }

        public void setShipping_time(int shipping_time) {
            this.shipping_time = shipping_time;
        }

        public int getEvaluation_time() {
            return evaluation_time;
        }

        public void setEvaluation_time(int evaluation_time) {
            this.evaluation_time = evaluation_time;
        }

        public int getIs_store_settled() {
            return is_store_settled;
        }

        public void setIs_store_settled(int is_store_settled) {
            this.is_store_settled = is_store_settled;
        }

        public Object getStore_settled_time() {
            return store_settled_time;
        }

        public void setStore_settled_time(Object store_settled_time) {
            this.store_settled_time = store_settled_time;
        }

        public int getIs_inner_order() {
            return is_inner_order;
        }

        public void setIs_inner_order(int is_inner_order) {
            this.is_inner_order = is_inner_order;
        }

        public String getStatus_text() {
            return status_text;
        }

        public void setStatus_text(String status_text) {
            this.status_text = status_text;
        }

        public String getPayment_name() {
            return payment_name;
        }

        public void setPayment_name(String payment_name) {
            this.payment_name = payment_name;
        }

        public List<?> getReceive_info() {
            return receive_info;
        }

        public void setReceive_info(List<?> receive_info) {
            this.receive_info = receive_info;
        }

        public List<OrdergoodsBean> getOrdergoods() {
            return ordergoods;
        }

        public void setOrdergoods(List<OrdergoodsBean> ordergoods) {
            this.ordergoods = ordergoods;
        }

        public static class OrdergoodsBean {
            /**
             * goods_id : 302
             * goods_guige :
             * goods_name : 测试测试商品
             * goods_price : 0.01
             * goods_num : 1
             * goods_image : http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019081316242915803.png
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
