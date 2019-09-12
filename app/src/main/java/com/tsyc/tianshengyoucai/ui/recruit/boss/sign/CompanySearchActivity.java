package com.tsyc.tianshengyoucai.ui.recruit.boss.sign;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.recruit.CompanySearchAdapter;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.listener.MyTextWatcherListener;
import com.tsyc.tianshengyoucai.requet.RequestBossHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.Util;
import com.tsyc.tianshengyoucai.vo.CompanySearchVo;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/26
 * File description：公司名
 */
public class CompanySearchActivity extends Base2Activity implements View.OnClickListener {


    private TextView mTvCompanysearchChooseRole;
    private EditText mEtCompanysearchCompanyName;
    private TextView mTvCompanysearchContentLength;
    private ConstraintLayout mCtlCompanysearchCompanyName;
    private Button mBtnCompanysearchNext;
    /**
     * 公司选择请求码
     */
    private static final int SELECTCOMPANYCODE = 1011;
    private RecyclerView mRlvCompanyContent;
    private LinearLayout mLlCompanyContent;
    private ConstraintLayout mCtlCompanyContent;
    private TextView mTvRlcSearchResult;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_company_search);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_company_search;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        initView();
        initEvent();
    }

    private void initEvent() {
        mEtCompanysearchCompanyName.addTextChangedListener(new MyTextWatcherListener() {
            @Override
            public void afterTextChanged(Editable s) {
                String com = StringUtil.getObjectToStr(mEtCompanysearchCompanyName);
                if (StringUtil.isEmpty(com)) {
                    showSearchView(false);
                }
                mTvCompanysearchContentLength.setText(s.length() + "/50");
            }
        });
        mEtCompanysearchCompanyName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Util.hideInputMethod(CompanySearchActivity.this);
                    submit();
                    return true;
                }
                return false;
            }
        });

    }

    private void initView() {

        mTvCompanysearchChooseRole = (TextView) findViewById(R.id.tv_companysearch_choose_role);
        mTvCompanysearchChooseRole.setOnClickListener(this);
        mEtCompanysearchCompanyName = (EditText) findViewById(R.id.et_companysearch_company_name);
        mEtCompanysearchCompanyName.setOnClickListener(this);
        mTvCompanysearchContentLength = (TextView) findViewById(R.id.tv_companysearch_content_length);
        mTvCompanysearchContentLength.setOnClickListener(this);
        mCtlCompanysearchCompanyName = (ConstraintLayout) findViewById(R.id.ctl_companysearch_company_name);
        mCtlCompanysearchCompanyName.setOnClickListener(this);
        mBtnCompanysearchNext = (Button) findViewById(R.id.btn_companysearch_next);
        mBtnCompanysearchNext.setOnClickListener(this);
        mRlvCompanyContent = (RecyclerView) findViewById(R.id.rlv_company_content);
        mRlvCompanyContent.setOnClickListener(this);
        mLlCompanyContent = (LinearLayout) findViewById(R.id.ll_company_content);
        mLlCompanyContent.setOnClickListener(this);
        mCtlCompanyContent = (ConstraintLayout) findViewById(R.id.ctl_company_content);
        mCtlCompanyContent.setOnClickListener(this);
        mTvRlcSearchResult = (TextView) findViewById(R.id.tv_rlc_search_result);
        mTvRlcSearchResult.setOnClickListener(this);
    }

    private void submit() {
        String name = mEtCompanysearchCompanyName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请填写公司名称", Toast.LENGTH_SHORT).show();
            return;
        }

        showProgress();
        RequestBossHttp.getSingleton(this).requestCompanySearch(name, new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                CompanySearchVo vo = GsonUtils.getGson(success, CompanySearchVo.class);
                List<CompanySearchVo.ResultBean> list = vo.getResult();
                if (list == null || list.isEmpty()) {
                    showSearchView(false);
                    mResultTo.startSearchResult(mContext, name);
                    return;
                }
                showSearchView(true);
                bindSearchResult(list);
            }

            @Override
            public void error(String error) {
                onError();
            }
        });
    }

    private void bindSearchResult(List<CompanySearchVo.ResultBean> list) {
        CompanySearchAdapter adapter = new CompanySearchAdapter(mContext, list);
        RecyclerUtil.setGridManage(mContext, mRlvCompanyContent, adapter);
        adapter.setListener(new CompanySearchAdapter.OnItemClickListener() {
            @Override
            public void itemClick(CompanySearchVo.ResultBean vo) {
                if (vo.getStatus() == 1) {
                    Intent intent = new Intent();
                    intent.putExtra(CreateBossCardActivity.SELECTCOMPANY, vo);
                    setResult(RESULT_OK, intent);
                    mResultTo.finishBase(mContext);

                }
            }
        });
    }

    private void showSearchView(boolean isShow) {
        mLlCompanyContent.setVisibility(isShow ? View.GONE : View.VISIBLE);
        mCtlCompanyContent.setVisibility(isShow ? View.GONE : View.VISIBLE);
        mRlvCompanyContent.setVisibility(isShow ? View.VISIBLE : View.GONE);
        mTvRlcSearchResult.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_companysearch_next:
                submit();
                break;
        }

    }


}
