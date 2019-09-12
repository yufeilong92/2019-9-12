package com.tsyc.tianshengyoucai.ui.recruit.infom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.recruit.TradeListAdapter;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.requet.RequestJobHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.view.FlowLayout;
import com.tsyc.tianshengyoucai.vo.JobSelectItemVo;
import com.tsyc.tianshengyoucai.vo.TradeSelectIndox;
import com.tsyc.tianshengyoucai.vo.TradeSelectVo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/20 16:53:
 * @Purpose :行业
 */
public class TradeActivity extends Base2Activity {

    private TextView mGmTvRightTitle;
    private RecyclerView mRlvTradeContent;
    private ArrayList mArray;
    private TradeListAdapter mAdapter;
    private String mInputParam;
    private List<TradeSelectIndox> mSelectList;
    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private FlowLayout mFlLayout;

    public static final String SELECTNUMBER = "selectnumber";
    private Integer mSelectNumber;
    private TextView mTvTradeWaring;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_trade);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_trade;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        if (getIntent() != null) {
            mSelectNumber = getIntent().getIntExtra(SELECTNUMBER, 1);
        }
        initView();
        bindView();
        clearData();
        initEvent();
        initAdapter();
        initRequest();
    }

    private void bindView() {
        mTvTradeWaring.setText("请选择行业，最多" + mSelectNumber + "个");
    }

    private void initEvent() {
        mSelectList = new ArrayList<>();
        mGmTvRightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable(JobPurposeActivity.SELECT_TRADE, (Serializable) mSelectList);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                mResultTo.finishBase(mContext);
            }
        });

    }

    private void initAdapter() {
        mAdapter = new TradeListAdapter(mContext, mArray, mSelectNumber);
        RecyclerUtil.setGridManage(mContext, mRlvTradeContent, mAdapter);
        mAdapter.setListener(new TradeListAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, JobSelectItemVo vo) {

            }
        });


    }


    private void initRequest() {
        showProgress();
        RequestJobHttp.getSingleton(this).requestIndustries(mInputParam, new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                NormalBean vos = GsonUtils.getGson(success, NormalBean.class);
                if (vos.getCode().equals("100")) {
                    T.showToast(mContext, vos.getMessage());
                    return;
                }
                TradeSelectVo vo = GsonUtils.getGson(success, TradeSelectVo.class);
                List<JobSelectItemVo> list = vo.getResult();
                clearData();
                addData(list);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void error(String error) {
                onError();
            }
        });
    }


    private void initView() {
        mGmTvRightTitle = (TextView) findViewById(R.id.gm_tv_right_title);
        mRlvTradeContent = (RecyclerView) findViewById(R.id.rlv_trade_content);
        mGmTvRightTitle.setText("保存");
        mFlLayout = (FlowLayout) findViewById(R.id.fl_layout);
        mTvTradeWaring = (TextView) findViewById(R.id.tv_trade_waring);
    }


    private void bindSelectView() {
        mFlLayout.removeAllViews();
        if (mSelectList == null || mSelectList.isEmpty()) {
            return;
        }

        for (int i = 0; i < mSelectList.size(); i++) {
            TradeSelectIndox vo = mSelectList.get(i);
            JobSelectItemVo itemVo = vo.getSelectVo();
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_trade_layout, null);
            TextView mTvTardeTag = view.findViewById(R.id.tv_tarde_tag);
            ImageView mIvTradeDelect = view.findViewById(R.id.iv_trade_delect);
            mTvTardeTag.setText(itemVo.getName());
            mIvTradeDelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addListSelectVo(itemVo, false, vo.getTypeone());
                    bindSelectView();
                }
            });
            mFlLayout.addView(view);
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

    public void addListSelectVo(JobSelectItemVo vo, boolean isChecked, int id) {
        if (isChecked) {
            addSelectVo(vo, id);
        } else {
            removeSelectVo(vo, id);
        }
        bindSelectView();
    }

    private void removeSelectVo(JobSelectItemVo vo, int id) {
        if (mSelectList == null || mSelectList.isEmpty()) return;
        int postion = -1;
        for (int i = 0; i < mSelectList.size(); i++) {
            TradeSelectIndox indox = mSelectList.get(i);
            if (indox.getTypeone() == id) {
                postion = i;
            }
        }
        if (postion != -1) {
            mSelectList.remove(postion);
        }
        removesSelectVo(vo, id);


    }

    private void removesSelectVo(JobSelectItemVo itemVo, int id) {
        for (int i = 0; i < mArray.size(); i++) {
            JobSelectItemVo vo = (JobSelectItemVo) mArray.get(i);
            if (vo.getId() == id) {
                List<JobSelectItemVo> sumItems = vo.getSumItems();
                for (int h = 0; h < sumItems.size(); h++) {
                    JobSelectItemVo vo1 = sumItems.get(h);
                    if (vo1.getId() == itemVo.getId()) {
                        vo1.setSelect(false);
                    }
                }
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    private void addSelectVo(JobSelectItemVo vo, int id) {
        if (mSelectList == null || mSelectList.isEmpty()) {
            TradeSelectIndox selectIndox = new TradeSelectIndox();
            selectIndox.setSelectVo(vo);
            selectIndox.setTypeone(id);
            mSelectList.add(selectIndox);
            setSelectVo(vo, id);
            return;
        }
        TradeSelectIndox indox = new TradeSelectIndox();
        indox.setTypeone(id);
        indox.setSelectVo(vo);
        mSelectList.add(indox);
        setSelectVo(vo, id);
    }

    public int getListSelectVo() {
        return mSelectList.size();
    }

    private void setSelectVo(JobSelectItemVo itemVo, int id) {
        for (int i = 0; i < mArray.size(); i++) {
            JobSelectItemVo vo = (JobSelectItemVo) mArray.get(i);
            if (vo.getId() == id) {
                vo.setSelect(true);
                List<JobSelectItemVo> sumItems = vo.getSumItems();
                for (int h = 0; h < sumItems.size(); h++) {
                    JobSelectItemVo vo1 = sumItems.get(h);
                    if (vo1.getId() == itemVo.getId()) {
                        vo1.setSelect(true);
                    }
                }
            }
        }
        mAdapter.notifyDataSetChanged();
    }

}
