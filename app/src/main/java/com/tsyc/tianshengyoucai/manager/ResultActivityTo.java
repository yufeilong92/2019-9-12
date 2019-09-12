package com.tsyc.tianshengyoucai.manager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.tsyc.tianshengyoucai.MainActivity;
import com.tsyc.tianshengyoucai.ui.ImagerActivity;
import com.tsyc.tianshengyoucai.ui.activity.home.GoodsDetailActivity;
import com.tsyc.tianshengyoucai.ui.activity.home.MapRedBagActivity;
import com.tsyc.tianshengyoucai.ui.activity.home.ShopDetailActivity;
import com.tsyc.tianshengyoucai.ui.activity.home.ShopListActivity;
import com.tsyc.tianshengyoucai.ui.activity.home.ShopServiceListActivity;
import com.tsyc.tianshengyoucai.ui.activity.login.BindPhoneActivity;
import com.tsyc.tianshengyoucai.ui.activity.login.LoginActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.MineCashActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.UserInfoActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.address.AddAddressActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.bank.AddBankActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.bank.BindAlipayActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.bank.MyBankListActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.bank.UnBindAlipayActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.help.HelpDetailActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.money.PayActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.shop.EvaluteListActivity;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.recruit.ChangerJobPhoneActivity;
import com.tsyc.tianshengyoucai.ui.recruit.JobDetailActivity;
import com.tsyc.tianshengyoucai.ui.recruit.JobInfomActivity;
import com.tsyc.tianshengyoucai.ui.recruit.LookResumeActivity;
import com.tsyc.tianshengyoucai.ui.recruit.RecruitHomeActivity;
import com.tsyc.tianshengyoucai.ui.recruit.RecruitInActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.BossChangeCompanyActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.BossCompanyInfomActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.BossHomeActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.BossInterviewActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.BossLookResumeActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.BossOtherJobResultActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.BossSendInterViewActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.PostManageActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.ReleaseJobActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.sign.BossNameActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.sign.BossWorkAreaActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.sign.BossWorkDescribeActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.sign.CompanyNameSignActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.sign.CompanySearchActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.sign.CompanyTradeActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.sign.CompanyVerifyActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.sign.CreateBossCardActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.sign.NewCompanyActivity;
import com.tsyc.tianshengyoucai.ui.recruit.chat.ChatBoss2Activity;
import com.tsyc.tianshengyoucai.ui.recruit.chat.ChatJobActivity;
import com.tsyc.tianshengyoucai.ui.recruit.infom.EducationInfomActivity;
import com.tsyc.tianshengyoucai.ui.recruit.infom.JobEvaluateActivity;
import com.tsyc.tianshengyoucai.ui.recruit.infom.JobPurposeActivity;
import com.tsyc.tianshengyoucai.ui.recruit.infom.JobSelectActivity;
import com.tsyc.tianshengyoucai.ui.recruit.infom.TradeActivity;
import com.tsyc.tianshengyoucai.ui.recruit.infom.WorkInfomActivity;
import com.tsyc.tianshengyoucai.ui.recruit.jobmine.PositonSearchActivity;
import com.tsyc.tianshengyoucai.vo.AddressListBeanVo;
import com.tsyc.tianshengyoucai.vo.DeliverBossVo;
import com.tsyc.tianshengyoucai.vo.HelpVo;
import com.tsyc.tianshengyoucai.vo.MyBankListVo;
import com.tsyc.tianshengyoucai.vo.ResumeVo;
import com.tsyc.tianshengyoucai.vo.SearchGoodVo;
import com.tsyc.tianshengyoucai.vo.ShopServiceVo;

import java.util.ArrayList;
import java.util.List;

public class ResultActivityTo extends StartActivityManger {
    public ResultActivityTo(Activity m) {
        super(m);
    }

    public void startLogin(BaseActivity mContext) {
        jumpTo(mContext, LoginActivity.class);
    }

    //评论界面
    public void startGoodEvalueat(Activity goodsDetailActivity, int goods_id) {
        Bundle bundle = new Bundle();
        bundle.putString(EvaluteListActivity.GOODID, String.valueOf(goods_id));
        jumpTo(goodsDetailActivity, EvaluteListActivity.class, bundle, "评价管理");
    }

