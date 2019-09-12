package com.tsyc.tianshengyoucai.model.bean;

/**
 * author：van
 * CreateTime：2019/9/11
 * File description：公告详情
 */
public class HomeNoticeDetailBean {

    /**
     * code : 200
     * result : {"id":40,"name":"APP上线了","text":"APP上线了，更多惊喜等你发现"}
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
         * id : 40
         * name : APP上线了
         * text : APP上线了，更多惊喜等你发现
         */

        private int id;
        private String name;
        private String text;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
