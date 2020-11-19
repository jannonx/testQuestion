package com.guyuan.dear.work.contractPause.views.myAppliedList;

import android.os.Bundle;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentMyPauseApplyListBinding;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.contractPause.adapters.MyPauseApplyListAdapter;
import com.guyuan.dear.work.contractPause.beans.MyPauseApplyBean;
import com.guyuan.dear.work.contractPause.views.applyDetail.MyPauseApplyDetailActivity;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 12:00
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MyPauseApplyListFragment extends BaseMvvmFragment<FragmentMyPauseApplyListBinding, MyPauseApplyListViewModel> {

    public static MyPauseApplyListFragment getInstance(String uid){
        Bundle bundle = new Bundle();
        bundle.putString(ConstantValue.KEY_USER_ID,uid);
        MyPauseApplyListFragment fragment = new MyPauseApplyListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_my_pause_apply_list_vm;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        String uid =bundle.getString(ConstantValue.KEY_USER_ID);
        getViewModel().getListFromNet(uid);

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        getViewModel().setOnClickListItem(new MyPauseApplyListAdapter.MyPauseListItemClickListener() {
            @Override
            public void onClickMyPauseApply(MyPauseApplyBean item, int pos) {
                MyPauseApplyDetailActivity.start(getContext(),"申请详情",item);
            }
        });

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_my_pause_apply_list;
    }
}
