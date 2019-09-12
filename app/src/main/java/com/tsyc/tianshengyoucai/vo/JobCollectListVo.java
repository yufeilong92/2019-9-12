package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/6 18:41
 * @Purpose :
 */
public class JobCollectListVo extends NormalBean {

    /**
     * result : {"total":1,"per_page":15,"current_page":1,"last_page":1,"data":[{"id":10,"user_cv_id":8,"boss_position_id":20,"create_time":"2019-09-06 18:39:12","update_time":"2019-09-06 18:39:12","position":{"id":20,"boss_id":5,"company_id":1,"status":{"value":1,"desc":"招聘中"},"position_name":"Android","position_id":43,"job_type":{"value":1,"desc":"全职"},"work_experience":{"value":1,"desc":"在校生"},"education":{"value":1,"desc":"初中及以下"},"salary":{"value":8,"desc":"15-20k"},"desc":"兔兔啊呸哥啊你啊Spa爱怕怕啊啪啪啪啊啪啪啪怕怕拍别试啊啪啪啪阿萨排骨lrc怕怕拍apapapapapa","highlights":{"value":"4,5,7,8,9,12,13,15,16,18,19,21","desc":["周末双休","加班补助","交通补助","通讯补助","餐补","包住","员工旅游","不打卡","零食下午茶","定期体检","全勤奖","年终分红"]},"province_id":16,"city_id":240,"area_id":2662,"address":"美林河畔","create_time":"2019-09-06 11:07:50","update_time":"今天","boss":{"id":5,"user_id":6,"status":1,"step":4,"avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201909/35_2019090316484314185.jpg","username":"于飞龙","company_id":1,"duties":"安卓","email":"931697478@qq.com","real_name":"于飞龙","IDcard_number":"410222199208095552","is_self":1,"create_time":"2019-09-03 16:48:44","update_time":"2019-09-06 16:13:04","last_login_time":1567757584,"sex":{"value":2,"desc":"男"},"mobile":"18317837561"},"company":{"id":1,"full_name":"河南捷付通电子科技有限公司","short_name":"捷付通","industries":{"value":2,"desc":"电子商务","pid":1},"company_size":{"value":3,"desc":"50-99人"},"license":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","logo":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","desc":"捷付通电子","status":1,"is_real":1,"province_id":10,"city_id":162,"area_id":2034,"address":"1245678","create_time":"2019-06-03 17:55:30","update_time":"2019-09-04 17:31:28","audit_time":"2019-06-04 10:46:12"},"address_info":{"province":"河南省","city":"郑州市","area":"管城回族区","address":"美林河畔"}}}]}
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
         * total : 1
         * per_page : 15
         * current_page : 1
         * last_page : 1
         * data : [{"id":10,"user_cv_id":8,"boss_position_id":20,"create_time":"2019-09-06 18:39:12","update_time":"2019-09-06 18:39:12","position":{"id":20,"boss_id":5,"company_id":1,"status":{"value":1,"desc":"招聘中"},"position_name":"Android","position_id":43,"job_type":{"value":1,"desc":"全职"},"work_experience":{"value":1,"desc":"在校生"},"education":{"value":1,"desc":"初中及以下"},"salary":{"value":8,"desc":"15-20k"},"desc":"兔兔啊呸哥啊你啊Spa爱怕怕啊啪啪啪啊啪啪啪怕怕拍别试啊啪啪啪阿萨排骨lrc怕怕拍apapapapapa","highlights":{"value":"4,5,7,8,9,12,13,15,16,18,19,21","desc":["周末双休","加班补助","交通补助","通讯补助","餐补","包住","员工旅游","不打卡","零食下午茶","定期体检","全勤奖","年终分红"]},"province_id":16,"city_id":240,"area_id":2662,"address":"美林河畔","create_time":"2019-09-06 11:07:50","update_time":"今天","boss":{"id":5,"user_id":6,"status":1,"step":4,"avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201909/35_2019090316484314185.jpg","username":"于飞龙","company_id":1,"duties":"安卓","email":"931697478@qq.com","real_name":"于飞龙","IDcard_number":"410222199208095552","is_self":1,"create_time":"2019-09-03 16:48:44","update_time":"2019-09-06 16:13:04","last_login_time":1567757584,"sex":{"value":2,"desc":"男"},"mobile":"18317837561"},"company":{"id":1,"full_name":"河南捷付通电子科技有限公司","short_name":"捷付通","industries":{"value":2,"desc":"电子商务","pid":1},"company_size":{"value":3,"desc":"50-99人"},"license":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","logo":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","desc":"捷付通电子","status":1,"is_real":1,"province_id":10,"city_id":162,"area_id":2034,"address":"1245678","create_time":"2019-06-03 17:55:30","update_time":"2019-09-04 17:31:28","audit_time":"2019-06-04 10:46:12"},"address_info":{"province":"河南省","city":"郑州市","area":"管城回族区","address":"美林河畔"}}}]
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
             * id : 10
             * user_cv_id : 8
             * boss_position_id : 20
             * create_time : 2019-09-06 18:39:12
             * update_time : 2019-09-06 18:39:12
             * position : {"id":20,"boss_id":5,"company_id":1,"status":{"value":1,"desc":"招聘中"},"position_name":"Android","position_id":43,"job_type":{"value":1,"desc":"全职"},"work_experience":{"value":1,"desc":"在校生"},"education":{"value":1,"desc":"初中及以下"},"salary":{"value":8,"desc":"15-20k"},"desc":"兔兔啊呸哥啊你啊Spa爱怕怕啊啪啪啪啊啪啪啪怕怕拍别试啊啪啪啪阿萨排骨lrc怕怕拍apapapapapa","highlights":{"value":"4,5,7,8,9,12,13,15,16,18,19,21","desc":["周末双休","加班补助","交通补助","通讯补助","餐补","包住","员工旅游","不打卡","零食下午茶","定期体检","全勤奖","年终分红"]},"province_id":16,"city_id":240,"area_id":2662,"address":"美林河畔","create_time":"2019-09-06 11:07:50","update_time":"今天","boss":{"id":5,"user_id":6,"status":1,"step":4,"avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201909/35_2019090316484314185.jpg","username":"于飞龙","company_id":1,"duties":"安卓","email":"931697478@qq.com","real_name":"于飞龙","IDcard_number":"410222199208095552","is_self":1,"create_time":"2019-09-03 16:48:44","update_time":"2019-09-06 16:13:04","last_login_time":1567757584,"sex":{"value":2,"desc":"男"},"mobile":"18317837561"},"company":{"id":1,"full_name":"河南捷付通电子科技有限公司","short_name":"捷付通","industries":{"value":2,"desc":"电子商务","pid":1},"company_size":{"value":3,"desc":"50-99人"},"license":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","logo":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","desc":"捷付通电子","status":1,"is_real":1,"province_id":10,"city_id":162,"area_id":2034,"address":"1245678","create_time":"2019-06-03 17:55:30","update_time":"2019-09-04 17:31:28","audit_time":"2019-06-04 10:46:12"},"address_info":{"province":"河南省","city":"郑州市","area":"管城回族区","address":"美林河畔"}}
             */

