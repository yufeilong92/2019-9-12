package com.tsyc.tianshengyoucai.Eventbuss;

import com.tsyc.tianshengyoucai.vo.GoodTypeVo;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/12 17:13
 * @Purpose :
 */
public class TypeContentEvent {
    private GoodTypeVo.ResultBean vo;

    public TypeContentEvent(GoodTypeVo.ResultBean vo) {
        this.vo = vo;
    }

    public GoodTypeVo.ResultBean getVo() {
        return vo;
    }
}
