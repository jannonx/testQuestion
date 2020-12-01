package com.guyuan.dear.focus.contract.adapter;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.guyuan.dear.R;

/**
 * @author : 唐力
 * @description :
 * @since: 2020/12/1 13:57
 * @company : 固远（深圳）信息技术有限公司
 **/

public class ContractBindingAdapter {

    private static final int NORMAL = 0;      //正常执行
    private static final int EXCEPTION = 1;   //异常执行
    private static final int QUALIFIED = 2;     //验收合格

    @BindingAdapter("contractStatus")
    public static void setContractStatus(TextView tv, int status) {
        switch (status) {
            case NORMAL:
                tv.setText("正常执行");
                tv.setTextAppearance(R.style.TextTagBlue);
                break;

            case EXCEPTION:
                tv.setText("执行异常");
                tv.setTextAppearance(R.style.TextTagOrange);
                break;

            case QUALIFIED:
                tv.setText("验收合格");
                tv.setTextAppearance(R.style.TextTagGreen);
                break;
        }

    }
}