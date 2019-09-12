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
import com.tsyc.tianshengyoucai.adapter.recruit.ChatJobAdapter;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.requet.RequestJobHttp;
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
import com.tsyc.tianshengyoucai.vo.ChatDataBeanVo;
import com.tsyc.tianshengyoucai.vo.ChatJobListData;
import com.tsyc.tianshengyoucai.vo.ChatListVo;
import com.tsyc.tianshengyoucai.vo.GmImagerVo;
import com.tsyc.tianshengyoucai.vo.JobChatMsgVo;
import com.youth.xframe.widget.XToast;
import com.zhihu.matisse.Matisse;

import java.util.ArrayList;
import java.util.Collections;
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
 * @Time :2019/9/10 10:02:
 * @Purpose :求职聊天
 */
@RuntimePermissions
public class ChatJobActivity extends Base2Activity implements View.OnClickListener, ChatInterface {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private RecyclerView mGmRlvContent;
    private ImageView mIvEmpty;
    private SmartRefreshLayout mGmSmrlayoyut;
    private ImageView mIvJobChatAdd;
    private EditText mEtJobChatInput;
    private Button mBtnJobChatSend;
    private LinearLayout mLlJobChatButton;
    private ArrayList mArray;
    private boolean isRefresh;
    private int mPage;
    private ChatJobAdapter mAdapter;
    public static final String RECIVER_ID = "RECIVER_ID";
    private String mJobChatId;
    private JobChatMsgVo.ResultBean mResult;
    private WsManager mBossInstance;
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

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_chat_job);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_chat_job;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        if (getIntent() != null) {
            mJobChatId = getIntent().getStringExtra(RECIVER_ID);
        }
        initView();
        clearData();
        initEvent();
        initWebStock();
        initAdapter();
        initRefresh();
        showProgress();
        initRequest(true);
    }


    private void initEvent() {
        mIvJobChatAdd.setOnClickListener(v -> {
            ChatJobActivityPermissionsDispatcher.showCameraWithPermissionCheck(this);
        });

    }

    private void initWebStock() {
        mBossInstance = WsManager.getInstance();
        mBossInstance.init();
        mBossInstance.attchSubject(this);

    }

    private void initAdapter() {
        mAdapter = new ChatJobAdapter(mContext, mArray);
        RecyclerUtil.setGridManageTwo(mContext, mGmRlvContent, mAdapter);
        mAdapter.setListener(new ChatJobAdapter.OnItemClickListener() {
            @Override
            public void itemClick(String comtent) {
                List<String> strings = new ArrayList<>();
                strings.add(comtent);
                mResultTo.startEvaluateImager(mContext, 0, strings);
            }
        });
        mGmRlvContent.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (bottom < oldBottom) {
                    mGmRlvContent.post(new Runnable() {
                        @Override
                        public void run() {
                            if (mAdapter.getItemCount() > 0) {
                                mGmRlvContent.smoothScrollToPosition(mAdapter.getItemCount() - 1);
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mBossInstance.sendClose();
        mBossInstance.deleteSubject();
    }

    private void initRefresh() {
        mGmSmrlayoyut.setEnableLoadMore(false);
        mGmSmrlayoyut.setOnRefreshListener(refreshLayout -> {
            initRequest(false);
        });
    }

    private void initRequest(boolean refresh) {
        if (isRefresh) return;
        isRefresh = true;
        if (refresh) mPage = 1;
        RequestJobHttp.getSingleton(this).requestEmployeeTalkInit(mJobChatId, new RequestResultCallback() {
            @Override
            public void success(String success) {
                isRefresh = false;
                if (!refresh)
                    mGmSmrlayoyut.finishRefresh();
                if (onSuccess(success)) return;
                JobChatMsgVo vo = GsonUtils.getGson(success, JobChatMsgVo.class);
                mResult = vo.getResult();

                List<ChatDataBeanVo> data = mResult.getLogs().getData();
                ArrayList<ChatJobListData> mList = new ArrayList<>();
                if (refresh) {
                    clearData();
                    ChatJobListData bossListData = new ChatJobListData();
                    bossListData.setHear(mResult.getHead_card());
                    bossListData.setTime(mResult.getWho_start());
                    mList.add(bossListData);
                }
                if (data != null && !data.isEmpty()) {
                    for (int i = 0; i < data.size(); i++) {
                        ChatDataBeanVo dataBean = data.get(i);
                        ChatJobListData bossListData = new ChatJobListData();

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

                        bossListData.setmData(chatListVo);
                        bossListData.setHear(mResult.getHead_card());
                        bossListData.setTime(mResult.getWho_start());
                        mList.add(bossListData);
                    }
                }
                if (refresh) {
                    mBossInstance.registerJobId(Integer.valueOf(mJobChatId));
                }
                if (refresh)
                    addData(mList);
                 else
                     addDataTop(mList);
                if (data == null || data.isEmpty()) {
                    mGmSmrlayoyut.setEnableRefresh(false);
                    mAdapter.changerText("暂无历史记录");
                    mAdapter.notifyDataSetChanged();
                    return;
                }
                if (mArray.size() >= mResult.getLogs().getTotal()) {
                    mGmSmrlayoyut.setEnableRefresh(false);
                    mAdapter.changerText("暂无历史记录");
                } else {
                    mPage = mResult.getLogs().getCurrent_page() + 1;
                    mGmSmrlayoyut.setEnableRefresh(true);
                }
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void error(String error) {
                isRefresh = false;
                onError();
                if (!refresh)
                    mGmSmrlayoyut.finishLoadMore();
            }
        });

    }

    private void addDataTop(ArrayList<?> mList) {
        if (mList == null || mList.isEmpty()) {
            return;
        }
        if (mArray == null) {
            clearData();
        }
        Collections.reverse(mList);
        mArray.addAll(0, mList);
    }

    private void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mGmRlvContent = (RecyclerView) findViewById(R.id.gm_rlv_content);
        mIvEmpty = (ImageView) findViewById(R.id.iv_empty);
        mGmSmrlayoyut = (SmartRefreshLayout) findViewById(R.id.gm_smrlayoyut);
        mIvJobChatAdd = (ImageView) findViewById(R.id.iv_job_chat_add);
        mEtJobChatInput = (EditText) findViewById(R.id.et_job_chat_input);
        mBtnJobChatSend = (Button) findViewById(R.id.btn_job_chat_send);
        mLlJobChatButton = (LinearLayout) findViewById(R.id.ll_job_chat_button);

        mBtnJobChatSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_job_chat_send:
                String com = StringUtil.getObjectToStr(mEtJobChatInput);
                if (StringUtil.isEmpty(com)) {
                    T.showToast(mContext, "请输入发送内容");
                    return;
                }
                subimt(com);
                break;
        }
    }

    private void subimt(String com) {
        mBossInstance.reconnect();
        mEtJobChatInput.setText(null);
        mBossInstance.sendJobText(com, false, "");
    }

    private void subimtImg(String path, String netPath) {
        mBossInstance.reconnect();
        mEtJobChatInput.setText(null);
        mBossInstance.sendJobText(netPath, true, netPath);
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
    public void updataMsg(String msg) {
        AcceptChatService vo = GsonUtils.getGson(msg, AcceptChatService.class);
        if (vo.getFlag().equals("error")) {
            T.showToast(mContext, vo.getMessage());
            return;
        }
        if (vo.getFlag().equals("init")) {
            showProgress();
            T.showToast(mContext, "连接中");
            connect();
            return;
        }
        List<ChatJobListData> mList = new ArrayList<>();
        ChatJobListData bossListData = new ChatJobListData();

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
        bossListData.setmData(chatListVo);
        mList.add(bossListData);
        addData(mList);
        move();
        mAdapter.notifyDataSetChanged();
    }

    private void connect() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                T.showToast(mContext, "连接成功");
                dismissProgress();
                mBossInstance.registerJobId(Integer.valueOf(mJobChatId));
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 500);
    }

    private void move() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (mAdapter.getItemCount() > 0) {
                    mGmRlvContent.smoothScrollToPosition(mAdapter.getItemCount() - 1);
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 500);
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
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
                ActivityUtil.openCammer(ChatJobActivity.this, REQUESTCODE);
            }

            @Override
            public void openXinCe() {
                ActivityUtil.openXiangCes(ChatJobActivity.this, REQUESTCODECE);
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
