package com.tsyc.tianshengyoucai.ui.activity.mine.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.PrecomputedText;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.model.adapter.ShopSpecAdapter;
import com.tsyc.tianshengyoucai.model.bean.ShopSpecBean;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.NumberUtils;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：van
 * CreateTime：2019/7/30
 * File description： 商品规格
 */
public class ShopSpecActivity extends BaseActivity {

    @BindView(R.id.et_spec_name)
    EditText etSpecName;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.rv_spec)
    RecyclerView rvSpec;
    @BindView(R.id.et_spec)
    EditText etSpec;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.et_count)
    EditText etCount;

    @BindView(R.id.et_inner_money)
    EditText etInnerMoney;
    @BindView(R.id.tv_inner_money)
    TextView tvInnerMoney;

    private boolean isContinue = true;
    public ArrayList<ShopSpecBean.ShopSpecItemBean> shopSpecBeans = new ArrayList<>();
    //LinkedList
    private ShopSpecAdapter shopSpecAdapter;
    private static String is_inner = "2";

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_shop_spec;
    }

    @Override
    public void initView() {
        mTvTitle.setText(getString(R.string.text_shop_spec_));

        Bundle extra = getIntent().getBundleExtra(Constant.bundleExtra);
        if (extra != null) {
            // 1 内部人  2 不是内部人
            is_inner = extra.getString("is_inner");
            String specName = extra.getString("spec_name");
            etSpecName.setText(specName);
            is_inner = "1";

            XLog.e("is inner" + is_inner);
            shopSpecBeans = extra.getParcelableArrayList("spec_list");

            if (is_inner != null && is_inner.equals("1")) {
                tvInnerMoney.setVisibility(View.VISIBLE);
                etInnerMoney.setVisibility(View.VISIBLE);
            }
        }

        rvSpec.setLayoutManager(new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });


        refrashAdapter(true);
        rvSpec.setHasFixedSize(true);
    }

    public void refrashAdapter(boolean isResert) {
        if (shopSpecAdapter == null || isResert) {

            shopSpecAdapter = new ShopSpecAdapter();
            shopSpecAdapter.setNewData(shopSpecBeans);
            shopSpecAdapter.setIsInner(is_inner);

            shopSpecBeans = (ArrayList<ShopSpecBean.ShopSpecItemBean>) shopSpecAdapter.getData();
            shopSpecAdapter.setAdapterInterface(adapterInterface);
            rvSpec.setAdapter(shopSpecAdapter);
            shopSpecAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                shopSpecBeans.remove(position);
                shopSpecAdapter.notifyDataSetChanged();
            });
        } else {
            shopSpecAdapter.notifyDataSetChanged();
        }
    }

    private ShopSpecAdapter.ShopSpecAdapterInterface adapterInterface = new ShopSpecAdapter.ShopSpecAdapterInterface() {
        @Override
        public void onTextChanged(int position, int type, String s) {
            ShopSpecBean.ShopSpecItemBean bean = shopSpecBeans.get(position);
            switch (type) {
                case 1:
                    bean.setSpec_value(s);
                    break;
                case 2:
                    bean.setPrice(s);
                    break;
                case 3:
                    bean.setStock_num(s);
                    break;
                case 4:
                    bean.setInner_price(s);
                    break;

            }
        }
    };

    @OnClick({R.id.tv_spec_add, R.id.tv_delete_spec, R.id.btn_commit})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_spec_add: // 添加属性
                addSpec();
                break;
            case R.id.tv_delete_spec: // 删除属性
                shopSpecBeans.removeAll(shopSpecBeans);
                shopSpecAdapter.notifyDataSetChanged();
                break;
            case R.id.btn_commit: // 设置完成
                commitSpec();
                break;
        }
    }

    //设置完成
    private void commitSpec() {
        String specName = etSpecName.getText().toString().trim();
        if (TextUtils.isEmpty(specName)) {
            XToast.normal("请输入规格名称");
            return;
        }
        if (shopSpecBeans.size()==0){
            XToast.normal("请添加规格选项");
            return;
        }
        for (ShopSpecBean.ShopSpecItemBean shopSpecBean : shopSpecBeans) {
            String price = shopSpecBean.getPrice();
            String spec = shopSpecBean.getSpec_value();
            String count = shopSpecBean.getStock_num();
            String innerMoney = shopSpecBean.getInner_price();
            if (TextUtils.isEmpty(price) || TextUtils.isEmpty(spec) || TextUtils.isEmpty(count)) {
                XToast.normal("请把规格信息填写完整");
                return;
            }
            if (is_inner != null && is_inner.equals("1")) {
                if (TextUtils.isEmpty(innerMoney)) {
                    XToast.normal("请输入内部价");
                    return;
                }
            }
        }

        String toJson = GsonUtils.toJson(shopSpecBeans);
        Map<String, List<ShopSpecBean.ShopSpecItemBean>> map = new HashMap();
        map.put(specName, shopSpecBeans);

        String toJsons = GsonUtils.toJson(map);
        String specJson = "{\"" + specName + "\":" + toJson + "}";
        XLog.e("specJson " + specJson);

        Intent intent = new Intent();
        intent.putExtra("specJson", specJson);
        intent.putExtra("specName", specName);
        intent.putParcelableArrayListExtra("spec_value", shopSpecBeans);
        setResult(ReleaseShopActivity.INTENT_REQUEST_CODE, intent);
        finish();
    }

    //添加条属性
    private void addSpec() {
        ShopSpecBean.ShopSpecItemBean shopSpecBean = new ShopSpecBean.ShopSpecItemBean();
        shopSpecBeans.add(shopSpecBean);
        refrashAdapter(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
