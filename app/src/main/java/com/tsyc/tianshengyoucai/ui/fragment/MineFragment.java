package com.tsyc.tianshengyoucai.ui.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.Eventbuss.MineInfomEvents;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.manager.DataMangerVo;
import com.tsyc.tianshengyoucai.model.adapter.ToolsAdapter;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.model.bean.OrderCountBean;
import com.tsyc.tianshengyoucai.model.bean.ShopApplyBean;
import com.tsyc.tianshengyoucai.model.bean.ToolsBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.requet.RequestInfomHttp;
import com.tsyc.tianshengyoucai.requet.RequestJobHttp;
import com.tsyc.tianshengyoucai.ui.activity.mine.AppSettingActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.BecomeShopActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.MyTeamActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.OnLineOrderActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.UnderLineOrderActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.UserInfoActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.MyCasherActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.MyShopActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseFragment;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.ui.recruit.RecruitInActivity;
import com.tsyc.tianshengyoucai.utils.DialogUtils;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.SaveUserInfomUtilJave;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.utils.Util;
import com.tsyc.tianshengyoucai.vo.RecruitInVo;
import com.tsyc.tianshengyoucai.vo.UserInfomVo;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.youth.banner.Banner;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

/**
 * author：cxd
 * CreateTime：2019/7/18
 * File description：我的
 */
