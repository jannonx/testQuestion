package com.guyuan.dear.work.purchase.fragment;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFocusAssessOverviewBinding;
import com.guyuan.dear.databinding.FragmentPurchaseDetailBinding;
import com.guyuan.dear.work.projectsite.fragment.CustomerAcceptanceFragment;

/**
 * @description: 我的工作--采购计划--详情
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class PurchaseDetailFragment extends BaseDataBindingFragment<FragmentPurchaseDetailBinding> {

    public static final String TAG = PurchaseDetailFragment.class.getSimpleName();

    public static PurchaseDetailFragment newInstance() {
        Bundle args = new Bundle();
        PurchaseDetailFragment fragment = new PurchaseDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_purchase_detail;
    }

    @Override
    protected void initialization() {

    }
}
