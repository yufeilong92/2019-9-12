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
import java.util.List;
import java.util.Map;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/19 13:58
 * @Purpose :求职模块
 */
public class RequestJobHttp extends BaseHttp {


    private volatile static RequestJobHttp singleton;
    private static Activity mContext;

    private RequestJobHttp(Activity mContext) {
        this.mContext = mContext;
    }

    public static RequestJobHttp getSingleton(Activity mContext) {
        if (singleton == null) {
            synchronized (RequestJobHttp.class) {
                if (singleton == null) {
                    singleton = new RequestJobHttp(mContext);
                }
            }
        }
        return singleton;
    }


    /**
     * 添加、编辑用户基本信息（一）
     *
     * @param avatar           头像
     * @param username         姓名
     * @param sex              性别 1女 2男
     * @param birthday
     * @param in_work          参加工作时间
     * @param living_city_name 现居住城市
     * @param mobile           手机号
     * @param email
     * @param callback
     */

    public void submitJobInfom(String avatar, String username,
                               String sex, String birthday, String in_work,
                               String living_city_name, String mobile, String email,
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
        map.put("avatar", avatar);
        map.put("username", username);
        map.put("sex", sex);
        map.put("birthday", birthday);
        map.put("in_work", in_work);
        map.put("living_city_name", living_city_name);
        map.put("mobile", mobile);
        map.put("email", email);
        map.put("key", key);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_addBaseInfo),
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
     * 职位大全
     *
     * @param callback
     */

    public void requestPositions(String select,
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
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_positions),
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
     * 职位大全
     *
     * @param callback
     */

    public void requestIndustries(String select,
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
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_industries),
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

    /***
     * 招聘入口
     * @param callback
     */
    public void requestTecruitIn(RequestResultCallback callback) {
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
                R.string.http_recruithome_in),
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

    /***
     * 求职者消息板块列表页面初始化内容
     * @param callback
     */
    public void requestJobsMsgLists(String page, RequestResultCallback callback) {
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
                R.string.http_re_employee),
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

    /***
     * 求职首页轮播图
     * @param callback
     */
    public void requestJobsBanner(RequestResultCallback callback) {
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
                R.string.http_jobs_banner),
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

    /***
     * 通用选项卡 key-value
     * @param callback
     */
    public void requestJobsCommonKeys(RequestResultCallback callback) {
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
                R.string.http_jobs_CommonKeys),
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

    /***
     *     推荐职位
     * @param callback
     */
    public void requestJobsRecommend(String page, RequestResultCallback callback) {
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
                R.string.http_jobs_recommend),
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

    /***
     * 选择/切换身份
     * @param callback
     */
    public void requestJobsChangeIdEntity(String identity, RequestResultCallback callback) {
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
        map.put("identity", identity);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_jobs_changeIdentity),
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

    /***
     * 添加教育经历（二）(编辑)
     * @param callback
     */
    public void submitAddEdu(String school, String education, String speciality, String start_time, String end_time,
                             String desc, String education_id, RequestResultCallback callback) {
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
        map.put("school", school);
        map.put("education", education);
        map.put("speciality", speciality);
        map.put("start_time", start_time);
        map.put("end_time", end_time);
        map.put("desc", desc);
        map.put("education_id", education_id);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                StringUtil.isEmpty(education_id) ? R.string.http_jobs_addEducation :
                        R.string.http_jobs_editEducation),
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
     * 添加工作经历（三）
     *
     * @param company    公司名称
     * @param position   职位
     * @param start_time 工作内容
     * @param end_time   入职时间
     * @param desc       离职时间
     * @param work_id    工作经历id
     * @param callback
     */
    public void submitWorkExperience(String company, String position, String start_time, String end_time,
                                     String desc, String work_id, RequestResultCallback callback) {
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
        map.put("company", company);
        map.put("position", position);
        map.put("start_time", start_time);
        map.put("end_time", end_time);
        map.put("desc", desc);
        map.put("work_id", work_id);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                StringUtil.isEmpty(work_id) ?
                        R.string.http_jobs_addWorkExperience : R.string.http_jobs_editWorkExperience),
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
     * 添加、编辑求职意向（四）
     *
     * @param job_type          工作性质 见keys表 1全职 2兼职
     * @param current_status    求职状态 见keys表 1离职-随时到岗 2在职-月内到岗 3在职-考虑机会 4在职-暂不考虑
     * @param expected_position 期望职位id
     * @param expected_industry 期望行业id(多选 ,隔开) 0不限制
     * @param expected_salary   期望薪资 见keys表
     * @param work_city_name    工作城市
     * @param callback
     */

    public void submitaddExpectJobInfo(String job_type, String current_status, String expected_position, String expected_industry,
                                       String expected_salary, String work_city_name, RequestResultCallback callback) {
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
        map.put("job_type", job_type);
        map.put("current_status", current_status);
        map.put("expected_position", expected_position);
        map.put("expected_industry", expected_industry);
        map.put("expected_salary", expected_salary);
        map.put("work_city_name", work_city_name);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_jobs_addExpectJobInfo),
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
     * 添加、编辑个人评价（五）
     *
     * @param self_assessment 个人评价
     * @param callback
     */

