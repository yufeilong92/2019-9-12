package com.tsyc.tianshengyoucai.ui.activity;

import java.util.List;

/**
 * author：cxd
 * CreateTime：2019/7/18
 * File description：
 */
public class normal {

    /**
     * code : 200
     * message :
     * result : {"geval_number":0,"goods_hair_info":{"content":"免运费"},"goods_info":{"commission":0,"detail_images":["http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123962112.png","http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123931278.png","http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123980332.png"],"goods_attr":[{"name":"颜色","value":"红色"},{"name":"大小","value":"12"},{"name":"尺寸","value":"18"}],"goods_detail":"商品描述一下看看详情，不要说话，不要说哈，不要说话","goods_id":563,"goods_images":["http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123990167.png","http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123957661.png","http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123946432.png","http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123942064.png"],"goods_name":"新版商品","goods_price":"8.80","goods_salenum":"月销0笔","goods_storage":100,"mobile_body":"<p style='line-height: 16px;font-size: 14px;padding: 10px'>商品描述一下看看详情，不要说话，不要说哈，不要说话<\/p><img src='http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123962112.png' alt='' style='width: 100%;vertical-align: bottom;'/><img src='http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123931278.png' alt='' style='width: 100%;vertical-align: bottom;'/><img src='http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123980332.png' alt='' style='width: 100%;vertical-align: bottom;'/>","producer":"浙江省 温州市","sharing_returns":""},"is_collect":0,"spec_list":[{"data":["小","中","大"],"name":"大小","select":"小"}],"store_id":18}
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
         * geval_number : 0
         * goods_hair_info : {"content":"免运费"}
         * goods_info : {"commission":0,"detail_images":["http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123962112.png","http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123931278.png","http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123980332.png"],"goods_attr":[{"name":"颜色","value":"红色"},{"name":"大小","value":"12"},{"name":"尺寸","value":"18"}],"goods_detail":"商品描述一下看看详情，不要说话，不要说哈，不要说话","goods_id":563,"goods_images":["http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123990167.png","http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123957661.png","http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123946432.png","http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123942064.png"],"goods_name":"新版商品","goods_price":"8.80","goods_salenum":"月销0笔","goods_storage":100,"mobile_body":"<p style='line-height: 16px;font-size: 14px;padding: 10px'>商品描述一下看看详情，不要说话，不要说哈，不要说话<\/p><img src='http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123962112.png' alt='' style='width: 100%;vertical-align: bottom;'/><img src='http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123931278.png' alt='' style='width: 100%;vertical-align: bottom;'/><img src='http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123980332.png' alt='' style='width: 100%;vertical-align: bottom;'/>","producer":"浙江省 温州市","sharing_returns":""}
         * is_collect : 0
         * spec_list : [{"data":["小","中","大"],"name":"大小","select":"小"}]
         * store_id : 18
         */

        private int geval_number;
        private GoodsHairInfoBean goods_hair_info;
        private GoodsInfoBean goods_info;
        private int is_collect;
        private int store_id;
        private List<SpecListBean> spec_list;

        public int getGeval_number() {
            return geval_number;
        }

        public void setGeval_number(int geval_number) {
            this.geval_number = geval_number;
        }

        public GoodsHairInfoBean getGoods_hair_info() {
            return goods_hair_info;
        }

        public void setGoods_hair_info(GoodsHairInfoBean goods_hair_info) {
            this.goods_hair_info = goods_hair_info;
        }

        public GoodsInfoBean getGoods_info() {
            return goods_info;
        }

        public void setGoods_info(GoodsInfoBean goods_info) {
            this.goods_info = goods_info;
        }

        public int getIs_collect() {
            return is_collect;
        }

        public void setIs_collect(int is_collect) {
            this.is_collect = is_collect;
        }

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public List<SpecListBean> getSpec_list() {
            return spec_list;
        }

        public void setSpec_list(List<SpecListBean> spec_list) {
            this.spec_list = spec_list;
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

        public static class GoodsInfoBean {
            /**
             * commission : 0
             * detail_images : ["http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123962112.png","http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123931278.png","http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123980332.png"]
             * goods_attr : [{"name":"颜色","value":"红色"},{"name":"大小","value":"12"},{"name":"尺寸","value":"18"}]
             * goods_detail : 商品描述一下看看详情，不要说话，不要说哈，不要说话
             * goods_id : 563
             * goods_images : ["http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123990167.png","http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123957661.png","http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123946432.png","http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123942064.png"]
             * goods_name : 新版商品
             * goods_price : 8.80
             * goods_salenum : 月销0笔
             * goods_storage : 100
             * mobile_body : <p style='line-height: 16px;font-size: 14px;padding: 10px'>商品描述一下看看详情，不要说话，不要说哈，不要说话</p><img src='http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123962112.png' alt='' style='width: 100%;vertical-align: bottom;'/><img src='http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123931278.png' alt='' style='width: 100%;vertical-align: bottom;'/><img src='http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123980332.png' alt='' style='width: 100%;vertical-align: bottom;'/>
             * producer : 浙江省 温州市
             * sharing_returns :
             */

            private int commission;
            private String goods_detail;
            private int goods_id;
            private String goods_name;
            private String goods_price;
            private String goods_salenum;
            private int goods_storage;
            private String mobile_body;
            private String producer;
            private String sharing_returns;
            private List<String> detail_images;
            private List<GoodsAttrBean> goods_attr;
            private List<String> goods_images;

            public int getCommission() {
                return commission;
            }

            public void setCommission(int commission) {
                this.commission = commission;
            }

            public String getGoods_detail() {
                return goods_detail;
            }

            public void setGoods_detail(String goods_detail) {
                this.goods_detail = goods_detail;
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

            public String getGoods_salenum() {
                return goods_salenum;
            }

            public void setGoods_salenum(String goods_salenum) {
                this.goods_salenum = goods_salenum;
            }

            public int getGoods_storage() {
                return goods_storage;
            }

            public void setGoods_storage(int goods_storage) {
                this.goods_storage = goods_storage;
            }

            public String getMobile_body() {
                return mobile_body;
            }

            public void setMobile_body(String mobile_body) {
                this.mobile_body = mobile_body;
            }

            public String getProducer() {
                return producer;
            }

            public void setProducer(String producer) {
                this.producer = producer;
            }

            public String getSharing_returns() {
                return sharing_returns;
            }

            public void setSharing_returns(String sharing_returns) {
                this.sharing_returns = sharing_returns;
            }

            public List<String> getDetail_images() {
                return detail_images;
            }

            public void setDetail_images(List<String> detail_images) {
                this.detail_images = detail_images;
            }

            public List<GoodsAttrBean> getGoods_attr() {
                return goods_attr;
            }

            public void setGoods_attr(List<GoodsAttrBean> goods_attr) {
                this.goods_attr = goods_attr;
            }

            public List<String> getGoods_images() {
                return goods_images;
            }

            public void setGoods_images(List<String> goods_images) {
                this.goods_images = goods_images;
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
        }

        public static class SpecListBean {
            /**
             * data : ["小","中","大"]
             * name : 大小
             * select : 小
             */

            private String name;
            private String select;
            private List<String> data;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSelect() {
                return select;
            }

            public void setSelect(String select) {
                this.select = select;
            }

            public List<String> getData() {
                return data;
            }

            public void setData(List<String> data) {
                this.data = data;
            }
        }
    }
}
