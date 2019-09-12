package com.tsyc.tianshengyoucai.model.bean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/29
 * File description： 线下订单bean
 */
public class UnderlineOrderBean {

    /**
     * code : 200
     * result : {"total":42,"per_page":20,"current_page":1,"last_page":3,"data":[{"order_id":112,"order_sn":"SP19072619243711482","store_id":1,"store_name":"测试店铺","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"148.00","ziti_verify_code":"78c28872a4505bdb","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":203,"goods_guige":"","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":"148.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":110,"order_sn":"SP19072619243516339","store_id":1,"store_name":"测试店铺","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"148.00","ziti_verify_code":"b7cc92b991cf4d28","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":203,"goods_guige":"","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":"148.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":109,"order_sn":"SP19072619223576896","store_id":1,"store_name":"测试店铺","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"148.00","ziti_verify_code":"3c475784381a64e4","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":203,"goods_guige":"","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":"148.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":108,"order_sn":"SP19072619223153351","store_id":1,"store_name":"测试店铺","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"148.00","ziti_verify_code":"33b64e9f27197a48","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":203,"goods_guige":"","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":"148.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":107,"order_sn":"SP19072619112995363","store_id":1,"store_name":"测试店铺","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"148.00","ziti_verify_code":"25290be4cb794ec6","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":203,"goods_guige":"","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":"148.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":106,"order_sn":"SP19072619110595435","store_id":1,"store_name":"测试店铺","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"148.00","ziti_verify_code":"eea6f38fabb3acb1","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":203,"goods_guige":"","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":"148.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":105,"order_sn":"SP19072619080281146","store_id":9,"store_name":"CCTV","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"1000.00","ziti_verify_code":"02bcb2651801455c","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":223,"goods_guige":"","goods_name":"CC","goods_price":"1000.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019071918534835021.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":104,"order_sn":"SP19072619073679473","store_id":9,"store_name":"CCTV","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"1000.00","ziti_verify_code":"d3f0faf985047d4c","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":223,"goods_guige":"","goods_name":"CC","goods_price":"1000.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019071918534835021.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":103,"order_sn":"SP19072619052028039","store_id":9,"store_name":"CCTV","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"1000.00","ziti_verify_code":"02fe5b1c2496fe17","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":223,"goods_guige":"","goods_name":"CC","goods_price":"1000.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019071918534835021.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":102,"order_sn":"SP19072619034876484","store_id":9,"store_name":"CCTV","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"1000.00","ziti_verify_code":"8137572f3849aabd","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":223,"goods_guige":"","goods_name":"CC","goods_price":"1000.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019071918534835021.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":101,"order_sn":"SP19072619034660549","store_id":9,"store_name":"CCTV","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"1000.00","ziti_verify_code":"196a4758191e42f7","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":223,"goods_guige":"","goods_name":"CC","goods_price":"1000.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019071918534835021.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":100,"order_sn":"SP19072619030161346","store_id":9,"store_name":"CCTV","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"1000.00","ziti_verify_code":"d20f50967c64dac2","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":223,"goods_guige":"","goods_name":"CC","goods_price":"1000.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019071918534835021.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":99,"order_sn":"SP19072619030049412","store_id":9,"store_name":"CCTV","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"1000.00","ziti_verify_code":"3917945245041ea1","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":223,"goods_guige":"","goods_name":"CC","goods_price":"1000.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019071918534835021.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":98,"order_sn":"SP19072619025733930","store_id":9,"store_name":"CCTV","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"1000.00","ziti_verify_code":"a99880d39e5daec3","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":223,"goods_guige":"","goods_name":"CC","goods_price":"1000.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019071918534835021.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":97,"order_sn":"SP19072619025234535","store_id":9,"store_name":"CCTV","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"1000.00","ziti_verify_code":"9c407203749ae32d","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":223,"goods_guige":"","goods_name":"CC","goods_price":"1000.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019071918534835021.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":96,"order_sn":"SP19072619023139114","store_id":9,"store_name":"CCTV","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"1000.00","ziti_verify_code":"c5372a547c104929","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":223,"goods_guige":"","goods_name":"CC","goods_price":"1000.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019071918534835021.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":95,"order_sn":"SP19072619022910954","store_id":9,"store_name":"CCTV","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"1000.00","ziti_verify_code":"63d57df27213ede1","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":223,"goods_guige":"","goods_name":"CC","goods_price":"1000.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019071918534835021.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":94,"order_sn":"SP19072619012243887","store_id":1,"store_name":"测试店铺","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"15.80","ziti_verify_code":"560c4e46ccc488ef","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":202,"goods_guige":"","goods_name":"花样游泳队指定 新面孔金眼霜 淡皱纹,隐眼袋,熬夜眼霜女","goods_price":"15.80","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071910240941270.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":92,"order_sn":"SP19072416295129891","store_id":10,"store_name":"贝佳尼","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"2.00","ziti_verify_code":"ffa5c10e873bf384","order_state":100,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":231,"goods_guige":"二连","goods_name":"小红书 型号 二连","goods_price":"2.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/8_2019072214103218585.jpg"}],"status_text":"退款完成","payment_name":""},{"order_id":90,"order_sn":"SP19072410152535263","store_id":10,"store_name":"贝佳尼","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"1.00","ziti_verify_code":"15140ac4f1c22da8","order_state":100,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":230,"goods_guige":"一连","goods_name":"小红书 型号 一连","goods_price":"1.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/8_2019072214103218585.jpg"}],"status_text":"退款完成","payment_name":""}]}
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
         * total : 42
         * per_page : 20
         * current_page : 1
         * last_page : 3
         * data : [{"order_id":112,"order_sn":"SP19072619243711482","store_id":1,"store_name":"测试店铺","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"148.00","ziti_verify_code":"78c28872a4505bdb","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":203,"goods_guige":"","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":"148.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":110,"order_sn":"SP19072619243516339","store_id":1,"store_name":"测试店铺","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"148.00","ziti_verify_code":"b7cc92b991cf4d28","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":203,"goods_guige":"","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":"148.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":109,"order_sn":"SP19072619223576896","store_id":1,"store_name":"测试店铺","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"148.00","ziti_verify_code":"3c475784381a64e4","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":203,"goods_guige":"","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":"148.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":108,"order_sn":"SP19072619223153351","store_id":1,"store_name":"测试店铺","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"148.00","ziti_verify_code":"33b64e9f27197a48","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":203,"goods_guige":"","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":"148.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":107,"order_sn":"SP19072619112995363","store_id":1,"store_name":"测试店铺","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"148.00","ziti_verify_code":"25290be4cb794ec6","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":203,"goods_guige":"","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":"148.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":106,"order_sn":"SP19072619110595435","store_id":1,"store_name":"测试店铺","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"148.00","ziti_verify_code":"eea6f38fabb3acb1","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":203,"goods_guige":"","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":"148.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":105,"order_sn":"SP19072619080281146","store_id":9,"store_name":"CCTV","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"1000.00","ziti_verify_code":"02bcb2651801455c","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":223,"goods_guige":"","goods_name":"CC","goods_price":"1000.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019071918534835021.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":104,"order_sn":"SP19072619073679473","store_id":9,"store_name":"CCTV","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"1000.00","ziti_verify_code":"d3f0faf985047d4c","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":223,"goods_guige":"","goods_name":"CC","goods_price":"1000.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019071918534835021.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":103,"order_sn":"SP19072619052028039","store_id":9,"store_name":"CCTV","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"1000.00","ziti_verify_code":"02fe5b1c2496fe17","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":223,"goods_guige":"","goods_name":"CC","goods_price":"1000.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019071918534835021.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":102,"order_sn":"SP19072619034876484","store_id":9,"store_name":"CCTV","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"1000.00","ziti_verify_code":"8137572f3849aabd","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":223,"goods_guige":"","goods_name":"CC","goods_price":"1000.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019071918534835021.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":101,"order_sn":"SP19072619034660549","store_id":9,"store_name":"CCTV","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"1000.00","ziti_verify_code":"196a4758191e42f7","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":223,"goods_guige":"","goods_name":"CC","goods_price":"1000.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019071918534835021.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":100,"order_sn":"SP19072619030161346","store_id":9,"store_name":"CCTV","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"1000.00","ziti_verify_code":"d20f50967c64dac2","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":223,"goods_guige":"","goods_name":"CC","goods_price":"1000.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019071918534835021.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":99,"order_sn":"SP19072619030049412","store_id":9,"store_name":"CCTV","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"1000.00","ziti_verify_code":"3917945245041ea1","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":223,"goods_guige":"","goods_name":"CC","goods_price":"1000.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019071918534835021.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":98,"order_sn":"SP19072619025733930","store_id":9,"store_name":"CCTV","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"1000.00","ziti_verify_code":"a99880d39e5daec3","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":223,"goods_guige":"","goods_name":"CC","goods_price":"1000.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019071918534835021.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":97,"order_sn":"SP19072619025234535","store_id":9,"store_name":"CCTV","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"1000.00","ziti_verify_code":"9c407203749ae32d","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":223,"goods_guige":"","goods_name":"CC","goods_price":"1000.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019071918534835021.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":96,"order_sn":"SP19072619023139114","store_id":9,"store_name":"CCTV","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"1000.00","ziti_verify_code":"c5372a547c104929","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":223,"goods_guige":"","goods_name":"CC","goods_price":"1000.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019071918534835021.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":95,"order_sn":"SP19072619022910954","store_id":9,"store_name":"CCTV","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"1000.00","ziti_verify_code":"63d57df27213ede1","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":223,"goods_guige":"","goods_name":"CC","goods_price":"1000.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/15_2019071918534835021.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":94,"order_sn":"SP19072619012243887","store_id":1,"store_name":"测试店铺","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"15.80","ziti_verify_code":"560c4e46ccc488ef","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":202,"goods_guige":"","goods_name":"花样游泳队指定 新面孔金眼霜 淡皱纹,隐眼袋,熬夜眼霜女","goods_price":"15.80","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071910240941270.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":92,"order_sn":"SP19072416295129891","store_id":10,"store_name":"贝佳尼","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"2.00","ziti_verify_code":"ffa5c10e873bf384","order_state":100,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":231,"goods_guige":"二连","goods_name":"小红书 型号 二连","goods_price":"2.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/8_2019072214103218585.jpg"}],"status_text":"退款完成","payment_name":""},{"order_id":90,"order_sn":"SP19072410152535263","store_id":10,"store_name":"贝佳尼","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"1.00","ziti_verify_code":"15140ac4f1c22da8","order_state":100,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":230,"goods_guige":"一连","goods_name":"小红书 型号 一连","goods_price":"1.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/membertag/201907/8_2019072214103218585.jpg"}],"status_text":"退款完成","payment_name":""}]
         */

