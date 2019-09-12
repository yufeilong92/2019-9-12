package com.tsyc.tianshengyoucai.vo;

import java.io.Serializable;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/17 15:45
 * @Purpose :
 */
public class RedpacketVo  implements Serializable {


    /**
     * id : 36
     * voucher_end_date : 2019-08-18 14:31
     * voucher_price : 10
     */

    private int id;
    private String voucher_end_date;
    private String voucher_price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoucher_end_date() {
        return voucher_end_date;
    }

    public void setVoucher_end_date(String voucher_end_date) {
        this.voucher_end_date = voucher_end_date;
    }

    public Double getVoucher_price() {
        return Double.parseDouble(voucher_price);
    }

    public void setVoucher_price(String voucher_price) {
        this.voucher_price = voucher_price;
    }
}
