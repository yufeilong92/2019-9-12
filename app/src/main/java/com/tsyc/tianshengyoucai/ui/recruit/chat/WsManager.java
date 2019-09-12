package com.tsyc.tianshengyoucai.ui.recruit.chat;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;
import com.tsyc.tianshengyoucai.AppContext;
import com.tsyc.tianshengyoucai.BuildConfig;
import com.tsyc.tianshengyoucai.ui.recruit.chat.chatObserver.ChatInterface;
import com.tsyc.tianshengyoucai.ui.recruit.chat.chatObserver.ChatSubject;
import com.tsyc.tianshengyoucai.utils.SaveUserInfomUtilJave;
import com.tsyc.tianshengyoucai.vo.UserInfomVo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/9/7 17:12
 * @Purpose :
 */
public class WsManager {
    private static WsManager mInstance;
    private final String TAG = this.getClass().getSimpleName();

    /**
     * WebSocket config
     */
    private static final int FRAME_QUEUE_SIZE = 5;
    private static final int CONNECT_TIMEOUT = 60000;
    private static final String DEF_TEST_URL = "ws://114.115.203.124:8898";//测试服默认地址
    private static final String DEF_RELEASE_URL = "ws://114.115.203.124:8898";//正式服默认地址
    private static final String DEF_URL = BuildConfig.DEBUG ? DEF_TEST_URL : DEF_RELEASE_URL;
    private String url;

    private WsStatus mStatus;
    private WebSocket ws;
    private WsListener mListener;
    private final ChatSubject mSubject;

    private WsManager() {
        mSubject = new ChatSubject();
    }

    public static WsManager getInstance() {
        if (mInstance == null) {
            synchronized (WsManager.class) {
                if (mInstance == null) {
                    mInstance = new WsManager();
                }
            }
        }
        return mInstance;
    }

