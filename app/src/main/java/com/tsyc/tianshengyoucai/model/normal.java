package com.tsyc.tianshengyoucai.model;

import java.util.List;

/**
 * author：cxd
 * CreateTime：2019/7/19
 * File description：
 */
public class normal {

    /**
     * code : 200
     * message :
     * result : {"all_type":[{"child":[{"sc_bail":0,"sc_icon":"http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-1.jpg","sc_id":17,"sc_name":"新鲜蔬菜","sc_pid":1,"sc_sort":255,"sc_top_show":1},{"sc_bail":0,"sc_icon":"http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-1.jpg","sc_id":18,"sc_name":"肉禽","sc_pid":1,"sc_sort":255,"sc_top_show":1},{"sc_bail":0,"sc_icon":"http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-1.jpg","sc_id":19,"sc_name":"米面粮油","sc_pid":1,"sc_sort":255,"sc_top_show":1},{"sc_bail":0,"sc_icon":"http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-1.jpg","sc_id":20,"sc_name":"休闲酒饮","sc_pid":1,"sc_sort":255,"sc_top_show":1},{"sc_bail":0,"sc_icon":"http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-1.jpg","sc_id":21,"sc_name":"调料干货","sc_pid":1,"sc_sort":255,"sc_top_show":1},{"sc_bail":0,"sc_icon":"http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-1.jpg","sc_id":22,"sc_name":"餐厨用品","sc_pid":1,"sc_sort":255,"sc_top_show":1},{"sc_bail":0,"sc_icon":"http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-1.jpg","sc_id":23,"sc_name":"方便速食","sc_pid":1,"sc_sort":255,"sc_top_show":1}],"sc_icon":"http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-1.jpg","sc_id":1,"sc_name":"美食类","sc_pid":0,"sc_sort":200,"sc_top_show":0},{"child":[{"sc_bail":0,"sc_icon":"0","sc_id":8,"sc_name":"童装","sc_pid":2,"sc_sort":255,"sc_top_show":0},{"sc_bail":0,"sc_icon":"0","sc_id":10,"sc_name":"运动休闲装","sc_pid":2,"sc_sort":255,"sc_top_show":0}],"sc_icon":"http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-2.jpg","sc_id":2,"sc_name":"服装类","sc_pid":0,"sc_sort":200,"sc_top_show":0},{"child":[{"sc_bail":0,"sc_icon":"0","sc_id":11,"sc_name":"护肤","sc_pid":3,"sc_sort":255,"sc_top_show":0},{"sc_bail":0,"sc_icon":"0","sc_id":12,"sc_name":"彩妆","sc_pid":3,"sc_sort":255,"sc_top_show":0}],"sc_icon":"http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-3.jpg","sc_id":3,"sc_name":"美妆类","sc_pid":0,"sc_sort":200,"sc_top_show":0},{"child":[{"sc_bail":0,"sc_icon":"0","sc_id":13,"sc_name":"家居家装","sc_pid":4,"sc_sort":255,"sc_top_show":0},{"sc_bail":0,"sc_icon":"0","sc_id":14,"sc_name":"电子电器","sc_pid":4,"sc_sort":255,"sc_top_show":0}],"sc_icon":"http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-4.jpg","sc_id":4,"sc_name":"生活类","sc_pid":0,"sc_sort":200,"sc_top_show":0},{"child":[{"sc_bail":0,"sc_icon":"0","sc_id":15,"sc_name":"酒店餐饮","sc_pid":5,"sc_sort":255,"sc_top_show":0},{"sc_bail":0,"sc_icon":"0","sc_id":16,"sc_name":"旅游","sc_pid":5,"sc_sort":255,"sc_top_show":0}],"sc_icon":"http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-5.jpg","sc_id":5,"sc_name":"服务类","sc_pid":0,"sc_sort":200,"sc_top_show":0},{"child":[],"sc_icon":"http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-6.jpg","sc_id":6,"sc_name":"休闲零食","sc_pid":0,"sc_sort":255,"sc_top_show":0},{"child":[],"sc_icon":"http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-7.jpg","sc_id":7,"sc_name":"下午咖啡","sc_pid":0,"sc_sort":255,"sc_top_show":0}],"keyvalue":[{"key":"default","name":"智能排序"},{"key":"distance","name":"离我最近"},{"key":"credit","name":"好评优先"},{"key":"sales","name":"销量最好"}],"sort":["智能排序","离我最近","好评优先","销量最好"]}
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
        private List<AllTypeBean> all_type;
        private List<KeyvalueBean> keyvalue;
        private List<String> sort;

        public List<AllTypeBean> getAll_type() {
            return all_type;
        }

        public void setAll_type(List<AllTypeBean> all_type) {
            this.all_type = all_type;
        }

        public List<KeyvalueBean> getKeyvalue() {
            return keyvalue;
        }

        public void setKeyvalue(List<KeyvalueBean> keyvalue) {
            this.keyvalue = keyvalue;
        }

        public List<String> getSort() {
            return sort;
        }

        public void setSort(List<String> sort) {
            this.sort = sort;
        }

        public static class AllTypeBean {
            /**
             * child : [{"sc_bail":0,"sc_icon":"http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-1.jpg","sc_id":17,"sc_name":"新鲜蔬菜","sc_pid":1,"sc_sort":255,"sc_top_show":1},{"sc_bail":0,"sc_icon":"http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-1.jpg","sc_id":18,"sc_name":"肉禽","sc_pid":1,"sc_sort":255,"sc_top_show":1},{"sc_bail":0,"sc_icon":"http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-1.jpg","sc_id":19,"sc_name":"米面粮油","sc_pid":1,"sc_sort":255,"sc_top_show":1},{"sc_bail":0,"sc_icon":"http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-1.jpg","sc_id":20,"sc_name":"休闲酒饮","sc_pid":1,"sc_sort":255,"sc_top_show":1},{"sc_bail":0,"sc_icon":"http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-1.jpg","sc_id":21,"sc_name":"调料干货","sc_pid":1,"sc_sort":255,"sc_top_show":1},{"sc_bail":0,"sc_icon":"http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-1.jpg","sc_id":22,"sc_name":"餐厨用品","sc_pid":1,"sc_sort":255,"sc_top_show":1},{"sc_bail":0,"sc_icon":"http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-1.jpg","sc_id":23,"sc_name":"方便速食","sc_pid":1,"sc_sort":255,"sc_top_show":1}]
             * sc_icon : http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-1.jpg
             * sc_id : 1
             * sc_name : 美食类
             * sc_pid : 0
             * sc_sort : 200
             * sc_top_show : 0
             */

            private String sc_icon;
            private int sc_id;
            private String sc_name;
            private int sc_pid;
            private int sc_sort;
            private int sc_top_show;
            private List<ChildBean> child;

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

            public List<ChildBean> getChild() {
                return child;
            }

            public void setChild(List<ChildBean> child) {
                this.child = child;
            }

            public static class ChildBean {
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

        public static class KeyvalueBean {
            /**
             * key : default
             * name : 智能排序
             */

            private String key;
            private String name;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
