package com.tsyc.tianshengyoucai.ui.recruit.chat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.recruit.BossChatAdapter;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.requet.RequestBossHttp;
import com.tsyc.tianshengyoucai.requet.UpdataImagHttp;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.ui.recruit.chat.chatObserver.ChatInterface;
import com.tsyc.tianshengyoucai.utils.ActivityUtil;
import com.tsyc.tianshengyoucai.utils.FileUtil;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.LunBanUtil;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.utils.Util;
import com.tsyc.tianshengyoucai.view.SelectCamerAlerdialog;
import com.tsyc.tianshengyoucai.vo.AcceptChatService;
import com.tsyc.tianshengyoucai.vo.BossChatList;
import com.tsyc.tianshengyoucai.vo.BossChatVo;
import com.tsyc.tianshengyoucai.vo.ChatDataBeanVo;
import com.tsyc.tianshengyoucai.vo.ChatListVo;
import com.tsyc.tianshengyoucai.vo.GmImagerVo;
import com.youth.xframe.widget.XToast;
import com.zhihu.matisse.Matisse;

import java.util.ArrayList;
import java.util.List;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/9/9 18:10:
 * @Purpose :
 */
@RuntimePermissions
public class ChatBossActivity extends Base2Activity implements View.OnClickListener, ChatInterface {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private RecyclerView mGmRlvContent;
    private ImageView mIvEmpty;
    private SmartRefreshLayout mGmSmrlayoyut;

