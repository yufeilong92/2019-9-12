package com.tsyc.tianshengyoucai.manager;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/10 09:36
 * @Purpose :工具
 */
public class DataMangerVo {
    public static final int PAGERE_NUMBER = 10;
    public static String key;
    //    微信id
    public static final String WeixinAPP_ID = "wxf4e011a4f65c3bf7";
    //    微信广播
    public static final String WEI_LOGIN_ACTION = "com.weixinlogin.com";
    //第三方程序发送时用来标识其请求的唯一性的标志，由第三方程序调用sendReq时传入，由微信终端回传，state字符串长度不能超过1K
    public static final String WEISTATE = "state";
    //用户换取access_token的code，仅在ErrCode为0时有效
    public static final String WEICODE = "code";
    /**
     * 通用标题
     */
    public static final String TITLEPARAM = "param_title";

    public static final String SocketUrl = "ws://114.115.203.124:8898";
    public static final int CONNECT_TIMEOUT=60000;

}
