package com.tsyc.tianshengyoucai.model.bean;

/**
 * author：van
 * CreateTime：2019/8/18
 * File description：扫描核销码
 */
public class ScanJsonBean {

    // {"money":"43af37db5ea4ce9a","type":"verify","cashier_id":0}

    /**
     * money : 43af37db5ea4ce9a
     * type : verify
     * cashier_id : 0
     */

    private String money;
    private String type;
    private int cashier_id;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCashier_id() {
        return cashier_id;
    }

    public void setCashier_id(int cashier_id) {
        this.cashier_id = cashier_id;
    }
}
