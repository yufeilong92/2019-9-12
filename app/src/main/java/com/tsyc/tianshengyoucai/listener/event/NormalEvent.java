package com.tsyc.tianshengyoucai.listener.event;

/**
 * author：van
 * CreateTime：2019/8/13
 * File description：
 */
public class NormalEvent {

    private String msg;
    private int code;

    public NormalEvent(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
