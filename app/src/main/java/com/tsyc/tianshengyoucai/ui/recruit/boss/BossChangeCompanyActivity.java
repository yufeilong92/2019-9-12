package com.tsyc.tianshengyoucai.ui.recruit.boss;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tsyc.tianshengyoucai.Eventbuss.BossChangeInfomEvent;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.requet.RequestBossHttp;
import com.tsyc.tianshengyoucai.requet.RequestJobHttp;
import com.tsyc.tianshengyoucai.requet.UpdataImagHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.ActivityUtil;
import com.tsyc.tianshengyoucai.utils.FileUtil;
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.LunBanUtil;
import com.tsyc.tianshengyoucai.utils.SelectKeyUtil;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.view.SelectCamerAlerdialog;
import com.tsyc.tianshengyoucai.vo.BossCompanyInfomVo;
import com.tsyc.tianshengyoucai.vo.GmImagerVo;
import com.tsyc.tianshengyoucai.vo.GmSelectBean;
import com.tsyc.tianshengyoucai.vo.JobSelectItemVo;
import com.tsyc.tianshengyoucai.vo.SelectKeyVo;
import com.tsyc.tianshengyoucai.vo.TradeSelectIndox;
import com.youth.xframe.widget.XToast;
import com.zhihu.matisse.Matisse;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/9/4 15:22:
 * @Purpose :编辑公司信息
 */
@RuntimePermissions
public class BossChangeCompanyActivity extends Base2Activity implements View.OnClickListener {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private ImageView mIvBossCciLogo;
    private TextView mTvBossCciName;
    private EditText mEtBossCciJianjie;
    private TextView mTvBossCciIndustry;
    private TextView mTvBossCciScale;
    private TextView mTvBossCciIntroduce;
    private TextView mTvBossCciAddress;
    private Button mBtnBossCciSubmit;
    private SelectKeyVo mVo;
    private String mProvince;
    private String mCity;
    private String mArea;
    private String mAddress;
    private String mProvinceId;
    private String mCityId;
    private String mAreaId;
    public static final String PROVINCE = "province";
    public static final String CITY = "city";
    public static final String AREA = "area";
    public static final String ADDRESS = "address";
    public static final String PROVINCE_ID = "province_ID";
    public static final String CITY_ID = "city_ID";
    public static final String AREA_ID = "area_ID";
    /**
     * 公司地址
     */
    private static final int ADDRESSREQUESCCODE = 1000;
    /**
     * 公司介绍
     */
    private static final int Wrokinfom_REQUESTCODE = 1001;
    /**
     * 行业
     */
    private static final int TRADE_REQUESTCODE = 1002;
    /**
     * 工作描述
     */
    public static final String INPUTWORK = "INPUTWORK";
    /**
     * 用户选择行业
     */
    public static final String SELECT_TRADE = "trade";
    /**
     * 相机
     */
    private static final int CAMMER_REQUESTCODE = 1020;
    /**
     * 相册
     */
    private static final int XIANGCE_REQUESTCODECE = 1021;

