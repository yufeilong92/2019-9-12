package com.tsyc.tianshengyoucai.ui.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.classic.common.MultipleStatusView;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.flyn.Eyes;
import com.tsyc.tianshengyoucai.listener.ItemClickListener;
import com.tsyc.tianshengyoucai.model.adapter.HomeCategoryAdapter;
import com.tsyc.tianshengyoucai.model.adapter.HomeListAdapter;
import com.tsyc.tianshengyoucai.model.adapter.HomePosAdapter;
import com.tsyc.tianshengyoucai.model.adapter.HomeShopAdapter;
import com.tsyc.tianshengyoucai.model.bean.HomeListBean;
import com.tsyc.tianshengyoucai.model.bean.HomeNoticeBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.SecondActivity;
import com.tsyc.tianshengyoucai.ui.activity.home.GoodsDetailActivity;
import com.tsyc.tianshengyoucai.ui.activity.home.ShopDetailActivity;
import com.tsyc.tianshengyoucai.ui.activity.home.ShopRecruitActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseFragment;
import com.tsyc.tianshengyoucai.utils.BannerImageLoader;
import com.tsyc.tianshengyoucai.utils.ImageLoader;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.view.SimpleDividerItemDecortion;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.youth.banner.Banner;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.utils.statusbar.XStatusBar;
import com.youth.xframe.widget.XToast;

import java.security.acl.Permission;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

import com.tsyc.tianshengyoucai.utils.FastJsonUtil;

/**
 * author：cxd
 * CreateTime：2019/7/18
 * File description：首页
 */
@RuntimePermissions
public class HomeFragment extends BaseFragment {