        private int total;
        private int per_page;
        private int current_page;
        private int last_page;
        private List<DataBean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

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

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * order_id : 112
             * order_sn : SP19072619243711482
             * store_id : 1
             * store_name : 测试店铺
             * buyer_id : 9
             * buyer_name : 1234sdfsdfsdf3
             * order_amount : 148.00
             * ziti_verify_code : 78c28872a4505bdb
             * order_state : 10
             * lock_state : 0
             * total_goods_num : 1
             * ordergoods : [{"goods_id":203,"goods_guige":"","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":"148.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg"}]
             * status_text : 待付款
             * payment_name :
             * liuyan :
             */

            private int order_id;
            private String order_sn;
            private int store_id;
            private String store_name;
            private int buyer_id;
            private String buyer_name;
            private String order_amount;
            private String ziti_verify_code;
            private int order_state;
            private int lock_state;
            private int total_goods_num;
            private String status_text;
            private String liuyan;
            private String payment_name;
            private List<OrdergoodsBean> ordergoods;

            public String getLiuyan() {
                return liuyan;
            }

            public void setLiuyan(String liuyan) {
                this.liuyan = liuyan;
            }

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public String getOrder_sn() {
                return order_sn;
            }

            public void setOrder_sn(String order_sn) {
                this.order_sn = order_sn;
            }

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public int getBuyer_id() {
                return buyer_id;
            }

