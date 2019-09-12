package com.tsyc.tianshengyoucai.ui.activity.mine.shop;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.model.bean.StoreCerticationBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.ImageLoader;
import com.youth.xframe.widget.XToast;

import java.util.HashMap;
import java.util.Map;

/**
 * author：van
 * CreateTime：2019/8/16
 * File description： 商家资质
 */
public class CertificationActivity extends BaseActivity {

    private ImageView ivImage;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_certication;
    }

    @Override
    public void initView() {
        ivImage = (ImageView) findViewById(R.id.iv_image);
        Bundle extra = getIntent().getBundleExtra(Constant.bundleExtra);
        mTvTitle.setText("营业资质");

        if (null != extra) {
            String storeId = extra.getString("store_id");
            requestShopCertification(storeId);
        }

    }

    private void requestShopCertification(String storeId) {

        Map<String, String> params = new HashMap<>();
        params.put("store_id", storeId);
        loading(true);

        BaseRequestUtils.postRequestWithHeader(this, UrlManager.certification, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                dismiss();
                StoreCerticationBean storeCerticationBean = FastJsonUtil.fromJson(response.body(), StoreCerticationBean.class);
                if (null == storeCerticationBean) {
                    XToast.normal("请求失败，请稍候再试");
                    return;
                }
                if (!storeCerticationBean.getCode().equals(Constant.REQUEST_SUCCESS) || storeCerticationBean.getResult() == null) {
                    XToast.normal("获取失败，请稍候再试");
                    return;
                }
                String businessLicense = storeCerticationBean.getResult().getBusiness_license();

                ImageLoader.loadNormal(mContext, businessLicense, ivImage);

            }

            @Override
            public void failed(Response<String> response) {
                XToast.normal(getString(R.string.service_error));
                dismiss();
            }
        });
    }

    //    certification
}
