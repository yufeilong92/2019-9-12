package com.tsyc.tianshengyoucai.listener;

/**
 * 创 建 者：van
 * 创建日期：2018/11/29.
 * 描    述： 键盘舰艇
 * 修改描述：
 * 修改日期：
 */
public interface PayBorderListener {

    void onFinished(String pwdContent); //完成

    void onClosed(); // 关闭

    void onPwdForget(); // 忘记密码

}
