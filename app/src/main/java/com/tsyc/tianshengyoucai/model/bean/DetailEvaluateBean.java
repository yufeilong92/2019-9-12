package com.tsyc.tianshengyoucai.model.bean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/8/6
 * File description： 商家详情 评论bean
 */
public class DetailEvaluateBean {

    /**
     * code : 200
     * result : {"count":{"all":8,"images":8,"good":8,"bad":0},"list":[{"geval_frommembername":"S*S","geval_scores":5,"geval_addtime":1564629892,"geval_content":"adwefsfrwefseffwegtyl,t869lo578i356y45t6q34gvaergaergwrhwry6hje5ju67ntdfhhw56huw5uhwtfh6hyu56uhyw56y","geval_image":["http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019070312060330818.jpg"],"geval_remark":null,"member_avatar":null},{"geval_frommembername":"p***************8","geval_scores":5,"geval_addtime":1563412945,"geval_content":"特意使用了一段时间才来评价的，首先是980的芯片，功能很强，游戏体验很好，然后充电非常快，基本上一小时左右就能充满，电池超级耐用，系统风格是magic的，屏幕是个人比较喜欢，拍照方面相机也挺好，用的是索尼的镜头，不过没有广角镜头和微距镜头，但带了一个TOF的镜头，增加了实用性和趣味性","geval_image":null,"geval_remark":null,"member_avatar":null},{"geval_frommembername":"C*","geval_scores":5,"geval_addtime":1563283544,"geval_content":"Hftrrrrrrwwweeeffffyyyhgffdfyu","geval_image":["http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019070312060330818.jpg"],"geval_remark":null,"member_avatar":null},{"geval_frommembername":"p***************4","geval_scores":5,"geval_addtime":1562063359,"geval_content":"商品不错","geval_image":["http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg","http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg"],"geval_remark":null,"member_avatar":null},{"geval_frommembername":"p***************4","geval_scores":5,"geval_addtime":1562052525,"geval_content":"商品不错","geval_image":["http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg","http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg"],"geval_remark":null,"member_avatar":null},{"geval_frommembername":"p***************4","geval_scores":5,"geval_addtime":1562051101,"geval_content":"商品不错","geval_image":["http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg","http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg"],"geval_remark":null,"member_avatar":null},{"geval_frommembername":"p***************4","geval_scores":5,"geval_addtime":1562047419,"geval_content":"测试数据","geval_image":["http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg","http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg"],"geval_remark":null,"member_avatar":null},{"geval_frommembername":"p***************4","geval_scores":5,"geval_addtime":1561965510,"geval_content":"商品不错","geval_image":["http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg","http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg"],"geval_remark":null,"member_avatar":null}]}
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
         * count : {"all":8,"images":8,"good":8,"bad":0}
         * list : [{"geval_frommembername":"S*S","geval_scores":5,"geval_addtime":1564629892,"geval_content":"adwefsfrwefseffwegtyl,t869lo578i356y45t6q34gvaergaergwrhwry6hje5ju67ntdfhhw56huw5uhwtfh6hyu56uhyw56y","geval_image":["http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019070312060330818.jpg"],"geval_remark":null,"member_avatar":null},{"geval_frommembername":"p***************8","geval_scores":5,"geval_addtime":1563412945,"geval_content":"特意使用了一段时间才来评价的，首先是980的芯片，功能很强，游戏体验很好，然后充电非常快，基本上一小时左右就能充满，电池超级耐用，系统风格是magic的，屏幕是个人比较喜欢，拍照方面相机也挺好，用的是索尼的镜头，不过没有广角镜头和微距镜头，但带了一个TOF的镜头，增加了实用性和趣味性","geval_image":null,"geval_remark":null,"member_avatar":null},{"geval_frommembername":"C*","geval_scores":5,"geval_addtime":1563283544,"geval_content":"Hftrrrrrrwwweeeffffyyyhgffdfyu","geval_image":["http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019070312060330818.jpg"],"geval_remark":null,"member_avatar":null},{"geval_frommembername":"p***************4","geval_scores":5,"geval_addtime":1562063359,"geval_content":"商品不错","geval_image":["http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg","http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg"],"geval_remark":null,"member_avatar":null},{"geval_frommembername":"p***************4","geval_scores":5,"geval_addtime":1562052525,"geval_content":"商品不错","geval_image":["http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg","http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg"],"geval_remark":null,"member_avatar":null},{"geval_frommembername":"p***************4","geval_scores":5,"geval_addtime":1562051101,"geval_content":"商品不错","geval_image":["http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg","http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg"],"geval_remark":null,"member_avatar":null},{"geval_frommembername":"p***************4","geval_scores":5,"geval_addtime":1562047419,"geval_content":"测试数据","geval_image":["http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg","http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg"],"geval_remark":null,"member_avatar":null},{"geval_frommembername":"p***************4","geval_scores":5,"geval_addtime":1561965510,"geval_content":"商品不错","geval_image":["http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg","http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg"],"geval_remark":null,"member_avatar":null}]
         */

        private CountBean count;
        private List<ListBean> list;

        public CountBean getCount() {
            return count;
        }

        public void setCount(CountBean count) {
            this.count = count;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class CountBean {
            /**
             * all : 8
             * images : 8
             * good : 8
             * bad : 0
             */

            private int all;
            private int images;
            private int good;
            private int bad;

            public int getAll() {
                return all;
            }

            public void setAll(int all) {
                this.all = all;
            }

            public int getImages() {
                return images;
            }

            public void setImages(int images) {
                this.images = images;
            }

            public int getGood() {
                return good;
            }

            public void setGood(int good) {
                this.good = good;
            }

            public int getBad() {
                return bad;
            }

            public void setBad(int bad) {
                this.bad = bad;
            }
        }

        public static class ListBean {
            /**
             * geval_frommembername : S*S
             * geval_scores : 5
             * geval_addtime : 1564629892
             * geval_content : adwefsfrwefseffwegtyl,t869lo578i356y45t6q34gvaergaergwrhwry6hje5ju67ntdfhhw56huw5uhwtfh6hyu56uhyw56y
             * geval_image : ["http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019070312060330818.jpg"]
             * geval_remark : hui fu
             * member_avatar :  avatar
             */

            private String geval_frommembername;
            private int geval_scores;
            private int geval_addtime;
            private String geval_content;
            private String geval_remark;
            private String member_avatar;
            private List<String> geval_image;

            public String getGeval_frommembername() {
                return geval_frommembername;
            }

            public void setGeval_frommembername(String geval_frommembername) {
                this.geval_frommembername = geval_frommembername;
            }

            public int getGeval_scores() {
                return geval_scores;
            }

            public void setGeval_scores(int geval_scores) {
                this.geval_scores = geval_scores;
            }

            public int getGeval_addtime() {
                return geval_addtime;
            }

            public void setGeval_addtime(int geval_addtime) {
                this.geval_addtime = geval_addtime;
            }

            public String getGeval_content() {
                return geval_content;
            }

            public void setGeval_content(String geval_content) {
                this.geval_content = geval_content;
            }

            public String getGeval_remark() {
                return geval_remark;
            }

            public void setGeval_remark(String geval_remark) {
                this.geval_remark = geval_remark;
            }

            public String getMember_avatar() {
                return member_avatar;
            }

            public void setMember_avatar(String member_avatar) {
                this.member_avatar = member_avatar;
            }

            public List<String> getGeval_image() {
                return geval_image;
            }

            public void setGeval_image(List<String> geval_image) {
                this.geval_image = geval_image;
            }
        }
    }
}
