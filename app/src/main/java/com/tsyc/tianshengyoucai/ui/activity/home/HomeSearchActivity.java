package com.tsyc.tianshengyoucai.ui.activity.home;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.home.HomSearchAdapter;
import com.tsyc.tianshengyoucai.flyn.Eyes;
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
import com.tsyc.tianshengyoucai.vo.SearchGoodVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/13 8:57:
 * @Purpose :首页搜索界面
 */
public class HomeSearchActivity extends BaseActivity {

    private ImageView mGmIcBack;
    private TextView mTvHomeshValues;
    private RelativeLayout mRlHsValues;
    private RecyclerView mGmRlvContent;
    private ImageView mIvEmpty;
    private SmartRefreshLayout mGmSmrlayoyut;
    private RequestSettingHttp mHttpReqeust;
    private ArrayList mArray;
    private boolean mRefrehs;
    /**
     * 搜索关键字
     */
    private String mKeyWordParam;
    /**
     * 排序类型 all（综合排序）sales（销量排序）price（价格排序）
     */
    private String mSortTypeParam;
    /**
     * 1（按价格降序排列） 0 （按价格升序排列 ）
     */
    private String mSortPriceParam = "0";
    /**
     * 分类id
     */
    private String mCategoryIdParam;
    private HomSearchAdapter mAdapter;
    private ImageView mIvHsSearch;
    private EditText mEtHsInput;
    private TextView mTvHsZsort;
    private TextView mTvHsXsort;
    private ImageView mIvHsLogo;
    /**
     * 综合
     */
    private boolean zong;
    /**
     * 销量
     */
    private boolean xiao;
    /**
     * 价格
     */
    private boolean values;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home_search);
//        initView();
//    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_home_search;
    }

    @Override
    public void initData() {
        Eyes.translucentStatusBar(this, false);
        initView();
        clearData();
        initEvent();
        bindView();
        initAdapter();
        initRefresh();
        mGmSmrlayoyut.autoRefresh();

    }

    private void bindView() {
        textColorSelect(mTvHsZsort, zong);
        textColorSelect(mTvHsXsort, xiao);
        textColorSelect(mTvHomeshValues, values);
        showImgSelect(mIvHsLogo, values);

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

    private int mPage;

    private void loadNew() {
        if (mRefrehs) {
            return;
        }
        mRefrehs = true;
        mHttpReqeust.requestHomeSearch("1", mKeyWordParam, mSortTypeParam, mSortPriceParam,
                mCategoryIdParam, new RequestResultCallback() {
                    @Override
                    public void success(String success) {
                        mRefrehs = false;
                        mGmSmrlayoyut.finishRefresh();
                        NormalBean vos = GsonUtils.getGson(success, NormalBean.class);
                        if (vos.getCode().equals("100")) {
                            T.showToast(mContext, vos.getMessage());
                            return;
                        }
                        SearchGoodVo vo = GsonUtils.getGson(success, SearchGoodVo.class);

                        clearData();
                        addData(vo.getResult().getData());
                        if (mArray == null || mArray.isEmpty()) {
                            mIvEmpty.setVisibility(View.VISIBLE);
                            mGmSmrlayoyut.setEnableLoadMore(false);
                            mAdapter.notifyDataSetChanged();
                            return;
                        }
                        mIvEmpty.setVisibility(View.GONE);
                        if (mArray.size() >= vo.getResult().getTotal()) {
                            mGmSmrlayoyut.setEnableLoadMore(false);
                            mAdapter.notifyDataSetChanged();
                            return;
                        }
                        mGmSmrlayoyut.setEnableLoadMore(true);
                        mPage = vo.getResult().getCurrent_page() + 1;
                        mAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void error(String error) {
                        mRefrehs = false;
                        mGmSmrlayoyut.finishRefresh();
                        toastErrorNet();
                    }
                });
    }

    private void loadMore() {
        if (mRefrehs) {
            return;
        }
        mRefrehs = true;
        mHttpReqeust.requestHomeSearch(String.valueOf(mPage), mKeyWordParam, mSortTypeParam, mSortPriceParam,
                mCategoryIdParam, new RequestResultCallback() {
                    @Override
                    public void success(String success) {
                        mRefrehs = false;
                        mGmSmrlayoyut.finishLoadMore();
                        NormalBean vos = GsonUtils.getGson(success, NormalBean.class);
                        if (vos.getCode().equals("100")) {
                            T.showToast(mContext, vos.getMessage());
                            return;
                        }
                        SearchGoodVo vo = GsonUtils.getGson(success, SearchGoodVo.class);
                        List<SearchGoodVo.ResultBean.DataBean> data = vo.getResult().getData();
                        if (data == null || data.isEmpty()) {
                            mGmSmrlayoyut.setEnableLoadMore(false);
                            mAdapter.notifyDataSetChanged();
                            return;
                        }
                        addData(data);
                        if (mArray.size() >= vo.getResult().getTotal()) {
                            mGmSmrlayoyut.setEnableLoadMore(false);
                            mAdapter.notifyDataSetChanged();
                            return;
                        }
                        mGmSmrlayoyut.setEnableLoadMore(true);
                        mPage = vo.getResult().getCurrent_page() + 1;
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void error(String error) {
                        mRefrehs = false;
                        mGmSmrlayoyut.finishLoadMore();
                        toastErrorNet();
                    }
                });

    }

    private void initAdapter() {
        mAdapter = new HomSearchAdapter(mContext, mArray);
        RecyclerUtil.setGridManage(mContext, mGmRlvContent, 2, mAdapter);
        mAdapter.setListener(new HomSearchAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, SearchGoodVo.ResultBean.DataBean bean) {
                mResultTo.startGoodDetail(mContext, bean);
            }
        });

    }

    private void initEvent() {
        mHttpReqeust = RequestSettingHttp.getSingleton(this);
        mEtHsInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    mKeyWordParam = StringUtil.getObjectToStr(mEtHsInput);
                    mGmSmrlayoyut.autoRefresh();
                    Util.hideInputMethod(HomeSearchActivity.this);
                    return true;
                }
                return false;
            }
        });

        mEtHsInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mKeyWordParam = StringUtil.getObjectToStr(mEtHsInput);
                if (TextUtils.isEmpty(s.toString()))
                    mGmSmrlayoyut.autoRefresh();
            }
        });
        mIvHsSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mKeyWordParam = StringUtil.getObjectToStr(mEtHsInput);
                mGmSmrlayoyut.autoRefresh();

            }
        });
        mTvHomeshValues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //综合排序
        mTvHsZsort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRefrehs) {
                    T.showToast(mContext, "正在请求数据，请稍后重试");
                    return;
                }
                if (zong) {
                    zong = false;
                    mSortTypeParam = "";
                } else {
                    zong = true;
                    mSortTypeParam = "all";


                }
                xiao = false;
                values = false;
                mSortPriceParam = "";
                bindView();
                refresh();

            }
        });
        //销量排序
        mTvHsXsort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRefrehs) {
                    T.showToast(mContext, "正在请求数据，请稍后重试");
                    return;
                }
                if (xiao) {
                    xiao = false;
                    mSortTypeParam = "";
                    mSortPriceParam = "";
                } else {
                    xiao = true;
                    mSortTypeParam = "sales";
                    mSortPriceParam = "1";
                }
                zong = false;
                values = false;

                bindView();
                refresh();
            }
        });
        //价格排序
