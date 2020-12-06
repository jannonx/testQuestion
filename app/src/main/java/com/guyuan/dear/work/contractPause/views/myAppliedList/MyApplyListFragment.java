package com.guyuan.dear.work.contractPause.views.myAppliedList;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmlibrary.base.fragment.BaseMvvmFragment;
import com.guyuan.dear.BR;
import com.guyuan.dear.R;
import com.guyuan.dear.databinding.FragmentMyApplyListBinding;
import com.guyuan.dear.utils.ConstantValue;
import com.guyuan.dear.work.contractPause.adapters.MyApplyListAdapter;
import com.guyuan.dear.work.contractPause.beans.MyApplyBean;
import com.guyuan.dear.work.contractPause.views.applyDetail.MyApplyDetailActivity;
import com.guyuan.dear.work.contractPause.views.home.ContractPauseHomeActivity;
import com.guyuan.dear.work.contractPause.views.home.ContractPauseHomeViewModel;
import com.guyuan.dear.work.contractRestart.view.home.ContractRestartHomeActivity;
import com.guyuan.dear.work.contractRestart.view.home.ContractRestartHomeViewModel;

import java.util.List;

import tl.com.easy_recycleview_library.BaseRecyclerView;
import tl.com.easy_recycleview_library.BaseRecyclerViewAdapter;
import tl.com.easy_recycleview_library.interfaces.OnItemClickListener;
import tl.com.easy_recycleview_library.interfaces.OnLoadMoreListener;

/**
 * @author: 廖华凯
 * @description:
 * @since: 2020/11/2 12:00
 * @company: 固远（深圳）信息技术有限公司
 **/
public class MyApplyListFragment extends BaseMvvmFragment<FragmentMyApplyListBinding, MyApplyListViewModel> {

    /**
     * 合同暂停列表
     */
    public static final int TYPE_MY_PAUSE_APPLY_LIST = 0;
    /**
     * 合同重启列表
     */
    public static final int TYPE_MY_RESTART_APPLY_LIST=1;
    private int type;
    private MutableLiveData<Boolean> shouldShowNoData=new MutableLiveData<>(true);

    /**
     *
     * @param type {@link MyApplyListFragment#TYPE_MY_PAUSE_APPLY_LIST} {@link MyApplyListFragment#TYPE_MY_RESTART_APPLY_LIST}
     * @return
     */
    public static MyApplyListFragment getInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.KEY_APPLY_TYPE, type);
        MyApplyListFragment fragment = new MyApplyListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getViewModelBrId() {
        return BR.fragment_my_apply_list_vm;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        type = bundle.getInt(ConstantValue.KEY_APPLY_TYPE,TYPE_MY_PAUSE_APPLY_LIST);
        switch (type){
            case TYPE_MY_PAUSE_APPLY_LIST:
                initPauseList();
                break;
            case TYPE_MY_RESTART_APPLY_LIST:
                initRestartList();
                break;
            default:
                break;
        }

    }

    private void initRestartList() {
        BaseRecyclerView recyclerView = getViewDataBinding().fragmentMyApplyListRecyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        MyApplyListAdapter adapter = new MyApplyListAdapter(getViewModel().getRestartApplyList().getValue());
        BaseRecyclerViewAdapter wrapper = new BaseRecyclerViewAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(wrapper);
        recyclerView.setLoadMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                getViewModel().updateRestartApplyListFromNet();
            }
        });
        getViewModel().getRestartApplyList().observe(getViewLifecycleOwner(), new Observer<List<MyApplyBean>>() {
            @Override
            public void onChanged(List<MyApplyBean> myApplyBeans) {
                wrapper.notifyDataSetChanged();
                recyclerView.refreshComplete(0);
                if(myApplyBeans.isEmpty()){
                    shouldShowNoData.postValue(true);
                }else {
                    shouldShowNoData.postValue(false);
                }
            }
        });
        getViewModel().getIsLoadAllRestartList().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                recyclerView.setLoadMoreEnabled(!aBoolean);
            }
        });
        wrapper.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                MyApplyBean bean = getViewModel().getRestartApplyList().getValue().get(i);
                MyApplyDetailActivity.start(getContext(), "申请详情", bean.getExamineId());
            }
        });
        getViewModel().updateRestartApplyListFromNet();

        //监听view model，刷新列表
        FragmentActivity activity = getActivity();
        if(activity instanceof ContractRestartHomeActivity){
            ContractRestartHomeViewModel viewModel = new ViewModelProvider(activity).get(ContractRestartHomeViewModel.class);
            viewModel.refreshMyRestartApplyList.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    if(aBoolean){
                        //刷新列表
                        getViewModel().clearRestartApplyList();
                        getViewModel().updateRestartApplyListFromNet();

                    }
                }
            });
        }

    }

    private void initPauseList() {
        BaseRecyclerView recyclerView = getViewDataBinding().fragmentMyApplyListRecyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        MyApplyListAdapter adapter = new MyApplyListAdapter(getViewModel().getPauseApplyList().getValue());
        BaseRecyclerViewAdapter wrapper = new BaseRecyclerViewAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(wrapper);
        recyclerView.setLoadMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                getViewModel().updatePauseApplyListFromNet();
            }
        });
        getViewModel().getPauseApplyList().observe(getViewLifecycleOwner(), new Observer<List<MyApplyBean>>() {
            @Override
            public void onChanged(List<MyApplyBean> myApplyBeans) {
                wrapper.notifyDataSetChanged();
                recyclerView.refreshComplete(0);
                if(myApplyBeans.isEmpty()){
                    shouldShowNoData.postValue(true);
                }else {
                    shouldShowNoData.postValue(false);
                }
            }
        });
        getViewModel().getIsLoadAllPauseList().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                recyclerView.setLoadMoreEnabled(!aBoolean);
            }
        });
        wrapper.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                MyApplyBean bean = getViewModel().getPauseApplyList().getValue().get(i);
                MyApplyDetailActivity.start(getContext(), "申请详情", bean.getExamineId());
            }
        });
        getViewModel().updatePauseApplyListFromNet();

        //监听view model，刷新列表
        FragmentActivity activity = getActivity();
        if(activity instanceof ContractPauseHomeActivity){
            ContractPauseHomeViewModel viewModel = new ViewModelProvider(activity).get(ContractPauseHomeViewModel.class);
            viewModel.refreshMyPauseApplyList.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    if(aBoolean){
                        //刷新列表
                        getViewModel().clearPauseApplyList();
                        getViewModel().updatePauseApplyListFromNet();
                    }
                }
            });
        }

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        shouldShowNoData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                getViewDataBinding().fragmentMyApplyListNoData.setIsShow(aBoolean);
            }
        });


    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_my_apply_list;
    }
}
