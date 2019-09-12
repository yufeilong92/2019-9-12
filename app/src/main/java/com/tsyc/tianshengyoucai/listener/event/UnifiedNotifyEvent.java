package com.tsyc.tianshengyoucai.listener.event;

/**
 * 创 建 者：van
 * 创建日期：2018/12/3.
 * 描    述： 统一通知事件
 * 修改描述：
 * 修改日期：
 */
public class UnifiedNotifyEvent {

    private int eventCode;

    public UnifiedNotifyEvent(int eventCode) {
        this.eventCode = eventCode;
    }

    public int getEventCode() {
        return eventCode;
    }

    public void setEventCode(int eventCode) {
        this.eventCode = eventCode;
    }
}
