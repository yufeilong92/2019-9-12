package com.tsyc.tianshengyoucai.vo;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/7 16:30
 * @Purpose :
 */
public class ChatListVo {
    private String name;
    private String logo;
    private String content;
    /**
     * ，1 文字 2 图片
     */
    private int type;
    private int id;
    /**
     * 是否用户
     */
    private boolean  isUserInfom;

    public boolean isUserInfom() {
        return isUserInfom;
    }

    public void setUserInfom(boolean userInfom) {
        isUserInfom = userInfom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
