package com.tsyc.tianshengyoucai.model.bean;

/**
 * author：van
 * CreateTime：2019/8/12
 * File description：
 */
public class CheckVersionBean {

    /**
     * code : 200
     * result : {"new_version":"1.0.0","android_version":"1.0.0","app_url":"http://down.shanfuyg.com/fui/fui/download.html","download_url":"https://caogen1.oss-cn-beijing.aliyuncs.com/zhangyitong.apk","is_update":0,"force_update":0,"app_desc":"修复问题","appcode":"20190407"}
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
         * new_version : 1.0.0
         * android_version : 1.0.0
         * app_url : http://down.shanfuyg.com/fui/fui/download.html
         * download_url : https://caogen1.oss-cn-beijing.aliyuncs.com/zhangyitong.apk
         * is_update : 0
         * force_update : 0
         * app_desc : 修复问题
         * appcode : 20190407
         */

        private String new_version;
        private String android_version;
        private String app_url;
        private String download_url;
        private int is_update;
        private int force_update;
        private String app_desc;
        private String appcode;

        public String getNew_version() {
            return new_version;
        }

        public void setNew_version(String new_version) {
            this.new_version = new_version;
        }

        public String getAndroid_version() {
            return android_version;
        }

        public void setAndroid_version(String android_version) {
            this.android_version = android_version;
        }

        public String getApp_url() {
            return app_url;
        }

        public void setApp_url(String app_url) {
            this.app_url = app_url;
        }

        public String getDownload_url() {
            return download_url;
        }

        public void setDownload_url(String download_url) {
            this.download_url = download_url;
        }

        public int getIs_update() {
            return is_update;
        }

        public void setIs_update(int is_update) {
            this.is_update = is_update;
        }

        public int getForce_update() {
            return force_update;
        }

        public void setForce_update(int force_update) {
            this.force_update = force_update;
        }

        public String getApp_desc() {
            return app_desc;
        }

        public void setApp_desc(String app_desc) {
            this.app_desc = app_desc;
        }

        public String getAppcode() {
            return appcode;
        }

        public void setAppcode(String appcode) {
            this.appcode = appcode;
        }
    }
}
