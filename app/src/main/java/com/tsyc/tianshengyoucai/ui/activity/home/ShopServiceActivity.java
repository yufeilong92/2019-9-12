package com.tsyc.tianshengyoucai.ui.activity.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.home.ShopServiceAdapter;
import com.tsyc.tianshengyoucai.manager.DataMangerVo;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.requet.RequestSettingHttp;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.ShopServiceVo;
import com.tsyc.tianshengyoucai.vo.ShopVo;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/14 19:03:
 * @Purpose :商家服务
 */
public class ShopServiceActivity extends BaseActivity {

    private ImageView mIvShoplistBack;
    private ImageView mIvShopBaidu;
    private RecyclerView mGmRlvContent;
    private ImageView mIvEmpty;
    private SmartRefreshLayout mGmSmrlayoyut;
    private ArrayList mArray;
    private boolean isRefresh;
    private ShopServiceAdapter mAdapter;
    private RequestSettingHttp mSingleton;
    private int mPage = 1;
    private ShopServiceVo mVo;
    private RelativeLayout mRlShopSearch;
    private TextView mTvShopSearch;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_shop_service);
//        initView();
//    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_shop_service;
    }

    @Override
    public void initData() {
        initView();
        clearData();
        initEvent();
        initRefresh();
        mGmSmrlayoyut.autoRefresh();
    }

    private void initRefresh() {
        mGmSmrlayoyut.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore();
            }
        });
        mGmSmrlayoyut.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadNew();
            }
        });
    }


    private void loadNew() {
        if (isRefresh) {
            return;
        }
        isRefresh = true;
        mSingleton.requestShopService("1", new RequestResultCallback() {
            @Override
            public void success(String success) {
                XLog.e("附近商家 "+success);
                isRefresh = false;
                mGmSmrlayoyut.finishRefresh();
               NormalBean  mVos = GsonUtils.getGson(success, NormalBean.class);
                if (mVos.getCode().equals("100")) {
                    mIvEmpty.setVisibility(View.VISIBLE);
                    mGmRlvContent.setVisibility(View.GONE);
                    T.showToast(mContext, mVos.getMessage());
                    return;
                }
                mVo = GsonUtils.getGson(success, ShopServiceVo.class);
                clearData();
                List<ShopVo> mListVo = mVo.getResult().getList();
                if (mListVo == null || mListVo.isEmpty()) {
                    mIvEmpty.setVisibility(View.VISIBLE);
                    mGmRlvContent.setVisibility(View.GONE);
                    if (mAdapter!=null)
                    mAdapter.notifyDataSetChanged();
                    mGmSmrlayoyut.setEnableLoadMore(false);
                    return;
                }
                mIvEmpty.setVisibility(View.GONE);
                mGmRlvContent.setVisibility(View.VISIBLE);
                addData(mListVo);
                if (mArray.size() % DataMangerVo.PAGERE_NUMBER == 0) {
                    mGmSmrlayoyut.setEnableLoadMore(true);
                } else {
                    mGmSmrlayoyut.setEnableLoadMore(false);
                }
                List<ShopServiceVo.ResultBean.TypeListBean> typeList = mVo.getResult().getTypeList();
                ShopServiceVo.ResultBean.TypeListBean bean = new ShopServiceVo.ResultBean.TypeListBean();
                bean.setLocal(R.mipmap.jft_but_recommend);
                bean.setSc_name("口碑推荐");
                bean.setSc_sort(-100);
                typeList.add(bean);

                ShopServiceVo.ResultBean.TypeListBean bean1 = new ShopServiceVo.ResultBean.TypeListBean();
                bean1.setLocal(R.mipmap.jft_but_thelargestcollection);
                bean1.setSc_name("收藏最多");
                bean1.setSc_sort(-101);
                typeList.add(bean1);

                ShopServiceVo.ResultBean.TypeListBean bean2 = new ShopServiceVo.ResultBean.TypeListBean();
                bean2.setLocal(R.mipmap.jft_but_nearbymerchants);
                bean2.setSc_name("附近商家");
                bean2.setSc_sort(-102);
                typeList.add(bean2);
                initAapter();


            }

            @Override
            public void error(String error) {
                isRefresh = false;
                mGmSmrlayoyut.finishRefresh();
                toastErrorNet();
            }
        });
    }

    private void loadMore() {
        if (isRefresh) {
            return;

        }
        isRefresh = true;
        mSingleton.requestShopService(String.valueOf(getNowPage() + 1), new RequestResultCallback() {
            @Override
            public void success(String success) {
                isRefresh = false;
                mGmSmrlayoyut.finishLoadMore();
                NormalBean vos = GsonUtils.getGson(success, NormalBean.class);
                if (vos.getCode().equals("100")) {
                    T.showToast(mContext, vos.getMessage());
                    return;
                }
                ShopServiceVo vo = GsonUtils.getGson(success, ShopServiceVo.class);

                List<ShopVo> mListVo = vo.getResult().getList();
                if (mListVo == null || mListVo.isEmpty()) {
                    mGmSmrlayoyut.setEnableLoadMore(false);
                    mAdapter.notifyDataSetChanged();
                    return;
                }
                addData(mListVo);
                if (mArray.size() % DataMangerVo.PAGERE_NUMBER == 0) {
                    mGmSmrlayoyut.setEnableLoadMore(true);
                } else {
                    mGmSmrlayoyut.setEnableLoadMore(false);
                }
                ShopServiceVo.ResultBean result = mVo.getResult();
                result.setList(mArray);
                mVo.setResult(result);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void error(String error) {
                isRefresh = false;
                mGmSmrlayoyut.finishLoadMore();
                toastErrorNet();
            }
        });
    }

    private void initAapter() {
        if (mAdapter != null) {
            mAdapter.refresh(mVo);
            mAdapter.notifyDataSetChanged();
            return;
        }
        mAdapter = new ShopServiceAdapter(mContext, mVo);
        RecyclerUtil.setGridManage(mContext, mGmRlvContent, mAdapter);
        mAdapter.setListener(new ShopServiceAdapter.OnItemClickListener() {

            @Override
            public void bannerClick(ShopServiceVo.ResultBean.AdBean vo) {

            }

            @Override
            public void moreShop() {
                mResultTo.startShopList(mContext);
            }

            @Override
            public void shopItemClick(ShopVo vo) {
                mResultTo.startShop(ShopServiceActivity.this, vo.getStore_id());
            }

            @Override
            public void itemTypeClick(int position, ShopServiceVo.ResultBean.TypeListBean vo) {
                mResultTo.startShopService(mContext, vo);
            }

            /**
             * 1 休闲零食 2，下午茶，3口碑推荐，4收藏最多，5附近商城
             * @param position
             */
            @Override
            public void postionTypeClick(int position) {
                switch (position) {
                    case 3:
                        mResultTo.startShopService(mContext,position);
                        break;
                    case 4:
                        mResultTo.startShopService(mContext,position);
                        break;
                    case 5:
                        mResultTo.startShopService(mContext,position);
                        break;
                }

            }
        });
    }

    private void initEvent() {
        mSingleton = RequestSettingHttp.getSingleton(this);

        mIvShopBaidu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResultTo.startBaiduMoeny(mContext);
            }
        });

    }

    public void initView() {
        mIvShoplistBack = (ImageView) findViewById(R.id.iv_shoplist_back);
        mIvShoplistBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResultTo.finishBase(mContext);
            }
        });
        mIvShopBaidu = (ImageView) findViewById(R.id.iv_shop_baidu);
        mGmRlvContent = (RecyclerView) findViewById(R.id.gm_rlv_content);
        mIvEmpty = (ImageView) findViewById(R.id.iv_empty);
        mGmSmrlayoyut = (SmartRefreshLayout) findViewById(R.id.gm_smrlayoyut);
        mRlShopSearch = (RelativeLayout) findViewById(R.id.rl_shop_search);
        mRlShopSearch.setOnClickListener(this);
        mTvShopSearch = (TextView) findViewById(R.id.tv_shop_search);
        mTvShopSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_shop_search:
            case R.id.tv_shop_search:
                mResultTo.startShopList(mContext);
                break;
            default:
                break;
        }


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
