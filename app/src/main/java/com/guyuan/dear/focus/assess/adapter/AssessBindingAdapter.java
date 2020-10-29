package com.guyuan.dear.focus.assess.adapter;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.guyuan.dear.R;

/**
 * @author : tl
 * @description :
 * @since: 2020/10/29 15:39
 * @company : 固远（深圳）信息技术有限公司
 **/

public class AssessBindingAdapter {
    //评审状态，0:保存草稿、10:待评审、20:评审中、30:评审通过、40:评审不通过)
    private static final int SAVED = 0;
    private static final int PREPARED = 10;
    private static final int ASSESSING = 20;
    private static final int PASSED = 30;
    private static final int NOT_PASSED = 40;

    @BindingAdapter("assessStatus")
    public static void setAssessStatus(TextView tv, int status) {
        switch (status) {
            case SAVED:

                break;

            case PREPARED:

                break;

            case ASSESSING:

                break;

            case PASSED:
                tv.setTextAppearance(R.style.TextPass);
                break;

            case NOT_PASSED:
                tv.setTextAppearance(R.style.TextNotPass);
                break;
        }
    }
}