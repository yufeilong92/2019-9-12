package com.tsyc.tianshengyoucai.ui.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.msg.HomeMsgAdapter;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.requet.RequestSettingHttp;
import com.tsyc.tianshengyoucai.ui.base.BaseFragment;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.DialogUtils;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.HomeDataBeanVo;
import com.tsyc.tianshengyoucai.vo.HomeMsgVo;
import com.tsyc.tianshengyoucai.vo.MsgNumberVo;

import java.util.ArrayList;
import java.util.List;

/**
 * author：cxd
 * CreateTime：2019/7/18
 * File description：消息
 */
public class MsgFragment extends BaseFragment {


    private TextView mGmTvTitle;
    private RecyclerView mRlvMsgContent;
    private SmartRefreshLayout mSrlContent;
    private ArrayList mArray;
    private HomeMsgAdapter mAdapter;
    private TextView mTvHomeMsgInvite;
    private TextView mTvHomeMsgSetting;
    private TextView mTvHomeMsgOrder;
    private TextView mTvHomeMsgServer;
    private String mParamType = "1";
    private ImageView mIvEmpty;
    private TextView mTvHomeMsgInviteNumber;
    private TextView mTvHomeMsgSettingNumber;
    private TextView mTvHomeMsgOrderNumber;
    private TextView mTvHomeMsgServerNumber;

