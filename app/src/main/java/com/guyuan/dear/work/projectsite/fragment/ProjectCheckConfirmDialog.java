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

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.DialogWorkProjectCheckBinding;
import com.guyuan.dear.focus.projectsite.bean.CheckGoodsBean;
import com.guyuan.dear.focus.projectsite.bean.CheckGoodsSatisfyType;
import com.guyuan.dear.focus.projectsite.bean.InstallDebugSatisfyType;
import com.guyuan.dear.focus.projectsite.bean.SiteExploreBean;
import com.guyuan.dear.utils.LogUtils;
import com.guyuan.dear.utils.ToastUtils;
import com.guyuan.dear.work.projectsite.bean.PostCheckInfo;
import com.guyuan.dear.work.projectsite.bean.PostInstallationDebugInfo;

import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.databinding.DataBindingUtil;

import java.util.ArrayList;
import java.util.List;

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
    private OnDialogClickListener clickListener;
    private DialogWorkProjectCheckBinding viewBinding;
    private SiteExploreBean siteExploreBean;
    private int isCheckOK = 0;


    public ProjectCheckConfirmDialog(@NonNull Context context, @StyleRes int theme, Activity activity) {
        super(context, R.style.BottomSheetDialog);
        this.activity = activity;
    }

    public ProjectCheckConfirmDialog(Activity activity, SiteExploreBean siteExploreBean, OnDialogClickListener clickListener) {
        this(activity, 0, activity);
        this.clickListener = clickListener;
        this.siteExploreBean = siteExploreBean;
        if (siteExploreBean.getCheckTransportProjectListVO()!=null){
            for (CheckGoodsBean checkGoodsBean : siteExploreBean.getCheckTransportProjectListVO()) {
                LogUtils.showLog("checkGoodsBean=" + checkGoodsBean.getIsException());
            }
        }

    }


    public static void show(Activity activity, SiteExploreBean siteExploreBean, OnDialogClickListener clickListener) {
        ProjectCheckConfirmDialog customerDialog = new ProjectCheckConfirmDialog(activity, siteExploreBean, clickListener);
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
//        viewBinding.tvOk.setClickable(false);
//        viewBinding.tvOk.setEnabled(false);
        viewBinding.tvOk.setOnClickListener(this);
        viewBinding.tvCancel.setOnClickListener(this);
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
            case R.id.iv_pick_image:
                break;
            default:

        }
    }

    private void commitApplyInfo() {
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
                break;

            default:
        }


    }

    private void commitInstallDebugInfo() {
        PostInstallationDebugInfo body = new PostInstallationDebugInfo();
        if (TextUtils.isEmpty(viewBinding.etSearch.getText().toString())) {
            ToastUtils.showLong(getContext(), "请填内容");
            return;
        }
        body.setId(siteExploreBean.getId());
        body.setRemark(viewBinding.etSearch.getText().toString());
        List<String> imageArr = new ArrayList<>();
        imageArr.add("https://demo-1302848661.cos.ap-shenzhen-fsi.myqcloud.com/dear-test/web/.png160612221432475");
        body.setImgUrl(imageArr);
        if (clickListener != null) {
            clickListener.onCommitInstallationDebugInfo(body);
            dismiss();
        }

    }

    private void commitCheckGoodsInfo() {
        PostCheckInfo body = new PostCheckInfo();
        if (TextUtils.isEmpty(viewBinding.etSearch.getText().toString())) {
            ToastUtils.showLong(getContext(), "请填内容");
            return;
        }


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
            bean.setStatus(checkGoodsBean.getIsException());
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
    }

    //就是返回页面高度
    private int getWindowHeight() {
        Resources res = activity.getResources();
        DisplayMetrics displayMetrics = res.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    public interface OnDialogClickListener {
        void onCommitCheckGoodsInfo(PostCheckInfo data);
        void onCommitInstallationDebugInfo(PostInstallationDebugInfo data);

    }

}