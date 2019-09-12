package com.tsyc.tianshengyoucai.ui.activity.mine;

import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.classic.common.MultipleStatusView;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;

import butterknife.BindView;

/**
 * author：van
 * CreateTime：2019/7/27
 * File description：
 */
public class HelpCenterActivity extends BaseActivity {

    private static final int REQUEST_DERAIL = 100;
    @BindView(R.id.pb_web_progress)
    ProgressBar mProgressBar;
    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.multiple_status_view)
    MultipleStatusView mMultipleStatusView;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_single_web;
    }

    @Override
    public void initView() {
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

    @Override
    public void initData() {
       mTvTitle.setText("帮助中心");
        mMultipleStatusView.setOnRetryClickListener(v -> mHandler.sendEmptyMessage(REQUEST_DERAIL));

        mWebView.loadUrl(UrlManager.helpCenter);
    }


    private class MyWebViewClient extends WebViewClient {
        @Override
        public void onReceivedError(WebView webView, int i, String s, String s1) {
            super.onReceivedError(webView, i, s, s1);
            mMultipleStatusView.showError();
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
            mMultipleStatusView.showContent();
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
