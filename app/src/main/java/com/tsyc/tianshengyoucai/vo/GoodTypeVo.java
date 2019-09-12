package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/12 16:16
 * @Purpose : 商品分类
 */
public class GoodTypeVo extends NormalBean {

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * adv :
         * child : [{"gc_id":786,"gc_name":"面膜","gc_parent_id":8,"pic":"http://tsyc.jiefutong.net/uploads/home/common/category-pic-786.jpg"}]
         * gc_id : 8
         * gc_name : 美妆
         * gc_parent_id : 0
         * pic :
         */

        private String adv;//一级广告图
        private int gc_id; //分类id
        private String gc_name;//分类名称
        private int gc_parent_id;//上级id
        private String pic;//icon 图片
        private List<ResultBean> child;

        public String getAdv() {
            return adv;
        }

        public void setAdv(String adv) {
            this.adv = adv;
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

        public int getGc_parent_id() {
            return gc_parent_id;
        }

        public void setGc_parent_id(int gc_parent_id) {
            this.gc_parent_id = gc_parent_id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public List<ResultBean> getChild() {
            return child;
        }

        public void setChild(List<ResultBean> child) {
            this.child = child;
        }


    }
}
