package com.tsyc.tianshengyoucai.manager;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;

import com.tsyc.tianshengyoucai.ui.ImagerActivity;
import com.tsyc.tianshengyoucai.ui.SecondActivity;
import com.tsyc.tianshengyoucai.ui.activity.home.GoodsDetailActivity;
import com.tsyc.tianshengyoucai.ui.activity.home.HomeSearchActivity;
import com.tsyc.tianshengyoucai.ui.activity.home.ShopDetailActivity;
import com.tsyc.tianshengyoucai.ui.activity.home.ShopListActivity;
import com.tsyc.tianshengyoucai.ui.activity.home.ShopServiceActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.BecomeShopActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.InviteActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.PartnerActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.address.AddressActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.collect.MyCollectActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.help.HelpActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.money.MyCoinsActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.money.MyMoneyActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.money.MyRedPacketActivity;
import com.tsyc.tianshengyoucai.ui.activity.mine.money.ShopRedSelectActivity;
import com.tsyc.tianshengyoucai.ui.activity.type.HomeTypeActivity;
import com.tsyc.tianshengyoucai.ui.activity.type.TypeListActivity;
import com.tsyc.tianshengyoucai.ui.recruit.JobDetailActivity;
import com.tsyc.tianshengyoucai.ui.recruit.JobInfomActivity;
import com.tsyc.tianshengyoucai.ui.recruit.JobSearchActivity;
import com.tsyc.tianshengyoucai.ui.recruit.RecruitHomeActivity;
import com.tsyc.tianshengyoucai.ui.recruit.RecruitInActivity;
import com.tsyc.tianshengyoucai.ui.recruit.ResumeActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.BossCompanyInfomActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.BossDeliverActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.BossHomeActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.BossHomeSearchTypeActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.BossInfoActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.BossInterestActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.BossInterviewActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.BossJobEditActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.BossLookResumeActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.BossOtherActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.BossOtherJobActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.PostManageActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.ReleaseJobActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.sign.BossNameActivity;
import com.tsyc.tianshengyoucai.ui.recruit.boss.sign.CreateBossCardActivity;
import com.tsyc.tianshengyoucai.ui.recruit.chat.ChatActivity;
import com.tsyc.tianshengyoucai.ui.recruit.chat.ChatBoss2Activity;
import com.tsyc.tianshengyoucai.ui.recruit.chat.ChatJobActivity;
import com.tsyc.tianshengyoucai.ui.recruit.infom.EducationInfomActivity;
import com.tsyc.tianshengyoucai.ui.recruit.infom.JobEvaluateActivity;
import com.tsyc.tianshengyoucai.ui.recruit.infom.JobPurposeActivity;
import com.tsyc.tianshengyoucai.ui.recruit.infom.TradeActivity;
import com.tsyc.tianshengyoucai.ui.recruit.infom.WorkInfomActivity;
import com.tsyc.tianshengyoucai.ui.recruit.jobmine.AllPostionActivity;
import com.tsyc.tianshengyoucai.ui.recruit.jobmine.CollectJobActivity;
import com.tsyc.tianshengyoucai.ui.recruit.jobmine.DeliveryDetailsActivity;
import com.tsyc.tianshengyoucai.ui.recruit.jobmine.JobPrivacyActivity;
import com.tsyc.tianshengyoucai.ui.recruit.jobmine.MyDeliveryActivity;
import com.tsyc.tianshengyoucai.ui.recruit.jobmine.NearestPositionActivity;
import com.tsyc.tianshengyoucai.ui.recruit.jobmine.PositionListsActivity;
import com.tsyc.tianshengyoucai.vo.CouponListBean;
import com.tsyc.tianshengyoucai.vo.GoodCollectVo;
import com.tsyc.tianshengyoucai.vo.GoodTypeVo;
import com.tsyc.tianshengyoucai.vo.SearchGoodVo;
import com.tsyc.tianshengyoucai.vo.ShopCollectVo;

