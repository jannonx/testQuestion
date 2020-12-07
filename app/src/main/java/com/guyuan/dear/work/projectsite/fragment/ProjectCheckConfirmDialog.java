package com.guyuan.dear.work.projectsite.fragment;

import android.annotation.SuppressLint;
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
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.DialogWorkProjectCheckBinding;
import com.guyuan.dear.focus.projectsite.adapter.ContentImageViewAdapter;
import com.guyuan.dear.focus.projectsite.bean.CheckGoodsBean;
import com.guyuan.dear.focus.projectsite.bean.CheckGoodsSatisfyType;
import com.guyuan.dear.focus.projectsite.bean.ProjectReportType;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.utils.ToastUtils;
import com.guyuan.dear.work.projectsite.bean.OnConfirmDialogListener;
import com.guyuan.dear.work.projectsite.bean.PostCheckInfo;
import com.guyuan.dear.work.projectsite.bean.PostCustomerAcceptanceInfo;
import com.guyuan.dear.work.projectsite.bean.PostInstallationDebugInfo;

import java.util.ArrayList;
import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/11/22 11:37
 * @company: 固远（深圳）信息技术有限公司
 */
public class ProjectCheckConfirmDialog extends BottomSheetDialog implements View.OnClickListener {

    // 到货或清点操作,1：到货操作标识，2：清点操作标识
    private static final int TYPE_GOOD_ARRIVE = 1;
    private static final int TYPE_GOOD_CHECK = 2;
    // 是否异常，0正常，1异常
    private static final int TYPE_CHECK_OK = 0;
    private static final int TYPE_CHECK_EXCEPTION = 1;

    private Activity activity;
    private OnConfirmDialogListener clickListener;
    private DialogWorkProjectCheckBinding viewBinding;
    private SiteExploreBean siteExploreBean;
    private int isCheckOK = 0;
    protected ArrayList<String> imageDataList = new ArrayList<>();
    private BaseRecyclerViewAdapter imageAdapter;

    public ProjectCheckConfirmDialog(@NonNull Context context, @StyleRes int theme, Activity activity) {
        super(context, R.style.BottomSheetDialog);
        this.activity = activity;
    }

    public ProjectCheckConfirmDialog(Activity activity, SiteExploreBean siteExploreBean, OnConfirmDialogListener clickListener) {
        this(activity, 0, activity);
        this.clickListener = clickListener;
        this.siteExploreBean = siteExploreBean;

    }

    public static void show(Activity activity, SiteExploreBean siteExploreBean, OnConfirmDialogListener clickListener) {
        ProjectCheckConfirmDialog customerDialog = new ProjectCheckConfirmDialog(activity, siteExploreBean, clickListener);
        customerDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.dialog_work_project_check, null, false);
        setContentView(viewBinding.getRoot());//核心代码

        BottomSheetBehavior mDialogBehavior = BottomSheetBehavior.from((View) viewBinding.getRoot().getParent());
        mDialogBehavior.setPeekHeight(getWindowHeight());

