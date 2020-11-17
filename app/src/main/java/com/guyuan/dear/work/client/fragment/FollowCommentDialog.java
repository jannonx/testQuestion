package com.guyuan.dear.work.client.fragment;

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
import com.guyuan.dear.databinding.DialogWorkFollowCommentBinding;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.utils.ToastUtils;

/**
 * @description: 填写跟进内容弹框
 * @author: 许建宁
 * @since: 2020/11/5 11:14
 * @company: 固远（深圳）信息技术有限公司
 */
public class FollowCommentDialog extends BottomSheetDialog {


    private Activity activity;
    private OnFollowClickListener clickListener;
    private DialogWorkFollowCommentBinding viewBinding;


    public FollowCommentDialog(@NonNull Context context, @StyleRes int theme, Activity activity) {
        super(context, R.style.BottomSheetDialog);
        this.activity = activity;
    }

    public FollowCommentDialog(Activity activity, OnFollowClickListener clickListener) {
        this(activity, 0, activity);
        this.clickListener = clickListener;
    }


    public static void show(Activity activity, OnFollowClickListener clickListener) {
        FollowCommentDialog customerDialog = new FollowCommentDialog(activity, clickListener);
        customerDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.dialog_work_follow_comment, null, false);
        setContentView(viewBinding.getRoot());//核心代码


//        viewBinding.etSearch.setFocusable(true);
//        viewBinding.etSearch.setFocusableInTouchMode(true);
//        viewBinding.etSearch.requestFocus();
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        BottomSheetBehavior mDialogBehavior = BottomSheetBehavior.from((View) viewBinding.getRoot().getParent());
        mDialogBehavior.setPeekHeight(getWindowHeight());

        init();
    }

    private void init() {
        //设置不可点击状态
        viewBinding.tvSearchBtn.setClickable(false);
        viewBinding.tvSearchBtn.setEnabled(false);
        viewBinding.tvSearchBtn.setChecked(false);
        viewBinding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                LogUtils.showLog("false=" + TextUtils.isEmpty(editable.toString()));
                viewBinding.tvSearchBtn.setClickable(!TextUtils.isEmpty(editable.toString()));
                viewBinding.tvSearchBtn.setEnabled(!TextUtils.isEmpty(editable.toString()));
                viewBinding.tvSearchBtn.setChecked(!TextUtils.isEmpty(editable.toString()));
            }
        });
        viewBinding.tvSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(viewBinding.etSearch.getText().toString())) {
                    ToastUtils.showLong(getContext(), "请填写跟进内容");
                    return;
                }

                if (clickListener != null) {
                    clickListener.onClick(viewBinding.etSearch.getText().toString());
                    dismiss();
                }
            }
        });
    }

    //就是返回页面高度
    private int getWindowHeight() {
        Resources res = activity.getResources();
        DisplayMetrics displayMetrics = res.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }


    public interface OnFollowClickListener {
        void onClick(String content);
    }

}