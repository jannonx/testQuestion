package com.guyuan.dear.focus.produce.fragment;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFocusProducePlanBinding;
import com.guyuan.dear.focus.client.bean.CommentsBean;
import com.guyuan.dear.focus.client.data.FocusClientViewModel;
import com.guyuan.dear.focus.produce.bean.FocusProduceBean;
import com.guyuan.dear.focus.produce.data.FocusProduceViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: 许建宁
 * @since: 2020/10/27 14:31
 * @company: 固远（深圳）信息技术有限公司
 */
public class FollowProducePlanFragment extends BaseDataBindingFragment<FragmentFocusProducePlanBinding, FocusProduceViewModel> {

    public static final String TAG = "FollowStatusFragment";


    public static FollowProducePlanFragment newInstance() {
        Bundle bundle = new Bundle();
        FollowProducePlanFragment fragment = new FollowProducePlanFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_focus_produce_plan;
    }

    @Override
    protected void initialization() {


    }

    public void setProduceData(FocusProduceBean data) {
        binding.tvDutyPerson.setText(data.getPrincipalPerson());
        binding.tvProductNumber.setText(String.valueOf(data.getNum()));
        binding.tvProductUnit.setText(data.getUnit());

        binding.tvDateProvidedTime.setText(data.getActualStartTime());
        binding.tvDrawingsIssuedTime.setText(data.getDrawingIssuedTime());
        binding.tvMaterialListIssuedTime.setText(data.getDrawingIssuedTime());
        binding.tvMaterialToFactoryTime.setText(data.getMaterialReceiveTime());
        binding.tvOutBuyTime.setText(data.getPurchaseReceiveTime());
        binding.tvPlanPutInTime.setText(data.getPlanStockTime());
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
