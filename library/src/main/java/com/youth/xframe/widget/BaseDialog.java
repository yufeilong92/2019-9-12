package com.youth.xframe.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.youth.xframe.R;

import static android.content.DialogInterface.BUTTON_NEGATIVE;
import static android.content.DialogInterface.BUTTON_POSITIVE;

/**
 * 创 建 者：van
 * 创建日期：2017/11/23.
 * 描    述： 弹出框界面
 * 修改描述：
 * 修改日期：
 */
public class BaseDialog {

    /**
     * 自定义 dialog
     *
     * @param mContext
     * @param dialogTitle
     * @param dialogBody
     * @param callBack
     */
    public static void showNormalDialog(Context mContext, String dialogTitle, String dialogBody,
                                        String posBtn, String negBtn, String normalBtn, final NormalDialogCallBack callBack) {

        final AlertDialog.Builder mDialog = new AlertDialog.Builder(mContext);
        mDialog.setTitle(dialogTitle);
        mDialog.setMessage(dialogBody);
        mDialog.setPositiveButton(posBtn,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (callBack != null) {
                            callBack.onPosClick(dialog, which);
                        }
                    }
                });
        mDialog.setNegativeButton(negBtn,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (callBack != null) {
                            callBack.onNegClick(dialog, which);
                        }
                    }
                });

        mDialog.setNeutralButton(normalBtn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (callBack != null) {
                    callBack.onNorClick(dialog, which);
                }
            }
        });
        // 显示
        mDialog.show();

    }

    /**
     *  dialog 改变按钮字体颜色
     *
     * @param mContext
     * @param dialogTitle
     * @param dialogBody
     * @param posBtn
     * @param negBtn
     * @param callBack
     */
    public static void selfBtnDialog(Context mContext, String dialogTitle, String dialogBody,
                                       String posBtn, String negBtn, final DoubleDialogCallBack callBack) {
        AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setTitle(dialogTitle)
                .setMessage(dialogBody)
                .setPositiveButton(posBtn, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (callBack!=null)
                            callBack.onPosClick(dialog,which);
                    }
                })
                .setNegativeButton(negBtn, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (callBack!=null)
                            callBack.onNegClick(dialog,which);
                    }
                })
                .create();
        dialog.show();
        dialog.getButton(BUTTON_POSITIVE).setTextColor(mContext.getResources().getColor(R.color.blue));
        dialog.getButton(BUTTON_NEGATIVE).setTextColor(mContext.getResources().getColor(R.color.gray_bf));
    }

    /**
     * 两个按钮 弹窗
     *
     * @param mContext
     * @param dialogTitle
     * @param dialogBody
     * @param callBack
     */
    public static void doubleBtnDialog(Context mContext, String dialogTitle, String dialogBody,
                                        String posBtn, String negBtn, final DoubleDialogCallBack callBack) {

        final AlertDialog.Builder mDialog = new AlertDialog.Builder(mContext);
        mDialog.setTitle(dialogTitle);
        mDialog.setMessage(dialogBody);
        mDialog.setPositiveButton(posBtn,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (callBack != null) {
                            callBack.onPosClick(dialog, which);
                        }
                    }
                });
        mDialog.setNegativeButton(negBtn,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (callBack != null) {
                            callBack.onNegClick(dialog, which);
                        }
                    }
                });

        // 显示
        mDialog.show();

    }

    /**
     *  两个按钮 弹窗
     *
     * @param mContext
     * @param dialogTitle
     * @param dialogBody
     * @param callBack
     */
    public static void doubleBtnDialog(Context mContext, int dialogTitle, int dialogBody,
                                        int posBtn, int negBtn, final DoubleDialogCallBack callBack) {

        final AlertDialog.Builder mDialog = new AlertDialog.Builder(mContext);
        mDialog.setTitle(dialogTitle);
        mDialog.setMessage(dialogBody);

        mDialog.setPositiveButton(posBtn,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (callBack != null) {
                            callBack.onPosClick(dialog, which);
                        }
                    }
                });
        mDialog.setNegativeButton(negBtn,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (callBack != null) {
                            callBack.onNegClick(dialog, which);
                        }
                    }
                });
        mDialog.setCancelable(false);
        // 显示
        mDialog.show();
    }

    /**
     *一个按钮 弹窗
     * @param mContext
     * @param dialogTitle
     * @param dialogBody
     * @param posBtn
     * @param callBack
     */
    public static void singleDialog(Context mContext, int dialogTitle, int dialogBody,
                                        int posBtn, final SingleDialogCallBack callBack) {

        final AlertDialog.Builder mDialog = new AlertDialog.Builder(mContext);

        mDialog.setTitle(dialogTitle);
        mDialog.setMessage(dialogBody);

        mDialog.setPositiveButton(posBtn,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (callBack != null) {
                            callBack.onPosClick(dialog, which);
                        }
                    }
                });
        mDialog.setCancelable(false);
        // 显示
        mDialog.show();
    }

    /**
     *  一个按钮 弹窗
     * @param mContext
     * @param dialogTitle
     * @param dialogBody
     * @param posBtn
     * @param callBack
     */
    public static void singleDialog(Context mContext, String dialogTitle, String dialogBody,
                                    String posBtn, final SingleDialogCallBack callBack) {
        final AlertDialog.Builder mDialog = new AlertDialog.Builder(mContext);

        mDialog.setTitle(dialogTitle);
        mDialog.setMessage(dialogBody);
        mDialog.setPositiveButton(posBtn,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (callBack != null) {
                            callBack.onPosClick(dialog, which);
                        }
                    }
                });
        mDialog.setCancelable(false);
        // 显示
        mDialog.show();

    }

    public interface NormalDialogCallBack {

        void onPosClick(DialogInterface dialog, int which);
        void onNegClick(DialogInterface dialog, int which);
        void onNorClick(DialogInterface dialog, int which);
    }

    public interface DoubleDialogCallBack {

        void onPosClick(DialogInterface dialog, int which);
        void onNegClick(DialogInterface dialog, int which);
    }

    public interface SingleDialogCallBack {
        void onPosClick(DialogInterface dialog, int which);
    }
}
