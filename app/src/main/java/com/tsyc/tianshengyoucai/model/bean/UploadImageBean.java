package com.tsyc.tianshengyoucai.model.bean;

/**
 * author：van
 * CreateTime：2019/7/31
 * File description： 上传图片
 */
public class UploadImageBean {

    /**
     * code : 200
     * result : {"img_url":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/9_2019073111351889848.png"}
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
         * img_url : http://tsyc.jiefutong.net/uploads/home/membertag/201907/9_2019073111351889848.png
         */

        private String img_url;

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }
    }
}
