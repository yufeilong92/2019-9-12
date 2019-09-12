package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/13 15:04
 * @Purpose :我的团队vo
 */
public class TeamVo extends NormalBean {

    /**
     * result : {"commission":"0.00","parentList":[{"member_add_time":"2019-05-27 17:09:47","member_mobile":"15037142280","member_name":"phone_15037142280"}]}
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
         * commission : 0.00
         * parentList : [{"member_add_time":"2019-05-27 17:09:47","member_mobile":"15037142280","member_name":"phone_15037142280"}]
         */
        /**
         * 佣金
         */
        private String commission;
        /**
         * 团队列表
         */
        private List<ParentListBean> parentList;

        public String getCommission() {
            return commission;
        }

        public void setCommission(String commission) {
            this.commission = commission;
        }

        public List<ParentListBean> getParentList() {
            return parentList;
        }

        public void setParentList(List<ParentListBean> parentList) {
            this.parentList = parentList;
        }

        public static class ParentListBean {
            /**
             * member_add_time : 2019-05-27 17:09:47
             * member_mobile : 15037142280
             * member_name : phone_15037142280
             */
            /**
             * 注册时间
             */
            private String member_add_time;
            /**
             * 手机号
             */
            private String member_mobile;
            /**
             * 会员昵称
             */
            private String member_name;

            public String getMember_add_time() {
                return member_add_time;
            }

            public void setMember_add_time(String member_add_time) {
                this.member_add_time = member_add_time;
            }

            public String getMember_mobile() {
                return member_mobile;
            }

            public void setMember_mobile(String member_mobile) {
                this.member_mobile = member_mobile;
            }

            public String getMember_name() {
                return member_name;
            }

            public void setMember_name(String member_name) {
                this.member_name = member_name;
            }
        }
    }
}
