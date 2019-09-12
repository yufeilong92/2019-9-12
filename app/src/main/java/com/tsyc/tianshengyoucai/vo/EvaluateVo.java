package com.tsyc.tianshengyoucai.vo;

import com.tsyc.tianshengyoucai.model.bean.NormalBean;

import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/12 19:33
 * @Purpose :评价vo
 */
public class EvaluateVo extends NormalBean {

    /**
     * result : {"count":{"all":1,"bad":0,"good":1,"images":1},"list":{"current_page":1,"data":[{"desc":"2019-08-01","geval_addtime":1564629892,"geval_content":"adwefsfrwefseffwegtyl,t869lo578i356y45t6q34gvaergaergwrhwry6hje5ju67ntdfhhw56huw5uhwtfh6hyu56uhyw56y","geval_frommemberid":15,"geval_id":8,"geval_image":["http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg","http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg"],"geval_scores":5,"member_avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019071014511320484.jpg","member_id":15,"member_name":"SSS"}],"last_page":1,"per_page":20,"total":1}}
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
         * count : {"all":1,"bad":0,"good":1,"images":1}
         * list : {"current_page":1,"data":[{"desc":"2019-08-01","geval_addtime":1564629892,"geval_content":"adwefsfrwefseffwegtyl,t869lo578i356y45t6q34gvaergaergwrhwry6hje5ju67ntdfhhw56huw5uhwtfh6hyu56uhyw56y","geval_frommemberid":15,"geval_id":8,"geval_image":["http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg","http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg"],"geval_scores":5,"member_avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019071014511320484.jpg","member_id":15,"member_name":"SSS"}],"last_page":1,"per_page":20,"total":1}
         */

        private CountBean count;
        private ListBean list;

        public CountBean getCount() {
            return count;
        }

        public void setCount(CountBean count) {
            this.count = count;
        }

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class CountBean {
            /**
             * all : 1
             * bad : 0
             * good : 1
             * images : 1
             */

            private int all;
            private int bad;
            private int good;
            private int images;

            public int getAll() {
                return all;
            }

            public void setAll(int all) {
                this.all = all;
            }

            public int getBad() {
                return bad;
            }

            public void setBad(int bad) {
                this.bad = bad;
            }

            public int getGood() {
                return good;
            }

            public void setGood(int good) {
                this.good = good;
            }

            public int getImages() {
                return images;
            }

            public void setImages(int images) {
                this.images = images;
            }
        }

        public static class ListBean {
            /**
             * current_page : 1
             * data : [{"desc":"2019-08-01","geval_addtime":1564629892,"geval_content":"adwefsfrwefseffwegtyl,t869lo578i356y45t6q34gvaergaergwrhwry6hje5ju67ntdfhhw56huw5uhwtfh6hyu56uhyw56y","geval_frommemberid":15,"geval_id":8,"geval_image":["http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg","http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg"],"geval_scores":5,"member_avatar":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019071014511320484.jpg","member_id":15,"member_name":"SSS"}]
             * last_page : 1
             * per_page : 20
             * total : 1
             */

            private int current_page;
            private int last_page;
            private int per_page;
            private int total;
            private List<DataBean> data;

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

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public static class DataBean {
                /**
                 * desc : 2019-08-01
                 * geval_addtime : 1564629892
                 * geval_content : adwefsfrwefseffwegtyl,t869lo578i356y45t6q34gvaergaergwrhwry6hje5ju67ntdfhhw56huw5uhwtfh6hyu56uhyw56y
                 * geval_frommemberid : 15
                 * geval_id : 8
                 * geval_image : ["http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg","http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092901284880537.jpg"]
                 * geval_scores : 5
                 * member_avatar : http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019071014511320484.jpg
                 * member_id : 15
                 * member_name : SSS
                 */

                private String desc;
                private int geval_addtime;
                private String geval_content;
                private int geval_frommemberid;
                private int geval_id;
                private int geval_scores;
                private String member_avatar;
                private int member_id;
                private String member_name;
                private List<String> geval_image;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
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

                public int getGeval_frommemberid() {
                    return geval_frommemberid;
                }

                public void setGeval_frommemberid(int geval_frommemberid) {
                    this.geval_frommemberid = geval_frommemberid;
                }

                public int getGeval_id() {
                    return geval_id;
                }

                public void setGeval_id(int geval_id) {
                    this.geval_id = geval_id;
                }

                public int getGeval_scores() {
                    return geval_scores;
                }

                public void setGeval_scores(int geval_scores) {
                    this.geval_scores = geval_scores;
                }

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

                public List<String> getGeval_image() {
                    return geval_image;
                }

                public void setGeval_image(List<String> geval_image) {
                    this.geval_image = geval_image;
                }
            }
        }
    }
}
