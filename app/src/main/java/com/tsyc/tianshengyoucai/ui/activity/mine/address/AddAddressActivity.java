package com.tsyc.tianshengyoucai.ui.activity.mine.address;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tsyc.tianshengyoucai.Eventbuss.AddressEvent;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.manager.SelectCityManager;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.requet.RequestSettingHttp;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.utils.Util;
import com.tsyc.tianshengyoucai.vo.AddressListBeanVo;
import com.youth.xframe.utils.log.XLog;

import org.greenrobot.eventbus.EventBus;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/9 10:51:
 * @Purpose :添加地址
 */
public class AddAddressActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private EditText mEtADName;
    private EditText mEtADPhone;
    private TextView mEtADArea;
    private EditText mEtADAddress;
    private TextView mTvADWaringLeft;
    private TextView mTvADWaring;
    private ImageView mIvADSelect;
    private Button mBtnADSubmit;
    private LinearLayout mLlADSelect;
    private String province;
    private String city;
    private String area;
    public static final String KEY = "key";
    private AddressListBeanVo mVo;
    private TextView mTvADArea;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_address);
//        initView();
//    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_add_address;
    }

    @Override
    public void initData() {
        if (getIntent() != null) {
            mVo = (AddressListBeanVo) getIntent().getSerializableExtra(KEY);
        }

        initView();
        initEvent();
        bindViewData();
    }

    private void bindViewData() {
        if (mVo == null || StringUtil.isEmpty(mVo.getTrue_name())) {
            return;
        } else {
            String[] s = mVo.getArea_info().split(" ");
            switch (s.length) {
                case 1:
                    province = s[0];
                    break;
                case 2:
                    province = s[0];
                    city = s[1];
                    break;
                case 3:
                    province = s[0];
                    city = s[1];
                    area = s[2];
                    break;

            }
            mTvADArea.setText(mVo.getArea_info());
            mEtADName.setText(mVo.getTrue_name());
            mEtADAddress.setText(mVo.getAddress());
            mEtADPhone.setText(mVo.getMob_phone());
            if (mVo.getIs_default().equals("1")) {
                mTvADWaring.setVisibility(View.GONE);
                mTvADWaringLeft.setText("该地址为当前默认地址");
                mIvADSelect.setVisibility(View.GONE);
            }
        }
    }

    private boolean select = false;
    private SelectCityManager selectCityManager;

    private void initEvent() {
        mLlADSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (select) {
                    select = false;
                    mIvADSelect.setImageResource(R.mipmap.jft_but_noslip);
                } else {
                    select = true;
                    mIvADSelect.setImageResource(R.mipmap.jft_but_slide);
                }
            }
        });
        mEtADArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.hideInputMethod(AddAddressActivity.this);
                selectAddress();
            }
        });
        mBtnADSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

    private int provinceOpt;
    private int cityOpt;
    private int areaOpt;


    //选择地址
    private void selectAddress() {
        if (selectCityManager == null) {
            selectCityManager = SelectCityManager.getInstance(mContext, true);
            selectCityManager.setOnSelectOptionListener((options1, provinceName, options2, cityName, options3, areaName) -> {
                provinceOpt = options1;
                cityOpt = options2;
                areaOpt = options3;
                province = provinceName;
                city = cityName;
                area = areaName;
                XLog.e("" + provinceOpt + " - " + cityOpt + " - " + areaOpt);
                mEtADArea.setText(String.valueOf(provinceName + " " + cityName + " " + areaName));
            });
        }

        selectCityManager.showDialog(provinceOpt, cityOpt, areaOpt);
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
        mEtADName = (EditText) findViewById(R.id.et_a_d_name);
        mEtADPhone = (EditText) findViewById(R.id.et_a_d_phone);
        mEtADArea = (TextView) findViewById(R.id.tv_a_d_area);
        mEtADAddress = (EditText) findViewById(R.id.et_a_d_address);
        mTvADWaringLeft = (TextView) findViewById(R.id.tv_a_d_waring_left);
        mTvADWaring = (TextView) findViewById(R.id.tv_a_d_waring);
        mIvADSelect = (ImageView) findViewById(R.id.iv_a_d_select);
        mBtnADSubmit = (Button) findViewById(R.id.btn_a_d_submit);

        mBtnADSubmit.setOnClickListener(this);
        mLlADSelect = (LinearLayout) findViewById(R.id.ll_a_d_select);
        mLlADSelect.setOnClickListener(this);
        mTvADArea = (TextView) findViewById(R.id.tv_a_d_area);
        mTvADArea.setOnClickListener(this);
    }


    private void submit() {
        String name = StringUtil.getObjectToStr(mEtADName);
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入真实姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        String phone = StringUtil.getObjectToStr(mEtADPhone);
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean num = Util.isPhoneNum(phone);
        if (!num) {
            Toast.makeText(this, "请输入正确手机号码", Toast.LENGTH_SHORT).show();
            return;
        }

        String areas = StringUtil.getObjectToStr(mEtADArea);
        if (TextUtils.isEmpty(areas)) {
            Toast.makeText(this, "请选择所在地区", Toast.LENGTH_SHORT).show();
            return;
        }
        String address = StringUtil.getObjectToStr(mEtADAddress);
        if (TextUtils.isEmpty(address)) {
            Toast.makeText(this, "请输入详细地址", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mVo == null) {
            showProgress();
            RequestSettingHttp.getSingleton(this).submitaddress(name, address, phone, select,
                    province, city, area, new RequestResultCallback() {
                        @Override
                        public void success(String success) {
                            dismissProgress();
                            NormalBean vo = GsonUtils.getGson(success, NormalBean.class);
                            if (vo.getCode().equals("100")) {
                                T.showToast(mContext, vo.getMessage());
                                return;
                            }
                            T.showToast(mContext, StringUtil.getStringWid(mContext, R.string.save_success));
                            EventBus.getDefault().postSticky(new AddressEvent(1));
                            mResultTo.finishBase(mContext);
                        }

                        @Override
                        public void error(String error) {
                            dismissProgress();
                            toastErrorNet();
                        }
                    });
        } else {
            showProgress();
            RequestSettingHttp.getSingleton(this).submitMakeadress(String.valueOf(mVo.getAddress_id()), name, address, phone,
                    String.valueOf(mVo.getProvince_id()), String.valueOf(mVo.getCity_id()), String.valueOf(mVo.getArea_id()), select,
                    province, city, area, new RequestResultCallback() {
                        @Override
                        public void success(String success) {
                            dismissProgress();
                            NormalBean vo = GsonUtils.getGson(success, NormalBean.class);
                            if (vo.getCode().equals("100")) {
                                T.showToast(mContext, vo.getMessage());
                                return;
                            }
                            T.showToast(mContext, StringUtil.getStringWid(mContext, R.string.save_success));
                            EventBus.getDefault().postSticky(new AddressEvent(1));
                            mResultTo.finishBase(mContext);
                        }

                        @Override
                        public void error(String error) {
                            dismissProgress();
                            toastErrorNet();
                        }
                    });
        }

    }
}
