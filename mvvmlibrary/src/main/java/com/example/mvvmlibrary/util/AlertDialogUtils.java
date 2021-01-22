package com.example.mvvmlibrary.util;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.mvvmlibrary.R;

import java.lang.ref.SoftReference;


/**
 * @description:
 * @author: 许建宁
 * @since: 2020/11/24 23:56
 */
public class AlertDialogUtils {

    public static AlertDialog showLoading(Context context, String title, String msg) {
        SoftReference<Context> softReference = new SoftReference<Context>(context);
        Context softContext = softReference.get();
        View view = LayoutInflater.from(softContext).inflate(R.layout.dialog_loading, null);
        AppCompatTextView tvTitle = view.findViewById(R.id.dialog_loading_tv_title);
        AppCompatTextView tvMsg = view.findViewById(R.id.dialog_loading_tv_msg);
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(title);
        }
        if (!TextUtils.isEmpty(msg)) {
            tvMsg.setVisibility(View.VISIBLE);
            tvMsg.setText(msg);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(softContext);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setWindowAnimations(R.style.loading_dialog_window_animation);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }


}
