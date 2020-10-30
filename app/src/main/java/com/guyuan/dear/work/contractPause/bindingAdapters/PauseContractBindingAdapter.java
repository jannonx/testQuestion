package com.guyuan.dear.work.contractPause.bindingAdapters;

import android.graphics.Color;
import android.text.TextUtils;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.work.contractPause.beans.StaffBean;

import java.util.List;
import java.util.Locale;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/30 15:20
 * @company: 固远（深圳）信息技术有限公司
 **/
public class PauseContractBindingAdapter {
    @BindingAdapter("setPauseContractSendList")
    public static void setPauseContractSendList(RecyclerView view, List<StaffBean> staffs) {
        if (staffs == null) {
            return;
        }


    }

    @BindingAdapter("setPauseContractCopyList")
    public static void setPauseContractCopyList(RecyclerView view, List<StaffBean> staffs) {
        setPauseContractSendList(view, staffs);
    }

    private static final int MAX_LEN = 240;

    @BindingAdapter("showInputCount")
    public static void showInputCount(AppCompatTextView view, String content) {
        if (TextUtils.isEmpty(content)) {
            view.setText("0/240");
            return;
        }
        String format = String.format(Locale.CHINA, "%d/%d", content.length(), MAX_LEN);
        view.setText(format);
        if (content.length() >= MAX_LEN) {
            view.setTextColor(Color.RED);
        } else {
            view.setTextColor(Color.parseColor("#999999"));
        }
    }
}
