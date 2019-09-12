package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/29 15:01
 * @Purpose :
 */
public class RcMineVo extends NormalBean {

    /**
     * result : {"cv":{"age":"24岁","avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201905/9_2019053016340065433.jpg","expected_position":{"desc":"PHP","value":19},"experience":4,"highest_education":{"desc":"硕士","value":6},"id":5,"sex":{"desc":"女士","value":1},"user_id":3,"username":"测试","work_city":{"desc":"郑州市","value":240}},"cv_sends":{"check":1,"interview":0,"send":1,"unmatch":0}}
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
         * cv : {"age":"24岁","avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201905/9_2019053016340065433.jpg","expected_position":{"desc":"PHP","value":19},"experience":4,"highest_education":{"desc":"硕士","value":6},"id":5,"sex":{"desc":"女士","value":1},"user_id":3,"username":"测试","work_city":{"desc":"郑州市","value":240}}
         * cv_sends : {"check":1,"interview":0,"send":1,"unmatch":0}
         */

        private CvBean cv;
        private CvSendsBean cv_sends;

        public CvBean getCv() {
            return cv;
        }

        public void setCv(CvBean cv) {
            this.cv = cv;
        }

        public CvSendsBean getCv_sends() {
            return cv_sends;
        }

        public void setCv_sends(CvSendsBean cv_sends) {
            this.cv_sends = cv_sends;
        }

        public static class CvBean {
            /**
             * age : 24岁
             * avatar : http://tsyc.jiefutong.net/uploads/home/membertag/201905/9_2019053016340065433.jpg
             * expected_position : {"desc":"PHP","value":19}
             * experience : 4
             * highest_education : {"desc":"硕士","value":6}
             * id : 5
             * sex : {"desc":"女士","value":1}
             * user_id : 3
             * username : 测试
             * work_city : {"desc":"郑州市","value":240}
             */

            private String age;
            private String avatar;
            private GmSelectBean expected_position;
            private int experience;
            private GmSelectBean highest_education;
            private int id;
            private GmSelectBean sex;
            private int user_id;
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

            public GmSelectBean getExpected_position() {
                return expected_position;
            }

            public void setExpected_position(GmSelectBean expected_position) {
                this.expected_position = expected_position;
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

            public GmSelectBean getWork_city() {
                return work_city;
            }

            public void setWork_city(GmSelectBean work_city) {
                this.work_city = work_city;
            }
        }

        public static class CvSendsBean {
            /**
             * check : 1
             * interview : 0
             * send : 1
             * unmatch : 0
             */

            private int check;
            private int interview;
            private int send;
            private int unmatch;

            public int getCheck() {
                return check;
            }

            public void setCheck(int check) {
                this.check = check;
            }

            public int getInterview() {
                return interview;
            }

            public void setInterview(int interview) {
                this.interview = interview;
            }

            public int getSend() {
                return send;
            }

            public void setSend(int send) {
                this.send = send;
            }

            public int getUnmatch() {
                return unmatch;
            }

            public void setUnmatch(int unmatch) {
                this.unmatch = unmatch;
            }
        }
    }
}
