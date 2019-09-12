package com.tsyc.tianshengyoucai.ui.activity.mine.address;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tsyc.tianshengyoucai.Eventbuss.AddressEvent;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.AddressAapter;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.requet.RequestSettingHttp;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.DialogUtils;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.AddressListBeanVo;
import com.tsyc.tianshengyoucai.vo.AddressListVo;
import com.youth.xframe.utils.log.XLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/9 10:29:
 * @Purpose :收货地
 */
public class AddressActivity extends BaseActivity {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private TextView mGmTvRightTitle;
    private RecyclerView mGmRlvContent;
    private ImageView mIvEmpty;
    private SmartRefreshLayout mGmSmrlayoyut;
    private ArrayList mArray;
    private AddressAapter mAddressAapter;
    private String returnAddInfo;
    private boolean isRefresh;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_address);
//        initView();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_address;
    }

    @Override
    public void initData() {

        initView();
        initEvent();
        clearData();
        initAdapter();
        mGmSmrlayoyut.autoRefresh();

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void refreshData(AddressEvent event) {
        if (mGmSmrlayoyut != null) {
            mGmSmrlayoyut.autoRefresh();
        }
    }

    private void initEvent() {
        mGmTvRightTitle.setText("新增");
        mGmTvRightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResultTo.startAddAddress(mContext);
            }
        });
        mGmSmrlayoyut.setEnableLoadMore(false);
        mGmSmrlayoyut.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initRequest();
            }
        });
        mGmSmrlayoyut.autoRefresh();
    }

    private void initRequest() {
        if (isRefresh) {
            return;
        }
        isRefresh = true;
        RequestSettingHttp.getSingleton(this).requestMyaddressList(new RequestResultCallback() {
            @Override
            public void success(String success) {
                isRefresh = false;
                mGmSmrlayoyut.finishRefresh();
                NormalBean beans = GsonUtils.getGson(success, NormalBean.class);
                if (beans.getCode().equals("100")) {
                    mIvEmpty.setVisibility(View.VISIBLE);
                    mGmRlvContent.setVisibility(View.GONE);
                    T.showToast(mContext, beans.getMessage());
                    return;
                } else {
                    mIvEmpty.setVisibility(View.GONE);
                    mGmRlvContent.setVisibility(View.VISIBLE);
                }
                AddressListVo bean = GsonUtils.getGson(success, AddressListVo.class);
                clearData();
                addData(bean.getResult().getAddress_list());
                if (mArray == null || mArray.isEmpty()) {
                    mIvEmpty.setVisibility(View.VISIBLE);
                    mAddressAapter.notifyDataSetChanged();
                    return;
                }
                mAddressAapter.notifyDataSetChanged();

            }

            @Override
            public void error(String error) {
                isRefresh = false;
                mGmSmrlayoyut.finishRefresh();
                toastErrorNet();
            }
        });
    }

    private void initAdapter() {
        RecyclerUtil.setGridManage(mContext, mGmRlvContent);
        mAddressAapter = new AddressAapter(mContext, mArray);
        mGmRlvContent.setAdapter(mAddressAapter);
        mAddressAapter.setListener(new AddressAapter.OnItemClickListener() {
            @Override
            public void addressItemClick(int position, AddressListBeanVo vo) {

                if (!TextUtils.isEmpty(returnAddInfo)) {
                    String address = vo.getAddress();
                    String true_name = vo.getTrue_name();
                    String area_info = vo.getArea_info();
                    String tel_phone = vo.getMob_phone();
                    int address_id = vo.getAddress_id();
                    Intent intent = new Intent();
                    intent.putExtra("mobile", tel_phone);
                    intent.putExtra("true_name", true_name);
                    intent.putExtra("address_id", String.valueOf(address_id));
                    intent.putExtra("address", area_info + address);
                    setResult(201, intent);
                    finish();
                } else {
                    mResultTo.startAddAddress(mContext, vo);
                }
            }

            @Override
            public void deleteItemClick(int position, AddressListBeanVo vo) {
                DialogUtils.getSingleton().showSureAlerDialog(mContext, "确认删除",
                        "", "确认", "取消", new DialogUtils.OnDialogSuceClickListener() {
                            @Override
                            public void sureClick() {

                                deleteAddress(vo.getAddress_id());
                            }

                            @Override
                            public void cannerClick() {

                            }
                        });

            }
        });
    }

    private void deleteAddress(int address_id) {
        showProgress();
        RequestSettingHttp.getSingleton(this).deleteMyaddress(String.valueOf(address_id), new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                NormalBean vo = GsonUtils.getGson(success, NormalBean.class);
                if (vo.getCode().equals("100")) {
                    T.showToast(mContext, vo.getMessage());
                    return;
                }
                T.showToast(mContext, "删除成功");
                mGmSmrlayoyut.autoRefresh();

            }

            @Override
            public void error(String error) {
                dismissProgress();
                toastErrorNet();
            }
        });

    }

    public void initView() {
        Bundle extra = getIntent().getBundleExtra(Constant.bundleExtra);
        if (null != extra) {
            returnAddInfo = extra.getString("type");
        }
        XLog.e("" + returnAddInfo);
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResultTo.finishBase(mContext);
            }
        });
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mGmTvRightTitle = (TextView) findViewById(R.id.gm_tv_right_title);
        mGmRlvContent = (RecyclerView) findViewById(R.id.gm_rlv_content);
        mIvEmpty = (ImageView) findViewById(R.id.iv_empty);
        mGmSmrlayoyut = (SmartRefreshLayout) findViewById(R.id.gm_smrlayoyut);
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
