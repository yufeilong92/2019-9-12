package com.tsyc.tianshengyoucai.ui.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.home.HomeAdapter;
import com.tsyc.tianshengyoucai.flyn.Eyes;
import com.tsyc.tianshengyoucai.model.bean.HomeListBean;
import com.tsyc.tianshengyoucai.model.bean.HomeNoticeBean;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.model.bean.ShopApplyBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.requet.RequestJobHttp;
import com.tsyc.tianshengyoucai.ui.activity.home.GoodsDetailActivity;
import com.tsyc.tianshengyoucai.ui.activity.home.MapRedBagActivity;
import com.tsyc.tianshengyoucai.ui.activity.home.NoticeDetailActivity;
import com.tsyc.tianshengyoucai.ui.activity.home.SaleGoodActivity;
import com.tsyc.tianshengyoucai.ui.activity.home.ShopDetailActivity;
import com.tsyc.tianshengyoucai.ui.activity.home.ShopNewsActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.MyShopActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.ReleaseShopNewActivity;
import com.tsyc.tianshengyoucai.ui.activity.type.HomeTypeActivity;
import com.tsyc.tianshengyoucai.ui.activity.type.TypeListActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseFragment;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.ui.recruit.RecruitInActivity;
import com.tsyc.tianshengyoucai.utils.DialogUtils;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.RecruitInVo;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import java.util.List;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class HomeNewFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    /**
     * 请验证码
     */
    private final int REQUESTCODE = 1002;
    private String mParam1;
    private String mParam2;
    private RecyclerView mRlvHomeContent;
    private SmartRefreshLayout mSmlHomeLayout;
    private HomeAdapter mHomeAdapter;

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_home_new;
    }

    @Override
    protected void loadData() {

    }

    public static HomeNewFragment newInstance(String param1, String param2) {
        HomeNewFragment fragment = new HomeNewFragment();
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

    public void initView(View inflate) {
        mRlvHomeContent = (RecyclerView) inflate.findViewById(R.id.rlv_home_content);
        mSmlHomeLayout = (SmartRefreshLayout) inflate.findViewById(R.id.sml_home_layout);
    }

    @Override
    public void initData() {
        Eyes.translucentStatusBar(getActivity(), false);
        initRequest();
        initRefresh();
    }

    private void initRefresh() {
        mSmlHomeLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initRequest();

            }
        });

    }

    private void initNoticeRequest() {
        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.homeNote, null, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                HomeNoticeBean homeNoticeBean = FastJsonUtil.fromJson(response.body(), HomeNoticeBean.class);
                if (null == homeNoticeBean || !homeNoticeBean.getCode().equals("200")) {
                    return;
                }

                List<HomeNoticeBean.ResultBean> result = homeNoticeBean.getResult();
                if (mHomeAdapter != null) {
                    mHomeAdapter.setNoticeData(result);
                }

            }

            @Override
            public void failed(Response<String> response) {

            }
        });

    }

    private void initRequest() {
        BaseRequestUtils.postRequest(mActivity, UrlManager.index, null, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                mSmlHomeLayout.finishRefresh();
                HomeListBean homeListBean = FastJsonUtil.fromJson(response.body(), HomeListBean.class);
                if (homeListBean == null || !homeListBean.getCode().equals("200")) {
                    return;
                }
                HomeListBean.ResultBean result = homeListBean.getResult();
                initAdapter(result);
            }

            @Override
            public void failed(Response<String> response) {
                T.showToast(mContext, "网络请求失败，请刷新数据");
                mSmlHomeLayout.finishRefresh();
            }
        });

    }

    private void initAdapter(HomeListBean.ResultBean data) {
        GridLayoutManager manager = new GridLayoutManager(mContext, 1);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        mRlvHomeContent.setLayoutManager(manager);
        mHomeAdapter = new HomeAdapter(mContext, data);
        mRlvHomeContent.setAdapter(mHomeAdapter);
        initNoticeRequest();
        mHomeAdapter.setItemListener(new HomeAdapter.OnItemClickListener() {
            //二维码
            @Override
            public void qrCodeClick() {
                HomeNewFragmentPermissionsDispatcher.showCameraWithPermissionCheck(HomeNewFragment.this);
            }

            /**
             * 搜索
             */
            @Override
            public void searchClick() {
                mResultTo.startHomeSearch(getActivity());
            }

            /**
             * banner
             * 0 无链接 1 链接到商品 2链接到店铺
             * @param bannerBean
             */
            @Override
            public void bannerClick(HomeListBean.ResultBean.BannerBean bannerBean) {


                Bundle bundle = new Bundle();
                int link_type = bannerBean.getLink_type();
                int link_id = bannerBean.getLink_id();
                if (link_type == 1) {
                    bundle.putString("goods_id", link_id + "");
                    openActivity(GoodsDetailActivity.class, bundle);
                } else if (link_type == 2) {
                    bundle.putString("store_id", link_id + "");
                    openActivity(ShopDetailActivity.class, bundle);
                }
            }

            /**
             * 热点
             * @param vo
             */
            @Override
            public void hostClick(HomeNoticeBean.ResultBean vo) {
                int id = vo.getId();
                Bundle b = new Bundle();
                b.putString("id", id + "");
                openActivity(NoticeDetailActivity.class, b);
            }

            /**
             * 商品点击
             * @param type 1 为招聘，2服务，3咨询，4 红包/分类 5商家入驻 ，6红包福利
             */
            @Override
            public void merchantRecruitClick(int type) {
                switch (type) {
                    case 1:
                        //  XToast.normal("正在开发中...");
                        openActivity(SaleGoodActivity.class);
//                        reqeustRecuit();
                        break;
                    case 2:
                        mResultTo.startShopService(getActivity());
                        break;
                    case 3:
                        XToast.normal("正在开发中...");
                        //openActivity(ShopNewsActivity.class);
                        break;
                    case 4:
                        openActivity(HomeTypeActivity.class);
                        break;
                    case 5:
                        requestShop(1);
                        break;
                    case 6:
                        requestShop(2);
                        break;

                    default:
                        break;
                }

            }

            /**
             * 商品
             * * 1 左侧 ，2右侧上面，3 右侧下面
             * @param type
             */
            @Override
            public void commodityClick(HomeListBean.ResultBean.RecommendBean type) {
                int goods_id = type.getGoods_id();
                Bundle bundle = new Bundle();
                bundle.putString("goods_id", goods_id+"");
               // bundle.putString("goods_id", "711");
                openActivity(GoodsDetailActivity.class, bundle);
            }

            /**
             * 更多点击
             * 1 附近商家 2 推荐职位
             * @param type
             */
            @Override
            public void moreClick(int type) {
                switch (type) {
                    case 1:
                        mResultTo.startMoreShop(getActivity());
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }

            }

            /**
             *商家item 点击事件
             * @param postion
             * @param vo
             */
            @Override
            public void merchantItemClicl(int postion, HomeListBean.ResultBean.StoreBean vo) {
                int store_id = vo.getStore_id();
                Bundle bundle = new Bundle();
                bundle.putString("store_id", String.valueOf(store_id));
                openActivity(ShopDetailActivity.class, bundle);
            }

            /***
             *  职位item点击事件
             * @param postion
             * @param vo
             */
            @Override
            public void jobItemClick(int postion, HomeListBean.ResultBean.PositionBean vo) {

            }

            /**
             *  * 精品点击事件
             * @param postion
             * @param vo
             */
            @Override
            public void goodItemClick(int postion, HomeListBean.ResultBean.GoodsBean vo) {
                int goods_id = vo.getGoods_id();
                Bundle bundle = new Bundle();
                bundle.putString("goods_id", String.valueOf(goods_id));
                openActivity(GoodsDetailActivity.class, bundle);
            }
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


    private void requestShop(int type) {
        loading(true);
        BaseRequestUtils.postRequestWithHeader(getActivity(), UrlManager.shopInfo, null, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("店铺开通状态 " + response.body());
                dismiss();
                ShopApplyBean shopApplyBean = FastJsonUtil.fromJson(response.body(), ShopApplyBean.class);
                if (null == shopApplyBean) {
                    XToast.normal(getString(R.string.service_error));
                    return;
                }

                if (shopApplyBean.getCode().equals("100") || shopApplyBean.getResult() == null) {
                    if (shopApplyBean.getMessage().contains("您还没有申请店铺")) {
                        if (type == 1)
                            mResultTo.checkShopStatus(getActivity());
                        else
                            showTipPop();
                    } else {
                        XToast.normal(getString(R.string.service_error));
                        return;
                    }
                    return;
                }

                int store_state = shopApplyBean.getResult().getStore_state();
                if (store_state == 0) {
                    XToast.normal("店铺已关闭");
                } else if (store_state == 1) {
                    if (type == 1)
                        openActivity(MyShopActivity.class);
                    else
                        openActivity(ReleaseShopNewActivity.class);

                } else if (store_state == 2) {
                    XToast.normal("店铺正在审核中");
                } else {
                    XToast.normal(getString(R.string.service_error));
                }
            }

            @Override
            public void failed(Response<String> response) {
                XToast.normal(getString(R.string.service_error));
                dismiss();
            }
        });
    }

    private void showTipPop() {
        DialogUtils.getSingleton().showSureAlerDialog(mContext, "您还未开通店铺，请先去申请店铺！", "", "明白了",
                "再想想", new DialogUtils.OnDialogSuceClickListener() {
                    @Override
                    public void sureClick() {
                        //mResultTo.checkShopStatus(getActivity());
                    }

                    @Override
                    public void cannerClick() {
                    }
                });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //HomeNewFragmentPermissionsDispatcher.showCameraWithPermissionCheck(HomeNewFragment.this);
        HomeNewFragmentPermissionsDispatcher.onRequestPermissionsResult(HomeNewFragment.this, requestCode, grantResults);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
