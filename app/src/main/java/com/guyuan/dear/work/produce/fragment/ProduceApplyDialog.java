package com.guyuan.dear.work.produce.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.guyuan.dear.R;
import com.guyuan.dear.base.adapter.TagStaffAdapter;
import com.guyuan.dear.customizeview.itemDecorator.AddCopyListItemDecorator;
import com.guyuan.dear.customizeview.itemDecorator.AddSendListItemDecorator;
import com.guyuan.dear.focus.produce.bean.ExecuteRequestBody;
import com.guyuan.dear.focus.produce.bean.OperateProduceType;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.work.contractPause.adapters.AddCopyListAdapter;
import com.guyuan.dear.work.contractPause.adapters.AddSendListAdapter;
import com.guyuan.dear.work.contractPause.beans.StaffBean;
import com.guyuan.dear.databinding.DialogWorkProduceBinding;
import com.guyuan.dear.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/11/5 11:14
 * @company: 固远（深圳）信息技术有限公司
 */
public class ProduceApplyDialog extends BottomSheetDialog implements View.OnClickListener {


    private Activity activity;
    private OnDialogClickListener clickListener;
    private DialogWorkProduceBinding viewBinding;
    private AddSendListAdapter sendAdapter;
    private AddCopyListAdapter copyAdapter;
    private BaseRecyclerViewAdapter baseSendAdapter, baseCopyAdapter;
    private List<StaffBean> sendStaffList = new ArrayList<>();
    private List<StaffBean> copyStaffList = new ArrayList<>();
    private OperateProduceType operateProduceType;


    public ProduceApplyDialog(@NonNull Context context, @StyleRes int theme, Activity activity) {
        super(context, R.style.BottomSheetDialog);
        this.activity = activity;
    }

    public ProduceApplyDialog(Activity activity, OperateProduceType type, OnDialogClickListener clickListener) {
        this(activity, 0, activity);
        this.clickListener = clickListener;
        this.operateProduceType = type;
    }


    public static void show(Activity activity, OperateProduceType type, OnDialogClickListener clickListener) {
        ProduceApplyDialog customerDialog = new ProduceApplyDialog(activity, type, clickListener);
        customerDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.dialog_work_produce, null, false);
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
        viewBinding.ivSentTo.setOnClickListener(this);
        viewBinding.ivCopyTo.setOnClickListener(this);

        viewBinding.tvTitle.setText(OperateProduceType.TYPE_EXECUTE_ACTIVATE == operateProduceType
                ? "激活申请" : "提交暂停");
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
            case R.id.iv_sent_to:
                if (clickListener == null) return;
                clickListener.onSendClick(sendAdapter,copyAdapter);
                break;
            case R.id.iv_copy_to:
                if (clickListener == null) return;
                clickListener.onCopyClick(copyAdapter,sendAdapter);
                break;

        }
    }

    private void commitApplyInfo() {
        ExecuteRequestBody body = new ExecuteRequestBody();
        if (TextUtils.isEmpty(viewBinding.etSearch.getText().toString())) {
            ToastUtils.showLong(getContext(), "请填内容");
            return;
        }
        body.setType(operateProduceType.getCode());
        body.setReason(viewBinding.etSearch.getText().toString());
        //审批人
//        StringBuilder sendBuilder = new StringBuilder();

        if (sendAdapter != null) {
            List<Long> sendArrList = new ArrayList<>();
            ArrayList<StaffBean> tagDataList = (ArrayList<StaffBean>) sendAdapter.getList();
            for (int ik = 0; ik < tagDataList.size(); ik++) {
                StaffBean staffBean = tagDataList.get(ik);
                sendArrList.add(staffBean.getId());
            }
            body.setApprovers(sendArrList);
        }
        //抄送人
        if (copyAdapter != null) {
            List<Long> copyArrList = new ArrayList<>();
            ArrayList<StaffBean> tagDataList = (ArrayList<StaffBean>) copyAdapter.getList();
            for (int ik = 0; ik < tagDataList.size(); ik++) {
                StaffBean staffBean = tagDataList.get(ik);
                copyArrList.add(staffBean.getId());
            }
            body.setNoticedPeople(copyArrList);
        }
        if (clickListener != null) {
            clickListener.onCommitInfo(body);
            dismiss();
        }

    }

    /**
     * 显示处理人数据
     */
    public void setSendToList(List<StaffBean> staffBeanList) {
        LogUtils.showLog("setSendToList00=" + sendStaffList.size() + "....staffBeanList=" + staffBeanList.size());
        sendStaffList.clear();
        LogUtils.showLog("setSendToList11=" + sendStaffList.size() + "....staffBeanList=" + staffBeanList.size());
        sendStaffList.addAll(staffBeanList);
        LogUtils.showLog("setSendToList22=" + sendStaffList.size() + "....staffBeanList=" + staffBeanList.size());
//        if (sendAdapter == null) {
        sendAdapter = new AddSendListAdapter(sendStaffList, getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4, RecyclerView.VERTICAL, false);
        viewBinding.rvSent.setLayoutManager(layoutManager);
        viewBinding.rvSent.addItemDecoration(new AddSendListItemDecorator());
//            baseSendAdapter = new BaseRecyclerViewAdapter(sendAdapter);
        viewBinding.rvSent.setAdapter(sendAdapter);

//        } else {
//            sendAdapter.setDataList(sendStaffList, true);
//        }
//        baseSendAdapter.refreshData();

    }

    /**
     * 显示抄送人数据
     */
    public void setCopyToList(List<StaffBean> staffBeanList) {
        copyStaffList.clear();
        copyStaffList.addAll(staffBeanList);
//        if (copyAdapter == null) {
        copyAdapter = new AddCopyListAdapter(copyStaffList, getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4, RecyclerView.VERTICAL, false);
        viewBinding.rvCopyTo.setLayoutManager(layoutManager);
        viewBinding.rvCopyTo.addItemDecoration(new AddCopyListItemDecorator());
//            baseSendAdapter = new BaseRecyclerViewAdapter(sendAdapter);
        viewBinding.rvCopyTo.setAdapter(copyAdapter);
//        } else {
//            copyAdapter.setDataList(copyStaffList, true);
//        }
//        baseCopyAdapter.refreshData();
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
        void onCommitInfo(ExecuteRequestBody data);

        void onSendClick(AddSendListAdapter sendListAdapter,AddCopyListAdapter addCopyListAdapter);

        void onCopyClick(AddCopyListAdapter addCopyListAdapter,AddSendListAdapter sendListAdapter);
    }

}