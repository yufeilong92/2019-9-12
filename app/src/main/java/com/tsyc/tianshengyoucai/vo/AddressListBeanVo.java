package com.tsyc.tianshengyoucai.vo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/9 14:17
 * @Purpose :
 */
public class AddressListBeanVo implements Serializable {
    /**
     * address : 哦哦
     * address_id : 33
     * area_id : 37
     * area_info : 北京市 北京市 东城区
     * city_id : 1
     * dlyp_id : 0
     * is_default : 0
     * member_id : 18
     * mob_phone : 18317837561
     * province_id : 1
     * tel_phone :
     * true_name : 通许县
     */

    private String address;
    private int address_id;
    private int area_id;
    private String area_info;
    private int city_id;
    private int dlyp_id;
    private String is_default;
    private int member_id;
    private String mob_phone;
    private int province_id;
    private String tel_phone;
    private String true_name;





    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public String getArea_info() {
        return area_info;
    }

    public void setArea_info(String area_info) {
        this.area_info = area_info;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public int getDlyp_id() {
        return dlyp_id;
    }

    public void setDlyp_id(int dlyp_id) {
        this.dlyp_id = dlyp_id;
    }

    public String getIs_default() {
        return is_default;
    }

    public void setIs_default(String is_default) {
        this.is_default = is_default;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getMob_phone() {
        return mob_phone;
    }

    public void setMob_phone(String mob_phone) {
        this.mob_phone = mob_phone;
    }

    public int getProvince_id() {
        return province_id;
    }

    public void setProvince_id(int province_id) {
        this.province_id = province_id;
    }

    public String getTel_phone() {
        return tel_phone;
    }

    public void setTel_phone(String tel_phone) {
        this.tel_phone = tel_phone;
    }

    public String getTrue_name() {
        return true_name;
    }

    public void setTrue_name(String true_name) {
        this.true_name = true_name;
    }


}
