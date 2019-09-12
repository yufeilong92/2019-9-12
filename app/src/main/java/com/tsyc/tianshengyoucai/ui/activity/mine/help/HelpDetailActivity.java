package com.tsyc.tianshengyoucai.ui.activity.mine.help;

import android.widget.ImageView;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.model.bean.NormalBean;
import com.tsyc.tianshengyoucai.requet.RequestSettingHttp;
import com.tsyc.tianshengyoucai.ui.base.BaseActivity;
import com.tsyc.tianshengyoucai.ui.base.RequestResultCallback;
import com.tsyc.tianshengyoucai.utils.GsonUtils;
import com.tsyc.tianshengyoucai.utils.T;
import com.tsyc.tianshengyoucai.vo.HelpDetailVo;
import com.zzhoujay.richtext.RichText;

/**
 * @Author : YFL  is Creating a porject in PC
 * @Email : yufeilong92@163.com
 * @Time :2019/8/16 12:06:
 * @Purpose :帮助中心详情页
 */
public class HelpDetailActivity extends BaseActivity {

    private ImageView mGmIvBack;
    private TextView mGmTvTitle;
    private TextView mTvHelpDetailTitel;
    private TextView mTvHelpDetailTitelContent;
    public static final String MHELPID = "id";
    private String mHelpId;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_help_detail);
//        initView();
//    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_help_detail;
    }

    @Override
    public void initData() {
        if (getIntent() != null) {
            mHelpId = getIntent().getStringExtra(MHELPID);
        }
        initView();
        initRequestDetail();

    }

    private void initRequestDetail() {
        showProgress();
        RequestSettingHttp.getSingleton(this).requestHelpDetail(mHelpId, new RequestResultCallback() {
            @Override
            public void success(String success) {
                dismissProgress();
                NormalBean vos = GsonUtils.getGson(success, NormalBean.class);
                if (vos.getCode().equals("100")) {
                    T.showToast(mContext, vos.getMessage());
                    return;
                }
                HelpDetailVo vo = GsonUtils.getGson(success, HelpDetailVo.class);
                bindView(vo);

            }

            @Override
            public void error(String error) {
                dismissProgress();
                toastErrorNet();
            }
        });


    }

    private void bindView(HelpDetailVo vo) {
        HelpDetailVo.ResultBean bean = vo.getResult();
        mTvHelpDetailTitel.setText(bean.getHelp_title() + "?");
        RichText.from(bean.getHelp_info()).into(mTvHelpDetailTitelContent);
    }

    public void initView() {
        mGmIvBack = (ImageView) findViewById(R.id.gm_iv_back);
        mGmTvTitle = (TextView) findViewById(R.id.gm_tv_title);
        mTvHelpDetailTitel = (TextView) findViewById(R.id.tv_help_detail_titel);
        mTvHelpDetailTitelContent = (TextView) findViewById(R.id.tv_help_detail_titel_content);
    }
}
