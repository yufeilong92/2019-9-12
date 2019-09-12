package com.tsyc.tianshengyoucai.fragment;

import android.os.Bundle;
import android.os.Handler;
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
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.home.HomSearchAdapter;
import com.tsyc.tianshengyoucai.flyn.Eyes;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.requet.RequestSettingHttp;
import com.tsyc.tianshengyoucai.ui.base.BaseFragment;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.utils.Util;
import com.tsyc.tianshengyoucai.vo.SearchGoodVo;

import java.util.ArrayList;
import java.util.List;


public class HomeTypeFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private ImageView mGmIcBack;
    private ImageView mIvHsSearch;
    private EditText mEtHsInput;
    private LinearLayout mLlHomeType;
    private TextView mIvHomeType;
    private TextView mTvHsZsort;
    private ImageView mIvHsZsort;
    private LinearLayout mLlHsZsort;
    private TextView mTvHsXsort;
    private ImageView mIvHsXsort;
    private LinearLayout mLlHsXsort;
    private TextView mTvHomeshValues;
    private ImageView mIvHsLogo;
    private ImageView mIvHsLogoDown;
    private LinearLayout mLlHsValues;
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
    private String mSortPriceParam;
    /**
     * 分类id
     */
    private String mCategoryIdParam;

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

    private HomSearchAdapter mAdapter;


    public static HomeTypeFragment newInstance(String param1, String param2) {
        HomeTypeFragment fragment = new HomeTypeFragment();
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
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_home_type, container, false);
//        initView(view);
//        return view;
//    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_home_type;
    }

    @Override
    protected void loadData() {
        Eyes.translucentStatusBar(getActivity(), false);
        clearData();
        initEvent();
        initAdapter();
        initRefresh();
        mGmSmrlayoyut.autoRefresh();

    }

    private void initEvent() {
        mHttpReqeust = RequestSettingHttp.getSingleton(getActivity());
        zong = true;
        showOrHineTitle(zong, false, false);
        //综合
        mLlHsZsort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRefrehs) {
                    T.showToast(mContext, "正在请求数据,请稍后重试");
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
                showOrHineTitle(zong, xiao, values);
                refresh();
            }
        });
        //销量
        mLlHsXsort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRefrehs) {
                    T.showToast(mContext, "正在请求数据,请稍后重试");
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

                showOrHineTitle(zong, xiao, values);
                refresh();
            }
        });
        //价格
        mLlHsValues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRefrehs) {
                    T.showToast(mContext, "正在请求数据,请稍后重试");
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
                showOrHineTitle(zong, xiao, values);
                refresh();
            }
        });
        mIvHomeType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResultTo.startTYpe(getActivity());
            }
        });
        //键盘
        mEtHsInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    mKeyWordParam = StringUtil.getObjectToStr(mEtHsInput);
                    mGmSmrlayoyut.autoRefresh();
                    Util.hideInputMethod(getActivity());
                    return true;
                }
                return false;
            }
        });
        //搜索
        mIvHsSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mKeyWordParam = StringUtil.getObjectToStr(mEtHsInput);
                mGmSmrlayoyut.autoRefresh();

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

    private void refresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mGmSmrlayoyut.autoRefresh();
            }
        }, 300);
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
                mResultTo.startGoodDetail(getActivity(), bean);
            }
        });

    }

    /**
     * +
     *
     * @param z 综合
     * @param x 销量
     * @param v 价格
     */
    private void showOrHineTitle(boolean z, boolean x, boolean v) {
        textColorSelect(mTvHsZsort, z);
        ivSelect(mIvHsZsort, z);
        textColorSelect(mTvHsXsort, x);
        ivSelect(mIvHsXsort, x);
        textColorSelect(mTvHomeshValues, v);
        ivSelect(mIvHsLogoDown, v);
        mIvHsLogo.setImageResource(v ? R.mipmap.jft_but_ascendingorder_down : R.mipmap.jft_but_ascendingorder);
    }

    private void textColorSelect(TextView tv, boolean select) {
        tv.setTextColor(getResources().getColor(select ? R.color.tab_color : R.color.color_444A53));
    }

    private void ivSelect(ImageView iv, boolean select) {
        iv.setVisibility(select ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void initView(View view) {
        mIvHsSearch = (ImageView) view.findViewById(R.id.iv_hs_search);
        mEtHsInput = (EditText) view.findViewById(R.id.et_hs_input);
        mLlHomeType = (LinearLayout) view.findViewById(R.id.ll_home_type);
        mIvHomeType = (TextView) view.findViewById(R.id.iv_home_type);
        mTvHsZsort = (TextView) view.findViewById(R.id.tv_hs_zsort);
        mIvHsZsort = (ImageView) view.findViewById(R.id.iv_hs_zsort);
        mLlHsZsort = (LinearLayout) view.findViewById(R.id.ll_hs_zsort);
        mTvHsXsort = (TextView) view.findViewById(R.id.tv_hs_xsort);
        mIvHsXsort = (ImageView) view.findViewById(R.id.iv_hs_xsort);
        mLlHsXsort = (LinearLayout) view.findViewById(R.id.ll_hs_xsort);
        mTvHomeshValues = (TextView) view.findViewById(R.id.tv_homesh_values);
        mIvHsLogo = (ImageView) view.findViewById(R.id.iv_hs_logo);
        mIvHsLogoDown = (ImageView) view.findViewById(R.id.iv_hs_logo_down);
        mLlHsValues = (LinearLayout) view.findViewById(R.id.ll_hs_values);
        mGmRlvContent = (RecyclerView) view.findViewById(R.id.gm_rlv_content);
        mIvEmpty = (ImageView) view.findViewById(R.id.iv_empty);
        mGmSmrlayoyut = (SmartRefreshLayout) view.findViewById(R.id.gm_smrlayoyut);
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
