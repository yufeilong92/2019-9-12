package com.tsyc.tianshengyoucai.model.bean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/26
 * File description： 发现页数据
 */
public class FindBean {


    /**
     * code : 200
     * result : {"total":14,"per_page":20,"current_page":1,"last_page":1,"data":[{"goods_id":202,"store_id":1,"goods_name":"花样游泳队指定 新面孔金眼霜 淡皱纹,隐眼袋,熬夜眼霜女","goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071910240941270.jpg","goods_salenum":0,"goods_price":"15.80","commission":null,"salenum_text":"月销","share_text":"分享消费赚","commission_type":"返","commission_money":"","commission_text":"佣金","is_commission":0},{"goods_id":203,"store_id":1,"goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg","goods_salenum":0,"goods_price":"148.00","commission":null,"salenum_text":"月销","share_text":"分享消费赚","commission_type":"返","commission_money":"","commission_text":"佣金","is_commission":0},{"goods_id":204,"store_id":4,"goods_name":"测试商品","goods_image":"http://tsyc.jiefutong.net/uploads/home/common/default_goods_image.gif","goods_salenum":0,"goods_price":"1.23","commission":"1.00","salenum_text":"月销","share_text":"分享消费赚","commission_type":"返","commission_money":"￥1","commission_text":"佣金","is_commission":1},{"goods_id":223,"store_id":9,"goods_name":"CC","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019071918534835021.jpg","goods_salenum":0,"goods_price":"1000.00","commission":"10.00","salenum_text":"月销","share_text":"分享消费赚","commission_type":"返","commission_money":"￥10","commission_text":"佣金","is_commission":1},{"goods_id":224,"store_id":13,"goods_name":"美工","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/19_2019071921051882032.jpg","goods_salenum":0,"goods_price":"10.00","commission":"0.50","salenum_text":"月销","share_text":"分享消费赚","commission_type":"返","commission_money":"￥0.5","commission_text":"佣金","is_commission":1},{"goods_id":225,"store_id":13,"goods_name":"美工设计","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/19_2019072208581353526.jpg","goods_salenum":0,"goods_price":"15.00","commission":"1.00","salenum_text":"月销","share_text":"分享消费赚","commission_type":"返","commission_money":"￥1","commission_text":"佣金","is_commission":1},{"goods_id":226,"store_id":15,"goods_name":"KAWAI钢琴","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/20_2019072209033615242.jpg","goods_salenum":0,"goods_price":"58880.00","commission":"2000.00","salenum_text":"月销","share_text":"分享消费赚","commission_type":"返","commission_money":"￥2000","commission_text":"佣金","is_commission":1},{"goods_id":227,"store_id":15,"goods_name":"KEYSTONE钢琴","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/20_2019072209060860866.jpg","goods_salenum":0,"goods_price":"68888.00","commission":"2000.00","salenum_text":"月销","share_text":"分享消费赚","commission_type":"返","commission_money":"￥2000","commission_text":"佣金","is_commission":1},{"goods_id":228,"store_id":15,"goods_name":"Reanl Riven钢琴","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/20_2019072209113399638.jpg","goods_salenum":0,"goods_price":"86000.00","commission":"2000.00","salenum_text":"月销","share_text":"分享消费赚","commission_type":"返","commission_money":"￥2000","commission_text":"佣金","is_commission":1},{"goods_id":229,"store_id":14,"goods_name":"珍藏级酒杯","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/26_2019072209223689094.jpg","goods_salenum":0,"goods_price":"99999.00","commission":"99.00","salenum_text":"月销","share_text":"分享消费赚","commission_type":"返","commission_money":"￥99","commission_text":"佣金","is_commission":1},{"goods_id":230,"store_id":10,"goods_name":"小红书 型号 一连","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/8_2019072214103218585.jpg","goods_salenum":0,"goods_price":"1.00","commission":"0.10","salenum_text":"月销","share_text":"分享消费赚","commission_type":"返","commission_money":"￥0.1","commission_text":"佣金","is_commission":1},{"goods_id":231,"store_id":10,"goods_name":"小红书 型号 二连","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/8_2019072214103218585.jpg","goods_salenum":0,"goods_price":"2.00","commission":"0.10","salenum_text":"月销","share_text":"分享消费赚","commission_type":"返","commission_money":"￥0.1","commission_text":"佣金","is_commission":1},{"goods_id":232,"store_id":10,"goods_name":"测试","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/8_2019072214170874785.jpg","goods_salenum":0,"goods_price":"100.00","commission":"1.00","salenum_text":"月销","share_text":"分享消费赚","commission_type":"返","commission_money":"￥1","commission_text":"佣金","is_commission":1},{"goods_id":233,"store_id":16,"goods_name":"小朋友","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/18_2019072515274763156.jpg","goods_salenum":0,"goods_price":"199.00","commission":"10.00","salenum_text":"月销","share_text":"分享消费赚","commission_type":"返","commission_money":"￥10","commission_text":"佣金","is_commission":1}]}
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
         * total : 14
         * per_page : 20
         * current_page : 1
         * last_page : 1
         * data : [{"goods_id":202,"store_id":1,"goods_name":"花样游泳队指定 新面孔金眼霜 淡皱纹,隐眼袋,熬夜眼霜女","goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071910240941270.jpg","goods_salenum":0,"goods_price":"15.80","commission":null,"salenum_text":"月销","share_text":"分享消费赚","commission_type":"返","commission_money":"","commission_text":"佣金","is_commission":0},{"goods_id":203,"store_id":1,"goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg","goods_salenum":0,"goods_price":"148.00","commission":null,"salenum_text":"月销","share_text":"分享消费赚","commission_type":"返","commission_money":"","commission_text":"佣金","is_commission":0},{"goods_id":204,"store_id":4,"goods_name":"测试商品","goods_image":"http://tsyc.jiefutong.net/uploads/home/common/default_goods_image.gif","goods_salenum":0,"goods_price":"1.23","commission":"1.00","salenum_text":"月销","share_text":"分享消费赚","commission_type":"返","commission_money":"￥1","commission_text":"佣金","is_commission":1},{"goods_id":223,"store_id":9,"goods_name":"CC","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019071918534835021.jpg","goods_salenum":0,"goods_price":"1000.00","commission":"10.00","salenum_text":"月销","share_text":"分享消费赚","commission_type":"返","commission_money":"￥10","commission_text":"佣金","is_commission":1},{"goods_id":224,"store_id":13,"goods_name":"美工","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/19_2019071921051882032.jpg","goods_salenum":0,"goods_price":"10.00","commission":"0.50","salenum_text":"月销","share_text":"分享消费赚","commission_type":"返","commission_money":"￥0.5","commission_text":"佣金","is_commission":1},{"goods_id":225,"store_id":13,"goods_name":"美工设计","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/19_2019072208581353526.jpg","goods_salenum":0,"goods_price":"15.00","commission":"1.00","salenum_text":"月销","share_text":"分享消费赚","commission_type":"返","commission_money":"￥1","commission_text":"佣金","is_commission":1},{"goods_id":226,"store_id":15,"goods_name":"KAWAI钢琴","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/20_2019072209033615242.jpg","goods_salenum":0,"goods_price":"58880.00","commission":"2000.00","salenum_text":"月销","share_text":"分享消费赚","commission_type":"返","commission_money":"￥2000","commission_text":"佣金","is_commission":1},{"goods_id":227,"store_id":15,"goods_name":"KEYSTONE钢琴","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/20_2019072209060860866.jpg","goods_salenum":0,"goods_price":"68888.00","commission":"2000.00","salenum_text":"月销","share_text":"分享消费赚","commission_type":"返","commission_money":"￥2000","commission_text":"佣金","is_commission":1},{"goods_id":228,"store_id":15,"goods_name":"Reanl Riven钢琴","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/20_2019072209113399638.jpg","goods_salenum":0,"goods_price":"86000.00","commission":"2000.00","salenum_text":"月销","share_text":"分享消费赚","commission_type":"返","commission_money":"￥2000","commission_text":"佣金","is_commission":1},{"goods_id":229,"store_id":14,"goods_name":"珍藏级酒杯","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/26_2019072209223689094.jpg","goods_salenum":0,"goods_price":"99999.00","commission":"99.00","salenum_text":"月销","share_text":"分享消费赚","commission_type":"返","commission_money":"￥99","commission_text":"佣金","is_commission":1},{"goods_id":230,"store_id":10,"goods_name":"小红书 型号 一连","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/8_2019072214103218585.jpg","goods_salenum":0,"goods_price":"1.00","commission":"0.10","salenum_text":"月销","share_text":"分享消费赚","commission_type":"返","commission_money":"￥0.1","commission_text":"佣金","is_commission":1},{"goods_id":231,"store_id":10,"goods_name":"小红书 型号 二连","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/8_2019072214103218585.jpg","goods_salenum":0,"goods_price":"2.00","commission":"0.10","salenum_text":"月销","share_text":"分享消费赚","commission_type":"返","commission_money":"￥0.1","commission_text":"佣金","is_commission":1},{"goods_id":232,"store_id":10,"goods_name":"测试","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/8_2019072214170874785.jpg","goods_salenum":0,"goods_price":"100.00","commission":"1.00","salenum_text":"月销","share_text":"分享消费赚","commission_type":"返","commission_money":"￥1","commission_text":"佣金","is_commission":1},{"goods_id":233,"store_id":16,"goods_name":"小朋友","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/18_2019072515274763156.jpg","goods_salenum":0,"goods_price":"199.00","commission":"10.00","salenum_text":"月销","share_text":"分享消费赚","commission_type":"返","commission_money":"￥10","commission_text":"佣金","is_commission":1}]
         */

