package com.tsyc.tianshengyoucai.Eventbuss;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/17 11:14
 * @Purpose :
 */
public class UserInfomEvent {
    private String refresh;

    public UserInfomEvent(String refresh) {
        this.refresh = refresh;
    }

    public String getRefresh() {
        return refresh;
    }
}
