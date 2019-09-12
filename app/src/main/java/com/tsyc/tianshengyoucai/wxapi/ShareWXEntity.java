package com.tsyc.tianshengyoucai.wxapi;

import java.io.Serializable;

/**
 * author：cxd
 * CreateTime：2019/7/16
 * File description： 微信分享需要
 */
public class ShareWXEntity implements Serializable {

    /**
     * errCode : 0
     * openId : oL2zL5pY9DsEc4Urn7GUhORUjkFU
     * transaction : news_detail_share_to_ex
     */

    private int errCode;
    private String openId;
    private String transaction;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }
}
