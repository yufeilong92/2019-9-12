package com.tsyc.tianshengyoucai.model.bean;

import com.tsyc.tianshengyoucai.ui.activity.normal;
import com.tsyc.tianshengyoucai.vo.GoodSpecification;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/25
 * File description： 商品详情 bean
 */
public class GoodsDetailBean {


    /**
     * code : 200
     * result : {"goods_info":{"goods_id":202,"goods_images":["http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071910240941270.jpg","http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071910244339819.jpg"],"goods_name":"花样游泳队指定 新面孔金眼霜 淡皱纹,隐眼袋,熬夜眼霜女","goods_price":"15.80","goods_storage":1000,"goods_salenum":"月销0笔","commission":0,"sharing_returns":"分享消费返","goods_detail":""},"goods_hair_info":{"content":"免运费"},"spec_list":[],"goods_eval_list":[],"goods_evaluate_info":{"good_star":5,"geval_content":"评价内容","geval_number":100,"description":"2019-06-21 评价副标题","nickname":"评价人昵称","avatar":"http://tsyc.jiefutong.net/static/store/images/degault.png"}}
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
         * goods_info : {"goods_id":202,"goods_images":["http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071910240941270.jpg","http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071910244339819.jpg"],"goods_name":"花样游泳队指定 新面孔金眼霜 淡皱纹,隐眼袋,熬夜眼霜女","goods_price":"15.80","goods_storage":1000,"goods_salenum":"月销0笔","commission":0,"sharing_returns":"分享消费返","goods_detail":""}
         * goods_hair_info : {"content":"免运费"}
         * spec_list : []
         * goods_eval_list : []
         * goods_evaluate_info : {"good_star":5,"geval_content":"评价内容","geval_number":100,"description":"2019-06-21 评价副标题","nickname":"评价人昵称","avatar":"http://tsyc.jiefutong.net/static/store/images/degault.png"}
         */

        private GoodsInfoBean goods_info;
        private GoodsHairInfoBean goods_hair_info;
        private GoodsEvaluateInfoBean goods_evaluate_info;
        private List<GoodSpecification> spec_list;

        private List<?> goods_eval_list;
        //收藏
        private int is_collect;
        //店铺id
        private int store_id;

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public int getIs_collect() {
            return is_collect;
        }

        public void setIs_collect(int is_collect) {
            this.is_collect = is_collect;
        }

        public GoodsInfoBean getGoods_info() {
            return goods_info;
        }

        public void setGoods_info(GoodsInfoBean goods_info) {
            this.goods_info = goods_info;
        }

        public GoodsHairInfoBean getGoods_hair_info() {
            return goods_hair_info;
        }

        public void setGoods_hair_info(GoodsHairInfoBean goods_hair_info) {
            this.goods_hair_info = goods_hair_info;
        }

        public GoodsEvaluateInfoBean getGoods_evaluate_info() {
            return goods_evaluate_info;
        }

        public void setGoods_evaluate_info(GoodsEvaluateInfoBean goods_evaluate_info) {
            this.goods_evaluate_info = goods_evaluate_info;
        }

        public List<GoodSpecification> getSpec_list() {
            return spec_list;
        }

        public void setSpec_list(List<GoodSpecification> spec_list) {
            this.spec_list = spec_list;
        }

        public List<?> getGoods_eval_list() {
            return goods_eval_list;
        }

        public void setGoods_eval_list(List<?> goods_eval_list) {
            this.goods_eval_list = goods_eval_list;
        }

        public static class GoodsInfoBean {
            /**
             * goods_id : 202
             * goods_images : ["http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071910240941270.jpg","http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071910244339819.jpg"]
             * goods_name : 花样游泳队指定 新面孔金眼霜 淡皱纹,隐眼袋,熬夜眼霜女
             * goods_price : 15.80
             * goods_storage : 1000
             * goods_salenum : 月销0笔
             * commission : 0
             * sharing_returns : 分享消费返
             * goods_detail :
             * mobile_body : 富文本
             * producer 产地
             * goods_attr 参数
             */

            private int goods_id;
            private String goods_name;
            private String goods_price;
            private int goods_storage;
            private String goods_salenum;
            private int commission;
            private String sharing_returns;
            private String goods_detail;
            private String mobile_body;
            private String producer;
            private List<String> goods_images;
            private List<String> detail_images;
            private List<GoodsAttrBean> goods_attr;

            public String getProducer() {
                return producer;
            }

            public void setProducer(String producer) {
                this.producer = producer;
            }

            public List<GoodsAttrBean> getGoods_attr() {
                return goods_attr;
            }

            public void setGoods_attr(List<GoodsAttrBean> goods_attr) {
                this.goods_attr = goods_attr;
            }

            public String getMobile_body() {
                return mobile_body;
            }

            public void setMobile_body(String mobile_body) {
                this.mobile_body = mobile_body;
            }

            public List<String> getDetail_images() {
                return detail_images;
            }

            public void setDetail_images(List<String> detail_images) {
                this.detail_images = detail_images;
            }

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

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public int getGoods_storage() {
                return goods_storage;
            }

            public void setGoods_storage(int goods_storage) {
                this.goods_storage = goods_storage;
            }

            public String getGoods_salenum() {
                return goods_salenum;
            }

            public void setGoods_salenum(String goods_salenum) {
                this.goods_salenum = goods_salenum;
            }

            public int getCommission() {
                return commission;
            }

            public void setCommission(int commission) {
                this.commission = commission;
            }

            public String getSharing_returns() {
                return sharing_returns;
            }

            public void setSharing_returns(String sharing_returns) {
                this.sharing_returns = sharing_returns;
            }

            public String getGoods_detail() {
                return goods_detail;
            }

            public void setGoods_detail(String goods_detail) {
                this.goods_detail = goods_detail;
            }

            public List<String> getGoods_images() {
                return goods_images;
            }

            public void setGoods_images(List<String> goods_images) {
                this.goods_images = goods_images;
            }


        }
        public static class GoodsAttrBean {
            /**
             * name : 颜色
             * value : 红色
             */

            private String name;
            private String value;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class GoodsHairInfoBean {
            /**
             * content : 免运费
             */

            private String content;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }

        public static class GoodsEvaluateInfoBean {
            /**
             * good_star : 5
             * geval_content : 评价内容
             * geval_number : 100
             * description : 2019-06-21 评价副标题
             * nickname : 评价人昵称
             * avatar : http://tsyc.jiefutong.net/static/store/images/degault.png
             */

            private int good_star;
            private String geval_content;
            private int geval_number;
            private String description;
            private String nickname;
            private String avatar;

            public int getGood_star() {
                return good_star;
            }

            public void setGood_star(int good_star) {
                this.good_star = good_star;
            }

            public String getGeval_content() {
                return geval_content;
            }

            public void setGeval_content(String geval_content) {
                this.geval_content = geval_content;
            }

            public int getGeval_number() {
                return geval_number;
            }

            public void setGeval_number(int geval_number) {
                this.geval_number = geval_number;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }
        }
    }
}
