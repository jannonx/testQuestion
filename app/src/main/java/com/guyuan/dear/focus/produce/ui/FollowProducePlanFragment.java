package com.guyuan.dear.focus.produce.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFocusProducePlanBinding;
import com.guyuan.dear.databinding.FragmentFollowStatusBinding;
import com.guyuan.dear.focus.client.adapter.FollowStatusExAdapter;
import com.guyuan.dear.focus.client.bean.CommentsBean;
import com.guyuan.dear.focus.client.data.FocusClientViewModel;
import com.guyuan.dear.focus.produce.data.FocusProduceViewModel;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.utils.LogUtils;

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
    private FocusClientViewModel viewModel;
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
