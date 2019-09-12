package com.tsyc.tianshengyoucai.ui.activity.home;

import android.os.Bundle;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/12 14:39:
 * @Purpose :红包地图
 */
public class RedPacketBaiDuActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_packet_baidu);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_red_packet_baidu;
    }

    @Override
    public void initData() {

    }
}
