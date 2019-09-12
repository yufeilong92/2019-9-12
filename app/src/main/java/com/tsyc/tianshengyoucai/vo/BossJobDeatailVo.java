package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/5 11:48
 * @Purpose :
 */
public class BossJobDeatailVo extends NormalBean {

    /**
     * result : {"address":"龙海101","address_info":{"address":"龙海101","area":"中原区","city":"郑州市","province":"河南省"},"area_id":2654,"boss_id":1,"city_id":240,"company":{"address":"1245678","area_id":2034,"audit_time":"2019-06-04 10:46:12","city_id":162,"company_size":{"desc":"50-99人","value":3},"create_time":"2019-06-03 17:55:30","desc":"捷付通电子","full_name":"河南捷付通电子科技有限公司","id":1,"industries":{"desc":"电子商务","pid":1,"value":2},"is_real":1,"license":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","logo":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","province_id":10,"short_name":"捷付通","status":1,"update_time":"2019-09-04 17:31:28"},"company_id":1,"create_time":"2019-06-20 14:31:33","default_position_name":"Android","desc":"安卓开发","education":{"desc":"本科","value":5},"highlights":{"desc":["五险一金","带薪年假","周末双休","节日福利"],"value":"1,2,4,6"},"id":11,"job_type":{"desc":"全职","value":1},"look_num":3,"position_id":43,"position_name":"安卓","province_id":16,"salary":{"desc":"10-15k","value":7},"status":{"desc":"招聘中","value":1},"talk_num":1,"update_time":"09月04日","work_experience":{"desc":"1-3年","value":4}}
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
         * address : 龙海101
         * address_info : {"address":"龙海101","area":"中原区","city":"郑州市","province":"河南省"}
         * area_id : 2654
         * boss_id : 1
         * city_id : 240
         * company : {"address":"1245678","area_id":2034,"audit_time":"2019-06-04 10:46:12","city_id":162,"company_size":{"desc":"50-99人","value":3},"create_time":"2019-06-03 17:55:30","desc":"捷付通电子","full_name":"河南捷付通电子科技有限公司","id":1,"industries":{"desc":"电子商务","pid":1,"value":2},"is_real":1,"license":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","logo":"http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","province_id":10,"short_name":"捷付通","status":1,"update_time":"2019-09-04 17:31:28"}
         * company_id : 1
         * create_time : 2019-06-20 14:31:33
         * default_position_name : Android
         * desc : 安卓开发
         * education : {"desc":"本科","value":5}
         * highlights : {"desc":["五险一金","带薪年假","周末双休","节日福利"],"value":"1,2,4,6"}
         * id : 11
         * job_type : {"desc":"全职","value":1}
         * look_num : 3
         * position_id : 43
         * position_name : 安卓
         * province_id : 16
         * salary : {"desc":"10-15k","value":7}
         * status : {"desc":"招聘中","value":1}
         * talk_num : 1
         * update_time : 09月04日
         * work_experience : {"desc":"1-3年","value":4}
         */

        private String address;
        private AddressInfoBean address_info;
        private int area_id;
        private int boss_id;
        private int city_id;
        private CompanyBean company;
        private int company_id;
        private String create_time;
        private String default_position_name;
        private String desc;
        private GmSelectBean education;
        private HighlightsBean highlights;
        private int id;
        private GmSelectBean job_type;
        private int look_num;
        private int position_id;
        private String position_name;
        private int province_id;
        private GmSelectBean salary;
        private GmSelectBean status;
        private int talk_num;
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

        public String getDefault_position_name() {
            return default_position_name;
        }

        public void setDefault_position_name(String default_position_name) {
            this.default_position_name = default_position_name;
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

        public int getLook_num() {
            return look_num;
        }

        public void setLook_num(int look_num) {
            this.look_num = look_num;
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

        public int getTalk_num() {
            return talk_num;
        }

        public void setTalk_num(int talk_num) {
            this.talk_num = talk_num;
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
             * address : 龙海101
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

        public static class CompanyBean {
            /**
             * address : 1245678
             * area_id : 2034
             * audit_time : 2019-06-04 10:46:12
             * city_id : 162
             * company_size : {"desc":"50-99人","value":3}
             * create_time : 2019-06-03 17:55:30
             * desc : 捷付通电子
             * full_name : 河南捷付通电子科技有限公司
             * id : 1
             * industries : {"desc":"电子商务","pid":1,"value":2}
             * is_real : 1
             * license : http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg
             * logo : http://tsyc.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg
             * province_id : 10
             * short_name : 捷付通
             * status : 1
             * update_time : 2019-09-04 17:31:28
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
                 * desc : 电子商务
                 * pid : 1
                 * value : 2
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
             * desc : ["五险一金","带薪年假","周末双休","节日福利"]
             * value : 1,2,4,6
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
