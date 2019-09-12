package com.tsyc.tianshengyoucai.Eventbuss;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/29 14:48
 * @Purpose :
 */
public class ChangerEvent {
    private String event;

    public ChangerEvent(String event) {
        this.event = event;
    }

    public String getEvent() {
        return event;
    }
}
