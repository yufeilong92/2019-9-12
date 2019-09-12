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

import com.neovisionaries.ws.client.WebSocket;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.recruit.ChatAdapter;
import com.tsyc.tianshengyoucai.base.Base2Activity;
import com.tsyc.tianshengyoucai.requet.RequestSettingHttp;
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
import com.tsyc.tianshengyoucai.vo.ChatListVo;
import com.tsyc.tianshengyoucai.vo.ChatVo;
import com.tsyc.tianshengyoucai.vo.GmImagerVo;
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
 * @Time :2019/9/7 12:17:
 * @Purpose :聊天界面
 */
@RuntimePermissions
public class ChatActivity extends Base2Activity implements View.OnClickListener, ChatInterface {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private RecyclerView mRlvChatContent;
    private ImageView mIvChatAdd;
    private EditText mEtChatInput;
    private Button mBtnChatSend;
    private LinearLayout mLlChatButton;
    private static final String TAG = "===";
    private WebSocket mSocket;
    private ArrayList mArray;
    private ChatAdapter mChatAdapter;
    private ChatVo.ResultBean.MemberBean mUserInfom;
    private WsManager mChatInstance;
    private ChatVo.ResultBean.ServiceBean mServiceInfom;
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
//        setContentView(R.layout.activity_chat);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_chat;

    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        initView();
        clearData();
        initEvent();
        initWebStock();
        initAdapter();
        initRequest();
    }

    private void initAdapter() {
        mChatAdapter = new ChatAdapter(mContext, mArray);
        RecyclerUtil.setGridManage(mContext, mRlvChatContent, mChatAdapter);
        mChatAdapter.setListener(new ChatAdapter.OnItemClickListener() {
            @Override
            public void itemClick(String content) {
                List<String> strings = new ArrayList<>();
                strings.add(content);
                mResultTo.startEvaluateImager(mContext, 0, strings);
            }
        });
        mRlvChatContent.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (bottom < oldBottom) {
                    mRlvChatContent.post(new Runnable() {
                        @Override
                        public void run() {
                            if (mChatAdapter.getItemCount() > 0) {
                                mRlvChatContent.smoothScrollToPosition(mChatAdapter.getItemCount() - 1);
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    private void initRequest() {
        showProgress();
        RequestSettingHttp.getSingleton(this).requestMyService(new RequestResultCallback() {
            @Override
            public void success(String success) {
                if (onSuccess(success)) return;
                ChatVo vo = GsonUtils.getGson(success, ChatVo.class);
                ChatVo.ResultBean result = vo.getResult();
                mUserInfom = result.getMember();
                mServiceInfom = result.getService();
                List<ChatDataBeanVo> data = result.getLogs().getData();
                ArrayList<ChatListVo> mList = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    ChatDataBeanVo dataBean = data.get(i);
                    ChatListVo chatListVo = new ChatListVo();
                    chatListVo.setUserInfom(dataBean.getFrom_side() == 1);
                    if (dataBean.getFrom_side()==1){
                        chatListVo.setLogo(mUserInfom.getMember_avatar());
                        chatListVo.setName(mUserInfom.getMember_name());
                    }else {
                        chatListVo.setLogo(mServiceInfom.getService_avatar());
                        chatListVo.setName(mServiceInfom.getService_name());
                    }

                    chatListVo.setContent(dataBean.getContent());
                    chatListVo.setType(dataBean.getType());
                    chatListVo.setId(dataBean.getId());
                    mList.add(chatListVo);
                }
                registerWebStoct(result.getRecord_id());
                addData(mList);
                mChatAdapter.notifyDataSetChanged();
                move();
            }

            @Override
            public void error(String error) {
                onError();
            }
        });

    }

    private void registerWebStoct(int record_id) {
        mChatInstance.regithst(record_id);
    }

    private void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mRlvChatContent = (RecyclerView) findViewById(R.id.rlv_chat_content);
        mIvChatAdd = (ImageView) findViewById(R.id.iv_chat_add);
        mEtChatInput = (EditText) findViewById(R.id.et_chat_input);
        mBtnChatSend = (Button) findViewById(R.id.btn_chat_send);
        mLlChatButton = (LinearLayout) findViewById(R.id.ll_chat_button);

        mBtnChatSend.setOnClickListener(this);
    }

    private void initEvent() {
        mRlvChatContent.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (bottom < oldBottom) {
                    mRlvChatContent.post(new Runnable() {
                        @Override
                        public void run() {
                            if (mChatAdapter.getItemCount() > 0) {
                                mRlvChatContent.smoothScrollToPosition(mChatAdapter.getItemCount() - 1);
                            }
                        }
                    });
                }
            }
        });
        mIvChatAdd.setOnClickListener(v -> {
            Util.hideInputMethod(this);
            ChatActivityPermissionsDispatcher.showCameraWithPermissionCheck(this);
        });
    }

    private void initWebStock() {
        mChatInstance = WsManager.getInstance();
        mChatInstance.init();
        mChatInstance.attchSubject(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_chat_send:
                String com = StringUtil.getObjectToStr(mEtChatInput);
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
        mEtChatInput.setText(null);
        mChatInstance.sendServiceText(com, false, "");
//        if (mChatAdapter.getItemCount() > 0) {
//            mRlvChatContent.smoothScrollToPosition(mChatAdapter.getItemCount() - 1);
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
        mEtChatInput.setText(null);
        mChatInstance.sendServiceText(netPath, true, netPath);
//        if (mChatAdapter.getItemCount() > 0) {
//            mRlvChatContent.smoothScrollToPosition(mChatAdapter.getItemCount() - 1);
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
        mChatInstance.disconnect();
        mChatInstance.deleteSubject();
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
            chatListVo.setLogo(mUserInfom.getMember_avatar());
            chatListVo.setName(mUserInfom.getMember_name());
        }else {
            chatListVo.setLogo(mServiceInfom.getService_avatar());
            chatListVo.setName(mServiceInfom.getService_name());
        }
        chatListVo.setContent(vo.getContent());
        mList.add(chatListVo);
        addData(mList);
        move();
        mChatAdapter.notifyDataSetChanged();
    }

    private void move() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (mChatAdapter.getItemCount() > 0) {
                    mRlvChatContent.smoothScrollToPosition(mChatAdapter.getItemCount() - 1);
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 500);
    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mChatInstance.sendClose();
        mChatInstance.deleteSubject();
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
                ActivityUtil.openCammer(ChatActivity.this, REQUESTCODE);
            }

            @Override
            public void openXinCe() {
                ActivityUtil.openXiangCes(ChatActivity.this, REQUESTCODECE);
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
}
