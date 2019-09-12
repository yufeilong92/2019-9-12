package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

import java.io.Serializable;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/13 20:19
 * @Purpose :我的银行卡列表
 */
public class MyBankListVo extends NormalBean {

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable {
        /**
         * bank_bg : http://tsyc.jiefutong.net/uploads/bank/CMBC.png
         * bank_logo : http://tsyc.jiefutong.net/uploads/bank/logo/CMBC.png
         * bank_name : 中国民生银行
         * bank_number : 6127******080843
         * bank_sn : CMBC
         * bank_type : 0
         */

        private String bank_bg;
        private String account_id;
        private String bank_logo;
        private String bank_name;
        private String bank_number;
        private String bank_sn;
        private String bank_type;

        public String getAccount_id() {
            return account_id;
        }

        public void setAccount_id(String account_id) {
            this.account_id = account_id;
        }

        public String getBank_bg() {
            return bank_bg;
        }

        public void setBank_bg(String bank_bg) {
            this.bank_bg = bank_bg;
        }

        public String getBank_logo() {
            return bank_logo;
        }

        public void setBank_logo(String bank_logo) {
            this.bank_logo = bank_logo;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getBank_number() {
            return bank_number;
        }

        public void setBank_number(String bank_number) {
            this.bank_number = bank_number;
        }

        public String getBank_sn() {
            return bank_sn;
        }

        public void setBank_sn(String bank_sn) {
            this.bank_sn = bank_sn;
        }

        public String getBank_type() {
            return bank_type;
        }

        public void setBank_type(String bank_type) {
            this.bank_type = bank_type;
        }
    }
}
