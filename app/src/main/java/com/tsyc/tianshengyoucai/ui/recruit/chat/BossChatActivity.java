package com.tsyc.tianshengyoucai.ui.recruit.chat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.recruit.ChatAdapter;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.requet.RequestBossHttp;
import com.tsyc.tianshengyoucai.requet.UpdataImagHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.ui.recruit.chat.chatObserver.ChatInterface;
import com.tsyc.tianshengyoucai.utils.ActivityUtil;
import com.tsyc.tianshengyoucai.utils.FileUtil;
import com.tsyc.tianshengyoucai.utils.GlideUtil;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.LunBanUtil;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.utils.Util;
import com.tsyc.tianshengyoucai.view.FlowLayout;
import com.tsyc.tianshengyoucai.view.SelectCamerAlerdialog;
import com.tsyc.tianshengyoucai.vo.AcceptChatService;
import com.tsyc.tianshengyoucai.vo.BossChatVo;
import com.tsyc.tianshengyoucai.vo.ChatDataBeanVo;
import com.tsyc.tianshengyoucai.vo.ChatListVo;
import com.tsyc.tianshengyoucai.vo.ChatVo;
import com.tsyc.tianshengyoucai.vo.GmImagerVo;
import com.tsyc.tianshengyoucai.vo.GmSelectBean;
import com.youth.xframe.widget.XToast;
import com.zhihu.matisse.Matisse;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/9/9 14:46:
 * @Purpose :boss聊天
 */
@RuntimePermissions
public class BossChatActivity extends Base2Activity implements View.OnClickListener, ChatInterface {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private RecyclerView mRlvBossChatContent;
    private ImageView mIvBossChatAdd;
    private EditText mEtBossChatInput;
    private Button mBtnBossChatSend;
    private LinearLayout mLlBossChatButton;
    private SelectCamerAlerdialog mAlerdialog;
    /**
     * 相机
     */
    private int REQUESTCODE = 100;
    /**
     * 相册
     */
    private int REQUESTCODECE = 101;
    private String mPath;
    private ChatAdapter mChatAdapter;
    private ArrayList mArray;
    private WsManager mBossInstance;
    private ChatVo.ResultBean.MemberBean mUserInfom;
    public static final String BOSSID = "bossid";
    private String mBossId;
    private ChatVo.ResultBean.ServiceBean mServiceInfom;
    private SmartRefreshLayout mBossSmrlayoyut;
    private BossChatVo.ResultBean.LeftUserBean mLeftUser;
    private BossChatVo.ResultBean.RightUserBean mRightUser;
    private TextView mTvBossTitle;
    private TextView mTvBossTag;
    private TextView mTvBossValues;
    private TextView mTvBossCompany;
    private FlowLayout mFlBossType;
    private ImageView mIvBossLogo;
    private TextView mTvBossUser;
    private TextView mTvJobBossDelete;
    private TextView mTvBossTime;
    private LinearLayout mLlChatJob;

//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_boss_chat);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_boss_chat;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        if (getIntent() != null) {
            mBossId = getIntent().getStringExtra(BOSSID);
        }
        initView();
        clearData();
        initEvent();
        initWebStock();
        initAdapter();
        initRequest();
        initRefresh();
    }

    private void initRefresh() {
//        mBossSmrlayoyut.setEnableRefresh(false);
//        mBossSmrlayoyut.setOnRefreshListener(refreshLayout -> {
//
//        });
//        mBossSmrlayoyut.setOnLoadMoreListener(refreshLayout -> {
//
//        });
    }


    private void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mRlvBossChatContent = (RecyclerView) findViewById(R.id.rlv_boss_chat_content);
        mIvBossChatAdd = (ImageView) findViewById(R.id.iv_boss_chat_add);
        mEtBossChatInput = (EditText) findViewById(R.id.et_boss_chat_input);
        mBtnBossChatSend = (Button) findViewById(R.id.btn_boss_chat_send);
        mLlBossChatButton = (LinearLayout) findViewById(R.id.ll_boss_chat_button);

        mBtnBossChatSend.setOnClickListener(this);
