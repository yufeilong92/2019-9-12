package com.tsyc.tianshengyoucai.ui.recruit.boss;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.recruit.TradeListAdapter;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.flyn.Eyes;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.requet.RequestJobHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.JobSelectItemVo;
import com.tsyc.tianshengyoucai.vo.TradeSelectIndox;
import com.tsyc.tianshengyoucai.vo.TradeSelectVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/9/6 15:21:
 * @Purpose :更多职位
 */
public class BossOtherJobActivity extends Base2Activity {
    private RecyclerView mRlvTradeContent;
    private ArrayList mArray;
    private TradeListAdapter mAdapter;
    private String mInputParam;
    private List<TradeSelectIndox> mSelectList;
    public static final String SELECTNUMBER = "selectnumber";
    private Integer mSelectNumber;
    private TextView mTvBossOtherJobSure;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_boss_other_job);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_boss_other_job;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        Eyes.translucentStatusBar(this, false);
        if (getIntent() != null) {
            mSelectNumber = getIntent().getIntExtra(SELECTNUMBER, 1);
        }
        initView();
        clearData();
        initEvent();
        initAdapter();
        initRequest();
    }

    private void initEvent() {
        mSelectList = new ArrayList<>();
        mTvBossOtherJobSure.setOnClickListener(v -> {
            if (mSelectList == null || mSelectList.isEmpty()) {
                T.showToast(mContext, "请选择查看的职位");
                return;
            }
            JobSelectItemVo selectVo = mSelectList.get(0).getSelectVo();
            mResultTo.startBossOtherJobResult(mContext, selectVo.getId(), selectVo.getName());
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
        mRlvTradeContent = (RecyclerView) findViewById(R.id.rlv_trade_content);
        mTvBossOtherJobSure = (TextView) findViewById(R.id.tv_boss_other_job_sure);
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

    public int getListSelectVo() {
        return mSelectList.size();
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

    private void addSelectVo(JobSelectItemVo vo,  int id) {
        if (mSelectList == null || mSelectList.isEmpty()) {
            TradeSelectIndox selectIndox = new TradeSelectIndox();
            selectIndox.setSelectVo(vo);
            selectIndox.setTypeone(id);
            mSelectList.add(selectIndox);
            setSelectVo(vo, id);
            return;
        }
        mSelectList.clear();
        TradeSelectIndox indox = new TradeSelectIndox();
        indox.setTypeone(id);
        indox.setSelectVo(vo);
        mSelectList.add(indox);
        setSelectVo(vo, id);
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
                    }else {
                        vo1.setSelect(false);
                    }
                }
            }else {
                List<JobSelectItemVo> items = vo.getSumItems();
                for (int k = 0; k < items.size(); k++) {
                    JobSelectItemVo selectItemVo = items.get(k);
                    selectItemVo.setSelect(false);
                }
            }
        }
        mAdapter.notifyDataSetChanged();
    }
}
