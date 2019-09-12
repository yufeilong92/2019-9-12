package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/10 14:57
 * @Purpose :
 */
public class DeliveryDetailVo extends NormalBean {

    /**
     * result : {"address_info":{"address":"陇海路1201","area":"中原区","city":"郑州市","province":"河南省"},"boss_id":1,"boss_mobile":"13500135000","boss_name":"game over","boss_position_id":9,"check_time":"06-25 11:49","company_id":1,"company_name":"河南捷付通电子科技有限公司","create_time":"2019-06-21 15:25:43","finish_time":"06-25 15:41","id":1,"interview_time":"2019-06-26 10:00","position_name":"PHP实习","salary":{"desc":"4-6k","value":4},"send_day":"06-21","send_interview_time":"06-25 14:44","send_time":"06-21 15:25","status":{"desc":"已结束","value":3},"update_time":"2019-06-25 15:41:30","user_cv_id":5}
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
         * address_info : {"address":"陇海路1201","area":"中原区","city":"郑州市","province":"河南省"}
         * boss_id : 1
         * boss_mobile : 13500135000
         * boss_name : game over
         * boss_position_id : 9
         * check_time : 06-25 11:49
         * company_id : 1
         * company_name : 河南捷付通电子科技有限公司
         * create_time : 2019-06-21 15:25:43
         * finish_time : 06-25 15:41
         * id : 1
         * interview_time : 2019-06-26 10:00
         * position_name : PHP实习
         * salary : {"desc":"4-6k","value":4}
         * send_day : 06-21
         * send_interview_time : 06-25 14:44
         * send_time : 06-21 15:25
         * status : {"desc":"已结束","value":3}
         * update_time : 2019-06-25 15:41:30
         * user_cv_id : 5
         */

        private AddressInfoBean address_info;
        private int boss_id;
        private String boss_mobile;
        private String boss_name;
        private int boss_position_id;
        private String check_time;
        private int company_id;
        private String company_name;
        private String create_time;
        private String finish_time;
        private int id;
        private String interview_time;
        private String position_name;
        private GmSelectBean salary;
        private String send_day;
        private String send_interview_time;
        private String send_time;
        private GmSelectBean status;
        private String update_time;
        private int user_cv_id;

        public AddressInfoBean getAddress_info() {
            return address_info;
        }

        public void setAddress_info(AddressInfoBean address_info) {
            this.address_info = address_info;
        }

        public int getBoss_id() {
            return boss_id;
        }

        public void setBoss_id(int boss_id) {
            this.boss_id = boss_id;
        }

        public String getBoss_mobile() {
            return boss_mobile;
        }

        public void setBoss_mobile(String boss_mobile) {
            this.boss_mobile = boss_mobile;
        }

        public String getBoss_name() {
            return boss_name;
        }

        public void setBoss_name(String boss_name) {
            this.boss_name = boss_name;
        }

        public int getBoss_position_id() {
            return boss_position_id;
        }

        public void setBoss_position_id(int boss_position_id) {
            this.boss_position_id = boss_position_id;
        }

        public String getCheck_time() {
            return check_time;
        }

        public void setCheck_time(String check_time) {
            this.check_time = check_time;
        }

        public int getCompany_id() {
            return company_id;
        }

        public void setCompany_id(int company_id) {
            this.company_id = company_id;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getFinish_time() {
            return finish_time;
        }

        public void setFinish_time(String finish_time) {
            this.finish_time = finish_time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getInterview_time() {
            return interview_time;
        }

        public void setInterview_time(String interview_time) {
            this.interview_time = interview_time;
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

        public String getSend_day() {
            return send_day;
        }

        public void setSend_day(String send_day) {
            this.send_day = send_day;
        }

        public String getSend_interview_time() {
            return send_interview_time;
        }

        public void setSend_interview_time(String send_interview_time) {
            this.send_interview_time = send_interview_time;
        }

        public String getSend_time() {
            return send_time;
        }

        public void setSend_time(String send_time) {
            this.send_time = send_time;
        }

        public GmSelectBean getStatus() {
            return status;
        }

        public void setStatus(GmSelectBean status) {
            this.status = status;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public int getUser_cv_id() {
            return user_cv_id;
        }

        public void setUser_cv_id(int user_cv_id) {
            this.user_cv_id = user_cv_id;
        }

        public static class AddressInfoBean {
            /**
             * address : 陇海路1201
             * area : 中原区
             * city : 郑州市
             * province : 河南省
             */

            private String address;
            private String area;
            private String city;
            private String province;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }
        }

    }
}
