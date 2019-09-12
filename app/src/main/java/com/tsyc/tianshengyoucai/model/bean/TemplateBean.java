package com.tsyc.tianshengyoucai.model.bean;

/**
 * author：van
 * CreateTime：2019/8/28
 * File description： 发布商品 模板 bean
 */
public class TemplateBean {

    private String name;
    private String type;
    private int require;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRequire() {
        return require;
    }

    public void setRequire(int require) {
        this.require = require;
    }
}
