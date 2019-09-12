package com.tsyc.tianshengyoucai.vo;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/9 09:17
 * @Purpose :客服发送
 */
public class AcceptChatService {

    /**
     * content : 慢慢来
     * flag : send_msg
     * from_side : 1
     * is_read : 1
     * record_id : 7
     * type : 1
     */

    private String content;
    private String flag;
    private int from_side;
    private int is_read;
    private String record_id;
    private int type;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getFrom_side() {
        return from_side;
    }

    public void setFrom_side(int from_side) {
        this.from_side = from_side;
    }

    public int getIs_read() {
        return is_read;
    }

    public void setIs_read(int is_read) {
        this.is_read = is_read;
    }

    public String getRecord_id() {
        return record_id;
    }

    public void setRecord_id(String record_id) {
        this.record_id = record_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
