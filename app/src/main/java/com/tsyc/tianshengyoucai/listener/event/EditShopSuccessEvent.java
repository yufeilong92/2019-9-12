package com.tsyc.tianshengyoucai.listener.event;

/**
 * author：van
 * CreateTime：2019/8/30
 * File description： 编辑成功
 */
public class EditShopSuccessEvent {

    private String msg;

    public EditShopSuccessEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
