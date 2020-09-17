package com.guyuan.dear.focus.purchase.fragment;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFocusAssessOverviewBinding;
import com.guyuan.dear.databinding.FragmentProviderDetailBinding;
import com.guyuan.dear.focus.quality.fragment.QualityDetailFragment;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class ProviderDetailFragment extends BaseDataBindingFragment<FragmentProviderDetailBinding> {

    public static final String TAG = ProviderDetailFragment.class.getSimpleName();

    public static ProviderDetailFragment newInstance() {
        Bundle args = new Bundle();
        ProviderDetailFragment fragment = new ProviderDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_provider_detail;
    }

    @Override
    protected void initialization() {

    }
}
