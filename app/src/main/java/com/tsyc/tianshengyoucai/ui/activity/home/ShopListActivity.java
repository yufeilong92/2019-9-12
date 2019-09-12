package com.tsyc.tianshengyoucai.ui.activity.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.tsyc.tianshengyoucai.adapter.shop.AllShopSubAdapter;
import com.tsyc.tianshengyoucai.adapter.shop.AllShopTypeAdapter;
import com.tsyc.tianshengyoucai.adapter.shop.ShopListAdapter;
import com.tsyc.tianshengyoucai.adapter.shop.ShopSortAdapter;
import com.tsyc.tianshengyoucai.manager.DataMangerVo;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.requet.RequestSettingHttp;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.utils.Util;
import com.tsyc.tianshengyoucai.view.pop.ShopListAllCategoryPop;
import com.tsyc.tianshengyoucai.vo.AllTypeBeanVo;
import com.tsyc.tianshengyoucai.vo.ShopListVo;
import com.tsyc.tianshengyoucai.vo.ShopTypeVo;
import com.tsyc.tianshengyoucai.vo.ShopVo;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/10 14:34:
 * @Purpose :更多商家
 */
public class ShopListActivity extends BaseActivity {

    private ImageView mIvMoreshopSearch;
    private RecyclerView mGmRlvContent;
    private ImageView mIvEmpty;
    private SmartRefreshLayout mGmSmrlayoyut;
    private RelativeLayout mRlShowSelect;
    private ArrayList mArray;
    private ArrayList mTypeArray;
    private ArrayList mSortArray;
    private ShopListAdapter mAdapter;
    private RelativeLayout mRlAllType;
    private RelativeLayout mRlFuType;
    private RelativeLayout mRlSortType;
    private ImageView mIvShoplistBack;
    private ShopTypeVo mVo;
    private AllShopTypeAdapter mTypeAdapter;
    private RecyclerView mRlvSelectTagContent;
    private RecyclerView mRlvSelectSortContent;
    private RelativeLayout mRlShowSortSelect;
    private TextView mTvType1;
    private ImageView mTvTypeLogo;
    private TextView mTvType2;
    private ImageView mIvFujinLogo;
    private TextView mTvType3;
    private ImageView mIvSortLogo;
    private ShopSortAdapter mSortAdapter;
    /**
     * 店铺分类id
     */
    private String mTidParam;
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
    /**
     * 搜素关键字
     */
    private String mkeyWordParam;
    private EditText mEtShopSeacher;
    private ImageView mivShopBaidu;
    private boolean isRefresh;

    //二级分类
    private LinearLayout mLLTop;
    private AllShopSubAdapter childAdapter;