            private int id;
            private int user_cv_id;
            private int boss_position_id;
            private String create_time;
            private String update_time;
            private PositionBean position;

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

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public PositionBean getPosition() {
                return position;
            }

            public void setPosition(PositionBean position) {
                this.position = position;
            }

            public static class PositionBean {
                /**
                 * id : 20
                 * boss_id : 5
                 * company_id : 1
                 * status : {"value":1,"desc":"招聘中"}
                 * position_name : Android
                 * position_id : 43
                 * job_type : {"value":1,"desc":"全职"}
                 * work_experience : {"value":1,"desc":"在校生"}
                 * education : {"value":1,"desc":"初中及以下"}
                 * salary : {"value":8,"desc":"15-20k"}
                 * desc : 兔兔啊呸哥啊你啊Spa爱怕怕啊啪啪啪啊啪啪啪怕怕拍别试啊啪啪啪阿萨排骨lrc怕怕拍apapapapapa
                 * highlights : {"value":"4,5,7,8,9,12,13,15,16,18,19,21","desc":["周末双休","加班补助","交通补助","通讯补助","餐补","包住","员工旅游","不打卡","零食下午茶","定期体检","全勤奖","年终分红"]}
                 * province_id : 16
                 * city_id : 240
                 * area_id : 2662
                 * address : 美林河畔
                 * create_time : 2019-09-06 11:07:50
                 * update_time : 今天
                 * boss : {"id":5,"user_id":6,"status":1,"step":4,"avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201909/35_2019090316484314185.jpg","username":"于飞龙","company_id":1,"duties":"安卓","email":"931697478@qq.com","real_name":"于飞龙","IDcard_number":"410222199208095552","is_self":1,"create_time":"2019-09-03 16:48:44","update_time":"2019-09-06 16:13:04","last_login_time":1567757584,"sex":{"value":2,"desc":"男"},"mobile":"18317837561"}
                 * company : {"id":1,"full_name":"河南捷付通电子科技有限公司","short_name":"捷付通","industries":{"value":2,"desc":"电子商务","pid":1},"company_size":{"value":3,"desc":"50-99人"},"license":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","logo":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","desc":"捷付通电子","status":1,"is_real":1,"province_id":10,"city_id":162,"area_id":2034,"address":"1245678","create_time":"2019-06-03 17:55:30","update_time":"2019-09-04 17:31:28","audit_time":"2019-06-04 10:46:12"}
                 * address_info : {"province":"河南省","city":"郑州市","area":"管城回族区","address":"美林河畔"}
                 */

