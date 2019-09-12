package com.tsyc.tianshengyoucai.fragment.recruite.boss;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.tsyc.tianshengyoucai.Eventbuss.RefreshHomeEvent;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.PageBuleFragmentAdapter;
import com.tsyc.tianshengyoucai.base.Base2Fragment;
import com.tsyc.tianshengyoucai.requet.RequestBossHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.ui.fragment.selectobserver.SelectSubject;
import com.tsyc.tianshengyoucai.ui.recruit.boss.BossHomeActivity;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.Util;
import com.tsyc.tianshengyoucai.vo.BossJobTitleVo;
import com.tsyc.tianshengyoucai.vo.SelectVo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/9/3 16:08:
 * @Purpose :boss职位
 */
public class BossJobFragment extends Base2Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private ImageView mIvBossHomeSearch;
    private ImageView mIvBossHomeDelete;

    private SelectSubject mSubject;
    private TabLayout mTabBossHomeTitle;
    private ViewPager mViewPageBossHomeContent;

    public static BossJobFragment newInstance(String param1, String param2) {
        BossJobFragment fragment = new BossJobFragment();
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
//        View view = inflater.inflate(R.layout.fragment_boss_job, container, false);
//        initView(view);
//        return view;
//    }

    @Override
    protected int getFramgentViewId() {
        return R.layout.fragment_boss_job;
    }

    @Override
    protected void initContentView(View view, Bundle savedInstanceState) {
        initView(view);
        initEvent();
        initRequest();
    }


    private void initRequest() {
        showProgress();
        RequestBossHttp.getSingleton(getActivity()).requestPositions(new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) {
                    return;
                }
                BossJobTitleVo mVo = GsonUtils.getGson(success, BossJobTitleVo.class);
                List<BossJobTitleVo.ResultBean> mTablelist = mVo.getResult();
                bindViewData(mTablelist);

            }

            @Override
            public void error(String error) {
                onError();
            }
        });

    }


    private void bindViewData(List<BossJobTitleVo.ResultBean> list) {
        if (list == null || list.isEmpty()) return;
        List<String> mTitls = new ArrayList<>();
        List<Fragment> mFragments = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            BossJobTitleVo.ResultBean bean = list.get(i);
            mTitls.add(bean.getPosition_name());
            BossHomeJobFragment fragment = BossHomeJobFragment.newInstance("", String.valueOf(bean.getId()));
            mSubject.attach(fragment);
            mFragments.add(fragment);
        }
        if (mTitls.isEmpty()) return;
        PageBuleFragmentAdapter adapter = new PageBuleFragmentAdapter(mContext, getFragmentManager(), mFragments, mTitls);
        mViewPageBossHomeContent.setAdapter(adapter);
        mTabBossHomeTitle.setupWithViewPager(mViewPageBossHomeContent);
        mTabBossHomeTitle.setTabMode(TabLayout.MODE_SCROLLABLE);
        Util.setBuleTablayoutCouncis(mContext, adapter, mTabBossHomeTitle);

    }

    private void initEvent() {
        mIvBossHomeSearch.setOnClickListener(v -> {
            mResultTo.startHomeSearchType(getActivity(), BossHomeActivity.SELECT_REQUESTCODE);
        });
        mSubject = new SelectSubject();
        mIvBossHomeDelete.setOnClickListener(v -> {
            mResultTo.startBossOtherJob(getActivity(), 1);
        });

    }

    public void notifyType(List<SelectVo> mEduList, List<SelectVo> mExpList, List<SelectVo> mMoneyList, List<SelectVo> mStatusList) {
        mSubject.notifyAllChange(mEduList, mExpList, mMoneyList, mStatusList);
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
    public void refreshEvent(RefreshHomeEvent event) {
        initRequest();
    }

    private void initView(View view) {
        mIvBossHomeSearch = (ImageView) view.findViewById(R.id.iv_boss_home_search);
        mIvBossHomeDelete = (ImageView) view.findViewById(R.id.iv_boss_home_delete);
        mTabBossHomeTitle = (TabLayout) view.findViewById(R.id.tab_boss_home_title);
        mViewPageBossHomeContent = (ViewPager) view.findViewById(R.id.view_page_boss_home_content);
    }

}
