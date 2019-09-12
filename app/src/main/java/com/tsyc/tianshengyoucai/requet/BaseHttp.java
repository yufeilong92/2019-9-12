package com.tsyc.tianshengyoucai.requet;

import com.tsyc.tianshengyoucai.vo.ParamGET;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/15 11:45
 * @Purpose :
 */
public class BaseHttp {
    protected ParamGET getNewParamt() {

        return new ParamGET();
    }
    protected List<ParamGET>  getListParamt() {
        return new ArrayList<>();
    }
}
