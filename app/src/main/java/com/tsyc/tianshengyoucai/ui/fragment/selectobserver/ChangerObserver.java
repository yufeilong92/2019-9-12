package com.tsyc.tianshengyoucai.ui.fragment.selectobserver;

import com.tsyc.tianshengyoucai.vo.SelectVo;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/6 14:12
 * @Purpose :
 */
public interface ChangerObserver {
    public abstract void updateData(List<SelectVo> mEduList, List<SelectVo> mExpList, List<SelectVo> mMoneyList, List<SelectVo> mStatusList);
}
