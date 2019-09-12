package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/12 14:25
 * @Purpose :
 */
public class HomeMsgVo extends NormalBean {

    /**
     * result : {"current_page":1,"data":[{"content":"系统上线测试.....000","create_time":"2019-06-25 11:14:21","flag":1,"id":2,"is_home_show":0,"is_read":0,"member_id":0,"relation_id":0,"title":"系统上线","update_time":"2019-07-08 16:12:36"}],"last_page":1,"per_page":15,"total":1}
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * current_page : 1
         * data : [{"content":"系统上线测试.....000","create_time":"2019-06-25 11:14:21","flag":1,"id":2,"is_home_show":0,"is_read":0,"member_id":0,"relation_id":0,"title":"系统上线","update_time":"2019-07-08 16:12:36"}]
         * last_page : 1
         * per_page : 15
         * total : 1
         */

        private int current_page;
        private int last_page;
        private int per_page;
        private int total;
        private List<HomeDataBeanVo> data;

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<HomeDataBeanVo> getData() {
            return data;
        }

        public void setData(List<HomeDataBeanVo> data) {
            this.data = data;
        }


    }
}
