package com.tsyc.tianshengyoucai.ui.recruit.boss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tsyc.tianshengyoucai.Eventbuss.BossChangeInfomEvent;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.recruit.BossJobManageAdapter;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.requet.RequestBossHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.DialogUtils;
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.BossCompanyInfomVo;
import com.tsyc.tianshengyoucai.vo.BossJobManagerVo;
import com.tsyc.tianshengyoucai.vo.CompanySearchVo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/9/4 11:33:
 * @Purpose :公司信息
 */
public class BossCompanyInfomActivity extends Base2Activity implements View.OnClickListener {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private TextView mTvBossCompanyName;
    private TextView mTvBossCompanyTag;
    private ImageView mIvBossCompanyLogo;
    private TextView mTvBossCompanyInfomInfom;
    private ImageView mIvBossCompanyInfomInfom;
    private LinearLayout mLlBossCompanyInfomInfom;
    private TextView mTvBossCompanyInfomJob;
    private ImageView mIvBossCompanyInfomJob;
    private LinearLayout mLlBossCompanyInfomJob;
    private TextView mTvBossCompanyInfomCom;
    private ImageView mIvBossCompanyInfomMore;
    private Button mBtnBossCompanyChangecp;
    private Button mBtnBossCompanyInfom;
    private LinearLayout mLlBossCompanyInfom;
    private RecyclerView mGmRlvContent;
    private ImageView mIvEmpty;
    private SmartRefreshLayout mGmSmrlayoyut;
    private LinearLayout mLlBossCompanyJob;
    private ScrollView mSvBossCompanyInfom;
    private TextView mTvBossCompanyCity;
    private TextView mTvBossCompanyAddress;
    private BossJobManageAdapter mAdapter;

    private ArrayList mArray;
    private boolean isRefresh;
    private int mPage;
    /**
     * 公司
     */
    public static final String SELECTCOMPANY = "company";
    /**
     * 更换公司
     */
    private static final int REQUESTCOMPANYCODE = 1111;


