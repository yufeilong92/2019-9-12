package com.tsyc.tianshengyoucai.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.adapter.bank.BankListAdapter;
import com.tsyc.tianshengyoucai.utils.RecyclerUtil;
import com.tsyc.tianshengyoucai.utils.StringUtil;
import com.tsyc.tianshengyoucai.vo.BankListVo;
import com.tsyc.tianshengyoucai.vo.SelectVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : YFL  is Creating a porject in PC$
 * @Email : yufeilong92@163.com
 * @Time :2019/8/8 15:59
 * @Purpose : 添加银行卡
 */
public class SelectBankListAlerDialog extends AlertDialog {
    private DisplayMetrics metrics;
    private RecyclerView mRlvItemDialogBank;
    private TextView mTvBankListCanner;
    private TextView mTvBanKListSure;
    private BankListAdapter mAdapter;
    private BankListVo.ResultBean mSelectData;

    protected SelectBankListAlerDialog(@NonNull Context context) {
        super(context);
        initData(context);
    }


    public SelectBankListAlerDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        initData(context);
    }

    public void initData(Context context) {
        metrics = context.getResources().getDisplayMetrics();
        getWindow().setWindowAnimations(R.style.popup_animation);
    }

    private void setSizeMode() {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = metrics.widthPixels;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
        getWindow().setGravity(Gravity.BOTTOM);
//        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    private List<BankListVo.ResultBean> result;
    private List<SelectVo> mSelectVo;

    public void setData(List<BankListVo.ResultBean> result) {
        this.result = result;
        mSelectVo = clearSelect(result, "");
        bindViewData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSizeMode();
        setContentView(R.layout.item_dialog_banklist);
        inivView();
        initEvent();
    }

    private void bindViewData() {
        RecyclerUtil.setGridManage(getContext(), mRlvItemDialogBank);
        mAdapter = new BankListAdapter(getContext(), result, mSelectVo);
        mRlvItemDialogBank.setAdapter(mAdapter);
        mAdapter.setListener(new BankListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int postion, BankListVo.ResultBean vo) {
                mSelectVo = clearSelect(result, vo.getBank_name());
                mAdapter.setRefresh(mSelectVo);
                mSelectData = vo;
            }
        });

    }

    private List<SelectVo> clearSelect(List<BankListVo.ResultBean> result, String name) {
        List<SelectVo> list = new ArrayList<>();
        if (result == null || result.size() == 0) return list;
        if (StringUtil.isEmpty(name)) {
            for (int i = 0; i < result.size(); i++) {
                BankListVo.ResultBean bean = result.get(i);
                SelectVo vo = new SelectVo();
                vo.setName(bean.getBank_name());
                vo.setSelect(false);
                list.add(vo);
            }
            return list;
        }
        for (int i = 0; i < result.size(); i++) {
            BankListVo.ResultBean bean = result.get(i);
            SelectVo vo = new SelectVo();
            vo.setName(bean.getBank_name());
            vo.setSelect(bean.getBank_name().equals(name));
            list.add(vo);
        }
        return list;


    }

    private void initEvent() {
        mTvBankListCanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mTvBanKListSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    dismiss();
                    listener.sureClicl(mSelectData);
                }
            }
        });
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void sureClicl(BankListVo.ResultBean vo);

    }


    private void inivView() {
        mRlvItemDialogBank = findViewById(R.id.rlv_item_dialog_bank);
        mTvBanKListSure = findViewById(R.id.tv_banK_list_sure);
        mTvBankListCanner = findViewById(R.id.tv_bank_list_canner);

    }
}