@RuntimePermissions
public class MineFragment extends BaseFragment {
    /**
     * 扫面二维码
     */
    private final int REQUESTCODE = 1002;
    private static final int REQUEST_CODE_SCAN = 10004;// 扫面二维码
    private static final int CHECK_SHOP_STATUS = 10005; // 店铺状态
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_invite_code)
    TextView tvInviteCode;
    @BindView(R.id.tv_partner)
    TextView tvPartner;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.ctl_money)
    ConstraintLayout ctlMoney;
    @BindView(R.id.tv_gold)
    TextView tvGold;
    @BindView(R.id.ctl_gold)
    ConstraintLayout ctlGold;
    @BindView(R.id.tv_card)
    TextView tvCard;
    @BindView(R.id.ctl_card)
    ConstraintLayout ctlCard;
    @BindView(R.id.iv_purse)
    ImageView ivPurse;
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.ctl_purse)
    ConstraintLayout ctlPurse;
    @BindView(R.id.ctl_wait_pay)
    ConstraintLayout ctlWaitPay;
    @BindView(R.id.ctl_wait_send)
    ConstraintLayout ctlWaitSend;
    @BindView(R.id.ctl_wait_rec)
    ConstraintLayout ctlWaitRec;
    @BindView(R.id.ctl_wait_back)
    ConstraintLayout ctlWaitBack;
    @BindView(R.id.ctl_wait_order)
    ConstraintLayout ctlWaitOrder;
    @BindView(R.id.ctl_order)
    ConstraintLayout ctlOrder;
    @BindView(R.id.ctl_become_partner)
    ConstraintLayout ctlBecomePartner;
    @BindView(R.id.ctl_become_shop)
    ConstraintLayout ctlBecomeShop;
    @BindView(R.id.rv_tool)
    RecyclerView rvTool;
    @BindView(R.id.ctl_help)
    ConstraintLayout ctlHelp;
    @BindView(R.id.ctl_setting)
    ConstraintLayout ctlSetting;
    @BindView(R.id.ctl_set)
    ConstraintLayout ctlSet;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rl_mine_partner)
    RelativeLayout rlMinePartner;
    @BindView(R.id.iv_inner)
    ImageView ivInner;
    @BindView(R.id.tv_wait_pay_count)
    TextView tvWaitPayCount;
    @BindView(R.id.tv_wait_rec_count)
    TextView tvWaitRecCount;
    @BindView(R.id.tv_wait_send_count)
    TextView tvWaitSendCount;
    @BindView(R.id.tv_wait_back_count)
    TextView tvWaitBackCount;

    private Integer[] toolsImg = {R.mipmap.jft_but_offlineorder, R.mipmap.jft_but_personalstores,
            R.mipmap.jft_but_mycashier, R.mipmap.jft_but_myteam, R.mipmap.jft_but_personaldata,
            R.mipmap.jft_but_mycollection, R.mipmap.jft_but_receivingaddress, R.mipmap.jft_but_invitefriends};
    private List<ToolsBean> toolsBeans = new ArrayList<>();
    private UserInfomVo.ResultBean mResult;
    private AlertDialog mParenterDialog;
    private int checkIndex = -1;

    public static MineFragment getInstance() {
        return new MineFragment();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case REQUEST_CODE_SCAN:

                    break;

                case CHECK_SHOP_STATUS:
                    checkShopStatus();
                    break;
            }
        }
    };

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void loadData() {
        initTools();
        initEvent();
    }

    private void initEvent() {
        rlMinePartner.setOnClickListener(v -> {
//            reqeustRecuit();
        });
    }

    private void reqeustRecuit() {
        showProgress();
        RequestJobHttp.getSingleton(mActivity).requestTecruitIn(new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                NormalBean vos = GsonUtils.getGson(success, NormalBean.class);
                if (vos.getCode().equals("100")) {
                    T.showToast(mContext, vos.getMessage());
                    return;
                }
                RecruitInVo vo = GsonUtils.getGson(success, RecruitInVo.class);
                RecruitInVo.ResultBean bean = vo.getResult();
                startJobActivity(bean);

            }

            @Override
            public void error(String error) {
                toastErrorNet();
                dismissProgress();
            }
        });

    }

    private void startJobActivity(RecruitInVo.ResultBean vo) {
        switch (vo.getStatus()) {
            case 0://请选择身份
                mResultTo.startRecruitInHome(getActivity(), RecruitInActivity.LOGINTYPE, "");
                break;
            case 1:
                switch (vo.getStep()) {
                    case 1://完善求职信息-基础信息
                        mResultTo.startJobInfom(getActivity());
                        break;
                    case 2://完善求职信息-教育经历
                        mResultTo.startJobEdu(getActivity());
                        break;
                    case 3://完善求职信息-工作经历
                        mResultTo.startJobWork(getActivity());
                        break;
                    case 4://完善求职信息-求职意向
                        mResultTo.startJobPurpost(getActivity());
                        break;
                    case 5://完善求职信-个体评价
                        mResultTo.startEvaluate(getActivity());
                        break;
                }
                break;
            case 2://进入求职者中心
                mResultTo.startRecruitHome(getActivity());
                break;
            case 3:
                switch (vo.getStep()) {
                    case 1://完善招聘信息-基础信息
                        mResultTo.startBossInfom(getActivity());
                        break;
                    case 2://完善招聘信息-实名认证
                        mResultTo.startBossId(getActivity());
                        break;
                    case 3://完善招聘信息-发布职位
                        mResultTo.startBossJob(getActivity());
                        break;
                }
                break;
            case 4://进入boss
                mResultTo.startBossHome(mContext);
                break;

        }


    }

    private void initRequest() {
        UserInfomVo userInfomVo = SaveUserInfomUtilJave.getInstance().getUserInfom();
        if (userInfomVo == null || StringUtil.isEmpty(userInfomVo.getResult().getKey())) {
            requestUserInfom("");
        } else {
            bindViewData(userInfomVo, "0");
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void mineInfomRefresh(MineInfomEvents events) {
        String refresh = events.getRefresh();
        requestUserInfom(refresh);
    }

    /**
     * 防止重复加载头像
     */
    private String loadType = "";

    private void bindViewData(UserInfomVo userInfomVo, String refresh) {
        mResult = userInfomVo.getResult();
        int is_inner = mResult.getIs_inner();
        if (is_inner == 1) {
            ivInner.setVisibility(View.VISIBLE);
        } else {
            ivInner.setVisibility(View.GONE);
        }
        tvName.setText(mResult.getUser_name());
        tvInviteCode.setText("邀请码：" + mResult.getInvite_code());
        if (refresh.equals("0"))//第一次加载
        {
            if (!loadType.equals(refresh)) {
                GlideUtil.getSingleton().loadCilcleImager(mContext, ivHeader, mResult.getAvatar());
            }
            loadType = refresh;
        }
        if (refresh.equals("1"))//更新头像
            GlideUtil.getSingleton().loadCilcleImager(mContext, ivHeader, mResult.getAvatar());
        tvMoney.setText(mResult.getMy_balance() + "");
        tvGold.setText(mResult.getMember_points() + "");
        tvCard.setText(mResult.getMyvoucher() + "");
        if (mResult.getGrade_status() != 0) {
            rlMinePartner.setBackgroundResource(R.mipmap.jft_icon_partner);
        } else {
            rlMinePartner.setBackgroundResource(R.mipmap.jft_icon_ordinaryusers);
        }
    }

    private void requestUserInfom(String refresh) {
        String mToken = DataMangerVo.key;
        RequestInfomHttp.getSingleton(getActivity()).requestUserInfom(mToken, new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                UserInfomVo data = GsonUtils.getGson(success, UserInfomVo.class);
                if (data.getCode().equals("100")) {
                    T.showToast(mContext, data.getMessage());
                } else {
                    UserInfomVo.ResultBean result = data.getResult();
                    result.setKey(mToken);
                    data.setResult(result);
                    SaveUserInfomUtilJave.getInstance().delectUserInfom();
                    SaveUserInfomUtilJave.getInstance().putUserInfom(data);
                    bindViewData(data, refresh);
                }
            }

            @Override
            public void error(String error) {
                dismissProgress();
                toastErrorNet();
            }
        });
    }

    @Override
    public void initView(View rootView) {
    }

    // 初始化 常用工具
    private void initTools() {
        View toolsHeader = LayoutInflater.from(mContext).inflate(R.layout.layout_tools_header, (ViewGroup) rvTool.getParent(), false);
        rvTool.setHasFixedSize(true);
        rvTool.setLayoutManager(new GridLayoutManager(mContext, 4) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        String[] toolsName = mContext.getResources().getStringArray(R.array.tools_name);
        for (int i = 0; i < toolsImg.length; i++) {
            ToolsBean toolsBean = new ToolsBean();
            toolsBean.setToolImg(toolsImg[i]);
            toolsBean.setToolName(toolsName[i]);
            toolsBeans.add(toolsBean);
        }

        ToolsAdapter toolsAdapter = new ToolsAdapter(toolsBeans);
        rvTool.setAdapter(toolsAdapter);
        toolsAdapter.addHeaderView(toolsHeader);
        toolsAdapter.setOnItemClickListener((adapter, view, position) -> {
            toolsItemClickListener(position);
        });

    }

    //扫描二维码
    private void scan() {
        Intent intent = new Intent(mActivity, CaptureActivity.class);
        mActivity.overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SCAN && data != null) {
            Bundle bundle = data.getExtras();
            if (bundle == null) {
                return;
            }
            if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                String result = bundle.getString(CodeUtils.RESULT_STRING);

                XLog.e("Fragment " + result);
                // 无效码  错误码

            } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                XToast.normal("没扫描粗来，尴尬😅 - -。");
            }
        }
    }

    @OnClick({R.id.iv_header, R.id.iv_scan, R.id.tv_partner, R.id.ctl_money, R.id.ctl_gold, R.id.ctl_card, R.id.ctl_purse,
            R.id.ctl_wait_pay, R.id.ctl_wait_send, R.id.ctl_wait_rec, R.id.ctl_wait_back, R.id.ctl_wait_order,
            R.id.ctl_become_partner, R.id.ctl_become_shop, R.id.ctl_help, R.id.ctl_setting})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();

        switch (view.getId()) {
            case R.id.iv_header:
                openActivity(UserInfoActivity.class);
                break;
            case R.id.iv_scan:
                MineFragmentPermissionsDispatcher.showCameraWithPermissionCheck(this);
                break;
            case R.id.tv_partner:
                break;
            case R.id.ctl_money://我的余额
                mResultTo.startMoney(mContext);
                break;
            case R.id.ctl_gold:
                mResultTo.startConis(mContext, StringUtil.getObjectToStr(tvGold));
                break;
            case R.id.ctl_card:
                mResultTo.startRedPacker(mContext);
                break;
            case R.id.ctl_purse:
                break;

            case R.id.ctl_wait_send: // 代发货

                bundle.putString("pageIndex", "2");
                openActivity(OnLineOrderActivity.class, bundle);
                break;
            case R.id.ctl_wait_rec: // 待收货
                bundle.putString("pageIndex", "3");
                openActivity(OnLineOrderActivity.class, bundle);
                break;
            case R.id.ctl_wait_back: // 退还货
                bundle.putString("pageIndex", "5");
                openActivity(OnLineOrderActivity.class, bundle);
                break;
            case R.id.ctl_wait_pay: // 待付款
            case R.id.ctl_wait_order: //线上订单
                openActivity(OnLineOrderActivity.class);
                break;
            case R.id.ctl_become_partner://合伙人
                if (mResult != null) {
                    if (mResult.getGrade_status() == 0) {
                        showPareter();
                    } else {
                        T.showToast(mContext, "您已经是合伙人");
                    }
                }

                break;
            case R.id.ctl_become_shop:
                mHandler.sendEmptyMessage(CHECK_SHOP_STATUS);

                break;
            case R.id.ctl_help://帮助中心
                mResultTo.startHelp(mContext);
                break;
            case R.id.ctl_setting: // 设置
                openActivity(AppSettingActivity.class);
                break;
        }
    }

    /**
     * 合伙人
     */
    private void showPareter() {
        mParenterDialog = DialogUtils.getSingleton().showPartnerAlerDialog(mContext, new DialogUtils.OnPartnerClickListener() {
            @Override
            public void partnerClick() {
                mResultTo.startPartner(mContext);
            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();
        Util.dismissDialog(mParenterDialog);
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().removeAllStickyEvents();
    }

    private void toolsItemClickListener(int position) {
        switch (position) {

            case 0: // 线下订单
//                XToast.normal("功能正在完善...");
                openActivity(UnderLineOrderActivity.class);
                break;

            case 1: //我的店铺
                checkIndex = 1;
                mHandler.sendEmptyMessage(CHECK_SHOP_STATUS);
                break;

            case 2: // 我的收银台
                checkIndex = 2;
                mHandler.sendEmptyMessage(CHECK_SHOP_STATUS);

                break;

            case 3: // 我的团队
                openActivity(MyTeamActivity.class);
                break;

            case 4: // 个人资料
                openActivity(UserInfoActivity.class);
                break;

            case 5: //我的收藏
                mResultTo.startCollect(mContext);

                break;

            case 6: //收货地址
                mResultTo.startMyAddress(mContext);
                break;

            case 7: //邀请好友
                mResultTo.startInvite(mContext);
                break;

            default:
                break;
        }
    }

    //检测商铺开通状态
    private void checkShopStatus() {
        loading(true);
        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.shopInfo, null, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {

                XLog.e("店铺开通状态 " + response.body());
                dismiss();
                //未开通  code 100   message 您还没有申请店铺！
                //       "store_state":2,                //类型：Number  必有字段  备注：店铺状态:0关闭，1开启，2审核中

                ShopApplyBean shopApplyBean = FastJsonUtil.fromJson(response.body(), ShopApplyBean.class);
                if (null == shopApplyBean) {
                    XToast.normal(getString(R.string.service_error));
                    return;
                }

                if (shopApplyBean.getCode().equals("100")) {
                    if (shopApplyBean.getMessage().contains("您还没有申请店铺")) {
                        openActivity(BecomeShopActivity.class);
                    } else {
                        XToast.normal(String.valueOf(shopApplyBean.getMessage()));
                        return;
                    }
                }

                if (shopApplyBean.getResult() == null) {
                    return;
                }
                int store_state = shopApplyBean.getResult().getStore_state();
                if (store_state == 0) {
                    XToast.normal("店铺已关闭");
                } else if (store_state == 1) {
                    if (checkIndex == 2) {
                        openActivity(MyCasherActivity.class);
                    } else {
                        openActivity(MyShopActivity.class);
                    }
                } else if (store_state == 2) {
                    XToast.normal("店铺正在审核中");
                } else {
                    XToast.normal(getString(R.string.service_error));
                }

            }

            @Override
            public void failed(Response<String> response) {
                dismiss();
                XToast.normal(getString(R.string.service_error));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        checkOrderCount();
        requestUserInfom("");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            initRequest();
        } else {

        }
    }

    //订单数
    private void checkOrderCount() {

        Map<String, String> params = new HashMap<>();
        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.order_count, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("订单数" + response.body());
                OrderCountBean orderCountBean = FastJsonUtil.fromJson(response.body(), OrderCountBean.class);
                if (orderCountBean == null)
                    return;
                if (!orderCountBean.getCode().equals(Constant.REQUEST_SUCCESS) || orderCountBean.getResult() == null) {
                    return;
                }

                OrderCountBean.ResultBean result = orderCountBean.getResult();
                int receive = result.getReceive();
                int refund = result.getRefund();
                int send = result.getSend();
                int unpay = result.getUnpay();

                if (unpay > 0) {
                    tvWaitPayCount.setText(String.valueOf(unpay));
                    tvWaitPayCount.setVisibility(View.VISIBLE);
                } else {
                    tvWaitPayCount.setVisibility(View.GONE);
                }

                if (send > 0) {
                    tvWaitSendCount.setText(String.valueOf(send));
                    tvWaitSendCount.setVisibility(View.VISIBLE);
                } else {
                    tvWaitSendCount.setVisibility(View.GONE);
                }

                if (refund > 0) {
                    tvWaitBackCount.setText(String.valueOf(refund));
                    tvWaitBackCount.setVisibility(View.VISIBLE);
                } else {
                    tvWaitBackCount.setVisibility(View.GONE);
                }

                if (receive > 0) {
                    tvWaitRecCount.setText(String.valueOf(receive));
                    tvWaitRecCount.setVisibility(View.VISIBLE);
                } else {
                    tvWaitRecCount.setVisibility(View.GONE);
                }
            }

            @Override
            public void failed(Response<String> response) {
//                XLog.e("订单数 失败" + response.getException().getMessage());

            }
        });

    }

    //拒绝
    @OnPermissionDenied({Manifest.permission.CAMERA})
    void showDeniedForCamera() {
        XToast.normal("权限拒绝");
    }

    // 不再询问
    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void showNeverAskForCamera() {
        XToast.normal("权限拒绝，不再询问");
    }


    @NeedsPermission(Manifest.permission.CAMERA)
    void showCamera() {
        mResultTo.startlib(getActivity(), REQUESTCODE);
    }

}
