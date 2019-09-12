package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/10 16:47
 * @Purpose :
 */
public class ShopListVo extends NormalBean {


    /**
     * result : {"list":[{"other":{"is_online_offline":"支持线上线下","is_voucher":""},"sc_id":0,"share_commission":"","store_avatar":"店铺头像","store_credit":0,"store_id":1,"store_name":"测试店铺","area_info":"河南 郑州市 上街区 ","store_address":"aaaaa","store_zy":""},{"area_info":"河南 郑州市 上街区 ","other":{"is_online_offline":"","is_voucher":""},"sc_id":1,"share_commission":"分享消费可获得20元佣金","store_address":"aaaaa","store_avatar":"店铺头像","store_credit":0,"store_id":2,"store_name":"aaa","store_zy":""},{"area_info":"省 市 区","other":{"is_online_offline":"支持线上线下","is_voucher":"红包活动"},"sc_id":0,"share_commission":"分享消费可获得20元佣金","store_address":"商家地址","store_avatar":"店铺头像","store_credit":0,"store_id":4,"store_name":"商家名称","store_zy":"美食 "},{"area_info":"北京 北京市 北京市","other":{"is_online_offline":"","is_voucher":""},"sc_id":1,"share_commission":"","store_avatar":"店铺头像","store_credit":0,"store_id":7,"store_name":"测试","store_zy":"男装,女装,童装"},{"area_info":"北京 北京市 北京市","other":{"is_online_offline":"","is_voucher":""},"sc_id":0,"share_commission":"分享消费可获得20元佣金","store_avatar":"店铺头像","store_credit":0,"store_id":8,"store_name":"6666","store_zy":"主营"},{"area_info":"北京市 北京市 东城区","other":{"is_online_offline":"支持线上线下","is_voucher":"红包活动"},"sc_id":0,"share_commission":"","store_address":"详细地址修改","store_avatar":"店铺头像","store_credit":0,"store_id":9,"store_name":"CC","store_zy":"AVAV"},{"area_info":"16,240,2662","other":{"is_online_offline":"支持线上线下","is_voucher":"红包活动"},"sc_id":0,"share_commission":"分享消费可获得20元佣金","store_address":"陇海路321号","store_avatar":"店铺头像","store_credit":0,"store_id":10,"store_name":"贝佳尼","store_zy":"美容"},{"area_info":"北京市 北京市 东城区","other":{"is_online_offline":"","is_voucher":"红包活动"},"sc_id":0,"share_commission":"分享消费可获得20元佣金","store_address":"Dadadawdwvardsvard","store_avatar":"店铺头像","store_credit":0,"store_id":12,"store_name":"豆豆","store_zy":"美妆"},{"area_info":"河南省 郑州市 二七区","other":{"is_online_offline":"支持线上线下","is_voucher":"红包活动"},"sc_id":0,"share_commission":"","store_address":"绿地滨湖国际城一区二号楼","store_avatar":"店铺头像","store_credit":0,"store_id":13,"store_name":"哈哈","store_zy":"休闲零食"},{"area_info":"河南省 郑州市 二七区","other":{"is_online_offline":"支持线上线下","is_voucher":"红包活动"},"sc_id":0,"share_commission":"分享消费可获得20元佣金","store_address":"大学路南三环","store_avatar":"店铺头像","store_credit":0,"store_id":14,"store_name":"平价酒水仓储批发","store_zy":"生活"}]}
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<ShopVo> list;

        public List<ShopVo> getList() {
            return list;
        }

        public void setList(List<ShopVo> list) {
            this.list = list;
        }


    }
}
