package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/27 09:16
 * @Purpose :招聘vo
 */
public class RcBannerVo extends NormalBean {

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * adv_id : 28
         * ap_id : 3
         * adv_title : 招聘-轮播图2
         * adv_link :
         * adv_code : http://tsyc.jiefutong.net/uploads/home/adv/5d0c3b77a8af2.png
         * adv_start_date : 1561046400
         * adv_end_date : 1592582400
         * adv_sort : 0
         * adv_enabled : 1
         * member_id : 0
         * member_name : null
         * click_num : 0
         * is_allow : 1
         * buy_style : null
         * goldpay : null
         */

        private int adv_id;
        private int ap_id;
        private String adv_title;
        private String adv_link;
        private String adv_code;
        private int adv_start_date;
        private int adv_end_date;
        private int adv_sort;
        private int adv_enabled;
        private int member_id;
        private Object member_name;
        private int click_num;
        private int is_allow;
        private Object buy_style;
        private Object goldpay;

        public int getAdv_id() {
            return adv_id;
        }

        public void setAdv_id(int adv_id) {
            this.adv_id = adv_id;
        }

        public int getAp_id() {
            return ap_id;
        }

        public void setAp_id(int ap_id) {
            this.ap_id = ap_id;
        }

        public String getAdv_title() {
            return adv_title;
        }

        public void setAdv_title(String adv_title) {
            this.adv_title = adv_title;
        }

        public String getAdv_link() {
            return adv_link;
        }

        public void setAdv_link(String adv_link) {
            this.adv_link = adv_link;
        }

        public String getAdv_code() {
            return adv_code;
        }

        public void setAdv_code(String adv_code) {
            this.adv_code = adv_code;
        }

        public int getAdv_start_date() {
            return adv_start_date;
        }

        public void setAdv_start_date(int adv_start_date) {
            this.adv_start_date = adv_start_date;
        }

        public int getAdv_end_date() {
            return adv_end_date;
        }

        public void setAdv_end_date(int adv_end_date) {
            this.adv_end_date = adv_end_date;
        }

        public int getAdv_sort() {
            return adv_sort;
        }

        public void setAdv_sort(int adv_sort) {
            this.adv_sort = adv_sort;
        }

        public int getAdv_enabled() {
            return adv_enabled;
        }

        public void setAdv_enabled(int adv_enabled) {
            this.adv_enabled = adv_enabled;
        }

        public int getMember_id() {
            return member_id;
        }

        public void setMember_id(int member_id) {
            this.member_id = member_id;
        }

        public Object getMember_name() {
            return member_name;
        }

        public void setMember_name(Object member_name) {
            this.member_name = member_name;
        }

        public int getClick_num() {
            return click_num;
        }

        public void setClick_num(int click_num) {
            this.click_num = click_num;
        }

        public int getIs_allow() {
            return is_allow;
        }

        public void setIs_allow(int is_allow) {
            this.is_allow = is_allow;
        }

        public Object getBuy_style() {
            return buy_style;
        }

        public void setBuy_style(Object buy_style) {
            this.buy_style = buy_style;
        }

        public Object getGoldpay() {
            return goldpay;
        }

        public void setGoldpay(Object goldpay) {
            this.goldpay = goldpay;
        }
    }
}
