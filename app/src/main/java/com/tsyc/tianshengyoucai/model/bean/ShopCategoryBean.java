package com.tsyc.tianshengyoucai.model.bean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/14
 * File description： 商品分类 bean
 */
public class ShopCategoryBean {


    /**
     * code : 200
     * message :
     * result : [{"adv":"http://tsyc.jiefutong.net/uploads/home/common/category-adv-824.jpg","child":[{"child":[{"child":[],"gc_id":862,"gc_name":"西湖龙井","gc_parent_id":860,"pic":""}],"gc_id":860,"gc_name":"其他茶","gc_parent_id":824,"pic":"http://tsyc.jiefutong.net/uploads/home/common/category-pic-860.jpg"}],"gc_id":824,"gc_name":"名茶","gc_parent_id":0,"pic":""}]
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
         * adv : http://tsyc.jiefutong.net/uploads/home/common/category-adv-824.jpg
         * child : [{"child":[{"child":[],"gc_id":862,"gc_name":"西湖龙井","gc_parent_id":860,"pic":""}],"gc_id":860,"gc_name":"其他茶","gc_parent_id":824,"pic":"http://tsyc.jiefutong.net/uploads/home/common/category-pic-860.jpg"}]
         * gc_id : 824
         * gc_name : 名茶
         * gc_parent_id : 0
         * pic :
         */

        private String adv;
        private int gc_id;
        private String gc_name;
        private int gc_parent_id;
        private String pic;
        private List<ChildBeanX> child;

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

        public List<ChildBeanX> getChild() {
            return child;
        }

        public void setChild(List<ChildBeanX> child) {
            this.child = child;
        }

        public static class ChildBeanX {
            /**
             * child : [{"child":[],"gc_id":862,"gc_name":"西湖龙井","gc_parent_id":860,"pic":""}]
             * gc_id : 860
             * gc_name : 其他茶
             * gc_parent_id : 824
             * pic : http://tsyc.jiefutong.net/uploads/home/common/category-pic-860.jpg
             */

            private int gc_id;
            private String gc_name;
            private int gc_parent_id;
            private String pic;
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

            public List<ChildBean> getChild() {
                return child;
            }

            public void setChild(List<ChildBean> child) {
                this.child = child;
            }

            public static class ChildBean {
                /**
                 * child : []
                 * gc_id : 862
                 * gc_name : 西湖龙井
                 * gc_parent_id : 860
                 * pic :
                 */

                private int gc_id;
                private String gc_name;
                private int gc_parent_id;
                private String pic;
                private List<?> child;

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

                public List<?> getChild() {
                    return child;
                }

                public void setChild(List<?> child) {
                    this.child = child;
                }
            }
        }
    }
}