import java.util.ArrayList;
import java.util.List;

public class ResultFragmentTo extends StartFragmentManger {
    public ResultFragmentTo(FragmentActivity m) {
        super(m);
    }

    public void startMoney(Context mContext) {
        Bundle bundle = new Bundle();
        bundle.putString(CNT_PARAMETE_TITLE, "我的余额");
        jumpTo(mContext, MyMoneyActivity.class, bundle);
    }

    public void startConis(Context mContext, String objectToStr) {
        Bundle bundle = new Bundle();
        bundle.putString(MyCoinsActivity.VALUES, objectToStr);
        jumpTo(mContext, MyCoinsActivity.class, bundle, "我的金币");

    }

    public void startRedPacker(Context mContext) {
        jumpTo(mContext, MyRedPacketActivity.class, "我的红包");
    }

    public void startMyAddress(Context mContext) {
        jumpTo(mContext, AddressActivity.class, "我的收货地址");
    }

    public void startlib(FragmentActivity activity, int requestCode) {
        jumpToFoResulBU(activity, SecondActivity.class, requestCode);
    }

    public void startPartner(Context mContext) {
        jumpTo(mContext, PartnerActivity.class, "成为合伙人");
    }

    public void startCollect(Context mContext) {
        jumpTo(mContext, MyCollectActivity.class, "我的收藏");
    }

    public void startGoodDetail(FragmentActivity activity, GoodCollectVo.ResultBean vo) {
        Bundle bundle = new Bundle();
        bundle.putString("goods_id", String.valueOf(vo.getFav_id()));
        jumpTo(activity, GoodsDetailActivity.class, bundle);

    }

    public void startGoodDetail(FragmentActivity activity, SearchGoodVo.ResultBean.DataBean vo) {
        Bundle bundle = new Bundle();
        bundle.putString("goods_id", String.valueOf(vo.getGoods_id()));
        jumpTo(activity, GoodsDetailActivity.class, bundle);

    }

    public void startMoreShop(FragmentActivity activity) {
        jumpTo(activity, ShopListActivity.class);

    }

    public void startInvite(Context mContext) {
        jumpTo(mContext, InviteActivity.class, "邀请有奖");

    }

    public void startHomeSearch(FragmentActivity activity) {
        jumpTo(activity, HomeSearchActivity.class);
    }

    public void startShopService(FragmentActivity activity) {
        jumpTo(activity, ShopServiceActivity.class);
    }

    public void startTypeList(FragmentActivity activity, GoodTypeVo.ResultBean vo) {
        Bundle bundle = new Bundle();
        bundle.putString(TypeListActivity.MCATEGORYIDPARAM, String.valueOf(vo.getGc_id()));
        jumpTo(activity, TypeListActivity.class, bundle, vo.getGc_name());
    }


    //检测商铺开通状态
    public void checkShopStatus(FragmentActivity activity) {
        jumpTo(activity, BecomeShopActivity.class);
    }

    public void startHelp(Context mContext) {

        jumpTo(mContext, HelpActivity.class, "帮助中心");
    }

    public void startShopDetail(FragmentActivity activity, ShopCollectVo.ResultBean vo, String type) {
        Bundle bundle = new Bundle();
        bundle.putString("store_id", String.valueOf(vo.getStore_id()));
        bundle.putString("type", type);
        jumpTo(activity, ShopDetailActivity.class, bundle);
    }

    public void startShopDetail(FragmentActivity activity, String Stroeid) {
        Bundle bundle = new Bundle();
        bundle.putString("store_id", Stroeid);
        jumpTo(activity, ShopDetailActivity.class, bundle);
    }


