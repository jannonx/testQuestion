package com.guyuan.dear.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.DialogRemarkBinding;
import com.guyuan.dear.focus.produce.bean.ExecuteRequestBody;
import com.guyuan.dear.utils.ToastUtils;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/11/26 15:55
 * @company : 固远（深圳）信息技术有限公司
 **/

public class RemarkDialog extends BottomSheetDialog {


    private Activity activity;
    private OnDialogClickListener clickListener;
    private DialogRemarkBinding viewBinding;
    private String title;

    public RemarkDialog(@NonNull Context context, @StyleRes int theme, Activity activity) {
        super(context, R.style.BottomSheetDialog);
        this.activity = activity;
    }

    public RemarkDialog(Activity activity, String title, OnDialogClickListener clickListener) {
        this(activity, 0, activity);
        this.clickListener = clickListener;
        this.title = title;
    }


    public static void show(Activity activity, String title, OnDialogClickListener clickListener) {
        RemarkDialog customerDialog = new RemarkDialog(activity, title, clickListener);
        customerDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.dialog_remark, null, false);
        setContentView(viewBinding.getRoot());//核心代码
        BottomSheetBehavior mDialogBehavior = BottomSheetBehavior.from((View) viewBinding.getRoot().getParent());
        mDialogBehavior.setPeekHeight(getWindowHeight());
        initListener();
        viewBinding.tvTitle.setText(title);
        viewBinding.tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewBinding.etSearch.setText("");
                dismiss();
            }
        });

        viewBinding.tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commitApplyInfo();
            }
        });
    }


    private void commitApplyInfo() {
        ExecuteRequestBody body = new ExecuteRequestBody();
        if (TextUtils.isEmpty(viewBinding.etSearch.getText().toString())) {
            ToastUtils.showLong(getContext(), "请填内容");
            return;
        }
        body.setReason(viewBinding.etSearch.getText().toString());

        if (clickListener != null) {
            clickListener.onCommitInfo(body);
            dismiss();
        }

    }


    private void initListener() {
        //记录字数上限
        int wordLimitNum = 240;
        viewBinding.etSearch.addTextChangedListener(new TextWatcher() {
            //记录输入的字数
            private CharSequence enterWords;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //实时记录输入的字数
                enterWords = s;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > wordLimitNum) {
                    //删除多余输入的字（不会显示出来）
                    editable.delete(wordLimitNum, editable.length());
                    viewBinding.etSearch.setText(editable);
                    //设置光标在最后
                    viewBinding.etSearch.setSelection(viewBinding.etSearch.getText().toString().length());
                }

                //已输入字数
                int enteredWords = wordLimitNum - editable.length();
                //TextView显示剩余字数
                viewBinding.tvNumber.setText((wordLimitNum - enteredWords )+ "/240");
            }
        });
    }

    //就是返回页面高度
    private int getWindowHeight() {
        Resources res = activity.getResources();
        DisplayMetrics displayMetrics = res.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    public interface OnDialogClickListener {
        void onCommitInfo(ExecuteRequestBody data);
    }
}