package com.tsyc.tianshengyoucai.ui.recruit.infom;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.Eventbuss.RcRefresh;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.manager.SelectCityManager;
import com.tsyc.tianshengyoucai.requet.RequestJobHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.GmSelectBean;
import com.tsyc.tianshengyoucai.vo.JobSelectIndox;
import com.tsyc.tianshengyoucai.vo.JobSelectItemVo;
import com.tsyc.tianshengyoucai.vo.ResumeVo;
import com.tsyc.tianshengyoucai.vo.SelectKeyVo;
import com.tsyc.tianshengyoucai.vo.TradeSelectIndox;
import com.youth.xframe.utils.log.XLog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.picker.SinglePicker;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/20 10:36:
 * @Purpose :求职意向
 */

public class JobPurposeActivity extends Base2Activity implements View.OnClickListener {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private TextView mTvJobpurposeNature;
    private TextView mTvJobpurposeStatus;
    private TextView mTvJobpurposePost;
    private TextView mTvJobpurposeTrade;
    private TextView mTvJobpurposeMoney;
    private TextView mTvJobpurposeAddree;
    private Button mBtnJobpurposeGo;
    /**
     * 职位选择
     */
    private static final int JOBSELECT_REQUESTCODE = 1002;
    /**
     * 行业选择
     */
    private static final int TRADE_REQUESTCODE = 1003;
    /**
     * 用户选择职业
     */
    public static final String SELECT_JOBVO = "job";
    /**
     * 用户选择行业
     */
    public static final String SELECT_TRADE = "trade";
    private JobSelectIndox mSelectJobVo;
    private List<TradeSelectIndox> mSelectTradeLists;
    private SelectCityManager mSelectCityManager;

    private SelectKeyVo mVo;
    private GmSelectBean mSelectWoke;
    private GmSelectBean mSelectStatus;
    private GmSelectBean mSelectSalary;

