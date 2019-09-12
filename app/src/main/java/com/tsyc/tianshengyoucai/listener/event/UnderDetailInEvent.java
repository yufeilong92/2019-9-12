package com.tsyc.tianshengyoucai.listener.event;

/**
 * author：van
 * CreateTime：2019/8/21
 * File description： 线下订单详情操作
 */
public class UnderDetailInEvent {

    private String msg;

    public UnderDetailInEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
