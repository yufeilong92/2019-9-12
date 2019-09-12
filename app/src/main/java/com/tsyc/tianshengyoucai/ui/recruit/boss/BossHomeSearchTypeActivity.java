package com.tsyc.tianshengyoucai.ui.recruit.boss;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.flyn.Eyes;
import com.tsyc.tianshengyoucai.requet.RequestJobHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.view.FlowLayout;
import com.tsyc.tianshengyoucai.vo.GmSelectBean;
import com.tsyc.tianshengyoucai.vo.SelectKeyVo;
import com.tsyc.tianshengyoucai.vo.SelectVo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/9/6 11:12:
 * @Purpose :首页搜索界面
 */
public class BossHomeSearchTypeActivity extends Base2Activity implements View.OnClickListener {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private FlowLayout mFlEduContent;
    private FlowLayout mFlExpContent;
    private FlowLayout mFlMoneyContent;
    private FlowLayout mFlStatusContent;
    private LinearLayout mLlRot;
    private List<SelectVo> mSelectEduLists;
    private List<SelectVo> mSelectStatusLists;
    private List<SelectVo> mSelectExpLists;
    private List<SelectVo> mSelectMoneyLists;
    private Button mBtnBossHomeCanner;
    private Button mBtnBossHomeSure;
    private SelectKeyVo mVo;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_boss_home_search_type);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_boss_home_search_type;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        Eyes.translucentStatusBar(this,false);
        initView();
        initEvent();
        initRequest();
    }

    private void initEvent() {

    }

    private void initRequest() {
        showProgress();
        RequestJobHttp.getSingleton(this).requestJobsCommonKeys(new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                mVo = GsonUtils.getGson(success, SelectKeyVo.class);
                bindViewData(mVo);
            }

            @Override
            public void error(String error) {
                onError();
            }
        });

    }

    private void bindViewData(SelectKeyVo mVo) {
        SelectKeyVo.ResultBean bean = mVo.getResult();
        List<GmSelectBean> education = bean.getEducation();
        bindEdu(education);

        List<GmSelectBean> highlights = bean.getHighlights();
        bindExp(highlights);

        List<GmSelectBean> selectBeans = bean.getExpected_salary();
        bindMoney(selectBeans);

        List<GmSelectBean> status = bean.getCurrent_status();
        bindStatus(status);

    }

    private void bindEdu(List<GmSelectBean> education) {
        mSelectEduLists = new ArrayList<>();
        gmSelect(1, education, mFlEduContent, mSelectEduLists);
    }

    private void bindExp(List<GmSelectBean> highlights) {
        mSelectExpLists = new ArrayList<>();
        gmSelect(2, highlights, mFlExpContent, mSelectExpLists);
    }

    private void bindMoney(List<GmSelectBean> selectBeans) {
        mSelectMoneyLists = new ArrayList<>();
        gmSelect(3, selectBeans, mFlMoneyContent, mSelectMoneyLists);
    }

    private void bindStatus(List<GmSelectBean> status) {
        mSelectStatusLists = new ArrayList<>();
        gmSelect(4, status, mFlStatusContent, mSelectStatusLists);
    }


    private void gmSelect(int type, List<GmSelectBean> education, FlowLayout flowLayout, List<SelectVo> mSelectVos) {
        ArrayList<SelectVo> selectVos = new ArrayList<>();
        for (int i = 0; i < education.size(); i++) {
            GmSelectBean bean = education.get(i);
            SelectVo vo = new SelectVo();
            vo.setName(bean.getDesc());
            vo.setId(bean.getValue());
            if (mSelectVos != null && !mSelectVos.isEmpty()) {
                for (int l = 0; l < mSelectVos.size(); l++) {
                    SelectVo selectVo = mSelectVos.get(l);
                    if (bean.getValue() == selectVo.getId()) {
                        vo.setSelect(true);
                    }
                }
            } else {
                vo.setSelect(false);
            }
            selectVos.add(vo);
        }
        refresh(mContext, type, flowLayout, selectVos);
    }

    private void refresh(Context context, int type, FlowLayout flowLayout, ArrayList<SelectVo> selectVos) {
        flowLayout.removeAllViews();
        for (int i = 0; i < selectVos.size(); i++) {
            SelectVo vo = selectVos.get(i);
            View view1 = LayoutInflater.from(context).inflate(R.layout.item_tarde_tag_layout, null);
            TextView tv = view1.findViewById(R.id.tv_tarde_tag);
            int color1 = context.getResources().getColor(R.color.color_444A53);
            int color2 = context.getResources().getColor(R.color.color_5769E7);
            tv.setText(vo.getName());
            tv.setTextColor(color1);
            if (vo.isSelect()) {
                tv.setTextColor(context.getResources().getColor(R.color.color_5769E7));
                tv.setBackgroundResource(R.drawable.gm_circle_blue_bg);
            } else {
                tv.setTextColor(context.getResources().getColor(R.color.color_444A53));
                tv.setBackgroundResource(R.drawable.gm_circle_gray_bg);
            }
            tv.setOnClickListener(v -> {
                int color = tv.getCurrentTextColor();
                if (color == color2) {//选择
                    addListSelectVo(selectVos, vo, false);
                    tv.setTextColor(context.getResources().getColor(R.color.color_444A53));
                    tv.setBackgroundResource(R.drawable.gm_circle_gray_bg);
                    refresh(context, type, flowLayout, selectVos);
                    setValues(type, selectVos);
                    return;
                }
                addListSelectVo(selectVos, vo, true);
                tv.setTextColor(context.getResources().getColor(R.color.color_5769E7));
                tv.setBackgroundResource(R.drawable.gm_circle_blue_bg);
                refresh(context, type, flowLayout, selectVos);
                setValues(type, selectVos);
                return;

            });
            flowLayout.addView(view1);
        }
    }

    private void setValues(int type, List<SelectVo> vos) {
        switch (type) {
            case 1:
                mSelectEduLists.clear();
                for (int i = 0; i < vos.size(); i++) {
                    SelectVo vo = vos.get(i);
                    if (vo.isSelect()) {
                        mSelectEduLists.add(vo);
                    }
                }
                break;
            case 2:
                mSelectExpLists.clear();
                for (int i = 0; i < vos.size(); i++) {
                    SelectVo vo = vos.get(i);
                    if (vo.isSelect()) {
                        mSelectExpLists.add(vo);
                    }
                }
                break;
            case 3:
                mSelectMoneyLists.clear();
                for (int i = 0; i < vos.size(); i++) {
                    SelectVo vo = vos.get(i);
                    if (vo.isSelect()) {
                        mSelectMoneyLists.add(vo);
                    }
                }
                break;
            case 4:
                mSelectStatusLists.clear();
                for (int i = 0; i < vos.size(); i++) {
                    SelectVo vo = vos.get(i);
                    if (vo.isSelect()) {
                        mSelectStatusLists.add(vo);
                    }
                }
                break;
        }

    }

    private void addListSelectVo(List<SelectVo> list, SelectVo vo, boolean isSelect) {
        for (int i = 0; i < list.size(); i++) {
            SelectVo selectVo = list.get(i);
            if (selectVo.getId() == vo.getId()) {
                selectVo.setSelect(isSelect);
            }
        }
    }

    private void initView() {
        mFlEduContent = (FlowLayout) findViewById(R.id.fl_edu_content);
        mFlExpContent = (FlowLayout) findViewById(R.id.fl_exp_content);
        mFlMoneyContent = (FlowLayout) findViewById(R.id.fl_money_content);
        mFlStatusContent = (FlowLayout) findViewById(R.id.fl_status_content);
        mLlRot = (LinearLayout) findViewById(R.id.ll_rot);
        mBtnBossHomeCanner = (Button) findViewById(R.id.btn_boss_home_canner);
        mBtnBossHomeCanner.setOnClickListener(this);
        mBtnBossHomeSure = (Button) findViewById(R.id.btn_boss_home_sure);
        mBtnBossHomeSure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_boss_home_canner:
                bindViewData(mVo);
                break;
            case R.id.btn_boss_home_sure:
                submit();
                break;
        }
    }


    private void submit() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BossHomeActivity.EDU, (Serializable) mSelectEduLists);
        bundle.putSerializable(BossHomeActivity.EXP, (Serializable) mSelectExpLists);
        bundle.putSerializable(BossHomeActivity.MONEY, (Serializable) mSelectMoneyLists);
        bundle.putSerializable(BossHomeActivity.STATUS, (Serializable) mSelectStatusLists);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        mResultTo.finishBase(mContext);
    }
}
