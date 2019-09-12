package com.tsyc.tianshengyoucai.ui.recruit;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tsyc.tianshengyoucai.Eventbuss.RcRefresh;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.manager.SelectCityManager;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.requet.RequestJobHttp;
import com.tsyc.tianshengyoucai.requet.UpdataImagHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.ActivityUtil;
import com.tsyc.tianshengyoucai.utils.DialogUtils;
import com.tsyc.tianshengyoucai.utils.FileUtil;
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.LunBanUtil;
import com.tsyc.tianshengyoucai.utils.SaveJobUtil;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.utils.Util;
import com.tsyc.tianshengyoucai.view.SelectCamerAlerdialog;
import com.tsyc.tianshengyoucai.vo.GmImagerVo;
import com.tsyc.tianshengyoucai.vo.JobInfom;
import com.tsyc.tianshengyoucai.vo.ResumeVo;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;
import com.zhihu.matisse.Matisse;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.picker.DatePicker;
import cn.addapp.pickers.picker.SinglePicker;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/19 10:37:
 * @Purpose :工作者信息
 */
@RuntimePermissions
public class JobInfomActivity extends Base2Activity implements View.OnClickListener {

    private ImageView mIvJobInfomHear;
    private EditText mEtJobInfomName;
    private TextView mTvJobInfomSex;
    private TextView mTvJobInfomBirthday;
    private TextView mTvJobInfomTime;
    private TextView mTvJobInfomCity;
    private TextView mTvJobInfomPhone;
    private EditText mEtJobInfomEmail;
    private Button mBtnJobInfomNext;
    private DialogUtils mDialogUtils;

    private int mBirthdayYearParam;
    private int mBirthdayMonthParam;
    private int mBirthdayDayParam;

    private int mWorkYearParam;
    private int mWorkMonthParam;
    private int mWorkDayParam;
    private SelectCityManager mSelectCityManager;
    private LinearLayout mLlJobInfomHear;
    private SelectCamerAlerdialog mCamerAlerdialog;
    /**
     * 相机
     */
    private int REQUESTCODE = 100;
    /**
     * 相册
     */
    private int REQUESTCODECE = 101;
    private String mPath;

    public static final String TYPE = "TYPE";
    private ResumeVo.ResultBean mData;
    /**
     * 绑定跌的手机号
     */
    private int VERIFYPHONECODE = 102;
    /**
     * 回调手机号
     */
    public static final String PHONE = "phone";

