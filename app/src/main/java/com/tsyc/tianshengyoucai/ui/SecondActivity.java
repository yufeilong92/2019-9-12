package com.tsyc.tianshengyoucai.ui;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.ActivityUtil;
import com.tsyc.tianshengyoucai.utils.LunBanUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.zhihu.matisse.Matisse;

import java.util.List;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: SecondActivity
 * @Package com.xuechuan.xcedu.ui
 * @Description: 二维码扫描界面
 * @author: L-BackPacker
 * @date: 2018/7/28 10:55
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018/7/28
 */
public class SecondActivity extends BaseActivity {

    private CaptureFragment captureFragment;
    private CheckBox mChbFlight;
    private FrameLayout mFlMyContainer;
    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private TextView mGmTvRightTitle;
    private LinearLayout mActivitySecond;
    public final int CHOOSE_REQUEST = 188;


//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_second);
//        initView();
//    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_second;
    }


    @Override
    public void initData() {
        initView();
        initContentView(savedInstanceState);
        initEvent();
    }

    private void initEvent() {
        mGmTvRightTitle.setText("相册");
        mGmTvTitle.setText("扫一扫");

        mGmIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResultTo.finishBase(mContext);
            }
        });
        mGmTvRightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.openXiangCes(SecondActivity.this, CHOOSE_REQUEST);
            }
        });
    }

    protected void initContentView(Bundle savedInstanceState) {
        captureFragment = new CaptureFragment();
        // 为二维码扫描界面设置定制化界面
        CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera);
        captureFragment.setAnalyzeCallback(analyzeCallback);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();
        initView();
    }


    public void initView() {
        mChbFlight = (CheckBox) findViewById(R.id.chb_flight);
        mFlMyContainer = (FrameLayout) findViewById(R.id.fl_my_container);
        mChbFlight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    CodeUtils.isLightEnable(true);
                } else {
                    CodeUtils.isLightEnable(false);
                }
            }
        });


        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmIvBack.setOnClickListener(this);
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mGmTvTitle.setOnClickListener(this);
        mGmTvRightTitle = (TextView) findViewById(R.id.gm_tv_right_title);
        mGmTvRightTitle.setOnClickListener(this);
        mActivitySecond = (LinearLayout) findViewById(R.id.activity_second);
        mActivitySecond.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        if (requestCode == CHOOSE_REQUEST && resultCode == RESULT_OK) {
            List<String> strings = Matisse.obtainPathResult(data);
            LunBanUtil.getSingleton(mContext).lunBanImagerS(strings, new LunBanUtil.lunBanInterface() {
                @Override
                public void imgStart() {
                    showProgress();
                }

                @Override
                public void imgSuccess(String path) {
                    dismissProgress();
                    returncode(path);;
                }

                @Override
                public void imgError() {
                    dismissProgress();
                    T.showToast(mContext, "图片压缩失败,请重新选择");
                }
            });
            return;
        }

    }

    private void returncode(String path) {
        CodeUtils.analyzeBitmap(path, new CodeUtils.AnalyzeCallback() {
            @Override
            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                Intent resultIntent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
                bundle.putString(CodeUtils.RESULT_STRING, result);
                resultIntent.putExtras(bundle);
                SecondActivity.this.setResult(RESULT_OK, resultIntent);
                SecondActivity.this.finish();
             //   Toast.makeText(SecondActivity.this, "解析结果:" + result, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAnalyzeFailed() {
                Intent resultIntent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
                bundle.putString(CodeUtils.RESULT_STRING, "");
                resultIntent.putExtras(bundle);
                SecondActivity.this.setResult(RESULT_OK, resultIntent);
                SecondActivity.this.finish();
                Toast.makeText(SecondActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
            }
        });
    }


    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
            bundle.putString(CodeUtils.RESULT_STRING, result);
            resultIntent.putExtras(bundle);
            SecondActivity.this.setResult(RESULT_OK, resultIntent);
            SecondActivity.this.finish();
          //  Toast.makeText(SecondActivity.this, "解析结果:" + result, Toast.LENGTH_LONG).show();

        }

        @Override
        public void onAnalyzeFailed() {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
            bundle.putString(CodeUtils.RESULT_STRING, "");
            resultIntent.putExtras(bundle);
            SecondActivity.this.setResult(RESULT_OK, resultIntent);
            SecondActivity.this.finish();
            Toast.makeText(SecondActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();

        }
    };


}
