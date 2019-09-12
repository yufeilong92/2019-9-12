package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/14 19:33
 * @Purpose :
 */
public class ShopServiceVo extends NormalBean {


    /**
     * result : {"ad":[{"adv_code":"http://tsyc.jiefutong.net/uploads/home/adv/5d53cd93840c6.png","adv_link":"","adv_sort":0,"adv_title":"店铺轮播图-1"},{"adv_code":"http://tsyc.jiefutong.net/uploads/home/adv/5d53cdab0973a.png","adv_link":"","adv_sort":0,"adv_title":"店铺轮播图-2"},{"adv_code":"http://tsyc.jiefutong.net/uploads/home/adv/5d53cd6014131.png","adv_link":"","adv_sort":0,"adv_title":"店铺轮播图-3"}],"list":[{"other":{"is_online_offline":"","is_voucher":""},"sc_id":0,"store_avatar":"http://tsyc.jiefutong.net/images/logo.png","store_credit":5,"store_id":1,"store_name":"测试店铺","area_info":"河南 郑州市 上街区 ","sc_name":"","store_address":"aaaaa","store_zy":""},{"area_info":"河南 郑州市 上街区 ","other":{"is_online_offline":"","is_voucher":""},"sc_id":1,"sc_name":"","store_address":"aaaaa","store_avatar":"http://tsyc.jiefutong.net/images/logo.png","store_credit":5,"store_id":2,"store_name":"aaa","store_zy":""},{"area_info":"省 市 区","other":{"is_online_offline":"支持线上线下","is_voucher":""},"sc_id":0,"sc_name":"美食 ","store_address":"商家地址","store_avatar":"门头照","store_credit":5,"store_id":4,"store_name":"商家名称","store_zy":"美食 "},{"area_info":"北京 北京市 北京市","other":{"is_online_offline":"","is_voucher":""},"sc_id":1,"sc_name":"男装,女装,童装","store_avatar":"http://tsyc.jiefutong.net/images/logo.png","store_credit":5,"store_id":7,"store_name":"测试","store_zy":"男装,女装,童装"},{"area_info":"北京 北京市 北京市","other":{"is_online_offline":"","is_voucher":""},"sc_id":0,"sc_name":"主营","store_avatar":"http://tsyc.jiefutong.net/images/logo.png","store_credit":5,"store_id":8,"store_name":"6666","store_zy":"主营"},{"area_info":"北京市 北京市 东城区","other":{"is_online_offline":"支持线上线下","is_voucher":""},"sc_id":0,"sc_name":"AVAV","store_address":"详细地址修改","store_avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201908/15_2019081313372343318.jpg","store_credit":5,"store_id":9,"store_name":"CC","store_zy":"AVAV"},{"area_info":"河南省 郑州市 管城回族区","other":{"is_online_offline":"支持线上线下","is_voucher":"红包活动"},"sc_id":0,"sc_name":"美容","store_address":"陇海路321号","store_avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201908/8_2019081019311338984.jpg","store_credit":5,"store_id":10,"store_name":"贝佳尼","store_zy":"美容"},{"area_info":"北京市 北京市 东城区","other":{"is_online_offline":"","is_voucher":"红包活动"},"sc_id":0,"sc_name":"美妆","store_address":"Dadadawdwvardsvard","store_avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201908/16_2019081509201774263.jpg","store_credit":5,"store_id":12,"store_name":"豆豆","store_zy":"美妆"},{"area_info":"河南省 郑州市 二七区","other":{"is_online_offline":"支持线上线下","is_voucher":""},"sc_id":0,"sc_name":"休闲零食","store_address":"绿地滨湖国际城一区二号楼","store_avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/19_2019071909424790375.jpg","store_credit":5,"store_id":13,"store_name":"哈哈","store_zy":"休闲零食"},{"area_info":"河南省 郑州市 二七区","other":{"is_online_offline":"支持线上线下","is_voucher":""},"sc_id":0,"sc_name":"生活","store_address":"大学路南三环","store_avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201908/26_2019081318171464961.jpg","store_credit":5,"store_id":14,"store_name":"平价酒水仓储批发","store_zy":"生活"}],"typeList":[{"sc_icon":"http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-1.jpg","sc_id":1,"sc_name":"美食类","sc_sort":200},{"sc_icon":"http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-2.jpg","sc_id":2,"sc_name":"服装类","sc_sort":200},{"sc_icon":"http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-3.jpg","sc_id":3,"sc_name":"美妆类","sc_sort":200},{"sc_icon":"http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-4.jpg","sc_id":4,"sc_name":"生活类","sc_sort":200},{"sc_icon":"http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-5.jpg","sc_id":5,"sc_name":"服务类","sc_sort":200}]}
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<AdBean> ad;
        private List<ShopVo> list;
        private List<TypeListBean> typeList;

        public List<AdBean> getAd() {
            return ad;
        }

        public void setAd(List<AdBean> ad) {
            this.ad = ad;
        }

        public List<ShopVo> getList() {
            return list;
        }

        public void setList(List<ShopVo> list) {
            this.list = list;
        }

        public List<TypeListBean> getTypeList() {
            return typeList;
        }

        public void setTypeList(List<TypeListBean> typeList) {
            this.typeList = typeList;
        }

        public static class AdBean {
            /**
             * adv_code : http://tsyc.jiefutong.net/uploads/home/adv/5d53cd93840c6.png
             * adv_link :
             * adv_sort : 0
             * adv_title : 店铺轮播图-1
             */

            private String adv_code;
            private String adv_link;
            private int adv_sort;
            private String adv_title;

            public String getAdv_code() {
                return adv_code;
            }

            public void setAdv_code(String adv_code) {
                this.adv_code = adv_code;
            }

            public String getAdv_link() {
                return adv_link;
            }

            public void setAdv_link(String adv_link) {
                this.adv_link = adv_link;
            }

            public int getAdv_sort() {
                return adv_sort;
            }

            public void setAdv_sort(int adv_sort) {
                this.adv_sort = adv_sort;
            }

            public String getAdv_title() {
                return adv_title;
            }

            public void setAdv_title(String adv_title) {
                this.adv_title = adv_title;
            }
        }



        public static class TypeListBean {
            /**
             * sc_icon : http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-1.jpg
             * sc_id : 1
             * sc_name : 美食类
             * sc_sort : 200
             */

            private String sc_icon;
            private int sc_id;
            private String sc_name;
            private int sc_sort;
            /**
             * 自定义字段
             */
            private int local;

            public int getLocal() {
                return local;
            }

            public void setLocal(int local) {
                this.local = local;
            }

            public String getSc_icon() {
                return sc_icon;
            }

            public void setSc_icon(String sc_icon) {
                this.sc_icon = sc_icon;
            }

            public int getSc_id() {
                return sc_id;
            }

            public void setSc_id(int sc_id) {
                this.sc_id = sc_id;
            }

            public String getSc_name() {
                return sc_name;
            }

            public void setSc_name(String sc_name) {
                this.sc_name = sc_name;
            }

            public int getSc_sort() {
                return sc_sort;
            }

            public void setSc_sort(int sc_sort) {
                this.sc_sort = sc_sort;
            }
        }
    }
}
