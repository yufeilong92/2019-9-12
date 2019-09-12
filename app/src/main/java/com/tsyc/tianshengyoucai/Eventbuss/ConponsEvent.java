package com.tsyc.tianshengyoucai.Eventbuss;

import com.tsyc.tianshengyoucai.vo.CouponListBean;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/17 18:54
 * @Purpose :线下
 */
public class ConponsEvent {
    private CouponListBean bean;

    public ConponsEvent(CouponListBean bean) {
        this.bean = bean;
    }

    public CouponListBean getBean() {
        return bean;
    }
}
