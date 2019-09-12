package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/13 19:20
 * @Purpose :w微信
 */
public class WeiXinLoginVo extends NormalBean {

    /**
     * result : {"is_bind_mobile":0,"key":"b0hsRlQwVElELV85a1BpTmo1eHZmQ0ZZbnhwSQ=="}
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
         * is_bind_mobile : 0
         * key : b0hsRlQwVElELV85a1BpTmo1eHZmQ0ZZbnhwSQ==
         */

        private int is_bind_mobile;
        private String key;
        private String userid;
        private String username;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getIs_bind_mobile() {
            return is_bind_mobile;
        }

        public void setIs_bind_mobile(int is_bind_mobile) {
            this.is_bind_mobile = is_bind_mobile;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
