package com.tsyc.tianshengyoucai.Eventbuss;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/6 11:09
 * @Purpose :
 */
public class RefreshHomeEvent {
    private String string;

    public RefreshHomeEvent(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
