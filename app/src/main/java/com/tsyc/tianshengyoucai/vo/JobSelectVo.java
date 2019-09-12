package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/20 15:08
 * @Purpose :
 */
public class JobSelectVo extends NormalBean {

    private List<JobSelectItemVo> result;

    public List<JobSelectItemVo> getResult() {
        return result;
    }

    public void setResult(List<JobSelectItemVo> result) {
        this.result = result;
    }


}
