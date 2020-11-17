package com.guyuan.dear.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.guyuan.dear.R;
import com.guyuan.dear.databinding.DialogSimpleComfirmBinding;


/**
 * @description: 简单确认弹框
 * @author: Jannonx
 * @since: 2020/11/12 13:43
 * @company: 固远（深圳）信息技术有限公司
 */
public class SimpleConfirmViewDialog extends Dialog {

    private Context context;//上下文
    private String title, content;
    private boolean isTitleShow, isContentShow;
    private OnClickListener listener;
    private DialogSimpleComfirmBinding viewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initListener();
    }

    /**
     * 设置数据
     */
    private void initData() {
        viewBinding.tvTitle.setText(title);
        viewBinding.tvContent.setText(title);
        viewBinding.tvTitle.setVisibility(isTitleShow ? View.VISIBLE : View.GONE);
        viewBinding.tvContent.setVisibility(isContentShow ? View.VISIBLE : View.GONE);
    }

    /**
     * 设置监听
     */
    private void initListener() {
        viewBinding.tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onConfirm();
                    dismiss();
                }
            }
        });

        viewBinding.tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    /**
     * 设置视图属性
     */
    private void initView() {
        //提前设置Dialog的一些样式
        Window dialogWindow = getWindow();
        //不留边距
        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
        dialogWindow.getDecorView().setBackgroundColor(Color.TRANSPARENT);
        dialogWindow.setGravity(Gravity.CENTER);//设置dialog显示居中
        dialogWindow.setWindowAnimations(R.style.dialogWindowAnim);//设置动画效果

        viewBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_simple_comfirm, null, false);
        setContentView(viewBinding.getRoot());//核心代码

        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(params);
        setCanceledOnTouchOutside(true);//点击外部Dialog消失
    }

    private SimpleConfirmViewDialog(@NonNull Context context, String title, boolean isTitleShow, String content, boolean isContentShow, OnClickListener listener) {
        super(context);
        this.context = context;
        this.title = title;
        this.isTitleShow = isTitleShow;
        this.content = content;
        this.isContentShow = isContentShow;
        this.listener = listener;
    }

    public static void showTitle(@NonNull Context context, String title, OnClickListener listener) {
        SimpleConfirmViewDialog dialog = new SimpleConfirmViewDialog(context, title, true, null, false, listener);
        dialog.show();
    }

    public static void showContent(@NonNull Context context, String content, OnClickListener listener) {
        SimpleConfirmViewDialog dialog = new SimpleConfirmViewDialog(context, null, false, content, true, listener);
        dialog.show();
    }

    public static void show(@NonNull Context context, String title, String content, OnClickListener listener) {
        SimpleConfirmViewDialog dialog = new SimpleConfirmViewDialog(context, title, true, content, true, listener);
        dialog.show();
    }

    public interface OnClickListener {
        void onConfirm();
    }


}
