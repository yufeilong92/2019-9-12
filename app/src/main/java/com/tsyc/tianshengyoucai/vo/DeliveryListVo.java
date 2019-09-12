package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/29 10:44
 * @Purpose :
 */
public class DeliveryListVo extends NormalBean {

    /**
     * result : {"total":4,"per_page":15,"current_page":1,"last_page":1,"data":[{"id":4,"user_cv_id":5,"boss_id":1,"company_id":1,"boss_position_id":9,"status":{"value":0,"desc":"已投递"},"create_time":"2019-08-27 09:45:33","update_time":"2019-08-27 09:45:33","check_time":"","send_interview_time":"","interview_time":"","finish_time":"","company_name":"河南捷付通电子科技有限公司","position_name":"PHP实习","salary":{"value":4,"desc":"4-6k"},"send_day":"08-27","send_time":"08-27 09:45"},{"id":3,"user_cv_id":5,"boss_id":1,"company_id":1,"boss_position_id":11,"status":{"value":1,"desc":"已查看"},"create_time":"2019-06-24 10:52:24","update_time":"2019-07-10 10:35:05","check_time":"07-10 10:35","send_interview_time":"","interview_time":"","finish_time":"","company_name":"河南捷付通电子科技有限公司","position_name":"安卓","salary":{"value":7,"desc":"10-15k"},"send_day":"06-24","send_time":"06-24 10:52"},{"id":2,"user_cv_id":5,"boss_id":1,"company_id":1,"boss_position_id":10,"status":{"value":4,"desc":"面试未到"},"create_time":"2019-06-24 10:52:19","update_time":"2019-06-25 15:40:26","check_time":"06-25 14:35","send_interview_time":"06-25 14:45","interview_time":"2019-06-26 10:00","finish_time":"06-25 15:40","company_name":"河南捷付通电子科技有限公司","position_name":"PHP","salary":{"value":6,"desc":"8-10k"},"send_day":"06-24","send_time":"06-24 10:52"},{"id":1,"user_cv_id":5,"boss_id":1,"company_id":1,"boss_position_id":9,"status":{"value":3,"desc":"已结束"},"create_time":"2019-06-21 15:25:43","update_time":"2019-06-25 15:41:30","check_time":"06-25 11:49","send_interview_time":"06-25 14:44","interview_time":"2019-06-26 10:00","finish_time":"06-25 15:41","company_name":"河南捷付通电子科技有限公司","position_name":"PHP实习","salary":{"value":4,"desc":"4-6k"},"send_day":"06-21","send_time":"06-21 15:25"}]}
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
         * total : 4
         * per_page : 15
         * current_page : 1
         * last_page : 1
         * data : [{"id":4,"user_cv_id":5,"boss_id":1,"company_id":1,"boss_position_id":9,"status":{"value":0,"desc":"已投递"},"create_time":"2019-08-27 09:45:33","update_time":"2019-08-27 09:45:33","check_time":"","send_interview_time":"","interview_time":"","finish_time":"","company_name":"河南捷付通电子科技有限公司","position_name":"PHP实习","salary":{"value":4,"desc":"4-6k"},"send_day":"08-27","send_time":"08-27 09:45"},{"id":3,"user_cv_id":5,"boss_id":1,"company_id":1,"boss_position_id":11,"status":{"value":1,"desc":"已查看"},"create_time":"2019-06-24 10:52:24","update_time":"2019-07-10 10:35:05","check_time":"07-10 10:35","send_interview_time":"","interview_time":"","finish_time":"","company_name":"河南捷付通电子科技有限公司","position_name":"安卓","salary":{"value":7,"desc":"10-15k"},"send_day":"06-24","send_time":"06-24 10:52"},{"id":2,"user_cv_id":5,"boss_id":1,"company_id":1,"boss_position_id":10,"status":{"value":4,"desc":"面试未到"},"create_time":"2019-06-24 10:52:19","update_time":"2019-06-25 15:40:26","check_time":"06-25 14:35","send_interview_time":"06-25 14:45","interview_time":"2019-06-26 10:00","finish_time":"06-25 15:40","company_name":"河南捷付通电子科技有限公司","position_name":"PHP","salary":{"value":6,"desc":"8-10k"},"send_day":"06-24","send_time":"06-24 10:52"},{"id":1,"user_cv_id":5,"boss_id":1,"company_id":1,"boss_position_id":9,"status":{"value":3,"desc":"已结束"},"create_time":"2019-06-21 15:25:43","update_time":"2019-06-25 15:41:30","check_time":"06-25 11:49","send_interview_time":"06-25 14:44","interview_time":"2019-06-26 10:00","finish_time":"06-25 15:41","company_name":"河南捷付通电子科技有限公司","position_name":"PHP实习","salary":{"value":4,"desc":"4-6k"},"send_day":"06-21","send_time":"06-21 15:25"}]
         */

        private int total;
        private int per_page;
        private int current_page;
        private int last_page;
        private List<DataBean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 4
             * user_cv_id : 5
             * boss_id : 1
             * company_id : 1
             * boss_position_id : 9
             * status : {"value":0,"desc":"已投递"}
             * create_time : 2019-08-27 09:45:33
             * update_time : 2019-08-27 09:45:33
             * check_time :
             * send_interview_time :
             * interview_time :
             * finish_time :
             * company_name : 河南捷付通电子科技有限公司
             * position_name : PHP实习
             * salary : {"value":4,"desc":"4-6k"}
             * send_day : 08-27
             * send_time : 08-27 09:45
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
            private String company_name;
            private String position_name;
            private GmSelectBean salary;
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

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
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

            public String getSend_time() {
                return send_time;
            }

            public void setSend_time(String send_time) {
                this.send_time = send_time;
            }

        }
    }
}