    //
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_job_infom);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_job_infom;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        if (getIntent() != null) {
            mData = (ResumeVo.ResultBean) getIntent().getSerializableExtra(TYPE);
        }
        initView();
        initEvent();
        bindViewData();

    }

    private void bindViewData() {
        if (mData == null) return;
        mEtJobInfomName.setText(mData.getUsername());
        if (mData.getSex().getDesc().equals("先生")) {
            mTvJobInfomSex.setText("男");
        } else {
            mTvJobInfomSex.setText("女");
        }
        GlideUtil.getSingleton().loadCilcleImager(mContext, mIvJobInfomHear, mData.getAvatar());
        if (!StringUtil.isEmpty(mData.getBirthday())) {
            String birthday = mData.getBirthday();
            String[] split = birthday.split("-");
            mBirthdayYearParam = Integer.parseInt(split[0]);
            mBirthdayMonthParam = Integer.parseInt(split[1]) - 1;
            mBirthdayDayParam = Integer.parseInt(split[2]);
            mTvJobInfomBirthday.setText(mData.getBirthday());
        }
        if (!StringUtil.isEmpty(mData.getIn_work())) {
            String inWork = mData.getIn_work();
            String[] split = inWork.split("-");
            mWorkYearParam = Integer.parseInt(split[0]);
            mWorkMonthParam = Integer.parseInt(split[1]) - 1;
            mWorkDayParam = Integer.parseInt(split[2]);
            mTvJobInfomTime.setText(mData.getIn_work());
        }
        cityParam = mData.getLiving_city().getDesc();
        mTvJobInfomCity.setText(mData.getLiving_city().getDesc());
        mTvJobInfomPhone.setText(mData.getMobile());
        mEtJobInfomEmail.setText(mData.getEmail());
        mBtnJobInfomNext.setText("提交");
    }

    private void initEvent() {
        mDialogUtils = DialogUtils.getSingleton();
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        mBirthdayYearParam = c.get(Calendar.YEAR);
        mBirthdayMonthParam = c.get(Calendar.MONTH);
        mBirthdayDayParam = c.get(Calendar.DATE);
        mWorkYearParam = c.get(Calendar.YEAR);
        mWorkMonthParam = c.get(Calendar.MONTH);
        mWorkDayParam = c.get(Calendar.DATE);
        mLlJobInfomHear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JobInfomActivityPermissionsDispatcher.showCameraWithPermissionCheck(JobInfomActivity.this);
            }
        });
        mTvJobInfomSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Util.handleOnDoubleClick()) return;
                showSelectDialog();
            }
        });
        mTvJobInfomBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Util.handleOnDoubleClick()) return;
                selectTime(true);
            }
        });
        mTvJobInfomTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Util.handleOnDoubleClick()) return;
                selectTime(false);
            }
        });
        mTvJobInfomCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAddress();
            }
        });


    }

    private void showSelectDialog() {
        ArrayList<String> items = new ArrayList<>();
        items.add("男");
        items.add("女");
        SinglePicker<String> picker = new SinglePicker<String>(this, items);
        picker.setCanLoop(false);//不禁用循环
        picker.setTopBackgroundColor(0xFFEEEEEE);
        picker.setTopHeight(50);
        picker.setTopLineColor(0xFF33B5E5);
        picker.setTopLineHeight(1);
        picker.setTitleText("请选择");
        picker.setTitleTextColor(0xFF999999);
        picker.setTitleTextSize(12);
        picker.setCancelTextColor(0xFF33B5E5);
        picker.setCancelTextSize(13);
        picker.setSubmitTextColor(0xFF33B5E5);
        picker.setSubmitTextSize(13);
        picker.setSelectedTextColor(0xFF33B5E5);
        picker.setUnSelectedTextColor(0xFF999999);
        picker.setWheelModeEnable(false);
        LineConfig config = new LineConfig();
        config.setColor(Color.TRANSPARENT);//线颜色
        config.setAlpha(120);//线透明度
//        config.setRatio(1);//线比率
        picker.setLineConfig(config);
        picker.setItemWidth(200);
        picker.setBackgroundColor(0xFFE1E1E1);
        //picker.setSelectedItem(isChinese ? "处女座" : "Virgo");
        picker.setSelectedIndex(7);
        picker.setOnItemPickListener(new OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int i, String com) {
                mTvJobInfomSex.setText(com);
            }
        });
        picker.show();

    }


    /**
     * 是否是生日 或者工作时间
     *
     * @param birthday
     */
    private void selectTime(boolean birthday) {
        DatePicker picker = new DatePicker(this);
        picker.setCanLoop(false);
        picker.setWheelModeEnable(true);
        picker.setTopPadding(15);
        picker.setRangeStart(1911, 1, 1);
        picker.setRangeEnd(3000, 1, 1);
        if (birthday)
            picker.setSelectedItem(mBirthdayYearParam, mBirthdayMonthParam + 1, mBirthdayDayParam);
        else
            picker.setSelectedItem(mWorkYearParam, mWorkMonthParam + 1, mWorkDayParam);
        picker.setWeightEnable(true);
        picker.setLineColor(Color.WHITE);
        picker.setOnDatePickListener(new cn.addapp.pickers.picker.DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                if (birthday) {
                    mBirthdayYearParam = Integer.parseInt(year);
                    mBirthdayMonthParam = Integer.parseInt(month) - 1;
                    mBirthdayDayParam = Integer.parseInt(day);
                    mTvJobInfomBirthday.setText(year + "-" + month + "-" + day);
                } else {
                    mWorkYearParam = Integer.parseInt(year);
                    mWorkMonthParam = Integer.parseInt(month) - 1;
                    mWorkDayParam = Integer.parseInt(day);
                    mTvJobInfomTime.setText(year + "-" + month + "-" + day);
                }
            }
        });
        picker.show();
    }


    private int provinceOpt;
    private int cityOpt;
    private int areaOpt;
    private String provinceParam;
    private String cityParam;
    private String areaParam;

    private void selectAddress() {
        if (mSelectCityManager == null) {
            mSelectCityManager = SelectCityManager.getInstance(mContext, true);
            mSelectCityManager.setOnSelectOptionListener((options1, provinceName, options2, cityName, options3, areaName) -> {
                provinceOpt = options1;
                cityOpt = options2;
                areaOpt = options3;
                provinceParam = provinceName;
                cityParam = cityName;
                areaParam = areaName;
                XLog.e("" + provinceOpt + " - " + cityOpt + " - " + areaOpt);
                mTvJobInfomCity.setText(String.valueOf(provinceName + " " + cityName + " " + areaName));
            });
        }
        mSelectCityManager.showDialog(provinceOpt, cityOpt, areaOpt);
    }

    private void initView() {
        mIvJobInfomHear = (ImageView) findViewById(R.id.iv_job_infom_hear);
        mEtJobInfomName = (EditText) findViewById(R.id.et_job_infom_name);
        mTvJobInfomSex = (TextView) findViewById(R.id.tv_job_infom_sex);
        mTvJobInfomBirthday = (TextView) findViewById(R.id.tv_job_infom_birthday);
        mTvJobInfomTime = (TextView) findViewById(R.id.tv_job_infom_time);
        mTvJobInfomCity = (TextView) findViewById(R.id.tv_job_infom_city);
        mTvJobInfomPhone = (TextView) findViewById(R.id.tv_job_infom_phone);
        mEtJobInfomEmail = (EditText) findViewById(R.id.et_job_infom_email);
        mBtnJobInfomNext = (Button) findViewById(R.id.btn_job_infom_next);

        mBtnJobInfomNext.setOnClickListener(this);
        mLlJobInfomHear = (LinearLayout) findViewById(R.id.ll_job_infom_hear);
        mLlJobInfomHear.setOnClickListener(this);
        mTvJobInfomPhone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_job_infom_next:
                submit();
//                mResultTo.startEducation(mContext);
                break;
            case R.id.tv_job_infom_phone://绑定手机号
                mResultTo.startVerifyPhone(mContext, VERIFYPHONECODE);
                break;

        }
    }

    private void submit() {
        if (mData == null)
            if (StringUtil.isEmpty(mPath)) {
                T.showToast(mContext, "请选择头像");
                return;
            }

        String name = mEtJobInfomName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请填写真实姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        String sex = StringUtil.getObjectToStr(mTvJobInfomSex);
        if (StringUtil.isEmpty(sex)) {
            T.showToast(mContext, "请选择性别");
            return;
        }
        String birthday = StringUtil.getObjectToStr(mTvJobInfomBirthday);
        if (StringUtil.isEmpty(birthday)) {
            T.showToast(mContext, "请选择出生年月");
            return;
        }
        String time = StringUtil.getObjectToStr(mTvJobInfomTime);
        if (StringUtil.isEmpty(time)) {
            T.showToast(mContext, "请选择参加工作时间");
            return;
        }
//        String mCity = StringUtil.getObjectToStr(mTvJobInfomCity);
        String mCity = cityParam;
        if (StringUtil.isEmpty(mCity)) {
            T.showToast(mContext, "请选择现在居住城市");
            return;
        }

        String phone = mTvJobInfomPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请绑定手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Util.isPhoneNum(phone)) {
            T.showToast(mContext, "请输入正确手机号");
            return;
        }

        String email = mEtJobInfomEmail.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "请输入电子邮箱", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Util.isEaml(email)) {
            T.showToast(mContext, "请输入正确的电子邮箱");
            return;
        }

        if (mData != null) {
            showProgress();
            submitHttp(name, sex, birthday, time, mCity, phone, email, mData.getAvatar());
            return;
        }
        showProgress();
        UpdataImagHttp.getSingleton(this).updataImage("", mPath, new RequestResultCallback() {
            @Override
            public void success(String success) {
                NormalBean vos = GsonUtils.getGson(success, NormalBean.class);
                if (vos.getCode().equals("100")) {
                    T.showToast(mContext, vos.getMessage());
                    return;
                }
                GmImagerVo vo = GsonUtils.getGson(success, GmImagerVo.class);

                submitHttp(name, sex, birthday, time, mCity, phone, email, vo.getResult().getImg_url());

            }

            @Override
            public void error(String error) {
                onError();
            }
        });

    }

    private void save() {
        String name = mEtJobInfomName.getText().toString().trim();
        String sex = StringUtil.getObjectToStr(mTvJobInfomSex);
        String phone = mTvJobInfomPhone.getText().toString().trim();
        String email = mEtJobInfomEmail.getText().toString().trim();
        JobInfom infom = new JobInfom();
        infom.setName(name);
        infom.setSex(sex);
        infom.setYear(mBirthdayYearParam);
        infom.setMonth(mBirthdayMonthParam);
        infom.setDay(mBirthdayDayParam);
        infom.setYearWorkTime(mWorkYearParam);
        infom.setMonthWorkTime(mWorkMonthParam);
        infom.setDayWorkTime(mWorkDayParam);

        infom.setProvinceId(provinceOpt);
        infom.setProvince(provinceParam);
        infom.setCityId(cityOpt);
        infom.setCity(cityParam);
        infom.setAreaId(areaOpt);
        infom.setArea(areaParam);
        infom.setPhone(phone);
        infom.setEmail(email);
        SaveJobUtil.getInstance().delectJobInfom();
        SaveJobUtil.getInstance().putJobInfom(infom);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        save();
    }

    private void submitHttp(String name, String sex, String birthday, String time, String mCity, String phone, String email, String img_url) {
        String sexParam;
        if (sex.equals("男")) {
            sexParam = "2";
        } else {
            sexParam = "1";
        }
        RequestJobHttp.getSingleton(this).submitJobInfom(img_url, name, sexParam, birthday, time, mCity, phone, email, new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                NormalBean vo = GsonUtils.getGson(success, NormalBean.class);
                if (vo.getCode().equals("100")) {
                    T.showToast(mContext, vo.getMessage());
                    return;
                }
                T.showToast(mContext, "提交成功");
                if (mData != null) {
                    EventBus.getDefault().postSticky(new RcRefresh(""));
                    mResultTo.finishBase(mContext);
                    return;
                }
                mResultTo.startEducation(mContext);

            }

            @Override
            public void error(String error) {
                onError();
            }
        });

    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    //拒绝
    @OnPermissionDenied({Manifest.permission.CAMERA})
    void showDeniedForCamera() {
        XToast.normal("权限拒绝");
    }

    // 不再询问
    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void showNeverAskForCamera() {
        XToast.normal("权限拒绝，不再询问");
    }


    @NeedsPermission(Manifest.permission.CAMERA)
    void showCamera() {
        showPicture();

    }

    private void showPicture() {
        if (mCamerAlerdialog != null) {
            if (mCamerAlerdialog.isShowing()) {
                return;
            }
            mCamerAlerdialog.show();
        }
        mCamerAlerdialog = new SelectCamerAlerdialog(mContext, R.style.mydialog);
        mCamerAlerdialog.setCancelable(true);
        mCamerAlerdialog.initData(mContext);
        mCamerAlerdialog.setListener(new SelectCamerAlerdialog.OnItemClickListener() {
            @Override
            public void openCamer() {
                ActivityUtil.openCammer(JobInfomActivity.this, REQUESTCODE);
            }

            @Override
            public void openXinCe() {
                ActivityUtil.openXiangCes(JobInfomActivity.this, REQUESTCODECE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        if (requestCode == REQUESTCODE && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            Bitmap bimap = (Bitmap) bundle.get("data");
            String s = FileUtil.saveImag(mContext, bimap);
            Log.e("===========", "onActivityResult: " + s);
            LunBanUtil.getSingleton(mContext).lunBanImager(s, new LunBanUtil.lunBanInterface() {
                @Override
                public void imgStart() {
                    showProgress();
                }

                @Override
                public void imgSuccess(String path) {
                    dismissProgress();
                    mPath = path;
                    showImag(mIvJobInfomHear, s);
                }

                @Override
                public void imgError() {
                    dismissProgress();
                    T.showToast(mContext, "图片压缩失败,请重新选择");
                }
            });
            return;
        }

        if (requestCode == REQUESTCODECE && resultCode == RESULT_OK) {
            List<String> strings = Matisse.obtainPathResult(data);
            LunBanUtil.getSingleton(mContext).lunBanImagerS(strings, new LunBanUtil.lunBanInterface() {
                @Override
                public void imgStart() {
                    showProgress();
                }

                @Override
                public void imgSuccess(String path) {
                    dismissProgress();
                    mPath = path;
                    showImag(mIvJobInfomHear, path);
                }

                @Override
                public void imgError() {
                    dismissProgress();
                    T.showToast(mContext, "图片压缩失败,请重新选择");
                }
            });
            return;
        }
        if (requestCode == VERIFYPHONECODE && resultCode == RESULT_OK) {
            String phone = data.getStringExtra(PHONE);
            mTvJobInfomPhone.setText(phone);
        }

    }

    private void showImag(ImageView mIvJobInfomHear, String s) {
        GlideUtil.getSingleton().loadBCilcleImager(mContext, mIvJobInfomHear, s);
    }
}
