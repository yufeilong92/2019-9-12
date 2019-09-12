package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/7 16:26
 * @Purpose :
 */
public class ChatVo extends NormalBean {

    /**
     * result : {"logs":{"current_page":1,"data":[{"content":"的","create_time":"2019-09-05 10:20:38","from_side":2,"id":167,"is_read":1,"member_id":0,"record_id":2,"type":1,"update_time":"2019-09-05 10:20:38"},{"content":"的","create_time":"2019-09-05 10:20:10","from_side":2,"id":166,"is_read":1,"member_id":0,"record_id":2,"type":1,"update_time":"2019-09-05 10:20:10"},{"content":"的的","create_time":"2019-09-05 10:20:09","from_side":2,"id":165,"is_read":1,"member_id":0,"record_id":2,"type":1,"update_time":"2019-09-05 10:20:09"},{"content":"的","create_time":"2019-09-05 10:20:05","from_side":2,"id":164,"is_read":1,"member_id":0,"record_id":2,"type":1,"update_time":"2019-09-05 10:20:05"},{"content":"功夫贷染发膏 联发科了适度反抗 快十点了荆防颗粒是 六点付款时","create_time":"2019-09-03 18:23:00","from_side":2,"id":163,"is_read":1,"member_id":0,"record_id":2,"type":1,"update_time":"2019-09-03 18:23:00"},{"content":"Xjie","create_time":"2019-09-03 17:55:56","from_side":2,"id":162,"is_read":1,"member_id":0,"record_id":2,"type":1,"update_time":"2019-09-03 17:55:56"},{"content":"23","create_time":"2019-09-03 17:51:01","from_side":2,"id":161,"is_read":1,"member_id":0,"record_id":2,"type":1,"update_time":"2019-09-03 17:51:01"},{"content":"大师","create_time":"2019-09-03 17:50:26","from_side":2,"id":160,"is_read":1,"member_id":0,"record_id":2,"type":1,"update_time":"2019-09-03 17:50:26"}],"last_page":1,"per_page":15,"total":8},"member":{"member_avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201909/8_2019090410334838908.jpg","member_id":8,"member_name":"wei"},"record_id":2,"service":{"service_avatar":"http://wwww.jiefutong.net/uploads/mobile/category/logo_logo.png","service_name":"X街客服"}}
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
         * logs : {"current_page":1,"data":[{"content":"的","create_time":"2019-09-05 10:20:38","from_side":2,"id":167,"is_read":1,"member_id":0,"record_id":2,"type":1,"update_time":"2019-09-05 10:20:38"},{"content":"的","create_time":"2019-09-05 10:20:10","from_side":2,"id":166,"is_read":1,"member_id":0,"record_id":2,"type":1,"update_time":"2019-09-05 10:20:10"},{"content":"的的","create_time":"2019-09-05 10:20:09","from_side":2,"id":165,"is_read":1,"member_id":0,"record_id":2,"type":1,"update_time":"2019-09-05 10:20:09"},{"content":"的","create_time":"2019-09-05 10:20:05","from_side":2,"id":164,"is_read":1,"member_id":0,"record_id":2,"type":1,"update_time":"2019-09-05 10:20:05"},{"content":"功夫贷染发膏 联发科了适度反抗 快十点了荆防颗粒是 六点付款时","create_time":"2019-09-03 18:23:00","from_side":2,"id":163,"is_read":1,"member_id":0,"record_id":2,"type":1,"update_time":"2019-09-03 18:23:00"},{"content":"Xjie","create_time":"2019-09-03 17:55:56","from_side":2,"id":162,"is_read":1,"member_id":0,"record_id":2,"type":1,"update_time":"2019-09-03 17:55:56"},{"content":"23","create_time":"2019-09-03 17:51:01","from_side":2,"id":161,"is_read":1,"member_id":0,"record_id":2,"type":1,"update_time":"2019-09-03 17:51:01"},{"content":"大师","create_time":"2019-09-03 17:50:26","from_side":2,"id":160,"is_read":1,"member_id":0,"record_id":2,"type":1,"update_time":"2019-09-03 17:50:26"}],"last_page":1,"per_page":15,"total":8}
         * member : {"member_avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201909/8_2019090410334838908.jpg","member_id":8,"member_name":"wei"}
         * record_id : 2
         * service : {"service_avatar":"http://wwww.jiefutong.net/uploads/mobile/category/logo_logo.png","service_name":"X街客服"}
         */

