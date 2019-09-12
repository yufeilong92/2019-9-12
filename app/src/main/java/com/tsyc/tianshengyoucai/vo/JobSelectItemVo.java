package com.tsyc.tianshengyoucai.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/20 15:09
 * @Purpose :
 */
public class JobSelectItemVo implements Serializable {
    /**
     * id : 3
     * level : 3
     * name : 高级管理职位
     * pid : 2
     * sumItems : []
     */
    /**
     * 是否选中
     */
    private boolean isSelect;
    private int id;
    private int level;
    private String name;
    private int pid;
    private List<JobSelectItemVo> sumItems;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public List<JobSelectItemVo> getSumItems() {
        return sumItems;
    }

    public void setSumItems(List<JobSelectItemVo> sumItems) {
        this.sumItems = sumItems;
    }
}
