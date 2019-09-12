package com.tsyc.tianshengyoucai.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/28
 * File description： 商品模板 bean
 */
public class GoodsTemplateBean {

    //{"code":"200","result":{"templete":[{"name":"品牌","type":"chs","require":0},{"name":"证书","type":"number","require":"1"}]},"message":""}
    /**
     * code : 200
     * result : {"templete":[{"name":"品牌","type":"chs","require":0},{"name":"证书","type":"number","require":"1"}]}
     * message :
     */

    private String code;
    private ResultBean result;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class ResultBean {
        private List<TempleteBean> templete;

        public List<TempleteBean> getTemplete() {
            return templete;
        }

        public void setTemplete(List<TempleteBean> templete) {
            this.templete = templete;
        }

        public static class TempleteBean implements Serializable {
            /**
             * name : 品牌
             * type : chs
             * require : 0
             * type_desc : “”
             */

            private String name;
            private String type;
            private String type_desc;
            private int require;
            private String value;

            public String getType_desc() {
                return type_desc;
            }

            public void setType_desc(String type_desc) {
                this.type_desc = type_desc;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getRequire() {
                return require;
            }

            public void setRequire(int require) {
                this.require = require;
            }
        }
    }


}
