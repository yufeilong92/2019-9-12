package com.tsyc.tianshengyoucai.vo;

import java.io.Serializable;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/7$ 11$:26$
 * @Purpose : 选择vo
 */
public class SelectVo  implements Serializable {
    private String name;
    private boolean select;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
