package com.guyuan.dear.focus.sales.bindingadpaters;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

import com.guyuan.dear.R;
import com.guyuan.dear.focus.sales.bean.contractPrgLog.Vote;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/16 16:58
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ItemSalesReviewVoteBindingAdapter {
    @BindingAdapter("setCheckIcon")
    public static void setCheckIcon(AppCompatImageView view, int result){
        if(result== Vote.VOTE_RESULT_PASS){
            view.setImageResource(R.drawable.ic_svg_checked);
        }else {
            view.setImageResource(R.drawable.ic_svg_cross_red_d04040_24dp);
        }
    }
}