    //    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_shop_list);
//        initView();
//    }
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
    private ShopListAllCategoryPop pop;


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_shop_list;
    }

    @Override
    public void initData() {
        initLocation();
        initEvent();
        clearData();
        clearSortData();
        clearTypeData();
        initAdapter();
        initRefresh();
        initAllType();

    }

    private void initLocation() {
        mLocationClient = new AMapLocationClient(mContext);
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(true);
        mLocationClient.setLocationListener(mLocationListener);

    }

    /**
     * 智能排序
     */
    private void initSortAdapter() {
        if (mSortAdapter != null) {
            mSortAdapter.notifyDataSetChanged();
            return;
        }
        RecyclerUtil.setGridManage(mContext, mRlvSelectSortContent);
        mSortAdapter = new ShopSortAdapter(mContext, mSortArray);
        mRlvSelectSortContent.setAdapter(mSortAdapter);
        mSortAdapter.setListener(new ShopSortAdapter.OnItemClickListener() {
            @Override
            public void itemClick(String vo) {
                if (StringUtil.isEquest(vo, "智能排序")) {
                    mfilterParam = "";
                    mGmSmrlayoyut.autoRefresh();
                    showTypeOrSort(false, false);
                } else if (StringUtil.isEquest(vo, "离我最近")) {
                    showTypeOrSort(false, false);
                    startLocation();
                } else if (StringUtil.isEquest(vo, "好评优先")) {
                    mfilterParam = "credit";
                    showTypeOrSort(false, false);
                    mGmSmrlayoyut.autoRefresh();
                } else if (StringUtil.isEquest(vo, "销量最好")) {
                    mfilterParam = "sales ";
                    showTypeOrSort(false, false);
                    mGmSmrlayoyut.autoRefresh();
                }
                mTvType3.setText(vo);

            }
        });
    }

    /**
     * 所有类型
     */
    private void initTypeAdapter() {
        if (mTypeAdapter != null) {
            mTypeAdapter.notifyDataSetChanged();
            return;
        }
        RecyclerUtil.setGridManage(mContext, mRlvSelectTagContent);
        mTypeAdapter = new AllShopTypeAdapter(mContext, mTypeArray);
        mRlvSelectTagContent.setAdapter(mTypeAdapter);
        mTypeAdapter.setListener(new AllShopTypeAdapter.OnItemClickListener() {
            @Override
            public void allTypeClick(int position, AllTypeBeanVo vo) {
                if (vo.getSc_id() == -1)
                    mTidParam = "";
                else
                    mTidParam = String.valueOf(vo.getSc_id());
                mTvType1.setText(vo.getSc_name());
                showTypeOrSort(false, false);
                mGmSmrlayoyut.autoRefresh();
            }
        });
    }

    private void initAllType() {

        showProgress();
        RequestSettingHttp.getSingleton(this).requestMoreType(new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                XLog.e("" + success);
                NormalBean mVos = GsonUtils.getGson(success, NormalBean.class);
                if (mVos.getCode().equals("100")) {
                    T.showToast(mContext, mVos.getMessage());
                    return;
                }
                mVo = GsonUtils.getGson(success, ShopTypeVo.class);
                List<AllTypeBeanVo> all_type = mVo.getResult().getAll_type();
                AllTypeBeanVo vo = new AllTypeBeanVo();
                vo.setSc_name("全部分类");
                vo.setSc_bail(R.mipmap.logo);
                vo.setSc_id(-1);
                all_type.add(0, vo);
                mGmSmrlayoyut.autoRefresh();
            }

            @Override
            public void error(String error) {
                dismissProgress();
                toastErrorNet();
            }
        });

    }

    private void initRefresh() {
        mGmSmrlayoyut.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initRequest();
            }
        });
        mGmSmrlayoyut.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore();
            }
        });
    }

    private void loadMore() {
        if (isRefresh) {
            return;
        }
        isRefresh = true;
        RequestSettingHttp.getSingleton(this).requestMoreShopList(mTidParam, String.valueOf(getNowPage() + 1), mfilterParam, mlonParam, mlatParam, mkeyWordParam, new RequestResultCallback() {
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

    private void initAdapter() {
        RecyclerUtil.setGridManage(mContext, mGmRlvContent);
        mAdapter = new ShopListAdapter(mContext, mArray);
        mGmRlvContent.setAdapter(mAdapter);
        mAdapter.setListener(new ShopListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int postion, ShopVo vo) {
                mResultTo.startShop(ShopListActivity.this, vo.getStore_id());
            }
        });
    }

    private void initRequest() {
        if (isRefresh) {
            return;
        }
        isRefresh = true;
        RequestSettingHttp.getSingleton(this).requestMoreShopList(mTidParam, "1", mfilterParam, mlonParam, mlatParam, mkeyWordParam, new RequestResultCallback() {
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

    private void initEvent() {
        mEtShopSeacher.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    mkeyWordParam = StringUtil.getObjectToStr(mEtShopSeacher);
                    mGmSmrlayoyut.autoRefresh();
                    return true;
                }
                return false;
            }
        });
        mIvMoreshopSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mkeyWordParam = StringUtil.getObjectToStr(mEtShopSeacher);
                mGmSmrlayoyut.autoRefresh();
            }
        });
        mEtShopSeacher.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                mkeyWordParam = StringUtil.getObjectToStr(mEtShopSeacher);
            }
        });


    }


    @Override
    public void initView() {
        mIvMoreshopSearch = (ImageView) findViewById(R.id.iv_moreshop_search);
        mGmRlvContent = (RecyclerView) findViewById(R.id.gm_rlv_content);
        mIvEmpty = (ImageView) findViewById(R.id.iv_empty);
        mGmSmrlayoyut = (SmartRefreshLayout) findViewById(R.id.gm_smrlayoyut);
        mLLTop = (LinearLayout) findViewById(R.id.ll_top);
        mRlShowSelect = (RelativeLayout) findViewById(R.id.rl_show_select);
        mRlShowSelect.setOnClickListener(this);
        mRlAllType = (RelativeLayout) findViewById(R.id.rl_all_type);
        mRlAllType.setOnClickListener(this);
        mRlFuType = (RelativeLayout) findViewById(R.id.rl_fu_type);
        mRlFuType.setOnClickListener(this);
        mRlSortType = (RelativeLayout) findViewById(R.id.rl_sort_type);
        mRlSortType.setOnClickListener(this);
        mIvShoplistBack = (ImageView) findViewById(R.id.iv_shoplist_back);
        mIvShoplistBack.setOnClickListener(this);
        mRlvSelectTagContent = (RecyclerView) findViewById(R.id.rlv_select_tag_content);
        mRlvSelectTagContent.setOnClickListener(this);
        mRlvSelectSortContent = (RecyclerView) findViewById(R.id.rlv_select_sort_content);
        mRlvSelectSortContent.setOnClickListener(this);
        mRlShowSortSelect = (RelativeLayout) findViewById(R.id.rl_show_sort_select);
        mRlShowSortSelect.setOnClickListener(this);
        mTvType1 = (TextView) findViewById(R.id.tv_type1);
        mTvTypeLogo = (ImageView) findViewById(R.id.tv_type_logo);
        mTvTypeLogo.setOnClickListener(this);
        mTvType2 = (TextView) findViewById(R.id.tv_type2);
        mIvFujinLogo = (ImageView) findViewById(R.id.iv_fujin_logo);
        mIvFujinLogo.setOnClickListener(this);
        mTvType3 = (TextView) findViewById(R.id.tv_type3);
        mIvSortLogo = (ImageView) findViewById(R.id.iv_sort_logo);
        mIvSortLogo.setOnClickListener(this);
        mEtShopSeacher = (EditText) findViewById(R.id.et_shop_seacher);
        mivShopBaidu = (ImageView) findViewById(R.id.iv_shop_baidu);
        mivShopBaidu.setOnClickListener(this);


    }

    // TODO: 2019/9/3
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_all_type:
                showAllPop();
