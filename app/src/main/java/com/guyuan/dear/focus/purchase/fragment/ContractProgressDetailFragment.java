package com.guyuan.dear.focus.purchase.fragment;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentProgressDetailBinding;
import com.guyuan.dear.focus.purchase.data.FocusPurchaseViewModel;

/**
 * @description: 我的关注--采购--订单进度详情
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class ContractProgressDetailFragment extends BaseDataBindingFragment<FragmentProgressDetailBinding, FocusPurchaseViewModel> {

    public static final String TAG = ContractProgressDetailFragment.class.getSimpleName();

    public static ContractProgressDetailFragment newInstance() {
        Bundle args = new Bundle();
        ContractProgressDetailFragment fragment = new ContractProgressDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_progress_detail;
    }

    @Override
    protected void initialization() {

    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
