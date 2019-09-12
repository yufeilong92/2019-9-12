package com.tsyc.tianshengyoucai.ui.activity.type;

import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.fragment.TypeFragment;

public class HomeTypeActivity extends BaseActivity {

    private FrameLayout mFlContent;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home_type);
//        initView();
//    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_home_type;
    }

    public void initView() {
        mFlContent = (FrameLayout) findViewById(R.id.fl_content);
    }

    @Override
    public void initData() {
        initView();
        initEvent();

    }

    private void initEvent() {
        TypeFragment fragment = TypeFragment.newInstance("", "");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl_content, fragment).commit();

    }
}
