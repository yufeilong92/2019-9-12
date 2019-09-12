package com.tsyc.tianshengyoucai.vo;

import java.io.Serializable;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/28 10:54
 * @Purpose :
 */
public class GmSelectBean implements Serializable {
    /**
     * desc : 0-20人
     * value : 1
     */

    private String desc;
    private int value;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
