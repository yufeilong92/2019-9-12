package com.tsyc.tianshengyoucai.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.Eventbuss.TypeContentEvent;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.type.GoodLiftTypeAdapter;
import com.tsyc.tianshengyoucai.fragment.TypeItemFragment;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.requet.RequestSettingHttp;
import com.tsyc.tianshengyoucai.ui.base.BaseFragment;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.GoodTypeVo;
import com.tsyc.tianshengyoucai.vo.SelectVo;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/12 16:00:
 * @Purpose :分类
 */
public class TypeFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private TextView mGmTvTitle;
    private RecyclerView mRlvTypeTitle;
    private FrameLayout mFlTypeContent;
    private List<GoodTypeVo.ResultBean> mArray;
    private List<SelectVo> mSelectDatas;
    private GoodLiftTypeAdapter mAdapter;
    private List<Fragment> mFragmentList;
    private ImageView mGmIvBack;
    private LinearLayout mRoolView;

    public static TypeFragment newInstance(String param1, String param2) {
        TypeFragment fragment = new TypeFragment();
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
//        View view = inflater.inflate(R.layout.fragment_type, container, false);
//        initView(view);
//        return view;
//    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_type;
    }

    @Override
    protected void loadData() {
        mGmTvTitle.setText("分类");
        clearData();
        mSelectDatas = new ArrayList<>();
        initEvent();
        initAdapter();
        initRequest();
    }

    private void initRequest() {
        showProgress();
        RequestSettingHttp.getSingleton(mActivity).requestHomeTypeList(new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                NormalBean vos = GsonUtils.getGson(success, NormalBean.class);
                if (vos.getCode().equals("100")) {
                    T.showToast(mContext, vos.getMessage());
                    return;
                }
                GoodTypeVo vo = GsonUtils.getGson(success, GoodTypeVo.class);

                addData(vo.getResult());
                mFragmentList = new ArrayList<>();
                FragmentManager fm = getFragmentManager();
                for (int i = 0; i < vo.getResult().size(); i++) {
                    TypeItemFragment fragment = TypeItemFragment.newInstance("", "");
                    mFragmentList.add(fragment);
                }
                for (int i = 0; i < mFragmentList.size(); i++) {

                    fm.beginTransaction().add(R.id.fl_type_content, mFragmentList.get(i)).
                            hide(mFragmentList.get(i)).commit();
                }
                fm.beginTransaction().show(mFragmentList.get(0)).commit();
                mSelectDatas = initSelectVo("");
                mAdapter.dataRefresh(mSelectDatas);
                mAdapter.notifyDataSetChanged();
                EventBus.getDefault().postSticky(new TypeContentEvent(vo.getResult().get(0)));
//                showFragment(0, vo.getResult().get(0));
            }

            @Override
            public void error(String error) {
                dismissProgress();
                toastErrorNet();
            }
        });
    }

    private void initAdapter() {
        RecyclerUtil.setGridManage(mActivity, mRlvTypeTitle);
        mAdapter = new GoodLiftTypeAdapter(mActivity, mArray, mSelectDatas);
        mRlvTypeTitle.setAdapter(mAdapter);
        mAdapter.setListener(new GoodLiftTypeAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, GoodTypeVo.ResultBean vo) {
                List<SelectVo> vos = initSelectVo(vo.getGc_name());
                mAdapter.dataRefresh(vos);
//                showFragment(position, vo);
                EventBus.getDefault().postSticky(new TypeContentEvent(vo));
                showSelectFragment(getFragmentManager(), mFragmentList, R.id.fl_type_content, position);

            }
        });
    }

    private void showFragment(int position, GoodTypeVo.ResultBean vo) {
        TypeItemFragment fragment = TypeItemFragment.newInstance("", "");
        EventBus.getDefault().postSticky(new TypeContentEvent(vo));
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fl_type_content, fragment);
        beginTransaction.commit();
    }

    private void initEvent() {

    }

    private List<SelectVo> initSelectVo(String name) {
        List<SelectVo> selectVos = new ArrayList<>();
        if (mArray == null || mArray.isEmpty()) return selectVos;
        if (StringUtil.isEmpty(name)) {
            for (int i = 0; i < mArray.size(); i++) {
                GoodTypeVo.ResultBean bean = mArray.get(i);
                SelectVo vo = new SelectVo();
                if (i == 0) {
                    vo.setSelect(true);
                } else {
                    vo.setSelect(false);
                }
                vo.setName(bean.getGc_name());
                selectVos.add(vo);
            }
        } else {
            for (int i = 0; i < mArray.size(); i++) {
                GoodTypeVo.ResultBean bean = mArray.get(i);
                SelectVo vo = new SelectVo();
                vo.setSelect(StringUtil.isEquest(name, bean.getGc_name()));
                vo.setName(bean.getGc_name());
                selectVos.add(vo);
            }
        }
        return selectVos;
    }

    @Override
    public void initView(View view) {
        mGmTvTitle = (TextView) view.findViewById(R.id.gm_tv_title);
        mGmIvBack = (ImageView) view.findViewById(R.id.gm_iv_back);
        mGmIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResultTo.finishBase(mContext           );
            }
        });
        mRlvTypeTitle = (RecyclerView) view.findViewById(R.id.rlv_type_title);
        mFlTypeContent = (FrameLayout) view.findViewById(R.id.fl_type_content);

        mRoolView = (LinearLayout) view.findViewById(R.id.rool_view);
        mRoolView.setOnClickListener(this);
    }

    private void clearData() {
        if (mArray == null) {
            mArray = new ArrayList();
        } else {
            mArray.clear();
        }
    }

    private void addData(List<GoodTypeVo.ResultBean> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        if (mArray == null) {
            clearData();
        }
        mArray.addAll(list);
    }

    /**
     * @param sfm    fragment管理器
     * @param list   fragment集合
     * @param layout 显示fragment 布局
     * @param id     要显示的集合的fragment 的几个
     * @return
     */
    private void showSelectFragment(FragmentManager sfm, List<Fragment> list, int layout, int id) {
        if (id + 1 > list.size()) {
            throw new IndexOutOfBoundsException("超出集合长度");
        }
        if (sfm == null) {
            throw new NullPointerException("FragmentManager不能为空");
        }
        FragmentTransaction beginTransaction = sfm.beginTransaction();
        Fragment fragment = list.get(id);
        if (!fragment.isVisible()) {
            if (!fragment.isAdded()) {
                beginTransaction.add(layout, fragment, fragment.getClass().getName());
            } else {
                for (int i = 0; i < list.size(); i++) {
                    sfm.beginTransaction().hide(list.get(i)).commit();

                }

                beginTransaction.show(fragment);
            }
        }
        beginTransaction.commit();
    }
}
