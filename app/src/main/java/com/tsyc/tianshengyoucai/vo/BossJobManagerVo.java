package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/4 11:06
 * @Purpose :
 */
public class BossJobManagerVo extends NormalBean {

    /**
     * result : {"current_page":1,"data":[{"education":{"desc":"高中","value":3},"id":12,"job_type":{"desc":"全职","value":1},"position_name":"PHP中级","salary":{"desc":"8-10k","value":6},"update_time":"07月25日","work_experience":{"desc":"应届生","value":2}},{"education":{"desc":"本科","value":5},"id":11,"job_type":{"desc":"全职","value":1},"position_name":"安卓","salary":{"desc":"10-15k","value":7},"update_time":"06月20日","work_experience":{"desc":"1-3年","value":4}},{"education":{"desc":"不限制","value":0},"id":10,"job_type":{"desc":"全职","value":1},"position_name":"PHP","salary":{"desc":"8-10k","value":6},"update_time":"06月04日","work_experience":{"desc":"不限制","value":0}},{"education":{"desc":"高中","value":3},"id":9,"job_type":{"desc":"全职","value":1},"position_name":"PHP实习","salary":{"desc":"4-6k","value":4},"update_time":"06月04日","work_experience":{"desc":"应届生","value":2}}],"last_page":1,"per_page":15,"total":4}
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
         * current_page : 1
         * data : [{"education":{"desc":"高中","value":3},"id":12,"job_type":{"desc":"全职","value":1},"position_name":"PHP中级","salary":{"desc":"8-10k","value":6},"update_time":"07月25日","work_experience":{"desc":"应届生","value":2}},{"education":{"desc":"本科","value":5},"id":11,"job_type":{"desc":"全职","value":1},"position_name":"安卓","salary":{"desc":"10-15k","value":7},"update_time":"06月20日","work_experience":{"desc":"1-3年","value":4}},{"education":{"desc":"不限制","value":0},"id":10,"job_type":{"desc":"全职","value":1},"position_name":"PHP","salary":{"desc":"8-10k","value":6},"update_time":"06月04日","work_experience":{"desc":"不限制","value":0}},{"education":{"desc":"高中","value":3},"id":9,"job_type":{"desc":"全职","value":1},"position_name":"PHP实习","salary":{"desc":"4-6k","value":4},"update_time":"06月04日","work_experience":{"desc":"应届生","value":2}}]
         * last_page : 1
         * per_page : 15
         * total : 4
         */

        private int current_page;
        private int last_page;
        private int per_page;
        private int total;
        private List<DataBean> data;

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

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * education : {"desc":"高中","value":3}
             * id : 12
             * job_type : {"desc":"全职","value":1}
             * position_name : PHP中级
             * salary : {"desc":"8-10k","value":6}
             * update_time : 07月25日
             * work_experience : {"desc":"应届生","value":2}
             */

            private GmSelectBean education;
            private int id;
            private GmSelectBean job_type;
            private String position_name;
            private GmSelectBean salary;
            private String update_time;
            private GmSelectBean work_experience;

            public GmSelectBean getEducation() {
                return education;
            }

            public void setEducation(GmSelectBean education) {
                this.education = education;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public GmSelectBean getJob_type() {
                return job_type;
            }

            public void setJob_type(GmSelectBean job_type) {
                this.job_type = job_type;
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

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public GmSelectBean getWork_experience() {
                return work_experience;
            }

            public void setWork_experience(GmSelectBean work_experience) {
                this.work_experience = work_experience;
            }
        }
    }
}