    public void startCouponList(FragmentActivity activity, List<CouponListBean> mCoupon_list, String type) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(ShopRedSelectActivity.LIST, (ArrayList<? extends Parcelable>) mCoupon_list);
        bundle.putString(ShopRedSelectActivity.TYPE, String.valueOf(type));
        jumpTo(activity, ShopRedSelectActivity.class, bundle, "优惠卷");

    }

    public void startTYpe(FragmentActivity activity) {
        jumpTo(activity, HomeTypeActivity.class);
    }

    public void startRecruitInHome(FragmentActivity activity, String type, String id) {
        Bundle bundle = new Bundle();
        bundle.putString(RecruitInActivity.TYPE, type);
        bundle.putString(RecruitInActivity.ID, id);
        jumpTo(activity, RecruitInActivity.class, bundle);
    }

    public void startImager(Context mContext, int adapterPosition, List<String> eval_image) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(ImagerActivity.listType, (ArrayList<String>) eval_image);
        bundle.putInt(ImagerActivity.postion, adapterPosition);
        jumpTo(mContext, ImagerActivity.class, bundle, "图片查看");

    }

    public void startRecruitHome(FragmentActivity activity) {
        jumpTo(activity, RecruitHomeActivity.class);
    }

    public void startJobSearch(FragmentActivity activity, String mLocationName) {
        Bundle bundle = new Bundle();
        bundle.putString(JobSearchActivity.Type, mLocationName);
        jumpTo(activity, JobSearchActivity.class, bundle);

    }

    public void startJobInfom(FragmentActivity activity) {

        jumpTo(activity, JobInfomActivity.class);
    }

    public void startEvaluate(FragmentActivity activity) {
        jumpTo(activity, JobEvaluateActivity.class);
    }

    public void startJobPurpost(FragmentActivity activity) {
        jumpTo(activity, JobPurposeActivity.class);
    }

    public void startJobWork(FragmentActivity activity) {
        jumpTo(activity, WorkInfomActivity.class);
    }

    public void startJobEdu(FragmentActivity activity) {
        jumpTo(activity, EducationInfomActivity.class);
    }

    public void startBossInfom(FragmentActivity activity) {
        jumpTo(activity, CreateBossCardActivity.class);
    }

    public void startBossId(FragmentActivity activity) {
        jumpTo(activity, BossNameActivity.class);
    }

    public void startBossJob(FragmentActivity activity) {
        jumpTo(activity, ReleaseJobActivity.class);
    }

    public void startAllMyDelivery(FragmentActivity activity, String type) {
        Bundle bundle = new Bundle();
        bundle.putString(MyDeliveryActivity.TYPE, type);
        jumpTo(activity, MyDeliveryActivity.class, bundle, "我的投递");
    }

    public void startJobSetting(FragmentActivity activity) {
        jumpTo(activity, JobPrivacyActivity.class, "隐私设置");

    }

    public void startRcLook(FragmentActivity activity) {
        jumpTo(activity, ResumeActivity.class, "编辑简历");
    }

    public void startJobCollect(FragmentActivity activity) {
        jumpTo(activity, CollectJobActivity.class, "职位收藏");
    }

    public void startBossHome(Context mContext) {
        jumpTo(mContext, BossHomeActivity.class);
    }

    public void startChangerBossInfom(FragmentActivity activity) {
        jumpTo(activity, BossInfoActivity.class, "个人信息");
    }

    public void startBossCompanyInfom(FragmentActivity activity) {
        jumpTo(activity, BossCompanyInfomActivity.class);
    }
    public void startBossCompanyInfom(FragmentActivity activity,int id) {
        Bundle bundle = new Bundle();
        bundle.putString(BossCompanyInfomActivity.COMPANY_ID, String.valueOf(id));
        jumpTo(activity, BossCompanyInfomActivity.class, bundle);
    }

    public void startBossJobManage(FragmentActivity activity) {
        jumpTo(activity, PostManageActivity.class, "职位管理");
    }

    public void startOtherBoss(FragmentActivity activity) {
        jumpTo(activity, BossOtherActivity.class, "已处理");
    }

    public void startBossAsk(FragmentActivity activity, int type) {
        Bundle bundle = new Bundle();
        String title = "";
        if (type == 1) {
            title = "待查看";
            bundle.putString(BossDeliverActivity.TYPE, BossDeliverActivity.LOOKTYPE);
        } else if (type == 2) {
            title = "邀请面试";
            bundle.putString(BossDeliverActivity.TYPE, BossDeliverActivity.ASKTYPE);
        }
        jumpTo(activity, BossDeliverActivity.class, bundle, title);
    }

    public void startReleaseJob(FragmentActivity activity) {
        Bundle bundle = new Bundle();
        bundle.putString(ReleaseJobActivity.Finish, ReleaseJobActivity.Finish);
        jumpTo(activity, ReleaseJobActivity.class, bundle);

    }

    public void startBossJobDetail(FragmentActivity activity, int id, String position_name) {
        Bundle bundle = new Bundle();
        bundle.putString(BossJobEditActivity.JOBID, String.valueOf(id));
        jumpTo(activity, BossJobEditActivity.class, bundle, position_name);
    }

    public void startHomeSearchType(FragmentActivity activity, int selectRequestcode) {
        jumpToFoResulBU(activity, BossHomeSearchTypeActivity.class, selectRequestcode, "筛选条件");
    }

    public void startBossOtherJob(FragmentActivity activity, int type) {
        Bundle bundle = new Bundle();
        bundle.putInt(TradeActivity.SELECTNUMBER, type);
        jumpTo(activity, BossOtherJobActivity.class, bundle);
    }

    public void startJobDetail(FragmentActivity activity, int id) {
        Bundle bundle = new Bundle();
        bundle.putString(JobDetailActivity.JOB_ID, String.valueOf(id));
        jumpTo(activity, JobDetailActivity.class, bundle, "职位详情");
    }

    public void startNearest(Context mContext, String city) {
        Bundle bundle = new Bundle();
        bundle.putString(NearestPositionActivity.CIty_NAME, city);
        jumpTo(mContext, NearestPositionActivity.class, bundle, "附近职位");
    }

    public void startAllPostion(Context mContext, String quanType) {
        String title = null;
        if (quanType.equals(AllPostionActivity.ALL_TYPE)) {
            title = "全部职位";
        } else if (quanType.equals(AllPostionActivity.JIAN_TYPE)) {
            title = "兼职";
        } else if (quanType.equals(AllPostionActivity.QUAN_TYPE)) {
            title = "全职";
        }
        Bundle bundle = new Bundle();
        bundle.putString(AllPostionActivity.TYPE, quanType);
        jumpTo(mContext, AllPostionActivity.class, bundle, title);

    }

    public void startPostionList(Context mContext) {
        jumpTo(mContext, PositionListsActivity.class, "职位");

    }

    public void startChat(FragmentActivity activity) {
        jumpTo(activity, ChatActivity.class, "客服");
    }

    public void startBossInterest(FragmentActivity activity) {
        jumpTo(activity, BossInterestActivity.class,"对我感兴趣");
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

    public void startDeliverDetail(FragmentActivity activity, int id) {
        Bundle bundle = new Bundle();
        bundle.putString(DeliveryDetailsActivity.POSTION_ID,String.valueOf(id));
        jumpTo(activity,DeliveryDetailsActivity.class,bundle,"投递进展");
    }

    public void startBossLookJianli(FragmentActivity activity, String otherType, int id) {
        Bundle bundle = new Bundle();
        bundle.putString(BossLookResumeActivity.TYPE, otherType);
        bundle.putString(BossLookResumeActivity.JIANLI_ID, String.valueOf(id));
        jumpTo(activity, BossLookResumeActivity.class, bundle, "查看简历");
    }

    public void startInterView(Context mContext, int id,String time, String showbtnType) {
        Bundle bundle = new Bundle();
        bundle.putString(BossInterviewActivity.TYPE, showbtnType);
        bundle.putString(BossInterviewActivity.JIANLI_ID, String.valueOf(id));
        bundle.putString(BossInterviewActivity.TIME,time);
        jumpTo(mContext, BossInterviewActivity.class, bundle);
    }
}

