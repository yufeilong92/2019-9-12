package com.tsyc.tianshengyoucai.requet;

import android.app.Activity;
import android.content.Intent;

import com.lzy.okgo.model.Response;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.net.BaseRequestUtils;
import com.tsyc.tianshengyoucai.net.UrlManager;
import com.tsyc.tianshengyoucai.ui.activity.login.LoginActivity;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.SaveUserInfomUtilJave;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.vo.UserInfomVo;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/30 16:56
 * @Purpose :
 */
public class RequestBossHttp {
    private volatile static RequestBossHttp singleton;
    private static Activity mContext;

    private RequestBossHttp(Activity mContext) {
        this.mContext = mContext;
    }

    public static RequestBossHttp getSingleton(Activity mContext) {
        if (singleton == null) {
            synchronized (RequestBossHttp.class) {
                if (singleton == null) {
                    singleton = new RequestBossHttp(mContext);
                }
            }
        }
        return singleton;
    }

    /**
     * 添加boss基本信息（一）
     *
     * @param avatar     头像
     * @param username   姓名（昵称）
     * @param company_id 公司id
     * @param duties     职务
     * @param email      邮箱
     * @param callback
     */

    public void submitAddBaseinfo(String avatar, String username, String company_id, String duties, String email,
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
        Map<String, String> map = new HashMap<>();
        map.put("key", key);
        map.put("avatar", avatar);
        map.put("username", username);
        map.put("company_id", company_id);
        map.put("duties", duties);
        map.put("email", email);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_boss_addBaseinfo),
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
     * 根据公司名称查询公司信息
     *
     * @param callback
     */

    public void requestCompanySearch(String keywords,
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
        Map<String, String> map = new HashMap<>();
        map.put("key", key);
        map.put("keywords", keywords);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_boss_companysearch),
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
     * boss实名认证（二）
     *
     * @param callback
     */

    public void submitCheckIDinfo(String real_name, String IDcard_number,
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
        Map<String, String> map = new HashMap<>();
        map.put("key", key);
        map.put("real_name", real_name);
        map.put("IDcard_number", IDcard_number);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_boss_checkidinfo),
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
     * 新建公司信息
     *
     * @param full_name    公司全称
     * @param short_name   公司简称
     * @param industries   行业id
     * @param company_size 公司规模
     * @param license      营业执照
     * @param callback
     */

    public void submitCompanyADD(String full_name, String short_name, String industries, String company_size, String license,
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
        Map<String, String> map = new HashMap<>();
        map.put("key", key);
        map.put("full_name", full_name);
        map.put("short_name", short_name);
        map.put("industries", industries);
        map.put("company_size", company_size);
        map.put("license", license);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_boss_companyadd),
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
     * -在招职位名称表
     *
     * @param callback
     */

    public void requestPositions(RequestResultCallback callback) {
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
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_boss_positions),
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
     * 发布新职位（三）
     *
     * @param position_name   职位名称
     * @param position_id     职位类型
     * @param job_type        工作性质
     * @param work_experience 工作经验
     * @param education       学历要求
     * @param salary          薪资范围
     * @param highlights      职位亮点
     * @param desc            职位描述
     * @param province_name   省
     * @param city_name
     * @param area_name
     * @param address         详细地址
     * @param boss_position_id         职位id
     * @param callback
     */

    public void submitaddPosition(String position_name, String position_id, String job_type, String work_experience, String education,
                                  String salary, String highlights, String desc, String province_name, String city_name, String area_name,
                                  String address,String boss_position_id, RequestResultCallback callback) {
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
        map.put("position_name", position_name);
        map.put("position_id", position_id);
        map.put("job_type", job_type);
        map.put("work_experience", work_experience);
        map.put("education", education);
        map.put("salary", salary);
        map.put("highlights", highlights);
        map.put("desc", desc);
        map.put("province_name", province_name);
        map.put("city_name", city_name);
        map.put("area_name", area_name);
        map.put("address", address);
        map.put("boss_position_id", boss_position_id);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
               StringUtil.isEmpty(boss_position_id) ?R.string.http_boss_addPosition:R.string.http_boss_editPosition),
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
     * boss-我的-个人信息
     *
     * @param callback
     */

    public void requestBossInfom(RequestResultCallback callback) {
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

        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_boss_bossInfom),
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
     * boss-我的-个人信息(我的界面)
     *
     * @param callback
     */

    public void requestBossMe(RequestResultCallback callback) {
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

        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_boss_bossme),
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
     * 编辑boss个人信息
     *
     * @param callback
     */

    public void submitEditBossInfom(String avatar, String username, String duties,
                                    String email, String sex, String mobile, RequestResultCallback callback) {
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
        map.put("avatar", avatar);
        map.put("username", username);
        map.put("duties", duties);
        map.put("email", email);
        map.put("sex", sex);
        map.put("mobile", mobile);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_boss_editbossinfom),
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
     * boss-职位管理-职位列表
     *
     * @param callback
     */

    public void requestBossList(String status, String page, RequestResultCallback callback) {
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
        map.put("status", status);
        map.put("page", page);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_boss_bosslists),
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
     * boss-我的-公司信息详情
     *
     * @param callback
     */