//                if (mVo != null) {
//                    showTypeOrSort(true, false);
//                    ShopTypeVo.ResultBean result = mVo.getResult();
//                    List<AllTypeBeanVo> type = result.getAll_type();
//                    clearTypeData();
//                    addTypeData(type);
//                    initTypeAdapter();
//                }

                break;
            case R.id.iv_shop_baidu:
                mResultTo.startBaiduMoeny(mContext);
                break;
            case R.id.rl_fu_type://附近
                if (Util.handleOnDoubleClick()) {
                    return;
                }
                startLocation();
                break;
            case R.id.rl_sort_type:
                if (mVo != null) {
                    showTypeOrSort(false, true);
                    ShopTypeVo.ResultBean result = mVo.getResult();
                    List<String> type = result.getSort();
                    clearSortData();
                    addSortData(type);
                    initSortAdapter();
                }
                break;
            case R.id.rl_show_select:
                showTypeOrSort(false, false);
                break;
            case R.id.iv_shoplist_back:
                mResultTo.finishBase(this);
                break;
            case R.id.rl_show_sort_select:
                showTypeOrSort(false, false);
                break;
            default:
                break;
        }


    }

    private void showAllPop() {
        pop = new ShopListAllCategoryPop(mContext);
        pop.showPopupWindow(mLLTop);

        if (mVo != null) {
            RecyclerView mRvMain = pop.findViewById(R.id.rv_main);
            RecyclerView mRvSub = pop.findViewById(R.id.rv_sub);
            initCateAdapter(mRvMain, mRvSub, mVo);

        }

    }

    private void initCateAdapter(RecyclerView main, RecyclerView sub, ShopTypeVo mVo) {
        showTypeOrSort(true, false);
        ShopTypeVo.ResultBean result = mVo.getResult();
        List<AllTypeBeanVo> type = result.getAll_type();
        ImageView mIvImage = pop.findViewById(R.id.iv_empty);
        mIvImage.setVisibility(View.VISIBLE);
        RecyclerUtil.setGridManage(mContext, main);
        RecyclerUtil.setGridManage(mContext, sub);
        AllShopTypeAdapter mTypeAdapter = new AllShopTypeAdapter(mContext, type);
        main.setAdapter(mTypeAdapter);

        mTypeAdapter.setListener((position, vo) -> {
            if (position == 0) {
                pop.dismiss();
                if (vo.getSc_id() == -1)
                    mTidParam = "";
                else
                    mTidParam = String.valueOf(vo.getSc_id());
                mTvType1.setText(vo.getSc_name());
                showTypeOrSort(false, false);
                mGmSmrlayoyut.autoRefresh();
                return;
            }

            AllTypeBeanVo allTypeBeanVo = mVo.getResult().getAll_type().get(position);
            if (allTypeBeanVo.getChild() != null && !allTypeBeanVo.getChild().isEmpty()) {
                mIvImage.setVisibility(View.GONE);
                List<AllTypeBeanVo.ChildBean> child = allTypeBeanVo.getChild();
                childAdapter = new AllShopSubAdapter(mContext, child);
                sub.setAdapter(childAdapter);

                childAdapter.setListener((position1, vo1) -> {
                    pop.dismiss();
                    if (vo1.getSc_id() == -1)
                        mTidParam = "";
                    else
                        mTidParam = String.valueOf(vo1.getSc_id());
                    mTvType1.setText(vo1.getSc_name());
                    showTypeOrSort(false, false);
                    mGmSmrlayoyut.autoRefresh();
                });
            } else {
                mIvImage.setVisibility(View.VISIBLE);
            }
        });
    }

    private void startLocation() {
        T.showToast(mContext, "获取定位中,请稍等");
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();
    }

    private void showTypeOrSort(boolean show, boolean sort) {
        mRlShowSelect.setVisibility(show ? View.VISIBLE : View.GONE);
        mRlShowSortSelect.setVisibility(sort ? View.VISIBLE : View.GONE);
    }


    private void clearData() {
        if (mArray == null) {
            mArray = new ArrayList();
        } else {
            mArray.clear();
        }
    }

    private void clearSortData() {
        if (mSortArray == null) {
            mSortArray = new ArrayList();
        } else {
            mSortArray.clear();
        }
    }

    private void clearTypeData() {
        if (mTypeArray == null) {
            mTypeArray = new ArrayList();
        } else {
            mTypeArray.clear();
        }
    }

    private void addTypeData(List<?> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        if (mTypeArray == null) {
            clearData();
        }
        mTypeArray.addAll(list);
    }

    private void addSortData(List<?> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        if (mSortArray == null) {
            clearData();
        }
        mSortArray.addAll(list);
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
