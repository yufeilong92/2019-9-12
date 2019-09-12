package com.tsyc.tianshengyoucai.model.bean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/31
 * File description：评价管理bean
 */
public class EvaluateManageBean {


    /**
     * code : 200
     * result : [{"geval_id":6,"star":"1","user_name":"小小豆","user_logo":"http://tsyc.jiefutong.net/uploads/mobile/category/logo_logo.png","description":"2019-07-16 21:25:44 规格规格","geval_content":"Hftrrrrrrwwweeeffffyyyhgffdfyu","geval_image":["http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019070312060330818.jpg"],"goods_info":{"goods_name":"美食aaaa aaa ddd eeee","goods_price":null,"goods_description":"规格规格","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060317350986964.jpg"},"reply":"你好，感谢你的c支持，我相信我们会做的更好，欢迎下次再来"},{"geval_id":5,"star":"1","user_name":"小小豆","user_logo":"http://tsyc.jiefutong.net/uploads/mobile/category/logo_logo.png","description":"2019-07-02 18:29:19 规格规格","geval_content":"商品不错","geval_image":["http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg","http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg"],"goods_info":{"goods_name":"与牧2017韩版短款 针织 黑 M","goods_price":"56.00","goods_description":"规格规格","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060317350986964.jpg"},"reply":"你好，感谢你的c支持，我相信我们会做的更好，欢迎下次再来"},{"geval_id":4,"star":"1","user_name":"小小豆","user_logo":"http://tsyc.jiefutong.net/uploads/mobile/category/logo_logo.png","description":"2019-07-02 15:28:45 规格规格","geval_content":"商品不错","geval_image":["http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg","http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg"],"goods_info":{"goods_name":"与牧2017韩版短款 针织 黑 M","goods_price":"22.00","goods_description":"规格规格","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060317350986964.jpg"},"reply":"你好，感谢你的c支持，我相信我们会做的更好，欢迎下次再来"},{"geval_id":3,"star":"1","user_name":"小小豆","user_logo":"http://tsyc.jiefutong.net/uploads/mobile/category/logo_logo.png","description":"2019-07-02 15:05:01 规格规格","geval_content":"商品不错","geval_image":["http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg","http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg"],"goods_info":{"goods_name":"与牧2017韩版短款 针织 黑 M","goods_price":"56.00","goods_description":"规格规格","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060317350986964.jpg"},"reply":"你好，感谢你的c支持，我相信我们会做的更好，欢迎下次再来"},{"geval_id":2,"star":"1","user_name":"小小豆","user_logo":"http://tsyc.jiefutong.net/uploads/mobile/category/logo_logo.png","description":"2019-07-02 14:03:39 规格规格","geval_content":"测试数据","geval_image":["http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg","http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg"],"goods_info":{"goods_name":"与牧2017韩版短款 针织 黑 M","goods_price":"34.00","goods_description":"规格规格","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060317350986964.jpg"},"reply":"你好，感谢你的c支持，我相信我们会做的更好，欢迎下次再来"},{"geval_id":1,"star":"1","user_name":"小小豆","user_logo":"http://tsyc.jiefutong.net/uploads/mobile/category/logo_logo.png","description":"2019-07-01 15:18:30 规格规格","geval_content":"商品不错","geval_image":["http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg","http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg"],"goods_info":{"goods_name":"与牧2017韩版短款 针织 黑 M","goods_price":"68.00","goods_description":"规格规格","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060317350986964.jpg"},"reply":"sadasdfasd"}]
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
         * geval_id : 6
         * star : 1
         * user_name : 小小豆
         * user_logo : http://tsyc.jiefutong.net/uploads/mobile/category/logo_logo.png
         * description : 2019-07-16 21:25:44 规格规格
         * geval_content : Hftrrrrrrwwweeeffffyyyhgffdfyu
         * geval_image : ["http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019070312060330818.jpg"]
         * goods_info : {"goods_name":"美食aaaa aaa ddd eeee","goods_price":null,"goods_description":"规格规格","goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060317350986964.jpg"}
         * reply : 你好，感谢你的c支持，我相信我们会做的更好，欢迎下次再来
         */

        private int geval_id;
        private String star;
        private String user_name;
        private String user_logo;
        private String description;
        private String geval_content;
        private GoodsInfoBean goods_info;
        private String reply;
        private List<String> geval_image;

        public int getGeval_id() {
            return geval_id;
        }

        public void setGeval_id(int geval_id) {
            this.geval_id = geval_id;
        }

        public String getStar() {
            return star;
        }

        public void setStar(String star) {
            this.star = star;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_logo() {
            return user_logo;
        }

        public void setUser_logo(String user_logo) {
            this.user_logo = user_logo;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getGeval_content() {
            return geval_content;
        }

        public void setGeval_content(String geval_content) {
            this.geval_content = geval_content;
        }

        public GoodsInfoBean getGoods_info() {
            return goods_info;
        }

        public void setGoods_info(GoodsInfoBean goods_info) {
            this.goods_info = goods_info;
        }

        public String getReply() {
            return reply;
        }

        public void setReply(String reply) {
            this.reply = reply;
        }

        public List<String> getGeval_image() {
            return geval_image;
        }

        public void setGeval_image(List<String> geval_image) {
            this.geval_image = geval_image;
        }

        public static class GoodsInfoBean {
            /**
             * goods_name : 美食aaaa aaa ddd eeee
             * goods_price : null
             * goods_description : 规格规格
             * goods_image : http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060317350986964.jpg
             */

            private String goods_name;
            private String goods_price;
            private String goods_description;
            private String goods_image;

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

            public String getGoods_description() {
                return goods_description;
            }

            public void setGoods_description(String goods_description) {
                this.goods_description = goods_description;
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