    public void requestBossCompanyDetail(RequestResultCallback callback) {
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
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_boss_bossdetail),
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
     * 编辑公司信息
     *
     * @param short_name   公司简称
     * @param industries   行业id
     * @param company_size 公司规模
     * @param logo         logo照片
     * @param desc         公司简介
     * @param company_id   公司id
     * @param province_id
     * @param city_id
     * @param area_id
     * @param address
     * @param callback
     */

    public void submitBossChangeInfom(String short_name, String industries, String company_size,
                                      String logo, String desc, String company_id, String province_id,
                                      String city_id, String area_id, String address,
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
        Map<String, String> map = new HashMap<>();
        map.put("key", key);
        map.put("short_name", short_name);
        map.put("industries", industries);
        map.put("company_size", company_size);
        map.put("logo", logo);
        map.put("desc", desc);
        map.put("company_id", company_id);
        map.put("province_name", province_id);
        map.put("city_name", city_id);
        map.put("area_name", area_id);
        map.put("address", address);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_boss_changerinfom),
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
     * @param type     1待查看简介 2 是否邀请面试  3对我感兴趣
     * @param page
     * @param callback
     */
    public void requestBossDeliverLists(int type, String page, RequestResultCallback callback) {
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
        map.put("page", page);
        int url=R.string.http_boss_check;
        switch (type) {
            case 1:
                url = R.string.http_boss_check;
                break;
            case 2:
                url = R.string.http_boss_interview;
                break;
            case 3:
                url = R.string.http_boss_send_like_me;
                break;
            default:
                break;
        }

        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                url),
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
     * boss首页--在招职位简历推荐（搜索）
     *
     * @param position_id     职位id
     * @param education       (选填）学历筛选
     * @param work_experience (选填）工作经验年限筛选
     * @param salary          (选填）期望薪资筛选
     * @param current_status  (选填）当前状态筛选
     * @param page
     * @param callback
     */
    public void requestBossCvRecommend(String position_id, String education, String work_experience,
                                       String salary, String current_status, String page, RequestResultCallback callback) {
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
        map.put("page", page);
        map.put("position_id", position_id);
        map.put("education", education);
        map.put("work_experience", work_experience);
        map.put("salary", salary);
        map.put("current_status", current_status);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_boss_cv_recommend),
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
     * @param ask      true 简历 false 面试
     * @param page
     * @param callback
     */
    public void requestBossOtherList(boolean ask, String page, RequestResultCallback callback) {
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
        map.put("page", page);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                ask ? R.string.http_boss_cv_logs : R.string.http_boss_interview_logs),
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
     * 更换公司
     *
     * @param callback
     */
    public void submitBosschangeCompany(String new_company_id, RequestResultCallback callback) {
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
        map.put("new_company_id", new_company_id);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_boss_changeCompany),
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
     * 查看简历详情
     *
     * @param callback
     */
    public void requestBossCvDetail(boolean isHome, String send_id, RequestResultCallback callback) {
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
        if (isHome)
            map.put("cv_id", send_id);
        else
            map.put("send_id", send_id);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                isHome ? R.string.http_boss_home_cv_detail : R.string.http_boss_cv_detail),
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
     * 职位详情
     *
     * @param callback
     */
    public void requestBossJobDetail(String boss_position_id, RequestResultCallback callback) {
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
        map.put("boss_position_id", boss_position_id);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_boss_job_detail),
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
     * -职位起停用
     *
     * @param callback
     */
    public void submitBossJobOpenOrStop(boolean isopen, String boss_position_id, RequestResultCallback callback) {
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
        map.put("boss_position_id", boss_position_id);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                isopen ? R.string.http_boss_job_open : R.string.http_boss_job_stop),
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
     * 面试详情
     *
     * @param send_id  简历id
     * @param time     面试时间
     * @param callback
     */
    public void requestBossInterviewDetail(String send_id, String time, RequestResultCallback callback) {
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
        map.put("send_id", send_id);
        map.put("time", time);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_boss_interview_detail),
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
     * @param type     1标记为不合适  2标记面试未到 3标记面试结束
     * @param send_id  简历id
     * @param callback
     */
    public void submitBossInterviewResult(int type, String send_id, RequestResultCallback callback) {
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
        map.put("send_id", send_id);

        int url = 0;
        switch (type) {
            case 1:
                url = R.string.http_boss_not_match;
                break;
            case 2:
                url = R.string.http_boss_not_come;
                break;
            case 3:
                url = R.string.http_boss_end_interview;
                break;

        }

        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                url),
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
     * 发起面试邀请
     *
     * @param send_id  投递记录id
     * @param time     面试时间
     * @param callback
     */
    public void submitSendBossInterview(String send_id, String time, RequestResultCallback callback) {
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
        map.put("send_id", send_id);
        map.put("time", time);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_boss_send_interview),
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
     * boss 消息板块列表页面初始化内容
     * @param callback
     */
    public void requestBosslistInit(String page, RequestResultCallback callback) {
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
        map.put("page",page);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_boss_list_init),
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
     *boss 向求职者发起沟通对话
     * @param callback
     */
    public void submitBossStartTalk(String boss_position_id,String cv_id, RequestResultCallback callback) {
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
        map.put("boss_position_id", boss_position_id);
        map.put("cv_id", cv_id);

        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_boss_start_talk),
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
     *boss端单个对话沟通页面初始化
     * @param callback
     */
    public void requestBossTalkInit(String page,String record_id, RequestResultCallback callback) {
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
        map.put("record_id", record_id);
        map.put("page", page);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_boss_talk_init),
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
