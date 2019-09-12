package com.tsyc.tianshengyoucai.model.bean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/30
 * File description：商品管理 商品列表bean
 */
public class SMGoodsListBean {

    /**
     * code : 200
     * result : [{"goods_id":233,"goods_name":"夏季新款女装真丝欧根纱圆领植绒印花无袖假两件宽松套头连衣长裙","goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019080112002796773.jpg","stock":1080,"goods_state":1,"goods_price":2198},{"goods_id":192,"goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg","stock":1200,"goods_state":1,"goods_price":148},{"goods_id":191,"goods_name":"花样游泳队指定 新面孔金眼霜 淡皱纹,隐眼袋,熬夜眼霜女","goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071910240941270.jpg","stock":1000,"goods_state":1,"goods_price":15.8}]
     * message :
     */

    private String code;
    private String message;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * goods_id : 233
         * goods_name : 夏季新款女装真丝欧根纱圆领植绒印花无袖假两件宽松套头连衣长裙
         * goods_image : http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019080112002796773.jpg
         * stock : 1080
         * goods_state : 1
         * goods_price : 2198
         */

        private int goods_id;
        private String goods_name;
        private String goods_image;
        private int stock;
        private int goods_state;
        private float  goods_price;

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
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

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public int getGoods_state() {
            return goods_state;
        }

        public void setGoods_state(int goods_state) {
            this.goods_state = goods_state;
        }

        public float getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(float goods_price) {
            this.goods_price = goods_price;
        }
    }
}
