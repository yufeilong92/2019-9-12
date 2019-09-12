package com.tsyc.tianshengyoucai.model.bean;

/**
 * author：van
 * CreateTime：2019/8/9
 * File description：收银台首页信息
 */
public class CashHomeInfoBean {


    /**
     * code : 200
     * result : {"cashier":{"id":7,"member_id":8,"store_id":10,"status":1,"create_time":"2019-07-04 16:49:27","update_time":"2019-07-04 16:49:27","store_name":"贝佳尼","door_photo":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019070311101842327.jpg"},"unsettled":0,"today_turnover":0,"month_turnover":0,"all_turnover":0}
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
        /**
         * cashier : {"id":7,"member_id":8,"store_id":10,"status":1,"create_time":"2019-07-04 16:49:27","update_time":"2019-07-04 16:49:27","store_name":"贝佳尼","door_photo":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019070311101842327.jpg"}
         * unsettled : 0
         * today_turnover : 0
         * month_turnover : 0
         * all_turnover : 0
         */

        private CashierBean cashier;
        private float unsettled;
        private float today_turnover;
        private float month_turnover;
        private float all_turnover;

        public CashierBean getCashier() {
            return cashier;
        }

        public void setCashier(CashierBean cashier) {
            this.cashier = cashier;
        }

        public float getUnsettled() {
            return unsettled;
        }

        public void setUnsettled(float unsettled) {
            this.unsettled = unsettled;
        }

        public float getToday_turnover() {
            return today_turnover;
        }

        public void setToday_turnover(float today_turnover) {
            this.today_turnover = today_turnover;
        }

        public float getMonth_turnover() {
            return month_turnover;
        }

        public void setMonth_turnover(float month_turnover) {
            this.month_turnover = month_turnover;
        }

        public float getAll_turnover() {
            return all_turnover;
        }

        public void setAll_turnover(float all_turnover) {
            this.all_turnover = all_turnover;
        }

        public static class CashierBean {
            /**
             * id : 7
             * member_id : 8
             * store_id : 10
             * status : 1
             * create_time : 2019-07-04 16:49:27
             * update_time : 2019-07-04 16:49:27
             * store_name : 贝佳尼
             * door_photo : http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019070311101842327.jpg
             */

            private int id;
            private int member_id;
            private int store_id;
            private int status;
            private String create_time;
            private String update_time;
            private String store_name;
            private String door_photo;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMember_id() {
                return member_id;
            }

            public void setMember_id(int member_id) {
                this.member_id = member_id;
            }

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getDoor_photo() {
                return door_photo;
            }

            public void setDoor_photo(String door_photo) {
                this.door_photo = door_photo;
            }
        }
    }
}
