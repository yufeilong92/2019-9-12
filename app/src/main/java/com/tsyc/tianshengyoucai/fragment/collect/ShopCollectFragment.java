package com.tsyc.tianshengyoucai.fragment.collect;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tsyc.tianshengyoucai.Eventbuss.ShopCollectEvent;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.ShopCollectAdapter;
import com.tsyc.tianshengyoucai.manager.DataMangerVo;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.requet.RequestSettingHttp;
import com.tsyc.tianshengyoucai.ui.base.BaseFragment;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.DialogUtils;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.ShopCollectVo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/10 9:52:
 * @Purpose :店铺收藏
 */
public class ShopCollectFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private RecyclerView mGmRlvContent;
    private ImageView mIvEmpty;
    private SmartRefreshLayout mGmSmrlayoyut;
    private ArrayList mArray;
    private int page = 1;
    private ShopCollectAdapter mAdapter;
    private boolean mRefresh = false;

    public static ShopCollectFragment newInstance(String param1, String param2) {
        ShopCollectFragment fragment = new ShopCollectFragment();
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
//        View view = inflater.inflate(R.layout.fragment_shop_collect, container, false);
//        initView(view);
//        return view;
//    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_shop_collect;
    }

    @Override
    protected void loadData() {

        clearData();
        initAdapter();
        initRefresh();
        mGmSmrlayoyut.autoRefresh();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().removeAllStickyEvents();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void refresh(ShopCollectEvent event) {
        if (mGmSmrlayoyut != null) {
            mGmSmrlayoyut.autoRefresh();
        }

    }

    @Override
    public void initView(View view) {
        mGmRlvContent = (RecyclerView) view.findViewById(R.id.gm_rlv_content);
        mIvEmpty = (ImageView) view.findViewById(R.id.iv_empty);
        mGmSmrlayoyut = (SmartRefreshLayout) view.findViewById(R.id.gm_smrlayoyut);
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

    private void initAdapter() {
        RecyclerUtil.setGridManage(mActivity, mGmRlvContent);
        mAdapter = new ShopCollectAdapter(mContext, mArray);
        mGmRlvContent.setAdapter(mAdapter);
        mAdapter.setListener(new ShopCollectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int postion, ShopCollectVo.ResultBean vo) {
                mResultTo.startShopDetail(getActivity(), vo,"1");
            }

            @Override
            public void cannerClick(int postion, ShopCollectVo.ResultBean vo) {
                canner(postion, vo);
            }
        });
    }

    private void canner(int postion, ShopCollectVo.ResultBean vo) {
        DialogUtils.getSingleton().showSureAlerDialog(mContext, "是否确定取消收藏", "", "确定",
                "取消", new DialogUtils.OnDialogSuceClickListener() {
                    @Override
                    public void sureClick() {
//                        submitRequest(postion, vo);
                        mResultTo.startShopDetail(getActivity(), vo,"2");
                    }

                    @Override
                    public void cannerClick() {

                    }
                });

    }

    private void submitRequest(int positon, ShopCollectVo.ResultBean vo) {
        showProgress();
        RequestSettingHttp.getSingleton(getActivity()).submitCannerCollect(String.valueOf(vo.getStore_id()), "", new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                NormalBean vo = GsonUtils.getGson(success, NormalBean.class);
                if (vo.getCode().equals("100")) {
                    T.showToast(mContext, vo.getMessage());
                    return;
                }
                T.showToast(mContext, "取消收藏成功");
                mArray.remove(positon);
                mAdapter.notifyItemRemoved(positon);
            }

            @Override
            public void error(String error) {
                dismissProgress();
                toastErrorNet();
            }
        });

    }

    private void initRequest() {
        if (mRefresh) {
            return;
        }
        mRefresh = true;
        RequestSettingHttp.getSingleton(getActivity()).requestMyCollect("store",
                "1", new RequestResultCallback() {
                    @Override
                    public void success(String success) {
                        mRefresh = false;
                        mGmSmrlayoyut.finishRefresh();
                        NormalBean vos = GsonUtils.getGson(success, NormalBean.class);
                        if (vos.getCode().equals("100")) {
                            T.showToast(mContext, vos.getMessage());
                            return;
                        }
                        ShopCollectVo vo = GsonUtils.getGson(success, ShopCollectVo.class);
                        List<ShopCollectVo.ResultBean> result = vo.getResult();
                        clearData();

                        if (result == null || result.isEmpty()) {
                            mIvEmpty.setVisibility(View.VISIBLE);
                            mGmSmrlayoyut.setEnableLoadMore(false);
                            mAdapter.notifyDataSetChanged();
                            return;
                        }
                        addData(result);
                        mIvEmpty.setVisibility(View.GONE);
//                        if (result.size() == DataMangerVo.PAGERE_NUMBER) {
//                            mGmSmrlayoyut.setEnableLoadMore(true);
//                            mAdapter.notifyDataSetChanged();
//                            return;
//                        }
//                        if (result.size() < DataMangerVo.PAGERE_NUMBER) {
//                            mGmSmrlayoyut.setEnableLoadMore(false);
//                            mAdapter.notifyDataSetChanged();
//                            return;
//                        }
                        if (mArray.size() % DataMangerVo.PAGERE_NUMBER == 0) {
                            mGmSmrlayoyut.setEnableLoadMore(true);
                        } else {
                            mGmSmrlayoyut.setEnableLoadMore(false);
                        }
                        mAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void error(String error) {
                        mRefresh = false;
                        mGmSmrlayoyut.finishRefresh();
                        toastErrorNet();

                    }
                });

    }

    private void loadMore() {
        if (mRefresh) {
            return;
        }
        mRefresh = true;
        RequestSettingHttp.getSingleton(getActivity()).requestMyCollect("store", String.valueOf(getNowPage() + 1), new RequestResultCallback() {
            @Override
            public void success(String success) {
                mRefresh = false;
                mGmSmrlayoyut.finishLoadMore();
                NormalBean vos = GsonUtils.getGson(success, NormalBean.class);
                if (vos.getCode().equals("100")) {
                    T.showToast(mContext, vos.getMessage());
                    return;
                }
                ShopCollectVo vo = GsonUtils.getGson(success, ShopCollectVo.class);

                List<ShopCollectVo.ResultBean> result = vo.getResult();
                addData(result);
//                if (mArray == null || mArray.isEmpty()) {
//                    mAdapter.notifyDataSetChanged();
//                    return;
//                }
//                if (mArray.size() <= DataMangerVo.PAGERE_NUMBER) {
//                    mGmSmrlayoyut.setEnableLoadMore(false);
//                    mAdapter.notifyDataSetChanged();
//                    return;
//                }
                if (mArray.size() % DataMangerVo.PAGERE_NUMBER == 0) {
                    mGmSmrlayoyut.setEnableLoadMore(true);
                } else {
                    mGmSmrlayoyut.setEnableLoadMore(false);

                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void error(String error) {
                mRefresh = false;
                mGmSmrlayoyut.finishLoadMore();
                toastErrorNet();
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
