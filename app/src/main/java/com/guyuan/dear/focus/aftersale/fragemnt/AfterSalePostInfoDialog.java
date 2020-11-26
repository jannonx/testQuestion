package com.guyuan.dear.focus.aftersale.fragemnt;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.DialogAfterSalePostInfoBinding;
import com.guyuan.dear.focus.aftersale.bean.AfterSaleBean;
import com.guyuan.dear.focus.aftersale.bean.SaleSectionType;
import com.guyuan.dear.focus.projectsite.adapter.ContentImageViewAdapter;
import com.guyuan.dear.work.projectsite.bean.PostCheckInfo;

import java.util.ArrayList;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;

/**
 * @description: 售后服务--提交信息弹框
 * @author: 许建宁
 * @since: 2020/11/22 11:37
 * @company: 固远（深圳）信息技术有限公司
 */
public class AfterSalePostInfoDialog extends BottomSheetDialog implements View.OnClickListener {

    private Activity activity;
    private OnDialogClickListener clickListener;
    private DialogAfterSalePostInfoBinding viewBinding;
    private AfterSaleBean afterSaleBean;
    protected ArrayList<String> imageDataList = new ArrayList<>();
    private BaseRecyclerViewAdapter imageAdapter;

    public AfterSalePostInfoDialog(@NonNull Context context, @StyleRes int theme, Activity activity) {
        super(context, R.style.BottomSheetDialog);
        this.activity = activity;
    }

    public AfterSalePostInfoDialog(Activity activity, AfterSaleBean afterSaleBean, OnDialogClickListener clickListener) {
        this(activity, 0, activity);
        this.clickListener = clickListener;
        this.afterSaleBean = afterSaleBean;

    }


    public static void show(Activity activity, AfterSaleBean afterSaleBean, OnDialogClickListener clickListener) {
        AfterSalePostInfoDialog customerDialog = new AfterSalePostInfoDialog(activity, afterSaleBean, clickListener);
        customerDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.dialog_after_sale_post_info, null, false);
        setContentView(viewBinding.getRoot());//核心代码


        BottomSheetBehavior mDialogBehavior = BottomSheetBehavior.from((View) viewBinding.getRoot().getParent());
        mDialogBehavior.setPeekHeight(getWindowHeight());

        initView();
        initListener();
    }

    private void initView() {
        ContentImageViewAdapter imageViewAdapter = new ContentImageViewAdapter(getContext(),
                imageDataList, R.layout.item_explorate_image);
        imageAdapter = new BaseRecyclerViewAdapter(imageViewAdapter);

        viewBinding.imageRecycleView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        viewBinding.imageRecycleView.setAdapter(imageAdapter);
        viewBinding.imageRecycleView.setPullRefreshEnabled(false);
        viewBinding.imageRecycleView.setLoadMoreEnabled(false);
        viewBinding.tvOk.setOnClickListener(this);
        viewBinding.tvCancel.setOnClickListener(this);

        viewBinding.tvTitle.setText(SaleSectionType.TYPE_SECTION_CHECK == afterSaleBean.getSectionType()
                ? "反馈问题" : "备注");


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_ok:

                break;
            case R.id.iv_pick_image:
                break;
            default:

        }
    }


    public void setPhotoList(ArrayList<String> photoList) {
        imageDataList.clear();
        imageDataList.addAll(photoList);
        imageAdapter.refreshData();
    }


    private void commitInstallDebugInfo() {
//        PostInstallationDebugInfo body = new PostInstallationDebugInfo();
//        body.setId(siteExploreBean.getId());
//        body.setRemark(viewBinding.etSearch.getText().toString());
//        body.setImgUrl(imageDataList);
//        if (clickListener != null) {
//            clickListener.onCommitInstallationDebugInfo(body);
//            dismiss();
//        }

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
//                viewBinding.tvOk.setClickable(!TextUtils.isEmpty(editable.toString()));
//                viewBinding.tvOk.setEnabled(!TextUtils.isEmpty(editable.toString()));
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

        viewBinding.ivPickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onPickImageClick();
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

        void onPickImageClick();

        void onCommitCheckGoodsInfo(PostCheckInfo data);


    }

}