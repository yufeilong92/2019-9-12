package com.tsyc.tianshengyoucai.ui.recruit;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.recruit.HistoryAdapter;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.SaveHistoryInfom;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.utils.Util;
import com.tsyc.tianshengyoucai.view.FlowLayout;
import com.tsyc.tianshengyoucai.vo.JobHomeDataVo;
import com.zaaach.citypicker.CityPicker;
import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.model.City;
import com.zaaach.citypicker.model.HotCity;
import com.zaaach.citypicker.model.LocateState;
import com.zaaach.citypicker.model.LocatedCity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/27 15:58:
 * @Purpose :职位搜索
 */
public class JobSearchActivity extends Base2Activity {

    public static String Type = "Type";
    private TextView mTvJobSearchAddress;
    private ImageView mIvJobShSh;
    private EditText mEtJobShInput;
    private TextView mTvJobSearchCanner;
    private RecyclerView mRlvHistoryDay;
    private FlowLayout mFlJobSearchHontJobs;
    private String mKeyWordParam;
    private SaveHistoryInfom mSaveUtils;
    private ImageView mIvJobSearchDelete;
    private ArrayList mArray;
    private HistoryAdapter mAdapter;
    private String mLocation;
    private RadioButton mRbJobSearchWork;
    private RadioButton mRbJobSearchCompany;
    private RadioGroup mRgJobSearch;
    /**
     * 是否公司
     */
    private boolean isCompany = false;
    private JobHomeDataVo mVo;
    private List<HotCity> mHotCities;

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_job_search);
//        initView();
//    }
    public AMapLocationClient mLocationClient = null;
    public AMapLocationClientOption mLocationOption = null;
    private AMapLocation MaMapLocaiton;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {


        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null && mAdapter != null) {
                MaMapLocaiton = aMapLocation;
                String city = MaMapLocaiton.getCity();
                String provider = MaMapLocaiton.getProvince();
                String cityCode = MaMapLocaiton.getCityCode();
                CityPicker.from(JobSearchActivity.this).locateComplete(new LocatedCity(city, provider, cityCode), LocateState.SUCCESS);
            }
        }
    };

    @Override
    protected int getComtentView() {
        return R.layout.activity_job_search;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        if (getIntent() != null) {
            mLocation = getIntent().getStringExtra(Type);
        }
        initView();
        initLocation();
        initEvent();
        clearData();
        initAdapter();
        initHistory();
        initHost();
        mTvJobSearchAddress.setText(mLocation);
    }

    private void initLocation() {
        mLocationClient = new AMapLocationClient(mContext);
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(true);
        mLocationClient.setLocationListener(mLocationListener);

    }

    private void initAdapter() {
        mAdapter = new HistoryAdapter(mContext, mArray);
        RecyclerUtil.setGridManage(mContext, mRlvHistoryDay, mAdapter);
        mAdapter.setListener(new HistoryAdapter.OnItemClickListener() {
            @Override
            public void itemClick(String com) {
                mResultTo.startPositionSearch(mContext, com, mLocation, isCompany);
            }
        });
    }

    private void initEvent() {
        mSaveUtils = SaveHistoryInfom.getInstance();
        mVo = new JobHomeDataVo();
        mVo.setJobs(mArray);
        mHotCities = new ArrayList<>();
        mHotCities.add(new HotCity("北京", "北京", "101010100"));
        mHotCities.add(new HotCity("上海", "上海", "101020100"));
        mHotCities.add(new HotCity("广州", "广东", "101280101"));
        mHotCities.add(new HotCity("深圳", "广东", "101280601"));
        mHotCities.add(new HotCity("杭州", "浙江", "101210101"));

        mEtJobShInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    mKeyWordParam = StringUtil.getObjectToStr(mEtJobShInput);
                    Util.hideInputMethod(JobSearchActivity.this);
                    bindViewData(mKeyWordParam);
                    submit(mKeyWordParam);
                    return true;
                }
                return false;
            }
        });
        mIvJobShSh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.hideInputMethod(JobSearchActivity.this);
                mKeyWordParam = StringUtil.getObjectToStr(mEtJobShInput);
                bindViewData(mKeyWordParam);
                submit(mKeyWordParam);
            }
        });
        mIvJobSearchDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSaveUtils.delectUserInfom();
                clearData();
                mAdapter.notifyDataSetChanged();
            }
        });
        mTvJobSearchCanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResultTo.finishBase(mContext);
            }
        });

        mRgJobSearch.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_job_search_work:
                    isCompany = false;
                    break;
                case R.id.rb_job_search_company:
                    isCompany = true;
                    break;
            }
        });
        mTvJobSearchAddress.setOnClickListener(v -> {
            initCity();
        });
    }

    private void initCity() {
        CityPicker.from(JobSearchActivity.this)
                .enableAnimation(true)
                .setAnimationStyle(R.style.CustomAnim)
                .setLocatedCity(null)
                .setHotCities(mHotCities)
                .setOnPickListener(new OnPickListener() {
                    @Override
                    public void onPick(int position, City data) {
                        mLocation=data.getName();
                        mTvJobSearchAddress.setText(data.getName());
//                        Log.e("===", String.format("当前城市：%s，%s", data.getName(), data.getCode()));
//                        Toast.makeText(
//                                getContext(),
//                                String.format("点击的数据：%s，%s", data.getName(), data.getCode()),
//                                Toast.LENGTH_SHORT)
//                                .show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(mContext, "取消选择", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLocate() {
                        startLocation();
                    }
                })
                .show();
    }

    private void submit(String mKeyWordParam) {
        if (StringUtil.isEmpty(mKeyWordParam)) {
            T.showToast(mContext, "搜索内容为空");
            return;
        }

        mResultTo.startPositionSearch(mContext, mKeyWordParam, mLocation, isCompany);


    }

    private void startLocation() {
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();
    }

    private void bindViewData(String com) {
        if (StringUtil.isEmpty(com)) {
            return;
        }

        List<String> infom = mSaveUtils.getHistoryInfom();
        if (infom == null || infom.isEmpty()) {
            infom = new ArrayList<>();
        }
        infom.add(0, com);
        if (infom.size() >= 10) {
            infom.remove(infom.size() - 1);
        }
        mSaveUtils.putHistoryInfom(infom);
        initHistory();
        mTvJobSearchAddress.setText(mLocation);
    }

    private void initHistory() {
        List<String> historyInfom = mSaveUtils.getHistoryInfom();
        if (historyInfom == null || historyInfom.isEmpty()) return;
        clearData();
        addData(historyInfom);
        mAdapter.notifyDataSetChanged();
    }

    private void initHost() {

    }

    private void initView() {
        mTvJobSearchAddress = (TextView) findViewById(R.id.tv_job_search_address);
        mIvJobShSh = (ImageView) findViewById(R.id.iv_job_sh_sh);
        mEtJobShInput = (EditText) findViewById(R.id.et_job_sh_input);
        mTvJobSearchCanner = (TextView) findViewById(R.id.tv_job_search_canner);
        mRlvHistoryDay = (RecyclerView) findViewById(R.id.rlv_history_day);
        mFlJobSearchHontJobs = (FlowLayout) findViewById(R.id.fl_job_search_hont_jobs);
        mIvJobSearchDelete = (ImageView) findViewById(R.id.iv_job_search_delete);
        mRbJobSearchWork = (RadioButton) findViewById(R.id.rb_job_search_work);
        mRbJobSearchCompany = (RadioButton) findViewById(R.id.rb_job_search_company);
        mRgJobSearch = (RadioGroup) findViewById(R.id.rg_job_search);
    }


    private void clearData() {
        if (mArray == null) {
            mArray = new ArrayList();
        } else {
            mArray.clear();
        }
    }

    private void addData(List<?> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        if (mArray == null) {
            clearData();
        }
        mArray.addAll(list);
    }
}
