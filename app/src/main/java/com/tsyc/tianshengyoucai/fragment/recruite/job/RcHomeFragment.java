package com.tsyc.tianshengyoucai.fragment.recruite.job;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.recruit.RecruitHomeAdapter;
import com.tsyc.tianshengyoucai.base.Base2Fragment;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.requet.RequestJobHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.ui.recruit.RecruitInActivity;
import com.tsyc.tianshengyoucai.ui.recruit.jobmine.AllPostionActivity;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.BossJobItemVo;
import com.tsyc.tianshengyoucai.vo.JobHomeDataVo;
import com.tsyc.tianshengyoucai.vo.JobHomeListsVo;
import com.tsyc.tianshengyoucai.vo.RcBannerVo;
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
 * @Time :2019/8/23 14:31:
 * @Purpose :招聘数据
 */
public class RcHomeFragment extends Base2Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private RecyclerView mGmRlvContent;
    private ImageView mIvEmpty;
    private SmartRefreshLayout mGmSmrlayoyut;
    private RecruitHomeAdapter mAdapter;
    private List<HotCity> mHotCities;
    private RequestJobHttp mRequestJobHttp;
    private JobHomeDataVo mVo;
    private ArrayList mArray;
    private String mCity;

    public static RcHomeFragment newInstance(String param1, String param2) {
        RcHomeFragment fragment = new RcHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    //    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_rc_home, container, false);
//        initView(view);
//        return view;
//    }
    public AMapLocationClient mLocationClient = null;
    public AMapLocationClientOption mLocationOption = null;
    private AMapLocation MaMapLocaiton;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {


        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null && mAdapter != null) {
                mCity = aMapLocation.getCity();
                MaMapLocaiton = aMapLocation;
                mAdapter.refreshLocation(aMapLocation.getCity());
            }
        }
    };

    @Override
    protected int getFramgentViewId() {
        return R.layout.fragment_rc_home;
    }

    @Override
    protected void initContentView(View view, Bundle savedInstanceState) {
        initView(view);
        initLocation();
        clearData();
        initEvent();
        initAdapter();
        initRefresh();
        mGmSmrlayoyut.autoRefresh();
        startLocation();
    }

    private void startLocation() {
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();
    }

    private void initView(View view) {
        mGmRlvContent = (RecyclerView) view.findViewById(R.id.gm_rlv_content);
        mIvEmpty = (ImageView) view.findViewById(R.id.iv_empty);
        mGmSmrlayoyut = (SmartRefreshLayout) view.findViewById(R.id.gm_smrlayoyut);
    }

    private void initEvent() {
        mRequestJobHttp = RequestJobHttp.getSingleton(getActivity());
        mVo = new JobHomeDataVo();
        mVo.setJobs(mArray);
        mHotCities = new ArrayList<>();
        mHotCities.add(new HotCity("北京", "北京", "101010100"));
        mHotCities.add(new HotCity("上海", "上海", "101020100"));
        mHotCities.add(new HotCity("广州", "广东", "101280101"));
        mHotCities.add(new HotCity("深圳", "广东", "101280601"));
        mHotCities.add(new HotCity("杭州", "浙江", "101210101"));

    }

    private void initLocation() {
        mLocationClient = new AMapLocationClient(mContext);
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(true);
        mLocationClient.setLocationListener(mLocationListener);

    }

    private void initAdapter() {
        mAdapter = new RecruitHomeAdapter(mContext, mVo);
        RecyclerUtil.setGridManage(mContext, mGmRlvContent, mAdapter);
        mAdapter.setListener(new RecruitHomeAdapter.OnItemClickListener() {
            @Override
            public void itemClick(BossJobItemVo vo) {
                mResultTo.startJobDetail(getActivity(), vo.getId());
            }

            @Override
            public void deleteItemClick(int vo) {

            }

            /**
             * 地址
             * @param mLocationName
             */
            @Override
            public void locationClick(String mLocationName) {
                initCity();
            }

            /**
             * 轮播图
             * @param resultBean
             */
            @Override
            public void bannerOnClick(RcBannerVo.ResultBean resultBean) {

            }

            @Override
            public void typeOnClick(int type) {
                switch (type) {
                    case 1://附近职位
                        mResultTo.startNearest(mContext, mCity);
                        break;
                    case 2://全职专区
                        mResultTo.startAllPostion(mContext, AllPostionActivity.QUAN_TYPE);
                        break;
                    case 3://兼职
                        mResultTo.startAllPostion(mContext, AllPostionActivity.JIAN_TYPE);
                        break;
                    case 4://全部
                        mResultTo.startAllPostion(mContext, AllPostionActivity.ALL_TYPE);
                        break;
                    case 5://我要招聘
                        mResultTo.startRecruitInHome(getActivity(), RecruitInActivity.CHANGERTYPE, RecruitInActivity.JOB_ID);
                        break;
                    case 6://更多职位
                        mResultTo.startPostionList(mContext);
                        break;
                }

            }

            @Override
            public void searchOnClick(String mLocationName) {
                mResultTo.startJobSearch(getActivity(), mLocationName);
            }
        });
    }

    private void initCity() {
        CityPicker.from(getActivity())
                .enableAnimation(true)
                .setAnimationStyle(R.style.CustomAnim)
                .setLocatedCity(null)
                .setHotCities(mHotCities)
                .setOnPickListener(new OnPickListener() {
                    @Override
                    public void onPick(int position, City data) {
                        mCity = data.getName();
                        mAdapter.refreshLocation(data.getName());
//                        Log.e("===", String.format("当前城市：%s，%s", data.getName(), data.getCode()));
//                        Toast.makeText(
//                                getContext(),
//                                String.format("点击的数据：%s，%s", data.getName(), data.getCode()),
//                                Toast.LENGTH_SHORT)
//                                .show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(getContext(), "取消选择", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLocate() {
                        //开始定位，这里模拟一下定位
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (MaMapLocaiton == null) {
                                    T.showToast(mContext, "定位失败");
                                    CityPicker.from(getActivity()).locateComplete(new LocatedCity("郑州", "河南", "101180101"), LocateState.SUCCESS);
                                } else {
                                    String city = MaMapLocaiton.getCity();
                                    String provider = MaMapLocaiton.getProvince();
                                    String cityCode = MaMapLocaiton.getCityCode();
                                    CityPicker.from(getActivity()).locateComplete(new LocatedCity(city, provider, cityCode), LocateState.SUCCESS);
                                }
                            }
                        }, 1000);

                    }
                })
                .show();
    }

    private void initRefresh() {
        mGmSmrlayoyut.setEnableAutoLoadMore(false);
        mGmSmrlayoyut.setEnableLoadMore(false);
        mGmSmrlayoyut.setEnableRefresh(true);
        mGmSmrlayoyut.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadNew();
            }
        });
        mGmSmrlayoyut.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore();
            }
        });
    }


    private void loadNew() {
        mRequestJobHttp.requestJobsBanner(new RequestResultCallback() {
            @Override
            public void success(String success) {
                NormalBean vo = GsonUtils.getGson(success, NormalBean.class);
                if (vo.getCode().equals("100")) {
                    mGmSmrlayoyut.finishRefresh();
                    mIvEmpty.setVisibility(View.VISIBLE);
                    mGmRlvContent.setVisibility(View.GONE);
                    T.showToast(mContext, vo.getMessage());
                    return;
                }
                RcBannerVo bannerVo = GsonUtils.getGson(success, RcBannerVo.class);
                mVo.setBanner(bannerVo.getResult());
                RequestJobsList();
            }

            @Override
            public void error(String error) {
                mGmSmrlayoyut.finishRefresh();
                onError();
            }
        });
    }

    private void RequestJobsList() {
        mRequestJobHttp.requestJobsRecommend("1", new RequestResultCallback() {
            @Override
            public void success(String success) {
                mGmSmrlayoyut.finishRefresh();
                if (onSuccess(success)) {
                    mAdapter.refreshData(mVo);
                    mAdapter.notifyDataSetChanged();
                    return;
                }
                showContent(success, true);


            }

            @Override
            public void error(String error) {
                mGmSmrlayoyut.finishRefresh();
                onError();
            }
        });


    }

    private void showContent(String success, boolean isRefresh) {
        if (isRefresh) {
            clearData();
        }
        JobHomeListsVo vo = GsonUtils.getGson(success, JobHomeListsVo.class);
        JobHomeListsVo.ResultBean result = vo.getResult();
        List<BossJobItemVo> data = result.getData();
        if (data == null || data.isEmpty()) {
            mVo.setJobs(mArray);
            mAdapter.refreshData(mVo);
            mAdapter.notifyDataSetChanged();
            return;
        }

        addData(data);
        mPage = result.getCurrent_page() + 1;
        mVo.setJobs(mArray);
        mAdapter.refreshData(mVo);
        mAdapter.notifyDataSetChanged();


    }

    private int mPage;

    private void loadMore() {
        mRequestJobHttp.requestJobsRecommend(String.valueOf(mPage), new RequestResultCallback() {
            @Override
            public void success(String success) {
                mGmSmrlayoyut.finishRefresh();
                if (onSuccess(success)) {
                    mAdapter.refreshData(mVo);
                    mAdapter.notifyDataSetChanged();
                    return;
                }
                showContent(success, false);
            }

            @Override
            public void error(String error) {
                mGmSmrlayoyut.finishRefresh();
                onError();
            }
        });

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
