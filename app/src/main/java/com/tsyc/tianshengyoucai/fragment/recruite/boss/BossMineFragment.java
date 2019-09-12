package com.tsyc.tianshengyoucai.fragment.recruite.boss;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.Eventbuss.RefreshBossMeEvent;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.base.Base2Fragment;
import com.tsyc.tianshengyoucai.requet.RequestBossHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.ui.recruit.RecruitInActivity;
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.vo.BossMineVo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/28 9:33:
 * @Purpose :boss 个人界面
 */

public class BossMineFragment extends Base2Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private ImageView mIvBossHeader;
    private TextView mTvBossName;
    private TextView mTvBossEditInfo;
    private TextView mTvBossSeeCount;
    private LinearLayout mLlBossWaitSee;
    private TextView mTvBossInviteCount;
    private LinearLayout mLlBossInvite;
    private TextView mTvBossDealCount;
    private LinearLayout mLlBossDeal;
    private TextView mTvBossJobManage;
    private TextView mTvBossSendRelease;
    private TextView mTvBossLikeCollect;
    private TextView mTvBoossCompanyInfo;
    private TextView mTvBossChangeId;
    private LinearLayout mLlBossMeRoot;
    private BossMineVo mUserInfom;


    public static BossMineFragment newInstance(String param1, String param2) {
        BossMineFragment fragment = new BossMineFragment();
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
//        View view = inflater.inflate(R.layout.fragment_boss_mine2, container, false);
//        initView(view);
//        return view;
//    }

    @Override
    protected int getFramgentViewId() {
        return R.layout.fragment_boss_mine2;
    }

    @Override
    protected void initContentView(View view, Bundle savedInstanceState) {
        initView(view);
        initEvent();
        initRequestInfom();
    }

    private void initRequestInfom() {
        RequestBossHttp.getSingleton(getActivity()).requestBossMe(new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                mUserInfom = GsonUtils.getGson(success, BossMineVo.class);
                bindViewData();

            }

            @Override
            public void error(String error) {
                onError();
            }
        });

    }

    private void bindViewData() {
        if (mUserInfom == null) {
            return;
        }
        BossMineVo.ResultBean result = mUserInfom.getResult();
        BossMineVo.ResultBean.BossBean boss = result.getBoss();
        BossMineVo.ResultBean.CvSendsBean cv_sends = result.getCv_sends();
        mLlBossMeRoot.setVisibility(View.VISIBLE);
        GlideUtil.getSingleton().loadQuadRangleImager(mContext, mIvBossHeader, boss.getAvatar());
        mTvBossName.setText(boss.getUsername());
        mTvBossSeeCount.setText(String.valueOf(cv_sends.getSend()));
        mTvBossInviteCount.setText(String.valueOf(cv_sends.getInterview()));
        mTvBossDealCount.setText(String.valueOf(cv_sends.getOthers()));

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
    public void refreshEvent(RefreshBossMeEvent event) {
        showProgress();
        initRequestInfom();
    }

    private void initEvent() {


    }


    private void initView(View view) {

        mIvBossHeader = (ImageView) view.findViewById(R.id.iv_boss_header);
        mIvBossHeader.setOnClickListener(this);
        mTvBossName = (TextView) view.findViewById(R.id.tv_boss_name);
        mTvBossName.setOnClickListener(this);
        mTvBossEditInfo = (TextView) view.findViewById(R.id.tv_boss_edit_info);
        mTvBossEditInfo.setOnClickListener(this);
        mTvBossSeeCount = (TextView) view.findViewById(R.id.tv_boss_see_count);
        mLlBossWaitSee = (LinearLayout) view.findViewById(R.id.ll_boss_wait_see);
        mLlBossWaitSee.setOnClickListener(this);
        mTvBossInviteCount = (TextView) view.findViewById(R.id.tv_boss_invite_count);
        mLlBossInvite = (LinearLayout) view.findViewById(R.id.ll_boss_invite);
        mLlBossInvite.setOnClickListener(this);
        mTvBossDealCount = (TextView) view.findViewById(R.id.tv_boss_deal_count);
        mLlBossDeal = (LinearLayout) view.findViewById(R.id.ll_boss_deal);
        mLlBossDeal.setOnClickListener(this);
        mTvBossJobManage = (TextView) view.findViewById(R.id.tv_boss_job_manage);
        mTvBossJobManage.setOnClickListener(this);
        mTvBossSendRelease = (TextView) view.findViewById(R.id.tv_boss_send_release);
        mTvBossSendRelease.setOnClickListener(this);
        mTvBossLikeCollect = (TextView) view.findViewById(R.id.tv_boss_like_collect);
        mTvBossLikeCollect.setOnClickListener(this);
        mTvBoossCompanyInfo = (TextView) view.findViewById(R.id.tv_booss_company_info);
        mTvBoossCompanyInfo.setOnClickListener(this);
        mTvBossChangeId = (TextView) view.findViewById(R.id.tv_boss_change_id);
        mTvBossChangeId.setOnClickListener(this);
        mLlBossMeRoot = (LinearLayout) view.findViewById(R.id.ll_boss_me_root);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_boss_job_manage://职位管理
                mResultTo.startBossJobManage(getActivity());
                break;
            case R.id.tv_boss_send_release://发布职位
               mResultTo.startReleaseJob(getActivity());
                break;

            case R.id.tv_booss_company_info://公司信息
                mResultTo.startBossCompanyInfom(getActivity());
                break;
            case R.id.tv_boss_change_id://切换身份
                mResultTo.startRecruitInHome(getActivity(), RecruitInActivity.CHANGERTYPE,RecruitInActivity.BOSS_ID);
                break;
            case R.id.iv_boss_header://编辑个人信息
            case R.id.tv_boss_name:
            case R.id.tv_boss_edit_info:
                mResultTo.startChangerBossInfom(getActivity());
                break;
            case R.id.tv_boss_like_collect://对我感兴趣
                mResultTo.startBossInterest(getActivity());
                break;
            case R.id.ll_boss_wait_see://待查看
                mResultTo.startBossAsk(getActivity(),1);
                break;
            case R.id.ll_boss_invite://邀面试
                mResultTo.startBossAsk(getActivity(),2);
                break;
            case R.id.ll_boss_deal://已处理
                mResultTo.startOtherBoss(getActivity());
                break;
            default:
                break;
        }

    }
}
