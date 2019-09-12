package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/15 15:57
 * @Purpose :
 */
public class BindVo extends NormalBean {

    /**
     * result : {"key":"ad95d9e6c1675b197003feb7c6d7bf53","userid":35,"username":"背包客"}
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
         * key : ad95d9e6c1675b197003feb7c6d7bf53
         * userid : 35
         * username : 背包客
         */

        private String key;
        private int userid;
        private String username;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
