package com.guyuan.dear.work.customerfollow.fragment;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentCustomerDetailBinding;
import com.guyuan.dear.databinding.FragmentFocusAssessOverviewBinding;
import com.guyuan.dear.work.purchase.fragment.PurchaseDetailFragment;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class CustomerDetailFragment extends BaseDataBindingFragment<FragmentCustomerDetailBinding> {

    public static final String TAG = CustomerDetailFragment.class.getSimpleName();

    public static CustomerDetailFragment newInstance() {
        Bundle args = new Bundle();
        CustomerDetailFragment fragment = new CustomerDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_customer_detail;
    }

    @Override
    protected void initialization() {

    }
}
