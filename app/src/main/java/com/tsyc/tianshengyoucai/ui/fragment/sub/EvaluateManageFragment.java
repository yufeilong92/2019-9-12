package com.tsyc.tianshengyoucai.ui.fragment.sub;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.listener.MyTextWatcherListener;
import com.tsyc.tianshengyoucai.model.adapter.EvaluateManageAdapter;
import com.tsyc.tianshengyoucai.model.bean.EvaluateManageBean;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.base.BaseFragment;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.view.pop.EvaluateReplyPop;
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
 * File description： 评论管理Fragment
 */
public class EvaluateManageFragment extends BaseFragment {


    private static final int FIRST_REQUEST_DATA = 1001;
    private static final int REFRESH_LIST_DATA = 1003;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;


    private int page = 1;
    private boolean isLoadMore = false;
    private List<EvaluateManageBean.ResultBean> dataList;
    private List<EvaluateManageBean.ResultBean> dataAllList = new ArrayList<>();
    private String itemPosition;
    private EvaluateManageAdapter shopManageAdapter;
    private View footer;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case FIRST_REQUEST_DATA:
                    requestListData(isLoadMore, page, itemPosition);
                    break;

                case REFRESH_LIST_DATA:
                    page = 1;
                    refreshListData(page, itemPosition);
                    break;

                default:
                    break;
            }
        }
    };
    private EvaluateReplyPop replyPopup;


    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_manage;
    }

    @Override
    protected void loadData() {

        mStateView.showLoading();
        Bundle arguments = getArguments();
        if (arguments == null) {
            mStateView.showRetry();
            return;
        }
        String title = arguments.getString("title");
        itemPosition = arguments.getString("position");

        XLog.e("ssss " + itemPosition);
        Message message = new Message();
        message.what = FIRST_REQUEST_DATA;
        message.obj = itemPosition;
        mHandler.sendMessage(message);
    }


    @Override
    public void initData() {

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);

        refreshLayout.setOnRefreshListener(refreshLayout -> {
            isLoadMore = false;
            mHandler.sendEmptyMessage(REFRESH_LIST_DATA);
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            page++;
            isLoadMore = true;
            mHandler.sendEmptyMessage(FIRST_REQUEST_DATA);
        });
        footer = LayoutInflater.from(mContext).inflate(R.layout.layout_coupon_footer, (ViewGroup) recyclerView.getParent(), false);

        mStateView.setOnRetryClickListener(this::loadData);

    }

    private void requestListData(boolean isLoadMore, int page, String itemPosition) {

        Map<String, String> params = new HashMap<>();
        params.put("star", String.valueOf(itemPosition)); //1 --- 5
        params.put("page", String.valueOf(page));

        XLog.e("请求数据  " + itemPosition);

        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.evaluateManage, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("评价数据  " + response.body());
                EvaluateManageBean evaluateManageBean = FastJsonUtil.fromJson(response.body(), EvaluateManageBean.class);
                if (null == evaluateManageBean) {
                    mStateView.showRetry();
                    refreshLayout.finishLoadMore();
                    return;
                }
                if (!evaluateManageBean.getCode().equals(Constant.REQUEST_SUCCESS) || null == evaluateManageBean.getResult()) {
                    mStateView.showRetry();
                    refreshLayout.finishLoadMore();
                    return;
                }

                dataList = evaluateManageBean.getResult();
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
                        mStateView.showEmpty().setOnClickListener(v -> loadData());
                        return;
                    }
                    mStateView.showContent();
                    dataAllList.clear();
                    dataAllList.addAll(dataList);

                    refreshAdapter(true);
                }
            }

            @Override
            public void failed(Response<String> response) {
                mStateView.showRetry();
                refreshLayout.finishLoadMore();

            }
        });
    }

    private void refreshAdapter(boolean isReset) {
        if (dataAllList == null) {
            return;
        }
        if (isReset) {

            shopManageAdapter = new EvaluateManageAdapter(dataAllList);
            recyclerView.setAdapter(shopManageAdapter);
            shopManageAdapter.setOnItemChildClickListener((adapter, view, position) -> childItemClickListener(adapter, view, position));
            shopManageAdapter.setListener(new EvaluateManageAdapter.OnItemClickListener() {
                @Override
                public void itemClick(int adapterPosition, List<String> eval_image) {
                    mResultTo.startImager(mContext,adapterPosition,eval_image);
                }
            });
        } else {
            shopManageAdapter.notifyDataSetChanged();
        }
    }

    private void refreshListData(int page, String itemPosition) {
        Map<String, String> params = new HashMap<>();
        params.put("star", String.valueOf(itemPosition));
        params.put("page", String.valueOf(page));
        XLog.e("刷新数据  " + itemPosition);
        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.evaluateManage, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                    refreshLayout.finishRefresh();
                EvaluateManageBean evaluateManageBean = FastJsonUtil.fromJson(response.body(), EvaluateManageBean.class);
                if (null == evaluateManageBean) {
                    return;
                }
                if (!evaluateManageBean.getCode().equals(Constant.REQUEST_SUCCESS) || null == evaluateManageBean.getResult()) {
                    return;
                }

                dataList = evaluateManageBean.getResult();
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
             //   XLog.e("" + response.getException().getMessage());
            }
        });
    }

    // 回复
    private void childItemClickListener(BaseQuickAdapter adapter, View view, int position) {

        replyPopup = new EvaluateReplyPop(mContext);

        replyPopup.showPopupWindow();

        TextView popTitle = replyPopup.findViewById(R.id.tv_title);
        View viewTop = replyPopup.findViewById(R.id.top);
        RelativeLayout rl_back = replyPopup.findViewById(R.id.rl_back);
        EditText etContent = replyPopup.findViewById(R.id.et_content);
        TextView tvContentLength = replyPopup.findViewById(R.id.tv_content_length);
        Button btnCommit = replyPopup.findViewById(R.id.btn_commit);
        viewTop.setVisibility(View.VISIBLE);
        tvContentLength.setText("0/200");
        popTitle.setText(getString(R.string.text_evaluate_reply));
        etContent.setHint("请输入回复内容");
        etContent.addTextChangedListener(new MyTextWatcherListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvContentLength.setText(s.length() + "/200");
            }
        });
        rl_back.setOnClickListener(v -> replyPopup.dismiss());
        btnCommit.setOnClickListener(v -> {
            String content = etContent.getText().toString().trim();
            if (TextUtils.isEmpty(content)) {
                XToast.normal("请输入回复内容");
                return;
            }
            int eval_id = dataAllList.get(position).getGeval_id();
            commitReply(String.valueOf(eval_id), content, replyPopup);
        });

    }

    private void commitReply(String id, String content, EvaluateReplyPop replyPopup) {
        Map<String, String> params = new HashMap<>();
        params.put("geval_id", id);
        params.put("content", content);
        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.evaluateReply, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                NormalBean normalBean = FastJsonUtil.fromJson(response.body(), NormalBean.class);
                if (null == normalBean) {
                    XToast.normal("回复评价失败");
                    return;
                }

                if (!normalBean.getCode().equals("200")) {
                    XToast.normal(String.valueOf(normalBean.getMessage()));
                    return;
                }
                XToast.normal(String.valueOf(normalBean.getMessage()));
                replyPopup.dismiss();
                mHandler.sendEmptyMessage(REFRESH_LIST_DATA);
            }

            @Override
            public void failed(Response<String> response) {
                XToast.normal("评价失败");
            }
        });
    }

}
