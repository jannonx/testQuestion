package com.guyuan.dear.work.client.fragment;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentBasicInfoBinding;
import com.guyuan.dear.focus.client.data.FocusClientViewModel;
import com.guyuan.dear.work.client.data.WorkClientViewModel;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/10/27 16:36
 * @company: 固远（深圳）信息技术有限公司
 */
public class BasicInfoFragment extends BaseDataBindingFragment<FragmentBasicInfoBinding, WorkClientViewModel> {

    public static final String TAG = "BasicInfoFragment";
    private FocusClientViewModel viewModel;

    public static BasicInfoFragment newInstance() {
        Bundle args = new Bundle();
        BasicInfoFragment fragment = new BasicInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_basic_info;
    }

    @Override
    protected void initialization() {

    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
