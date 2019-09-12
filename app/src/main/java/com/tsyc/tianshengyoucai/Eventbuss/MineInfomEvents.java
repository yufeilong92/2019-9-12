package com.tsyc.tianshengyoucai.Eventbuss;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/8 19:51
 * @Purpose :
 */
public class MineInfomEvents {
    /**
     * 1 为更新头像，其为更新内容
     */
    private String refresh;

    public MineInfomEvents(String refresh) {
        this.refresh = refresh;
    }

    public String getRefresh() {
        return refresh;
    }
}
