package com.tsyc.tianshengyoucai.manager;


import android.app.Activity;

import java.util.Stack;

/**
 * author：YL
 * CreateTime：2019/6/19
 * File description：
 */
public class ActivityManager {
    private static ActivityManager instance;
    private Stack<Activity> activityStack;// activity栈

    // 单例模式
    public static ActivityManager getInstance() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    // 把一个activity压入栈中
    public void pushOneActivity(Activity actvity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(actvity);
    }

    // 获取栈顶的activity，先进后出原则
    public Activity getLastActivity() {
        return activityStack.lastElement();
    }

    // 移除一个activity
    public void popOneActivity(Class<?> activity) {
        if (activityStack != null && activityStack.size() > 0) {
            if (activity != null) {
                for (int i = 0; i < activityStack.size(); i++) {
                    Activity activity1 = activityStack.get(i);
                    if (activity.equals(activity1.getClass())) {
                        activityStack.remove(i);
                        activity = null;
                    }
                }
            }

        }
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    // 退出所有activity
    public void finishAllActivity() {
        if (activityStack != null && !activityStack.isEmpty()) {
            for (int i = 0; i < activityStack.size(); i++) {
                Activity activity = activityStack.get(i);
                if (activity.isFinishing()) {
                    activity.finish();
                }
            }
        }

    }
}