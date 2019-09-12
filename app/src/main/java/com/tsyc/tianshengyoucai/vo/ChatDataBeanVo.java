package com.tsyc.tianshengyoucai.vo;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/9 09:14
 * @Purpose : 服务接口
 */
public class ChatDataBeanVo {
    /**
     * content : 的
     * create_time : 2019-09-05 10:20:38
     * from_side : 2
     * id : 167
     * is_read : 1
     * member_id : 0
     *
     * record_id : 2
     * type : 1
     * update_time : 2019-09-05 10:20:38
     */

    private String content;
    private String create_time;
    private int from_side; // '1用户发送  2客服发送',  '1由招聘人发送  2由求职者发送',
    private int id;
    private int is_read;
    private int member_id;
    private int record_id;
    private int from_uid;
    private int type;
    private String update_time;

    public int getFrom_uid() {
        return from_uid;
    }

    public void setFrom_uid(int from_uid) {
        this.from_uid = from_uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getFrom_side() {
        return from_side;
    }

    public void setFrom_side(int from_side) {
        this.from_side = from_side;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIs_read() {
        return is_read;
    }

    public void setIs_read(int is_read) {
        this.is_read = is_read;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public int getRecord_id() {
        return record_id;
    }

    public void setRecord_id(int record_id) {
        this.record_id = record_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
