package com.tsyc.tianshengyoucai.model.bean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/1
 * File description：主营类目 bean
 */
public class MainCategoryBean {

    /**
     * code : 200
     * result : [{"gc_id":1,"gc_name":"美食类","child":[{"gc_id":17,"gc_name":"新鲜蔬菜"},{"gc_id":18,"gc_name":"肉禽"},{"gc_id":19,"gc_name":"米面粮油"},{"gc_id":20,"gc_name":"休闲酒饮"},{"gc_id":21,"gc_name":"调料干货"},{"gc_id":22,"gc_name":"餐厨用品"},{"gc_id":23,"gc_name":"方便速食"}]},{"gc_id":2,"gc_name":"服装类","child":[{"gc_id":8,"gc_name":"童装"},{"gc_id":10,"gc_name":"运动休闲装"}]},{"gc_id":3,"gc_name":"美妆类","child":[{"gc_id":11,"gc_name":"护肤"},{"gc_id":12,"gc_name":"彩妆"}]},{"gc_id":4,"gc_name":"生活类","child":[{"gc_id":13,"gc_name":"家居家装"},{"gc_id":14,"gc_name":"电子电器"}]},{"gc_id":5,"gc_name":"服务类","child":[{"gc_id":15,"gc_name":"酒店餐饮"},{"gc_id":16,"gc_name":"旅游"}]},{"gc_id":6,"gc_name":"休闲零食","child":[]},{"gc_id":7,"gc_name":"下午咖啡","child":[]}]
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
         * gc_id : 1
         * gc_name : 美食类
         * child : [{"gc_id":17,"gc_name":"新鲜蔬菜"},{"gc_id":18,"gc_name":"肉禽"},{"gc_id":19,"gc_name":"米面粮油"},{"gc_id":20,"gc_name":"休闲酒饮"},{"gc_id":21,"gc_name":"调料干货"},{"gc_id":22,"gc_name":"餐厨用品"},{"gc_id":23,"gc_name":"方便速食"}]
         */

        private int gc_id;
        private String gc_name;
        private List<ChildBean> child;

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

        public List<ChildBean> getChild() {
            return child;
        }

        public void setChild(List<ChildBean> child) {
            this.child = child;
        }

        public static class ChildBean {
            /**
             * gc_id : 17
             * gc_name : 新鲜蔬菜
             */

            private int gc_id;
            private String gc_name;

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
        }
    }
}