//        mBossSmrlayoyut = (SmartRefreshLayout) findViewById(R.id.boss_smrlayoyut);
        mTvBossTitle = (TextView) findViewById(R.id.tv_boss_title);
        mTvBossValues = (TextView) findViewById(R.id.tv_boss_values);
        mFlBossType = (FlowLayout) findViewById(R.id.fl_boss_type);
        mIvBossLogo = (ImageView) findViewById(R.id.iv_boss_logo);
        mTvBossUser = (TextView) findViewById(R.id.tv_boss_user);
        mTvJobBossDelete = (TextView) findViewById(R.id.tv_job_boss_delete);
        mTvBossTime = (TextView) findViewById(R.id.tv_boss_time);
        mLlChatJob = (LinearLayout) findViewById(R.id.ll_chat_job);
    }


    private void initAdapter() {
        mChatAdapter = new ChatAdapter(mContext, mArray);
        RecyclerUtil.setGridManage(mContext, mRlvBossChatContent, mChatAdapter);
        mChatAdapter.setListener(new ChatAdapter.OnItemClickListener() {
            @Override
            public void itemClick(String content) {
                List<String> strings = new ArrayList<>();
                strings.add(content);
                mResultTo.startEvaluateImager(mContext, 0, strings);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    private void initRequest() {
        showProgress();
        RequestBossHttp.getSingleton(this).requestBossTalkInit("1",mBossId, new RequestResultCallback() {
            private ChatVo.ResultBean.ServiceBean mServiceInfom;

            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                BossChatVo vo = GsonUtils.getGson(success, BossChatVo.class);
                BossChatVo.ResultBean result = vo.getResult();
                mLeftUser = result.getLeft_user();
                mRightUser = result.getRight_user();
                BossChatVo.ResultBean.HeadCardBean head_card = result.getHead_card();
                bindViewData(head_card, result.getWho_start());
                List<ChatDataBeanVo> data = result.getLogs().getData();
                ArrayList<ChatListVo> mList = new ArrayList<>();
                if (data != null && !data.isEmpty())
                    for (int i = 0; i < data.size(); i++) {
                        ChatDataBeanVo dataBean = data.get(i);
                        ChatListVo chatListVo = new ChatListVo();
                        chatListVo.setUserInfom(dataBean.getFrom_side() == 1);
                        if (dataBean.getFrom_side()==1){
                            chatListVo.setLogo(mRightUser.getAvatar());
                            chatListVo.setName(mRightUser.getUsername());
                        }else {
                            chatListVo.setLogo(mLeftUser.getAvatar());
                            chatListVo.setName(mLeftUser.getUsername());
                        }
                        chatListVo.setContent(dataBean.getContent());
                        chatListVo.setType(dataBean.getType());
                        chatListVo.setId(dataBean.getId());
                        mList.add(chatListVo);
                    }
                addData(mList);
                mChatAdapter.notifyDataSetChanged();
                mBossInstance.registerBoss(Integer.valueOf(mBossId));
                move();
            }

            @Override
            public void error(String error) {
                onError();
            }
        });

    }

    private void bindViewData(BossChatVo.ResultBean.HeadCardBean vo, String time) {
        GmSelectBean position = vo.getExpected_position();
        mTvBossTitle.setText(position.getDesc());
        GmSelectBean salary = vo.getExpected_salary();
        mTvBossValues.setText(salary.getDesc());
        List<String> items = new ArrayList<>();
        GmSelectBean city = vo.getWork_city();
        items.add(city.getDesc());
        GmSelectBean education = vo.getHighest_education();
        items.add(education.getDesc());
        bindViewData(mFlBossType, items);

        GlideUtil.getSingleton().loadQuadRangleImager(mContext, mIvBossLogo, vo.getAvatar());
        mTvBossUser.setText(vo.getUsername() + "·" + vo.getSex().getDesc());
        mTvBossTime.setText(time);
    }

    private void bindViewData(FlowLayout flowLayout, List<String> list) {
        if (list == null || list.isEmpty()) return;
        flowLayout.removeAllViews();

        for (int i = 0; i < list.size(); i++) {
            String comtent = list.get(i);
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_job_type, null);
            TextView tv = inflate.findViewById(R.id.tv_job_type_content);
            tv.setText(comtent);
            flowLayout.addView(inflate);
        }


    }

    private void initEvent() {
        mRlvBossChatContent.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (bottom < oldBottom) {
                    mRlvBossChatContent.post(new Runnable() {
                        @Override
                        public void run() {
                            if (mChatAdapter.getItemCount() > 0) {
                                mRlvBossChatContent.smoothScrollToPosition(mChatAdapter.getItemCount() - 1);
                            }
                        }
                    });
                }
            }
        });
        mIvBossChatAdd.setOnClickListener(v -> {
            Util.hideInputMethod(this);
            BossChatActivityPermissionsDispatcher.showCameraWithPermissionCheck(this);
        });
    }

    private void initWebStock() {
        mBossInstance = WsManager.getInstance();
        mBossInstance.init();
        mBossInstance.attchSubject(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_boss_chat_send:
                String com = StringUtil.getObjectToStr(mEtBossChatInput);
                if (StringUtil.isEmpty(com)) {
                    T.showToast(mContext, "请输入发送内容");
                    return;
                }
                subimt(com);
                break;
        }
    }

    private void subimt(String com) {
//        List<ChatListVo> mList = new ArrayList<>();
//        ChatListVo vo = new ChatListVo();
//        vo.setId(mUserInfom.getMember_id());
//        vo.setLogo(mUserInfom.getMember_avatar());
//        vo.setContent(com);
//        vo.setType(1);
//        mList.add(vo);
//        vo.setUserInfom(true);
//        addData(mList);
//        mChatAdapter.notifyDataSetChanged();
        mEtBossChatInput.setText(null);
        mBossInstance.sendBossText(com, false, "");
//        if (mChatAdapter.getItemCount() > 0) {
//            mRlvBossChatContent.smoothScrollToPosition(mChatAdapter.getItemCount() - 1);
//        }
    }

    private void subimtImg(String path, String netPath) {
//        List<ChatListVo> mList = new ArrayList<>();
//        ChatListVo vo = new ChatListVo();
//        vo.setId(mUserInfom.getMember_id());
//        vo.setLogo(mUserInfom.getMember_avatar());
//        vo.setContent(path);
//        vo.setType(2);
//        vo.setUserInfom(true);
//        mList.add(vo);
//        addData(mList);
//        mChatAdapter.notifyDataSetChanged();
        mEtBossChatInput.setText(null);
        mBossInstance.sendBossText(netPath, true, netPath);
//        if (mChatAdapter.getItemCount() > 0) {
//            mRlvBossChatContent.smoothScrollToPosition(mChatAdapter.getItemCount() - 1);
//        }
    }

    private void clearData() {
        if (mArray == null) {
            mArray = new ArrayList();
        } else {
            mArray.clear();
        }
    }

    private void addData(List<?> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        if (mArray == null) {
            clearData();
        }
        mArray.addAll(list);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WsManager.getInstance().disconnect();
        WsManager.getInstance().deleteSubject();
    }

    @Override
    public void updataMsg(String msg) {
        AcceptChatService vo = GsonUtils.getGson(msg, AcceptChatService.class);
        if (vo.getFlag().equals("error")) {
            T.showToast(mContext, vo.getMessage());
            return;
        }
        List<ChatListVo> mList = new ArrayList<>();
        ChatListVo chatListVo = new ChatListVo();
        chatListVo.setType(vo.getType());
        chatListVo.setUserInfom(vo.getFrom_side()==1);
        if (vo.getFrom_side()==1){
            chatListVo.setLogo(mRightUser.getAvatar());
            chatListVo.setName(mRightUser.getUsername());
        }else {
            chatListVo.setLogo(mLeftUser.getAvatar());
            chatListVo.setName(mLeftUser.getUsername());
        }
        chatListVo.setContent(vo.getContent());
        mList.add(chatListVo);
        addData(mList);
        mChatAdapter.notifyDataSetChanged();
        move();


    }

    private void move() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (mChatAdapter.getItemCount() > 0) {
                    mRlvBossChatContent.smoothScrollToPosition(mChatAdapter.getItemCount() - 1);
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 500);
    }

    private void showPicture() {
        mAlerdialog = new SelectCamerAlerdialog(mContext, R.style.mydialog);
        mAlerdialog.initData(mContext);
        mAlerdialog.setListener(new SelectCamerAlerdialog.OnItemClickListener() {
            @Override
            public void openCamer() {
                ActivityUtil.openCammer(BossChatActivity.this, REQUESTCODE);
            }

            @Override
            public void openXinCe() {
                ActivityUtil.openXiangCes(BossChatActivity.this, REQUESTCODECE);
            }
        });
        mAlerdialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            Bitmap bimap = (Bitmap) bundle.get("data");
            String s = FileUtil.saveImag(mContext, bimap);
            Log.e("===========", "onActivityResult: " + s);
            LunBanUtil.getSingleton(mContext).lunBanImager(s, new LunBanUtil.lunBanInterface() {
                @Override
                public void imgStart() {
                    showProgress();
                }

                @Override
                public void imgSuccess(String path) {
                    dismissProgress();
                    mPath = path;
                    showDialog();
                }

                @Override
                public void imgError() {
                    dismissProgress();
                    T.showToast(mContext, "图片压缩失败,请重新选择");
                }
            });
            return;
        }

        if (requestCode == REQUESTCODECE && resultCode == RESULT_OK) {
            List<String> strings = Matisse.obtainPathResult(data);
            LunBanUtil.getSingleton(mContext).lunBanImagerS(strings, new LunBanUtil.lunBanInterface() {
                @Override
                public void imgStart() {
                    showProgress();
                }

                @Override
                public void imgSuccess(String path) {
                    dismissProgress();
                    mPath = path;
                    showDialog();
                }

                @Override
                public void imgError() {
                    dismissProgress();
                    T.showToast(mContext, "图片压缩失败,请重新选择");
                }
            });
            return;
        }
    }

    private void showDialog() {
        if (StringUtil.isEmpty(mPath)) {
            T.showToast(mContext, "请选择上传图片");
            return;
        }
        showProgress();
        UpdataImagHttp.getSingleton(this).updataImage("", mPath, new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                GmImagerVo vo = GsonUtils.getGson(success, GmImagerVo.class);
                subimtImg(mPath, vo.getResult().getImg_url());
                mPath = null;

            }

            @Override
            public void error(String error) {
                onError();
            }
        });
    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    //拒绝
    @OnPermissionDenied({Manifest.permission.CAMERA})
    void showDeniedForCamera() {
        XToast.normal("权限拒绝");
    }

    // 不再询问
    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void showNeverAskForCamera() {
        XToast.normal("权限拒绝，不再询问");
    }


    @NeedsPermission(Manifest.permission.CAMERA)
    void showCamera() {
        showPicture();

    }
}
