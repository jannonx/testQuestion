package com.guyuan.dear.focus.produce.ui;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFocusProduceOverviewBinding;

/**
 * @author : tl
 * @description :
 * @since: 2020/10/10 11:17
 * @company : 固远（深圳）信息技术有限公司
 **/
public class FocusProduceOverviewFragment extends BaseDataBindingFragment<FragmentFocusProduceOverviewBinding> {

    public static final String TAG = "FocusProduceOverviewFragment";

    public static FocusProduceOverviewFragment newInstance() {
        
        Bundle args = new Bundle();
        
        FocusProduceOverviewFragment fragment = new FocusProduceOverviewFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    protected int getLayoutID() {
        return R.layout.fragment_focus_produce_overview;
    }

    @Override
    protected void initialization() {

    }
}
