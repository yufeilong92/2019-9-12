package com.tsyc.tianshengyoucai.ui.activity.mine.money;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.Eventbuss.ConponEvent;
import com.tsyc.tianshengyoucai.Eventbuss.ConponsEvent;
import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.bank.CoponListAdapter;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.vo.CouponListBean;
import com.youth.xframe.utils.log.XLog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/17 15:37:
 * @Purpose :选择红包
 */
public class ShopRedSelectActivity extends BaseActivity {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private RecyclerView mGmRlvContent;
    public static final String LIST = "lIST";
    public static final String TYPE = "type";
    private ArrayList<CouponListBean> mCoponList;
    private String mType;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_shop_red_select);
//        initView();
//    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_shop_red_select;
    }

    @Override
    public void initData() {
        if (getIntent() != null) {
            mCoponList = getIntent().getParcelableArrayListExtra(LIST);
            mType = getIntent().getStringExtra(TYPE);
        }
        initView();
        initEvent();
        initAdapter();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mCoponList.size(); i++) {
            sb.append( mCoponList.get(i).getId()+",\n");
        }

        XLog.e("列表破啊 " + sb.toString());
    }

    private void initAdapter() {
        CoponListAdapter adapter = new CoponListAdapter(mContext, mCoponList);
        RecyclerUtil.setGridManage(mContext, mGmRlvContent, adapter);
        adapter.setListener(new CoponListAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, CouponListBean vo) {
                int voucher_id = mCoponList.get(position).getId();
                XLog.e("initAdapter" + voucher_id);
                if (mType.equals("1")) {
                    EventBus.getDefault().postSticky(new ConponEvent(vo));

                } else {
                    EventBus.getDefault().postSticky(new ConponsEvent(vo));
                }
                mResultTo.finishBase(mContext);
            }
        });
    }

    private void initEvent() {


    }

    public void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResultTo.finishBase(mContext);
            }
        });
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mGmRlvContent = (RecyclerView) findViewById(R.id.gm_rlv_content);
    }
}
