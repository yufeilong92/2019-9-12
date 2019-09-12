package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/8 16:08
 * @Purpose :银行卡列表
 */
public class BankListVo extends NormalBean {


    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * bank_name : 深圳农村商业银行
         * bank_sn : SRCB
         */

        private String bank_name;
        private String bank_sn;

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getBank_sn() {
            return bank_sn;
        }

        public void setBank_sn(String bank_sn) {
            this.bank_sn = bank_sn;
        }
    }
}
