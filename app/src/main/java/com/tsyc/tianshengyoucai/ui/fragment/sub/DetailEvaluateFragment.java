package com.tsyc.tianshengyoucai.ui.fragment.sub;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import com.classic.common.MultipleStatusView;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.model.adapter.ShopEvaluateAdapter;
import com.tsyc.tianshengyoucai.model.bean.DetailEvaluateBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.base.BaseFragment;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.view.FlowRadioGroup;
import com.tsyc.tianshengyoucai.view.SimpleDividerItemDecortion;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import butterknife.BindView;

/**
 * author：van
 * CreateTime：2019/7/30
 * File description： 商家详情 评价
 */
public class DetailEvaluateFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {

    private static final int FIRST_REQUEST_DATA = 1001;
    private static final int REFRESH_LIST_DATA = 1003;
    private static final int REFRESH_TYPE_DATA = 1004;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.root)
    RelativeLayout root;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.multiple_status_view)
    MultipleStatusView mMultipleStatusView;
    @BindView(R.id.rb_all)
    RadioButton rbAll;
    @BindView(R.id.rb_news)
    RadioButton rbNews;
    @BindView(R.id.rb_image)
    RadioButton rbImage;
    @BindView(R.id.rb_good)
    RadioButton rbGood;
    @BindView(R.id.rb_bad)
    RadioButton rbBad;
    @BindView(R.id.frg)
    FlowRadioGroup frg;

    private int page = 1;
    private boolean isLoadMore = false;
    private List<DetailEvaluateBean.ResultBean.ListBean> dataList;
    private List<DetailEvaluateBean.ResultBean.ListBean> dataAllList = new ArrayList<>();
    private String storeId;
    private ShopEvaluateAdapter evaluateAdapter;
    private String evaluateType;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case FIRST_REQUEST_DATA:
                    requestListData(isLoadMore, page, storeId, evaluateType);
                    break;

                case REFRESH_LIST_DATA:
                    page = 1;
                    refreshListData(page, storeId, evaluateType);
                    break;
                case REFRESH_TYPE_DATA:
                    page = 1;
                    requestListData(false, page, storeId, evaluateType);
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_detail_evaluate;
    }

    @Override
    protected void loadData() {

        mMultipleStatusView.showLoading();
        Bundle arguments = getArguments();
        if (arguments == null) {
            mMultipleStatusView.showEmpty();
            return;
        }
        storeId = arguments.getString("store_id");
        Message message = new Message();
        message.what = FIRST_REQUEST_DATA;
        message.obj = storeId;
        mHandler.sendMessage(message);
    }

    @Override
    public void initView(View rootView) {

    }

    @Override
    public void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SimpleDividerItemDecortion(mContext));
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            isLoadMore = false;
            mHandler.sendEmptyMessage(REFRESH_LIST_DATA);
        });

        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            page++;
            isLoadMore = true;
            mHandler.sendEmptyMessage(FIRST_REQUEST_DATA);
        });

        mMultipleStatusView.setOnRetryClickListener(v -> mHandler.sendEmptyMessage(REFRESH_LIST_DATA));

        evaluateAdapter = new ShopEvaluateAdapter();
        recyclerView.setAdapter(evaluateAdapter);
        frg.setOnCheckedChangeListener(this);
    }

    private void requestListData(boolean isLoadMore, int page, String storeId, String evaluateType) {

        Map<String, String> params = new HashMap<>();
        params.put("store_id", storeId);
        params.put("page", String.valueOf(page));
        params.put("type", evaluateType);
        params.put("pageNum", "10");

        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.detailComment, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("评论：" + response.body());
                DetailEvaluateBean evaluateBean = FastJsonUtil.fromJson(response.body(), DetailEvaluateBean.class);
                if (null == evaluateBean) {
                    mMultipleStatusView.showError();
                    refreshLayout.finishLoadMore();
                    return;
                }
                if (!evaluateBean.getCode().equals(Constant.REQUEST_SUCCESS) || null == evaluateBean.getResult()) {
                    mMultipleStatusView.showEmpty();
                    refreshLayout.finishLoadMore();
                    return;
                }
                DetailEvaluateBean.ResultBean.CountBean countBean = evaluateBean.getResult().getCount();
                initRb(countBean);
                dataList = evaluateBean.getResult().getList();

                if (isLoadMore) {
                    if (null != dataList && dataList.size() > 0) {
                        dataAllList.addAll(dataList);
                        refreshLayout.finishLoadMore();
                    } else {
                        refreshLayout.setNoMoreData(true);
                        refreshLayout.autoLoadMoreAnimationOnly();
                    }
                    refreshAdapter(false);
                } else {
                    if (null == dataList || dataList.size() == 0) {
                        mMultipleStatusView.showEmpty();//.setOnClickListener(v -> loadData());
                        return;
                    }
                    mMultipleStatusView.showContent();
                    dataAllList.clear();
                    dataAllList.addAll(dataList);

                    refreshAdapter(true);
                }
            }

            @Override
            public void failed(Response<String> response) {
                mMultipleStatusView.showError();
                refreshLayout.finishLoadMore();
                XLog.e("" + "");
            }
        });
    }

    private void refreshAdapter(boolean isReset) {
        if (dataAllList == null) {
            return;
        }
        if (isReset) {
            evaluateAdapter.addData(dataAllList);
        } else {
            evaluateAdapter.setNewData(dataAllList);
        }
    }

    private void refreshListData(int page, String storeId, String evaluateType) {
        Map<String, String> params = new HashMap<>();
        params.put("store_id", storeId);
        params.put("page", String.valueOf(page));
        params.put("type", evaluateType);
        params.put("pageNum", "10");
        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.detailComment, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                    refreshLayout.finishRefresh();
                DetailEvaluateBean evaluateBean = FastJsonUtil.fromJson(response.body(), DetailEvaluateBean.class);
                if (null == evaluateBean) {
                    return;
                }
                if (!evaluateBean.getCode().equals(Constant.REQUEST_SUCCESS) || null == evaluateBean.getResult()) {
                    return;
                }

                dataList = evaluateBean.getResult().getList();
                if (null == dataList || dataList.size() == 0) {
                    return;
                }
                dataAllList.clear();
                dataAllList.addAll(dataList);
                refreshAdapter(true);

            }

            @Override
            public void failed(Response<String> response) {
                XToast.normal("刷新失败");
                refreshLayout.finishRefresh();
                XLog.e("" + "");
            }
        });
    }

    //评价类型
    @SuppressLint("SetTextI18n")
    private void initRb(DetailEvaluateBean.ResultBean.CountBean countBean) {
        int evaAll = countBean.getAll();
        int evaGood = countBean.getGood();
        int evaImages = countBean.getImages();
        int evaBad = countBean.getBad();

        rbAll.setText("全部 " + evaAll);
        rbBad.setText("差评 " + evaBad);
        rbImage.setText("有图 " + evaImages);
        rbGood.setText("好评 " + evaGood);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Message message = mHandler.obtainMessage();
        message.what = REFRESH_TYPE_DATA;
        mMultipleStatusView.showLoading();
        switch (checkedId) {

            case R.id.rb_all:
                message.obj = "all";
                break;
            case R.id.rb_news:
                message.obj = "new";
                break;
            case R.id.rb_good:
                message.obj = "good";
                break;
            case R.id.rb_bad:
                message.obj = "bad";
                break;
            case R.id.rb_image:
                message.obj = "image";
                break;
        }

        mHandler.sendMessage(message);
    }
}
