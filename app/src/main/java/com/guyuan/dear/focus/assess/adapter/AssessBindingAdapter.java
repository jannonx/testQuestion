package com.guyuan.dear.focus.assess.adapter;

import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;

import com.google.android.material.tabs.TabLayout;
import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.TabLayoutHelper;
import com.guyuan.dear.focus.assess.data.bean.AssessDetailBean;

import java.util.List;

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
                tv.setTextAppearance(R.style.TextAssess);
                tv.setText("保存草稿");
                break;

            case PREPARED:
                tv.setTextAppearance(R.style.TextAssess);
                tv.setText("待评审");
                break;

            case ASSESSING:
                tv.setTextAppearance(R.style.TextAssess);
                tv.setText("评审中");
                break;

            case PASSED:
                tv.setTextAppearance(R.style.TextPass);
                tv.setText("通过");
                break;

            case NOT_PASSED:
                tv.setTextAppearance(R.style.TextNotPass);
                tv.setText("不通过");
                break;
        }
    }

    @BindingAdapter("assessStatus")
    public static void setAssessStatus(ImageView iv,String result){
        if(result.contains("通过")){
             iv.setImageResource(R.mipmap.right);
        }else if(result.contains("不通过")){
            iv.setImageResource(R.mipmap.wrong);
        }
    }
}