    public static final String TYPE = "TYPE";
    private ResumeVo.ResultBean mData;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_job_purpose);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_job_purpose;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        if (getIntent() != null) {
            mData = (ResumeVo.ResultBean) getIntent().getSerializableExtra(TYPE);
        }
        initView();
        initEvent();
        initRequest();
        bindView();

    }

    private void bindView() {
        if (mData == null) return;
        mSelectWoke = mData.getJob_type();
        mTvJobpurposeNature.setText(mData.getJob_type().getDesc());

        mSelectStatus = mData.getCurrent_status();
        mTvJobpurposeStatus.setText(mData.getCurrent_status().getDesc());

        JobSelectIndox indox = new JobSelectIndox();
        indox.setTypethree(mData.getExpected_position().getValue());
        mTvJobpurposePost.setText(mData.getExpected_position().getDesc());
        mSelectJobVo = indox;

        mTvJobpurposeTrade.setText(mData.getExpected_industry().getDesc());
        String value = mData.getExpected_industry().getValue();
        String[] split = value.split(",");
        List<TradeSelectIndox> lists = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            JobSelectItemVo vo = new JobSelectItemVo();
            TradeSelectIndox indox1 = new TradeSelectIndox();
            vo.setId(Integer.parseInt(split[i]));
            indox1.setSelectVo(vo);
            lists.add(indox1);
        }
        mSelectTradeLists = lists;
        mSelectSalary = mData.getExpected_salary();
        mTvJobpurposeMoney.setText(mData.getExpected_salary().getDesc());

        cityParam = mData.getWork_city().getDesc();
        mTvJobpurposeAddree.setText(cityParam);
        mBtnJobpurposeGo.setText("提交");

    }

    private void initRequest() {
        showProgress();
        RequestJobHttp.getSingleton(this).requestJobsCommonKeys(new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) {
                    return;
                }
                mVo = GsonUtils.getGson(success, SelectKeyVo.class);
            }

            @Override
            public void error(String error) {
                onError();
            }
        });
    }

    private void initEvent() {

    }

    private void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mTvJobpurposeNature = (TextView) findViewById(R.id.tv_jobpurpose_nature);
        mTvJobpurposeStatus = (TextView) findViewById(R.id.tv_jobpurpose_status);
        mTvJobpurposePost = (TextView) findViewById(R.id.tv_jobpurpose_post);
        mTvJobpurposeTrade = (TextView) findViewById(R.id.tv_jobpurpose_trade);
        mTvJobpurposeMoney = (TextView) findViewById(R.id.tv_jobpurpose_money);
        mTvJobpurposeAddree = (TextView) findViewById(R.id.tv_jobpurpose_addree);
        mBtnJobpurposeGo = (Button) findViewById(R.id.btn_jobpurpose_go);

        mBtnJobpurposeGo.setOnClickListener(this);
        mTvJobpurposePost.setOnClickListener(this);
        mTvJobpurposeTrade.setOnClickListener(this);
        mTvJobpurposeNature.setOnClickListener(this);
        mTvJobpurposeStatus.setOnClickListener(this);
        mTvJobpurposeMoney.setOnClickListener(this);
        mTvJobpurposeAddree.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_jobpurpose_go:
                submit();

                break;
            case R.id.tv_jobpurpose_post://职位
                mResultTo.startJobsSelect(mContext, JOBSELECT_REQUESTCODE);
                break;
            case R.id.tv_jobpurpose_trade:
                mResultTo.startTrade(mContext, TRADE_REQUESTCODE,3);
                break;
            case R.id.tv_jobpurpose_addree:
                selectAddress();
                break;
            case R.id.tv_jobpurpose_nature://工作性质
                if (mVo == null) {
                    initRequest();
                    return;
                }

                showSelectKey(1);
                break;
            case R.id.tv_jobpurpose_status://求职状态
                if (mVo == null) {
                    initRequest();
                    return;
                }
                showSelectKey(2);
                break;
            case R.id.tv_jobpurpose_money://薪资要求
                if (mVo == null) {
                    initRequest();
                    return;
                }
                showSelectKey(3);
                break;

        }
    }

    List<GmSelectBean> job_type = null;
    List<GmSelectBean> status = null;
    List<GmSelectBean> salary = null;

    private void showSelectKey(int index) {
        ArrayList<String> items = new ArrayList<>();
        SelectKeyVo.ResultBean bean = mVo.getResult();

        switch (index) {
            case 1://工作性质
                job_type = bean.getJob_type();
                if (job_type == null) return;
                for (int h = 0; h < job_type.size(); h++) {
                    GmSelectBean job = job_type.get(h);
                    items.add(job.getDesc());
                }
                break;
            case 2://求职状态
                status = bean.getCurrent_status();
                if (status == null) return;
                for (int j = 0; j < status.size(); j++) {
                    GmSelectBean workStatus = status.get(j);
                    items.add(workStatus.getDesc());
                }

                break;
            case 3://薪资要求
                salary = bean.getExpected_salary();
                if (salary == null) return;
                for (int l = 0; l < salary.size(); l++) {
                    GmSelectBean salarys = salary.get(l);
                    items.add(salarys.getDesc());
                }
                break;
        }
        if (items == null || items.isEmpty()) return;
        SinglePicker<String> picker = new SinglePicker<String>(this, items);
        picker.setCanLoop(false);//不禁用循环
        picker.setTopBackgroundColor(0xFFEEEEEE);
        picker.setTopHeight(50);
        picker.setTopLineColor(0xFF33B5E5);
        picker.setTopLineHeight(1);
        picker.setTitleText("请选择");
        picker.setTitleTextColor(0xFF999999);
        picker.setTitleTextSize(12);
        picker.setCancelTextColor(0xFF33B5E5);
        picker.setCancelTextSize(13);
        picker.setSubmitTextColor(0xFF33B5E5);
        picker.setSubmitTextSize(13);
        picker.setSelectedTextColor(0xFF33B5E5);
        picker.setUnSelectedTextColor(0xFF999999);
        picker.setWheelModeEnable(false);
        LineConfig config = new LineConfig();
        config.setColor(Color.TRANSPARENT);//线颜色
        config.setAlpha(120);//线透明度
//        config.setRatio(1);//线比率
        picker.setLineConfig(config);
        picker.setItemWidth(200);
        picker.setBackgroundColor(0xFFE1E1E1);
        //picker.setSelectedItem(isChinese ? "处女座" : "Virgo");
        picker.setSelectedIndex(7);
        picker.setOnItemPickListener(new OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int i, String com) {
                switch (index) {
                    case 1:
                        mSelectWoke = job_type.get(i);
                        mTvJobpurposeNature.setText(com);
                        break;
                    case 2:
                        mSelectStatus = status.get(i);
                        mTvJobpurposeStatus.setText(com);
                        break;
                    case 3:
                        mSelectSalary = salary.get(i);
                        mTvJobpurposeMoney.setText(com);
                        break;
                }

            }
        });
        picker.show();

    }

    private void submit() {
        //性质
        String nature = StringUtil.getObjectToStr(mTvJobpurposeNature);
        if (StringUtil.isEmpty(nature)) {
            T.showToast(mContext, "请选择工作性质");
            return;
        }
        //状态
        String status = StringUtil.getObjectToStr(mTvJobpurposeStatus);
        if (StringUtil.isEmpty(status)) {
            T.showToast(mContext, "请选择求职状态");
            return;
        }
        //职位
        String post = StringUtil.getObjectToStr(mTvJobpurposePost);
        if (StringUtil.isEmpty(post)) {
            T.showToast(mContext, "请选择期望职位");
            return;
        }
        //行业
        String trade = StringUtil.getObjectToStr(mTvJobpurposeTrade);
        if (StringUtil.isEmpty(trade)) {
            T.showToast(mContext, "请选择期望行业");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < mSelectTradeLists.size(); i++) {
            TradeSelectIndox indox = mSelectTradeLists.get(i);
            JobSelectItemVo selectVo = indox.getSelectVo();
            int id = selectVo.getId();
            buffer.append(id);
            if (i != mSelectTradeLists.size() - 1) {
                buffer.append(",");
            }
        }
        //薪资
        String money = StringUtil.getObjectToStr(mTvJobpurposeMoney);
        if (StringUtil.isEmpty(money)) {
            T.showToast(mContext, "请选择薪资要求");
            return;
        }
        //地址
        String addree = StringUtil.getObjectToStr(mTvJobpurposeAddree);
        if (StringUtil.isEmpty(addree)) {
            T.showToast(mContext, "请选择工作地点");
            return;
        }
        showProgress();
        RequestJobHttp.getSingleton(this).submitaddExpectJobInfo(String.valueOf(mSelectWoke.getValue())
                , String.valueOf(mSelectStatus.getValue()), String.valueOf(mSelectJobVo.getTypethree()),
                buffer.toString(), String.valueOf(mSelectSalary.getValue()), cityParam, new RequestResultCallback() {
                    @Override
                    public void success(String success) {
                        if (onSuccess(success)) return;
                        T.showToast(mContext, "提交成功");
                        if (mData != null) {
                            EventBus.getDefault().postSticky(new RcRefresh(""));
                            mResultTo.finishBase(mContext);
                            return;
                        }
                        mResultTo.startEvaluest(mContext);
                    }

                    @Override
                    public void error(String error) {
                        onError();
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        if (resultCode == RESULT_OK && requestCode == JOBSELECT_REQUESTCODE) {
            mSelectJobVo = (JobSelectIndox) data.getSerializableExtra(SELECT_JOBVO);
            mTvJobpurposePost.setText(mSelectJobVo.getSelectVo().getName());
        }
        if (resultCode == RESULT_OK && requestCode == TRADE_REQUESTCODE) {
            mSelectTradeLists = (List<TradeSelectIndox>) data.getSerializableExtra(SELECT_TRADE);
            bindViewData();
        }

    }

    private void bindViewData() {
        if (mSelectTradeLists == null) {
            return;
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < mSelectTradeLists.size(); i++) {
            TradeSelectIndox selectIndox = mSelectTradeLists.get(i);
            JobSelectItemVo vo = selectIndox.getSelectVo();
            buffer.append(vo.getName());
            if (mSelectTradeLists.size() - 1 != i)
                buffer.append("/");

        }
        mTvJobpurposeTrade.setText(buffer.toString());

    }

    private int provinceOpt;
    private int cityOpt;
    private int areaOpt;
    private String provinceParam;
    private String cityParam;
    private String areaParam;

    private void selectAddress() {
        if (mSelectCityManager == null) {
            mSelectCityManager = SelectCityManager.getInstance(mContext, true);
            mSelectCityManager.setOnSelectOptionListener((options1, provinceName, options2, cityName, options3, areaName) -> {
                provinceOpt = options1;
                cityOpt = options2;
                areaOpt = options3;
                provinceParam = provinceName;
                cityParam = cityName;
                areaParam = areaName;
                XLog.e("" + provinceOpt + " - " + cityOpt + " - " + areaOpt);
                mTvJobpurposeAddree.setText(String.valueOf(provinceName + " " + cityName + " " + areaName));
            });
        }
        mSelectCityManager.showDialog(provinceOpt, cityOpt, areaOpt);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        salary = null;
        status = null;
        mVo = null;
        mSelectTradeLists = null;
        mSelectWoke = null;
        job_type = null;
    }
}