                private int id;
                private int boss_id;
                private int company_id;
                private GmSelectBean status;
                private String position_name;
                private int position_id;
                private GmSelectBean job_type;
                private GmSelectBean work_experience;
                private GmSelectBean education;
                private GmSelectBean salary;
                private String desc;
                private HighlightsBean highlights;
                private int province_id;
                private int city_id;
                private int area_id;
                private String address;
                private String create_time;
                private String update_time;
                private BossBean boss;
                private CompanyBean company;
                private AddressInfoBean address_info;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
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

                public GmSelectBean getStatus() {
                    return status;
                }

                public void setStatus(GmSelectBean status) {
                    this.status = status;
                }

                public String getPosition_name() {
                    return position_name;
                }

                public void setPosition_name(String position_name) {
                    this.position_name = position_name;
                }

                public int getPosition_id() {
                    return position_id;
                }

                public void setPosition_id(int position_id) {
                    this.position_id = position_id;
                }

                public GmSelectBean getJob_type() {
                    return job_type;
                }

                public void setJob_type(GmSelectBean job_type) {
                    this.job_type = job_type;
                }

                public GmSelectBean getWork_experience() {
                    return work_experience;
                }

                public void setWork_experience(GmSelectBean work_experience) {
                    this.work_experience = work_experience;
                }

                public GmSelectBean getEducation() {
                    return education;
                }

                public void setEducation(GmSelectBean education) {
                    this.education = education;
                }

                public GmSelectBean getSalary() {
                    return salary;
                }

