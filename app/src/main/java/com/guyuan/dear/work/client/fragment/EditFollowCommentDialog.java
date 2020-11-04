package com.guyuan.dear.work.client.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.guyuan.dear.R;
import com.guyuan.dear.databinding.DialogWorkClientFollowBinding;


import java.util.ArrayList;
import java.util.List;


/**
 * @description: 填写跟进内容弹框
 * @author: Jannonx
 * @since: 2020/11/4 14:11
 * @company: 固远（深圳）信息技术有限公司
 */
public class EditFollowCommentDialog extends Dialog {

    protected List<String> listData = new ArrayList<>();
    private Context context;//上下文
    private OnFollowClickListener listener;
    private DialogWorkClientFollowBinding viewBinding;

    public static void show(Context context, OnFollowClickListener listener) {
        EditFollowCommentDialog dialog = new EditFollowCommentDialog(context);
        dialog.setData(listener);
        dialog.show();
    }

    private EditFollowCommentDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //提前设置Dialog的一些样式
        Window dialogWindow = getWindow();
        dialogWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //不留边距
        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
        dialogWindow.getDecorView().setBackgroundColor(Color.TRANSPARENT);
        dialogWindow.setGravity(Gravity.BOTTOM);//设置dialog显示居中
//        setContentView(R.layout.dialog_work_client_follow);
//        ButterKnife.bind(this);
        viewBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_work_client_follow, null, false);
        setContentView(viewBinding.getRoot());//核心代码

        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
        setCanceledOnTouchOutside(true);//点击外部Dialog消失

        initData();
    }

    private void initData() {
        viewBinding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                viewBinding.tvSearchBtn.setClickable(!TextUtils.isEmpty(editable.toString()));
                viewBinding.tvSearchBtn.setEnabled(!TextUtils.isEmpty(editable.toString()));
            }
        });
        viewBinding.tvSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(viewBinding.etSearch.getText().toString());
                    dismiss();
                }
            }
        });

    }

    private void setData(OnFollowClickListener listener) {
        this.listener = listener;
    }

    public interface OnFollowClickListener {
        void onClick(String content);
    }


}