        private int total;
        private int per_page;
        private int current_page;
        private int last_page;
        private List<DataBean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * goods_id : 202
             * store_id : 1
             * goods_name : 花样游泳队指定 新面孔金眼霜 淡皱纹,隐眼袋,熬夜眼霜女
             * goods_image : http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071910240941270.jpg
             * goods_salenum : 0
             * goods_price : 15.80
             * commission : null
             * salenum_text : 月销
             * share_text : 分享消费赚
             * commission_type : 返
             * commission_money :
             * commission_text : 佣金
             * is_commission : 0
             */

            private int goods_id;
            private int store_id;
            private String goods_name;
            private String goods_image;
            private int goods_salenum;
            private String goods_price;
            private String commission;
            private String salenum_text;
            private String share_text;
            private String commission_type;
            private String commission_money;
            private String commission_text;
            private int is_commission;

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

            public String getCommission() {
                return commission;
            }

            public void setCommission(String commission) {
                this.commission = commission;
            }

            public String getSalenum_text() {
                return salenum_text;
            }

            public void setSalenum_text(String salenum_text) {
                this.salenum_text = salenum_text;
            }

            public String getShare_text() {
                return share_text;
            }

            public void setShare_text(String share_text) {
                this.share_text = share_text;
            }

            public String getCommission_type() {
                return commission_type;
            }

            public void setCommission_type(String commission_type) {
                this.commission_type = commission_type;
            }

            public String getCommission_money() {
                return commission_money;
            }

            public void setCommission_money(String commission_money) {
                this.commission_money = commission_money;
            }

            public String getCommission_text() {
                return commission_text;
            }

            public void setCommission_text(String commission_text) {
                this.commission_text = commission_text;
            }

            public int getIs_commission() {
                return is_commission;
            }

            public void setIs_commission(int is_commission) {
                this.is_commission = is_commission;
            }
        }
    }
}