        initView();
        initListener();
    }

    private void initView() {
        setRefreshTipText();
        ContentImageViewAdapter imageViewAdapter = new ContentImageViewAdapter(getContext(),
                imageDataList, R.layout.item_explorate_image, true);
        imageAdapter = new BaseRecyclerViewAdapter(imageViewAdapter);

        viewBinding.imageRecycleView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        viewBinding.imageRecycleView.setAdapter(imageAdapter);
        viewBinding.imageRecycleView.setPullRefreshEnabled(false);
        viewBinding.imageRecycleView.setLoadMoreEnabled(false);
        viewBinding.tvOk.setOnClickListener(this);
        viewBinding.tvCancel.setOnClickListener(this);
        viewBinding.clPickPic.setOnClickListener(this);
        switchRadioButton(viewBinding.rbRight, true);
        switchRadioButton(viewBinding.rbWrong, false);
        //默认正常
        viewBinding.rgCheck.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkId) {
                switch (checkId) {
                    case R.id.rb_right:
                        isCheckOK = TYPE_CHECK_OK;
                        switchRadioButton(viewBinding.rbRight, true);
                        switchRadioButton(viewBinding.rbWrong, false);
                        break;
                    case R.id.rb_wrong:
                        isCheckOK = TYPE_CHECK_EXCEPTION;
                        switchRadioButton(viewBinding.rbRight, false);
                        switchRadioButton(viewBinding.rbWrong, true);
                        break;
                    default:
                        break;
                }
            }
        });

        viewBinding.clStatus.setVisibility(ProjectReportType.TYPE_INSTALLATION_DEBUG == siteExploreBean.getProjectReportType() ||
                ProjectReportType.TYPE_CUSTOMER_ACCEPTANCE == siteExploreBean.getProjectReportType() ? View.GONE : View.VISIBLE);

        imageViewAdapter.setAdapterListener(new ContentImageViewAdapter.OnListAdapterListener() {
            @Override
            public void onDeleteCLick(int position) {
                if (clickListener != null) {
                    clickListener.onDeleteClick(position);
                }
                if (imageDataList.size() == 0) {
                    viewBinding.labelDocument.setText("拍照电子档");
                    viewBinding.tvTip.setText("点击此框上传资料拍照照片");
                }
            }
        });
    }

    /**
     * 切换radioButton状态
     *
     * @param button    组件
     * @param isChecked 选中
     */
    private void switchRadioButton(AppCompatRadioButton button, boolean isChecked) {
        //定义底部标签图片大小和位置
        @SuppressLint("UseCompatLoadingForDrawables")
        Drawable drawable = getContext().getResources().getDrawable(isChecked ?
                R.drawable.bg_work_project_rb_selector : R.drawable.bg_work_project_rb_unselector);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable.setBounds(0, 0, 50, 50);
        //设置图片在文字的哪个方向
        button.setCompoundDrawables(drawable, null, null, null);
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
            case R.id.cl_pick_pic:
                if (clickListener != null) {
                    clickListener.onPickImageClick();
                }
                break;
            default:

        }
    }

    /**
     * 根据类型提交信息
     */
    private void commitApplyInfo() {
        if (TextUtils.isEmpty(viewBinding.etSearch.getText().toString())) {
            ToastUtils.showLong(getContext(), "请填写内容");
            return;
        }
        if (imageDataList == null || imageDataList.size() == 0) {
            ToastUtils.showLong(getContext(), "请选择图片");
            return;
        }
        switch (siteExploreBean.getProjectReportType()) {
            ///现场勘查报告
            case TYPE_SITE_EXPLORATION:
                break;
            ///货物清点报告
            case TYPE_CHECK_GOODS:
                commitCheckGoodsInfo();
                break;
            ///安全排查报告
            case TYPE_CHECK_SAFE:
                break;
            ///安装调试报告
            case TYPE_INSTALLATION_DEBUG:
                commitInstallDebugInfo();
                break;
            ///客户验收报告
            case TYPE_CUSTOMER_ACCEPTANCE:
                commitCustomerAcceptanceInfo();
                break;

            default:
        }


    }

    /**
     * 设置回调图片
     *
     * @param photoList 图片资源
     */
    public void setPhotoList(ArrayList<String> photoList) {
        LogUtils.showLog("setPhotoList=" + photoList.size());
        imageDataList.clear();
        imageDataList.addAll(photoList);
        imageAdapter.refreshData();
        setRefreshTipText();
    }


    /**
     * 设置拍照或者浏览图片文本提示
     */
    private void setRefreshTipText() {
        viewBinding.labelDocument.setText(imageDataList.size() == 0 ? "拍照电子档" : "电子文件档");
        viewBinding.tvTip.setText(imageDataList.size() == 0 ? "点击此框上传资料拍照照片" : "点击图片，放大查看");
    }

    private void commitCustomerAcceptanceInfo() {
        PostCustomerAcceptanceInfo body = new PostCustomerAcceptanceInfo();

        body.setId(siteExploreBean.getId());
        body.setCheckRemark(viewBinding.etSearch.getText().toString());
        body.setCheckUrl(imageDataList);
        if (clickListener != null) {
            clickListener.onCommitCustomerAcceptanceInfo(body);
            dismiss();
        }
    }

    private void commitInstallDebugInfo() {
        PostInstallationDebugInfo body = new PostInstallationDebugInfo();
        body.setId(siteExploreBean.getId());
        body.setRemark(viewBinding.etSearch.getText().toString());
        body.setImgUrl(imageDataList);
        if (clickListener != null) {
            clickListener.onCommitInstallationDebugInfo(body);
            dismiss();
        }

    }

    private void commitCheckGoodsInfo() {
        PostCheckInfo body = new PostCheckInfo();
        body.setId(siteExploreBean.getId());
        body.setIsException(isCheckOK);
        body.setSign(siteExploreBean.getCheckGoodsSatisfyType() == CheckGoodsSatisfyType.TYPE_GOODS_TRANSPORTING
                ? TYPE_GOOD_ARRIVE : TYPE_GOOD_CHECK);
        body.setCheckRemark(viewBinding.etSearch.getText().toString());
        List<PostCheckInfo.CheckDetailParamsListBean> goodList = new ArrayList<>();
        for (CheckGoodsBean checkGoodsBean : siteExploreBean.getCheckTransportProjectListVO()) {
            LogUtils.showLog("checkGoodsBean=" + checkGoodsBean.getIsException());
            PostCheckInfo.CheckDetailParamsListBean bean = new PostCheckInfo.CheckDetailParamsListBean();
            bean.setId(checkGoodsBean.getId());
            bean.setStatus(checkGoodsBean.getStatus());
            goodList.add(bean);
        }
        body.setCheckDetailParamsList(goodList);

        if (clickListener != null) {
            clickListener.onCommitCheckGoodsInfo(body);
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
                viewBinding.tvNumber.setText((wordLimitNum - enteredWords) + "/240");
            }
        });


    }

    //就是返回页面高度
    private int getWindowHeight() {
        Resources res = activity.getResources();
        DisplayMetrics displayMetrics = res.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }


}