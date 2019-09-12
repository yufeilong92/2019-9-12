package com.tsyc.tianshengyoucai.model.bean;

import com.tsyc.tianshengyoucai.model.normal;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/5
 * File description：
 */
public class ShopDetailHeaderBean {

    /**
     * code : 200
     * result : {"store_id":18,"store_name":"V MALL旗舰","member_id":30,"store_phone":"15639736373","area_info":"河南省 郑州市 管城回族区","store_address":"详细地址，外加经纬度","store_credit_average":5,"store_avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019081321445273424.png","is_favorate":false,"mb_title_img":"","type_name":"服务类","other":{"is_online_offline":"支持线上线下","is_voucher":"红包活动","is_recruit":0,"commission":"45545.00"},"can_get_redpacket":false,"redpacket":null,"coupon_list":[{"voucher_id":73,"voucher_price":"10.00","voucher_limit":"50.00","place_limit":1,"is_get":0},{"voucher_id":74,"voucher_price":"10.00","voucher_limit":"50.00","place_limit":1,"is_get":0},{"voucher_id":75,"voucher_price":"1.00","voucher_limit":"1.00","place_limit":1,"is_get":0},{"voucher_id":90,"voucher_price":"55.00","voucher_limit":"40.00","place_limit":2,"is_get":0},{"voucher_id":91,"voucher_price":"55.00","voucher_limit":"40.00","place_limit":2,"is_get":0},{"voucher_id":203,"voucher_price":"464646.00","voucher_limit":"1511.00","place_limit":134646,"is_get":0},{"voucher_id":259,"voucher_price":"12.00","voucher_limit":"90.00","place_limit":1,"is_get":0}]}
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
         * store_id : 18
         * store_name : V MALL旗舰
         * member_id : 30
         * store_phone : 15639736373
         * area_info : 河南省 郑州市 管城回族区
         * store_address : 详细地址，外加经纬度
         * store_credit_average : 5
         * store_avatar : http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019081321445273424.png
         * is_favorate : false
         * mb_title_img :
         * type_name : 服务类
         * other : {"is_online_offline":"支持线上线下","is_voucher":"红包活动","is_recruit":0,"commission":"45545.00"}
         * can_get_redpacket : false
         * redpacket : null
         * coupon_list : [{"voucher_id":73,"voucher_price":"10.00","voucher_limit":"50.00","place_limit":1,"is_get":0},{"voucher_id":74,"voucher_price":"10.00","voucher_limit":"50.00","place_limit":1,"is_get":0},{"voucher_id":75,"voucher_price":"1.00","voucher_limit":"1.00","place_limit":1,"is_get":0},{"voucher_id":90,"voucher_price":"55.00","voucher_limit":"40.00","place_limit":2,"is_get":0},{"voucher_id":91,"voucher_price":"55.00","voucher_limit":"40.00","place_limit":2,"is_get":0},{"voucher_id":203,"voucher_price":"464646.00","voucher_limit":"1511.00","place_limit":134646,"is_get":0},{"voucher_id":259,"voucher_price":"12.00","voucher_limit":"90.00","place_limit":1,"is_get":0}]
         */

        private int store_id;
        private String store_name;
        private int member_id;
        private String store_phone;
        private String area_info;
        private String store_address;
        private String store_credit_average;
        private String store_avatar;
        private boolean is_favorate;
        private String mb_title_img;
        private String type_name;
        private OtherBean other;
        private boolean can_get_redpacket;
        private Redpacket redpacket;
        private List<CouponListBean> coupon_list;

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

        public int getMember_id() {
            return member_id;
        }

        public void setMember_id(int member_id) {
            this.member_id = member_id;
        }

        public String getStore_phone() {
            return store_phone;
        }

        public void setStore_phone(String store_phone) {
            this.store_phone = store_phone;
        }

        public String getArea_info() {
            return area_info;
        }

        public void setArea_info(String area_info) {
            this.area_info = area_info;
        }

        public String getStore_address() {
            return store_address;
        }

        public void setStore_address(String store_address) {
            this.store_address = store_address;
        }

        public String getStore_credit_average() {
            return store_credit_average;
        }

