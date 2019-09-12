package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/5 16:38
 * @Purpose :
 */
public class BossInterViewVo extends NormalBean {

    /**
     * result : {"id":3,"user_cv_id":5,"boss_id":1,"company_id":1,"boss_position_id":11,"status":{"value":1,"desc":"已查看"},"create_time":"2019-06-24 10:52:24","update_time":"2019-07-10 10:35:05","check_time":"07-10 10:35","send_interview_time":"","interview_time":"","finish_time":"","position_name":"安卓","salary":{"value":7,"desc":"10-15k"},"address_info":{"province":"河南省","city":"郑州市","area":"中原区","address":"龙海101"},"cv":{"id":5,"avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201905/9_2019053016340065433.jpg","username":"测试","mobile":"13500135000","sex":{"value":1,"desc":"女士"},"age":"24岁"},"send_day":"06-24","send_time":"06-24 10:52"}
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
         * id : 3
         * user_cv_id : 5
         * boss_id : 1
         * company_id : 1
         * boss_position_id : 11
         * status : {"value":1,"desc":"已查看"}
         * create_time : 2019-06-24 10:52:24
         * update_time : 2019-07-10 10:35:05
         * check_time : 07-10 10:35
         * send_interview_time :
         * interview_time :
         * finish_time :
         * position_name : 安卓
         * salary : {"value":7,"desc":"10-15k"}
         * address_info : {"province":"河南省","city":"郑州市","area":"中原区","address":"龙海101"}
         * cv : {"id":5,"avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201905/9_2019053016340065433.jpg","username":"测试","mobile":"13500135000","sex":{"value":1,"desc":"女士"},"age":"24岁"}
         * send_day : 06-24
         * send_time : 06-24 10:52
         */

        private int id;
        private int user_cv_id;
        private int boss_id;
        private int company_id;
        private int boss_position_id;
        private GmSelectBean status;
        private String create_time;
        private String update_time;
        private String check_time;
        private String send_interview_time;
        private String interview_time;
        private String finish_time;
        private String position_name;
        private GmSelectBean salary;
        private AddressInfoBean address_info;
        private CvBean cv;
        private String send_day;
        private String send_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_cv_id() {
            return user_cv_id;
        }

        public void setUser_cv_id(int user_cv_id) {
            this.user_cv_id = user_cv_id;
        }

        public int getBoss_id() {
            return boss_id;
        }

        public void setBoss_id(int boss_id) {
            this.boss_id = boss_id;
        }

        public int getCompany_id() {
            return company_id;
        }

        public void setCompany_id(int company_id) {
            this.company_id = company_id;
        }

        public int getBoss_position_id() {
            return boss_position_id;
        }

        public void setBoss_position_id(int boss_position_id) {
            this.boss_position_id = boss_position_id;
        }

        public GmSelectBean getStatus() {
            return status;
        }

        public void setStatus(GmSelectBean status) {
            this.status = status;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getCheck_time() {
            return check_time;
        }

        public void setCheck_time(String check_time) {
            this.check_time = check_time;
        }

        public String getSend_interview_time() {
            return send_interview_time;
        }

        public void setSend_interview_time(String send_interview_time) {
            this.send_interview_time = send_interview_time;
        }

        public String getInterview_time() {
            return interview_time;
        }

        public void setInterview_time(String interview_time) {
            this.interview_time = interview_time;
        }

        public String getFinish_time() {
            return finish_time;
        }

        public void setFinish_time(String finish_time) {
            this.finish_time = finish_time;
        }

        public String getPosition_name() {
            return position_name;
        }

        public void setPosition_name(String position_name) {
            this.position_name = position_name;
        }

        public GmSelectBean getSalary() {
            return salary;
        }

        public void setSalary(GmSelectBean salary) {
            this.salary = salary;
        }

        public AddressInfoBean getAddress_info() {
            return address_info;
        }

        public void setAddress_info(AddressInfoBean address_info) {
            this.address_info = address_info;
        }

        public CvBean getCv() {
            return cv;
        }

        public void setCv(CvBean cv) {
            this.cv = cv;
        }

        public String getSend_day() {
            return send_day;
        }

        public void setSend_day(String send_day) {
            this.send_day = send_day;
        }

        public String getSend_time() {
            return send_time;
        }

        public void setSend_time(String send_time) {
            this.send_time = send_time;
        }


        public static class AddressInfoBean {
            /**
             * province : 河南省
             * city : 郑州市
             * area : 中原区
             * address : 龙海101
             */

            private String province;
            private String city;
            private String area;
            private String address;

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }

        public static class CvBean {
            /**
             * id : 5
             * avatar : http://tsyc.jiefutong.net/uploads/home/membertag/201905/9_2019053016340065433.jpg
             * username : 测试
             * mobile : 13500135000
             * sex : {"value":1,"desc":"女士"}
             * age : 24岁
             */

            private int id;
            private String avatar;
            private String username;
            private String mobile;
            private GmSelectBean sex;
            private String age;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public GmSelectBean getSex() {
                return sex;
            }

            public void setSex(GmSelectBean sex) {
                this.sex = sex;
            }

            public String getAge() {
                return age;
            }

            public void setAge(String age) {
                this.age = age;
            }

        }
    }
}
