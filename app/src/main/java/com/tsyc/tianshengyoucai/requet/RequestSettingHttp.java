package com.tsyc.tianshengyoucai.requet;

import android.app.Activity;
import android.content.Intent;

import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.manager.DataMangerVo;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.activity.login.LoginActivity;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.SaveUserInfomUtilJave;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.vo.UserInfomVo;

import java.util.HashMap;
import java.util.Map;

public class RequestSettingHttp extends BaseHttp {
    private volatile static RequestSettingHttp singleton;
    private Activity mContext;

    private RequestSettingHttp(Activity mContext) {
        this.mContext = mContext;
    }

    public static RequestSettingHttp getSingleton(Activity mContext) {
        if (singleton == null) {
            synchronized (RequestSettingHttp.class) {
                if (singleton == null) {
                    singleton = new RequestSettingHttp(mContext);
                }
            }
        }
        return singleton;
    }

    /**
     * 提交修改密码
     *
     * @param oldpsw
     * @param newpsw
     * @param callback
     */
    public void submit(String oldpsw, String newpsw, RequestResultCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("old_password", oldpsw);
        map.put("new_password", newpsw);
        map.put("new_password_confirm", newpsw);

        BaseRequestUtils.postRequestWithHeader(mContext, UrlManager.getUrl(mContext, R.string.http_changpaw),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {

                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * 修改用户手机号
     *
     * @param phone
     * @param code
     * @param callback
     */

    public void submitPhone(String phone, String code, RequestResultCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("mobile", phone);
        map.put("captcha", code);
        BaseRequestUtils.postRequestWithHeader(mContext, UrlManager.getUrl(mContext, R.string.http_changphone),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {

                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }


    /**
     * 修改用支付密码
     *
     * @param new_password
     * @param code
     * @param callback
     */

    public void submitPayPsw(String new_password, String code, RequestResultCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("new_password", new_password);
        map.put("new_password_confirm", new_password);
        map.put("captcha", code);
        BaseRequestUtils.postRequestWithHeader(mContext, UrlManager.getUrl(mContext, R.string.http_pay_psw),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {

                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * 请求我的金额
     *
     * @param page
     * @param callback
     */

    public void requestMyMoney(String page, RequestResultCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("page", page);
        map.put("pageNum", "10");
        BaseRequestUtils.postRequestWithHeader(mContext, UrlManager.getUrl(mContext, R.string.http_money),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {

                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }


    /**
     * 请求银行卡列表
     *
     * @param callback
     */

    public void requestBankList(RequestResultCallback callback) {

        BaseRequestUtils.postRequestWithHeader(mContext, UrlManager.getUrl(mContext, R.string.http_bank_list),
                null, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {

                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }


    /**
     * 请求银行卡列表
     *
     * @param callback
     */

    public void requestMyBankList(String key, RequestResultCallback callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("key", key);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext, R.string.http_my_bank_list),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {

                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * 请求银行卡列表
     *
     * @param callback
     */

    public void submitBank(String real_name, String id_card, String bank_name, String bank_number, String bank_mobile, String bank_sn, RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        String key = infom.getResult().getKey();
        HashMap<String, String> map = new HashMap<>();
        map.put("key", key);
        map.put("real_name", real_name);
        map.put("id_card", id_card);
        map.put("bank_name", bank_name);
        map.put("bank_number", bank_number);
        map.put("bank_mobile", bank_mobile);
        map.put("bank_sn", bank_sn);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext, R.string.http_bind_bank),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {

                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * 上传头像
     *
     * @param callback
     */

    public void submitHearAndNickName(String name, String path, RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        String key1 = result.getKey();
        HashMap<String, String> map = new HashMap<>();
        map.put("key", key1);
        map.put("nickname", name);
        map.put("avatar_url", path);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext, R.string.http_submit_hear_nick), map, new BaseRequestUtils.getRequestCallBack() {
            @Override
            public void success(Response<String> response) {
                callback.success(response.body().toString());
            }

            @Override
            public void failed(Response<String> response) {
                callback.error("");
            }
        });
    }


    /**
     * 请求我的红包
     *
     * @param stauts   不传参为有效，传为无效
     * @param callback
     */

    public void requestMyRedPacker(String page, String stauts, RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        String key1 = result.getKey();
        HashMap<String, String> map = new HashMap<>();
        map.put("key", key1);
        map.put("page", page);
        map.put("stauts", stauts);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext, R.string.http_my_red_packer),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {

                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * 请求我的红包
     *
     * @param stauts   不传参为有效，传为无效
     * @param callback
     */

    public void requestMyCouponList(String page, String stauts, RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        String key1 = result.getKey();
        HashMap<String, String> map = new HashMap<>();
        map.put("key", key1);
        map.put("page", page);
        map.put("stauts", stauts);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_my_voucher),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {

                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * 请求我的收货地址
     *
     * @param callback
     */

    public void requestMyaddressList(RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        String key1 = result.getKey();
        HashMap<String, String> map = new HashMap<>();
        map.put("key", key1);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext, R.string.http_my_address_lsit),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {

                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * 请求我的收货地址
     *
     * @param callback
     */

    public void deleteMyaddress(String id, RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        String key1 = result.getKey();
        HashMap<String, String> map = new HashMap<>();
        map.put("key", key1);
        map.put("address_id", id);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext, R.string.http_delete_address),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {

                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * 添加收货地址
     *
     * @param name
     * @param addres
     * @param phone
     * @param is_default
     * @param provicne
     * @param city
     * @param area
     * @param callback
     */

    public void submitaddress(String name, String addres, String phone, boolean is_default,
                              String provicne, String city, String area,
                              RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        String key1 = result.getKey();
        HashMap<String, String> map = new HashMap<>();
        map.put("key", key1);
        map.put("true_name", name);
        map.put("address", addres);
        map.put("mob_phone", phone);
        map.put("is_default", is_default ? "1" : "0");
        map.put("province_name", provicne);
        map.put("city_name", city);
        map.put("area_name", area);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext, R.string.http_add_address),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {

                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * 添加收货地址
     *
     * @param name
     * @param addres
     * @param phone
     * @param is_default
     * @param provicne
     * @param city
     * @param area
     * @param callback
     */

    public void submitMakeadress(String address_id, String name, String addres, String phone,
                                 String province_id, String city_id, String area_id,
                                 boolean is_default,
                                 String provicne, String city, String area,
                                 RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        String key1 = result.getKey();
        HashMap<String, String> map = new HashMap<>();
        map.put("key", key1);
        map.put("address_id", address_id);
        map.put("true_name", name);
        map.put("address", addres);
        map.put("mob_phone", phone);
        map.put("province_id", province_id);
        map.put("city_id", city_id);
        map.put("area_id", area_id);
        map.put("is_default", is_default ? "1" : "0");
        map.put("province_name", provicne);
        map.put("city_name", city);
        map.put("area_name", area);
        BaseRequestUtils.postRequestWithHeader(mContext, UrlManager.getUrl(mContext, R.string.http_make_address),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {

                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * 请求我的收货地址
     *
     * @param callback
     */

    public void requestPartner(RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        BaseRequestUtils.postRequestWithHeader(mContext, UrlManager.getUrl(mContext, R.string.http_partner_info),
                null, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {

                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * 请求我的收货地址
     *
     * @param callback
     */

    public void requestMyCollect(String type, String page, RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        String key = infom.getResult().getKey();
        map.put("key", key);
        map.put("page", page);
        map.put("type", type);
        map.put("pageNum", String.valueOf(DataMangerVo.PAGERE_NUMBER));
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext, R.string.http_my_collect),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {

                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * 取消收藏
     *
     * @param store_id 商品或店铺id
     * @param type     传此参数代表取消商品收藏 不传是取消店铺收藏
     * @param callback
     */

    public void submitCannerCollect(String store_id, String type, RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        String key = infom.getResult().getKey();
        HashMap<String, String> map = new HashMap<>();
        map.put("store_id", store_id);
        map.put("key", key);
        map.put("type", type);
        BaseRequestUtils.postRequestWithHeader(mContext, UrlManager.getUrl(mContext, R.string.http_canner_collect),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {

                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * 更多商家列表-排序分类
     *
     * @param callback
     */

    public void requestMoreType(RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        BaseRequestUtils.postRequestWithHeader(mContext, UrlManager.getUrl(mContext, R.string.http_more_shop),
                null, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {

                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * 更多商家列表
     *
     * @param tid      店铺分类id
     * @param page     当前条数
     * @param filter   条件过滤 参数 credit: 按好评排序 | sales 按销量排序
     * @param lon      地理位置-经度
     * @param lat      地理位置-纬度
     * @param callback
     */

    public void requestMoreShopList(String tid, String page, String filter, String lon, String lat, String keyword, RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("tid", tid);
        map.put("page", page);
        map.put("pageNum", String.valueOf(DataMangerVo.PAGERE_NUMBER));
        map.put("filter", filter);
        map.put("lon", lon);
        map.put("lat", lat);
        map.put("keyword", keyword);

//        List<ParamGET> list = getListParamt();
//        ParamGET paramVo = new ParamGET();
//        paramVo.setNama("tid");
//        paramVo.setValues(tid);
//        list.add(paramVo);
//
//        ParamGET paramt = getNewParamt();
//        paramt.setNama("page");
//        paramt.setValues(page);
//        list.add(paramt);
//
//        ParamGET paramt1 = getNewParamt();
//        paramt1.setNama("pageNun");
//        paramt1.setValues( String.valueOf(DataMangerVo.PAGERE_NUMBER));
//        list.add(paramt1);
//
//        ParamGET paramt2 = getNewParamt();
//        paramt2.setNama("filter");
//        paramt2.setValues( filter);
//        list.add(paramt2);
//
//        ParamGET paramt3 = getNewParamt();
//        paramt3.setNama("lon");
//        paramt3.setValues( lon);
//        list.add(paramt3);
//
//        ParamGET paramt4 = getNewParamt();
//        paramt4.setNama("lat");
//        paramt4.setValues( lat);
//        list.add(paramt4);
//
//        ParamGET paramt5 = getNewParamt();
//        paramt5.setNama("keyword");
//        paramt5.setValues( keyword);
//        list.add(paramt5);

        BaseRequestUtils.postRequestWithHeader(mContext, UrlManager.getUrl(mContext, R.string.http_shop_list),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {

                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * 商品评价列表-商品详情
     *
     * @param callback
     */

    public void requestEvaluateList(String goodid, String type, String page, RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("goods_id", goodid);
        map.put("type", type);
        map.put("page", page);
        map.put("pageNun", String.valueOf(DataMangerVo.PAGERE_NUMBER));
        BaseRequestUtils.postRequestWithHeader(mContext, UrlManager.getUrl(mContext, R.string.http_evaluate_list),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {

                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * 暂无使用
     * 注册
     *
     * @param phone    手机号
     * @param captcha  验证码
     * @param password 密码
     * @param invite   邀请码
     * @param callback
     */

    public void submitNewRegist(String phone, String captcha,
                                String password, String invite,
                                RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("captcha", captcha);
        map.put("password", password);
        map.put("invite", invite);
        BaseRequestUtils.postRequestWithHeader(mContext, UrlManager.getUrl(mContext, R.string.http_invite_regitst),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {

                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }


    /**
     * @param type     默认全部 1系统消息 2订单消息 3招聘消息
     * @param callback
     */

    public void requestMsgList(String type,
                               RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        UserInfomVo.ResultBean bean = infom.getResult();
        HashMap<String, String> map = new HashMap<>();
        map.put("key", bean.getKey());
        map.put("type", type);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_msg_list),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {

                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }


    /**
     * @param callback
     */

    public void requestHomeTypeList(
            RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        BaseRequestUtils.postRequestWithHeader(mContext, UrlManager.getUrl(mContext,
                R.string.http_home_type),
                null, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {

                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }


    /**
     * 商品列表（ 带搜索、排序、分类）
     *
     * @param keyword     搜索关键字
     * @param sort_type   排序类型 all（综合排序）sales（销量排序）price（价格排序）
     * @param sort_price  1（按价格降序排列） 0 （按价格升序排列 ）
     * @param category_id 分类id
     * @param callback
     */
    public void requestHomeSearch(String page, String keyword, String sort_type, String sort_price,
                                  String category_id,
                                  RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("keyword", keyword);
        map.put("page", page);
        map.put("sort_type", sort_type);
        map.put("sort_price", sort_price);
        map.put("category_id", category_id);
        BaseRequestUtils.postRequestWithHeader(mContext, UrlManager.getUrl(mContext,
                R.string.http_home_seach),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {

                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * @param callback
     */
    public void requestMyTeam(String page,
                              RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        String key = infom.getResult().getKey();
        HashMap<String, String> map = new HashMap<>();
        map.put("key", key);
        map.put("page", page);
        map.put("pageNum", String.valueOf(DataMangerVo.PAGERE_NUMBER));
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_my_tream),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {

                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * @param callback
     */
    public void submitWeiXinLogin(String code,
                                  RequestResultCallback callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("code", code);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_weixin_login),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {

                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * @param callback
     */
    public void submitBindWeiXinLogin(String mobile, String password, String key, String code,
                                      RequestResultCallback callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("mobile", mobile);
        map.put("captcha", code);
        map.put("password", password);
        map.put("key", key);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_bind_weixin),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {

                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * 绑定/修改支付宝
     *
     * @param callback
     */
    public void submitAlipayBind(String real_name, String account,
                                 RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        String key = result.getKey();
        HashMap<String, String> map = new HashMap<>();
        map.put("real_name", real_name);
        map.put("account", account);
        map.put("key", key);
        BaseRequestUtils.postRequestWithHeader(mContext, UrlManager.getUrl(mContext,
                R.string.http_bind_alipay),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {

                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * 绑定/修改支付宝
     *
     * @param callback
     */
    public void requestMyAlipay(RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        String key = result.getKey();
        HashMap<String, String> map = new HashMap<>();
        map.put("key", key);
        BaseRequestUtils.postRequestWithHeader(mContext, UrlManager.getUrl(mContext,
                R.string.http_my_alipay),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {

                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * 合伙人购买订单提交
     *
     * @param callback
     */
    public void submitPartnerOrder(String partner_id, String pay_type, RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("partner_id", partner_id);
        map.put("pay_type", pay_type);
        BaseRequestUtils.postRequestWithHeader(mContext, UrlManager.getUrl(mContext,
                R.string.http_partner_submit_order),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {
                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * 合伙人购买订单提交
     *
     * @param callback
     */
    public void requestShopService(String page, RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("page", page);
        map.put("pageNum", String.valueOf(DataMangerVo.PAGERE_NUMBER));
        BaseRequestUtils.postRequestWithHeader(mContext, UrlManager.getUrl(mContext,
                R.string.http_shop_service),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {
                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * 支付宝解绑
     *
     * @param callback
     */
    public void submitUnBindAlipay(String captcha, RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        String key = infom.getResult().getKey();
        HashMap<String, String> map = new HashMap<>();
        map.put("captcha", captcha);
        map.put("key", key);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_unbindAlipay),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {
                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * 删除消息
     *
     * @param callback
     */
    public void submitDeleteMsg(String message_id, RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("message_id", message_id);
        BaseRequestUtils.postRequestWithHeader(mContext, UrlManager.getUrl(mContext,
                R.string.http_delete_msg),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {
                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * 帮助中心
     *
     * @param callback
     */
    public void requestHelpList(String page, RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("page", page);
        BaseRequestUtils.postRequestWithHeader(mContext, UrlManager.getUrl(mContext,
                R.string.http_help_list),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {
                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * 帮助中心
     *
     * @param callback
     */
    public void requestHelpDetail(String help_id, RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("help_id", help_id);
        BaseRequestUtils.postRequestWithHeader(mContext, UrlManager.getUrl(mContext,
                R.string.http_help_detail),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {
                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * 解绑微信
     *
     * @param callback
     */
    public void submitDeleteWeiXin(RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        String key = infom.getResult().getKey();
        HashMap<String, String> map = new HashMap<>();
        map.put("key", key);
        BaseRequestUtils.postRequestWithHeader(mContext, UrlManager.getUrl(mContext,
                R.string.http_delete_weixin),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {
                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * 解绑微信
     *
     * @param callback
     */
    public void requestMsgNumber(RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }

        BaseRequestUtils.postRequestWithHeader(mContext, UrlManager.getUrl(mContext,
                R.string.http_msg_number),
                null, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {
                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * 充值或支付方式
     *
     * @param type     1 充值 2 支付 3余额提现 4佣金提现 5店铺提现 6收银台提现
     * @param callback
     */
    public void requestPayType(String type, RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("type", type);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_payment),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {
                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * 余额充值
     *
     * @param callback
     */
    public void requestPayValues(RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        BaseRequestUtils.postRequestWithHeader(mContext, UrlManager.getUrl(mContext,
                R.string.http_recharge_amount),
                null, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {
                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * 我的金币
     *
     * @param type     类型字段 不填默认全部 1 收入 2 支出
     * @param page
     * @param callback
     */
    public void requestGoldCoin(String type, String page, RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("type", type);
        map.put("page", page);
        BaseRequestUtils.postRequestWithHeader(mContext, UrlManager.getUrl(mContext,
                R.string.http_gold_coin),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {
                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }

    /**
     * 绑定微信
     *
     * @param callback
     */
    public void submitBindWeixinMine(String code, RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        String key = infom.getResult().getKey();
        HashMap<String, String> map = new HashMap<>();
        map.put("code", code);
        map.put("key", key);
        BaseRequestUtils.postRequestWithHeader(mContext, UrlManager.getUrl(mContext,
                R.string.http_bind_weixinmine),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {
                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });
    }


    /**
     * 删除银行卡
     *
     * @param callback
     */

    public void submitDeleteCard(String account_id, RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        String key = infom.getResult().getKey();
        Map<String, String> map = new HashMap<>();
        map.put("account_id", account_id);
        BaseRequestUtils.postRequestWithHeader(mContext, UrlManager.getUrl(mContext,
                R.string.http_jobs_deleteCard),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {
                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });

    }

    /**
     * 编辑银行卡信息
     *
     * @param callback
     */

    public void submiteditbankcard(String real_name, String id_card, String bank_name, String bank_number,
                                   String bank_mobile, String bank_sn, String account_id, RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        String key = infom.getResult().getKey();
        Map<String, String> map = new HashMap<>();
        map.put("account_id", account_id);
        map.put("real_name", real_name);
        map.put("id_card", id_card);
        map.put("bank_name", bank_name);
        map.put("bank_number", bank_number);
        map.put("bank_mobile", bank_mobile);
        map.put("bank_sn", bank_sn);
        BaseRequestUtils.postRequestWithHeader(mContext, UrlManager.getUrl(mContext,
                R.string.http_jobs_editbankcard),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {
                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });

    }

    /**
     * 请求我的服务
     *
     * @param callback
     */

    public void requestMyService(RequestResultCallback callback) {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean result = infom.getResult();
        if (infom == null || result == null || StringUtil.isEmpty(result.getKey())) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
            return;
        }
        String key = infom.getResult().getKey();
        Map<String, String> map = new HashMap<>();
        map.put("key", key);
        BaseRequestUtils.postRequestWithHeader(mContext, UrlManager.getUrl(mContext,
                R.string.http_jobs_MyService),
                map, new BaseRequestUtils.getRequestCallBack() {
                    @Override
                    public void success(Response<String> response) {
                        callback.success(response.body().toString());
                    }

                    @Override
                    public void failed(Response<String> response) {
                        callback.error("");

                    }
                });

    }

}