    private ArrayList mArray;
    private boolean isRefresh;
    private int mPage;
    public static final String BOSSID = "bossid";
    private String mBossId;
    private BossChatList mChatList;
    private BossChatAdapter mChatAdapter;
    private ImageView mIvBossChatAdd;
    private EditText mEtBossChatInput;
    private Button mBtnBossChatSend;
    private LinearLayout mLlBossChatButton;
    private WsManager mBossInstance;
    private BossChatVo.ResultBean mResult;
    /**
     * 相机
     */
    private int REQUESTCODE = 100;
    /**
     * 相册
     */
    private int REQUESTCODECE = 101;
    private String mPath;
    private SelectCamerAlerdialog mAlerdialog;
    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_chat_boss);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_chat_boss;
    }


    @Override
    protected void initContent(Bundle savedInstanceState) {
        if (getIntent() != null) {
            mBossId = getIntent().getStringExtra(BOSSID);
        }
        initView();
        initEvent();
        initWebStock();
        initAdapter();
        initRefresh();
        showProgress();
        loadData(true);
    }


    private void initRefresh() {
        mGmSmrlayoyut.setEnableRefresh(false);
        mGmSmrlayoyut.setOnLoadMoreListener(refreshLayout -> {
            loadData(false);
        });
    }

    private void loadData(boolean refresh) {
        if (isRefresh) return;
        isRefresh = true;
        if (refresh)
            mPage = 1;
        RequestBossHttp.getSingleton(this).requestBossTalkInit(String.valueOf(mPage), mBossId, new RequestResultCallback() {

            @Override
            public void success(String success) {
                isRefresh = false;
                if (!refresh) {
                    mGmSmrlayoyut.finishLoadMore();
                }
                if (onSuccess(success)) return;
                BossChatVo vo = GsonUtils.getGson(success, BossChatVo.class);
                mResult = vo.getResult();
                mChatList.setJob(mResult.getLeft_user());
                mChatList.setUser(mResult.getRight_user());
                mChatList.setHear(mResult.getHead_card());
                mChatList.setTime(mResult.getWho_start());
                List<ChatDataBeanVo> data = mResult.getLogs().getData();

                ArrayList<ChatListVo> mList = new ArrayList<>();
                if (data != null && !data.isEmpty()) {
                    for (int i = 0; i < data.size(); i++) {
                        ChatDataBeanVo dataBean = data.get(i);
                        ChatListVo chatListVo = new ChatListVo();
                        chatListVo.setUserInfom(dataBean.getFrom_side() == 1);
                        if (dataBean.getFrom_side() == 1) {
                            chatListVo.setLogo(mResult.getRight_user().getAvatar());
                            chatListVo.setName(mResult.getRight_user().getUsername());
                        } else {
                            chatListVo.setLogo(mResult.getLeft_user().getAvatar());
                            chatListVo.setName(mResult.getLeft_user().getUsername());
                        }
                        chatListVo.setContent(dataBean.getContent());
                        chatListVo.setType(dataBean.getType());
                        chatListVo.setId(dataBean.getId());
                        mList.add(chatListVo);
                    }
                }

                List<ChatListVo> data1 = mChatList.getmData();
                if (data1 == null) {
                    data1 = new ArrayList<>();
                }
                data1.addAll(mList);
                mChatList.setmData(data1);
                if (data1.isEmpty()) {
                    mGmSmrlayoyut.setEnableLoadMore(false);
                } else {
                    BossChatVo.ResultBean.LogsBean logs = mResult.getLogs();
                    if (data1.size() >= logs.getTotal()) {
                        mGmSmrlayoyut.setEnableLoadMore(false);
                    } else {
                        mPage = logs.getCurrent_page() + 1;
                        mGmSmrlayoyut.setEnableLoadMore(true);
                    }
                }
                if (refresh) {
                    mBossInstance.registerBoss(Integer.valueOf(mBossId));
                }
                mChatAdapter.refreshData(mChatList);
                mChatAdapter.notifyDataSetChanged();
            }

            @Override
            public void error(String error) {
                isRefresh = false;
                if (!refresh) {
                    mGmSmrlayoyut.finishLoadMore();
                }
                onError();
            }
        });

    }

    private void initAdapter() {
        mChatAdapter = new BossChatAdapter(mContext, mChatList);
        RecyclerUtil.setGridManage(mContext, mGmRlvContent, mChatAdapter);

    }

    private void initEvent() {
        mChatList = new BossChatList();

        mIvBossChatAdd.setOnClickListener(v -> {
            ChatBossActivityPermissionsDispatcher.showCameraWithPermissionCheck(this);
        });
    }

    private void initView() {
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mGmRlvContent = (RecyclerView) findViewById(R.id.gm_rlv_content);
        mIvEmpty = (ImageView) findViewById(R.id.iv_empty);
        mGmSmrlayoyut = (SmartRefreshLayout) findViewById(R.id.gm_smrlayoyut);
        mIvBossChatAdd = (ImageView) findViewById(R.id.iv_boss_chat_add);
        mEtBossChatInput = (EditText) findViewById(R.id.et_boss_chat_input);
        mBtnBossChatSend = (Button) findViewById(R.id.btn_boss_chat_send);
        mBtnBossChatSend.setOnClickListener(this);
        mLlBossChatButton = (LinearLayout) findViewById(R.id.ll_boss_chat_button);
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

    private void initWebStock() {
        mBossInstance = WsManager.getInstance();
        mBossInstance.init();
        mBossInstance.attchSubject(this);

    }

    private void subimt(String com) {
        mBossInstance.reconnect();
        mEtBossChatInput.setText(null);
        mBossInstance.sendBossText(com, false, "");
    }

    private void subimtImg(String path, String netPath) {
        mBossInstance.reconnect();
        mEtBossChatInput.setText(null);
        mBossInstance.sendBossText(netPath, true, netPath);
    }

    @Override
    public void updataMsg(String msg) {
        AcceptChatService vo = GsonUtils.getGson(msg, AcceptChatService.class);
        if (vo.getFlag().equals("error")) {
            T.showToast(mContext, vo.getMessage());
            return;
        }
        if (vo.getFlag().equals("init")) {
            mBossInstance.registerBoss(Integer.valueOf(mBossId));
            return;
        }
        List<ChatListVo> mList = new ArrayList<>();
        ChatListVo chatListVo = new ChatListVo();
        chatListVo.setType(vo.getType());
        chatListVo.setUserInfom(vo.getFrom_side() == 1);
        if (vo.getFrom_side() == 1) {
            chatListVo.setLogo(mResult.getRight_user().getAvatar());
            chatListVo.setName(mResult.getRight_user().getUsername());
        } else {
            chatListVo.setLogo(mResult.getLeft_user().getAvatar());
            chatListVo.setName(mResult.getLeft_user().getUsername());
        }
        chatListVo.setContent(vo.getContent());
        mList.add(chatListVo);

        List<ChatListVo> data = mChatList.getmData();
        if (data == null) {
            data = new ArrayList<>();
        }
        data.addAll(mList);
        mChatList.setmData(data);
        mChatAdapter.refreshData(mChatList);
        mChatAdapter.notifyDataSetChanged();

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

    private void showPicture() {
        mAlerdialog = new SelectCamerAlerdialog(mContext, R.style.mydialog);
        mAlerdialog.initData(mContext);
        mAlerdialog.setListener(new SelectCamerAlerdialog.OnItemClickListener() {
            @Override
            public void openCamer() {
                ActivityUtil.openCammer(ChatBossActivity.this, REQUESTCODE);
            }

            @Override
            public void openXinCe() {
                ActivityUtil.openXiangCes(ChatBossActivity.this, REQUESTCODECE);
            }
        });
        mAlerdialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Util.dismissDialog(mAlerdialog);
        mBossInstance.disconnect();
        mBossInstance.deleteSubject();
    }
}
