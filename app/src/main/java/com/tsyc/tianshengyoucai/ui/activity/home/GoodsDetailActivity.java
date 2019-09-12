package com.tsyc.tianshengyoucai.ui.activity.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.manager.SPManager;
import com.tsyc.tianshengyoucai.model.adapter.ShopDetailImageAdapter;
import com.tsyc.tianshengyoucai.model.adapter.ShowShopSpecAdapter;
import com.tsyc.tianshengyoucai.model.bean.GoodsDetailBean;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.BannerImageLoader;
import com.tsyc.tianshengyoucai.utils.BannerImageLoader2;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.FileUtil;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.ImageLoader;
import com.tsyc.tianshengyoucai.utils.SaveUserInfomUtilJave;
import com.tsyc.tianshengyoucai.utils.SimpleUtils;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.utils.TextUtil;
import com.tsyc.tianshengyoucai.utils.Util;
import com.tsyc.tianshengyoucai.view.SelectGoodDetailAlertdialog;
import com.tsyc.tianshengyoucai.view.SimpleDividerItemDecortion;
import com.tsyc.tianshengyoucai.view.StarBar;
import com.tsyc.tianshengyoucai.view.pop.SharePop;
import com.tsyc.tianshengyoucai.view.pop.ShopDetailSpecPop;
import com.tsyc.tianshengyoucai.view.pop.ShopPosterPop;
import com.tsyc.tianshengyoucai.vo.GoodSpecification;
import com.tsyc.tianshengyoucai.vo.UserInfomVo;
import com.tsyc.tianshengyoucai.wxapi.WxShareUtils;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.youth.banner.Banner;
import com.youth.xframe.utils.XDensityUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;
import com.zzhoujay.richtext.RichText;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author：van
 * CreateTime：2019/7/25
 * File description：商品详情
 */

public class GoodsDetailActivity extends BaseActivity {

