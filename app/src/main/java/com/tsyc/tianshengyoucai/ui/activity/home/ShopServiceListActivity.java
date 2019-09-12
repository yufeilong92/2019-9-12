package com.tsyc.tianshengyoucai.ui.activity.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.shop.ShopListAdapter;
import com.tsyc.tianshengyoucai.manager.DataMangerVo;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.requet.RequestSettingHttp;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.ShopListVo;
import com.tsyc.tianshengyoucai.vo.ShopVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/15 11:15:
 * @Purpose :商机列表
 */
public class ShopServiceListActivity extends BaseActivity {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private RecyclerView mGmRlvContent;
    private ImageView mIvEmpty;
    private SmartRefreshLayout mGmSmrlayoyut;
    private boolean isRefresh;
    private ArrayList mArray;
    private ShopListAdapter mAdapter;
    public static final String MSHOPID = "ShopiD";
    public static final String TYPE = "type";
    private String mType;
    /**
     * 口碑推荐
     */
    public static final String TYPE_RECOMMEND = "recommend";
    /**
     * 收藏最多
     */
    public static final String TYPE_COLLECT = "collect";
    /**
     * 附近商家
     */
    public static final String TYPE_LOCATION = "location";
    private String mShopId;

    /**
     * 条件过滤 参数 credit: 按好评排序 | sales 按销量排序
     */
    private String mfilterParam;
    /**
     * 地理位置-经度
     */
    private String mlonParam;
    /**
     * 地理位置-纬度
     */
    private String mlatParam;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    public AMapLocationClientOption mLocationOption = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                mlonParam = String.valueOf(aMapLocation.getLongitude());
                mlatParam = String.valueOf(aMapLocation.getLatitude());
                mGmSmrlayoyut.autoRefresh();
            }


        }
    };

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_shop_service_list);
//        initView();
//    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_shop_service_list;
    }

    @Override
    public void initData() {
        if (getIntent() != null) {
            mShopId = getIntent().getStringExtra(MSHOPID);
            mType = getIntent().getStringExtra(TYPE);
        }
        initView();
        initLocation();
        clearData();
        initAdapter();
        initRefresh();
        initEvent();

    }

    private void initLocation() {
        mLocationClient = new AMapLocationClient(mContext);
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(true);
        mLocationClient.setLocationListener(mLocationListener);

    }

    private void initEvent() {
        if (StringUtil.isEmpty(mType)) {
            mGmSmrlayoyut.autoRefresh();
            return;
        }
        if (mType.equals(TYPE_RECOMMEND)) {//口碑推荐
            mfilterParam = "credit";
            mGmSmrlayoyut.autoRefresh();
        } else if (mType.equals(TYPE_COLLECT)) {//收藏最多
            mfilterParam = "sales ";
            mGmSmrlayoyut.autoRefresh();
        } else if (mType.equals(TYPE_LOCATION)) {//定位
            T.showToast(mContext, "获取定位中,请稍等");
            mLocationClient.setLocationOption(mLocationOption);
            mLocationClient.startLocation();
        }


    }

    private void initAdapter() {
        mAdapter = new ShopListAdapter(mContext, mArray);
        RecyclerUtil.setGridManage(mContext, mGmRlvContent, mAdapter);
        mAdapter.setListener(new ShopListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int postion, ShopVo vo) {
                mResultTo.startShop(ShopServiceListActivity.this, vo.getStore_id());
            }
        });

    }

    private void initRefresh() {

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
        if (isRefresh) {
            return;
        }
        isRefresh = true;
        RequestSettingHttp.getSingleton(this).requestMoreShopList(mShopId, "1", mfilterParam, mlonParam, mlatParam, "", new RequestResultCallback() {
            @Override
            public void success(String success) {
                isRefresh = false;
                mGmSmrlayoyut.finishRefresh();
                NormalBean vos = GsonUtils.getGson(success, NormalBean.class);
                if (vos.getCode().equals("100")) {
                    mIvEmpty.setVisibility(View.VISIBLE);
                    mGmRlvContent.setVisibility(View.GONE);
                    T.showToast(mContext, vos.getMessage());
                    return;
                }
                ShopListVo vo = GsonUtils.getGson(success, ShopListVo.class);

                clearData();
                List<ShopVo> datas = vo.getResult().getList();
                if (datas == null || datas.isEmpty()) {
                    mIvEmpty.setVisibility(View.VISIBLE);
                    mGmRlvContent.setVisibility(View.GONE);
                    mGmSmrlayoyut.setEnableLoadMore(false);
                    mAdapter.notifyDataSetChanged();
                    return;
                }
                mIvEmpty.setVisibility(View.GONE);
                mGmRlvContent.setVisibility(View.VISIBLE);
                addData(datas);

                if (mArray.size() % DataMangerVo.PAGERE_NUMBER == 0) {
                    mGmSmrlayoyut.setEnableLoadMore(true);
                } else {
                    mGmSmrlayoyut.setEnableLoadMore(false);
                }
                mAdapter.notifyDataSetChanged();


            }

            @Override
            public void error(String error) {
                isRefresh = false;
                mGmSmrlayoyut.finishRefresh();
                dismissProgress();
                toastErrorNet();
            }
        });


    }

    private void loadMore() {
        if (isRefresh) {
            return;
        }
        isRefresh = true;
        RequestSettingHttp.getSingleton(this).requestMoreShopList(mShopId, String.valueOf(getNowPage() + 1),
                mfilterParam, mlonParam, mlatParam, "", new RequestResultCallback() {
                    @Override
                    public void success(String success) {
                        isRefresh = false;
                        mGmSmrlayoyut.finishLoadMore();
                        NormalBean vos = GsonUtils.getGson(success, NormalBean.class);
                        if (vos.getCode().equals("100")) {
                            T.showToast(mContext, vos.getMessage());
                            return;
                        }
                        ShopListVo vo = GsonUtils.getGson(success, ShopListVo.class);

                        List<ShopVo> datas = vo.getResult().getList();
                        if (datas == null || datas.isEmpty()) {
                            mGmSmrlayoyut.setEnableLoadMore(false);
                            mAdapter.notifyDataSetChanged();
                            return;
                        }
                        addData(datas);
                        if (mArray.size() % DataMangerVo.PAGERE_NUMBER == 0) {
                            mGmSmrlayoyut.setEnableLoadMore(true);
                        } else {
                            mGmSmrlayoyut.setEnableLoadMore(false);
                        }
                        mAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void error(String error) {
                        isRefresh = false;
                        mGmSmrlayoyut.finishLoadMore();
                        dismissProgress();
                        toastErrorNet();
                    }
                });

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
        mGmRlvContent = (RecyclerView) findViewById(R.id.gm_rlv_content);
        mIvEmpty = (ImageView) findViewById(R.id.iv_empty);
        mGmSmrlayoyut = (SmartRefreshLayout) findViewById(R.id.gm_smrlayoyut);
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

    /**
     * 当前数据有几页
     *
     * @return
     */
    private int getNowPage() {
        if (mArray == null || mArray.isEmpty())
            return 0;
        if (mArray.size() % DataMangerVo.PAGERE_NUMBER == 0)
            return mArray.size() / DataMangerVo.PAGERE_NUMBER;
        else
            return mArray.size() / DataMangerVo.PAGERE_NUMBER + 1;
    }
}
