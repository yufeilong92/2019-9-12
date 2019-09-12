package com.tsyc.tianshengyoucai.ui.activity.mine.shop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.flyn.Eyes;
import com.tsyc.tianshengyoucai.manager.SPManager;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.FileUtil;
import com.tsyc.tianshengyoucai.utils.ImageLoader;
import com.tsyc.tianshengyoucai.utils.SimpleUtils;
import com.tsyc.tianshengyoucai.view.pop.SaveQrCodePop;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.youth.xframe.utils.XDensityUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：van
 * CreateTime：2019/8/9
 * File description：点击收款
 */
public class MyReceiveCashActivity extends BaseActivity {

    private static final int SAVE_FAIL = 1001;
    private static final int SAVE_SUCCESS = 1002;
    @BindView(R.id.iv_qrcode)
    ImageView ivQrcode;
    @BindView(R.id.tv_receive_count)
    TextView tvReceiveCount;
    @BindView(R.id.tv_set_clear)
    TextView tvSetClear;
    @BindView(R.id.tv_save_qrcode)
    TextView tvSaveQrcode;

    private String cashier_id;
    private Bitmap qrImage;
    private String casher_name;

    private SaveQrCodePop saveQrCodePop;
    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SAVE_FAIL:
                    saveQrCodePop.dismiss();
                    XToast.normal("保存失败");
                    break;
                case SAVE_SUCCESS:
                    saveQrCodePop.dismiss();
                    XToast.normal("保存成功");
                    break;
            }
        }
    };

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_receive_cash;
    }

    @Override
    public void initView() {

        Eyes.translucentStatusBar(this, true);

        Bundle extra = getIntent().getBundleExtra(Constant.bundleExtra);
        if (extra == null) {
            return;
        }
        cashier_id = extra.getString("casher_id");
        casher_name = extra.getString("casher_name");

        genQrCode(cashier_id, "");
    }

    @OnClick({R.id.tv_set_clear, R.id.tv_save_qrcode, R.id.tv_receive_record, R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_set_clear:
                if (tvReceiveCount.getVisibility() == View.VISIBLE) {
                    tvReceiveCount.setText("");
                    tvReceiveCount.setVisibility(View.GONE);
                    tvSetClear.setText(getString(R.string.text_set_count));
                    genQrCode(cashier_id, "");
                } else {
                    Intent intent = new Intent(this, SetPayCountActivity.class);
                    startActivityForResult(intent, 200);
                }
                break;
            case R.id.tv_save_qrcode:
                saveQrCodeImg();
                break;
            case R.id.tv_receive_record: // 收款记录
                openActivity(BillDetailActivity.class);
                break;
            case R.id.rl_back:
                finish();
                break;
        }
    }

    //保存收款码  二维码生成   根据商家id + 交易金额
    private void saveQrCodeImg() {
        saveQrCodePop = new SaveQrCodePop(mContext);
        saveQrCodePop.showPopupWindow();
        LinearLayout ctlAll = saveQrCodePop.findViewById(R.id.ctl_all);
        ImageView ivQrCode = saveQrCodePop.findViewById(R.id.iv_qrcode);
        TextView tvPhone = saveQrCodePop.findViewById(R.id.tv_phone);

        ivQrCode.setImageBitmap(qrImage);
        String receiveCount = tvReceiveCount.getText().toString().trim();
        if (!TextUtils.isEmpty(receiveCount)) {
            tvReceiveCount.setVisibility(View.VISIBLE);
            tvPhone.setText(receiveCount);
        }else {
            tvReceiveCount.setVisibility(View.GONE);
        }

        mHandler.postDelayed(() -> {
            Bitmap bitmap = SimpleUtils.getCacheBitmapFromView(ctlAll);
            saveMyBitmap("收款二维码", bitmap);
        }, 1000);
    }

    //使用IO流将bitmap对象存到本地指定文件夹
    public void saveMyBitmap(final String bitName, final Bitmap bitmap) {
        new Thread(() -> {
            String filePath = Environment.getExternalStorageDirectory().getPath();
            File file = new File(filePath + "/DCIM/Camera/" + bitName + ".png");
            try {
                file.createNewFile();
                FileOutputStream fOut = null;
                fOut = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);

                mHandler.sendEmptyMessage(SAVE_SUCCESS);
                fOut.flush();
                fOut.close();
            } catch (IOException e) {
                e.printStackTrace();
                mHandler.sendEmptyMessage(SAVE_FAIL);
            }
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null && resultCode == 201) {
            tvReceiveCount.setVisibility(View.VISIBLE);
            String count = data.getStringExtra("count");
            tvReceiveCount.setText("￥" + count);
            tvSetClear.setText(getString(R.string.text_clear_count));
            genQrCode(cashier_id, count);
        }
    }

    private void genQrCode(String cashier_id, String money) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.logo);

        String qrCodeData = "{\"money\":" + (money.equals("") ? "-1" : money) + ",\"cashier_id\":" + cashier_id + ",\"type\":\""+Constant.SCAN_PAY+"\"}";

        XLog.e("二维码json" + qrCodeData);
        qrImage = CodeUtils.createImage(qrCodeData, XDensityUtils.dp2px(179), XDensityUtils.dp2px(178), null);
        ivQrcode.setImageBitmap(qrImage);
    }


}
