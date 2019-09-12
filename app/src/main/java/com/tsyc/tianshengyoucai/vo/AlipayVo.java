package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/14 14:44
 * @Purpose :支付吧
 */
public class AlipayVo extends NormalBean {

    /**
     * result : {"alipay":{"account":"15037142280","real_name":"张文平"},"bindAliPayStatus":1}
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
         * alipay : {"account":"15037142280","real_name":"张文平"}
         * bindAliPayStatus : 1
         */

        private AlipayBean alipay;
        private int bindAliPayStatus;

        public AlipayBean getAlipay() {
            return alipay;
        }

        public void setAlipay(AlipayBean alipay) {
            this.alipay = alipay;
        }

        public int getBindAliPayStatus() {
            return bindAliPayStatus;
        }

        public void setBindAliPayStatus(int bindAliPayStatus) {
            this.bindAliPayStatus = bindAliPayStatus;
        }

        public static class AlipayBean {
            /**
             * account : 15037142280
             * real_name : 张文平
             */

            private String account;
            private String real_name;
            private String member_mobile;

            public String getMember_mobile() {
                return member_mobile;
            }

            public void setMember_mobile(String member_mobile) {
                this.member_mobile = member_mobile;
            }

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public String getReal_name() {
                return real_name;
            }

            public void setReal_name(String real_name) {
                this.real_name = real_name;
            }
        }
    }
}
