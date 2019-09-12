package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.normal;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/10 15:20
 * @Purpose :全部分类
 */
public class AllTypeBeanVo {
    /**
     * sc_bail : 0
     * sc_icon : http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-1.jpg
     * sc_id : 1
     * sc_name : 美食类
     * sc_sort : 200
     */

    private int sc_bail;
    private String sc_icon;
    private int sc_id;
    private String sc_name;
    private int sc_sort;
    private List<ChildBean> child;

    public List<ChildBean> getChild() {
        return child;
    }

    public void setChild(List<ChildBean> child) {
        this.child = child;
    }

    public int getSc_bail() {
        return sc_bail;
    }

    public void setSc_bail(int sc_bail) {
        this.sc_bail = sc_bail;
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

    public static class ChildBean{
        /**
         * sc_bail : 0
         * sc_icon : http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-1.jpg
         * sc_id : 17
         * sc_name : 新鲜蔬菜
         * sc_pid : 1
         * sc_sort : 255
         * sc_top_show : 1
         */

        private int sc_bail;
        private String sc_icon;
        private int sc_id;
        private String sc_name;
        private int sc_pid;
        private int sc_sort;
        private int sc_top_show;

        public int getSc_bail() {
            return sc_bail;
        }

        public void setSc_bail(int sc_bail) {
            this.sc_bail = sc_bail;
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

        public int getSc_pid() {
            return sc_pid;
        }

        public void setSc_pid(int sc_pid) {
            this.sc_pid = sc_pid;
        }

        public int getSc_sort() {
            return sc_sort;
        }

        public void setSc_sort(int sc_sort) {
            this.sc_sort = sc_sort;
        }

        public int getSc_top_show() {
            return sc_top_show;
        }

        public void setSc_top_show(int sc_top_show) {
            this.sc_top_show = sc_top_show;
        }

    }
}
