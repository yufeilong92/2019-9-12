package com.tsyc.tianshengyoucai.ui.activity.home;

import android.os.Bundle;
import android.widget.TextView;

import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.model.bean.HomeNoticeDetailBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.youth.xframe.widget.XToast;

import java.util.HashMap;
import java.util.Map;

/**
 * author：van
 * CreateTime：2019/9/11
 * File description：公告详情
 */
public class NoticeDetailActivity extends BaseActivity {

    private TextView tvContent;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_notice_detail;
    }


    @Override
    public void initView() {
        tvContent = (TextView) findViewById(R.id.tv_content);

        Bundle extra = getIntent().getBundleExtra(Constant.bundleExtra);
        if (extra == null) {
            XToast.normal(getString(R.string.service_error));
            return;
        }
        String id = extra.getString("id");

        requestNoticeDetail(id);
    }

    private void requestNoticeDetail(String id) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        loading(false);
        BaseRequestUtils.postRequest(this, UrlManager.homeNoteDetail, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                dismiss();
                HomeNoticeDetailBean detailBean = FastJsonUtil.fromJson(response.body(), HomeNoticeDetailBean.class);
                if (detailBean == null) {
                    XToast.normal(getString(R.string.service_error));
                    return;
                }

                if (!detailBean.getCode().equals(Constant.REQUEST_SUCCESS) || detailBean.getResult() == null) {
                    XToast.normal(Constant.GETDATA_FAIL_MESSAGE);
                    return;
                }
                HomeNoticeDetailBean.ResultBean resultBean = detailBean.getResult();
                String name = resultBean.getName();
                String text = resultBean.getText();
                mTvTitle.setText(name);

                tvContent.setText(text);
            }

            @Override
            public void failed(Response<String> response) {
                dismiss();
            }
        });
    }
}
