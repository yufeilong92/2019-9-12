package com.tsyc.tianshengyoucai.vo;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/9 18:20
 * @Purpose :
 */
public class BossChatList  {
    private BossChatVo.ResultBean.LeftUserBean job;
    private BossChatVo.ResultBean.RightUserBean user;
    private BossChatVo.ResultBean.HeadCardBean hear;
    private List<ChatListVo> mData;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public BossChatVo.ResultBean.LeftUserBean getJob() {
        return job;
    }

    public void setJob(BossChatVo.ResultBean.LeftUserBean job) {
        this.job = job;
    }

    public BossChatVo.ResultBean.RightUserBean getUser() {
        return user;
    }

    public void setUser(BossChatVo.ResultBean.RightUserBean user) {
        this.user = user;
    }

    public BossChatVo.ResultBean.HeadCardBean getHear() {
        return hear;
    }

    public void setHear(BossChatVo.ResultBean.HeadCardBean hear) {
        this.hear = hear;
    }

    public List<ChatListVo> getmData() {
        return mData;
    }

    public void setmData(List<ChatListVo> mData) {
        this.mData = mData;
    }
}
