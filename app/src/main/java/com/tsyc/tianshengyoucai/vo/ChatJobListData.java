package com.tsyc.tianshengyoucai.vo;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/10 10:14
 * @Purpose :
 */
public class ChatJobListData {
    private JobChatMsgVo.ResultBean.HeadCardBean hear;
    private ChatListVo mData;
    private String time;

    public JobChatMsgVo.ResultBean.HeadCardBean getHear() {
        return hear;
    }

    public void setHear(JobChatMsgVo.ResultBean.HeadCardBean hear) {
        this.hear = hear;
    }

    public ChatListVo getmData() {
        return mData;
    }

    public void setmData(ChatListVo mData) {
        this.mData = mData;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
