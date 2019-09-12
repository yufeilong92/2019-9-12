package com.tsyc.tianshengyoucai.net;


import android.content.Context;

import com.tsyc.tianshengyoucai.constant.Config;
import com.tsyc.tianshengyoucai.utils.StringUtil;

/**
 * author：cxd
 * CreateTime：2019/7/15
 * File description：
 */
public class UrlManager {


    protected static final String baseUrl = Config.BASE_URL;

    //注册
    public static String register = baseUrl + "api/Connect/sms_register.html";

    //注册
    public static String register_agreement = baseUrl + "api/document/user_register";

    //发送验证码 1 注册 2为登录,3为找回密码 4 设置支付密码 5 修改手机号 6招聘绑定手机号
    public static String send_captcha = baseUrl + "index.php/api/Connect/get_sms_captcha.html";

    //登录
    public static String login = baseUrl + "index.php/api/Login/index.html";

    //登录
    public static String userInfo = baseUrl + "api/Member/index";

    //找回密码
    public static String find_password = baseUrl + "api/Connect/find_password";

    //微信登录
    public static String wx_login = baseUrl + "api/Wxlogin/getUserAccessUserInfo";

    //首页
    public static String index = baseUrl + "api/index";

    //公告
    public static String homeNote = baseUrl + "api/index/note";

    //商品详情
    public static String goodsDetail = baseUrl + "api/goods/goods_detail";

    //商品收藏
    public static String goodsCollect = baseUrl + "api/Member/collect";

    //发现界面
    public static String find = baseUrl + "api/index/find";

    //线下订单
    public static String underline_order = baseUrl + "api/Memberorder/order_line";

    //线上订单
    public static String online_list = baseUrl + "api/Memberorder/order_list";

    //成为商家协议
    public static String becomeShopAgree = baseUrl + "api/Member/agree";


    //上传图片 类型 store_apply店铺申请 goods_issue商品发布 evaluate评价 举报feedback 退货 goods_return 店铺门头照 foor
    public static String uploadImage = baseUrl + "api/Member/upload_image";

    //获取地区列表
    public static String area_list = baseUrl + "api/Area/area_list";

    //获取支付方式    1 充值 2 支付 3余额提现 4佣金提现 5店铺提现 6收银台提现
    public static String payType = baseUrl + "api/index/payment_type";

    //获取店铺提现规则
    public static String cashRule = baseUrl + "api/store_account/credit";

    //获取收银台提现规则
    public static String unsettled = baseUrl + "api/cashier.withdraw/unsettled";

    //获取佣金提现规则
    public static String withdrawDate = baseUrl + "api/member/withdrawDate";

    //充值接口
    public static String rechargeOrder = baseUrl + "api/member/rechargeOrder";


    //我的 店铺相关
    // 发布商品
    public static String addGoods = baseUrl + "api/Seller/add_goods";

    // 发布商品
    public static String addGoodsV2 = baseUrl + "api/Seller/add_goods_v2";

    // 获取分类模板
    public static String get_goods_templete = baseUrl + "api/seller/get_goods_templete";

    // 发布红包
    public static String relRedBag = baseUrl + "api/voucher/add_redpacket";

    // 发布 编辑  优惠券
    public static String relCoupon = baseUrl + "api/voucher/add_coupon";

    // 优惠券列表
    public static String couponList = baseUrl + "api/voucher/coupon_list";

    // 红包列表
    public static String redBagList = baseUrl + "api/voucher/redpackage_list";

    // 取消 优惠券 或 红包
    public static String cancelCouponBag = baseUrl + "api/voucher/voucher_cancel";

    // 优惠券 或 红包 领取详情
    public static String receiveDetail = baseUrl + "api/voucher/receive_list";


    // 成为商家
    public static String becomeShop = baseUrl + "api/Member/store_apply";
    // 申请提现
    public static String cashApply = baseUrl + "api/store_account/apply";