    public void submitaddSelfAssessment(String self_assessment, RequestResultCallback callback) {
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
        map.put("self_assessment", self_assessment);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_jobs_addSelfAssessment),
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
     * 我的投递列表
     *
     * @param state    全部 all 已投递send 已查看check 邀面试interview 不合适 unmatch
     * @param callback
     */

    public void requestSendList(String state, String page, RequestResultCallback callback) {
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
        map.put("state", state);
        map.put("page", page);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_jobs_sendList),
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
     * 隐私设置
     *
     * @param callback
     */

    public void requestPrivaySetting(RequestResultCallback callback) {
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
                R.string.http_jobs_setting),
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
     * 修改绑定手机号
     *
     * @param callback
     */

    public void submitChangeMobile(String mobile, String captcha, int id, RequestResultCallback callback) {
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
        map.put("mobile", mobile);
        map.put("captcha", captcha);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                id),
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
     * 修改绑定邮箱
     *
     * @param callback
     */

    public void submitchangeEmail(String email, RequestResultCallback callback) {
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
        map.put("email", email);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_jobs_changeEmail),
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
     * 隐藏显示简历
     *
     * @param callback
     */

    public void submitchangeState(String state, RequestResultCallback callback) {
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
        map.put("state", state);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_jobs_changeState),
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
     * 求职者个人中心
     *
     * @param callback
     */

    public void requestIndxeMine(RequestResultCallback callback) {
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
                R.string.http_jobs_rcmin),
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
     * 求职者个人中心
     *
     * @param callback
     */

    public void requestMyVc(RequestResultCallback callback) {
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
                R.string.http_jobs_myCV),
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
     * 删除工作经历
     *
     * @param callback
     */

    public void submitDeleteWork(String work_id, RequestResultCallback callback) {
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
        map.put("work_id", work_id);

        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_jon_delWorkExperience),
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
     * 删除教育经历
     *
     * @param callback
     */

    public void submitDeleteEdu(String education_id, RequestResultCallback callback) {
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
        map.put("education_id", education_id);

        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_jon_delEducation),
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
     * 职位收藏列表
     *
     * @param callback
     */

    public void requestJobMyLickList(String page, RequestResultCallback callback) {
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
                R.string.http_jon_my_like),
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
     * 取消职位收藏
     *
     * @param callback
     */

    public void subimtCannerCollectJob(String boss_position_id, RequestResultCallback callback) {
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
                R.string.http_jon_canceljob),
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
     * 编辑求职状态
     *
     * @param current_status 求职状态 见keys表 1离职-随时到岗 2在职-月内到岗 3在职-考虑机会 4在职-暂不考虑
     * @param callback
     */

    public void subimtEditCurrentStatus(String current_status, RequestResultCallback callback) {
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
        map.put("current_status", current_status);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_jon_editCurrentStatus),
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
     * @param position_id
     * @param callback
     */
    public void requestPositionDetail(String position_id, RequestResultCallback callback) {
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
        map.put("position_id", position_id);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_boss_position_detail),
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
     * 取消职位收藏
     *
     * @param isCollect        true 收藏， fasle 取消
     * @param boss_position_id
     * @param callback
     */
    public void submitJobCollect(boolean isCollect, String boss_position_id, RequestResultCallback callback) {
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
                isCollect ? R.string.http_boss_position_add : R.string.http_boss_position_cancel),
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
     * 举报公司
     *
     * @param callback
     */
    public void submitCompanyReport(String company_id, String content, List<String> list, RequestResultCallback callback) {
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
        map.put("company_id", company_id);
        map.put("content", content);
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                map.put("image" + i, list.get(i));
            }
        }
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                 R.string.http_boss_company_report),
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
     * 投递简历
     *
     * @param callback
     */
    public void submitCv_Send(String position_id, RequestResultCallback callback) {
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
        map.put("position_id", position_id);

        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_boss_cv_send),
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
     * 附近职位
     *
     * @param callback
     */
    public void requestNearestPosition(String city_name,String page, RequestResultCallback callback) {
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
        map.put("city_name", city_name);
        map.put("page", page);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_boss_nearest_position),
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
    /**全职、兼职、全部职位
     *
     * @param callback
     */
    public void requestPositionlist(String job_type,String page, RequestResultCallback callback) {
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
        map.put("job_type", job_type);
        map.put("page", page);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_boss_position_list),
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
     *职位搜索
     * @param callback
     */
    public void requestPositionSearch(String keywords,String city_name,String search_type,String page, RequestResultCallback callback) {
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
        map.put("city_name", city_name);
        map.put("search_type", search_type);
        map.put("page", page);
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_boss_position_search),
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
     *求职者向boss发起沟通对话
     * @param callback
     */
    public void submitEmployeeStartTalk(String boss_position_id, RequestResultCallback callback) {
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
                R.string.http_boss_employee_start_talk),
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
     *求职者端单个对话沟通页面初始化
     * @param callback
     */
    public void requestEmployeeTalkInit(String record_id, RequestResultCallback callback) {
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
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_boss_employee_talk_init),
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
     *投递记录详情
     * @param callback
     */
    public void requestSendDetail(String send_id, RequestResultCallback callback) {
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
        BaseRequestUtils.postRequest(mContext, UrlManager.getUrl(mContext,
                R.string.http_job_sendDetail),
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
