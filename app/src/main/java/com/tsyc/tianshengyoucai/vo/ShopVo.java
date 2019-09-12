package com.tsyc.tianshengyoucai.vo;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/10 16:48
 * @Purpose :店铺vo
 */
public class ShopVo {
    /**
     * store_id : 1
     * store_name : 测试店铺
     * sc_id : 0
     * area_info : null
     * store_address : null
     * store_avatar : 店铺头像
     * store_credit : 0
     * store_zy : null
     * share_commission :
     * other : {"is_online_offline":"支持线上线下","is_voucher":""}
     */

    private int store_id;
    private String store_name;
    private int sc_id;
    private String area_info;
    private String store_address;
    private String store_avatar;
    private int store_credit;
    private String store_zy;
    private String share_commission;
    private ShopVo.OtherBean other;

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

    public int getSc_id() {
        return sc_id;
    }

    public void setSc_id(int sc_id) {
        this.sc_id = sc_id;
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

    public String getStore_avatar() {
        return store_avatar;
    }

    public void setStore_avatar(String store_avatar) {
        this.store_avatar = store_avatar;
    }

    public int getStore_credit() {
        return store_credit;
    }

    public void setStore_credit(int store_credit) {
        this.store_credit = store_credit;
    }

    public String getStore_zy() {
        return store_zy;
    }

    public void setStore_zy(String store_zy) {
        this.store_zy = store_zy;
    }

    public String getShare_commission() {
        return share_commission;
    }

    public void setShare_commission(String share_commission) {
        this.share_commission = share_commission;
    }

    public ShopVo.OtherBean getOther() {
        return other;
    }

    public void setOther(ShopVo.OtherBean other) {
        this.other = other;
    }
    public static class OtherBean {
        /**
         * is_online_offline : 支持线上线下
         * is_voucher :
         */

        private String is_online_offline;
        private String is_voucher;
        private String category_tab;
        private String commission;

        public String getCommission() {
            return commission;
        }

        public void setCommission(String commission) {
            this.commission = commission;
        }

        public String getCategory_tab() {
            return category_tab;
        }

        public void setCategory_tab(String category_tab) {
            this.category_tab = category_tab;
        }

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
    }
}
