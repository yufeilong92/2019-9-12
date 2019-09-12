package com.tsyc.tianshengyoucai.ui.activity.mine.bank;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.sdk.model.BankCardResult;
import com.baidu.ocr.sdk.model.IDCardParams;
import com.baidu.ocr.sdk.model.IDCardResult;
import com.baidu.ocr.sdk.model.Word;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.Eventbuss.BankEvent;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.requet.RequestSettingHttp;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.ReleaseShopNewActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.ActivityUtil;
import com.tsyc.tianshengyoucai.utils.Base64Util;
import com.tsyc.tianshengyoucai.utils.FileUtil;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.HttpUtil;
import com.tsyc.tianshengyoucai.utils.LunBanUtil;
import com.tsyc.tianshengyoucai.utils.RecognizeService;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.utils.Util;
import com.tsyc.tianshengyoucai.utils.matisse_picker.Glide4Engine;
import com.tsyc.tianshengyoucai.view.SelectBankListAlerDialog;
import com.tsyc.tianshengyoucai.view.SelectCamerAlerdialog;
import com.tsyc.tianshengyoucai.vo.BankListVo;
import com.tsyc.tianshengyoucai.vo.MyBankListVo;
import com.youth.xframe.utils.XRegexUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/8 15:06:
 * @Purpose ：添加银行卡
 */
public class AddBankActivity extends BaseActivity implements View.OnClickListener {


    private final int REQUEST_CODE_BANKCARD = 1057;
    private final int REQUEST_CODE_CAMERA = 1056;

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private EditText mEtAddBankName;
    private EditText mEtAddBankIdcard;
    private EditText mEtAddBankIdnumber;
    private TextView mEtAddBankIdtheir;
    private EditText mEtAddBankPhone;
    private Button mBtnAddBankSubmit;
    public static final String IDCARDTYPE = "idtype";
    private MyBankListVo.ResultBean mData;
    private boolean hasGotToken = false;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_add_bank;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_bank);
