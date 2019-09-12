package com.tsyc.tianshengyoucai.model.bean;

/**
 * author：van
 * CreateTime：2019/8/14
 * File description：
 */
public class IsInnerBean {

    /**
     * code : 200
     * result : {"is_inner":1}
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
         * is_inner : 1
         */

        private int is_inner;

        public int getIs_inner() {
            return is_inner;
        }

        public void setIs_inner(int is_inner) {
            this.is_inner = is_inner;
        }
    }
}
