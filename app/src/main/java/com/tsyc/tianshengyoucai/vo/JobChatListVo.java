package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/30 15:07
 * @Purpose : 求职聊天
 */
public class JobChatListVo extends NormalBean {

    /**
     * result : {"current_page":1,"data":[{"boss":{"avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","duties":"经理","id":1,"username":"张女士"},"boss_id":1,"boss_position_id":10,"boss_unread":4,"company":{"full_name":"河南捷付通电子科技有限公司","id":1,"short_name":"捷付通"},"company_id":1,"create_time":"2019-07-13 11:23:40","employee_unread":3,"id":2,"last_record":"我","last_time":"08/05","start_user":2,"update_time":"2019-07-15 14:49:33","user_cv_id":5},{"boss":{"avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","duties":"经理","id":1,"username":"张女士"},"boss_id":1,"boss_position_id":11,"boss_unread":0,"company":{"full_name":"河南捷付通电子科技有限公司","id":1,"short_name":"捷付通"},"company_id":1,"create_time":"2019-07-15 10:54:12","employee_unread":0,"id":3,"last_record":"[新招呼]","last_time":"01/01","start_user":2,"update_time":"2019-07-15 10:54:12","user_cv_id":5},{"boss":{"avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","duties":"经理","id":1,"username":"张女士"},"boss_id":1,"boss_position_id":9,"boss_unread":0,"company":{"full_name":"河南捷付通电子科技有限公司","id":1,"short_name":"捷付通"},"company_id":1,"create_time":"2019-07-13 11:23:19","employee_unread":0,"id":1,"last_record":"[新招呼]","last_time":"01/01","start_user":1,"update_time":"2019-07-13 11:23:19","user_cv_id":5}],"last_page":1,"per_page":15,"total":3}
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
         * data : [{"boss":{"avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","duties":"经理","id":1,"username":"张女士"},"boss_id":1,"boss_position_id":10,"boss_unread":4,"company":{"full_name":"河南捷付通电子科技有限公司","id":1,"short_name":"捷付通"},"company_id":1,"create_time":"2019-07-13 11:23:40","employee_unread":3,"id":2,"last_record":"我","last_time":"08/05","start_user":2,"update_time":"2019-07-15 14:49:33","user_cv_id":5},{"boss":{"avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","duties":"经理","id":1,"username":"张女士"},"boss_id":1,"boss_position_id":11,"boss_unread":0,"company":{"full_name":"河南捷付通电子科技有限公司","id":1,"short_name":"捷付通"},"company_id":1,"create_time":"2019-07-15 10:54:12","employee_unread":0,"id":3,"last_record":"[新招呼]","last_time":"01/01","start_user":2,"update_time":"2019-07-15 10:54:12","user_cv_id":5},{"boss":{"avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","duties":"经理","id":1,"username":"张女士"},"boss_id":1,"boss_position_id":9,"boss_unread":0,"company":{"full_name":"河南捷付通电子科技有限公司","id":1,"short_name":"捷付通"},"company_id":1,"create_time":"2019-07-13 11:23:19","employee_unread":0,"id":1,"last_record":"[新招呼]","last_time":"01/01","start_user":1,"update_time":"2019-07-13 11:23:19","user_cv_id":5}]
         * last_page : 1
         * per_page : 15
         * total : 3
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
             * boss : {"avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","duties":"经理","id":1,"username":"张女士"}
             * boss_id : 1
             * boss_position_id : 10
             * boss_unread : 4
             * company : {"full_name":"河南捷付通电子科技有限公司","id":1,"short_name":"捷付通"}
             * company_id : 1
             * create_time : 2019-07-13 11:23:40
             * employee_unread : 3
             * id : 2
             * last_record : 我
             * last_time : 08/05
             * start_user : 2
             * update_time : 2019-07-15 14:49:33
             * user_cv_id : 5
             */

            private BossBean boss;
            private int boss_id;
            private int boss_position_id;
            private int boss_unread;
            private CompanyBean company;
            private int company_id;
            private String create_time;
            private int employee_unread;
            private int id;
            private String last_record;
            private String last_time;
            private int start_user;
            private String update_time;
            private int user_cv_id;
            private CvBean cv;

            public CvBean getCv() {
                return cv;
            }

            public void setCv(CvBean cv) {
                this.cv = cv;
            }

            public BossBean getBoss() {
                return boss;
            }

            public void setBoss(BossBean boss) {
                this.boss = boss;
            }

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

            public int getBoss_unread() {
                return boss_unread;
            }

            public void setBoss_unread(int boss_unread) {
                this.boss_unread = boss_unread;
            }

            public CompanyBean getCompany() {
                return company;
            }

            public void setCompany(CompanyBean company) {
                this.company = company;
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

            public int getEmployee_unread() {
                return employee_unread;
            }

            public void setEmployee_unread(int employee_unread) {
                this.employee_unread = employee_unread;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLast_record() {
                return last_record;
            }

            public void setLast_record(String last_record) {
                this.last_record = last_record;
            }

            public String getLast_time() {
                return last_time;
            }

            public void setLast_time(String last_time) {
                this.last_time = last_time;
            }

            public int getStart_user() {
                return start_user;
            }

            public void setStart_user(int start_user) {
                this.start_user = start_user;
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

            public static class BossBean {
                /**
                 * avatar : http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg
                 * duties : 经理
                 * id : 1
                 * username : 张女士
                 */

                private String avatar;
                private String duties;
                private int id;
                private String username;

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public String getDuties() {
                    return duties;
                }

                public void setDuties(String duties) {
                    this.duties = duties;
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

            public static class CompanyBean {
                /**
                 * full_name : 河南捷付通电子科技有限公司
                 * id : 1
                 * short_name : 捷付通
                 */

                private String full_name;
                private int id;
                private String short_name;

                public String getFull_name() {
                    return full_name;
                }

                public void setFull_name(String full_name) {
                    this.full_name = full_name;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getShort_name() {
                    return short_name;
                }

                public void setShort_name(String short_name) {
                    this.short_name = short_name;
                }
            }

            public static class CvBean {

                /**
                 * age : 31岁
                 * avatar : http://wwww.jiefutong.net/uploads/home/membertag/201908/28_2019082722164980206.jpg
                 * expected_position : {"desc":"游戏策划","value":181}
                 * id : 7
                 * username : 王二小
                 */

                private String age;
                private String avatar;
                private GmSelectBean expected_position;
                private int id;
                private String username;

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
}
