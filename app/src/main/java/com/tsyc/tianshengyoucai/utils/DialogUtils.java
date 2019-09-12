package com.tsyc.tianshengyoucai.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.R;
import com.tsyc.tianshengyoucai.view.FlowLayout;
import com.tsyc.tianshengyoucai.vo.GmSelectBean;
import com.tsyc.tianshengyoucai.vo.SelectVo;

import java.util.ArrayList;
import java.util.List;

public class DialogUtils {

    private volatile static DialogUtils singleton;

    private DialogUtils() {
    }

    public static DialogUtils getSingleton() {
        if (singleton == null) {
            synchronized (DialogUtils.class) {
                if (singleton == null) {
                    singleton = new DialogUtils();
                }
            }
        }
        return singleton;
    }

    public AlertDialog showProgress(Context mContext) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.mydialog);
        Animation animation = AnimationUtils.loadAnimation(mContext,
                R.anim.loading_animation);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_progress, null);
        ImageView img = view.findViewById(R.id.dialog_image);
        builder.setCancelable(false);
        builder.setView(view);
        AlertDialog alertDialog = builder.show();
        img.startAnimation(animation);
        return alertDialog;
    }


    public interface OnDialogSuceClickListener {
        void sureClick();

        void cannerClick();
    }

    public AlertDialog showSureAlerDialog(Context mContext, String waring, String content, String sure, String canner, OnDialogSuceClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.mydialog);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_sure, null);
        TextView titles = view.findViewById(R.id.tv_dialog_sure_title);
        TextView contents = view.findViewById(R.id.tv_dialog_sure_content);
        TextView canners = view.findViewById(R.id.tv_dialog_sure_canner);
        TextView sures = view.findViewById(R.id.tv_dialog_sure_sure);
        builder.setView(view);
        if (!StringUtil.isEmpty(waring)) {
            titles.setText(waring + "?");
        }
        if (!StringUtil.isEmpty(content)) {
            contents.setText(canner);
        } else {
            contents.setVisibility(View.GONE);
        }
        if (!StringUtil.isEmpty(sure)) {
            sures.setText(sure);
        }
        if (!StringUtil.isEmpty(canner)) {
            canners.setText(canner);
        }
        AlertDialog alertDialog = builder.show();
        alertDialog.setCanceledOnTouchOutside(true);
        canners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    alertDialog.dismiss();
                    listener.cannerClick();
                }
            }
        });
        sures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    alertDialog.dismiss();
                    listener.sureClick();
                }
            }
        });

        return alertDialog;

    }

    public interface OnPartnerClickListener {
        void partnerClick();
    }

    /**
     * 合伙人
     *
     * @param mContext
     * @param listener
     * @return
     */
    public AlertDialog showPartnerAlerDialog(Context mContext, OnPartnerClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.mydialog);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_partner, null);
        View tv = view.findViewById(R.id.tv_dialog_partner);
        builder.setView(view);
        AlertDialog show = builder.show();
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    show.dismiss();
                    listener.partnerClick();
                }
            }
        });

        return show;
    }


    public interface BindAlertDialog {
        void bindClick(int type);
    }

    /**
     * @param mContext
     * @param type     1 微信 2支付宝 3 银行
     * @param lisenter
     * @return
     */
    public AlertDialog showBindBank(Context mContext, int type, BindAlertDialog lisenter) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.mydialog);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_bind_layout, null);
        builder.setView(view);
        AlertDialog show = builder.show();
        ImageView logo = view.findViewById(R.id.iv_dialog_band_logo);
        TextView tvcontent = view.findViewById(R.id.tv_dialog_bind_content);
        TextView tvcontents = view.findViewById(R.id.tv_dialog_bind_contents);
        Button btnCanner = view.findViewById(R.id.btn_dialog_bind_canner);
        Button btnSure = view.findViewById(R.id.btn_dialog_bind_bind);
        String title;
        String content;
        String sure;

        switch (type) {
            case 0:
                title = "您还未绑定支付宝账号，无法提现";
                content = "请先绑定支付宝账号！";
                sure = "去绑定";
                logo.setImageResource(R.mipmap.jft_icon_alipaypay);
                break;
            case 1:
                title = "您还未授权微信账号，无法提现";
                content = "请先绑定微信账号！";
                sure = "去授权";
                logo.setImageResource(R.mipmap.jft_icon_wechatpay);
                break;
            case 2:
                title = "您还未绑定银行卡号，无法提现";
                content = "请先绑定银行卡号！";
                sure = "去绑定";
                logo.setImageResource(R.mipmap.jft_but_cashcard);
                break;
            default:
                title = "您还未绑定支付宝账号，无法提现";
                content = "请先绑定支付宝账号！";
                sure = "去绑定";
                break;
        }
        tvcontent.setText(title);
        tvcontents.setText(content);
        btnSure.setText(sure);
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lisenter != null) {
                    show.dismiss();
                    lisenter.bindClick(type);
                }
            }
        });
        btnCanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();
            }
        });

        return show;
    }


    public interface WithDrawInterface {
        void withdrawOnClicl();

    }

    /**
     * 提现成功
     *
     * @param mContext
     * @param anInterface
     * @return
     */
    public AlertDialog showWithdrawSuccess(Context mContext, WithDrawInterface anInterface) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.mydialog);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_withdraw_layout, null);
        builder.setView(view);
        AlertDialog show = builder.show();
        Button btn = view.findViewById(R.id.btn_dialog_withdraw);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (anInterface != null) {
                    anInterface.withdrawOnClicl();
                }
            }
        });
        return show;
    }

    public interface SexInface {
        void selectSex(int type);
    }

    public AlertDialog showSex(Context mContext, SexInface inface) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_sex_layout, null);
        builder.setView(view);
        AlertDialog show = builder.show();
        RadioGroup radioGroup = view.findViewById(R.id.rg_layout);
        TextView canner = view.findViewById(R.id.tv_sex_canner);
        TextView sure = view.findViewById(R.id.tv_sex_sure);
        final int[] type = {0};
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_girl:
                        type[0] = 1;
                        break;
                    case R.id.rb_boy:
                        type[0] = 2;
                        break;
                }
            }
        });
        canner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inface != null) {
                    show.dismiss();
                    inface.selectSex(type[0]);
                }
            }
        });

        return show;
    }

    public interface birthdayInface {
        void selectSex(int year, int moth, int day);
    }

    public AlertDialog showBirthday(Context mContext, int year, int moth, int day, birthdayInface inface) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_birthday_layout, null);
        builder.setView(view);
        AlertDialog show = builder.show();
        DatePicker dp = view.findViewById(R.id.dp_time);
        TextView canner = view.findViewById(R.id.tv_birthday_canner);
        TextView sure = view.findViewById(R.id.tv_birthday_sure);
        final int[] yearParame = new int[1];
        yearParame[0] = year;
        final int[] monthParame = new int[1];
        monthParame[0] = moth;
        final int[] dayParame = new int[1];
        dayParame[0] = day;
        dp.init(year, moth, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                yearParame[0] = year;
                monthParame[0] = monthOfYear;
                dayParame[0] = dayOfMonth;
            }
        });
        canner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inface != null) {
                    show.dismiss();
                    inface.selectSex(yearParame[0], monthParame[0], dayParame[0]);
                }
            }
        });

        return show;
    }

    public interface WorkTagInterface {
        void onClickListener(List<SelectVo> list);
    }

    public AlertDialog selectWorkTagAlertDialog(Context context, List<GmSelectBean> list, List<SelectVo> mSelectVos, WorkTagInterface anInterface) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.item_work_tag, null);
        builder.setView(view);
        TextView canner = view.findViewById(R.id.tv_item_work_tag_canner);
        TextView sure = view.findViewById(R.id.tv_item_work_tag_sure);
        FlowLayout flowLayout = view.findViewById(R.id.fl_work_tag_content);
        AlertDialog dialog = builder.create();
        dialog.show();
        ArrayList<SelectVo> selectVos = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            GmSelectBean bean = list.get(i);
            SelectVo vo = new SelectVo();
            vo.setName(bean.getDesc());
            vo.setId(bean.getValue());
            if (mSelectVos != null && !mSelectVos.isEmpty()) {
                for (int l = 0; l < mSelectVos.size(); l++) {
                    SelectVo selectVo = mSelectVos.get(l);
                    if (bean.getValue()==selectVo.getId()) {
                        vo.setSelect(true);
                    }
                }
            }else {
                vo.setSelect(false);
            }
            selectVos.add(vo);
        }
        refresh(context, flowLayout, selectVos);
        sure.setOnClickListener(v -> {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            List<SelectVo> selects = new ArrayList<>();
            for (int i = 0; i < selectVos.size(); i++) {
                SelectVo selectVo = selectVos.get(i);
                if (selectVo.isSelect()) {
                    selects.add(selectVo);
                }
            }
            if (anInterface != null) {
                anInterface.onClickListener(selects);
            }

        });
        canner.setOnClickListener(v -> {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        });
        return dialog;
    }

    private void refresh(Context context, FlowLayout flowLayout, ArrayList<SelectVo> selectVos) {
        flowLayout.removeAllViews();
        for (int i = 0; i < selectVos.size(); i++) {
            SelectVo vo = selectVos.get(i);
            View view1 = LayoutInflater.from(context).inflate(R.layout.item_tarde_tag_layout, null);
            TextView tv = view1.findViewById(R.id.tv_tarde_tag);
            int color1 = context.getResources().getColor(R.color.color_444A53);
            int color2 = context.getResources().getColor(R.color.color_5769E7);
            tv.setText(vo.getName());
            tv.setTextColor(color1);
            if (vo.isSelect()) {
                tv.setTextColor(context.getResources().getColor(R.color.color_5769E7));
                tv.setBackgroundResource(R.drawable.gm_circle_blue_bg);
            } else {
                tv.setTextColor(context.getResources().getColor(R.color.color_444A53));
                tv.setBackgroundResource(R.drawable.gm_circle_gray_bg);
            }
            tv.setOnClickListener(v -> {
                int color = tv.getCurrentTextColor();
                if (color == color2) {//选择
                    addListSelectVo(selectVos, vo, false);
                    tv.setTextColor(context.getResources().getColor(R.color.color_444A53));
                    tv.setBackgroundResource(R.drawable.gm_circle_gray_bg);
                    refresh(context, flowLayout, selectVos);
                    return;
                }
                addListSelectVo(selectVos, vo, true);
                tv.setTextColor(context.getResources().getColor(R.color.color_5769E7));
                tv.setBackgroundResource(R.drawable.gm_circle_blue_bg);
                refresh(context, flowLayout, selectVos);
                return;

            });
            flowLayout.addView(view1);
        }
    }

    private void addListSelectVo(List<SelectVo> list, SelectVo vo, boolean isSelect) {
        for (int i = 0; i < list.size(); i++) {
            SelectVo selectVo = list.get(i);
            if (selectVo.getId() == vo.getId()) {
                selectVo.setSelect(isSelect);
            }
        }
    }

}
