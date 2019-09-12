package com.tsyc.tianshengyoucai.ui.recruit.boss.sign;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.base.Base2Activity;

/**
 * author：van
 * CreateTime：2019/8/26
 * File description：公司 认证
 */
public class CompanyNameSignActivity extends Base2Activity {


    private String mCompanyName;

    public static final String COMPANYNAME="company_name";
    @Override
    protected int getComtentView() {
        return R.layout.activity_company_name_sign;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        initView();
    }


    private void initView() {
        if (getIntent() != null) {
            mCompanyName = getIntent().getStringExtra(COMPANYNAME);
        }
        String startStr = "请您再次确认您的公司全称";
        String endStr = "是否与营业执照上的全称相同";

        TextView tvNoFind = (TextView) findViewById(R.id.tv_no_find);
        if (!TextUtils.isEmpty(mCompanyName)) {
            SpannableString spannableString = new SpannableString(startStr + "\"" + mCompanyName + "\"" + endStr);
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#5584F6"));
            spannableString.setSpan(colorSpan, startStr.length(), (startStr + mCompanyName).length() + 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

            tvNoFind.setText(spannableString);
        }


        findViewById(R.id.tv_update_info).setOnClickListener(v -> {
            mResultTo.finishBase(mContext);
        });

        //创建新公司
        findViewById(R.id.tv_update_next).setOnClickListener(v ->
                mResultTo.startCreateNewCompany(mContext,mCompanyName));
    }


}

