package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/30 11:36
 * @Purpose :
 */
public class JobHomeListsVo extends NormalBean {

    /**
     * result : {"current_page":1,"data":[{"boss_position_id":9,"create_time":"2019-06-20 17:13:43","id":5,"position":{"address":"陇海路1201","address_info":{"address":"陇海路1201","area":"中原区","city":"郑州市","province":"河南省"},"area_id":2654,"boss":{"IDcard_number":"41142419921020373X","avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","company_id":1,"create_time":"2019-06-03 11:29:49","duties":"经理","email":"123@126.com","id":1,"is_self":1,"last_login_time":1559532589,"mobile":"13500135000","real_name":"郭冬生","sex":{"desc":"女","value":1},"status":1,"step":3,"update_time":"2019-08-28 17:26:41","user_id":3,"username":"张女士"},"boss_id":1,"city_id":240,"company":{"address":"陇海路1204","area_id":2656,"audit_time":"2019-06-04 10:46:12","city_id":240,"company_size":{"desc":"20-50人","value":2},"create_time":"2019-06-03 17:55:30","desc":"捷付通电子","full_name":"河南捷付通电子科技有限公司","id":1,"industries":{"desc":"互联网/IT/电子/通信","pid":0,"value":1},"is_real":1,"license":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","logo":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","province_id":16,"short_name":"捷付通","status":1,"update_time":"2019-07-25 10:13:07"},"company_id":1,"create_time":"2019-06-04 15:51:39","desc":"PHP开发职位小苏打发实打实的","education":{"desc":"高中","value":3},"highlights":{"desc":["五险一金","带薪年假","年终奖"],"value":"1,2,3"},"id":9,"job_type":{"desc":"全职","value":1},"position_id":19,"position_name":"PHP实习","province_id":16,"salary":{"desc":"4-6k","value":4},"status":{"desc":"招聘中","value":1},"update_time":"06月04日","work_experience":{"desc":"应届生","value":2}},"update_time":"2019-06-20 17:13:43","user_cv_id":5},{"boss_position_id":11,"create_time":"2019-06-20 17:13:38","id":4,"position":{"address":"龙海101","address_info":{"address":"龙海101","area":"中原区","city":"郑州市","province":"河南省"},"area_id":2654,"boss":{"IDcard_number":"41142419921020373X","avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","company_id":1,"create_time":"2019-06-03 11:29:49","duties":"经理","email":"123@126.com","id":1,"is_self":1,"last_login_time":1559532589,"mobile":"13500135000","real_name":"郭冬生","sex":{"desc":"女","value":1},"status":1,"step":3,"update_time":"2019-08-28 17:26:41","user_id":3,"username":"张女士"},"boss_id":1,"city_id":240,"company":{"address":"陇海路1204","area_id":2656,"audit_time":"2019-06-04 10:46:12","city_id":240,"company_size":{"desc":"20-50人","value":2},"create_time":"2019-06-03 17:55:30","desc":"捷付通电子","full_name":"河南捷付通电子科技有限公司","id":1,"industries":{"desc":"互联网/IT/电子/通信","pid":0,"value":1},"is_real":1,"license":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","logo":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","province_id":16,"short_name":"捷付通","status":1,"update_time":"2019-07-25 10:13:07"},"company_id":1,"create_time":"2019-06-20 14:31:33","desc":"安卓开发","education":{"desc":"本科","value":5},"highlights":{"desc":["五险一金","带薪年假","周末双休","节日福利"],"value":"1,2,4,6"},"id":11,"job_type":{"desc":"全职","value":1},"position_id":43,"position_name":"安卓","province_id":16,"salary":{"desc":"10-15k","value":7},"status":{"desc":"招聘中","value":1},"update_time":"06月20日","work_experience":{"desc":"1-3年","value":4}},"update_time":"2019-06-20 17:13:38","user_cv_id":5}],"last_page":1,"per_page":15,"total":2}
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
         * data : [{"boss_position_id":9,"create_time":"2019-06-20 17:13:43","id":5,"position":{"address":"陇海路1201","address_info":{"address":"陇海路1201","area":"中原区","city":"郑州市","province":"河南省"},"area_id":2654,"boss":{"IDcard_number":"41142419921020373X","avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","company_id":1,"create_time":"2019-06-03 11:29:49","duties":"经理","email":"123@126.com","id":1,"is_self":1,"last_login_time":1559532589,"mobile":"13500135000","real_name":"郭冬生","sex":{"desc":"女","value":1},"status":1,"step":3,"update_time":"2019-08-28 17:26:41","user_id":3,"username":"张女士"},"boss_id":1,"city_id":240,"company":{"address":"陇海路1204","area_id":2656,"audit_time":"2019-06-04 10:46:12","city_id":240,"company_size":{"desc":"20-50人","value":2},"create_time":"2019-06-03 17:55:30","desc":"捷付通电子","full_name":"河南捷付通电子科技有限公司","id":1,"industries":{"desc":"互联网/IT/电子/通信","pid":0,"value":1},"is_real":1,"license":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","logo":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","province_id":16,"short_name":"捷付通","status":1,"update_time":"2019-07-25 10:13:07"},"company_id":1,"create_time":"2019-06-04 15:51:39","desc":"PHP开发职位小苏打发实打实的","education":{"desc":"高中","value":3},"highlights":{"desc":["五险一金","带薪年假","年终奖"],"value":"1,2,3"},"id":9,"job_type":{"desc":"全职","value":1},"position_id":19,"position_name":"PHP实习","province_id":16,"salary":{"desc":"4-6k","value":4},"status":{"desc":"招聘中","value":1},"update_time":"06月04日","work_experience":{"desc":"应届生","value":2}},"update_time":"2019-06-20 17:13:43","user_cv_id":5},{"boss_position_id":11,"create_time":"2019-06-20 17:13:38","id":4,"position":{"address":"龙海101","address_info":{"address":"龙海101","area":"中原区","city":"郑州市","province":"河南省"},"area_id":2654,"boss":{"IDcard_number":"41142419921020373X","avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","company_id":1,"create_time":"2019-06-03 11:29:49","duties":"经理","email":"123@126.com","id":1,"is_self":1,"last_login_time":1559532589,"mobile":"13500135000","real_name":"郭冬生","sex":{"desc":"女","value":1},"status":1,"step":3,"update_time":"2019-08-28 17:26:41","user_id":3,"username":"张女士"},"boss_id":1,"city_id":240,"company":{"address":"陇海路1204","area_id":2656,"audit_time":"2019-06-04 10:46:12","city_id":240,"company_size":{"desc":"20-50人","value":2},"create_time":"2019-06-03 17:55:30","desc":"捷付通电子","full_name":"河南捷付通电子科技有限公司","id":1,"industries":{"desc":"互联网/IT/电子/通信","pid":0,"value":1},"is_real":1,"license":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","logo":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","province_id":16,"short_name":"捷付通","status":1,"update_time":"2019-07-25 10:13:07"},"company_id":1,"create_time":"2019-06-20 14:31:33","desc":"安卓开发","education":{"desc":"本科","value":5},"highlights":{"desc":["五险一金","带薪年假","周末双休","节日福利"],"value":"1,2,4,6"},"id":11,"job_type":{"desc":"全职","value":1},"position_id":43,"position_name":"安卓","province_id":16,"salary":{"desc":"10-15k","value":7},"status":{"desc":"招聘中","value":1},"update_time":"06月20日","work_experience":{"desc":"1-3年","value":4}},"update_time":"2019-06-20 17:13:38","user_cv_id":5}]
         * last_page : 1
         * per_page : 15
         * total : 2
         */

