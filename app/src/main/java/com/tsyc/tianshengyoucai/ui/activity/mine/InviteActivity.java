package com.tsyc.tianshengyoucai.ui.activity.mine;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.requet.BitmapDownHttp;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.DialogUtils;
import com.tsyc.tianshengyoucai.utils.FileUtil;
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.tsyc.tianshengyoucai.utils.SaveUserInfomUtilJave;
import com.tsyc.tianshengyoucai.utils.ScreenShot;
import com.tsyc.tianshengyoucai.utils.ShareUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.UserInfomVo;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/12 9:15:
 * @Purpose :邀请
 */
public class InviteActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private TextView mTvAskCode;
    private ImageView mIvInviteCode;
    private ImageView mIvInviteAsk;
    private ScrollView mRootView;
    private UserInfomVo.ResultBean mBean;


//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_invite);
//        initView();
//    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_invite;
    }

    @Override
    public void initData() {
        initView();
        initEvent();
        initRequest();

    }

    private void initRequest() {
        UserInfomVo vo = SaveUserInfomUtilJave.getInstance().getUserInfom();
        if (vo == null) {
            T.showToast(mContext, "用户信息丢失");
            mRootView.setVisibility(View.GONE);
            return;
        }
        mBean = vo.getResult();
        mRootView.setVisibility(View.VISIBLE);
        mTvAskCode.setText(mBean.getInvite_code());
        GlideUtil.getSingleton().loadQuadRangleImager(mContext, mIvInviteCode, mBean.getInvite_qrcode());

    }

    private void initEvent() {

        mIvInviteCode.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mBean == null) {
                    return true;
                }
                DialogUtils.getSingleton().showSureAlerDialog(mContext, "是否保存手机", "",
                        "保存", "取消", new DialogUtils.OnDialogSuceClickListener() {
                            @Override
                            public void sureClick() {
                                downBitmap(mBean.getInvite_qrcode());

                            }

                            @Override
                            public void cannerClick() {

                            }
                        });

                return false;
            }
        });
    }

    private void downBitmap(String invite_qrcode) {
        showProgress();
        BitmapDownHttp.getSingleton(mContext).requestBitmap(invite_qrcode, new BitmapDownHttp.BitmapInfaceCallBack() {
            @Override
            public void onSuccess(Bitmap bitmap) {
                dismissProgress();
                String imag = FileUtil.saveImag(mContext, bitmap);
                MediaStore.Images.Media.insertImage(mContext.getContentResolver(), bitmap, imag, null);
                mContext.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + imag)));
                final Snackbar snackbar = Snackbar.make(mRootView, "", Snackbar.LENGTH_LONG);
                snackbar.setActionTextColor(mContext.getResources().getColor(R.color.tab_color));
                snackbar.getView().setBackgroundColor(mContext.getResources().getColor(R.color.color_444A53));
                snackbar.setAction("查看", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        mContext.startActivity(intent);
                        snackbar.dismiss();
                    }
                });
                snackbar.show();
                T.showToast(mContext, getString(R.string.save_success));

            }

            @Override
            public void onError(String msg) {
                dismissProgress();
                T.showToast(mContext, "下载失败");
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_invite_ask://分享邀请
//                ShareAlertdialog alertdialog = new ShareAlertdialog(mContext, R.style.mydialog);
//                alertdialog.initData(mContext);
//                alertdialog.show();
//                alertdialog.setItemListener(new ShareAlertdialog.OnItemClickListener() {
//                    @Override
//                    public void weixinClick() {
//                        shareImage(false);
//                    }
//
//                    @Override
//                    public void weixinCircleClick() {
//                        shareImage(true);
//                    }
//                });

                shareImage(true);
                break;
            default:
                break;
        }

    }

    private void shareImage(boolean firend) {
        Bitmap bitmap = ScreenShot.getBitmapByView(mContext, mRootView);
        ShareUtil shareUtil = ShareUtil.getSingleton();
        shareUtil.shareImage(mContext,bitmap);
//        shareUtil.initWeiXin(getApplicationContext());
//        shareUtil.shareWeiXinText(getApplicationContext(), "222",false);
//        shareUtil.shareWeiXinImager(getApplicationContext(), bitmap, firend);
    }

    public void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResultTo.finishBase(mContext);
            }
        });
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mTvAskCode = (TextView) findViewById(R.id.tv_ask_code);
        mIvInviteCode = (ImageView) findViewById(R.id.iv_invite_code);
        mIvInviteCode.setOnClickListener(this);
        mIvInviteAsk = (ImageView) findViewById(R.id.iv_invite_ask);
        mIvInviteAsk.setOnClickListener(this);
        mRootView = (ScrollView) findViewById(R.id.root_view);
        mRootView.setOnClickListener(this);
    }


}
