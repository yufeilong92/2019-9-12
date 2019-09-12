package com.tsyc.tianshengyoucai.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.chrisbanes.photoview.PhotoView;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.zhihu.matisse.listener.OnFragmentInteractionListener;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/16 11:57:
 * @Purpose :图片查看其
 */
public class ImagerFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private PhotoView mPhotoView;

    public ImagerFragment() {
    }


    public static ImagerFragment newInstance(String param1, String param2) {
        ImagerFragment fragment = new ImagerFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_imager, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mPhotoView = (PhotoView) view.findViewById(R.id.photo_view);
        GlideUtil.getSingleton().loadQuadRangleImager(getContext(), mPhotoView, mParam2);
    }
}
