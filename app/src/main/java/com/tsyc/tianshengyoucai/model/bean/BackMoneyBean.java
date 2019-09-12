package com.tsyc.tianshengyoucai.model.bean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/10
 * File description：申请退款
 */
public class BackMoneyBean {

    /**
     * code : 200
     * result : {"refund_type":[{"name":"我要退货退款","description":"已收到货，需要退还已收到的货物","img":"http://tsyc.jiefutong.net/uploads/mobile/category/53a973e7cbd2d294a93290a7b93aeb0.png"},{"name":"我要退款(无需退货)","description":"未收到货，或与商家协商后申请","img":"http://tsyc.jiefutong.net/uploads/mobile/category/911dddb7ee50dc645479f0b6bf5490c.png"}],"goods_status":["未收到货","已收到货"],"goods_reason":["拍错了/多拍","未收到货","不喜欢效果不好","材质与商品描述不符合","大小尺寸与商品描述不符合","质量问题"],"money":0.04,"money_info":"¥0.04(实付金额)","mobile":"13673991339"}
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
         * refund_type : [{"name":"我要退货退款","description":"已收到货，需要退还已收到的货物","img":"http://tsyc.jiefutong.net/uploads/mobile/category/53a973e7cbd2d294a93290a7b93aeb0.png"},{"name":"我要退款(无需退货)","description":"未收到货，或与商家协商后申请","img":"http://tsyc.jiefutong.net/uploads/mobile/category/911dddb7ee50dc645479f0b6bf5490c.png"}]
         * goods_status : ["未收到货","已收到货"]
         * goods_reason : ["拍错了/多拍","未收到货","不喜欢效果不好","材质与商品描述不符合","大小尺寸与商品描述不符合","质量问题"]
         * money : 0.04
         * money_info : ¥0.04(实付金额)
         * mobile : 13673991339
         */

        private double money;
        private String money_info;
        private String mobile;
        private List<RefundTypeBean> refund_type;
        private List<String> goods_status;
        private List<String> goods_reason;

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public String getMoney_info() {
            return money_info;
        }

        public void setMoney_info(String money_info) {
            this.money_info = money_info;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public List<RefundTypeBean> getRefund_type() {
            return refund_type;
        }

        public void setRefund_type(List<RefundTypeBean> refund_type) {
            this.refund_type = refund_type;
        }

        public List<String> getGoods_status() {
            return goods_status;
        }

        public void setGoods_status(List<String> goods_status) {
            this.goods_status = goods_status;
        }

        public List<String> getGoods_reason() {
            return goods_reason;
        }

        public void setGoods_reason(List<String> goods_reason) {
            this.goods_reason = goods_reason;
        }

        public static class RefundTypeBean {
            /**
             * name : 我要退货退款
             * description : 已收到货，需要退还已收到的货物
             * img : http://tsyc.jiefutong.net/uploads/mobile/category/53a973e7cbd2d294a93290a7b93aeb0.png
             */

            private String name;
            private String description;
            private String img;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}
