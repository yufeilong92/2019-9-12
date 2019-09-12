package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/9 14:16
 * @Purpose :
 */
public class AddressListVo extends NormalBean {

    /**
     * result : {"address_list":[{"address":"哦哦","address_id":33,"area_id":37,"area_info":"北京市 北京市 东城区","city_id":1,"dlyp_id":0,"is_default":"0","member_id":18,"mob_phone":"18317837561","province_id":1,"tel_phone":"","true_name":"通许县"},{"address":"看看咯","address_id":32,"area_id":37,"area_info":"北京市 北京市 东城区","city_id":1,"dlyp_id":0,"is_default":"0","member_id":18,"mob_phone":"1831785","province_id":1,"tel_phone":"","true_name":"看见了"},{"address":"您名","address_id":26,"area_id":37,"area_info":"北京市 北京市 东城区","city_id":1,"dlyp_id":0,"is_default":"1","member_id":18,"mob_phone":"18916977773","province_id":1,"tel_phone":"","true_name":"鱼涛"}]}
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<AddressListBeanVo> address_list;

        public List<AddressListBeanVo> getAddress_list() {
            return address_list;
        }

        public void setAddress_list(List<AddressListBeanVo> address_list) {
            this.address_list = address_list;
        }

    }
}