            public void setBuyer_id(int buyer_id) {
                this.buyer_id = buyer_id;
            }

            public String getBuyer_name() {
                return buyer_name;
            }

            public void setBuyer_name(String buyer_name) {
                this.buyer_name = buyer_name;
            }

            public String getOrder_amount() {
                return order_amount;
            }

            public void setOrder_amount(String order_amount) {
                this.order_amount = order_amount;
            }

            public String getZiti_verify_code() {
                return ziti_verify_code;
            }

            public void setZiti_verify_code(String ziti_verify_code) {
                this.ziti_verify_code = ziti_verify_code;
            }

            public int getOrder_state() {
                return order_state;
            }

            public void setOrder_state(int order_state) {
                this.order_state = order_state;
            }

            public int getLock_state() {
                return lock_state;
            }

            public void setLock_state(int lock_state) {
                this.lock_state = lock_state;
            }

            public int getTotal_goods_num() {
                return total_goods_num;
            }

            public void setTotal_goods_num(int total_goods_num) {
                this.total_goods_num = total_goods_num;
            }

            public String getStatus_text() {
                return status_text;
            }

            public void setStatus_text(String status_text) {
                this.status_text = status_text;
            }

            public String getPayment_name() {
                return payment_name;
            }

            public void setPayment_name(String payment_name) {
                this.payment_name = payment_name;
            }

            public List<OrdergoodsBean> getOrdergoods() {
                return ordergoods;
            }

            public void setOrdergoods(List<OrdergoodsBean> ordergoods) {
                this.ordergoods = ordergoods;
            }

            public static class OrdergoodsBean {
                /**
                 * goods_id : 203
                 * goods_guige :
                 * goods_name : LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套
                 * goods_price : 148.00
                 * goods_num : 1
                 * goods_image : http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg
                 */

                private int goods_id;
                private String goods_guige;
                private String goods_name;
                private String goods_price;
                private int goods_num;
                private String goods_image;

                public int getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(int goods_id) {
                    this.goods_id = goods_id;
                }

                public String getGoods_guige() {
                    return goods_guige;
                }

                public void setGoods_guige(String goods_guige) {
                    this.goods_guige = goods_guige;
                }

                public String getGoods_name() {
                    return goods_name;
                }

                public void setGoods_name(String goods_name) {
                    this.goods_name = goods_name;
                }

                public String getGoods_price() {
                    return goods_price;
                }

                public void setGoods_price(String goods_price) {
                    this.goods_price = goods_price;
                }

                public int getGoods_num() {
                    return goods_num;
                }

                public void setGoods_num(int goods_num) {
                    this.goods_num = goods_num;
                }

                public String getGoods_image() {
                    return goods_image;
                }

                public void setGoods_image(String goods_image) {
                    this.goods_image = goods_image;
                }
            }
        }
    }
}
