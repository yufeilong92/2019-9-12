package com.tsyc.tianshengyoucai.model.bean;

/**
 * author：van
 * CreateTime：2019/8/19
 * File description：付款码信息bean
 */
public class ScanCashierInfoBean {

    /**
     * code : 200
     * result : {"cashier":{"id":18,"member_id":35,"store_id":24,"status":1,"create_time":"2019-08-16 21:15:49","update_time":"2019-08-16 21:15:49","store_name":"背包客","door_photo":"http://tsyc.jiefutong.net/uploads/home/membertag/201908/35_2019081610564040867.jpg"},"money":"50","member_credit":"1578.00"}
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
         * cashier : {"id":18,"member_id":35,"store_id":24,"status":1,"create_time":"2019-08-16 21:15:49","update_time":"2019-08-16 21:15:49","store_name":"背包客","door_photo":"http://tsyc.jiefutong.net/uploads/home/membertag/201908/35_2019081610564040867.jpg"}
         * money : 50
         * member_credit : 1578.00
         */

        private CashierBean cashier;
        private String money;
        private String member_credit;

        public CashierBean getCashier() {
            return cashier;
        }

        public void setCashier(CashierBean cashier) {
            this.cashier = cashier;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getMember_credit() {
            return member_credit;
        }

        public void setMember_credit(String member_credit) {
            this.member_credit = member_credit;
        }

        public static class CashierBean {
            /**
             * id : 18
             * member_id : 35
             * store_id : 24
             * status : 1
             * create_time : 2019-08-16 21:15:49
             * update_time : 2019-08-16 21:15:49
             * store_name : 背包客
             * door_photo : http://tsyc.jiefutong.net/uploads/home/membertag/201908/35_2019081610564040867.jpg
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