        private int current_page;
        private int last_page;
        private int per_page;
        private int total;
        private List<BossJobItemVo> data;

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

        public List<BossJobItemVo> getData() {
            return data;
        }

        public void setData(List<BossJobItemVo> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * boss_position_id : 9
             * create_time : 2019-06-20 17:13:43
             * id : 5
             * position : {"address":"陇海路1201","address_info":{"address":"陇海路1201","area":"中原区","city":"郑州市","province":"河南省"},"area_id":2654,"boss":{"IDcard_number":"41142419921020373X","avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","company_id":1,"create_time":"2019-06-03 11:29:49","duties":"经理","email":"123@126.com","id":1,"is_self":1,"last_login_time":1559532589,"mobile":"13500135000","real_name":"郭冬生","sex":{"desc":"女","value":1},"status":1,"step":3,"update_time":"2019-08-28 17:26:41","user_id":3,"username":"张女士"},"boss_id":1,"city_id":240,"company":{"address":"陇海路1204","area_id":2656,"audit_time":"2019-06-04 10:46:12","city_id":240,"company_size":{"desc":"20-50人","value":2},"create_time":"2019-06-03 17:55:30","desc":"捷付通电子","full_name":"河南捷付通电子科技有限公司","id":1,"industries":{"desc":"互联网/IT/电子/通信","pid":0,"value":1},"is_real":1,"license":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","logo":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","province_id":16,"short_name":"捷付通","status":1,"update_time":"2019-07-25 10:13:07"},"company_id":1,"create_time":"2019-06-04 15:51:39","desc":"PHP开发职位小苏打发实打实的","education":{"desc":"高中","value":3},"highlights":{"desc":["五险一金","带薪年假","年终奖"],"value":"1,2,3"},"id":9,"job_type":{"desc":"全职","value":1},"position_id":19,"position_name":"PHP实习","province_id":16,"salary":{"desc":"4-6k","value":4},"status":{"desc":"招聘中","value":1},"update_time":"06月04日","work_experience":{"desc":"应届生","value":2}}
             * update_time : 2019-06-20 17:13:43
             * user_cv_id : 5
             */

            private int boss_position_id;
            private String create_time;
            private int id;
            private PositionBean position;
            private String update_time;
            private int user_cv_id;

            public int getBoss_position_id() {
                return boss_position_id;
            }

            public void setBoss_position_id(int boss_position_id) {
                this.boss_position_id = boss_position_id;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public PositionBean getPosition() {
                return position;
            }

            public void setPosition(PositionBean position) {
                this.position = position;
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

            public static class PositionBean {
                /**
                 * address : 陇海路1201
                 * address_info : {"address":"陇海路1201","area":"中原区","city":"郑州市","province":"河南省"}
                 * area_id : 2654
                 * boss : {"IDcard_number":"41142419921020373X","avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","company_id":1,"create_time":"2019-06-03 11:29:49","duties":"经理","email":"123@126.com","id":1,"is_self":1,"last_login_time":1559532589,"mobile":"13500135000","real_name":"郭冬生","sex":{"desc":"女","value":1},"status":1,"step":3,"update_time":"2019-08-28 17:26:41","user_id":3,"username":"张女士"}
                 * boss_id : 1
                 * city_id : 240
                 * company : {"address":"陇海路1204","area_id":2656,"audit_time":"2019-06-04 10:46:12","city_id":240,"company_size":{"desc":"20-50人","value":2},"create_time":"2019-06-03 17:55:30","desc":"捷付通电子","full_name":"河南捷付通电子科技有限公司","id":1,"industries":{"desc":"互联网/IT/电子/通信","pid":0,"value":1},"is_real":1,"license":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","logo":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","province_id":16,"short_name":"捷付通","status":1,"update_time":"2019-07-25 10:13:07"}
                 * company_id : 1
                 * create_time : 2019-06-04 15:51:39
                 * desc : PHP开发职位小苏打发实打实的
                 * education : {"desc":"高中","value":3}
                 * highlights : {"desc":["五险一金","带薪年假","年终奖"],"value":"1,2,3"}
                 * id : 9
                 * job_type : {"desc":"全职","value":1}
                 * position_id : 19
                 * position_name : PHP实习
                 * province_id : 16
                 * salary : {"desc":"4-6k","value":4}
                 * status : {"desc":"招聘中","value":1}
                 * update_time : 06月04日
                 * work_experience : {"desc":"应届生","value":2}
                 */

                private String address;
                private AddressInfoBean address_info;
                private int area_id;
                private BossBean boss;
                private int boss_id;
                private int city_id;
                private CompanyBean company;
                private int company_id;
                private String create_time;
                private String desc;
                private GmSelectBean education;
                private HighlightsBean highlights;
                private int id;
                private GmSelectBean job_type;
                private int position_id;
                private String position_name;
                private int province_id;
                private GmSelectBean salary;
                private GmSelectBean status;
                private String update_time;
                private GmSelectBean work_experience;

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public AddressInfoBean getAddress_info() {
                    return address_info;
                }

                public void setAddress_info(AddressInfoBean address_info) {
                    this.address_info = address_info;
                }

                public int getArea_id() {
                    return area_id;
                }

                public void setArea_id(int area_id) {
                    this.area_id = area_id;
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

                public int getCity_id() {
                    return city_id;
                }

                public void setCity_id(int city_id) {
                    this.city_id = city_id;
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

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public GmSelectBean getEducation() {
                    return education;
                }

                public void setEducation(GmSelectBean education) {
                    this.education = education;
                }

                public HighlightsBean getHighlights() {
                    return highlights;
                }

                public void setHighlights(HighlightsBean highlights) {
                    this.highlights = highlights;
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

                public int getPosition_id() {
                    return position_id;
                }

                public void setPosition_id(int position_id) {
                    this.position_id = position_id;
                }

                public String getPosition_name() {
                    return position_name;
                }

                public void setPosition_name(String position_name) {
                    this.position_name = position_name;
                }

                public int getProvince_id() {
                    return province_id;
                }

                public void setProvince_id(int province_id) {
                    this.province_id = province_id;
                }

                public GmSelectBean getSalary() {
                    return salary;
                }

                public void setSalary(GmSelectBean salary) {
                    this.salary = salary;
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

                public GmSelectBean getWork_experience() {
                    return work_experience;
                }

                public void setWork_experience(GmSelectBean work_experience) {
                    this.work_experience = work_experience;
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

                public static class BossBean {
                    /**
                     * IDcard_number : 41142419921020373X
                     * avatar : http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg
                     * company_id : 1
                     * create_time : 2019-06-03 11:29:49
                     * duties : 经理
                     * email : 123@126.com
                     * id : 1
                     * is_self : 1
                     * last_login_time : 1559532589
                     * mobile : 13500135000
                     * real_name : 郭冬生
                     * sex : {"desc":"女","value":1}
                     * status : 1
                     * step : 3
                     * update_time : 2019-08-28 17:26:41
                     * user_id : 3
                     * username : 张女士
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

                public static class CompanyBean {
                    /**
                     * address : 陇海路1204
                     * area_id : 2656
                     * audit_time : 2019-06-04 10:46:12
                     * city_id : 240
                     * company_size : {"desc":"20-50人","value":2}
                     * create_time : 2019-06-03 17:55:30
                     * desc : 捷付通电子
                     * full_name : 河南捷付通电子科技有限公司
                     * id : 1
                     * industries : {"desc":"互联网/IT/电子/通信","pid":0,"value":1}
                     * is_real : 1
                     * license : http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg
                     * logo : http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg
                     * province_id : 16
                     * short_name : 捷付通
                     * status : 1
                     * update_time : 2019-07-25 10:13:07
                     */

                    private String address;
                    private int area_id;
                    private String audit_time;
                    private int city_id;
                    private GmSelectBean company_size;
                    private String create_time;
                    private String desc;
                    private String full_name;
                    private int id;
                    private IndustriesBean industries;
                    private int is_real;
                    private String license;
                    private String logo;
                    private int province_id;
                    private String short_name;
                    private int status;
                    private String update_time;

                    public String getAddress() {
                        return address;
                    }

                    public void setAddress(String address) {
                        this.address = address;
                    }

                    public int getArea_id() {
                        return area_id;
                    }

                    public void setArea_id(int area_id) {
                        this.area_id = area_id;
                    }

                    public String getAudit_time() {
                        return audit_time;
                    }

                    public void setAudit_time(String audit_time) {
                        this.audit_time = audit_time;
                    }

                    public int getCity_id() {
                        return city_id;
                    }

                    public void setCity_id(int city_id) {
                        this.city_id = city_id;
                    }

                    public GmSelectBean getCompany_size() {
                        return company_size;
                    }

                    public void setCompany_size(GmSelectBean company_size) {
                        this.company_size = company_size;
                    }

                    public String getCreate_time() {
                        return create_time;
                    }

                    public void setCreate_time(String create_time) {
                        this.create_time = create_time;
                    }

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }

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

                    public IndustriesBean getIndustries() {
                        return industries;
                    }

                    public void setIndustries(IndustriesBean industries) {
                        this.industries = industries;
                    }

                    public int getIs_real() {
                        return is_real;
                    }

                    public void setIs_real(int is_real) {
                        this.is_real = is_real;
                    }

                    public String getLicense() {
                        return license;
                    }

                    public void setLicense(String license) {
                        this.license = license;
                    }

                    public String getLogo() {
                        return logo;
                    }

                    public void setLogo(String logo) {
                        this.logo = logo;
                    }

                    public int getProvince_id() {
                        return province_id;
                    }

                    public void setProvince_id(int province_id) {
                        this.province_id = province_id;
                    }

                    public String getShort_name() {
                        return short_name;
                    }

                    public void setShort_name(String short_name) {
                        this.short_name = short_name;
                    }

                    public int getStatus() {
                        return status;
                    }

                    public void setStatus(int status) {
                        this.status = status;
                    }

                    public String getUpdate_time() {
                        return update_time;
                    }

                    public void setUpdate_time(String update_time) {
                        this.update_time = update_time;
                    }



                    public static class IndustriesBean {
                        /**
                         * desc : 互联网/IT/电子/通信
                         * pid : 0
                         * value : 1
                         */

                        private String desc;
                        private int pid;
                        private int value;

                        public String getDesc() {
                            return desc;
                        }

                        public void setDesc(String desc) {
                            this.desc = desc;
                        }

                        public int getPid() {
                            return pid;
                        }

                        public void setPid(int pid) {
                            this.pid = pid;
                        }

                        public int getValue() {
                            return value;
                        }

                        public void setValue(int value) {
                            this.value = value;
                        }
                    }
                }



                public static class HighlightsBean {
                    /**
                     * desc : ["五险一金","带薪年假","年终奖"]
                     * value : 1,2,3
                     */

                    private String value;
                    private List<String> desc;

                    public String getValue() {
                        return value;
                    }

                    public void setValue(String value) {
                        this.value = value;
                    }

                    public List<String> getDesc() {
                        return desc;
                    }

                    public void setDesc(List<String> desc) {
                        this.desc = desc;
                    }
                }

            }
        }
    }
}
