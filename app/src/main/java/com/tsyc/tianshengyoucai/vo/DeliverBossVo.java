package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/4 17:41
 * @Purpose :
 */
public class DeliverBossVo extends NormalBean {

    /**
     * result : {"current_page":1,"data":[{"boss_id":1,"boss_position_id":11,"check_time":"mock","company_id":1,"create_time":"2019-06-24 10:52:24","cv":{"age":"24岁","avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201905/9_2019053016340065433.jpg","birthday":"1995-01","expected_position":{"desc":"PHP","value":19},"expected_salary":{"desc":"10-15k","value":7},"experience":4,"highest_education":{"desc":"硕士","value":6},"id":5,"sex":{"desc":"女士","value":1},"username":"测试","work_city":{"desc":"郑州市","value":240}},"finish_time":"mock","id":3,"interview_time":"mock","position_name":"安卓","salary":{"desc":"10-15k","value":7},"send_day":"06-24","send_interview_time":"mock","send_time":"06-24 10:52","status":{"desc":"已投递","value":0},"update_time":"2019-06-24 10:52:24","user_cv_id":5}],"last_page":1,"per_page":15,"total":1}
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
         * data : [{"boss_id":1,"boss_position_id":11,"check_time":"mock","company_id":1,"create_time":"2019-06-24 10:52:24","cv":{"age":"24岁","avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201905/9_2019053016340065433.jpg","birthday":"1995-01","expected_position":{"desc":"PHP","value":19},"expected_salary":{"desc":"10-15k","value":7},"experience":4,"highest_education":{"desc":"硕士","value":6},"id":5,"sex":{"desc":"女士","value":1},"username":"测试","work_city":{"desc":"郑州市","value":240}},"finish_time":"mock","id":3,"interview_time":"mock","position_name":"安卓","salary":{"desc":"10-15k","value":7},"send_day":"06-24","send_interview_time":"mock","send_time":"06-24 10:52","status":{"desc":"已投递","value":0},"update_time":"2019-06-24 10:52:24","user_cv_id":5}]
         * last_page : 1
         * per_page : 15
         * total : 1
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
             * boss_id : 1
             * boss_position_id : 11
             * check_time : mock
             * company_id : 1
             * create_time : 2019-06-24 10:52:24
             * cv : {"age":"24岁","avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201905/9_2019053016340065433.jpg","birthday":"1995-01","expected_position":{"desc":"PHP","value":19},"expected_salary":{"desc":"10-15k","value":7},"experience":4,"highest_education":{"desc":"硕士","value":6},"id":5,"sex":{"desc":"女士","value":1},"username":"测试","work_city":{"desc":"郑州市","value":240}}
             * finish_time : mock
             * id : 3
             * interview_time : mock
             * position_name : 安卓
             * salary : {"desc":"10-15k","value":7}
             * send_day : 06-24
             * send_interview_time : mock
             * send_time : 06-24 10:52
             * status : {"desc":"已投递","value":0}
             * update_time : 2019-06-24 10:52:24
             * user_cv_id : 5
             */

            private int boss_id;
            private int boss_position_id;
            private String check_time;
            private int company_id;
            private String create_time;
            private CvBean cv;
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

            public int getBoss_id() {
                return boss_id;
            }

            public void setBoss_id(int boss_id) {
                this.boss_id = boss_id;
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

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public CvBean getCv() {
                return cv;
            }

            public void setCv(CvBean cv) {
                this.cv = cv;
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

            public static class CvBean {
                /**
                 * age : 24岁
                 * avatar : http://tsyc.jiefutong.net/uploads/home/membertag/201905/9_2019053016340065433.jpg
                 * birthday : 1995-01
                 * expected_position : {"desc":"PHP","value":19}
                 * expected_salary : {"desc":"10-15k","value":7}
                 * experience : 4
                 * highest_education : {"desc":"硕士","value":6}
                 * id : 5
                 * sex : {"desc":"女士","value":1}
                 * username : 测试
                 * work_city : {"desc":"郑州市","value":240}
                 */

                private String age;
                private String avatar;
                private String birthday;
                private GmSelectBean expected_position;
                private GmSelectBean expected_salary;
                private int experience;
                private GmSelectBean highest_education;
                private int id;
                private GmSelectBean sex;
                private String username;
                private GmSelectBean work_city;

                public String getAge() {
                    return age;
                }

                public void setAge(String age) {
                    this.age = age;
                }

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public String getBirthday() {
                    return birthday;
                }

                public void setBirthday(String birthday) {
                    this.birthday = birthday;
                }

                public GmSelectBean getExpected_position() {
                    return expected_position;
                }

                public void setExpected_position(GmSelectBean expected_position) {
                    this.expected_position = expected_position;
                }

                public GmSelectBean getExpected_salary() {
                    return expected_salary;
                }

                public void setExpected_salary(GmSelectBean expected_salary) {
                    this.expected_salary = expected_salary;
                }

                public int getExperience() {
                    return experience;
                }

                public void setExperience(int experience) {
                    this.experience = experience;
                }

                public GmSelectBean getHighest_education() {
                    return highest_education;
                }

                public void setHighest_education(GmSelectBean highest_education) {
                    this.highest_education = highest_education;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public GmSelectBean getSex() {
                    return sex;
                }

                public void setSex(GmSelectBean sex) {
                    this.sex = sex;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public GmSelectBean getWork_city() {
                    return work_city;
                }

                public void setWork_city(GmSelectBean work_city) {
                    this.work_city = work_city;
                }
            }
        }
    }
}