    private final int REFRESH_DATA = 10010;
    private final int LOAD_LIST_DATA = 10011;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.multiple_status_view)
    MultipleStatusView mMultipleStatusView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;


    private int page = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == LOAD_LIST_DATA) {
                loadListData();
                loadNoticeData();
            } else if (msg.what == REFRESH_DATA) {
                refreshListData();
            }
        }
    };


    private HomeListAdapter adapter;
    private Banner mBanner;
    private List<String> bannerList = new ArrayList<>();

    private TextView mTvLeftName;
    private TextView mTvLeftDesc;
    private TextView mTvLeftType;
    private ImageView mIvLeftImg;
    private TextView mTvTopName;
    private TextView mTvTopDesc;
    private TextView mTvTopType;
    private ImageView mIvTopImg;
    private TextView mTvBtmName;
    private TextView mTvBtmDesc;
    private TextView mTvBtmType;
    private ImageView mIvBtmImg;
    private RecyclerView mRvNearShop;
    private RecyclerView mRvPosRecommend;
    private HomeShopAdapter shopAdapter;
    private HomePosAdapter posAdapter;
    private ViewFlipper mVfNotice;

    private Integer[] categoryIcons = {R.mipmap.jft_but_businessrecruitment, R.mipmap.jft_but_merchantservice,
            R.mipmap.jft_but_businessinformation, R.mipmap.jft_but_mapredenvelopes};
    private List<HomeListBean.ResultBean.StoreBean> store;


    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void loadData() {
        mHandler.sendEmptyMessage(LOAD_LIST_DATA);
    }

    @Override
    public void initData() {
        //图片沉浸
//        XStatusBar.setTranslucentForCoordinatorLayout(mActivity, 0);
        Eyes.translucentStatusBar(getActivity(), false);

        recyclerView.setHasFixedSize(true);
        GridLayoutManager manager = new GridLayoutManager(mContext, 2) {
            @Override
            public boolean canScrollVertically() {
                return super.canScrollVertically();
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(refreshLayout -> mHandler.sendEmptyMessage(REFRESH_DATA));

        mMultipleStatusView.setOnRetryClickListener(v -> mHandler.sendEmptyMessage(REFRESH_DATA));
        View headerView = LayoutInflater.from(mContext).inflate(R.layout.layout_home_header,
                (ViewGroup) recyclerView.getParent(), false);
        View headerView2 = LayoutInflater.from(mContext).inflate(R.layout.layout_home_header2,
                (ViewGroup) recyclerView.getParent(), false);
        View footerView = LayoutInflater.from(mContext).inflate(R.layout.layout_home_footer,
                (ViewGroup) recyclerView.getParent(), false);
        initHeaderView(headerView);
        adapter = new HomeListAdapter();
        recyclerView.setAdapter(adapter);

        adapter.addHeaderView(headerView);
        adapter.addHeaderView(headerView2);
        adapter.addFooterView(footerView);


    }

    private void initHeaderView(View hv) {
        String[] categoryNames = mContext.getResources().getStringArray(R.array.category_name);
        EditText mEtSearch = hv.findViewById(R.id.et_search);
        RelativeLayout mRLScan = hv.findViewById(R.id.rl_scan);
        mBanner = hv.findViewById(R.id.banner);

        mVfNotice = hv.findViewById(R.id.vf_notice);
        ConstraintLayout mCrlLeft = hv.findViewById(R.id.crl_left);
        ConstraintLayout mCrlRightTop = hv.findViewById(R.id.ctl_right_top);
        ConstraintLayout mCrlRightBtm = hv.findViewById(R.id.ctl_right_btm);
        mTvLeftName = hv.findViewById(R.id.tv_left_item_name);
        mTvLeftDesc = hv.findViewById(R.id.tv_left_item_desc);
        mTvLeftType = hv.findViewById(R.id.tv_left_item_type);
        mIvLeftImg = hv.findViewById(R.id.iv_left_item_img);

        mTvTopName = hv.findViewById(R.id.tv_top_item_name);
        mTvTopDesc = hv.findViewById(R.id.tv_top_item_desc);
        mTvTopType = hv.findViewById(R.id.tv_top_item_type);
        mIvTopImg = hv.findViewById(R.id.iv_top_item_img);

        mTvBtmName = hv.findViewById(R.id.tv_btm_item_name);
        mTvBtmDesc = hv.findViewById(R.id.tv_btm_item_desc);
        mTvBtmType = hv.findViewById(R.id.tv_btm_item_type);
        mIvBtmImg = hv.findViewById(R.id.iv_btm_item_img);

        ImageView mIvLeftShop = hv.findViewById(R.id.iv_left_shop);
        ImageView mIvRightInvite = hv.findViewById(R.id.iv_right_invite);
        ConstraintLayout mCtlMoreShop = hv.findViewById(R.id.ctl_more_shop); // 更多商家
        ConstraintLayout mCtlRecommendMore = hv.findViewById(R.id.ctl_recommend_more); // 更多职位

        RecyclerView mRvCategory = hv.findViewById(R.id.rv_category);// 四个分类
        mRvCategory.setLayoutManager(new GridLayoutManager(mContext, 4));
        mRvCategory.setHasFixedSize(true);
        HomeCategoryAdapter categoryAdapter = new HomeCategoryAdapter(categoryIcons, categoryNames);
        mRvCategory.setAdapter(categoryAdapter);
        categoryAdapter.setItemClickListener(position -> {
            categoryItemClickListener(position);
        });
        // 附近商家
        mRvNearShop = hv.findViewById(R.id.rv_near_shop);
        // 职位推荐
        mRvPosRecommend = hv.findViewById(R.id.rv_pos_recommend);

        shopAdapter = new HomeShopAdapter();
        mRvNearShop.setAdapter(shopAdapter);


        shopAdapter.setOnItemClickListener((adapter, view, position) -> {
            int store_id = store.get(position).getStore_id();
            XLog.e("____" + store_id);
            Bundle bundle = new Bundle();
            bundle.putString("store_id", String.valueOf(store_id));
            openActivity(ShopDetailActivity.class, bundle);
        });
        posAdapter = new HomePosAdapter();
        mRvPosRecommend.setAdapter(posAdapter);
        initInnerRv(mRvNearShop);
        initInnerRv(mRvPosRecommend);


        mRLScan.setOnClickListener(this);
        mIvLeftShop.setOnClickListener(this);
        mIvRightInvite.setOnClickListener(this);
        mCtlMoreShop.setOnClickListener(this);
        mCtlRecommendMore.setOnClickListener(this);
        mCrlLeft.setOnClickListener(this);
        mCrlRightTop.setOnClickListener(this);
        mCrlRightBtm.setOnClickListener(this);
        mCtlMoreShop.setOnClickListener(this);
        mCtlRecommendMore.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_scan: //扫一扫
                HomeFragmentPermissionsDispatcher.showCameraWithPermissionCheck(this);
                break;

            case R.id.iv_left_shop://商家入驻
                break;

            case R.id.iv_right_invite: //邀请
                break;

            case R.id.crl_left: //旗舰手机
                break;

            case R.id.ctl_right_top: //笔记本

                break;

            case R.id.ctl_right_btm: //智能科技
                break;

            case R.id.ctl_more_shop: //附近商家
                break;

            case R.id.ctl_recommend_more: //更多职位
                break;

            default:
                break;
        }
    }

    //四个分类点击事件
    private void categoryItemClickListener(int position) {
        switch (position) {
            case 0: // 商家招聘
                openActivity(ShopRecruitActivity.class);
                break;
            case 1: //商家服务
                break;
            case 2: //商业资讯
                break;
            case 3: // 地图红包
                break;

        }
    }


    private void initInnerRv(RecyclerView recyclerView) {
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SimpleDividerItemDecortion(mContext));
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    //请求数据
    private void loadListData() {
        BaseRequestUtils.postRequest(mActivity, UrlManager.index, null, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {

                XLog.e("首页数据" + response.body());
                HomeListBean homeListBean = FastJsonUtil.fromJson(response.body(), HomeListBean.class);
                if (homeListBean == null || !homeListBean.getCode().equals("200")) {
                    return;
                }

                HomeListBean.ResultBean result = homeListBean.getResult();
                List<HomeListBean.ResultBean.BannerBean> banner = result.getBanner();
                List<HomeListBean.ResultBean.RecommendBean> recommend = result.getRecommend();
                store = result.getStore();
                List<HomeListBean.ResultBean.PositionBean> positionBeans = result.getPosition();
                List<HomeListBean.ResultBean.GoodsBean> goods = result.getGoods();
                loadBanner(banner);
                loadRecommend(recommend);
                shopAdapter.setNewData(store);
                posAdapter.setNewData(positionBeans);
                adapter.setNewData(goods);
                adapter.setOnItemClickListener((adapter, view, position) -> {
                    HomeListBean.ResultBean.GoodsBean goodsBean = goods.get(position);
                    int goods_id = goodsBean.getGoods_id();
                    XLog.e("" + goods_id);
                    Bundle bundle = new Bundle();
                    bundle.putString("goods_id", String.valueOf(goods_id));
                    openActivity(GoodsDetailActivity.class, bundle);
                });

            }

            @Override
            public void failed(Response<String> response) {
                Throwable e = response.getException();
                e.printStackTrace();
               // XLog.e("首页数据 " + e.getMessage());
                T.showToast(mContext, "网络请求失败，请刷新数据");
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
            }
        });
    }

    private void loadRecommend(List<HomeListBean.ResultBean.RecommendBean> recommend) {
        HomeListBean.ResultBean.RecommendBean recommendBean = recommend.get(0);
        HomeListBean.ResultBean.RecommendBean recommendBean1 = recommend.get(1);
        HomeListBean.ResultBean.RecommendBean recommendBean2 = recommend.get(2);
        if (null == recommendBean || TextUtils.isEmpty(recommendBean.getName())) {
            return;
        }
        mTvLeftName.setText(recommendBean.getName());
        mTvLeftDesc.setText(recommendBean.getNote());
        mTvLeftType.setText(recommendBean.getTypes());
        ImageLoader.loadNormal(mContext, recommendBean.getImage(), mIvLeftImg);
        if (null == recommendBean1 || TextUtils.isEmpty(recommendBean1.getName())) {
            return;
        }
        mTvTopName.setText(recommendBean1.getName());
        mTvTopDesc.setText(recommendBean1.getNote());
        mTvTopType.setText(recommendBean1.getTypes());
        ImageLoader.loadNormal(mContext, recommendBean1.getImage(), mIvTopImg);
        if (null == recommendBean2 || TextUtils.isEmpty(recommendBean2.getName())) {
            return;
        }
        mTvBtmName.setText(recommendBean2.getName());
        mTvBtmDesc.setText(recommendBean2.getNote());
        mTvBtmType.setText(recommendBean2.getTypes());
        ImageLoader.loadNormal(mContext, recommendBean2.getImage(), mIvBtmImg);

    }

    private void loadBanner(List<HomeListBean.ResultBean.BannerBean> banner) {
        bannerList.clear();
        for (int i = 0; i < banner.size(); i++) {
            bannerList.add(banner.get(i).getUrl());
        }
        mBanner.setImageLoader(new BannerImageLoader(26));//设置图片加载器
        mBanner.setImages(bannerList);
        mBanner.start();

    }

    // 公告数据
    private void loadNoticeData() {

        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.homeNote, null, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                HomeNoticeBean homeNoticeBean = FastJsonUtil.fromJson(response.body(), HomeNoticeBean.class);
                if (null == homeNoticeBean || !homeNoticeBean.getCode().equals("200")) {
                    return;
                }

                List<HomeNoticeBean.ResultBean> result = homeNoticeBean.getResult();
                for (int i = 0; i < result.size(); i++) {
                    String name = result.get(i).getName();
                    String text = result.get(i).getText();
                    View childView = View.inflate(mActivity, R.layout.viewflipper_item, null);
                    TextView mTvVfContent = childView.findViewById(R.id.tv_vf_content);
                    mTvVfContent.setText(name + "  " + text);
                    mVfNotice.addView(childView);
                    mTvVfContent.setOnClickListener(v -> {
                        int displayedChild = mVfNotice.getDisplayedChild();
                        String title = result.get(displayedChild).getName();

                        XToast.normal(title);
                    });
                }
                mVfNotice.startFlipping();

            }

            @Override
            public void failed(Response<String> response) {

            }
        });
    }


    //刷新数据
    private void refreshListData() {
        BaseRequestUtils.postRequest(mActivity, UrlManager.index, null, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {

                XLog.e("首页数据" + response.body());
                HomeListBean homeListBean = FastJsonUtil.fromJson(response.body(), HomeListBean.class);
                if (homeListBean == null || !homeListBean.getCode().equals("200")) {
                    return;
                }

                refreshLayout.finishRefresh();
                HomeListBean.ResultBean result = homeListBean.getResult();
                List<HomeListBean.ResultBean.BannerBean> banner = result.getBanner();
                List<HomeListBean.ResultBean.RecommendBean> recommend = result.getRecommend();
                store = result.getStore();
                List<HomeListBean.ResultBean.PositionBean> position = result.getPosition();
                List<HomeListBean.ResultBean.GoodsBean> goods = result.getGoods();

                bannerList.clear();
                loadBanner(banner);
                loadRecommend(recommend);
                shopAdapter.setNewData(store);
                posAdapter.setNewData(position);
                adapter.setNewData(goods);

                shopAdapter.notifyDataSetChanged();
                posAdapter.notifyDataSetChanged();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void failed(Response<String> response) {
                Throwable e = response.getException();
                e.printStackTrace();
              //  XLog.e("首页数据 " + e.getMessage());

                refreshLayout.finishLoadMore();
                refreshLayout.finishRefresh();
            }
        });

    }


    @Override
    public void onPause() {
        super.onPause();
        if (null != mBanner)
            mBanner.stopAutoPlay();
        if (null != mVfNotice)
            mVfNotice.stopFlipping();

    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != mBanner)
            mBanner.startAutoPlay();
        if (null != mVfNotice)
            mVfNotice.startFlipping();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //ShopDetailActivityPermissionsDispatcher.onSkipIntentWithPermissionCheck(ShopDetailActivity.this);
        HomeFragmentPermissionsDispatcher.showCameraWithPermissionCheck(HomeFragment.this);
    }


    @NeedsPermission(Manifest.permission.CAMERA)
    void showCamera() {
        Intent intent = new Intent(mContext, SecondActivity.class);
        startActivity(intent);
    }


/*   @OnShowRationale(Manifest.permission.CAMERA)
   void showRationaleForCamera(final PermissionRequest request) {
       new AlertDialog.Builder(this)
               .setMessage(R.string.permission_camera_rationale)
               .setPositiveButton(R.string.button_allow, (dialog, button) -> request.proceed())
               .setNegativeButton(R.string.button_deny, (dialog, button) -> request.cancel())
               .show();
   }*/


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


}
