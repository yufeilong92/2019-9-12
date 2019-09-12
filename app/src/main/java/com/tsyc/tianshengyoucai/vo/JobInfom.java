package com.tsyc.tianshengyoucai.vo;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/19 15:51
 * @Purpose :工作者信息
 */
public class JobInfom {
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 生日
     */
    private int year;
    private int month;
    private int day;
    /**
     * 工作日
     */
    private int yearWorkTime;
    private int monthWorkTime;
    private int dayWorkTime;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String area;
    private int provinceId;
    private int cityId;
    private int areaId;
    /**
     * 手机
     */
    private String phone;
    /**
     * email
     */
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYearWorkTime() {
        return yearWorkTime;
    }

    public void setYearWorkTime(int yearWorkTime) {
        this.yearWorkTime = yearWorkTime;
    }

    public int getMonthWorkTime() {
        return monthWorkTime;
    }

    public void setMonthWorkTime(int monthWorkTime) {
        this.monthWorkTime = monthWorkTime;
    }

    public int getDayWorkTime() {
        return dayWorkTime;
    }

    public void setDayWorkTime(int dayWorkTime) {
        this.dayWorkTime = dayWorkTime;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
