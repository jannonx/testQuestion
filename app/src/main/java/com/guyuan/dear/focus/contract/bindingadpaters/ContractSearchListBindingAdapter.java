package com.guyuan.dear.focus.contract.bindingadpaters;

import android.graphics.Color;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;

import com.guyuan.dear.R;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/12/3 12:09
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractSearchListBindingAdapter {

    @BindingAdapter("setContractSearchListRightTag")
    public void setContractSearchListRightTag(AppCompatTextView view, int contractStatus) {
        //0 正常执行 1 质保金异常 2 验收合格 3 暂停
        String tag = "";
        if (contractStatus == 0) {
            tag = "正常执行";
            view.setTextColor(Color.parseColor("#1677FF"));
            view.setBackgroundResource(R.drawable.bg_light_blue_e7f1ff_round_corner_2dp);
        } else if (contractStatus == 1) {
            tag = "质保金异常";
            view.setTextColor(Color.parseColor("#FF6010"));
            view.setBackgroundResource(R.drawable.bg_pink_ffece3_round_corner_2dp);
        } else if (contractStatus == 2) {
            tag = "验收合格";
            view.setTextColor(Color.parseColor("#00B578"));
            view.setBackgroundResource(R.drawable.bg_green_d4fff1_round_corner_2dp);
        } else if (contractStatus == 3) {
            tag = "合同暂停";
            view.setTextColor(Color.parseColor("#FF6010"));
            view.setBackgroundResource(R.drawable.bg_pink_ffece3_round_corner_2dp);
        }
        view.setText(tag);

    }
}
