package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/9 17:53
 * @Purpose :
 */
public class GmImagerVo extends NormalBean {


    /**
     * result : {"img_url":"http://tsyc.jiefutong.net/uploads/home/membertag/201908/18_2019080917522489672.jpg"}
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
         * img_url : http://tsyc.jiefutong.net/uploads/home/membertag/201908/18_2019080917522489672.jpg
         */

        private String img_url;

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }
    }
}
