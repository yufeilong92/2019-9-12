package com.tsyc.tianshengyoucai.fragment.recruite.job;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.Eventbuss.RcRefresh;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.base.Base2Fragment;
import com.tsyc.tianshengyoucai.requet.RequestJobHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.ui.recruit.jobmine.MyDeliveryActivity;
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.vo.RcMineVo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/23 15:28:
 * @Purpose :招聘我的模块
 */
public class RcMineFragment extends Base2Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private LinearLayout mLlRcMineGoResume;
    private ImageView mIvRcMineHear;
    private TextView mTvRcMineName;
    private TextView mTvRcMineSex;
    private TextView mTvRcMineWork;
    private TextView mTvRcMineTime;
    private TextView mTvRcMineAge;
    private TextView mTvRcMineEdu;
    private TextView mTvRcMineAddress;
    private TextView mTvRcMineLookmore;
    private TextView mTvRcMineDelivernumber;
    private LinearLayout mLlRcMineDeliver;
    private TextView mTvRcMineLooknumber;
    private LinearLayout mLlRcMineLook;
    private TextView mTvRcMineInterviewnumber;
    private LinearLayout mLlRcMineInterview;
    private TextView mTvRcMineSuitablenumebr;
    private LinearLayout mLlRcMineNosuitable;
    private TextView mTvRcMineCollect;
    private TextView mTvRcMineSetting;

    public static RcMineFragment newInstance(String param1, String param2) {
        RcMineFragment fragment = new RcMineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_rc_mine, container, false);
//        initView(view);
//        return view;
//    }

    @Override
    protected int getFramgentViewId() {
        return R.layout.fragment_rc_mine;
    }

    @Override
    protected void initContentView(View view, Bundle savedInstanceState) {
        initView(view);
        initEvent();
        initRequest();
    }

    private void initRequest() {
        RequestJobHttp.getSingleton(getActivity()).requestIndxeMine(new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) {
                    return;
                }
                RcMineVo vo = GsonUtils.getGson(success, RcMineVo.class);
                RcMineVo.ResultBean result = vo.getResult();
                bindViewData(result);


            }

            @Override
            public void error(String error) {
                onError();
            }
        });

    }

    private void bindViewData(RcMineVo.ResultBean result) {
        RcMineVo.ResultBean.CvBean userinfom = result.getCv();
        mTvRcMineName.setText(userinfom.getUsername());
        mTvRcMineSex.setText(userinfom.getSex().getDesc());
        GlideUtil.getSingleton().loadQuadRangleImager(mContext, mIvRcMineHear, userinfom.getAvatar());
        mTvRcMineWork.setText(userinfom.getExpected_position().getDesc());
        mTvRcMineTime.setText("工作" + userinfom.getExperience() + "年");
        mTvRcMineAge.setText(userinfom.getAge());
        mTvRcMineEdu.setText(userinfom.getHighest_education().getDesc());
        mTvRcMineAddress.setText(userinfom.getWork_city().getDesc());

        RcMineVo.ResultBean.CvSendsBean sends = result.getCv_sends();
        mTvRcMineDelivernumber.setText(String.valueOf(sends.getSend()));
        mTvRcMineLooknumber.setText(String.valueOf(sends.getCheck()));
        mTvRcMineInterviewnumber.setText(String.valueOf(sends.getInterview()));
        mTvRcMineSuitablenumebr.setText(String.valueOf(sends.getUnmatch()));

    }

    private void initEvent() {
        mLlRcMineGoResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mResultTo.startRcLook(getActivity());
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void refreshData(RcRefresh refresh) {
        initRequest();
    }

    private void initView(View view) {
        mLlRcMineGoResume = (LinearLayout) view.findViewById(R.id.ll_rc_mine_go_resume);
        mIvRcMineHear = (ImageView) view.findViewById(R.id.iv_rc_mine_hear);
        mTvRcMineName = (TextView) view.findViewById(R.id.tv_rc_mine_name);
        mTvRcMineSex = (TextView) view.findViewById(R.id.tv_rc_mine_sex);
        mTvRcMineWork = (TextView) view.findViewById(R.id.tv_rc_mine_work);
        mTvRcMineTime = (TextView) view.findViewById(R.id.tv_rc_mine_time);
        mTvRcMineAge = (TextView) view.findViewById(R.id.tv_rc_mine_age);
        mTvRcMineEdu = (TextView) view.findViewById(R.id.tv_rc_mine_edu);
        mTvRcMineAddress = (TextView) view.findViewById(R.id.tv_rc_mine_address);
        mTvRcMineLookmore = (TextView) view.findViewById(R.id.tv_rc_mine_lookmore);
        mTvRcMineDelivernumber = (TextView) view.findViewById(R.id.tv_rc_mine_delivernumber);
        mLlRcMineDeliver = (LinearLayout) view.findViewById(R.id.ll_rc_mine_deliver);
        mTvRcMineLooknumber = (TextView) view.findViewById(R.id.tv_rc_mine_looknumber);
        mLlRcMineLook = (LinearLayout) view.findViewById(R.id.ll_rc_mine_look);
        mTvRcMineInterviewnumber = (TextView) view.findViewById(R.id.tv_rc_mine_interviewnumber);
        mLlRcMineInterview = (LinearLayout) view.findViewById(R.id.ll_rc_mine_interview);
        mTvRcMineSuitablenumebr = (TextView) view.findViewById(R.id.tv_rc_mine_suitablenumebr);
        mLlRcMineNosuitable = (LinearLayout) view.findViewById(R.id.ll_rc_mine_nosuitable);
        mTvRcMineCollect = (TextView) view.findViewById(R.id.tv_rc_mine_collect);
        mTvRcMineSetting = (TextView) view.findViewById(R.id.tv_rc_mine_setting);

        mTvRcMineLookmore.setOnClickListener(this);
        mLlRcMineDeliver.setOnClickListener(this);
        mLlRcMineLook.setOnClickListener(this);
        mLlRcMineInterview.setOnClickListener(this);
        mLlRcMineNosuitable.setOnClickListener(this);
        mTvRcMineSetting.setOnClickListener(this);
        mTvRcMineCollect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_rc_mine_lookmore://查看全部
                mResultTo.startAllMyDelivery(getActivity(), MyDeliveryActivity.ALLTYPE);
                break;
            case R.id.ll_rc_mine_deliver://投递
                mResultTo.startAllMyDelivery(getActivity(), MyDeliveryActivity.GOTYPE);
                break;
            case R.id.ll_rc_mine_look://投递
                mResultTo.startAllMyDelivery(getActivity(), MyDeliveryActivity.LOOKTYPE);
                break;
            case R.id.ll_rc_mine_interview://投递
                mResultTo.startAllMyDelivery(getActivity(), MyDeliveryActivity.ASKTYPE);
                break;
            case R.id.ll_rc_mine_nosuitable://投递
                mResultTo.startAllMyDelivery(getActivity(), MyDeliveryActivity.NOTYPE);
                break;
            case R.id.tv_rc_mine_setting://隐私设置
                mResultTo.startJobSetting(getActivity());
                break;
            case R.id.tv_rc_mine_collect://收藏
                mResultTo.startJobCollect(getActivity());
                break;
        }


    }
}
