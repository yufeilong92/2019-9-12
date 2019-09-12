package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/10 10:30
 * @Purpose :
 */
public class JobChatMsgVo extends NormalBean {

    /**
     * result : {"head_card":{"address":{"area":"中原区","city":"郑州市","province":"河南省"},"boss":{"avatar":"http://wwww.jiefutong.net/uploads/home/membertag/201909/8_2019090318004962880.jpg","duties":"网络工程师","id":1,"username":"game over"},"company_name":"河南捷付通电子科技有限公司","education":"不限制","job_type":{"desc":"全职","value":1},"position_name":"PHP","salary":"8-10k","work_experience":"不限制"},"left_user":{"avatar":"http://wwww.jiefutong.net/uploads/home/membertag/201909/8_2019090318004962880.jpg","id":1,"username":"game over"},"logs":{"current_page":1,"data":[{"content":"我","create_time":"2019-08-05 11:54:03","from_side":1,"from_uid":1,"id":22,"is_read":0,"record_id":2,"type":1,"update_time":"2019-08-05 11:54:03"},{"content":"dsfsdfsd","create_time":"2019-07-27 16:55:32","from_side":1,"from_uid":1,"id":21,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-27 16:55:32"},{"content":"ferwerw","create_time":"2019-07-27 16:55:24","from_side":2,"from_uid":5,"id":20,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-27 16:55:24"},{"content":"456","create_time":"2019-07-27 16:51:42","from_side":1,"from_uid":1,"id":19,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-27 16:51:42"},{"content":"123","create_time":"2019-07-27 16:50:27","from_side":2,"from_uid":5,"id":18,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-27 16:50:27"},{"content":"ferwee","create_time":"2019-07-27 16:48:10","from_side":1,"from_uid":1,"id":17,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-27 16:48:10"},{"content":"fedf","create_time":"2019-07-27 16:47:31","from_side":2,"from_uid":5,"id":16,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-27 16:47:31"},{"content":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/0_2019072716470530230.png","create_time":"2019-07-27 16:47:05","from_side":1,"from_uid":1,"id":15,"is_read":0,"record_id":2,"type":2,"update_time":"2019-07-27 16:47:05"},{"content":"test","create_time":"2019-07-18 10:58:43","from_side":1,"from_uid":1,"id":14,"is_read":0,"record_id":2,"type":1,"update_time":"2019-07-18 10:58:43"},{"content":"测试","create_time":"2019-07-18 10:56:50","from_side":2,"from_uid":5,"id":13,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-18 10:56:50"},{"content":"您好","create_time":"2019-07-18 10:53:10","from_side":1,"from_uid":1,"id":12,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-18 10:53:10"},{"content":"你好","create_time":"2019-07-18 10:37:36","from_side":2,"from_uid":5,"id":11,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-18 10:37:36"},{"content":"fdsfsdfsfd","create_time":"2019-07-18 09:49:12","from_side":2,"from_uid":5,"id":10,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-18 09:49:12"},{"content":"2313213","create_time":"2019-07-18 09:48:51","from_side":2,"from_uid":5,"id":9,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-18 09:48:51"},{"content":"rt ","create_time":"2019-07-17 18:58:01","from_side":2,"from_uid":5,"id":8,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-17 18:58:01"}],"last_page":2,"per_page":15,"total":22},"record_id":"2","right_user":{"age":"24岁","avatar":"http://wwww.jiefutong.net/uploads/home/membertag/201905/9_2019053016340065433.jpg","id":5,"username":"测试"},"title":"捷付通-game over","who_start":"07月13日 11:23由你发起的沟通"}
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
         * head_card : {"address":{"area":"中原区","city":"郑州市","province":"河南省"},"boss":{"avatar":"http://wwww.jiefutong.net/uploads/home/membertag/201909/8_2019090318004962880.jpg","duties":"网络工程师","id":1,"username":"game over"},"company_name":"河南捷付通电子科技有限公司","education":"不限制","job_type":{"desc":"全职","value":1},"position_name":"PHP","salary":"8-10k","work_experience":"不限制"}
         * left_user : {"avatar":"http://wwww.jiefutong.net/uploads/home/membertag/201909/8_2019090318004962880.jpg","id":1,"username":"game over"}
         * logs : {"current_page":1,"data":[{"content":"我","create_time":"2019-08-05 11:54:03","from_side":1,"from_uid":1,"id":22,"is_read":0,"record_id":2,"type":1,"update_time":"2019-08-05 11:54:03"},{"content":"dsfsdfsd","create_time":"2019-07-27 16:55:32","from_side":1,"from_uid":1,"id":21,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-27 16:55:32"},{"content":"ferwerw","create_time":"2019-07-27 16:55:24","from_side":2,"from_uid":5,"id":20,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-27 16:55:24"},{"content":"456","create_time":"2019-07-27 16:51:42","from_side":1,"from_uid":1,"id":19,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-27 16:51:42"},{"content":"123","create_time":"2019-07-27 16:50:27","from_side":2,"from_uid":5,"id":18,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-27 16:50:27"},{"content":"ferwee","create_time":"2019-07-27 16:48:10","from_side":1,"from_uid":1,"id":17,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-27 16:48:10"},{"content":"fedf","create_time":"2019-07-27 16:47:31","from_side":2,"from_uid":5,"id":16,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-27 16:47:31"},{"content":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/0_2019072716470530230.png","create_time":"2019-07-27 16:47:05","from_side":1,"from_uid":1,"id":15,"is_read":0,"record_id":2,"type":2,"update_time":"2019-07-27 16:47:05"},{"content":"test","create_time":"2019-07-18 10:58:43","from_side":1,"from_uid":1,"id":14,"is_read":0,"record_id":2,"type":1,"update_time":"2019-07-18 10:58:43"},{"content":"测试","create_time":"2019-07-18 10:56:50","from_side":2,"from_uid":5,"id":13,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-18 10:56:50"},{"content":"您好","create_time":"2019-07-18 10:53:10","from_side":1,"from_uid":1,"id":12,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-18 10:53:10"},{"content":"你好","create_time":"2019-07-18 10:37:36","from_side":2,"from_uid":5,"id":11,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-18 10:37:36"},{"content":"fdsfsdfsfd","create_time":"2019-07-18 09:49:12","from_side":2,"from_uid":5,"id":10,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-18 09:49:12"},{"content":"2313213","create_time":"2019-07-18 09:48:51","from_side":2,"from_uid":5,"id":9,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-18 09:48:51"},{"content":"rt ","create_time":"2019-07-17 18:58:01","from_side":2,"from_uid":5,"id":8,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-17 18:58:01"}],"last_page":2,"per_page":15,"total":22}
         * record_id : 2
         * right_user : {"age":"24岁","avatar":"http://wwww.jiefutong.net/uploads/home/membertag/201905/9_2019053016340065433.jpg","id":5,"username":"测试"}
         * title : 捷付通-game over
         * who_start : 07月13日 11:23由你发起的沟通
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
             * address : {"area":"中原区","city":"郑州市","province":"河南省"}
             * boss : {"avatar":"http://wwww.jiefutong.net/uploads/home/membertag/201909/8_2019090318004962880.jpg","duties":"网络工程师","id":1,"username":"game over"}
             * company_name : 河南捷付通电子科技有限公司
             * education : 不限制
             * job_type : {"desc":"全职","value":1}
             * position_name : PHP
             * salary : 8-10k
             * work_experience : 不限制
             */

