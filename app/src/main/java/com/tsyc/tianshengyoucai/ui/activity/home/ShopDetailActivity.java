package com.tsyc.tianshengyoucai.ui.activity.home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tsyc.tianshengyoucai.Eventbuss.ShopCollectEvent;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.model.adapter.StoreCouponAdapter;
import com.tsyc.tianshengyoucai.model.adapter.ViewPagerAdapter;
import com.tsyc.tianshengyoucai.model.bean.NormalResultBean;
import com.tsyc.tianshengyoucai.model.bean.ShopDetailHeaderBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.fragment.sub.DetailEvaluateFragment;
import com.tsyc.tianshengyoucai.ui.fragment.sub.DetailShopFragment;
import com.tsyc.tianshengyoucai.ui.fragment.sub.DetailStoreFragment;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.FileUtil;
import com.tsyc.tianshengyoucai.utils.ImageLoader;
import com.tsyc.tianshengyoucai.utils.NumberUtils;
import com.tsyc.tianshengyoucai.utils.SaveUserInfomUtilJave;
import com.tsyc.tianshengyoucai.utils.ScreenUtils;
import com.tsyc.tianshengyoucai.utils.SimpleUtils;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.view.StarBar;
import com.tsyc.tianshengyoucai.view.indicator.CommonPagerIndicator;
import com.tsyc.tianshengyoucai.view.indicator.MClipIndicatorTitleView;
import com.tsyc.tianshengyoucai.view.pop.EnterStoreGiftPop;
import com.tsyc.tianshengyoucai.view.pop.SharePop;
import com.tsyc.tianshengyoucai.view.pop.ShopCouponListPop;
import com.tsyc.tianshengyoucai.view.pop.ShopPosterPop;
import com.tsyc.tianshengyoucai.vo.UserInfomVo;
import com.tsyc.tianshengyoucai.wxapi.WxShareUtils;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.youth.xframe.utils.XDensityUtils;
import com.youth.xframe.utils.XRegexUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * author：van
 * CreateTime：2019/8/5
 * File description： 商家详情界面
 */
@RuntimePermissions
public class ShopDetailActivity extends BaseActivity {

