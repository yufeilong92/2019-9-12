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
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.GoodCollectAdapter;
import com.tsyc.tianshengyoucai.manager.DataMangerVo;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.requet.RequestSettingHttp;
import com.tsyc.tianshengyoucai.ui.base.BaseFragment;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.DialogUtils;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.GoodCollectVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/10 9:52:
 * @Purpose :商品收藏
 */

public class GoodCollectFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private RecyclerView mGmRlvContent;
    private ImageView mIvEmpty;
    private SmartRefreshLayout mGmSmrlayoyut;
    private ArrayList mArray;
    private boolean mRefresh = false;
    private GoodCollectAdapter mAdapter;

    public static GoodCollectFragment newInstance(String param1, String param2) {
        GoodCollectFragment fragment = new GoodCollectFragment();
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
//        View view = inflater.inflate(R.layout.fragment_good_collect, container, false);
//        initView(view);
//        return view;
//    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_good_collect;
    }

    @Override
    protected void loadData() {

        clearData();
        initAdapter();
        initRefresh();
        mGmSmrlayoyut.autoRefresh();

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
        mAdapter = new GoodCollectAdapter(mContext, mArray);
        mGmRlvContent.setAdapter(mAdapter);
        mAdapter.setListener(new GoodCollectAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int postion, GoodCollectVo.ResultBean vo) {
                mResultTo.startGoodDetail(getActivity(), vo);
            }

            @Override
            public void cannerClick(int postion, GoodCollectVo.ResultBean vo) {
                canner(postion, vo);
            }
        });
    }

    private void canner(int postion, GoodCollectVo.ResultBean vo) {
        DialogUtils.getSingleton().showSureAlerDialog(mContext, "是否确定取消收藏", "", "确定",
                "取消", new DialogUtils.OnDialogSuceClickListener() {
                    @Override
                    public void sureClick() {
                        submitRequest(postion, vo);
                    }

                    @Override
                    public void cannerClick() {

                    }
                });

    }

    private void submitRequest(int positon, GoodCollectVo.ResultBean vo) {
        showProgress();
        RequestSettingHttp.getSingleton(getActivity()).submitCannerCollect(String.valueOf(vo.getFav_id()), "1", new RequestResultCallback() {
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
        RequestSettingHttp.getSingleton(getActivity()).requestMyCollect("goods",
                "1", new RequestResultCallback() {
                    @Override
                    public void success(String success) {
                        mRefresh = false;
                        mGmSmrlayoyut.finishRefresh();
                        GoodCollectVo vo = GsonUtils.getGson(success, GoodCollectVo.class);
                        if (vo.getCode().equals("100")) {
                            T.showToast(mContext, vo.getMessage());
                            return;
                        }
                        List<GoodCollectVo.ResultBean> result = vo.getResult();
                        clearData();

                        if (result == null || result.isEmpty()) {
                            mIvEmpty.setVisibility(View.VISIBLE);
                            mGmSmrlayoyut.setEnableLoadMore(false);
                            mAdapter.notifyDataSetChanged();
                            return;
                        }
                        mIvEmpty.setVisibility(View.GONE);
                        addData(result);
                        if (result.size() == DataMangerVo.PAGERE_NUMBER) {
                            mGmSmrlayoyut.setEnableLoadMore(true);
                            mAdapter.notifyDataSetChanged();
                            return;
                        }
                        if (mArray.size() < DataMangerVo.PAGERE_NUMBER) {
                            mGmSmrlayoyut.setEnableLoadMore(false);
                            mAdapter.notifyDataSetChanged();
                            return;
                        }
                        if (mArray.size() % DataMangerVo.PAGERE_NUMBER == 0) {
                            mGmSmrlayoyut.setEnableLoadMore(false);
                        } else {
                            mGmSmrlayoyut.setEnableLoadMore(true);

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
        RequestSettingHttp.getSingleton(getActivity()).requestMyCollect("goods", String.valueOf(getNowPage() + 1), new RequestResultCallback() {
            @Override
            public void success(String success) {
                mRefresh = false;
                mGmSmrlayoyut.finishLoadMore();
                NormalBean vos = GsonUtils.getGson(success, NormalBean.class);
                if (vos.getCode().equals("100")) {
                    T.showToast(mContext, vos.getMessage());
                    return;
                }
                GoodCollectVo vo = GsonUtils.getGson(success, GoodCollectVo.class);

                List<GoodCollectVo.ResultBean> result = vo.getResult();
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

    @Override
    public void initView(View view) {
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
