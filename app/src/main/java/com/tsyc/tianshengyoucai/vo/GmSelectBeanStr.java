package com.tsyc.tianshengyoucai.vo;

import java.io.Serializable;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/28 10:54
 * @Purpose :
 */
public class GmSelectBeanStr implements Serializable {
    /**
     * desc : 0-20人
     * value : 1
     */

    private String desc;
    private String value;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
