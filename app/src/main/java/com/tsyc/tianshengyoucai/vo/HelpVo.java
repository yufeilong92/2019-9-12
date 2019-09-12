package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/16 11:44
 * @Purpose :帮助中心
 */
public class HelpVo extends NormalBean {

    /**
     * result : {"adv":"http://tsyc.jiefutong.net/uploads/home/adv/5d550818cdf7f.png","list":{"current_page":1,"data":[{"help_id":4,"help_title":"商品质量有保证吗"},{"help_id":1,"help_title":"商场支持什么支付方式"},{"help_id":2,"help_title":"忘记密码这么办"},{"help_id":3,"help_title":"如何换绑手机"}],"last_page":1,"per_page":15,"total":4}}
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * adv : http://tsyc.jiefutong.net/uploads/home/adv/5d550818cdf7f.png
         * list : {"current_page":1,"data":[{"help_id":4,"help_title":"商品质量有保证吗"},{"help_id":1,"help_title":"商场支持什么支付方式"},{"help_id":2,"help_title":"忘记密码这么办"},{"help_id":3,"help_title":"如何换绑手机"}],"last_page":1,"per_page":15,"total":4}
         */

        private String adv;
        private ListBean list;

        public String getAdv() {
            return adv;
        }

        public void setAdv(String adv) {
            this.adv = adv;
        }

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * current_page : 1
             * data : [{"help_id":4,"help_title":"商品质量有保证吗"},{"help_id":1,"help_title":"商场支持什么支付方式"},{"help_id":2,"help_title":"忘记密码这么办"},{"help_id":3,"help_title":"如何换绑手机"}]
             * last_page : 1
             * per_page : 15
             * total : 4
             */

            private int current_page;
            private int last_page;
            private int per_page;
            private int total;
            private List<DataBean> data;

            public int getCurrent_page() {
                return current_page;
            }

            public void setCurrent_page(int current_page) {
                this.current_page = current_page;
            }

            public int getLast_page() {
                return last_page;
            }

            public void setLast_page(int last_page) {
                this.last_page = last_page;
            }

            public int getPer_page() {
                return per_page;
            }

            public void setPer_page(int per_page) {
                this.per_page = per_page;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public static class DataBean {
                /**
                 * help_id : 4
                 * help_title : 商品质量有保证吗
                 */

                private int help_id;
                private String help_title;

                public int getHelp_id() {
                    return help_id;
                }

                public void setHelp_id(int help_id) {
                    this.help_id = help_id;
                }

                public String getHelp_title() {
                    return help_title;
                }

                public void setHelp_title(String help_title) {
                    this.help_title = help_title;
                }
            }
        }
    }
}
