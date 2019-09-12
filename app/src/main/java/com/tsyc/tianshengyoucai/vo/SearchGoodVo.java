package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/13 10:29
 * @Purpose :搜索商品vo
 */
public class SearchGoodVo extends NormalBean {

    /**
     * result : {"current_page":1,"data":[{"commission_money":"","commission_text":"佣金","commission_type":"返","goods_id":202,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071910240941270.jpg","goods_name":"花样游泳队指定 新面孔金眼霜 淡皱纹,隐眼袋,熬夜眼霜女","goods_price":"15.80","goods_salenum":0,"inner_price":"0.00","is_commission":0,"salenum_text":"月销","share_text":"分享消费返","store_id":1,"commission":"1.00"},{"commission_money":"","commission_text":"佣金","commission_type":"返","goods_id":203,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":"148.00","goods_salenum":0,"inner_price":"108.00","is_commission":0,"salenum_text":"月销","share_text":"分享消费返","store_id":1},{"commission":"1.00","commission_money":"￥1","commission_text":"佣金","commission_type":"返","goods_id":204,"goods_image":"http://tsyc.jiefutong.net/uploads/home/common/default_goods_image.gif","goods_name":"测试商品","goods_price":"1.23","goods_salenum":0,"inner_price":"0.00","is_commission":1,"salenum_text":"月销","share_text":"分享消费返","store_id":4},{"commission":"0.50","commission_money":"￥0.5","commission_text":"佣金","commission_type":"返","goods_id":224,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/19_2019071921051882032.jpg","goods_name":"美工","goods_price":"10.00","goods_salenum":0,"inner_price":"0.00","is_commission":1,"salenum_text":"月销","share_text":"分享消费返","store_id":13},{"commission":"1.00","commission_money":"￥1","commission_text":"佣金","commission_type":"返","goods_id":225,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/19_2019072208581353526.jpg","goods_name":"美工设计","goods_price":"15.00","goods_salenum":0,"inner_price":"0.00","is_commission":1,"salenum_text":"月销","share_text":"分享消费返","store_id":13},{"commission":"2000.00","commission_money":"￥2000","commission_text":"佣金","commission_type":"返","goods_id":226,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/20_2019072209033615242.jpg","goods_name":"KAWAI钢琴","goods_price":"58880.00","goods_salenum":0,"inner_price":"0.00","is_commission":1,"salenum_text":"月销","share_text":"分享消费返","store_id":15},{"commission":"2000.00","commission_money":"￥2000","commission_text":"佣金","commission_type":"返","goods_id":227,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/20_2019072209060860866.jpg","goods_name":"KEYSTONE钢琴","goods_price":"68888.00","goods_salenum":0,"inner_price":"0.00","is_commission":1,"salenum_text":"月销","share_text":"分享消费返","store_id":15},{"commission":"2000.00","commission_money":"￥2000","commission_text":"佣金","commission_type":"返","goods_id":228,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/20_2019072209113399638.jpg","goods_name":"Reanl Riven钢琴","goods_price":"86000.00","goods_salenum":0,"inner_price":"0.00","is_commission":1,"salenum_text":"月销","share_text":"分享消费返","store_id":15},{"commission":"99.00","commission_money":"￥99","commission_text":"佣金","commission_type":"返","goods_id":229,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/26_2019072209223689094.jpg","goods_name":"珍藏级酒杯","goods_price":"99999.00","goods_salenum":0,"inner_price":"0.00","is_commission":1,"salenum_text":"月销","share_text":"分享消费返","store_id":14},{"commission":"1.00","commission_money":"￥1","commission_text":"佣金","commission_type":"返","goods_id":232,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/8_2019072214170874785.jpg","goods_name":"测试","goods_price":"100.00","goods_salenum":0,"inner_price":"0.00","is_commission":1,"salenum_text":"月销","share_text":"分享消费返","store_id":10},{"commission":"10.00","commission_money":"￥10","commission_text":"佣金","commission_type":"返","goods_id":233,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/18_2019072515274763156.jpg","goods_name":"小朋友","goods_price":"199.00","goods_salenum":0,"inner_price":"0.00","is_commission":1,"salenum_text":"月销","share_text":"分享消费返","store_id":16},{"commission":"10.00","commission_money":"￥10","commission_text":"佣金","commission_type":"返","goods_id":234,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/18_2019072915210693737.jpg","goods_name":"飞机","goods_price":"198.00","goods_salenum":0,"inner_price":"0.00","is_commission":1,"salenum_text":"月销","share_text":"分享消费返","store_id":16},{"commission":"10.00","commission_money":"￥10","commission_text":"佣金","commission_type":"返","goods_id":235,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019072919542219837.jpg","goods_name":"推油按摩","goods_price":"598.00","goods_salenum":0,"inner_price":"0.00","is_commission":1,"salenum_text":"月销","share_text":"分享消费返","store_id":9},{"commission":"1.00","commission_money":"￥1","commission_text":"佣金","commission_type":"返","goods_id":236,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/16_2019073022083588588.jpg","goods_name":"衣服 尺寸 xxl","goods_price":"50.00","goods_salenum":0,"inner_price":"0.00","is_commission":1,"salenum_text":"月销","share_text":"分享消费返","store_id":12},{"commission":"20.00","commission_money":"￥20","commission_text":"佣金","commission_type":"返","goods_id":238,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019073117031639898.jpg","goods_name":"NIKE SIZE 41cm","goods_price":"599.00","goods_salenum":0,"inner_price":"0.00","is_commission":1,"salenum_text":"月销","share_text":"分享消费返","store_id":9},{"commission":"10.00","commission_money":"￥10","commission_text":"佣金","commission_type":"返","goods_id":248,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019073117195994674.jpg","goods_name":"AnTa SIZE 41cm","goods_price":"299.00","goods_salenum":0,"inner_price":"0.00","is_commission":1,"salenum_text":"月销","share_text":"分享消费返","store_id":9},{"commission_money":"","commission_text":"佣金","commission_type":"返","goods_id":250,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019080112002796773.jpg","goods_name":"夏季新款女装真丝欧根纱圆领植绒印花无袖假两件宽松套头连衣长裙 样式1 红色 L","goods_price":"0.01","goods_salenum":0,"inner_price":"0.00","is_commission":0,"salenum_text":"月销","share_text":"分享消费返","store_id":1},{"commission_money":"","commission_text":"佣金","commission_type":"返","goods_id":258,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/10/10_2019080815033821374.png","goods_name":"测试商品","goods_price":"50.00","goods_salenum":0,"inner_price":"0.00","is_commission":0,"salenum_text":"月销","share_text":"分享消费返","store_id":10},{"commission":"0.00","commission_money":"￥0","commission_text":"佣金","commission_type":"返","goods_id":270,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201908/15_2019080919014365900.jpg","goods_name":"CSCS","goods_price":"0.01","goods_salenum":0,"inner_price":"0.00","is_commission":1,"salenum_text":"月销","share_text":"分享消费返","store_id":9},{"commission":"0.01","commission_money":"￥0.01","commission_text":"佣金","commission_type":"返","goods_id":277,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201908/26_2019081009105532812.jpg","goods_name":"蛋挞","goods_price":"0.10","goods_salenum":0,"inner_price":"0.10","is_commission":1,"salenum_text":"月销","share_text":"分享消费返","store_id":14}],"last_page":2,"per_page":20,"total":23}
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
         * current_page : 1
         * data : [{"commission_money":"","commission_text":"佣金","commission_type":"返","goods_id":202,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071910240941270.jpg","goods_name":"花样游泳队指定 新面孔金眼霜 淡皱纹,隐眼袋,熬夜眼霜女","goods_price":"15.80","goods_salenum":0,"inner_price":"0.00","is_commission":0,"salenum_text":"月销","share_text":"分享消费返","store_id":1},{"commission_money":"","commission_text":"佣金","commission_type":"返","goods_id":203,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":"148.00","goods_salenum":0,"inner_price":"108.00","is_commission":0,"salenum_text":"月销","share_text":"分享消费返","store_id":1},{"commission":"1.00","commission_money":"￥1","commission_text":"佣金","commission_type":"返","goods_id":204,"goods_image":"http://tsyc.jiefutong.net/uploads/home/common/default_goods_image.gif","goods_name":"测试商品","goods_price":"1.23","goods_salenum":0,"inner_price":"0.00","is_commission":1,"salenum_text":"月销","share_text":"分享消费返","store_id":4},{"commission":"0.50","commission_money":"￥0.5","commission_text":"佣金","commission_type":"返","goods_id":224,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/19_2019071921051882032.jpg","goods_name":"美工","goods_price":"10.00","goods_salenum":0,"inner_price":"0.00","is_commission":1,"salenum_text":"月销","share_text":"分享消费返","store_id":13},{"commission":"1.00","commission_money":"￥1","commission_text":"佣金","commission_type":"返","goods_id":225,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/19_2019072208581353526.jpg","goods_name":"美工设计","goods_price":"15.00","goods_salenum":0,"inner_price":"0.00","is_commission":1,"salenum_text":"月销","share_text":"分享消费返","store_id":13},{"commission":"2000.00","commission_money":"￥2000","commission_text":"佣金","commission_type":"返","goods_id":226,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/20_2019072209033615242.jpg","goods_name":"KAWAI钢琴","goods_price":"58880.00","goods_salenum":0,"inner_price":"0.00","is_commission":1,"salenum_text":"月销","share_text":"分享消费返","store_id":15},{"commission":"2000.00","commission_money":"￥2000","commission_text":"佣金","commission_type":"返","goods_id":227,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/20_2019072209060860866.jpg","goods_name":"KEYSTONE钢琴","goods_price":"68888.00","goods_salenum":0,"inner_price":"0.00","is_commission":1,"salenum_text":"月销","share_text":"分享消费返","store_id":15},{"commission":"2000.00","commission_money":"￥2000","commission_text":"佣金","commission_type":"返","goods_id":228,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/20_2019072209113399638.jpg","goods_name":"Reanl Riven钢琴","goods_price":"86000.00","goods_salenum":0,"inner_price":"0.00","is_commission":1,"salenum_text":"月销","share_text":"分享消费返","store_id":15},{"commission":"99.00","commission_money":"￥99","commission_text":"佣金","commission_type":"返","goods_id":229,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/26_2019072209223689094.jpg","goods_name":"珍藏级酒杯","goods_price":"99999.00","goods_salenum":0,"inner_price":"0.00","is_commission":1,"salenum_text":"月销","share_text":"分享消费返","store_id":14},{"commission":"1.00","commission_money":"￥1","commission_text":"佣金","commission_type":"返","goods_id":232,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/8_2019072214170874785.jpg","goods_name":"测试","goods_price":"100.00","goods_salenum":0,"inner_price":"0.00","is_commission":1,"salenum_text":"月销","share_text":"分享消费返","store_id":10},{"commission":"10.00","commission_money":"￥10","commission_text":"佣金","commission_type":"返","goods_id":233,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/18_2019072515274763156.jpg","goods_name":"小朋友","goods_price":"199.00","goods_salenum":0,"inner_price":"0.00","is_commission":1,"salenum_text":"月销","share_text":"分享消费返","store_id":16},{"commission":"10.00","commission_money":"￥10","commission_text":"佣金","commission_type":"返","goods_id":234,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/18_2019072915210693737.jpg","goods_name":"飞机","goods_price":"198.00","goods_salenum":0,"inner_price":"0.00","is_commission":1,"salenum_text":"月销","share_text":"分享消费返","store_id":16},{"commission":"10.00","commission_money":"￥10","commission_text":"佣金","commission_type":"返","goods_id":235,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019072919542219837.jpg","goods_name":"推油按摩","goods_price":"598.00","goods_salenum":0,"inner_price":"0.00","is_commission":1,"salenum_text":"月销","share_text":"分享消费返","store_id":9},{"commission":"1.00","commission_money":"￥1","commission_text":"佣金","commission_type":"返","goods_id":236,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/16_2019073022083588588.jpg","goods_name":"衣服 尺寸 xxl","goods_price":"50.00","goods_salenum":0,"inner_price":"0.00","is_commission":1,"salenum_text":"月销","share_text":"分享消费返","store_id":12},{"commission":"20.00","commission_money":"￥20","commission_text":"佣金","commission_type":"返","goods_id":238,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019073117031639898.jpg","goods_name":"NIKE SIZE 41cm","goods_price":"599.00","goods_salenum":0,"inner_price":"0.00","is_commission":1,"salenum_text":"月销","share_text":"分享消费返","store_id":9},{"commission":"10.00","commission_money":"￥10","commission_text":"佣金","commission_type":"返","goods_id":248,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019073117195994674.jpg","goods_name":"AnTa SIZE 41cm","goods_price":"299.00","goods_salenum":0,"inner_price":"0.00","is_commission":1,"salenum_text":"月销","share_text":"分享消费返","store_id":9},{"commission_money":"","commission_text":"佣金","commission_type":"返","goods_id":250,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019080112002796773.jpg","goods_name":"夏季新款女装真丝欧根纱圆领植绒印花无袖假两件宽松套头连衣长裙 样式1 红色 L","goods_price":"0.01","goods_salenum":0,"inner_price":"0.00","is_commission":0,"salenum_text":"月销","share_text":"分享消费返","store_id":1},{"commission_money":"","commission_text":"佣金","commission_type":"返","goods_id":258,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/10/10_2019080815033821374.png","goods_name":"测试商品","goods_price":"50.00","goods_salenum":0,"inner_price":"0.00","is_commission":0,"salenum_text":"月销","share_text":"分享消费返","store_id":10},{"commission":"0.00","commission_money":"￥0","commission_text":"佣金","commission_type":"返","goods_id":270,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201908/15_2019080919014365900.jpg","goods_name":"CSCS","goods_price":"0.01","goods_salenum":0,"inner_price":"0.00","is_commission":1,"salenum_text":"月销","share_text":"分享消费返","store_id":9},{"commission":"0.01","commission_money":"￥0.01","commission_text":"佣金","commission_type":"返","goods_id":277,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201908/26_2019081009105532812.jpg","goods_name":"蛋挞","goods_price":"0.10","goods_salenum":0,"inner_price":"0.10","is_commission":1,"salenum_text":"月销","share_text":"分享消费返","store_id":14}]
         * last_page : 2
         * per_page : 20
         * total : 23
         */