//        mRlHsValues.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//            }
//        });
        mTvHomeshValues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRefrehs) {
                    T.showToast(mContext, "正在请求数据，请稍后重试");
                    return;
                }
                if (values) {
                    values = false;
                    mSortPriceParam = "0";
                    mSortTypeParam = "price";
                } else {
                    values = true;
                    mSortPriceParam = "1";
                    mSortTypeParam = "price";
                }
                xiao = false;
                zong = false;
                bindView();
                refresh();
            }
        });
    }

    private void refresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mGmSmrlayoyut.autoRefresh();
            }
        }, 300);
    }

    private void textColorSelect(TextView tv, boolean select) {
        tv.setTextColor(getResources().getColor(select ? R.color.tab_color : R.color.color_444A53));
    }

    private void showImgSelect(ImageView iv, boolean select) {
        iv.setImageResource(select ? R.mipmap.jft_but_ascendingorder_down : R.mipmap.jft_but_ascendingorder);
    }


    public void initView() {
        mGmIcBack = (ImageView) findViewById(R.id.gm_ic_back);
        mGmIcBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResultTo.finishBase(mContext);
            }
        });
        mTvHomeshValues = (TextView) findViewById(R.id.tv_homesh_values);
        mRlHsValues = (RelativeLayout) findViewById(R.id.rl_hs_values);
        mGmRlvContent = (RecyclerView) findViewById(R.id.gm_rlv_content);
        mIvEmpty = (ImageView) findViewById(R.id.iv_empty);
        mGmSmrlayoyut = (SmartRefreshLayout) findViewById(R.id.gm_smrlayoyut);
        mIvHsSearch = (ImageView) findViewById(R.id.iv_hs_search);
        mEtHsInput = (EditText) findViewById(R.id.et_hs_input);
        mTvHsZsort = (TextView) findViewById(R.id.tv_hs_zsort);
        mTvHsXsort = (TextView) findViewById(R.id.tv_hs_xsort);
        mIvHsLogo = (ImageView) findViewById(R.id.iv_hs_logo);
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

    private void submit() {
        String input = mEtHsInput.getText().toString().trim();
        if (TextUtils.isEmpty(input)) {
            Toast.makeText(this, "请输入您要搜索的内容", Toast.LENGTH_SHORT).show();
            return;
        }

    }
}