    // 获取店铺信息和审核状态
    public static String shopInfo = baseUrl + "api/Member/store_info";

    // 主营类目
    public static String mainCategory = baseUrl + "api/Member/category_one";

    // 商品列表
    public static String goodsList = baseUrl + "api/seller/goods_list";

    // 收入明细
    public static String incomeLog = baseUrl + "api/store_account/logs";

    // 评价管理
    public static String evaluateManage = baseUrl + "api/seller/evaluate_manage";

    // 商家回复
    public static String evaluateReply = baseUrl + "api/seller/evaluate_store_apply";

    // 订单管理
    public static String shopManage = baseUrl + "api/store_order/lists";

    // 查看物流
    public static String lookLogistics = baseUrl + "api/Memberorder/search_deliver";

    // 物流公司列表
    public static String logisticsCompanyList = baseUrl + "api/store_order/express_list";

    // 订单发货
    public static String orderSend = baseUrl + "api/store_order/send";

    // 订单详情
    public static String orderDetail = baseUrl + "api/store_order/detail";

    // 店铺详情页-头部信息
    public static String shopDetailHeader = baseUrl + "api/merchant_service/shopDetailHedaer";

    // 进店有礼 领取红包
    public static String receiveRedPack = baseUrl + "api/lottery/receiveRedPack";

    // 商家详情页选项卡--商品列表
    public static String productList = baseUrl + "api/merchant_service/productList";

    // 商家详情页选项卡--商家信息
    public static String shopDetail = baseUrl + "api/merchant_service/shopDetail";

    // 商家详情页选项卡--商家信息评价数据
    public static String detailComment = baseUrl + "api/merchant_service/detailComment";

    // 商家详情资质
    public static String certification = baseUrl + "api/merchant_service/certification";

    // 举报商家
    public static String reportStore = baseUrl + "api/merchant_service/report";

    //公告详情
    public static String homeNoteDetail = baseUrl+"api/index/note_detail";


    public static String getUrl(Context m, int id) {
        return baseUrl.concat(StringUtil.getStringWid(m, id));
    }

    //编辑商铺
    public static String storeEdit = baseUrl + "api/Member/store_edit";

    //我的店铺首页
    public static String storeIndex = baseUrl + "api/Seller/index";

    //领取优惠券
    public static String receiveCoupons = baseUrl + "api/member/receive_coupons";

    //上下架商品
    public static String goods_lockup = baseUrl + "api/seller/goods_lockup";

    //删除商品
    public static String goods_del = baseUrl + "api/seller/goods_del";

    //商品编辑 获取商品详情
    public static String goodsDetailEdit = baseUrl + "api/seller/goods_detail";

    //商家 不同意 、 同意退款
    public static String confirm_refund = baseUrl + "api/Seller/confirm_refund";

    //商家 用户退款 商家收货
    public static String store_confirm_receive = baseUrl + "api/store_order/receive";

    // 申请提现列表
    public static String cash_list = baseUrl + "api/store_account/lists";

    // 收银台提现申请列表
    public static String cashApplyList = baseUrl + "api/cashier.withdraw/lists";

    // 收银台提现申请
    public static String casherApply = baseUrl + "api/cashier.withdraw/apply";

    // 收银台首页信息
    public static String cashInfo = baseUrl + "api/cashier.user/info";

    // 店铺收藏
    public static String favorites_add = baseUrl + "api/merchant_service/favorites_add";

    // 店铺取消收藏
    public static String favorites_del = baseUrl + "api/merchant_service/favorites_del";

    // 取消订单
    public static String cancel_order = baseUrl + "api/Memberorder/quxiao_order";

    // 删除订单
    public static String del_order = baseUrl + "api/Memberorder/del_order";

    // 发表评价
    public static String evaluation_add = baseUrl + "api/Memberorder/evaluation_add";

    // 申请退款
    public static String refund_submit = baseUrl + "api/Memberorder/refund_submit";

