package com.tsyc.tianshengyoucai.ui.activity.mine.bank;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.Eventbuss.BankEvent;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.bank.MyBankListAdapter;
import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.listener.ItemClickListener;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.requet.RequestSettingHttp;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.DialogUtils;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.SaveUserInfomUtilJave;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.MyBankListVo;
import com.tsyc.tianshengyoucai.vo.UserInfomVo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/19 16:28:
 * @Purpose :银行卡列表
 */
public class MyBankListActivity extends BaseActivity {
    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private RecyclerView mRlvMyBankContent;
    private TextView mGmTvRightTitle;
    private LinearLayout mLlAddBankList;
    private MyBankListAdapter mAdapter;
    private String returnBankInfo;
    private List<MyBankListVo.ResultBean> mList = new ArrayList<>();

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_my_bank_list;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_bank_list);
        Bundle extra = getIntent().getBundleExtra(Constant.bundleExtra);
        if (extra != null) {
            returnBankInfo = extra.getString("type");
        }
        initView();
        initAdapter();
        initRequest();
    }

    private void initAdapter() {
        RecyclerUtil.setGridManage(mContext, mRlvMyBankContent);
        mAdapter = new MyBankListAdapter(mContext, mList);
        mRlvMyBankContent.setAdapter(mAdapter);
        mAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                MyBankListVo.ResultBean resultBean = mList.get(position);
                String bank_name = resultBean.getBank_name();
                String bank_number = resultBean.getBank_number();
                String account_id = resultBean.getAccount_id();
                if (!TextUtils.isEmpty(returnBankInfo)) {
                    Intent intent = new Intent();
                    intent.putExtra("bank_name", bank_name);
                    intent.putExtra("bank_number", bank_number);
                    intent.putExtra("bank_id", account_id);
                    setResult(201, intent);
                    finish();
                }
            }
        });
        mAdapter.setListener(new MyBankListAdapter.OnItemClickListener() {
            @Override
            public void deleteCard(MyBankListVo.ResultBean vo) {
                showDelete(vo);
            }
        });

    }

    private void initRequest() {
        showProgress();
        UserInfomVo vo = SaveUserInfomUtilJave.getInstance().getUserInfom();
        RequestSettingHttp.getSingleton(this).requestMyBankList(vo.getResult().getKey(), new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                MyBankListVo vo = GsonUtils.getGson(success, MyBankListVo.class);
                if (vo == null || vo.getCode().equals("100")) {
                    T.showToast(mContext, vo.getMessage());
                    return;
                }
                mList.clear();
                mList = vo.getResult();
                mAdapter.refresh(mList);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void error(String error) {
                dismissProgress();
                toastErrorNet();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void addRefresh(BankEvent event) {
        initRequest();
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


    private void showDelete(MyBankListVo.ResultBean vo) {
        DialogUtils.getSingleton().showSureAlerDialog(mContext, "是否确认删除", "",
                "确认", "取消", new DialogUtils.OnDialogSuceClickListener() {
                    @Override
                    public void sureClick() {
                        showDiagog(vo);
                    }

                    @Override
                    public void cannerClick() {

                    }
                });

    }

    private void showDiagog(MyBankListVo.ResultBean mVo) {
        showProgress();
        RequestSettingHttp.getSingleton(this).submitDeleteCard(String.valueOf(mVo.getAccount_id()), new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                NormalBean vo = GsonUtils.getGson(success, NormalBean.class);
                if (vo.getCode().equals("100")) {
                    T.showToast(mContext, vo.getMessage());
                    return;
                }
                T.showToast(mContext, "删除成功");
                int postion = -1;
                for (int i = 0; i < mList.size(); i++) {
                    MyBankListVo.ResultBean bean = mList.get(i);
                    if (bean.getAccount_id().equals(mVo.getAccount_id())) {
                        postion = i;
                    }
                }
                if (postion == -1) {
                    return;
                }
                mList.remove(postion);
                mAdapter.refresh(mList);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void error(String error) {
                dismissProgress();
                toastErrorNet();
            }
        });

    }


    public void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResultTo.finishBase(mContext);
            }
        });
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mRlvMyBankContent = (RecyclerView) findViewById(R.id.rlv_my_bank_content);
        mGmTvRightTitle = (TextView) findViewById(R.id.gm_tv_right_title);
        mGmTvRightTitle.setOnClickListener(this);
//        mGmTvRightTitle.setText("添加银行卡");
//        mGmTvRightTitle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mResultTo.startAddBankList(mContext);
//            }
//        });
        mLlAddBankList = (LinearLayout) findViewById(R.id.ll_add_bank_list);
        mLlAddBankList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mResultTo.startAddBankList(mContext);

            }
        });
    }
}
