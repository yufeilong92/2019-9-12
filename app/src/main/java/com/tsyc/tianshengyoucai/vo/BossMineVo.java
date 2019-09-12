package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/4 09:49
 * @Purpose :
 */
public class BossMineVo extends NormalBean {

    /**
     * result : {"boss":{"avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201909/8_2019090318004962880.jpg","id":1,"user_id":3,"username":"game over"},"cv_sends":{"interview":0,"others":6,"send":1}}
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
         * boss : {"avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201909/8_2019090318004962880.jpg","id":1,"user_id":3,"username":"game over"}
         * cv_sends : {"interview":0,"others":6,"send":1}
         */

        private BossBean boss;
        private CvSendsBean cv_sends;

        public BossBean getBoss() {
            return boss;
        }

        public void setBoss(BossBean boss) {
            this.boss = boss;
        }

        public CvSendsBean getCv_sends() {
            return cv_sends;
        }

        public void setCv_sends(CvSendsBean cv_sends) {
            this.cv_sends = cv_sends;
        }

        public static class BossBean {
            /**
             * avatar : http://tsyc.jiefutong.net/uploads/home/membertag/201909/8_2019090318004962880.jpg
             * id : 1
             * user_id : 3
             * username : game over
             */

            private String avatar;
            private int id;
            private int user_id;
            private String username;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }

        public static class CvSendsBean {
            /**
             * interview : 0
             * others : 6
             * send : 1
             */

            private int interview;
            private int others;
            private int send;

            public int getInterview() {
                return interview;
            }

            public void setInterview(int interview) {
                this.interview = interview;
            }

            public int getOthers() {
                return others;
            }

            public void setOthers(int others) {
                this.others = others;
            }

            public int getSend() {
                return send;
            }

            public void setSend(int send) {
                this.send = send;
            }
        }
    }
}
