package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/3 16:24
 * @Purpose :
 */
public class BossJobTitleVo  extends NormalBean {

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * default_position_name : PHP
         * id : 9
         * position_name : PHP实习
         */

        private String default_position_name;
        private int id;
        private String position_name;

        public String getDefault_position_name() {
            return default_position_name;
        }

        public void setDefault_position_name(String default_position_name) {
            this.default_position_name = default_position_name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPosition_name() {
            return position_name;
        }

        public void setPosition_name(String position_name) {
            this.position_name = position_name;
        }
    }
}
