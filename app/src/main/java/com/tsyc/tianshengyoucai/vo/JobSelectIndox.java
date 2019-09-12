package com.tsyc.tianshengyoucai.vo;

import java.io.Serializable;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/20 16:20
 * @Purpose :
 */
public class JobSelectIndox  implements Serializable {
    /**
     * 第一级
     */
    private int typeone;
    /**
     * 第二级
     */
    private int typetwo;
    /**
     * 第三极
     */
    private int typethree;
    /**
     * 选择数据
     */
    private JobSelectItemVo selectVo;


    public JobSelectItemVo getSelectVo() {
        return selectVo;
    }

    public void setSelectVo(JobSelectItemVo selectVo) {
        this.selectVo = selectVo;
    }

    public int getTypeone() {
        return typeone;
    }

    public void setTypeone(int typeone) {
        this.typeone = typeone;
    }

    public int getTypetwo() {
        return typetwo;
    }

    public void setTypetwo(int typetwo) {
        this.typetwo = typetwo;
    }

    public int getTypethree() {
        return typethree;
    }

    public void setTypethree(int typethree) {
        this.typethree = typethree;
    }


}
