package com.tsyc.tianshengyoucai.utils;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tsyc.tianshengyoucai.constant.Constant;
import com.tsyc.tianshengyoucai.model.bean.ScanJsonBean;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import java.io.ByteArrayOutputStream;

public class StringUtil {


    public static boolean isEmpty(String str) {
        if (str == null || str.equals(""))
            return true;
        return false;
    }

    public static String getObjectToStr(View v) {
        if (v instanceof TextView) {
            TextView tv = (TextView) v;
            return tv.getText().toString().trim();
        }
        if (v instanceof EditText) {
            EditText et = (EditText) v;
            return et.getText().toString().trim();
        }
        if (v instanceof Button) {
            Button btn = (Button) v;
            return btn.getText().toString().trim();
        }
        return "";
    }

    public static String getStringWid(Context m, int id) {
        return m.getResources().getString(id);
    }


    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean isEquest(String com, String coms) {
        if (com.equals(coms)) {
            return true;
        }
        return false;

    }

    public static void copyText(Context mContext, String copyText) {
        ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData mClipData = ClipData.newPlainText("Label", copyText);
        cm.setPrimaryClip(mClipData);
        XToast.normal("复制成功");
    }


    // 检测扫描结果
    public static String checkCode(String code) {
        if (code != null) {
            ScanJsonBean jsonBean = FastJsonUtil.fromJson(code, ScanJsonBean.class);
            if (jsonBean == null) {
                return "";
            }
            if (jsonBean.getType().equals(Constant.SCAN_UNDERLINE_ORDER)) {
                return jsonBean.getMoney();
            }
        }
        return "";
    }


    // 从黏贴板获取数据
    public static String getPasteString(Activity activity) {
        // 获取并保存粘贴板里的内容
        try {
            activity.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clipData = clipboard.getPrimaryClip();
                    if (clipData != null && clipData.getItemCount() > 0) {
                        CharSequence text = clipData.getItemAt(0).getText();
                        String pasteString = text.toString();
                        Log.e("粘贴板：", "getFromClipboard text=" + pasteString);
                    }
                }
            });
        } catch (Exception e) {
            Log.e("粘贴板：", "getFromClipboard error");
            e.printStackTrace();
            return "";
        }
        return "";
    }

   static String taoCode = "";

    /**
     * 获取粘贴板数据 ，是否是口令
     *
     * @param activity
     * @return
     */
    public static String getTaoCode(Activity activity) {
        try {
            activity.runOnUiThread(() -> {
                ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
                if (clipboard == null) return;

                ClipData clipData = clipboard.getPrimaryClip();
                if (clipData != null && clipData.getItemCount() > 0) {
                    CharSequence text = clipData.getItemAt(0).getText();
                    String pasteString = text.toString();

                    if (!pasteString.endsWith("进入APP商城") && !pasteString.contains("@")) {
                        XLog.e("没有找到@，口令不合法");
                        return;
                    }
                    int endOaIndex = pasteString.lastIndexOf("@");
                    String firstOaStr = pasteString.substring(0, endOaIndex);

                    if (!firstOaStr.contains("@")) {
                        XLog.e("没有找到开始的@");
                        return;
                    }
                    int firstOaIndex = firstOaStr.lastIndexOf("@");

                    taoCode = pasteString.substring(firstOaIndex + 1, endOaIndex);
                    if (taoCode.length() != 4) {
                        XLog.e("口令长度不符合");
                        return;
                    }
                    XLog.e("淘口令 邀请码  " + taoCode);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return taoCode;

        }
        return taoCode;
    }

    // 是否能滑动
    public static boolean canVerticalScroll(EditText contentEt) {
        //滚动的距离
        int scrollY = contentEt.getScrollY();
        //控件内容的总高度
        int scrollRange = contentEt.getLayout().getHeight();
        //控件实际显示的高度
        int scrollExtent = contentEt.getHeight() - contentEt.getCompoundPaddingTop() - contentEt.getCompoundPaddingBottom();
        //控件内容总高度与实际显示高度的差值
        int scrollDifference = scrollRange - scrollExtent;

        if (scrollDifference == 0) {
            return false;
        }
        return (scrollY > 0) || (scrollY < scrollDifference - 1);
    }
}
