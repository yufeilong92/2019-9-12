package com.tsyc.tianshengyoucai.utils;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/8 17:11
 * @Purpose :
 */
public class RecyclerUtil {
    public static void setGridManage(Context m,RecyclerView rlv){
        GridLayoutManager manager = new GridLayoutManager(m, 1);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        rlv.setLayoutManager(manager);
    }
    public static void setGridManage(Context m,RecyclerView rlv,int lin){
        GridLayoutManager manager = new GridLayoutManager(m, lin);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        rlv.setLayoutManager(manager);
    }
    public static void setGridManageH(Context m,RecyclerView rlv){
        GridLayoutManager manager = new GridLayoutManager(m, 1);
        manager.setOrientation(GridLayoutManager.HORIZONTAL);
        rlv.setLayoutManager(manager);
    }
    public static void setGridManage(Context m,RecyclerView rlv,RecyclerView.Adapter adapter){
        GridLayoutManager manager = new GridLayoutManager(m, 1);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        rlv.setLayoutManager(manager);
        rlv.setAdapter(adapter);
    }
    public static void setGridManageTwo(Context m,RecyclerView rlv,RecyclerView.Adapter adapter){
        GridLayoutManager manager = new GridLayoutManager(m, 1);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        rlv.setLayoutManager(manager);
        rlv.setAdapter(adapter);
    }
    public static void setGridManage(Context m,RecyclerView rlv,int lin,RecyclerView.Adapter adapter){
        GridLayoutManager manager = new GridLayoutManager(m, lin);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        rlv.setLayoutManager(manager);
        rlv.setAdapter(adapter);
    }
    /**
     * 滑动到指定位置
     */
    public static void smoothMoveToPosition(final RecyclerView mRecyclerView, final int position) {
        // 第一个可见位置
        int firstItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0));
        // 最后一个可见位置
        int lastItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1));
        if (position < firstItem) {
            // 第一种可能:跳转位置在第一个可见位置之前
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    mRecyclerView.smoothScrollToPosition(position);
                }
            }, 300);
        } else if (position <= lastItem) {
            // 第二种可能:跳转位置在第一个可见位置之后
            int movePosition = position - firstItem;
            if (movePosition >= 0 && movePosition < mRecyclerView.getChildCount()) {
                final int top = mRecyclerView.getChildAt(movePosition).getTop();
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        mRecyclerView.smoothScrollBy(0, top, new AccelerateDecelerateInterpolator());
                    }
                }, 300);
            }
        } else {
            // 第三种可能:跳转位置在最后可见项之后
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    mRecyclerView.smoothScrollToPosition(position);
                }
            }, 300);

        }
    }
}
