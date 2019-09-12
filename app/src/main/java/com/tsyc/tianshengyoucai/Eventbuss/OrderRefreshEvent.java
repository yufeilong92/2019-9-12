package com.tsyc.tianshengyoucai.Eventbuss;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/21 16:39
 * @Purpose :
 */
public class OrderRefreshEvent  {
    private String refresh;

    public OrderRefreshEvent(String refresh) {
        this.refresh = refresh;
    }

    public String getRefresh() {
        return refresh;
    }
}
