package com.guyuan.dear.focus.contract.bindingadpaters;

import android.graphics.Color;
import android.text.TextUtils;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;

import com.guyuan.dear.R;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2021/1/4 17:18
 * @company: 固远（深圳）信息技术有限公司
 **/
public class AllContractListBindingAdapter {
    @BindingAdapter("setContractAbnormalDesc")
    public static void setContractAbnormalDesc(AppCompatTextView view ,String abnormalDesc){
        if(!TextUtils.isEmpty(abnormalDesc)){
            view.setText(abnormalDesc);
            view.setTextColor(Color.parseColor("#FF6010"));
            view.setBackgroundResource(R.drawable.shape_stroke_orange_ff6010_solid_transparent);
        }else {
            view.setText("查看合同执行状态");
            view.setTextColor(Color.parseColor("#1677FF"));
            view.setBackgroundResource(R.drawable.shape_stroke_blue_1677ff_solid_transparent);
        }
    }
}
