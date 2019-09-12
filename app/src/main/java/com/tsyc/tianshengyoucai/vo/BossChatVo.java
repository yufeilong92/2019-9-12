package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/9 16:55
 * @Purpose :
 */
public class BossChatVo extends NormalBean {

    /**
     * result : {"head_card":{"age":"31岁","avatar":"http://wwww.jiefutong.net/uploads/home/membertag/201908/28_2019082722164980206.jpg","birthday":"1988-08-27","expected_position":{"desc":"游戏策划","value":181},"expected_salary":{"desc":"50k以上","value":11},"experience":9,"highest_education":{"desc":"博士","value":7},"id":7,"sex":{"desc":"先生","value":2},"username":"王二小","work_city":{"desc":"北京市","value":1}},"left_user":{"avatar":"http://wwww.jiefutong.net/uploads/home/membertag/201908/28_2019082722164980206.jpg","id":7,"username":"王二小"},"logs":{"current_page":1,"data":[],"last_page":0,"per_page":15,"total":0},"record_id":"7","right_user":{"avatar":"http://wwww.jiefutong.net/uploads/home/membertag/201909/35_2019090316484314185.jpg","id":5,"username":"于飞龙"},"title":"王二小\u2014","who_start":"09月09日 16:19由你发起的沟通"}
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
         * head_card : {"age":"31岁","avatar":"http://wwww.jiefutong.net/uploads/home/membertag/201908/28_2019082722164980206.jpg","birthday":"1988-08-27","expected_position":{"desc":"游戏策划","value":181},"expected_salary":{"desc":"50k以上","value":11},"experience":9,"highest_education":{"desc":"博士","value":7},"id":7,"sex":{"desc":"先生","value":2},"username":"王二小","work_city":{"desc":"北京市","value":1}}
         * left_user : {"avatar":"http://wwww.jiefutong.net/uploads/home/membertag/201908/28_2019082722164980206.jpg","id":7,"username":"王二小"}
         * logs : {"current_page":1,"data":[],"last_page":0,"per_page":15,"total":0}
         * record_id : 7
         * right_user : {"avatar":"http://wwww.jiefutong.net/uploads/home/membertag/201909/35_2019090316484314185.jpg","id":5,"username":"于飞龙"}
         * title : 王二小—
         * who_start : 09月09日 16:19由你发起的沟通
         */

        private HeadCardBean head_card;
        private LeftUserBean left_user;
        private LogsBean logs;
        private String record_id;
        private RightUserBean right_user;
        private String title;
        private String who_start;

        public HeadCardBean getHead_card() {
            return head_card;
        }

        public void setHead_card(HeadCardBean head_card) {
            this.head_card = head_card;
        }

        public LeftUserBean getLeft_user() {
            return left_user;
        }

        public void setLeft_user(LeftUserBean left_user) {
            this.left_user = left_user;
        }

        public LogsBean getLogs() {
            return logs;
        }

        public void setLogs(LogsBean logs) {
            this.logs = logs;
        }

        public String getRecord_id() {
            return record_id;
        }

        public void setRecord_id(String record_id) {
            this.record_id = record_id;
        }

        public RightUserBean getRight_user() {
            return right_user;
        }

        public void setRight_user(RightUserBean right_user) {
            this.right_user = right_user;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getWho_start() {
            return who_start;
        }

        public void setWho_start(String who_start) {
            this.who_start = who_start;
        }

        public static class HeadCardBean {
            /**
             * age : 31岁
             * avatar : http://wwww.jiefutong.net/uploads/home/membertag/201908/28_2019082722164980206.jpg
             * birthday : 1988-08-27
             * expected_position : {"desc":"游戏策划","value":181}
             * expected_salary : {"desc":"50k以上","value":11}
             * experience : 9
             * highest_education : {"desc":"博士","value":7}
             * id : 7
             * sex : {"desc":"先生","value":2}
             * username : 王二小
             * work_city : {"desc":"北京市","value":1}
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

        public static class LeftUserBean {
            /**
             * avatar : http://wwww.jiefutong.net/uploads/home/membertag/201908/28_2019082722164980206.jpg
             * id : 7
             * username : 王二小
             */

            private String avatar;
            private int id;
            private String username;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }

        public static class LogsBean {
            /**
             * current_page : 1
             * data : []
             * last_page : 0
             * per_page : 15
             * total : 0
             */

            private int current_page;
            private int last_page;
            private int per_page;
            private int total;
            private List<ChatDataBeanVo> data;

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

            public List<ChatDataBeanVo> getData() {
                return data;
            }

            public void setData(List<ChatDataBeanVo> data) {
                this.data = data;
            }
        }

        public static class RightUserBean {
            /**
             * avatar : http://wwww.jiefutong.net/uploads/home/membertag/201909/35_2019090316484314185.jpg
             * id : 5
             * username : 于飞龙
             */

            private String avatar;
            private int id;
            private String username;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }
    }
}