        private LogsBean logs;
        private MemberBean member;
        private int record_id;
        private ServiceBean service;

        public LogsBean getLogs() {
            return logs;
        }

        public void setLogs(LogsBean logs) {
            this.logs = logs;
        }

        public MemberBean getMember() {
            return member;
        }

        public void setMember(MemberBean member) {
            this.member = member;
        }

        public int getRecord_id() {
            return record_id;
        }

        public void setRecord_id(int record_id) {
            this.record_id = record_id;
        }

        public ServiceBean getService() {
            return service;
        }

        public void setService(ServiceBean service) {
            this.service = service;
        }

        public static class LogsBean {
            /**
             * current_page : 1
             * data : [{"content":"的","create_time":"2019-09-05 10:20:38","from_side":2,"id":167,"is_read":1,"member_id":0,"record_id":2,"type":1,"update_time":"2019-09-05 10:20:38"},{"content":"的","create_time":"2019-09-05 10:20:10","from_side":2,"id":166,"is_read":1,"member_id":0,"record_id":2,"type":1,"update_time":"2019-09-05 10:20:10"},{"content":"的的","create_time":"2019-09-05 10:20:09","from_side":2,"id":165,"is_read":1,"member_id":0,"record_id":2,"type":1,"update_time":"2019-09-05 10:20:09"},{"content":"的","create_time":"2019-09-05 10:20:05","from_side":2,"id":164,"is_read":1,"member_id":0,"record_id":2,"type":1,"update_time":"2019-09-05 10:20:05"},{"content":"功夫贷染发膏 联发科了适度反抗 快十点了荆防颗粒是 六点付款时","create_time":"2019-09-03 18:23:00","from_side":2,"id":163,"is_read":1,"member_id":0,"record_id":2,"type":1,"update_time":"2019-09-03 18:23:00"},{"content":"Xjie","create_time":"2019-09-03 17:55:56","from_side":2,"id":162,"is_read":1,"member_id":0,"record_id":2,"type":1,"update_time":"2019-09-03 17:55:56"},{"content":"23","create_time":"2019-09-03 17:51:01","from_side":2,"id":161,"is_read":1,"member_id":0,"record_id":2,"type":1,"update_time":"2019-09-03 17:51:01"},{"content":"大师","create_time":"2019-09-03 17:50:26","from_side":2,"id":160,"is_read":1,"member_id":0,"record_id":2,"type":1,"update_time":"2019-09-03 17:50:26"}]
             * last_page : 1
             * per_page : 15
             * total : 8
             */

            private int current_page;
            private int last_page;
            private int per_page;
            private int total;
            private List<ChatDataBeanVo> data;

            public int getCurrent_page() {
                return current_page;
            }

            public void setCurrent_page(int current_page) {
                this.current_page = current_page;
            }

            public int getLast_page() {
                return last_page;
            }

            public void setLast_page(int last_page) {
                this.last_page = last_page;
            }

            public int getPer_page() {
                return per_page;
            }

            public void setPer_page(int per_page) {
                this.per_page = per_page;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public List<ChatDataBeanVo> getData() {
                return data;
            }

            public void setData(List<ChatDataBeanVo> data) {
                this.data = data;
            }


        }

        public static class MemberBean {
            /**
             * member_avatar : http://tsyc.jiefutong.net/uploads/home/membertag/201909/8_2019090410334838908.jpg
             * member_id : 8
             * member_name : wei
             */

            private String member_avatar;
            private int member_id;
            private String member_name;

            public String getMember_avatar() {
                return member_avatar;
            }

            public void setMember_avatar(String member_avatar) {
                this.member_avatar = member_avatar;
            }

            public int getMember_id() {
                return member_id;
            }

            public void setMember_id(int member_id) {
                this.member_id = member_id;
            }

            public String getMember_name() {
                return member_name;
            }

            public void setMember_name(String member_name) {
                this.member_name = member_name;
            }
        }

        public static class ServiceBean {
            /**
             * service_avatar : http://wwww.jiefutong.net/uploads/mobile/category/logo_logo.png
             * service_name : X街客服
             */

            private String service_avatar;
            private String service_name;

            public String getService_avatar() {
                return service_avatar;
            }

            public void setService_avatar(String service_avatar) {
                this.service_avatar = service_avatar;
            }

            public String getService_name() {
                return service_name;
            }

            public void setService_name(String service_name) {
                this.service_name = service_name;
            }
        }
    }
}
