package com.tsyc.tianshengyoucai.Eventbuss;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/18 19:01
 * @Purpose : 店铺收藏
 */
public class ShopCollectEvent {
    private String  refresh;

    public ShopCollectEvent(String refresh) {
        this.refresh = refresh;
    }

    public String getRefresh() {
        return refresh;
    }
}
