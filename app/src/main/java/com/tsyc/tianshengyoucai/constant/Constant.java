package com.tsyc.tianshengyoucai.constant;

/**
 * author：YL
 * CreateTime：2019/6/16
 * File description： 一些常量
 */
public class Constant {

    public static final String WX_APP_ID = "wxf4e011a4f65c3bf7";

    public static final String REQUEST_SUCCESS = "200";
    public static final int REQUEST_UN_LOGIN = 100;//未登录

    public static final int SMS_LENGTH = 64;//一条短信的长度
    public static final String CONTACT_REMARK = "wankelai123_123";

    //腾讯地图的key
    public static final String TENGXUN_MAP_KEY = "ONXBZ-A6M64-4XOUZ-X7A4R-XPSFK-X3BRV";

    public static final String UNLOGIN_MESSAGE = "请重新登录";
    public static final String GETDATA_FAIL_MESSAGE = "获取数据失败，请稍候重试";
    public static final String OPERATION_FAIL_MESSAGE = "操作失败，请稍候重试";

    //1 注册 2为登录,3为找回密码 4 设置支付密码 5 修改手机号 6招聘绑定手机号
    public static final String SMS_REGISTER = "1";
    public static final String SMS_LOGIN = "2";
    public static final String SMS_FIND_PWD = "3";
    public static final String SMS_PAY_PWD = "4";
    public static final String SMS_PHONE = "5";
    public static final String SMS_BIND = "6";

    public static final String CLIENT = "android";

    public static final String mimeType = "text/html";
    public static final String encoding = "utf-8";

    public static final String bundleExtra = "extra";


    public static final int CASHIER_SCAN = 300; //收银台 扫码核销
    public static final int APPLY_BACK_MONEY = 10;//申请退款成功
    public static final int UNDERLINE_APPLY_BACK_MONEY = 11;//线下订单状态
    public static final int WX_PAY_SUCCESS_EVENT = 12; // 微信支付成功
    public static final int ALI_PAY_SUCCESS_EVENT = 13; // 支付宝支付成功
    public static final int EDIT_SHOP_RELEASE = 14; // 编辑商品发布
    public static final int REFRESH_ORDER_LIST = 15; //刷新订单状态
    public static final int UPDATE_ADDRESS = 16;
    public static final int UPDATE_TIME = 17;
    public static final int SCAN_ORDER = 18;  // 扫码核销的订单
    public static final int WX_PAY_SUCCESS_EVENT_ONLINE = 19;  // 线上线下

    public static final String SCAN_PAY = "shoukuan"; //扫码付款
    public static final String SCAN_UNDERLINE_ORDER = "verify"; //线下订单


//    public static final int WX_PAY_
}
