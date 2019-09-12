package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/28 10:30
 * @Purpose :
 */
public class SelectKeyVo extends NormalBean {

    /**
     * result : {"company_size":[{"desc":"0-20人","value":1},{"desc":"20-50人","value":2},{"desc":"50-99人","value":3},{"desc":"100-499人","value":4},{"desc":"500-999人","value":5},{"desc":"1000-9999人","value":6},{"desc":"10000人以上","value":7}],"current_status":[{"desc":"离职-随时到岗","value":1},{"desc":"在职-月内到岗","value":2},{"desc":"在职-考虑机会","value":3},{"desc":"在职-暂不考虑","value":4}],"education":[{"desc":"初中及以下","value":1},{"desc":"中专/中技","value":2},{"desc":"高中","value":3},{"desc":"大专","value":4},{"desc":"本科","value":5},{"desc":"硕士","value":6},{"desc":"博士","value":7}],"expected_salary":[{"desc":"面议","value":1},{"desc":"2k以下","value":2},{"desc":"2-4k","value":3},{"desc":"4-6k","value":4},{"desc":"6-8k","value":5},{"desc":"8-10k","value":6},{"desc":"10-15k","value":7},{"desc":"15-20k","value":8},{"desc":"20-30k","value":9},{"desc":"30-50k","value":10},{"desc":"50k以上","value":11}],"experience":[{"desc":"在校生","value":1},{"desc":"应届生","value":2},{"desc":"一年以内","value":3},{"desc":"1-3年","value":4},{"desc":"3-5年","value":5},{"desc":"5-10年","value":6},{"desc":"10年以上","value":7}],"highlights":[{"desc":"五险一金","value":1},{"desc":"带薪年假","value":2},{"desc":"年终奖","value":3},{"desc":"周末双休","value":4},{"desc":"加班补助","value":5},{"desc":"节日福利","value":6},{"desc":"交通补助","value":7},{"desc":"通讯补助","value":8},{"desc":"餐补","value":9},{"desc":"房补","value":10},{"desc":"包吃","value":11},{"desc":"包住","value":12},{"desc":"员工旅游","value":13},{"desc":"公司氛围好","value":14},{"desc":"不打卡","value":15},{"desc":"零食下午茶","value":16},{"desc":"地铁周边","value":17},{"desc":"定期体检","value":18},{"desc":"全勤奖","value":19},{"desc":"股票期权","value":20},{"desc":"年终分红","value":21}],"job_type":[{"desc":"全职","value":1},{"desc":"兼职","value":2}]}
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<GmSelectBean> company_size;
        private List<GmSelectBean> current_status;
        private List<GmSelectBean> education;
        private List<GmSelectBean> expected_salary;
        private List<GmSelectBean> experience;
        private List<GmSelectBean> highlights;
        private List<GmSelectBean> job_type;

        public List<GmSelectBean> getCompany_size() {
            return company_size;
        }

        public void setCompany_size(List<GmSelectBean> company_size) {
            this.company_size = company_size;
        }

        public List<GmSelectBean> getCurrent_status() {
            return current_status;
        }

        public void setCurrent_status(List<GmSelectBean> current_status) {
            this.current_status = current_status;
        }

        public List<GmSelectBean> getEducation() {
            return education;
        }

        public void setEducation(List<GmSelectBean> education) {
            this.education = education;
        }

        public List<GmSelectBean> getExpected_salary() {
            return expected_salary;
        }

        public void setExpected_salary(List<GmSelectBean> expected_salary) {
            this.expected_salary = expected_salary;
        }

        public List<GmSelectBean> getExperience() {
            return experience;
        }

        public void setExperience(List<GmSelectBean> experience) {
            this.experience = experience;
        }

        public List<GmSelectBean> getHighlights() {
            return highlights;
        }

        public void setHighlights(List<GmSelectBean> highlights) {
            this.highlights = highlights;
        }

        public List<GmSelectBean> getJob_type() {
            return job_type;
        }

        public void setJob_type(List<GmSelectBean> job_type) {
            this.job_type = job_type;
        }



    }
}
