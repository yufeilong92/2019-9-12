package com.tsyc.tianshengyoucai.ui.fragment.selectobserver;

import com.tsyc.tianshengyoucai.vo.SelectVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/6 14:11
 * @Purpose :
 */
public class SelectSubject {

    private List<ChangerObserver> observers = new ArrayList<>();

    public void attach(ChangerObserver observer) {
        observers.add(observer);
    }

    public void notifyAllChange(List<SelectVo> mEduList, List<SelectVo> mExpList, List<SelectVo> mMoneyList, List<SelectVo> mStatusList) {
        if (observers == null || observers.isEmpty()) return;
        for (int i = 0; i < observers.size(); i++) {
            ChangerObserver observer = observers.get(i);
            observer.updateData(mEduList,mExpList,mMoneyList,mStatusList);
        }
    }
}
