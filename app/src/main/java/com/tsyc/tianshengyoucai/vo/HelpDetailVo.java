package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/16 12:34
 * @Purpose :帮助详情
 */
public class HelpDetailVo extends NormalBean {

    /**
     * result : {"help_id":4,"help_info":"绝对正品，如假包换","help_title":"商品质量有保证吗"}
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
         * help_id : 4
         * help_info : 绝对正品，如假包换
         * help_title : 商品质量有保证吗
         */

        private int help_id;
        private String help_info;
        private String help_title;

        public int getHelp_id() {
            return help_id;
        }

        public void setHelp_id(int help_id) {
            this.help_id = help_id;
        }

        public String getHelp_info() {
            return help_info;
        }

        public void setHelp_info(String help_info) {
            this.help_info = help_info;
        }

        public String getHelp_title() {
            return help_title;
        }

        public void setHelp_title(String help_title) {
            this.help_title = help_title;
        }
    }
}