    public static MsgFragment getInstance() {
        return new MsgFragment();
    }

//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_msg, container, false);
//        initView(view);
//        return view;
//    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_msg;
    }

    @Override
    protected void loadData() {
        mGmTvTitle.setText("消息中心");
        clearData();
        initEvent();
        initRefresh();
        initAdapter();
        mSrlContent.autoRefresh();
    }

    private void initRequestNubmer() {
        RequestSettingHttp.getSingleton(getActivity()).requestMsgNumber(new RequestResultCallback() {
            @Override
            public void success(String success) {
                MsgNumberVo vo = GsonUtils.getGson(success, MsgNumberVo.class);
                if (vo.getCode().equals("100")) {
                    T.showToast(mContext, vo.getMessage());
                    return;
                }
                bindViewData(vo.getResult());

            }

            @Override
            public void error(String error) {
            }
        });
    }

    private void bindViewData(MsgNumberVo.ResultBean result) {
      mTvHomeMsgInviteNumber.setText(result.getRecruit_msg()+"");
      mTvHomeMsgOrderNumber.setText(result.getOrder_msg()+"");
      mTvHomeMsgSettingNumber.setText(result.getSystem_msg()+"");

    }

    private void initRequest() {
        RequestSettingHttp.getSingleton(mActivity).requestMsgList(mParamType, new RequestResultCallback() {
            @Override
            public void success(String success) {
                mSrlContent.finishRefresh();
                initRequestNubmer();
                HomeMsgVo vo = GsonUtils.getGson(success, HomeMsgVo.class);
                if (vo.getCode().equals("100")) {
                    T.showToast(mContext, vo.getMessage());
                    return;
                }
                HomeMsgVo.ResultBean data = vo.getResult();
                clearData();
                List<HomeDataBeanVo> list = data.getData();
                if (list == null || list.isEmpty()) {
                    mRlvMsgContent.setVisibility(View.GONE);
                    mIvEmpty.setVisibility(View.VISIBLE);
                    return;
                }
                mRlvMsgContent.setVisibility(View.VISIBLE);
                mIvEmpty.setVisibility(View.GONE);
                addData(list);
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void error(String error) {
                mAdapter.notifyDataSetChanged();
                mSrlContent.finishRefresh();
            }
        });
    }

    private void initRefresh() {
        mSrlContent.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initRequest();
            }
        });

    }

    private void initAdapter() {
        RecyclerUtil.setGridManage(mActivity, mRlvMsgContent);
        mAdapter = new HomeMsgAdapter(mContext, mArray);
        mRlvMsgContent.setAdapter(mAdapter);
        mAdapter.setListener(new HomeMsgAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, HomeDataBeanVo vo) {

            }

            @Override
            public void itemDeleteClick(int postion, HomeDataBeanVo vo) {
                showDelete(postion, vo);
            }
        });
    }

    private void showDelete(int postion, HomeDataBeanVo vo) {
        DialogUtils.getSingleton().showSureAlerDialog(mContext, "是否确认删除", "",
                "确认", "取消", new DialogUtils.OnDialogSuceClickListener() {
                    @Override
                    public void sureClick() {
                        submitDeleteMsg(postion, vo);
                    }

                    @Override
                    public void cannerClick() {

                    }
                });
    }

    private void submitDeleteMsg(int postion, HomeDataBeanVo vo) {
        showProgress();
        RequestSettingHttp.getSingleton(getActivity()).submitDeleteMsg(String.valueOf(vo.getId()),
                new RequestResultCallback() {
                    @Override
                    public void success(String success) {
                        dismissProgress();
                        NormalBean vo = GsonUtils.getGson(success, NormalBean.class);
                        if (vo.getCode().equals("100")) {
                            T.showToast(mContext, vo.getMessage());
                            return;
                        }
                        mArray.remove(postion);
                        mAdapter.notifyItemRemoved(postion);
                        T.showToast(mContext, "删除成功");
                    }

                    @Override
                    public void error(String error) {
                        dismissProgress();
                        toastErrorNet();
                    }
                });

    }

    private void initEvent() {
    }

    @Override
    public void initView(View view) {

        mGmTvTitle = (TextView) view.findViewById(R.id.gm_tv_title);
        mGmTvTitle.setOnClickListener(this);
        mRlvMsgContent = (RecyclerView) view.findViewById(R.id.rlv_msg_content);
        mRlvMsgContent.setOnClickListener(this);
        mSrlContent = (SmartRefreshLayout) view.findViewById(R.id.srl_content);
        mSrlContent.setOnClickListener(this);
        mTvHomeMsgInvite = (TextView) view.findViewById(R.id.tv_home_msg_invite);
        mTvHomeMsgInvite.setOnClickListener(this);
        mTvHomeMsgSetting = (TextView) view.findViewById(R.id.tv_home_msg_setting);
        mTvHomeMsgSetting.setOnClickListener(this);
        mTvHomeMsgOrder = (TextView) view.findViewById(R.id.tv_home_msg_order);
        mTvHomeMsgOrder.setOnClickListener(this);
        mTvHomeMsgServer = (TextView) view.findViewById(R.id.tv_home_msg_server);
        mTvHomeMsgServer.setOnClickListener(this);
        mIvEmpty = (ImageView) view.findViewById(R.id.iv_empty);
        mIvEmpty.setOnClickListener(this);
        mTvHomeMsgInviteNumber = (TextView) view.findViewById(R.id.tv_home_msg_invite_number);
        mTvHomeMsgInviteNumber.setOnClickListener(this);
        mTvHomeMsgSettingNumber = (TextView) view.findViewById(R.id.tv_home_msg_setting_number);
        mTvHomeMsgSettingNumber.setOnClickListener(this);
        mTvHomeMsgOrderNumber = (TextView) view.findViewById(R.id.tv_home_msg_order_number);
        mTvHomeMsgOrderNumber.setOnClickListener(this);
        mTvHomeMsgServerNumber = (TextView) view.findViewById(R.id.tv_home_msg_server_number);
        mTvHomeMsgServerNumber.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_home_msg_invite://招聘
                mParamType = "3";
                mAdapter.showDelete(true);
                mSrlContent.autoRefresh();
                break;
            case R.id.tv_home_msg_setting://系统设置
                mParamType = "1";
                mAdapter.showDelete(false);
                mSrlContent.autoRefresh();
                break;
            case R.id.tv_home_msg_order://订单通知
                mParamType = "2";
                mAdapter.showDelete(true);
                mSrlContent.autoRefresh();
                break;
            case R.id.tv_home_msg_server://我的客服

                mResultTo.startChat(getActivity());
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

}