        private int current_page;
        private int last_page;
        private int per_page;
        private int total;
        private List<DataBean> data;

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

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * commission_money :
             * commission_text : 佣金
             * commission_type : 返
             * goods_id : 202
             * goods_image : http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071910240941270.jpg
             * goods_name : 花样游泳队指定 新面孔金眼霜 淡皱纹,隐眼袋,熬夜眼霜女
             * goods_price : 15.80
             * goods_salenum : 0
             * inner_price : 0.00
             * is_commission : 0
             * salenum_text : 月销
             * share_text : 分享消费返
             * store_id : 1
             * commission : 1.00
             */

            private String commission_money;
            private String commission_text;
            private String commission_type;
            private int goods_id;
            private String goods_image;
            private String goods_name;
            private String goods_price;
            private int goods_salenum;
            private String inner_price;
            private int is_commission;
            private String salenum_text;
            private String share_text;
            private int store_id;
            private String commission;

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

            public String getCommission_type() {
                return commission_type;
            }

            public void setCommission_type(String commission_type) {
                this.commission_type = commission_type;
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

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public int getGoods_salenum() {
                return goods_salenum;
            }

            public void setGoods_salenum(int goods_salenum) {
                this.goods_salenum = goods_salenum;
            }

            public String getInner_price() {
                return inner_price;
            }

            public void setInner_price(String inner_price) {
                this.inner_price = inner_price;
            }

            public int getIs_commission() {
                return is_commission;
            }

            public void setIs_commission(int is_commission) {
                this.is_commission = is_commission;
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

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public String getCommission() {
                return commission;
            }

            public void setCommission(String commission) {
                this.commission = commission;
            }
        }
    }
}