    public static final String COMPANY_ID = "id";
    private String mCompanyId;
    private RelativeLayout mRlBossCompanyBtn;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_boss_company_infom);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_boss_company_infom;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        if (getIntent() != null) {
            mCompanyId = getIntent().getStringExtra(COMPANY_ID);
        }
        initView();
        initEvent();
        initRequest();
    }

    private void initRequest() {
        showProgress();
        RequestBossHttp.getSingleton(this).requestBossCompanyDetail(new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                BossCompanyInfomVo vo = GsonUtils.getGson(success, BossCompanyInfomVo.class);
                BossCompanyInfomVo.ResultBean result = vo.getResult();
                bindViewData(result);
            }

            @Override
            public void error(String error) {
                onError();
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void requestChangerInfom(BossChangeInfomEvent event) {
        initRequest();
    }

    private void bindViewData(BossCompanyInfomVo.ResultBean result) {
        mTvBossCompanyName.setText(result.getFull_name());
        GlideUtil.getSingleton().loadQuadRangleImager(mContext, mIvBossCompanyLogo, result.getLogo());
        StringBuffer buffer = new StringBuffer();
        if (result.getCompany_size() != null)
            buffer.append(result.getCompany_size().getDesc());
        buffer.append("|");
        if (result.getIndustries() != null)
            buffer.append(result.getIndustries().getDesc());
        mTvBossCompanyTag.setText(buffer.toString());
        mTvBossCompanyInfomCom.setText(result.getDesc());
        if (result.getAddress_info() != null) {
            mTvBossCompanyCity.setText(result.getAddress_info().getCity());
            BossCompanyInfomVo.ResultBean.AddressInfoBean info = result.getAddress_info();
            StringBuffer addressStr = new StringBuffer();
            addressStr.append(info.getProvince());
            addressStr.append(info.getCity());
            addressStr.append(info.getArea());
            addressStr.append(info.getAddress());
            mTvBossCompanyAddress.setText(addressStr.toString());
        }
    }

    private void initEvent() {
        showSelect(true);
        mLlBossCompanyInfomInfom.setOnClickListener(v -> {
            showSelect(true);
        });
        mLlBossCompanyInfomJob.setOnClickListener(v -> {
            showSelect(false);
            bindView();
        });
        mIvBossCompanyInfomMore.setOnClickListener(v -> {
            String com = StringUtil.getObjectToStr(mTvBossCompanyInfomCom);
            if (StringUtil.isEmpty(com)) {
                mIvBossCompanyInfomMore.setVisibility(View.GONE);
                return;
            }
            if (com.length() < 150) {
                mIvBossCompanyInfomMore.setVisibility(View.GONE);
                return;
            }
            ViewGroup.LayoutParams params = mTvBossCompanyInfomCom.getLayoutParams();
            mIvBossCompanyInfomMore.setVisibility(View.GONE);
            params.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
            mTvBossCompanyInfomCom.setLayoutParams(params);
        });
        if (!StringUtil.isEmpty(mCompanyId)) {
            mRlBossCompanyBtn.setVisibility(View.GONE);
        } else {
            mRlBossCompanyBtn.setVisibility(View.VISIBLE);
        }
    }

    private void bindView() {
        clearData();
        initAdapter();
        initRefresh();
        mGmSmrlayoyut.autoRefresh();
    }

    private void initAdapter() {
        mAdapter = new BossJobManageAdapter(mContext, mArray);
        RecyclerUtil.setGridManage(mContext, mGmRlvContent, mAdapter);
        mAdapter.setListener(vo -> {

        });
    }

    private void initRefresh() {
        mGmSmrlayoyut.setEnableLoadMore(false);
        mGmSmrlayoyut.setOnRefreshListener(refreshLayout -> {
            loadData(true);

        });
        mGmSmrlayoyut.setOnLoadMoreListener(refreshLayout -> {
            loadData(false);
        });

    }

    private void loadData(boolean refresh) {
        if (isRefresh) {
            return;
        }
        isRefresh = true;
        if (refresh)
            mPage = 1;
        RequestBossHttp.getSingleton(this).requestBossList("1", String.valueOf(mPage), new RequestResultCallback() {
            @Override
            public void success(String success) {
                isRefresh = false;
                if (refresh)
                    mGmSmrlayoyut.finishRefresh();
                else
                    mGmSmrlayoyut.finishLoadMore();
                if (onSuccess(success)) return;
                BossJobManagerVo vo = GsonUtils.getGson(success, BossJobManagerVo.class);

                BossJobManagerVo.ResultBean result = vo.getResult();
                List<BossJobManagerVo.ResultBean.DataBean> data = result.getData();

                if (refresh)
                    clearData();
                if (data == null || data.isEmpty()) {
                    if (refresh) {
                        mIvEmpty.setVisibility(View.VISIBLE);
                        mGmRlvContent.setVisibility(View.GONE);
                    }
                    mGmSmrlayoyut.setEnableLoadMore(false);
                    mAdapter.notifyDataSetChanged();
                    return;
                }
                if (refresh) {
                    mIvEmpty.setVisibility(View.GONE);
                    mGmRlvContent.setVisibility(View.VISIBLE);
                }
                addData(data);
                if (mArray.size() >= result.getTotal()) {
                    mGmSmrlayoyut.setEnableLoadMore(false);
                } else {
                    mGmSmrlayoyut.setEnableLoadMore(true);
                    mPage = result.getCurrent_page() + 1;
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void error(String error) {
                isRefresh = false;
                if (refresh)
                    mGmSmrlayoyut.finishRefresh();
                else
                    mGmSmrlayoyut.finishLoadMore();
                onError();
            }
        });

    }

    private void showSelect(boolean show) {
        mTvBossCompanyInfomInfom.setTextColor(mContext.getResources().getColor(show ? R.color.color_5769E7 : R.color.color_444A53));
        mTvBossCompanyInfomJob.setTextColor(mContext.getResources().getColor(show ? R.color.color_444A53 : R.color.color_5769E7));
        mIvBossCompanyInfomInfom.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        mIvBossCompanyInfomJob.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
        mLlBossCompanyJob.setVisibility(show ? View.GONE : View.VISIBLE);
        mSvBossCompanyInfom.setVisibility(show ? View.VISIBLE : View.GONE);

    }

    private void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mTvBossCompanyName = (TextView) findViewById(R.id.tv_boss_company_name);
        mTvBossCompanyTag = (TextView) findViewById(R.id.tv_boss_company_tag);
        mIvBossCompanyLogo = (ImageView) findViewById(R.id.iv_boss_company_logo);
        mTvBossCompanyInfomInfom = (TextView) findViewById(R.id.tv_boss_company_infom_infom);
        mIvBossCompanyInfomInfom = (ImageView) findViewById(R.id.iv_boss_company_infom_infom);
        mLlBossCompanyInfomInfom = (LinearLayout) findViewById(R.id.ll_boss_company_infom_infom);
        mTvBossCompanyInfomJob = (TextView) findViewById(R.id.tv_boss_company_infom_job);
        mIvBossCompanyInfomJob = (ImageView) findViewById(R.id.iv_boss_company_infom_job);
        mLlBossCompanyInfomJob = (LinearLayout) findViewById(R.id.ll_boss_company_infom_job);
        mTvBossCompanyInfomCom = (TextView) findViewById(R.id.tv_boss_company_infom_com);
        mIvBossCompanyInfomMore = (ImageView) findViewById(R.id.iv_boss_company_infom_more);
        mBtnBossCompanyChangecp = (Button) findViewById(R.id.btn_boss_company_changecp);
        mBtnBossCompanyInfom = (Button) findViewById(R.id.btn_boss_company_infom);
        mLlBossCompanyInfom = (LinearLayout) findViewById(R.id.ll_boss_company_infom);
        mGmRlvContent = (RecyclerView) findViewById(R.id.gm_rlv_content);
        mIvEmpty = (ImageView) findViewById(R.id.iv_empty);
        mGmSmrlayoyut = (SmartRefreshLayout) findViewById(R.id.gm_smrlayoyut);
        mLlBossCompanyJob = (LinearLayout) findViewById(R.id.ll_boss_company_job);

        mBtnBossCompanyChangecp.setOnClickListener(this);
        mBtnBossCompanyInfom.setOnClickListener(this);
        mSvBossCompanyInfom = (ScrollView) findViewById(R.id.sv_boss_company_infom);
        mSvBossCompanyInfom.setOnClickListener(this);
        mTvBossCompanyCity = (TextView) findViewById(R.id.tv_boss_company_city);
        mTvBossCompanyCity.setOnClickListener(this);
        mTvBossCompanyAddress = (TextView) findViewById(R.id.tv_boss_company_address);
        mTvBossCompanyAddress.setOnClickListener(this);
        mRlBossCompanyBtn = (RelativeLayout) findViewById(R.id.rl_boss_company_btn);
        mRlBossCompanyBtn.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        //公司名称
        if (resultCode == RESULT_OK && requestCode == REQUESTCOMPANYCODE) {
            CompanySearchVo.ResultBean mSelectCompany = (CompanySearchVo.ResultBean) data.getSerializableExtra(SELECTCOMPANY);
            showChangerCompany(mSelectCompany);
        }
    }

    private void showChangerCompany(CompanySearchVo.ResultBean mSelectCompany) {
        DialogUtils.getSingleton().showSureAlerDialog(mContext, "是否确认更换公司", "",
                "确认", "取消", new DialogUtils.OnDialogSuceClickListener() {
                    @Override
                    public void sureClick() {
                        submitChangeCompany(mSelectCompany);
                    }

                    @Override
                    public void cannerClick() {

                    }
                });
    }

    /**
     * 提交更换公司接口
     *
     * @param mSelectCompany
     */
    private void submitChangeCompany(CompanySearchVo.ResultBean mSelectCompany) {
        showProgress();
        RequestBossHttp.getSingleton(this).submitBosschangeCompany(String.valueOf(mSelectCompany.getId()), new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                T.showToast(mContext, "修改成功");
                mResultTo.finishBase(mContext);
            }

            @Override
            public void error(String error) {
                onError();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_boss_company_changecp:
                mResultTo.startCompany(mContext, REQUESTCOMPANYCODE);
                break;
            case R.id.btn_boss_company_infom:
                mResultTo.startBossCompanyChanger(mContext);
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