//        initView();
//
//    }

    @Override
    public void initData() {


        initView();
        initEvent();
        bindView();
        initBaiDuQCR();
    }

    private void initBaiDuQCR() {
        OCR.getInstance(this).initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken result) {
                String token = result.getAccessToken();
                XLog.e("initAccessTokenWithAkSk  " + token);
                hasGotToken = true;
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
                XLog.e("AK，SK方式获取token失败" + error.getMessage());
            }
        }, getApplicationContext(), "MbokFE46WbtNI0rfBw27qXz9", "nxGTcQyP9RT0LrY7kIVzqnabSy3bt30R");
    }

    private void bindView() {

    }

    private void initEvent() {
        mEtAddBankIdtheir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initReuqest();
            }
        });

    }

    private void initReuqest() {
        showProgress();
        RequestSettingHttp.getSingleton(this).requestBankList(new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                NormalBean vo = GsonUtils.getGson(success, NormalBean.class);
                if (vo == null || vo.getCode().equals("100")) {
                    T.showToast(mContext, vo.getMessage());
                    return;
                }
                BankListVo vos = GsonUtils.getGson(success, BankListVo.class);

                showDialog(vos);
            }

            @Override
            public void error(String error) {
                toastErrorNet();
                dismissProgress();

            }
        });
    }

    private BankListVo.ResultBean mSelectVo;

    private void showDialog(BankListVo vo) {
        SelectBankListAlerDialog alerDialog = new SelectBankListAlerDialog(mContext, R.style.mydialog);
        alerDialog.setCanceledOnTouchOutside(true);
        alerDialog.show();
        alerDialog.setData(vo.getResult());
        alerDialog.setListener(new SelectBankListAlerDialog.OnItemClickListener() {
            @Override
            public void sureClicl(BankListVo.ResultBean vo) {
                if (vo == null) return;
                mSelectVo = vo;
                mEtAddBankIdtheir.setText(vo.getBank_name());
            }
        });
    }

    private void submit() {

        String name = StringUtil.getObjectToStr(mEtAddBankName);
        if (StringUtil.isEmpty(name)) {
            T.showToast(mContext, "请输入真实姓名");
            return;
        }
        String idcard = StringUtil.getObjectToStr(mEtAddBankIdcard);
        if (StringUtil.isEmpty(idcard)) {
            T.showToast(mContext, "请输入身份证号码");
            return;
        }
        if (!XRegexUtils.checkIdCard(idcard)) {
            T.showToast(mContext, "请输入正确的身份证号码");
            return;
        }
        String idNumber = StringUtil.getObjectToStr(mEtAddBankIdnumber);
        if (StringUtil.isEmpty(idNumber)) {
            T.showToast(mContext, "请输入银行卡卡号");
            return;
        }
        if (!XRegexUtils.checkBankCard(idNumber)) {
            T.showToast(mContext, "请输入正确的银行卡卡号");
            return;
        }
        String idTheir = StringUtil.getObjectToStr(mEtAddBankIdtheir);
        if (StringUtil.isEmpty(idTheir)) {
            T.showToast(mContext, "请选择发卡行名称");
            return;
        }
        String phone = StringUtil.getObjectToStr(mEtAddBankPhone);
        if (StringUtil.isEmpty(phone)) {
            T.showToast(mContext, "输入银行预留手机号码");
            return;
        }
        if (!Util.isPhoneNum(phone)) {
            T.showToast(mContext, "请输入正确的手机号");
            return;
        }
        idNumber = idNumber.replace(" ", "");
        showProgress();
        RequestSettingHttp.getSingleton(this).submitBank(name, idcard, idTheir, idNumber, phone, mSelectVo.getBank_sn(), new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                NormalBean vo = GsonUtils.getGson(success, NormalBean.class);
                if (vo.getCode().equals("100")) {
                    T.showToast(mContext, vo.getMessage());
                    return;
                }
                T.showToast(mContext, "绑定成功");
                EventBus.getDefault().postSticky(new BankEvent(""));
                mResultTo.finishBase(mContext);
            }

            @Override
            public void error(String error) {
                dismissProgress();
                toastErrorNet();

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_bank_submit:
                submit();
                break;
            case R.id.gm_iv_back:
                mResultTo.finishBase(this);
                break;

            case R.id.iv_show_card: // 识别银行卡
                selectBankCard();
                break;
            case R.id.iv_show_id: // 识别身份证
                selectIdCard();
                break;
        }
    }

    public void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        ImageView mIvShowCard = (ImageView) findViewById(R.id.iv_show_card);
        ImageView mIvShowId = (ImageView) findViewById(R.id.iv_show_id);
        mIvShowId.setOnClickListener(this);
        mIvShowCard.setOnClickListener(this);
        mGmIvBack.setOnClickListener(this);
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mEtAddBankName = (EditText) findViewById(R.id.et_add_bank_name);
        mEtAddBankIdcard = (EditText) findViewById(R.id.et_add_bank_idcard);
        mEtAddBankIdnumber = (EditText) findViewById(R.id.et_add_bank_idnumber);
        mEtAddBankIdtheir = (TextView) findViewById(R.id.et_add_bank_idtheir);
        mEtAddBankPhone = (EditText) findViewById(R.id.et_add_bank_phone);
        mBtnAddBankSubmit = (Button) findViewById(R.id.btn_add_bank_submit);

        mBtnAddBankSubmit.setOnClickListener(this);
    }

    //选择身份证进行识别
    private void selectIdCard() {
        if (!hasGotToken) {
            XToast.normal("未获取到token，识别失败");
            return;
        }
        Intent intent = new Intent(AddBankActivity.this, CameraActivity.class);
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                FileUtil.getSaveFile(getApplication()).getAbsolutePath());
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT);
        startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }

    //选择银行卡进行识别
    private void selectBankCard() {

        if (!hasGotToken) {
            XToast.normal("未获取到token，识别失败");
            return;
        }
        Intent intent = new Intent(AddBankActivity.this, CameraActivity.class);
        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                FileUtil.getSaveFile(getApplication()).getAbsolutePath());
        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE,
                CameraActivity.CONTENT_TYPE_BANK_CARD);
        startActivityForResult(intent, REQUEST_CODE_BANKCARD);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null) return;
        // 识别成功回调，银行卡识别
        if (requestCode == REQUEST_CODE_BANKCARD && resultCode == Activity.RESULT_OK) {
            RecognizeService.recBankCard(this, FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath(),
                    new RecognizeService.ServiceListener() {
                        @Override
                        public void onResult(String result) {
                            if (result.contains("card error")) {
                                XToast.normal("请选择识别正确的银行卡");
                            }
                        }

                        @Override
                        public void onResult(BankCardResult result) {
                            if (result == null) {
                                XToast.normal("识别失败，请手动输入");
                                return;
                            }
                            String bankCardNumber = result.getBankCardNumber();
                            String bankName = result.getBankName();

                            mEtAddBankIdtheir.setText(bankName);
                            mEtAddBankIdnumber.setText(bankCardNumber);
                        }
                    });
        }

        if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK) {
            String contentType = data.getStringExtra(CameraActivity.KEY_CONTENT_TYPE);
            String filePath = FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath();
            if (!TextUtils.isEmpty(contentType)) {
                if (CameraActivity.CONTENT_TYPE_ID_CARD_FRONT.equals(contentType)) {
                    recIDCard(IDCardParams.ID_CARD_SIDE_FRONT, filePath);
                } else if (CameraActivity.CONTENT_TYPE_ID_CARD_BACK.equals(contentType)) {
                    recIDCard(IDCardParams.ID_CARD_SIDE_BACK, filePath);
                }
            }
        }
    }

    private void recIDCard(String idCardSide, String filePath) {
        IDCardParams param = new IDCardParams();
        param.setImageFile(new File(filePath));
        param.setIdCardSide(idCardSide);
        param.setDetectDirection(true);
        param.setImageQuality(20);

        OCR.getInstance(this).recognizeIDCard(param, new OnResultListener<IDCardResult>() {
            @Override
            public void onResult(IDCardResult result) {
                if (result != null) {
                    Word idNumber = result.getIdNumber();
                    mEtAddBankIdcard.setText(idNumber.toString());
                    Word name = result.getName();
                    mEtAddBankName.setText(name.toString());
                }
            }

            @Override
            public void onError(OCRError error) {
                XToast.normal("身份证识别失败：" + error.getMessage());
            }
        });
    }

}
