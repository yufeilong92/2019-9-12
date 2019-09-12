package com.tsyc.tianshengyoucai.model.bean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/12
 * File description： 地图红包bean
 */
public class MapBagBean {

    /**
     * code : 200
     * result : [{"store_name":"贝佳尼","store_id":10,"lng":"113.717521","lat":"34.918328","distance":20.009143242887195},{"store_name":"豆豆","store_id":12,"lng":"113.717521","lat":"34.745756","distance":1.8792695547514207}]
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
         * store_name : 贝佳尼
         * store_id : 10
         * lng : 113.717521
         * lat : 34.918328
         * distance : 20.009143242887195
         */

        private String store_name;
        private int store_id;
        private double lng;
        private double lat;
        private double distance;

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }
    }
}
