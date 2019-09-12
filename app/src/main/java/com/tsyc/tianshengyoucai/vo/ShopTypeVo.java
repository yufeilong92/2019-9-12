package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/10 15:19
 * @Purpose :更多商城分类
 */
public class ShopTypeVo extends NormalBean {

    /**
     * result : {"all_type":[{"sc_bail":0,"sc_icon":"http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-1.jpg","sc_id":1,"sc_name":"美食类","sc_sort":200},{"sc_bail":0,"sc_icon":"http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-2.jpg","sc_id":2,"sc_name":"服装类","sc_sort":200},{"sc_bail":0,"sc_icon":"http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-3.jpg","sc_id":3,"sc_name":"美妆类","sc_sort":200},{"sc_bail":0,"sc_icon":"http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-4.jpg","sc_id":4,"sc_name":"生活类","sc_sort":200},{"sc_bail":0,"sc_icon":"http://tsyc.jiefutong.net/uploads/home/store/class/store_class-pic-5.jpg","sc_id":5,"sc_name":"服务类","sc_sort":200}],"sort":["智能排序","离我最近","好评优化","销量最好"]}
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<AllTypeBeanVo> all_type;
        private List<String> sort;

        public List<AllTypeBeanVo> getAll_type() {
            return all_type;
        }

        public void setAll_type(List<AllTypeBeanVo> all_type) {
            this.all_type = all_type;
        }

        public List<String> getSort() {
            return sort;
        }

        public void setSort(List<String> sort) {
            this.sort = sort;
        }


    }
}