    public void init() {
        try {
            /**
             * configUrl其实是缓存在本地的连接地址
             * 这个缓存本地连接地址是app启动的时候通过http请求去服务端获取的,
             * 每次app启动的时候会拿当前时间与缓存时间比较,超过6小时就再次去服务端获取新的连接地址更新本地缓存
             */
            String configUrl = "";
            url = TextUtils.isEmpty(configUrl) ? DEF_URL : configUrl;
            ws = new WebSocketFactory().createSocket(url, CONNECT_TIMEOUT)
                    .setFrameQueueSize(FRAME_QUEUE_SIZE)//设置帧队列最大值为5
                    .setMissingCloseFrameAllowed(false)//设置不允许服务端关闭连接却未发送关闭帧
                    .addListener(mListener = new WsListener())//添加回调监听
                    .connectAsynchronously();//异步连接
            setStatus(WsStatus.CONNECTING);
            Log.i(TAG, "第一次连接");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void attchSubject(ChatInterface chatActivity) {
        mSubject.attach(chatActivity);
    }

    /**
     * 客服 id
     */
    private int mId;
    /**
     * 用户类型 1 客服 2boss 列表 3 boss 某个 接收信息， 4 求职者接收信息列表 5求职者消息某个类别
     */
    private int mType;

    public void regithst(int id) {
        mId = id;
        String message = "{\"getway\":\"member_login\",\"key\":\"" + getkey() + "\",\"record_id\":\"" + id + "\"}";
        ws.sendText(message);
    }

    public void sendServiceText(String comdata, boolean isImage, String path) {
        String message = "{\"getway\":\"member_send_msg\",\"key\":\"" + getkey() + "\",\"record_id\":\"" + mId + "\"," +
                "\"type\":" + (isImage ? "2" : "1") + ",\"content\":\"" + (isImage ? path : comdata) + "\"}";
        ws.sendText(message);
    }

    /**
     * Boss 消息列表页
     *
     * @return
     */
    public void regithstBossList() {
        String mId = "{\"getway\":\"boss_lists_login\",\"key\":\"" + getkey() + "\"}";
        ws.sendText(mId);
    }

    public void registerBoss(int id) {
        mId = id;
        String message = "{\"getway\":\"boss_talk_login\",\"key\":\"" + getkey() + "\",\"record_id\":\"" + id + "\"}";
        ws.sendText(message);

    }

    public void sendBossText(String comdata, boolean isImage, String path) {
        String message = "{\"getway\":\"boss_talk_msg\",\"key\":\"" + getkey() + "\",\"record_id\":\"" + mId + "\"," +
                "\"type\":" + (isImage ? "2" : "1") + ",\"content\":\"" + (isImage ? path : comdata) + "\"}";
        ws.sendText(message);
    }

    /**
     * 求职消息列表
     *
     * @return
     */
    public void registerJobList() {
        String message = "{\"getway\":\"employee_lists_login\",\"key\":\"" + getkey() + "\"}";
        ws.sendText(message);
    }

    public void registerJobId(int id) {
        mId = id;
        String message = "{\"getway\":\"employee_talk_login\",\"key\":\"" + getkey() + "\",\"record_id\":\"" + mId + "\"}\n";
        ws.sendText(message);
    }

    public void sendJobText(String comdata, boolean isImage, String path) {
        String message = "{\"getway\":\"boss_talk_msg\",\"key\":\"" + getkey() + "\",\"record_id\":\"" + mId + "\"," +
                "\"type\":" + (isImage ? "2" : "1") + ",\"content\":\"" + (isImage ? path : comdata) + "\"}";
        ws.sendText(message);
    }

    private String getkey() {
        UserInfomVo infom = SaveUserInfomUtilJave.getInstance().getUserInfom();
        UserInfomVo.ResultBean use = infom.getResult();
        return use.getKey();
    }

    /**
     * 继承默认的监听空实现WebSocketAdapter,重写我们需要的方法
     * onTextMessage 收到文字信息
     * onConnected 连接成功
     * onConnectError 连接失败
     * onDisconnected 连接关闭
     */
    class WsListener extends WebSocketAdapter {
        @Override
        public void onTextMessage(WebSocket websocket, String text) throws Exception {
            super.onTextMessage(websocket, text);
            Log.i(TAG + "===onTextMessage", text);
            mSubject.notifyAllChange(text);

        }


        @Override
        public void onConnected(WebSocket websocket, Map<String, List<String>> headers)
                throws Exception {
            super.onConnected(websocket, headers);
            Log.i(TAG + "===onConnected", "连接成功");
            setStatus(WsStatus.CONNECT_SUCCESS);
        }


        @Override
        public void onConnectError(WebSocket websocket, WebSocketException exception)
                throws Exception {
            super.onConnectError(websocket, exception);
            Log.i(TAG + "===onConnectError", "连接错误");
            setStatus(WsStatus.CONNECT_FAIL);
        }


        @Override
        public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer)
                throws Exception {
            super.onDisconnected(websocket, serverCloseFrame, clientCloseFrame, closedByServer);
            Log.i(TAG + "===onDisconnected", "断开连接");
            setStatus(WsStatus.CONNECT_FAIL);
        }
    }

    private void setStatus(WsStatus status) {
        this.mStatus = status;
    }

    private WsStatus getStatus() {
        return mStatus;
    }

    public void disconnect() {
        if (ws != null)
            ws.disconnect();

    }

    public void sendClose() {
        if (ws != null) {
            ws.sendClose();
            setStatus(WsStatus.CONNECT_FAIL);
        }
    }

    public boolean islink() {
        if (ws != null) {
            return ws.isOpen();
        }
        return false;
    }

    private Handler mHandler = new Handler();

    private int reconnectCount = 0;//重连次数
    private long minInterval = 3000;//重连最小时间间隔
    private long maxInterval = 60000;//重连最大时间间隔


    public void reconnect() {
        if (!isNetConnect()) {
            reconnectCount = 0;
            Log.i(TAG, "重连失败网络不可用");
            return;
        }

        //这里其实应该还有个用户是否登录了的判断 因为当连接成功后我们需要发送用户信息到服务端进行校验
        //由于我们这里是个demo所以省略了
        if (ws != null &&
                !ws.isOpen() &&//当前连接断开了
                getStatus() != WsStatus.CONNECTING) {//不是正在重连状态

            reconnectCount++;
            setStatus(WsStatus.CONNECTING);

            long reconnectTime = minInterval;
            if (reconnectCount > 3) {
                url = DEF_URL;
                long temp = minInterval * (reconnectCount - 2);
                reconnectTime = temp > maxInterval ? maxInterval : temp;
            }

            Log.i(TAG, "准备开始第%d次重连,重连间隔%d -- url:%s" + reconnectCount + reconnectTime + url);
            mHandler.postDelayed(mReconnectTask, reconnectTime);

        }
    }


    private Runnable mReconnectTask = new Runnable() {

        @Override
        public void run() {
            try {
                ws = new WebSocketFactory().createSocket(url, CONNECT_TIMEOUT)
                        .setFrameQueueSize(FRAME_QUEUE_SIZE)//设置帧队列最大值为5
                        .setMissingCloseFrameAllowed(false)//设置不允许服务端关闭连接却未发送关闭帧
                        .addListener(mListener = new WsListener())//添加回调监听
                        .connectAsynchronously();//异步连接
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };


    private void cancelReconnect() {
        reconnectCount = 0;
        mHandler.removeCallbacks(mReconnectTask);
    }


    private boolean isNetConnect() {
        ConnectivityManager connectivity = (ConnectivityManager) AppContext.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }

    public void deleteSubject() {
        mId = 0;
        mSubject.deleteAll();
    }

}
