package com.guyuan.dear.focus.contract.bindingadpaters;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.R;
import com.guyuan.dear.customizeview.layoutManager.FullyLinearLayoutManager;
import com.guyuan.dear.focus.contract.adapter.ContractCommentAdapter;
import com.guyuan.dear.focus.contract.bean.ContractExcptComment;
import com.guyuan.dear.focus.contract.bean.contractPrgLog.Vote;

import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/28 11:49
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractExcptDetailBindingAdapter {
    @BindingAdapter(value = {"setContractExcptDetailCommentList","setContractExcptDetailCreateDate","setContractExcptDetailApplier"},requireAll = true)
    public static void setContractExcptDetailCommentList(
            RecyclerView view, List<ContractExcptComment> list, long contractDate,String applier){
        if(list==null){
            return;
        }

        LinearLayoutManager manager = new LinearLayoutManager(view.getContext(),RecyclerView.VERTICAL,false);
        view.setLayoutManager(manager);
        ContractCommentAdapter adapter = new ContractCommentAdapter(list,contractDate,applier,view.getContext());
        view.setAdapter(adapter);
    }

    @BindingAdapter("setContractExcptDetailApproval")
    public static void setContractExcptDetailApproval(AppCompatTextView view,int result){
        if(result== Vote.VOTE_RESULT_PASS){
            view.setText("通过");
        }else {
            view.setText("不通过");
        }
    }

    @BindingAdapter("setContractExcptDetailApproveResultColor")
    public static void setContractExcptDetailApproveResultColor(AppCompatImageView view,int result){
        if(result==Vote.VOTE_RESULT_PASS){
            view.setImageResource(R.drawable.ic_svg_round_filled_green_2fc25b_24dp);
        }else {
            view.setImageResource(R.drawable.ic_svg_round_filled_red_f04864_24dp);
        }

    }

}
