package com.tsyc.tianshengyoucai.vo;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/7 17:46
 * @Purpose :消费记录
 */
public class ExpenseVo {

    /**
     * money : +12.00
     * time : 2019-05-28 10:50:05
     * beizhu : 支付宝充值
     */

    private String money;
    private String time;
    private String beizhu;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }
}
