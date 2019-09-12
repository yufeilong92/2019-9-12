package com.tsyc.tianshengyoucai.model.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/22
 * File description：新闻资讯bean
 */
public class ShopNewsListBean {

    /**
     * code : 200
     * result : [{"article_title":"每日一句早安问候语","article_id":6,"article_reading_vol":0,"article_thumb":["/ueditor/php/upload/thumb/20190709/9e29614fa0a6eeed6a7ea20bac39123e.jpg","/ueditor/php/upload/thumb/20190709/dd2571a50aec0df07928da9c1b403f2e.jpg","/ueditor/php/upload/thumb/20190709/553511bdb64821eb8b1f338c08df18f7.jpg"]}]
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

    public static class ResultBean implements MultiItemEntity {
        /**
         * article_title : 每日一句早安问候语
         * article_id : 6
         * article_reading_vol : 0
         * article_thumb : ["/ueditor/php/upload/thumb/20190709/9e29614fa0a6eeed6a7ea20bac39123e.jpg","/ueditor/php/upload/thumb/20190709/dd2571a50aec0df07928da9c1b403f2e.jpg","/ueditor/php/upload/thumb/20190709/553511bdb64821eb8b1f338c08df18f7.jpg"]
         * itemType
         */

        private String article_title;
        private int article_id;
        private int article_reading_vol;
        private List<String> article_thumb;
        private int itemType;
        public String getArticle_title() {
            return article_title;
        }

        public void setArticle_title(String article_title) {
            this.article_title = article_title;
        }

        public int getArticle_id() {
            return article_id;
        }

        public void setArticle_id(int article_id) {
            this.article_id = article_id;
        }

        public int getArticle_reading_vol() {
            return article_reading_vol;
        }

        public void setArticle_reading_vol(int article_reading_vol) {
            this.article_reading_vol = article_reading_vol;
        }

        public List<String> getArticle_thumb() {
            return article_thumb;
        }

        public void setArticle_thumb(List<String> article_thumb) {
            this.article_thumb = article_thumb;
        }


        public void setItemType(int itemType) {
            this.itemType = itemType;
        }

        @Override
        public int getItemType() {
            return itemType;
        }
    }
}
