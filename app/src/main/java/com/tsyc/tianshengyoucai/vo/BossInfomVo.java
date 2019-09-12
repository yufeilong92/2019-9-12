package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/4 09:42
 * @Purpose :
 */
public class BossInfomVo extends NormalBean {

    /**
     * result : {"IDcard_number":"41142419921020373X","avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201909/8_2019090318004962880.jpg","company_id":1,"create_time":"2019-06-03 11:29:49","duties":"网络工程师","email":"123@126.com","id":1,"is_self":1,"last_login_time":1567560825,"mobile":"13500135000","real_name":"郭冬生","sex":{"desc":"男","value":2},"status":1,"step":3,"update_time":"2019-09-04 09:33:45","user_id":3,"username":"game over"}
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
         * IDcard_number : 41142419921020373X
         * avatar : http://tsyc.jiefutong.net/uploads/home/membertag/201909/8_2019090318004962880.jpg
         * company_id : 1
         * create_time : 2019-06-03 11:29:49
         * duties : 网络工程师
         * email : 123@126.com
         * id : 1
         * is_self : 1
         * last_login_time : 1567560825
         * mobile : 13500135000
         * real_name : 郭冬生
         * sex : {"desc":"男","value":2}
         * status : 1
         * step : 3
         * update_time : 2019-09-04 09:33:45
         * user_id : 3
         * username : game over
         */

        private String IDcard_number;
        private String avatar;
        private int company_id;
        private String create_time;
        private String duties;
        private String email;
        private int id;
        private int is_self;
        private int last_login_time;
        private String mobile;
        private String real_name;
        private GmSelectBean sex;
        private int status;
        private int step;
        private String update_time;
        private int user_id;
        private String username;

        public String getIDcard_number() {
            return IDcard_number;
        }

        public void setIDcard_number(String IDcard_number) {
            this.IDcard_number = IDcard_number;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getCompany_id() {
            return company_id;
        }

        public void setCompany_id(int company_id) {
            this.company_id = company_id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getDuties() {
            return duties;
        }

        public void setDuties(String duties) {
            this.duties = duties;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIs_self() {
            return is_self;
        }

        public void setIs_self(int is_self) {
            this.is_self = is_self;
        }

        public int getLast_login_time() {
            return last_login_time;
        }

        public void setLast_login_time(int last_login_time) {
            this.last_login_time = last_login_time;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public GmSelectBean getSex() {
            return sex;
        }

        public void setSex(GmSelectBean sex) {
            this.sex = sex;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getStep() {
            return step;
        }

        public void setStep(int step) {
            this.step = step;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

    }
}
