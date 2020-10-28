package com.guyuan.dear.mine.fragment;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseDataBindingFragment;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentFeedBackBinding;
import com.guyuan.dear.mine.data.MineViewModel;

/**
 * @description: 我的--意见反馈
 * @author: Jannonx
 * @since: 2020/9/17 11:42
 * @company: 固远（深圳）信息技术有限公司
 */
public class FeedBackFragment extends BaseDataBindingFragment<FragmentFeedBackBinding, MineViewModel> {

    public static final String TAG = "FocusAssessOverviewFrag";

    public static FeedBackFragment newInstance() {
        Bundle args = new Bundle();
        FeedBackFragment fragment = new FeedBackFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_feed_back;
    }

    @Override
    protected void initialization() {

    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}
