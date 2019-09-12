package com.tsyc.tianshengyoucai.ui.fragment.sub;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.model.bean.DetailStoreBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.activity.home.ReportStoreActivity;
import com.tsyc.tianshengyoucai.ui.activity.home.ShopDetailActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.CertificationActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseFragment;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.ImageLoader;
import com.youth.xframe.utils.XRegexUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

import static com.youth.xframe.utils.XRegexUtils.*;

/**
 * author：van
 * CreateTime：2019/7/30
 * File description： 商家详情 商家
 */
@RuntimePermissions
public class DetailStoreFragment extends BaseFragment {

    @BindView(R.id.rv_shop_image)
    RecyclerView rvShopImage;
    @BindView(R.id.tv_shop_desc)
    TextView tvShopDesc;
    @BindView(R.id.tv_hot_pos)
    TextView tvHotPos;
    @BindView(R.id.tv_pos_more)
    TextView tvPosMore;
    @BindView(R.id.tv_store_name)
    TextView tvStoreName;
    @BindView(R.id.tv_store_cate)
    TextView tvStoreCate;
    @BindView(R.id.tv_store_work)
    TextView tvStoreWork;
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.iv_door_image)
    ImageView iv_door_image;

    @BindView(R.id.tv_shop_phone)
    TextView tvShopPhone;


    private String storeId;
    private String storePhone;

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_detail_shop;
    }

    @Override
    protected void loadData() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            mStateView.showEmpty();
            return;
        }
        storeId = arguments.getString("store_id");
        shopDetail();
    }

    private void shopDetail() {

        Map<String, String> params = new HashMap<>();
        params.put("store_id", storeId);
        loading(true);
        BaseRequestUtils.postRequestWithHeader(mActivity, UrlManager.shopDetail, params, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("商家" + response.body());
                dismiss();
                DetailStoreBean detailStoreBean = FastJsonUtil.fromJson(response.body(), DetailStoreBean.class);
                if (detailStoreBean == null) {
                    return;
                }

                if (!detailStoreBean.getCode().equals(Constant.REQUEST_SUCCESS) || null == detailStoreBean.getResult()) {
                    return;
                }

                DetailStoreBean.ResultBean result = detailStoreBean.getResult();
                int is_recruit = result.getIs_recruit();
                String sc_name = result.getSc_name();
                String store_name = result.getStore_name();
                storePhone = result.getStore_phone();

                String store_working_time = result.getStore_working_time();
                String door_photo = result.getDoor_photo();
                tvStoreWork.setText(store_working_time);
                tvStoreName.setText(store_name);
                tvStoreCate.setText(sc_name);
                tvShopPhone.setText(storePhone);
                ImageLoader.loadCenterCrop(mContext, door_photo, iv_door_image, 10);
                if (is_recruit == 0) {
                    ivImage.setBackgroundResource(R.mipmap.jft_icon_jobinformationhasbeenclosed);
                    tvPosMore.setText(getString(R.string.text_store_hot_pos_tip));
                } else {
                    ivImage.setBackgroundResource(R.mipmap.jft_icon_jobinformationrecruitment);
                    tvPosMore.setText(getString(R.string.text_store_hot_pos_tip2));
                }

            }

            @Override
            public void failed(Response<String> response) {
                dismiss();
                XToast.normal(getString(R.string.service_error));
            }
        });
    }


    @OnClick({R.id.ctl_pos_more, R.id.tv_profit, R.id.tv_shop_phone, R.id.tv_report_shop})
    public void onViewClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("store_id", storeId);
        switch (view.getId()) {
            case R.id.ctl_pos_more: // 更多职位

                break;
            case R.id.tv_profit:// 资质
                openActivity(CertificationActivity.class, bundle);
                break;
            case R.id.tv_report_shop: //举报商家
                openActivity(ReportStoreActivity.class, bundle);
                break;
            case R.id.tv_shop_phone: //商家电话
                DetailStoreFragmentPermissionsDispatcher.onSkipIntentWithPermissionCheck(this);
                break;
        }
    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        DetailStoreFragmentPermissionsDispatcher.onSkipIntentWithPermissionCheck(this);
    }

    @NeedsPermission(Manifest.permission.CALL_PHONE)
    void onSkipIntent() {
        if (!checkMobile(storePhone)) {
            XToast.normal("店铺号码有误，拨号失败");
            return;
        }
        Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + storePhone));//直接拨打电话

        if (ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(mActivity, new String[]{"Manifest.permission.CALL_PHONE"}, 0);
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
