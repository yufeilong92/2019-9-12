package com.tsyc.tianshengyoucai.model.bean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/31
 * File description：物流公司bean
 */
public class LogisticsCompanyBean {

    /**
     * code : 200
     * result : [{"id":29,"e_name":"顺丰快递","e_code":"shunfeng","e_letter":"S","e_order":"1","e_url":"http://www.sf-express.com","e_zt_state":0},{"id":40,"e_name":"圆通快递","e_code":"yuantong","e_letter":"Y","e_order":"1","e_url":"http://www.yto.net.cn","e_zt_state":0},{"id":41,"e_name":"韵达快递","e_code":"yunda","e_letter":"Y","e_order":"1","e_url":"http://www.yundaex.com","e_zt_state":0},{"id":44,"e_name":"中通快递","e_code":"zhongtong","e_letter":"Z","e_order":"1","e_url":"http://www.zto.cn","e_zt_state":0},{"id":2,"e_name":"包裹平邮","e_code":"youzhengguonei","e_letter":"B","e_order":"2","e_url":"http://yjcx.chinapost.com.cn","e_zt_state":0},{"id":7,"e_name":"德邦物流","e_code":"debangwuliu","e_letter":"D","e_order":"2","e_url":"http://www.deppon.com","e_zt_state":0},{"id":9,"e_name":"EMS国际","e_code":"emsguoji","e_letter":"E","e_order":"2","e_url":"###","e_zt_state":0},{"id":8,"e_name":"EMS","e_code":"ems","e_letter":"E","e_order":"2","e_url":"http://www.ems.com.cn","e_zt_state":0},{"id":15,"e_name":"共速达","e_code":"gongsuda","e_letter":"G","e_order":"2","e_url":"http://www.gongsuda.com/mall/Search.aspx","e_zt_state":0},{"id":14,"e_name":"挂号信","e_code":"youzhengguonei","e_letter":"G","e_order":"2","e_url":"http://yjcx.chinapost.com.cn","e_zt_state":0},{"id":16,"e_name":"汇通快递","e_code":"huitongkuaidi","e_letter":"H","e_order":"2","e_url":"http://www.htky365.com","e_zt_state":0},{"id":17,"e_name":"华宇物流","e_code":"tiandihuayu","e_letter":"H","e_order":"2","e_url":"http://www.hoau.net","e_zt_state":0},{"id":20,"e_name":"急先达","e_code":"jixianda","e_letter":"J","e_order":"2","e_url":"http://www.joust.cn","e_zt_state":0},{"id":21,"e_name":"快捷速递","e_code":"kuaijiesudi","e_letter":"K","e_order":"2","e_url":"http://www.fastexpress.com.cn","e_zt_state":0},{"id":22,"e_name":"龙邦快递","e_code":"longbanwuliu","e_letter":"L","e_order":"2","e_url":"http://www.lbex.com.cn","e_zt_state":0},{"id":23,"e_name":"联邦快递","e_code":"lianbangkuaidi","e_letter":"L","e_order":"2","e_url":"http://cndxp.apac.fedex.com/dxp.html","e_zt_state":0},{"id":25,"e_name":"全一快递","e_code":"quanyikuaidi","e_letter":"Q","e_order":"2","e_url":"http://www.apex100.com","e_zt_state":0},{"id":27,"e_name":"全日通","e_code":"quanritongkuaidi","e_letter":"Q","e_order":"2","e_url":"http://www.at-express.com","e_zt_state":0},{"id":26,"e_name":"全峰快递","e_code":"quanfengkuaidi","e_letter":"Q","e_order":"2","e_url":"http://www.qfkd.com.cn","e_zt_state":0},{"id":28,"e_name":"申通快递","e_code":"shentong","e_letter":"S","e_order":"2","e_url":"http://www.sto.cn","e_zt_state":0},{"id":32,"e_name":"天天快递","e_code":"tiantian","e_letter":"T","e_order":"2","e_url":"http://www.ttkdex.com","e_zt_state":0},{"id":42,"e_name":"邮政包裹","e_code":"youzhengguonei","e_letter":"Y","e_order":"2","e_url":"http://yjcx.chinapost.com.cn","e_zt_state":0},{"id":45,"e_name":"中铁快运","e_code":"zhongtiewuliu","e_letter":"Z","e_order":"2","e_url":"http://www.cre.cn","e_zt_state":0},{"id":46,"e_name":"宅急送","e_code":"zhaijisong","e_letter":"Z","e_order":"2","e_url":"http://www.zjs.com.cn","e_zt_state":0},{"id":47,"e_name":"中邮物流","e_code":"zhongyouwuliu","e_letter":"Z","e_order":"2","e_url":"http://www.cnpl.com.cn","e_zt_state":0}]
     * message :
     */

    private String code;
    private String message;
    private List<ResultBean> result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 29
         * e_name : 顺丰快递
         * e_code : shunfeng
         * e_letter : S
         * e_order : 1
         * e_url : http://www.sf-express.com
         * e_zt_state : 0
         */

        private int id;
        private String e_name;
        private String e_code;
        private String e_letter;
        private String e_order;
        private String e_url;
        private int e_zt_state;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getE_name() {
            return e_name;
        }

        public void setE_name(String e_name) {
            this.e_name = e_name;
        }

        public String getE_code() {
            return e_code;
        }

        public void setE_code(String e_code) {
            this.e_code = e_code;
        }

        public String getE_letter() {
            return e_letter;
        }

        public void setE_letter(String e_letter) {
            this.e_letter = e_letter;
        }

        public String getE_order() {
            return e_order;
        }

        public void setE_order(String e_order) {
            this.e_order = e_order;
        }

        public String getE_url() {
            return e_url;
        }

        public void setE_url(String e_url) {
            this.e_url = e_url;
        }

        public int getE_zt_state() {
            return e_zt_state;
        }

        public void setE_zt_state(int e_zt_state) {
            this.e_zt_state = e_zt_state;
        }
    }
}