                public void setSalary(GmSelectBean salary) {
                    this.salary = salary;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public HighlightsBean getHighlights() {
                    return highlights;
                }

                public void setHighlights(HighlightsBean highlights) {
                    this.highlights = highlights;
                }

                public int getProvince_id() {
                    return province_id;
                }

                public void setProvince_id(int province_id) {
                    this.province_id = province_id;
                }

                public int getCity_id() {
                    return city_id;
                }

                public void setCity_id(int city_id) {
                    this.city_id = city_id;
                }

                public int getArea_id() {
                    return area_id;
                }

                public void setArea_id(int area_id) {
                    this.area_id = area_id;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
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

                public BossBean getBoss() {
                    return boss;
                }

                public void setBoss(BossBean boss) {
                    this.boss = boss;
                }

                public CompanyBean getCompany() {
                    return company;
                }

                public void setCompany(CompanyBean company) {
                    this.company = company;
                }

                public AddressInfoBean getAddress_info() {
                    return address_info;
                }

                public void setAddress_info(AddressInfoBean address_info) {
                    this.address_info = address_info;
                }



                public static class HighlightsBean {
                    /**
                     * value : 4,5,7,8,9,12,13,15,16,18,19,21
                     * desc : ["周末双休","加班补助","交通补助","通讯补助","餐补","包住","员工旅游","不打卡","零食下午茶","定期体检","全勤奖","年终分红"]
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

                public static class BossBean {
                    /**
                     * id : 5
                     * user_id : 6
                     * status : 1
                     * step : 4
                     * avatar : http://tsyc.jiefutong.net/uploads/home/membertag/201909/35_2019090316484314185.jpg
                     * username : 于飞龙
                     * company_id : 1
                     * duties : 安卓
                     * email : 931697478@qq.com
                     * real_name : 于飞龙
                     * IDcard_number : 410222199208095552
                     * is_self : 1
                     * create_time : 2019-09-03 16:48:44
                     * update_time : 2019-09-06 16:13:04
                     * last_login_time : 1567757584
                     * sex : {"value":2,"desc":"男"}
                     * mobile : 18317837561
                     */

                    private int id;
                    private int user_id;
                    private int status;
                    private int step;
                    private String avatar;
                    private String username;
                    private int company_id;
                    private String duties;
                    private String email;
                    private String real_name;
                    private String IDcard_number;
                    private int is_self;
                    private String create_time;
                    private String update_time;
                    private int last_login_time;
                    private GmSelectBean sex;
                    private String mobile;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public int getUser_id() {
                        return user_id;
                    }

                    public void setUser_id(int user_id) {
                        this.user_id = user_id;
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

                    public int getCompany_id() {
                        return company_id;
                    }

                    public void setCompany_id(int company_id) {
                        this.company_id = company_id;
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

                    public String getReal_name() {
                        return real_name;
                    }

                    public void setReal_name(String real_name) {
                        this.real_name = real_name;
                    }

                    public String getIDcard_number() {
                        return IDcard_number;
                    }

                    public void setIDcard_number(String IDcard_number) {
                        this.IDcard_number = IDcard_number;
                    }

                    public int getIs_self() {
                        return is_self;
                    }

                    public void setIs_self(int is_self) {
                        this.is_self = is_self;
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

                    public int getLast_login_time() {
                        return last_login_time;
                    }

                    public void setLast_login_time(int last_login_time) {
                        this.last_login_time = last_login_time;
                    }

                    public GmSelectBean getSex() {
                        return sex;
                    }

                    public void setSex(GmSelectBean sex) {
                        this.sex = sex;
                    }

                    public String getMobile() {
                        return mobile;
                    }

                    public void setMobile(String mobile) {
                        this.mobile = mobile;
                    }


                }

                public static class CompanyBean {
                    /**
                     * id : 1
                     * full_name : 河南捷付通电子科技有限公司
                     * short_name : 捷付通
                     * industries : {"value":2,"desc":"电子商务","pid":1}
                     * company_size : {"value":3,"desc":"50-99人"}
                     * license : http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg
                     * logo : http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg
                     * desc : 捷付通电子
                     * status : 1
                     * is_real : 1
                     * province_id : 10
                     * city_id : 162
                     * area_id : 2034
                     * address : 1245678
                     * create_time : 2019-06-03 17:55:30
                     * update_time : 2019-09-04 17:31:28
                     * audit_time : 2019-06-04 10:46:12
                     */

                    private int id;
                    private String full_name;
                    private String short_name;
                    private IndustriesBean industries;
                    private GmSelectBean company_size;
                    private String license;
                    private String logo;
                    private String desc;
                    private int status;
                    private int is_real;
                    private int province_id;
                    private int city_id;
                    private int area_id;
                    private String address;
                    private String create_time;
                    private String update_time;
                    private String audit_time;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getFull_name() {
                        return full_name;
                    }

                    public void setFull_name(String full_name) {
                        this.full_name = full_name;
                    }

                    public String getShort_name() {
                        return short_name;
                    }

                    public void setShort_name(String short_name) {
                        this.short_name = short_name;
                    }

                    public IndustriesBean getIndustries() {
                        return industries;
                    }

                    public void setIndustries(IndustriesBean industries) {
                        this.industries = industries;
                    }

                    public GmSelectBean getCompany_size() {
                        return company_size;
                    }

                    public void setCompany_size(GmSelectBean company_size) {
                        this.company_size = company_size;
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

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }

                    public int getStatus() {
                        return status;
                    }

                    public void setStatus(int status) {
                        this.status = status;
                    }

                    public int getIs_real() {
                        return is_real;
                    }

                    public void setIs_real(int is_real) {
                        this.is_real = is_real;
                    }

                    public int getProvince_id() {
                        return province_id;
                    }

                    public void setProvince_id(int province_id) {
                        this.province_id = province_id;
                    }

                    public int getCity_id() {
                        return city_id;
                    }

                    public void setCity_id(int city_id) {
                        this.city_id = city_id;
                    }

                    public int getArea_id() {
                        return area_id;
                    }

                    public void setArea_id(int area_id) {
                        this.area_id = area_id;
                    }

                    public String getAddress() {
                        return address;
                    }

                    public void setAddress(String address) {
                        this.address = address;
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

                    public String getAudit_time() {
                        return audit_time;
                    }

                    public void setAudit_time(String audit_time) {
                        this.audit_time = audit_time;
                    }

                    public static class IndustriesBean {
                        /**
                         * value : 2
                         * desc : 电子商务
                         * pid : 1
                         */

                        private int value;
                        private String desc;
                        private int pid;

                        public int getValue() {
                            return value;
                        }

                        public void setValue(int value) {
                            this.value = value;
                        }

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
                    }


                }

                public static class AddressInfoBean {
                    /**
                     * province : 河南省
                     * city : 郑州市
                     * area : 管城回族区
                     * address : 美林河畔
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
            }
        }
    }
}
