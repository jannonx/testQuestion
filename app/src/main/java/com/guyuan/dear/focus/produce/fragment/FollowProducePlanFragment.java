package com.guyuan.dear.focus.produce.fragment;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFocusProducePlanBinding;
import com.guyuan.dear.focus.client.bean.CommentsBean;
import com.guyuan.dear.focus.client.data.FocusClientViewModel;
import com.guyuan.dear.focus.produce.data.FocusProduceViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/10/27 14:31
 * @company: 固远（深圳）信息技术有限公司
 */
public class FollowProducePlanFragment extends BaseDataBindingFragment<FragmentFocusProducePlanBinding, FocusProduceViewModel> {

    public static final String TAG = "FollowStatusFragment";
    private FocusProduceViewModel viewModel;
    private List<CommentsBean> dataList = new ArrayList<>();
    private boolean isFocus = false;


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

    @Override
    protected int getVariableId() {
        return 0;
    }
}
