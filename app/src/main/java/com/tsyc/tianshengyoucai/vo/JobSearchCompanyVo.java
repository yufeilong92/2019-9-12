package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/7 14:37
 * @Purpose :
 */
public class JobSearchCompanyVo extends NormalBean {

    /**
     * result : {"current_page":1,"data":[{"address":"1245678","area_id":2034,"audit_time":"2019-06-04 10:46:12","city_id":162,"company_size":{"desc":"50-99人","value":3},"create_time":"2019-06-03 17:55:30","desc":"捷付通电子","full_name":"河南捷付通电子科技有限公司","id":1,"industries":{"desc":"电子商务","pid":1,"value":2},"is_real":1,"license":"http://wwww.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","logo":"http://wwww.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","province_id":10,"short_name":"捷付通","status":1,"update_time":"2019-09-04 17:31:28"}],"last_page":1,"per_page":15,"total":1}
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
         * data : [{"address":"1245678","area_id":2034,"audit_time":"2019-06-04 10:46:12","city_id":162,"company_size":{"desc":"50-99人","value":3},"create_time":"2019-06-03 17:55:30","desc":"捷付通电子","full_name":"河南捷付通电子科技有限公司","id":1,"industries":{"desc":"电子商务","pid":1,"value":2},"is_real":1,"license":"http://wwww.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","logo":"http://wwww.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg","province_id":10,"short_name":"捷付通","status":1,"update_time":"2019-09-04 17:31:28"}]
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
             * license : http://wwww.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg
             * logo : http://wwww.jiefutong.net/uploads/home/membertag/201906/9_2019060311262174792.jpg
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
    }
}
