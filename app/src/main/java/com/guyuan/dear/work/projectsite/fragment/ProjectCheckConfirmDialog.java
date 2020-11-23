package com.guyuan.dear.work.projectsite.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.itemDecorator.AddCopyListItemDecorator;
import com.guyuan.dear.customizeview.itemDecorator.AddSendListItemDecorator;
import com.guyuan.dear.databinding.DialogWorkProduceBinding;
import com.guyuan.dear.databinding.DialogWorkProjectCheckBinding;
import com.guyuan.dear.focus.produce.bean.ExecuteRequestBody;
import com.guyuan.dear.focus.produce.bean.OperateProduceType;
import com.guyuan.dear.focus.projectsite.bean.CheckGoodsBean;
import com.guyuan.dear.utils.ToastUtils;
import com.guyuan.dear.work.contractPause.adapters.AddCopyListAdapter;
import com.guyuan.dear.work.contractPause.adapters.AddSendListAdapter;
import com.guyuan.dear.work.contractPause.beans.StaffBean;
import com.guyuan.dear.work.projectsite.bean.PostCheckInfo;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/11/22 11:37
 * @company: 固远（深圳）信息技术有限公司
 */
public class ProjectCheckConfirmDialog extends BottomSheetDialog implements View.OnClickListener {

    private Activity activity;
    private OnDialogClickListener clickListener;
    private DialogWorkProjectCheckBinding viewBinding;
    private CheckGoodsBean checkGoodsBean;


    public ProjectCheckConfirmDialog(@NonNull Context context, @StyleRes int theme, Activity activity) {
        super(context, R.style.BottomSheetDialog);
        this.activity = activity;
    }

    public ProjectCheckConfirmDialog(Activity activity, CheckGoodsBean checkGoodsBean, OnDialogClickListener clickListener) {
        this(activity, 0, activity);
        this.clickListener = clickListener;
        this.checkGoodsBean = checkGoodsBean;
    }


    public static void show(Activity activity, CheckGoodsBean checkGoodsBean, OnDialogClickListener clickListener) {
        ProjectCheckConfirmDialog customerDialog = new ProjectCheckConfirmDialog(activity, checkGoodsBean, clickListener);
        customerDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.dialog_work_project_check, null, false);
        setContentView(viewBinding.getRoot());//核心代码


//        viewBinding.etSearch.setFocusable(true);
//        viewBinding.etSearch.setFocusableInTouchMode(true);
//        viewBinding.etSearch.requestFocus();
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        BottomSheetBehavior mDialogBehavior = BottomSheetBehavior.from((View) viewBinding.getRoot().getParent());
        mDialogBehavior.setPeekHeight(getWindowHeight());

        initView();
        initListener();
    }

    private void initView() {
        //设置不可点击状态
        viewBinding.tvOk.setClickable(false);
        viewBinding.tvOk.setEnabled(false);
        viewBinding.tvOk.setOnClickListener(this);
        viewBinding.tvCancel.setOnClickListener(this);

        //定义底部标签图片大小和位置
        Drawable drawable = getContext().getResources().getDrawable(R.drawable.bg_work_project_rb_selector);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable.setBounds(0, 0, 50, 50);
        //设置图片在文字的哪个方向
        viewBinding.rbRight.setCompoundDrawables(drawable, null, null, null);
        viewBinding.rbWrong.setCompoundDrawables(drawable, null, null, null);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_ok:
                commitApplyInfo();
                break;
            case R.id.iv_pick_image:
                break;
            default:

        }
    }

    private void commitApplyInfo() {
        PostCheckInfo body = new PostCheckInfo();
        if (TextUtils.isEmpty(viewBinding.etSearch.getText().toString())) {
            ToastUtils.showLong(getContext(), "请填内容");
            return;
        }

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
                viewBinding.tvOk.setClickable(!TextUtils.isEmpty(editable.toString()));
                viewBinding.tvOk.setEnabled(!TextUtils.isEmpty(editable.toString()));
                //已输入字数
                int enteredWords = wordLimitNum - editable.length();
                //TextView显示剩余字数
                viewBinding.tvNumber.setText(wordLimitNum - enteredWords + "/240");
                int selectionStart = viewBinding.etSearch.getSelectionStart();
                int selectionEnd = viewBinding.etSearch.getSelectionEnd();
                if (enterWords.length() > wordLimitNum) {
                    //删除多余输入的字（不会显示出来）
                    editable.delete(selectionStart - 1, selectionEnd);
                    viewBinding.etSearch.setText(editable);
                    //设置光标在最后
                    viewBinding.etSearch.setSelection(selectionEnd);
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

    public interface OnDialogClickListener {
        void onCommitInfo(PostCheckInfo data);

    }

}