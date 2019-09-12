package com.tsyc.tianshengyoucai.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.tsyc.tianshengyoucai.Eventbuss.TypeContentEvent;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.type.GoodRightAdapter;
import com.tsyc.tianshengyoucai.ui.base.BaseFragment;
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.vo.GoodTypeVo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/12 17:06:
 * @Purpose :分类item
 */
public class TypeItemFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private ImageView mViTypePicture;
    private RecyclerView mRlvTypeContent;
    private ArrayList mArray;
    private GoodRightAdapter mAdapter;


    public static TypeItemFragment newInstance(String param1, String param2) {
        TypeItemFragment fragment = new TypeItemFragment();
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
//        View inflate = inflater.inflate(R.layout.fragment_type_item, container, false);
//        initView(inflate);
//        return inflate;
//    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_type_item;
    }

    @Override
    protected void loadData() {
        initEvent();
        clearData();
        initAdapter();
    }

    private void initAdapter() {
        RecyclerUtil.setGridManage(mActivity, mRlvTypeContent);
        mAdapter = new GoodRightAdapter(mContext, mArray, "");
        mRlvTypeContent.setAdapter(mAdapter);
        mAdapter.setListener(new GoodRightAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, GoodTypeVo.ResultBean bean) {
                mResultTo.startTypeList(getActivity(),bean);
            }
        });
    }

    private void initEvent() {

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void dataRefresh(TypeContentEvent event) {
        bindDataView(event.getVo());
    }

    private void bindDataView(GoodTypeVo.ResultBean event) {
        clearData();
        if (StringUtil.isEmpty(event.getAdv())){
            mViTypePicture.setVisibility(View.GONE);
        }else {
            mViTypePicture.setVisibility(View.VISIBLE);
        }
        GlideUtil.getSingleton().loadQuadRangleImager(mContext, mViTypePicture, event.getAdv());
        mAdapter.dataRefresh(event.getGc_name());
        addData(event.getChild());
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void initView(View inflate) {
        mViTypePicture = (ImageView) inflate.findViewById(R.id.vi_type_picture);
        mRlvTypeContent = (RecyclerView) inflate.findViewById(R.id.rlv_type_content);
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
}