    @BindView(R.id.indicator)
    MagicIndicator indicator;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.iv_shop_image)
    ImageView ivShopImage;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.sb_starBar)
    StarBar sbStarBar;
    @BindView(R.id.tv_start_count)
    TextView tvStartCount;
    @BindView(R.id.ctl_coupon)
    ConstraintLayout ctlCoupon;
    @BindView(R.id.rv_coupon)
    RecyclerView rvCoupon;
    @BindView(R.id.tv_shop_address)
    TextView tvShopAddress;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_red_bag)
    TextView tvRedBag;
    @BindView(R.id.tv_lines)
    TextView tvLines;
    @BindView(R.id.tv_has_pos)
    TextView tvHasPos;

    @BindView(R.id.tv_text_back)
    TextView tvTextBack;
    @BindView(R.id.ll_more_coupon)
    LinearLayout llMoreCoupon;
    @BindView(R.id.tv_money)
    TextView tv_money;
    @BindView(R.id.tv_coupon_tip)
    TextView tv_coupon_tip;
    @BindView(R.id.tv_coupon)
    TextView tv_coupon;

    @BindView(R.id.v_line)
    View v_line;

    private String[] titles = {"商品", "评价", "商家"};
    private List<Fragment> fragments = new ArrayList<>();
    public String storeId;

    private boolean isCollect;
    private String store_phone;
    /**
     * 用于通知收藏列表刷新
     */
    private String mType;
    private UserInfomVo userInfomVo;
    private StoreCouponAdapter storeCouponAdapter;
    private String store_name;
    boolean can_get_redpacket = false;
    private int is_get;
    private String store_avatar;
    private String store_credit_average;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_shop_detail;
    }

    @Override
    public void initView() {
        mTvTitle.setText("商家详情");

        userInfomVo = SaveUserInfomUtilJave.getInstance().getUserInfom();
        Bundle extra = getIntent().getBundleExtra(Constant.bundleExtra);
        if (extra != null)
            storeId = extra.getString("store_id");

        if (getIntent() != null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                String goods_id = extras.getString("store_id");
                mType = extras.getString("type");
                if (!StringUtil.isEmpty(goods_id)) {
                    storeId = goods_id;
                }
            }

        }
        rvCoupon.setHasFixedSize(true);
        rvCoupon.setLayoutManager(new GridLayoutManager(mContext, 2) {
            @Override
            public boolean canScrollVertically() {
                return true;
            }
        });

        requestHeaderData(storeId);
    }

    @Override
    public void initData() {
        initIndicator();
        DetailShopFragment shopFragment = new DetailShopFragment();
        DetailEvaluateFragment evaluateFragment = new DetailEvaluateFragment();
        DetailStoreFragment storeFragment = new DetailStoreFragment();
        Bundle bundle = new Bundle();
        bundle.putString("store_id", storeId);
        bundle.putString("store_phone", store_phone);
        shopFragment.setArguments(bundle);
        evaluateFragment.setArguments(bundle);
        storeFragment.setArguments(bundle);
        fragments.add(shopFragment);
        fragments.add(evaluateFragment);
        fragments.add(storeFragment);

        ViewPagerAdapter mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(pageListener);
    }

    private void initIndicator() {
        CommonNavigator mCommonNavigator = new CommonNavigator(this);
        mCommonNavigator.setSkimOver(true);
        mCommonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {

                MClipIndicatorTitleView clipPagerTitleView = new MClipIndicatorTitleView(context);
                clipPagerTitleView.setText(titles[index]);
                clipPagerTitleView.setTextSize(ScreenUtils.sp2px(context, 16));
                // 设置字体选中和非选中的颜色
                clipPagerTitleView.setClipColor(getResources().getColor(R.color.tab_color));
                clipPagerTitleView.setTextColor(getResources().getColor(R.color.color_7B8391));
                clipPagerTitleView.setOnClickListener(v -> mViewPager.setCurrentItem(index));

                return clipPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                CommonPagerIndicator commonPagerIndicator = new CommonPagerIndicator(context);
                Drawable drawable = context.getResources().getDrawable(R.mipmap.jft_but_switch);
                commonPagerIndicator.setIndicatorDrawable(drawable);
                commonPagerIndicator.setDrawableHeight(UIUtil.dip2px(context, 4));
                commonPagerIndicator.setDrawableWidth(UIUtil.dip2px(context, 35));
                commonPagerIndicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                commonPagerIndicator.setStartInterpolator(new AccelerateInterpolator());

                return commonPagerIndicator;
            }
        });
        mCommonNavigator.setAdjustMode(true); // 平分
        indicator.setNavigator(mCommonNavigator);
    }

    /**
     * ViewPager切换监听方法
     */
    public ViewPager.OnPageChangeListener pageListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrollStateChanged(int state) {
            indicator.onPageScrollStateChanged(state);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            indicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }

        @Override
        public void onPageSelected(int position) {
            mViewPager.setCurrentItem(position);
            indicator.onPageSelected(position);
        }
    };

    //  请求顶部信息
    @SuppressLint("SetTextI18n")
    private void requestHeaderData(String storeId) {

        loading(true);
        Map<String, String> params = new HashMap<>();
        params.put("store_id", storeId);
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.shopDetailHeader, params, new BaseRequestUtils.getRequestCallBack() {

            @Override
            public void success(Response<String> response) {
                XLog.e(":" + response.body());
                dismiss();

                ShopDetailHeaderBean headerBean = FastJsonUtil.fromJson(response.body(), ShopDetailHeaderBean.class);
                if (null == headerBean) {
                    XToast.normal("信息请求失败，请稍候再试");
                    return;
                }
                if (!headerBean.getCode().equals(Constant.REQUEST_SUCCESS) || headerBean.getResult() == null) {
                    XToast.normal(String.valueOf(headerBean.getMessage()));
                    return;
                }

                ShopDetailHeaderBean.ResultBean resultBean = headerBean.getResult();

                can_get_redpacket = resultBean.isCan_get_redpacket();
                if (can_get_redpacket && resultBean.getRedpacket() != null) {
                    ShopDetailHeaderBean.ResultBean.Redpacket redpacket = resultBean.getRedpacket();
                    showRedPackPop(redpacket);
                }
                store_name = resultBean.getStore_name();
                store_avatar = resultBean.getStore_avatar();
                // 评分
                store_credit_average = resultBean.getStore_credit_average();
                String area_info = resultBean.getArea_info();
                String store_address = resultBean.getStore_address();
                boolean is_favorate = resultBean.isIs_favorate();
                String mb_title_img = resultBean.getMb_title_img(); // 顶部背景图
                String type_name = resultBean.getType_name();
                store_phone = resultBean.getStore_phone();
                int store_id = resultBean.getStore_id();
                tvShopName.setText(store_name);
                ImageLoader.loadCenterCrop(mContext, store_avatar, ivShopImage, 5);
                sbStarBar.setStarCount(Integer.valueOf(store_credit_average));
                sbStarBar.setStarMark(Integer.valueOf(store_credit_average));
//                sbStarBar.setIntegerMark(false);
                tvStartCount.setText(store_credit_average + "分");
                tvShopAddress.setText(area_info + store_address);
                tvPhone.setText(store_phone);


                List<ShopDetailHeaderBean.ResultBean.CouponListBean> voucher_list = resultBean.getCoupon_list();
                if (voucher_list != null && voucher_list.size() > 0) {
                    ctlCoupon.setVisibility(View.VISIBLE);
                    llMoreCoupon.setOnClickListener(v -> showCouponPop(voucher_list));
                    String voucher_limit = voucher_list.get(0).getVoucher_limit();
                    String voucher_price = voucher_list.get(0).getVoucher_price();
                    int voucher_id = voucher_list.get(0).getVoucher_id();
                    String formatNum = NumberUtils.formatNum(voucher_limit, "0");
                    tv_money.setText("￥" + voucher_price);
                    tv_coupon_tip.setText("满" + formatNum + "元可用");
                    is_get = voucher_list.get(0).getIs_get();
                    if (is_get == 0) {
                        tv_coupon.setText("领取");
                        tv_coupon.setOnClickListener(v -> {
                            receiveCoupon(voucher_id, -1);
                        });
                    } else {
                        tv_coupon.setText("已领取");
                    }
                }

                if (is_favorate) {
                    mToolBar.getMenu().findItem(R.id.iv_collect).setIcon(R.mipmap.jft_but_successfulcollectionofcommodities);
                    isCollect = true;
                } else {
                    mToolBar.getMenu().findItem(R.id.iv_collect).setIcon(R.mipmap.jft_but_bookmark);
                    isCollect = false;
                }
                ShopDetailHeaderBean.ResultBean.OtherBean otherBean = resultBean.getOther();
                if (otherBean != null) {
                    int is_recruit = otherBean.getIs_recruit();// 是否招聘
                    String is_online_offline = otherBean.getIs_online_offline(); // 是否支持线上线下
                    String is_voucher = otherBean.getIs_voucher(); //是否有红包活动

                    String commission = otherBean.getCommission();
                    if (!commission.equals("0")) {
                        tvTextBack.setText("分享消费后得 " + commission + "元佣金");
                        v_line.setVisibility(View.VISIBLE);
                    } else {
                        v_line.setVisibility(View.GONE);
                        tvTextBack.setVisibility(View.GONE);
                    }
                    if (is_recruit == 0) {
                        tvHasPos.setVisibility(View.GONE);
                    } else {
                        tvHasPos.setVisibility(View.VISIBLE);
                    }

                    if (TextUtils.isEmpty(is_online_offline)) {
                        tvLines.setVisibility(View.GONE);
                    } else {
                        tvLines.setVisibility(View.VISIBLE);
                    }
                    if (TextUtils.isEmpty(is_voucher)) {
                        tvRedBag.setVisibility(View.GONE);
                    } else {
                        tvRedBag.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void failed(Response<String> response) {
                dismiss();
                XToast.normal("信息请求失败，请稍候再试");
               // XLog.e(" shop detail " + response.getException().getMessage().toString());
            }
        });
    }

    //优惠券
    private void showCouponPop(List<ShopDetailHeaderBean.ResultBean.CouponListBean> voucher_list) {
        ShopCouponListPop couponListPop = new ShopCouponListPop(mContext);
        couponListPop.showPopupWindow();

        RecyclerView rvCoupon = couponListPop.findViewById(R.id.rv_coupon);
        rvCoupon.setHasFixedSize(true);
        rvCoupon.setLayoutManager(new LinearLayoutManager(mContext));

        storeCouponAdapter = new StoreCouponAdapter(voucher_list);
        rvCoupon.setAdapter(storeCouponAdapter);
        storeCouponAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            int voucher_id = voucher_list.get(position).getVoucher_id();

            receiveCoupon(voucher_id, position);
        });

    }

    @OnClick({R.id.tv_text_back, R.id.tv_call})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_text_back:
                break;
            case R.id.tv_call:
                ShopDetailActivityPermissionsDispatcher.onSkipIntentWithPermissionCheck(this);
                break;
        }
    }

    //领红包
    private void receiveCoupon(int voucher_id, int position) {

        Map<String, String> params = new HashMap<>();
        params.put("voucher_id", String.valueOf(voucher_id));
        BaseRequestUtils.postRequestWithHeader((Activity) mContext, UrlManager.receiveCoupons, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                NormalResultBean resultBean = FastJsonUtil.fromJson(response.body(), NormalResultBean.class);
                if (null == resultBean) {
                    return;
                }

                if (!resultBean.getCode().equals(Constant.REQUEST_SUCCESS)) {
                    XToast.normal(resultBean.getMessage());
                    return;
                }
                XToast.normal(resultBean.getMessage());
                requestHeaderData(storeId);
                if (position!=-1) {
                    storeCouponAdapter.notifyItem(position);
                    storeCouponAdapter.refreshNotifyItemChanged(position);
                }

            }

            @Override
            public void failed(Response<String> response) {
                XToast.normal("领取失败");
            }
        });
    }

    /**
     * @param item
     * @param type 1收藏  2取消收藏
     */
    private void favorites_add(MenuItem item, int type) {
        Map<String, String> params = new HashMap<>();
        params.put("store_id", storeId);
        String url;
        if (type == 2) {
            params.put("type", "");
            url = UrlManager.favorites_del;
        } else {
            url = UrlManager.favorites_add;
        }
        loading(true);
        BaseRequestUtils.postRequestWithHeader(this, url, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("shifou 收藏" + response.body());
                NormalResultBean normalBean = FastJsonUtil.fromJson(response.body(), NormalResultBean.class);
                dismiss();
                if (null == normalBean) {
                    return;
                }

                if (!normalBean.getCode().equals(Constant.REQUEST_SUCCESS)) {
                    XToast.normal(normalBean.getMessage());
                    return;
                }

                XToast.normal(normalBean.getMessage());

                if (type == 2) {
                    if (!StringUtil.isEmpty(mType)) {
                        mType = null;
                        EventBus.getDefault().postSticky(new ShopCollectEvent(""));
                    }
                    isCollect = false;
                    item.setIcon(R.mipmap.jft_but_bookmark);
                    XToast.normal("取消成功");

                } else {
                    isCollect = true;
                    item.setIcon(R.mipmap.jft_but_successfulcollectionofcommodities);
                    XToast.normal("收藏成功");
                }
            }

            @Override
            public void failed(Response<String> response) {
                XToast.normal(getString(R.string.service_error));
                dismiss();
            }
        });
    }

    //进店有礼弹窗
    private void showRedPackPop(ShopDetailHeaderBean.ResultBean.Redpacket redPackInfo) {
        EnterStoreGiftPop giftPop = new EnterStoreGiftPop(mContext);
        giftPop.showPopupWindow();
        giftPop.setAllowDismissWhenTouchOutside(false);

        TextView popMoney = giftPop.findViewById(R.id.tv_pop_money);
        TextView getBag = giftPop.findViewById(R.id.tv_get_bag);
        TextView tvDesc = giftPop.findViewById(R.id.tv_desc);
        RelativeLayout rlBack = giftPop.findViewById(R.id.rl_back);

        rlBack.setOnClickListener(v -> giftPop.dismiss());

        if (redPackInfo == null)
            return;
        String voucher_price = redPackInfo.getVoucher_price();
        String desc = redPackInfo.getDesc();
        String title = redPackInfo.getTitle();
        String use_desc = redPackInfo.getUse_desc();
        int voucher_id = redPackInfo.getVoucher_id();
        popMoney.setText("￥ " + voucher_price);
        tvDesc.setText(desc);

        getBag.setOnClickListener(v -> receiveRedPack(voucher_id, giftPop));

    }

    //领取红包
    private void receiveRedPack(int voucher_id, EnterStoreGiftPop giftPop) {
        Map<String, String> params = new HashMap<>();
        params.put("voucher_id", String.valueOf(voucher_id));
        loading(true);
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.receiveRedPack, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                dismiss();
                if (giftPop != null)
                    giftPop.dismiss();
                NormalResultBean resultBean = FastJsonUtil.fromJson(response.body(), NormalResultBean.class);
                if (null == resultBean) {
                    XToast.normal("领取失败,请稍候再试");
                    return;
                }
                if (!resultBean.getCode().equals(Constant.REQUEST_SUCCESS)) {
                    XToast.normal(resultBean.getMessage());

                    return;
                }

                XToast.normal(resultBean.getMessage());
            }

            @Override
            public void failed(Response<String> response) {
                dismiss();
                XToast.normal(getString(R.string.service_error));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.store_top, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.iv_collect) {
            if (isCollect) {
                favorites_add(item, 2);
            } else {
                favorites_add(item, 1);
            }
            return true;
        } else if (id == R.id.iv_share) {
            // http://tsyc.jiefutong.net/push/share/store?store_id=10&invite_code=AAAQ
            UserInfomVo.ResultBean result = userInfomVo.getResult();
            String invite_code = result.getInvite_code();
            String url = "http://tsyc.jiefutong.net/push/share/store?store_id=" + storeId + "&invite_code=" + invite_code;
            SharePop sharePop = new SharePop(mContext);
            sharePop.showPopupWindow();
            IWXAPI api = WXAPIFactory.createWXAPI(this, Constant.WX_APP_ID);
            sharePop.findViewById(R.id.ll_share_wx).setOnClickListener(v -> { // shareImg
                WxShareUtils.shareWebToWx(mContext, api, url, getString(R.string.app_name), store_name, "store_detail", SendMessageToWX.Req.WXSceneSession);
                sharePop.dismiss();
            });

            sharePop.findViewById(R.id.ll_share_com).setOnClickListener(v -> {
                WxShareUtils.shareWebToWx(mContext, api, url, getString(R.string.app_name), store_name, "store_detail", SendMessageToWX.Req.WXSceneTimeline);
                sharePop.dismiss();
            });

            sharePop.findViewById(R.id.ll_save_img).setVisibility(View.INVISIBLE);
            sharePop.findViewById(R.id.ll_save_img).setOnClickListener(v -> {
                sharePop.dismiss();
                showShopPoster(url);
            });

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showShopPoster(String url) {
        ShopPosterPop posterPop = new ShopPosterPop(mContext);
        posterPop.showPopupWindow();
        ImageView ivImage = posterPop.findViewById(R.id.iv_image);
        ImageView iv_qrcode = posterPop.findViewById(R.id.iv_qrcode);
        TextView tv_name = posterPop.findViewById(R.id.tv_name);
        TextView tv_price = posterPop.findViewById(R.id.tv_price);
        TextView tv_title = posterPop.findViewById(R.id.tv_title);
        RelativeLayout rl_back = posterPop.findViewById(R.id.rl_back);
        tv_title.setText("画报分享");
        rl_back.setOnClickListener(v -> posterPop.dismiss());
        ImageLoader.loadCenterCrop(mContext, store_avatar, ivImage, 5);
        tv_name.setText(store_name);
        tv_price.setText(store_credit_average+" 星");
        Bitmap qrImage = CodeUtils.createImage(url, XDensityUtils.dp2px(57), XDensityUtils.dp2px(57), null);
        iv_qrcode.setImageBitmap(qrImage);
        LinearLayout ll_save_img = posterPop.findViewById(R.id.ll_save_img);
        LinearLayout ll_poster = posterPop.findViewById(R.id.ll_poster);
        ll_save_img.setOnClickListener(v -> {
            Bitmap bitmap = SimpleUtils.getCacheBitmapFromView(ll_poster);
            String imag = FileUtil.saveImag(mContext, bitmap);
            MediaStore.Images.Media.insertImage(mContext.getContentResolver(), bitmap, imag, null);
            mContext.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + imag)));
            if (!TextUtils.isEmpty(imag)) {
                Toast.makeText(mContext, "保存成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mContext, "保存失败", Toast.LENGTH_SHORT).show();
            }
            posterPop.dismiss();
        });
    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ShopDetailActivityPermissionsDispatcher.onSkipIntentWithPermissionCheck(ShopDetailActivity.this);
    }

    @NeedsPermission(Manifest.permission.CALL_PHONE)
    void onSkipIntent() {
        if (!XRegexUtils.checkMobile(store_phone)) {
            XToast.normal("店铺号码有误，拨号失败");
            return;
        }
        Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + store_phone));//直接拨打电话

        if (ActivityCompat.checkSelfPermission(ShopDetailActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ShopDetailActivity.this, new String[]{"Manifest.permission.CALL_PHONE"}, 0);
            return;
        }
        startActivity(dialIntent);
    }


    @OnShowRationale(Manifest.permission.CALL_PHONE)
    void onSkipIntentShow(final PermissionRequest request) {
        request.proceed();
    }

    @OnNeverAskAgain(Manifest.permission.CALL_PHONE)
    void onSkipIntentNever() {
        XToast.normal("当前功能需要在设置-应用-X街-权限中开启打电话权限。");
    }


}
