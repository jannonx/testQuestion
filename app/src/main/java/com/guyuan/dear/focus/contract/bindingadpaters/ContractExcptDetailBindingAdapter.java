package com.guyuan.dear.focus.contract.bindingadpaters;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guyuan.dear.R;
import com.guyuan.dear.focus.contract.adapter.ContractCommentAdapter;
import com.guyuan.dear.focus.contract.bean.DetailContractApplyBean;
import com.guyuan.dear.focus.contract.bean.ContractApproveLog;
import com.guyuan.dear.focus.contract.bean.contractPrgLog.Vote;

import java.util.Collections;
import java.util.List;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/10/28 11:49
 * @company: 固远（深圳）信息技术有限公司
 **/
public class ContractExcptDetailBindingAdapter {
    @BindingAdapter(value = {"setContractExcptDetailCommentList","setContractExcptDetailCreateDate","setContractExcptDetailApplier"},requireAll = true)
    public static void setContractExcptDetailCommentList(RecyclerView view, List<ContractApproveLog> list, long contractDate, String applier){
        if(list==null){
            return;
        }
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext(),RecyclerView.VERTICAL,false);
        view.setLayoutManager(manager);
        Collections.reverse(list);
        ContractCommentAdapter adapter = new ContractCommentAdapter(list,contractDate,applier,view.getContext());
        view.setAdapter(adapter);
    }

    @BindingAdapter("setContractExcptDetailApproval")
    public static void setContractExcptDetailApproval(AppCompatTextView view,int result){
        if(result== Vote.VOTE_RESULT_PASS){
            view.setText("通过");
        }else if(result==Vote.VOTE_RESULT_REJECT) {
            view.setText("拒绝");
        }else if(result==Vote.VOTE_RESULT_DEFAULT){
            view.setText("未审批");
        }else if(result==Vote.VOTE_RESULT_PENDING){
            view.setText("待审批");
        }else {
            view.setText("");
        }
    }

    @BindingAdapter("setContractExcptDetailApproveResultColor")
    public static void setContractExcptDetailApproveResultColor(AppCompatImageView view,int result){
        if(result==Vote.VOTE_RESULT_PASS){
            view.setImageResource(R.drawable.ic_svg_round_filled_green_2fc25b_24dp);
        }else if(result==Vote.VOTE_RESULT_PENDING){
            view.setImageResource(R.drawable.ic_svg_round_filled_blue_1890ff_24dp);
        }else if(result==Vote.VOTE_RESULT_DEFAULT){
            view.setImageResource(R.drawable.ic_svg_round_filled_blue_1890ff_24dp);
        }else {
            view.setImageResource(R.drawable.ic_svg_round_filled_red_f04864_24dp);
        }
    }

    @BindingAdapter("showContractApplyDetailTypeMark")
    public static void showContractApplyDetailTypeMark(AppCompatImageView view,int type){
        if(type == DetailContractApplyBean.APPLY_TYPE_PAUSE){
            view.setImageResource(R.mipmap.ic_webp_contract_exception_pause);
        }else if(type == DetailContractApplyBean.APPLY_TYPE_RESTART){
            view.setImageResource(R.mipmap.webp_restarted_contract_state_activated);
        }
    }

}
