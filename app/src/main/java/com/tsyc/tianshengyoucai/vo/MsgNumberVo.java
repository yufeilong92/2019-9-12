package com.tsyc.tianshengyoucai.vo;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/17 12:30
 * @Purpose :
 */
public class MsgNumberVo {

    /**
     * code : 200
     * message :
     * result : {"order_msg":0,"recruit_msg":0,"system_msg":0}
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
        /**
         * order_msg : 0
         * recruit_msg : 0
         * system_msg : 0
         */

        private int order_msg;
        private int recruit_msg;
        private int system_msg;

        public int getOrder_msg() {
            return order_msg;
        }

        public void setOrder_msg(int order_msg) {
            this.order_msg = order_msg;
        }

        public int getRecruit_msg() {
            return recruit_msg;
        }

        public void setRecruit_msg(int recruit_msg) {
            this.recruit_msg = recruit_msg;
        }

        public int getSystem_msg() {
            return system_msg;
        }

        public void setSystem_msg(int system_msg) {
            this.system_msg = system_msg;
        }
    }
}
