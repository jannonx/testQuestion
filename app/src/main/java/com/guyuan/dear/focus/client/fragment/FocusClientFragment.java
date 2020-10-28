package com.guyuan.dear.focus.client.fragment;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFoucsClientBinding;
import com.guyuan.dear.databinding.FragmentUserInfoBinding;
import com.guyuan.dear.focus.client.data.FocusClientViewModel;

/**
 * @description: 我的关注-客户
 * @author: Jannonx
 * @since: 2020/10/26 16:11
 * @company: 固远（深圳）信息技术有限公司
 */
public class FocusClientFragment extends BaseDataBindingFragment<FragmentFoucsClientBinding, FocusClientViewModel> {

    public static final String TAG = "FocusClientFragment";

    public static FocusClientFragment newInstance() {
        Bundle args = new Bundle();
        FocusClientFragment fragment = new FocusClientFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_foucs_client;
    }

    @Override
    protected void initialization() {

    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
