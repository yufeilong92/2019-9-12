package com.tsyc.tianshengyoucai.vo;

import java.util.List;

/**
 * 规格
 */
public class GoodSpecification {
    /**
     * data : ["样式1","样式2"]
     * name : 样式
     * select : 样式1
     */

    private String name;
    private String select;
    private List<String> data;
    /**
     * 选择的类型（自定义）
     */
    private String selectType;

    public String getSelectType() {
        return selectType;
    }

    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
