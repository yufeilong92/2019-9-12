package com.tsyc.tianshengyoucai.ui.activity.mine.shop;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.youth.xframe.widget.XToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：van
 * CreateTime：2019/8/9
 * File description： 收款码设置金额
 */
public class SetPayCountActivity extends BaseActivity {

    @BindView(R.id.et_count)
    EditText etCount;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_set_pay_count;
    }

    @Override
    public void initView() {
        mTvTitle.setText(getString(R.string.text_set_count));
    }

    @OnClick(R.id.btn_commit)
    public void onViewClicked() {

        String content = etCount.getText().toString().trim();
        if (TextUtils.isEmpty(content) || Double.valueOf(content) == 0) {
            XToast.normal("设置金额必须大于0");
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("count", content);
        setResult(201, intent);
        finish();
    }
}