    private static final int REQUEST_GOODS_DERAIL = 200;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_goods_name)
    TextView tvGoodsName;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_text_back)
    TextView tvTextBack;
    @BindView(R.id.tv_share_money)
    TextView tvShareMoney;
    @BindView(R.id.tv_commission_text)
    TextView tvCommissionText;
    @BindView(R.id.ll_charge)
    LinearLayout llCharge;
    @BindView(R.id.tv_share_back)
    TextView tvShareBack;
    @BindView(R.id.tv_post)
    TextView tvPost;
    @BindView(R.id.tv_month_count)
    TextView tvMonthCount;
    @BindView(R.id.tv_shop_address)
    TextView tvShopAddress;
    @BindView(R.id.tv_tip_choose)
    TextView tvTipChoose;
    @BindView(R.id.tv_choose_specs)
    TextView tvChooseSpecs;
    @BindView(R.id.tv_shop_evaluate)
    TextView tvShopEvaluate;
    @BindView(R.id.tv_evaluate_more)
    TextView tvEvaluateMore;
    // 评论
    @BindView(R.id.iv_header)
    ImageView ivEvaHeader;
    @BindView(R.id.tv_name)
    TextView tvEvaName;
    @BindView(R.id.tv_date)
    TextView tvEvaDate;
    @BindView(R.id.tv_desc)
    TextView tvEvaDesc;
    @BindView(R.id.tv_evaluate_content)
    TextView tvEvaContent;
    @BindView(R.id.sb_starBar)
    StarBar sbStarBar;
    // 底部
    @BindView(R.id.tv_shop)
    TextView tvShop;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_collect)
    TextView tvCollect;
    @BindView(R.id.btn_buy)
    Button btnBuy;
    @BindView(R.id.good_evaluate_root)
    RelativeLayout goodEvaluate_root;
    @BindView(R.id.tv_webview)
    TextView tv_webview;
    @BindView(R.id.rv_detail_image)
    RecyclerView rvDetailImage;

    @BindView(R.id.ctl_detail_spec) //参数
            ConstraintLayout ctlDetailSpec;

    private String goodsId;
    //用户规格数据
    private List<GoodSpecification> mSpec_list;
    /**
     * 用户选择的规格数据
     */
    private List<GoodSpecification> mSelectSpeclist;
    private GoodsDetailBean.ResultBean.GoodsInfoBean mGoods_info;


    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == REQUEST_GOODS_DERAIL) {
                mMultipleStatusView.showLoading();
                requestGoodsDetail(1);
            }
        }
    };

    private SelectGoodDetailAlertdialog mAlertdialog;
    private GoodsDetailBean.ResultBean mResultBean;
    private int number = -1;
    private boolean isCollect;

    private GoodsDetailBean.ResultBean.GoodsEvaluateInfoBean goodsEvaluateInfo;
    private String shareImg;
    private String goodsName;
    /**
     * 库存量
     */
    private int mInventoryNumber = 0;
    private List<GoodsDetailBean.ResultBean.GoodsAttrBean> goods_attr;
    private String taoInviteCode;
    private List<String> bannerImages;
    private String goodsPrice;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_goods_detail;
    }

    @Override
    public void initData() {
        mTvTitle.setText("商品详情");

        rvDetailImage.setHasFixedSize(true);
        rvDetailImage.setLayoutManager(new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

        Bundle extra = getIntent().getBundleExtra(Constant.bundleExtra);
        if (extra != null) {
            goodsId = extra.getString("goods_id");
            taoInviteCode = extra.getString("taoInviteCode");
            XLog.e("goods_id" + goodsId);
            mHandler.sendEmptyMessage(REQUEST_GOODS_DERAIL);
        }
        if (getIntent() != null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) return;
            String goods_id = extras.getString("goods_id");
            if (StringUtil.isEmpty(goods_id)) return;
            goodsId = goods_id;
            mHandler.sendEmptyMessage(REQUEST_GOODS_DERAIL);
        }


    }

    @OnClick({R.id.tv_choose_specs, R.id.tv_evaluate_more, R.id.tv_shop, R.id.ctl_detail_spec, R.id.tv_share, R.id.tv_collect, R.id.btn_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_choose_specs://选择商品规格
                if (mGoods_info == null) {
                    T.showToast(mContext, "暂无数据");
                    return;
                }
                if (!Util.handleOnDoubleClick()) {
                    showSpecDialog();
                }
                break;
            case R.id.tv_evaluate_more://查看全部
                if (mGoods_info == null) {
                    T.showToast(mContext, "暂无数据");
                    return;
                }
                mResultTo.startGoodEvalueat(this, mGoods_info.getGoods_id());
                break;
            case R.id.tv_shop:
                if (mResultBean == null) {
                    T.showToast(mContext, "暂无数据");
                    return;
                }
                mResultTo.startShop(this, mResultBean.getStore_id());
                break;
            case R.id.tv_share://分享
                share();
                break;
            case R.id.tv_collect:
                if (isCollect) {
                    goodsCollect(1);
                } else {
                    goodsCollect(0);
                }
                break;

            case R.id.ctl_detail_spec: // 属性
                if (goods_attr == null || goods_attr.size() == 0) {
                    XToast.normal("该商品暂无属性");
                    return;
                }
                showSpecPop();
                break;
            case R.id.btn_buy:

                String chooseSpec = tvChooseSpecs.getText().toString().trim();
                if (number == -1) {
                    T.showToast(mContext, "请选择商品,再点击购买");
                    showSpecDialog();
                    return;
                }
                startCreateOrder(goodsId);
                break;

            default:
                break;
        }
    }

    //商品属性
    private void showSpecPop() {
        ShopDetailSpecPop detailSpecPop = new ShopDetailSpecPop(mContext);
        detailSpecPop.showPopupWindow();

        ImageView ivImageView = detailSpecPop.findViewById(R.id.iv_delete);
        ivImageView.setOnClickListener(v -> detailSpecPop.dismiss());

        RecyclerView rvSpec = detailSpecPop.findViewById(R.id.rv_spec);
        rvSpec.setLayoutManager(new LinearLayoutManager(mContext));
        rvSpec.addItemDecoration(new SimpleDividerItemDecortion(mContext));

        ShowShopSpecAdapter specAdapter = new ShowShopSpecAdapter(goods_attr);
        rvSpec.setAdapter(specAdapter);

    }

    private void startCreateOrder(String goodsId) {

        if (mInventoryNumber == 0) {
            T.showToast(mContext, "选择商品库存量为0,请选择其它商品");
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putString("goods_id", goodsId);
        bundle.putString("goods_spec", tvChooseSpecs.getText().toString());
        bundle.putString("goods_num", String.valueOf(number));
        bundle.putString("taoInviteCode", taoInviteCode);
        openActivity(CreateOrderActivity.class, bundle);
    }

    private void showSpecDialog() {
        if (mAlertdialog != null) {
            if (mAlertdialog.isShowing()) return;
            mAlertdialog.show();
            return;
        }
        mAlertdialog = new SelectGoodDetailAlertdialog(mContext, R.style.mydialog);
        mAlertdialog.setCanceledOnTouchOutside(true);
        mAlertdialog.show();
        mAlertdialog.setData(mSpec_list, mGoods_info);

        mAlertdialog.setListener(new SelectGoodDetailAlertdialog.OnSureItemClickListener() {
            @Override
            public void sureClick(List<GoodSpecification> mListVo, int mNumber) {

                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < mListVo.size(); i++) {
                    GoodSpecification specification = mListVo.get(i);
                    builder.append("[ ");
                    builder.append(specification.getName());
                    builder.append(" ");
                    builder.append(specification.getSelectType());
                    builder.append("] ");
                }
                builder.append("[数量");
                builder.append(" ");
                builder.append(mNumber + "]");
                tvChooseSpecs.setText(builder.toString());
                tvTipChoose.setText("已选");
                number = mAlertdialog.getmNumber();
            }
        });

        mAlertdialog.setValuesChanget(new SelectGoodDetailAlertdialog.ValuesChangetInterface() {
            @Override
            public void Changer(List<GoodSpecification> mListVo) {
                mSelectSpeclist = mListVo;
                requestGoodsDetail(2);
            }

            @Override
            public void Cannerchanger(List<GoodSpecification> mListVo) {
                mSelectSpeclist = mListVo;
                requestGoodsDetail(2);
            }
        });
    }

    private void share() {
        UserInfomVo userInfomVo = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = userInfomVo.getResult();
        String invite_code = result.getInvite_code();
        // http://tsyc.jiefutong.net/push/share/goods?goods_id=333&invite_code=AAAQ
        String url = "http://tsyc.jiefutong.net/push/share/goods?goods_id=" + goodsId + "&invite_code=" + invite_code;
        //  ShareUtil.getSingleton().shareText(mContext, url);
        SharePop sharePop = new SharePop(mContext);
        sharePop.showPopupWindow();
        IWXAPI api = WXAPIFactory.createWXAPI(this, Constant.WX_APP_ID);
        sharePop.findViewById(R.id.ll_share_wx).setOnClickListener(v -> { // shareImg
            WxShareUtils.shareWebToWx(mContext, api, url, getString(R.string.app_name), goodsName, "goods_detail", SendMessageToWX.Req.WXSceneSession);
            sharePop.dismiss();
        });
        sharePop.findViewById(R.id.ll_share_com).setOnClickListener(v -> {
            WxShareUtils.shareWebToWx(mContext, api, url, getString(R.string.app_name), goodsName, "goods_detail", SendMessageToWX.Req.WXSceneTimeline);
            sharePop.dismiss();
        });
        sharePop.findViewById(R.id.ll_save_img).setOnClickListener(v -> {
            sharePop.dismiss();
            showShopPoster(url, api);
        });
    }

    private void showShopPoster(String url, IWXAPI api) {
        ShopPosterPop posterPop = new ShopPosterPop(mContext);
        posterPop.showPopupWindow();

        ImageView ivImage = posterPop.findViewById(R.id.iv_image);
        ImageView iv_qrcode = posterPop.findViewById(R.id.iv_qrcode);
        TextView tv_name = posterPop.findViewById(R.id.tv_name);
        TextView tv_price = posterPop.findViewById(R.id.tv_price);
        TextView tv_title = posterPop.findViewById(R.id.tv_title);
        RelativeLayout rl_back = posterPop.findViewById(R.id.rl_back);
        TextView mTvWx = posterPop.findViewById(R.id.tv_wx);
        TextView mTvComm = posterPop.findViewById(R.id.tv_comm);

        tv_title.setText("画报分享");
        rl_back.setOnClickListener(v -> posterPop.dismiss());
        ImageLoader.loadCenterCrop(mContext, bannerImages.get(0), ivImage, 5);
        tv_name.setText(goodsName);
        tv_price.setText("￥" + goodsPrice);
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

        mTvWx.setOnClickListener(v -> {
            Bitmap bitmap = SimpleUtils.getCacheBitmapFromView(ll_poster);
            WxShareUtils.shareImgToWx(bitmap, "goods_detail", api, SendMessageToWX.Req.WXSceneSession);
            posterPop.dismiss();

        });
        mTvComm.setOnClickListener(v -> {
            Bitmap bitmap = SimpleUtils.getCacheBitmapFromView(ll_poster);
            WxShareUtils.shareImgToWx(bitmap, "goods_detail", api, SendMessageToWX.Req.WXSceneTimeline);
            posterPop.dismiss();
        });

    }

    /**
     * @param type 1 取消收藏
     */
    private void goodsCollect(int type) {
        Map<String, String> params = new HashMap<>();
        loading(true);
        XLog.e("操作类型 " + type);
        String url;
        if (type == 1) {
            url = UrlManager.favorites_del;
            params.put("store_id", goodsId);
            params.put("type", goodsId);

        } else {
            params.put("goods_id", goodsId);
            url = UrlManager.goodsCollect;
        }
        BaseRequestUtils.postRequestWithHeader(this, url, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("收藏" + response.body());
                NormalBean data = GsonUtils.getGson(response.body().toString().trim(), NormalBean.class);
                if (null == data || data.getCode().equals("100")) {
                    T.showToast(mContext, data.getMessage().toString().trim());
                    dismiss();
                    return;
                }
                if (data.getCode().equals("200")) {
                    if (type == 1) {
                        isCollect = false;
                        XToast.normal("取消成功");
                        setColloctBg(0);
                    } else {
                        XToast.normal("收藏成功");
                        setColloctBg(1);
                        isCollect = true;
                    }
                    dismiss();
                    return;
                }
                XToast.normal(data.getMessage());
                dismiss();
            }

            @Override
            public void failed(Response<String> response) {
                dismiss();
                XToast.normal(getString(R.string.service_error));
            }
        });
    }

    private void setColloctBg(int type) {
        if (type == 1) {
            Drawable drawable = getResources().getDrawable(R.mipmap.jft_but_successfulcollectionofcommodities);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvCollect.setCompoundDrawables(null, drawable, null, null);
        } else {

            Drawable drawable = getResources().getDrawable(R.mipmap.jft_but_collectionofcommodities);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvCollect.setCompoundDrawables(null, drawable, null, null);
        }
    }

    /**
     * @param type 1 未正常请求， 2 获取新的goodid
     */
    private void requestGoodsDetail(int type) {
        Map<String, String> params = new HashMap<>();
        params.put("goods_id", goodsId);
        switch (type) {
            case 1:
                break;
            case 2:
                showProgress();
                if (mSelectSpeclist != null && !mSelectSpeclist.isEmpty())
                    for (int i = 0; i < mSelectSpeclist.size(); i++) {
                        GoodSpecification vo = mSelectSpeclist.get(i);
                        params.put("spec_list[" + i + "][name]", vo.getName());
                        params.put("spec_list[" + i + "][select]", vo.getSelectType());
                    }
                break;

        }
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.goodsDetail,
                params, new BaseRequestUtils.getRequestCallBack() {

                    @Override
                    public void success(Response<String> response) {
                        XLog.e("商品详情" + response.body());
                        dismissProgress();
                        GoodsDetailBean goodsDetailBean = FastJsonUtil.fromJson(response.body(), GoodsDetailBean.class);

                        if (goodsDetailBean.getCode().equals("100")) {
                            mMultipleStatusView.showError();
                            T.showToast(mContext, goodsDetailBean.getMessage());
                            return;
                        }

                        if (null == goodsDetailBean || null == goodsDetailBean.getResult()) {
                            mMultipleStatusView.showError();
                            return;
                        }
                        if (!goodsDetailBean.getCode().equals(Constant.REQUEST_SUCCESS)) {
                            mMultipleStatusView.showError();
                            return;
                        }
                        goodsId = String.valueOf(goodsDetailBean.getResult().getGoods_info().getGoods_id());
                        mInventoryNumber = goodsDetailBean.getResult().getGoods_info().getGoods_storage();
                        if (type == 2) {
                            if (mAlertdialog != null && mAlertdialog.isShowing()) {
                                mAlertdialog.refreshValues(goodsDetailBean.getResult().getGoods_info());
                            }
                            return;
                        }
                        mMultipleStatusView.showContent();
                        mResultBean = goodsDetailBean.getResult();
                        GoodsDetailBean.ResultBean.GoodsInfoBean goodsInfo = mResultBean.getGoods_info();
                        GoodsDetailBean.ResultBean.GoodsHairInfoBean hairInfoBean = mResultBean.getGoods_hair_info();
                        goods_attr = goodsInfo.getGoods_attr();
                        loadGoodsInfo(goodsInfo, hairInfoBean);
                        mSpec_list = mResultBean.getSpec_list();
                        mGoods_info = mResultBean.getGoods_info();
                        goodsEvaluateInfo = mResultBean.getGoods_evaluate_info();
                        loadEvaluateInfo(goodsEvaluateInfo);
                        bindDetail(mGoods_info);

                        int is_collect = mResultBean.getIs_collect();
                        if (is_collect == 0) {
                            isCollect = false;
                            setColloctBg(0);
                        } else {
                            isCollect = true;
                            setColloctBg(1);
                        }

                    }

                    @Override
                    public void failed(Response<String> response) {
                        if (type == 2) {
                            dismissProgress();
                            return;
                        }
                        mMultipleStatusView.showError();

                        XLog.e("-- " + response.getException().getMessage());
                    }
                });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loadEvaluateInfo(goodsEvaluateInfo);
    }

    private void bindDetail(GoodsDetailBean.ResultBean.GoodsInfoBean mGoods_info) {
        if (StringUtil.isEmpty(mGoods_info.getGoods_detail())) {
            tv_webview.setVisibility(View.GONE);
            return;
        }
        tv_webview.setVisibility(View.VISIBLE);

        RichText.from(mGoods_info.getMobile_body()).into(tv_webview);

    }

    //评价
    private void loadEvaluateInfo(GoodsDetailBean.ResultBean.GoodsEvaluateInfoBean goodsEvaluateInfo) {
        if (goodsEvaluateInfo == null) {
            goodEvaluate_root.setVisibility(View.GONE);
            return;
        }
        goodEvaluate_root.setVisibility(View.VISIBLE);
        String avatar = goodsEvaluateInfo.getAvatar();
        String description = goodsEvaluateInfo.getDescription();
        int goodStar = goodsEvaluateInfo.getGood_star();
        String nickname = goodsEvaluateInfo.getNickname();
        String evalContent = goodsEvaluateInfo.getGeval_content();

        tvEvaContent.setText(evalContent);
        tvEvaDate.setText(description);

        //tvEvaDesc.setText(description);
        tvEvaName.setText(nickname);
        sbStarBar.setStarMark(goodStar);
        ImageLoader.loadCornersImage(mContext, avatar, ivEvaHeader, 10);
    }

    //加载商品信息
    @SuppressLint("SetTextI18n")
    private void loadGoodsInfo(GoodsDetailBean.ResultBean.GoodsInfoBean goodsInfo, GoodsDetailBean.ResultBean.GoodsHairInfoBean hairInfoBean) {

        String content = hairInfoBean.getContent();
        tvPost.setText("邮费：" + content);

        bannerImages = goodsInfo.getGoods_images();
        if (bannerImages != null && bannerImages.size() > 0) {
            shareImg = bannerImages.get(0);

            banner.setImageLoader(new BannerImageLoader2(1));//设置图片加载器
            banner.setImages(bannerImages);
            banner.start();
        }
        if (TextUtils.isEmpty(goodsInfo.getProducer())) {
            tvShopAddress.setText("产地：无");
        } else {
            tvShopAddress.setText("产地：" + goodsInfo.getProducer());
        }
        goodsName = goodsInfo.getGoods_name();
        goodsPrice = goodsInfo.getGoods_price();
        String goodsSaleNum = goodsInfo.getGoods_salenum();// 销量
        int commission = goodsInfo.getCommission(); // 佣金
        String sharingReturns = goodsInfo.getSharing_returns();//分享销售返
        int goodsStorage = goodsInfo.getGoods_storage();
        String goodsDetail = goodsInfo.getGoods_detail();// 富文本

        tvGoodsName.setText(goodsName);
        tvPrice.setText("￥" + goodsPrice);

        llCharge.setVisibility(View.GONE);
      /*  if (commission == 0) {
            llCharge.setVisibility(View.GONE);
        } else {
            llCharge.setVisibility(View.VISIBLE);
            tvShareMoney.setText("￥" + commission);
        }*/

        if (commission == 0) {
            tvShareBack.setVisibility(View.INVISIBLE);
        } else {
            tvShareBack.setText("分享消费返" + commission+"元");
            tvShareBack.setVisibility(View.VISIBLE);
        }
        tvMonthCount.setText("月销：" + goodsSaleNum);

//        List<String> detail_images = goodsInfo.getDetail_images();
//        if (detail_images != null && detail_images.size() > 0) {
//            XLog.e(detail_images.size() + "");
//            ShopDetailImageAdapter shopDetailImageAdapter = new ShopDetailImageAdapter(detail_images);
//            rvDetailImage.setAdapter(shopDetailImageAdapter);
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        banner.startAutoPlay();
    }

    @Override
    protected void onPause() {
        super.onPause();
        banner.stopAutoPlay();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Util.dismissDialog(mAlertdialog);
    }
}
