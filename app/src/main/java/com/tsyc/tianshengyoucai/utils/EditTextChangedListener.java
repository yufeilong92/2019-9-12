package com.tsyc.tianshengyoucai.utils;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.tsyc.tianshengyoucai.listener.EditTextContentChangedListener;
import com.tsyc.tianshengyoucai.listener.MyTextWatcherListener;

/**
 * author：van
 * CreateTime：2019/8/26
 * File description：
 */
public class EditTextChangedListener {

    EditTextContentChangedListener mChangeListener;
    public  void setChangeListener(EditTextContentChangedListener changeListener) {
        mChangeListener = changeListener;
    }

    private View view;
    private EditText[] editTexts;

    public EditTextChangedListener(View view) {
        this.view = view;
    }

    public EditTextChangedListener addAllEditText(EditText... editTexts) {
        this.editTexts = editTexts;
        initEditListener();
        return this;
    }


    private void initEditListener() {
        for (EditText editText : editTexts) {
            editText.addTextChangedListener(new textChange());
        }
    }

    private class textChange extends MyTextWatcherListener {
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (checkAllEdit()) {
                mChangeListener.textChanged(true);
                view.setEnabled(true);
            } else {
                view.setEnabled(false);
                mChangeListener.textChanged(false);
            }
        }

    }

    private boolean checkAllEdit() {
        for (EditText editText : editTexts) {
            if (!TextUtils.isEmpty(editText.getText() + "")) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

}