    private boolean isUpData;
    /**
     * 人员规模
     */
    private GmSelectBean mSelectScale;
    /**
     * 行业
     */
    private List<TradeSelectIndox> mSelectTradeLists;
    private SelectCamerAlerdialog mAlerdialog;
    private String mPath;
    private String mCompanyId;
    private JobSelectItemVo mSelectIdVo;
    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_boss_change_company);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_boss_change_company;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        initView();
        initEvent();
        initRequestSelect(true);

    }

    private void initRequestSelect(boolean first) {
        showProgress();
        RequestJobHttp.getSingleton(this).requestJobsCommonKeys(new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                mVo = GsonUtils.getGson(success, SelectKeyVo.class);
                if (first)
                    initRequest();
            }

            @Override
            public void error(String error) {
                onError();
            }
        });
    }

    private void initEvent() {

    }

    private void initRequest() {
        showProgress();
        RequestBossHttp.getSingleton(this).requestBossCompanyDetail(new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                BossCompanyInfomVo vo = GsonUtils.getGson(success, BossCompanyInfomVo.class);
                BossCompanyInfomVo.ResultBean result = vo.getResult();
                bindViewData(result);
            }

            @Override
            public void error(String error) {
                onError();
            }
        });
    }

    private void bindViewData(BossCompanyInfomVo.ResultBean result) {
        if (!StringUtil.isEmpty(result.getLogo())) {
            isUpData = true;
        }
        GlideUtil.getSingleton().loadQuadRangleImager(mContext, mIvBossCciLogo, result.getLogo());
        mTvBossCciName.setText(result.getFull_name());
        mEtBossCciJianjie.setText(result.getShort_name());

        BossCompanyInfomVo.ResultBean.IndustriesBean bean = result.getIndustries();
        mSelectTradeLists = new ArrayList<>();
        TradeSelectIndox indox = new TradeSelectIndox();
        JobSelectItemVo vo = new JobSelectItemVo();
        if (bean != null) {
            vo.setId(bean.getValue());
            vo.setName(bean.getDesc());
            mTvBossCciIndustry.setText(bean.getDesc());
        }
        indox.setSelectVo(vo);
        mSelectTradeLists.add(indox);

        GmSelectBean companySize = result.getCompany_size();
        mSelectScale = companySize;
        mTvBossCciScale.setText(companySize.getDesc());
        mTvBossCciIntroduce.setText(result.getDesc());

        BossCompanyInfomVo.ResultBean.AddressInfoBean info = result.getAddress_info();
        StringBuffer buffer = new StringBuffer();
        if (info != null) {
            mProvince = info.getProvince();
            buffer.append(mProvince);
            mCity = info.getCity();
            buffer.append(mCity);
            mArea = info.getArea();
            buffer.append(mArea);
            mAddress = info.getAddress();
            buffer.append(mAddress);
        }
        mTvBossCciAddress.setText(buffer.toString());
        mPath = result.getLogo();
        mCompanyId = String.valueOf(result.getId());
        mProvinceId = String.valueOf(result.getProvince_id());
        mCityId = String.valueOf(result.getCity_id());
        mAreaId = String.valueOf(result.getArea_id());

    }

    private void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mIvBossCciLogo = (ImageView) findViewById(R.id.iv_boss_cci_logo);
        mTvBossCciName = (TextView) findViewById(R.id.tv_boss_cci_name);
        mEtBossCciJianjie = (EditText) findViewById(R.id.et_boss_cci_jianjie);
        mTvBossCciIndustry = (TextView) findViewById(R.id.tv_boss_cci_industry);
        mTvBossCciScale = (TextView) findViewById(R.id.tv_boss_cci_scale);
        mTvBossCciIntroduce = (TextView) findViewById(R.id.tv_boss_cci_introduce);
        mTvBossCciAddress = (TextView) findViewById(R.id.tv_boss_cci_address);
        mBtnBossCciSubmit = (Button) findViewById(R.id.btn_boss_cci_submit);

        mBtnBossCciSubmit.setOnClickListener(this);
        mTvBossCciAddress.setOnClickListener(this);
        mTvBossCciScale.setOnClickListener(this);
        mTvBossCciIndustry.setOnClickListener(this);
        mIvBossCciLogo.setOnClickListener(this);
        mTvBossCciIntroduce.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_boss_cci_submit:
                submit();
                break;
            case R.id.tv_boss_cci_address://地址
                mResultTo.startBossWorkArea(mContext, mProvince, mCity, mArea, mAddress, ADDRESSREQUESCCODE);
                break;
            case R.id.tv_boss_cci_introduce://公司介绍
                String com = StringUtil.getObjectToStr(mTvBossCciIntroduce);
                mResultTo.startWorkInfom(mContext, com, 1, Wrokinfom_REQUESTCODE);
                break;
            case R.id.tv_boss_cci_scale://人员规模
                if (mVo == null) {
                    initRequestSelect(false);
                }
                showScale(mTvBossCciScale);
                break;
            case R.id.tv_boss_cci_industry://行业领域
                mResultTo.startTrade(mContext, TRADE_REQUESTCODE, 1);
                break;
            case R.id.iv_boss_cci_logo://头像
                BossChangeCompanyActivityPermissionsDispatcher.showCameraWithPermissionCheck(BossChangeCompanyActivity.this);
                break;
        }
    }

    private void showScale(TextView mTvBossCciScale) {
        SelectKeyVo.ResultBean result = mVo.getResult();
        List<GmSelectBean> companySize = result.getCompany_size();
        List<String> lists = new ArrayList<>();
        for (int i = 0; i < companySize.size(); i++) {
            GmSelectBean bean = companySize.get(i);
            lists.add(bean.getDesc());
        }

        SelectKeyUtil.showSelect(this, lists, new SelectKeyUtil.SelectClick() {
            @Override
            public void selectItem(int postion, String com) {
                mSelectScale = companySize.get(postion);
                mTvBossCciScale.setText(com);
            }
        });

    }

    private void submit() {

        if (StringUtil.isEmpty(mPath)) {
            T.showToast(mContext, "请选择公司logo");
            return;
        }
        String jianjie = mEtBossCciJianjie.getText().toString().trim();
        if (TextUtils.isEmpty(jianjie)) {
            Toast.makeText(this, "请输入公司简介", Toast.LENGTH_SHORT).show();
            return;
        }
        String industry = StringUtil.getObjectToStr(mTvBossCciIndustry);
        if (StringUtil.isEmpty(industry)) {
            T.showToast(mContext, "请选择行业");
            return;
        }
        String scale = StringUtil.getObjectToStr(mTvBossCciScale);
        if (StringUtil.isEmpty(scale)) {
            T.showToast(mContext, "请选择人员规模");
            return;
        }
        String introduce = StringUtil.getObjectToStr(mTvBossCciIntroduce);
        if (StringUtil.isEmpty(introduce)) {
            T.showToast(mContext, "请输入公司介绍");
            return;
        }
        String address = StringUtil.getObjectToStr(mTvBossCciAddress);
        if (StringUtil.isEmpty(address)) {
            T.showToast(mContext, "请输入公司地址");
            return;
        }
        mSelectIdVo = mSelectTradeLists.get(0).getSelectVo();
        showProgress();
        if (isUpData) {
            submitChanger(jianjie, String.valueOf(mSelectIdVo.getId()), String.valueOf(mSelectScale.getValue()),
                    mPath, introduce, mCompanyId, mProvince, mCity, mArea, mAddress);
            return;
        }

        UpdataImagHttp.getSingleton(this).updataImage("", mPath, new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                GmImagerVo imagerVo = GsonUtils.getGson(success, GmImagerVo.class);
                GmImagerVo.ResultBean result = imagerVo.getResult();
                submitChanger(jianjie, String.valueOf(mSelectIdVo.getId()), String.valueOf(mSelectScale.getValue()),
                        result.getImg_url(), introduce, mCompanyId, mProvince, mCity, mArea, mAddress);

            }

            @Override
            public void error(String error) {
                onError();
            }
        });


    }

    private void submitChanger(String short_name, String industries, String company_size,
                               String logo, String desc, String company_id, String province_id,
                               String city_id, String area_id, String address) {
        RequestBossHttp.getSingleton(this).submitBossChangeInfom(short_name, industries, company_size, logo, desc, company_id, province_id,
                city_id, area_id, address, new RequestResultCallback() {
                    @Override
                    public void success(String success) {
                        if (onSuccess(success)) return;
                        T.showToast(mContext, "提交成功");
                        EventBus.getDefault().postSticky(new BossChangeInfomEvent(""));
                        mResultTo.finishBase(mContext);
                    }

                    @Override
                    public void error(String error) {
                        onError();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        if (resultCode == RESULT_OK && requestCode == ADDRESSREQUESCCODE) {
            mProvince = data.getStringExtra(PROVINCE);
            mCity = data.getStringExtra(CITY);
            mArea = data.getStringExtra(AREA);
            mProvinceId = data.getStringExtra(PROVINCE_ID);
            mCityId = data.getStringExtra(CITY_ID);
            mAreaId = data.getStringExtra(AREA_ID);
            mAddress = data.getStringExtra(ADDRESS);

            mTvBossCciAddress.setText(mProvince + mCity + mArea + mAddress);
            return;
        }

        if (resultCode == RESULT_OK && requestCode == Wrokinfom_REQUESTCODE) {
            String com = data.getStringExtra(INPUTWORK);
            mTvBossCciIntroduce.setText(com);
            return;
        }
        if (resultCode == RESULT_OK && requestCode == TRADE_REQUESTCODE) {
            mSelectTradeLists = (List<TradeSelectIndox>) data.getSerializableExtra(SELECT_TRADE);
            bindTrade(mSelectTradeLists);
            return;
        }
        if (requestCode == CAMMER_REQUESTCODE && resultCode == RESULT_OK) {
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
                    isUpData = true;
                    showImag(mIvBossCciLogo, s);
                }

                @Override
                public void imgError() {
                    dismissProgress();
                    isUpData = false;
                    T.showToast(mContext, "图片压缩失败,请重新选择");
                }
            });
            return;
        }

        if (requestCode == XIANGCE_REQUESTCODECE && resultCode == RESULT_OK) {
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
                    isUpData = true;
                    showImag(mIvBossCciLogo, path);
                }

                @Override
                public void imgError() {
                    dismissProgress();
                    isUpData = false;
                    T.showToast(mContext, "图片压缩失败,请重新选择");
                }
            });
            return;
        }
    }

    private void showImag(ImageView mIvBossCciLogo, String path) {
        GlideUtil.getSingleton().loadBQuadRangleImager(mContext, mIvBossCciLogo, path);
    }

    private void bindTrade(List<TradeSelectIndox> mSelectScale) {
        TradeSelectIndox tradeSelectIndox = mSelectScale.get(0);
        mTvBossCciIndustry.setText(tradeSelectIndox.getSelectVo().getName());

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
        showAlertDialog();
    }

    private void showAlertDialog() {
        if (mAlerdialog != null) {
            if (mAlerdialog.isShowing()) {
                return;
            }
            mAlerdialog.show();
            return;
        }
        mAlerdialog = new SelectCamerAlerdialog(mContext, R.style.mydialog);
        mAlerdialog.initData(mContext);
        mAlerdialog.setListener(new SelectCamerAlerdialog.OnItemClickListener() {
            @Override
            public void openCamer() {
                ActivityUtil.openCammer(BossChangeCompanyActivity.this, CAMMER_REQUESTCODE);
            }

            @Override
            public void openXinCe() {
                ActivityUtil.openXiangCes(BossChangeCompanyActivity.this, XIANGCE_REQUESTCODECE);

            }
        });

    }
}

