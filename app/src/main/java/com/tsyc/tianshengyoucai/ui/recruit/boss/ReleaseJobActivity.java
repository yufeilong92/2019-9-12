package com.tsyc.tianshengyoucai.ui.recruit.boss;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tsyc.tianshengyoucai.Eventbuss.RefreshHomeEvent;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.manager.SelectCityManager;
import com.tsyc.tianshengyoucai.requet.RequestBossHttp;
import com.tsyc.tianshengyoucai.requet.RequestJobHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.DialogUtils;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.SelectKeyUtil;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.BossJobDeatailVo;
import com.tsyc.tianshengyoucai.vo.GmSelectBean;
import com.tsyc.tianshengyoucai.vo.JobSelectIndox;
import com.tsyc.tianshengyoucai.vo.JobSelectItemVo;
import com.tsyc.tianshengyoucai.vo.SelectKeyVo;
import com.tsyc.tianshengyoucai.vo.SelectVo;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/9/3 16:57:
 * @Purpose :发布职位
 */
public class ReleaseJobActivity extends Base2Activity implements View.OnClickListener {

    private SelectKeyVo mVo;
    private TextView mTvBossPosition;
    private TextView mTvBossWork;
    private ConstraintLayout mCtlBossWork;
    private TextView mTvBossExperience;
    private ConstraintLayout mCtlBossExperience;
    private TextView mTvBossEducation;
    private ConstraintLayout mCtlBossEducation;
    private TextView mTvBossWorkPay;
    private ConstraintLayout mCtlBossWorkPay;
    private TextView mTvBossPositionDesc;
    private ConstraintLayout mCtlBossPositionDesc;
    private TextView mTvBossPositionLight;
    private TextView mTvBossPositionArea;
    private ConstraintLayout mCtlBossPositionArea;
    private Button mBtnBossCommit;
    /**
     * 工作性质
     */
    private GmSelectBean mSelectWorkVo;
    private GmSelectBean mSelectEdu;
    private GmSelectBean mSelectMoney;
    private SelectCityManager mSelectCityManager;

    public static final String Finish = "finish";
    public static final String JOB_ID = "job_id";
    /**
     * 工作描述
     */
    public static final String INPUTWORK = "INPUTWORK";

    /**
     * 工作地址
     *
     * @return
     */
    private static final int Work_REQUESTCODE = 1011;
    /**
     * 职位描述
     */
    private static final int Wrokinfom_REQUESTCODE = 1012;
    /**
     * 选择职位
     */
    private static final int JOB_REQUESTCODE = 1010;

    /**
     * 用户选择职业
     */
    public static final String SELECT_JOBVO = "job";

