package com.tsyc.tianshengyoucai.model.bean;

import java.util.List;

/**
 * author：van
 * CreateTime：2019/7/31
 * File description：
 */
public class OrderManageBean {

    /**
     * code : 200
     * result : {"total":27,"per_page":20,"current_page":1,"last_page":2,"data":[{"order_id":138,"order_sn":"SP19080312372362684","store_id":1,"store_name":"测试店铺","buyer_id":18,"buyer_name":"phone_18916977773","order_amount":"2198.00","ziti_verify_code":"1481d1c21bd1635f","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":250,"goods_guige":"样式1 红色 L","goods_name":"夏季新款女装真丝欧根纱圆领植绒印花无袖假两件宽松套头连衣长裙 样式1 红色 L","goods_price":"2198.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019080112002796773.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":136,"order_sn":"SP19080312292142507","store_id":1,"store_name":"测试店铺","buyer_id":19,"buyer_name":"嘻嘻","order_amount":"2198.00","ziti_verify_code":"fe9ddb119e23e9b2","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":256,"goods_guige":"样式2 绿色 L","goods_name":"夏季新款女装真丝欧根纱圆领植绒印花无袖假两件宽松套头连衣长裙 样式2 绿色 L","goods_price":"2198.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019080112002796773.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":128,"order_sn":"SP19072922043077133","store_id":1,"store_name":"测试店铺","buyer_id":16,"buyer_name":"豆豆","order_amount":"47.40","ziti_verify_code":"f146054a5a720606","order_state":10,"lock_state":0,"total_goods_num":3,"ordergoods":[{"goods_id":202,"goods_guige":"","goods_name":"花样游泳队指定 新面孔金眼霜 淡皱纹,隐眼袋,熬夜眼霜女","goods_price":"15.80","goods_num":3,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071910240941270.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":122,"order_sn":"SP19072909085728332","store_id":1,"store_name":"测试店铺","buyer_id":15,"buyer_name":"CC","order_amount":"47.40","ziti_verify_code":"c6a6d31695acbae3","order_state":0,"lock_state":0,"total_goods_num":3,"ordergoods":[{"goods_id":202,"goods_guige":"","goods_name":"花样游泳队指定 新面孔金眼霜 淡皱纹,隐眼袋,熬夜眼霜女","goods_price":"15.80","goods_num":3,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071910240941270.jpg"}],"status_text":"已取消","payment_name":""},{"order_id":120,"order_sn":"SP19072716031888114","store_id":1,"store_name":"测试店铺","buyer_id":15,"buyer_name":"CC","order_amount":"296.00","ziti_verify_code":"d6b89b95bcb416d6","order_state":"104","lock_state":1,"total_goods_num":2,"ordergoods":[{"goods_id":203,"goods_guige":"","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":"148.00","goods_num":2,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg"}],"status_text":"维权中-待平台打款","payment_name":""},{"order_id":119,"order_sn":"SP19072715593672019","store_id":1,"store_name":"测试店铺","buyer_id":15,"buyer_name":"CC","order_amount":"47.40","ziti_verify_code":"e30dd0c4e8bdbcc8","order_state":0,"lock_state":0,"total_goods_num":3,"ordergoods":[{"goods_id":202,"goods_guige":"","goods_name":"花样游泳队指定 新面孔金眼霜 淡皱纹,隐眼袋,熬夜眼霜女","goods_price":"15.80","goods_num":3,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071910240941270.jpg"}],"status_text":"已取消","payment_name":""},{"order_id":112,"order_sn":"SP19072619243711482","store_id":1,"store_name":"测试店铺","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"148.00","ziti_verify_code":"78c28872a4505bdb","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":203,"goods_guige":"","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":"148.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":111,"order_sn":"SP19072619243671483","store_id":1,"store_name":"测试店铺","buyer_id":15,"buyer_name":"CC","order_amount":"148.00","ziti_verify_code":"dc006dadf2190caa","order_state":0,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":203,"goods_guige":"","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":"148.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg"}],"status_text":"已取消","payment_name":""},{"order_id":110,"order_sn":"SP19072619243516339","store_id":1,"store_name":"测试店铺","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"148.00","ziti_verify_code":"b7cc92b991cf4d28","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":203,"goods_guige":"","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":"148.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":109,"order_sn":"SP19072619223576896","store_id":1,"store_name":"测试店铺","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"148.00","ziti_verify_code":"3c475784381a64e4","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":203,"goods_guige":"","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":"148.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":108,"order_sn":"SP19072619223153351","store_id":1,"store_name":"测试店铺","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"148.00","ziti_verify_code":"33b64e9f27197a48","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":203,"goods_guige":"","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":"148.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":107,"order_sn":"SP19072619112995363","store_id":1,"store_name":"测试店铺","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"148.00","ziti_verify_code":"25290be4cb794ec6","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":203,"goods_guige":"","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":"148.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":106,"order_sn":"SP19072619110595435","store_id":1,"store_name":"测试店铺","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"148.00","ziti_verify_code":"eea6f38fabb3acb1","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":203,"goods_guige":"","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":"148.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":94,"order_sn":"SP19072619012243887","store_id":1,"store_name":"测试店铺","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"15.80","ziti_verify_code":"560c4e46ccc488ef","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":202,"goods_guige":"","goods_name":"花样游泳队指定 新面孔金眼霜 淡皱纹,隐眼袋,熬夜眼霜女","goods_price":"15.80","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071910240941270.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":133,"order_sn":"SP19070415130839243","store_id":1,"store_name":"DsMll","buyer_id":8,"buyer_name":"phone_15037142280","order_amount":"39.00","ziti_verify_code":"880bb0c9b8a3bf97","order_state":110,"lock_state":0,"total_goods_num":0,"ordergoods":[],"status_text":"","payment_name":""},{"order_id":73,"order_sn":"SP19070415130839243","store_id":1,"store_name":"DsMll","buyer_id":8,"buyer_name":"phone_15037142280","order_amount":"39.00","ziti_verify_code":"880bb0c9b8a3bf97","order_state":40,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":45,"goods_guige":"针织 黑 M","goods_name":"与牧2017韩版短款 针织 黑 M","goods_price":"39.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092203045216863.jpg"}],"status_text":"交易完成","payment_name":""},{"order_id":132,"order_sn":"SP19070415130839243","store_id":1,"store_name":"DsMll","buyer_id":8,"buyer_name":"phone_15037142280","order_amount":"39.00","ziti_verify_code":"880bb0c9b8a3bf97","order_state":100,"lock_state":0,"total_goods_num":0,"ordergoods":[],"status_text":"退款完成","payment_name":""},{"order_id":69,"order_sn":"SP19070119283365864","store_id":1,"store_name":"DsMll","buyer_id":8,"buyer_name":"phone_15037142280","order_amount":"39.00","ziti_verify_code":"2f19414de3ebd9f6","order_state":"101","lock_state":1,"total_goods_num":1,"ordergoods":[{"goods_id":45,"goods_guige":"针织 黑 M","goods_name":"与牧2017韩版短款 针织 黑 M","goods_price":"39.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092203045216863.jpg"}],"status_text":"维权中-待商家审核","payment_name":""},{"order_id":42,"order_sn":"SP19062511171477379","store_id":1,"store_name":"DsMll","buyer_id":9,"buyer_name":"phone_18137127634","order_amount":"39.00","ziti_verify_code":null,"order_state":0,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":45,"goods_guige":"针织 黑 M","goods_name":"与牧2017韩版短款 针织 黑 M","goods_price":"39.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092203045216863.jpg"}],"status_text":"已取消","payment_name":""},{"order_id":41,"order_sn":"SP19062511165416968","store_id":1,"store_name":"DsMll","buyer_id":9,"buyer_name":"phone_18137127634","order_amount":"39.00","ziti_verify_code":null,"order_state":10,"lock_state":0,"total_goods_num":0,"ordergoods":[],"status_text":"待付款","payment_name":""}]}
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
         * total : 27
         * per_page : 20
         * current_page : 1
         * last_page : 2
         * data : [{"order_id":138,"order_sn":"SP19080312372362684","store_id":1,"store_name":"测试店铺","buyer_id":18,"buyer_name":"phone_18916977773","order_amount":"2198.00","ziti_verify_code":"1481d1c21bd1635f","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":250,"goods_guige":"样式1 红色 L","goods_name":"夏季新款女装真丝欧根纱圆领植绒印花无袖假两件宽松套头连衣长裙 样式1 红色 L","goods_price":"2198.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019080112002796773.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":136,"order_sn":"SP19080312292142507","store_id":1,"store_name":"测试店铺","buyer_id":19,"buyer_name":"嘻嘻","order_amount":"2198.00","ziti_verify_code":"fe9ddb119e23e9b2","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":256,"goods_guige":"样式2 绿色 L","goods_name":"夏季新款女装真丝欧根纱圆领植绒印花无袖假两件宽松套头连衣长裙 样式2 绿色 L","goods_price":"2198.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019080112002796773.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":128,"order_sn":"SP19072922043077133","store_id":1,"store_name":"测试店铺","buyer_id":16,"buyer_name":"豆豆","order_amount":"47.40","ziti_verify_code":"f146054a5a720606","order_state":10,"lock_state":0,"total_goods_num":3,"ordergoods":[{"goods_id":202,"goods_guige":"","goods_name":"花样游泳队指定 新面孔金眼霜 淡皱纹,隐眼袋,熬夜眼霜女","goods_price":"15.80","goods_num":3,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071910240941270.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":122,"order_sn":"SP19072909085728332","store_id":1,"store_name":"测试店铺","buyer_id":15,"buyer_name":"CC","order_amount":"47.40","ziti_verify_code":"c6a6d31695acbae3","order_state":0,"lock_state":0,"total_goods_num":3,"ordergoods":[{"goods_id":202,"goods_guige":"","goods_name":"花样游泳队指定 新面孔金眼霜 淡皱纹,隐眼袋,熬夜眼霜女","goods_price":"15.80","goods_num":3,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071910240941270.jpg"}],"status_text":"已取消","payment_name":""},{"order_id":120,"order_sn":"SP19072716031888114","store_id":1,"store_name":"测试店铺","buyer_id":15,"buyer_name":"CC","order_amount":"296.00","ziti_verify_code":"d6b89b95bcb416d6","order_state":"104","lock_state":1,"total_goods_num":2,"ordergoods":[{"goods_id":203,"goods_guige":"","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":"148.00","goods_num":2,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg"}],"status_text":"维权中-待平台打款","payment_name":""},{"order_id":119,"order_sn":"SP19072715593672019","store_id":1,"store_name":"测试店铺","buyer_id":15,"buyer_name":"CC","order_amount":"47.40","ziti_verify_code":"e30dd0c4e8bdbcc8","order_state":0,"lock_state":0,"total_goods_num":3,"ordergoods":[{"goods_id":202,"goods_guige":"","goods_name":"花样游泳队指定 新面孔金眼霜 淡皱纹,隐眼袋,熬夜眼霜女","goods_price":"15.80","goods_num":3,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071910240941270.jpg"}],"status_text":"已取消","payment_name":""},{"order_id":112,"order_sn":"SP19072619243711482","store_id":1,"store_name":"测试店铺","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"148.00","ziti_verify_code":"78c28872a4505bdb","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":203,"goods_guige":"","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":"148.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":111,"order_sn":"SP19072619243671483","store_id":1,"store_name":"测试店铺","buyer_id":15,"buyer_name":"CC","order_amount":"148.00","ziti_verify_code":"dc006dadf2190caa","order_state":0,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":203,"goods_guige":"","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":"148.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg"}],"status_text":"已取消","payment_name":""},{"order_id":110,"order_sn":"SP19072619243516339","store_id":1,"store_name":"测试店铺","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"148.00","ziti_verify_code":"b7cc92b991cf4d28","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":203,"goods_guige":"","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":"148.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":109,"order_sn":"SP19072619223576896","store_id":1,"store_name":"测试店铺","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"148.00","ziti_verify_code":"3c475784381a64e4","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":203,"goods_guige":"","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":"148.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":108,"order_sn":"SP19072619223153351","store_id":1,"store_name":"测试店铺","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"148.00","ziti_verify_code":"33b64e9f27197a48","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":203,"goods_guige":"","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":"148.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":107,"order_sn":"SP19072619112995363","store_id":1,"store_name":"测试店铺","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"148.00","ziti_verify_code":"25290be4cb794ec6","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":203,"goods_guige":"","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":"148.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":106,"order_sn":"SP19072619110595435","store_id":1,"store_name":"测试店铺","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"148.00","ziti_verify_code":"eea6f38fabb3acb1","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":203,"goods_guige":"","goods_name":"LES帅T林弯弯秋季单排扣格子西服男女韩国宽松休闲千鸟格西装外套","goods_price":"148.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071911265520426.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":94,"order_sn":"SP19072619012243887","store_id":1,"store_name":"测试店铺","buyer_id":9,"buyer_name":"1234sdfsdfsdf3","order_amount":"15.80","ziti_verify_code":"560c4e46ccc488ef","order_state":10,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":202,"goods_guige":"","goods_name":"花样游泳队指定 新面孔金眼霜 淡皱纹,隐眼袋,熬夜眼霜女","goods_price":"15.80","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019071910240941270.jpg"}],"status_text":"待付款","payment_name":""},{"order_id":133,"order_sn":"SP19070415130839243","store_id":1,"store_name":"DsMll","buyer_id":8,"buyer_name":"phone_15037142280","order_amount":"39.00","ziti_verify_code":"880bb0c9b8a3bf97","order_state":110,"lock_state":0,"total_goods_num":0,"ordergoods":[],"status_text":"","payment_name":""},{"order_id":73,"order_sn":"SP19070415130839243","store_id":1,"store_name":"DsMll","buyer_id":8,"buyer_name":"phone_15037142280","order_amount":"39.00","ziti_verify_code":"880bb0c9b8a3bf97","order_state":40,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":45,"goods_guige":"针织 黑 M","goods_name":"与牧2017韩版短款 针织 黑 M","goods_price":"39.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092203045216863.jpg"}],"status_text":"交易完成","payment_name":""},{"order_id":132,"order_sn":"SP19070415130839243","store_id":1,"store_name":"DsMll","buyer_id":8,"buyer_name":"phone_15037142280","order_amount":"39.00","ziti_verify_code":"880bb0c9b8a3bf97","order_state":100,"lock_state":0,"total_goods_num":0,"ordergoods":[],"status_text":"退款完成","payment_name":""},{"order_id":69,"order_sn":"SP19070119283365864","store_id":1,"store_name":"DsMll","buyer_id":8,"buyer_name":"phone_15037142280","order_amount":"39.00","ziti_verify_code":"2f19414de3ebd9f6","order_state":"101","lock_state":1,"total_goods_num":1,"ordergoods":[{"goods_id":45,"goods_guige":"针织 黑 M","goods_name":"与牧2017韩版短款 针织 黑 M","goods_price":"39.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092203045216863.jpg"}],"status_text":"维权中-待商家审核","payment_name":""},{"order_id":42,"order_sn":"SP19062511171477379","store_id":1,"store_name":"DsMll","buyer_id":9,"buyer_name":"phone_18137127634","order_amount":"39.00","ziti_verify_code":null,"order_state":0,"lock_state":0,"total_goods_num":1,"ordergoods":[{"goods_id":45,"goods_guige":"针织 黑 M","goods_name":"与牧2017韩版短款 针织 黑 M","goods_price":"39.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2017092203045216863.jpg"}],"status_text":"已取消","payment_name":""},{"order_id":41,"order_sn":"SP19062511165416968","store_id":1,"store_name":"DsMll","buyer_id":9,"buyer_name":"phone_18137127634","order_amount":"39.00","ziti_verify_code":null,"order_state":10,"lock_state":0,"total_goods_num":0,"ordergoods":[],"status_text":"待付款","payment_name":""}]
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
             * order_id : 138
             * order_sn : SP19080312372362684
             * store_id : 1
             * store_name : 测试店铺
             * buyer_id : 18
             * buyer_name : phone_18916977773
             * order_amount : 2198.00
             * ziti_verify_code : 1481d1c21bd1635f
             * order_state : 10
             * lock_state : 0
             * total_goods_num : 1
             * ordergoods : [{"goods_id":250,"goods_guige":"样式1 红色 L","goods_name":"夏季新款女装真丝欧根纱圆领植绒印花无袖假两件宽松套头连衣长裙 样式1 红色 L","goods_price":"2198.00","goods_num":1,"goods_image":"http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019080112002796773.jpg"}]
             * status_text : 待付款
             * payment_name :
             */

            private int order_id;
            private String order_sn;
            private int store_id;
            private int order_type;
            private String store_name;
            private int buyer_id;
            private String buyer_name;
            private String order_amount;
            private String ziti_verify_code;
            private int order_state;
            private int lock_state;
            private int total_goods_num;
            private String status_text;
            private String payment_name;
            private List<OrdergoodsBean> ordergoods;

            public int getOrder_type() {
                return order_type;
            }

            public void setOrder_type(int order_type) {
                this.order_type = order_type;
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
                 * goods_id : 250
                 * goods_guige : 样式1 红色 L
                 * goods_name : 夏季新款女装真丝欧根纱圆领植绒印花无袖假两件宽松套头连衣长裙 样式1 红色 L
                 * goods_price : 2198.00
                 * goods_num : 1
                 * goods_image : http://tsyc.jiefutong.net/uploads/home/store/goods/1/1_2019080112002796773.jpg
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