            private AddressBean address;
            private BossBean boss;
            private String company_name;
            private String education;
            private GmSelectBean job_type;
            private String position_name;
            private String salary;
            private String work_experience;

            public AddressBean getAddress() {
                return address;
            }

            public void setAddress(AddressBean address) {
                this.address = address;
            }

            public BossBean getBoss() {
                return boss;
            }

            public void setBoss(BossBean boss) {
                this.boss = boss;
            }

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public String getEducation() {
                return education;
            }

            public void setEducation(String education) {
                this.education = education;
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

            public String getSalary() {
                return salary;
            }

            public void setSalary(String salary) {
                this.salary = salary;
            }

            public String getWork_experience() {
                return work_experience;
            }

            public void setWork_experience(String work_experience) {
                this.work_experience = work_experience;
            }

            public static class AddressBean {
                /**
                 * area : 中原区
                 * city : 郑州市
                 * province : 河南省
                 */

                private String area;
                private String city;
                private String province;

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

            public static class BossBean {
                /**
                 * avatar : http://wwww.jiefutong.net/uploads/home/membertag/201909/8_2019090318004962880.jpg
                 * duties : 网络工程师
                 * id : 1
                 * username : game over
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

            public static class JobTypeBean {
                /**
                 * desc : 全职
                 * value : 1
                 */

                private String desc;
                private int value;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public int getValue() {
                    return value;
                }

                public void setValue(int value) {
                    this.value = value;
                }
            }
        }

        public static class LeftUserBean {
            /**
             * avatar : http://wwww.jiefutong.net/uploads/home/membertag/201909/8_2019090318004962880.jpg
             * id : 1
             * username : game over
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
             * data : [{"content":"我","create_time":"2019-08-05 11:54:03","from_side":1,"from_uid":1,"id":22,"is_read":0,"record_id":2,"type":1,"update_time":"2019-08-05 11:54:03"},{"content":"dsfsdfsd","create_time":"2019-07-27 16:55:32","from_side":1,"from_uid":1,"id":21,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-27 16:55:32"},{"content":"ferwerw","create_time":"2019-07-27 16:55:24","from_side":2,"from_uid":5,"id":20,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-27 16:55:24"},{"content":"456","create_time":"2019-07-27 16:51:42","from_side":1,"from_uid":1,"id":19,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-27 16:51:42"},{"content":"123","create_time":"2019-07-27 16:50:27","from_side":2,"from_uid":5,"id":18,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-27 16:50:27"},{"content":"ferwee","create_time":"2019-07-27 16:48:10","from_side":1,"from_uid":1,"id":17,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-27 16:48:10"},{"content":"fedf","create_time":"2019-07-27 16:47:31","from_side":2,"from_uid":5,"id":16,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-27 16:47:31"},{"content":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/0_2019072716470530230.png","create_time":"2019-07-27 16:47:05","from_side":1,"from_uid":1,"id":15,"is_read":0,"record_id":2,"type":2,"update_time":"2019-07-27 16:47:05"},{"content":"test","create_time":"2019-07-18 10:58:43","from_side":1,"from_uid":1,"id":14,"is_read":0,"record_id":2,"type":1,"update_time":"2019-07-18 10:58:43"},{"content":"测试","create_time":"2019-07-18 10:56:50","from_side":2,"from_uid":5,"id":13,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-18 10:56:50"},{"content":"您好","create_time":"2019-07-18 10:53:10","from_side":1,"from_uid":1,"id":12,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-18 10:53:10"},{"content":"你好","create_time":"2019-07-18 10:37:36","from_side":2,"from_uid":5,"id":11,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-18 10:37:36"},{"content":"fdsfsdfsfd","create_time":"2019-07-18 09:49:12","from_side":2,"from_uid":5,"id":10,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-18 09:49:12"},{"content":"2313213","create_time":"2019-07-18 09:48:51","from_side":2,"from_uid":5,"id":9,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-18 09:48:51"},{"content":"rt ","create_time":"2019-07-17 18:58:01","from_side":2,"from_uid":5,"id":8,"is_read":1,"record_id":2,"type":1,"update_time":"2019-07-17 18:58:01"}]
             * last_page : 2
             * per_page : 15
             * total : 22
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

//            public static class DataBean {
//                /**
//                 * content : 我
//                 * create_time : 2019-08-05 11:54:03
//                 * from_side : 1
//                 * from_uid : 1
//                 * id : 22
//                 * is_read : 0
//                 * record_id : 2
//                 * type : 1
//                 * update_time : 2019-08-05 11:54:03
//                 */
//
//                private String content;
//                private String create_time;
//                private int from_side;
//                private int from_uid;
//                private int id;
//                private int is_read;
//                private int record_id;
//                private int type;
//                private String update_time;
//
//                public String getContent() {
//                    return content;
//                }
//
//                public void setContent(String content) {
//                    this.content = content;
//                }
//
//                public String getCreate_time() {
//                    return create_time;
//                }
//
//                public void setCreate_time(String create_time) {
//                    this.create_time = create_time;
//                }
//
//                public int getFrom_side() {
//                    return from_side;
//                }
//
//                public void setFrom_side(int from_side) {
//                    this.from_side = from_side;
//                }
//
//                public int getFrom_uid() {
//                    return from_uid;
//                }
//
//                public void setFrom_uid(int from_uid) {
//                    this.from_uid = from_uid;
//                }
//
//                public int getId() {
//                    return id;
//                }
//
//                public void setId(int id) {
//                    this.id = id;
//                }
//
//                public int getIs_read() {
//                    return is_read;
//                }
//
//                public void setIs_read(int is_read) {
//                    this.is_read = is_read;
//                }
//
//                public int getRecord_id() {
//                    return record_id;
//                }
//
//                public void setRecord_id(int record_id) {
//                    this.record_id = record_id;
//                }
//
//                public int getType() {
//                    return type;
//                }
//
//                public void setType(int type) {
//                    this.type = type;
//                }
//
//                public String getUpdate_time() {
//                    return update_time;
//                }
//
//                public void setUpdate_time(String update_time) {
//                    this.update_time = update_time;
//                }
//            }
        }

        public static class RightUserBean {
            /**
             * age : 24岁
             * avatar : http://wwww.jiefutong.net/uploads/home/membertag/201905/9_2019053016340065433.jpg
             * id : 5
             * username : 测试
             */

            private String age;
            private String avatar;
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