    public static final String PROVINCE = "province";
    public static final String CITY = "city";
    public static final String AREA = "area";
    public static final String ADDRESS = "address";
    private String mProvince;
    private String mCity;
    private String mArea;
    private String mAddress;
    private JobSelectIndox mSelectJobVo;
    private GmSelectBean mSelectTag;
    private GmSelectBean mSelectExpersion;
    private String mFinish;
    private List<SelectVo> mSelectVos;
    private String mJobId;

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_release_job);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_release_job;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        if (getIntent() != null) {
            mFinish = getIntent().getStringExtra(Finish);
            mJobId = getIntent().getStringExtra(JOB_ID);
        }
        initView();
        initEvent();
        initRequest();

    }

    private void initRequest() {
        showProgress();
        RequestJobHttp.getSingleton(this).requestJobsCommonKeys(new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                mVo = GsonUtils.getGson(success, SelectKeyVo.class);

                if (StringUtil.isEmpty(mJobId)) return;
                requestJianlI();
            }

            @Override
            public void error(String error) {
                onError();
            }
        });

    }

    private void requestJianlI() {
        showProgress();
        RequestBossHttp.getSingleton(this).requestBossJobDetail(mJobId, new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                BossJobDeatailVo mVo = GsonUtils.getGson(success, BossJobDeatailVo.class);
                BossJobDeatailVo.ResultBean result = mVo.getResult();
                bindViewData(result);

            }

            @Override
            public void error(String error) {
                onError();
            }
        });

    }

    private void bindViewData(BossJobDeatailVo.ResultBean result) {
        mSelectJobVo = new JobSelectIndox();
        JobSelectItemVo vo = new JobSelectItemVo();
        vo.setName(result.getPosition_name());
        vo.setId(result.getPosition_id());
        mSelectJobVo.setSelectVo(vo);
        //工作
        mTvBossPosition.setText(result.getPosition_name());

        mSelectWorkVo = result.getJob_type();
        mTvBossWork.setText(mSelectWorkVo.getDesc());

        mSelectExpersion = result.getWork_experience();
        mTvBossExperience.setText(mSelectExpersion.getDesc());

        mSelectEdu = result.getEducation();
        mTvBossEducation.setText(mSelectEdu.getDesc());

        mSelectMoney = result.getSalary();
        mTvBossWorkPay.setText(mSelectMoney.getDesc());

        mTvBossPositionDesc.setText(result.getDesc());

        BossJobDeatailVo.ResultBean.HighlightsBean bean = result.getHighlights();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < bean.getDesc().size(); i++) {
            String com = bean.getDesc().get(i);
            buffer.append(com);
            if (i != bean.getDesc().size() - 1) {
                buffer.append("/");
            }
        }
        mTvBossPositionLight.setText(buffer.toString());

        String value = bean.getValue();
        String[] split = value.split(",");
        mSelectVos = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            SelectVo selectVo = new SelectVo();
            selectVo.setId(Integer.valueOf(split[i]));
            mSelectVos.add(selectVo);
        }

        BossJobDeatailVo.ResultBean.AddressInfoBean info = result.getAddress_info();
        mProvince = info.getProvince();
        mCity = info.getCity();
        mArea = info.getArea();
        mAddress = info.getAddress();
        mTvBossPositionArea.setText(mProvince + mCity + mArea + mAddress);

    }

    private void initEvent() {


    }


    private void initView() {
        mTvBossPosition = (TextView) findViewById(R.id.tv_boss_position);
        mTvBossPosition.setOnClickListener(this);
        mTvBossWork = (TextView) findViewById(R.id.tv_boss_work);
        mTvBossWork.setOnClickListener(this);
        mCtlBossWork = (ConstraintLayout) findViewById(R.id.ctl_boss_work);
        mCtlBossWork.setOnClickListener(this);
        mTvBossExperience = (TextView) findViewById(R.id.tv_boss_experience);
        mTvBossExperience.setOnClickListener(this);
        mCtlBossExperience = (ConstraintLayout) findViewById(R.id.ctl_boss_experience);
        mCtlBossExperience.setOnClickListener(this);
        mTvBossEducation = (TextView) findViewById(R.id.tv_boss_education);
        mTvBossEducation.setOnClickListener(this);
        mCtlBossEducation = (ConstraintLayout) findViewById(R.id.ctl_boss_education);
        mCtlBossEducation.setOnClickListener(this);
        mTvBossWorkPay = (TextView) findViewById(R.id.tv_boss_work_pay);
        mTvBossWorkPay.setOnClickListener(this);
        mCtlBossWorkPay = (ConstraintLayout) findViewById(R.id.ctl_boss_work_pay);
        mCtlBossWorkPay.setOnClickListener(this);
        mTvBossPositionDesc = (TextView) findViewById(R.id.tv_boss_position_desc);
        mTvBossPositionDesc.setOnClickListener(this);
        mCtlBossPositionDesc = (ConstraintLayout) findViewById(R.id.ctl_boss_position_desc);
        mCtlBossPositionDesc.setOnClickListener(this);
        mTvBossPositionLight = (TextView) findViewById(R.id.tv_boss_position_light);
        mTvBossPositionLight.setOnClickListener(this);
        mTvBossPositionArea = (TextView) findViewById(R.id.tv_boss_position_area);
        mTvBossPositionArea.setOnClickListener(this);
        mBtnBossCommit = (Button) findViewById(R.id.btn_boss_commit);
        mBtnBossCommit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_boss_commit:
                submit();
                break;
            case R.id.tv_boss_work://工作性质
                if (mVo == null) {
                    initRequest();
                }
                showBossWork(mTvBossWork);
                break;
            case R.id.tv_boss_experience://工作经验
                if (mVo == null) {
                    initRequest();
                }
                showExperience(mTvBossExperience);
                break;
            case R.id.tv_boss_education://最低学历
                if (mVo == null) {
                    initRequest();
                }
                showEdu(mTvBossEducation);
                break;
            case R.id.tv_boss_work_pay://薪资范围
                if (mVo == null) {
                    initRequest();
                }
                showMoney(mTvBossWorkPay);
                break;
            case R.id.tv_boss_position_light://工作亮点
                if (mVo == null) {
                    initRequest();
                }
                shoWorkTag(mTvBossPositionLight);
                break;
            case R.id.tv_boss_position_area://工作地址
                mResultTo.startBossWorkArea(mContext, mProvince, mCity, mArea, mAddress, Work_REQUESTCODE);
                break;
            case R.id.tv_boss_position_desc://工作描述
                String com = StringUtil.getObjectToStr(mTvBossPositionDesc);
                mResultTo.startWorkInfom(mContext, com, Wrokinfom_REQUESTCODE);
                break;

            case R.id.tv_boss_position://职位
                mResultTo.startJobsSelect(mContext, JOB_REQUESTCODE);
                break;
        }
    }


    private void shoWorkTag(TextView tv) {
        SelectKeyVo.ResultBean result = mVo.getResult();
        List<GmSelectBean> job_type = result.getHighlights();
        DialogUtils.getSingleton().selectWorkTagAlertDialog(mContext, job_type, mSelectVos, new DialogUtils.WorkTagInterface() {
            @Override
            public void onClickListener(List<SelectVo> list) {
                mSelectVos = list;
                if (list == null || list.isEmpty()) return;
                StringBuffer buffer = new StringBuffer();
                for (int i = 0; i < list.size(); i++) {
                    SelectVo vo = list.get(i);
                    buffer.append(vo.getName());
                    if (i != list.size() - 1) {
                        buffer.append("/");
                    }
                }
                tv.setText(buffer.toString());
            }
        });

//        List<String> items = new ArrayList<>();
//        for (int i = 0; i < job_type.size(); i++) {
//            GmSelectBean bean = job_type.get(i);
//            items.add(bean.getDesc());
//        }
//
//
//
//        SelectKeyUtil.showSelect(ReleaseJobActivity.this, items, new SelectKeyUtil.SelectClick() {
//
//            @Override
//            public void selectItem(int postion, String com) {
//                mSelectTag = job_type.get(postion);
//                tv.setText(com);
//
//            }
//        });


    }

    private void showExperience(TextView tv) {
        SelectKeyVo.ResultBean result = mVo.getResult();
        List<GmSelectBean> job_type = result.getExperience();
        List<String> items = new ArrayList<>();
        for (int i = 0; i < job_type.size(); i++) {
            GmSelectBean bean = job_type.get(i);
            items.add(bean.getDesc());
        }

        SelectKeyUtil.showSelect(ReleaseJobActivity.this, items, new SelectKeyUtil.SelectClick() {
            @Override
            public void selectItem(int postion, String com) {
                mSelectExpersion = job_type.get(postion);
                tv.setText(com);

            }
        });

    }

    private void showEdu(TextView tv) {

        SelectKeyVo.ResultBean result = mVo.getResult();
        List<GmSelectBean> job_type = result.getEducation();
        List<String> items = new ArrayList<>();
        for (int i = 0; i < job_type.size(); i++) {
            GmSelectBean bean = job_type.get(i);
            items.add(bean.getDesc());
        }

        SelectKeyUtil.showSelect(ReleaseJobActivity.this, items, new SelectKeyUtil.SelectClick() {
            @Override
            public void selectItem(int postion, String com) {
                mSelectEdu = job_type.get(postion);
                tv.setText(com);

            }
        });

    }

    private void showBossWork(TextView tv) {
        SelectKeyVo.ResultBean result = mVo.getResult();
        List<GmSelectBean> job_type = result.getJob_type();
        List<String> items = new ArrayList<>();
        for (int i = 0; i < job_type.size(); i++) {
            GmSelectBean bean = job_type.get(i);
            items.add(bean.getDesc());
        }

        SelectKeyUtil.showSelect(ReleaseJobActivity.this, items, new SelectKeyUtil.SelectClick() {
            @Override
            public void selectItem(int postion, String com) {
                mSelectWorkVo = job_type.get(postion);
                tv.setText(com);

            }
        });

    }

    private void showMoney(TextView tv) {

        SelectKeyVo.ResultBean result = mVo.getResult();
        List<GmSelectBean> job_type = result.getExpected_salary();
        List<String> items = new ArrayList<>();
        for (int i = 0; i < job_type.size(); i++) {
            GmSelectBean bean = job_type.get(i);
            items.add(bean.getDesc());
        }

        SelectKeyUtil.showSelect(ReleaseJobActivity.this, items, new SelectKeyUtil.SelectClick() {


            @Override
            public void selectItem(int postion, String com) {
                mSelectMoney = job_type.get(postion);
                tv.setText(com);

            }
        });
    }

    private void submit() {
        String position = mTvBossPosition.getText().toString().trim();
        if (TextUtils.isEmpty(position)) {
            Toast.makeText(this, "请填写职位名称", Toast.LENGTH_SHORT).show();
            return;
        }

        String work = mTvBossWork.getText().toString().trim();
        if (TextUtils.isEmpty(work)) {
            Toast.makeText(this, "请选择工作性质", Toast.LENGTH_SHORT).show();
            return;
        }

        String experience = mTvBossExperience.getText().toString().trim();
        if (TextUtils.isEmpty(experience)) {
            Toast.makeText(this, "请选择经验要求", Toast.LENGTH_SHORT).show();
            return;
        }

        String education = mTvBossEducation.getText().toString().trim();
        if (TextUtils.isEmpty(education)) {
            Toast.makeText(this, "请选择最低学历", Toast.LENGTH_SHORT).show();
            return;
        }

        String pay = mTvBossWorkPay.getText().toString().trim();
        if (TextUtils.isEmpty(pay)) {
            Toast.makeText(this, "请填写职位描述", Toast.LENGTH_SHORT).show();
            return;
        }

        String desc = mTvBossPositionDesc.getText().toString().trim();
        if (TextUtils.isEmpty(desc)) {
            Toast.makeText(this, "请填写职位描述", Toast.LENGTH_SHORT).show();
            return;
        }

        String light = mTvBossPositionLight.getText().toString().trim();
        if (TextUtils.isEmpty(light)) {
            Toast.makeText(this, "请选择职位亮点", Toast.LENGTH_SHORT).show();
            return;
        }

        String area = mTvBossPositionArea.getText().toString().trim();
        if (TextUtils.isEmpty(area)) {
            Toast.makeText(this, "请选择工作地点", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < mSelectVos.size(); i++) {
            SelectVo vo = mSelectVos.get(i);
            buffer.append(vo.getId());
            if (i != mSelectVos.size() - 1) {
                buffer.append(",");
            }
        }

        showProgress();
        JobSelectItemVo selectVo = mSelectJobVo.getSelectVo();
        RequestBossHttp.getSingleton(this).submitaddPosition(selectVo.getName(), String.valueOf(selectVo.getId()),
                String.valueOf(mSelectWorkVo.getValue()), String.valueOf(mSelectExpersion.getValue()),
                String.valueOf(mSelectEdu.getValue()), String.valueOf(mSelectMoney.getValue()), buffer.toString(),
                desc, mProvince, mCity, mArea, mAddress, mJobId, new RequestResultCallback() {
                    @Override
                    public void success(String success) {
                        if (onSuccess(success)) return;
                        T.showToast(mContext, "提交成功");
                        if (StringUtil.isEmpty(mFinish)) {
                            showProgress();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    dismissProgress();
                                    mResultTo.startBossHome(mContext);
                                }
                            }, 1000);
                        } else {
                            if (StringUtil.isEmpty(mJobId)) {
                                mResultTo.finishBase(mContext);
                            } else {
                                mResultTo.startBossJobManage(mContext);
                            }
                            EventBus.getDefault().postSticky(new RefreshHomeEvent(""));
                        }
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

        if (resultCode == RESULT_OK && requestCode == JOB_REQUESTCODE) {
            mSelectJobVo = (JobSelectIndox) data.getSerializableExtra(SELECT_JOBVO);
            mTvBossPosition.setText(mSelectJobVo.getSelectVo().getName());
            return;
        }

        if (resultCode == RESULT_OK && requestCode == Wrokinfom_REQUESTCODE) {
            String com = data.getStringExtra(INPUTWORK);
            mTvBossPositionDesc.setText(com);
            return;
        }

        if (resultCode == RESULT_OK && requestCode == Work_REQUESTCODE) {
            mProvince = data.getStringExtra(PROVINCE);
            mCity = data.getStringExtra(CITY);
            mArea = data.getStringExtra(AREA);
            mAddress = data.getStringExtra(ADDRESS);
            mTvBossPositionArea.setText(mProvince + mCity + mArea + mAddress);
        }
    }
}
