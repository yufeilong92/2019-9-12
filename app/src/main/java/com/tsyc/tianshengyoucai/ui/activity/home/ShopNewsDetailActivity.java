package com.tsyc.tianshengyoucai.ui.activity.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Config;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.model.bean.ArticleDetailBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.activity.mine.HelpCenterActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import java.util.HashMap;
import java.util.Map;

/**
 * author：van
 * CreateTime：2019/8/23
 * File description：咨询详情
 */
public class ShopNewsDetailActivity extends BaseActivity {

    private WebView mWebView;
    private ProgressBar mProgressBar;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_news_detail;
    }

    @Override
    public void initView() {
        mWebView = (WebView) findViewById(R.id.webView);
        mProgressBar = (ProgressBar) findViewById(R.id.pb_web_progress);

        initWeb();

    }

    @Override
    public void initData() {
        Bundle extra = getIntent().getBundleExtra(Constant.bundleExtra);

        if (extra == null) return;
        String id = extra.getString("id");
        if (id == null) return;
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        loading(false);
        BaseRequestUtils.postRequest(this, UrlManager.gitArticle, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("" + response.body());
                ArticleDetailBean detailBean = FastJsonUtil.fromJson(response.body(), ArticleDetailBean.class);
                dismiss();
                if (detailBean == null) {
                    XToast.normal(Constant.GETDATA_FAIL_MESSAGE);
                    return;
                }
                if (!detailBean.getCode().equals(Constant.REQUEST_SUCCESS) || detailBean.getResult() == null) {
                    XToast.normal(detailBean.getMessage());
                    return;
                }

                ArticleDetailBean.ResultBean result = detailBean.getResult();
                String article_content = result.getArticle_content();
                mTvTitle.setText(result.getArticle_title());
                mWebView.loadDataWithBaseURL(Config.BASE_URL, article_content, "text/html", "utf-8", null);
            }

            @Override
            public void failed(Response<String> response) {
                XToast.normal(getString(R.string.service_error));
                dismiss();
            }
        });
    }

    private void initWeb() {
        mWebView.setOnLongClickListener(v -> true);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);//设置能够解析Javascript
        webSettings.setDomStorageEnabled(true);//设置适应Html5的一些方法
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);//自适应屏幕
        webSettings.setAppCacheEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setSupportZoom(true); // 支持缩放
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        // 加快HTML网页加载完成的速度，等页面finish再加载图片
        if (Build.VERSION.SDK_INT >= 19) {
            webSettings.setLoadsImagesAutomatically(true);
        } else {
            webSettings.setLoadsImagesAutomatically(false);
        }

        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.setWebChromeClient(new MyWebChromeClient());


    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public void onReceivedError(WebView webView, int i, String s, String s1) {
            super.onReceivedError(webView, i, s, s1);
        }

        @Override
        public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
            super.onPageStarted(webView, s, bitmap);
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView webView, String s) {
            super.onPageFinished(webView, s);
            mProgressBar.setVisibility(View.GONE);
        }

        @Override
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            //super.onReceivedSslError(webView, sslErrorHandler, sslError);
            sslErrorHandler.proceed();// 接受所有网站的证书

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            String scheme = Uri.parse(url).getScheme();
            if (scheme != null) {
                scheme = scheme.toLowerCase();
            }
            if ("http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme)) {
                mWebView.loadUrl(url);
            }
            // 已经处理该链接请求
            return true;

        }
    }

    private class MyWebChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView webView, int newProgress) {
            mProgressBar.setProgress(newProgress);

        }

        @Override
        public void onReceivedTitle(WebView webView, String s) {
            super.onReceivedTitle(webView, s);

        }
    }

}
