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
import com.tsyc.tianshengyoucai.adapter.recruit.ChatAdapter;
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
import com.tsyc.tianshengyoucai.view.FlowLayout;
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
 * @Time :2019/9/9 14:46:
 * @Purpose :求职聊天
 */
@RuntimePermissions
public class JobChatActivity extends Base2Activity implements View.OnClickListener, ChatInterface {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private RecyclerView mRlvJobChatContent;
    private ImageView mIvJobChatAdd;
    private EditText mEtJobChatInput;
    private Button mBtnJobChatSend;
    private LinearLayout mLlJobChatButton;
    private ChatAdapter mChatAdapter;
    private ArrayList mArray;
    private ChatVo.ResultBean.MemberBean mUserInfom;
    private ChatVo.ResultBean.ServiceBean mServiceInfom;
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
    private WsManager mChatInstance;

    public static final String CHAID_RECICEID = "rcecivce_id";
    private String mId;
    private TextView mTvJobTitle;
    private TextView mTvJobValues;
    private FlowLayout mFlJobType;
    private ImageView mIvJobLogo;
    private TextView mTvJobUser;
    private TextView mTvJobJobDelete;
    private TextView mTvJobTime;
    private LinearLayout mLlChatBoss;
    private SmartRefreshLayout mJobSmrlayoyut;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_job_chat);
//        initView();
//    }

    @Override
    protected int getComtentView() {
        return R.layout.activity_job_chat;
    }

    @Override
    protected void initContent(Bundle savedInstanceState) {
        if (getIntent() != null) {
            mId = getIntent().getStringExtra(CHAID_RECICEID);
        }
        initView();
        clearData();
        initEvent();
        initWebStock();
        initAdapter();
        initRequest();
    }

    private void initAdapter() {
        mChatAdapter = new ChatAdapter(mContext, mArray);
        RecyclerUtil.setGridManage(mContext, mRlvJobChatContent, mChatAdapter);
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
        RequestJobHttp.getSingleton(this).requestEmployeeTalkInit(mId, new RequestResultCallback() {
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
                    chatListVo.setLogo(mServiceInfom.getService_avatar());
                    chatListVo.setName(mServiceInfom.getService_name());
                    chatListVo.setContent(dataBean.getContent());
                    chatListVo.setType(dataBean.getType());
                    chatListVo.setId(dataBean.getId());
                    mList.add(chatListVo);
                }
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

    private void initEvent() {
        mRlvJobChatContent.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (bottom < oldBottom) {
                    mRlvJobChatContent.post(new Runnable() {
                        @Override
                        public void run() {
                            if (mChatAdapter.getItemCount() > 0) {
                                mRlvJobChatContent.smoothScrollToPosition(mChatAdapter.getItemCount() - 1);
                            }
                        }
                    });
                }
            }
        });
        mIvJobChatAdd.setOnClickListener(v -> {
            Util.hideInputMethod(this);
            JobChatActivityPermissionsDispatcher.showCameraWithPermissionCheck(this);
        });
    }

    private void initWebStock() {
        mChatInstance = WsManager.getInstance();
        mChatInstance.init();
        mChatInstance.attchSubject(this);
        mChatInstance.registerJobId(Integer.valueOf(mId));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_job_chat_input:
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
        mEtJobChatInput.setText(null);
        mChatInstance.sendJobText(com, false, "");
//        if (mChatAdapter.getItemCount() > 0) {
//            mRlvJobChatContent.smoothScrollToPosition(mChatAdapter.getItemCount() - 1);
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
        mEtJobChatInput.setText(null);
        mChatInstance.sendJobText(netPath, true, netPath);
//        if (mChatAdapter.getItemCount() > 0) {
//            mRlvJobChatContent.smoothScrollToPosition(mChatAdapter.getItemCount() - 1);
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
        chatListVo.setUserInfom(vo.getFrom_side() == 1);
        chatListVo.setLogo(mServiceInfom.getService_avatar());
        chatListVo.setName(mServiceInfom.getService_name());
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
                    mRlvJobChatContent.smoothScrollToPosition(mChatAdapter.getItemCount() - 1);
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
                ActivityUtil.openCammer(JobChatActivity.this, REQUESTCODE);
            }

            @Override
            public void openXinCe() {
                ActivityUtil.openXiangCes(JobChatActivity.this, REQUESTCODECE);
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

    private void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mRlvJobChatContent = (RecyclerView) findViewById(R.id.rlv_job_chat_content);
        mIvJobChatAdd = (ImageView) findViewById(R.id.iv_job_chat_add);
        mEtJobChatInput = (EditText) findViewById(R.id.et_job_chat_input);
        mBtnJobChatSend = (Button) findViewById(R.id.btn_job_chat_send);
        mLlJobChatButton = (LinearLayout) findViewById(R.id.ll_job_chat_button);
        mBtnJobChatSend.setOnClickListener(this);
        mTvJobTitle = (TextView) findViewById(R.id.tv_job_title);
        mTvJobTitle.setOnClickListener(this);
        mTvJobValues = (TextView) findViewById(R.id.tv_job_values);
        mTvJobValues.setOnClickListener(this);
        mFlJobType = (FlowLayout) findViewById(R.id.fl_job_type);
        mFlJobType.setOnClickListener(this);
        mIvJobLogo = (ImageView) findViewById(R.id.iv_job_logo);
        mIvJobLogo.setOnClickListener(this);
        mTvJobUser = (TextView) findViewById(R.id.tv_job_user);
        mTvJobUser.setOnClickListener(this);
        mTvJobJobDelete = (TextView) findViewById(R.id.tv_job_job_delete);
        mTvJobJobDelete.setOnClickListener(this);
        mTvJobTime = (TextView) findViewById(R.id.tv_job_time);
        mTvJobTime.setOnClickListener(this);
        mLlChatBoss = (LinearLayout) findViewById(R.id.ll_chat_boss);
        mLlChatBoss.setOnClickListener(this);
        mJobSmrlayoyut = (SmartRefreshLayout) findViewById(R.id.job_smrlayoyut);
        mJobSmrlayoyut.setOnClickListener(this);
    }

}