        public void setStore_credit_average(String store_credit_average) {
            this.store_credit_average = store_credit_average;
        }

        public String getStore_avatar() {
            return store_avatar;
        }

        public void setStore_avatar(String store_avatar) {
            this.store_avatar = store_avatar;
        }

        public boolean isIs_favorate() {
            return is_favorate;
        }

        public void setIs_favorate(boolean is_favorate) {
            this.is_favorate = is_favorate;
        }

        public String getMb_title_img() {
            return mb_title_img;
        }

        public void setMb_title_img(String mb_title_img) {
            this.mb_title_img = mb_title_img;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public OtherBean getOther() {
            return other;
        }

        public void setOther(OtherBean other) {
            this.other = other;
        }

        public boolean isCan_get_redpacket() {
            return can_get_redpacket;
        }

        public void setCan_get_redpacket(boolean can_get_redpacket) {
            this.can_get_redpacket = can_get_redpacket;
        }

        public Redpacket getRedpacket() {
            return redpacket;
        }

        public void setRedpacket(Redpacket redpacket) {
            this.redpacket = redpacket;
        }

        public List<CouponListBean> getCoupon_list() {
            return coupon_list;
        }

        public void setCoupon_list(List<CouponListBean> coupon_list) {
            this.coupon_list = coupon_list;
        }

        public static class OtherBean {
            /**
             * is_online_offline : 支持线上线下
             * is_voucher : 红包活动
             * is_recruit : 0
             * commission : 45545.00
             */

            private String is_online_offline;
            private String is_voucher;
            private int is_recruit;
            private String commission;

            public String getIs_online_offline() {
                return is_online_offline;
            }

            public void setIs_online_offline(String is_online_offline) {
                this.is_online_offline = is_online_offline;
            }

            public String getIs_voucher() {
                return is_voucher;
            }

            public void setIs_voucher(String is_voucher) {
                this.is_voucher = is_voucher;
            }

            public int getIs_recruit() {
                return is_recruit;
            }

            public void setIs_recruit(int is_recruit) {
                this.is_recruit = is_recruit;
            }

            public String getCommission() {
                return commission;
            }

            public void setCommission(String commission) {
                this.commission = commission;
            }
        }

        public static class Redpacket{
            /**
             * voucher_id : 205
             * voucher_price : 0.10
             * title : 进店有礼
             * desc : 恭喜获得店铺发给您的店铺红包
             * use_desc : 现金红包可在购买商品时使用
             */
            private int voucher_id;
            private String voucher_price;
            private String title;
            private String desc;
            private String use_desc;

            public int getVoucher_id() {
                return voucher_id;
            }

            public void setVoucher_id(int voucher_id) {
                this.voucher_id = voucher_id;
            }

            public String getVoucher_price() {
                return voucher_price;
            }

            public void setVoucher_price(String voucher_price) {
                this.voucher_price = voucher_price;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getUse_desc() {
                return use_desc;
            }

            public void setUse_desc(String use_desc) {
                this.use_desc = use_desc;
            }
        }

        public static class CouponListBean {
            /**
             * voucher_id : 73
             * voucher_price : 10.00
             * voucher_limit : 50.00
             * place_limit : 1
             * is_get : 0
             */

            private int voucher_id;
            private String voucher_price;
            private String voucher_limit;
            private int place_limit;
            private int is_get;

            public int getVoucher_id() {
                return voucher_id;
            }

            public void setVoucher_id(int voucher_id) {
                this.voucher_id = voucher_id;
            }

            public String getVoucher_price() {
                return voucher_price;
            }

            public void setVoucher_price(String voucher_price) {
                this.voucher_price = voucher_price;
            }

            public String getVoucher_limit() {
                return voucher_limit;
            }

            public void setVoucher_limit(String voucher_limit) {
                this.voucher_limit = voucher_limit;
            }

            public int getPlace_limit() {
                return place_limit;
            }

            public void setPlace_limit(int place_limit) {
                this.place_limit = place_limit;
            }

            public int getIs_get() {
                return is_get;
            }

            public void setIs_get(int is_get) {
                this.is_get = is_get;
            }
        }
    }

}
