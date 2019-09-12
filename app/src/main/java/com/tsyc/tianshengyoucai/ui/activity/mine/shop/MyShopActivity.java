package com.tsyc.tianshengyoucai.ui.activity.mine.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.model.adapter.HomeCategoryAdapter;
import com.tsyc.tianshengyoucai.model.bean.IsInnerBean;
import com.tsyc.tianshengyoucai.model.bean.StoreIndexBean;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.SecondActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.FastJsonUtil;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：van
 * CreateTime：2019/7/29
 * File description： 我的店铺
 */
public class MyShopActivity extends BaseActivity {


    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.rv_category)
    RecyclerView rvCategory;
    @BindView(R.id.ctl_detail)
    ConstraintLayout ctlDetail;

    private Integer[] categoryIcons = {R.mipmap.jft_but_publishgoods, R.mipmap.jft_but_publishingpreferences,
            R.mipmap.jft_but_commoditymanagement, R.mipmap.jft_but_ordermanagement};

    private boolean isInner = false;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_my_shop;
    }

    @Override
    public void initView() {
        checkIsInner();
        String[] categoryNames = mContext.getResources().getStringArray(R.array.shop_tool_name);

        rvCategory.setLayoutManager(new GridLayoutManager(mContext, 4));
        rvCategory.setHasFixedSize(true);
        HomeCategoryAdapter categoryAdapter = new HomeCategoryAdapter(categoryIcons, categoryNames);
        rvCategory.setAdapter(categoryAdapter);
        categoryAdapter.setItemClickListener(this::toolItemClickListener);
        requestStoreIndex();
    }

    @OnClick({R.id.tv_shop_set, R.id.ll_go_cash, R.id.ctl_evaluate, R.id.ctl_detail, R.id.ctl_under_order, R.id.ctl_scan})
    public void onViewClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.tv_shop_set: // 店铺设置
                openActivity(ShopSetActivity.class);
                break;
            case R.id.ll_go_cash: // 去体现
                bundle.putString("type", "shop");
                openActivity(GoCashActivity.class, bundle);
                break;
            case R.id.ctl_evaluate: // 评价管理
                openActivity(EvaluateManageActivity.class);
                break;
            case R.id.ctl_detail: // 收入明细
                openActivity(IncomeLogActivity.class);
                break;

            case R.id.ctl_scan:
                openActivity(SecondActivity.class, Constant.CASHIER_SCAN);
                break;

            case R.id.ctl_under_order: // 先下订单
                bundle.putString("type", "2");
                openActivity(OrderManageActivity.class, bundle);
                break;
        }
    }

    //请求店铺信息
    private void requestStoreIndex() {
        loading(true);
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.storeIndex, null, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                dismiss();
                StoreIndexBean storeIndexBean = FastJsonUtil.fromJson(response.body(), StoreIndexBean.class);
                if (storeIndexBean == null) {
                    return;
                }
                if (!storeIndexBean.getCode().equals(Constant.REQUEST_SUCCESS) || storeIndexBean.getResult() == null) {
                    XToast.normal(storeIndexBean.getMessage());
                    return;
                }
                StoreIndexBean.ResultBean result = storeIndexBean.getResult();
                int income = result.getStore_info().getIncome();
                String storeName = result.getStore_info().getStore_name();

                tvMoney.setText(String.valueOf(income));
            }

            @Override
            public void failed(Response<String> response) {
                dismiss();
                XToast.normal(getString(R.string.service_error));
            }
        });
    }

    private void toolItemClickListener(int position) {
        Bundle bundle = new Bundle();
        switch (position) {

            case 0: //发布商品
                if (isInner) {
                    bundle.putString("is_inner", "1");
                } else {
                    bundle.putString("is_inner", "0");
                }
                //openActivity(ReleaseShopActivity.class, bundle);
                openActivity(ReleaseShopNewActivity.class, bundle);
                break;
            case 1: // 发布优惠
                openActivity(ReleaseCouponActivity.class);
                break;
            case 2: //商品管理
                openActivity(ShopManageActivity.class);
                break;
            case 3: //订单管理
                bundle.putString("type", "1");
                openActivity(OrderManageActivity.class, bundle);
                break;
        }
    }

    private void checkIsInner() {
        BaseRequestUtils.postRequestWithHeader(this, UrlManager.is_inner, null, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                XLog.e("内部人? " + response.body());
                IsInnerBean isInnerBean = FastJsonUtil.fromJson(response.body(), IsInnerBean.class);
                if (null == isInnerBean) {
                    isInner = false;
                    return;
                }
                if (!isInnerBean.getCode().equals(Constant.REQUEST_SUCCESS) || isInnerBean.getResult() == null) {
                    isInner = false;
                    return;
                }

                int is_inner = isInnerBean.getResult().getIs_inner();
                if (is_inner == 1) {
                    isInner = true;
                } else {
                    isInner = false;
                }
            }

            @Override
            public void failed(Response<String> response) {
                isInner = false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null && resultCode == RESULT_OK) {

            Bundle extra = data.getExtras();
            if (null != extra) {
                String code = extra.getString(CodeUtils.RESULT_STRING);
                Bundle bundle = new Bundle();
                XLog.e("------------" + code);

                String finalCode = StringUtil.checkCode(code);
                if (TextUtils.isEmpty(finalCode)) {
                    XToast.normal(getString(R.string.plz_show_code));
                    return;
                }
                bundle.putString("code", finalCode);
                bundle.putString("type", "order_cashier");
                openActivity(ScanOrderActivity.class, bundle);
            }
        }
    }


}
