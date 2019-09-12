package com.tsyc.tianshengyoucai.model.bean;

/**
 * author：van
 * CreateTime：2019/7/25
 * File description：
 */
public class LoginSuccessBean {

    /**
     * code : 200
     * result : {"username":"lzp","userid":1,"key":"0ac2652c54ca06396ecab11d0478041e"}
     * message :
     */

    private String code;
    private ResultBean result;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class ResultBean {
        /**
         * username : lzp
         * userid : 1
         * key : 0ac2652c54ca06396ecab11d0478041e
         */

        private String username;
        private int userid;
        private String key;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
