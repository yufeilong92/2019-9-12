package com.tsyc.tianshengyoucai.Eventbuss;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/9 14:30
 * @Purpose :
 */
public class AddressEvent {
    private int type;

    public AddressEvent(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