    public void startAddMoney(BaseActivity mContext) {
        jumpTo(mContext, PayActivity.class, "充值");
    }

    //评论界面
    public void startTiXianMoney(BaseActivity mContext) {

//        jumpTo(mContext, MineCashActivity.class, "提现");
        Bundle bundle = new Bundle();
        bundle.putString("type", "0");
        mContext.openActivity(MineCashActivity.class, bundle);
    }

    public void startBankList(Context m) {
        jumpTo(m, MyBankListActivity.class, "我的银行卡");
    }

    public void startAddBankList(Context m) {
        jumpTo(m, AddBankActivity.class, "添加银行卡");
    }

    public void startAddBankList(Context m, MyBankListVo.ResultBean bean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(AddBankActivity.IDCARDTYPE, bean);
        jumpTo(m, AddBankActivity.class, bundle, "编辑银行卡");
    }

    public void startAddAddress(Context mContext) {
        jumpTo(mContext, AddAddressActivity.class, "添加收货地址");
    }

    public void startAddAddress(Context mContext, AddressListBeanVo vo) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(AddAddressActivity.KEY, vo);
        jumpTo(mContext, AddAddressActivity.class, bundle, "修改收货地址");
    }

    public void startShop(Activity goodsDetailActivity, int store_id) {
        Bundle bundle = new Bundle();
        bundle.putString("store_id", String.valueOf(store_id));
        jumpTo(goodsDetailActivity, ShopDetailActivity.class, bundle);

    }

    public void startEvaluateImager(Context mContext, int postion, ArrayList<String> list) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(ImagerActivity.listType, list);
        bundle.putInt(ImagerActivity.postion, postion);
        jumpTo(mContext, ImagerActivity.class, bundle, "图片查看");
    }

    public void startEvaluateImager(Context mContext, int postion, List<String> list) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(ImagerActivity.listType, (ArrayList<String>) list);
        bundle.putInt(ImagerActivity.postion, postion);
        jumpTo(mContext, ImagerActivity.class, bundle, "图片查看");
    }

    public void startGoodDetail(Context mContext, SearchGoodVo.ResultBean.DataBean bean) {
        Bundle bundle = new Bundle();
        bundle.putString("goods_id", String.valueOf(bean.getGoods_id()));
        jumpTo(mContext, GoodsDetailActivity.class, bundle);
    }

    public void startBaiduMoeny(Context mContext) {
        jumpTo(mContext, MapRedBagActivity.class);
    }

    public void startMain(Context mContext) {
        jumpTo(mContext, MainActivity.class);
    }

    public void startMain(Context mContext, String mToken) {
        Bundle bundle = new Bundle();
        bundle.putString(MainActivity.token, mToken);
        jumpTo(mContext, MainActivity.class, bundle);
    }

    public void startBindPhone(Context mContext, String key) {
        Bundle bundle = new Bundle();
        bundle.putString(BindPhoneActivity.KEY, key);
        jumpTo(mContext, BindPhoneActivity.class, bundle, "绑定手机号");
    }

    /**
     * @param mContext
     * @param key
     * @param type     个人信息
     */
    public void startBindPhone(Context mContext, String key, String type) {
        Bundle bundle = new Bundle();
        bundle.putString(BindPhoneActivity.KEY, key);
        bundle.putString(BindPhoneActivity.TYPE, type);
        jumpTo(mContext, BindPhoneActivity.class, bundle, "绑定手机号");
    }

    public void startBindAlipay(Context userInfoActivity) {
        jumpTo(userInfoActivity, BindAlipayActivity.class, "支付宝账号");

    }

    public void startShopList(Context mContext) {
        jumpTo(mContext, ShopListActivity.class);

    }

    public void startShopService(Context mContext, ShopServiceVo.ResultBean.TypeListBean vo) {
        Bundle bundle = new Bundle();
        bundle.putString(ShopServiceListActivity.MSHOPID, String.valueOf(vo.getSc_id()));
        jumpTo(mContext, ShopServiceListActivity.class, bundle, "商家列表");
    }

    /**
     * @param mContext
     * @param type     3口碑推荐，4收藏最多，5附近商城
     */
    public void startShopService(Context mContext, int type) {
        Bundle bundle = new Bundle();
        switch (type) {
            case 3:
                bundle.putString(ShopServiceListActivity.TYPE, ShopServiceListActivity.TYPE_RECOMMEND);
                break;
            case 4:
                bundle.putString(ShopServiceListActivity.TYPE, ShopServiceListActivity.TYPE_COLLECT);
                break;
            case 5:
                bundle.putString(ShopServiceListActivity.TYPE, ShopServiceListActivity.TYPE_LOCATION);
                break;
        }
        jumpTo(mContext, ShopServiceListActivity.class, bundle, "商家列表");
    }

    public void startUnBind(Context mContext, String account) {
        Bundle bundle = new Bundle();
        bundle.putString(UnBindAlipayActivity.MPHONE, account);
        jumpTo(mContext, UnBindAlipayActivity.class, bundle, "解绑支付宝");
    }

    public void startHelpDetail(Context mContext, HelpVo.ResultBean.ListBean.DataBean vo) {
        Bundle bundle = new Bundle();
        bundle.putString(HelpDetailActivity.MHELPID, String.valueOf(vo.getHelp_id()));
        jumpTo(mContext, HelpDetailActivity.class, bundle, "问答");

    }

    public void startInviteGo(Context mContext) {
        jumpTo(mContext, JobInfomActivity.class);
    }

    public void startJobGo(Context mContext) {
        jumpTo(mContext, CreateBossCardActivity.class);
    }

    public void startJobGo(Context mContext, ResumeVo.ResultBean bean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(JobInfomActivity.TYPE, bean);
        jumpTo(mContext, JobInfomActivity.class, bundle);
    }

    public void startEducation(Context mContext) {
        jumpTo(mContext, EducationInfomActivity.class);
    }

    public void startEducation(Context mContext, String com) {
        Bundle bundle = new Bundle();
        bundle.putString(EducationInfomActivity.ADDTYPE, com);
        jumpTo(mContext, EducationInfomActivity.class, bundle);
    }

    public void startEducation(Context mContext, ResumeVo.EducationsBean bean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EducationInfomActivity.TYPE, bean);
        jumpTo(mContext, EducationInfomActivity.class, bundle);
    }

    public void startWork(Context mContext) {

        jumpTo(mContext, WorkInfomActivity.class);
    }

    public void startWork(Context mContext, String com) {
        Bundle bundle = new Bundle();
        bundle.putString(WorkInfomActivity.ADDTYPE, com);
        jumpTo(mContext, WorkInfomActivity.class, bundle);
    }

    public void startWork(Context mContext, ResumeVo.WorksBean bean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(WorkInfomActivity.TYPE, bean);
        jumpTo(mContext, WorkInfomActivity.class, bundle);
    }


    public void startJobPurpost(Context mContext) {

        jumpTo(mContext, JobPurposeActivity.class);
    }

    public void startJobPurpost(Context mContext, ResumeVo.ResultBean beanStr) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(JobPurposeActivity.TYPE, beanStr);
        jumpTo(mContext, JobPurposeActivity.class, bundle);
    }

    public void startEvaluest(Context mContext) {
        jumpTo(mContext, JobEvaluateActivity.class);

    }

    public void startJobsSelect(Context mContext, int jobselectRequestcode) {
        jumpToFoResulBU(mContext, JobSelectActivity.class, jobselectRequestcode);
    }

    public void startTrade(Context mContext, int tradeRequestcode, int type) {
        Bundle bundle = new Bundle();
        bundle.putInt(TradeActivity.SELECTNUMBER, type);
        jumpToFoResulBU(mContext, TradeActivity.class, bundle, tradeRequestcode);
    }

    public void startInfom(Context mContext) {
        jumpTo(mContext, UserInfoActivity.class);

    }


    public void startRecruitHome(Context mContext) {

        jumpTo(mContext, RecruitHomeActivity.class);
    }

    public void startBossName(Context mContext) {
        jumpTo(mContext, BossNameActivity.class);
    }

    public void startCompany(Context mContext, int requestcompanycode) {
        jumpToFoResulBU(mContext, CompanySearchActivity.class, requestcompanycode);
    }

    public void startChanger(Context mContext) {
        Bundle bundle = new Bundle();
        bundle.putString(ChangerJobPhoneActivity.TYPE, ChangerJobPhoneActivity.DEILIVETYPE);
        jumpTo(mContext, ChangerJobPhoneActivity.class, bundle, "修改手机号码");
    }


    public void startRcEvalue(Context mContext, String self_assessment) {
        Bundle bundle = new Bundle();
        bundle.putString(JobEvaluateActivity.TYPE, self_assessment);
        jumpTo(mContext, JobEvaluateActivity.class, bundle);
    }

    public void startVerifyPhone(Context mContext, int verifyphonecode) {
        Bundle bundle = new Bundle();
        bundle.putString(ChangerJobPhoneActivity.TYPE, ChangerJobPhoneActivity.INFOMTYPE);
        jumpToFoResulBU(mContext, ChangerJobPhoneActivity.class, bundle, "手机号绑定", verifyphonecode);
    }

    public void startLookResume(Context mContext) {
        jumpTo(mContext, LookResumeActivity.class);
    }


    public void startRecruitInHome(Context mContext, String changertype, String id) {
        Bundle bundle = new Bundle();
        bundle.putString(RecruitInActivity.TYPE, changertype);
        bundle.putString(RecruitInActivity.ID, id);
        jumpTo(mContext, RecruitInActivity.class, bundle);
    }

    public void startCreateNewCompany(Context mContext, String mCompanyName) {
        Bundle bundle = new Bundle();
        bundle.putString(NewCompanyActivity.CompanyName, mCompanyName);
        jumpTo(mContext, NewCompanyActivity.class, bundle);
    }

    public void startSearchResult(Context context, String name) {
        Bundle bundle = new Bundle();
        bundle.putString(CompanyNameSignActivity.COMPANYNAME, name);
        jumpTo(context, CompanyNameSignActivity.class, bundle);
    }

    public void startCreatCompanyOver(Context mContext) {
        jumpTo(mContext, CompanyVerifyActivity.class);
    }

    public void startTradingCertificate(Context mContext, String company, int tradeRequestcode) {
        Bundle bundle = new Bundle();
        bundle.putString(CompanyTradeActivity.COMPANYNAME, company);
        jumpToFoResulBU(mContext, CompanyTradeActivity.class, bundle, tradeRequestcode);

    }


    public void startBossHome(Context mContext) {
        jumpTo(mContext, BossHomeActivity.class);

    }

    public void startSendJob(Context mContext) {
        jumpTo(mContext, ReleaseJobActivity.class);

    }

    public void startSendJobFinish(Context mContext, String mJobId) {
        Bundle bundle = new Bundle();
        bundle.putString(ReleaseJobActivity.Finish, ReleaseJobActivity.Finish);
        bundle.putString(ReleaseJobActivity.JOB_ID, mJobId);
        jumpTo(mContext, ReleaseJobActivity.class, bundle);
    }

    public void startBossWorkArea(Context mContext, String mProvince, String mCity, String mArea, String mAddress, int work_requestcode) {
        Bundle bundle = new Bundle();
        bundle.putString(BossWorkAreaActivity.PRIVOINCE, mProvince);
        bundle.putString(BossWorkAreaActivity.CITY, mCity);
        bundle.putString(BossWorkAreaActivity.AREA, mArea);
        bundle.putString(BossWorkAreaActivity.ADDRESS, mAddress);
        jumpToFoResulBU(mContext, BossWorkAreaActivity.class, bundle, work_requestcode);
    }

    public void startWorkInfom(Context mContext, String com, int wrokinfom_requestcode) {
        Bundle bundle = new Bundle();
        bundle.putString(BossWorkDescribeActivity.INFOMWORK, com);
        jumpToFoResulBU(mContext, BossWorkDescribeActivity.class, bundle, wrokinfom_requestcode);
    }

    public void startWorkInfom(Context mContext, String com, int type, int wrokinfom_requestcode) {
        Bundle bundle = new Bundle();
        bundle.putString(BossWorkDescribeActivity.INFOMWORK, com);
        bundle.putString(BossWorkDescribeActivity.TYPE, String.valueOf(type));
        jumpToFoResulBU(mContext, BossWorkDescribeActivity.class, bundle, wrokinfom_requestcode);
    }

    public void startBossCompanyChanger(Context mContext) {
        jumpTo(mContext, BossChangeCompanyActivity.class, "修改公司信息");
    }

    public void startInterView(Context mContext, DeliverBossVo.ResultBean.DataBean vo, String showbtnType) {
        Bundle bundle = new Bundle();
        bundle.putString(BossInterviewActivity.TYPE, showbtnType);
        bundle.putString(BossInterviewActivity.JIANLI_ID, String.valueOf(vo.getId()));
        bundle.putString(BossInterviewActivity.TIME, vo.getInterview_time());
        jumpTo(mContext, BossInterviewActivity.class, bundle);

    }

    public void startBossLookJianli(Context mContext, DeliverBossVo.ResultBean.DataBean vo, String otherType) {
        Bundle bundle = new Bundle();
        bundle.putString(BossLookResumeActivity.TYPE, otherType);
        bundle.putString(BossLookResumeActivity.JIANLI_ID, String.valueOf(vo.getId()));
        jumpTo(mContext, BossLookResumeActivity.class, bundle, "查看简历");
    }

    public void startBossLookJianli(Context mContext, String id, String otherType) {
        Bundle bundle = new Bundle();
        bundle.putString(BossLookResumeActivity.TYPE, otherType);
        bundle.putString(BossLookResumeActivity.JIANLI_ID, String.valueOf(id));
        jumpTo(mContext, BossLookResumeActivity.class, bundle, "查看简历");
    }

    public void startSendInterView(Context mContext, String mJianLiId, String username, String desc, String avatar) {
        Bundle bundle = new Bundle();
        bundle.putString(BossSendInterViewActivity.ID, mJianLiId);
        bundle.putString(BossSendInterViewActivity.NAME, username);
        bundle.putString(BossSendInterViewActivity.HEAR, avatar);
        bundle.putString(BossSendInterViewActivity.JOB, desc);
        jumpTo(mContext, BossSendInterViewActivity.class, bundle, "发起邀请");

    }

    public void startBossJobManage(Context mContext) {
        jumpTo(mContext, PostManageActivity.class, "职位管理");
    }

    public void startBossOtherJobResult(Context mContext, int id, String name) {
        Bundle bundle = new Bundle();
        bundle.putString(BossOtherJobResultActivity.JOB_ID, String.valueOf(id));
        jumpTo(mContext, BossOtherJobResultActivity.class, bundle, name);
    }

    public void startBossCompanyInfom(Context mContext, int id) {
        Bundle bundle = new Bundle();
        bundle.putString(BossCompanyInfomActivity.COMPANY_ID, String.valueOf(id));
        jumpTo(mContext, BossCompanyInfomActivity.class, bundle);
    }

    public void startPositionSearch(Context mContext, String mKeyWordParam, String mLocation, boolean isCompany) {
        Bundle bundle = new Bundle();
        bundle.putString(PositonSearchActivity.KEYWORDS, mKeyWordParam);
        bundle.putString(PositonSearchActivity.CITY_NAME, mLocation);
        bundle.putString(PositonSearchActivity.SEARCH_TYPE, isCompany ? "2" : "1");
        jumpTo(mContext, PositonSearchActivity.class, bundle, "搜索结果");

    }

    public void startJobDetail(Context mContext, int id) {
        Bundle bundle = new Bundle();
        bundle.putString(JobDetailActivity.JOB_ID, String.valueOf(id));
        jumpTo(mContext, JobDetailActivity.class, bundle, "职位详情");
    }

    public void starBossChat(Context mContext, int id,String name) {
        Bundle bundle = new Bundle();
        bundle.putString(ChatBoss2Activity.BOSSID,String.valueOf(id));
        jumpTo(mContext,ChatBoss2Activity.class,bundle,name);

    }

    public void startChatId(Context mContext, int record_id, String company) {
        Bundle bundle = new Bundle();
        bundle.putString(ChatJobActivity.RECIVER_ID, String.valueOf(record_id));
        jumpTo(mContext, ChatJobActivity.class, bundle, company);
    }
}
