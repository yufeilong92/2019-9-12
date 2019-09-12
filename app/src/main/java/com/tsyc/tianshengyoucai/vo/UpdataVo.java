package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/14 18:14
 * @Purpose :
 */
public class UpdataVo extends NormalBean {

    /**
     * result : {"android_version":"1.0.0","android_version_code":1,"app_desc":"修复问题","app_url":"http://down.shanfuyg.com/fui/fui/download.html","appcode":"20190407","download_url":"https://caogen1.oss-cn-beijing.aliyuncs.com/zhangyitong.apk","force_update":0,"is_update":0,"new_version":"1.0.0"}
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
         * android_version : 1.0.0
         * android_version_code : 1
         * app_desc : 修复问题
         * app_url : http://down.shanfuyg.com/fui/fui/download.html
         * appcode : 20190407
         * download_url : https://caogen1.oss-cn-beijing.aliyuncs.com/zhangyitong.apk
         * force_update : 0
         * is_update : 0
         * new_version : 1.0.0
         */

        private String android_version;
        private int android_version_code;
        private String app_desc;
        private String app_url;
        private String appcode;
        private String download_url;
        private int force_update;
        private int is_update;
        private String new_version;

        public String getAndroid_version() {
            return android_version;
        }

        public void setAndroid_version(String android_version) {
            this.android_version = android_version;
        }

        public int getAndroid_version_code() {
            return android_version_code;
        }

        public void setAndroid_version_code(int android_version_code) {
            this.android_version_code = android_version_code;
        }

        public String getApp_desc() {
            return app_desc;
        }

        public void setApp_desc(String app_desc) {
            this.app_desc = app_desc;
        }

        public String getApp_url() {
            return app_url;
        }

        public void setApp_url(String app_url) {
            this.app_url = app_url;
        }

        public String getAppcode() {
            return appcode;
        }

        public void setAppcode(String appcode) {
            this.appcode = appcode;
        }

        public String getDownload_url() {
            return download_url;
        }

        public void setDownload_url(String download_url) {
            this.download_url = download_url;
        }

        public int getForce_update() {
            return force_update;
        }

        public void setForce_update(int force_update) {
            this.force_update = force_update;
        }

        public int getIs_update() {
            return is_update;
        }

        public void setIs_update(int is_update) {
            this.is_update = is_update;
        }

        public String getNew_version() {
            return new_version;
        }

        public void setNew_version(String new_version) {
            this.new_version = new_version;
        }
    }
}
