package com.tsyc.tianshengyoucai.vo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/17 18:16
 * @Purpose :
 */
public class CouponListBean implements Parcelable {

    /**
     * voucher_end_date : 2019-06-09 00:00:00
     * id : 1
     * voucher_limit : 100
     * voucher_price : 10
     * voucher_start_date : 2019-06-04 00:00:00
     * voucher_type : 1
     */

    private String voucher_end_date;
    private int id;
    private String voucher_limit;
    private String voucher_price;
    private String voucher_start_date;
    private int voucher_type;

    protected CouponListBean(Parcel in) {
        voucher_end_date = in.readString();
        id = in.readInt();
        voucher_limit = in.readString();
        voucher_price = in.readString();
        voucher_start_date = in.readString();
        voucher_type = in.readInt();
    }

    public static final Creator<CouponListBean> CREATOR = new Creator<CouponListBean>() {
        @Override
        public CouponListBean createFromParcel(Parcel in) {
            return new CouponListBean(in);
        }

        @Override
        public CouponListBean[] newArray(int size) {
            return new CouponListBean[size];
        }
    };

    public String getVoucher_end_date() {
        return voucher_end_date;
    }

    public void setVoucher_end_date(String voucher_end_date) {
        this.voucher_end_date = voucher_end_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoucher_limit() {
        return voucher_limit;
    }

    public void setVoucher_limit(String voucher_limit) {
        this.voucher_limit = voucher_limit;
    }

    public Double getVoucher_price() {
        return  Double.parseDouble(voucher_price);
    }

    public void setVoucher_price(String voucher_price) {
        this.voucher_price = voucher_price;
    }

    public String getVoucher_start_date() {
        return voucher_start_date;
    }

    public void setVoucher_start_date(String voucher_start_date) {
        this.voucher_start_date = voucher_start_date;
    }

    public int getVoucher_type() {
        return voucher_type;
    }

    public void setVoucher_type(int voucher_type) {
        this.voucher_type = voucher_type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(voucher_end_date);
        dest.writeInt(id);
        dest.writeString(voucher_limit);
        dest.writeString(voucher_price);
        dest.writeString(voucher_start_date);
        dest.writeInt(voucher_type);
    }
}