    // 确认收货
    public static String confirm_receive = baseUrl + "api/Memberorder/confirm_receive";

    // 退款详情
    public static String back_detail = baseUrl + "api/memberorder/detail";

    // 退款 界面数据
    public static String refund_info = baseUrl + "api/Memberorder/refund_info";

    // 退货退款-物流公司列表
    public static String ship_form = baseUrl + "api/Memberreturn/ship_form";

    // 退货退款-用户填写退货物流单号
    public static String ship_post = baseUrl + "api/Memberreturn/ship_post";

    // 自提时间
    public static String taking_time = baseUrl + "api/Memberbuy/taking_time";

    // 商品购买界面
    public static String goods_buy = baseUrl + "api/Memberbuy/goods_buy";

    // 商品购买支付
    public static String order_pay = baseUrl + "api/Memberbuy/order_pay";

    // 商品购买 提交订单
    public static String submit_order = baseUrl + "api/Memberbuy/submit_order";

    // 线上线下订单详情
    public static String line_underline_order = baseUrl + "api/memberorder/detail";

    //店铺订单详情
    public static String store_order = baseUrl + "api/store_order/detail";

    //用户到店自取协议
    public static String service_taking = baseUrl + "api/Memberbuy/service_taking";

    //地图红包
    public static String red_envelope = baseUrl + "api/seller/red_envelope";

    //版本更新
    public static String update = baseUrl + "api/Index/update";

    //商家订单详情
    public static String storeDetail = baseUrl + "api/store_order/detail";

    //帮助中心h5
    public static String helpCenter = baseUrl + "mobile/help.html";

    //扫码核销订单
    public static String scan_order = baseUrl + "api/cashier.verify/scan";

    //确认核销订单
    public static String doVerify = baseUrl + "api/cashier.verify/doVerify";

    //用户扫描付款吗
    public static String userScan = baseUrl + "api/cashier.order/scan";

    //用户扫描付款吗后支付
    public static String userScanPay = baseUrl + "api/cashier.order/pay";

    // 收银台明细
    public static String cashierList = baseUrl + "api/cashier.order/lists";

    // 商品分类
    public static String shopCategory = baseUrl + "api/index/category";

    // 是不是内部人
    public static String is_inner = baseUrl + "api/member/is_inner";

    // 订单数
    public static String order_count = baseUrl + "api/member/order_count";

    // 余额 佣金提现
    public static String mine_withdraw = baseUrl + "api/member/withdraw";

    // 我的余额
    public static String my_balance = baseUrl + "api/member/my_balance";

    // 余额 佣金提现记录
    public static String withdrawLog = baseUrl + "api/member/withdrawLog";

    // 商业资讯 列表
    public static String getTypeList = baseUrl + "api/article/getTypeList";

    // 商业资讯 列表
    public static String getNewsList = baseUrl + "api/article/getNewsList";

    // 商业资讯 详情
    public static String gitArticle = baseUrl + "api/article/getArticle";

    // 招聘

    // 行业大全
    public static String industries = baseUrl + "api/recruit.cominfo/industries";

    // 职位大全
    public static String positions = baseUrl + "api/recruit.cominfo/positions";

    // 创建boss信息第一步：填写基础信息
    public static String addBaseinfo = baseUrl + "api/recruit.boss_info/addBaseinfo";

    // 根据公司名称查询公司信息
    public static String companySearch = baseUrl + "api/recruit.company/search";

    // 根据公司名称查询公司信息
    public static String companyAdd = baseUrl + "api/recruit.company/add";

    // boss实名认证（二）
    public static String checkIDinfo = baseUrl + "api/recruit.boss_info/CheckIDinfo";


    // boss发布职位选项
    public static String getCommonKeys = baseUrl + "api/recruit.cominfo/getCommonKeys";

    //商品发布协议接口
    public static String goods_notice = baseUrl + "api/index/goods_notice";

}
