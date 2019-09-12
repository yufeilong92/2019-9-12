package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

import java.io.Serializable;

public class UserInfomVo extends NormalBean implements Serializable {

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }


    public static class ResultBean implements Serializable  {
         //昵称
        private String user_name;
        private int member_id;
        /**
         * 用户头像
         */
        private String avatar;
        /**
         * 金币
         */
        private int member_points;
        /**
         * 邀请码
         */
        private String invite_code;
        /**
         * 余额
         */
        private double my_balance;
        private String phone;
        /**
         * ：0普通会员 1 合伙人
         */
        private int grade_status;
        /**
         * ：等级名称
         */
        private String my_grade;
        /**
         * 优惠券数量
         */
        private int myvoucher;
        /**
         * 绑定支付宝状态
         */
        private int bindAliPayStatus;
        /**
         * 绑定微信状态
         */
        private int bindWeChatStatus;
        /**
         * 绑定银行卡状态
         */
        private int bindBankStatus;
        /**
         * 用户店铺状态 0 关闭 1正常 2审核中 4 没有店铺
         */
        private int is_store;
        /**
         * 邀请二维码
         */
        private String invite_qrcode;
        /**
         * 分享链接
         */
        private String share_link;
        /**
         * 支付宝姓名
         */
        private String member_truename;

        /**
         * 支付宝账号
         */
        private String member_alipay;
        /**
         * 注：是否设置支付密码 1 已设置 0 未设置
         */
        private int is_paypwd;
        /**
         * key 自己添加
         */
        private String key;

        /**
         * is_inner 是否是内部人  kvip 0 不是 1 是
         * @return
         */
        private int is_inner;

        /**
         *  性别
         */
        private String gender;

        private String member_sex;

        public String getMember_sex() {
            return member_sex;
        }

        public void setMember_sex(String member_sex) {
            this.member_sex = member_sex;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getIs_inner() {
            return is_inner;
        }

        public void setIs_inner(int is_inner) {
            this.is_inner = is_inner;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public int getMember_id() {
            return member_id;
        }

        public void setMember_id(int member_id) {
            this.member_id = member_id;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getMember_points() {
            return member_points;
        }

        public void setMember_points(int member_points) {
            this.member_points = member_points;
        }

        public String getInvite_code() {
            return invite_code;
        }

        public void setInvite_code(String invite_code) {
            this.invite_code = invite_code;
        }

        public double getMy_balance() {
            return my_balance;
        }

        public void setMy_balance(double my_balance) {
            this.my_balance = my_balance;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getGrade_status() {
            return grade_status;
        }

        public void setGrade_status(int grade_status) {
            this.grade_status = grade_status;
        }

        public String getMy_grade() {
            return my_grade;
        }

        public void setMy_grade(String my_grade) {
            this.my_grade = my_grade;
        }

        public int getMyvoucher() {
            return myvoucher;
        }

        public void setMyvoucher(int myvoucher) {
            this.myvoucher = myvoucher;
        }

        public int getBindAliPayStatus() {
            return bindAliPayStatus;
        }

        public void setBindAliPayStatus(int bindAliPayStatus) {
            this.bindAliPayStatus = bindAliPayStatus;
        }

        public int getBindWeChatStatus() {
            return bindWeChatStatus;
        }

        public void setBindWeChatStatus(int bindWeChatStatus) {
            this.bindWeChatStatus = bindWeChatStatus;
        }

        public int getBindBankStatus() {
            return bindBankStatus;
        }

        public void setBindBankStatus(int bindBankStatus) {
            this.bindBankStatus = bindBankStatus;
        }

        public int getIs_store() {
            return is_store;
        }

        public void setIs_store(int is_store) {
            this.is_store = is_store;
        }

        public String getInvite_qrcode() {
            return invite_qrcode;
        }

        public void setInvite_qrcode(String invite_qrcode) {
            this.invite_qrcode = invite_qrcode;
        }

        public String getShare_link() {
            return share_link;
        }

        public void setShare_link(String share_link) {
            this.share_link = share_link;
        }

        public String getMember_truename() {
            return member_truename;
        }

        public void setMember_truename(String member_truename) {
            this.member_truename = member_truename;
        }

        public String getMember_alipay() {
            return member_alipay;
        }

        public void setMember_alipay(String member_alipay) {
            this.member_alipay = member_alipay;
        }

        public int getIs_paypwd() {
            return is_paypwd;
        }

        public void setIs_paypwd(int is_paypwd) {
            this.is_paypwd = is_paypwd;
        }
    }
}
