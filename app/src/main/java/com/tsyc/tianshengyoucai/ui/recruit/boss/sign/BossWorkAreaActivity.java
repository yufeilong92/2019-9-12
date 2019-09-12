package com.tsyc.tianshengyoucai.ui.recruit.boss.sign;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.manager.SelectCityManager;
import com.tsyc.tianshengyoucai.ui.recruit.boss.BossChangeCompanyActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.ReleaseJobActivity;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.youth.xframe.utils.log.XLog;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/9/3 17:56:
 * @Purpose :boss选择地区
 */
public class BossWorkAreaActivity extends Base2Activity implements View.OnClickListener {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private TextView mTvBossAreaAdress;
    private EditText mEtBossAreaInfom;
    private Button mBtnBossAreaSave;
    private SelectCityManager mSelectCityManager;

    public static final String PRIVOINCE = "privocin";
    public static final String CITY = "city";
    public static final String AREA = "area";
    public static final String ADDRESS = "address";
    private String mAddress;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_boss_work_area);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_boss_work_area;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        if (getIntent() != null) {
            provinceParam = getIntent().getStringExtra(PRIVOINCE);
            cityParam = getIntent().getStringExtra(CITY);
            areaParam = getIntent().getStringExtra(AREA);
            mAddress = getIntent().getStringExtra(ADDRESS);
        }
        initView();
        initEvent();
        bindView();
    }

    private void bindView() {

        mEtBossAreaInfom.setText(mAddress);
        if (!StringUtil.isEmpty(mAddress))
            mEtBossAreaInfom.setSelection(mAddress.length());
        StringBuffer buffer = new StringBuffer();
        if (!StringUtil.isEmpty(provinceParam))
            buffer.append(provinceParam);
        if (!StringUtil.isEmpty(cityParam))
            buffer.append(cityParam);
        if (!StringUtil.isEmpty(areaParam))
            buffer.append(areaParam);
        mTvBossAreaAdress.setText(buffer.toString());
    }

    private void initEvent() {

        mTvBossAreaAdress.setOnClickListener(v -> {
            selectAddress();
        });
    }

    private void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mTvBossAreaAdress = (TextView) findViewById(R.id.tv_boss_area_adress);
        mEtBossAreaInfom = (EditText) findViewById(R.id.et_boss_area_infom);
        mBtnBossAreaSave = (Button) findViewById(R.id.btn_boss_area_save);

        mBtnBossAreaSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_boss_area_save:
                submit();
                break;
        }
    }

    private void submit() {
        String address = StringUtil.getObjectToStr(mTvBossAreaAdress);
        if (StringUtil.isEmpty(address)) {
            T.showToast(mContext, "请选择地区");
            return;
        }

        String infom = mEtBossAreaInfom.getText().toString().trim();
        if (TextUtils.isEmpty(infom)) {
            Toast.makeText(this, "请输入详细地址", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(ReleaseJobActivity.PROVINCE, provinceParam);
        intent.putExtra(ReleaseJobActivity.CITY, cityParam);
        intent.putExtra(ReleaseJobActivity.AREA, areaParam);
        intent.putExtra(ReleaseJobActivity.ADDRESS, infom);
        intent.putExtra(BossChangeCompanyActivity.PROVINCE_ID, String.valueOf(provinceOpt));
        intent.putExtra(BossChangeCompanyActivity.CITY_ID, String.valueOf(cityOpt));
        intent.putExtra(BossChangeCompanyActivity.AREA_ID, String.valueOf(areaOpt));
        setResult(RESULT_OK, intent);
        mResultTo.finishBase(mContext);

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
                mTvBossAreaAdress.setText(String.valueOf(provinceName + " " + cityName + " " + areaName));
            });
        }
        mSelectCityManager.showDialog(provinceOpt, cityOpt, areaOpt);
    }

}
