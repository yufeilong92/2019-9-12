package com.tsyc.tianshengyoucai.model.bean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/13
 * File description：商品编辑
 */
public class EditShopBean {

    /**
     * code : 200
     * message :
     * result : {"can_edit_spec":true,"commission":"20.00","detail":"商品描述一下看看详情，不要说话，不要说哈，不要说话","detail_images":["http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123962112.png","http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123931278.png","http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123980332.png"],"gc_id":858,"gc_name":"花草茶","goods_attr":[{"name":"颜色","value":"红色"},{"name":"大小","value":"12"},{"name":"尺寸","value":"18"}],"goods_images":["http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123990167.png","http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123957661.png","http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123946432.png","http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123942064.png"],"goods_name":"新版商品","goods_price":"8.80","goods_stock":300,"goods_templete":[{"name":"证书","value":"123456789987654321"}],"inner_price":"0.00","is_yourself":1,"producer":"浙江省 温州市","spec_list":{"spec_name":"大小","spec_value":[{"goods_id":563,"inner_price":"0.00","price":"8.80","spec_value":"小","stock_num":100},{"goods_id":564,"inner_price":"0.00","price":"18.80","spec_value":"中","stock_num":100},{"goods_id":565,"inner_price":"0.00","price":"28.80","spec_value":"大","stock_num":100}]}}
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
         * can_edit_spec : true
         * commission : 20.00
         * goods_freight : 20.00
         * detail : 商品描述一下看看详情，不要说话，不要说哈，不要说话
         * detail_images : ["http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123962112.png","http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123931278.png","http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123980332.png"]
         * gc_id : 858
         * gc_name : 花草茶
         * goods_attr : [{"name":"颜色","value":"红色"},{"name":"大小","value":"12"},{"name":"尺寸","value":"18"}]
         * goods_images : ["http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123990167.png","http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123957661.png","http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123946432.png","http://tsyc.jiefutong.net/uploads/home/membertag/201908/30_2019082817123942064.png"]
         * goods_name : 新版商品
         * goods_price : 8.80
         * goods_stock : 300
         * goods_templete : [{"name":"证书","value":"123456789987654321"}]
         * inner_price : 0.00
         * is_yourself : 1
         * producer : 浙江省 温州市
         * spec_list : {"spec_name":"大小","spec_value":[{"goods_id":563,"inner_price":"0.00","price":"8.80","spec_value":"小","stock_num":100},{"goods_id":564,"inner_price":"0.00","price":"18.80","spec_value":"中","stock_num":100},{"goods_id":565,"inner_price":"0.00","price":"28.80","spec_value":"大","stock_num":100}]}
         */

        private boolean can_edit_spec;
        private String commission;
        private String detail;
        private int gc_id;
        private String gc_name;
        private String goods_name;
        private String goods_price;
        private String goods_freight;
        private int goods_stock;
        private String inner_price;
        private int is_yourself;
        private String producer;
        private SpecListBean spec_list;
        private List<String> detail_images;
        private List<GoodsAttrBean> goods_attr;
        private List<String> goods_images;
        private List<GoodsTempleteBean> goods_templete;

        public String getGoods_freight() {
            return goods_freight;
        }

        public void setGoods_freight(String goods_freight) {
            this.goods_freight = goods_freight;
        }

        public boolean isCan_edit_spec() {
            return can_edit_spec;
        }

        public void setCan_edit_spec(boolean can_edit_spec) {
            this.can_edit_spec = can_edit_spec;
        }

        public String getCommission() {
            return commission;
        }

        public void setCommission(String commission) {
            this.commission = commission;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public int getGc_id() {
            return gc_id;
        }

        public void setGc_id(int gc_id) {
            this.gc_id = gc_id;
        }

        public String getGc_name() {
            return gc_name;
        }

        public void setGc_name(String gc_name) {
            this.gc_name = gc_name;
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

        public int getGoods_stock() {
            return goods_stock;
        }

        public void setGoods_stock(int goods_stock) {
            this.goods_stock = goods_stock;
        }

        public String getInner_price() {
            return inner_price;
        }

        public void setInner_price(String inner_price) {
            this.inner_price = inner_price;
        }

        public int getIs_yourself() {
            return is_yourself;
        }

        public void setIs_yourself(int is_yourself) {
            this.is_yourself = is_yourself;
        }

        public String getProducer() {
            return producer;
        }

        public void setProducer(String producer) {
            this.producer = producer;
        }

        public SpecListBean getSpec_list() {
            return spec_list;
        }

        public void setSpec_list(SpecListBean spec_list) {
            this.spec_list = spec_list;
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

        public List<GoodsTempleteBean> getGoods_templete() {
            return goods_templete;
        }

        public void setGoods_templete(List<GoodsTempleteBean> goods_templete) {
            this.goods_templete = goods_templete;
        }

        public static class SpecListBean {
            /**
             * spec_name : 大小
             * spec_value : [{"goods_id":563,"inner_price":"0.00","price":"8.80","spec_value":"小","stock_num":100},{"goods_id":564,"inner_price":"0.00","price":"18.80","spec_value":"中","stock_num":100},{"goods_id":565,"inner_price":"0.00","price":"28.80","spec_value":"大","stock_num":100}]
             */

            private String spec_name;
            private List<SpecValueBean> spec_value;

            public String getSpec_name() {
                return spec_name;
            }

            public void setSpec_name(String spec_name) {
                this.spec_name = spec_name;
            }

            public List<SpecValueBean> getSpec_value() {
                return spec_value;
            }

            public void setSpec_value(List<SpecValueBean> spec_value) {
                this.spec_value = spec_value;
            }

            public static class SpecValueBean {
                /**
                 * goods_id : 563
                 * inner_price : 0.00
                 * price : 8.80
                 * spec_value : 小
                 * stock_num : 100
                 */

                private int goods_id;
                private String inner_price;
                private String price;
                private String spec_value;
                private int stock_num;

                public int getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(int goods_id) {
                    this.goods_id = goods_id;
                }

                public String getInner_price() {
                    return inner_price;
                }

                public void setInner_price(String inner_price) {
                    this.inner_price = inner_price;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getSpec_value() {
                    return spec_value;
                }

                public void setSpec_value(String spec_value) {
                    this.spec_value = spec_value;
                }

                public int getStock_num() {
                    return stock_num;
                }

                public void setStock_num(int stock_num) {
                    this.stock_num = stock_num;
                }
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

        public static class GoodsTempleteBean {
            /**
             * name : 证书
             * value : 123456789987654321
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
}
