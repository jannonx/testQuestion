package com.guyuan.dear.focus.contract.bindingadpaters;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

import com.guyuan.dear.R;
import com.guyuan.dear.focus.contract.bean.ContractPrgKnot;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/25 15:47
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractPrgKnotBindingAdapter {
    @BindingAdapter("updateBgByKnotStatus")
    public static void updateBgByKnotStatus(AppCompatImageView view, int status) {
        switch (status) {
            case ContractPrgKnot.KNOT_STATUS_FINISHED:
                view.setImageResource(R.drawable.ic_svg_round_filled_green_2fc25b_24dp);
                break;
            case ContractPrgKnot.KNOT_STATUS_PROCESSING:
                view.setImageResource(R.drawable.ic_svg_round_filled_blue_1890ff_24dp);
                break;
            case ContractPrgKnot.KNOT_STATUS_DEFAULT:
                view.setImageResource(R.drawable.ic_svg_round_filled_grey_cccccc_24dp);
            default:
                break;
        }
    }
}
